package com.xiji.cashloan.cl.model.pay.helipay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiji.cashloan.cl.domain.HelipayUser;
import com.xiji.cashloan.cl.mapper.HelipayUserMapper;
import com.xiji.cashloan.cl.model.pay.BasePay;
import com.xiji.cashloan.cl.model.pay.common.constant.PayConstant;
import com.xiji.cashloan.cl.model.pay.helipay.constant.HelipayConstant;
import com.xiji.cashloan.cl.model.pay.helipay.util.*;
import com.xiji.cashloan.cl.model.pay.helipay.vo.delegation.*;
import com.xiji.cashloan.cl.model.pay.helipay.vo.request.AgreementBindCardValidateCodeVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.request.BindCardPayVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.request.BindCardVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.request.PayForReqVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.request.QueryOrderVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.request.UnBindCardVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.response.AgreementSendValidateCodeResponseVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.response.BindCardPayResponseVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.response.BindCardResponseVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.response.HeliPayForPaymentQueryResponseVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.response.HeliPayForPaymentResultVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.response.QueryOrderResponseVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.response.UnBindCardResponseVo;
import com.xiji.cashloan.cl.util.black.JSONUtil;
import com.xiji.cashloan.core.common.util.HttpsUtil;
import com.xiji.cashloan.core.common.util.OrderNoUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @Auther: king
 * @Date: 2019/1/24 16:51
 * @Description:
 */
public class HelipayHelper extends BasePay {

    public static final Logger logger = LoggerFactory.getLogger(HelipayHelper.class);

    private static final String tempDir = System.getProperty("java.io.tmpdir");

    @Resource
    private HelipayUserMapper helipayUserMapper;

    static {
        if (tempDir == null) {
            throw new IllegalStateException("没设置系统临时文件存储路径");
        }
    }

    /**
     * 放款支付
     * @param reqVo
     * @return
     */
    public HeliPayForPaymentResultVo payment(PayForReqVo reqVo) {
        HeliPayForPaymentResultVo result = new HeliPayForPaymentResultVo();
        reqVo.setCustomerNumber(HelipayUtil.customerNumber());
        reqVo.setBizType(HelipayConstant.BTYPE_Transfer);
        Map<String, String> reqestMap = new HashMap();
        try {
            reqestMap = reqVo.paymentParams();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        String resp = requestTransfer(reqestMap,reqVo.getBizType(),reqVo.getOrderId());
        if (isException(resp)) {
            result.setRt2_retCode(resp);
        } else if (!JSONUtil.isJsonObject(resp)){
            result.setRt2_retCode(PayConstant.REQ_ERROR_CODE_10);
            result.setRt3_retMsg(resp);
        }else {
            result = JSONObject.parseObject(resp, HeliPayForPaymentResultVo.class);
        }

        modifyReqLog(reqVo.getOrderId(), resp);
        return result;
    }

    /**
     * 放款支付查询
     * @param reqVo
     * @return
     */
    public HeliPayForPaymentQueryResponseVo queryPayment(PayForReqVo reqVo) {
        HeliPayForPaymentQueryResponseVo result = new HeliPayForPaymentQueryResponseVo();
        reqVo.setCustomerNumber(HelipayUtil.customerNumber());
        reqVo.setBizType(HelipayConstant.BTYPE_TransferQuery);
        String orderNo = OrderNoUtil.getSerialNumber();
        Map<String, String> reqestMap = new HashMap();
        try {
            reqestMap = reqVo.payQueryParams();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        String resp = requestTransfer(reqestMap,reqVo.getBizType(),orderNo);
        if (isException(resp)) {
            result.setRt2_retCode(resp);
        } else if (!JSONUtil.isJsonObject(resp)){
            result.setRt2_retCode(PayConstant.REQ_ERROR_CODE_10);
            result.setRt3_retMsg(resp);
        }else  {
            result = JSONObject.parseObject(resp, HeliPayForPaymentQueryResponseVo.class);;
        }

        modifyReqLog(orderNo, resp);
        return result;
    }

    // ------------------------------------

    /**
     * 1、绑卡发短信接口
     * 2、绑卡接口
     * 3、绑卡支付接口
     * 4、支付查询接口
     * 5、解除绑卡接口
     * 6、用户绑定银行卡信息查询（仅限于交易卡）
     */

    /**
     * 绑卡短信
     * @param reqVo
     * @return
     */
    public AgreementSendValidateCodeResponseVo bindMsg(AgreementBindCardValidateCodeVo reqVo) {
        AgreementSendValidateCodeResponseVo result = new AgreementSendValidateCodeResponseVo();
        reqVo.setP1_bizType(HelipayConstant.BTYPE_AGREEMENT_PAYCODE);
        reqVo.setP2_customerNumber(HelipayUtil.customerNumber());
        Map reqestMap = new HashMap();
        try {
            reqestMap = MessageHandle.getReqestMap(reqVo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        String resp = request(reqestMap,"sendBingCardCode",reqVo.getP4_orderId());
        if (isException(resp)) {
            result.setRt2_retCode(resp);
        } else if (!JSONUtil.isJsonObject(resp)){
            result.setRt2_retCode(PayConstant.REQ_ERROR_CODE_10);
            result.setRt3_retMsg(resp);
        }else {
            result = JSONObject.parseObject(resp, AgreementSendValidateCodeResponseVo.class);;
        }

        modifyReqLog(reqVo.getP4_orderId(), resp);
        return result;
    }

    /**
     * 解除绑卡
     * @param reqVo
     * @return
     */
    public UnBindCardResponseVo unbind(UnBindCardVo reqVo){
        UnBindCardResponseVo result = new UnBindCardResponseVo();
        reqVo.setP1_bizType(HelipayConstant.BTYPE_BankCardUnbind);
        reqVo.setP2_customerNumber(HelipayUtil.customerNumber());
        String orderNo = HelipayUtil.getOrderId();
        Map reqestMap = new HashMap();
        try {
            reqestMap = MessageHandle.getReqestMap(reqVo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        String resp = request(reqestMap,reqVo.getP1_bizType(),orderNo);
        if (isException(resp)) {
            result.setRt2_retCode(resp);
        } else if (!JSONUtil.isJsonObject(resp)){
            result.setRt2_retCode(PayConstant.REQ_ERROR_CODE_10);
            result.setRt3_retMsg(resp);
        }else {
            result = JSONObject.parseObject(resp, UnBindCardResponseVo.class);;
        }

        modifyReqLog(orderNo, resp);
        return result;
    }

    /**
     * 绑卡
     * @param reqVo
     * @return
     */
    public BindCardResponseVo bindCommit(BindCardVo reqVo) {
        BindCardResponseVo result = new BindCardResponseVo();
        reqVo.setP1_bizType(HelipayConstant.BTYPE_QuickPayBindCard);
        reqVo.setP2_customerNumber(HelipayUtil.customerNumber());
        String orderNo = HelipayUtil.getOrderId();
        Map reqestMap = new HashMap();
        try {
            reqestMap = MessageHandle.getReqestMap(reqVo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        String resp = request(reqestMap,reqVo.getP1_bizType(),orderNo);
        if (isException(resp)) {
            result.setRt2_retCode(resp);
        } else if (!JSONUtil.isJsonObject(resp)){
            result.setRt2_retCode(PayConstant.REQ_ERROR_CODE_10);
            result.setRt3_retMsg(resp);
        }else  {
            result = JSONObject.parseObject(resp, BindCardResponseVo.class);;
        }

        modifyReqLog(orderNo, resp);
        return result;
    }

    /**
     * 还款支付
     * @param reqVo
     * @return
     */
    public BindCardPayResponseVo repayment(BindCardPayVo reqVo) {
        BindCardPayResponseVo result = new BindCardPayResponseVo();
        reqVo.setP1_bizType(HelipayConstant.BTYPE_QuickPayBindPay);
        reqVo.setP2_customerNumber(HelipayUtil.customerNumber());
        Map reqestMap = new HashMap();
        try {
            reqestMap = MessageHandle.getReqestMap(reqVo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        String resp = request(reqestMap,reqVo.getP1_bizType(),reqVo.getP5_orderId());
        if (isException(resp)) {
            result.setRt2_retCode(resp);
        } else if (!JSONUtil.isJsonObject(resp)){
            result.setRt2_retCode(PayConstant.REQ_ERROR_CODE_10);
            result.setRt3_retMsg(resp);
        }else  {
            result = JSONObject.parseObject(resp, BindCardPayResponseVo.class);;
        }

        modifyReqLog(reqVo.getP5_orderId(), resp);
        return result;
    }

    /**
     * 还款支付查询
     * @param reqVo
     * @return
     */
    public QueryOrderResponseVo queryOrder(QueryOrderVo reqVo){
        QueryOrderResponseVo result = new QueryOrderResponseVo();
        reqVo.setP1_bizType(HelipayConstant.BTYPE_QuickPayQuery);
        reqVo.setP3_customerNumber(HelipayUtil.customerNumber());
        String orderNo = HelipayUtil.getOrderId();
        Map reqestMap = new HashMap();
        try {
            reqestMap = MessageHandle.getReqestMap(reqVo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        String resp = request(reqestMap,reqVo.getP1_bizType(),orderNo);
        if (isException(resp)) {
            result.setRt2_retCode(resp);
        } else if (!JSONUtil.isJsonObject(resp)){
            result.setRt2_retCode(PayConstant.REQ_ERROR_CODE_10);
            result.setRt3_retMsg(resp);
        }else  {
            result = JSONObject.parseObject(resp, QueryOrderResponseVo.class);;
        }

        modifyReqLog(orderNo, resp);
        return result;
    }


    /**
     * 商户用户注册
     * @param userVo
     * @return
     */
    public Map<String,String> heLiPayUserRegister(MerchantUserVo userVo,Long userId) {
        //信息域加密
        if (StringUtils.isNotBlank(userVo.getP5_legalPersonID())) {
            userVo.setP5_legalPersonID(Des3Encryption.encode(HelipayConstant.DESKEY_KEY, userVo.getP5_legalPersonID()));
        }
        if (StringUtils.isNotBlank(userVo.getP6_mobile())) {
            userVo.setP6_mobile(Des3Encryption.encode(HelipayConstant.DESKEY_KEY, userVo.getP6_mobile()));
        }
        // ModelAndView mav = new ModelAndView();
         Map<String,String> result = new HashMap<>();
        // mav.setViewName("entrustedLoanApi/response");
        try {
            Map<String, String> map = MyBeanUtils.convertBean(userVo, new LinkedHashMap());
            String oriMessage = MyBeanUtils.getSigned(map, null);
            logger.info("签名原文串：" + oriMessage);
            String sign = RSA.sign(oriMessage.trim(), RSA.getPrivateKey(HelipayUtil.transferKey()));
            logger.info("签名串：" + sign);
            map.put("sign", sign);
            logger.info("发送参数：" + map);
            // 保存请求参数
            request(map,userVo.getP1_bizType(),userVo.getP3_orderId());
            Map<String, Object> resultMap = HttpClientService.getHttpResp(map, HelipayUtil.DelegationUrl(), null);
            logger.info("响应结果：" + resultMap);
            if ((Integer) resultMap.get("statusCode") == HttpStatus.SC_OK) {
                String resultMsg = (String) resultMap.get("response");
                MerchantUserResVo merchantUserResVo = JSONObject.parseObject(resultMsg, MerchantUserResVo.class);
                String assemblyRespOriSign = MyBeanUtils.getSigned(merchantUserResVo, null);
                logger.info("组装返回结果签名串：" + assemblyRespOriSign);
                String responseSign = merchantUserResVo.getSign();
                logger.info("响应签名：" + responseSign);
                String checkSign = Disguiser.disguiseMD5(assemblyRespOriSign.trim()+HelipayUtil.split+HelipayUtil.getMD5Key());
                if (checkSign.equals(responseSign)) {
                    if ("0000".equals(merchantUserResVo.getRt2_retCode())) {
                        HelipayUser helipayUser = new HelipayUser();
                        helipayUser.setHelipayUserId(merchantUserResVo.getRt6_userId());
                        helipayUser.setUserStatus(merchantUserResVo.getRt7_userStatus());
                        helipayUser.setUserId(userId);
                        helipayUserMapper.save(helipayUser);
                        result.put("code","success");
                        result.put("message","开户成功");
                    } else {
                        result.put("message",merchantUserResVo.getRt3_retMsg());
                    }
                } else {
                    logger.error("验签失败：userVo ==>" + userVo);
                    result.put("message","开户失败");
                }
            } else {
                logger.error("请求失败 ：userVo ==>" + userVo);
                result.put("message","开户失败");
            }
            // 更新请求结果
            modifyReqLog(userVo.getP3_orderId(),resultMap.toString());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("交易异常 ：userVo ==>" + userVo);
            result.put("message","开户失败");
        }
        return result;
    }

    /**
     * 商户用户注册信息查询
     * @param userVo
     * @return
     */
    public MerchantUserQueryResVo userQuery(MerchantUserQueryVo userVo){
        MerchantUserQueryResVo resVo = null;
        // ModelAndView mav = new ModelAndView();
        // mav.setViewName("entrustedLoanApi/response");
        try {
            if (StringUtils.isNotBlank(userVo.getP6_legalPersonID())) {
                userVo.setP6_legalPersonID(Des3Encryption.encode(HelipayConstant.DESKEY_KEY, userVo.getP6_legalPersonID()  ));
            }
            Map<String, String> map = MyBeanUtils.convertBean(userVo, new LinkedHashMap());
            String[] excludes = {"P6_legalPersonID"};
            String oriMessage = MyBeanUtils.getSigned(map, excludes);
            logger.info("签名原文串：" + oriMessage);
            String sign = RSA.sign(oriMessage.trim(), RSA.getPrivateKey(HelipayUtil.transferKey()));;
            logger.info("签名串：" + sign);
            map.put("sign", sign);
            logger.info("发送参数：" + map);
            // 保存请求参数
            request(map,userVo.getP1_bizType(),userVo.getP3_orderId());
            Map<String, Object> resultMap = HttpClientService.getHttpResp(map, HelipayUtil.DelegationUrl(), null);
            logger.info("响应结果：" + resultMap);
            System.out.println(resultMap);
            if ((Integer) resultMap.get("statusCode") == HttpStatus.SC_OK) {
                String resultMsg = (String) resultMap.get("response");
                resVo = JSONObject.parseObject(resultMsg, MerchantUserQueryResVo.class);
                String assemblyRespOriSign = MyBeanUtils.getSigned(resVo, null);
                logger.info("组装返回结果签名串：" + assemblyRespOriSign);
                String responseSign = resVo.getSign();
                logger.info("响应签名：" + responseSign);
                String checkSign = Disguiser.disguiseMD5(assemblyRespOriSign.trim()+HelipayUtil.split+HelipayUtil.getMD5Key());
                if (checkSign.equals(responseSign)) {
                    // mav.addObject("message", resVo.getRt3_retMsg());
                    // mav.addObject("json", JSONObject.parseObject(resultMsg));
                    logger.info("商户用户注册信息查询验签成功");
                } else {
                    // mav.addObject("message", "验签失败");
                    // mav.addObject("json", resultMsg);
                    logger.error("商户用户注册信息查询验签失败,userVo ==>"+userVo);
                }
            } else {
                // mav.addObject("message", "请求失败");
                logger.error("商户用户注册信息查询请求失败,userVo ==>"+userVo);
            }
            modifyReqLog(userVo.getP3_orderId(),resultMap.toString());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("商户用户注册信息查询交易异常,userVo ==>"+userVo);
            // mav.addObject("message", "交易异常：" + e.getMessage());
        }
        return resVo;
    }

    /**
     * 商户用户资质上传
     * @param userVo
     * @param file
     * @return
     */
    public MerchantUserUploadResVo userUpload(MerchantUserUploadVo userVo,MultipartFile file,String credentialType,Long helipayId) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("entrustedLoanApi/response");
        MerchantUserUploadResVo resVo = null;
        try {
            File tempFile = new File(tempDir, file.getOriginalFilename());
            file.transferTo(tempFile);
            // 文件签名
            try (InputStream is = new FileInputStream(tempFile)){
                userVo.setP7_fileSign(DigestUtils.md5DigestAsHex(is));
            }
            Map<String, String> map = MyBeanUtils.convertBean(userVo, new LinkedHashMap());
            String oriMessage = MyBeanUtils.getSigned(map, null);
            logger.info("签名原文串：" + oriMessage);
            String sign = RSA.sign(oriMessage.trim(), RSA.getPrivateKey(HelipayUtil.transferKey()));
            logger.info("签名串：" + sign);
            map.put("sign", sign);
            logger.info("发送参数：" + map);
            // 保存请求参数
            request(map,userVo.getP1_bizType(),userVo.getP3_orderId());
            Map resultMap = HttpClientService.getHttpResp(map, HelipayUtil.QualificationUploadUrl(), tempFile);
            logger.info("响应结果：" + resultMap);
            if ((Integer) resultMap.get("statusCode") == HttpStatus.SC_OK) {
                String resultMsg = (String) resultMap.get("response");
                resVo = JSONObject.parseObject(resultMsg, MerchantUserUploadResVo.class);
                String assemblyRespOriSign = MyBeanUtils.getSigned(resVo, null);
                logger.info("组装返回结果签名串：" + assemblyRespOriSign);
                String responseSign = resVo.getSign();
                logger.info("响应签名：" + responseSign);
                String checkSign = Disguiser.disguiseMD5(assemblyRespOriSign.trim()+HelipayUtil.split+HelipayUtil.getMD5Key());
                if (checkSign.equals(responseSign)) {

                    Map<String,Object> params = new HashMap<>();
                    params.put("id",helipayId);
                    params.put("gmtModified",new Date());
                    HelipayUser helipayUser = new HelipayUser();
                    helipayUser.setId(helipayId);
                    if ("FRONT".equals(credentialType)){
                        helipayUser.setFrontCredentialStatus(resVo.getRt7_credentialStatus());
                        params.put("frontCredentialStatus",resVo.getRt7_credentialStatus());
                    }else {
                        helipayUser.setBackCredentialStatus(resVo.getRt7_credentialStatus());
                        params.put("backCredentialStatus",resVo.getRt7_credentialStatus());
                    }
                    logger.info("商户用户资质上传验签成功");
                    // 资质上传状态更新
                    helipayUserMapper.updateSelective(params);
                    //mav.addObject("message", resVo.getRt3_retMsg());
                    //mav.addObject("json", JSONObject.parseObject(resultMsg));
                } else {
                    logger.error("商户用户资质上传验签失败,userVo ==>"+userVo);
                }
            } else {
                logger.error("商户用户资质上传请求失败,userVo ==>"+userVo);
            }
            // 更新请求结果
            modifyReqLog(userVo.getP3_orderId(),resultMap.toString());
        } catch (Exception e) {
            logger.error("商户用户资质上传交易异常,userVo ==>"+userVo);
        }
        return resVo;
    }

    /**
     * 用户资质查询
     * @param userVo
     * @param model
     * @return
     */
    public ModelAndView userUploadQuery(MerchantUserUploadVo userVo, Model model) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("entrustedLoanApi/response");
        try {
            String[] excludes = {"P7_fileSign"};
            Map<String, String> map = MyBeanUtils.convertBean(userVo, new LinkedHashMap());
            String oriMessage = MyBeanUtils.getSigned(map, null);
            logger.info("签名原文串：" + oriMessage);
            String sign = RSA.sign(oriMessage.trim(), RSA.getPrivateKey(HelipayUtil.transferKey()));;
            logger.info("签名串：" + sign);
            map.put("sign", sign);
            logger.info("发送参数：" + map);
            // 保存请求参数
            request(map,userVo.getP1_bizType(),userVo.getP3_orderId());
            Map<String, Object> resultMap = HttpClientService.getHttpResp(map, HelipayUtil.DelegationUrl(), null);
            logger.info("响应结果：" + resultMap);
            if ((Integer) resultMap.get("statusCode") == HttpStatus.SC_OK) {
                String resultMsg = (String) resultMap.get("response");
                MerchantUserUploadResVo resVo = JSONObject.parseObject(resultMsg, MerchantUserUploadResVo.class);
                String assemblyRespOriSign = MyBeanUtils.getSigned(resVo, null);
                logger.info("组装返回结果签名串：" + assemblyRespOriSign);
                String responseSign = resVo.getSign();
                logger.info("响应签名：" + responseSign);
                String checkSign = Disguiser.disguiseMD5(assemblyRespOriSign.trim()+HelipayUtil.split+HelipayUtil.getMD5Key());
                if (checkSign.equals(responseSign)) {
                    logger.info("用户资质查询验签成功");
                } else {
                    logger.error("用户资质查询验签失败,userVo ==>"+userVo);
                }
            } else {
                logger.error("用户资质查询请求失败,userVo ==>"+userVo);
            }
            // 更新请求结果
            modifyReqLog(userVo.getP3_orderId(),resultMap.toString());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("用户资质查询交易异常,userVo ==>"+userVo);
        }
        return mav;
    }

    /**
     * 创建委托代付订单
     * @param orderVo
     * @return
     */
    public OrderResVo createOrder(OrderVo orderVo) {
        // ModelAndView mav = new ModelAndView();
        if (StringUtils.isNotBlank(orderVo.getP10_bankAccountNo())) {
            orderVo.setP10_bankAccountNo(Des3Encryption.encode(HelipayConstant.DESKEY_KEY, orderVo.getP10_bankAccountNo()));
        }
        if (StringUtils.isNotBlank(orderVo.getP11_legalPersonID())) {
            orderVo.setP11_legalPersonID(Des3Encryption.encode(HelipayConstant.DESKEY_KEY, orderVo.getP11_legalPersonID()));
        }
        if (StringUtils.isNotBlank(orderVo.getP12_mobile())) {
            orderVo.setP12_mobile(Des3Encryption.encode(HelipayConstant.DESKEY_KEY, orderVo.getP12_mobile()));
        }
        if (StringUtils.isNotBlank(orderVo.getP14_year())) {
            orderVo.setP14_year(Des3Encryption.encode(HelipayConstant.DESKEY_KEY, orderVo.getP14_year()));
        }
        if (StringUtils.isNotBlank(orderVo.getP15_month())) {
            orderVo.setP15_month(Des3Encryption.encode(HelipayConstant.DESKEY_KEY, orderVo.getP15_month()));
        }
        if (StringUtils.isNotBlank(orderVo.getP16_cvv2())) {
            orderVo.setP16_cvv2(Des3Encryption.encode(HelipayConstant.DESKEY_KEY, orderVo.getP16_cvv2()));
        }
        // mav.setViewName("entrustedLoanApi/response");
        OrderResVo resVo = null;
        try {
            Map<String, String> map = MyBeanUtils.convertBean(orderVo, new LinkedHashMap());
            String oriMessage = MyBeanUtils.getSigned(map, null);
            logger.info("签名原文串：" + oriMessage);
            String sign = RSA.sign(oriMessage.trim(), RSA.getPrivateKey(HelipayUtil.transferKey()));;
            logger.info("签名串：" + sign);
            map.put("sign", sign);
            logger.info("发送参数：" + map);
            // 保存请求参数
            request(map,orderVo.getP1_bizType(),orderVo.getP3_orderId());
            Map<String, Object> resultMap = HttpClientService.getHttpResp(map, HelipayUtil.DelegationUrl(), null);
            logger.info("响应结果：" + resultMap);
            System.out.println(resultMap);
            if ((Integer) resultMap.get("statusCode") == HttpStatus.SC_OK) {
                String resultMsg = (String) resultMap.get("response");
                resVo = JSONObject.parseObject(resultMsg, OrderResVo.class);
                String assemblyRespOriSign = MyBeanUtils.getSigned(resVo, null);
                logger.info("组装返回结果签名串：" + assemblyRespOriSign);
                String responseSign = resVo.getSign();
                logger.info("响应签名：" + responseSign);
                String checkSign = Disguiser.disguiseMD5(assemblyRespOriSign.trim()+HelipayUtil.split+HelipayUtil.getMD5Key());
                if (checkSign.equals(responseSign)) {
                    logger.info("创建委托代付订单验签成功");
                    resVo.setSignResult("success");
                } else {
                    logger.error("创建委托代付订单验签失败,orderVo ==>"+orderVo);
                    resVo.setSignResult("fail");
                }
            } else {
                logger.error("创建委托代付订单请求失败,orderVo ==>"+orderVo);
            }
            // 更新请求结果
            modifyReqLog(orderVo.getP3_orderId(),resultMap.toString());
        } catch (Exception e) {
            logger.error("创建委托代付订单交易异常,orderVo ==>"+orderVo);
        }
        return resVo;
    }

    /**
     * 委托代付订单查询
     * @param orderVo
     * @return
     */
    public OrderQueryResVo orderQuery(OrderQueryVo orderVo) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("entrustedLoanApi/response");
        OrderQueryResVo resVo = null;
        try {
            Map<String, String> map = MyBeanUtils.convertBean(orderVo, new LinkedHashMap());
            String oriMessage = MyBeanUtils.getSigned(map, null);
            logger.info("签名原文串：" + oriMessage);
            String sign = RSA.sign(oriMessage.trim(), RSA.getPrivateKey(HelipayUtil.transferKey()));;
            logger.info("签名串：" + sign);
            map.put("sign", sign);
            logger.info("发送参数：" + map);
            // 保存请求参数
            request(map,orderVo.getP1_bizType(),orderVo.getP3_orderId());
            Map<String, Object> resultMap = HttpClientService.getHttpResp(map, HelipayUtil.DelegationUrl(), null);
            logger.info("响应结果：" + resultMap);
            if ((Integer) resultMap.get("statusCode") == HttpStatus.SC_OK) {
                String resultMsg = (String) resultMap.get("response");
                resVo = JSONObject.parseObject(resultMsg, OrderQueryResVo.class);
                String assemblyRespOriSign = MyBeanUtils.getSigned(resVo, null);
                logger.info("组装返回结果签名串：" + assemblyRespOriSign);
                String responseSign = resVo.getSign();
                logger.info("响应签名：" + responseSign);
                String checkSign = Disguiser.disguiseMD5(assemblyRespOriSign.trim()+HelipayUtil.split+HelipayUtil.getMD5Key());
                if (checkSign.equals(responseSign)) {
                    logger.info("查询委托代付订单验签成功");
                    //mav.addObject("message", resVo.getRt3_retMsg());
                    //mav.addObject("json", JSONObject.parseObject(resultMsg));
                } else {
                    logger.error("查询委托代付订单验签失败,orderVo ==>"+orderVo);
                    //mav.addObject("message", "验签失败");
                }
            } else {
                logger.error("查询委托代付订单请求失败,orderVo ==>"+orderVo);
                //mav.addObject("message", "请求失败");
            }
            // 更新请求结果
            modifyReqLog(orderVo.getP3_orderId(),resultMap.toString());
        } catch (Exception e) {
            // e.printStackTrace();
            //mav.addObject("message", "交易异常：" + e.getMessage());
            logger.error("查询委托代付订单交易异常,orderVo ==>"+orderVo);
        }
        return resVo;
    }

    //----------------------------------------------------------------------

    private String requestTransfer(Map reqestMap,String bizType,String orderId) {
        String resp = "";
        saveReqLog(bizType, orderId, "", JSON.toJSONString(reqestMap));
        try {
            if (HelipayUtil.switchHelipay()) {
                resp = HttpsUtil.postClient(HelipayUtil.transferUrl(), reqestMap);
            } else {
                if (logger.isDebugEnabled()) {
                    logger.debug("关闭支付,模拟返回结果");
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return resp;
    }

    private String request(Map reqestMap,String bizType,String orderId) {
        String resp = "";
        saveReqLog(bizType, orderId, "", JSON.toJSONString(reqestMap));
        try {
            if (HelipayUtil.switchHelipay()) {
                resp = HttpsUtil.postClient(HelipayUtil.quickPayUrl(), reqestMap);
            } else {
                if (logger.isDebugEnabled()) {
                    logger.debug("关闭支付,模拟返回结果");
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return resp;
    }
    @Override
    public void modifyReqLog(String orderNo, String resp){
        super.modifyReqLog(orderNo,resp);
    }
}

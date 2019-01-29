package com.xiji.cashloan.cl.model.pay.helipay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiji.cashloan.cl.model.pay.BasePay;
import com.xiji.cashloan.cl.model.pay.common.constant.PayConstant;
import com.xiji.cashloan.cl.model.pay.helipay.constant.HelipayConstant;
import com.xiji.cashloan.cl.model.pay.helipay.util.HelipayUtil;
import com.xiji.cashloan.cl.model.pay.helipay.util.MessageHandle;
import com.xiji.cashloan.cl.model.pay.helipay.vo.request.AgreementBindCardValidateCodeVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.request.BindCardPayVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.request.BindCardVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.request.PayForReqVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.request.QueryOrderVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.request.UnBindCardVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.response.AgreementSendValidateCodeResponseVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.response.BindCardPayResponseVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.response.BindCardResponseVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.response.HeliPayForPaymentResultVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.response.QueryOrderResponseVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.response.UnBindCardResponseVo;
import com.xiji.cashloan.cl.util.black.JSONUtil;
import com.xiji.cashloan.core.common.util.HttpsUtil;
import com.xiji.cashloan.core.common.util.OrderNoUtil;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Auther: king
 * @Date: 2019/1/24 16:51
 * @Description:
 */
public class HelipayHelper extends BasePay {

    public static final Logger logger = LoggerFactory.getLogger(HelipayHelper.class);

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
            result = JSONObject.parseObject(resp, HeliPayForPaymentResultVo.class);;
        }

        modifyReqLog(reqVo.getOrderId(), resp);
        return result;
    }

    /**
     * 放款支付查询
     * @param reqVo
     * @return
     */
    public HeliPayForPaymentResultVo queryPayment(PayForReqVo reqVo) {
        HeliPayForPaymentResultVo result = new HeliPayForPaymentResultVo();
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
            result = JSONObject.parseObject(resp, HeliPayForPaymentResultVo.class);;
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

package com.xiji.cashloan.cl.model.pay.kuaiqian.agreement;

import com.alibaba.fastjson.JSON;
import com.xiji.cashloan.cl.domain.BankCard;
import com.xiji.cashloan.cl.domain.PayReqLog;
import com.xiji.cashloan.cl.model.pay.BasePay;
import com.xiji.cashloan.cl.model.pay.fuiou.constant.FuiouConstant;
import com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.constant.KuaiqianPayConstant;
import com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.util.Post;
import com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.vo.TransInfo;
import com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.vo.request.BindXmlBeanReq;
import com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.vo.response.BindXmlBeanResp;
import com.xiji.cashloan.cl.service.BankCardService;
import com.xiji.cashloan.cl.service.PayReqLogService;
import com.xiji.cashloan.core.common.context.Global;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tool.util.BeanUtil;
import tool.util.DateUtil;
import tool.util.NumberUtil;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;


public class KuaiqianpayHelper extends BasePay {

    public static final Logger logger = LoggerFactory.getLogger(KuaiqianpayHelper.class);

    /**
     * 获取验证码
     * @param reqVo
     * @return
     */
    public BindXmlBeanResp bindMsg(BindXmlBeanReq reqVo) {
        BindXmlBeanResp bindResult = new BindXmlBeanResp();
        reqVo.setVersion(KuaiqianPayConstant.PROTOCOL_VERSION);
        reqVo.setMerchantId(Global.getValue("kuaiqian_protocol_merchantId"));
        reqVo.setTerminalId(Global.getValue("kuaiqian_protocol_terminalId"));

        TransInfo transInfo = new TransInfo();
        //设置手机动态鉴权节点
        transInfo.setRecordeText_1("indAuthContent");
        transInfo.setRecordeText_2("ErrorMsgContent");

        //Tr1报文拼接
        String str1Xml = "";

        str1Xml += "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
        str1Xml += "<MasMessage xmlns=\"http://www.99bill.com/mas_cnp_merchant_interface\">";
        str1Xml += "<version>"+reqVo.getVersion()+"</version>";
        str1Xml += "<indAuthContent>";
        str1Xml += "<merchantId>" + reqVo.getMerchantId() + "</merchantId>";
        str1Xml += "<terminalId>" + reqVo.getTerminalId()+ "</terminalId>";
        str1Xml += "<customerId>" + reqVo.getCustomerId()+ "</customerId>";
        str1Xml += "<externalRefNumber>" + reqVo.getExternalRefNumber()+ "</externalRefNumber>";
        str1Xml += "<pan>" + reqVo.getPan() + "</pan>";
        str1Xml += "<cardHolderName>" + reqVo.getCardHolderName() + "</cardHolderName>";
        str1Xml += "<idType>" + reqVo.getIdType() + "</idType>";
        str1Xml += "<cardHolderId>" + reqVo.getCardHolderId() + "</cardHolderId>";
        if(!"".equals(reqVo.getExpiredDate()) && !"".equals(reqVo.getCvv2())){
            str1Xml += "<expiredDate>" + reqVo.getExpiredDate() + "</expiredDate>";
            str1Xml += "<cvv2>" + reqVo.getCvv2() + "</cvv2>";
        }
        str1Xml += "<phoneNO>" + reqVo.getPhoneNO() + "</phoneNO>";
        str1Xml += " <bindType>0</bindType>";
        str1Xml += "</indAuthContent>";
        str1Xml += "</MasMessage>";

        System.out.println("tr1报文  str1Xml = " + str1Xml);

        String url = Global.getValue("kq_Captcha_test_url");    //测试环境地址
        //String url = "kq_Captcha_production_url";     //生产环境地址
        String signStr = reqVo.sendMsgSignStr();
        saveReqLog(KuaiqianPayConstant.PROTOCOL_BINDMSG, reqVo.getExternalRefNumber(), signStr, JSON.toJSONString(reqVo));

        HashMap respXml=null;
        try{
             respXml = Post.sendPost(url,str1Xml,transInfo);
            //TR2接收的数据
            System.out.println("respXml = " + respXml);

            if(respXml==null) {
                logger.error("读取内容出错");
            } else {
                bindResult=map2Bean(respXml,BindXmlBeanResp.class);
                //如果TR2获取的应答码responseCode的值为00时，成功
                if("00".equals((String)respXml.get("responseCode"))) {
                    String token = respXml.get("token").toString();
                    updateReqLog(reqVo.getExternalRefNumber(), respXml.toString(),token);
                    logger.info("卡信息验证交易成功");
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }

        return bindResult;
    }


    /**
     * 更新返回数据
     *
     * @param orderNo
     * @param resp
     */
    protected void updateReqLog(String orderNo, String resp,String token) {
        PayReqLogService payReqLogService = (PayReqLogService) BeanUtil.getBean("payReqLogService");
        PayReqLog reqLog = payReqLogService.findByOrderNo(orderNo);
        if (null == reqLog) {
            return;
        }
        reqLog.setReturnParams(resp);
        reqLog.setToken(token);
        reqLog.setReturnTime(DateUtil.getNow());
        payReqLogService.updateById(reqLog);
    }
    /**
     *
     *
     * Map转换层Bean。
     * @param <T>
     * @param map
     * @param class1
     * @return
     */
    public static <T> T map2Bean(Map<String, String> map, Class<T> class1) {
        T bean = null;
        try {
            bean = class1.newInstance();
            BeanUtils.populate(bean, map);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }


}

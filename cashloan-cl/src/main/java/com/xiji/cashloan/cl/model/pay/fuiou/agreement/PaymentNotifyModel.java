package com.xiji.cashloan.cl.model.pay.fuiou.agreement;

import com.fuiou.util.MD5;
import com.xiji.cashloan.cl.model.pay.fuiou.constant.FuiouConstant;
import com.xiji.cashloan.core.common.util.StringUtil;

/**
 * @Auther: king
 * @ss 支付异步通知model
 * @Date: 2018/11/28 10:41
 * @Description:
 */
public class PaymentNotifyModel {
    private String version;//VERSION
    private String type;//TYPE
    private String mchntCd;//商户号
    private String userId;//用户编号
    private String responseCode;//0000 成功
    private String responseMsg;//响应中文 述,支付成功
    private String orderId;//富友订单
    private String mchntOrderId;//商户订单号
    private String amt;//交易金额
    private String bankCard;//银行卡号
    private String protocolNo;//协议号
    /**
     * md5(TYPE+"|"+VERSION+"|"+RESPONSECODE+"|"+"MCHNTCD"
     * +"|"MCHNTORDERID+"|"+ORDE RID"|"+AMT+"|"+BANKCARD+"|"+"商户 32 位密钥 key")
     */
    private String sign;//SIGN

    public String getMchntCd() {
        return mchntCd;
    }

    public void setMchntCd(String mchntCd) {
        this.mchntCd = mchntCd;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMchntOrderId() {
        return mchntOrderId;
    }

    public void setMchntOrderId(String mchntOrderId) {
        this.mchntOrderId = mchntOrderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProtocolNo() {
        return protocolNo;
    }

    public void setProtocolNo(String protocolNo) {
        this.protocolNo = protocolNo;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    private String signReturnMsg(String key) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(type)
            .append("|")
            .append(version)
            .append("|")
            .append(responseCode)
            .append("|")
            .append(mchntCd)
            .append("|")
            .append(mchntOrderId)
            .append("|")
            .append(orderId)
            .append("|")
            .append(amt)
            .append("|")
            .append(bankCard)
            .append("|")
            .append(key);
        return buffer.toString();
    }

    public boolean checkSign(String key) {
        return StringUtil.equalsIgnoreCase(MD5.MD5Encode(this.signReturnMsg(key)),sign);
    }

    public boolean checkReturn() {
        return tool.util.StringUtil.equals(responseCode, FuiouConstant.RESPONSE_SUCCESS_CODE);
    }
}

package com.xiji.cashloan.cl.model.pay.chanpay.agreement.vo.response;

import com.xiji.cashloan.cl.model.pay.chanpay.constant.ChanPayConstant;
import org.nuxeo.common.xmap.annotation.XNode;
import tool.util.StringUtil;

public class BindCardVerifyRespVo {
    @XNode("TRXID")
    private String trxId;//订单号
    @XNode("ORDERTRXID")
    private String orderTrxid;//畅捷流水号
    @XNode("STATUS")
    private String status;//鉴权状态
    @XNode("RETCODE")
    private String retCode;//业务返回码
    @XNode("RETMSG")
    private String retMsg;//返回描述
    @XNode("APPRETCODE")
    private String appRetcode;//应用返回码
    @XNode("APPRETMSG")
    private String appRetMsg;//应用返回描述
    @XNode("EXTENSION")
    private String extension;//扩展字段

    //公共字段
    private String partnerId; //商户号
    private String inputCharset; //参数编码字符集
    private String acceptStatus; //网关返回码(S：受理成功F：受理失败 表示接口调用是否成功，并不表明业务处理结果)
    private String tradeDate; //请求的日期
    private String tradeTime; //请求的时间
    private String sign; //签名
    private String signType; //签名方式
    private String memo; //备注

    public String getTrxId() {
        return trxId;
    }

    public void setTrxId(String trxId) {
        this.trxId = trxId;
    }

    public String getOrderTrxid() {
        return orderTrxid;
    }

    public void setOrderTrxid(String orderTrxid) {
        this.orderTrxid = orderTrxid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public String getAppRetcode() {
        return appRetcode;
    }

    public void setAppRetcode(String appRetcode) {
        this.appRetcode = appRetcode;
    }

    public String getAppRetMsg() {
        return appRetMsg;
    }

    public void setAppRetMsg(String appRetMsg) {
        this.appRetMsg = appRetMsg;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public boolean checkReturn() {
        return StringUtil.equals(retCode, ChanPayConstant.RESPONSE_SUCCESS_CODE);
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getInputCharset() {
        return inputCharset;
    }

    public void setInputCharset(String inputCharset) {
        this.inputCharset = inputCharset;
    }

    public String getAcceptStatus() {
        return acceptStatus;
    }

    public void setAcceptStatus(String acceptStatus) {
        this.acceptStatus = acceptStatus;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "BindCardVerifyRespVo{" +
                "trxId='" + trxId + '\'' +
                ", orderTrxid='" + orderTrxid + '\'' +
                ", status='" + status + '\'' +
                ", retCode='" + retCode + '\'' +
                ", retMsg='" + retMsg + '\'' +
                ", appRetcode='" + appRetcode + '\'' +
                ", appRetMsg='" + appRetMsg + '\'' +
                ", extension='" + extension + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", inputCharset='" + inputCharset + '\'' +
                ", acceptStatus='" + acceptStatus + '\'' +
                ", tradeDate='" + tradeDate + '\'' +
                ", tradeTime='" + tradeTime + '\'' +
                ", sign='" + sign + '\'' +
                ", signType='" + signType + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }
}

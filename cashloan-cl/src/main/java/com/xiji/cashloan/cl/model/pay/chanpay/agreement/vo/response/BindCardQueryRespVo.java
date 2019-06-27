package com.xiji.cashloan.cl.model.pay.chanpay.agreement.vo.response;

import org.nuxeo.common.xmap.annotation.XNode;

public class BindCardQueryRespVo {

    @XNode("TRXID")
    private String trxId;//订单号
    @XNode("MERUSERID")
    private String merUserId;//商户网站用户唯一标识
    @XNode("CARDBEGIN")
    private String cardBegin;//卡号前6位
    @XNode("CARDEND")
    private String cardEnd;//卡号后4位
    @XNode("BANKNAME")
    private String bankName;//卡所属银行名称
    @XNode("BKACCTTP")
    private String bkAcctTp;//卡类型 0失效  1正常 2锁定 3鉴权失效
    @XNode("MOBNO")
    private String mobNo;//持卡人手机号
    @XNode("BINDINGSTATUS")
    private String bindingStatus;//卡状态
    @XNode("MODIFYTIME")
    private String modifyTime;//最后更新时间
    @XNode("STATUS")
    private String status;//业务状态
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

    public String getTrxId() {
        return trxId;
    }

    public void setTrxId(String trxId) {
        this.trxId = trxId;
    }

    public String getMerUserId() {
        return merUserId;
    }

    public void setMerUserId(String merUserId) {
        this.merUserId = merUserId;
    }

    public String getCardBegin() {
        return cardBegin;
    }

    public void setCardBegin(String cardBegin) {
        this.cardBegin = cardBegin;
    }

    public String getCardEnd() {
        return cardEnd;
    }

    public void setCardEnd(String cardEnd) {
        this.cardEnd = cardEnd;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBkAcctTp() {
        return bkAcctTp;
    }

    public void setBkAcctTp(String bkAcctTp) {
        this.bkAcctTp = bkAcctTp;
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }

    public String getBindingStatus() {
        return bindingStatus;
    }

    public void setBindingStatus(String bindingStatus) {
        this.bindingStatus = bindingStatus;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
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

    @Override
    public String toString() {
        return "BindCardQueryRespVo{" +
                "trxId='" + trxId + '\'' +
                ", merUserId='" + merUserId + '\'' +
                ", cardBegin='" + cardBegin + '\'' +
                ", cardEnd='" + cardEnd + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bkAcctTp='" + bkAcctTp + '\'' +
                ", mobNo='" + mobNo + '\'' +
                ", bindingStatus='" + bindingStatus + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                ", status='" + status + '\'' +
                ", retCode='" + retCode + '\'' +
                ", retMsg='" + retMsg + '\'' +
                ", appRetcode='" + appRetcode + '\'' +
                ", appRetMsg='" + appRetMsg + '\'' +
                ", extension='" + extension + '\'' +
                '}';
    }
}

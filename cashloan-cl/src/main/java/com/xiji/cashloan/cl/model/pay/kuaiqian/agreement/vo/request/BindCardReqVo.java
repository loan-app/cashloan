package com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.vo.request;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject(value = "REQUEST")

public class BindCardReqVo {

    @XNode("VERSION")
    private String version;//版本号
    @XNode("MERCHANTID")
    private String merchantId;//商户号
    @XNode("TERMINALID")
    private String terminalId;//终端号
    @XNode("CUSTOMERID")
    private String customerId;//客户号
    @XNode("EXTERNALREFNUMBER")
    private String externalRefNumber;//外部跟踪号
    @XNode("PAN")
    private String Pan;//卡号
    @XNode("PHONENO")
    private String phoneNO; //手机号
    @XNode("VALIDCODE")
    private String validCode; //手机号
    @XNode("TOKEN")
    private String token; //手机号

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getExternalRefNumber() {
        return externalRefNumber;
    }

    public void setExternalRefNumber(String externalRefNumber) {
        this.externalRefNumber = externalRefNumber;
    }

    public String getPan() {
        return Pan;
    }

    public void setPan(String pan) {
        Pan = pan;
    }

    public String getPhoneNO() {
        return phoneNO;
    }

    public void setPhoneNO(String phoneNO) {
        this.phoneNO = phoneNO;
    }

    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}

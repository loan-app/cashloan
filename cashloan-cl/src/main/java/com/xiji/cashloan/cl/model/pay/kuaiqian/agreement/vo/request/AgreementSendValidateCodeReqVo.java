package com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.vo.request;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject(value = "REQUEST")
public class AgreementSendValidateCodeReqVo {

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
    @XNode("CARDHOLDERNAME")
    private String cardHolderName;//持卡人户名
    @XNode("IDTYPE")
    private String idType; //证件类型
    @XNode("CARDHOLDERID")
    private String cardHolderId; //证件号码
    @XNode("EXPIREDDATE")
    private String expiredDate;//有效期
    @XNode("CVV2")
    private String cvv2; //卡校验码
    @XNode("PHONENO")
    private String phoneNO; //手机号


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

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getCardHolderId() {
        return cardHolderId;
    }

    public void setCardHolderId(String cardHolderId) {
        this.cardHolderId = cardHolderId;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getCvv2() {
        return cvv2;
    }

    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }

    public String getPhoneNO() {
        return phoneNO;
    }

    public void setPhoneNO(String phoneNO) {
        this.phoneNO = phoneNO;
    }

}

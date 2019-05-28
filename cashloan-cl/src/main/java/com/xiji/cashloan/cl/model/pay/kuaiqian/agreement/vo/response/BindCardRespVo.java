package com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.vo.response;

import com.xiji.cashloan.cl.model.pay.kuaiqian.constant.KuaiqianPayConstant;
import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;
import tool.util.StringUtil;

@XObject(value = "RESPONSE")

public class BindCardRespVo {
    @XNode("VERSION")
    private String version;		//版本号(1.0)
    @XNode("ERRORCODE")
    private String errorCode;//B.MGW.0001 错误代码
    @XNode("ERRORMESSAGE")
    private String errorMessage;//错误消息
    @XNode("MERCHANTID")
    private String merchantId;//商户号
    @XNode("CUSTOMERID")
    private String customerId;//客户号
    @XNode("EXTERNALREFNUMBER")
    private String externalRefNumber;//外部跟踪 编号
    @XNode("STORABLEPAN")
    private String storablePan;//缩略卡号
    @XNode("PAYTOKEN")
    private String payToken;//支付标记
    @XNode("RESPONSECODE")
    private String responseCode;//应答码
    @XNode("RESPONTEXTMESSAGE")
    private String responseTextMessage;//应答码文 本消息

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
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

    public String getStorablePan() {
        return storablePan;
    }

    public void setStorablePan(String storablePan) {
        this.storablePan = storablePan;
    }

    public String getPayToken() {
        return payToken;
    }

    public void setPayToken(String payToken) {
        this.payToken = payToken;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseTextMessage() {
        return responseTextMessage;
    }

    public void setResponseTextMessage(String responseTextMessage) {
        this.responseTextMessage = responseTextMessage;
    }

    @Override
    public String toString() {
        return "BindCardRespVo{" +
                "version='" + version + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", merchantId='" + merchantId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", externalRefNumber='" + externalRefNumber + '\'' +
                ", storablePan='" + storablePan + '\'' +
                ", payToken='" + payToken + '\'' +
                ", responseCode='" + responseCode + '\'' +
                ", responseTextMessage='" + responseTextMessage + '\'' +
                '}';
    }

    public boolean checkReturn() {
        return StringUtil.equals(responseCode, KuaiqianPayConstant.RESPONSE_SUCCESS_CODE);
    }
}

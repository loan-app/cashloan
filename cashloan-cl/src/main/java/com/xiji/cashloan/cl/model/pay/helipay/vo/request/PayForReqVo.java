package com.xiji.cashloan.cl.model.pay.helipay.vo.request;

import com.xiji.cashloan.cl.model.pay.helipay.util.HelipayUtil;
import com.xiji.cashloan.cl.model.pay.helipay.util.RSA;
import com.xiji.cashloan.core.common.util.StringUtil;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: king
 * @Date: 2019/1/23 20:03
 * @Description:
 */
public class PayForReqVo {

    private String bizType = "";
    private String orderId = "";
    private String customerNumber = "";
    private String amount = "";
    private String bankCode = "";
    private String bankAccountNo = "";
    private String bankAccountName = "";
    private String biz = "";
    private String bankUnionCode = "";
    private String feeType = "";
    private String urgency = "";
    private String summary = "";
    private String notifyUrl = "";
    private String sign = "";

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getBiz() {
        return biz;
    }

    public void setBiz(String biz) {
        this.biz = biz;
    }

    public String getBankUnionCode() {
        return bankUnionCode;
    }

    public void setBankUnionCode(String bankUnionCode) {
        this.bankUnionCode = bankUnionCode;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * 签名
     */
    public String signPayment() throws Exception {
        String split = HelipayUtil.split;
        StringBuffer sb = new StringBuffer();
        sb.append(split).append(StringUtil.trimToEmpty(bizType)).append(split).append(orderId).append(split)
            .append(customerNumber).append(split).append(amount).append(split).append(bankCode)
            .append(split).append(bankAccountNo).append(split).append(bankAccountName);
        sb.append(split).append(biz);
        sb.append(split).append(StringUtil.trimToEmpty(bankUnionCode));
        sb.append(split).append(StringUtil.trimToEmpty(feeType));
        sb.append(split).append(StringUtil.trimToEmpty(urgency));
        sb.append(split).append(StringUtil.trimToEmpty(summary));

        this.sign = RSA.sign(sb.toString(), RSA.getPrivateKey(HelipayUtil.transferKey()));
        return this.sign;
    }

    public Map<String, String> paymentParams() throws Exception {
        Map<String,String> paramters = new HashMap<String,String>();
        paramters.put("P1_bizType",bizType);
        paramters.put("P2_orderId",orderId);
        paramters.put("P3_customerNumber",customerNumber);
        paramters.put("P4_amount",amount);
        paramters.put("P5_bankCode",bankCode);
        paramters.put("P6_bankAccountNo",bankAccountNo);
        paramters.put("P7_bankAccountName",bankAccountName);
        paramters.put("P8_biz",biz);
        paramters.put("P9_bankUnionCode",bankUnionCode);
        paramters.put("P10_feeType",feeType);
        paramters.put("P11_urgency",urgency);
        paramters.put("P12_summary",summary);
        paramters.put("notifyUrl", notifyUrl);
        paramters.put("sign",signPayment());
        return paramters;
    }

    public String signPayQuery() throws Exception {
        String split = HelipayUtil.split;
        StringBuffer sb = new StringBuffer();
        sb.append(split).append(bizType).append(split).append(orderId).append(split)
            .append(customerNumber);

        this.sign = RSA.sign(sb.toString(), RSA.getPrivateKey(HelipayUtil.transferKey()));
        return this.sign;
    }

    public Map<String, String> payQueryParams() throws Exception {
        Map<String,String> paramters = new HashMap<String,String>();
        paramters.put("P1_bizType",bizType);
        paramters.put("P2_orderId",orderId);
        paramters.put("P3_customerNumber",customerNumber);
        paramters.put("sign",signPayQuery());
        return paramters;
    }
}

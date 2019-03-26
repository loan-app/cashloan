package com.xiji.cashloan.cl.model.pay.common.dto;

/**
 * @Auther: king
 * @ss 支付异步通知model
 * @Date: 2018/11/28 10:41
 * @Description:
 */
public class RepaymentNotifyDto {
    private String orderNo;
    private String status;
    private String message;
    /**
     * 平台流水
     */
    private String payPlatNo;
    /**
     * 银行卡号
     */
    private String cardNo;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPayPlatNo() {
        return payPlatNo;
    }

    public void setPayPlatNo(String payPlatNo) {
        this.payPlatNo = payPlatNo;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
}

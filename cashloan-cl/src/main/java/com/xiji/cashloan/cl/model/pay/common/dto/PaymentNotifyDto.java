package com.xiji.cashloan.cl.model.pay.common.dto;

/**
 * @Auther: king
 * @ss 支付异步通知model
 * @Date: 2018/11/28 10:41
 * @Description:
 */
public class PaymentNotifyDto {
    private String orderNo;
    private String status;
    private String message;

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
}

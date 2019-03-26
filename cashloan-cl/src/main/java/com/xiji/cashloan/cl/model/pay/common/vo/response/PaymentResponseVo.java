package com.xiji.cashloan.cl.model.pay.common.vo.response;

/**
 * @Auther: king
 * @Date: 2019/1/25 18:26
 * @Description:
 */
public class PaymentResponseVo {
    private String orderNo = "";
    private String status = "";
    private String statusCode = "";
    private String message = "";

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

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}

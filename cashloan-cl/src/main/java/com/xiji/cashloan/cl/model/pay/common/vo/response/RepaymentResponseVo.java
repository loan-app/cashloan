package com.xiji.cashloan.cl.model.pay.common.vo.response;

/**
 * @Auther: king
 * @Date: 2019/1/26 15:31
 * @Description:
 */
public class RepaymentResponseVo {
    /**
     * 状态
     */
    private String status;
    private String message;
    /**
     * 平台流水
     */
    private String payPlatNo;
    //订单号
    private String orderNo;
    private String statusCode;

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

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}

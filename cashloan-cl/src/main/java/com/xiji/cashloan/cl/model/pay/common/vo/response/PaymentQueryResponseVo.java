package com.xiji.cashloan.cl.model.pay.common.vo.response;

/**
 * @Auther: king
 * @Date: 2019/1/25 18:30
 * @Description:
 */
public class PaymentQueryResponseVo {
    private String orderNo;
    /**
     * 状态
     */
    private String status;
    /**
     * 平台流水
     */
    private String payPlatNo;

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

    public String getPayPlatNo() {
        return payPlatNo;
    }

    public void setPayPlatNo(String payPlatNo) {
        this.payPlatNo = payPlatNo;
    }
}

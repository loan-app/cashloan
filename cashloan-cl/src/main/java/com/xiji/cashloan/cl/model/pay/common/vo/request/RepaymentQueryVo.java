package com.xiji.cashloan.cl.model.pay.common.vo.request;

/**
 * @Auther: king
 * @Date: 2019/1/25 18:29
 * @Description:
 */
public class RepaymentQueryVo extends PayReq {
    private String orderNo;
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

    public String getPayPlatNo() {
        return payPlatNo;
    }

    public void setPayPlatNo(String payPlatNo) {
        this.payPlatNo = payPlatNo;
    }
}

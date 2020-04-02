package com.xiji.cashloan.cl.model.pay.common.vo.request;

/**
 * @Auther: king
 * @Date: 2019/1/25 18:29
 * @Description:
 */
public class PaymentQueryVo  extends PayReq {
    private String orderNo;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}

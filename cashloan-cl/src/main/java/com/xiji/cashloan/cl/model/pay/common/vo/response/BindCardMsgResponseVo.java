package com.xiji.cashloan.cl.model.pay.common.vo.response;

/**
 * @Auther: king
 * @Date: 2019/1/26 15:31
 * @Description:
 */
public class BindCardMsgResponseVo {
    /**
     * 状态
     */
    private String status;
    private String orderNo;
    private String message;
    private String protocolNo;//协议号

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

    public String getProtocolNo() {
        return protocolNo;
    }

    public void setProtocolNo(String protocolNo) {
        this.protocolNo = protocolNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}

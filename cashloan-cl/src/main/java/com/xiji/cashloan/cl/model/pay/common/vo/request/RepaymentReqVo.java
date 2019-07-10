package com.xiji.cashloan.cl.model.pay.common.vo.request;

/**
 * @Auther: king
 * @Date: 2019/1/26 15:41
 * @Description:
 */
public class RepaymentReqVo  extends PayReq {
    private String userId;
    private String ip;
    private Double amount;
    private String protocolNo;//协议号
    private String remark;
    private String remark2;
    private String terminalId;
    private String terminalType;
    private String borrowOrderNo;
    private Long borrowId;
    private String cardNo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getProtocolNo() {
        return protocolNo;
    }

    public void setProtocolNo(String protocolNo) {
        this.protocolNo = protocolNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public String getBorrowOrderNo() {
        return borrowOrderNo;
    }

    public void setBorrowOrderNo(String borrowOrderNo) {
        this.borrowOrderNo = borrowOrderNo;
    }

    public Long getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Long borrowId) {
        this.borrowId = borrowId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
}

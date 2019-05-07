package com.xiji.cashloan.cl.model.pay.common.vo.request;

/**
 * @Auther: king
 * @Date: 2019/1/25 18:24
 * @Description:
 */
public class PaymentReqVo  extends PayReq {
    private String bankCardNo;
    /**
     * 用户名称（银行卡拥有者姓名）
     */
    private String bankCardName;
    private Double amount;
    private String mobile;
    private String borrowOrderNo;
    private String remark;
    private Long borrowId;

    /**
     * 银行名称
     */
    private String bankName;

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getBankCardName() {
        return bankCardName;
    }

    public void setBankCardName(String bankCardName) {
        this.bankCardName = bankCardName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBorrowOrderNo() {
        return borrowOrderNo;
    }

    public void setBorrowOrderNo(String borrowOrderNo) {
        this.borrowOrderNo = borrowOrderNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Long borrowId) {
        this.borrowId = borrowId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}

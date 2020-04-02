package com.xiji.cashloan.cl.model.pay.common.vo.response;

/**
 * @Auther: king
 * @Date: 2019/1/28 19:39
 * @Description:
 */
public class CardBinQueryResponseVo {
    /**
     * 状态
     */
    private String status;
    private String bank;
    private String bankCode;

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

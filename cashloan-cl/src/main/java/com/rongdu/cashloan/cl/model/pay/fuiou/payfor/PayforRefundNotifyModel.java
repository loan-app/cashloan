package com.rongdu.cashloan.cl.model.pay.fuiou.payfor;

/**
 * @Auther: king
 * @Date: 2018/11/22 10:53
 * @Description:
 */
public class PayforRefundNotifyModel extends PayforNotifyModel{
    private String tpmerdt;//退票日期
    private String futporderno;//退票流水

    public String getTpmerdt() {
        return tpmerdt;
    }

    public void setTpmerdt(String tpmerdt) {
        this.tpmerdt = tpmerdt;
    }

    public String getFutporderno() {
        return futporderno;
    }

    public void setFutporderno(String futporderno) {
        this.futporderno = futporderno;
    }
}

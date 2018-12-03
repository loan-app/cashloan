package com.rongdu.cashloan.cl.model.pay.fuiou.payfor;

import com.rongdu.cashloan.cl.util.fuiou.MD5Util;
import org.apache.commons.codec.binary.StringUtils;

/**
 * @Auther: king
 * @Date: 2018/11/22 10:50
 * @Description:
 */
public class PayforNotifyModel{
    private String orderno;//商户请求流水
    private String merdt;//原请求日期
    private String fuorderno;//富友流水,富友生成的交易流水
    private String accntno;//账号
    private String accntnm;//账户名称
    private String bankno;//总行代码
    private String amt;//退票金额
    private String state;//状态,1为付款成功
    private String result;//交易结果
    private String reason;//结果原因
    /**
     * 商户号|富友分配给商户的密钥|商户请 求流水|原请求日期|账号|退票金额，用 竖线拼接后用 MD5 散列后的
     16 进制文本 Md5(merid+”|”+pwd+”|”+orderno+”|”+m erdt+”|”+accntno+”|”+amt)
     */
    private String mac;

    /**
     * 商户号|富友分配给商户的密钥|商户请 求流水|原请求日期|账号|退票金额，用 竖线拼接后用 MD5 散列后的
     16 进制文本 Md5(merid+”|”+pwd+”|”+orderno+”|”+m erdt+”|”+accntno+”|”+amt)
     * @return
     */
    public boolean checkSign(String merid,String pwd) {
        String str=merid+"|"+pwd+"|"+orderno+"|"+merdt+"|"+accntno+"|"+amt;
        String sign= MD5Util.MD5Encode(str,"UTF-8");
        return StringUtils.equals(sign,this.mac);
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getMerdt() {
        return merdt;
    }

    public void setMerdt(String merdt) {
        this.merdt = merdt;
    }

    public String getFuorderno() {
        return fuorderno;
    }

    public void setFuorderno(String fuorderno) {
        this.fuorderno = fuorderno;
    }

    public String getAccntno() {
        return accntno;
    }

    public void setAccntno(String accntno) {
        this.accntno = accntno;
    }

    public String getAccntnm() {
        return accntnm;
    }

    public void setAccntnm(String accntnm) {
        this.accntnm = accntnm;
    }

    public String getBankno() {
        return bankno;
    }

    public void setBankno(String bankno) {
        this.bankno = bankno;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}

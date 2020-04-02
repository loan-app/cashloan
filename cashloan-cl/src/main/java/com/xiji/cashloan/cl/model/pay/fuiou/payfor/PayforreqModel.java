package com.xiji.cashloan.cl.model.pay.fuiou.payfor;

import static com.xiji.cashloan.cl.model.pay.fuiou.constant.FuiouConstant.DAIFU_PAYFOR_VERSION;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Auther: king
 * @Date: 2018/11/21 17:19
 * @Description:
 */
@XmlRootElement(name = "payforreq") // 必须要标明这个元素
@XmlAccessorType(XmlAccessType.FIELD)
public class PayforreqModel {
    private String ver = DAIFU_PAYFOR_VERSION;//版本号
    private String merdt;//请求日期 yyyyMMdd
    /**
     * 请求流水
     c(10,30)
     否
     数字串，当天必须唯一
     */
    private String orderno;
    /**
     * 总行代码
     */
    private String bankno;
    /**
     * 城市代码
     */
    private String cityno;
    /**
     * 支行名称
     */
    private String branchnm;
    /**
     * 账号
     */
    private String accntno;
    /**
     * 账户名称
     */
    private String accntnm;
    /**
     * 金额
     n(1,12)
     否
     单位:分(单笔最少 3 元)
     */
    private String amt;
    /**
     * 企业流水号
     */
    private String entseq;
    /**
     * 备注
     */
    private String memo;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 是否返回交易状态 类别
     * 值为 1，其他值或不填则认为不需要返回交易状态 类别且响应参数无状态类别节点
     */
    private String addDesc;

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getMerdt() {
        return merdt;
    }

    public void setMerdt(String merdt) {
        this.merdt = merdt;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getBankno() {
        return bankno;
    }

    public void setBankno(String bankno) {
        this.bankno = bankno;
    }

    public String getCityno() {
        return cityno;
    }

    public void setCityno(String cityno) {
        this.cityno = cityno;
    }

    public String getBranchnm() {
        return branchnm;
    }

    public void setBranchnm(String branchnm) {
        this.branchnm = branchnm;
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

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getEntseq() {
        return entseq;
    }

    public void setEntseq(String entseq) {
        this.entseq = entseq;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddDesc() {
        return addDesc;
    }

    public void setAddDesc(String addDesc) {
        this.addDesc = addDesc;
    }
}

package com.rongdu.cashloan.cl.model.pay.fuiou.payfor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Auther: king
 * @Date: 2018/11/21 19:52
 * @Description:
 */
@XmlRootElement(name = "qrytransreq")
@XmlAccessorType(XmlAccessType.FIELD)
public class QrytransreqModel {
    private String ver;//版本号
    private String busicd;//业务代码,AC01,AP01
    private String orderno;//商户通过接口请求代收付交易时的流水号
    private String startdt;//yyyyMMdd
    private String enddt;//yyyyMMdd 开始和结束时间间隔不能超过 15 天
    private String transst;//交易状态
    /**
     * 平台交易值位:WMP 接口交易值为:HMP ftp 交易值为:FMP
     */
    private String srcModuleCd = "HMP";//交易来源

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getBusicd() {
        return busicd;
    }

    public void setBusicd(String busicd) {
        this.busicd = busicd;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getStartdt() {
        return startdt;
    }

    public void setStartdt(String startdt) {
        this.startdt = startdt;
    }

    public String getEnddt() {
        return enddt;
    }

    public void setEnddt(String enddt) {
        this.enddt = enddt;
    }

    public String getTransst() {
        return transst;
    }

    public void setTransst(String transst) {
        this.transst = transst;
    }

    public String getSrcModuleCd() {
        return srcModuleCd;
    }

    public void setSrcModuleCd(String srcModuleCd) {
        this.srcModuleCd = srcModuleCd;
    }
}

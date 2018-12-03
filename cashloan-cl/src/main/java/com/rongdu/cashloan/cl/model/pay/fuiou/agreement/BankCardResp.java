package com.rongdu.cashloan.cl.model.pay.fuiou.agreement;

import com.fuiou.util.MD5;
import com.rongdu.cashloan.cl.model.pay.fuiou.constant.FuiouConstant;
import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;
import tool.util.StringUtil;

/**
 * @Auther: king
 * @Date: 2018/11/23 17:28
 * @Description:
 */
@XObject(value = "FM")
public class BankCardResp extends ProtocolResp{
    /**
     * 0000 表示“成功”
     1014 表示“无效卡号”
     5505 表示“不支持的银行卡” 100001 表示“不支持的卡类型”
     */
    @XNode("Rcd")
    private String rcd;
    @XNode("RDesc")
    private String rDesc;
    /**
     * 01-借记卡，02-信用卡，03-准贷记 卡，04-富友卡，05-非法卡号
     */
    @XNode("Ctp")
    private String ctp;
    /**
     * 银行卡所在的银行名称
     */
    @XNode("Cnm")
    private String cnm;
    /**
     * 根据机构号前 6 位判断为同一家银 行(北京银行除外，北京银行根据 10 位机构号判断为同一家银行) '0801020000' - 中国工商银行 '0801030000' - 中国农业银行 '0801040000' –中国银行 '0801050000' - 中国建设银行 '0804031000' –北京银行
     */
    @XNode("InsCd")
    private String insCd;
    /**
     *
     Rcd+ "|" + mchnt_key 做 md5 摘要， 其中 mchnt_key 为 32 位的商户密 钥，系统分配
     */
    @XNode("Sign")
    private String sign;

    public String getRcd() {
        return rcd;
    }

    public void setRcd(String rcd) {
        this.rcd = rcd;
    }

    public String getrDesc() {
        return rDesc;
    }

    public void setrDesc(String rDesc) {
        this.rDesc = rDesc;
    }

    public String getCtp() {
        return ctp;
    }

    public void setCtp(String ctp) {
        this.ctp = ctp;
    }

    public String getCnm() {
        return cnm;
    }

    public void setCnm(String cnm) {
        this.cnm = cnm;
    }

    public String getInsCd() {
        return insCd;
    }

    public void setInsCd(String insCd) {
        this.insCd = insCd;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
    public String signReturnMsg(String key){
        StringBuilder sb = new StringBuilder();
        sb.append(rcd);
        sb.append("|");
        sb.append(key);
        return sb.toString();
    }

    public boolean checkReturn() {
        return StringUtil.equals(rcd, FuiouConstant.RESPONSE_SUCCESS_CODE);
    }

    public boolean checkSign(String key) {
        return StringUtil.equalsIgnoreCase(MD5.MD5Encode(signReturnMsg(key)),sign);
    }
}

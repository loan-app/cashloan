package com.rongdu.cashloan.cl.model.pay.fuiou.agreement;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

/**
 * @Auther: king
 * @Date: 2018/11/23 17:25
 * @Description:
 */
@XObject(value = "FM")
public class BankCardReq {

    /**
     * 富友分配给各合作商户的唯一识别 码
     */
    @XNode("MchntCd")
    private String mchntCd;
    /**
     * 支付的银行卡卡号
     */
    @XNode("Ono")
    private String ono;
    /**
     * MchntCd+ "|"+Ono+ "|" +mchnt_key 做 md5 摘要，其中 mchnt_key 为 32 位的商户密钥，系统分配
     */
    @XNode("Sign")
    private String sign;

    public String getMchntCd() {
        return mchntCd;
    }

    public void setMchntCd(String mchntCd) {
        this.mchntCd = mchntCd;
    }

    public String getOno() {
        return ono;
    }

    public void setOno(String ono) {
        this.ono = ono;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String querySign(String mchntKey) {
        StringBuilder temp = new StringBuilder();
        temp.append(this.mchntCd).append("|").append(this.ono).append("|").append(mchntKey);
        return temp.toString();
    }
}

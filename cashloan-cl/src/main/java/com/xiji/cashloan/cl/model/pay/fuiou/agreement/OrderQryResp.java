package com.xiji.cashloan.cl.model.pay.fuiou.agreement;

import com.fuiou.util.MD5;
import com.xiji.cashloan.cl.model.pay.fuiou.constant.FuiouConstant;
import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;
import tool.util.StringUtil;

/**
 * @Auther: king
 * @Date: 2018/11/23 17:40
 * @Description:
 */
@XObject(value = "RESPONSE")
public class OrderQryResp extends ProtocolResp {
    @XNode("VERSION")
    private String version;//VERSION
    @XNode("RESPONSECODE")
    private String responseCode;//0000 成功
    @XNode("RESPONSEMSG")
    private String responseMsg;//响应中文 述,支付成功
    @XNode("MCHNTORDERID")
    private String mchntOrderId;//商户流水号
    @XNode("AMT")
    private String amt;//请求报文中的交易金额，分为单位
    @XNode("BANKCARD")
    private String bankCard;//请求报文中的银行卡号
    /**
     * VERSION+"|"+RESPONSECODE+"|"+ RESPONSEMSG+"|"+MCHNTORDERID+ "|"+商户 32 位密钥 key"
     */
    @XNode("SIGN")
    private String sign;//SIGN
    @XNode("ORDERDATE")
    private String orderDate;//富友接受商户订单请求的时间

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public String getMchntOrderId() {
        return mchntOrderId;
    }

    public void setMchntOrderId(String mchntOrderId) {
        this.mchntOrderId = mchntOrderId;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String signReturnMsg(String key){
        //VERSION+"|"+RESPONSECODE+"|"+ RESPONSEMSG+"|"+MCHNTORDERID+ "|"+商户 32 位密钥 key"
        StringBuilder sb = new StringBuilder();
        sb.append(version);
        sb.append("|");
        sb.append(responseCode);
        sb.append("|");
        sb.append(responseMsg);
        sb.append("|");
        sb.append(mchntOrderId);
        sb.append("|");
        sb.append(key);
        return sb.toString();
    }
    public boolean checkReturn() {
        return StringUtil.equals(responseCode, FuiouConstant.RESPONSE_SUCCESS_CODE);
    }

    public boolean checkSign(String key) {
        return StringUtil.equalsIgnoreCase(MD5.MD5Encode(signReturnMsg(key)),sign);
    }
}

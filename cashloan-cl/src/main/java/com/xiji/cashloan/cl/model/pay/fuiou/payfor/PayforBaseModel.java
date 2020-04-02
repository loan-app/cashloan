package com.xiji.cashloan.cl.model.pay.fuiou.payfor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Auther: king
 * @Date: 2018/11/21 19:40
 * @Description:
 */
@XmlRootElement // 必须要标明这个元素
@XmlAccessorType(XmlAccessType.FIELD)
public class PayforBaseModel {

    /**
     * 000000 受理成功
     * 0000 交易成功
     */
    private String ret;//响应码
    private String memo;//响应 述
    /**
     * success AC01 交易代表交易成功。
     AP01 交易: 7*24h 商户对私卡号(卡 bin 存在)代表交易成功，
     非 7*24h 商户、对公账户、卡 bin 不存在，代表受理成功。
     acceptSuccess 只有 AP01 交易有，代表富友受理成功，具体到账情况还需要再次查询可知 internalFail 代表富友拒绝交易，由于交易信息校验失败导致，商户可根据应答码和响应 述
     定位问题，修改问题后，再次 交交易即可。
     14
     富友代收付平台企业直连接入规范
     channelFail 代表银行通道拒绝交易，商户可根据应答码和响应 述定位问题，或者咨询对 接人员，修改问题后，再次 交交易即可。
     cardInfoError 代表卡信息不对，银行通道拒绝交易，请核对交易中姓名、身份证、卡号、手 机号等信息，修改后，再次 交交易即可。
     unknowReasons 代表交易结果未知，可以参考应答码和应答 述， 200001/200002/999999 这三个应答码需作为交易超时处理。
     其他情况可及时联系富友对接人员或客服。
     */
    private String transStatusDesc;//交易状态类别

    private String errorCode;
    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getTransStatusDesc() {
        return transStatusDesc;
    }

    public void setTransStatusDesc(String transStatusDesc) {
        this.transStatusDesc = transStatusDesc;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}

package com.xiji.cashloan.cl.model.pay.fuiou.agreement;

import com.fuiou.util.MD5;
import com.xiji.cashloan.cl.model.pay.fuiou.constant.FuiouConstant;
import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;
import tool.util.StringUtil;
/**
 * @author wnb
 * @date 2018/11/30
 * @version 1.0.0
 */
@XObject(value = "RESPONSE")
public class OrderXmlBeanResp extends ProtocolResp {
	@XNode("VERSION")
	private String version;//VERSION
	@XNode("TYPE")
	private String type;//TYPE
	@XNode("RESPONSECODE")
	private String responseCode;//0000 成功
	@XNode("RESPONSEMSG")
	private String responseMsg;//响应中文 述,支付成功
	@XNode("MCHNTCD")
	private String mchntCd;//商户号
	@XNode("USERID")
	private String userId;//用户编号
	@XNode("MCHNTORDERID")
	private String mchntOrderId;//商户订单号
	@XNode("ORDERID")
	private String orderId;//富友订单号
	@XNode("PROTOCOLNO")
	private String protocolNo;//协议号
	@XNode("BANKCARD")
	private String bankCard;//银行卡号
	@XNode("AMT")
	private String amt;//交易金额
	@XNode("REM1")
	private String rem1;//REM1 保留字段 1
	@XNode("REM2")
	private String rem2;//REM2 保留字段 2
	@XNode("REM3")
	private String rem3;//REM3 保留字段 3
	@XNode("SIGNTP")
	private String signTp;//签名类型,md5 或 rsa
	/**
	 * TYPE+"|"+VERSION+"|"+RESP ONSECODE+"|"+"MCHNTCD"+"| "MCHNTORDERID+"|"+ORDERID "|"+AMT+"|"+BANKCARD+"|"+ "商户 32 位密钥 key"
	 */
	@XNode("SIGN")
	private String sign;//SIGN

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getMchntCd() {
		return mchntCd;
	}

	public void setMchntCd(String mchntCd) {
		this.mchntCd = mchntCd;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMchntOrderId() {
		return mchntOrderId;
	}

	public void setMchntOrderId(String mchntOrderId) {
		this.mchntOrderId = mchntOrderId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProtocolNo() {
		return protocolNo;
	}

	public void setProtocolNo(String protocolNo) {
		this.protocolNo = protocolNo;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}

	public String getRem1() {
		return rem1;
	}

	public void setRem1(String rem1) {
		this.rem1 = rem1;
	}

	public String getRem2() {
		return rem2;
	}

	public void setRem2(String rem2) {
		this.rem2 = rem2;
	}

	public String getRem3() {
		return rem3;
	}

	public void setRem3(String rem3) {
		this.rem3 = rem3;
	}

	public String getSignTp() {
		return signTp;
	}

	public void setSignTp(String signTp) {
		this.signTp = signTp;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String signReturnMsg(String key) {
		StringBuffer buffer = new StringBuffer();
		//TYPE+"|"+VERSION+"|"+RESP ONSECODE+"|"+"MCHNTCD"+"| "MCHNTORDERID+"|"+ORDERID "|"+AMT+"|"+BANKCARD+"|"+ "商户 32 位密钥 key"
		buffer.append(type)
			.append("|")
			.append(version)
			.append("|")
			.append(responseCode)
			.append("|")
			.append(mchntCd)
			.append("|")
			.append(mchntOrderId)
			.append("|")
			.append(orderId)
			.append("|")
			.append(amt)
			.append("|")
			.append(bankCard)
			.append("|")
			.append(key);
		return buffer.toString();
	}
	public boolean checkReturn() {
		return StringUtil.equals(responseCode, FuiouConstant.RESPONSE_SUCCESS_CODE);
	}

	public boolean checkSign(String key) {
		return StringUtil.equalsIgnoreCase(MD5.MD5Encode(signReturnMsg(key)),sign);
	}
}

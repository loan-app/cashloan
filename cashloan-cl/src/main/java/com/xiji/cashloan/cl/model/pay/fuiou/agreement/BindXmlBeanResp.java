package com.xiji.cashloan.cl.model.pay.fuiou.agreement;

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
public class BindXmlBeanResp extends ProtocolResp{
	@XNode("VERSION")
	private String version;		//版本号(1.0)
	@XNode("RESPONSECODE")
	private String responseCode;//0000 成功
	@XNode("RESPONSEMSG")
	private String responseMsg;//响应中文 述,成功
	@XNode("PROTOCOLNO")
	private String protocolNo;//协议号
	@XNode("MCHNTSSN")
	private String mchntSsn;//商户流水号,
	@XNode("MCHNTCD")
	private String mchntCd;//商户代码
	@XNode("USERID")
	private String userId;//商户端用户的唯一编号，即用户 ID
	@XNode("CARDNO")
	private String cardNo;//银行卡号

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

	public String getProtocolNo() {
		return protocolNo;
	}

	public void setProtocolNo(String protocolNo) {
		this.protocolNo = protocolNo;
	}

	public String getMchntSsn() {
		return mchntSsn;
	}

	public void setMchntSsn(String mchntSsn) {
		this.mchntSsn = mchntSsn;
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

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public boolean checkReturn() {
		return StringUtil.equals(responseCode, FuiouConstant.RESPONSE_SUCCESS_CODE);
	}
}

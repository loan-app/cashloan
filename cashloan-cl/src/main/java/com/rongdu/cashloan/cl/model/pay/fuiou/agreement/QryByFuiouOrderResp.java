package com.rongdu.cashloan.cl.model.pay.fuiou.agreement;

import com.fuiou.util.MD5;
import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;
import tool.util.StringUtil;

@XObject(value = "FM")
public class QryByFuiouOrderResp extends ProtocolResp{
	/** 响应代码
	 * 5185 表示“订单已支付”
	 5077 表示“无此订单”
	 11V3 表示“订单失效”
	 11E3 表示“订单支付失败”
	 51B3 表示 “订单支付中(隔段时间 之后再来查询或等待异步通知)”
	 */
	@XNode("Rcd")
	private String rcd;
	@XNode("RDesc")
	private String rDesc;
	/**
	 * Rcd+"|"+ mchnt_key 做 md5 摘要其 中 mchnt_key 为 32 位的商户密钥， 系统分配
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

	public boolean checkSign(String key) {
		return StringUtil.equalsIgnoreCase(MD5.MD5Encode(signReturnMsg(key)),sign);
	}
}

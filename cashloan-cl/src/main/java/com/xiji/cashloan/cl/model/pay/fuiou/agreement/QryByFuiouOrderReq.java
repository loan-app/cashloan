package com.xiji.cashloan.cl.model.pay.fuiou.agreement;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject(value = "FM")
public class QryByFuiouOrderReq {
	@XNode("MchntCd")
	private String mchntCd;//商户代码
	/**
	 * 商户调富友下单接口产生的一个唯 一的订单号，
	 * 该订单号在相当长的 时间内不重复。富友通过订单号来 唯一确认一笔订单的重复性
	 */
	@XNode("OrderId")
	private String orderId;//订单号
	/**
	 * MchntCd+ "|" + OrderId+ "|" +mchnt_key 做 md5 摘要，其中 mchnt_key 为 32 位的商户密钥，系 统分配
	 */
	@XNode("Sign")
	private String sign;//

	public String getMchntCd() {
		return mchntCd;
	}

	public void setMchntCd(String mchntCd) {
		this.mchntCd = mchntCd;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String querySignStr(String key){
		StringBuilder sb = new StringBuilder();
		sb.append(mchntCd);
		sb.append("|");
		sb.append(orderId);
		sb.append("|");
		sb.append(key);
		return sb.toString();
	}
}

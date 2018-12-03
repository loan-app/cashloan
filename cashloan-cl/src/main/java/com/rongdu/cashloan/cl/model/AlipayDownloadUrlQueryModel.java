package com.rongdu.cashloan.cl.model;


 /**
 * 支付宝查询对账单下载地址
 * @author jdd
 * @version 1.0.0
 * @date 2017-4-5 上午11:20:28
 * Copyright 杭州融都科技股份有限公司 金融创新事业部 cashloan  All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public class AlipayDownloadUrlQueryModel {

	/**
	 * 账单类型  
	 * 商户通过接口或商户经开放平台授权后其所属服务商通过接口可以获取以下账单类型：trade、signcustomer；
	 * trade指商户基于支付宝交易收单的业务账单；
	 * signcustomer是指基于商户支付宝余额收入及支出等资金变动的帐务账单；
	 */
	private String bill_type;
	
	/**
	 * 账单时间    ：日账单格式为yyyy-MM-dd，月账单格式为yyyy-MM。
	 */
	private String bill_date;
	
 

	public AlipayDownloadUrlQueryModel() {
		super();
	}



	public String getBill_type() {
		return bill_type;
	}



	public void setBill_type(String bill_type) {
		this.bill_type = bill_type;
	}



	public String getBill_date() {
		return bill_date;
	}



	public void setBill_date(String bill_date) {
		this.bill_date = bill_date;
	}
	
	 
}

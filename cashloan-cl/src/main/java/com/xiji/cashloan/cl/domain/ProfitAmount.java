package com.xiji.cashloan.cl.domain;

import java.io.Serializable;

/**
 * 分润资金实体
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-02-18 16:29:51
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 public class ProfitAmount implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 用户id
    */
    private Long userId;

    /**
    * 总金额
    */
    private Double total;

    /**
    * 未提现
    */
    private Double noCashed;

    /**
    * 已提现
    */
    private Double cashed;

    /**
    * 状态 10正常 20冻结
    */
    private String state;

	/**
	 * 获取主键Id
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置主键Id
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取用户id
	 * @return userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 设置用户id
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 获取总金额
	 * @return total
	 */
	public Double getTotal() {
		return total;
	}

	/**
	 * 设置总金额
	 * @param total
	 */
	public void setTotal(Double total) {
		this.total = total;
	}

	/**
	 * 获取未提现
	 * @return noCashed
	 */
	public Double getNoCashed() {
		return noCashed;
	}

	/**
	 * 设置未提现
	 * @param noCashed
	 */
	public void setNoCashed(Double noCashed) {
		this.noCashed = noCashed;
	}

	/**
	 * 获取已提现
	 * @return cashed
	 */
	public Double getCashed() {
		return cashed;
	}

	/**
	 * 设置已提现
	 * @param cashed
	 */
	public void setCashed(Double cashed) {
		this.cashed = cashed;
	}

	/**
	 * 获取状态10正常20冻结
	 * @return state
	 */
	public String getState() {
		return state;
	}

	/**
	 * 设置状态10正常20冻结
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}

}
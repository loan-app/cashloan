package com.xiji.cashloan.cl.domain;

import java.io.Serializable;

/**
 * 运营商信息--月账单实体
 * Created by szb on 18/11/23
 */
public class OperatorBill extends OperatorBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 账单月，格式：yyyy-MM
	 */
	private String billMonth;

	/**
	 * 账期起始日期，格式：yyyy-MM-dd
	 */
	private String billStartDate;

	/**
	 * 账期结束日期，格式：yyyy-MM-dd
	 */
	private String billEndDate;

	/**
	 * 本机号码套餐及固定费
	 */
	private Integer baseFee;

	/**
	 * 增值业务费
	 */
	private Integer extraServiceFee;

	/**
	 * 语音费
	 */
	private Integer voiceFee;

	/**
	 * 短彩信费
	 */
	private Integer smsFee;

	/**
	 * 网络流量费
	 */
	private Integer webFee;

	/**
	 * 其它费用
	 */
	private Integer extraFee;

	/**
	 * 本月总费用
	 */
	private Integer totalFee;

	/**
	 * 优惠费
	 */
	private Integer discount;

	/**
	 * 其它优惠
	 */
	private Integer extraDiscount;

	/**
	 * 个人实际费用
	 */
	private Integer actualFee;

	/**
	 * 本期已付费用
	 */
	private Integer paidFee;

	/**
	 * 本期未付费用
	 */
	private Integer unpaidFee;

	/**
	 * 本期可用积分
	 */
	private Integer point;

	/**
	 * 上期可用积分
	 */
	private Integer lastPoint;

	/**
	 * 本手机关联号码, 多个手机号以逗号分隔
	 */
	private String relatedMobiles;

	/**
	 * 备注
	 */
	private String notes;

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getBillMonth() {
		return billMonth;
	}

	public void setBillMonth(String billMonth) {
		this.billMonth = billMonth;
	}

	public String getBillStartDate() {
		return billStartDate;
	}

	public void setBillStartDate(String billStartDate) {
		this.billStartDate = billStartDate;
	}

	public String getBillEndDate() {
		return billEndDate;
	}

	public void setBillEndDate(String billEndDate) {
		this.billEndDate = billEndDate;
	}

	public Integer getBaseFee() {
		return baseFee;
	}

	public void setBaseFee(Integer baseFee) {
		this.baseFee = baseFee;
	}

	public Integer getExtraServiceFee() {
		return extraServiceFee;
	}

	public void setExtraServiceFee(Integer extraServiceFee) {
		this.extraServiceFee = extraServiceFee;
	}

	public Integer getVoiceFee() {
		return voiceFee;
	}

	public void setVoiceFee(Integer voiceFee) {
		this.voiceFee = voiceFee;
	}

	public Integer getSmsFee() {
		return smsFee;
	}

	public void setSmsFee(Integer smsFee) {
		this.smsFee = smsFee;
	}

	public Integer getWebFee() {
		return webFee;
	}

	public void setWebFee(Integer webFee) {
		this.webFee = webFee;
	}

	public Integer getExtraFee() {
		return extraFee;
	}

	public void setExtraFee(Integer extraFee) {
		this.extraFee = extraFee;
	}

	public Integer getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Integer getExtraDiscount() {
		return extraDiscount;
	}

	public void setExtraDiscount(Integer extraDiscount) {
		this.extraDiscount = extraDiscount;
	}

	public Integer getActualFee() {
		return actualFee;
	}

	public void setActualFee(Integer actualFee) {
		this.actualFee = actualFee;
	}

	public Integer getPaidFee() {
		return paidFee;
	}

	public void setPaidFee(Integer paidFee) {
		this.paidFee = paidFee;
	}

	public Integer getUnpaidFee() {
		return unpaidFee;
	}

	public void setUnpaidFee(Integer unpaidFee) {
		this.unpaidFee = unpaidFee;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Integer getLastPoint() {
		return lastPoint;
	}

	public void setLastPoint(Integer lastPoint) {
		this.lastPoint = lastPoint;
	}

	public String getRelatedMobiles() {
		return relatedMobiles;
	}

	public void setRelatedMobiles(String relatedMobiles) {
		this.relatedMobiles = relatedMobiles;
	}
}
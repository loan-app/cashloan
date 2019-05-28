package com.xiji.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 渠道信息实体
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2018/12/03
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 public class Channel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 渠道代码
    */
    private String code;

    /**
    * 渠道名称
    */
    private String name;

    /**
    *联系人
    */
    private String linker;

    /**
    * 联系电话
    */
    private String phone;

    /**
    * 渠道类型  备用
    */
    private String type;

    /**
    * 状态 10：启用20：禁用
    */
    private String state;

    /**
    * 添加时间
    */
    private Date createTime;

    /**
     * 综合费用
     */
    private String fee;

    /**
     * 注册时给予额度
     */
    private String initCredit;

    /**
     * 借款额度
     */
    private String borrowCredit;
    /**
     * 还款成功累计提额上限
     */
    private String improveCreditLimit;

    /**
     * 还款成功单次增加的额度
     */
    private String oneRepayCredit;

    /**
     * 还款提额开关 10：启用20：禁用
     */
    private String isImproveCredit;

    /**
     * 借款天数
     */
    private String borrowDay;


    /**
    * 获取主键Id
    *
    * @return id
    */
    public Long getId(){
    return id;
    }

    /**
    * 设置主键Id
    * 
    * @param 要设置的主键Id
    */
    public void setId(Long id){
    this.id = id;
    }

    /**
    * 获取渠道代码
    *
    * @return 渠道代码
    */
    public String getCode(){
    return code;
    }

    /**
    * 设置渠道代码
    * 
    * @param code 要设置的渠道代码
    */
    public void setCode(String code){
    this.code = code;
    }

    /**
    * 获取渠道名称
    *
    * @return 渠道名称
    */
    public String getName(){
    return name;
    }

    /**
    * 设置渠道名称
    * 
    * @param name 要设置的渠道名称
    */
    public void setName(String name){
    this.name = name;
    }

    
    public String getLinker() {
		return linker;
	}

	public void setLinker(String linker) {
		this.linker = linker;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
    * 获取渠道类型 10：Android 20：IOS
    *
    * @return 渠道类型 10：Android 20：IOS
    */
    public String getType(){
    return type;
    }

    /**
    * 设置渠道类型 10：Android 20：IOS
    * 
    * @param type 要设置的渠道类型 10：Android 20：IOS
    */
    public void setType(String type){
    this.type = type;
    }

    /**
    * 获取状态 10：启用20：禁用
    *
    * @return 状态 10：启用20：禁用
    */
    public String getState(){
    return state;
    }

    /**
    * 设置状态 10：启用20：禁用
    * 
    * @param state 要设置的状态 10：启用20：禁用
    */
    public void setState(String state){
    this.state = state;
    }

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getInitCredit() {
        return initCredit;
    }

    public void setInitCredit(String initCredit) {
        this.initCredit = initCredit;
    }

    public String getBorrowCredit() {
        return borrowCredit;
    }

    public void setBorrowCredit(String borrowCredit) {
        this.borrowCredit = borrowCredit;
    }

    public String getImproveCreditLimit() {
        return improveCreditLimit;
    }

    public void setImproveCreditLimit(String improveCreditLimit) {
        this.improveCreditLimit = improveCreditLimit;
    }

    public String getOneRepayCredit() {
        return oneRepayCredit;
    }

    public void setOneRepayCredit(String oneRepayCredit) {
        this.oneRepayCredit = oneRepayCredit;
    }

    public String getIsImproveCredit() {
        return isImproveCredit;
    }

    public void setIsImproveCredit(String isImproveCredit) {
        this.isImproveCredit = isImproveCredit;
    }

    public String getBorrowDay() {
        return borrowDay;
    }

    public void setBorrowDay(String borrowDay) {
        this.borrowDay = borrowDay;
    }
}
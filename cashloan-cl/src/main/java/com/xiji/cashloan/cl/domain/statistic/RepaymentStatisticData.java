package com.xiji.cashloan.cl.domain.statistic;

import java.io.Serializable;
import java.util.Date;

/**
 * 还款统计数据实体
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2019-02-15 15:36:37
 */
 public class RepaymentStatisticData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 更新时间
    */
    private Date updateTime;

    /**
    * 统计时间
    */
    private Date countTime;

    /**
    * 应还订单笔数
    */
    private Integer shouldOrder;

    /**
    * 待还订单笔数
    */
    private Integer remainOrder;

    /**
    * 提前还款笔数
    */
    private Integer advanceRepayment;

    /**
    * 正常还款笔数
    */
    private Integer normalRepayment;

    /**
    * 展期还款笔数
    */
    private Integer extendRepayment;

    /**
    * 逾期还款笔数
    */
    private Integer overdueRepayment;

    /**
    * 应还金额
    */
    private Double shouldAmount;

    /**
    * 到期实还金额
    */
    private Double realReturnAmount;

    /**
     * 今日实还金额
     */
    private Double todayRealReturnAmount;

    /**
    * 放款本金
    */
    private Double loadAmount;

    /**
    * 减免金额
    */
    private Double derateAmount;

    /**
    * 待还金额
    */
    private Double remainAmount;

    /**
     * 统计时间Str
     */
    private String countTimeStr;

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
    * @param id
    */
    public void setId(Long id){
        this.id = id;
    }

    /**
    * 获取创建时间
    *
    * @return 创建时间
    */
    public Date getCreateTime(){
        return createTime;
    }

    /**
    * 设置创建时间
    * 
    * @param createTime 要设置的创建时间
    */
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    /**
    * 获取更新时间
    *
    * @return 更新时间
    */
    public Date getUpdateTime(){
        return updateTime;
    }

    /**
    * 设置更新时间
    * 
    * @param updateTime 要设置的更新时间
    */
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }

    /**
    * 获取统计时间
    *
    * @return 统计时间
    */
    public Date getCountTime(){
        return countTime;
    }

    /**
    * 设置统计时间
    * 
    * @param countTime 要设置的统计时间
    */
    public void setCountTime(Date countTime){
        this.countTime = countTime;
    }

    /**
    * 获取应还订单笔数
    *
    * @return 应还订单笔数
    */
    public Integer getShouldOrder(){
        return shouldOrder;
    }

    /**
    * 设置应还订单笔数
    * 
    * @param shouldOrder 要设置的应还订单笔数
    */
    public void setShouldOrder(Integer shouldOrder){
        this.shouldOrder = shouldOrder;
    }

    /**
    * 获取待还订单笔数
    *
    * @return 待还订单笔数
    */
    public Integer getRemainOrder(){
        return remainOrder;
    }

    /**
    * 设置待还订单笔数
    * 
    * @param remainOrder 要设置的待还订单笔数
    */
    public void setRemainOrder(Integer remainOrder){
        this.remainOrder = remainOrder;
    }

    /**
    * 获取提前还款笔数
    *
    * @return 提前还款笔数
    */
    public Integer getAdvanceRepayment(){
        return advanceRepayment;
    }

    /**
    * 设置提前还款笔数
    * 
    * @param advanceRepayment 要设置的提前还款笔数
    */
    public void setAdvanceRepayment(Integer advanceRepayment){
        this.advanceRepayment = advanceRepayment;
    }

    /**
    * 获取正常还款笔数
    *
    * @return 正常还款笔数
    */
    public Integer getNormalRepayment(){
        return normalRepayment;
    }

    /**
    * 设置正常还款笔数
    * 
    * @param normalRepayment 要设置的正常还款笔数
    */
    public void setNormalRepayment(Integer normalRepayment){
        this.normalRepayment = normalRepayment;
    }

    /**
    * 获取展期还款笔数
    *
    * @return 展期还款笔数
    */
    public Integer getExtendRepayment(){
        return extendRepayment;
    }

    /**
    * 设置展期还款笔数
    * 
    * @param extendRepayment 要设置的展期还款笔数
    */
    public void setExtendRepayment(Integer extendRepayment){
        this.extendRepayment = extendRepayment;
    }

    /**
    * 获取逾期还款笔数
    *
    * @return 逾期还款笔数
    */
    public Integer getOverdueRepayment(){
        return overdueRepayment;
    }

    /**
    * 设置逾期还款笔数
    * 
    * @param overdueRepayment 要设置的逾期还款笔数
    */
    public void setOverdueRepayment(Integer overdueRepayment){
        this.overdueRepayment = overdueRepayment;
    }

    /**
    * 获取应还金额
    *
    * @return 应还金额
    */
    public Double getShouldAmount(){
        return shouldAmount;
    }

    /**
    * 设置应还金额
    * 
    * @param shouldAmount 要设置的应还金额
    */
    public void setShouldAmount(Double shouldAmount){
        this.shouldAmount = shouldAmount;
    }

    /**
    * 获取实还金额
    *
    * @return 实还金额
    */
    public Double getRealRetrunAmount(){
        return realReturnAmount;
    }

    /**
    * 设置实还金额
    * 
    * @param realReturnAmount 要设置的实还金额
    */
    public void setRealRetrunAmount(Double realReturnAmount){
        this.realReturnAmount = realReturnAmount;
    }

    /**
    * 获取放款本金
    *
    * @return 放款本金
    */
    public Double getLoadAmount(){
        return loadAmount;
    }

    /**
    * 设置放款本金
    * 
    * @param loadAmount 要设置的放款本金
    */
    public void setLoadAmount(Double loadAmount){
        this.loadAmount = loadAmount;
    }

    /**
    * 获取减免金额
    *
    * @return 减免金额
    */
    public Double getDerateAmount(){
        return derateAmount;
    }

    /**
    * 设置减免金额
    * 
    * @param derateAmount 要设置的减免金额
    */
    public void setDerateAmount(Double derateAmount){
        this.derateAmount = derateAmount;
    }

    /**
    * 获取待还金额
    *
    * @return 待还金额
    */
    public Double getRemainAmount(){
        return remainAmount;
    }

    /**
    * 设置待还金额
    * 
    * @param remainAmount 要设置的待还金额
    */
    public void setRemainAmount(Double remainAmount){
        this.remainAmount = remainAmount;
    }


    /**
     * 获取今日实还金额
     * @return
     */
    public Double getTodayRealRetrunAmount() {
        return todayRealReturnAmount;
    }

    /**
     * 设置今日实还金额
     * @param todayRealRetrunAmount
     */
    public void setTodayRealRetrunAmount(Double todayRealRetrunAmount) {
        this.todayRealReturnAmount = todayRealRetrunAmount;
    }

    public Double getRealReturnAmount() {
        return realReturnAmount;
    }

    public void setRealReturnAmount(Double realReturnAmount) {
        this.realReturnAmount = realReturnAmount;
    }

    public Double getTodayRealReturnAmount() {
        return todayRealReturnAmount;
    }

    public void setTodayRealReturnAmount(Double todayRealReturnAmount) {
        this.todayRealReturnAmount = todayRealReturnAmount;
    }

    public String getCountTimeStr() {
        return countTimeStr;
    }

    public void setCountTimeStr(String countTimeStr) {
        this.countTimeStr = countTimeStr;
    }
}
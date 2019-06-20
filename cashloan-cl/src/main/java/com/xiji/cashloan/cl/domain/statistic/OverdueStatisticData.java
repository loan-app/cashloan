package com.xiji.cashloan.cl.domain.statistic;

import java.io.Serializable;
import java.util.Date;

/**
 * 逾期统计数据实体
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2019-03-04 18:07:35
 */
 public class OverdueStatisticData implements Comparable<OverdueStatisticData>, Serializable {

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
    * 新客未还量
    */
    private Integer newOverdue;

    /**
    * 复借未还量
    */
    private Integer againOverdue;

    /**
    * 展期未还量
    */
    private Integer extendOverdue;

    /**
    * 新客到期量
    */
    private Integer newExpire;

    /**
    * 复借到期量
    */
    private Integer againExpire;

    /**
    * 展期到期量
    */
    private Integer extendExpire;

    /**
    * 新客已还款量
    */
    private Integer newRepayment;

    /**
    * 复借已还款量
    */
    private Integer againRepayment;

    /**
    * 展期已还款量
    */
    private Integer extendRepayment;

    /**
    * 新客逾期率
    */
    private Double newOverdueRate;

    /**
    * 复借逾期率
    */
    private Double againOverdueRate;

    /**
    * 展期逾期率
    */
    private Double extendOverdueRate;

    /**
     * 总逾期率
     */
    private Double overdueRate;

    /**
     * 统计时间
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
    * @param
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
    * 获取新客未还量
    *
    * @return 新客未还量
    */
    public Integer getNewOverdue(){
        return newOverdue;
    }

    /**
    * 设置新客未还量
    * 
    * @param newOverdue 要设置的新客未还量
    */
    public void setNewOverdue(Integer newOverdue){
        this.newOverdue = newOverdue;
    }

    /**
    * 获取复借未还量
    *
    * @return 复借未还量
    */
    public Integer getAgainOverdue(){
        return againOverdue;
    }

    /**
    * 设置复借未还量
    * 
    * @param againOverdue 要设置的复借未还量
    */
    public void setAgainOverdue(Integer againOverdue){
        this.againOverdue = againOverdue;
    }

    /**
    * 获取展期未还量
    *
    * @return 展期未还量
    */
    public Integer getExtendOverdue(){
        return extendOverdue;
    }

    /**
    * 设置展期未还量
    * 
    * @param extendOverdue 要设置的展期未还量
    */
    public void setExtendOverdue(Integer extendOverdue){
        this.extendOverdue = extendOverdue;
    }

    /**
    * 获取新客到期量
    *
    * @return 新客到期量
    */
    public Integer getNewExpire(){
        return newExpire;
    }

    /**
    * 设置新客到期量
    * 
    * @param newExpire 要设置的新客到期量
    */
    public void setNewExpire(Integer newExpire){
        this.newExpire = newExpire;
    }

    /**
    * 获取复借到期量
    *
    * @return 复借到期量
    */
    public Integer getAgainExpire(){
        return againExpire;
    }

    /**
    * 设置复借到期量
    * 
    * @param againExpire 要设置的复借到期量
    */
    public void setAgainExpire(Integer againExpire){
        this.againExpire = againExpire;
    }

    /**
    * 获取展期到期量
    *
    * @return 展期到期量
    */
    public Integer getExtendExpire(){
        return extendExpire;
    }

    /**
    * 设置展期到期量
    * 
    * @param extendExpire 要设置的展期到期量
    */
    public void setExtendExpire(Integer extendExpire){
        this.extendExpire = extendExpire;
    }

    /**
    * 获取新客已还款量
    *
    * @return 新客已还款量
    */
    public Integer getNewRepayment(){
        return newRepayment;
    }

    /**
    * 设置新客已还款量
    * 
    * @param newRepayment 要设置的新客已还款量
    */
    public void setNewRepayment(Integer newRepayment){
        this.newRepayment = newRepayment;
    }

    /**
    * 获取复借已还款量
    *
    * @return 复借已还款量
    */
    public Integer getAgainRepayment(){
        return againRepayment;
    }

    /**
    * 设置复借已还款量
    * 
    * @param againRepayment 要设置的复借已还款量
    */
    public void setAgainRepayment(Integer againRepayment){
        this.againRepayment = againRepayment;
    }

    /**
    * 获取展期已还款量
    *
    * @return 展期已还款量
    */
    public Integer getExtendRepayment(){
        return extendRepayment;
    }

    /**
    * 设置展期已还款量
    * 
    * @param extendRepayment 要设置的展期已还款量
    */
    public void setExtendRepayment(Integer extendRepayment){
        this.extendRepayment = extendRepayment;
    }

    /**
    * 获取新客逾期率
    *
    * @return 新客逾期率
    */
    public Double getNewOverdueRate(){
        return newOverdueRate;
    }

    /**
    * 设置新客逾期率
    * 
    * @param newOverdueRate 要设置的新客逾期率
    */
    public void setNewOverdueRate(Double newOverdueRate){
        this.newOverdueRate = newOverdueRate;
    }

    /**
    * 获取复借逾期率
    *
    * @return 复借逾期率
    */
    public Double getAgainOverdueRate(){
        return againOverdueRate;
    }

    /**
    * 设置复借逾期率
    * 
    * @param againOverdueRate 要设置的复借逾期率
    */
    public void setAgainOverdueRate(Double againOverdueRate){
        this.againOverdueRate = againOverdueRate;
    }

    /**
    * 获取展期逾期率
    *
    * @return 展期逾期率
    */
    public Double getExtendOverdueRate(){
        return extendOverdueRate;
    }

    /**
    * 设置展期逾期率
    * 
    * @param extendOverdueRate 要设置的展期逾期率
    */
    public void setExtendOverdueRate(Double extendOverdueRate){
        this.extendOverdueRate = extendOverdueRate;
    }

    public Double getOverdueRate() {
        return overdueRate;
    }

    public void setOverdueRate(Double overdueRate) {
        this.overdueRate = overdueRate;
    }

    public String getCountTimeStr() {
        return countTimeStr;
    }

    public void setCountTimeStr(String countTimeStr) {
        this.countTimeStr = countTimeStr;
    }

    /**
     * 根据统计时间降序排列，升序修改相减顺序即可
     * @param o
     * @return
     */
    @Override
    public int compareTo(OverdueStatisticData o) {
        return o.countTime.compareTo(this.getCountTime());
    }
}
package com.xiji.cashloan.cl.domain.statistic;

import java.io.Serializable;
import java.util.Date;

/**
 * 审核人员统计数据实体
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2019-02-15 15:35:23
 */
 public class AuditorStatisticData implements Serializable {

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
    * 审核人员ID
    */
    private Integer auditorId;

    /**
    * 申请笔数
    */
    private Integer borrowApplyCount;

    /**
    * 通过订单量
    */
    private Integer passOrder;

    /**
    * 通过率
    */
    private Double passRate;

    /**
    * 当前逾期率
    */
    private Double currentOverdueRate;

    /**
    * 首逾率
    */
    private Double firstOverdueRate;

    /**
     * 当日到期首贷逾期数
     */
    private Integer firstOverdue;

    /**
     * 当日到期首贷放款数
     */
    private Integer firstLoadCount;

    /**
     * 当日到期逾期数
     */
    private Integer currentOverdue;

    /**
     * 当日到期放款数
     */
    private Integer loadCount;

    /**
     * 当日到期展期逾期数
     */
    private Integer firstExtendOverdueCount;

    /**
     * 审核员名称
     */
    private String auditorName;

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
    * 获取审核人员ID
    *
    * @return 审核人员ID
    */
    public Integer getAuditorId(){
        return auditorId;
    }

    /**
    * 设置审核人员ID
    * 
    * @param auditorId 要设置的审核人员ID
    */
    public void setAuditorId(Integer auditorId){
        this.auditorId = auditorId;
    }

    /**
    * 获取申请笔数
    *
    * @return 申请笔数
    */
    public Integer getBorrowApplyCount(){
        return borrowApplyCount;
    }

    /**
    * 设置申请笔数
    * 
    * @param borrowApplyCount 要设置的申请笔数
    */
    public void setBorrowApplyCount(Integer borrowApplyCount){
        this.borrowApplyCount = borrowApplyCount;
    }

    /**
    * 获取通过订单量
    *
    * @return 通过订单量
    */
    public Integer getPassOrder(){
        return passOrder;
    }

    /**
    * 设置通过订单量
    * 
    * @param passOrder 要设置的通过订单量
    */
    public void setPassOrder(Integer passOrder){
        this.passOrder = passOrder;
    }

    /**
    * 获取通过率
    *
    * @return 通过率
    */
    public Double getPassRate(){
        return passRate;
    }

    /**
    * 设置通过率
    * 
    * @param passRate 要设置的通过率
    */
    public void setPassRate(Double passRate){
        this.passRate = passRate;
    }

    /**
    * 获取当前逾期率
    *
    * @return 当前逾期率
    */
    public Double getCurrentOverdueRate(){
        return currentOverdueRate;
    }

    /**
    * 设置当前逾期率
    * 
    * @param currentOverdueRate 要设置的当前逾期率
    */
    public void setCurrentOverdueRate(Double currentOverdueRate){
        this.currentOverdueRate = currentOverdueRate;
    }

    /**
    * 获取首逾率
    *
    * @return 首逾率
    */
    public Double getFirstOverdueRate(){
        return firstOverdueRate;
    }

    /**
    * 设置首逾率
    * 
    * @param firstOverdueRate 要设置的首逾率
    */
    public void setFirstOverdueRate(Double firstOverdueRate){
        this.firstOverdueRate = firstOverdueRate;
    }

    /**
     * 获取首逾数
     * @return
     */
    public Integer getFirstOverdue() {
        return firstOverdue;
    }

    /**
     * 设置首逾数
     * @param firstOverdue
     */
    public void setFirstOverdue(Integer firstOverdue) {
        this.firstOverdue = firstOverdue;
    }

    /**
     * 获取首贷订单数
     * @return
     */
    public Integer getFirstLoadCount() {
        return firstLoadCount;
    }

    /**
     * 设置首贷订单数
     * @param firstLoadCount
     */
    public void setFirstLoadCount(Integer firstLoadCount) {
        this.firstLoadCount = firstLoadCount;
    }

    /**
     * 获取当前逾期数
     * @return
     */
    public Integer getCurrentOverdue() {
        return currentOverdue;
    }

    /**
     * 设置当前逾期数
     * @param currentOverdue
     */
    public void setCurrentOverdue(Integer currentOverdue) {
        this.currentOverdue = currentOverdue;
    }

    /**
     * 获取放款订单数
     * @return
     */
    public Integer getLoadCount() {
        return loadCount;
    }

    /**
     * 设置放款订单数
     * @param loadCount
     */
    public void setLoadCount(Integer loadCount) {
        this.loadCount = loadCount;
    }

    /**
     * 获取当日到期展期逾期数
     * @return
     */
    public Integer getFirstExtendOverdueCount() {
        return firstExtendOverdueCount;
    }

    /**
     * 设置当日到期展期逾期数
     * @param firstExtendOverdueCount
     */
    public void setFirstExtendOverdueCount(Integer firstExtendOverdueCount) {
        this.firstExtendOverdueCount = firstExtendOverdueCount;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }
}
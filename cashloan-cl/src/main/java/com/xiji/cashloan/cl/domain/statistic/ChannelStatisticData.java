package com.xiji.cashloan.cl.domain.statistic;

import java.io.Serializable;
import java.util.Date;

/**
 * 渠道统计数据实体
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2019-02-15 15:40:18
 */
 public class ChannelStatisticData implements Serializable {

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
    * 渠道ID
    */
    private Integer channelId;

    /**
    * 当日注册数
    */
    private Integer userRegister;

    /**
    * 当日申请总数
    */
    private Integer borrowApplyCount;

    /**
    * 当日机审通过数
    */
    private Integer machineAuditPassCount;

    /**
    * 当日机审拒绝数
    */
    private Integer machineAuditNotPassCount;

    /**
    * 当日人工通过数
    */
    private Integer reviewPassCount;

    /**
    * 当日人工拒绝数
    */
    private Integer reviewNotPassCount;

    /**
    * 当日首贷放款笔数
    */
    private Integer firstLoadCount;

    /**
    * 当日复贷放款笔数
    */
    private Integer againLoadCount;

    /**
    * 当日到期逾期笔数
    */
    private Integer expireOverdueCount;

    /**
    * 当日到期首逾笔数
    */
    private Integer firstExpireOverdueCount;

    /**
     * 当日到期首逾笔数
     */
    private Integer againExpireOverdueCount;

    /**
    * 机审通过率
    */
    private Double machineAuditPassRate;

    /**
    * 机审拒绝率
    */
    private Double machineAuditNotPassRate;

    /**
    * 人工复审通过率
    */
    private Double reviewPassRate;

    /**
    * 人工复审拒绝率
    */
    private Double reviewNotPassRate;

    /**
    * 当日到期首逾率
    */
    private Double firstOverdueRate;

    /**
    * 当日到期逾期率
    */
    private Double overdueRate;

    /**
     * 老客逾期率
     */
    private Double againOverdueRate;

    /**
    * 放款率
    */
    private Double loadRate;

    /**
     * 当日到期展期数
     */
    private Integer extendCount;

    /**
     * 当日到期展期逾期数
     */
    private Integer extendOverdueCount;

    /**
     * 当日到期首贷展期数
     *
     */
    private Integer firstExtendCount;

    /**
     * 当日到期首贷展期逾期数
     */
    private Integer firstExtendOverdueCount;

    /**
     *
     * 当日到期首贷笔数
     *
     */
    private Integer firstExpireLoadCount;

    /**
     * 当日到期复贷笔数
     */
    private Integer againExpireLoadCount;

    /**
     * 当日申请总数
     */
    private Integer newBorrowApplyCount;

    /**
     * 当日新客机审通过数
     */
    private Integer newMachineAuditPassCount;

    /**
     * 当日新客机审拒绝数
     */
    private Integer newMachineAuditNotPassCount;

    /**
     * 当日新客人工通过数
     */
    private Integer newReviewPassCount;

    /**
     * 当日新客人工拒绝数
     */
    private Integer newReviewNotPassCount;



   /**
    * 渠道名称
    */
   private String channelName;

    /**
     * 渠道标识
     */
   private String channelCode;


    /**
     * 新客下款/申请数
     */
    private Double newLoadApplyRate;

    /**
     * 新客下款/注册数
     */
    private Double newLoadRegisterRate;

    /**
     * 新客转化率
     */
    private Double newTransformRate;

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
    * 获取渠道ID
    *
    * @return 渠道ID
    */
    public Integer getChannelId(){
        return channelId;
    }

    /**
    * 设置渠道ID
    * 
    * @param channelId 要设置的渠道ID
    */
    public void setChannelId(Integer channelId){
        this.channelId = channelId;
    }

    /**
    * 获取当日注册数
    *
    * @return 当日注册数
    */
    public Integer getUserRegister(){
        return userRegister;
    }

    /**
    * 设置当日注册数
    * 
    * @param userRegister 要设置的当日注册数
    */
    public void setUserRegister(Integer userRegister){
        this.userRegister = userRegister;
    }

    /**
    * 获取当日申请总数
    *
    * @return 当日申请总数
    */
    public Integer getBorrowApplyCount(){
        return borrowApplyCount;
    }

    /**
    * 设置当日申请总数
    * 
    * @param borrowApplyCount 要设置的当日申请总数
    */
    public void setBorrowApplyCount(Integer borrowApplyCount){
        this.borrowApplyCount = borrowApplyCount;
    }

    /**
    * 获取当日机审通过数
    *
    * @return 当日机审通过数
    */
    public Integer getMachineAuditPassCount(){
        return machineAuditPassCount;
    }

    /**
    * 设置当日机审通过数
    * 
    * @param machineAuditPassCount 要设置的当日机审通过数
    */
    public void setMachineAuditPassCount(Integer machineAuditPassCount){
        this.machineAuditPassCount = machineAuditPassCount;
    }

    /**
    * 获取当日机审拒绝数
    *
    * @return 当日机审拒绝数
    */
    public Integer getMachineAuditNotPassCount(){
        return machineAuditNotPassCount;
    }

    /**
    * 设置当日机审拒绝数
    * 
    * @param machineAuditNotPassCount 要设置的当日机审拒绝数
    */
    public void setMachineAuditNotPassCount(Integer machineAuditNotPassCount){
        this.machineAuditNotPassCount = machineAuditNotPassCount;
    }

    /**
    * 获取当日人工通过数
    *
    * @return 当日人工通过数
    */
    public Integer getReviewPassCount(){
        return reviewPassCount;
    }

    /**
    * 设置当日人工通过数
    * 
    * @param reviewPassCount 要设置的当日人工通过数
    */
    public void setReviewPassCount(Integer reviewPassCount){
        this.reviewPassCount = reviewPassCount;
    }

    /**
    * 获取当日人工拒绝数
    *
    * @return 当日人工拒绝数
    */
    public Integer getReviewNotPassCount(){
        return reviewNotPassCount;
    }

    /**
    * 设置当日人工拒绝数
    * 
    * @param reviewNotPassCount 要设置的当日人工拒绝数
    */
    public void setReviewNotPassCount(Integer reviewNotPassCount){
        this.reviewNotPassCount = reviewNotPassCount;
    }

    /**
    * 获取当日首贷放款笔数
    *
    * @return 当日首贷放款笔数
    */
    public Integer getFirstLoadCount(){
        return firstLoadCount;
    }

    /**
    * 设置当日首贷放款笔数
    * 
    * @param firstLoadCount 要设置的当日首贷放款笔数
    */
    public void setFirstLoadCount(Integer firstLoadCount){
        this.firstLoadCount = firstLoadCount;
    }

    /**
    * 获取当日复贷放款笔数
    *
    * @return 当日复贷放款笔数
    */
    public Integer getAgainLoadCount(){
        return againLoadCount;
    }

    /**
    * 设置当日复贷放款笔数
    * 
    * @param againLoadCount 要设置的当日复贷放款笔数
    */
    public void setAgainLoadCount(Integer againLoadCount){
        this.againLoadCount = againLoadCount;
    }

    /**
    * 获取当日逾期笔数
    *
    * @return 当日逾期笔数
    */
    public Integer getExpireOverdueCount(){
        return expireOverdueCount;
    }

    /**
    * 设置当日逾期笔数
    * 
    * @param expireOverdueCount 要设置的当日逾期笔数
    */
    public void setExpireOverdueCount(Integer expireOverdueCount){
        this.expireOverdueCount = expireOverdueCount;
    }

    /**
    * 获取当日首逾笔数
    *
    * @return 当日首逾笔数
    */
    public Integer getFirstExpireOverdueCount(){
        return firstExpireOverdueCount;
    }

    /**
    * 设置当日首逾笔数
    * 
    * @param firstExpireOverdueCount 要设置的当日首逾笔数
    */
    public void setFirstExpireOverdueCount(Integer firstExpireOverdueCount){
        this.firstExpireOverdueCount = firstExpireOverdueCount;
    }

    /**
    * 获取机审通过率
    *
    * @return 机审通过率
    */
    public Double getMachineAuditPassRate(){
        return machineAuditPassRate;
    }

    /**
    * 设置机审通过率
    * 
    * @param machineAuditPassRate 要设置的机审通过率
    */
    public void setMachineAuditPassRate(Double machineAuditPassRate){
        this.machineAuditPassRate = machineAuditPassRate;
    }

    /**
    * 获取机审拒绝率
    *
    * @return 机审拒绝率
    */
    public Double getMachineAuditNotPassRate(){
        return machineAuditNotPassRate;
    }

    /**
    * 设置机审拒绝率
    * 
    * @param machineAuditNotPassRate 要设置的机审拒绝率
    */
    public void setMachineAuditNotPassRate(Double machineAuditNotPassRate){
        this.machineAuditNotPassRate = machineAuditNotPassRate;
    }

    /**
    * 获取人工复审通过率
    *
    * @return 人工复审通过率
    */
    public Double getReviewPassRate(){
        return reviewPassRate;
    }

    /**
    * 设置人工复审通过率
    * 
    * @param reviewPassRate 要设置的人工复审通过率
    */
    public void setReviewPassRate(Double reviewPassRate){
        this.reviewPassRate = reviewPassRate;
    }

    /**
    * 获取人工复审拒绝率
    *
    * @return 人工复审拒绝率
    */
    public Double getReviewNotPassRate(){
        return reviewNotPassRate;
    }

    /**
    * 设置人工复审拒绝率
    * 
    * @param reviewNotPassRate 要设置的人工复审拒绝率
    */
    public void setReviewNotPassRate(Double reviewNotPassRate){
        this.reviewNotPassRate = reviewNotPassRate;
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
    * 获取逾期率
    *
    * @return 逾期率
    */
    public Double getOverdueRate(){
        return overdueRate;
    }

    /**
    * 设置逾期率
    * 
    * @param overdueRate 要设置的逾期率
    */
    public void setOverdueRate(Double overdueRate){
        this.overdueRate = overdueRate;
    }

    /**
    * 获取放款率
    *
    * @return 放款率
    */
    public Double getLoadRate(){
        return loadRate;
    }

    /**
    * 设置放款率
    * 
    * @param loadRate 要设置的放款率
    */
    public void setLoadRate(Double loadRate){
        this.loadRate = loadRate;
    }


    public Integer getExtendCount() {
        return extendCount;
    }

    public void setExtendCount(Integer extendCount) {
        this.extendCount = extendCount;
    }

    public Integer getExtendOverdueCount() {
        return extendOverdueCount;
    }

    public void setExtendOverdueCount(Integer extendOverdueCount) {
        this.extendOverdueCount = extendOverdueCount;
    }

    public Integer getFirstExtendCount() {
        return firstExtendCount;
    }

    public void setFirstExtendCount(Integer firstExtendCount) {
        this.firstExtendCount = firstExtendCount;
    }

    public Integer getFirstExtendOverdueCount() {
        return firstExtendOverdueCount;
    }

    public void setFirstExtendOverdueCount(Integer firstExtendOverdueCount) {
        this.firstExtendOverdueCount = firstExtendOverdueCount;
    }

    public Integer getFirstExpireLoadCount() {
        return firstExpireLoadCount;
    }

    public void setFirstExpireLoadCount(Integer firstExpireLoadCount) {
        this.firstExpireLoadCount = firstExpireLoadCount;
    }

    public Integer getAgainExpireLoadCount() {
        return againExpireLoadCount;
    }

    public void setAgainExpireLoadCount(Integer againExpireLoadCount) {
        this.againExpireLoadCount = againExpireLoadCount;
    }

    public Integer getNewBorrowApplyCount() {
        return newBorrowApplyCount;
    }

    public void setNewBorrowApplyCount(Integer newBorrowApplyCount) {
        this.newBorrowApplyCount = newBorrowApplyCount;
    }

    public Integer getNewMachineAuditPassCount() {
        return newMachineAuditPassCount;
    }

    public void setNewMachineAuditPassCount(Integer newMachineAuditPassCount) {
        this.newMachineAuditPassCount = newMachineAuditPassCount;
    }

    public Integer getNewMachineAuditNotPassCount() {
        return newMachineAuditNotPassCount;
    }

    public void setNewMachineAuditNotPassCount(Integer newMachineAuditNotPassCount) {
        this.newMachineAuditNotPassCount = newMachineAuditNotPassCount;
    }

    public Integer getNewReviewPassCount() {
        return newReviewPassCount;
    }

    public void setNewReviewPassCount(Integer newReviewPassCount) {
        this.newReviewPassCount = newReviewPassCount;
    }

    public Integer getNewReviewNotPassCount() {
        return newReviewNotPassCount;
    }

    public void setNewReviewNotPassCount(Integer newReviewNotPassCount) {
        this.newReviewNotPassCount = newReviewNotPassCount;
    }

    public Integer getAgainExpireOverdueCount() {
        return againExpireOverdueCount;
    }

    public void setAgainExpireOverdueCount(Integer againExpireOverdueCount) {
        this.againExpireOverdueCount = againExpireOverdueCount;
    }

    public Double getAgainOverdueRate() {
        return againOverdueRate;
    }

    public void setAgainOverdueRate(Double againOverdueRate) {
        this.againOverdueRate = againOverdueRate;
    }

   public String getChannelName() {
      return channelName;
   }

   public void setChannelName(String channelName) {
      this.channelName = channelName;
   }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public Double getNewLoadApplyRate() {
        return newLoadApplyRate;
    }

    public void setNewLoadApplyRate(Double newLoadApplyRate) {
        this.newLoadApplyRate = newLoadApplyRate;
    }

    public Double getNewLoadRegisterRate() {
        return newLoadRegisterRate;
    }

    public void setNewLoadRegisterRate(Double newLoadRegisterRate) {
        this.newLoadRegisterRate = newLoadRegisterRate;
    }

    public Double getNewTransformRate() {
        return newTransformRate;
    }

    public void setNewTransformRate(Double newTransformRate) {
        this.newTransformRate = newTransformRate;
    }

}
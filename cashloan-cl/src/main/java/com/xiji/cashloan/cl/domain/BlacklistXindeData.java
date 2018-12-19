package com.xiji.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 信德数聚灰名单实体
 * 
 * @author king
 * @version 1.0.0
 * @date 2018-12-19 14:15:32
 */
 public class BlacklistXindeData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 借款id
    */
    private Long borrowId;

    /**
    * 手机号
    */
    private String phone;

    /**
    * 身份证号
    */
    private String idCard;

    /**
    * 最后一次申请是否被拒贷 true: 是；false: 否
    */
    private String isLastloanRefused;

    /**
    * 历史借款次数(所有的借款次数，包含当前借款)
    */
    private Integer totalLoanCount;

    /**
    * 历史逾期次数(所有的逾期次数，包含当前逾期)
    */
    private Integer totalOverdueCount;

    /**
    * 已经还清的历史逾期最长时间，M1:小于1月; M2:大于1月，小于2月; M3:大于2月，小于3月; M4:3月及以上
    */
    private String longestOverduePaid;

    /**
    * 当前处于逾期状态的借款笔数
    */
    private Integer currentOverdueCount;

    /**
    * 当前逾期总金额，0: 0(没有逾期); 1:[0,100]; 2:[100,500); 3:[500,1000); 4:[1000,2000); 5:[2000,4000); 6:[4000,6000); 7:[6000,10000); 8:>=10000
    */
    private Integer currentOverdueAmount;

    /**
    * 有逾期90天以上运营商联系人个数
    */
    private Integer overDue90ContactsCount;

    /**
    * 当前最长逾期时间(不包括已经还清的)，M1:小于1月; M2:大于1月，小于2月; M3:大于2月，小于3月; M4:3月及以上
    */
    private String longestOverdueUnpaid;

    /**
    * 最后一次拒贷原因
    */
    private String lastLoanRefuseReason;

    /**
    * 最后一次拒贷时间
    */
    private String lastLoanRefuseTime;

    /**
    * 其他详情
    */
    private String remark;

    /**
    * 
    */
    private String firstLoanTime;

    /**
    * 
    */
    private Date createTime;

    /**
    * 
    */
    private Date lastModifyTime;


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
    * 获取借款id
    *
    * @return 借款id
    */
    public Long getBorrowId(){
        return borrowId;
    }

    /**
    * 设置借款id
    * 
    * @param borrowId 要设置的借款id
    */
    public void setBorrowId(Long borrowId){
        this.borrowId = borrowId;
    }

    /**
    * 获取手机号
    *
    * @return 手机号
    */
    public String getPhone(){
        return phone;
    }

    /**
    * 设置手机号
    * 
    * @param phone 要设置的手机号
    */
    public void setPhone(String phone){
        this.phone = phone;
    }

    /**
    * 获取身份证号
    *
    * @return 身份证号
    */
    public String getIdCard(){
        return idCard;
    }

    /**
    * 设置身份证号
    * 
    * @param idCard 要设置的身份证号
    */
    public void setIdCard(String idCard){
        this.idCard = idCard;
    }

    /**
    * 获取最后一次申请是否被拒贷 true: 是；false: 否
    *
    * @return 最后一次申请是否被拒贷 true: 是；false: 否
    */
    public String getIsLastloanRefused(){
        return isLastloanRefused;
    }

    /**
    * 设置最后一次申请是否被拒贷 true: 是；false: 否
    * 
    * @param isLastloanRefused 要设置的最后一次申请是否被拒贷 true: 是；false: 否
    */
    public void setIsLastloanRefused(String isLastloanRefused){
        this.isLastloanRefused = isLastloanRefused;
    }

    /**
    * 获取历史借款次数(所有的借款次数，包含当前借款)
    *
    * @return 历史借款次数(所有的借款次数，包含当前借款)
    */
    public Integer getTotalLoanCount(){
        return totalLoanCount;
    }

    /**
    * 设置历史借款次数(所有的借款次数，包含当前借款)
    * 
    * @param totalLoanCount 要设置的历史借款次数(所有的借款次数，包含当前借款)
    */
    public void setTotalLoanCount(Integer totalLoanCount){
        this.totalLoanCount = totalLoanCount;
    }

    /**
    * 获取历史逾期次数(所有的逾期次数，包含当前逾期)
    *
    * @return 历史逾期次数(所有的逾期次数，包含当前逾期)
    */
    public Integer getTotalOverdueCount(){
        return totalOverdueCount;
    }

    /**
    * 设置历史逾期次数(所有的逾期次数，包含当前逾期)
    * 
    * @param totalOverdueCount 要设置的历史逾期次数(所有的逾期次数，包含当前逾期)
    */
    public void setTotalOverdueCount(Integer totalOverdueCount){
        this.totalOverdueCount = totalOverdueCount;
    }

    /**
    * 获取已经还清的历史逾期最长时间，M1:小于1月; M2:大于1月，小于2月; M3:大于2月，小于3月; M4:3月及以上
    *
    * @return 已经还清的历史逾期最长时间，M1:小于1月; M2:大于1月，小于2月; M3:大于2月，小于3月; M4:3月及以上
    */
    public String getLongestOverduePaid(){
        return longestOverduePaid;
    }

    /**
    * 设置已经还清的历史逾期最长时间，M1:小于1月; M2:大于1月，小于2月; M3:大于2月，小于3月; M4:3月及以上
    * 
    * @param longestOverduePaid 要设置的已经还清的历史逾期最长时间，M1:小于1月; M2:大于1月，小于2月; M3:大于2月，小于3月; M4:3月及以上
    */
    public void setLongestOverduePaid(String longestOverduePaid){
        this.longestOverduePaid = longestOverduePaid;
    }

    /**
    * 获取当前处于逾期状态的借款笔数
    *
    * @return 当前处于逾期状态的借款笔数
    */
    public Integer getCurrentOverdueCount(){
        return currentOverdueCount;
    }

    /**
    * 设置当前处于逾期状态的借款笔数
    * 
    * @param currentOverdueCount 要设置的当前处于逾期状态的借款笔数
    */
    public void setCurrentOverdueCount(Integer currentOverdueCount){
        this.currentOverdueCount = currentOverdueCount;
    }

    /**
    * 获取当前逾期总金额，0: 0(没有逾期); 1:[0,100]; 2:[100,500); 3:[500,1000); 4:[1000,2000); 5:[2000,4000); 6:[4000,6000); 7:[6000,10000); 8:>=10000
    *
    * @return 当前逾期总金额，0: 0(没有逾期); 1:[0,100]; 2:[100,500); 3:[500,1000); 4:[1000,2000); 5:[2000,4000); 6:[4000,6000); 7:[6000,10000); 8:>=10000
    */
    public Integer getCurrentOverdueAmount(){
        return currentOverdueAmount;
    }

    /**
    * 设置当前逾期总金额，0: 0(没有逾期); 1:[0,100]; 2:[100,500); 3:[500,1000); 4:[1000,2000); 5:[2000,4000); 6:[4000,6000); 7:[6000,10000); 8:>=10000
    * 
    * @param currentOverdueAmount 要设置的当前逾期总金额，0: 0(没有逾期); 1:[0,100]; 2:[100,500); 3:[500,1000); 4:[1000,2000); 5:[2000,4000); 6:[4000,6000); 7:[6000,10000); 8:>=10000
    */
    public void setCurrentOverdueAmount(Integer currentOverdueAmount){
        this.currentOverdueAmount = currentOverdueAmount;
    }

    /**
    * 获取有逾期90天以上运营商联系人个数
    *
    * @return 有逾期90天以上运营商联系人个数
    */
    public Integer getOverDue90ContactsCount(){
        return overDue90ContactsCount;
    }

    /**
    * 设置有逾期90天以上运营商联系人个数
    * 
    * @param overDue90ContactsCount 要设置的有逾期90天以上运营商联系人个数
    */
    public void setOverDue90ContactsCount(Integer overDue90ContactsCount){
        this.overDue90ContactsCount = overDue90ContactsCount;
    }

    /**
    * 获取当前最长逾期时间(不包括已经还清的)，M1:小于1月; M2:大于1月，小于2月; M3:大于2月，小于3月; M4:3月及以上
    *
    * @return 当前最长逾期时间(不包括已经还清的)，M1:小于1月; M2:大于1月，小于2月; M3:大于2月，小于3月; M4:3月及以上
    */
    public String getLongestOverdueUnpaid(){
        return longestOverdueUnpaid;
    }

    /**
    * 设置当前最长逾期时间(不包括已经还清的)，M1:小于1月; M2:大于1月，小于2月; M3:大于2月，小于3月; M4:3月及以上
    * 
    * @param longestOverdueUnpaid 要设置的当前最长逾期时间(不包括已经还清的)，M1:小于1月; M2:大于1月，小于2月; M3:大于2月，小于3月; M4:3月及以上
    */
    public void setLongestOverdueUnpaid(String longestOverdueUnpaid){
        this.longestOverdueUnpaid = longestOverdueUnpaid;
    }

    /**
    * 获取最后一次拒贷原因
    *
    * @return 最后一次拒贷原因
    */
    public String getLastLoanRefuseReason(){
        return lastLoanRefuseReason;
    }

    /**
    * 设置最后一次拒贷原因
    * 
    * @param lastLoanRefuseReason 要设置的最后一次拒贷原因
    */
    public void setLastLoanRefuseReason(String lastLoanRefuseReason){
        this.lastLoanRefuseReason = lastLoanRefuseReason;
    }

    /**
    * 获取最后一次拒贷时间
    *
    * @return 最后一次拒贷时间
    */
    public String getLastLoanRefuseTime(){
        return lastLoanRefuseTime;
    }

    /**
    * 设置最后一次拒贷时间
    * 
    * @param lastLoanRefuseTime 要设置的最后一次拒贷时间
    */
    public void setLastLoanRefuseTime(String lastLoanRefuseTime){
        this.lastLoanRefuseTime = lastLoanRefuseTime;
    }

    /**
    * 获取其他详情
    *
    * @return 其他详情
    */
    public String getRemark(){
        return remark;
    }

    /**
    * 设置其他详情
    * 
    * @param remark 要设置的其他详情
    */
    public void setRemark(String remark){
        this.remark = remark;
    }

    /**
    * 获取
    *
    * @return 
    */
    public String getFirstLoanTime(){
        return firstLoanTime;
    }

    /**
    * 设置
    * 
    * @param firstLoanTime 要设置的
    */
    public void setFirstLoanTime(String firstLoanTime){
        this.firstLoanTime = firstLoanTime;
    }

    /**
    * 获取
    *
    * @return 
    */
    public Date getCreateTime(){
        return createTime;
    }

    /**
    * 设置
    * 
    * @param createTime 要设置的
    */
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    /**
    * 获取
    *
    * @return 
    */
    public Date getLastModifyTime(){
        return lastModifyTime;
    }

    /**
    * 设置
    * 
    * @param lastModifyTime 要设置的
    */
    public void setLastModifyTime(Date lastModifyTime){
        this.lastModifyTime = lastModifyTime;
    }

}
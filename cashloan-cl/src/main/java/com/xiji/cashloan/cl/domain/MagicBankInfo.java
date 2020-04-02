package com.xiji.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 魔杖2.0报告-基础信息表实体
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-01 10:56:03
 */
 public class MagicBankInfo implements Serializable {

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
    * 报告id
    */
    private String transId;

    /**
    * 最近数据更新时间. 格式为yyyy-MM-dd
    */
    private String debitUpdateDate;

    /**
    * 借记卡数量
    */
    private Integer debitCardAmount;

    /**
    * 借记卡总余额
    */
    private String debitBalance;

    /**
    * 近一年总收入
    */
    private String debitTotalIncome;

    /**
    * 近一年工资收入
    */
    private String debitTotalSalaryIncome;

    /**
    * 近一年贷款收入
    */
    private String debitTotalLoanIncome;

    /**
    * 近一年总支出
    */
    private String debitTotalOutcome;

    /**
    * 近一年消费支出
    */
    private String debitTotalConsumeOutcome;

    /**
    * 近一年还贷支出
    */
    private String debitTotalLoanOutcome;

    /**
    * 最近数据更新时间. 格式为yyyy-MM-dd
    */
    private String creditUpdateDate;

    /**
    * 信用卡数量
    */
    private Integer creditCardAmount;

    /**
    * 总信用额度
    */
    private String creditTotalCreditLimit;

    /**
    * 总可用信用额
    */
    private String creditTotalCreditAvailable;

    /**
    * 单一银行最高信用额
    */
    private String creditMaxCreditLimit;

    /**
    * 近一年逾期次数
    */
    private Integer creditOverdueTimes;

    /**
    * 近一年逾期月数
    */
    private Integer creditOverdueMonths;

    /**
    * 创建时间
    */
    private Date createTime;


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
    * 获取用户id
    *
    * @return 用户id
    */
    public Long getUserId(){
        return userId;
    }

    /**
    * 设置用户id
    * 
    * @param userId 要设置的用户id
    */
    public void setUserId(Long userId){
        this.userId = userId;
    }

    /**
    * 获取报告id
    *
    * @return 报告id
    */
    public String getTransId(){
        return transId;
    }

    /**
    * 设置报告id
    * 
    * @param transId 要设置的报告id
    */
    public void setTransId(String transId){
        this.transId = transId;
    }

    /**
    * 获取最近数据更新时间. 格式为yyyy-MM-dd
    *
    * @return 最近数据更新时间. 格式为yyyy-MM-dd
    */
    public String getDebitUpdateDate(){
        return debitUpdateDate;
    }

    /**
    * 设置最近数据更新时间. 格式为yyyy-MM-dd
    * 
    * @param debitUpdateDate 要设置的最近数据更新时间. 格式为yyyy-MM-dd
    */
    public void setDebitUpdateDate(String debitUpdateDate){
        this.debitUpdateDate = debitUpdateDate;
    }

    /**
    * 获取借记卡数量
    *
    * @return 借记卡数量
    */
    public Integer getDebitCardAmount(){
        return debitCardAmount;
    }

    /**
    * 设置借记卡数量
    * 
    * @param debitCardAmount 要设置的借记卡数量
    */
    public void setDebitCardAmount(Integer debitCardAmount){
        this.debitCardAmount = debitCardAmount;
    }

    /**
    * 获取借记卡总余额
    *
    * @return 借记卡总余额
    */
    public String getDebitBalance(){
        return debitBalance;
    }

    /**
    * 设置借记卡总余额
    * 
    * @param debitBalance 要设置的借记卡总余额
    */
    public void setDebitBalance(String debitBalance){
        this.debitBalance = debitBalance;
    }

    /**
    * 获取近一年总收入
    *
    * @return 近一年总收入
    */
    public String getDebitTotalIncome(){
        return debitTotalIncome;
    }

    /**
    * 设置近一年总收入
    * 
    * @param debitTotalIncome 要设置的近一年总收入
    */
    public void setDebitTotalIncome(String debitTotalIncome){
        this.debitTotalIncome = debitTotalIncome;
    }

    /**
    * 获取近一年工资收入
    *
    * @return 近一年工资收入
    */
    public String getDebitTotalSalaryIncome(){
        return debitTotalSalaryIncome;
    }

    /**
    * 设置近一年工资收入
    * 
    * @param debitTotalSalaryIncome 要设置的近一年工资收入
    */
    public void setDebitTotalSalaryIncome(String debitTotalSalaryIncome){
        this.debitTotalSalaryIncome = debitTotalSalaryIncome;
    }

    /**
    * 获取近一年贷款收入
    *
    * @return 近一年贷款收入
    */
    public String getDebitTotalLoanIncome(){
        return debitTotalLoanIncome;
    }

    /**
    * 设置近一年贷款收入
    * 
    * @param debitTotalLoanIncome 要设置的近一年贷款收入
    */
    public void setDebitTotalLoanIncome(String debitTotalLoanIncome){
        this.debitTotalLoanIncome = debitTotalLoanIncome;
    }

    /**
    * 获取近一年总支出
    *
    * @return 近一年总支出
    */
    public String getDebitTotalOutcome(){
        return debitTotalOutcome;
    }

    /**
    * 设置近一年总支出
    * 
    * @param debitTotalOutcome 要设置的近一年总支出
    */
    public void setDebitTotalOutcome(String debitTotalOutcome){
        this.debitTotalOutcome = debitTotalOutcome;
    }

    /**
    * 获取近一年消费支出
    *
    * @return 近一年消费支出
    */
    public String getDebitTotalConsumeOutcome(){
        return debitTotalConsumeOutcome;
    }

    /**
    * 设置近一年消费支出
    * 
    * @param debitTotalConsumeOutcome 要设置的近一年消费支出
    */
    public void setDebitTotalConsumeOutcome(String debitTotalConsumeOutcome){
        this.debitTotalConsumeOutcome = debitTotalConsumeOutcome;
    }

    /**
    * 获取近一年还贷支出
    *
    * @return 近一年还贷支出
    */
    public String getDebitTotalLoanOutcome(){
        return debitTotalLoanOutcome;
    }

    /**
    * 设置近一年还贷支出
    * 
    * @param debitTotalLoanOutcome 要设置的近一年还贷支出
    */
    public void setDebitTotalLoanOutcome(String debitTotalLoanOutcome){
        this.debitTotalLoanOutcome = debitTotalLoanOutcome;
    }

    /**
    * 获取最近数据更新时间. 格式为yyyy-MM-dd
    *
    * @return 最近数据更新时间. 格式为yyyy-MM-dd
    */
    public String getCreditUpdateDate(){
        return creditUpdateDate;
    }

    /**
    * 设置最近数据更新时间. 格式为yyyy-MM-dd
    * 
    * @param creditUpdateDate 要设置的最近数据更新时间. 格式为yyyy-MM-dd
    */
    public void setCreditUpdateDate(String creditUpdateDate){
        this.creditUpdateDate = creditUpdateDate;
    }

    /**
    * 获取信用卡数量
    *
    * @return 信用卡数量
    */
    public Integer getCreditCardAmount(){
        return creditCardAmount;
    }

    /**
    * 设置信用卡数量
    * 
    * @param creditCardAmount 要设置的信用卡数量
    */
    public void setCreditCardAmount(Integer creditCardAmount){
        this.creditCardAmount = creditCardAmount;
    }

    /**
    * 获取总信用额度
    *
    * @return 总信用额度
    */
    public String getCreditTotalCreditLimit(){
        return creditTotalCreditLimit;
    }

    /**
    * 设置总信用额度
    * 
    * @param creditTotalCreditLimit 要设置的总信用额度
    */
    public void setCreditTotalCreditLimit(String creditTotalCreditLimit){
        this.creditTotalCreditLimit = creditTotalCreditLimit;
    }

    /**
    * 获取总可用信用额
    *
    * @return 总可用信用额
    */
    public String getCreditTotalCreditAvailable(){
        return creditTotalCreditAvailable;
    }

    /**
    * 设置总可用信用额
    * 
    * @param creditTotalCreditAvailable 要设置的总可用信用额
    */
    public void setCreditTotalCreditAvailable(String creditTotalCreditAvailable){
        this.creditTotalCreditAvailable = creditTotalCreditAvailable;
    }

    /**
    * 获取单一银行最高信用额
    *
    * @return 单一银行最高信用额
    */
    public String getCreditMaxCreditLimit(){
        return creditMaxCreditLimit;
    }

    /**
    * 设置单一银行最高信用额
    * 
    * @param creditMaxCreditLimit 要设置的单一银行最高信用额
    */
    public void setCreditMaxCreditLimit(String creditMaxCreditLimit){
        this.creditMaxCreditLimit = creditMaxCreditLimit;
    }

    /**
    * 获取近一年逾期次数
    *
    * @return 近一年逾期次数
    */
    public Integer getCreditOverdueTimes(){
        return creditOverdueTimes;
    }

    /**
    * 设置近一年逾期次数
    * 
    * @param creditOverdueTimes 要设置的近一年逾期次数
    */
    public void setCreditOverdueTimes(Integer creditOverdueTimes){
        this.creditOverdueTimes = creditOverdueTimes;
    }

    /**
    * 获取近一年逾期月数
    *
    * @return 近一年逾期月数
    */
    public Integer getCreditOverdueMonths(){
        return creditOverdueMonths;
    }

    /**
    * 设置近一年逾期月数
    * 
    * @param creditOverdueMonths 要设置的近一年逾期月数
    */
    public void setCreditOverdueMonths(Integer creditOverdueMonths){
        this.creditOverdueMonths = creditOverdueMonths;
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

}
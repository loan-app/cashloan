package com.xiji.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 魔杖2.0报告-基础信息表实体
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-01 10:56:04
 */
 public class MagicLoanBehaviorAnalysis implements Serializable {

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
    * 借贷第一次逾期距今天数(确定)
    */
    private String defaultdayFromFirstToEnd;

    /**
    * 最近7日借贷逾期还款笔数
    */
    private String jiedai4SumFailCntD7;

    /**
    * 最近7日借贷逾期平均天数(确定)
    */
    private String jiedaiAvgDefaultdaysD7;

    /**
    * 最近7日借贷逾期还款大于3日笔数
    */
    private String jiedai4CountFillD3CntD7;

    /**
    * 最近7日借贷逾期最大天数(确定)
    */
    private String jiedaiMaxDefaultdaysD7;

    /**
    * 最近7日借贷逾期借贷大于5天笔数
    */
    private String ddJiedaiSumFillD5CntD7;

    /**
    * 最近7日借贷逾期借贷新增借贷平台数
    */
    private String ddJiedaiCountFailMamberaddD7;

    /**
    * 最近7日借贷逾期借贷平均天数(疑似)
    */
    private String ddJiedaiAvgFailDays1D7;

    /**
    * 最近7日借贷逾期最小天数(确定)
    */
    private String jiedaiMinDefaultdaysD7;

    /**
    * 近7天最近一次逾期距今天数(确定)
    */
    private String lastToEndSureDueAllProAllTimeD7;

    /**
    * 近7天非超短期现金贷最大逾期天数(确定)
    */
    private String maxSureDueDaysNonCdqAllTimeD7;

    /**
    * 近7天累计逾期天数(确定)
    */
    private String sumSureDueDaysAllProAllTimeD7;

    /**
    * 近7天最近一次非超短期现金贷逾期距今天数(确定)
    */
    private String lastToEndSureDueNonCdqAllTimeD7;

    /**
    * 最近15日借贷逾期平均天数(确定)
    */
    private String jiedaiAvgDefaultdaysD15;

    /**
    * 最近15日借贷逾期最小天数(确定)
    */
    private String jiedaiMinDefaultdaysD15;

    /**
    * 最近15日借贷逾期还款笔数
    */
    private String jiedai4SumFailCntD15;

    /**
    * 最近15日借贷逾期借贷平均天数(疑似)
    */
    private String ddJiedaiAvgFailDays1D15;

    /**
    * 最近15日借贷逾期还款大于3日笔数
    */
    private String jiedai4CountFillD3CntD15;

    /**
    * 最近15日借贷逾期还款大于5日笔数
    */
    private String jiedai4CountFillD5CntD15;

    /**
    * 最近15日借贷逾期借贷新增借贷平台数
    */
    private String ddJiedaiCountFailMamberaddD15;

    /**
    * 最近15日逾期借贷总金额
    */
    private String jiedaiSumFailAmtD15;

    /**
    * 最近15日借贷逾期借贷最大天数(疑似)
    */
    private String ddJiedaiMaxFailDays1D15;

    /**
    * 近15天累计逾期天数(确定)
    */
    private String sumSureDueDaysAllProAllTimeD15;

    /**
    * 近15天最近一次逾期距今天数(确定)
    */
    private String lastToEndSureDueAllProAllTimeD15;

    /**
    * 近15天非超短期现金贷最大逾期天数(确定)
    */
    private String maxSureDueDaysNonCdqAllTimeD15;

    /**
    * 近15天最近一次非超短期现金贷逾期距今天数(确定)
    */
    private String lastToEndSureDueNonCdqAllTimeD15;

    /**
    * 最近1月借贷逾期平均天数(确定)
    */
    private String jiedaiAvgDefaultdaysM1;

    /**
    * 最近1月借贷逾期借贷最大天数(疑似)
    */
    private String ddJiedaiMaxFailDays1M1;

    /**
    * 最近1月借贷逾期还款笔数
    */
    private String jiedai4SumFailCnt1;

    /**
    * 最近1月借贷逾期借贷最大天数(确定)
    */
    private String ddJiedaiMaxFailDaysM1;

    /**
    * 最近1月借贷逾期借贷新增借贷平台数
    */
    private String ddJiedaiCountFailMamberaddM1;

    /**
    * 最近1月借贷逾期还款大于3日笔数
    */
    private String jiedai4CountFillD3CntM1;

    /**
    * 最近1月借贷逾期借贷最小天数(疑似)
    */
    private String ddJiedaiMinFailDays1M1;

    /**
    * 最近1月逾期借贷总金额
    */
    private String jiedaiSumFailAmt1;

    /**
    * 最近1月超短期借贷逾期借贷最大天数(疑似)
    */
    private String cdqDdJiedaiMaxFailDays1M1;

    /**
    * 最近1月借贷逾期还款大于5日笔数
    */
    private String jiedai4CountFillD5CntM1;

    /**
    * 最近1月借贷未逾期还款金额平均值
    */
    private String jiedai4AvgSuccAmt1;

    /**
    * 近1个月非超短期现金贷累计逾期天数(确定)
    */
    private String sumSureDueDaysNonCdqAllTimeM1;

    /**
    * 近1个月累计逾期天数(确定)
    */
    private String sumSureDueDaysAllProAllTimeM1;

    /**
    * 近1个月非超短期现金贷平均逾期天数(确定)
    */
    private String avgSureDueDaysNonCdqAllTimeM1;

    /**
    * 近1个月超短期现金贷还款金额占比
    */
    private String pctPayAmtCdqProAllTimeM1;

    /**
    * 近1个月最大还款金额
    */
    private String maxPayAmtAllProAllTimeM1;

    /**
    * 最近3月借贷逾期平均天数(确定)
    */
    private String jiedaiAvgDefaultdaysM3;

    /**
    * 最近3月借贷逾期借贷最大天数(疑似)
    */
    private String ddJiedaiMaxFailDays1M3;

    /**
    * 最近3月借贷逾期借贷平均天数(确定)
    */
    private String ddJiedaiAvgFailDaysM3;

    /**
    * 最近3月借贷逾期借贷新增借贷平台数
    */
    private String ddJiedaiCountFailMamberaddM3;

    /**
    * 最近3月借贷逾期还款大于3日笔数
    */
    private String jiedai4CountFillD3CntM3;

    /**
    * 最近3月借贷逾期还款大于5日笔数
    */
    private String jiedai4CountFillD5CntM3;

    /**
    * 最近3月超短期借贷逾期借贷平均天数(疑似)
    */
    private String cdqDdJiedaiAvgFailDays1M3;

    /**
    * 最近3月借贷未逾期还款金额平均值
    */
    private String jiedai4AvgSuccAmt3;

    /**
    * 最近3月逾期借贷总金额
    */
    private String jiedaiSumFailAmt3;

    /**
    * 最近3月借贷逾期借贷最小天数(疑似)
    */
    private String ddJiedaiMinFailDays1M3;

    /**
    * 近3个月累计逾期天数(确定)
    */
    private String sumSureDueDaysAllProAllTimeM3;

    /**
    * 近3个月非超短期现金贷累计逾期天数(确定)
    */
    private String sumSureDueDaysNonCdqAllTimeM3;

    /**
    * 近3个月平均逾期天数(确定)
    */
    private String avgSureDueDaysAllProAllTimeM3;

    /**
    * 近3个月非超短期现金贷最大逾期次数
    */
    private String maxDueCntNonCdqAllTimeM3;

    /**
    * 近3个月非超短期现金贷平均逾期天数(确定)
    */
    private String avgSureDueDaysNonCdqAllTimeM3;

    /**
    * 近3个月超短期现金贷还款金额占比
    */
    private String pctPayAmtCdqProAllTimeM3;

    /**
    * 最近6月借贷逾期平均天数(确定)
    */
    private String jiedaiAvgDefaultdaysM6;

    /**
    * 最近6月借贷逾期借贷平均天数(疑似)
    */
    private String ddJiedaiAvgFailDays1M6;

    /**
    * 最近6月借贷逾期借贷平均天数(确定)
    */
    private String ddJiedaiAvgFailDaysM6;

    /**
    * 最近6月借贷逾期借贷新增借贷平台数
    */
    private String ddJiedaiCountFailMamberaddM6;

    /**
    * 最近6月借贷逾期还款大于3日笔数
    */
    private String jiedai4CountFillD3CntM6;

    /**
    * 最近6月超短期借贷逾期借贷平均天数(疑似)
    */
    private String cdqDdJiedaiAvgFailDays1M6;

    /**
    * 最近6月超短期借贷逾期借贷最大天数(疑似)
    */
    private String cdqDdJiedaiMaxFailDays1M6;

    /**
    * 最近6月借贷未逾期还款金额平均值
    */
    private String jiedai4AvgSuccAmt6;

    /**
    * 最近6月借贷逾期还款大于5日笔数
    */
    private String jiedai4CountFillD5CntM6;

    /**
    * 近6个月非超短期现金贷累计逾期天数(确定)
    */
    private String sumSureDueDaysNonCdqAllTimeM6;

    /**
    * 近6个月最大逾期天数(确定)
    */
    private String maxSureDueDaysAllProAllTimeM6;

    /**
    * 近6个月平均逾期天数(确定)
    */
    private String avgSureDueDaysAllProAllTimeM6;

    /**
    * 近6个月非超短期现金贷平均逾期天数(确定)
    */
    private String avgSureDueDaysNonCdqAllTimeM6;

    /**
    * 近6个月超短期现金贷还款金额占比
    */
    private String pctPayAmtCdqProAllTimeM6;

    /**
    * 最近12月借贷逾期借贷最大天数(确定)
    */
    private String ddJiedaiMaxFailDaysM12;

    /**
    * 最近12月借贷逾期借贷大于5天笔数
    */
    private String ddJiedaiSumFillD5CntM12;

    /**
    * 近12个月最近一次逾期距今天数(确定)
    */
    private String lastToEndSureDueAllProAllTimeM12;

    /**
    * 近12个月非超短期现金贷累计逾期天数(确定)
    */
    private String sumSureDueDaysNonCdqAllTimeM12;

    /**
    * 近12个月最近一次非超短期现金贷逾期距今天数(确定)
    */
    private String lastToEndSureDueNonCdqAllTimeM12;

    /**
    * 近12个月最大逾期次数
    */
    private String maxDueCntAllProAllTimeM12;

    /**
    * 近12个月非超短期现金贷最大逾期次数
    */
    private String maxDueCntNonCdqAllTimeM12;

    /**
    * 近12个月最大还款金额
    */
    private String maxPayAmtAllProAllTimeM12;

    /**
    * 近12个月累计还款笔数
    */
    private String sumPayCntAllProAllTimeM12;

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
    * 获取借贷第一次逾期距今天数(确定)
    *
    * @return 借贷第一次逾期距今天数(确定)
    */
    public String getDefaultdayFromFirstToEnd(){
        return defaultdayFromFirstToEnd;
    }

    /**
    * 设置借贷第一次逾期距今天数(确定)
    * 
    * @param defaultdayFromFirstToEnd 要设置的借贷第一次逾期距今天数(确定)
    */
    public void setDefaultdayFromFirstToEnd(String defaultdayFromFirstToEnd){
        this.defaultdayFromFirstToEnd = defaultdayFromFirstToEnd;
    }

    /**
    * 获取最近7日借贷逾期还款笔数
    *
    * @return 最近7日借贷逾期还款笔数
    */
    public String getJiedai4SumFailCntD7(){
        return jiedai4SumFailCntD7;
    }

    /**
    * 设置最近7日借贷逾期还款笔数
    * 
    * @param jiedai4SumFailCntD7 要设置的最近7日借贷逾期还款笔数
    */
    public void setJiedai4SumFailCntD7(String jiedai4SumFailCntD7){
        this.jiedai4SumFailCntD7 = jiedai4SumFailCntD7;
    }

    /**
    * 获取最近7日借贷逾期平均天数(确定)
    *
    * @return 最近7日借贷逾期平均天数(确定)
    */
    public String getJiedaiAvgDefaultdaysD7(){
        return jiedaiAvgDefaultdaysD7;
    }

    /**
    * 设置最近7日借贷逾期平均天数(确定)
    * 
    * @param jiedaiAvgDefaultdaysD7 要设置的最近7日借贷逾期平均天数(确定)
    */
    public void setJiedaiAvgDefaultdaysD7(String jiedaiAvgDefaultdaysD7){
        this.jiedaiAvgDefaultdaysD7 = jiedaiAvgDefaultdaysD7;
    }

    /**
    * 获取最近7日借贷逾期还款大于3日笔数
    *
    * @return 最近7日借贷逾期还款大于3日笔数
    */
    public String getJiedai4CountFillD3CntD7(){
        return jiedai4CountFillD3CntD7;
    }

    /**
    * 设置最近7日借贷逾期还款大于3日笔数
    * 
    * @param jiedai4CountFillD3CntD7 要设置的最近7日借贷逾期还款大于3日笔数
    */
    public void setJiedai4CountFillD3CntD7(String jiedai4CountFillD3CntD7){
        this.jiedai4CountFillD3CntD7 = jiedai4CountFillD3CntD7;
    }

    /**
    * 获取最近7日借贷逾期最大天数(确定)
    *
    * @return 最近7日借贷逾期最大天数(确定)
    */
    public String getJiedaiMaxDefaultdaysD7(){
        return jiedaiMaxDefaultdaysD7;
    }

    /**
    * 设置最近7日借贷逾期最大天数(确定)
    * 
    * @param jiedaiMaxDefaultdaysD7 要设置的最近7日借贷逾期最大天数(确定)
    */
    public void setJiedaiMaxDefaultdaysD7(String jiedaiMaxDefaultdaysD7){
        this.jiedaiMaxDefaultdaysD7 = jiedaiMaxDefaultdaysD7;
    }

    /**
    * 获取最近7日借贷逾期借贷大于5天笔数
    *
    * @return 最近7日借贷逾期借贷大于5天笔数
    */
    public String getDdJiedaiSumFillD5CntD7(){
        return ddJiedaiSumFillD5CntD7;
    }

    /**
    * 设置最近7日借贷逾期借贷大于5天笔数
    * 
    * @param ddJiedaiSumFillD5CntD7 要设置的最近7日借贷逾期借贷大于5天笔数
    */
    public void setDdJiedaiSumFillD5CntD7(String ddJiedaiSumFillD5CntD7){
        this.ddJiedaiSumFillD5CntD7 = ddJiedaiSumFillD5CntD7;
    }

    /**
    * 获取最近7日借贷逾期借贷新增借贷平台数
    *
    * @return 最近7日借贷逾期借贷新增借贷平台数
    */
    public String getDdJiedaiCountFailMamberaddD7(){
        return ddJiedaiCountFailMamberaddD7;
    }

    /**
    * 设置最近7日借贷逾期借贷新增借贷平台数
    * 
    * @param ddJiedaiCountFailMamberaddD7 要设置的最近7日借贷逾期借贷新增借贷平台数
    */
    public void setDdJiedaiCountFailMamberaddD7(String ddJiedaiCountFailMamberaddD7){
        this.ddJiedaiCountFailMamberaddD7 = ddJiedaiCountFailMamberaddD7;
    }

    /**
    * 获取最近7日借贷逾期借贷平均天数(疑似)
    *
    * @return 最近7日借贷逾期借贷平均天数(疑似)
    */
    public String getDdJiedaiAvgFailDays1D7(){
        return ddJiedaiAvgFailDays1D7;
    }

    /**
    * 设置最近7日借贷逾期借贷平均天数(疑似)
    * 
    * @param ddJiedaiAvgFailDays1D7 要设置的最近7日借贷逾期借贷平均天数(疑似)
    */
    public void setDdJiedaiAvgFailDays1D7(String ddJiedaiAvgFailDays1D7){
        this.ddJiedaiAvgFailDays1D7 = ddJiedaiAvgFailDays1D7;
    }

    /**
    * 获取最近7日借贷逾期最小天数(确定)
    *
    * @return 最近7日借贷逾期最小天数(确定)
    */
    public String getJiedaiMinDefaultdaysD7(){
        return jiedaiMinDefaultdaysD7;
    }

    /**
    * 设置最近7日借贷逾期最小天数(确定)
    * 
    * @param jiedaiMinDefaultdaysD7 要设置的最近7日借贷逾期最小天数(确定)
    */
    public void setJiedaiMinDefaultdaysD7(String jiedaiMinDefaultdaysD7){
        this.jiedaiMinDefaultdaysD7 = jiedaiMinDefaultdaysD7;
    }

    /**
    * 获取近7天最近一次逾期距今天数(确定)
    *
    * @return 近7天最近一次逾期距今天数(确定)
    */
    public String getLastToEndSureDueAllProAllTimeD7(){
        return lastToEndSureDueAllProAllTimeD7;
    }

    /**
    * 设置近7天最近一次逾期距今天数(确定)
    * 
    * @param lastToEndSureDueAllProAllTimeD7 要设置的近7天最近一次逾期距今天数(确定)
    */
    public void setLastToEndSureDueAllProAllTimeD7(String lastToEndSureDueAllProAllTimeD7){
        this.lastToEndSureDueAllProAllTimeD7 = lastToEndSureDueAllProAllTimeD7;
    }

    /**
    * 获取近7天非超短期现金贷最大逾期天数(确定)
    *
    * @return 近7天非超短期现金贷最大逾期天数(确定)
    */
    public String getMaxSureDueDaysNonCdqAllTimeD7(){
        return maxSureDueDaysNonCdqAllTimeD7;
    }

    /**
    * 设置近7天非超短期现金贷最大逾期天数(确定)
    * 
    * @param maxSureDueDaysNonCdqAllTimeD7 要设置的近7天非超短期现金贷最大逾期天数(确定)
    */
    public void setMaxSureDueDaysNonCdqAllTimeD7(String maxSureDueDaysNonCdqAllTimeD7){
        this.maxSureDueDaysNonCdqAllTimeD7 = maxSureDueDaysNonCdqAllTimeD7;
    }

    /**
    * 获取近7天累计逾期天数(确定)
    *
    * @return 近7天累计逾期天数(确定)
    */
    public String getSumSureDueDaysAllProAllTimeD7(){
        return sumSureDueDaysAllProAllTimeD7;
    }

    /**
    * 设置近7天累计逾期天数(确定)
    * 
    * @param sumSureDueDaysAllProAllTimeD7 要设置的近7天累计逾期天数(确定)
    */
    public void setSumSureDueDaysAllProAllTimeD7(String sumSureDueDaysAllProAllTimeD7){
        this.sumSureDueDaysAllProAllTimeD7 = sumSureDueDaysAllProAllTimeD7;
    }

    /**
    * 获取近7天最近一次非超短期现金贷逾期距今天数(确定)
    *
    * @return 近7天最近一次非超短期现金贷逾期距今天数(确定)
    */
    public String getLastToEndSureDueNonCdqAllTimeD7(){
        return lastToEndSureDueNonCdqAllTimeD7;
    }

    /**
    * 设置近7天最近一次非超短期现金贷逾期距今天数(确定)
    * 
    * @param lastToEndSureDueNonCdqAllTimeD7 要设置的近7天最近一次非超短期现金贷逾期距今天数(确定)
    */
    public void setLastToEndSureDueNonCdqAllTimeD7(String lastToEndSureDueNonCdqAllTimeD7){
        this.lastToEndSureDueNonCdqAllTimeD7 = lastToEndSureDueNonCdqAllTimeD7;
    }

    /**
    * 获取最近15日借贷逾期平均天数(确定)
    *
    * @return 最近15日借贷逾期平均天数(确定)
    */
    public String getJiedaiAvgDefaultdaysD15(){
        return jiedaiAvgDefaultdaysD15;
    }

    /**
    * 设置最近15日借贷逾期平均天数(确定)
    * 
    * @param jiedaiAvgDefaultdaysD15 要设置的最近15日借贷逾期平均天数(确定)
    */
    public void setJiedaiAvgDefaultdaysD15(String jiedaiAvgDefaultdaysD15){
        this.jiedaiAvgDefaultdaysD15 = jiedaiAvgDefaultdaysD15;
    }

    /**
    * 获取最近15日借贷逾期最小天数(确定)
    *
    * @return 最近15日借贷逾期最小天数(确定)
    */
    public String getJiedaiMinDefaultdaysD15(){
        return jiedaiMinDefaultdaysD15;
    }

    /**
    * 设置最近15日借贷逾期最小天数(确定)
    * 
    * @param jiedaiMinDefaultdaysD15 要设置的最近15日借贷逾期最小天数(确定)
    */
    public void setJiedaiMinDefaultdaysD15(String jiedaiMinDefaultdaysD15){
        this.jiedaiMinDefaultdaysD15 = jiedaiMinDefaultdaysD15;
    }

    /**
    * 获取最近15日借贷逾期还款笔数
    *
    * @return 最近15日借贷逾期还款笔数
    */
    public String getJiedai4SumFailCntD15(){
        return jiedai4SumFailCntD15;
    }

    /**
    * 设置最近15日借贷逾期还款笔数
    * 
    * @param jiedai4SumFailCntD15 要设置的最近15日借贷逾期还款笔数
    */
    public void setJiedai4SumFailCntD15(String jiedai4SumFailCntD15){
        this.jiedai4SumFailCntD15 = jiedai4SumFailCntD15;
    }

    /**
    * 获取最近15日借贷逾期借贷平均天数(疑似)
    *
    * @return 最近15日借贷逾期借贷平均天数(疑似)
    */
    public String getDdJiedaiAvgFailDays1D15(){
        return ddJiedaiAvgFailDays1D15;
    }

    /**
    * 设置最近15日借贷逾期借贷平均天数(疑似)
    * 
    * @param ddJiedaiAvgFailDays1D15 要设置的最近15日借贷逾期借贷平均天数(疑似)
    */
    public void setDdJiedaiAvgFailDays1D15(String ddJiedaiAvgFailDays1D15){
        this.ddJiedaiAvgFailDays1D15 = ddJiedaiAvgFailDays1D15;
    }

    /**
    * 获取最近15日借贷逾期还款大于3日笔数
    *
    * @return 最近15日借贷逾期还款大于3日笔数
    */
    public String getJiedai4CountFillD3CntD15(){
        return jiedai4CountFillD3CntD15;
    }

    /**
    * 设置最近15日借贷逾期还款大于3日笔数
    * 
    * @param jiedai4CountFillD3CntD15 要设置的最近15日借贷逾期还款大于3日笔数
    */
    public void setJiedai4CountFillD3CntD15(String jiedai4CountFillD3CntD15){
        this.jiedai4CountFillD3CntD15 = jiedai4CountFillD3CntD15;
    }

    /**
    * 获取最近15日借贷逾期还款大于5日笔数
    *
    * @return 最近15日借贷逾期还款大于5日笔数
    */
    public String getJiedai4CountFillD5CntD15(){
        return jiedai4CountFillD5CntD15;
    }

    /**
    * 设置最近15日借贷逾期还款大于5日笔数
    * 
    * @param jiedai4CountFillD5CntD15 要设置的最近15日借贷逾期还款大于5日笔数
    */
    public void setJiedai4CountFillD5CntD15(String jiedai4CountFillD5CntD15){
        this.jiedai4CountFillD5CntD15 = jiedai4CountFillD5CntD15;
    }

    /**
    * 获取最近15日借贷逾期借贷新增借贷平台数
    *
    * @return 最近15日借贷逾期借贷新增借贷平台数
    */
    public String getDdJiedaiCountFailMamberaddD15(){
        return ddJiedaiCountFailMamberaddD15;
    }

    /**
    * 设置最近15日借贷逾期借贷新增借贷平台数
    * 
    * @param ddJiedaiCountFailMamberaddD15 要设置的最近15日借贷逾期借贷新增借贷平台数
    */
    public void setDdJiedaiCountFailMamberaddD15(String ddJiedaiCountFailMamberaddD15){
        this.ddJiedaiCountFailMamberaddD15 = ddJiedaiCountFailMamberaddD15;
    }

    /**
    * 获取最近15日逾期借贷总金额
    *
    * @return 最近15日逾期借贷总金额
    */
    public String getJiedaiSumFailAmtD15(){
        return jiedaiSumFailAmtD15;
    }

    /**
    * 设置最近15日逾期借贷总金额
    * 
    * @param jiedaiSumFailAmtD15 要设置的最近15日逾期借贷总金额
    */
    public void setJiedaiSumFailAmtD15(String jiedaiSumFailAmtD15){
        this.jiedaiSumFailAmtD15 = jiedaiSumFailAmtD15;
    }

    /**
    * 获取最近15日借贷逾期借贷最大天数(疑似)
    *
    * @return 最近15日借贷逾期借贷最大天数(疑似)
    */
    public String getDdJiedaiMaxFailDays1D15(){
        return ddJiedaiMaxFailDays1D15;
    }

    /**
    * 设置最近15日借贷逾期借贷最大天数(疑似)
    * 
    * @param ddJiedaiMaxFailDays1D15 要设置的最近15日借贷逾期借贷最大天数(疑似)
    */
    public void setDdJiedaiMaxFailDays1D15(String ddJiedaiMaxFailDays1D15){
        this.ddJiedaiMaxFailDays1D15 = ddJiedaiMaxFailDays1D15;
    }

    /**
    * 获取近15天累计逾期天数(确定)
    *
    * @return 近15天累计逾期天数(确定)
    */
    public String getSumSureDueDaysAllProAllTimeD15(){
        return sumSureDueDaysAllProAllTimeD15;
    }

    /**
    * 设置近15天累计逾期天数(确定)
    * 
    * @param sumSureDueDaysAllProAllTimeD15 要设置的近15天累计逾期天数(确定)
    */
    public void setSumSureDueDaysAllProAllTimeD15(String sumSureDueDaysAllProAllTimeD15){
        this.sumSureDueDaysAllProAllTimeD15 = sumSureDueDaysAllProAllTimeD15;
    }

    /**
    * 获取近15天最近一次逾期距今天数(确定)
    *
    * @return 近15天最近一次逾期距今天数(确定)
    */
    public String getLastToEndSureDueAllProAllTimeD15(){
        return lastToEndSureDueAllProAllTimeD15;
    }

    /**
    * 设置近15天最近一次逾期距今天数(确定)
    * 
    * @param lastToEndSureDueAllProAllTimeD15 要设置的近15天最近一次逾期距今天数(确定)
    */
    public void setLastToEndSureDueAllProAllTimeD15(String lastToEndSureDueAllProAllTimeD15){
        this.lastToEndSureDueAllProAllTimeD15 = lastToEndSureDueAllProAllTimeD15;
    }

    /**
    * 获取近15天非超短期现金贷最大逾期天数(确定)
    *
    * @return 近15天非超短期现金贷最大逾期天数(确定)
    */
    public String getMaxSureDueDaysNonCdqAllTimeD15(){
        return maxSureDueDaysNonCdqAllTimeD15;
    }

    /**
    * 设置近15天非超短期现金贷最大逾期天数(确定)
    * 
    * @param maxSureDueDaysNonCdqAllTimeD15 要设置的近15天非超短期现金贷最大逾期天数(确定)
    */
    public void setMaxSureDueDaysNonCdqAllTimeD15(String maxSureDueDaysNonCdqAllTimeD15){
        this.maxSureDueDaysNonCdqAllTimeD15 = maxSureDueDaysNonCdqAllTimeD15;
    }

    /**
    * 获取近15天最近一次非超短期现金贷逾期距今天数(确定)
    *
    * @return 近15天最近一次非超短期现金贷逾期距今天数(确定)
    */
    public String getLastToEndSureDueNonCdqAllTimeD15(){
        return lastToEndSureDueNonCdqAllTimeD15;
    }

    /**
    * 设置近15天最近一次非超短期现金贷逾期距今天数(确定)
    * 
    * @param lastToEndSureDueNonCdqAllTimeD15 要设置的近15天最近一次非超短期现金贷逾期距今天数(确定)
    */
    public void setLastToEndSureDueNonCdqAllTimeD15(String lastToEndSureDueNonCdqAllTimeD15){
        this.lastToEndSureDueNonCdqAllTimeD15 = lastToEndSureDueNonCdqAllTimeD15;
    }

    /**
    * 获取最近1月借贷逾期平均天数(确定)
    *
    * @return 最近1月借贷逾期平均天数(确定)
    */
    public String getJiedaiAvgDefaultdaysM1(){
        return jiedaiAvgDefaultdaysM1;
    }

    /**
    * 设置最近1月借贷逾期平均天数(确定)
    * 
    * @param jiedaiAvgDefaultdaysM1 要设置的最近1月借贷逾期平均天数(确定)
    */
    public void setJiedaiAvgDefaultdaysM1(String jiedaiAvgDefaultdaysM1){
        this.jiedaiAvgDefaultdaysM1 = jiedaiAvgDefaultdaysM1;
    }

    /**
    * 获取最近1月借贷逾期借贷最大天数(疑似)
    *
    * @return 最近1月借贷逾期借贷最大天数(疑似)
    */
    public String getDdJiedaiMaxFailDays1M1(){
        return ddJiedaiMaxFailDays1M1;
    }

    /**
    * 设置最近1月借贷逾期借贷最大天数(疑似)
    * 
    * @param ddJiedaiMaxFailDays1M1 要设置的最近1月借贷逾期借贷最大天数(疑似)
    */
    public void setDdJiedaiMaxFailDays1M1(String ddJiedaiMaxFailDays1M1){
        this.ddJiedaiMaxFailDays1M1 = ddJiedaiMaxFailDays1M1;
    }

    /**
    * 获取最近1月借贷逾期还款笔数
    *
    * @return 最近1月借贷逾期还款笔数
    */
    public String getJiedai4SumFailCnt1(){
        return jiedai4SumFailCnt1;
    }

    /**
    * 设置最近1月借贷逾期还款笔数
    * 
    * @param jiedai4SumFailCnt1 要设置的最近1月借贷逾期还款笔数
    */
    public void setJiedai4SumFailCnt1(String jiedai4SumFailCnt1){
        this.jiedai4SumFailCnt1 = jiedai4SumFailCnt1;
    }

    /**
    * 获取最近1月借贷逾期借贷最大天数(确定)
    *
    * @return 最近1月借贷逾期借贷最大天数(确定)
    */
    public String getDdJiedaiMaxFailDaysM1(){
        return ddJiedaiMaxFailDaysM1;
    }

    /**
    * 设置最近1月借贷逾期借贷最大天数(确定)
    * 
    * @param ddJiedaiMaxFailDaysM1 要设置的最近1月借贷逾期借贷最大天数(确定)
    */
    public void setDdJiedaiMaxFailDaysM1(String ddJiedaiMaxFailDaysM1){
        this.ddJiedaiMaxFailDaysM1 = ddJiedaiMaxFailDaysM1;
    }

    /**
    * 获取最近1月借贷逾期借贷新增借贷平台数
    *
    * @return 最近1月借贷逾期借贷新增借贷平台数
    */
    public String getDdJiedaiCountFailMamberaddM1(){
        return ddJiedaiCountFailMamberaddM1;
    }

    /**
    * 设置最近1月借贷逾期借贷新增借贷平台数
    * 
    * @param ddJiedaiCountFailMamberaddM1 要设置的最近1月借贷逾期借贷新增借贷平台数
    */
    public void setDdJiedaiCountFailMamberaddM1(String ddJiedaiCountFailMamberaddM1){
        this.ddJiedaiCountFailMamberaddM1 = ddJiedaiCountFailMamberaddM1;
    }

    /**
    * 获取最近1月借贷逾期还款大于3日笔数
    *
    * @return 最近1月借贷逾期还款大于3日笔数
    */
    public String getJiedai4CountFillD3CntM1(){
        return jiedai4CountFillD3CntM1;
    }

    /**
    * 设置最近1月借贷逾期还款大于3日笔数
    * 
    * @param jiedai4CountFillD3CntM1 要设置的最近1月借贷逾期还款大于3日笔数
    */
    public void setJiedai4CountFillD3CntM1(String jiedai4CountFillD3CntM1){
        this.jiedai4CountFillD3CntM1 = jiedai4CountFillD3CntM1;
    }

    /**
    * 获取最近1月借贷逾期借贷最小天数(疑似)
    *
    * @return 最近1月借贷逾期借贷最小天数(疑似)
    */
    public String getDdJiedaiMinFailDays1M1(){
        return ddJiedaiMinFailDays1M1;
    }

    /**
    * 设置最近1月借贷逾期借贷最小天数(疑似)
    * 
    * @param ddJiedaiMinFailDays1M1 要设置的最近1月借贷逾期借贷最小天数(疑似)
    */
    public void setDdJiedaiMinFailDays1M1(String ddJiedaiMinFailDays1M1){
        this.ddJiedaiMinFailDays1M1 = ddJiedaiMinFailDays1M1;
    }

    /**
    * 获取最近1月逾期借贷总金额
    *
    * @return 最近1月逾期借贷总金额
    */
    public String getJiedaiSumFailAmt1(){
        return jiedaiSumFailAmt1;
    }

    /**
    * 设置最近1月逾期借贷总金额
    * 
    * @param jiedaiSumFailAmt1 要设置的最近1月逾期借贷总金额
    */
    public void setJiedaiSumFailAmt1(String jiedaiSumFailAmt1){
        this.jiedaiSumFailAmt1 = jiedaiSumFailAmt1;
    }

    /**
    * 获取最近1月超短期借贷逾期借贷最大天数(疑似)
    *
    * @return 最近1月超短期借贷逾期借贷最大天数(疑似)
    */
    public String getCdqDdJiedaiMaxFailDays1M1(){
        return cdqDdJiedaiMaxFailDays1M1;
    }

    /**
    * 设置最近1月超短期借贷逾期借贷最大天数(疑似)
    * 
    * @param cdqDdJiedaiMaxFailDays1M1 要设置的最近1月超短期借贷逾期借贷最大天数(疑似)
    */
    public void setCdqDdJiedaiMaxFailDays1M1(String cdqDdJiedaiMaxFailDays1M1){
        this.cdqDdJiedaiMaxFailDays1M1 = cdqDdJiedaiMaxFailDays1M1;
    }

    /**
    * 获取最近1月借贷逾期还款大于5日笔数
    *
    * @return 最近1月借贷逾期还款大于5日笔数
    */
    public String getJiedai4CountFillD5CntM1(){
        return jiedai4CountFillD5CntM1;
    }

    /**
    * 设置最近1月借贷逾期还款大于5日笔数
    * 
    * @param jiedai4CountFillD5CntM1 要设置的最近1月借贷逾期还款大于5日笔数
    */
    public void setJiedai4CountFillD5CntM1(String jiedai4CountFillD5CntM1){
        this.jiedai4CountFillD5CntM1 = jiedai4CountFillD5CntM1;
    }

    /**
    * 获取最近1月借贷未逾期还款金额平均值
    *
    * @return 最近1月借贷未逾期还款金额平均值
    */
    public String getJiedai4AvgSuccAmt1(){
        return jiedai4AvgSuccAmt1;
    }

    /**
    * 设置最近1月借贷未逾期还款金额平均值
    * 
    * @param jiedai4AvgSuccAmt1 要设置的最近1月借贷未逾期还款金额平均值
    */
    public void setJiedai4AvgSuccAmt1(String jiedai4AvgSuccAmt1){
        this.jiedai4AvgSuccAmt1 = jiedai4AvgSuccAmt1;
    }

    /**
    * 获取近1个月非超短期现金贷累计逾期天数(确定)
    *
    * @return 近1个月非超短期现金贷累计逾期天数(确定)
    */
    public String getSumSureDueDaysNonCdqAllTimeM1(){
        return sumSureDueDaysNonCdqAllTimeM1;
    }

    /**
    * 设置近1个月非超短期现金贷累计逾期天数(确定)
    * 
    * @param sumSureDueDaysNonCdqAllTimeM1 要设置的近1个月非超短期现金贷累计逾期天数(确定)
    */
    public void setSumSureDueDaysNonCdqAllTimeM1(String sumSureDueDaysNonCdqAllTimeM1){
        this.sumSureDueDaysNonCdqAllTimeM1 = sumSureDueDaysNonCdqAllTimeM1;
    }

    /**
    * 获取近1个月累计逾期天数(确定)
    *
    * @return 近1个月累计逾期天数(确定)
    */
    public String getSumSureDueDaysAllProAllTimeM1(){
        return sumSureDueDaysAllProAllTimeM1;
    }

    /**
    * 设置近1个月累计逾期天数(确定)
    * 
    * @param sumSureDueDaysAllProAllTimeM1 要设置的近1个月累计逾期天数(确定)
    */
    public void setSumSureDueDaysAllProAllTimeM1(String sumSureDueDaysAllProAllTimeM1){
        this.sumSureDueDaysAllProAllTimeM1 = sumSureDueDaysAllProAllTimeM1;
    }

    /**
    * 获取近1个月非超短期现金贷平均逾期天数(确定)
    *
    * @return 近1个月非超短期现金贷平均逾期天数(确定)
    */
    public String getAvgSureDueDaysNonCdqAllTimeM1(){
        return avgSureDueDaysNonCdqAllTimeM1;
    }

    /**
    * 设置近1个月非超短期现金贷平均逾期天数(确定)
    * 
    * @param avgSureDueDaysNonCdqAllTimeM1 要设置的近1个月非超短期现金贷平均逾期天数(确定)
    */
    public void setAvgSureDueDaysNonCdqAllTimeM1(String avgSureDueDaysNonCdqAllTimeM1){
        this.avgSureDueDaysNonCdqAllTimeM1 = avgSureDueDaysNonCdqAllTimeM1;
    }

    /**
    * 获取近1个月超短期现金贷还款金额占比
    *
    * @return 近1个月超短期现金贷还款金额占比
    */
    public String getPctPayAmtCdqProAllTimeM1(){
        return pctPayAmtCdqProAllTimeM1;
    }

    /**
    * 设置近1个月超短期现金贷还款金额占比
    * 
    * @param pctPayAmtCdqProAllTimeM1 要设置的近1个月超短期现金贷还款金额占比
    */
    public void setPctPayAmtCdqProAllTimeM1(String pctPayAmtCdqProAllTimeM1){
        this.pctPayAmtCdqProAllTimeM1 = pctPayAmtCdqProAllTimeM1;
    }

    /**
    * 获取近1个月最大还款金额
    *
    * @return 近1个月最大还款金额
    */
    public String getMaxPayAmtAllProAllTimeM1(){
        return maxPayAmtAllProAllTimeM1;
    }

    /**
    * 设置近1个月最大还款金额
    * 
    * @param maxPayAmtAllProAllTimeM1 要设置的近1个月最大还款金额
    */
    public void setMaxPayAmtAllProAllTimeM1(String maxPayAmtAllProAllTimeM1){
        this.maxPayAmtAllProAllTimeM1 = maxPayAmtAllProAllTimeM1;
    }

    /**
    * 获取最近3月借贷逾期平均天数(确定)
    *
    * @return 最近3月借贷逾期平均天数(确定)
    */
    public String getJiedaiAvgDefaultdaysM3(){
        return jiedaiAvgDefaultdaysM3;
    }

    /**
    * 设置最近3月借贷逾期平均天数(确定)
    * 
    * @param jiedaiAvgDefaultdaysM3 要设置的最近3月借贷逾期平均天数(确定)
    */
    public void setJiedaiAvgDefaultdaysM3(String jiedaiAvgDefaultdaysM3){
        this.jiedaiAvgDefaultdaysM3 = jiedaiAvgDefaultdaysM3;
    }

    /**
    * 获取最近3月借贷逾期借贷最大天数(疑似)
    *
    * @return 最近3月借贷逾期借贷最大天数(疑似)
    */
    public String getDdJiedaiMaxFailDays1M3(){
        return ddJiedaiMaxFailDays1M3;
    }

    /**
    * 设置最近3月借贷逾期借贷最大天数(疑似)
    * 
    * @param ddJiedaiMaxFailDays1M3 要设置的最近3月借贷逾期借贷最大天数(疑似)
    */
    public void setDdJiedaiMaxFailDays1M3(String ddJiedaiMaxFailDays1M3){
        this.ddJiedaiMaxFailDays1M3 = ddJiedaiMaxFailDays1M3;
    }

    /**
    * 获取最近3月借贷逾期借贷平均天数(确定)
    *
    * @return 最近3月借贷逾期借贷平均天数(确定)
    */
    public String getDdJiedaiAvgFailDaysM3(){
        return ddJiedaiAvgFailDaysM3;
    }

    /**
    * 设置最近3月借贷逾期借贷平均天数(确定)
    * 
    * @param ddJiedaiAvgFailDaysM3 要设置的最近3月借贷逾期借贷平均天数(确定)
    */
    public void setDdJiedaiAvgFailDaysM3(String ddJiedaiAvgFailDaysM3){
        this.ddJiedaiAvgFailDaysM3 = ddJiedaiAvgFailDaysM3;
    }

    /**
    * 获取最近3月借贷逾期借贷新增借贷平台数
    *
    * @return 最近3月借贷逾期借贷新增借贷平台数
    */
    public String getDdJiedaiCountFailMamberaddM3(){
        return ddJiedaiCountFailMamberaddM3;
    }

    /**
    * 设置最近3月借贷逾期借贷新增借贷平台数
    * 
    * @param ddJiedaiCountFailMamberaddM3 要设置的最近3月借贷逾期借贷新增借贷平台数
    */
    public void setDdJiedaiCountFailMamberaddM3(String ddJiedaiCountFailMamberaddM3){
        this.ddJiedaiCountFailMamberaddM3 = ddJiedaiCountFailMamberaddM3;
    }

    /**
    * 获取最近3月借贷逾期还款大于3日笔数
    *
    * @return 最近3月借贷逾期还款大于3日笔数
    */
    public String getJiedai4CountFillD3CntM3(){
        return jiedai4CountFillD3CntM3;
    }

    /**
    * 设置最近3月借贷逾期还款大于3日笔数
    * 
    * @param jiedai4CountFillD3CntM3 要设置的最近3月借贷逾期还款大于3日笔数
    */
    public void setJiedai4CountFillD3CntM3(String jiedai4CountFillD3CntM3){
        this.jiedai4CountFillD3CntM3 = jiedai4CountFillD3CntM3;
    }

    /**
    * 获取最近3月借贷逾期还款大于5日笔数
    *
    * @return 最近3月借贷逾期还款大于5日笔数
    */
    public String getJiedai4CountFillD5CntM3(){
        return jiedai4CountFillD5CntM3;
    }

    /**
    * 设置最近3月借贷逾期还款大于5日笔数
    * 
    * @param jiedai4CountFillD5CntM3 要设置的最近3月借贷逾期还款大于5日笔数
    */
    public void setJiedai4CountFillD5CntM3(String jiedai4CountFillD5CntM3){
        this.jiedai4CountFillD5CntM3 = jiedai4CountFillD5CntM3;
    }

    /**
    * 获取最近3月超短期借贷逾期借贷平均天数(疑似)
    *
    * @return 最近3月超短期借贷逾期借贷平均天数(疑似)
    */
    public String getCdqDdJiedaiAvgFailDays1M3(){
        return cdqDdJiedaiAvgFailDays1M3;
    }

    /**
    * 设置最近3月超短期借贷逾期借贷平均天数(疑似)
    * 
    * @param cdqDdJiedaiAvgFailDays1M3 要设置的最近3月超短期借贷逾期借贷平均天数(疑似)
    */
    public void setCdqDdJiedaiAvgFailDays1M3(String cdqDdJiedaiAvgFailDays1M3){
        this.cdqDdJiedaiAvgFailDays1M3 = cdqDdJiedaiAvgFailDays1M3;
    }

    /**
    * 获取最近3月借贷未逾期还款金额平均值
    *
    * @return 最近3月借贷未逾期还款金额平均值
    */
    public String getJiedai4AvgSuccAmt3(){
        return jiedai4AvgSuccAmt3;
    }

    /**
    * 设置最近3月借贷未逾期还款金额平均值
    * 
    * @param jiedai4AvgSuccAmt3 要设置的最近3月借贷未逾期还款金额平均值
    */
    public void setJiedai4AvgSuccAmt3(String jiedai4AvgSuccAmt3){
        this.jiedai4AvgSuccAmt3 = jiedai4AvgSuccAmt3;
    }

    /**
    * 获取最近3月逾期借贷总金额
    *
    * @return 最近3月逾期借贷总金额
    */
    public String getJiedaiSumFailAmt3(){
        return jiedaiSumFailAmt3;
    }

    /**
    * 设置最近3月逾期借贷总金额
    * 
    * @param jiedaiSumFailAmt3 要设置的最近3月逾期借贷总金额
    */
    public void setJiedaiSumFailAmt3(String jiedaiSumFailAmt3){
        this.jiedaiSumFailAmt3 = jiedaiSumFailAmt3;
    }

    /**
    * 获取最近3月借贷逾期借贷最小天数(疑似)
    *
    * @return 最近3月借贷逾期借贷最小天数(疑似)
    */
    public String getDdJiedaiMinFailDays1M3(){
        return ddJiedaiMinFailDays1M3;
    }

    /**
    * 设置最近3月借贷逾期借贷最小天数(疑似)
    * 
    * @param ddJiedaiMinFailDays1M3 要设置的最近3月借贷逾期借贷最小天数(疑似)
    */
    public void setDdJiedaiMinFailDays1M3(String ddJiedaiMinFailDays1M3){
        this.ddJiedaiMinFailDays1M3 = ddJiedaiMinFailDays1M3;
    }

    /**
    * 获取近3个月累计逾期天数(确定)
    *
    * @return 近3个月累计逾期天数(确定)
    */
    public String getSumSureDueDaysAllProAllTimeM3(){
        return sumSureDueDaysAllProAllTimeM3;
    }

    /**
    * 设置近3个月累计逾期天数(确定)
    * 
    * @param sumSureDueDaysAllProAllTimeM3 要设置的近3个月累计逾期天数(确定)
    */
    public void setSumSureDueDaysAllProAllTimeM3(String sumSureDueDaysAllProAllTimeM3){
        this.sumSureDueDaysAllProAllTimeM3 = sumSureDueDaysAllProAllTimeM3;
    }

    /**
    * 获取近3个月非超短期现金贷累计逾期天数(确定)
    *
    * @return 近3个月非超短期现金贷累计逾期天数(确定)
    */
    public String getSumSureDueDaysNonCdqAllTimeM3(){
        return sumSureDueDaysNonCdqAllTimeM3;
    }

    /**
    * 设置近3个月非超短期现金贷累计逾期天数(确定)
    * 
    * @param sumSureDueDaysNonCdqAllTimeM3 要设置的近3个月非超短期现金贷累计逾期天数(确定)
    */
    public void setSumSureDueDaysNonCdqAllTimeM3(String sumSureDueDaysNonCdqAllTimeM3){
        this.sumSureDueDaysNonCdqAllTimeM3 = sumSureDueDaysNonCdqAllTimeM3;
    }

    /**
    * 获取近3个月平均逾期天数(确定)
    *
    * @return 近3个月平均逾期天数(确定)
    */
    public String getAvgSureDueDaysAllProAllTimeM3(){
        return avgSureDueDaysAllProAllTimeM3;
    }

    /**
    * 设置近3个月平均逾期天数(确定)
    * 
    * @param avgSureDueDaysAllProAllTimeM3 要设置的近3个月平均逾期天数(确定)
    */
    public void setAvgSureDueDaysAllProAllTimeM3(String avgSureDueDaysAllProAllTimeM3){
        this.avgSureDueDaysAllProAllTimeM3 = avgSureDueDaysAllProAllTimeM3;
    }

    /**
    * 获取近3个月非超短期现金贷最大逾期次数
    *
    * @return 近3个月非超短期现金贷最大逾期次数
    */
    public String getMaxDueCntNonCdqAllTimeM3(){
        return maxDueCntNonCdqAllTimeM3;
    }

    /**
    * 设置近3个月非超短期现金贷最大逾期次数
    * 
    * @param maxDueCntNonCdqAllTimeM3 要设置的近3个月非超短期现金贷最大逾期次数
    */
    public void setMaxDueCntNonCdqAllTimeM3(String maxDueCntNonCdqAllTimeM3){
        this.maxDueCntNonCdqAllTimeM3 = maxDueCntNonCdqAllTimeM3;
    }

    /**
    * 获取近3个月非超短期现金贷平均逾期天数(确定)
    *
    * @return 近3个月非超短期现金贷平均逾期天数(确定)
    */
    public String getAvgSureDueDaysNonCdqAllTimeM3(){
        return avgSureDueDaysNonCdqAllTimeM3;
    }

    /**
    * 设置近3个月非超短期现金贷平均逾期天数(确定)
    * 
    * @param avgSureDueDaysNonCdqAllTimeM3 要设置的近3个月非超短期现金贷平均逾期天数(确定)
    */
    public void setAvgSureDueDaysNonCdqAllTimeM3(String avgSureDueDaysNonCdqAllTimeM3){
        this.avgSureDueDaysNonCdqAllTimeM3 = avgSureDueDaysNonCdqAllTimeM3;
    }

    /**
    * 获取近3个月超短期现金贷还款金额占比
    *
    * @return 近3个月超短期现金贷还款金额占比
    */
    public String getPctPayAmtCdqProAllTimeM3(){
        return pctPayAmtCdqProAllTimeM3;
    }

    /**
    * 设置近3个月超短期现金贷还款金额占比
    * 
    * @param pctPayAmtCdqProAllTimeM3 要设置的近3个月超短期现金贷还款金额占比
    */
    public void setPctPayAmtCdqProAllTimeM3(String pctPayAmtCdqProAllTimeM3){
        this.pctPayAmtCdqProAllTimeM3 = pctPayAmtCdqProAllTimeM3;
    }

    /**
    * 获取最近6月借贷逾期平均天数(确定)
    *
    * @return 最近6月借贷逾期平均天数(确定)
    */
    public String getJiedaiAvgDefaultdaysM6(){
        return jiedaiAvgDefaultdaysM6;
    }

    /**
    * 设置最近6月借贷逾期平均天数(确定)
    * 
    * @param jiedaiAvgDefaultdaysM6 要设置的最近6月借贷逾期平均天数(确定)
    */
    public void setJiedaiAvgDefaultdaysM6(String jiedaiAvgDefaultdaysM6){
        this.jiedaiAvgDefaultdaysM6 = jiedaiAvgDefaultdaysM6;
    }

    /**
    * 获取最近6月借贷逾期借贷平均天数(疑似)
    *
    * @return 最近6月借贷逾期借贷平均天数(疑似)
    */
    public String getDdJiedaiAvgFailDays1M6(){
        return ddJiedaiAvgFailDays1M6;
    }

    /**
    * 设置最近6月借贷逾期借贷平均天数(疑似)
    * 
    * @param ddJiedaiAvgFailDays1M6 要设置的最近6月借贷逾期借贷平均天数(疑似)
    */
    public void setDdJiedaiAvgFailDays1M6(String ddJiedaiAvgFailDays1M6){
        this.ddJiedaiAvgFailDays1M6 = ddJiedaiAvgFailDays1M6;
    }

    /**
    * 获取最近6月借贷逾期借贷平均天数(确定)
    *
    * @return 最近6月借贷逾期借贷平均天数(确定)
    */
    public String getDdJiedaiAvgFailDaysM6(){
        return ddJiedaiAvgFailDaysM6;
    }

    /**
    * 设置最近6月借贷逾期借贷平均天数(确定)
    * 
    * @param ddJiedaiAvgFailDaysM6 要设置的最近6月借贷逾期借贷平均天数(确定)
    */
    public void setDdJiedaiAvgFailDaysM6(String ddJiedaiAvgFailDaysM6){
        this.ddJiedaiAvgFailDaysM6 = ddJiedaiAvgFailDaysM6;
    }

    /**
    * 获取最近6月借贷逾期借贷新增借贷平台数
    *
    * @return 最近6月借贷逾期借贷新增借贷平台数
    */
    public String getDdJiedaiCountFailMamberaddM6(){
        return ddJiedaiCountFailMamberaddM6;
    }

    /**
    * 设置最近6月借贷逾期借贷新增借贷平台数
    * 
    * @param ddJiedaiCountFailMamberaddM6 要设置的最近6月借贷逾期借贷新增借贷平台数
    */
    public void setDdJiedaiCountFailMamberaddM6(String ddJiedaiCountFailMamberaddM6){
        this.ddJiedaiCountFailMamberaddM6 = ddJiedaiCountFailMamberaddM6;
    }

    /**
    * 获取最近6月借贷逾期还款大于3日笔数
    *
    * @return 最近6月借贷逾期还款大于3日笔数
    */
    public String getJiedai4CountFillD3CntM6(){
        return jiedai4CountFillD3CntM6;
    }

    /**
    * 设置最近6月借贷逾期还款大于3日笔数
    * 
    * @param jiedai4CountFillD3CntM6 要设置的最近6月借贷逾期还款大于3日笔数
    */
    public void setJiedai4CountFillD3CntM6(String jiedai4CountFillD3CntM6){
        this.jiedai4CountFillD3CntM6 = jiedai4CountFillD3CntM6;
    }

    /**
    * 获取最近6月超短期借贷逾期借贷平均天数(疑似)
    *
    * @return 最近6月超短期借贷逾期借贷平均天数(疑似)
    */
    public String getCdqDdJiedaiAvgFailDays1M6(){
        return cdqDdJiedaiAvgFailDays1M6;
    }

    /**
    * 设置最近6月超短期借贷逾期借贷平均天数(疑似)
    * 
    * @param cdqDdJiedaiAvgFailDays1M6 要设置的最近6月超短期借贷逾期借贷平均天数(疑似)
    */
    public void setCdqDdJiedaiAvgFailDays1M6(String cdqDdJiedaiAvgFailDays1M6){
        this.cdqDdJiedaiAvgFailDays1M6 = cdqDdJiedaiAvgFailDays1M6;
    }

    /**
    * 获取最近6月超短期借贷逾期借贷最大天数(疑似)
    *
    * @return 最近6月超短期借贷逾期借贷最大天数(疑似)
    */
    public String getCdqDdJiedaiMaxFailDays1M6(){
        return cdqDdJiedaiMaxFailDays1M6;
    }

    /**
    * 设置最近6月超短期借贷逾期借贷最大天数(疑似)
    * 
    * @param cdqDdJiedaiMaxFailDays1M6 要设置的最近6月超短期借贷逾期借贷最大天数(疑似)
    */
    public void setCdqDdJiedaiMaxFailDays1M6(String cdqDdJiedaiMaxFailDays1M6){
        this.cdqDdJiedaiMaxFailDays1M6 = cdqDdJiedaiMaxFailDays1M6;
    }

    /**
    * 获取最近6月借贷未逾期还款金额平均值
    *
    * @return 最近6月借贷未逾期还款金额平均值
    */
    public String getJiedai4AvgSuccAmt6(){
        return jiedai4AvgSuccAmt6;
    }

    /**
    * 设置最近6月借贷未逾期还款金额平均值
    * 
    * @param jiedai4AvgSuccAmt6 要设置的最近6月借贷未逾期还款金额平均值
    */
    public void setJiedai4AvgSuccAmt6(String jiedai4AvgSuccAmt6){
        this.jiedai4AvgSuccAmt6 = jiedai4AvgSuccAmt6;
    }

    /**
    * 获取最近6月借贷逾期还款大于5日笔数
    *
    * @return 最近6月借贷逾期还款大于5日笔数
    */
    public String getJiedai4CountFillD5CntM6(){
        return jiedai4CountFillD5CntM6;
    }

    /**
    * 设置最近6月借贷逾期还款大于5日笔数
    * 
    * @param jiedai4CountFillD5CntM6 要设置的最近6月借贷逾期还款大于5日笔数
    */
    public void setJiedai4CountFillD5CntM6(String jiedai4CountFillD5CntM6){
        this.jiedai4CountFillD5CntM6 = jiedai4CountFillD5CntM6;
    }

    /**
    * 获取近6个月非超短期现金贷累计逾期天数(确定)
    *
    * @return 近6个月非超短期现金贷累计逾期天数(确定)
    */
    public String getSumSureDueDaysNonCdqAllTimeM6(){
        return sumSureDueDaysNonCdqAllTimeM6;
    }

    /**
    * 设置近6个月非超短期现金贷累计逾期天数(确定)
    * 
    * @param sumSureDueDaysNonCdqAllTimeM6 要设置的近6个月非超短期现金贷累计逾期天数(确定)
    */
    public void setSumSureDueDaysNonCdqAllTimeM6(String sumSureDueDaysNonCdqAllTimeM6){
        this.sumSureDueDaysNonCdqAllTimeM6 = sumSureDueDaysNonCdqAllTimeM6;
    }

    /**
    * 获取近6个月最大逾期天数(确定)
    *
    * @return 近6个月最大逾期天数(确定)
    */
    public String getMaxSureDueDaysAllProAllTimeM6(){
        return maxSureDueDaysAllProAllTimeM6;
    }

    /**
    * 设置近6个月最大逾期天数(确定)
    * 
    * @param maxSureDueDaysAllProAllTimeM6 要设置的近6个月最大逾期天数(确定)
    */
    public void setMaxSureDueDaysAllProAllTimeM6(String maxSureDueDaysAllProAllTimeM6){
        this.maxSureDueDaysAllProAllTimeM6 = maxSureDueDaysAllProAllTimeM6;
    }

    /**
    * 获取近6个月平均逾期天数(确定)
    *
    * @return 近6个月平均逾期天数(确定)
    */
    public String getAvgSureDueDaysAllProAllTimeM6(){
        return avgSureDueDaysAllProAllTimeM6;
    }

    /**
    * 设置近6个月平均逾期天数(确定)
    * 
    * @param avgSureDueDaysAllProAllTimeM6 要设置的近6个月平均逾期天数(确定)
    */
    public void setAvgSureDueDaysAllProAllTimeM6(String avgSureDueDaysAllProAllTimeM6){
        this.avgSureDueDaysAllProAllTimeM6 = avgSureDueDaysAllProAllTimeM6;
    }

    /**
    * 获取近6个月非超短期现金贷平均逾期天数(确定)
    *
    * @return 近6个月非超短期现金贷平均逾期天数(确定)
    */
    public String getAvgSureDueDaysNonCdqAllTimeM6(){
        return avgSureDueDaysNonCdqAllTimeM6;
    }

    /**
    * 设置近6个月非超短期现金贷平均逾期天数(确定)
    * 
    * @param avgSureDueDaysNonCdqAllTimeM6 要设置的近6个月非超短期现金贷平均逾期天数(确定)
    */
    public void setAvgSureDueDaysNonCdqAllTimeM6(String avgSureDueDaysNonCdqAllTimeM6){
        this.avgSureDueDaysNonCdqAllTimeM6 = avgSureDueDaysNonCdqAllTimeM6;
    }

    /**
    * 获取近6个月超短期现金贷还款金额占比
    *
    * @return 近6个月超短期现金贷还款金额占比
    */
    public String getPctPayAmtCdqProAllTimeM6(){
        return pctPayAmtCdqProAllTimeM6;
    }

    /**
    * 设置近6个月超短期现金贷还款金额占比
    * 
    * @param pctPayAmtCdqProAllTimeM6 要设置的近6个月超短期现金贷还款金额占比
    */
    public void setPctPayAmtCdqProAllTimeM6(String pctPayAmtCdqProAllTimeM6){
        this.pctPayAmtCdqProAllTimeM6 = pctPayAmtCdqProAllTimeM6;
    }

    /**
    * 获取最近12月借贷逾期借贷最大天数(确定)
    *
    * @return 最近12月借贷逾期借贷最大天数(确定)
    */
    public String getDdJiedaiMaxFailDaysM12(){
        return ddJiedaiMaxFailDaysM12;
    }

    /**
    * 设置最近12月借贷逾期借贷最大天数(确定)
    * 
    * @param ddJiedaiMaxFailDaysM12 要设置的最近12月借贷逾期借贷最大天数(确定)
    */
    public void setDdJiedaiMaxFailDaysM12(String ddJiedaiMaxFailDaysM12){
        this.ddJiedaiMaxFailDaysM12 = ddJiedaiMaxFailDaysM12;
    }

    /**
    * 获取最近12月借贷逾期借贷大于5天笔数
    *
    * @return 最近12月借贷逾期借贷大于5天笔数
    */
    public String getDdJiedaiSumFillD5CntM12(){
        return ddJiedaiSumFillD5CntM12;
    }

    /**
    * 设置最近12月借贷逾期借贷大于5天笔数
    * 
    * @param ddJiedaiSumFillD5CntM12 要设置的最近12月借贷逾期借贷大于5天笔数
    */
    public void setDdJiedaiSumFillD5CntM12(String ddJiedaiSumFillD5CntM12){
        this.ddJiedaiSumFillD5CntM12 = ddJiedaiSumFillD5CntM12;
    }

    /**
    * 获取近12个月最近一次逾期距今天数(确定)
    *
    * @return 近12个月最近一次逾期距今天数(确定)
    */
    public String getLastToEndSureDueAllProAllTimeM12(){
        return lastToEndSureDueAllProAllTimeM12;
    }

    /**
    * 设置近12个月最近一次逾期距今天数(确定)
    * 
    * @param lastToEndSureDueAllProAllTimeM12 要设置的近12个月最近一次逾期距今天数(确定)
    */
    public void setLastToEndSureDueAllProAllTimeM12(String lastToEndSureDueAllProAllTimeM12){
        this.lastToEndSureDueAllProAllTimeM12 = lastToEndSureDueAllProAllTimeM12;
    }

    /**
    * 获取近12个月非超短期现金贷累计逾期天数(确定)
    *
    * @return 近12个月非超短期现金贷累计逾期天数(确定)
    */
    public String getSumSureDueDaysNonCdqAllTimeM12(){
        return sumSureDueDaysNonCdqAllTimeM12;
    }

    /**
    * 设置近12个月非超短期现金贷累计逾期天数(确定)
    * 
    * @param sumSureDueDaysNonCdqAllTimeM12 要设置的近12个月非超短期现金贷累计逾期天数(确定)
    */
    public void setSumSureDueDaysNonCdqAllTimeM12(String sumSureDueDaysNonCdqAllTimeM12){
        this.sumSureDueDaysNonCdqAllTimeM12 = sumSureDueDaysNonCdqAllTimeM12;
    }

    /**
    * 获取近12个月最近一次非超短期现金贷逾期距今天数(确定)
    *
    * @return 近12个月最近一次非超短期现金贷逾期距今天数(确定)
    */
    public String getLastToEndSureDueNonCdqAllTimeM12(){
        return lastToEndSureDueNonCdqAllTimeM12;
    }

    /**
    * 设置近12个月最近一次非超短期现金贷逾期距今天数(确定)
    * 
    * @param lastToEndSureDueNonCdqAllTimeM12 要设置的近12个月最近一次非超短期现金贷逾期距今天数(确定)
    */
    public void setLastToEndSureDueNonCdqAllTimeM12(String lastToEndSureDueNonCdqAllTimeM12){
        this.lastToEndSureDueNonCdqAllTimeM12 = lastToEndSureDueNonCdqAllTimeM12;
    }

    /**
    * 获取近12个月最大逾期次数
    *
    * @return 近12个月最大逾期次数
    */
    public String getMaxDueCntAllProAllTimeM12(){
        return maxDueCntAllProAllTimeM12;
    }

    /**
    * 设置近12个月最大逾期次数
    * 
    * @param maxDueCntAllProAllTimeM12 要设置的近12个月最大逾期次数
    */
    public void setMaxDueCntAllProAllTimeM12(String maxDueCntAllProAllTimeM12){
        this.maxDueCntAllProAllTimeM12 = maxDueCntAllProAllTimeM12;
    }

    /**
    * 获取近12个月非超短期现金贷最大逾期次数
    *
    * @return 近12个月非超短期现金贷最大逾期次数
    */
    public String getMaxDueCntNonCdqAllTimeM12(){
        return maxDueCntNonCdqAllTimeM12;
    }

    /**
    * 设置近12个月非超短期现金贷最大逾期次数
    * 
    * @param maxDueCntNonCdqAllTimeM12 要设置的近12个月非超短期现金贷最大逾期次数
    */
    public void setMaxDueCntNonCdqAllTimeM12(String maxDueCntNonCdqAllTimeM12){
        this.maxDueCntNonCdqAllTimeM12 = maxDueCntNonCdqAllTimeM12;
    }

    /**
    * 获取近12个月最大还款金额
    *
    * @return 近12个月最大还款金额
    */
    public String getMaxPayAmtAllProAllTimeM12(){
        return maxPayAmtAllProAllTimeM12;
    }

    /**
    * 设置近12个月最大还款金额
    * 
    * @param maxPayAmtAllProAllTimeM12 要设置的近12个月最大还款金额
    */
    public void setMaxPayAmtAllProAllTimeM12(String maxPayAmtAllProAllTimeM12){
        this.maxPayAmtAllProAllTimeM12 = maxPayAmtAllProAllTimeM12;
    }

    /**
    * 获取近12个月累计还款笔数
    *
    * @return 近12个月累计还款笔数
    */
    public String getSumPayCntAllProAllTimeM12(){
        return sumPayCntAllProAllTimeM12;
    }

    /**
    * 设置近12个月累计还款笔数
    * 
    * @param sumPayCntAllProAllTimeM12 要设置的近12个月累计还款笔数
    */
    public void setSumPayCntAllProAllTimeM12(String sumPayCntAllProAllTimeM12){
        this.sumPayCntAllProAllTimeM12 = sumPayCntAllProAllTimeM12;
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
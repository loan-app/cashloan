package com.xiji.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 新颜小额网贷报告实体
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-10 19:16:55
 */
 public class XinyanLoanReport implements Serializable {

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
    * 交易订单号
    */
    private String tradeNo;

    /**
    * 分数
    */
    private String score;

    /**
    * 本业务最大授信额度
    */
    private String curMaxCredit;

    /**
    * 本业务平均授信额度
    */
    private String curAvgCredit;

    /**
    * 本业务近1个月贷款笔数
    */
    private String curLoanCnt30d;

    /**
    * 本业务近3个月贷款笔数
    */
    private String curLoanCnt90d;

    /**
    * 本业务近6个月贷款笔数
    */
    private String curLoanCnt180d;

    /**
    * 本业务贷款总笔数
    */
    private String curLoanTotalCnt;

    /**
    * 本业务贷款机构数
    */
    private String curLoanOrgTotalCnt;

    /**
    * 本业务最近一次贷款距今天数
    */
    private String curLastToEndLoan;

    /**
    * 本业务贷款已结清笔数
    */
    private String curLoanClearCnt;

    /**
    * 本业务贷款逾期订单数（30天）
    */
    private String curOverdueCnt30d;

    /**
    * 本业务贷款逾期订单数（30天以上）
    */
    private String curOverdueCntMore30d;

    /**
    * 查询多头机构数
    */
    private String queryOrgCnt;

    /**
    * 总查询次数
    */
    private String queryCnt;

    /**
    * 最近查询时间距今天数
    */
    private String lastToEndDays;

    /**
    * 近1个月查询多头
    */
    private String queryCnt30d;

    /**
    * 近3个月查询多头
    */
    private String queryCnt90d;

    /**
    * 近6个月查询多头
    */
    private String queryCnt180d;

    /**
    * 贷款已结清笔数
    */
    private String loanClearNum;

    /**
    * 贷款逾期订单数（30天）
    */
    private String overdueCnt30d;

    /**
    * 贷款逾期订单数（30天以上）
    */
    private String overdueCntMore30d;

    /**
    * 最近1个月工作日全部产品非逾期借贷在总借贷中金额占比
    */
    private String workDayNotOverdueAmountRadio30d;

    /**
    * 最近6个月全部时间全部产品非逾期借贷在总借贷中订单数占比
    */
    private String notOverdueOrderRadio180d;

    /**
    * 最近3个月全部时间消费金融类在全部产品中借贷确定逾期订单数占比
    */
    private String overdueOrderRadio90d;

    /**
    * 最近一年新增平台全部时间全部产品最大借贷费率
    */
    private String maxLoanRate12m;

    /**
    * 最近12个月全部时间超短期现金贷平均借贷费率
    */
    private String avgLoanRate12m;

    /**
    * 最近半年新增平台全部时间全部产品平均借贷确定逾期平台数
    */
    private String overdueOrgCnt6m;

    /**
    * 最近20次全部时间全部产品最大借贷疑似逾期天数
    */
    private String ddOverdueDays20time;

    /**
    * 最近3次工作日全部产品平均借贷疑似逾期天数
    */
    private String ddWorkDayOverdueDays3time;

    /**
    * 最近一年新增平台全部时间全部产品平均还款疑似逾期天数
    */
    private String ddOverdueDays12m;

    /**
    * 最近3个月全部时间全部产品最大还款疑似逾期天数
    */
    private String ddMaxOverdueDays3m;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 响应时间
    */
    private Date respTime;


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
    * 获取交易订单号
    *
    * @return 交易订单号
    */
    public String getTradeNo(){
        return tradeNo;
    }

    /**
    * 设置交易订单号
    * 
    * @param tradeNo 要设置的交易订单号
    */
    public void setTradeNo(String tradeNo){
        this.tradeNo = tradeNo;
    }

    /**
    * 获取分数
    *
    * @return 分数
    */
    public String getScore(){
        return score;
    }

    /**
    * 设置分数
    * 
    * @param score 要设置的分数
    */
    public void setScore(String score){
        this.score = score;
    }

    /**
    * 获取本业务最大授信额度
    *
    * @return 本业务最大授信额度
    */
    public String getCurMaxCredit(){
        return curMaxCredit;
    }

    /**
    * 设置本业务最大授信额度
    * 
    * @param curMaxCredit 要设置的本业务最大授信额度
    */
    public void setCurMaxCredit(String curMaxCredit){
        this.curMaxCredit = curMaxCredit;
    }

    /**
    * 获取本业务平均授信额度
    *
    * @return 本业务平均授信额度
    */
    public String getCurAvgCredit(){
        return curAvgCredit;
    }

    /**
    * 设置本业务平均授信额度
    * 
    * @param curAvgCredit 要设置的本业务平均授信额度
    */
    public void setCurAvgCredit(String curAvgCredit){
        this.curAvgCredit = curAvgCredit;
    }

    /**
    * 获取本业务近1个月贷款笔数
    *
    * @return 本业务近1个月贷款笔数
    */
    public String getCurLoanCnt30d(){
        return curLoanCnt30d;
    }

    /**
    * 设置本业务近1个月贷款笔数
    * 
    * @param curLoanCnt30d 要设置的本业务近1个月贷款笔数
    */
    public void setCurLoanCnt30d(String curLoanCnt30d){
        this.curLoanCnt30d = curLoanCnt30d;
    }

    /**
    * 获取本业务近3个月贷款笔数
    *
    * @return 本业务近3个月贷款笔数
    */
    public String getCurLoanCnt90d(){
        return curLoanCnt90d;
    }

    /**
    * 设置本业务近3个月贷款笔数
    * 
    * @param curLoanCnt90d 要设置的本业务近3个月贷款笔数
    */
    public void setCurLoanCnt90d(String curLoanCnt90d){
        this.curLoanCnt90d = curLoanCnt90d;
    }

    /**
    * 获取本业务近6个月贷款笔数
    *
    * @return 本业务近6个月贷款笔数
    */
    public String getCurLoanCnt180d(){
        return curLoanCnt180d;
    }

    /**
    * 设置本业务近6个月贷款笔数
    * 
    * @param curLoanCnt180d 要设置的本业务近6个月贷款笔数
    */
    public void setCurLoanCnt180d(String curLoanCnt180d){
        this.curLoanCnt180d = curLoanCnt180d;
    }

    /**
    * 获取本业务贷款总笔数
    *
    * @return 本业务贷款总笔数
    */
    public String getCurLoanTotalCnt(){
        return curLoanTotalCnt;
    }

    /**
    * 设置本业务贷款总笔数
    * 
    * @param curLoanTotalCnt 要设置的本业务贷款总笔数
    */
    public void setCurLoanTotalCnt(String curLoanTotalCnt){
        this.curLoanTotalCnt = curLoanTotalCnt;
    }

    /**
    * 获取本业务贷款机构数
    *
    * @return 本业务贷款机构数
    */
    public String getCurLoanOrgTotalCnt(){
        return curLoanOrgTotalCnt;
    }

    /**
    * 设置本业务贷款机构数
    * 
    * @param curLoanOrgTotalCnt 要设置的本业务贷款机构数
    */
    public void setCurLoanOrgTotalCnt(String curLoanOrgTotalCnt){
        this.curLoanOrgTotalCnt = curLoanOrgTotalCnt;
    }

    /**
    * 获取本业务最近一次贷款距今天数
    *
    * @return 本业务最近一次贷款距今天数
    */
    public String getCurLastToEndLoan(){
        return curLastToEndLoan;
    }

    /**
    * 设置本业务最近一次贷款距今天数
    * 
    * @param curLastToEndLoan 要设置的本业务最近一次贷款距今天数
    */
    public void setCurLastToEndLoan(String curLastToEndLoan){
        this.curLastToEndLoan = curLastToEndLoan;
    }

    /**
    * 获取本业务贷款已结清笔数
    *
    * @return 本业务贷款已结清笔数
    */
    public String getCurLoanClearCnt(){
        return curLoanClearCnt;
    }

    /**
    * 设置本业务贷款已结清笔数
    * 
    * @param curLoanClearCnt 要设置的本业务贷款已结清笔数
    */
    public void setCurLoanClearCnt(String curLoanClearCnt){
        this.curLoanClearCnt = curLoanClearCnt;
    }

    /**
    * 获取本业务贷款逾期订单数（30天）
    *
    * @return 本业务贷款逾期订单数（30天）
    */
    public String getCurOverdueCnt30d(){
        return curOverdueCnt30d;
    }

    /**
    * 设置本业务贷款逾期订单数（30天）
    * 
    * @param curOverdueCnt30d 要设置的本业务贷款逾期订单数（30天）
    */
    public void setCurOverdueCnt30d(String curOverdueCnt30d){
        this.curOverdueCnt30d = curOverdueCnt30d;
    }

    /**
    * 获取本业务贷款逾期订单数（30天以上）
    *
    * @return 本业务贷款逾期订单数（30天以上）
    */
    public String getCurOverdueCntMore30d(){
        return curOverdueCntMore30d;
    }

    /**
    * 设置本业务贷款逾期订单数（30天以上）
    * 
    * @param curOverdueCntMore30d 要设置的本业务贷款逾期订单数（30天以上）
    */
    public void setCurOverdueCntMore30d(String curOverdueCntMore30d){
        this.curOverdueCntMore30d = curOverdueCntMore30d;
    }

    /**
    * 获取查询多头机构数
    *
    * @return 查询多头机构数
    */
    public String getQueryOrgCnt(){
        return queryOrgCnt;
    }

    /**
    * 设置查询多头机构数
    * 
    * @param queryOrgCnt 要设置的查询多头机构数
    */
    public void setQueryOrgCnt(String queryOrgCnt){
        this.queryOrgCnt = queryOrgCnt;
    }

    /**
    * 获取总查询次数
    *
    * @return 总查询次数
    */
    public String getQueryCnt(){
        return queryCnt;
    }

    /**
    * 设置总查询次数
    * 
    * @param queryCnt 要设置的总查询次数
    */
    public void setQueryCnt(String queryCnt){
        this.queryCnt = queryCnt;
    }

    /**
    * 获取最近查询时间距今天数
    *
    * @return 最近查询时间距今天数
    */
    public String getLastToEndDays(){
        return lastToEndDays;
    }

    /**
    * 设置最近查询时间距今天数
    * 
    * @param lastToEndDays 要设置的最近查询时间距今天数
    */
    public void setLastToEndDays(String lastToEndDays){
        this.lastToEndDays = lastToEndDays;
    }

    /**
    * 获取近1个月查询多头
    *
    * @return 近1个月查询多头
    */
    public String getQueryCnt30d(){
        return queryCnt30d;
    }

    /**
    * 设置近1个月查询多头
    * 
    * @param queryCnt30d 要设置的近1个月查询多头
    */
    public void setQueryCnt30d(String queryCnt30d){
        this.queryCnt30d = queryCnt30d;
    }

    /**
    * 获取近3个月查询多头
    *
    * @return 近3个月查询多头
    */
    public String getQueryCnt90d(){
        return queryCnt90d;
    }

    /**
    * 设置近3个月查询多头
    * 
    * @param queryCnt90d 要设置的近3个月查询多头
    */
    public void setQueryCnt90d(String queryCnt90d){
        this.queryCnt90d = queryCnt90d;
    }

    /**
    * 获取近6个月查询多头
    *
    * @return 近6个月查询多头
    */
    public String getQueryCnt180d(){
        return queryCnt180d;
    }

    /**
    * 设置近6个月查询多头
    * 
    * @param queryCnt180d 要设置的近6个月查询多头
    */
    public void setQueryCnt180d(String queryCnt180d){
        this.queryCnt180d = queryCnt180d;
    }

    /**
    * 获取贷款已结清笔数
    *
    * @return 贷款已结清笔数
    */
    public String getLoanClearNum(){
        return loanClearNum;
    }

    /**
    * 设置贷款已结清笔数
    * 
    * @param loanClearNum 要设置的贷款已结清笔数
    */
    public void setLoanClearNum(String loanClearNum){
        this.loanClearNum = loanClearNum;
    }

    /**
    * 获取贷款逾期订单数（30天）
    *
    * @return 贷款逾期订单数（30天）
    */
    public String getOverdueCnt30d(){
        return overdueCnt30d;
    }

    /**
    * 设置贷款逾期订单数（30天）
    * 
    * @param overdueCnt30d 要设置的贷款逾期订单数（30天）
    */
    public void setOverdueCnt30d(String overdueCnt30d){
        this.overdueCnt30d = overdueCnt30d;
    }

    /**
    * 获取贷款逾期订单数（30天以上）
    *
    * @return 贷款逾期订单数（30天以上）
    */
    public String getOverdueCntMore30d(){
        return overdueCntMore30d;
    }

    /**
    * 设置贷款逾期订单数（30天以上）
    * 
    * @param overdueCntMore30d 要设置的贷款逾期订单数（30天以上）
    */
    public void setOverdueCntMore30d(String overdueCntMore30d){
        this.overdueCntMore30d = overdueCntMore30d;
    }

    /**
    * 获取最近1个月工作日全部产品非逾期借贷在总借贷中金额占比
    *
    * @return 最近1个月工作日全部产品非逾期借贷在总借贷中金额占比
    */
    public String getWorkDayNotOverdueAmountRadio30d(){
        return workDayNotOverdueAmountRadio30d;
    }

    /**
    * 设置最近1个月工作日全部产品非逾期借贷在总借贷中金额占比
    * 
    * @param workDayNotOverdueAmountRadio30d 要设置的最近1个月工作日全部产品非逾期借贷在总借贷中金额占比
    */
    public void setWorkDayNotOverdueAmountRadio30d(String workDayNotOverdueAmountRadio30d){
        this.workDayNotOverdueAmountRadio30d = workDayNotOverdueAmountRadio30d;
    }

    /**
    * 获取最近6个月全部时间全部产品非逾期借贷在总借贷中订单数占比
    *
    * @return 最近6个月全部时间全部产品非逾期借贷在总借贷中订单数占比
    */
    public String getNotOverdueOrderRadio180d(){
        return notOverdueOrderRadio180d;
    }

    /**
    * 设置最近6个月全部时间全部产品非逾期借贷在总借贷中订单数占比
    * 
    * @param notOverdueOrderRadio180d 要设置的最近6个月全部时间全部产品非逾期借贷在总借贷中订单数占比
    */
    public void setNotOverdueOrderRadio180d(String notOverdueOrderRadio180d){
        this.notOverdueOrderRadio180d = notOverdueOrderRadio180d;
    }

    /**
    * 获取最近3个月全部时间消费金融类在全部产品中借贷确定逾期订单数占比
    *
    * @return 最近3个月全部时间消费金融类在全部产品中借贷确定逾期订单数占比
    */
    public String getOverdueOrderRadio90d(){
        return overdueOrderRadio90d;
    }

    /**
    * 设置最近3个月全部时间消费金融类在全部产品中借贷确定逾期订单数占比
    * 
    * @param overdueOrderRadio90d 要设置的最近3个月全部时间消费金融类在全部产品中借贷确定逾期订单数占比
    */
    public void setOverdueOrderRadio90d(String overdueOrderRadio90d){
        this.overdueOrderRadio90d = overdueOrderRadio90d;
    }

    /**
    * 获取最近一年新增平台全部时间全部产品最大借贷费率
    *
    * @return 最近一年新增平台全部时间全部产品最大借贷费率
    */
    public String getMaxLoanRate12m(){
        return maxLoanRate12m;
    }

    /**
    * 设置最近一年新增平台全部时间全部产品最大借贷费率
    * 
    * @param maxLoanRate12m 要设置的最近一年新增平台全部时间全部产品最大借贷费率
    */
    public void setMaxLoanRate12m(String maxLoanRate12m){
        this.maxLoanRate12m = maxLoanRate12m;
    }

    /**
    * 获取最近12个月全部时间超短期现金贷平均借贷费率
    *
    * @return 最近12个月全部时间超短期现金贷平均借贷费率
    */
    public String getAvgLoanRate12m(){
        return avgLoanRate12m;
    }

    /**
    * 设置最近12个月全部时间超短期现金贷平均借贷费率
    * 
    * @param avgLoanRate12m 要设置的最近12个月全部时间超短期现金贷平均借贷费率
    */
    public void setAvgLoanRate12m(String avgLoanRate12m){
        this.avgLoanRate12m = avgLoanRate12m;
    }

    /**
    * 获取最近半年新增平台全部时间全部产品平均借贷确定逾期平台数
    *
    * @return 最近半年新增平台全部时间全部产品平均借贷确定逾期平台数
    */
    public String getOverdueOrgCnt6m(){
        return overdueOrgCnt6m;
    }

    /**
    * 设置最近半年新增平台全部时间全部产品平均借贷确定逾期平台数
    * 
    * @param overdueOrgCnt6m 要设置的最近半年新增平台全部时间全部产品平均借贷确定逾期平台数
    */
    public void setOverdueOrgCnt6m(String overdueOrgCnt6m){
        this.overdueOrgCnt6m = overdueOrgCnt6m;
    }

    /**
    * 获取最近20次全部时间全部产品最大借贷疑似逾期天数
    *
    * @return 最近20次全部时间全部产品最大借贷疑似逾期天数
    */
    public String getDdOverdueDays20time(){
        return ddOverdueDays20time;
    }

    /**
    * 设置最近20次全部时间全部产品最大借贷疑似逾期天数
    * 
    * @param ddOverdueDays20time 要设置的最近20次全部时间全部产品最大借贷疑似逾期天数
    */
    public void setDdOverdueDays20time(String ddOverdueDays20time){
        this.ddOverdueDays20time = ddOverdueDays20time;
    }

    /**
    * 获取最近3次工作日全部产品平均借贷疑似逾期天数
    *
    * @return 最近3次工作日全部产品平均借贷疑似逾期天数
    */
    public String getDdWorkDayOverdueDays3time(){
        return ddWorkDayOverdueDays3time;
    }

    /**
    * 设置最近3次工作日全部产品平均借贷疑似逾期天数
    * 
    * @param ddWorkDayOverdueDays3time 要设置的最近3次工作日全部产品平均借贷疑似逾期天数
    */
    public void setDdWorkDayOverdueDays3time(String ddWorkDayOverdueDays3time){
        this.ddWorkDayOverdueDays3time = ddWorkDayOverdueDays3time;
    }

    /**
    * 获取最近一年新增平台全部时间全部产品平均还款疑似逾期天数
    *
    * @return 最近一年新增平台全部时间全部产品平均还款疑似逾期天数
    */
    public String getDdOverdueDays12m(){
        return ddOverdueDays12m;
    }

    /**
    * 设置最近一年新增平台全部时间全部产品平均还款疑似逾期天数
    * 
    * @param ddOverdueDays12m 要设置的最近一年新增平台全部时间全部产品平均还款疑似逾期天数
    */
    public void setDdOverdueDays12m(String ddOverdueDays12m){
        this.ddOverdueDays12m = ddOverdueDays12m;
    }

    /**
    * 获取最近3个月全部时间全部产品最大还款疑似逾期天数
    *
    * @return 最近3个月全部时间全部产品最大还款疑似逾期天数
    */
    public String getDdMaxOverdueDays3m(){
        return ddMaxOverdueDays3m;
    }

    /**
    * 设置最近3个月全部时间全部产品最大还款疑似逾期天数
    * 
    * @param ddMaxOverdueDays3m 要设置的最近3个月全部时间全部产品最大还款疑似逾期天数
    */
    public void setDdMaxOverdueDays3m(String ddMaxOverdueDays3m){
        this.ddMaxOverdueDays3m = ddMaxOverdueDays3m;
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
    * 获取响应时间
    *
    * @return 响应时间
    */
    public Date getRespTime(){
        return respTime;
    }

    /**
    * 设置响应时间
    * 
    * @param respTime 要设置的响应时间
    */
    public void setRespTime(Date respTime){
        this.respTime = respTime;
    }

}
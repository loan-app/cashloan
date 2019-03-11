package com.xiji.cashloan.cl.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiji.cashloan.cl.domain.YouDunRiskReport;
import com.xiji.cashloan.cl.mapper.YouDunRiskReportMapper;
import com.xiji.cashloan.cl.service.YouDunRiskReportService;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


/**
 * 有盾详细数据ServiceImpl
 */

@Service("youdunLoanDetailService")
public class YouDunRiskReportServiceImpl extends BaseServiceImpl<YouDunRiskReport, Long> implements YouDunRiskReportService {

    private static final Logger logger = LoggerFactory.getLogger(YouDunRiskReportServiceImpl.class);

    @Resource
    private YouDunRiskReportMapper youDunRiskReportMapper;

    @Override
    public BaseMapper<YouDunRiskReport, Long> getMapper() {
        return youDunRiskReportMapper;
    }

    /**
     * 根据借款id 获取有盾风险评估报告
     *
     * @return
     */
    @Override
    public Map<String, Object> getYouDunRiskReportMap(Long borrowId) {
        HashMap<String, Object> queryMap = new HashMap<>();
        queryMap.put("borrowId", borrowId);
        YouDunRiskReport youDunRiskReport = youDunRiskReportMapper.findSelective(queryMap);
        if (youDunRiskReport == null || youDunRiskReport.getData() == null) {
            return null;
        }

        //获取有盾详细参数,转换成接json格式
        JSONObject data = JSON.parseObject(youDunRiskReport.getData());
        //获取借款详情数据
        JSONObject loanDetail = data.getJSONObject("loan_detail");
        //近1月申请/实际借款平台数(字符串拼接)
        String applyActualLoanPlatformCount1m=loanDetail.get("loan_platform_count_1m")
                .toString()+"/"+loanDetail.get("actual_loan_platform_count_1m").toString();
        //近3月申请/实际借款平台数(字符串拼接)
        String applyActualLoanPlatformCount3m=loanDetail.get("loan_platform_count_3m")
                .toString()+"/"+loanDetail.get("actual_loan_platform_count_3m").toString();
        //近6月申请/实际借款平台数(字符串拼接)
        String applyActualLoanPlatformCount6m=loanDetail.get("loan_platform_count_6m")
                .toString()+"/"+loanDetail.get("actual_loan_platform_count_6m").toString();
        //总的申请/实际借款平台数
        String applyActualLoanPlatformCount=loanDetail.get("loan_platform_count")
                .toString()+"/"+loanDetail.get("actual_loan_platform_count").toString();

        Map<String,Object> map = new HashMap();

        map.put("apply_actual_loan_platform_count_1m",applyActualLoanPlatformCount1m);
        map.put("apply_actual_loan_platform_count_3m",applyActualLoanPlatformCount3m);
        map.put("apply_actual_loan_platform_count_6m",applyActualLoanPlatformCount6m);
        map.put("apply_actual_loan_platform_count",applyActualLoanPlatformCount);
        //近1月还款平台数
        map.put("repayment_platform_count_1m",loanDetail.get("repayment_platform_count_1m").toString());
        //近3月还款平台数
        map.put("repayment_platform_count_3m",loanDetail.get("repayment_platform_count_3m").toString());
        //近6月还款平台数
        map.put("repayment_platform_count_6m",loanDetail.get("repayment_platform_count_6m").toString());
        //总的还款平台数
        map.put("repayment_platform_count",loanDetail.get("repayment_platform_count").toString());
        //还款笔数
        map.put("repayment_times_count",loanDetail.get("repayment_times_count").toString());
        //获取风险模型得分数据json对象
        JSONObject scoreDetail = data.getJSONObject("score_detail");
        //欺诈得分
        map.put("score",scoreDetail.get("score").toString());
        //欺诈等级
        map.put("risk_evaluation",scoreDetail.get("risk_evaluation").toString());
        //最后更新时间
        map.put("last_modified_time",data.get("last_modified_time").toString());
        //用户特征列表
        map.put("user_features",data.getJSONArray("user_features"));
        //设备信息
        map.put("devices_list",data.getJSONArray("devices_list"));
        return map;
    }


}
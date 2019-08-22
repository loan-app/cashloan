package com.xiji.cashloan.cl.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xiji.cashloan.cl.domain.*;
import com.xiji.cashloan.cl.mapper.*;
import com.xiji.cashloan.cl.service.YixinRiskService;
import com.xiji.cashloan.cl.util.CallsOutSideFeeConstant;
import com.xiji.cashloan.cl.util.token.HttpRestUtils;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.util.DateUtil;
import com.xiji.cashloan.core.common.util.StringUtil;
import com.xiji.cashloan.core.domain.Borrow;
import com.xiji.cashloan.core.domain.UserBaseInfo;
import com.xiji.cashloan.core.mapper.UserBaseInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 宜信阿福查询
 * Created by szb on 18/12/25.
 */
@Service
public class YixinRiskServiceImpl implements YixinRiskService {

    public static final Logger logger = LoggerFactory.getLogger(YixinRiskServiceImpl.class);

    @Resource
    private UserBaseInfoMapper userBaseInfoMapper;
    @Resource
    private CallsOutSideFeeMapper callsOutSideFeeMapper;
    @Resource
    private YixinRiskReportMapper yixinRiskReportMapper;
    @Resource
    private YixinReqLogMapper yixinReqLogMapper;
    @Resource
    private YixinFraudMapper yixinFraudMapper;
    @Resource
    private YixinScoreMapper yixinScoreMapper;

    //查询原因-贷款审批
    private static String QUERY_REASON = "LOAN_AUDIT";
    //风险评估类型
    private static int RISK_TYPE = 1;
    //欺诈甄别类型
    private static int FRAUD_TYPE = 2;
    //综合决策报告小额评分
    private static int SCORE_TYPE = 3;

    @Override
    public int queryRisk(Borrow borrow) {
        int i = 0;
        UserBaseInfo userBaseinfo = userBaseInfoMapper.findByUserId(borrow.getUserId());
        if (userBaseinfo == null) {
            logger.error("查询用户userId：" + userBaseinfo.getUserId() + ",用户不存在");
            return i;
        }
        Date createDate = DateUtil.getNow();
        Long userId = userBaseinfo.getUserId();
        YixinReqLog log = new YixinReqLog();
        log.setUserId(userId);
        log.setBorrowId(borrow.getId());
        log.setCreateTime(createDate);
        log.setType(RISK_TYPE);
        log.setIsFee(0);

        /** 1、调用用户名 **/
        String USER_NAME = Global.getValue("yixin_user_name_fraud_risk");
        /** 2、调用秘钥 **/
        String SIGN = Global.getValue("yixin_sign_fraud_risk");
        /** 3、请求地址 **/
        String API_HOST = Global.getValue("yixin_risk_url");

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("user_name", USER_NAME);
        paramMap.put("sign", SIGN);
        paramMap.put("api_name", Global.getValue("yixin_risk_api_name"));
        paramMap.put("query_reason", QUERY_REASON);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id_no", userBaseinfo.getIdNo());
        jsonObject.put("name", userBaseinfo.getRealName());

        paramMap.put("params", jsonObject.toJSONString());

        try {
            String result = HttpRestUtils.postForm(API_HOST, null, paramMap);
            if (StringUtil.isNotBlank(result)) {
                JSONObject resJson = JSONObject.parseObject(result);
                log.setRespCode(String.valueOf(resJson.get("code")));
                log.setIsSuccess(resJson.getBoolean("success") ? 1 : 0);
                log.setRespMsg(resJson.getString("msg"));
                Date respTime = DateUtil.getNow();
                log.setRespTime(respTime);
                if (resJson.getBoolean("success") && "10000".equals(resJson.getString("code"))) {
                    log.setIsFee(1);
                    log.setFlowId(resJson.getString("flowId"));
                    //插入收费记录表
                    CallsOutSideFee callsOutSideFee = new CallsOutSideFee(userId, resJson.getString("flowId"), CallsOutSideFeeConstant.CALLS_TYPE_YIXIN_RISK,
                            CallsOutSideFeeConstant.FEE_YIXIN_RISK, CallsOutSideFeeConstant.CAST_TYPE_CONSUME, userBaseinfo.getPhone());
                    callsOutSideFeeMapper.save(callsOutSideFee);
                    i = saveRiskReport(resJson.getString("data"), borrow.getUserId(), resJson.getString("flowId"), borrow.getId());
                } else {
                    logger.error("用户" + userBaseinfo.getRealName() + "，请求宜信风险评估响应错误, result:" + result);
                }
            } else {
                logger.error("用户" + userBaseinfo.getRealName() + "，请求宜信风险评估同步响应数据为空，result:" + result);
            }
        } catch (Exception e) {
            logger.error("用户" + userBaseinfo.getRealName() + "，请求宜信风险评估出现异常", e);
        }

        yixinReqLogMapper.save(log);
        return i;
    }

    @Override
    public int queryFraud(Borrow borrow) {
        int i = 0;
        UserBaseInfo userBaseinfo = userBaseInfoMapper.findByUserId(borrow.getUserId());
        if (userBaseinfo == null) {
            logger.error("查询用户userId：" + userBaseinfo.getUserId() + ",用户不存在");
            return i;
        }
        Date createDate = DateUtil.getNow();
        Long userId = userBaseinfo.getUserId();
        YixinReqLog log = new YixinReqLog();
        log.setUserId(userId);
        log.setBorrowId(borrow.getId());
        log.setCreateTime(createDate);
        log.setType(FRAUD_TYPE);
        log.setIsFee(0);

        /** 1、调用用户名 **/
        String USER_NAME = Global.getValue("yixin_user_name_fraud_risk");
        /** 2、调用秘钥 **/
        String SIGN = Global.getValue("yixin_sign_fraud_risk");
        /** 3、请求地址 **/
        String API_HOST = Global.getValue("yixin_risk_url");

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("user_name", USER_NAME);
        paramMap.put("sign", SIGN);
        paramMap.put("api_name", Global.getValue("yixin_fraud_api_name"));
        paramMap.put("query_reason", QUERY_REASON);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id_no", userBaseinfo.getIdNo());
        jsonObject.put("name", userBaseinfo.getRealName());
        jsonObject.put("amount_business", "0");
        jsonObject.put("mobile", userBaseinfo.getPhone());

        paramMap.put("params", jsonObject.toJSONString());

        try {
            String result = HttpRestUtils.postForm(API_HOST, null, paramMap);
            if (StringUtil.isNotBlank(result)) {
                JSONObject resJson = JSONObject.parseObject(result);
                log.setRespCode(String.valueOf(resJson.get("code")));
                log.setIsSuccess(resJson.getBoolean("success") ? 1 : 0);
                log.setRespMsg(resJson.getString("msg"));
                Date respTime = DateUtil.getNow();
                log.setRespTime(respTime);
                if (resJson.getBoolean("success") && "10000".equals(resJson.getString("code"))) {
                    log.setIsFee(1);
                    //插入收费记录表
                    CallsOutSideFee callsOutSideFee = new CallsOutSideFee(userId, resJson.getString("flowId"), CallsOutSideFeeConstant.CALLS_TYPE_YIXIN_FRAUD,
                            CallsOutSideFeeConstant.FEE_YIXIN_FRAUD, CallsOutSideFeeConstant.CAST_TYPE_CONSUME, userBaseinfo.getPhone());
                    callsOutSideFeeMapper.save(callsOutSideFee);
                    i = saveFraud(resJson.getString("data"), borrow.getUserId(), resJson.getString("flowId"), borrow.getId());
                } else {
                    logger.error("用户" + userBaseinfo.getRealName() + "，请求宜信欺诈甄别响应错误, result:" + result);
                }
            } else {
                logger.error("用户" + userBaseinfo.getRealName() + "，请求宜信欺诈甄别同步响应数据为空，result:" + result);
            }
        } catch (Exception e) {
            logger.error("用户" + userBaseinfo.getRealName() + "，请求宜信欺诈甄别出现异常", e);
        }

        yixinReqLogMapper.save(log);
        return i;
    }

    /**
     * 查询综合决策报告小额评分
     * @param borrow
     * @return
     */
    @Override
    public double queryScore(Borrow borrow) {
        double score = 0.00;
        UserBaseInfo userBaseinfo = userBaseInfoMapper.findByUserId(borrow.getUserId());
        if (userBaseinfo == null) {
            logger.error("查询用户userId：" + userBaseinfo.getUserId() + ",用户不存在");
            return score;
        }
        Date createDate = DateUtil.getNow();
        Long userId = userBaseinfo.getUserId();
        YixinReqLog log = new YixinReqLog();
        log.setUserId(userId);
        log.setBorrowId(borrow.getId());
        log.setCreateTime(createDate);
        log.setType(SCORE_TYPE);
        log.setIsFee(1);

        /** 1、调用用户名 **/
        String USER_NAME = Global.getValue("yixin_user_name");
        /** 2、调用秘钥 **/
        String SIGN = Global.getValue("yixin_sign");
        /** 3、请求地址 **/
        String API_HOST = Global.getValue("yixin_risk_url");

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("user_name", USER_NAME);
        paramMap.put("sign", SIGN);
        paramMap.put("api_name", Global.getValue("yixin_score_api_name"));
        paramMap.put("query_reason", QUERY_REASON);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id_no", userBaseinfo.getIdNo());
        jsonObject.put("name", userBaseinfo.getRealName());
        jsonObject.put("amount_business", "0");
        jsonObject.put("mobile", userBaseinfo.getPhone());

        paramMap.put("params", jsonObject.toJSONString());

        try {
            String result = HttpRestUtils.postForm(API_HOST, null, paramMap);
            if (StringUtil.isNotBlank(result)) {
                JSONObject resJson = JSONObject.parseObject(result);
                log.setRespCode(String.valueOf(resJson.get("code")));
                log.setIsSuccess(resJson.getBoolean("success") ? 1 : 0);
                log.setRespMsg(resJson.getString("msg"));
                Date respTime = DateUtil.getNow();
                log.setRespTime(respTime);
                if (resJson.getBoolean("success") && "10000".equals(resJson.getString("code"))) {
                    log.setIsFee(1);
                    //插入收费记录表
                    CallsOutSideFee callsOutSideFee = new CallsOutSideFee(userId, resJson.getString("flowId"), CallsOutSideFeeConstant.CALLS_TYPE_YIXIN_SCORE,
                            CallsOutSideFeeConstant.FEE_YIXIN_SCORE, CallsOutSideFeeConstant.CAST_TYPE_CONSUME, userBaseinfo.getPhone());
                    callsOutSideFeeMapper.save(callsOutSideFee);
                    JSONObject  jsonData = JSONObject.parseObject(resJson.getString("data")) ;
                    if (jsonData != null && jsonData.getDouble("compositeScore") != null){
                        score = jsonData.getDouble("compositeScore");
                        saveScore(jsonData, borrow.getUserId(), resJson.getString("flowId"), borrow.getId());
                        logger.info("用户"+ userBaseinfo.getRealName() + ",综合决策报告小额评分为，result ==>"+score);
                    } else {
                        logger.info("用户"+ userBaseinfo.getRealName() + ",综合决策报告小额评分异常，result ==>"+resJson);
                    }
                } else {
                    logger.error("用户" + userBaseinfo.getRealName() + "，请求宜信综合决策报告小额评分响应错误, result:" + result);
                }
            } else {
                logger.error("用户" + userBaseinfo.getRealName() + "，请求宜信综合决策报告小额评分同步响应数据为空，result:" + result);
            }
        } catch (Exception e) {
            logger.error("用户" + userBaseinfo.getRealName() + "，请求宜信综合决策报告小额评分出现异常", e);
        }
        yixinReqLogMapper.save(log);
        return score;
    }

    private int saveRiskReport(String data, Long userId, String flowId, Long borrowId) {
        YixinRiskReport yixinRiskReport = new YixinRiskReport();
        yixinRiskReport.setUserId(userId);
        yixinRiskReport.setFlowId(flowId);
        yixinRiskReport.setBorrowId(borrowId);
        yixinRiskReport.setData(data);
        yixinRiskReport.setGmtCreate(DateUtil.getNow());
        yixinRiskReport.setGmtModified(DateUtil.getNow());

        int i = yixinRiskReportMapper.save(yixinRiskReport);
        return i;
    }

    private int saveFraud(String data, Long userId, String flowId, Long borrowId) {
        YixinFraud yixinFraud = new YixinFraud();
        yixinFraud.setUserId(userId);
        yixinFraud.setFlowId(flowId);
        yixinFraud.setBorrowId(borrowId);
        yixinFraud.setData(data);
        yixinFraud.setGmtCreate(DateUtil.getNow());
        yixinFraud.setGmtModified(DateUtil.getNow());

        int i = yixinFraudMapper.save(yixinFraud);
        return i;
    }

    @Override
    public int saveScore(JSONObject  jsonData, Long userId, String flowId, Long borrowId) {
        YixinScore yixinScore = new YixinScore();
        yixinScore.setUserId(userId);
        yixinScore.setFlowId(flowId);
        yixinScore.setBorrowId(borrowId);
        yixinScore.setCompositeScore(jsonData.getDouble("compositeScore"));
        yixinScore.setDecisionSuggest(jsonData.getString("decisionSuggest"));
        yixinScore.setGmtCreate(DateUtil.getNow());
        yixinScore.setGmtModified(DateUtil.getNow());
        int i = yixinScoreMapper.save(yixinScore);
        return i;
    }
}

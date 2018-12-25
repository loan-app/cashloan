package com.xiji.cashloan.cl.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xiji.cashloan.cl.domain.CallsOutSideFee;
import com.xiji.cashloan.cl.domain.XinyanReqLog;
import com.xiji.cashloan.cl.domain.YixinReqLog;
import com.xiji.cashloan.cl.domain.YixinRiskReport;
import com.xiji.cashloan.cl.mapper.CallsOutSideFeeMapper;
import com.xiji.cashloan.cl.mapper.YixinReqLogMapper;
import com.xiji.cashloan.cl.mapper.YixinRiskReportMapper;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    //业务码-固定值
    private static String API_NAME = "credit.evaluation.share.api";
    //查询原因-贷款审批
    private static String QUERY_REASON = "LOAN_AUDIT";

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
        log.setType(1);
        log.setIsFee(0);

        /** 1、调用用户名 **/
        String USER_NAME = Global.getValue("yixin_user_name");
        /** 2、调用秘钥 **/
        String SIGN = Global.getValue("yixin_sign");
        /** 3、请求地址 **/
        String API_HOST = Global.getValue("yixin_risk_url");

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("user_name", USER_NAME);
        paramMap.put("sign", SIGN);
        paramMap.put("api_name", API_NAME);
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
}

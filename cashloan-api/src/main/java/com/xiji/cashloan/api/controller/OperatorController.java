package com.xiji.cashloan.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiji.cashloan.cl.domain.*;
import com.xiji.cashloan.cl.model.UserAuthModel;
import com.xiji.cashloan.cl.model.moxie.MxConstant;
import com.xiji.cashloan.cl.model.moxie.MxCreditRequest;
import com.xiji.cashloan.cl.model.moxie.OperatorStatusEnum;
import com.xiji.cashloan.cl.model.moxie.SignatureUtils;
import com.xiji.cashloan.cl.service.*;
import com.xiji.cashloan.cl.util.CallsOutSideFeeConstant;
import com.xiji.cashloan.cl.util.token.HttpRestUtils;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.util.DateUtil;
import com.xiji.cashloan.core.common.util.ServletUtils;
import com.xiji.cashloan.core.common.util.StringUtil;
import com.xiji.cashloan.core.common.web.controller.BaseController;
import com.xiji.cashloan.core.domain.UserBaseInfo;
import com.xiji.cashloan.core.service.UserBaseInfoService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 运营商认证
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/12/03
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Controller
@Scope("prototype")
public class OperatorController extends BaseController {

    public static final Logger logger = LoggerFactory.getLogger(OperatorController.class);

    @Resource
    private UserBaseInfoService userBaseInfoService;
    @Resource
    private UserAuthService userAuthService;
    @Resource
    private OperatorReqLogService operatorReqLogService;
    @Resource
    private OperatorRespDetailService operatorRespDetailService;
    @Resource
    private OperatorService operatorService;
    @Resource
    private OperatorReportService operatorReportService;
    @Resource
    private CallsOutSideFeeService callsOutSideFeeService;
    @Resource
    private OperatorReportLinkService operatorReportLinkService;
    @Resource
    private OperatorVoiceCntService operatorVoiceCntService;

    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(20);

    /**
     * @return void
     * @throws Exception
     * @description 运营商认证，上述和同盾 获取第三方url，用于APP跳转
     * @author chenxy
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/api/act/mine/operator/operatorCredit.htm")
    public void operatorCredit() throws Exception {
        long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
        //long userId = 1;
        Map<String, Object> respMap = new HashMap<>();
        boolean isCanCredit = operatorReqLogService.checkUserOperator(userId);
        if (!isCanCredit) {
            respMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            respMap.put(Constant.RESPONSE_CODE_MSG, "您今日认证操作过于频繁，请明日再试");
        } else {
            UserBaseInfo userBaseInfo = userBaseInfoService.findByUserId(userId);

            String operatorSelect = Global.getValue("operator_select");
            String backUrl = Global.getValue("server_host") + "/api/operatorReturnback.htm";

            Map<String, Object> data = new HashMap<>();
            if ("moxie".equals(operatorSelect)){
                //上数运营商
                String url = Global.getValue("mx_operator_url");
                backUrl = "&backUrl=" + URLEncoder.encode(backUrl, "UTF-8");
                url += "?apiKey=" + Global.getValue("mx_apikey") + "&userId=" + userId + "&phone=" + userBaseInfo.getPhone()
                        + "&idcard=" + userBaseInfo.getIdNo() + "&name=" + URLEncoder.encode(userBaseInfo.getRealName(), "UTF-8") + backUrl;

                data.put("url", url);
            }else if ("gxb".equals(operatorSelect)){
                long timestamp = System.currentTimeMillis();
                String appId = Global.getValue("gxb_appid");
                String appSecret = Global.getValue("gxb_appsecret");
                String gxbGetTokenUrl = Global.getValue("gxb_get_token_url");
                String sequenceNo = HttpRestUtils.getRandomNumber("hzxjgxb");
                String sign = DigestUtils.md5Hex(String.format("%s%s%s%s%s",appId, appSecret, "operator_plus", timestamp, sequenceNo));
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("appId",appId);
                jsonObject.put("sign",sign);
                jsonObject.put("sequenceNo",sequenceNo);
                jsonObject.put("authItem","operator_plus");
                jsonObject.put("timestamp",timestamp);
                jsonObject.put("name",userBaseInfo.getRealName());
                jsonObject.put("phone",userBaseInfo.getPhone());
                jsonObject.put("idcard",userBaseInfo.getIdNo());
                jsonObject.put("authVersion","v1");
                String token = "";
                try {
                    token = HttpRestUtils.postRequest(gxbGetTokenUrl,null,jsonObject.toJSONString());
                } catch (Exception e) {
                    logger.error("公信宝获取token失败   jsonObject ==> "+jsonObject.toJSONString());
                }
                JSONObject result = JSONObject.parseObject(token);
                backUrl = "?returnUrl=" + URLEncoder.encode(backUrl, "UTF-8");
                String gxbH5Url = Global.getValue("gxb_h5_url");
                gxbH5Url += backUrl +"&token="+ JSONObject.parseObject(result.getString("data")).getString("token");
                data.put("url", gxbH5Url);
            }
            respMap.put(Constant.RESPONSE_DATA, data);
            respMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            respMap.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        }

        ServletUtils.writeToResponse(response, respMap);
    }

    /**
     * 运营商验证，在用户提交完验证信息之后访问的接口，修改状态为认证中
     */
    @RequestMapping(value = "/api/operatorReturnback.htm")
    public String operatorReturnback() {
        return "operatorReturnback";
    }


    @RequestMapping(value = "/api/moxie/operatorCallback.htm")
    public void notifyUpdateBill(@RequestBody String body, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        //事件类型：task or bill
        String eventName = request.getHeader(MxConstant.HEADER_MOXIE_EVENT);
        //业务类型：email、bank、carrier 等
        String eventType = request.getHeader(MxConstant.HEADER_MOXIE_TYPE);
        //body签名
        String signature = request.getHeader(MxConstant.HEADER_MOXIE_SIGNATURE);
        logger.debug("魔蝎运营商回调,request body:" + body);
        if (StringUtil.isBlank(eventName)) {
            writeMessage(response, HttpServletResponse.SC_BAD_REQUEST, "header not found:" + MxConstant.HEADER_MOXIE_EVENT);
            return;
        }
        if (StringUtil.isBlank(eventType)) {
            writeMessage(response, HttpServletResponse.SC_BAD_REQUEST, "header not found:" + MxConstant.HEADER_MOXIE_TYPE);
            return;
        }
        if (StringUtil.isBlank(signature)) {
            writeMessage(response, HttpServletResponse.SC_BAD_REQUEST, "header not found:" + MxConstant.HEADER_MOXIE_SIGNATURE);
            return;
        }
        if (StringUtil.isBlank(body)) {
            writeMessage(response, HttpServletResponse.SC_BAD_REQUEST, "request body is empty");
            return;
        }
        //验签，判断body是否被篡改
        if (!SignatureUtils.base64Hmac256(Global.getValue("mx_secret"), body).equals(signature)) {
            writeMessage(response, HttpServletResponse.SC_BAD_REQUEST, "signature mismatch");
            return;
        }

        logger.debug("event name:" + eventName.toLowerCase());

        JSONObject requestJson = JSON.parseObject(body);
        final String taskId = requestJson.getString("task_id");
        final long userId = requestJson.getLongValue("user_id");
        long timestamp = requestJson.getLongValue("timestamp");
        final String mobile = requestJson.getString("mobile");

        //任务创建成功,判断task是否存在,不存在,创建
        Map<String, Object> temp = new HashMap<>();
        temp.put("taskId", taskId);
        OperatorReqLog operatorReqLog = operatorReqLogService.findSelective(temp);
        if (operatorReqLog == null) {
            if (StringUtil.equals(eventName.toLowerCase(), "task.submit")) {
                //运营商认证记录不存在,新建
                UserBaseInfo userBaseInfo = userBaseInfoService.findByUserId(userId);
                if (userBaseInfo == null) {
                    writeMessage(response, HttpServletResponse.SC_BAD_REQUEST, "user does not exist");
                    return;
                }
                operatorReqLog = new OperatorReqLog(userId, taskId, OperatorReqLog.STATE_CREATE_SUCCESS);
                operatorReqLogService.insert(operatorReqLog);

                temp.clear();
                temp.put("userId", operatorReqLog.getUserId());
                temp.put("phoneState", UserAuthModel.STATE_ERTIFICATION);
                updateUserAuthState(operatorReqLog.getUserId(), UserAuthModel.STATE_ERTIFICATION);

                writeMessage(response, HttpServletResponse.SC_CREATED, "success");
                return;
            } else {
                writeMessage(response, HttpServletResponse.SC_BAD_REQUEST, "task does not exist");
                return;
            }
        }
        final Long reqLogId = operatorReqLog.getId();
        //登录结果
        //{"mobile":"15368098198","timestamp":1476084445670,"result":false,"message":"[CALO-22001-10]-服务密码错误，请确认正确后输入。","user_id":"374791","task_id":"fdda6b30-8eba-11e6-b7e9-00163e10b2cd"}
        if (StringUtil.equals(eventName.toLowerCase(), "task")) {
            if (requestJson.containsKey("result")) {
                String result = requestJson.get("result").toString();
                if (StringUtil.equals(result, "false")) {
                    if (requestJson.containsKey("message")) {
                        String message = requestJson.get("message") == null ? "未知异常" : requestJson.get("message").toString();
                        //认证记录状态修改为登录失败
                        updateOperatorLogState(operatorReqLog.getId(), "task.false", message, timestamp);
                        //用户运营商认证状态修改为未认证
                        updateUserAuthState(userId, UserAuthModel.STATE_NOT_CERTIFIED);
                        logger.debug("task event. result={}, message={}", result, message);
                    }
                } else {
                    //状态修改为 登录成功
                    updateOperatorLogState(operatorReqLog.getId(), "task.true", StringUtil.EMPTY, timestamp);
                }
            }
        }

        //采集状态
        //运营商的格式{"mobile":"13429801680","timestamp":1474641874728,"result":false,"message":"系统繁忙，请稍后再试","user_id":"1111","task_id":"3e9ff350-819c-11e6-b7fe-00163e004a23"}
        if (StringUtil.equals(eventName.toLowerCase(), "task.fail")) {
            if (requestJson.containsKey("result") && requestJson.containsKey("message")) {
                String result = requestJson.get("result").toString();
                String message = requestJson.get("message") == null ? "未知异常" : requestJson.get("message").toString();
                if (StringUtil.equals(result, "false")) {
                    //认证记录状态修改为采集失败
                    updateOperatorLogState(operatorReqLog.getId(), "task.fail", message, timestamp);
                    //用户运营商认证状态修改为未认证
                    updateUserAuthState(userId, UserAuthModel.STATE_NOT_CERTIFIED);
                    logger.debug("task fail event. result={}, message={}", result, message);
                }
            }
        }
        //任务完成的通知处理bill
        if (StringUtil.equals(eventName.toLowerCase(), "bill")) {
            final Date updateTime = new Date();
            //通知状态变更为 '采集成功'
            updateOperatorLogState(reqLogId, "bill", StringUtil.EMPTY, timestamp);
            //运营商数据生成成功,调用接口获取数据
            fixedThreadPool.execute(new Runnable() {
                public void run() {
                    try {
                        String host = Global.getValue("mx_operator_mxdata");
                        final String token = Global.getValue("mx_token");
                        Map<String, String> headMap = new HashMap<>();

                        headMap.put("Authorization", "token" + " " + token);
                        host = host.replace("{mobile}", mobile);
                        host += "?task_id=" + taskId;
                        String result = MxCreditRequest.get(host, headMap);
//                        String result = MxCreditRequest.getCompress(host, headMap);
                        OperatorRespDetail oldDetail = operatorRespDetailService.getByTaskId(taskId);
                        if (oldDetail == null) {
                            OperatorRespDetail operatorRespDetail = new OperatorRespDetail(reqLogId, taskId, result);
                            operatorRespDetailService.insert(operatorRespDetail);
                        } else {
                            Map<String, Object> updateMap = new HashMap<>();
                            updateMap.put("id", oldDetail.getId());
                            updateMap.put("operatorData", result);
                            operatorRespDetailService.updateSelective(updateMap);
                        }

                        int start = DateUtil.getNowTime();
                        operatorService.saveOperatorInfos(result, userId, updateTime, mobile, reqLogId);
                        int end = DateUtil.getNowTime();
                        logger.info("保存userId" + userId + "运营商数据，耗时" + (end - start) + "秒");
                    } catch (Exception e) {
                        logger.error("严重问题，userId:" + userId + "运营商数据保存异常", e);
                        return;
                    }
                    //插入调用外部数据接口费用表
                    CallsOutSideFee callsOutSideFee = callsOutSideFeeService.getByTaskId(taskId);
                    if(callsOutSideFee == null) {
                        callsOutSideFee = new CallsOutSideFee(userId, taskId, CallsOutSideFeeConstant.CALLS_TYPE_OPERATOR, CallsOutSideFeeConstant.FEE_OPERATOR,CallsOutSideFeeConstant.CAST_TYPE_CONSUME,mobile);
                        callsOutSideFeeService.insert(callsOutSideFee);
                    }
                }
            });
        }

        if (StringUtil.equals(eventName.toLowerCase(), "report")) {
            logger.info("开始保存userId" + userId + "运营商报告");
            if (requestJson.containsKey("result")) {
                String result = requestJson.get("result").toString();
                if (StringUtil.equals(result, "false")) {
                    if (requestJson.containsKey("message")) {
                        String message = requestJson.get("message") == null ? "未知异常" : requestJson.get("message").toString();
                        //认证记录状态修改为报告生成失败,不处理用户运营商认证状态
                        updateOperatorLogState(operatorReqLog.getId(), "report.false", message, timestamp);
                        logger.debug("report event. result={}, message={}", result, message);
                    }
                } else {
                    final Date updateTime = new Date();
                    //状态修改为 报告生成成功
                    updateOperatorLogState(operatorReqLog.getId(), "report.true", StringUtil.EMPTY, timestamp);
                    //保存运营商报告链接
                    String message = requestJson.getString("message");
                    OperatorReportLink operatorReportLink = new OperatorReportLink(userId, taskId, message);
                    operatorReportLinkService.insert(operatorReportLink);
                    //获取运营商报告
                    fixedThreadPool.execute(new Runnable() {
                        public void run() {
                            Map<String, Object> userAuth = new HashMap<String, Object>();
                            userAuth.put("userId", userId);
                            userAuth.put("phoneTime", DateUtil.getNow());
                            try {
                                String host = Global.getValue("mx_operator_report");
                                final String token = Global.getValue("mx_token");
                                Map<String, String> headMap = new HashMap<>();
                                headMap.put("Authorization", "token" + " " + token);
                                host = host.replace("{mobile}", mobile);
                                String result = MxCreditRequest.get(host, headMap);
                                OperatorReport oldReport = operatorReportService.getByTaskId(taskId);
                                if (oldReport == null) {
                                    OperatorReport operatorReport = new OperatorReport(userId, reqLogId, taskId, result);
                                    operatorReportService.insert(operatorReport);
                                } else {
                                    Map<String, Object> updateMap = new HashMap<>();
                                    updateMap.put("id", oldReport.getId());
                                    updateMap.put("report", result);
                                    updateMap.put("gmtModified", DateUtil.getNow());
                                    operatorReportService.updateSelective(updateMap);
                                }

                                int start = DateUtil.getNowTime();
                                operatorVoiceCntService.paserReportDetail(result, userId, updateTime, reqLogId);
                                operatorVoiceCntService.lastContactTime(userId, reqLogId);
                                int end = DateUtil.getNowTime();
                                logger.info("保存userId" + userId + "运营商报告，详情统计，耗时" + (end - start) + "秒");
                                //修改认证状态为认证完成
                                userAuth.put("phoneState", UserAuthModel.STATE_VERIFIED);
                                userAuthService.updateByUserId(userAuth);
                            } catch (Exception e) {
                                // 运营商报告保存失败，将认证状态改回未认证
                                userAuth.put("phoneState", UserAuthModel.STATE_NOT_CERTIFIED);
                                userAuthService.updateByUserId(userAuth);
                                logger.error("严重问题，userId:" + userId + "运营商数据报告保存失败", e);
                                return;
                            }
                        }
                    });
                }
            }
        }
        writeMessage(response, HttpServletResponse.SC_CREATED, "success");
    }
//    @RequestMapping(value = "/api/moxie/voiceCnt.htm")
//    public void testOperatorReport() throws Exception {
//        String res = "";
//        long userId = 1;
//        Date updateTime = new Date();
//        long reqLogId= 12022L;
//        operatorVoiceCntService.paserReportDetail(res, userId, updateTime, reqLogId);
//    }

    public static void main(String[] args){
        OperatorRespDetail operatorRespDetail = new OperatorRespDetail(null, "003123", "测试数据");



    }


    private void updateOperatorLogState(long id, String eventName, String message, long respTime) {
        OperatorReqLog updateReqLog = new OperatorReqLog();
        updateReqLog.setId(id);
        updateReqLog.setRespTime(new Date(respTime));
        updateReqLog.setMessage(message);
        updateReqLog.setTaskState(OperatorStatusEnum.getStatusByKey(eventName.toLowerCase()));
        operatorReqLogService.updateById(updateReqLog);
    }

    private void updateUserAuthState(long userId, String state) {
        Map<String, Object> temp = new HashMap<>();
        temp.put("userId", userId);
        temp.put("phoneState", state);
        userAuthService.updateByUserId(temp);
    }

    private void writeMessage(HttpServletResponse response, int status, String content) {
        response.setStatus(status);
        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 公信宝运营商认证回调
     * @param body
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/api/moxie/gxbCallback.htm")
    public void gxbCallback(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) throws Exception {

        logger.info("--------------------------公信宝运营商回调开始--------------------------");
        Map<String, Object> respMap = new HashMap<>();
        respMap.put("retCode",2);
        respMap.put("retMsg","失败");
        if (response.getStatus() != 200){
            ServletUtils.writeToResponse(response, respMap);
            logger.info("--------------------------公信宝运营商回调接收失败且结束--------------------------");
            return;
        }
        JSONObject requestJson = JSON.parseObject(body);
        final String sequenceNo = requestJson.getString("sequenceNo");
        final String phone = requestJson.getString("phone");
        final String authJson = requestJson.getString("authJson");
        final String token = requestJson.getString("token");

        if (StringUtil.isBlank(sequenceNo) || StringUtil.isBlank(phone) || StringUtil.isBlank(authJson) || StringUtil.isBlank(token)){
            ServletUtils.writeToResponse(response, respMap);
            logger.info("--------------------------公信宝运营商回调数据推送为空且结束-------------------------phone ==>{},token ==>{}",phone,token);
            return;
        }
        Map<String,Object> params = new HashMap<>();
        params.put("phone",phone);
        final UserBaseInfo userBaseInfo = userBaseInfoService.findSelective(params);

        if (userBaseInfo != null){
            // 修改认证状态为认证中
            Map<String, Object> userAuth = new HashMap<String, Object>();
            userAuth.put("userId", userBaseInfo.getUserId());
            userAuth.put("phoneTime", DateUtil.getNow());
            userAuth.put("phoneState", UserAuthModel.STATE_ERTIFICATION);
            userAuthService.updateByUserId(userAuth);
            fixedThreadPool.execute(new Runnable(){
                                        public void run() {

                                            // 保存运营商认证记录
                                            Map<String, Object> temp = new HashMap<>();
                                            temp.put("taskId", sequenceNo);
                                            OperatorReqLog operatorReqLog = operatorReqLogService.findSelective(temp);
                                            if (operatorReqLog == null){
                                                operatorReqLog = new OperatorReqLog();
                                                operatorReqLog.setRespTime( DateUtil.getNow());
                                                operatorReqLog.setCreateTime( DateUtil.getNow());
                                                operatorReqLog.setUserId(userBaseInfo.getUserId());
                                                operatorReqLog.setTaskId(sequenceNo);
                                                operatorReqLog.setReqToken(token);
                                                operatorReqLogService.insert(operatorReqLog);
                                            }
                                            Long reqLogId = operatorReqLog.getId();

                                            Map<String, Object> userAuth = new HashMap<String, Object>();
                                            userAuth.put("userId", userBaseInfo.getUserId());
                                            userAuth.put("phoneTime", DateUtil.getNow());
                                            logger.info("--------------------------公信宝运营商回调保存原始数据--------------------------");
                                            int start = 0;
                                            int end = 0;
                                            try {
                                                // 保存原始数据
                                                OperatorRespDetail respDetail = operatorRespDetailService.getByTaskId(sequenceNo);
                                                if (respDetail == null) {
                                                    OperatorRespDetail operatorRespDetail = new OperatorRespDetail();
                                                    operatorRespDetail.setCreateTime(DateUtil.getNow());
                                                    operatorRespDetail.setTaskId(sequenceNo);
                                                    operatorRespDetail.setReqLogId(reqLogId);
                                                    operatorRespDetail.setOperatorData(authJson);
                                                    operatorRespDetailService.insert(operatorRespDetail);

                                                    logger.info("--------------------------公信宝运营商数据分类保存--------------------------");
                                                    // 将运营商数据分类保存
                                                    start = DateUtil.getNowTime();
                                                    operatorService.saveOperatorInfos(authJson, userBaseInfo.getUserId(), DateUtil.getNow(),phone,reqLogId);
                                                    end = DateUtil.getNowTime();
                                                    logger.info("保存userId " + userBaseInfo.getUserId() + "运营商数据，耗时" + (end - start) + "秒");
                                                } else {
                                                    Map<String, Object> updateMap = new HashMap<>();
                                                    updateMap.put("id", respDetail.getId());
                                                    updateMap.put("operatorData", authJson);
                                                    operatorRespDetailService.updateSelective(updateMap);
                                                }

                                                // 插入调用外部数据接口费用表
                                                CallsOutSideFee callsOutSideFee = callsOutSideFeeService.getByTaskId(sequenceNo);
                                                if(callsOutSideFee == null) {
                                                    callsOutSideFee = new CallsOutSideFee(userBaseInfo.getUserId(), sequenceNo, CallsOutSideFeeConstant.CALLS_TYPE_OPERATOR, CallsOutSideFeeConstant.FEE_OPERATOR,CallsOutSideFeeConstant.CAST_TYPE_CONSUME,phone);
                                                    callsOutSideFeeService.insert(callsOutSideFee);
                                                }

                                                logger.info("用户 "+userBaseInfo.getUserId()+"： 运营商原始数据回调模式获取成功,准备拉取运营商报告");
                                                String  gxbPullAllReportUrl = Global.getValue("gxb_pull_all_report_url");
                                                String appId = Global.getValue("gxb_appid");
                                                String appSecret = Global.getValue("gxb_appsecret");
                                                long timestamp = System.currentTimeMillis();
                                                String sign = DigestUtils.md5Hex(String.format("%s%s%s", appId, appSecret, timestamp));
                                                gxbPullAllReportUrl = gxbPullAllReportUrl +token +"?appId="+appId+"&timestamp="+timestamp+"&sign="+sign;
                                                String reportStr = HttpRestUtils.getRequest(gxbPullAllReportUrl,null);
                                                JSONObject reportJson = JSONObject.parseObject(reportStr);
                                                if (reportJson != null && "1".equals(reportJson.getString("retCode"))){
                                                    String data = reportJson.getString("data");
                                                    String authResult = null;
                                                    if (StringUtil.isNotBlank(data)){
                                                        authResult = JSONObject.parseObject(data).getString("authResult");
                                                    }

                                                    if (authResult == null){
                                                        userAuth.put("phoneState", UserAuthModel.STATE_NOT_CERTIFIED);
                                                        userAuthService.updateByUserId(userAuth);
                                                        logger.info("--------------------------公信宝运运营商报告为空且结束-------------------------phone ==>"+phone);
                                                        return;
                                                    }

                                                    // 保存运营商报告
                                                    OperatorReport oldReport = operatorReportService.getByTaskId(sequenceNo);
                                                    if (oldReport == null) {
                                                        OperatorReport operatorReport = new OperatorReport(userBaseInfo.getUserId(), reqLogId, sequenceNo, authResult);
                                                        operatorReportService.insert(operatorReport);

                                                        start = DateUtil.getNowTime();
                                                        operatorVoiceCntService.paserReportDetail(authResult, userBaseInfo.getUserId(), DateUtil.getNow(), reqLogId);
                                                        operatorVoiceCntService.lastContactTime(userBaseInfo.getUserId(), reqLogId);
                                                        end = DateUtil.getNowTime();
                                                        logger.info("保存userId " + userBaseInfo.getUserId() + "运营商报告，详情统计，耗时" + (end - start) + "秒");
                                                    } else {
                                                        Map<String, Object> updateMap = new HashMap<>();
                                                        updateMap.put("id", oldReport.getId());
                                                        updateMap.put("report", authResult);
                                                        updateMap.put("gmtModified", DateUtil.getNow());
                                                        operatorReportService.updateSelective(updateMap);
                                                    }
                                                }
                                                // 运营商认证完成
                                                userAuth.put("phoneState", UserAuthModel.STATE_VERIFIED);
                                                userAuthService.updateByUserId(userAuth);
                                                logger.info("--------------------------公信宝运营商认证完成--------------------------");
                                            } catch (Exception e){
                                                // 异常时 运营商认证修改为 未认证
                                                userAuth.put("phoneState", UserAuthModel.STATE_NOT_CERTIFIED);
                                                userAuthService.updateByUserId(userAuth);
                                                logger.info("--------------------------公信宝运营商认证异常--------------------------");
                                            }
                                        }
            });
        }
        logger.info("--------------------------公信宝运营商回调结束--------------------------");
        respMap.put("retCode",1);
        respMap.put("retMsg","成功");
        ServletUtils.writeToResponse(response, respMap);
    }
}

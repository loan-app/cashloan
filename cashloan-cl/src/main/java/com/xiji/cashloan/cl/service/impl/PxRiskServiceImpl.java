package com.xiji.cashloan.cl.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.xiji.cashloan.cl.domain.*;
import com.xiji.cashloan.cl.mapper.*;
import com.xiji.cashloan.cl.service.PxRiskService;
import com.xiji.cashloan.cl.util.CallsOutSideFeeConstant;
import com.xiji.cashloan.cl.util.paixu.RiskApiUtil;
import com.xiji.cashloan.cl.util.weijifen.Gzip;
import com.xiji.cashloan.cl.util.weijifen.RSASign;
import com.xiji.cashloan.cl.util.weijifen.RiskRequestDto;
import com.xiji.cashloan.cl.util.weijifen.TestConstants;
import com.xiji.cashloan.cl.util.xinyan.UUIDGenerator;
import com.xiji.cashloan.core.common.util.DateUtil;
import com.xiji.cashloan.core.common.util.ShardTableUtil;
import com.xiji.cashloan.core.common.util.StringUtil;
import com.xiji.cashloan.core.domain.Borrow;
import com.xiji.cashloan.core.domain.User;
import com.xiji.cashloan.core.domain.UserBaseInfo;
import com.xiji.cashloan.core.mapper.UserBaseInfoMapper;
import com.xiji.cashloan.core.mapper.UserMapper;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service("pxRiskService")
public class PxRiskServiceImpl implements PxRiskService {

    public static final Logger logger = LoggerFactory.getLogger(PxRiskServiceImpl.class);

    @Resource
    private UserBaseInfoMapper userBaseInfoMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private BorrowOperatorLogMapper borrowOperatorLogMapper;
    @Resource
    private OperatorReportMapper operatorReportMapper;
    @Resource
    private OperatorRespDetailMapper operatorRespDetailMapper;
    @Resource
    private OperatorReqLogMapper operatorReqLogMapper;

    @Resource
    private PxModelMapper pxModelMapper;

    @Resource
    private PxReqLogMapper pxReqLogMapper;

    @Resource
    private CallsOutSideFeeMapper callsOutSideFeeMapper;

    @Resource
    private UserEquipmentInfoMapper userEquipmentInfoMapper;

    @Resource
    private UserContactsMapper userContactsMapper;

    @Resource
    private UserEmerContactsMapper userEmerContactsMapper;


    private static String weijifen_id="10022";
    @Override
    public double getWjfScore(Borrow borrow) {
        double i = -1d;
        UserBaseInfo userBaseinfo = userBaseInfoMapper.findByUserId(borrow.getUserId());
        if (userBaseinfo == null) {
            logger.error("查询用户userId：" + userBaseinfo.getUserId() + ",用户不存在");
            return i;
        }
        User user = userMapper.findByPrimary(borrow.getUserId());

        Date createDate = DateUtil.getNow();
        PxReqLog log = new PxReqLog();
        log.setUserId(borrow.getUserId());
        log.setBorrowId(borrow.getId());
        log.setCreateTime(createDate);
        // type-2  weijifen
        log.setType(2);
        log.setIsFee(0);
        String requestId = UUIDGenerator.getUUID();
        log.setRequestId(requestId);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectionPool(new ConnectionPool(20, 1, TimeUnit.HOURS));
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);
        OkHttpClient client = builder.build();
        String url="https://risk.hancrosskeji.com/risk/loan";
        RiskRequestDto requestDto = new RiskRequestDto();
        requestDto.setAppId(weijifen_id);
        requestDto.setUserName(userBaseinfo.getRealName());
        requestDto.setApplyId(borrow.getId().toString());
        requestDto.setIdcard(userBaseinfo.getIdNo());
        requestDto.setMobile(userBaseinfo.getPhone());
        requestDto.setApplyTime(DateUtil.dateStr4(borrow.getCreateTime()));
        requestDto.setTimestamp(System.currentTimeMillis());
        requestDto.setCallback("<callback url>");

        JSONObject dataObj = new JSONObject();
        //获取用户的运营商原始数据
        BorrowOperatorLog borrowOperatorLog = borrowOperatorLogMapper.findByBorrowId(borrow.getId());
        if(borrowOperatorLog == null) {
            logger.error("借款订单Id：" + borrow.getId() + "对应运营商数据不存在");
            return i;
        }
        //运营商原始数据
        OperatorReqLog operatorReqLog = operatorReqLogMapper.findByPrimary(borrowOperatorLog.getReqLogId());
        if(operatorReqLog == null) {
            logger.error("借款订单Id：" + borrow.getId() + "运营商请求记录不存在");
            return i;
        }
        OperatorRespDetail operatorRespDetail = operatorRespDetailMapper.getByTaskId(operatorReqLog.getTaskId());
        //运营商报告
        OperatorReport operatorReport = operatorReportMapper.getOperatorReport(borrow.getId());
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("rawdata",operatorRespDetail.getOperatorData());
        jsonObject2.put("report",operatorReport.getReport());


        //  dataObj.put("rawdata", operatorRespDetail.getOperatorData());
        //获取用户的运营商报告数据
        //  dataObj.put("moxie", operatorReport.getReport());

        dataObj.put("moxie",jsonObject2);


        requestDto.setData(dataObj.toJSONString());
        try {
            requestDto = RSASign.sign(requestDto, TestConstants.PRIVATE_KEY);
            SerializeConfig config = new SerializeConfig(); // 生产环境中，config要做singleton处理，要不然会存在性能问题
            config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
            String json = JSON.toJSONString(requestDto, config);

            //使用gzip压缩json数据
            String compressJson = Gzip.compress(json);

            RequestBody body = RequestBody.create(MediaType.get("application/json"), compressJson);
            Request request = new Request.Builder().url(url).post(body).build();
            Response response = client.newCall(request).execute();
            JSONObject jsonObject = JSONObject.parseObject(response.body().string());
            if(jsonObject!=null){
                log.setReturnCode(jsonObject.getString("code"));
                log.setReturnInfo(jsonObject.toJSONString());
                log.setRespTime(new Date());
            }
            if(jsonObject.getString("data")!=null){
                log.setIsFee(1);
                //保存模型分
                PxModel pxModel = new PxModel();
                pxModel.setCreateTime(new Date());
                pxModel.setRequestId(requestId);
                pxModel.setScore(jsonObject.getJSONObject("data").getDouble("score"));
                pxModel.setUserId(borrow.getUserId());
                pxModel.setBorrowId(borrow.getId());
                pxModelMapper.save(pxModel);
                String data1 = jsonObject.getString("data");
                JSONObject jsonObject1 = JSONObject.parseObject(data1);
                String score = jsonObject1.getString("score");
               i =Double.parseDouble(score);
                //插入收费记录表
                CallsOutSideFee callsOutSideFee = new CallsOutSideFee(userBaseinfo.getUserId(),operatorReqLog.getTaskId(), CallsOutSideFeeConstant.CALLS_TYPE_WEIJIFEN_MODEL,
                        CallsOutSideFeeConstant.FEE_WEIJIFEN_MODEL, CallsOutSideFeeConstant.CAST_TYPE_CONSUME, userBaseinfo.getPhone());
                callsOutSideFeeMapper.save(callsOutSideFee);
            }else {
             return i;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }



    @Override
    public double getPxScore(Borrow borrow) {
        double i = -1d;
        UserBaseInfo userBaseinfo = userBaseInfoMapper.findByUserId(borrow.getUserId());
        if (userBaseinfo == null) {
            logger.error("查询用户userId：" + userBaseinfo.getUserId() + ",用户不存在");
            return i;
        }
        User user = userMapper.findByPrimary(borrow.getUserId());

        Date createDate = DateUtil.getNow();
        PxReqLog log = new PxReqLog();
        log.setUserId(borrow.getUserId());
        log.setBorrowId(borrow.getId());
        log.setCreateTime(createDate);
        // type-1 模型
        log.setType(1);
        log.setIsFee(0);
        String requestId = UUIDGenerator.getUUID();
        log.setRequestId(requestId);


        RiskApiUtil riskApiUtil = new RiskApiUtil();
        JSONObject dataObj = new JSONObject();

        //与指迷模型调用类似,代码可以优化
        //通讯录信息
        JSONArray contacts = new JSONArray();
        // 分表
        String tableName = ShardTableUtil.generateTableNameById("cl_user_contacts", borrow.getUserId(), 30000);
        int countTable = userContactsMapper.countTable(tableName);
        if (countTable == 0) {
            userContactsMapper.createTable(tableName);
        }
        Map<String, Object> params = new HashMap<>();
        params.put("userId", borrow.getUserId());
        List<UserContacts> userContactses = userContactsMapper.listShardSelective(tableName, params);
        for (UserContacts userContact : userContactses) {
            JSONObject c = new JSONObject();
            c.put("contact_name", userContact.getName());
            c.put("contact_phone", userContact.getPhone());
            c.put("update_time", DateUtil.dateStr4(user.getRegistTime()));
            contacts.add(c);
        }
        dataObj.put("contactStr", contacts.toJSONString());

        //紧急联系人
        JSONArray emergency = new JSONArray();
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("userId", borrow.getUserId());
        List<UserEmerContacts> userEmerContactses = userEmerContactsMapper.listSelective(queryMap);
        if(userEmerContactses == null || userEmerContactses.size() != 2) {
            logger.error("用户userId：" + userBaseinfo.getUserId() + "紧急联系人未完善");
            return i;
        }
        for (UserEmerContacts userEmerContact : userEmerContactses) {
            JSONObject e = new JSONObject();
            e.put("name", userEmerContact.getName());
            e.put("phone", userEmerContact.getPhone());
            e.put("relation", userEmerContact.getRelation());
            emergency.add(e);
        }
        dataObj.put("emergencyContactStr", emergency.toJSONString());

        //获取用户的运营商原始数据
        BorrowOperatorLog borrowOperatorLog = borrowOperatorLogMapper.findByBorrowId(borrow.getId());
        if(borrowOperatorLog == null) {
            logger.error("借款订单Id：" + borrow.getId() + "对应运营商数据不存在");
            return i;
        }

        //运营商原始数据
        OperatorReqLog operatorReqLog = operatorReqLogMapper.findByPrimary(borrowOperatorLog.getReqLogId());
        if(operatorReqLog == null) {
            logger.error("借款订单Id：" + borrow.getId() + "运营商请求记录不存在");
            return i;
        }
        OperatorRespDetail operatorRespDetail = operatorRespDetailMapper.getByTaskId(operatorReqLog.getTaskId());

        //运营商报告
        OperatorReport operatorReport = operatorReportMapper.getOperatorReport(borrow.getId());
        dataObj.put("yys_raw", operatorRespDetail.getOperatorData());
        //获取用户的运营商报告数据
        dataObj.put("yys_report", operatorReport.getReport());


        //设备
        String deviceType = "android";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", borrow.getUserId());
        UserEquipmentInfo userEquipmentInfo = userEquipmentInfoMapper.findSelective(paramMap);
        if(userEquipmentInfo != null) {
            if (StringUtil.isNotBlank(userEquipmentInfo.getPhoneType()) && userEquipmentInfo.getPhoneType().toLowerCase().contains("phone")) {
                deviceType = "ios";
            }
        }
        dataObj.put("deviceType", deviceType);

        //地址信息
//        JSONObject present = new JSONObject();
//        present.put("province", "");
//        present.put("city", "");
//        present.put("liveAddr", "");
        dataObj.put("present", StringUtil.EMPTY);
        //性别
        dataObj.put("gender", userBaseinfo.getSex());
        //申请时间
        dataObj.put("applyTime", DateUtil.dateStr4(borrow.getCreateTime()));


        JSONObject returnJson = riskApiUtil.mode3("gxb", userBaseinfo.getRealName(), userBaseinfo.getIdNo(), userBaseinfo.getPhone(), requestId, dataObj.toJSONString());
        logger.info("借款订单" + borrow.getId() + "排序返回数据:" + returnJson);
        try {
            if (returnJson != null) {
                log.setReturnCode(returnJson.getString("errCode"));
                log.setReturnInfo(returnJson.toJSONString());
                log.setRespTime(new Date());
                if (returnJson.getBoolean("success")) {
                    log.setIsFee(1);
                    //保存模型分
                    PxModel pxModel = new PxModel();
                    pxModel.setCreateTime(new Date());
                    pxModel.setRequestId(requestId);
                    pxModel.setScore(returnJson.getJSONObject("body").getDouble("score"));
                    pxModel.setUserId(borrow.getUserId());
                    pxModel.setBorrowId(borrow.getId());
                    pxModelMapper.save(pxModel);
                    //插入收费记录表
                    CallsOutSideFee callsOutSideFee = new CallsOutSideFee(userBaseinfo.getUserId(), returnJson.getString("request_id"), CallsOutSideFeeConstant.CALLS_TYPE_PAIXU_MODEL,
                            CallsOutSideFeeConstant.FEE_PAIXU_MODEL, CallsOutSideFeeConstant.CAST_TYPE_CONSUME, userBaseinfo.getPhone());
                    callsOutSideFeeMapper.save(callsOutSideFee);
                    if(returnJson.getJSONObject("body") != null) {
                        i = returnJson.getJSONObject("body").getDouble("score");
                    }
                }
            } else {
                logger.error("用户" + userBaseinfo.getUserId() + "，请求排序响应数据为空，result:" + returnJson);
            }
        } catch (Exception e) {
            logger.error("请求排序模型数据异常", e);
        }
        pxReqLogMapper.save(log);
        return i;
    }



}

















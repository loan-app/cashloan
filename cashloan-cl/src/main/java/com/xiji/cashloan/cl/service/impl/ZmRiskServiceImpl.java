package com.xiji.cashloan.cl.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xiji.cashloan.cl.domain.*;
import com.xiji.cashloan.cl.mapper.*;
import com.xiji.cashloan.cl.model.zhimi.ZhimiConstant;
import com.xiji.cashloan.cl.service.ZmRiskService;
import com.xiji.cashloan.cl.util.CallsOutSideFeeConstant;
import com.xiji.cashloan.cl.util.magic.GzipUtil;
import com.xiji.cashloan.cl.util.token.HttpRestUtils;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.util.DateUtil;
import com.xiji.cashloan.core.common.util.ShardTableUtil;
import com.xiji.cashloan.core.common.util.StringUtil;
import com.xiji.cashloan.core.domain.Borrow;
import com.xiji.cashloan.core.domain.User;
import com.xiji.cashloan.core.domain.UserBaseInfo;
import com.xiji.cashloan.core.mapper.UserBaseInfoMapper;
import com.xiji.cashloan.core.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by szb on 19/5/5.
 */
@Service
public class ZmRiskServiceImpl implements ZmRiskService {
    public static final Logger logger = LoggerFactory.getLogger(ZmRiskServiceImpl.class);

    @Resource
    private UserBaseInfoMapper userBaseInfoMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserEmerContactsMapper userEmerContactsMapper;
    @Resource
    private BorrowOperatorLogMapper borrowOperatorLogMapper;
    @Resource
    private OperatorReportMapper operatorReportMapper;
    @Resource
    private OperatorRespDetailMapper operatorRespDetailMapper;
    @Resource
    private OperatorReqLogMapper operatorReqLogMapper;
    @Resource
    private UserContactsMapper userContactsMapper;
    @Resource
    private ZmReqLogMapper zmReqLogMapper;
    @Resource
    private CallsOutSideFeeMapper callsOutSideFeeMapper;
    @Resource
    private ZmModelMapper zmModelMapper;

    @Override
    public double getScore(Borrow borrow, boolean isAgain) {
        double i = -1d;
        UserBaseInfo userBaseinfo = userBaseInfoMapper.findByUserId(borrow.getUserId());
        if (userBaseinfo == null) {
            logger.error("查询用户userId：" + userBaseinfo.getUserId() + ",用户不存在");
            return i;
        }

        Date createDate = DateUtil.getNow();
        ZmReqLog log = new ZmReqLog();
        log.setUserId(borrow.getUserId());
        log.setBorrowId(borrow.getId());
        log.setCreateTime(createDate);
        log.setType(ZhimiConstant.MODEL_TYPE);
        log.setIsFee(0);

        User user = userMapper.findByPrimary(borrow.getUserId());
        String API_HOST = Global.getValue("zm_model_url");

        JSONObject param = new JSONObject();

        param.put("first_loan", isAgain ? 1 : 0);
        if(user.getChannelId() != null && user.getChannelId() != 0) {
            param.put("channel", user.getChannelId().toString());
        }
        param.put("apply_time", DateUtil.dateStr4(borrow.getCreateTime()));
        param.put("mobile", userBaseinfo.getPhone());
        param.put("name", userBaseinfo.getRealName());
        param.put("idcard", userBaseinfo.getIdNo());

        //紧急联系人
        JSONArray eContacts = new JSONArray();
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("userId", borrow.getUserId());
        List<UserEmerContacts> userEmerContactses = userEmerContactsMapper.listSelective(queryMap);
        if(userEmerContactses == null || userEmerContactses.size() != 2) {
            logger.error("用户userId：" + userBaseinfo.getUserId() + "紧急联系人未完善");
            return i;
        }
        for (UserEmerContacts userEmerContact : userEmerContactses) {
            JSONObject eContact = new JSONObject();
            eContact.put("contact_name", userEmerContact.getName());
            eContact.put("contact_phone", userEmerContact.getPhone());
            eContacts.add(eContact);
        }
        param.put("e_contacts", eContacts);

        //运营商数据
        JSONObject carrierData = new JSONObject();
        //模型名称选择
        String operatorSelect = Global.getValue("operator_select");
        param.put("model_name", Global.getValue("zm_model_name_mx"));

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


        if("gxb".equals(operatorSelect)) {
            param.put("model_name", Global.getValue("zm_model_name_gxb"));
            carrierData.put("gxb_raw", operatorRespDetail.getOperatorData());
            carrierData.put("gxb_report", operatorReport.getReport());
        } else {
            carrierData.put("mx_raw", operatorRespDetail.getOperatorData());
            carrierData.put("mx_report", operatorReport.getReport());
        }

        param.put("carrier_data", carrierData);

        //通讯录
        JSONArray contactArray = new JSONArray();
        // 分表
        String tableName = ShardTableUtil.generateTableNameById("cl_user_contacts", borrow.getUserId(), 30000);
        int countTable = userContactsMapper.countTable(tableName);
        if (countTable == 0) {
            userContactsMapper.createTable(tableName);
        }
        Map<String,Object> params = new HashMap<>();
        params.put("userId", borrow.getUserId());
        List<UserContacts> userContactses = userContactsMapper.listShardSelective(tableName, params);
        for (UserContacts userContact : userContactses) {
            JSONObject contactJson = new JSONObject();
            contactJson.put("contact_name", userContact.getName());
            contactJson.put("contact_phone", userContact.getPhone());
            contactJson.put("update_time", DateUtil.dateStr4(user.getRegistTime()));
            contactArray.add(contactJson);
        }
        param.put("contact", contactArray);


        try {
            String data = JSON.toJSONString(param, SerializerFeature.WriteMapNullValue);
            String result = HttpRestUtils.zmRequest(API_HOST, data);
            logger.info("借款订单" + borrow.getId() + "指迷返回数据: " + result);
            if (StringUtil.isNotBlank(result)) {
                JSONObject returnJson = JSONObject.parseObject(result);
                log.setReturnCode(returnJson.getString("return_code"));
                log.setReturnInfo(returnJson.getString("return_info"));
                log.setRespTime(new Date());
                log.setRequestId(returnJson.getString("request_id"));
                if (returnJson.getInteger("return_code") == 0) {
                    log.setIsFee(1);
                    //保存模型分
                    ZmModel zmModel = new ZmModel();
                    zmModel.setCreateTime(new Date());
                    zmModel.setRequestId(returnJson.getString("request_id"));
                    zmModel.setScore(returnJson.getDouble("score"));
                    zmModel.setUserId(borrow.getUserId());
                    zmModel.setBorrowId(borrow.getId());
                    zmModelMapper.save(zmModel);
                    //插入收费记录表
                    CallsOutSideFee callsOutSideFee = new CallsOutSideFee(userBaseinfo.getUserId(), returnJson.getString("request_id"), CallsOutSideFeeConstant.CALLS_TYPE_ZHIMI_MODEL,
                            CallsOutSideFeeConstant.FEE_ZHIMI_MODEL, CallsOutSideFeeConstant.CAST_TYPE_CONSUME, userBaseinfo.getPhone());
                    callsOutSideFeeMapper.save(callsOutSideFee);
                    i = returnJson.getDouble("score");
                }
            } else {
                logger.error("用户" + userBaseinfo.getUserId() + "，请求指迷响应数据为空，result:" + result);
            }
        } catch (Exception e) {
            logger.error("请求指迷模型数据异常", e);
        }
        zmReqLogMapper.save(log);
        return i;
    }
}

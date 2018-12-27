package com.xiji.cashloan.cl.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiji.cashloan.cl.domain.*;
import com.xiji.cashloan.cl.mapper.*;
import com.xiji.cashloan.cl.model.PinganCallDetailsModel;
import com.xiji.cashloan.cl.service.PinganRiskService;
import com.xiji.cashloan.cl.util.CallsOutSideFeeConstant;
import com.xiji.cashloan.cl.util.token.HttpRestUtils;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.util.DateUtil;
import com.xiji.cashloan.core.common.util.ShardTableUtil;
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
import java.util.List;
import java.util.Map;

/**
 * 凭安风控
 * Created by szb on 18/12/26.
 */
@Service
public class PinganRiskServiceImpl implements PinganRiskService {

    public static final Logger logger = LoggerFactory.getLogger(PinganRiskServiceImpl.class);

    private static final int CALL_DETAIL_TYPE = 0;

    @Resource
    private UserBaseInfoMapper userBaseInfoMapper;
    @Resource
    private CallsOutSideFeeMapper callsOutSideFeeMapper;
    @Resource
    private PinganReqLogMapper pinganReqLogMapper;
    @Resource
    private PinganGrayscaleMapper pinganGrayscaleMapper;
    @Resource
    private OperatorReqLogMapper operatorReqLogMapper;
    @Resource
    private OperatorVoiceMapper operatorVoiceMapper;

    @Override
    public int queryGrayscaleStat(Borrow borrow) {
        int i = 0;
        UserBaseInfo userBaseinfo = userBaseInfoMapper.findByUserId(borrow.getUserId());
        if (userBaseinfo == null) {
            logger.error("查询用户userId：" + userBaseinfo.getUserId() + ",用户不存在");
            return i;
        }
        Date createDate = DateUtil.getNow();
        Long userId = userBaseinfo.getUserId();
        PinganReqLog log = new PinganReqLog();
        log.setUserId(userId);
        log.setBorrowId(borrow.getId());
        log.setCreateTime(createDate);
        log.setType(1);
        log.setIsFee(0);

        String vkey = Global.getValue("pingan_vkey");
        String API_HOST = Global.getValue("pingan_grayscale_stat_url");
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", userBaseinfo.getRealName());
        paramMap.put("phone", userBaseinfo.getPhone());
        paramMap.put("idcard", userBaseinfo.getIdNo());
        paramMap.put("vkey", vkey);
        paramMap.put("call_detail_type", CALL_DETAIL_TYPE);

        //查询用户通话详单
        OperatorReqLog operatorReqLog = operatorReqLogMapper.queryLastReqLog(userId);

        // 分表
        String tableName = ShardTableUtil.generateTableNameById("cl_operator_voice", userId, 30000);
        int countTable = operatorVoiceMapper.countTable(tableName);
        if (countTable == 0) {
            operatorVoiceMapper.createTable(tableName);
        }
        List<PinganCallDetailsModel> details = operatorVoiceMapper.queryPinganVoiceDetail(tableName, operatorReqLog.getId());

        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(details));
        paramMap.put("call_details", jsonArray.toJSONString());
        try {
            logger.debug("凭安请求数据:" + JSON.toJSONString(paramMap));
            String result = HttpRestUtils.postForm(API_HOST, null, paramMap);
            logger.debug("凭安返回数据:" + result);
            if (StringUtil.isNotBlank(result)) {
                JSONObject resJson = JSONObject.parseObject(result);
                log.setRespCode(String.valueOf(resJson.get("result")));
                log.setIsSuccess(resJson.getInteger("result") == 200 ? 1 : 0);
                log.setRespMsg(resJson.getString("message"));
                Date respTime = DateUtil.getNow();
                log.setRespTime(respTime);
                if (resJson.getInteger("result") == 200) {
                    log.setIsFee(1);
                    //插入收费记录表
                    CallsOutSideFee callsOutSideFee = new CallsOutSideFee(userId, StringUtil.EMPTY, CallsOutSideFeeConstant.CALLS_TYPE_GRAYSCALE_STAT,
                            CallsOutSideFeeConstant.FEE_GRAYSCALE_STAT, CallsOutSideFeeConstant.CAST_TYPE_CONSUME, userBaseinfo.getPhone());
                    callsOutSideFeeMapper.save(callsOutSideFee);
                    i = saveData(resJson.getString("data"), borrow.getUserId(), borrow.getId());
                } else {
                    logger.error("用户" + userBaseinfo.getRealName() + "，请求凭安染黑度统计响应错误, result:" + result);
                }
            } else {
                logger.error("用户" + userBaseinfo.getRealName() + "，请求凭安染黑度统计同步响应数据为空，result:" + result);
            }
        } catch (Exception e) {
            logger.error("用户" + userBaseinfo.getRealName() + "，请求凭安染黑度统计出现异常", e);
        }
        pinganReqLogMapper.save(log);
        return i;
    }

    private int saveData(String data, Long userId, Long borrowId) {
        PinganGrayscale pinganGrayscale = new PinganGrayscale();
        pinganGrayscale.setUserId(userId);
        pinganGrayscale.setBorrowId(borrowId);
        pinganGrayscale.setData(data);
        pinganGrayscale.setGmtCreate(DateUtil.getNow());
        pinganGrayscale.setGmtModified(DateUtil.getNow());

        int i = pinganGrayscaleMapper.save(pinganGrayscale);
        return i;
    }
}

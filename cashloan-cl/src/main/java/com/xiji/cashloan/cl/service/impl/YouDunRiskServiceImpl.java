package com.xiji.cashloan.cl.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xiji.cashloan.cl.domain.*;
import com.xiji.cashloan.cl.mapper.CallsOutSideFeeMapper;
import com.xiji.cashloan.cl.mapper.YouDunReqLogMapper;
import com.xiji.cashloan.cl.mapper.YouDunRiskReportMapper;
import com.xiji.cashloan.cl.service.YouDunRiskService;
import com.xiji.cashloan.cl.util.CallsOutSideFeeConstant;
import com.xiji.cashloan.cl.util.xinyan.UUIDGenerator;
import com.xiji.cashloan.cl.util.youdun.YouDunDataUtil;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.util.DateUtil;
import com.xiji.cashloan.core.domain.Borrow;
import com.xiji.cashloan.core.domain.UserBaseInfo;
import com.xiji.cashloan.core.mapper.UserBaseInfoMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;


import static com.xiji.cashloan.cl.util.youdun.YouDunDataUtil.doHttpRequest;


/**
 *  有盾查询
 * Created by szb on 18/12/25.
 */
@Service("YouDunRiskServiceImpl")
public class YouDunRiskServiceImpl implements YouDunRiskService {

    public static final Logger logger = LoggerFactory.getLogger(YouDunRiskServiceImpl.class);

    @Resource
    private CallsOutSideFeeMapper callsOutSideFeeMapper;

    @Resource
    private UserBaseInfoMapper userBaseInfoMapper;

    @Resource
    private YouDunRiskReportMapper youDunRiskReportMapper;

    @Resource
    private YouDunReqLogMapper youDunReqLogMapper;

    //用户画像类型
    private static int RISK_TYPE = 1;

    @Override
    public int multiPoint(Borrow borrow) {

        int i = 0;
        UserBaseInfo userBaseinfo = userBaseInfoMapper.findByUserId(borrow.getUserId());
        if (userBaseinfo == null) {
            logger.error("查询用户userId：" + userBaseinfo.getUserId() + ",用户不存在");
            return i;
        }
        Date createDate = DateUtil.getNow();
        Long userId = userBaseinfo.getUserId();
        YouDunReqLog reqLog = new YouDunReqLog();
        reqLog.setUserId(userId);
        reqLog.setBorrowId(borrow.getId());
        reqLog.setCreateTime(createDate);
        reqLog.setType(RISK_TYPE);
        reqLog.setIsFee(0);

        /** 1、商户密钥 **/
        String PUB_KEY = Global.getValue("youdun_pub_key");
        /** 2、产品编码 **/
        String PRODUCT_CODE = Global.getValue("youdun_product_code");
        /** 3、请求地址 **/
        String dataservice_url = Global.getValue("youdun_risk_url");
        /** 4、安全密钥 **/
        String secretkey = Global.getValue("youdun_secret_key");
        /**
         * 商户订单号：由商户自定义传入的唯一且不大于32位的字符串
         */
        String out_order_id =UUIDGenerator.getUUID();


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id_no", userBaseinfo.getIdNo());
        jsonObject.put("id_name",userBaseinfo.getRealName());
//        jsonObject.put("id_no", "330281199311011337");
//        jsonObject.put("id_name","刘梁");
        try {
            //加签
            String signature = YouDunDataUtil.getMd5(jsonObject, secretkey);
            String url = String.format(dataservice_url, PUB_KEY, PRODUCT_CODE, out_order_id, signature);
            JSONObject resJson =doHttpRequest(url,jsonObject);

            String result = resJson.toString();
            if (resJson!=null) {
                //获取响应头
                JSONObject header = resJson.getJSONObject("header");
                reqLog.setRespCode(String.valueOf(header.get("ret_code")));
                reqLog.setIsSuccess("000000".equals(header.getString("ret_code"))? 1 : 0);
                reqLog.setRespMsg(header.getString("ret_msg"));
                Date respTime = DateUtil.getNow();
                reqLog.setRespTime(respTime);
                if ("000000".equals(header.getString("ret_code"))) {
                    reqLog.setIsSuccess(1);
                    //获取相应体
                    JSONObject body = resJson.getJSONObject("body");
                    reqLog.setIsFee(1);
                    reqLog.setFlowId(body.getString("ud_order_no"));
                    //插入收费记录表
                    CallsOutSideFee callsOutSideFee = new CallsOutSideFee(userId, body.getString("ud_order_no"), CallsOutSideFeeConstant.CALLS_TYPE_YOUDUN_RISK,
                            CallsOutSideFeeConstant.FEE_YOUDUN_RISK, CallsOutSideFeeConstant.CAST_TYPE_CONSUME, userBaseinfo.getPhone());
                    callsOutSideFeeMapper.save(callsOutSideFee);
                    //有盾风险评估数据存储
                    i = saveRiskReport(resJson.getString("body"), borrow.getUserId(), body.getString("ud_order_no"), borrow.getId());
                } else {
                    logger.error("用户" + userBaseinfo.getRealName() + "，请求有盾用户画像响应错误, result:" + result);
                }
            } else {
                logger.error("用户" + userBaseinfo.getRealName() + "，请求有盾用户画像同步响应数据为空，result:" + result);
            }
        } catch (Exception e) {
            logger.error("用户" + userBaseinfo.getRealName() + "，请求有盾用户画像出现异常", e);
        }
        //有盾请求记录数据存储
        youDunReqLogMapper.save(reqLog);
        return i;
    }

    private int saveRiskReport(String data, Long userId, String flowId, Long borrowId) {
        YouDunRiskReport youDunRiskReport = new YouDunRiskReport();
        youDunRiskReport.setUserId(userId);
        youDunRiskReport.setFlowId(flowId);
        youDunRiskReport.setBorrowId(borrowId);
        youDunRiskReport.setData(data);
        youDunRiskReport.setGmtCreate(DateUtil.getNow());
        youDunRiskReport.setGmtModified(DateUtil.getNow());
        int i = youDunRiskReportMapper.save(youDunRiskReport);
        return i;
    }

}

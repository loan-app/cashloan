package com.xiji.cashloan.cl.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiji.cashloan.cl.model.moxie.MxCreditRequest;
import com.xiji.cashloan.cl.service.MagicRiskService;
import com.xiji.cashloan.cl.util.CallsOutSideFeeConstant;
import com.xiji.cashloan.cl.util.magic.MagicRiskConstant;
import com.xiji.cashloan.cl.util.magic.MagicRiskUtils;
import com.xiji.cashloan.cl.util.magic.MoxieSignUtils;
import com.xiji.cashloan.cl.util.magic.ObjectConvertUtil;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.util.DateUtil;
import com.xiji.cashloan.core.common.util.StringUtil;
import com.xiji.cashloan.core.domain.Borrow;
import com.xiji.cashloan.core.domain.UserBaseInfo;
import com.xiji.cashloan.core.mapper.UserBaseInfoMapper;
import com.xiji.cashloan.rc.domain.TppBusiness;
import com.xiji.cashloan.cl.domain.*;
import com.xiji.cashloan.cl.domain.mozhang.*;
import com.xiji.cashloan.cl.mapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 魔蝎风控数据保存实现类
 * Created by szb on 18/12/1.
 */
@Service("magicRiskService")
public class MagicRiskServiceImpl implements MagicRiskService {

    public static final Logger logger = LoggerFactory.getLogger(MagicRiskServiceImpl.class);

    @Resource
    private MagicBasicMapper magicBasicMapper;

    @Resource
    private MagicBlackGrayMapper magicBlackGrayMapper;

    @Resource
    private MagicMobileContactMapper magicMobileContactMapper;

    @Resource
    private MagicIntimateContactMapper magicIntimateContactMapper;

    @Resource
    private MagicMultipointMapper magicMultipointMapper;

    @Resource
    private MagicMultipointQueriedAnalyzeMapper magicMultipointQueriedAnalyzeMapper;

    @Resource
    private MagicMultipointQueriedInfoMapper magicMultipointQueriedInfoMapper;

    @Resource
    private MagicUntrustedMapper magicUntrustedMapper;

    @Resource
    private MagicUntrustedDetailMapper magicUntrustedDetailMapper;

    @Resource
    private MagicSuspiciousIdcardMapper magicSuspiciousIdcardMapper;

    @Resource
    private MagicSuspiciousOtherNameMapper magicSuspiciousOtherNameMapper;

    @Resource
    private MagicSuspiciousOtherMobileMapper magicSuspiciousOtherMobileMapper;

    @Resource
    private MagicSuspiciousInformationSourceMapper magicSuspiciousInformationSourceMapper;

    @Resource
    private MagicSuspiciousMobileMapper magicSuspiciousMobileMapper;

    @Resource
    private MagicSuspiciousDeviceMapper magicSuspiciousDeviceMapper;

    @Resource
    private MagicSuspiciousOtherIdcardMapper magicSuspiciousOtherIdcardMapper;

    @Resource
    private MagicRiskQqGroupMapper magicRiskQqGroupMapper;

    @Resource
    private MagicRiskDeviceMapper magicRiskDeviceMapper;

    @Resource
    private MagicCreditCardOverdueMapper magicCreditCardOverdueMapper;

    @Resource
    private MagicLoanBehaviorAnalysisMapper magicLoanBehaviorAnalysisMapper;

    @Resource
    private MagicFundInfoMapper magicFundInfoMapper;

    @Resource
    private MagicBankInfoMapper magicBankInfoMapper;

    @Resource
    private UserBaseInfoMapper userBaseInfoMapper;

    @Resource
    private CallsOutSideFeeMapper callsOutSideFeeMapper;

    @Resource
    private MagicFraudulenceInfoMapper magicFraudulenceInfoMapper;

    @Resource
    private MagicReqDetailMapper magicReqDetailMapper;

    @Resource
    private MagicReqLogMapper magicReqLogMapper;

    @Override
    public int queryAntiFraud(Borrow borrow) {
        int i = 0;
        UserBaseInfo userBaseinfo = userBaseInfoMapper.findByUserId(borrow.getUserId());
        if(userBaseinfo == null) {
            logger.error("查询用户userId：" + userBaseinfo.getUserId() + ",用户不存在");
            return i;
        }
        Long userId = userBaseinfo.getUserId();
        MagicReqLog log = new MagicReqLog();
        log.setBorrowId(borrow.getId());
        log.setCreateTime(new Date());
        log.setUserId(borrow.getUserId());
        log.setType(CallsOutSideFeeConstant.CALLS_TYPE_ANTI_FRAUD);
        try {
            String privateKey = Global.getValue("mx_private_key");
            String apiUrl = Global.getValue("mx_risk_url");
            /** 请求参数 */
            Map<String, String> reqParams = getReqParams(MagicRiskConstant.METHOD_MAGICWAND2_ANTI_FRAUD);

            /** 业务参数 */
            Map<String, String> bizParams = new HashMap<>();
            bizParams.put("name", userBaseinfo.getRealName());
            bizParams.put("mobile", userBaseinfo.getPhone());
            bizParams.put("idcard", userBaseinfo.getIdNo());
            String bizContent = new ObjectMapper().writeValueAsString(bizParams);

            reqParams.put(MagicRiskConstant.REQ_BIZ_CONTENT, bizContent);

            //签名
            String sign = MoxieSignUtils.signSHA1WithRSA(reqParams, privateKey);

            reqParams.put(MagicRiskConstant.REQ_SIGN, sign);

            String getURL = MagicRiskUtils.getWholeGetURL(apiUrl, reqParams);
            String resContent = MxCreditRequest.get(getURL, null);

            if(StringUtil.isNotBlank(resContent)){
                JSONObject resJson = JSONObject.parseObject(resContent);
                String code = resJson.getString("code");
                if ("0000".equals(code)) {
                    JSONObject data = JSONObject.parseObject(resJson.getString("data"));
                    String transId = data.getString("trans_id");
                    log.setRespCode(code);
                    log.setRespTime(new Date());
                    log.setTransId(transId);

                    //插入收费记录表
                    Date createDate = DateUtil.getNow();
                    CallsOutSideFee callsOutSideFee = new CallsOutSideFee(userId, transId, CallsOutSideFeeConstant.CALLS_TYPE_ANTI_FRAUD, CallsOutSideFeeConstant.FEE_ANTI_FRAUD);
                    callsOutSideFeeMapper.save(callsOutSideFee);
                    //插入详情表
                    MagicReqDetail magicReqDetail = new MagicReqDetail(userId, transId, resJson.getString("data"), CallsOutSideFeeConstant.CALLS_TYPE_ANTI_FRAUD);
                    magicReqDetailMapper.save(magicReqDetail);
                    //法院失信
                    UntrustedInfoBean untrustedInfo = JSONObject.parseObject(data.getString("untrusted_info"), UntrustedInfoBean.class);
                    if (untrustedInfo != null) {
                        saveUntrustedInfo(untrustedInfo, userId, transId, createDate);
                    }
                    //身份证变更信息
                    SuspiciousIdcardBean suspiciousIdcard = JSONObject.parseObject(data.getString("suspicious_idcard"), SuspiciousIdcardBean.class);
                    if(suspiciousIdcard != null) {
                        saveSuspiciousIdcard(suspiciousIdcard, userId, transId, createDate);
                    }
                    //手机号变更信息
                    SuspiciousMobileBean suspiciousMobile = JSONObject.parseObject(data.getString("suspicious_mobile"), SuspiciousMobileBean.class);
                    if(suspiciousMobile != null) {
                        saveSuspiciousMobile(suspiciousMobile, userId, transId, createDate);
                    }
                    //设备变更信息
                    SuspiciousDeviceBean suspiciousDevice = JSONObject.parseObject(data.getString("suspicious_device"), SuspiciousDeviceBean.class);
                    if(suspiciousDevice != null) {
                        saveSuspiciousDevice(suspiciousDevice, userId, transId, createDate);
                    }
                    //QQ群风险信息
                    RiskQqgroupBean riskQqgroup = JSONObject.parseObject(data.getString("risk_qqgroup"), RiskQqgroupBean.class);
                    if(riskQqgroup != null) {
                        saveRiskQqgroup(riskQqgroup, userId, transId, createDate);
                    }
                    //欺诈风险名单
                    FraudulenceInfoBean fraudulenceInfo = JSONObject.parseObject(data.getString("fraudulence_info"), FraudulenceInfoBean.class);
                    if(fraudulenceInfo != null) {
                        saveFraudulenceInfo(fraudulenceInfo, userId, transId, createDate);
                    }
                    i = 1;
                } else {
                    log.setRespCode(code);
                    log.setRespTime(new Date());
                    log.setRespParams(resContent);
                }
            }

        } catch (Exception e) {
            logger.error("用户userId：" + userId + "魔杖2.0-反欺诈同步响应数据错误", e);
        }
        magicReqLogMapper.save(log);
        return i;
    }

    @Override
    public int queryPostLoad(Borrow borrow) {
        int i = 0;
        UserBaseInfo userBaseinfo = userBaseInfoMapper.findByUserId(borrow.getUserId());
        if(userBaseinfo == null) {
            logger.error("查询用户userId：" + userBaseinfo.getUserId() + ",用户不存在");
            return i;
        }
        Long userId = userBaseinfo.getUserId();
        MagicReqLog log = new MagicReqLog();
        log.setBorrowId(borrow.getId());
        log.setCreateTime(new Date());
        log.setUserId(borrow.getUserId());
        log.setType(CallsOutSideFeeConstant.CALLS_TYPE_POST_LOAD);
        try {
            String privateKey = Global.getValue("mx_private_key");
            String apiUrl = Global.getValue("mx_risk_url");
            /** 请求参数 */
            Map<String, String> reqParams = getReqParams(MagicRiskConstant.METHOD_MAGICWAND2_POST_LOAD);

            /** 业务参数 */
            Map<String, String> bizParams = new HashMap<>();
            bizParams.put("name", userBaseinfo.getRealName());
            bizParams.put("mobile", userBaseinfo.getPhone());
            bizParams.put("idcard", userBaseinfo.getIdNo());
            String bizContent = new ObjectMapper().writeValueAsString(bizParams);

            reqParams.put(MagicRiskConstant.REQ_BIZ_CONTENT, bizContent);

            //签名
            String sign = MoxieSignUtils.signSHA1WithRSA(reqParams, privateKey);

            reqParams.put(MagicRiskConstant.REQ_SIGN, sign);

            String getURL = MagicRiskUtils.getWholeGetURL(apiUrl, reqParams);
            String resContent = MxCreditRequest.get(getURL, null);
            if(StringUtil.isNotBlank(resContent)) {
                JSONObject resJson = JSONObject.parseObject(resContent);
                String code = resJson.getString("code");
                if ("0000".equals(code)) {
                    JSONObject data = JSONObject.parseObject(resJson.getString("data"));
                    String transId = data.getString("trans_id");

                    log.setRespCode(code);
                    log.setRespTime(new Date());
                    log.setTransId(transId);
                    Date createDate = DateUtil.getNow();
                    //插入详情表
                    MagicReqDetail magicReqDetail = new MagicReqDetail(userId, transId, resJson.getString("data"), CallsOutSideFeeConstant.CALLS_TYPE_POST_LOAD);
                    magicReqDetailMapper.save(magicReqDetail);
                    //插入收费记录表
                    CallsOutSideFee callsOutSideFee = new CallsOutSideFee(userId, transId, CallsOutSideFeeConstant.CALLS_TYPE_POST_LOAD, CallsOutSideFeeConstant.FEE_POST_LOAD);
                    callsOutSideFeeMapper.save(callsOutSideFee);
                    //保存数据
                    LoanBehaviorAnalysisBean loanBehaviorAnalysis = JSONObject.parseObject(data.getString("loan_behavior_analysis"), LoanBehaviorAnalysisBean.class);
                    if (loanBehaviorAnalysis != null) {
                        saveLoanBehaviorAnalysis(loanBehaviorAnalysis, userId, transId, createDate);
                    }
                    i = 1;
                } else {
                    log.setRespCode(code);
                    log.setRespTime(new Date());
                    log.setRespParams(resContent);
                }
            }

        } catch (Exception e) {
            logger.error("用户userId：" + userId + "魔杖2.0-多头同步响应数据错误", e);
        }
        magicReqLogMapper.save(log);
        return i;
    }

    @Override
    public int queryApply(Borrow borrow) {
        int i = 0;
        UserBaseInfo userBaseinfo = userBaseInfoMapper.findByUserId(borrow.getUserId());
        if(userBaseinfo == null) {
            logger.error("查询用户userId：" + userBaseinfo.getUserId() + ",用户不存在");
            return i;
        }
        Long userId = userBaseinfo.getUserId();
        MagicReqLog log = new MagicReqLog();
        log.setBorrowId(borrow.getId());
        log.setCreateTime(new Date());
        log.setUserId(borrow.getUserId());
        log.setType(CallsOutSideFeeConstant.CALLS_TYPE_APPLY);
        try {
            String privateKey = Global.getValue("mx_private_key");
            String apiUrl = Global.getValue("mx_risk_url");
            /** 请求参数 */
            Map<String, String> reqParams = getReqParams(MagicRiskConstant.METHOD_MAGICWAND2_APPLY);

            /** 业务参数 */
            Map<String, String> bizParams = new HashMap<>();
            bizParams.put("name", userBaseinfo.getRealName());
            bizParams.put("mobile", userBaseinfo.getPhone());
            bizParams.put("idcard", userBaseinfo.getIdNo());
            String bizContent = new ObjectMapper().writeValueAsString(bizParams);

            reqParams.put(MagicRiskConstant.REQ_BIZ_CONTENT, bizContent);

            //签名
            String sign = MoxieSignUtils.signSHA1WithRSA(reqParams, privateKey);

            reqParams.put(MagicRiskConstant.REQ_SIGN, sign);

            String getURL = MagicRiskUtils.getWholeGetURL(apiUrl, reqParams);
            String resContent = MxCreditRequest.get(getURL, null);
            if(StringUtil.isNotBlank(resContent)) {
                JSONObject resJson = JSONObject.parseObject(resContent);
                String code = resJson.getString("code");
                if ("0000".equals(code)) {
                    JSONObject data = JSONObject.parseObject(resJson.getString("data"));
                    String transId = data.getString("trans_id");

                    log.setRespCode(code);
                    log.setRespTime(new Date());
                    log.setTransId(transId);
                    Date createDate = DateUtil.getNow();
                    //插入详情表
                    MagicReqDetail magicReqDetail = new MagicReqDetail(userId, transId, resJson.getString("data"), CallsOutSideFeeConstant.CALLS_TYPE_APPLY);
                    magicReqDetailMapper.save(magicReqDetail);
                    //插入收费记录表
                    CallsOutSideFee callsOutSideFee = new CallsOutSideFee(userId, transId, CallsOutSideFeeConstant.CALLS_TYPE_APPLY, CallsOutSideFeeConstant.FEE_APPLY);
                    callsOutSideFeeMapper.save(callsOutSideFee);
                    //保存数据
                    //用户联系人信息
                    MobileInfoBean mobileInfo = JSONObject.parseObject(data.getString("mobile_info"), MobileInfoBean.class);
                    if (mobileInfo != null) {
                        saveMobileInfo(mobileInfo, userId, transId, createDate);
                    }
                    //多头信息
                    AuthQueriedDetailBean authQueriedDetail = JSONObject.parseObject(data.getString("auth_queried_detail"), AuthQueriedDetailBean.class);
                    if (authQueriedDetail != null) {
                        saveMutiInfo(authQueriedDetail, userId, transId, createDate);
                    }
                    //app安装情况
                    List<RiskDeviceBean> riskDevices = JSONArray.parseArray(data.getString("risk_device"), RiskDeviceBean.class);

                    if (riskDevices != null && riskDevices.size() > 0) {
                        saveRiskDevice(riskDevices, userId, transId, createDate);
                    }
                    //信用卡逾期信息
                    CreditCardBean creditCard = JSONObject.parseObject(data.getString("credit_card"), CreditCardBean.class);
                    if(creditCard != null) {
                        saveCreditCard(creditCard, userId, transId, createDate);
                    }
                    i = 1;
                } else {
                    log.setRespCode(code);
                    log.setRespTime(new Date());
                    log.setRespParams(resContent);
                }
            }
        } catch (Exception e) {
            logger.error("用户userId：" + userId + "魔杖2.0-申请准入响应数据错误", e);
        }
        magicReqLogMapper.save(log);
        return i;
    }

    @Override
    public int magicReportRequest(Borrow borrow, TppBusiness business) {
        int i = 0;
        UserBaseInfo userBaseinfo = userBaseInfoMapper.findByUserId(borrow.getUserId());
        try {
            String appId = Global.getValue("mx_app_id");
            String privateKey = Global.getValue("mx_private_key");
            String apiUrl = Global.getValue("mx_risk_url");
            /** 请求参数 */
            Map<String, String> reqParams = new HashMap<>();
            reqParams.put(MagicRiskConstant.REQ_METHOD, MagicRiskConstant.METHOD_MAGICWAND2);
            reqParams.put(MagicRiskConstant.REQ_APP_ID, appId);
            reqParams.put(MagicRiskConstant.REQ_VERSION, MagicRiskConstant.VERSION);
            reqParams.put(MagicRiskConstant.REQ_FORMAT, MagicRiskConstant.FORMAT);
            reqParams.put(MagicRiskConstant.REQ_SIGN_TYPE, MagicRiskConstant.SIGN_TYPE);
            reqParams.put(MagicRiskConstant.REQ_TIMESTAMP, String.valueOf(System.currentTimeMillis()));

            /** 业务参数 */
            Map<String, String> bizParams = new HashMap<>();
            bizParams.put("name", userBaseinfo.getRealName());
            bizParams.put("mobile", userBaseinfo.getPhone());
            bizParams.put("idcard", userBaseinfo.getIdNo());
            String bizContent = new ObjectMapper().writeValueAsString(bizParams);

            reqParams.put(MagicRiskConstant.REQ_BIZ_CONTENT, bizContent);

            //签名
            String sign = MoxieSignUtils.signSHA1WithRSA(reqParams, privateKey);

            reqParams.put(MagicRiskConstant.REQ_SIGN, sign);

            String getURL = MagicRiskUtils.getWholeGetURL(apiUrl, reqParams);
            String resContent = MxCreditRequest.get(getURL, null);
            JSONObject resJson = JSONObject.parseObject(resContent);
            if ("0000".equals(resJson.getString("code"))) {
                //插入收费记录表
                saveMagicRisk(resContent, userBaseinfo.getUserId());
                i = 1;
            }
        } catch (Exception e) {
            logger.error("查询用户userId：" + userBaseinfo.getUserId() + "魔杖2.0报告异常", e);
        }
        return i;
    }

    @Override
    public void saveMagicRisk(String res, Long userId) {
        try {
            if (StringUtil.isNotBlank(res)) {
                JSONObject resJson = JSONObject.parseObject(res);
                if ("0000".equals(resJson.getString("code"))) {
                    DataBean data = JSONObject.parseObject(resJson.getString("data"), DataBean.class);
                    if (data == null) {
                        logger.error("保存用户userId：" + userId + "魔杖2.0数据报告时，data为空，处理失败");
                        return;
                    }
                    String transId = data.getTransId();
                    Date createDate = new Date();


                    //处理用户基本信息
                    PersonInfoBean personInfo = data.getPersonInfo();
                    MagicBasic magicBasic = new MagicBasic();
                    BeanUtils.copyProperties(personInfo, magicBasic);
                    magicBasic.setTransId(transId);
                    magicBasic.setCreateTime(createDate);
                    magicBasic.setUserId(userId);
                    magicBasicMapper.save(magicBasic);

                    //处理用户黑灰名单信息
                    BlackInfoDetailBean blackInfoDetail = data.getBlackInfoDetail();
                    GrayInfoDetailBean grayInfoDetail = data.getGrayInfoDetail();
                    if (blackInfoDetail != null || grayInfoDetail != null) {
                        saveBlackGrayInfo(blackInfoDetail, grayInfoDetail, userId, transId, createDate);
                    }

                    MobileInfoBean mobileInfo = data.getMobileInfo();
                    if (mobileInfo != null) {
                        saveMobileInfo(mobileInfo, userId, transId, createDate);
                    }

                    //处理用户多头信息
                    AuthQueriedDetailBean authQueriedDetail = data.getAuthQueriedDetail();
                    if (authQueriedDetail != null) {
                        saveMutiInfo(authQueriedDetail, userId, transId, createDate);
                    }

                    //处理用户法院失信信息
                    UntrustedInfoBean untrustedInfo = data.getUntrustedInfo();
                    if (untrustedInfo != null) {
                        saveUntrustedInfo(untrustedInfo, userId, transId, createDate);
                    }

                    //变更信息处理
                    //身份证变更信息处理
                    SuspiciousIdcardBean suspiciousIdcard = data.getSuspiciousIdcard();
                    if (suspiciousIdcard != null) {
                        saveSuspiciousIdcard(suspiciousIdcard, userId, transId, createDate);
                    }

                    //手机号变更信息处理
                    SuspiciousMobileBean suspiciousMobile = data.getSuspiciousMobile();
                    if (suspiciousMobile != null) {
                        saveSuspiciousMobile(suspiciousMobile, userId, transId, createDate);
                    }

                    //设备变更信息处理
                    SuspiciousDeviceBean suspiciousDevice = data.getSuspiciousDevice();
                    if (suspiciousDevice != null) {
                        saveSuspiciousDevice(suspiciousDevice, userId, transId, createDate);
                    }

                    //处理QQ群风险信息
                    RiskQqgroupBean riskQqgroup = data.getRiskQqgroup();
                    if (riskQqgroup != null) {
                        saveRiskQqgroup(riskQqgroup, userId, transId, createDate);
                    }

                    //处理设备指纹风险信息
                    List<RiskDeviceBean> riskDevices = data.getRiskDevice();
                    if (riskDevices != null && riskDevices.size() > 0) {
                        saveRiskDevice(riskDevices, userId, transId, createDate);
                    }

                    //处理信用卡违约信息
                    CreditCardBean creditCard = data.getCreditCard();
                    if (creditCard != null) {
                        saveCreditCard(creditCard, userId, transId, createDate);
                    }

                    //处理贷后行为信息
                    LoanBehaviorAnalysisBean loanBehaviorAnalysis = data.getLoanBehaviorAnalysis();
                    if (loanBehaviorAnalysis != null) {
                        saveLoanBehaviorAnalysis(loanBehaviorAnalysis, userId, transId, createDate);
                    }

                    //处理公积金信息
                    List<FundInfosBean> fundInfos = data.getFundInfos();
                    if (fundInfos != null && fundInfos.size() > 0) {
                        List<MagicFundInfo> magicFundInfos = ObjectConvertUtil.getMagicFundInfoList(fundInfos, userId, transId, createDate);
                        magicFundInfoMapper.saveBatch(magicFundInfos);
                    }

                    //处理银行卡信息
                    BankInfosBean bankInfos = data.getBankInfos();
                    if (bankInfos != null && (bankInfos.getDebitCardInfo() != null || bankInfos.getCreditCardInfo() != null)) {
                        MagicBankInfo magicBankInfo = ObjectConvertUtil.getMagicBankInfo(bankInfos);
                        magicBankInfoMapper.save(magicBankInfo);
                    }

                    //处理欺诈风险信息
                    FraudulenceInfoBean fraudulenceInfo = data.getFraudulenceInfo();
                    if(fraudulenceInfo != null) {
                        saveFraudulenceInfo(fraudulenceInfo, userId, transId, createDate);
                    }
                } else {
                    logger.error("保存用户userId：" + userId + "魔杖2.0数据时，查询魔杖报告返回异常，处理失败，返回数据:" + resJson);
                }

            } else {
                logger.error("保存用户userId：" + userId + "魔杖2.0数据时，res为空，处理失败");
            }


        } catch (Exception e) {
            logger.error("严重问题，userId:" + userId + "魔杖2.0数据报告保存失败", e);
        }


    }

    private Map<String, String> getReqParams(String method) {
        Map<String, String> reqParams = new HashMap<>();
        reqParams.put(MagicRiskConstant.REQ_METHOD, method);
        reqParams.put(MagicRiskConstant.REQ_APP_ID, Global.getValue("mx_app_id"));
        reqParams.put(MagicRiskConstant.REQ_VERSION, MagicRiskConstant.VERSION);
        reqParams.put(MagicRiskConstant.REQ_FORMAT, MagicRiskConstant.FORMAT);
        reqParams.put(MagicRiskConstant.REQ_SIGN_TYPE, MagicRiskConstant.SIGN_TYPE);
        reqParams.put(MagicRiskConstant.REQ_TIMESTAMP, String.valueOf(System.currentTimeMillis()));

        return reqParams;
    }

    private void saveMutiInfo(AuthQueriedDetailBean authQueriedDetail, Long userId, String transId, Date createDate) throws Exception {
        MagicMultipoint magicMultipoint = ObjectConvertUtil.getMagicMultipoint(authQueriedDetail);
        magicMultipoint.setUserId(userId);
        magicMultipoint.setTransId(transId);
        magicMultipoint.setCreateTime(createDate);
        magicMultipointMapper.save(magicMultipoint);

        List<QueriedInfosBean> queriedInfosList = authQueriedDetail.getQueriedInfos();
        if (queriedInfosList != null && queriedInfosList.size() > 0) {
            List<MagicMultipointQueriedInfo> queriedInfoList = ObjectConvertUtil.getQueriedInfoList(queriedInfosList, userId, transId, createDate);
            magicMultipointQueriedInfoMapper.saveBatch(queriedInfoList);
        }

        List<QueriedAnalyzeBean> queriedAnalyzeList = authQueriedDetail.getQueriedAnalyze();
        if (queriedAnalyzeList != null && queriedAnalyzeList.size() > 0) {
            List<MagicMultipointQueriedAnalyze> queriedAnalyzes = ObjectConvertUtil.getQueriedAnalyzList(queriedAnalyzeList, userId, transId, createDate);
            magicMultipointQueriedAnalyzeMapper.saveBatch(queriedAnalyzes);
        }
    }

    private void saveUntrustedInfo(UntrustedInfoBean untrustedInfo, Long userId, String transId, Date createDate) throws Exception {
        MagicUntrusted magicUntrusted = ObjectConvertUtil.getMagicUntrusted(untrustedInfo);
        magicUntrusted.setUserId(userId);
        magicUntrusted.setTransId(transId);
        magicUntrusted.setCreateTime(createDate);
        magicUntrustedMapper.save(magicUntrusted);

        List<DishonestDetailInoBean> dishonestDetailInos = untrustedInfo.getDishonestDetailIno();
        if (dishonestDetailInos != null && dishonestDetailInos.size() > 0) {
            List<MagicUntrustedDetail> magicUntrustedDetails = ObjectConvertUtil.getMagicUntrustedDetailList(dishonestDetailInos, userId, transId, createDate);
            magicUntrustedDetailMapper.saveBatch(magicUntrustedDetails);
        }
    }

    private void saveSuspiciousIdcard(SuspiciousIdcardBean suspiciousIdcard, Long userId, String transId, Date createDate) throws Exception {
        MagicSuspiciousIdcard magicSuspiciousIdcard = ObjectConvertUtil.getMagicSuspiciousIdcard(suspiciousIdcard);
        magicSuspiciousIdcard.setUserId(userId);
        magicSuspiciousIdcard.setTransId(transId);
        magicSuspiciousIdcard.setCreateTime(createDate);
        magicSuspiciousIdcardMapper.save(magicSuspiciousIdcard);


        List<OtherNamesBean> otherNames = suspiciousIdcard.getOtherNames();
        if (otherNames != null && otherNames.size() > 0) {
            List<MagicSuspiciousOtherName> suspiciousOtherNames = ObjectConvertUtil.getMagicSuspiciousOtherNameList(otherNames, userId, transId, createDate, "idcard");
            magicSuspiciousOtherNameMapper.saveBatch(suspiciousOtherNames);
        }

        List<OtherMobilesBean> otherMobiles = suspiciousIdcard.getOtherMobiles();
        if (otherMobiles != null && otherMobiles.size() > 0) {
            List<MagicSuspiciousOtherMobile> suspiciousOtherMobiles = ObjectConvertUtil.getMagicSuspiciousOtherMobileList(otherMobiles, userId, transId, createDate, "idcard");
            magicSuspiciousOtherMobileMapper.saveBatch(suspiciousOtherMobiles);
        }

        List<InformationSourcesBean> informationSources = suspiciousIdcard.getInformationSources();
        if (informationSources != null && informationSources.size() > 0) {
            List<MagicSuspiciousInformationSource> suspiciousInformationSources = ObjectConvertUtil.getMagicSuspiciousInformationSourceList(informationSources, userId, transId, createDate, "idcard");
            magicSuspiciousInformationSourceMapper.saveBatch(suspiciousInformationSources);
        }
    }

    private void saveSuspiciousMobile(SuspiciousMobileBean suspiciousMobile, Long userId, String transId, Date createDate) throws Exception {
        MagicSuspiciousMobile magicSuspiciousMobile = ObjectConvertUtil.getMagicSuspiciousMobile(suspiciousMobile);
        magicSuspiciousMobile.setUserId(userId);
        magicSuspiciousMobile.setTransId(transId);
        magicSuspiciousMobile.setCreateTime(createDate);
        magicSuspiciousMobileMapper.save(magicSuspiciousMobile);

        List<OtherNamesBean> otherNames = suspiciousMobile.getOtherNames();
        if (otherNames != null && otherNames.size() > 0) {
            List<MagicSuspiciousOtherName> suspiciousOtherNames = ObjectConvertUtil.getMagicSuspiciousOtherNameList(otherNames, userId, transId, createDate, "mobile");
            magicSuspiciousOtherNameMapper.saveBatch(suspiciousOtherNames);
        }

        List<OtherIdcardsBean> otherIdcards = suspiciousMobile.getOtherIdcards();
        if (otherIdcards != null && otherIdcards.size() > 0) {
            List<MagicSuspiciousOtherIdcard> suspiciousOtherIdcards = ObjectConvertUtil.getMagicSuspiciousOtherIdcardList(otherIdcards, userId, transId, createDate, "mobile");
            magicSuspiciousOtherIdcardMapper.saveBatch(suspiciousOtherIdcards);
        }

        List<InformationSourcesBean> informationSources = suspiciousMobile.getInformationSources();
        if (informationSources != null && informationSources.size() > 0) {
            List<MagicSuspiciousInformationSource> suspiciousInformationSources = ObjectConvertUtil.getMagicSuspiciousInformationSourceList(informationSources, userId, transId, createDate, "mobile");
            magicSuspiciousInformationSourceMapper.saveBatch(suspiciousInformationSources);
        }
    }

    private void saveSuspiciousDevice(SuspiciousDeviceBean suspiciousDevice, Long userId, String transId, Date createDate) throws Exception {
        MagicSuspiciousDevice magicSuspiciousDevice = ObjectConvertUtil.getMagicSuspiciousDevice(suspiciousDevice);
        magicSuspiciousDevice.setUserId(userId);
        magicSuspiciousDevice.setTransId(transId);
        magicSuspiciousDevice.setCreateTime(createDate);
        magicSuspiciousDeviceMapper.save(magicSuspiciousDevice);

        List<OtherNamesBean> otherNames = suspiciousDevice.getOtherNames();
        if (otherNames != null && otherNames.size() > 0) {
            List<MagicSuspiciousOtherName> suspiciousOtherNames = ObjectConvertUtil.getMagicSuspiciousOtherNameList(otherNames, userId, transId, createDate, "device");
            magicSuspiciousOtherNameMapper.saveBatch(suspiciousOtherNames);
        }

        List<OtherMobilesBean> otherMobiles = suspiciousDevice.getOtherMobiles();
        if (otherMobiles != null && otherMobiles.size() > 0) {
            List<MagicSuspiciousOtherMobile> suspiciousOtherMobiles = ObjectConvertUtil.getMagicSuspiciousOtherMobileList(otherMobiles, userId, transId, createDate, "device");
            magicSuspiciousOtherMobileMapper.saveBatch(suspiciousOtherMobiles);
        }

        List<OtherIdcardsBean> otherIdcards = suspiciousDevice.getOtherIdcards();
        if (otherIdcards != null && otherIdcards.size() > 0) {
            List<MagicSuspiciousOtherIdcard> suspiciousOtherIdcards = ObjectConvertUtil.getMagicSuspiciousOtherIdcardList(otherIdcards, userId, transId, createDate, "device");
            magicSuspiciousOtherIdcardMapper.saveBatch(suspiciousOtherIdcards);
        }

        List<InformationSourcesBean> informationSources = suspiciousDevice.getInformationSources();
        if (informationSources != null && informationSources.size() > 0) {
            List<MagicSuspiciousInformationSource> suspiciousInformationSources = ObjectConvertUtil.getMagicSuspiciousInformationSourceList(informationSources, userId, transId, createDate, "device");
            magicSuspiciousInformationSourceMapper.saveBatch(suspiciousInformationSources);
        }
    }

    private void saveRiskQqgroup(RiskQqgroupBean riskQqgroup, Long userId, String transId, Date createDate) throws Exception {
        MagicRiskQqGroup magicRiskQqGroup = ObjectConvertUtil.getMagicRiskQqGroup(riskQqgroup);
        magicRiskQqGroup.setUserId(userId);
        magicRiskQqGroup.setTransId(transId);
        magicRiskQqGroup.setCreateTime(createDate);
        magicRiskQqGroupMapper.save(magicRiskQqGroup);
    }


    private void saveLoanBehaviorAnalysis(LoanBehaviorAnalysisBean loanBehaviorAnalysis, Long userId, String transId, Date createDate) throws Exception {
        MagicLoanBehaviorAnalysis magicLoanBehaviorAnalysis = ObjectConvertUtil.getMagicLoanBehaviorAnalysis(loanBehaviorAnalysis);
        magicLoanBehaviorAnalysis.setUserId(userId);
        magicLoanBehaviorAnalysis.setTransId(transId);
        magicLoanBehaviorAnalysis.setCreateTime(createDate);
        magicLoanBehaviorAnalysisMapper.save(magicLoanBehaviorAnalysis);
    }

    private void saveBlackGrayInfo(BlackInfoDetailBean blackInfoDetail, GrayInfoDetailBean grayInfoDetail, Long userId, String transId, Date createDate) throws Exception {
        MagicBlackGray magicBlackGray = ObjectConvertUtil.getMagicBlackGray(blackInfoDetail, grayInfoDetail);
        magicBlackGray.setUserId(userId);
        magicBlackGray.setTransId(transId);
        magicBlackGray.setCreateTime(createDate);
        magicBlackGrayMapper.save(magicBlackGray);
    }

    private void saveFraudulenceInfo(FraudulenceInfoBean fraudulenceInfo, Long userId, String transId, Date createDate) throws Exception {
        MagicFraudulenceInfo magicFraudulenceInfo = ObjectConvertUtil.getMagicFraudulenceInfo(fraudulenceInfo);
        magicFraudulenceInfo.setUserId(userId);
        magicFraudulenceInfo.setTransId(transId);
        magicFraudulenceInfo.setCreateTime(createDate);
        magicFraudulenceInfoMapper.save(magicFraudulenceInfo);
    }

    private void saveMobileInfo(MobileInfoBean mobileInfo, Long userId, String transId, Date createDate) throws Exception {
        //处理用户通讯录信息
        MagicMobileContact magicMobileContact = ObjectConvertUtil.getMagicMobileContact(mobileInfo);
        magicMobileContact.setUserId(userId);
        magicMobileContact.setTransId(transId);
        magicMobileContact.setCreateTime(createDate);
        magicMobileContactMapper.save(magicMobileContact);
        //处理用户亲密联系人信息
        MagicIntimateContact magicIntimateContact = ObjectConvertUtil.getMagicIntimateContact(mobileInfo);
        magicIntimateContact.setUserId(userId);
        magicIntimateContact.setTransId(transId);
        magicIntimateContact.setCreateTime(createDate);
        magicIntimateContactMapper.save(magicIntimateContact);
    }

    private void saveRiskDevice(List<RiskDeviceBean> riskDevices, Long userId, String transId, Date createDate) throws Exception {
        List<MagicRiskDevice> magicRiskDevices = ObjectConvertUtil.getMagicRiskDeviceList(riskDevices, userId, transId, createDate);
        magicRiskDeviceMapper.saveBatch(magicRiskDevices);
    }

    private void saveCreditCard(CreditCardBean creditCard, Long userId, String transId, Date createDate) throws Exception {
        MagicCreditCardOverdue magicCreditCardOverdue = ObjectConvertUtil.getMagicCreditCardOverdue(creditCard);
        magicCreditCardOverdue.setUserId(userId);
        magicCreditCardOverdue.setTransId(transId);
        magicCreditCardOverdue.setCreateTime(createDate);
        magicCreditCardOverdueMapper.save(magicCreditCardOverdue);
    }
}

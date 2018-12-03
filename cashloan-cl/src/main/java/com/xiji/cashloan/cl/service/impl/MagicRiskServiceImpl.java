package com.xiji.cashloan.cl.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xiji.cashloan.cl.service.MagicRiskService;
import com.xiji.cashloan.cl.util.magic.ObjectConvertUtil;
import com.xiji.cashloan.core.common.util.StringUtil;
import com.xiji.cashloan.core.domain.Borrow;
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
import java.util.List;

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

    @Override
    public int magicReportRequest(Borrow borrow, TppBusiness business) {
        return 0;
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
                    String transId = resJson.getString("trans_id");
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
                        MagicBlackGray magicBlackGray = ObjectConvertUtil.getMagicBlackGray(blackInfoDetail, grayInfoDetail);
                        magicBlackGray.setUserId(userId);
                        magicBlackGray.setTransId(transId);
                        magicBlackGray.setCreateTime(createDate);
                        magicBlackGrayMapper.save(magicBlackGray);
                    }

                    MobileInfoBean mobileInfo = data.getMobileInfo();
                    if (mobileInfo != null) {
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

                    //处理用户多头信息
                    AuthQueriedDetailBean authQueriedDetail = data.getAuthQueriedDetail();
                    if (authQueriedDetail != null) {
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

                    //处理用户法院失信信息
                    UntrustedInfoBean untrustedInfo = data.getUntrustedInfo();
                    if (untrustedInfo != null) {
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

                    //变更信息处理
                    //身份证变更信息处理
                    SuspiciousIdcardBean suspiciousIdcard = data.getSuspiciousIdcard();
                    if (suspiciousIdcard != null) {
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

                    //手机号变更信息处理
                    SuspiciousMobileBean suspiciousMobile = data.getSuspiciousMobile();
                    if(suspiciousMobile != null) {
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
                        if(otherIdcards != null && otherIdcards.size() > 0) {
                            List<MagicSuspiciousOtherIdcard> suspiciousOtherIdcards = ObjectConvertUtil.getMagicSuspiciousOtherIdcardList(otherIdcards, userId, transId, createDate, "mobile");
                            magicSuspiciousOtherIdcardMapper.saveBatch(suspiciousOtherIdcards);
                        }

                        List<InformationSourcesBean> informationSources = suspiciousMobile.getInformationSources();
                        if (informationSources != null && informationSources.size() > 0) {
                            List<MagicSuspiciousInformationSource> suspiciousInformationSources = ObjectConvertUtil.getMagicSuspiciousInformationSourceList(informationSources, userId, transId, createDate, "mobile");
                            magicSuspiciousInformationSourceMapper.saveBatch(suspiciousInformationSources);
                        }
                    }

                    //设备变更信息处理
                    SuspiciousDeviceBean suspiciousDevice = data.getSuspiciousDevice();
                    if(suspiciousDevice != null) {
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
                        if(otherIdcards != null && otherIdcards.size() > 0) {
                            List<MagicSuspiciousOtherIdcard> suspiciousOtherIdcards = ObjectConvertUtil.getMagicSuspiciousOtherIdcardList(otherIdcards, userId, transId, createDate, "device");
                            magicSuspiciousOtherIdcardMapper.saveBatch(suspiciousOtherIdcards);
                        }

                        List<InformationSourcesBean> informationSources = suspiciousDevice.getInformationSources();
                        if (informationSources != null && informationSources.size() > 0) {
                            List<MagicSuspiciousInformationSource> suspiciousInformationSources = ObjectConvertUtil.getMagicSuspiciousInformationSourceList(informationSources, userId, transId, createDate, "device");
                            magicSuspiciousInformationSourceMapper.saveBatch(suspiciousInformationSources);
                        }
                    }

                    //处理QQ群风险信息
                    RiskQqgroupBean riskQqgroup = data.getRiskQqgroup();
                    if (riskQqgroup != null) {
                        MagicRiskQqGroup magicRiskQqGroup = ObjectConvertUtil.getMagicRiskQqGroup(riskQqgroup);
                        magicRiskQqGroup.setUserId(userId);
                        magicRiskQqGroup.setTransId(transId);
                        magicRiskQqGroup.setCreateTime(createDate);
                        magicRiskQqGroupMapper.save(magicRiskQqGroup);
                    }

                    //处理设备指纹风险信息
                    List<RiskDeviceBean> riskDevices = data.getRiskDevice();
                    if (riskDevices != null && riskDevices.size() > 0) {
                        List<MagicRiskDevice> magicRiskDevices = ObjectConvertUtil.getMagicRiskDeviceList(riskDevices, userId, transId, createDate);
                        magicRiskDeviceMapper.saveBatch(magicRiskDevices);
                    }

                    //处理信用卡违约信息
                    CreditCardBean creditCard = data.getCreditCard();
                    if (creditCard != null) {
                        MagicCreditCardOverdue magicCreditCardOverdue = ObjectConvertUtil.getMagicCreditCardOverdue(creditCard);
                        magicCreditCardOverdue.setUserId(userId);
                        magicCreditCardOverdue.setTransId(transId);
                        magicCreditCardOverdue.setCreateTime(createDate);
                        magicCreditCardOverdueMapper.save(magicCreditCardOverdue);
                    }

                    //处理贷后行为信息
                    LoanBehaviorAnalysisBean loanBehaviorAnalysis = data.getLoanBehaviorAnalysis();
                    if (loanBehaviorAnalysis != null) {
                        MagicLoanBehaviorAnalysis magicLoanBehaviorAnalysis = ObjectConvertUtil.getMagicLoanBehaviorAnalysis(loanBehaviorAnalysis);
                        magicLoanBehaviorAnalysis.setUserId(userId);
                        magicLoanBehaviorAnalysis.setTransId(transId);
                        magicLoanBehaviorAnalysis.setCreateTime(createDate);
                        magicLoanBehaviorAnalysisMapper.save(magicLoanBehaviorAnalysis);
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
}

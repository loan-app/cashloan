package com.xiji.cashloan.cl.util.magic;

import com.xiji.cashloan.cl.util.BeanUtilsExt;
import com.xiji.cashloan.cl.util.KeyMapping;
import com.xiji.cashloan.core.common.util.StringUtil;
import com.xiji.cashloan.cl.domain.*;
import com.xiji.cashloan.cl.domain.mozhang.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 魔蝎类型转换工具类
 * Created by szb on 18/12/1.
 */
public class ObjectConvertUtil {

    public static MagicBlackGray getMagicBlackGray(BlackInfoDetailBean blackInfoDetail, GrayInfoDetailBean grayInfoDetail) throws Exception {
        MagicBlackGray magicBlackGray = new MagicBlackGray();
        if (blackInfoDetail != null) {
            magicBlackGray.setBlackMobileNameInBlacklist(blackInfoDetail.isMobileNameInBlacklist() ? 1 : 0);
            magicBlackGray.setBlackIdcardNameInBlacklist(blackInfoDetail.isIdcardNameInBlacklist() ? 1 : 0);
            BeanUtilsExt.copyPropertiesExt(magicBlackGray, blackInfoDetail, null);
        }
        if (grayInfoDetail != null) {
            magicBlackGray.setGrayMobileNameInBlacklist(grayInfoDetail.isMobileNameInGray() ? 1 : 0);
            magicBlackGray.setGrayIdcardNameInBlacklist(grayInfoDetail.isIdcardNameInGray() ? 1 : 0);
            BeanUtilsExt.copyPropertiesExt(magicBlackGray, grayInfoDetail, null);
        }
        return magicBlackGray;
    }

    public static MagicMobileContact getMagicMobileContact(MobileInfoBean mobileInfo) throws Exception {
        MagicMobileContact magicMobileContact = new MagicMobileContact();

        MobileContactBean mobileContact30d = mobileInfo.getMobileContact30d();
        MobileContactBean mobileContact90d = mobileInfo.getMobileContact90d();
        MobileContactBean mobileContact180d = mobileInfo.getMobileContact180d();
        magicMobileContact.setMatchScore(mobileInfo.getMatchScore());

        if (mobileContact30d != null) {
            BeanUtilsExt.copyPropertiesExt(magicMobileContact, mobileContact30d, new KeyMapping() {
                @Override
                public String mapping(String orig) {
                    return orig + "30d";
                }
            });
        }
        if (mobileContact90d != null) {
            BeanUtilsExt.copyPropertiesExt(magicMobileContact, mobileContact90d, new KeyMapping() {
                @Override
                public String mapping(String orig) {
                    return orig + "90d";
                }
            });
        }
        if (mobileContact180d != null) {
            BeanUtilsExt.copyPropertiesExt(magicMobileContact, mobileContact180d, new KeyMapping() {
                @Override
                public String mapping(String orig) {
                    return orig + "180d";
                }
            });
        }

        return magicMobileContact;
    }


    public static MagicIntimateContact getMagicIntimateContact(MobileInfoBean mobileInfo) throws Exception {
        MagicIntimateContact magicIntimateContact = new MagicIntimateContact();

        IntimateContactInfoBean intimateContactInfo30d = mobileInfo.getIntimateContactInfo30d();
        IntimateContactInfoBean intimateContactInfo90d = mobileInfo.getIntimateContactInfo90d();
        IntimateContactInfoBean intimateContactInfo180d = mobileInfo.getIntimateContactInfo180d();

        if (intimateContactInfo30d != null) {
            BeanUtilsExt.copyPropertiesExt(magicIntimateContact, intimateContactInfo30d, new KeyMapping() {
                @Override
                public String mapping(String orig) {
                    return orig + "30d";
                }
            });
        }

        if (intimateContactInfo90d != null) {
            BeanUtilsExt.copyPropertiesExt(magicIntimateContact, intimateContactInfo90d, new KeyMapping() {
                @Override
                public String mapping(String orig) {
                    return orig + "90d";
                }
            });
        }

        if (intimateContactInfo180d != null) {
            BeanUtilsExt.copyPropertiesExt(magicIntimateContact, intimateContactInfo180d, new KeyMapping() {
                @Override
                public String mapping(String orig) {
                    return orig + "180d";
                }
            });
        }

        return magicIntimateContact;
    }

    public static MagicMultipoint getMagicMultipoint(AuthQueriedDetailBean authQueriedDetail) throws Exception {
        MagicMultipoint magicMultipoint = new MagicMultipoint();
        RegisterInfoBean registerInfo = authQueriedDetail.getRegisterInfo();
        LoanInfoBean loanInfo = authQueriedDetail.getLoanInfo();

        BeanUtilsExt.copyPropertiesExt(magicMultipoint, authQueriedDetail, null);
        if (registerInfo != null) {
            BeanUtilsExt.copyPropertiesExt(magicMultipoint, registerInfo, null);
            magicMultipoint.setRegisterOrgTypes(StringUtil.join(registerInfo.getOrgTypes(), ","));
        }
        if (loanInfo != null) {
            BeanUtilsExt.copyPropertiesExt(magicMultipoint, loanInfo, null);
        }
        return magicMultipoint;
    }

    public static List<MagicMultipointQueriedInfo> getQueriedInfoList(List<QueriedInfosBean> queriedInfosBeanList, Long userId, String transId, Date createTime) throws Exception {
        List<MagicMultipointQueriedInfo> queriedInfos = new ArrayList<>();
        for (QueriedInfosBean queriedInfosBean : queriedInfosBeanList) {
            MagicMultipointQueriedInfo magicMultipointQueriedInfo = new MagicMultipointQueriedInfo();
            BeanUtilsExt.copyPropertiesExt(magicMultipointQueriedInfo, queriedInfosBean, null);
            magicMultipointQueriedInfo.setIsSelf(queriedInfosBean.isIsSelf() ? 1 : 0);
            magicMultipointQueriedInfo.setUserId(userId);
            magicMultipointQueriedInfo.setTransId(transId);
            magicMultipointQueriedInfo.setCreateTime(createTime);
            queriedInfos.add(magicMultipointQueriedInfo);
        }
        return queriedInfos;
    }


    public static List<MagicMultipointQueriedAnalyze> getQueriedAnalyzList(List<QueriedAnalyzeBean> queriedAnalyzeList, Long userId, String transId, Date createTime) throws Exception {
        List<MagicMultipointQueriedAnalyze> queriedAnalyzes = new ArrayList<>();
        for (QueriedAnalyzeBean analyzeBean : queriedAnalyzeList) {
            MagicMultipointQueriedAnalyze queriedAnalyze = new MagicMultipointQueriedAnalyze();
            BeanUtilsExt.copyPropertiesExt(queriedAnalyze, analyzeBean, null);
            queriedAnalyze.setUserId(userId);
            queriedAnalyze.setTransId(transId);
            queriedAnalyze.setCreateTime(createTime);
            queriedAnalyzes.add(queriedAnalyze);
        }
        return queriedAnalyzes;
    }

    public static MagicUntrusted getMagicUntrusted(UntrustedInfoBean untrustedInfo) throws Exception {
        MagicUntrusted magicUntrusted = new MagicUntrusted();
        BeanUtilsExt.copyPropertiesExt(magicUntrusted, untrustedInfo, null);
        return magicUntrusted;
    }

    public static List<MagicUntrustedDetail> getMagicUntrustedDetailList(List<DishonestDetailInoBean> dishonestDetailInos, Long userId, String transId, Date createTime) throws Exception {
        List<MagicUntrustedDetail> magicUntrustedDetails = new ArrayList<>();
        for (DishonestDetailInoBean detailIno : dishonestDetailInos) {
            MagicUntrustedDetail magicUntrustedDetail = new MagicUntrustedDetail();
            magicUntrustedDetail.setUserId(userId);
            magicUntrustedDetail.setTransId(transId);
            magicUntrustedDetail.setCreateTime(createTime);
            BeanUtilsExt.copyPropertiesExt(magicUntrustedDetail, detailIno, null);
            magicUntrustedDetails.add(magicUntrustedDetail);
        }

        return magicUntrustedDetails;
    }

    public static MagicRiskQqGroup getMagicRiskQqGroup(RiskQqgroupBean riskQqgroupBean) throws Exception {
        MagicRiskQqGroup magicRiskQqGroup = new MagicRiskQqGroup();
        BeanUtilsExt.copyPropertiesExt(magicRiskQqGroup, riskQqgroupBean, null);
        return magicRiskQqGroup;
    }

    public static List<MagicRiskDevice> getMagicRiskDeviceList(List<RiskDeviceBean> riskDeviceBeanList, Long userId, String transId, Date createTime) throws Exception {
        List<MagicRiskDevice> magicRiskDevices = new ArrayList<>();
        for (RiskDeviceBean riskDeviceBean : riskDeviceBeanList) {
            MagicRiskDevice magicRiskDevice = new MagicRiskDevice();
            magicRiskDevice.setUserId(userId);
            magicRiskDevice.setTransId(transId);
            magicRiskDevice.setCreateTime(createTime);
            BeanUtilsExt.copyPropertiesExt(magicRiskDevice, riskDeviceBean, null);
            magicRiskDevices.add(magicRiskDevice);
        }
        return magicRiskDevices;
    }

    public static MagicCreditCardOverdue getMagicCreditCardOverdue(CreditCardBean creditCard) throws Exception {
        MagicCreditCardOverdue creditCardOverdue = new MagicCreditCardOverdue();
        BeanUtilsExt.copyPropertiesExt(creditCard, creditCardOverdue, null);
        if (creditCard.getCreditOverdueItem3m() != null) {
            BeanUtilsExt.copyPropertiesExt(creditCardOverdue, creditCard.getCreditOverdueItem3m(), new KeyMapping() {
                @Override
                public String mapping(String orig) {
                    return orig + "3m";
                }
            });
        }
        if (creditCard.getCreditOverdueItem6m() != null) {
            BeanUtilsExt.copyPropertiesExt(creditCardOverdue, creditCard.getCreditOverdueItem6m(), new KeyMapping() {
                @Override
                public String mapping(String orig) {
                    return orig + "6m";
                }
            });
        }
        if (creditCard.getCreditOverdueItem12m() != null) {
            BeanUtilsExt.copyPropertiesExt(creditCardOverdue, creditCard.getCreditOverdueItem12m(), new KeyMapping() {
                @Override
                public String mapping(String orig) {
                    return orig + "12m";
                }
            });
        }
        return creditCardOverdue;
    }

    public static MagicLoanBehaviorAnalysis getMagicLoanBehaviorAnalysis(LoanBehaviorAnalysisBean analysisBean) throws Exception {
        MagicLoanBehaviorAnalysis loanBehaviorAnalysis = new MagicLoanBehaviorAnalysis();
        BeanUtilsExt.copyPropertiesExt(analysisBean, loanBehaviorAnalysis, null);

        if (analysisBean.getFeature7d() != null) {
            BeanUtilsExt.copyPropertiesExt(loanBehaviorAnalysis, analysisBean.getFeature7d(), new KeyMapping() {
                @Override
                public String mapping(String orig) {
                    return orig + "d7";
                }
            });
        }
        if (analysisBean.getFeature15d() != null) {
            BeanUtilsExt.copyPropertiesExt(loanBehaviorAnalysis, analysisBean.getFeature15d(), new KeyMapping() {
                @Override
                public String mapping(String orig) {
                    return orig + "d15";
                }
            });
        }
        if (analysisBean.getFeature30d() != null) {
            BeanUtilsExt.copyPropertiesExt(loanBehaviorAnalysis, analysisBean.getFeature30d(), new KeyMapping() {
                @Override
                public String mapping(String orig) {
                    return orig + "m1";
                }
            });
        }
        if (analysisBean.getFeature90d() != null) {
            BeanUtilsExt.copyPropertiesExt(loanBehaviorAnalysis, analysisBean.getFeature90d(), new KeyMapping() {
                @Override
                public String mapping(String orig) {
                    return orig + "m3";
                }
            });
        }
        if (analysisBean.getFeature180d() != null) {
            BeanUtilsExt.copyPropertiesExt(loanBehaviorAnalysis, analysisBean.getFeature180d(), new KeyMapping() {
                @Override
                public String mapping(String orig) {
                    return orig + "m6";
                }
            });
        }
        if (analysisBean.getFeature360d() != null) {
            BeanUtilsExt.copyPropertiesExt(loanBehaviorAnalysis, analysisBean.getFeature360d(), new KeyMapping() {
                @Override
                public String mapping(String orig) {
                    return orig + "m12";
                }
            });
        }
        return loanBehaviorAnalysis;
    }

    public static List<MagicFundInfo> getMagicFundInfoList(List<FundInfosBean> infosBeanList, Long userId, String transId, Date createTime) throws Exception {
        List<MagicFundInfo> magicFundInfos = new ArrayList<>();
        for (FundInfosBean fundInfosBean : infosBeanList) {
            MagicFundInfo magicFundInfo = new MagicFundInfo();
            FundBasicBean fundBasic = fundInfosBean.getFundBasic();
            if (fundBasic != null) {
                BeanUtilsExt.copyPropertiesExt(magicFundInfo, fundBasic, null);
            }
            FundStatisticsBean fundStatistics = fundInfosBean.getFundStatistics();
            if (fundStatistics != null) {
                BeanUtilsExt.copyPropertiesExt(magicFundInfo, fundStatistics, null);
            }

            magicFundInfos.add(magicFundInfo);
        }

        return magicFundInfos;
    }

    public static MagicBankInfo getMagicBankInfo(BankInfosBean bankInfosBean) throws Exception {
        MagicBankInfo magicBankInfo = new MagicBankInfo();
        if (bankInfosBean.getCreditCardInfo() != null) {
            BeanUtilsExt.copyPropertiesExt(magicBankInfo, bankInfosBean.getCreditCardInfo(), null);
        }
        if (bankInfosBean.getDebitCardInfo() != null) {
            BeanUtilsExt.copyPropertiesExt(magicBankInfo, bankInfosBean.getDebitCardInfo(), null);
        }
        return magicBankInfo;
    }

    public static MagicSuspiciousIdcard getMagicSuspiciousIdcard(SuspiciousIdcardBean suspiciousIdcardBean) throws Exception {
        MagicSuspiciousIdcard magicSuspiciousIdcard = new MagicSuspiciousIdcard();
        BeanUtilsExt.copyPropertiesExt(magicSuspiciousIdcard, suspiciousIdcardBean, null);
        return magicSuspiciousIdcard;
    }

    public static List<MagicSuspiciousOtherName> getMagicSuspiciousOtherNameList(List<OtherNamesBean> otherNamesList, Long userId, String transId, Date createTime, String type) throws Exception {
        List<MagicSuspiciousOtherName> suspiciousOtherNames = new ArrayList<>();

        for (OtherNamesBean otherNamesBean : otherNamesList) {
            MagicSuspiciousOtherName suspiciousOtherName = new MagicSuspiciousOtherName();
            BeanUtilsExt.copyPropertiesExt(suspiciousOtherName,otherNamesBean,  null);
            suspiciousOtherName.setType(type);
            suspiciousOtherName.setUserId(userId);
            suspiciousOtherName.setTransId(transId);
            suspiciousOtherName.setCreateTime(createTime);
            suspiciousOtherNames.add(suspiciousOtherName);
        }

        return suspiciousOtherNames;
    }

    public static List<MagicSuspiciousOtherMobile> getMagicSuspiciousOtherMobileList(List<OtherMobilesBean> otherMobilesList, Long userId, String transId, Date createTime, String type) throws Exception {
        List<MagicSuspiciousOtherMobile> suspiciousOtherMobiles = new ArrayList<>();

        for (OtherMobilesBean otherMobilesBean : otherMobilesList) {
            MagicSuspiciousOtherMobile suspiciousOtherMobile = new MagicSuspiciousOtherMobile();
            BeanUtilsExt.copyPropertiesExt(suspiciousOtherMobile, otherMobilesBean, null);
            suspiciousOtherMobile.setIsblack(otherMobilesBean.isIsblack() ? 1 : 0);
            suspiciousOtherMobile.setType(type);
            suspiciousOtherMobile.setUserId(userId);
            suspiciousOtherMobile.setTransId(transId);
            suspiciousOtherMobile.setCreateTime(createTime);
            suspiciousOtherMobiles.add(suspiciousOtherMobile);
        }

        return suspiciousOtherMobiles;
    }

    public static List<MagicSuspiciousInformationSource> getMagicSuspiciousInformationSourceList(List<InformationSourcesBean> informationSourcesList, Long userId, String transId, Date createTime, String type) throws Exception {
        List<MagicSuspiciousInformationSource> suspiciousInformationSources = new ArrayList<>();
        for (InformationSourcesBean informationSourcesBean : informationSourcesList) {
            MagicSuspiciousInformationSource suspiciousInformationSource = new MagicSuspiciousInformationSource();
            BeanUtilsExt.copyPropertiesExt(suspiciousInformationSource, informationSourcesBean, null);
            suspiciousInformationSource.setType(type);
            suspiciousInformationSource.setUserId(userId);
            suspiciousInformationSource.setTransId(transId);
            suspiciousInformationSource.setCreateTime(createTime);
            suspiciousInformationSources.add(suspiciousInformationSource);
        }
        return suspiciousInformationSources;
    }

    public static MagicSuspiciousMobile getMagicSuspiciousMobile(SuspiciousMobileBean suspiciousMobileBean) throws Exception {
        MagicSuspiciousMobile magicSuspiciousMobile = new MagicSuspiciousMobile();
        BeanUtilsExt.copyPropertiesExt(magicSuspiciousMobile, suspiciousMobileBean, null);
        return magicSuspiciousMobile;
    }

    public static List<MagicSuspiciousOtherIdcard> getMagicSuspiciousOtherIdcardList(List<OtherIdcardsBean> otherIdcardList, Long userId, String transId, Date createTime, String type) throws Exception {
        List<MagicSuspiciousOtherIdcard> suspiciousOtherIdcards = new ArrayList<>();
        for (OtherIdcardsBean otherIdcardsBean : otherIdcardList) {
            MagicSuspiciousOtherIdcard suspiciousOtherIdcard = new MagicSuspiciousOtherIdcard();
            BeanUtilsExt.copyPropertiesExt(suspiciousOtherIdcard, otherIdcardsBean, null);
            suspiciousOtherIdcard.setIsblack(otherIdcardsBean.isIsblack() ? 1 : 0);
            suspiciousOtherIdcard.setType(type);
            suspiciousOtherIdcard.setUserId(userId);
            suspiciousOtherIdcard.setTransId(transId);
            suspiciousOtherIdcard.setCreateTime(createTime);
            suspiciousOtherIdcards.add(suspiciousOtherIdcard);
        }

        return suspiciousOtherIdcards;
    }

    public static MagicSuspiciousDevice getMagicSuspiciousDevice(SuspiciousDeviceBean suspiciousDeviceBean) throws Exception {
        MagicSuspiciousDevice magicSuspiciousDevice = new MagicSuspiciousDevice();
        BeanUtilsExt.copyPropertiesExt(magicSuspiciousDevice, suspiciousDeviceBean, null);
        return magicSuspiciousDevice;
    }
}

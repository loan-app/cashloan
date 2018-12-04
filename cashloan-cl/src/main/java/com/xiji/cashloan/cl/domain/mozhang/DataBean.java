package com.xiji.cashloan.cl.domain.mozhang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author wnb
 * @date 2018/12/03
 * @version 1.0.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataBean {
    /**
     * trans_id : string
     * person_info : {"idcard":"string","idcard_location":"string","mobile":"string","carrier":"string","mobile_location":"string","name":"string","age":0,"gender":"string","email":"string"}
     * verification_info : {"has_carrier_data":true,"has_onlinebank_data":true}
     * black_info_detail : {"mobile_name_in_blacklist":true,"mobile_name_blacklist_updated_time":"string","idcard_name_in_blacklist":true,"idcard_name_blacklist_updated_time":"string","black_types":"string","blacklist_record":{"overdue_count":0,"overdue_amount":"string","overdue_status":"string"}}
     * gray_info_detail : {"mobile_name_in_gray":true,"mobile_name_gray_updated_time":"string","idcard_name_in_gray":true,"idcard_name_gray_updated_time":"string","gray_types":"string","graylist_record":{"overdue_count":0,"overdue_amount":"string","overdue_status":"string"}}
     * mobile_info : {"match_score":0,"mobile_contact_30d":{"contactnum":0,"auth_contactnum":0,"auth_contact_ratio":"string","black_contactnum":0,"black_contactnum_ratio":"string","contact_type":"string","auth_indirectnum":0,"auth_indirectnum_ratio":"string","black_indirectnum":0,"black_indirectnum_ratio":"string","black_indirect_peernum":0,"black_indirect_peernum_ratio":"string","auth_indirect_peernum":0,"auth_indirect_peernum_ratio":"string"},"intimate_contact_info_30d":{"intimatenum":0,"auth_intimatenum":0,"auth_intimatenum_ratio":"string","black_intimatenum":0,"black_intimatenum_ratio":"string","intimate_type":"string","auth_intimate_indirectnum":0,"auth_intimate_indirectnum_ratio":"string","black_intimate_indirectnum":0,"black_intimate_indirectnum_ratio":"string","black_intimate_indirect_peernum":0,"black_intimate_indirect_peernum_ratio":"string","auth_intimate_indirect_peernum":0,"auth_intimate_indirect_peernum_ratio":"string"},"mobile_contact_90d":{"contactnum":0,"auth_contactnum":0,"auth_contact_ratio":"string","black_contactnum":0,"black_contactnum_ratio":"string","contact_type":"string","auth_indirectnum":0,"auth_indirectnum_ratio":"string","black_indirectnum":0,"black_indirectnum_ratio":"string","black_indirect_peernum":0,"black_indirect_peernum_ratio":"string","auth_indirect_peernum":0,"auth_indirect_peernum_ratio":"string"},"intimate_contact_info_90d":{"intimatenum":0,"auth_intimatenum":0,"auth_intimatenum_ratio":"string","black_intimatenum":0,"black_intimatenum_ratio":"string","intimate_type":"string","auth_intimate_indirectnum":0,"auth_intimate_indirectnum_ratio":"string","black_intimate_indirectnum":0,"black_intimate_indirectnum_ratio":"string","black_intimate_indirect_peernum":0,"black_intimate_indirect_peernum_ratio":"string","auth_intimate_indirect_peernum":0,"auth_intimate_indirect_peernum_ratio":"string"},"mobile_contact_180d":{"contactnum":0,"auth_contactnum":0,"auth_contact_ratio":"string","black_contactnum":0,"black_contactnum_ratio":"string","contact_type":"string","auth_indirectnum":0,"auth_indirectnum_ratio":"string","black_indirectnum":0,"black_indirectnum_ratio":"string","black_indirect_peernum":0,"black_indirect_peernum_ratio":"string","auth_indirect_peernum":0,"auth_indirect_peernum_ratio":"string"},"intimate_contact_info_180d":{"intimatenum":0,"auth_intimatenum":0,"auth_intimatenum_ratio":"string","black_intimatenum":0,"black_intimatenum_ratio":"string","intimate_type":"string","auth_intimate_indirectnum":0,"auth_intimate_indirectnum_ratio":"string","black_intimate_indirectnum":0,"black_intimate_indirectnum_ratio":"string","black_intimate_indirect_peernum":0,"black_intimate_indirect_peernum_ratio":"string","auth_intimate_indirect_peernum":0,"auth_intimate_indirect_peernum_ratio":"string"}}
     * auth_queried_detail : {"register_info":{"other_count":0,"org_count":0,"org_types":["string"]},"queried_detail_org_count":0,"queried_analyze":[{"org_type":"string","loan_cnt_15d":0,"loan_cnt_30d":0,"loan_cnt_90d":0,"loan_cnt_180d":"int"}],"queried_infos":[{"date":"string","org_type":"string","is_self":true}],"loan_org_cnt_all":0,"loan_info":{"loan_org_cnt":0,"loan_cnt":0,"loan_org_cnt_15d":0,"loan_org_cnt_30d":0,"loan_org_cnt_90d":0,"loan_org_cnt_180d":0,"loan_cnt_15d":0,"loan_cnt_30d":0,"loan_cnt_90d":0,"loan_cnt_180d":0}}
     * untrusted_info : {"courtcase_cnt":0,"dishonest_cnt":0,"dishonest_detail_ino":[{"court_name":"string","area_name":"string","case_code":"string","publish_date":"string","gist_id":"string","duty":"string","performance":"string","disrupt_type_name":"string"}]}
     * suspicious_idcard : {"other_names_cnt":0,"other_mobiles_cnt":0,"other_mobiles_black_cnt":0,"information_sources_cnt":0,"other_names":[{"latest_used_time":"string","name":"string"}],"other_mobiles":[{"latest_used_time":"string","mobile":"string","carrier":"string","mobile_location":"string","isblack":true}],"information_sources":[{"latest_used_time":"string","org_type":"string"}]}
     * suspicious_mobile : {"other_names_cnt":0,"other_idcards_cnt":0,"other_idcards_black_cnt":0,"information_sources_cnt":0,"other_names":[{"latest_used_time":"string","name":"string"}],"other_idcards":[{"latest_used_time":"string","idcard":"string","isblack":true}],"information_sources":[{"latest_used_time":"string","org_type":"string"}]}
     * suspicious_device : {"other_devices_cnt":0,"mobile_other_devices_cnt":0,"idcard_other_devices_cnt":0,"information_sources_cnt":0,"other_names_cnt":0,"other_mobiles_cnt":0,"other_mobiles_black_cnt":0,"other_idcards_cnt":0,"other_idcards_black_cnt":0,"other_names":[{"latest_used_time":"string","name":"string"}]}
     * risk_qqgroup : {"loan_groupcnt":0,"installment_groupcnt":0,"financial_management_groupcnt":0,"woolen_groupcnt":0,"gambling_groupcnt":"int"}
     */

    @JsonProperty("trans_id")
    private String transId;
    @JsonProperty("person_info")
    private PersonInfoBean personInfo;
    @JsonProperty("verification_info")
    private VerificationInfoBean verificationInfo;
    @JsonProperty("black_info_detail")
    private BlackInfoDetailBean blackInfoDetail;
    @JsonProperty("gray_info_detail")
    private GrayInfoDetailBean grayInfoDetail;
    @JsonProperty("mobile_info")
    private MobileInfoBean mobileInfo;
    @JsonProperty("auth_queried_detail")
    private AuthQueriedDetailBean authQueriedDetail;
    @JsonProperty("untrusted_info")
    private UntrustedInfoBean untrustedInfo;
    @JsonProperty("suspicious_idcard")
    private SuspiciousIdcardBean suspiciousIdcard;
    @JsonProperty("suspicious_mobile")
    private SuspiciousMobileBean suspiciousMobile;
    @JsonProperty("suspicious_device")
    private SuspiciousDeviceBean suspiciousDevice;
    @JsonProperty("risk_qqgroup")
    private RiskQqgroupBean riskQqgroup;
    @JsonProperty("risk_device")
    private List<RiskDeviceBean> riskDevice;
    @JsonProperty("fund_infos")
    private List<FundInfosBean> fundInfos;
    @JsonProperty("credit_card")
    private CreditCardBean creditCard;
    @JsonProperty("loan_behavior_analysis")
    private LoanBehaviorAnalysisBean loanBehaviorAnalysis;
    @JsonProperty("bank_infos")
    private BankInfosBean bankInfos;

    public List<RiskDeviceBean> getRiskDevice() {
        return riskDevice;
    }

    public void setRiskDevice(List<RiskDeviceBean> riskDevice) {
        this.riskDevice = riskDevice;
    }

    public List<FundInfosBean> getFundInfos() {
        return fundInfos;
    }

    public void setFundInfos(List<FundInfosBean> fundInfos) {
        this.fundInfos = fundInfos;
    }

    public CreditCardBean getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCardBean creditCard) {
        this.creditCard = creditCard;
    }

    public LoanBehaviorAnalysisBean getLoanBehaviorAnalysis() {
        return loanBehaviorAnalysis;
    }

    public void setLoanBehaviorAnalysis(LoanBehaviorAnalysisBean loanBehaviorAnalysis) {
        this.loanBehaviorAnalysis = loanBehaviorAnalysis;
    }

    public BankInfosBean getBankInfos() {
        return bankInfos;
    }

    public void setBankInfos(BankInfosBean bankInfos) {
        this.bankInfos = bankInfos;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public PersonInfoBean getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfoBean personInfo) {
        this.personInfo = personInfo;
    }

    public VerificationInfoBean getVerificationInfo() {
        return verificationInfo;
    }

    public void setVerificationInfo(VerificationInfoBean verificationInfo) {
        this.verificationInfo = verificationInfo;
    }

    public BlackInfoDetailBean getBlackInfoDetail() {
        return blackInfoDetail;
    }

    public void setBlackInfoDetail(BlackInfoDetailBean blackInfoDetail) {
        this.blackInfoDetail = blackInfoDetail;
    }

    public GrayInfoDetailBean getGrayInfoDetail() {
        return grayInfoDetail;
    }

    public void setGrayInfoDetail(GrayInfoDetailBean grayInfoDetail) {
        this.grayInfoDetail = grayInfoDetail;
    }

    public MobileInfoBean getMobileInfo() {
        return mobileInfo;
    }

    public void setMobileInfo(MobileInfoBean mobileInfo) {
        this.mobileInfo = mobileInfo;
    }

    public AuthQueriedDetailBean getAuthQueriedDetail() {
        return authQueriedDetail;
    }

    public void setAuthQueriedDetail(AuthQueriedDetailBean authQueriedDetail) {
        this.authQueriedDetail = authQueriedDetail;
    }

    public UntrustedInfoBean getUntrustedInfo() {
        return untrustedInfo;
    }

    public void setUntrustedInfo(UntrustedInfoBean untrustedInfo) {
        this.untrustedInfo = untrustedInfo;
    }

    public SuspiciousIdcardBean getSuspiciousIdcard() {
        return suspiciousIdcard;
    }

    public void setSuspiciousIdcard(SuspiciousIdcardBean suspiciousIdcard) {
        this.suspiciousIdcard = suspiciousIdcard;
    }

    public SuspiciousMobileBean getSuspiciousMobile() {
        return suspiciousMobile;
    }

    public void setSuspiciousMobile(SuspiciousMobileBean suspiciousMobile) {
        this.suspiciousMobile = suspiciousMobile;
    }

    public SuspiciousDeviceBean getSuspiciousDevice() {
        return suspiciousDevice;
    }

    public void setSuspiciousDevice(SuspiciousDeviceBean suspiciousDevice) {
        this.suspiciousDevice = suspiciousDevice;
    }

    public RiskQqgroupBean getRiskQqgroup() {
        return riskQqgroup;
    }

    public void setRiskQqgroup(RiskQqgroupBean riskQqgroup) {
        this.riskQqgroup = riskQqgroup;
    }
}

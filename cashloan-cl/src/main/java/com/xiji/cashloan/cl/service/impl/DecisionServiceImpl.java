package com.xiji.cashloan.cl.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiji.cashloan.cl.domain.*;
import com.xiji.cashloan.cl.mapper.*;
import com.xiji.cashloan.cl.service.DecisionService;
import com.xiji.cashloan.cl.service.impl.assist.blacklist.BlacklistConstant;
import com.xiji.cashloan.cl.util.CallsOutSideFeeConstant;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.core.common.util.ShardTableUtil;
import com.xiji.cashloan.core.common.util.StringUtil;
import com.xiji.cashloan.core.domain.Borrow;
import com.xiji.cashloan.core.domain.UserBaseInfo;
import com.xiji.cashloan.core.mapper.UserBaseInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by szb on 19/1/12.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DecisionServiceImpl extends BaseServiceImpl<Decision, Long> implements DecisionService {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Resource
    private UserBaseInfoMapper userBaseInfoMapper;
    @Resource
    private OperatorReportMapper operatorReportMapper;
    @Resource
    private BorrowOperatorLogMapper borrowOperatorLogMapper;
    @Resource
    private UserContactsMapper userContactsMapper;
    @Resource
    private YixinRiskReportMapper yixinRiskReportMapper;
    @Resource
    private MagicReqLogMapper magicReqLogMapper;
    @Resource
    private MagicBlackGrayMapper magicBlackGrayMapper;
    @Resource
    private MagicSuspiciousIdcardMapper magicSuspiciousIdcardMapper;
    @Resource
    private MagicSuspiciousMobileMapper magicSuspiciousMobileMapper;
    @Resource
    private MagicSuspiciousDeviceMapper magicSuspiciousDeviceMapper;
    @Resource
    private MagicFraudulenceInfoMapper magicFraudulenceInfoMapper;
    @Resource
    private BlacklistXindeDataMapper blacklistXindeDataMapper;
    @Resource
    private BlacklistCommonDataMapper blacklistCommonDataMapper;
    @Resource
    private XinyanXwldMapper xinyanXwldMapper;
    @Resource
    private PinganGrayscaleMapper pinganGrayscaleMapper;
    @Resource
    private DecisionMapper decisionMapper;
    @Resource
    private YouDunRiskReportMapper youDunRiskReportMapper;
    @Resource
    private OperatorVoiceCntMapper operatorVoiceCntMapper;

    @Override
    public BaseMapper<Decision, Long> getMapper() {
        return decisionMapper;
    }


    @Override
    public int saveBorrowDecision(Borrow borrow) {
        int i = 0;
        Long borrowId = borrow.getId();
        Long userId = borrow.getUserId();

        Map<String, Object> queryMap = new HashMap<>();
        //基础信息
        Decision decision = new Decision();
        decision.setUserId(userId);
        decision.setBorrowId(borrowId);
        UserBaseInfo userBaseInfo = userBaseInfoMapper.findByUserId(userId);
        decision.setAge(userBaseInfo.getAge());
        decision.setCompanyName(userBaseInfo.getCompanyName());

        queryMap.put("userId", userId);
        String contactTableName = ShardTableUtil.generateTableNameById("cl_user_contacts", userId, 30000);
        int contactNum = userContactsMapper.countContacts(contactTableName, queryMap);
        queryMap.put("name", "sensitive");
        int contactSensitiveNum = userContactsMapper.countContacts(contactTableName, queryMap);
        decision.setContactNum(contactNum);
        decision.setContactSensitiveNum(contactSensitiveNum);

        queryMap.clear();
        queryMap.put("userId", userId);
        queryMap.put("sumDuration", 0);
        int matchingCnt = userContactsMapper.countContacts(contactTableName, queryMap);
        decision.setMxContactMatchingVoiceSituation(matchingCnt < 4 ? 1 : 0);

        //处理魔蝎运营商报告
        queryMap.clear();
        queryMap.put("borrowId", borrowId);
        BorrowOperatorLog borrowOperatorLog = borrowOperatorLogMapper.findSelective(queryMap);
        Long reqLogId = borrowOperatorLog.getReqLogId();

        queryMap.clear();
        queryMap.put("reqLogId", reqLogId);
        OperatorReport operatorReport = operatorReportMapper.findSelective(queryMap);
        setOperatorData(operatorReport, decision);

        //处理通话记录详情 TOP10是否包含12599
        queryMap.clear();
        queryMap.put("userId", userId);
        queryMap.put("reqLogId", reqLogId);
        String tableName = ShardTableUtil.generateTableNameById("cl_operator_voice_cnt", borrow.getUserId(), 30000);
        PageHelper.startPage(0, 10);
        Page<OperatorVoiceCnt> operatorVoiceCntPage = (Page<OperatorVoiceCnt>) operatorVoiceCntMapper
                .listShardSelective(tableName,queryMap);
        for (OperatorVoiceCnt operatorVoiceCnt : operatorVoiceCntPage) {
            if("12599".equals(operatorVoiceCnt.getPeerNum())) {
                decision.setMxVoiceHasSensitivePhone(1);
            }
        }

        //处理至诚宜信阿福报告
        queryMap.clear();
        queryMap.put("borrowId", borrowId);
        YixinRiskReport yixinRiskReport = yixinRiskReportMapper.findSelective(queryMap);
        setYXReportData(yixinRiskReport, decision);

        //处理魔杖黑灰名单
        queryMap.clear();
        queryMap.put("borrowId", borrowId);
        queryMap.put("type", CallsOutSideFeeConstant.CALLS_TYPE_BLACK_GRAY);
        MagicReqLog blackGrayLog = magicReqLogMapper.findSelective(queryMap);
        if(blackGrayLog != null) {
            String transId = blackGrayLog.getTransId();
            queryMap.clear();
            queryMap.put("transId", transId);
            MagicBlackGray blackGray = magicBlackGrayMapper.findSelective(queryMap);
            setMXBlackGray(blackGray, decision);
        }

        //处理魔杖反欺诈
        queryMap.clear();
        queryMap.put("borrowId", borrowId);
        queryMap.put("type", CallsOutSideFeeConstant.CALLS_TYPE_ANTI_FRAUD);
        MagicReqLog antiFraudLog = magicReqLogMapper.findSelective(queryMap);
        if(antiFraudLog != null) {
            String transId = antiFraudLog.getTransId();
            queryMap.clear();
            queryMap.put("transId", transId);
            MagicSuspiciousIdcard magicSuspiciousIdcard = magicSuspiciousIdcardMapper.findSelective(queryMap);
            setMXSuspiciousIdcard(magicSuspiciousIdcard, decision);
            MagicSuspiciousMobile magicSuspiciousMobile = magicSuspiciousMobileMapper.findSelective(queryMap);
            setMXSuspiciousMobile(magicSuspiciousMobile, decision);
            MagicSuspiciousDevice magicSuspiciousDevice = magicSuspiciousDeviceMapper.findSelective(queryMap);
            setMXSuspiciousDevice(magicSuspiciousDevice, decision);
            MagicFraudulenceInfo magicFraudulenceInfo = magicFraudulenceInfoMapper.findSelective(queryMap);
            setMXFraudulenceInfo(magicFraudulenceInfo, decision);
        }

        //处理信德数据
        queryMap.clear();
        queryMap.put("borrowId", borrowId);
        BlacklistXindeData blacklistXindeData = blacklistXindeDataMapper.findSelective(queryMap);
        setXDBlackData(blacklistXindeData, decision);

        //处理拍拍信数据
        queryMap.clear();
        queryMap.put("borrowId", borrowId);
        queryMap.put("source", BlacklistConstant.source_ppx);
        BlacklistCommonData blacklistCommonData = blacklistCommonDataMapper.findSelective(queryMap);
        setPPXBlackData(blacklistCommonData, decision);

        //处理新颜行为雷达
        queryMap.clear();
        queryMap.put("borrowId", borrowId);
        XinyanXwld xinyanXwld = xinyanXwldMapper.findSelective(queryMap);
        setXinyanXwld(xinyanXwld, decision);

        //处理凭安染黑数据
        queryMap.clear();
        queryMap.put("borrowId", borrowId);
        PinganGrayscale pinganGrayscale = pinganGrayscaleMapper.findSelective(queryMap);
        setPinganGrayscale(pinganGrayscale, decision);

        //处理有盾数据
        queryMap.clear();
        queryMap.put("borrowId", borrowId);
        YouDunRiskReport youDunRiskReport = youDunRiskReportMapper.findSelective(queryMap);
        setYoudunRisk(youDunRiskReport, decision, yixinRiskReport, xinyanXwld);

        i = decisionMapper.saveSelective(decision);
        return i;
    }

    private void setYXReportData(YixinRiskReport yixinRiskReport, Decision decision) {
        if(yixinRiskReport != null) {
            // 历史逾期数
            int countOverdueHistory = 0;
            // M3历史逾期数
            int countOverdueHistoryM3 = 0;
            // M6历史逾期数
            int countOverdueHistoryM6 = 0;
            // 借款申请笔数
            int countBorrowApply = 0;
            // 借款申请已同意数
            int countApprovalAccept = 0;
            // 借贷正常订单数量
            int countNormal = 0;
            JSONArray loanRecordsJsonArray = JSON.parseObject(yixinRiskReport.getData()).getJSONArray("loanRecords");
            if (loanRecordsJsonArray != null){
                Iterator iterator = loanRecordsJsonArray.iterator();
                while (iterator.hasNext()){
                    countBorrowApply = countBorrowApply +1;
                    String str = iterator.next().toString();
                    JSONObject json = JSON.parseObject(str);
                    if (json.get("approvalStatus") != null){
                        String result = json.get("approvalStatus").toString();
                        if ("ACCEPT".equals(result)){
                            countApprovalAccept = countApprovalAccept +1;
                        }
                    }
                    if (json.get("overdueM3") != null) {
                        countOverdueHistoryM3 = countOverdueHistoryM3 +1;
                    }
                    if (json.get("overdueM6") != null) {
                        countOverdueHistoryM6 = countOverdueHistoryM6 +1;
                    }
                    if (StringUtil.isNotBlank(json.getString("overdueStatus"))) {
                        countOverdueHistory = countOverdueHistory + 1;
                    }

                }
            }
            decision.setYxOverdueHistoryCount(countOverdueHistory);
            decision.setYxOverdueHistoryM3Count(countOverdueHistoryM3);
            decision.setYxOverdueHistoryM6Count(countOverdueHistoryM6);
            //申请借款数量大于20且放款数量为0
            if(countBorrowApply > 20 && countApprovalAccept == 0) {
                decision.setYxAm20NoAccept(1);
            }
        }
    }

    private void setMXBlackGray(MagicBlackGray blackGray, Decision decision) {
        if(blackGray != null) {
            decision.setMxBlackIdcardNameInBlacklist(blackGray.getBlackIdcardNameInBlacklist());
            decision.setMxBlackMobileNameInBlacklist(blackGray.getBlackMobileNameInBlacklist());
            decision.setMxGrayIdcardNameInBlacklist(blackGray.getGrayIdcardNameInBlacklist());
            decision.setMxGrayMobileNameInBlacklist(blackGray.getGrayIdcardNameInBlacklist());
        }
    }

    private void setMXSuspiciousIdcard(MagicSuspiciousIdcard magicSuspiciousIdcard, Decision decision) {
        if(magicSuspiciousIdcard != null) {
            decision.setMxIdcardOtherNamesCnt(magicSuspiciousIdcard.getOtherNamesCnt());
            decision.setMxIdcardOtherMobilesCnt(magicSuspiciousIdcard.getOtherMobilesCnt());
            decision.setMxIdcardOtherMobilesBlackCnt(magicSuspiciousIdcard.getOtherMobilesBlackCnt());
        }
    }

    private void setMXSuspiciousMobile(MagicSuspiciousMobile magicSuspiciousMobile, Decision decision) {
        if(magicSuspiciousMobile != null) {
            decision.setMxMobileOtherIdcardsCnt(magicSuspiciousMobile.getOtherIdcardsCnt());
            decision.setMxMobileOtherNamesCnt(magicSuspiciousMobile.getOtherNamesCnt());
            decision.setMxMobileOtherIdcardsBlackCnt(magicSuspiciousMobile.getOtherIdcardsBlackCnt());
        }
    }

    private void setMXSuspiciousDevice(MagicSuspiciousDevice magicSuspiciousDevice, Decision decision) {
        if(magicSuspiciousDevice != null) {
            decision.setMxOtherDevicesCnt(magicSuspiciousDevice.getOtherDevicesCnt());
            decision.setMxMobileOtherDevicesCnt(magicSuspiciousDevice.getMobileOtherDevicesCnt());
            decision.setMxIdcardOtherDevicesCnt(magicSuspiciousDevice.getIdcardOtherDevicesCnt());
            decision.setMxDeviceOtherNamesCnt(magicSuspiciousDevice.getOtherNamesCnt());
            decision.setMxDeviceOtherMobilesCnt(magicSuspiciousDevice.getOtherMobilesCnt());
            decision.setMxDeviceOtherMobilesBlackCnt(magicSuspiciousDevice.getOtherMobilesBlackCnt());
            decision.setMxDeviceOtherIdcardsCnt(magicSuspiciousDevice.getOtherIdcardsCnt());
            decision.setMxDeviceOtherIdcardsBlackCnt(magicSuspiciousDevice.getOtherIdcardsBlackCnt());
        }
    }

    private void setMXFraudulenceInfo(MagicFraudulenceInfo magicFraudulenceInfo, Decision decision) {
        if(magicFraudulenceInfo != null) {
            decision.setMxFraudIsHit(magicFraudulenceInfo.getIsHit());
            decision.setMxFraudHitType(magicFraudulenceInfo.getType());
        }
    }

    private void setXDBlackData(BlacklistXindeData blacklistXindeData, Decision decision) {
        if(blacklistXindeData != null) {
            decision.setXdIsLastloanRefused(blacklistXindeData.getIsLastloanRefused());
            decision.setXdTotalLoanCount(blacklistXindeData.getTotalLoanCount());
            decision.setXdTotalOverdueCount(blacklistXindeData.getTotalOverdueCount());
            decision.setXdLongestOverduePaid(blacklistXindeData.getLongestOverduePaid());
            decision.setXdCurrentOverdueCount(blacklistXindeData.getCurrentOverdueCount());
            decision.setXdCurrentOverdueAmount(blacklistXindeData.getCurrentOverdueAmount());
            decision.setXdOverDue90ContactsCount(blacklistXindeData.getOverDue90ContactsCount());
            decision.setXdLongestOverdueUnpaid(blacklistXindeData.getLongestOverdueUnpaid());
            decision.setXdLastLoanRefuseTime(blacklistXindeData.getLastLoanRefuseTime());
            decision.setXdLastLoanRefuseReason(blacklistXindeData.getLastLoanRefuseReason());
            decision.setXdFirstLoanTime(blacklistXindeData.getFirstLoanTime());
            decision.setXdRemark(blacklistXindeData.getRemark());
        }
    }

    private void setPPXBlackData(BlacklistCommonData blacklistCommonData, Decision decision) {
        if(blacklistCommonData != null) {
            JSONObject resJson = JSONObject.parseObject(blacklistCommonData.getDataMsg());
            if (resJson != null) {
                JSONObject blacksum = resJson.getJSONObject("blackSummary");
                if (blacksum != null) {
                    JSONObject hkxw = blacksum.getJSONObject("HKXW");
                    if (hkxw != null) {
                        decision.setPpxOverdueFirstTime(hkxw.getString("HK001"));
                        decision.setPpxOverdueLastTime(hkxw.getString("HK002"));
                        decision.setPpxTotalOverdueCount(Integer.valueOf(hkxw.getString("HK003")));
                        decision.setPpxCurrentOverdueAmount(Integer.valueOf(hkxw.getString("HK004")));
                        decision.setPpxCurrentOverdueDays(Integer.valueOf(hkxw.getString("HK005")));
                        decision.setPpxHistoryOverdueAmount(Integer.valueOf(hkxw.getString("HK006")));
                        decision.setPpxHistoryOverdueDays(Integer.valueOf(hkxw.getString("HK007")));
                    }

                    JSONObject lsqz = blacksum.getJSONObject("LSQZ");
                    if (lsqz != null) {
                        decision.setPpxFraudFirstTime(lsqz.getString("QZ001"));
                        decision.setPpxFraudLastTime(lsqz.getString("QZ001"));
                        decision.setPpxTotalFraudCount(Integer.valueOf(lsqz.getString("QZ003")));
                    }

                    JSONObject zffm = blacksum.getJSONObject("ZFFM");
                    if (zffm != null) {
                        decision.setPpxNegativeFirstTime(zffm.getString("FM001"));
                        decision.setPpxNegativeLastTime(zffm.getString("FM002"));
                        decision.setPpxTotalNegativeCount(Integer.valueOf(zffm.getString("FM003")));
                    }
                }
                String isBlack = resJson.getString("isBlack");
                if (StringUtil.equals("1", isBlack)) {
                    decision.setPpxIsBlack(1);
                }
                String isAlert = resJson.getString("isAlert");
                if (StringUtil.equals("1", isAlert)) {
                    decision.setPpxIsAlert(1);
                }
            }
        }
    }

    private void setXinyanXwld(XinyanXwld xinyanXwld, Decision decision) {
        if(xinyanXwld != null && xinyanXwld.getData() != null) {
            JSONObject dataJson = JSONObject.parseObject(xinyanXwld.getData());
            if(dataJson == null){
                return ;
            }
            decision.setXyLoansScore(dataJson.getString("loans_score"));
            decision.setXyLoansCredibility(dataJson.getString("loans_credibility"));
            decision.setXyLoansCount(dataJson.getString("loans_count"));
            decision.setXyLoansSettleCount(dataJson.getString("loans_settle_count"));
            decision.setXyLoansOverdueCount(dataJson.getString("loans_overdue_count"));
            decision.setXyLoansOrgCount(dataJson.getString("loans_org_count"));
            decision.setXyConsfinOrgCount(dataJson.getString("consfin_org_count"));
            decision.setXyLoansCashCount(dataJson.getString("loans_cash_count"));
            decision.setXyLatestOneMonth(dataJson.getString("latest_one_month"));
            decision.setXyLatestThreeMonth(dataJson.getString("latest_three_month"));
            decision.setXyLatestSixMonth(dataJson.getString("latest_six_month"));
            decision.setXyHistorySucFee(dataJson.getString("history_suc_fee"));
            decision.setXyHistoryFailFee(dataJson.getString("history_fail_fee"));
            decision.setXyLatestOneMonthSuc(dataJson.getString("latest_one_month_suc"));
            decision.setXyLatestOneMonthFail(dataJson.getString("latest_one_month_fail"));
            decision.setXyLoansLongTime(dataJson.getString("loans_long_time"));
            decision.setXyLoansLatestTime(dataJson.getString("loans_latest_time"));
            if(StringUtil.isNumber(dataJson.getString("history_suc_fee")) &&
                    StringUtil.isNumber(dataJson.getString("history_fail_fee"))) {
                //历史扣款成功笔数-失败笔数
                int historyNum = Integer.valueOf(dataJson.getString("history_suc_fee")) -
                        Integer.valueOf(dataJson.getString("history_fail_fee"));
                decision.setXyHistorySucMinusFailNum(historyNum);
            }
            if(StringUtil.isNumber(dataJson.getString("history_suc_fee")) &&
                    StringUtil.isNumber(dataJson.getString("history_fail_fee"))) {
                //近一个月扣款成功笔数-失败笔数
                int latestMonthNum = Integer.valueOf(dataJson.getString("latest_one_month_suc")) -
                        Integer.valueOf(dataJson.getString("latest_one_month_fail"));
                decision.setXyLatestOneMonthSucMinusFailNum(latestMonthNum);
            }
        }
    }

    private void setPinganGrayscale(PinganGrayscale pinganGrayscale, Decision decision) {
        if(pinganGrayscale != null && pinganGrayscale.getData() != null) {
            JSONObject dataJson = JSONObject.parseObject(pinganGrayscale.getData());
            if (dataJson == null){
                return;
            }
            decision.setPaL1wwdcnTNumsCon(dataJson.getInteger("l1wwdcn_TNumsCon"));
            decision.setPaL1wwdcnTNumsConBank(dataJson.getInteger("l1wwdcn_TNumsCon_bank"));
            decision.setPaL1wwdcnTNumsConCf(dataJson.getInteger("l1wwdcn_TNumsCon_cf"));
            decision.setPaL1wwdcnTNumsConF(dataJson.getInteger("l1wwdcn_TNumsCon_f"));
            decision.setPaL1wwdcnTNumsConIf(dataJson.getInteger("l1wwdcn_TNumsCon_if"));
            decision.setPaL1wwdcnTNumsConOrg(dataJson.getInteger("l1wwdcn_TNumsCon_if"));
            decision.setPaL1wwdcnTTimesIn(dataJson.getInteger("l1wwdcn_TTimesIn"));
            decision.setPaL1wwdcnTTimesOut(dataJson.getInteger("l1wwdcn_TTimesOut"));
            decision.setPaL2wwdcnTNumsConOrgType(dataJson.getInteger("l2wwdcn_TNumsCon_orgtype"));
            decision.setPaL3wwdcnTNumsConIf(dataJson.getInteger("l3wwdcn_TNumsCon_if"));
            decision.setPaL1mwdcnTNumsCon(dataJson.getInteger("l1mwdcn_TNumsCon"));
            decision.setPaL1mwdcnTNumsConBank(dataJson.getInteger("l1mwdcn_TNumsCon_bank"));
            decision.setPaL1mwdcnTNumsConCf(dataJson.getInteger("l1mwdcn_TNumsCon_cf"));
            decision.setPaL1mwdcnTNumsConF(dataJson.getInteger("l1mwdcn_TNumsCon_f"));
            decision.setPaL1mwdcnTNumsConIf(dataJson.getInteger("l1mwdcn_TNumsCon_if"));
            decision.setPaL1mwdcnTNumsConOrg(dataJson.getInteger("l1mwdcn_TNumsCon_org"));
            decision.setPaL1mwdcnTTimesIn(dataJson.getInteger("l1mwdcn_TTimesIn"));
            decision.setPaL1mwdcnTTimesOut(dataJson.getInteger("l1mwdcn_TTimesOut"));

            decision.setPaL2mwdcnTTimesIn(dataJson.getInteger("l2mwdcn_TTimesIn"));
            decision.setPaL2mwdcnTNumsIn(dataJson.getInteger("l2mwdcn_TNumsIn"));
            decision.setPaL2mwdcnTaxTimesIn(dataJson.getInteger("l2mwdcn_MaxTimesIn"));
            decision.setPaL2mwdcnMaxTimesCon(dataJson.getInteger("l2mwdcn_MaxTimesCon"));

            decision.setPaL2mencnTTaysCon(dataJson.getInteger("l2mencn_TDaysCon"));

            decision.setPaL3mwdcnTNumsCon(dataJson.getInteger("l3mwdcn_TNumsCon"));
            decision.setPaL3mwdcnTNumsConBank(dataJson.getInteger("l3mwdcn_TNumsCon_bank"));
            decision.setPaL3mwdcnTNumsConCf(dataJson.getInteger("l3mwdcn_TNumsCon_cf"));
            decision.setPaL3mwdcnTNumsConF(dataJson.getInteger("l3mwdcn_TNumsCon_f"));
            decision.setPaL3mwdcnTNumsConIf(dataJson.getInteger("l3mwdcn_TNumsCon_if"));
            decision.setPaL3mencnTNumsConOrg(dataJson.getInteger("l3mencn_TNumsCon_org"));
            decision.setPaL3mwdcnTNumsConOrg(dataJson.getInteger("l3mwdcn_TNumsCon_org"));
            decision.setPaL3mwdcnTTimesIn(dataJson.getInteger("l3mwdcn_TTimesIn"));
            decision.setPaL3mwdcnTTimesOut(dataJson.getInteger("l3mwdcn_TTimesOut"));

            decision.setPaL3mwdcnAddTNumsInOrg(dataJson.getInteger("l3mwdcn_AddTNumsIn_org"));
            decision.setPaL3mwdcnMaxDaysIn(dataJson.getInteger("l3mwdcn_MaxDaysIn"));

            decision.setPaL4mwdcnTNumsConOrgType(dataJson.getInteger("l4mwdcn_TNumsCon_orgtype"));
            decision.setPaL4mwdcnTTimesInNonBank(dataJson.getInteger("l4mwdcn_TTimesIn_nonbank"));
            decision.setPaL4mwdcnTDurCon(dataJson.getInteger("l4mwdcn_TDurCon"));
            decision.setPaL4mwdcnTTimesCon(dataJson.getInteger("l4mwdcn_TTimesCon"));
            decision.setPaL4mwdcnTNumsConF(dataJson.getInteger("l4mwdcn_TNumsCon_f"));
            decision.setPaL4mwdcnTDurIn(dataJson.getInteger("l4mwdcn_TDurIn"));
            decision.setPaL4mwdcnTDurInMaxTimes(dataJson.getInteger("l4mwdcn_TDurIn_MaxTimes"));
            decision.setPaL4mwdcnTNumsInF(dataJson.getInteger("l4mwdcn_TNumsIn_f"));

            decision.setPaL5mwdcnTDaysCon(dataJson.getInteger("l5mwdcn_TDaysCon"));
            decision.setPaL5mwdcnTDurInF(dataJson.getInteger("l5mwdcn_TDurIn_f"));
            decision.setPaL5mwdcmTDurCon(dataJson.getInteger("l5mwdcm_TDurCon"));
            decision.setPaL5mwdcnTNumsConIf(dataJson.getInteger("l5mwdcn_TNumsCon_if"));

            decision.setPaAllwdcnTNumsCon(dataJson.getInteger("allwdcn_TNumsCon"));
            decision.setPaAllwdcnTNumsConBank(dataJson.getInteger("allwdcn_TNumsCon_bank"));
            decision.setPaAllwdcnTNumsConCf(dataJson.getInteger("allwdcn_TNumsCon_cf"));
            decision.setPaAllwdcnTNumsConF(dataJson.getInteger("allwdcn_TNumsCon_f"));
            decision.setPaAllwdcnTNumsConIf(dataJson.getInteger("allwdcn_TNumsCon_if"));
            decision.setPaAllwdcnTNumsConOrg(dataJson.getInteger("allwdcn_TNumsCon_org"));
            decision.setPaAllwdcnTTimesIn(dataJson.getInteger("allwdcn_TTimesIn"));
            decision.setPaAllwdcnTTimesOut(dataJson.getInteger("allwdcn_TTimesOut"));
            decision.setPaAllwdcnTDurInF(dataJson.getInteger("allwdcn_TDurIn_f"));

            decision.setPaAllwdcmMaxOrgTypeIn(dataJson.getInteger("allwdcm_MaxOrgtypeIn"));
            decision.setPaAlldtcnwkAdur(dataJson.getInteger("alldtcnwk_Adur"));
            decision.setPaAllwdcnAdur(dataJson.getInteger("allwdcn_Adur"));
            decision.setPaL1mdtcnAdur(dataJson.getInteger("l1mdtcn_Adur"));
            decision.setPaL1mwdcnAdur(dataJson.getInteger("l1mwdcn_Adur"));
            decision.setPaAllmncnrtNumsCon(dataJson.getInteger("allmncnrt_numsCon"));
            decision.setPaL1wwdcnrtNumsCon(dataJson.getInteger("l1wwdcnrt_numsCon"));
            decision.setPaL2mwdcnNumsCon(dataJson.getInteger("l2mwdcn_numsCon"));
            decision.setPaL2wwdcnwkNumsCon(dataJson.getInteger("l2wwdcnwk_numsCon"));
            decision.setPaAllwdcnADurOneNums(dataJson.getInteger("allwdcn_ADurOneNums"));
            decision.setPaL4mwdcnADurOneNums(dataJson.getInteger("l4mwdcn_ADurOneNums"));
            decision.setPaL6mwdcnADurOneNums(dataJson.getInteger("l6mwdcn_ADurOneNums"));
            decision.setPaL1mdtcnwkRankCount(dataJson.getInteger("l1mdtcnwk_RankCount"));
            decision.setPaL1wdtcnrtRankCount(dataJson.getInteger("l1wdtcnrt_RankCount"));
            decision.setPaL2mdtcnRankCount(dataJson.getInteger("l2mdtcn_RankCount"));

            decision.setPaL3wwdcnwkRankCount(dataJson.getInteger("l3wwdcnwk_RankCount"));
            decision.setPaL1mevcnrtSepcialHourCount(dataJson.getInteger("l1mevcnrt_SepcialhourCount"));
            decision.setPaL1mmncnwkSepcialHourCount(dataJson.getInteger("l1mmncnwk_SepcialhourCount"));
            decision.setPaL1wevcnSepcialHourCount(dataJson.getInteger("l1wevcn_SepcialhourCount"));
            decision.setPaL1wwdcnrtSepcialHourCount(dataJson.getInteger("l1wwdcnrt_SepcialhourCount"));
            decision.setPaL2mevcnSepcialHourCount(dataJson.getInteger("l2mevcn_SepcialhourCount"));
            decision.setPaL2mevcnrtSepcialHourCount(dataJson.getInteger("l2mevcnrt_SepcialhourCount"));
            decision.setPaL2mwdcnSepcialHourCount(dataJson.getInteger("l2mwdcn_SepcialhourCount"));
            decision.setPaL3mevcnSepcialHourCount(dataJson.getInteger("l3mevcn_SepcialhourCount"));

            decision.setPaL3mwdcnrtSepcialHourCount(dataJson.getInteger("l3mwdcnrt_SepcialhourCount"));
            decision.setPaL6mwdcnwkSepcialHourCount(dataJson.getInteger("l6mwdcnwk_SepcialhourCount"));
            decision.setPaL2mdtcnActiveDaysCount(dataJson.getInteger("l2mdtcn_ActivedaysCount"));
            decision.setPaL2wwdcnActiveDaysCount(dataJson.getInteger("l2wwdcn_ActivedaysCount"));
            decision.setPaL3mwdcnrtActiveDaysCount(dataJson.getInteger("l3mwdcnrt_ActivedaysCount"));
            decision.setPaL2wdtcnMaxTimesIn(dataJson.getInteger("l2wdtcn_MaxTimesIn"));
            decision.setPaL2wevcnMaxDaysOfOneNumberCount(dataJson.getInteger("l2wevcn_MaxdaysofOnenumberCount"));
            decision.setPaL3mwdcnrtMaxDaysOfOneNumberCount(dataJson.getInteger("l3mwdcnrt_MaxdaysofOnenumberCount"));
            decision.setPaL3wwdcnMaxDaysOfOneNumberCount(dataJson.getInteger("l3wwdcn_MaxdaysofOnenumberCount"));

            decision.setPaL2wwdcnADurOneNumsIn(dataJson.getInteger("l2wwdcn_ADurOneNumsIn"));
            decision.setPaL2wwdcnTTimesIn(dataJson.getInteger("l2wwdcn_TTimesIn"));
            decision.setPaL3mwdcnADurIn(dataJson.getInteger("l3mwdcn_AdurIn"));
            decision.setPaL1mevcnrtTDur(dataJson.getInteger("l1mevcnrt_TDur"));
            decision.setPaL1mmncnwkTDur(dataJson.getInteger("l1mmncnwk_TDur"));
            decision.setPaL2mevcnTDur(dataJson.getInteger("l2mevcn_TDur"));
            decision.setPaL2mwdcnrtTDur(dataJson.getInteger("l2mwdcnrt_TDur"));
            decision.setPaL3mevcnTDur(dataJson.getInteger("l3mevcn_TDur"));
            decision.setPaL3mwdcnrtTDur(dataJson.getInteger("l3mwdcnrt_TDur"));
            decision.setPaL6mwdcnwkTDur(dataJson.getInteger("l6mwdcnwk_TDur"));

            decision.setPaL4mwdcnTDur(dataJson.getInteger("l4mwdcn_TDur"));
            decision.setPaL6mwdcnMinTtvDaysIn(dataJson.getInteger("l6mwdcn_MinItvDaysIn"));
            decision.setPaL6mwdcnTDurF(dataJson.getInteger("l6mwdcn_TDur_f"));
            decision.setPaAll25acfriDur(dataJson.getInteger("all25acfri_Dur"));
            decision.setPaAllwdacwkDur(dataJson.getInteger("allwdacwk_Dur"));
            decision.setPaAll25acTimes(dataJson.getInteger("all25ac_Times"));
        }
    }

    private void setYoudunRisk(YouDunRiskReport youDunRiskReport, Decision decision, YixinRiskReport yixinRiskReport, XinyanXwld xinyanXwld) {
        if(youDunRiskReport != null && youDunRiskReport.getData() != null) {
            JSONObject dataJson = JSONObject.parseObject(youDunRiskReport.getData());
            if (dataJson == null) {
                return;
            }
            if(dataJson.getJSONObject("loan_detail") != null) {
                JSONObject loanDetail = dataJson.getJSONObject("loan_detail");
                decision.setYdActualLoanPlatformCount(loanDetail.getInteger("actual_loan_platform_count"));
                decision.setYdActualLoanPlatformCount1m(loanDetail.getInteger("actual_loan_platform_count_1m"));
                decision.setYdActualLoanPlatformCount3m(loanDetail.getInteger("actual_loan_platform_count_3m"));
                decision.setYdActualLoanPlatformCount6m(loanDetail.getInteger("actual_loan_platform_count_6m"));
                decision.setYdLoanPlatformCount(loanDetail.getInteger("loan_platform_count"));
                decision.setYdLoanPlatformCount1m(loanDetail.getInteger("loan_platform_count_1m"));
                decision.setYdLoanPlatformCount3m(loanDetail.getInteger("loan_platform_count_3m"));
                decision.setYdLoanPlatformCount6m(loanDetail.getInteger("loan_platform_count_6m"));
                decision.setYdRepaymentPlatformCount(loanDetail.getInteger("repayment_platform_count"));
                decision.setYdRepaymentPlatformCount1m(loanDetail.getInteger("repayment_platform_count_1m"));
                decision.setYdRepaymentPlatformCount3m(loanDetail.getInteger("repayment_platform_count_3m"));
                decision.setYdRepaymentPlatformCount6m(loanDetail.getInteger("repayment_platform_count_6m"));
                decision.setYdRepaymentTimesCount(loanDetail.getInteger("repayment_times_count"));
            }
            if(dataJson.getJSONObject("score_detail") != null) {
                JSONObject scoreDetail = dataJson.getJSONObject("score_detail");
                decision.setYdRiskEvaluation(scoreDetail.getString("risk_evaluation"));
                decision.setYdScore(scoreDetail.getInteger("score"));
            }

            // 借贷正常订单数量
            int countApprovalAccept = 0;

            if(yixinRiskReport != null) {
                int countNormal = 0;
                JSONArray loanRecordsJsonArray = JSON.parseObject(yixinRiskReport.getData()).getJSONArray("loanRecords");
                if (loanRecordsJsonArray != null) {
                    Iterator iterator = loanRecordsJsonArray.iterator();
                    while (iterator.hasNext()) {
                        String str = iterator.next().toString();
                        JSONObject json = JSON.parseObject(str);
                        if (json.get("approvalStatus") != null) {
                            String result = json.get("approvalStatus").toString();
                            if ("ACCEPT".equals(result)) {
                                countApprovalAccept = countApprovalAccept++;
                            }
                        }
                        if (json.get("loanStatus") != null) {
                            if ("NORMAL".equals(json.get("loanStatus").toString())) {
                                countNormal++;
                            }
                        }
                    }
                }
                //借贷正常N笔以上 且借贷多头近3月申请平台大于M家
                int ydLoanPlatformCount3m = decision.getYdLoanPlatformCount3m() == null ? 0 : decision.getYdLoanPlatformCount3m();
                if (countNormal > 8 && ydLoanPlatformCount3m > 50) {
                    decision.setYxLoaningAm3m(1);
                }

                //新颜无数据,阿福无下款数据,有盾借贷多头半年未下款且有盾1个月申请平台大于30
                int ydLoanPlatformCount1m = decision.getYdLoanPlatformCount1m() == null ? 0 : decision.getYdLoanPlatformCount1m();
                int ydActualLoanPlatformCount6m = decision.getYdActualLoanPlatformCount6m() == null ? 0 : decision.getYdActualLoanPlatformCount6m();
                if((xinyanXwld == null || xinyanXwld.getData() == null) && countApprovalAccept == 0 && ydActualLoanPlatformCount6m == 0 && ydLoanPlatformCount1m > 30) {
                    decision.setYxYdNoLoan(1);
                }
            }

            //是否命中有盾拒绝风险项--关联过多
            int ydRefusedFeature = 0;
            if(dataJson.getJSONArray("user_features") != null) {
                JSONArray userFeatures = dataJson.getJSONArray("user_features");
                for (Object userFeature : userFeatures) {
                    JSONObject featureJson = JSON.parseObject(userFeature.toString());
                    String userFeatureType = featureJson.getString("user_feature_type");
                    if("8".equals(userFeatureType)) {
                        ydRefusedFeature = 1;
                        break;
                    }
                }
            }
            decision.setYdRefusedFeature(ydRefusedFeature);

        }
    }

    private void setOperatorData(OperatorReport operatorReport, Decision decision) {
        if (operatorReport != null) {
            String report = operatorReport.getReport();
            if (StringUtil.isNotBlank(report)) {
                JSONObject reportJson = JSONObject.parseObject(report);

                //处理用户基本信息
                JSONArray userBasicArray = reportJson.getJSONArray("user_basic");
                for (Object obj : userBasicArray) {
                    JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(obj));
                    String key = jsonObject.getString("key");
                    String value = jsonObject.getString("value");
                    switch (key) {
                        case "province":
                            decision.setMxProvince(value);
                            break;
                        case "city":
                            decision.setMxCity(value);
                            break;
                        case "region":
                            decision.setMxRegion(value);
                            break;
                        case "native_place":
                            decision.setMxNativePlace(value);
                            break;
                        case "bill_certification_day":
                            decision.setMxBillCertificationDay(value);
                            break;
                        default:
                            break;
                    }
                }

                JSONArray cellPhoneArray = reportJson.getJSONArray("cell_phone");
                for (Object obj : cellPhoneArray) {
                    JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(obj));
                    if ("reliability".equals(jsonObject.getString("key"))) {
                        String reliability = jsonObject.getString("value");
                        decision.setMxIsReliability("实名认证".equals(reliability) ? 1 : 0);
                    }
                    if ("in_time".equals(jsonObject.getString("key"))) {
                        String inTime = jsonObject.getString("value");
                        decision.setMxInTime(inTime);
                    }
                    if ("bill_certification_day".equals(jsonObject.getString("key"))) {
                        String billCertDay = jsonObject.getString("value");
                        decision.setMxBillCertificationDay(billCertDay);
                    }
                }
                //处理基础信息检测项
                JSONArray basicCheckItems = reportJson.getJSONArray("basic_check_items");
                for (Object obj : basicCheckItems) {
                    JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(obj));
                    String checkItem = jsonObject.getString("check_item");
                    String result = jsonObject.getString("result");
                    switch (checkItem) {
                        case "is_name_and_idcard_in_court_black":
                            decision.setMxIsNameAndIdcardInCourtBlack("否".equals(result) ? 0 : 1);
                            break;
                        case "is_name_and_idcard_in_finance_black":
                            decision.setMxIsNameAndIdcardInFinanceBlack("否".equals(result) ? 0 : 1);
                            break;
                        case "is_name_and_mobile_in_finance_black":
                            decision.setMxIsNameAndMobileInFinanceBlack("否".equals(result) ? 0 : 1);
                            break;
                        case "idcard_check":
                            decision.setMxIdcardCheck(result);
                            break;
                        case "email_check":
                            decision.setMxEmailCheck(result);
                            break;
                        case "address_check":
                            decision.setMxAddressCheck(result);
                            break;
                        case "call_data_check":
                            decision.setMxCallDataCheck(result);
                            break;
                        case "idcard_match":
                            decision.setMxIdcardMatch(result);
                            break;
                        case "name_match":
                            decision.setMxNameMatch(result);
                            break;
                        case "mobile_silence_3m":
                            decision.setMxMobileSilence3m(result);
                            break;
                        case "mobile_silence_6m":
                            decision.setMxMobileSilence6m(result);
                            break;
                        case "arrearage_risk_3m":
                            decision.setMxArrearageRisk3m(result);
                            break;
                        case "arrearage_risk_6m":
                            decision.setMxArrearageRisk6m(result);
                            break;
                        case "binding_risk":
                            decision.setMxBindingRisk(result);
                            break;
                        case "regular_circle":
                            decision.setMxRegularCircle(result);
                            break;
                        default:
                            break;
                    }
                }
                //用户信息
                JSONArray infoCheckArray = reportJson.getJSONArray("user_info_check");
                if(infoCheckArray != null) {
                    JSONObject infoCheckJson = JSONObject.parseObject(JSON.toJSONString(infoCheckArray.get(0)));
                    if(infoCheckJson != null) {
                        JSONObject searchJson = infoCheckJson.getJSONObject("check_search_info");
                        if(searchJson != null) {
                            decision.setMxSearchedOrgCnt(searchJson.getInteger("searched_org_cnt"));
                            decision.setMxSearchedOrgType(searchJson.getString("searched_org_type"));
                            decision.setMxIdcardWithOtherPhoneNum(searchJson.getJSONArray("idcard_with_other_phones").size());
                            decision.setMxIdcardWithOtherNames(searchJson.getJSONArray("idcard_with_other_names").toJSONString());
                            decision.setMxIdcardWithOtherPhones(searchJson.getJSONArray("idcard_with_other_phones").toJSONString());
                            decision.setMxPhoneWithOtherIdcards(searchJson.getJSONArray("phone_with_other_idcards").toJSONString());
                            decision.setMxPhoneWithOtherNames(searchJson.getJSONArray("phone_with_other_names").toJSONString());
                            decision.setMxRegisterOrgCnt(searchJson.getInteger("register_org_cnt"));
                            if (searchJson.getJSONArray("register_org_type") != null){
                                decision.setMxRegisterOrgType(searchJson.getJSONArray("register_org_type").toJSONString());
                            }
                            if (searchJson.getJSONArray("arised_open_web") != null){
                                decision.setMxArisedOpenWeb(searchJson.getJSONArray("arised_open_web").toJSONString());
                            }
                        }

                        JSONObject blackJson = infoCheckJson.getJSONObject("check_black_info");
                        if(blackJson != null) {
                            decision.setMxPhoneGrayScore(blackJson.getDouble("phone_gray_score"));
                            decision.setMxContactsClass1BlacklistCnt(blackJson.getInteger("contacts_class1_blacklist_cnt"));
                            decision.setMxContactsClass2BlacklistCnt(blackJson.getInteger("contacts_class2_blacklist_cnt"));
                            decision.setMxContactsClass1Cnt(blackJson.getInteger("contacts_class1_cnt"));
                            decision.setMxContactsRouterCnt(blackJson.getInteger("contacts_router_cnt"));
                            decision.setMxContactsRouterRatio(blackJson.getDouble("contacts_router_ratio"));
                        }
                    }
                }

                //运营商消费数据
                decision.setMxFiveMonthVoiceSituation(0);
                JSONArray cellBehaviorArray = reportJson.getJSONArray("cell_behavior");
                for (Object obj : cellBehaviorArray) {
                    JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(obj));
                    JSONArray beArray = jsonObject.getJSONArray("behavior");
                    if(beArray != null && beArray.size() > 0) {
                        int countDialNum = 0;
                        int countCellTime = 0;
                        int lessThan20Num = 0;
                        for (int i = 1; i < beArray.size(); i++) {
                            JSONObject beJson = JSONObject.parseObject(JSON.toJSONString(beArray.get(i)));
                            //主叫次数
                            countDialNum += beJson.getInteger("dial_cnt");
                            //主叫被叫呼叫总时间
                            countCellTime += beJson.getInteger("dial_time") + beJson.getInteger("dialed_time");
                            //总消费金额
                            Integer totalAmount = beJson.getInteger("total_amount");
                            if(totalAmount < 2000) {
                                lessThan20Num++;
                            }
                        }
                        decision.setMxFiveMonthBillLessThan20Num(lessThan20Num);
                        Double avgDialNum = Double.valueOf(df.format((double) countDialNum / 5));
                        Double avgCellTime = Double.valueOf(df.format((double) countCellTime / (60 * 5)));
                        if(avgDialNum < 20 && avgCellTime < 70) {
                            decision.setMxFiveMonthVoiceSituation(1);
                        }
                    }
                }

                //处理用户行为检测
                JSONArray behaviorCheckArray = reportJson.getJSONArray("behavior_check");
                if (behaviorCheckArray != null && behaviorCheckArray.size() > 0) {

                    for (Object obj : behaviorCheckArray) {
                        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(obj));
                        String checkPoint = jsonObject.getString("check_point");
                        String checkResult = jsonObject.getString("result");
                        if (StringUtil.isBlank(checkPoint) || StringUtil.isBlank(checkResult)){
                            continue;
                        }
                        switch (checkPoint) {
                            case "phone_silent":
                                if (checkResult.indexOf("天无通话记录") > -1) {
                                    decision.setMxPhoneNoVoiceDays(Integer.valueOf(checkResult.substring(6, checkResult.indexOf("天无通话记录"))));
                                    break;
                                }
                            case "contact_loan":
                                decision.setMxContactLoanSituation(checkResult.indexOf("经常被联系") > -1 ? 1 : 0);
                                decision.setMxContactLoan(checkResult);
                                break;
                            case "regular_circle":
                                decision.setMxRegularCircle(checkResult);
                                break;
                            case "phone_used_time":
                                decision.setMxPhoneUsedTime(checkResult);
                                break;
                            case "phone_power_off":
                                decision.setMxPhonePowerOff(checkResult);
                                break;
                            case "contact_each_other":
                                decision.setMxContactEachOther(checkResult);
                                break;
                            case "contact_macao":
                                decision.setMxContactMacao(checkResult);
                                break;
                            case "contact_110":
                                decision.setMxContact110(checkResult);
                                break;
                            case "contact_120":
                                decision.setMxContact120(checkResult);
                                break;
                            case "contact_lawyer":
                                decision.setMxContactLawyer(checkResult);
                                break;
                            case "contact_court":
                                decision.setMxContactCourt(checkResult);
                                break;
                            case "contact_bank":
                                decision.setMxContactBank(checkResult);
                                break;
                            case "contact_credit_card":
                                decision.setMxContactCreditCard(checkResult);
                                break;
                            case "contact_collection":
                                decision.setMxContactCollection(checkResult);
                                break;
                            case "contact_night":
                                decision.setMxContactNight(checkResult);
                                break;
                            case "phone_call":
                                decision.setMxPhoneCall(checkResult);
                                break;
                            default:
                                break;
                        }
                    }
                }
                //朋友圈信息
                JSONObject friendJson = reportJson.getJSONObject("friend_circle");
                if(friendJson != null) {
                    JSONArray summaryArray = friendJson.getJSONArray("summary");
                    for (Object obj : summaryArray) {
                        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(obj));
                        String key = jsonObject.getString("key");
                        String value = jsonObject.getString("value");
                        switch (key) {
                            case "friend_num_3m":
                                decision.setMxFriendNum3m(value);
                                break;
                            case "good_friend_num_3m":
                                decision.setMxGoodFriendNum3m(jsonObject.getString(value));
                                break;
                            case "friend_city_center_3m":
                                decision.setMxFriendCityCenter3m(jsonObject.getString(value));
                                break;
                            case "is_city_match_friend_city_center_3m":
                                decision.setMxIsCityMatchFriendCityCenter3m(jsonObject.getString(value));
                                break;
                            case "inter_peer_num_3m":
                                decision.setMxInterPeerNum3m(jsonObject.getString(value));
                                break;
                            case "friend_num_6m":
                                decision.setMxFriendNum6m(jsonObject.getString(value));
                                break;
                            case "good_friend_num_6m":
                                decision.setMxGoodFriendNum6m(jsonObject.getString(value));
                                break;
                            case "friend_city_center_6m":
                                decision.setMxFriendCityCenter6m(jsonObject.getString(value));
                                break;
                            case "is_city_match_friend_city_center_6m":
                                decision.setMxIsCityMatchFriendCityCenter6m(jsonObject.getString(value));
                                break;
                            case "inter_peer_num_6m":
                                decision.setMxInterPeerNum6m(jsonObject.getString(value));
                                break;
                            default:
                                break;
                        }
                    }
                }


            }
        }
    }
}

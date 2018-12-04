package com.xiji.cashloan.cl.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xiji.cashloan.core.common.util.StringUtil;
import com.xiji.cashloan.cl.domain.*;
import com.xiji.cashloan.cl.domain.operator.*;
import com.xiji.cashloan.cl.mapper.*;
import com.xiji.cashloan.cl.model.OperatorVoiceModel;
import com.xiji.cashloan.cl.service.OperatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.xiji.cashloan.core.common.util.ShardTableUtil;
import com.xiji.cashloan.core.domain.UserBaseInfo;
import com.xiji.cashloan.core.mapper.UserBaseInfoMapper;

/**
 * 运营商回调信息处理
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 */
@Service("operatorService")
@Transactional(rollbackFor = Exception.class)
public class OperatorServiceImpl implements OperatorService {

    public static final Logger logger = LoggerFactory.getLogger(OperatorServiceImpl.class);

    @Resource
    private OperatorBillMapper operatorBillMapper;

    @Resource
    private OperatorVoiceMapper operatorVoiceMapper;

    @Resource
    private UserBaseInfoMapper userBaseInfoMapper;

    @Resource
    private UserAuthMapper userAuthMapper;

    @Resource
    private UserContactsMapper userContactsMapper;

    @Resource
    private OperatorBasicMapper operatorBasicMapper;

    @Resource
    private OperatorPackageMapper operatorPackageMapper;

    @Resource
    private OperatorFamilyMapper operatorFamilyMapper;

    @Resource
    private OperatorNetMapper operatorNetMapper;

    @Resource
    private OperatorSmsMapper operatorSmsMapper;

    @Resource
    private OperatorRechargeMapper operatorRechargeMapper;

    @Resource
    private OperatorMonthInfoMapper operatorMonthInfoMapper;

    @Override
    public void saveOperatorInfos(String res, Long userId, Date createTime, String mobile, Long reqLogId) {
        if (StringUtil.isNotBlank(res)) {
            JSONObject resJson = JSONObject.parseObject(res);

            if ("0".equals(resJson.get("code").toString())) {
                UserBaseInfo baseInfo = null;
                if (userId != null && userId > 0) {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("userId", userId);
                    baseInfo = userBaseInfoMapper.findSelective(params);
                }
                if (baseInfo == null) {
                    logger.error("保存用户userId：" + userId + "运营商数据时未找到其userBaseInfo，处理失败");
                    return;
                }

                OperatorBasic operatorBasic = JSONObject.parseObject(res, OperatorBasic.class);
                if (operatorBasic == null) {
                    logger.info("保存用户userId：" + userId + "运营商数据时operatorBasic对象为空");
                    return;
                }
                operatorBasic.setGmtCreate(createTime);
                operatorBasic.setUserId(userId);
                operatorBasic.setMobile(mobile);
                operatorBasic.setReqLogId(reqLogId);
                operatorBasicMapper.save(operatorBasic);

                String packages = String.valueOf(resJson.get("packages"));
                if (StringUtil.isNotBlank(packages)) {
                    List<OperatorPackageMeta> packageMetaList = JSONObject.parseArray(packages, OperatorPackageMeta.class);
                    if (packageMetaList != null && packageMetaList.size() > 0) {
                        for (OperatorPackageMeta operatorPackageMeta : packageMetaList) {
                            if (operatorPackageMeta.getItems() != null && operatorPackageMeta.getItems().size() > 0) {
                                for (OperatorPackageItem operatorPackageItem : operatorPackageMeta.getItems()) {
                                    OperatorPackage operatorPackage = new OperatorPackage();
                                    BeanUtils.copyProperties(operatorPackageItem, operatorPackage);
                                    operatorPackage.setBillStartDate(operatorPackageMeta.getBillStartDate());
                                    operatorPackage.setBillEndDate(operatorPackageMeta.getBillEndDate());
                                    operatorPackage.setUserId(userId);
                                    operatorPackage.setMobile(mobile);
                                    operatorPackage.setGmtCreate(createTime);
                                    operatorPackage.setReqLogId(reqLogId);
                                    operatorPackageMapper.save(operatorPackage);
                                }
                            }
                        }
                    }
                }

                String families = String.valueOf(resJson.get("families"));
                if (StringUtil.isNotBlank(families)) {
                    List<OperatorFamilyMeta> familyMetaList = JSONObject.parseArray(families, OperatorFamilyMeta.class);
                    if (familyMetaList != null && familyMetaList.size() > 0) {
                        for (OperatorFamilyMeta operatorFamilyMeta : familyMetaList) {
                            if (operatorFamilyMeta.getItems() != null && operatorFamilyMeta.getItems().size() > 0) {
                                for (OperatorFamilyItem operatorFamilyItem : operatorFamilyMeta.getItems()) {
                                    OperatorFamily operatorFamily = new OperatorFamily();
                                    BeanUtils.copyProperties(operatorFamilyItem, operatorFamily);
                                    operatorFamily.setFamilyNum(operatorFamilyMeta.getFamilyNum());
                                    operatorFamily.setUserId(userId);
                                    operatorFamily.setMobile(mobile);
                                    operatorFamily.setGmtCreate(createTime);
                                    operatorFamily.setReqLogId(reqLogId);
                                    operatorFamilyMapper.save(operatorFamily);
                                }
                            }
                        }
                    }
                }

                String recharges = String.valueOf(resJson.get("recharges"));
                if (StringUtil.isNotBlank(recharges)) {
                    List<OperatorRecharge> rechargeList = JSONObject.parseArray(recharges, OperatorRecharge.class);
                    if (rechargeList != null && rechargeList.size() > 0) {
                        for (OperatorRecharge operatorRecharge : rechargeList) {
                            operatorRecharge.setUserId(userId);
                            operatorRecharge.setGmtCreate(createTime);
                            operatorRecharge.setMobile(mobile);
                            operatorRecharge.setReqLogId(reqLogId);
                            operatorRechargeMapper.save(operatorRecharge);
                        }
                    }
                }

                String bills = String.valueOf(resJson.get("bills"));
                if (StringUtil.isNotBlank(bills)) {
                    List<OperatorBill> billList = JSONObject.parseArray(bills, OperatorBill.class);
                    if (billList != null && billList.size() > 0) {
                        for (OperatorBill operatorBill : billList) {
                            operatorBill.setGmtCreate(createTime);
                            operatorBill.setUserId(userId);
                            operatorBill.setReqLogId(reqLogId);
                            operatorBill.setMobile(mobile);
                            operatorBillMapper.save(operatorBill);
                        }
                    }
                }

                String monthInfos = String.valueOf(resJson.get("monthInfo"));
                if (StringUtil.isNotBlank(monthInfos)) {
                    List<OperatorMonthInfo> monthInfoList = JSONObject.parseArray(monthInfos, OperatorMonthInfo.class);
                    if (monthInfoList != null && monthInfoList.size() > 0) {
                        for (OperatorMonthInfo operatorMonthInfo : monthInfoList) {
                            operatorMonthInfo.setGmtCreate(createTime);
                            operatorMonthInfo.setUserId(userId);
                            operatorMonthInfo.setMobile(mobile);
                            operatorMonthInfo.setReqLogId(reqLogId);
                            operatorMonthInfoMapper.save(operatorMonthInfo);
                        }
                    }
                }

                String operatorVoices = String.valueOf(resJson.get("calls"));
                if (StringUtil.isNotBlank(operatorVoices)) {
                    List<OperatorVoiceMeta> operatorVoiceMetaList = JSONObject.parseArray(operatorVoices, OperatorVoiceMeta.class);
                    if (operatorVoiceMetaList != null && operatorVoiceMetaList.size() > 0) {
                        // 分表
                        String tableName = ShardTableUtil.generateTableNameById("cl_operator_voice", userId, 30000);
                        int countTable = operatorVoiceMapper.countTable(tableName);
                        if (countTable == 0) {
                            operatorVoiceMapper.createTable(tableName);
                        }

                        for (OperatorVoiceMeta voiceMeta : operatorVoiceMetaList) {
                            if (voiceMeta.getItems() != null && voiceMeta.getItems().size() > 0) {
                                for (OperatorVoiceItem operatorVoiceItem : voiceMeta.getItems()) {
                                    OperatorVoice operatorVoice = new OperatorVoice();
                                    BeanUtils.copyProperties(operatorVoiceItem, operatorVoice);
                                    operatorVoice.setBillMonth(voiceMeta.getBillMonth());
                                    operatorVoice.setUserId(userId);
                                    operatorVoice.setMobile(mobile);
                                    operatorVoice.setGmtCreate(createTime);
                                    operatorVoice.setReqLogId(reqLogId);
                                    operatorVoiceMapper.saveShard(tableName, operatorVoice);
                                }
                            }
                        }

                        UserAuth auth = userAuthMapper.findByUserId(userId);
                        if (auth.getContactState().equals("30")) {
                            String tableName1 = ShardTableUtil.generateTableNameById("cl_user_contacts", userId, 30000);
                            Map<String, Object> params = new HashMap<>();
                            params.put("userId", userId);
                            List<UserContacts> contacts = userContactsMapper.listShardSelective1(tableName1, params);
                            for (UserContacts userContacts : contacts) {
                                String phone = userContacts.getPhone();
                                OperatorVoiceModel operatorVoicesModel = operatorVoiceMapper.operatorVoicesCount(tableName, userId, phone);
                                Integer totalCount = operatorVoicesModel.getTotalCount();
                                Integer sumDuration = operatorVoicesModel.getSumDuration();
                                Map<String, Object> param = new HashMap<String, Object>();
                                param.put("phone", phone);
                                param.put("tableName1", tableName1);
                                param.put("totalCount", String.valueOf(totalCount == null ? 0 : totalCount));
                                param.put("sumDuration", String.valueOf(sumDuration == null ? 0 : sumDuration));
                                param.put("userId", userId);
                                userContactsMapper.updateCount(param);
                            }
                        }
                    }
                }

                String operatorSmses = String.valueOf(resJson.get("smses"));
                if (StringUtil.isNotBlank(operatorSmses)) {
                    List<OperatorSmsMeta> operatorSmsMetaList = JSONObject.parseArray(operatorSmses, OperatorSmsMeta.class);
                    if (operatorSmsMetaList != null && operatorSmsMetaList.size() > 0) {
                        // 分表
                        String tableName = ShardTableUtil.generateTableNameById("cl_operator_sms", userId, 30000);
                        int countTable = operatorSmsMapper.countTable(tableName);
                        if (countTable == 0) {
                            operatorSmsMapper.createTable(tableName);
                        }

                        for (OperatorSmsMeta operatorSmsMeta : operatorSmsMetaList) {
                            if (operatorSmsMeta.getItems() != null && operatorSmsMeta.getItems().size() > 0) {
                                for (OperatorSmsItem operatorSmsItem : operatorSmsMeta.getItems()) {
                                    OperatorSms operatorSms = new OperatorSms();
                                    BeanUtils.copyProperties(operatorSmsItem, operatorSms);
                                    operatorSms.setBillMonth(operatorSmsMeta.getBillMonth());
                                    operatorSms.setUserId(userId);
                                    operatorSms.setMobile(mobile);
                                    operatorSms.setGmtCreate(createTime);
                                    operatorSms.setReqLogId(reqLogId);
                                    operatorSmsMapper.saveShard(tableName, operatorSms);
                                }
                            }
                        }
                    }
                }

                String operatorNets = String.valueOf(resJson.get("nets"));
                if (StringUtil.isNotBlank(operatorNets)) {
                    List<OperatorNetMeta> operatorNetMetaList = JSONObject.parseArray(operatorNets, OperatorNetMeta.class);
                    if (operatorNetMetaList != null && operatorNetMetaList.size() > 0) {
                        // 分表
                        String tableName = ShardTableUtil.generateTableNameById("cl_operator_net", userId, 30000);
                        int countTable = operatorNetMapper.countTable(tableName);
                        if (countTable == 0) {
                            operatorNetMapper.createTable(tableName);
                        }

                        for (OperatorNetMeta operatorNetMeta : operatorNetMetaList) {
                            if (operatorNetMeta.getItems() != null && operatorNetMeta.getItems().size() > 0) {
                                for (OperatorNetItem operatorNetItem : operatorNetMeta.getItems()) {
                                    OperatorNet operatorNet = new OperatorNet();
                                    BeanUtils.copyProperties(operatorNetItem, operatorNet);
                                    operatorNet.setBillMonth(operatorNetMeta.getBillMonth());
                                    operatorNet.setUserId(userId);
                                    operatorNet.setMobile(mobile);
                                    operatorNet.setGmtCreate(createTime);
                                    operatorNet.setReqLogId(reqLogId);
                                    operatorNetMapper.saveShard(tableName, operatorNet);
                                }
                            }
                        }
                    }
                }
            } else {
                logger.error("保存用户userId：" + userId + "运营商数据时，获取运营商数据失败");
            }

        } else {
            logger.error("保存用户userId：" + userId + "运营商数据时，res为空，处理失败");
        }
    }

    /**
     * 改写手机号网龄
     *
     * @param operator_basic
     * @return
     */
    private static String modifyExtendPhoneAge(String operator_basic) {
        String afterStr = operator_basic;

        int beginIndex = afterStr.indexOf("extendPhoneAge");
        if (beginIndex == -1) {
            return operator_basic;
        }

        String subStr = afterStr.substring(beginIndex + 17);
        int endIndex = subStr.indexOf("\"");


        //字段值
        String extendPhoneAge = subStr.substring(0, endIndex);
        //无数据则为0
        if (StringUtil.isEmpty(extendPhoneAge)) {
            StringBuffer stringBuffer = new StringBuffer(afterStr);
            return stringBuffer.insert(beginIndex + 17, 0).toString();
        } else {
            //年月相加
            int monthIndex = extendPhoneAge.indexOf("个月");
            int yearIndex = extendPhoneAge.indexOf("年");
            Integer year = 0;
            Integer month = 0;
            if (yearIndex != -1) {
                year = Integer.valueOf(extendPhoneAge.substring(0, yearIndex)) * 12;
            }
            if (monthIndex != -1) {
                month = Integer.valueOf(extendPhoneAge.substring(yearIndex + 1, monthIndex));
            }
            return afterStr.replace(extendPhoneAge, String.valueOf(year + month));
        }
    }
}

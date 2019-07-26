package com.xiji.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 决策数据实体
 * 
 * @author szb
 * @version 1.0.0
 * @date 2019-01-18 18:40:20
 */
 public class Decision implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 用户标识
    */
    private Long userId;

    /**
    * 订单id
    */
    private Long borrowId;

    /**
    * 年龄
    */
    private Integer age;

    /**
    * 通讯录联系人数量
    */
    private Integer contactNum;

    /**
    * 通讯录命中敏感性词汇(借、贷、催收)5个以上
    */
    private Integer contactSensitiveNum;

    /**
    * 身份证是否命中法院黑名单 0-未命中 1-命中
    */
    private Integer mxIsNameAndIdcardInCourtBlack;

    /**
    * 身份证是否命中金融机构黑名单 0-未命中 1-命中
    */
    private Integer mxIsNameAndIdcardInFinanceBlack;

    /**
    * 手机号是否命中金融机构黑名单 0-未命中 1-命中
    */
    private Integer mxIsNameAndMobileInFinanceBlack;

    /**
    * 查询过该用户相关企业数量
    */
    private Integer mxSearchedOrgCnt;

    /**
    * 身份证号码组合过的其他号码数量
    */
    private Integer mxIdcardWithOtherPhoneNum;

    /**
    * 180天内无通话记录天数
    */
    private Integer mxPhoneNoVoiceDays;

    /**
    * 与贷款类号码联系次数5次及以上，且主动呼叫占比大于50% 0-否 1-是
    */
    private Integer mxContactLoanSituation;

    /**
    * 连续近5个月(当月不算在内)平均主叫次数20次以下并且连续近5个月(当月不算在内)平均通话时长(主叫+被叫)70分钟以下 0-否 1-是
    */
    private Integer mxFiveMonthVoiceSituation;

    /**
    * 连续近5个月(当月不算在内)话费消费低于20元的次数
    */
    private Integer mxFiveMonthBillLessThan20Num;

    /**
    * 通讯录与通话记录中匹配两个以下号码 0-否 1-是
    */
    private Integer mxContactMatchingVoiceSituation;

    /**
    * 手机号未实名认证(或认证的用户姓名与当前用户不匹配) 0-未实名认证 1-实名认证
    */
    private Integer mxIsReliability;

    /**
    * 户口所在省份
    */
    private String mxProvince;

    /**
    * 户口所在城市
    */
    private String mxCity;

    /**
    * 户口所在县
    */
    private String mxRegion;

    /**
    * 籍贯
    */
    private String mxNativePlace;

    /**
    * 开户时长，单位月
    */
    private String mxInTime;

    /**
    * 账单认证日期
    */
    private String mxBillCertificationDay;

    /**
    * 身份证号码有效性
    */
    private String mxIdcardCheck;

    /**
    * 邮箱有效性
    */
    private String mxEmailCheck;

    /**
    * 地址有效性
    */
    private String mxAddressCheck;

    /**
    * 通话记录完整性
    */
    private String mxCallDataCheck;

    /**
    * 身份证号码是否与运营商数据匹配
    */
    private String mxIdcardMatch;

    /**
    * 姓名是否与运营商数据匹配
    */
    private String mxNameMatch;

    /**
    * 号码沉默度(近3月) 0-10分，分数越低越活跃
    */
    private String mxMobileSilence3m;

    /**
    * 号码沉默度(近6月) 0-10分，分数越低越活跃
    */
    private String mxMobileSilence6m;

    /**
    * 欠费风险度(近3月) 0-10分，分值越高欠费风险越大
    */
    private String mxArrearageRisk3m;

    /**
    * 欠费风险度(近6月) 0-10分，分值越高欠费风险越大
    */
    private String mxArrearageRisk6m;

    /**
    * 亲情网风险度 0-10分，分数越低，亲情网人数越大，加入时长越久
    */
    private String mxBindingRisk;

    /**
    * 朋友圈在哪里
    */
    private String mxRegularCircle;

    /**
    * 号码使用时间
    */
    private String mxPhoneUsedTime;

    /**
    * 关机情况
    */
    private String mxPhonePowerOff;

    /**
    * 互通过的电话号码数量
    */
    private String mxContactEachOther;

    /**
    * 与澳门地区电话通话情况
    */
    private String mxContactMacao;

    /**
    * 与110电话通话情况
    */
    private String mxContact110;

    /**
    * 与120电话通话情况
    */
    private String mxContact120;

    /**
    * 与律师电话通话情况
    */
    private String mxContactLawyer;

    /**
    * 与法院电话通话情况
    */
    private String mxContactCourt;

    /**
    * 与贷款类号码联系情况
    */
    private String mxContactLoan;

    /**
    * 与银行类号码联系情况
    */
    private String mxContactBank;

    /**
    * 与信用卡类号码联系情况
    */
    private String mxContactCreditCard;

    /**
    * 与催收类号码联系情况
    */
    private String mxContactCollection;

    /**
    * 夜间活动情况
    */
    private String mxContactNight;

    /**
    * 居住地本地(省份)地址在电商中使用时长 保留字段
    */
    private String mxDwellUsedTime;

    /**
    * 总体电商使用情况 保留字段
    */
    private String mxEbusinessInfo;

    /**
    * 申请人本人电商使用情况 保留字段
    */
    private String mxPersonEbusinessInfo;

    /**
    * 虚拟商品购买情况 保留字段
    */
    private String mxVirtualBuying;

    /**
    * 彩票购买情况 保留字段
    */
    private String mxLotteryBuying;

    /**
    * 申请人本人地址变化情况 保留字段
    */
    private String mxPersonAddrChanged;

    /**
    * 申请人的学籍状态是否为在校学生 保留字段
    */
    private String mxSchoolStatus;

    /**
    * 申请人的学历情况 保留字段
    */
    private String mxEducationInfo;

    /**
    * 申请人本人最近使用工作地址情况 保留字段
    */
    private String mxWorkAddrInfo;

    /**
    * 申请人本人最近使用居住地址情况 保留字段
    */
    private String mxLiveAddrInfo;

    /**
    * 申请人本人最近使用学校地址情况 保留字段
    */
    private String mxSchoolAddrInfo;

    /**
    * 号码通话情况
    */
    private String mxPhoneCall;

    /**
    * 近3月朋友联系数量
    */
    private String mxFriendNum3m;

    /**
    * 近3月好朋友联系数量（联系10次以上）
    */
    private String mxGoodFriendNum3m;

    /**
    * 近3月朋友圈中心城市
    */
    private String mxFriendCityCenter3m;

    /**
    * 近3月朋友圈中心地是否与手机归属地一致
    */
    private String mxIsCityMatchFriendCityCenter3m;

    /**
    * 近3月互通电话号码数目
    */
    private String mxInterPeerNum3m;

    /**
    * 近6月朋友联系数量
    */
    private String mxFriendNum6m;

    /**
    * 近6月好朋友联系数量（联系10次以上）
    */
    private String mxGoodFriendNum6m;

    /**
    * 近6月朋友圈中心城市
    */
    private String mxFriendCityCenter6m;

    /**
    * 近6月朋友圈中心地是否与手机归属地一致
    */
    private String mxIsCityMatchFriendCityCenter6m;

    /**
    * 近6月互通电话号码数目
    */
    private String mxInterPeerNum6m;

    /**
    * 查询过该用户的相关企业类型(姓名+身份证+电话号码)
    */
    private String mxSearchedOrgType;

    /**
    * 身份证组合过的其他姓名
    */
    private String mxIdcardWithOtherNames;

    /**
    * 身份证组合过其他电话
    */
    private String mxIdcardWithOtherPhones;

    /**
    * 电话号码组合过其他姓名
    */
    private String mxPhoneWithOtherNames;

    /**
    * 电话号码组合过其他身份证
    */
    private String mxPhoneWithOtherIdcards;

    /**
    * 电话号码注册过的相关企业数量
    */
    private Integer mxRegisterOrgCnt;

    /**
    * 电话号码注册过的相关企业类型
    */
    private String mxRegisterOrgType;

    /**
    * 电话号码出现过的公开信息网站
    */
    private String mxArisedOpenWeb;

    /**
    * 用户号码联系黑中介分数(0-100,分数越低风险越高，40分以下为高危人群)
    */
    private Double mxPhoneGrayScore;

    /**
    * 直接联系人中黑名单人数
    */
    private Integer mxContactsClass1BlacklistCnt;

    /**
    * 间接联系人中黑名单人数
    */
    private Integer mxContactsClass2BlacklistCnt;

    /**
    * 直接联系人人数
    */
    private Integer mxContactsClass1Cnt;

    /**
    * 引起间接黑名单人数
    */
    private Integer mxContactsRouterCnt;

    /**
    * 直接联系人中引起间接黑名单占比
    */
    private Double mxContactsRouterRatio;

    /**
    * 逾期历史数量
    */
    private Integer yxOverdueHistoryCount;

    /**
    * 历史逾期M3+(大于90天)数量
    */
    private Integer yxOverdueHistoryM3Count;

    /**
    * 历史逾期M6+(大于180天)数量
    */
    private Integer yxOverdueHistoryM6Count;

    /**
    * 申请借款数量大于20且放款数量为0,0-否 1-是
    */
    private Integer yxAm20NoAccept;

    /**
    * 法院执行人次数
    */
    private Integer mxCourtcaseCnt;

    /**
    * 法院未执行次数
    */
    private Integer mxDishonestCnt;

    /**
    * 身份证存疑姓名数
    */
    private Integer mxIdcardOtherNamesCnt;

    /**
    * 身份证存疑手机号码数
    */
    private Integer mxIdcardOtherMobilesCnt;

    /**
    * 身份证存疑触黑手机号码数
    */
    private Integer mxIdcardOtherMobilesBlackCnt;

    /**
    * 手机存疑姓名数
    */
    private Integer mxMobileOtherNamesCnt;

    /**
    * 手机存疑身份证数
    */
    private Integer mxMobileOtherIdcardsCnt;

    /**
    * 手机存疑触黑身份证数
    */
    private Integer mxMobileOtherIdcardsBlackCnt;

    /**
    * 使用过的设备数
    */
    private Integer mxOtherDevicesCnt;

    /**
    * 手机号码使用过的设备数
    */
    private Integer mxMobileOtherDevicesCnt;

    /**
    * 身份证号码使用过的设备数
    */
    private Integer mxIdcardOtherDevicesCnt;

    /**
    * 使用过的设备上登陆的其他姓名数
    */
    private Integer mxDeviceOtherNamesCnt;

    /**
    * 使用过的设备上登陆的其他手机号码数
    */
    private Integer mxDeviceOtherMobilesCnt;

    /**
    * 使用过的设备上登陆的其他触黑手机号码数
    */
    private Integer mxDeviceOtherMobilesBlackCnt;

    /**
    * 使用过的设备上登陆的其他身份证号码数
    */
    private Integer mxDeviceOtherIdcardsCnt;

    /**
    * 使用过的设备上登陆的其他触黑身份证号码数
    */
    private Integer mxDeviceOtherIdcardsBlackCnt;

    /**
    * 手机和姓名是否在黑名单 0-否 1-是
    */
    private Integer mxBlackMobileNameInBlacklist;

    /**
    * 身份证和姓名是否在黑名单 0-否 1-是
    */
    private Integer mxBlackIdcardNameInBlacklist;

    /**
    * 手机和姓名是否在灰名单 0-否 1-是
    */
    private Integer mxGrayMobileNameInBlacklist;

    /**
    * 身份证和姓名是否在灰名单 0-否 1-是
    */
    private Integer mxGrayIdcardNameInBlacklist;

    /**
    * 是否命中欺诈风险名单 0-否 1-是
    */
    private Integer mxFraudIsHit;

    /**
    * 命中欺诈风险类型
    */
    private String mxFraudHitType;

    /**
    * 最后一次申请是否被拒贷 true: 是；false: 否
    */
    private String xdIsLastloanRefused;

    /**
    * 历史借款次数(所有的借款次数，包含当前借款)
    */
    private Integer xdTotalLoanCount;

    /**
    * 历史逾期次数(所有的逾期次数，包含当前逾期)
    */
    private Integer xdTotalOverdueCount;

    /**
    * 已经还清的历史逾期最长时间，M1:小于1月; M2:大于1月，小于2月; M3:大于2月，小于3月; M4:3月及以上
    */
    private String xdLongestOverduePaid;

    /**
    * 当前处于逾期状态的借款笔数
    */
    private Integer xdCurrentOverdueCount;

    /**
    * 当前逾期总金额，0: 0(没有逾期); 1:[0,100]; 2:[100,500); 3:[500,1000); 4:[1000,2000); 5:[2000,4000); 6:[4000,6000); 7:[6000,10000); 8:>=10000
    */
    private Integer xdCurrentOverdueAmount;

    /**
    * 有逾期90天以上运营商联系人个数
    */
    private Integer xdOverDue90ContactsCount;

    /**
    * 当前最长逾期时间(不包括已经还清的)，M1:小于1月; M2:大于1月，小于2月; M3:大于2月，小于3月; M4:3月及以上
    */
    private String xdLongestOverdueUnpaid;

    /**
    * 最后一次拒贷原因
    */
    private String xdLastLoanRefuseReason;

    /**
    * 最后一次拒贷时间
    */
    private String xdLastLoanRefuseTime;

    /**
    * 其他详情
    */
    private String xdRemark;

    /**
    * 最早借款时间
    */
    private String xdFirstLoanTime;

    /**
    * 是否不良用户 0-否 1-是
    */
    private Integer ppxIsBlack;

    /**
    * 是否关注用户 0-否 1-是
    */
    private Integer ppxIsAlert;

    /**
    * 逾期最早出现时间
    */
    private String ppxOverdueFirstTime;

    /**
    * 逾期最近出现时间
    */
    private String ppxOverdueLastTime;

    /**
    * 逾期累计出现次数
    */
    private Integer ppxTotalOverdueCount;

    /**
    * 当前总逾期金额，0: 0(没有逾期); 1:[0,1000]; 2:[1000,2000); 3:[2000,3000); 4:[3000,4000); 5:[4000,6000); 6:[6000,8000); 7:[8000,10000); 8:[10000,30000); 9:[30000,50000); 10:[50000,100000); 11:>=10000
    */
    private Integer ppxCurrentOverdueAmount;

    /**
    * 当前最大逾期时长 0:没有逾期 1:1-30天; 2:31-60天; M3:61-90天; 4:91-120天; 5:121-150天; 6:151-180天; 7:>180天
    */
    private Integer ppxCurrentOverdueDays;

    /**
    * 历史最大逾期金额，0: 0(没有逾期); 1:[0,1000]; 2:[1000,2000); 3:[2000,3000); 4:[3000,4000); 5:[4000,6000); 6:[6000,8000); 7:[8000,10000); 8:[10000,30000); 9:[30000,50000); 10:[50000,100000); 11:>=10000
    */
    private Integer ppxHistoryOverdueAmount;

    /**
    * 历史最大逾期时长 0:没有逾期 1:1-30天; 2:31-60天; M3:61-90天; 4:91-120天; 5:121-150天; 6:151-180天; 7:>180天
    */
    private Integer ppxHistoryOverdueDays;

    /**
    * 欺诈最早出现时间
    */
    private String ppxFraudFirstTime;

    /**
    * 欺诈最近出现时间
    */
    private String ppxFraudLastTime;

    /**
    * 欺诈累计出现次数
    */
    private Integer ppxTotalFraudCount;

    /**
    * 负面最早出现时间
    */
    private String ppxNegativeFirstTime;

    /**
    * 负面最近出现时间
    */
    private String ppxNegativeLastTime;

    /**
    * 负面累计出现次数
    */
    private Integer ppxTotalNegativeCount;

    /**
    * 贷款行为分
    */
    private String xyLoansScore;

    /**
    * 贷款行为置信度
    */
    private String xyLoansCredibility;

    /**
    * 贷款放款总订单数
    */
    private String xyLoansCount;

    /**
    * 贷款已结清订单数
    */
    private String xyLoansSettleCount;

    /**
    * 贷款逾期订单数（M0+)
    */
    private String xyLoansOverdueCount;

    /**
    * 贷款机构数
    */
    private String xyLoansOrgCount;

    /**
    * 消费金融类机构数
    */
    private String xyConsfinOrgCount;

    /**
    * 网络贷款类机构数
    */
    private String xyLoansCashCount;

    /**
    * 近1个月贷款笔数
    */
    private String xyLatestOneMonth;

    /**
    * 近3个月贷款笔数
    */
    private String xyLatestThreeMonth;

    /**
    * 近6个月贷款笔数
    */
    private String xyLatestSixMonth;

    /**
    * 历史贷款机构成功扣款笔数
    */
    private String xyHistorySucFee;

    /**
    * 历史贷款机构失败扣款笔数
    */
    private String xyHistoryFailFee;

    /**
    * 历史贷款机构成功扣款数-失败数
    */
    private Integer xyHistorySucMinusFailNum;

    /**
    * 近1个月贷款机构成功扣款笔数
    */
    private String xyLatestOneMonthSuc;

    /**
    * 近1个月贷款机构失败扣款笔数
    */
    private String xyLatestOneMonthFail;

    /**
    * 近1个月贷款机构成功扣款数-失败数
    */
    private Integer xyLatestOneMonthSucMinusFailNum;

    /**
    * 信用贷款时长
    */
    private String xyLoansLongTime;

    /**
    * 最近一次贷款时间
    */
    private String xyLoansLatestTime;

    /**
    * 近一周与几个催收号码有过联系
    */
    private Integer paL1wwdcnTNumsCon;

    /**
    * 近一周与几家银行机构催收号码有过联系
    */
    private Integer paL1wwdcnTNumsConBank;

    /**
    * 近一周与几家消费金融机构催收号码有过联系
    */
    private Integer paL1wwdcnTNumsConCf;

    /**
    * 近一周与几家委外催收机构催收号码有过联系
    */
    private Integer paL1wwdcnTNumsConF;

    /**
    * 近一周与几家互联网金融机构催收号码有过联系
    */
    private Integer paL1wwdcnTNumsConIf;

    /**
    * 近一周涉及催收号码的总机构数
    */
    private Integer paL1wwdcnTNumsConOrg;

    /**
    * 近一周被催收号码呼叫次数
    */
    private Integer paL1wwdcnTTimesIn;

    /**
    * 近一周主叫催收号码次数
    */
    private Integer paL1wwdcnTTimesOut;

    /**
    * 近两周联系机构类型总数
    */
    private Integer paL2wwdcnTNumsConOrgType;

    /**
    * 近三周联系互联网金融机构的总个数
    */
    private Integer paL3wwdcnTNumsConIf;

    /**
    * 近一月与几个催收号码有过联系
    */
    private Integer paL1mwdcnTNumsCon;

    /**
    * 近一月与几家银行机构催收号码有过联系
    */
    private Integer paL1mwdcnTNumsConBank;

    /**
    * 近一月与几家消费金融机构催收号码有过联系
    */
    private Integer paL1mwdcnTNumsConCf;

    /**
    * 近一月与几家委外催收机构催收号码有过联系
    */
    private Integer paL1mwdcnTNumsConF;

    /**
    * 近一月与几家互联网金融机构催收号码有过联系
    */
    private Integer paL1mwdcnTNumsConIf;

    /**
    * 近一月涉及催收号码的总机构数
    */
    private Integer paL1mwdcnTNumsConOrg;

    /**
    * 近一月被催收号码呼叫次数
    */
    private Integer paL1mwdcnTTimesIn;

    /**
    * 近一月主叫催收号码次数
    */
    private Integer paL1mwdcnTTimesOut;

    /**
    * 近两月被催收号码呼叫次数
    */
    private Integer paL2mwdcnTTimesIn;

    /**
    * 近两月申请人收到催收号的总个数
    */
    private Integer paL2mwdcnTNumsIn;

    /**
    * 近两月被单个催收号码呼叫的最大次数
    */
    private Integer paL2mwdcnTaxTimesIn;

    /**
    * 近两月申请人联系次数最大的催收号的总时长
    */
    private Integer paL2mwdcnMaxTimesCon;

    /**
    * 近两月晚上联系催收号的总天数
    */
    private Integer paL2mencnTTaysCon;

    /**
    * 近三月与几个催收号码有过联系
    */
    private Integer paL3mwdcnTNumsCon;

    /**
    * 近三月与几家银行机构催收号码有过联系
    */
    private Integer paL3mwdcnTNumsConBank;

    /**
    * 近三月与几家消费金融机构催收号码有过联系
    */
    private Integer paL3mwdcnTNumsConCf;

    /**
    * 近三月与几家委外催收机构催收号码有过联系
    */
    private Integer paL3mwdcnTNumsConF;

    /**
    * 近三月与几家互联网金融机构催收号码有过联系
    */
    private Integer paL3mwdcnTNumsConIf;

    /**
    * 近三月晚上涉及催收号码的总机构数
    */
    private Integer paL3mencnTNumsConOrg;

    /**
    * 近三月涉及催收号码的总机构数
    */
    private Integer paL3mwdcnTNumsConOrg;

    /**
    * 近三月被催收号码呼叫次数
    */
    private Integer paL3mwdcnTTimesIn;

    /**
    * 近三月主叫催收号码次数
    */
    private Integer paL3mwdcnTTimesOut;

    /**
    * 近三月新增机构数
    */
    private Integer paL3mwdcnAddTNumsInOrg;

    /**
    * 近三月被每个催收号呼叫的天数的最大值
    */
    private Integer paL3mwdcnMaxDaysIn;

    /**
    * 近四月申请人联系机构类型的总个数
    */
    private Integer paL4mwdcnTNumsConOrgType;

    /**
    * 近四月非银机构呼入的总次数
    */
    private Integer paL4mwdcnTTimesInNonBank;

    /**
    * 近四月联系催收号的总时长
    */
    private Integer paL4mwdcnTDurCon;

    /**
    * 近四月联系催收号的总次数
    */
    private Integer paL4mwdcnTTimesCon;

    /**
    * 近四月与几家委外催收机构催收号码有过联系
    */
    private Integer paL4mwdcnTNumsConF;

    /**
    * 近四月被催收号呼叫的总时长
    */
    private Integer paL4mwdcnTDurIn;

    /**
    * 近四月被叫次数最大的催收号的总时长
    */
    private Integer paL4mwdcnTDurInMaxTimes;

    /**
    * 近四月被几家委外催收机构呼叫过
    */
    private Integer paL4mwdcnTNumsInF;

    /**
    * 近五月联系催收号的总天数
    */
    private Integer paL5mwdcnTDaysCon;

    /**
    * 近五月被委外催收号码呼叫的总时长
    */
    private Integer paL5mwdcnTDurInF;

    /**
    * 近五月联系催收手机总时长
    */
    private Integer paL5mwdcmTDurCon;

    /**
    * 近五月与几家互联网金融机构催收号码有过联系
    */
    private Integer paL5mwdcnTNumsConIf;

    /**
    * 详单周期内与几个催收号码有过联系
    */
    private Integer paAllwdcnTNumsCon;

    /**
    * 详单周期内与几家银行机构催收号码有过联系
    */
    private Integer paAllwdcnTNumsConBank;

    /**
    * 详单周期内与几家消费金融机构催收号码有过联系
    */
    private Integer paAllwdcnTNumsConCf;

    /**
    * 详单周期内与几家委外催收机构催收号码有过联系
    */
    private Integer paAllwdcnTNumsConF;

    /**
    * 详单周期内与几家互联网金融机构催收号码有过联系
    */
    private Integer paAllwdcnTNumsConIf;

    /**
    * 详单周期内涉及催收号码的总机构数
    */
    private Integer paAllwdcnTNumsConOrg;

    /**
    * 详单周期内被催收号码呼叫次数
    */
    private Integer paAllwdcnTTimesIn;

    /**
    * 详单周期内主叫催收号码次数
    */
    private Integer paAllwdcnTTimesOut;

    /**
    * 详单周期内被委外催收号码呼叫的总时长
    */
    private Integer paAllwdcnTDurInF;

    /**
    * 详单周期内被叫次数最大的催收手机号的机构类型
    */
    private Integer paAllwdcmMaxOrgTypeIn;

    /**
    * 全部详单周期工作日(周一~周五)白天(8~18 点)_平 均时长
    */
    private Integer paAlldtcnwkAdur;

    /**
    * 全部详单周期全天(0~23 点)_平均时长
    */
    private Integer paAllwdcnAdur;

    /**
    * 近一月白天(8~18 点)_平均时长
    */
    private Integer paL1mdtcnAdur;

    /**
    * 近一月全天(0~23 点)_平均时长
    */
    private Integer paL1mwdcnAdur;

    /**
    * 全部详单周期周末(周六周日)午夜(0~7 点)_详单中 的联系人总个数
    */
    private Integer paAllmncnrtNumsCon;

    /**
    * 近一周周末(周六周日)全天(0~23 点)_详单中的联系 人总个数
    */
    private Integer paL1wwdcnrtNumsCon;

    /**
    * 近两月全天(0~23 点)_详单中的联系人总个数
    */
    private Integer paL2mwdcnNumsCon;

    /**
    * 近两周工作日(周一~周五)全天(0~23 点)_详单中的 联系人总个数
    */
    private Integer paL2wwdcnwkNumsCon;

    /**
    * 全部详单周期全天(0~23 点)_通话一次催收号码的 平均时长
    */
    private Integer paAllwdcnADurOneNums;

    /**
    * 近四月全天(0~23 点)_通话一次催收号码的平均时 长
    */
    private Integer paL4mwdcnADurOneNums;

    /**
    * 近六月全天(0~23 点)_通话一次催收号码的平均时 长
    */
    private Integer paL6mwdcnADurOneNums;

    /**
    * 近一月工作日(周一~周五)白天(8~18 点)_最高排名 比例
    */
    private Integer paL1mdtcnwkRankCount;

    /**
    * 近一周周末(周六周日)白天(8~18 点)_最高排名比例
    */
    private Integer paL1wdtcnrtRankCount;

    /**
    * 近两月白天(8~18 点)_最高排名比例
    */
    private Integer paL2mdtcnRankCount;

    /**
    * 近三周工作日(周一~周五)全天(0~23 点)_最高排名 比例
    */
    private Integer paL3wwdcnwkRankCount;

    /**
    * 近一月周末(周六周日)夜晚(19~23 点)_将一小时打 6 个及以上电话称为特殊小时，统计这种小时的数量
    */
    private Integer paL1mevcnrtSepcialHourCount;

    /**
    * 近一月工作日(周一~周五)午夜(0~7 点)_将一小时 打 6 个及以上电话称为特殊小时，统计这种小时的数 量
    */
    private Integer paL1mmncnwkSepcialHourCount;

    /**
    * 近一周夜晚(19~23 点)_将一小时打 6 个及以上电话 称为特殊小时，统计这种小时的数量
    */
    private Integer paL1wevcnSepcialHourCount;

    /**
    * 近一周周末(周六周日)全天(0~23 点)_将一小时打 6 个及以上电话称为特殊小时，统计这种小时的数量
    */
    private Integer paL1wwdcnrtSepcialHourCount;

    /**
    * 近两月夜晚(19~23 点)_将一小时打 6 个及以上电话 称为特殊小时，统计这种小时的数量
    */
    private Integer paL2mevcnSepcialHourCount;

    /**
    * 近两月周末(周六周日)夜晚(19~23 点)_将一小时打 6 个及以上电话称为特殊小时，统计这种小时的数量
    */
    private Integer paL2mevcnrtSepcialHourCount;

    /**
    * 近两月全天(0~23 点)_将一小时打 6 个及以上电话称 为特殊小时，统计这种小时的数量
    */
    private Integer paL2mwdcnSepcialHourCount;

    /**
    * 近三月夜晚(19~23 点)_将一小时打 6 个及以上电话 称为特殊小时，统计这种小时的数量
    */
    private Integer paL3mevcnSepcialHourCount;

    /**
    * 近三月周末(周六周日)全天(0~23 点)_将一小时打 6 个及以上电话称为特殊小时，统计这种小时的数量
    */
    private Integer paL3mwdcnrtSepcialHourCount;

    /**
    * 近六月工作日(周一~周五)全天(0~23 点)_将一小时 打 6 个及以上电话称为特殊小时，统计这种小时的数 量
    */
    private Integer paL6mwdcnwkSepcialHourCount;

    /**
    * 近两月白天(8~18 点)_活跃的天数
    */
    private Integer paL2mdtcnActiveDaysCount;

    /**
    * 近两周全天(0~23 点)_活跃的天数
    */
    private Integer paL2wwdcnActiveDaysCount;

    /**
    * 近三月周末(周六周日)全天(0~23 点)_活跃的天数
    */
    private Integer paL3mwdcnrtActiveDaysCount;

    /**
    * 近两周白天(8~18 点)_被单个催收号码呼叫的最大 次数
    */
    private Integer paL2wdtcnMaxTimesIn;

    /**
    * 近两周夜晚(19~23 点)_申请人联系一个号码的天数 的最大值
    */
    private Integer paL2wevcnMaxDaysOfOneNumberCount;

    /**
    * 近三月周末(周六周日)全天(0~23 点)_申请人联系一 个号码的天数的最大值
    */
    private Integer paL3mwdcnrtMaxDaysOfOneNumberCount;

    /**
    * 近三周全天(0~23 点)_申请人联系一个号码的天数 的最大值
    */
    private Integer paL3wwdcnMaxDaysOfOneNumberCount;

    /**
    * 近两周全天(0~23 点)_被催收号码呼叫一次催收号 码的平均时长
    */
    private Integer paL2wwdcnADurOneNumsIn;

    /**
    * 近两周全天(0~23 点)_被催收号码呼叫次数
    */
    private Integer paL2wwdcnTTimesIn;

    /**
    * 近三月全天(0~23 点)_被催收号码呼叫的平均时长
    */
    private Integer paL3mwdcnADurIn;

    /**
    * 近一月周末(周六周日)夜晚(19~23 点)_与催收号码 通话总时长
    */
    private Integer paL1mevcnrtTDur;

    /**
    * 近一月工作日(周一~周五)午夜(0~7 点)_与催收号 码通话总时长
    */
    private Integer paL1mmncnwkTDur;

    /**
    * 近两月夜晚(19~23 点)_与催收号码通话总时长
    */
    private Integer paL2mevcnTDur;

    /**
    * 近两月周末(周六周日)全天(0~23 点)_与催收号码通 话总时长
    */
    private Integer paL2mwdcnrtTDur;

    /**
    * 近三月夜晚(19~23 点)_与催收号码通话总时长
    */
    private Integer paL3mevcnTDur;

    /**
    * 近三月周末(周六周日)全天(0~23 点)_与催收号码通 话总时长
    */
    private Integer paL3mwdcnrtTDur;

    /**
    * 近六月工作日(周一~周五)全天(0~23 点)_与催收号 码通话总时长
    */
    private Integer paL6mwdcnwkTDur;

    /**
    * 近四月全天(0~23 点)_与催收号码通话总时长
    */
    private Integer paL4mwdcnTDur;

    /**
    * 近六月全天(0~23 点)_被催收号呼叫最小间隔天数
    */
    private Integer paL6mwdcnMinTtvDaysIn;

    /**
    * 近六月全天(0~23 点)_与委外机构催收号呼叫的总 时长
    */
    private Integer paL6mwdcnTDurF;

    /**
    * 周五的凌晨 2 点至 5 点通话时长
    */
    private Integer paAll25acfriDur;

    /**
    * 周一至周五的通话时长
    */
    private Integer paAllwdacwkDur;

    /**
    * 凌晨 2 点凌晨 5 点之间的通话次数
    */
    private Integer paAll25acTimes;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 更新时间
    */
    private Date lastModifyTime;

    /**
    * 近1月实际借款平台数
    */
    private Integer ydActualLoanPlatformCount1m;
    /**
    * 还款次数
    */
    private Integer ydRepaymentTimesCount;
    /**
    * 近6月实际借款平台数
    */
    private Integer ydActualLoanPlatformCount6m;
    /**
    * 近3月实际借款平台数
    */
    private Integer ydActualLoanPlatformCount3m;
    /**
    * 实际借款平台数
    */
    private Integer ydActualLoanPlatformCount;
    /**
    * 近3月申请平台数
    */
    private Integer ydLoanPlatformCount3m;
    /**
    * 近1月申请平台数
    */
    private Integer ydLoanPlatformCount1m;
    /**
    * 近6月申请平台数
    */
    private Integer ydLoanPlatformCount6m;
    /**
    * 近6月还款平台数
    */
    private Integer ydRepaymentPlatformCount6m;
    /**
    * 近1月还款平台数
    */
    private Integer ydRepaymentPlatformCount1m;
    /**
    * 近3月还款平台数
    */
    private Integer ydRepaymentPlatformCount3m;
    /**
    * 还款平台总数
    */
    private Integer ydRepaymentPlatformCount;
    /**
    * 申请借款平台总数
    */
    private Integer ydLoanPlatformCount;
    /**
    * 风险等级
    */
    private String ydRiskEvaluation;
    /**
    * 评估模型得分，分数越高风险越高，0标识缺乏足够信息判断
    */
    private Integer ydScore;
    /**
    * 借贷正常N笔以上 且借贷多头近3月申请平台大于M家,0-否 1-是
    */
    private Integer yxLoaningAm3m;
    /**
    * 是否命中有盾拒绝风险项,0-否 1-是
    */
    private Integer ydRefusedFeature;
    /**
    * 宜信有盾无下款,0-否 1-是
    */
    private Integer yxYdNoLoan;
    /**
    * 通话详情包含敏感号码,0-否 1-是
    */
    private Integer mxVoiceHasSensitivePhone;
    /**
    * 公司名称
    */
    private String companyName;
    /**
    * 多头近6月未下款且申请平台大于15
    */
    private Integer ydNoLoan6m;
    /**
    * 多头近1月申请平台数大于等于30家 且下款平台数小于3
    */
    private Integer ydLoan1m;
    /**
    * 近一月短信和被叫是上一个月的2倍及以上
    */
    private Integer mxMessageNum;
    /**
    * 还款行为历史和一月失败均大于成功
    */
    private Integer xySucMinusFailNum;
    /**
    * 多头实际借款平台数1 还款平台1 还款笔数1
    */
    private Integer ydPlatformLoanNum;
    /**
    * 多头近3月申请平台数大于等于50家
    */
    private Integer ydPlatformNum3m;



   /**
    * 同设备使用用户总数
    */
   private int ydDeviceLinkIdCount;

   /**
    * 欺诈甄别欺诈评分
    * (分值范围 1-100， 未命中数据时不返 回分数)
    */
   private Integer yxFraudScore;

    /**
    * 获取主键Id
    *
    * @return id
    */
    public Long getId(){
        return id;
    }

    /**
    * 设置主键Id
    * 
    * @param 要设置的主键Id
    */
    public void setId(Long id){
        this.id = id;
    }

    /**
    * 获取用户标识
    *
    * @return 用户标识
    */
    public Long getUserId(){
        return userId;
    }

    /**
    * 设置用户标识
    * 
    * @param userId 要设置的用户标识
    */
    public void setUserId(Long userId){
        this.userId = userId;
    }

    /**
    * 获取订单id
    *
    * @return 订单id
    */
    public Long getBorrowId(){
        return borrowId;
    }

    /**
    * 设置订单id
    * 
    * @param borrowId 要设置的订单id
    */
    public void setBorrowId(Long borrowId){
        this.borrowId = borrowId;
    }

    /**
    * 获取年龄
    *
    * @return 年龄
    */
    public Integer getAge(){
        return age;
    }

    /**
    * 设置年龄
    * 
    * @param age 要设置的年龄
    */
    public void setAge(Integer age){
        this.age = age;
    }

    /**
    * 获取通讯录联系人数量
    *
    * @return 通讯录联系人数量
    */
    public Integer getContactNum(){
        return contactNum;
    }

    /**
    * 设置通讯录联系人数量
    * 
    * @param contactNum 要设置的通讯录联系人数量
    */
    public void setContactNum(Integer contactNum){
        this.contactNum = contactNum;
    }

    /**
    * 获取通讯录命中敏感性词汇(借、贷、催收)5个以上
    *
    * @return 通讯录命中敏感性词汇(借、贷、催收)5个以上
    */
    public Integer getContactSensitiveNum(){
        return contactSensitiveNum;
    }

    /**
    * 设置通讯录命中敏感性词汇(借、贷、催收)5个以上
    * 
    * @param contactSensitiveNum 要设置的通讯录命中敏感性词汇(借、贷、催收)5个以上
    */
    public void setContactSensitiveNum(Integer contactSensitiveNum){
        this.contactSensitiveNum = contactSensitiveNum;
    }

    /**
    * 获取身份证是否命中法院黑名单 0-未命中 1-命中
    *
    * @return 身份证是否命中法院黑名单 0-未命中 1-命中
    */
    public Integer getMxIsNameAndIdcardInCourtBlack(){
        return mxIsNameAndIdcardInCourtBlack;
    }

    /**
    * 设置身份证是否命中法院黑名单 0-未命中 1-命中
    * 
    * @param mxIsNameAndIdcardInCourtBlack 要设置的身份证是否命中法院黑名单 0-未命中 1-命中
    */
    public void setMxIsNameAndIdcardInCourtBlack(Integer mxIsNameAndIdcardInCourtBlack){
        this.mxIsNameAndIdcardInCourtBlack = mxIsNameAndIdcardInCourtBlack;
    }

    /**
    * 获取身份证是否命中金融机构黑名单 0-未命中 1-命中
    *
    * @return 身份证是否命中金融机构黑名单 0-未命中 1-命中
    */
    public Integer getMxIsNameAndIdcardInFinanceBlack(){
        return mxIsNameAndIdcardInFinanceBlack;
    }

    /**
    * 设置身份证是否命中金融机构黑名单 0-未命中 1-命中
    * 
    * @param mxIsNameAndIdcardInFinanceBlack 要设置的身份证是否命中金融机构黑名单 0-未命中 1-命中
    */
    public void setMxIsNameAndIdcardInFinanceBlack(Integer mxIsNameAndIdcardInFinanceBlack){
        this.mxIsNameAndIdcardInFinanceBlack = mxIsNameAndIdcardInFinanceBlack;
    }

    /**
    * 获取手机号是否命中金融机构黑名单 0-未命中 1-命中
    *
    * @return 手机号是否命中金融机构黑名单 0-未命中 1-命中
    */
    public Integer getMxIsNameAndMobileInFinanceBlack(){
        return mxIsNameAndMobileInFinanceBlack;
    }

    /**
    * 设置手机号是否命中金融机构黑名单 0-未命中 1-命中
    * 
    * @param mxIsNameAndMobileInFinanceBlack 要设置的手机号是否命中金融机构黑名单 0-未命中 1-命中
    */
    public void setMxIsNameAndMobileInFinanceBlack(Integer mxIsNameAndMobileInFinanceBlack){
        this.mxIsNameAndMobileInFinanceBlack = mxIsNameAndMobileInFinanceBlack;
    }

    /**
    * 获取查询过该用户相关企业数量
    *
    * @return 查询过该用户相关企业数量
    */
    public Integer getMxSearchedOrgCnt(){
        return mxSearchedOrgCnt;
    }

    /**
    * 设置查询过该用户相关企业数量
    * 
    * @param mxSearchedOrgCnt 要设置的查询过该用户相关企业数量
    */
    public void setMxSearchedOrgCnt(Integer mxSearchedOrgCnt){
        this.mxSearchedOrgCnt = mxSearchedOrgCnt;
    }

    /**
    * 获取身份证号码组合过的其他号码数量
    *
    * @return 身份证号码组合过的其他号码数量
    */
    public Integer getMxIdcardWithOtherPhoneNum(){
        return mxIdcardWithOtherPhoneNum;
    }

    /**
    * 设置身份证号码组合过的其他号码数量
    * 
    * @param mxIdcardWithOtherPhoneNum 要设置的身份证号码组合过的其他号码数量
    */
    public void setMxIdcardWithOtherPhoneNum(Integer mxIdcardWithOtherPhoneNum){
        this.mxIdcardWithOtherPhoneNum = mxIdcardWithOtherPhoneNum;
    }

    /**
    * 获取180天内无通话记录天数
    *
    * @return 180天内无通话记录天数
    */
    public Integer getMxPhoneNoVoiceDays(){
        return mxPhoneNoVoiceDays;
    }

    /**
    * 设置180天内无通话记录天数
    * 
    * @param mxPhoneNoVoiceDays 要设置的180天内无通话记录天数
    */
    public void setMxPhoneNoVoiceDays(Integer mxPhoneNoVoiceDays){
        this.mxPhoneNoVoiceDays = mxPhoneNoVoiceDays;
    }

    /**
    * 获取与贷款类号码联系次数5次及以上，且主动呼叫占比大于50% 0-否 1-是
    *
    * @return 与贷款类号码联系次数5次及以上，且主动呼叫占比大于50% 0-否 1-是
    */
    public Integer getMxContactLoanSituation(){
        return mxContactLoanSituation;
    }

    /**
    * 设置与贷款类号码联系次数5次及以上，且主动呼叫占比大于50% 0-否 1-是
    * 
    * @param mxContactLoanSituation 要设置的与贷款类号码联系次数5次及以上，且主动呼叫占比大于50% 0-否 1-是
    */
    public void setMxContactLoanSituation(Integer mxContactLoanSituation){
        this.mxContactLoanSituation = mxContactLoanSituation;
    }

    /**
    * 获取连续近5个月(当月不算在内)平均主叫次数20次以下并且连续近5个月(当月不算在内)平均通话时长(主叫+被叫)70分钟以下 0-否 1-是
    *
    * @return 连续近5个月(当月不算在内)平均主叫次数20次以下并且连续近5个月(当月不算在内)平均通话时长(主叫+被叫)70分钟以下 0-否 1-是
    */
    public Integer getMxFiveMonthVoiceSituation(){
        return mxFiveMonthVoiceSituation;
    }

    /**
    * 设置连续近5个月(当月不算在内)平均主叫次数20次以下并且连续近5个月(当月不算在内)平均通话时长(主叫+被叫)70分钟以下 0-否 1-是
    * 
    * @param mxFiveMonthVoiceSituation 要设置的连续近5个月(当月不算在内)平均主叫次数20次以下并且连续近5个月(当月不算在内)平均通话时长(主叫+被叫)70分钟以下 0-否 1-是
    */
    public void setMxFiveMonthVoiceSituation(Integer mxFiveMonthVoiceSituation){
        this.mxFiveMonthVoiceSituation = mxFiveMonthVoiceSituation;
    }

    /**
    * 获取连续近5个月(当月不算在内)话费消费低于20元的次数
    *
    * @return 连续近5个月(当月不算在内)话费消费低于20元的次数
    */
    public Integer getMxFiveMonthBillLessThan20Num(){
        return mxFiveMonthBillLessThan20Num;
    }

    /**
    * 设置连续近5个月(当月不算在内)话费消费低于20元的次数
    * 
    * @param mxFiveMonthBillLessThan20Num 要设置的连续近5个月(当月不算在内)话费消费低于20元的次数
    */
    public void setMxFiveMonthBillLessThan20Num(Integer mxFiveMonthBillLessThan20Num){
        this.mxFiveMonthBillLessThan20Num = mxFiveMonthBillLessThan20Num;
    }

    /**
    * 获取通讯录与通话记录中匹配两个以下号码 0-否 1-是
    *
    * @return 通讯录与通话记录中匹配两个以下号码 0-否 1-是
    */
    public Integer getMxContactMatchingVoiceSituation(){
        return mxContactMatchingVoiceSituation;
    }

    /**
    * 设置通讯录与通话记录中匹配两个以下号码 0-否 1-是
    * 
    * @param mxContactMatchingVoiceSituation 要设置的通讯录与通话记录中匹配两个以下号码 0-否 1-是
    */
    public void setMxContactMatchingVoiceSituation(Integer mxContactMatchingVoiceSituation){
        this.mxContactMatchingVoiceSituation = mxContactMatchingVoiceSituation;
    }

    /**
    * 获取手机号未实名认证(或认证的用户姓名与当前用户不匹配) 0-未实名认证 1-实名认证
    *
    * @return 手机号未实名认证(或认证的用户姓名与当前用户不匹配) 0-未实名认证 1-实名认证
    */
    public Integer getMxIsReliability(){
        return mxIsReliability;
    }

    /**
    * 设置手机号未实名认证(或认证的用户姓名与当前用户不匹配) 0-未实名认证 1-实名认证
    * 
    * @param mxIsReliability 要设置的手机号未实名认证(或认证的用户姓名与当前用户不匹配) 0-未实名认证 1-实名认证
    */
    public void setMxIsReliability(Integer mxIsReliability){
        this.mxIsReliability = mxIsReliability;
    }

    /**
    * 获取户口所在省份
    *
    * @return 户口所在省份
    */
    public String getMxProvince(){
        return mxProvince;
    }

    /**
    * 设置户口所在省份
    * 
    * @param mxProvince 要设置的户口所在省份
    */
    public void setMxProvince(String mxProvince){
        this.mxProvince = mxProvince;
    }

    /**
    * 获取户口所在城市
    *
    * @return 户口所在城市
    */
    public String getMxCity(){
        return mxCity;
    }

    /**
    * 设置户口所在城市
    * 
    * @param mxCity 要设置的户口所在城市
    */
    public void setMxCity(String mxCity){
        this.mxCity = mxCity;
    }

    /**
    * 获取户口所在县
    *
    * @return 户口所在县
    */
    public String getMxRegion(){
        return mxRegion;
    }

    /**
    * 设置户口所在县
    * 
    * @param mxRegion 要设置的户口所在县
    */
    public void setMxRegion(String mxRegion){
        this.mxRegion = mxRegion;
    }

    /**
    * 获取籍贯
    *
    * @return 籍贯
    */
    public String getMxNativePlace(){
        return mxNativePlace;
    }

    /**
    * 设置籍贯
    * 
    * @param mxNativePlace 要设置的籍贯
    */
    public void setMxNativePlace(String mxNativePlace){
        this.mxNativePlace = mxNativePlace;
    }

    /**
    * 获取开户时长，单位月
    *
    * @return 开户时长，单位月
    */
    public String getMxInTime(){
        return mxInTime;
    }

    /**
    * 设置开户时长，单位月
    * 
    * @param mxInTime 要设置的开户时长，单位月
    */
    public void setMxInTime(String mxInTime){
        this.mxInTime = mxInTime;
    }

    /**
    * 获取账单认证日期
    *
    * @return 账单认证日期
    */
    public String getMxBillCertificationDay(){
        return mxBillCertificationDay;
    }

    /**
    * 设置账单认证日期
    * 
    * @param mxBillCertificationDay 要设置的账单认证日期
    */
    public void setMxBillCertificationDay(String mxBillCertificationDay){
        this.mxBillCertificationDay = mxBillCertificationDay;
    }

    /**
    * 获取身份证号码有效性
    *
    * @return 身份证号码有效性
    */
    public String getMxIdcardCheck(){
        return mxIdcardCheck;
    }

    /**
    * 设置身份证号码有效性
    * 
    * @param mxIdcardCheck 要设置的身份证号码有效性
    */
    public void setMxIdcardCheck(String mxIdcardCheck){
        this.mxIdcardCheck = mxIdcardCheck;
    }

    /**
    * 获取邮箱有效性
    *
    * @return 邮箱有效性
    */
    public String getMxEmailCheck(){
        return mxEmailCheck;
    }

    /**
    * 设置邮箱有效性
    * 
    * @param mxEmailCheck 要设置的邮箱有效性
    */
    public void setMxEmailCheck(String mxEmailCheck){
        this.mxEmailCheck = mxEmailCheck;
    }

    /**
    * 获取地址有效性
    *
    * @return 地址有效性
    */
    public String getMxAddressCheck(){
        return mxAddressCheck;
    }

    /**
    * 设置地址有效性
    * 
    * @param mxAddressCheck 要设置的地址有效性
    */
    public void setMxAddressCheck(String mxAddressCheck){
        this.mxAddressCheck = mxAddressCheck;
    }

    /**
    * 获取通话记录完整性
    *
    * @return 通话记录完整性
    */
    public String getMxCallDataCheck(){
        return mxCallDataCheck;
    }

    /**
    * 设置通话记录完整性
    * 
    * @param mxCallDataCheck 要设置的通话记录完整性
    */
    public void setMxCallDataCheck(String mxCallDataCheck){
        this.mxCallDataCheck = mxCallDataCheck;
    }

    /**
    * 获取身份证号码是否与运营商数据匹配
    *
    * @return 身份证号码是否与运营商数据匹配
    */
    public String getMxIdcardMatch(){
        return mxIdcardMatch;
    }

    /**
    * 设置身份证号码是否与运营商数据匹配
    * 
    * @param mxIdcardMatch 要设置的身份证号码是否与运营商数据匹配
    */
    public void setMxIdcardMatch(String mxIdcardMatch){
        this.mxIdcardMatch = mxIdcardMatch;
    }

    /**
    * 获取姓名是否与运营商数据匹配
    *
    * @return 姓名是否与运营商数据匹配
    */
    public String getMxNameMatch(){
        return mxNameMatch;
    }

    /**
    * 设置姓名是否与运营商数据匹配
    * 
    * @param mxNameMatch 要设置的姓名是否与运营商数据匹配
    */
    public void setMxNameMatch(String mxNameMatch){
        this.mxNameMatch = mxNameMatch;
    }

    /**
    * 获取号码沉默度(近3月) 0-10分，分数越低越活跃
    *
    * @return 号码沉默度(近3月) 0-10分，分数越低越活跃
    */
    public String getMxMobileSilence3m(){
        return mxMobileSilence3m;
    }

    /**
    * 设置号码沉默度(近3月) 0-10分，分数越低越活跃
    * 
    * @param mxMobileSilence3m 要设置的号码沉默度(近3月) 0-10分，分数越低越活跃
    */
    public void setMxMobileSilence3m(String mxMobileSilence3m){
        this.mxMobileSilence3m = mxMobileSilence3m;
    }

    /**
    * 获取号码沉默度(近6月) 0-10分，分数越低越活跃
    *
    * @return 号码沉默度(近6月) 0-10分，分数越低越活跃
    */
    public String getMxMobileSilence6m(){
        return mxMobileSilence6m;
    }

    /**
    * 设置号码沉默度(近6月) 0-10分，分数越低越活跃
    * 
    * @param mxMobileSilence6m 要设置的号码沉默度(近6月) 0-10分，分数越低越活跃
    */
    public void setMxMobileSilence6m(String mxMobileSilence6m){
        this.mxMobileSilence6m = mxMobileSilence6m;
    }

    /**
    * 获取欠费风险度(近3月) 0-10分，分值越高欠费风险越大
    *
    * @return 欠费风险度(近3月) 0-10分，分值越高欠费风险越大
    */
    public String getMxArrearageRisk3m(){
        return mxArrearageRisk3m;
    }

    /**
    * 设置欠费风险度(近3月) 0-10分，分值越高欠费风险越大
    * 
    * @param mxArrearageRisk3m 要设置的欠费风险度(近3月) 0-10分，分值越高欠费风险越大
    */
    public void setMxArrearageRisk3m(String mxArrearageRisk3m){
        this.mxArrearageRisk3m = mxArrearageRisk3m;
    }

    /**
    * 获取欠费风险度(近6月) 0-10分，分值越高欠费风险越大
    *
    * @return 欠费风险度(近6月) 0-10分，分值越高欠费风险越大
    */
    public String getMxArrearageRisk6m(){
        return mxArrearageRisk6m;
    }

    /**
    * 设置欠费风险度(近6月) 0-10分，分值越高欠费风险越大
    * 
    * @param mxArrearageRisk6m 要设置的欠费风险度(近6月) 0-10分，分值越高欠费风险越大
    */
    public void setMxArrearageRisk6m(String mxArrearageRisk6m){
        this.mxArrearageRisk6m = mxArrearageRisk6m;
    }

    /**
    * 获取亲情网风险度 0-10分，分数越低，亲情网人数越大，加入时长越久
    *
    * @return 亲情网风险度 0-10分，分数越低，亲情网人数越大，加入时长越久
    */
    public String getMxBindingRisk(){
        return mxBindingRisk;
    }

    /**
    * 设置亲情网风险度 0-10分，分数越低，亲情网人数越大，加入时长越久
    * 
    * @param mxBindingRisk 要设置的亲情网风险度 0-10分，分数越低，亲情网人数越大，加入时长越久
    */
    public void setMxBindingRisk(String mxBindingRisk){
        this.mxBindingRisk = mxBindingRisk;
    }

    /**
    * 获取朋友圈在哪里
    *
    * @return 朋友圈在哪里
    */
    public String getMxRegularCircle(){
        return mxRegularCircle;
    }

    /**
    * 设置朋友圈在哪里
    * 
    * @param mxRegularCircle 要设置的朋友圈在哪里
    */
    public void setMxRegularCircle(String mxRegularCircle){
        this.mxRegularCircle = mxRegularCircle;
    }

    /**
    * 获取号码使用时间
    *
    * @return 号码使用时间
    */
    public String getMxPhoneUsedTime(){
        return mxPhoneUsedTime;
    }

    /**
    * 设置号码使用时间
    * 
    * @param mxPhoneUsedTime 要设置的号码使用时间
    */
    public void setMxPhoneUsedTime(String mxPhoneUsedTime){
        this.mxPhoneUsedTime = mxPhoneUsedTime;
    }

    /**
    * 获取关机情况
    *
    * @return 关机情况
    */
    public String getMxPhonePowerOff(){
        return mxPhonePowerOff;
    }

    /**
    * 设置关机情况
    * 
    * @param mxPhonePowerOff 要设置的关机情况
    */
    public void setMxPhonePowerOff(String mxPhonePowerOff){
        this.mxPhonePowerOff = mxPhonePowerOff;
    }

    /**
    * 获取互通过的电话号码数量
    *
    * @return 互通过的电话号码数量
    */
    public String getMxContactEachOther(){
        return mxContactEachOther;
    }

    /**
    * 设置互通过的电话号码数量
    * 
    * @param mxContactEachOther 要设置的互通过的电话号码数量
    */
    public void setMxContactEachOther(String mxContactEachOther){
        this.mxContactEachOther = mxContactEachOther;
    }

    /**
    * 获取与澳门地区电话通话情况
    *
    * @return 与澳门地区电话通话情况
    */
    public String getMxContactMacao(){
        return mxContactMacao;
    }

    /**
    * 设置与澳门地区电话通话情况
    * 
    * @param mxContactMacao 要设置的与澳门地区电话通话情况
    */
    public void setMxContactMacao(String mxContactMacao){
        this.mxContactMacao = mxContactMacao;
    }

    /**
    * 获取与110电话通话情况
    *
    * @return 与110电话通话情况
    */
    public String getMxContact110(){
        return mxContact110;
    }

    /**
    * 设置与110电话通话情况
    * 
    * @param mxContact110 要设置的与110电话通话情况
    */
    public void setMxContact110(String mxContact110){
        this.mxContact110 = mxContact110;
    }

    /**
    * 获取与120电话通话情况
    *
    * @return 与120电话通话情况
    */
    public String getMxContact120(){
        return mxContact120;
    }

    /**
    * 设置与120电话通话情况
    * 
    * @param mxContact120 要设置的与120电话通话情况
    */
    public void setMxContact120(String mxContact120){
        this.mxContact120 = mxContact120;
    }

    /**
    * 获取与律师电话通话情况
    *
    * @return 与律师电话通话情况
    */
    public String getMxContactLawyer(){
        return mxContactLawyer;
    }

    /**
    * 设置与律师电话通话情况
    * 
    * @param mxContactLawyer 要设置的与律师电话通话情况
    */
    public void setMxContactLawyer(String mxContactLawyer){
        this.mxContactLawyer = mxContactLawyer;
    }

    /**
    * 获取与法院电话通话情况
    *
    * @return 与法院电话通话情况
    */
    public String getMxContactCourt(){
        return mxContactCourt;
    }

    /**
    * 设置与法院电话通话情况
    * 
    * @param mxContactCourt 要设置的与法院电话通话情况
    */
    public void setMxContactCourt(String mxContactCourt){
        this.mxContactCourt = mxContactCourt;
    }

    /**
    * 获取与贷款类号码联系情况
    *
    * @return 与贷款类号码联系情况
    */
    public String getMxContactLoan(){
        return mxContactLoan;
    }

    /**
    * 设置与贷款类号码联系情况
    * 
    * @param mxContactLoan 要设置的与贷款类号码联系情况
    */
    public void setMxContactLoan(String mxContactLoan){
        this.mxContactLoan = mxContactLoan;
    }

    /**
    * 获取与银行类号码联系情况
    *
    * @return 与银行类号码联系情况
    */
    public String getMxContactBank(){
        return mxContactBank;
    }

    /**
    * 设置与银行类号码联系情况
    * 
    * @param mxContactBank 要设置的与银行类号码联系情况
    */
    public void setMxContactBank(String mxContactBank){
        this.mxContactBank = mxContactBank;
    }

    /**
    * 获取与信用卡类号码联系情况
    *
    * @return 与信用卡类号码联系情况
    */
    public String getMxContactCreditCard(){
        return mxContactCreditCard;
    }

    /**
    * 设置与信用卡类号码联系情况
    * 
    * @param mxContactCreditCard 要设置的与信用卡类号码联系情况
    */
    public void setMxContactCreditCard(String mxContactCreditCard){
        this.mxContactCreditCard = mxContactCreditCard;
    }

    /**
    * 获取与催收类号码联系情况
    *
    * @return 与催收类号码联系情况
    */
    public String getMxContactCollection(){
        return mxContactCollection;
    }

    /**
    * 设置与催收类号码联系情况
    * 
    * @param mxContactCollection 要设置的与催收类号码联系情况
    */
    public void setMxContactCollection(String mxContactCollection){
        this.mxContactCollection = mxContactCollection;
    }

    /**
    * 获取夜间活动情况
    *
    * @return 夜间活动情况
    */
    public String getMxContactNight(){
        return mxContactNight;
    }

    /**
    * 设置夜间活动情况
    * 
    * @param mxContactNight 要设置的夜间活动情况
    */
    public void setMxContactNight(String mxContactNight){
        this.mxContactNight = mxContactNight;
    }

    /**
    * 获取居住地本地(省份)地址在电商中使用时长 保留字段
    *
    * @return 居住地本地(省份)地址在电商中使用时长 保留字段
    */
    public String getMxDwellUsedTime(){
        return mxDwellUsedTime;
    }

    /**
    * 设置居住地本地(省份)地址在电商中使用时长 保留字段
    * 
    * @param mxDwellUsedTime 要设置的居住地本地(省份)地址在电商中使用时长 保留字段
    */
    public void setMxDwellUsedTime(String mxDwellUsedTime){
        this.mxDwellUsedTime = mxDwellUsedTime;
    }

    /**
    * 获取总体电商使用情况 保留字段
    *
    * @return 总体电商使用情况 保留字段
    */
    public String getMxEbusinessInfo(){
        return mxEbusinessInfo;
    }

    /**
    * 设置总体电商使用情况 保留字段
    * 
    * @param mxEbusinessInfo 要设置的总体电商使用情况 保留字段
    */
    public void setMxEbusinessInfo(String mxEbusinessInfo){
        this.mxEbusinessInfo = mxEbusinessInfo;
    }

    /**
    * 获取申请人本人电商使用情况 保留字段
    *
    * @return 申请人本人电商使用情况 保留字段
    */
    public String getMxPersonEbusinessInfo(){
        return mxPersonEbusinessInfo;
    }

    /**
    * 设置申请人本人电商使用情况 保留字段
    * 
    * @param mxPersonEbusinessInfo 要设置的申请人本人电商使用情况 保留字段
    */
    public void setMxPersonEbusinessInfo(String mxPersonEbusinessInfo){
        this.mxPersonEbusinessInfo = mxPersonEbusinessInfo;
    }

    /**
    * 获取虚拟商品购买情况 保留字段
    *
    * @return 虚拟商品购买情况 保留字段
    */
    public String getMxVirtualBuying(){
        return mxVirtualBuying;
    }

    /**
    * 设置虚拟商品购买情况 保留字段
    * 
    * @param mxVirtualBuying 要设置的虚拟商品购买情况 保留字段
    */
    public void setMxVirtualBuying(String mxVirtualBuying){
        this.mxVirtualBuying = mxVirtualBuying;
    }

    /**
    * 获取彩票购买情况 保留字段
    *
    * @return 彩票购买情况 保留字段
    */
    public String getMxLotteryBuying(){
        return mxLotteryBuying;
    }

    /**
    * 设置彩票购买情况 保留字段
    * 
    * @param mxLotteryBuying 要设置的彩票购买情况 保留字段
    */
    public void setMxLotteryBuying(String mxLotteryBuying){
        this.mxLotteryBuying = mxLotteryBuying;
    }

    /**
    * 获取申请人本人地址变化情况 保留字段
    *
    * @return 申请人本人地址变化情况 保留字段
    */
    public String getMxPersonAddrChanged(){
        return mxPersonAddrChanged;
    }

    /**
    * 设置申请人本人地址变化情况 保留字段
    * 
    * @param mxPersonAddrChanged 要设置的申请人本人地址变化情况 保留字段
    */
    public void setMxPersonAddrChanged(String mxPersonAddrChanged){
        this.mxPersonAddrChanged = mxPersonAddrChanged;
    }

    /**
    * 获取申请人的学籍状态是否为在校学生 保留字段
    *
    * @return 申请人的学籍状态是否为在校学生 保留字段
    */
    public String getMxSchoolStatus(){
        return mxSchoolStatus;
    }

    /**
    * 设置申请人的学籍状态是否为在校学生 保留字段
    * 
    * @param mxSchoolStatus 要设置的申请人的学籍状态是否为在校学生 保留字段
    */
    public void setMxSchoolStatus(String mxSchoolStatus){
        this.mxSchoolStatus = mxSchoolStatus;
    }

    /**
    * 获取申请人的学历情况 保留字段
    *
    * @return 申请人的学历情况 保留字段
    */
    public String getMxEducationInfo(){
        return mxEducationInfo;
    }

    /**
    * 设置申请人的学历情况 保留字段
    * 
    * @param mxEducationInfo 要设置的申请人的学历情况 保留字段
    */
    public void setMxEducationInfo(String mxEducationInfo){
        this.mxEducationInfo = mxEducationInfo;
    }

    /**
    * 获取申请人本人最近使用工作地址情况 保留字段
    *
    * @return 申请人本人最近使用工作地址情况 保留字段
    */
    public String getMxWorkAddrInfo(){
        return mxWorkAddrInfo;
    }

    /**
    * 设置申请人本人最近使用工作地址情况 保留字段
    * 
    * @param mxWorkAddrInfo 要设置的申请人本人最近使用工作地址情况 保留字段
    */
    public void setMxWorkAddrInfo(String mxWorkAddrInfo){
        this.mxWorkAddrInfo = mxWorkAddrInfo;
    }

    /**
    * 获取申请人本人最近使用居住地址情况 保留字段
    *
    * @return 申请人本人最近使用居住地址情况 保留字段
    */
    public String getMxLiveAddrInfo(){
        return mxLiveAddrInfo;
    }

    /**
    * 设置申请人本人最近使用居住地址情况 保留字段
    * 
    * @param mxLiveAddrInfo 要设置的申请人本人最近使用居住地址情况 保留字段
    */
    public void setMxLiveAddrInfo(String mxLiveAddrInfo){
        this.mxLiveAddrInfo = mxLiveAddrInfo;
    }

    /**
    * 获取申请人本人最近使用学校地址情况 保留字段
    *
    * @return 申请人本人最近使用学校地址情况 保留字段
    */
    public String getMxSchoolAddrInfo(){
        return mxSchoolAddrInfo;
    }

    /**
    * 设置申请人本人最近使用学校地址情况 保留字段
    * 
    * @param mxSchoolAddrInfo 要设置的申请人本人最近使用学校地址情况 保留字段
    */
    public void setMxSchoolAddrInfo(String mxSchoolAddrInfo){
        this.mxSchoolAddrInfo = mxSchoolAddrInfo;
    }

    /**
    * 获取号码通话情况
    *
    * @return 号码通话情况
    */
    public String getMxPhoneCall(){
        return mxPhoneCall;
    }

    /**
    * 设置号码通话情况
    * 
    * @param mxPhoneCall 要设置的号码通话情况
    */
    public void setMxPhoneCall(String mxPhoneCall){
        this.mxPhoneCall = mxPhoneCall;
    }

    /**
    * 获取近3月朋友联系数量
    *
    * @return 近3月朋友联系数量
    */
    public String getMxFriendNum3m(){
        return mxFriendNum3m;
    }

    /**
    * 设置近3月朋友联系数量
    * 
    * @param mxFriendNum3m 要设置的近3月朋友联系数量
    */
    public void setMxFriendNum3m(String mxFriendNum3m){
        this.mxFriendNum3m = mxFriendNum3m;
    }

    /**
    * 获取近3月好朋友联系数量（联系10次以上）
    *
    * @return 近3月好朋友联系数量（联系10次以上）
    */
    public String getMxGoodFriendNum3m(){
        return mxGoodFriendNum3m;
    }

    /**
    * 设置近3月好朋友联系数量（联系10次以上）
    * 
    * @param mxGoodFriendNum3m 要设置的近3月好朋友联系数量（联系10次以上）
    */
    public void setMxGoodFriendNum3m(String mxGoodFriendNum3m){
        this.mxGoodFriendNum3m = mxGoodFriendNum3m;
    }

    /**
    * 获取近3月朋友圈中心城市
    *
    * @return 近3月朋友圈中心城市
    */
    public String getMxFriendCityCenter3m(){
        return mxFriendCityCenter3m;
    }

    /**
    * 设置近3月朋友圈中心城市
    * 
    * @param mxFriendCityCenter3m 要设置的近3月朋友圈中心城市
    */
    public void setMxFriendCityCenter3m(String mxFriendCityCenter3m){
        this.mxFriendCityCenter3m = mxFriendCityCenter3m;
    }

    /**
    * 获取近3月朋友圈中心地是否与手机归属地一致
    *
    * @return 近3月朋友圈中心地是否与手机归属地一致
    */
    public String getMxIsCityMatchFriendCityCenter3m(){
        return mxIsCityMatchFriendCityCenter3m;
    }

    /**
    * 设置近3月朋友圈中心地是否与手机归属地一致
    * 
    * @param mxIsCityMatchFriendCityCenter3m 要设置的近3月朋友圈中心地是否与手机归属地一致
    */
    public void setMxIsCityMatchFriendCityCenter3m(String mxIsCityMatchFriendCityCenter3m){
        this.mxIsCityMatchFriendCityCenter3m = mxIsCityMatchFriendCityCenter3m;
    }

    /**
    * 获取近3月互通电话号码数目
    *
    * @return 近3月互通电话号码数目
    */
    public String getMxInterPeerNum3m(){
        return mxInterPeerNum3m;
    }

    /**
    * 设置近3月互通电话号码数目
    * 
    * @param mxInterPeerNum3m 要设置的近3月互通电话号码数目
    */
    public void setMxInterPeerNum3m(String mxInterPeerNum3m){
        this.mxInterPeerNum3m = mxInterPeerNum3m;
    }

    /**
    * 获取近6月朋友联系数量
    *
    * @return 近6月朋友联系数量
    */
    public String getMxFriendNum6m(){
        return mxFriendNum6m;
    }

    /**
    * 设置近6月朋友联系数量
    * 
    * @param mxFriendNum6m 要设置的近6月朋友联系数量
    */
    public void setMxFriendNum6m(String mxFriendNum6m){
        this.mxFriendNum6m = mxFriendNum6m;
    }

    /**
    * 获取近6月好朋友联系数量（联系10次以上）
    *
    * @return 近6月好朋友联系数量（联系10次以上）
    */
    public String getMxGoodFriendNum6m(){
        return mxGoodFriendNum6m;
    }

    /**
    * 设置近6月好朋友联系数量（联系10次以上）
    * 
    * @param mxGoodFriendNum6m 要设置的近6月好朋友联系数量（联系10次以上）
    */
    public void setMxGoodFriendNum6m(String mxGoodFriendNum6m){
        this.mxGoodFriendNum6m = mxGoodFriendNum6m;
    }

    /**
    * 获取近6月朋友圈中心城市
    *
    * @return 近6月朋友圈中心城市
    */
    public String getMxFriendCityCenter6m(){
        return mxFriendCityCenter6m;
    }

    /**
    * 设置近6月朋友圈中心城市
    * 
    * @param mxFriendCityCenter6m 要设置的近6月朋友圈中心城市
    */
    public void setMxFriendCityCenter6m(String mxFriendCityCenter6m){
        this.mxFriendCityCenter6m = mxFriendCityCenter6m;
    }

    /**
    * 获取近6月朋友圈中心地是否与手机归属地一致
    *
    * @return 近6月朋友圈中心地是否与手机归属地一致
    */
    public String getMxIsCityMatchFriendCityCenter6m(){
        return mxIsCityMatchFriendCityCenter6m;
    }

    /**
    * 设置近6月朋友圈中心地是否与手机归属地一致
    * 
    * @param mxIsCityMatchFriendCityCenter6m 要设置的近6月朋友圈中心地是否与手机归属地一致
    */
    public void setMxIsCityMatchFriendCityCenter6m(String mxIsCityMatchFriendCityCenter6m){
        this.mxIsCityMatchFriendCityCenter6m = mxIsCityMatchFriendCityCenter6m;
    }

    /**
    * 获取近6月互通电话号码数目
    *
    * @return 近6月互通电话号码数目
    */
    public String getMxInterPeerNum6m(){
        return mxInterPeerNum6m;
    }

    /**
    * 设置近6月互通电话号码数目
    * 
    * @param mxInterPeerNum6m 要设置的近6月互通电话号码数目
    */
    public void setMxInterPeerNum6m(String mxInterPeerNum6m){
        this.mxInterPeerNum6m = mxInterPeerNum6m;
    }

    /**
    * 获取查询过该用户的相关企业类型(姓名+身份证+电话号码)
    *
    * @return 查询过该用户的相关企业类型(姓名+身份证+电话号码)
    */
    public String getMxSearchedOrgType(){
        return mxSearchedOrgType;
    }

    /**
    * 设置查询过该用户的相关企业类型(姓名+身份证+电话号码)
    * 
    * @param mxSearchedOrgType 要设置的查询过该用户的相关企业类型(姓名+身份证+电话号码)
    */
    public void setMxSearchedOrgType(String mxSearchedOrgType){
        this.mxSearchedOrgType = mxSearchedOrgType;
    }

    /**
    * 获取身份证组合过的其他姓名
    *
    * @return 身份证组合过的其他姓名
    */
    public String getMxIdcardWithOtherNames(){
        return mxIdcardWithOtherNames;
    }

    /**
    * 设置身份证组合过的其他姓名
    * 
    * @param mxIdcardWithOtherNames 要设置的身份证组合过的其他姓名
    */
    public void setMxIdcardWithOtherNames(String mxIdcardWithOtherNames){
        this.mxIdcardWithOtherNames = mxIdcardWithOtherNames;
    }

    /**
    * 获取身份证组合过其他电话
    *
    * @return 身份证组合过其他电话
    */
    public String getMxIdcardWithOtherPhones(){
        return mxIdcardWithOtherPhones;
    }

    /**
    * 设置身份证组合过其他电话
    * 
    * @param mxIdcardWithOtherPhones 要设置的身份证组合过其他电话
    */
    public void setMxIdcardWithOtherPhones(String mxIdcardWithOtherPhones){
        this.mxIdcardWithOtherPhones = mxIdcardWithOtherPhones;
    }

    /**
    * 获取电话号码组合过其他姓名
    *
    * @return 电话号码组合过其他姓名
    */
    public String getMxPhoneWithOtherNames(){
        return mxPhoneWithOtherNames;
    }

    /**
    * 设置电话号码组合过其他姓名
    * 
    * @param mxPhoneWithOtherNames 要设置的电话号码组合过其他姓名
    */
    public void setMxPhoneWithOtherNames(String mxPhoneWithOtherNames){
        this.mxPhoneWithOtherNames = mxPhoneWithOtherNames;
    }

    /**
    * 获取电话号码组合过其他身份证
    *
    * @return 电话号码组合过其他身份证
    */
    public String getMxPhoneWithOtherIdcards(){
        return mxPhoneWithOtherIdcards;
    }

    /**
    * 设置电话号码组合过其他身份证
    * 
    * @param mxPhoneWithOtherIdcards 要设置的电话号码组合过其他身份证
    */
    public void setMxPhoneWithOtherIdcards(String mxPhoneWithOtherIdcards){
        this.mxPhoneWithOtherIdcards = mxPhoneWithOtherIdcards;
    }

    /**
    * 获取电话号码注册过的相关企业数量
    *
    * @return 电话号码注册过的相关企业数量
    */
    public Integer getMxRegisterOrgCnt(){
        return mxRegisterOrgCnt;
    }

    /**
    * 设置电话号码注册过的相关企业数量
    * 
    * @param mxRegisterOrgCnt 要设置的电话号码注册过的相关企业数量
    */
    public void setMxRegisterOrgCnt(Integer mxRegisterOrgCnt){
        this.mxRegisterOrgCnt = mxRegisterOrgCnt;
    }

    /**
    * 获取电话号码注册过的相关企业类型
    *
    * @return 电话号码注册过的相关企业类型
    */
    public String getMxRegisterOrgType(){
        return mxRegisterOrgType;
    }

    /**
    * 设置电话号码注册过的相关企业类型
    * 
    * @param mxRegisterOrgType 要设置的电话号码注册过的相关企业类型
    */
    public void setMxRegisterOrgType(String mxRegisterOrgType){
        this.mxRegisterOrgType = mxRegisterOrgType;
    }

    /**
    * 获取电话号码出现过的公开信息网站
    *
    * @return 电话号码出现过的公开信息网站
    */
    public String getMxArisedOpenWeb(){
        return mxArisedOpenWeb;
    }

    /**
    * 设置电话号码出现过的公开信息网站
    * 
    * @param mxArisedOpenWeb 要设置的电话号码出现过的公开信息网站
    */
    public void setMxArisedOpenWeb(String mxArisedOpenWeb){
        this.mxArisedOpenWeb = mxArisedOpenWeb;
    }

    /**
    * 获取用户号码联系黑中介分数(0-100,分数越低风险越高，40分以下为高危人群)
    *
    * @return 用户号码联系黑中介分数(0-100,分数越低风险越高，40分以下为高危人群)
    */
    public Double getMxPhoneGrayScore(){
        return mxPhoneGrayScore;
    }

    /**
    * 设置用户号码联系黑中介分数(0-100,分数越低风险越高，40分以下为高危人群)
    * 
    * @param mxPhoneGrayScore 要设置的用户号码联系黑中介分数(0-100,分数越低风险越高，40分以下为高危人群)
    */
    public void setMxPhoneGrayScore(Double mxPhoneGrayScore){
        this.mxPhoneGrayScore = mxPhoneGrayScore;
    }

    /**
    * 获取直接联系人中黑名单人数
    *
    * @return 直接联系人中黑名单人数
    */
    public Integer getMxContactsClass1BlacklistCnt(){
        return mxContactsClass1BlacklistCnt;
    }

    /**
    * 设置直接联系人中黑名单人数
    * 
    * @param mxContactsClass1BlacklistCnt 要设置的直接联系人中黑名单人数
    */
    public void setMxContactsClass1BlacklistCnt(Integer mxContactsClass1BlacklistCnt){
        this.mxContactsClass1BlacklistCnt = mxContactsClass1BlacklistCnt;
    }

    /**
    * 获取间接联系人中黑名单人数
    *
    * @return 间接联系人中黑名单人数
    */
    public Integer getMxContactsClass2BlacklistCnt(){
        return mxContactsClass2BlacklistCnt;
    }

    /**
    * 设置间接联系人中黑名单人数
    * 
    * @param mxContactsClass2BlacklistCnt 要设置的间接联系人中黑名单人数
    */
    public void setMxContactsClass2BlacklistCnt(Integer mxContactsClass2BlacklistCnt){
        this.mxContactsClass2BlacklistCnt = mxContactsClass2BlacklistCnt;
    }

    /**
    * 获取直接联系人人数
    *
    * @return 直接联系人人数
    */
    public Integer getMxContactsClass1Cnt(){
        return mxContactsClass1Cnt;
    }

    /**
    * 设置直接联系人人数
    * 
    * @param mxContactsClass1Cnt 要设置的直接联系人人数
    */
    public void setMxContactsClass1Cnt(Integer mxContactsClass1Cnt){
        this.mxContactsClass1Cnt = mxContactsClass1Cnt;
    }

    /**
    * 获取引起间接黑名单人数
    *
    * @return 引起间接黑名单人数
    */
    public Integer getMxContactsRouterCnt(){
        return mxContactsRouterCnt;
    }

    /**
    * 设置引起间接黑名单人数
    * 
    * @param mxContactsRouterCnt 要设置的引起间接黑名单人数
    */
    public void setMxContactsRouterCnt(Integer mxContactsRouterCnt){
        this.mxContactsRouterCnt = mxContactsRouterCnt;
    }

    /**
    * 获取直接联系人中引起间接黑名单占比
    *
    * @return 直接联系人中引起间接黑名单占比
    */
    public Double getMxContactsRouterRatio(){
        return mxContactsRouterRatio;
    }

    /**
    * 设置直接联系人中引起间接黑名单占比
    * 
    * @param mxContactsRouterRatio 要设置的直接联系人中引起间接黑名单占比
    */
    public void setMxContactsRouterRatio(Double mxContactsRouterRatio){
        this.mxContactsRouterRatio = mxContactsRouterRatio;
    }

    /**
    * 获取逾期历史数量
    *
    * @return 逾期历史数量
    */
    public Integer getYxOverdueHistoryCount(){
        return yxOverdueHistoryCount;
    }

    /**
    * 设置逾期历史数量
    * 
    * @param yxOverdueHistoryCount 要设置的逾期历史数量
    */
    public void setYxOverdueHistoryCount(Integer yxOverdueHistoryCount){
        this.yxOverdueHistoryCount = yxOverdueHistoryCount;
    }

    /**
    * 获取历史逾期M3+(大于90天)数量
    *
    * @return 历史逾期M3+(大于90天)数量
    */
    public Integer getYxOverdueHistoryM3Count(){
        return yxOverdueHistoryM3Count;
    }

    /**
    * 设置历史逾期M3+(大于90天)数量
    * 
    * @param yxOverdueHistoryM3Count 要设置的历史逾期M3+(大于90天)数量
    */
    public void setYxOverdueHistoryM3Count(Integer yxOverdueHistoryM3Count){
        this.yxOverdueHistoryM3Count = yxOverdueHistoryM3Count;
    }

    /**
    * 获取历史逾期M6+(大于180天)数量
    *
    * @return 历史逾期M6+(大于180天)数量
    */
    public Integer getYxOverdueHistoryM6Count(){
        return yxOverdueHistoryM6Count;
    }

    /**
    * 设置历史逾期M6+(大于180天)数量
    * 
    * @param yxOverdueHistoryM6Count 要设置的历史逾期M6+(大于180天)数量
    */
    public void setYxOverdueHistoryM6Count(Integer yxOverdueHistoryM6Count){
        this.yxOverdueHistoryM6Count = yxOverdueHistoryM6Count;
    }

    /**
    * 获取申请借款数量大于20且放款数量为0,0-否 1-是
    *
    * @return 申请借款数量大于20且放款数量为0,0-否 1-是
    */
    public Integer getYxAm20NoAccept(){
        return yxAm20NoAccept;
    }

    /**
    * 设置申请借款数量大于20且放款数量为0,0-否 1-是
    * 
    * @param yxAm20NoAccept 要设置的申请借款数量大于20且放款数量为0,0-否 1-是
    */
    public void setYxAm20NoAccept(Integer yxAm20NoAccept){
        this.yxAm20NoAccept = yxAm20NoAccept;
    }

    /**
    * 获取法院执行人次数
    *
    * @return 法院执行人次数
    */
    public Integer getMxCourtcaseCnt(){
        return mxCourtcaseCnt;
    }

    /**
    * 设置法院执行人次数
    * 
    * @param mxCourtcaseCnt 要设置的法院执行人次数
    */
    public void setMxCourtcaseCnt(Integer mxCourtcaseCnt){
        this.mxCourtcaseCnt = mxCourtcaseCnt;
    }

    /**
    * 获取法院未执行次数
    *
    * @return 法院未执行次数
    */
    public Integer getMxDishonestCnt(){
        return mxDishonestCnt;
    }

    /**
    * 设置法院未执行次数
    * 
    * @param mxDishonestCnt 要设置的法院未执行次数
    */
    public void setMxDishonestCnt(Integer mxDishonestCnt){
        this.mxDishonestCnt = mxDishonestCnt;
    }

    /**
    * 获取身份证存疑姓名数
    *
    * @return 身份证存疑姓名数
    */
    public Integer getMxIdcardOtherNamesCnt(){
        return mxIdcardOtherNamesCnt;
    }

    /**
    * 设置身份证存疑姓名数
    * 
    * @param mxIdcardOtherNamesCnt 要设置的身份证存疑姓名数
    */
    public void setMxIdcardOtherNamesCnt(Integer mxIdcardOtherNamesCnt){
        this.mxIdcardOtherNamesCnt = mxIdcardOtherNamesCnt;
    }

    /**
    * 获取身份证存疑手机号码数
    *
    * @return 身份证存疑手机号码数
    */
    public Integer getMxIdcardOtherMobilesCnt(){
        return mxIdcardOtherMobilesCnt;
    }

    /**
    * 设置身份证存疑手机号码数
    * 
    * @param mxIdcardOtherMobilesCnt 要设置的身份证存疑手机号码数
    */
    public void setMxIdcardOtherMobilesCnt(Integer mxIdcardOtherMobilesCnt){
        this.mxIdcardOtherMobilesCnt = mxIdcardOtherMobilesCnt;
    }

    /**
    * 获取身份证存疑触黑手机号码数
    *
    * @return 身份证存疑触黑手机号码数
    */
    public Integer getMxIdcardOtherMobilesBlackCnt(){
        return mxIdcardOtherMobilesBlackCnt;
    }

    /**
    * 设置身份证存疑触黑手机号码数
    * 
    * @param mxIdcardOtherMobilesBlackCnt 要设置的身份证存疑触黑手机号码数
    */
    public void setMxIdcardOtherMobilesBlackCnt(Integer mxIdcardOtherMobilesBlackCnt){
        this.mxIdcardOtherMobilesBlackCnt = mxIdcardOtherMobilesBlackCnt;
    }

    /**
    * 获取手机存疑姓名数
    *
    * @return 手机存疑姓名数
    */
    public Integer getMxMobileOtherNamesCnt(){
        return mxMobileOtherNamesCnt;
    }

    /**
    * 设置手机存疑姓名数
    * 
    * @param mxMobileOtherNamesCnt 要设置的手机存疑姓名数
    */
    public void setMxMobileOtherNamesCnt(Integer mxMobileOtherNamesCnt){
        this.mxMobileOtherNamesCnt = mxMobileOtherNamesCnt;
    }

    /**
    * 获取手机存疑身份证数
    *
    * @return 手机存疑身份证数
    */
    public Integer getMxMobileOtherIdcardsCnt(){
        return mxMobileOtherIdcardsCnt;
    }

    /**
    * 设置手机存疑身份证数
    * 
    * @param mxMobileOtherIdcardsCnt 要设置的手机存疑身份证数
    */
    public void setMxMobileOtherIdcardsCnt(Integer mxMobileOtherIdcardsCnt){
        this.mxMobileOtherIdcardsCnt = mxMobileOtherIdcardsCnt;
    }

    /**
    * 获取手机存疑触黑身份证数
    *
    * @return 手机存疑触黑身份证数
    */
    public Integer getMxMobileOtherIdcardsBlackCnt(){
        return mxMobileOtherIdcardsBlackCnt;
    }

    /**
    * 设置手机存疑触黑身份证数
    * 
    * @param mxMobileOtherIdcardsBlackCnt 要设置的手机存疑触黑身份证数
    */
    public void setMxMobileOtherIdcardsBlackCnt(Integer mxMobileOtherIdcardsBlackCnt){
        this.mxMobileOtherIdcardsBlackCnt = mxMobileOtherIdcardsBlackCnt;
    }

    /**
    * 获取使用过的设备数
    *
    * @return 使用过的设备数
    */
    public Integer getMxOtherDevicesCnt(){
        return mxOtherDevicesCnt;
    }

    /**
    * 设置使用过的设备数
    * 
    * @param mxOtherDevicesCnt 要设置的使用过的设备数
    */
    public void setMxOtherDevicesCnt(Integer mxOtherDevicesCnt){
        this.mxOtherDevicesCnt = mxOtherDevicesCnt;
    }

    /**
    * 获取手机号码使用过的设备数
    *
    * @return 手机号码使用过的设备数
    */
    public Integer getMxMobileOtherDevicesCnt(){
        return mxMobileOtherDevicesCnt;
    }

    /**
    * 设置手机号码使用过的设备数
    * 
    * @param mxMobileOtherDevicesCnt 要设置的手机号码使用过的设备数
    */
    public void setMxMobileOtherDevicesCnt(Integer mxMobileOtherDevicesCnt){
        this.mxMobileOtherDevicesCnt = mxMobileOtherDevicesCnt;
    }

    /**
    * 获取身份证号码使用过的设备数
    *
    * @return 身份证号码使用过的设备数
    */
    public Integer getMxIdcardOtherDevicesCnt(){
        return mxIdcardOtherDevicesCnt;
    }

    /**
    * 设置身份证号码使用过的设备数
    * 
    * @param mxIdcardOtherDevicesCnt 要设置的身份证号码使用过的设备数
    */
    public void setMxIdcardOtherDevicesCnt(Integer mxIdcardOtherDevicesCnt){
        this.mxIdcardOtherDevicesCnt = mxIdcardOtherDevicesCnt;
    }

    /**
    * 获取使用过的设备上登陆的其他姓名数
    *
    * @return 使用过的设备上登陆的其他姓名数
    */
    public Integer getMxDeviceOtherNamesCnt(){
        return mxDeviceOtherNamesCnt;
    }

    /**
    * 设置使用过的设备上登陆的其他姓名数
    * 
    * @param mxDeviceOtherNamesCnt 要设置的使用过的设备上登陆的其他姓名数
    */
    public void setMxDeviceOtherNamesCnt(Integer mxDeviceOtherNamesCnt){
        this.mxDeviceOtherNamesCnt = mxDeviceOtherNamesCnt;
    }

    /**
    * 获取使用过的设备上登陆的其他手机号码数
    *
    * @return 使用过的设备上登陆的其他手机号码数
    */
    public Integer getMxDeviceOtherMobilesCnt(){
        return mxDeviceOtherMobilesCnt;
    }

    /**
    * 设置使用过的设备上登陆的其他手机号码数
    * 
    * @param mxDeviceOtherMobilesCnt 要设置的使用过的设备上登陆的其他手机号码数
    */
    public void setMxDeviceOtherMobilesCnt(Integer mxDeviceOtherMobilesCnt){
        this.mxDeviceOtherMobilesCnt = mxDeviceOtherMobilesCnt;
    }

    /**
    * 获取使用过的设备上登陆的其他触黑手机号码数
    *
    * @return 使用过的设备上登陆的其他触黑手机号码数
    */
    public Integer getMxDeviceOtherMobilesBlackCnt(){
        return mxDeviceOtherMobilesBlackCnt;
    }

    /**
    * 设置使用过的设备上登陆的其他触黑手机号码数
    * 
    * @param mxDeviceOtherMobilesBlackCnt 要设置的使用过的设备上登陆的其他触黑手机号码数
    */
    public void setMxDeviceOtherMobilesBlackCnt(Integer mxDeviceOtherMobilesBlackCnt){
        this.mxDeviceOtherMobilesBlackCnt = mxDeviceOtherMobilesBlackCnt;
    }

    /**
    * 获取使用过的设备上登陆的其他身份证号码数
    *
    * @return 使用过的设备上登陆的其他身份证号码数
    */
    public Integer getMxDeviceOtherIdcardsCnt(){
        return mxDeviceOtherIdcardsCnt;
    }

    /**
    * 设置使用过的设备上登陆的其他身份证号码数
    * 
    * @param mxDeviceOtherIdcardsCnt 要设置的使用过的设备上登陆的其他身份证号码数
    */
    public void setMxDeviceOtherIdcardsCnt(Integer mxDeviceOtherIdcardsCnt){
        this.mxDeviceOtherIdcardsCnt = mxDeviceOtherIdcardsCnt;
    }

    /**
    * 获取使用过的设备上登陆的其他触黑身份证号码数
    *
    * @return 使用过的设备上登陆的其他触黑身份证号码数
    */
    public Integer getMxDeviceOtherIdcardsBlackCnt(){
        return mxDeviceOtherIdcardsBlackCnt;
    }

    /**
    * 设置使用过的设备上登陆的其他触黑身份证号码数
    * 
    * @param mxDeviceOtherIdcardsBlackCnt 要设置的使用过的设备上登陆的其他触黑身份证号码数
    */
    public void setMxDeviceOtherIdcardsBlackCnt(Integer mxDeviceOtherIdcardsBlackCnt){
        this.mxDeviceOtherIdcardsBlackCnt = mxDeviceOtherIdcardsBlackCnt;
    }

    /**
    * 获取手机和姓名是否在黑名单 0-否 1-是
    *
    * @return 手机和姓名是否在黑名单 0-否 1-是
    */
    public Integer getMxBlackMobileNameInBlacklist(){
        return mxBlackMobileNameInBlacklist;
    }

    /**
    * 设置手机和姓名是否在黑名单 0-否 1-是
    * 
    * @param mxBlackMobileNameInBlacklist 要设置的手机和姓名是否在黑名单 0-否 1-是
    */
    public void setMxBlackMobileNameInBlacklist(Integer mxBlackMobileNameInBlacklist){
        this.mxBlackMobileNameInBlacklist = mxBlackMobileNameInBlacklist;
    }

    /**
    * 获取身份证和姓名是否在黑名单 0-否 1-是
    *
    * @return 身份证和姓名是否在黑名单 0-否 1-是
    */
    public Integer getMxBlackIdcardNameInBlacklist(){
        return mxBlackIdcardNameInBlacklist;
    }

    /**
    * 设置身份证和姓名是否在黑名单 0-否 1-是
    * 
    * @param mxBlackIdcardNameInBlacklist 要设置的身份证和姓名是否在黑名单 0-否 1-是
    */
    public void setMxBlackIdcardNameInBlacklist(Integer mxBlackIdcardNameInBlacklist){
        this.mxBlackIdcardNameInBlacklist = mxBlackIdcardNameInBlacklist;
    }

    /**
    * 获取手机和姓名是否在灰名单 0-否 1-是
    *
    * @return 手机和姓名是否在灰名单 0-否 1-是
    */
    public Integer getMxGrayMobileNameInBlacklist(){
        return mxGrayMobileNameInBlacklist;
    }

    /**
    * 设置手机和姓名是否在灰名单 0-否 1-是
    * 
    * @param mxGrayMobileNameInBlacklist 要设置的手机和姓名是否在灰名单 0-否 1-是
    */
    public void setMxGrayMobileNameInBlacklist(Integer mxGrayMobileNameInBlacklist){
        this.mxGrayMobileNameInBlacklist = mxGrayMobileNameInBlacklist;
    }

    /**
    * 获取身份证和姓名是否在灰名单 0-否 1-是
    *
    * @return 身份证和姓名是否在灰名单 0-否 1-是
    */
    public Integer getMxGrayIdcardNameInBlacklist(){
        return mxGrayIdcardNameInBlacklist;
    }

    /**
    * 设置身份证和姓名是否在灰名单 0-否 1-是
    * 
    * @param mxGrayIdcardNameInBlacklist 要设置的身份证和姓名是否在灰名单 0-否 1-是
    */
    public void setMxGrayIdcardNameInBlacklist(Integer mxGrayIdcardNameInBlacklist){
        this.mxGrayIdcardNameInBlacklist = mxGrayIdcardNameInBlacklist;
    }

    /**
    * 获取是否命中欺诈风险名单 0-否 1-是
    *
    * @return 是否命中欺诈风险名单 0-否 1-是
    */
    public Integer getMxFraudIsHit(){
        return mxFraudIsHit;
    }

    /**
    * 设置是否命中欺诈风险名单 0-否 1-是
    * 
    * @param mxFraudIsHit 要设置的是否命中欺诈风险名单 0-否 1-是
    */
    public void setMxFraudIsHit(Integer mxFraudIsHit){
        this.mxFraudIsHit = mxFraudIsHit;
    }

    /**
    * 获取命中欺诈风险类型
    *
    * @return 命中欺诈风险类型
    */
    public String getMxFraudHitType(){
        return mxFraudHitType;
    }

    /**
    * 设置命中欺诈风险类型
    * 
    * @param mxFraudHitType 要设置的命中欺诈风险类型
    */
    public void setMxFraudHitType(String mxFraudHitType){
        this.mxFraudHitType = mxFraudHitType;
    }

    /**
    * 获取最后一次申请是否被拒贷 true: 是；false: 否
    *
    * @return 最后一次申请是否被拒贷 true: 是；false: 否
    */
    public String getXdIsLastloanRefused(){
        return xdIsLastloanRefused;
    }

    /**
    * 设置最后一次申请是否被拒贷 true: 是；false: 否
    * 
    * @param xdIsLastloanRefused 要设置的最后一次申请是否被拒贷 true: 是；false: 否
    */
    public void setXdIsLastloanRefused(String xdIsLastloanRefused){
        this.xdIsLastloanRefused = xdIsLastloanRefused;
    }

    /**
    * 获取历史借款次数(所有的借款次数，包含当前借款)
    *
    * @return 历史借款次数(所有的借款次数，包含当前借款)
    */
    public Integer getXdTotalLoanCount(){
        return xdTotalLoanCount;
    }

    /**
    * 设置历史借款次数(所有的借款次数，包含当前借款)
    * 
    * @param xdTotalLoanCount 要设置的历史借款次数(所有的借款次数，包含当前借款)
    */
    public void setXdTotalLoanCount(Integer xdTotalLoanCount){
        this.xdTotalLoanCount = xdTotalLoanCount;
    }

    /**
    * 获取历史逾期次数(所有的逾期次数，包含当前逾期)
    *
    * @return 历史逾期次数(所有的逾期次数，包含当前逾期)
    */
    public Integer getXdTotalOverdueCount(){
        return xdTotalOverdueCount;
    }

    /**
    * 设置历史逾期次数(所有的逾期次数，包含当前逾期)
    * 
    * @param xdTotalOverdueCount 要设置的历史逾期次数(所有的逾期次数，包含当前逾期)
    */
    public void setXdTotalOverdueCount(Integer xdTotalOverdueCount){
        this.xdTotalOverdueCount = xdTotalOverdueCount;
    }

    /**
    * 获取已经还清的历史逾期最长时间，M1:小于1月; M2:大于1月，小于2月; M3:大于2月，小于3月; M4:3月及以上
    *
    * @return 已经还清的历史逾期最长时间，M1:小于1月; M2:大于1月，小于2月; M3:大于2月，小于3月; M4:3月及以上
    */
    public String getXdLongestOverduePaid(){
        return xdLongestOverduePaid;
    }

    /**
    * 设置已经还清的历史逾期最长时间，M1:小于1月; M2:大于1月，小于2月; M3:大于2月，小于3月; M4:3月及以上
    * 
    * @param xdLongestOverduePaid 要设置的已经还清的历史逾期最长时间，M1:小于1月; M2:大于1月，小于2月; M3:大于2月，小于3月; M4:3月及以上
    */
    public void setXdLongestOverduePaid(String xdLongestOverduePaid){
        this.xdLongestOverduePaid = xdLongestOverduePaid;
    }

    /**
    * 获取当前处于逾期状态的借款笔数
    *
    * @return 当前处于逾期状态的借款笔数
    */
    public Integer getXdCurrentOverdueCount(){
        return xdCurrentOverdueCount;
    }

    /**
    * 设置当前处于逾期状态的借款笔数
    * 
    * @param xdCurrentOverdueCount 要设置的当前处于逾期状态的借款笔数
    */
    public void setXdCurrentOverdueCount(Integer xdCurrentOverdueCount){
        this.xdCurrentOverdueCount = xdCurrentOverdueCount;
    }

    /**
    * 获取当前逾期总金额，0: 0(没有逾期); 1:[0,100]; 2:[100,500); 3:[500,1000); 4:[1000,2000); 5:[2000,4000); 6:[4000,6000); 7:[6000,10000); 8:>=10000
    *
    * @return 当前逾期总金额，0: 0(没有逾期); 1:[0,100]; 2:[100,500); 3:[500,1000); 4:[1000,2000); 5:[2000,4000); 6:[4000,6000); 7:[6000,10000); 8:>=10000
    */
    public Integer getXdCurrentOverdueAmount(){
        return xdCurrentOverdueAmount;
    }

    /**
    * 设置当前逾期总金额，0: 0(没有逾期); 1:[0,100]; 2:[100,500); 3:[500,1000); 4:[1000,2000); 5:[2000,4000); 6:[4000,6000); 7:[6000,10000); 8:>=10000
    * 
    * @param xdCurrentOverdueAmount 要设置的当前逾期总金额，0: 0(没有逾期); 1:[0,100]; 2:[100,500); 3:[500,1000); 4:[1000,2000); 5:[2000,4000); 6:[4000,6000); 7:[6000,10000); 8:>=10000
    */
    public void setXdCurrentOverdueAmount(Integer xdCurrentOverdueAmount){
        this.xdCurrentOverdueAmount = xdCurrentOverdueAmount;
    }

    /**
    * 获取有逾期90天以上运营商联系人个数
    *
    * @return 有逾期90天以上运营商联系人个数
    */
    public Integer getXdOverDue90ContactsCount(){
        return xdOverDue90ContactsCount;
    }

    /**
    * 设置有逾期90天以上运营商联系人个数
    * 
    * @param xdOverDue90ContactsCount 要设置的有逾期90天以上运营商联系人个数
    */
    public void setXdOverDue90ContactsCount(Integer xdOverDue90ContactsCount){
        this.xdOverDue90ContactsCount = xdOverDue90ContactsCount;
    }

    /**
    * 获取当前最长逾期时间(不包括已经还清的)，M1:小于1月; M2:大于1月，小于2月; M3:大于2月，小于3月; M4:3月及以上
    *
    * @return 当前最长逾期时间(不包括已经还清的)，M1:小于1月; M2:大于1月，小于2月; M3:大于2月，小于3月; M4:3月及以上
    */
    public String getXdLongestOverdueUnpaid(){
        return xdLongestOverdueUnpaid;
    }

    /**
    * 设置当前最长逾期时间(不包括已经还清的)，M1:小于1月; M2:大于1月，小于2月; M3:大于2月，小于3月; M4:3月及以上
    * 
    * @param xdLongestOverdueUnpaid 要设置的当前最长逾期时间(不包括已经还清的)，M1:小于1月; M2:大于1月，小于2月; M3:大于2月，小于3月; M4:3月及以上
    */
    public void setXdLongestOverdueUnpaid(String xdLongestOverdueUnpaid){
        this.xdLongestOverdueUnpaid = xdLongestOverdueUnpaid;
    }

    /**
    * 获取最后一次拒贷原因
    *
    * @return 最后一次拒贷原因
    */
    public String getXdLastLoanRefuseReason(){
        return xdLastLoanRefuseReason;
    }

    /**
    * 设置最后一次拒贷原因
    * 
    * @param xdLastLoanRefuseReason 要设置的最后一次拒贷原因
    */
    public void setXdLastLoanRefuseReason(String xdLastLoanRefuseReason){
        this.xdLastLoanRefuseReason = xdLastLoanRefuseReason;
    }

    /**
    * 获取最后一次拒贷时间
    *
    * @return 最后一次拒贷时间
    */
    public String getXdLastLoanRefuseTime(){
        return xdLastLoanRefuseTime;
    }

    /**
    * 设置最后一次拒贷时间
    * 
    * @param xdLastLoanRefuseTime 要设置的最后一次拒贷时间
    */
    public void setXdLastLoanRefuseTime(String xdLastLoanRefuseTime){
        this.xdLastLoanRefuseTime = xdLastLoanRefuseTime;
    }

    /**
    * 获取其他详情
    *
    * @return 其他详情
    */
    public String getXdRemark(){
        return xdRemark;
    }

    /**
    * 设置其他详情
    * 
    * @param xdRemark 要设置的其他详情
    */
    public void setXdRemark(String xdRemark){
        this.xdRemark = xdRemark;
    }

    /**
    * 获取最早借款时间
    *
    * @return 最早借款时间
    */
    public String getXdFirstLoanTime(){
        return xdFirstLoanTime;
    }

    /**
    * 设置最早借款时间
    * 
    * @param xdFirstLoanTime 要设置的最早借款时间
    */
    public void setXdFirstLoanTime(String xdFirstLoanTime){
        this.xdFirstLoanTime = xdFirstLoanTime;
    }

    /**
    * 获取是否不良用户 0-否 1-是
    *
    * @return 是否不良用户 0-否 1-是
    */
    public Integer getPpxIsBlack(){
        return ppxIsBlack;
    }

    /**
    * 设置是否不良用户 0-否 1-是
    * 
    * @param ppxIsBlack 要设置的是否不良用户 0-否 1-是
    */
    public void setPpxIsBlack(Integer ppxIsBlack){
        this.ppxIsBlack = ppxIsBlack;
    }

    /**
    * 获取是否关注用户 0-否 1-是
    *
    * @return 是否关注用户 0-否 1-是
    */
    public Integer getPpxIsAlert(){
        return ppxIsAlert;
    }

    /**
    * 设置是否关注用户 0-否 1-是
    * 
    * @param ppxIsAlert 要设置的是否关注用户 0-否 1-是
    */
    public void setPpxIsAlert(Integer ppxIsAlert){
        this.ppxIsAlert = ppxIsAlert;
    }

    /**
    * 获取逾期最早出现时间
    *
    * @return 逾期最早出现时间
    */
    public String getPpxOverdueFirstTime(){
        return ppxOverdueFirstTime;
    }

    /**
    * 设置逾期最早出现时间
    * 
    * @param ppxOverdueFirstTime 要设置的逾期最早出现时间
    */
    public void setPpxOverdueFirstTime(String ppxOverdueFirstTime){
        this.ppxOverdueFirstTime = ppxOverdueFirstTime;
    }

    /**
    * 获取逾期最近出现时间
    *
    * @return 逾期最近出现时间
    */
    public String getPpxOverdueLastTime(){
        return ppxOverdueLastTime;
    }

    /**
    * 设置逾期最近出现时间
    * 
    * @param ppxOverdueLastTime 要设置的逾期最近出现时间
    */
    public void setPpxOverdueLastTime(String ppxOverdueLastTime){
        this.ppxOverdueLastTime = ppxOverdueLastTime;
    }

    /**
    * 获取逾期累计出现次数
    *
    * @return 逾期累计出现次数
    */
    public Integer getPpxTotalOverdueCount(){
        return ppxTotalOverdueCount;
    }

    /**
    * 设置逾期累计出现次数
    * 
    * @param ppxTotalOverdueCount 要设置的逾期累计出现次数
    */
    public void setPpxTotalOverdueCount(Integer ppxTotalOverdueCount){
        this.ppxTotalOverdueCount = ppxTotalOverdueCount;
    }

    /**
    * 获取当前总逾期金额，0: 0(没有逾期); 1:[0,1000]; 2:[1000,2000); 3:[2000,3000); 4:[3000,4000); 5:[4000,6000); 6:[6000,8000); 7:[8000,10000); 8:[10000,30000); 9:[30000,50000); 10:[50000,100000); 11:>=10000
    *
    * @return 当前总逾期金额，0: 0(没有逾期); 1:[0,1000]; 2:[1000,2000); 3:[2000,3000); 4:[3000,4000); 5:[4000,6000); 6:[6000,8000); 7:[8000,10000); 8:[10000,30000); 9:[30000,50000); 10:[50000,100000); 11:>=10000
    */
    public Integer getPpxCurrentOverdueAmount(){
        return ppxCurrentOverdueAmount;
    }

    /**
    * 设置当前总逾期金额，0: 0(没有逾期); 1:[0,1000]; 2:[1000,2000); 3:[2000,3000); 4:[3000,4000); 5:[4000,6000); 6:[6000,8000); 7:[8000,10000); 8:[10000,30000); 9:[30000,50000); 10:[50000,100000); 11:>=10000
    * 
    * @param ppxCurrentOverdueAmount 要设置的当前总逾期金额，0: 0(没有逾期); 1:[0,1000]; 2:[1000,2000); 3:[2000,3000); 4:[3000,4000); 5:[4000,6000); 6:[6000,8000); 7:[8000,10000); 8:[10000,30000); 9:[30000,50000); 10:[50000,100000); 11:>=10000
    */
    public void setPpxCurrentOverdueAmount(Integer ppxCurrentOverdueAmount){
        this.ppxCurrentOverdueAmount = ppxCurrentOverdueAmount;
    }

    /**
    * 获取当前最大逾期时长 0:没有逾期 1:1-30天; 2:31-60天; M3:61-90天; 4:91-120天; 5:121-150天; 6:151-180天; 7:>180天
    *
    * @return 当前最大逾期时长 0:没有逾期 1:1-30天; 2:31-60天; M3:61-90天; 4:91-120天; 5:121-150天; 6:151-180天; 7:>180天
    */
    public Integer getPpxCurrentOverdueDays(){
        return ppxCurrentOverdueDays;
    }

    /**
    * 设置当前最大逾期时长 0:没有逾期 1:1-30天; 2:31-60天; M3:61-90天; 4:91-120天; 5:121-150天; 6:151-180天; 7:>180天
    * 
    * @param ppxCurrentOverdueDays 要设置的当前最大逾期时长 0:没有逾期 1:1-30天; 2:31-60天; M3:61-90天; 4:91-120天; 5:121-150天; 6:151-180天; 7:>180天
    */
    public void setPpxCurrentOverdueDays(Integer ppxCurrentOverdueDays){
        this.ppxCurrentOverdueDays = ppxCurrentOverdueDays;
    }

    /**
    * 获取历史最大逾期金额，0: 0(没有逾期); 1:[0,1000]; 2:[1000,2000); 3:[2000,3000); 4:[3000,4000); 5:[4000,6000); 6:[6000,8000); 7:[8000,10000); 8:[10000,30000); 9:[30000,50000); 10:[50000,100000); 11:>=10000
    *
    * @return 历史最大逾期金额，0: 0(没有逾期); 1:[0,1000]; 2:[1000,2000); 3:[2000,3000); 4:[3000,4000); 5:[4000,6000); 6:[6000,8000); 7:[8000,10000); 8:[10000,30000); 9:[30000,50000); 10:[50000,100000); 11:>=10000
    */
    public Integer getPpxHistoryOverdueAmount(){
        return ppxHistoryOverdueAmount;
    }

    /**
    * 设置历史最大逾期金额，0: 0(没有逾期); 1:[0,1000]; 2:[1000,2000); 3:[2000,3000); 4:[3000,4000); 5:[4000,6000); 6:[6000,8000); 7:[8000,10000); 8:[10000,30000); 9:[30000,50000); 10:[50000,100000); 11:>=10000
    * 
    * @param ppxHistoryOverdueAmount 要设置的历史最大逾期金额，0: 0(没有逾期); 1:[0,1000]; 2:[1000,2000); 3:[2000,3000); 4:[3000,4000); 5:[4000,6000); 6:[6000,8000); 7:[8000,10000); 8:[10000,30000); 9:[30000,50000); 10:[50000,100000); 11:>=10000
    */
    public void setPpxHistoryOverdueAmount(Integer ppxHistoryOverdueAmount){
        this.ppxHistoryOverdueAmount = ppxHistoryOverdueAmount;
    }

    /**
    * 获取历史最大逾期时长 0:没有逾期 1:1-30天; 2:31-60天; M3:61-90天; 4:91-120天; 5:121-150天; 6:151-180天; 7:>180天
    *
    * @return 历史最大逾期时长 0:没有逾期 1:1-30天; 2:31-60天; M3:61-90天; 4:91-120天; 5:121-150天; 6:151-180天; 7:>180天
    */
    public Integer getPpxHistoryOverdueDays(){
        return ppxHistoryOverdueDays;
    }

    /**
    * 设置历史最大逾期时长 0:没有逾期 1:1-30天; 2:31-60天; M3:61-90天; 4:91-120天; 5:121-150天; 6:151-180天; 7:>180天
    * 
    * @param ppxHistoryOverdueDays 要设置的历史最大逾期时长 0:没有逾期 1:1-30天; 2:31-60天; M3:61-90天; 4:91-120天; 5:121-150天; 6:151-180天; 7:>180天
    */
    public void setPpxHistoryOverdueDays(Integer ppxHistoryOverdueDays){
        this.ppxHistoryOverdueDays = ppxHistoryOverdueDays;
    }

    /**
    * 获取欺诈最早出现时间
    *
    * @return 欺诈最早出现时间
    */
    public String getPpxFraudFirstTime(){
        return ppxFraudFirstTime;
    }

    /**
    * 设置欺诈最早出现时间
    * 
    * @param ppxFraudFirstTime 要设置的欺诈最早出现时间
    */
    public void setPpxFraudFirstTime(String ppxFraudFirstTime){
        this.ppxFraudFirstTime = ppxFraudFirstTime;
    }

    /**
    * 获取欺诈最近出现时间
    *
    * @return 欺诈最近出现时间
    */
    public String getPpxFraudLastTime(){
        return ppxFraudLastTime;
    }

    /**
    * 设置欺诈最近出现时间
    * 
    * @param ppxFraudLastTime 要设置的欺诈最近出现时间
    */
    public void setPpxFraudLastTime(String ppxFraudLastTime){
        this.ppxFraudLastTime = ppxFraudLastTime;
    }

    /**
    * 获取欺诈累计出现次数
    *
    * @return 欺诈累计出现次数
    */
    public Integer getPpxTotalFraudCount(){
        return ppxTotalFraudCount;
    }

    /**
    * 设置欺诈累计出现次数
    * 
    * @param ppxTotalFraudCount 要设置的欺诈累计出现次数
    */
    public void setPpxTotalFraudCount(Integer ppxTotalFraudCount){
        this.ppxTotalFraudCount = ppxTotalFraudCount;
    }

    /**
    * 获取负面最早出现时间
    *
    * @return 负面最早出现时间
    */
    public String getPpxNegativeFirstTime(){
        return ppxNegativeFirstTime;
    }

    /**
    * 设置负面最早出现时间
    * 
    * @param ppxNegativeFirstTime 要设置的负面最早出现时间
    */
    public void setPpxNegativeFirstTime(String ppxNegativeFirstTime){
        this.ppxNegativeFirstTime = ppxNegativeFirstTime;
    }

    /**
    * 获取负面最近出现时间
    *
    * @return 负面最近出现时间
    */
    public String getPpxNegativeLastTime(){
        return ppxNegativeLastTime;
    }

    /**
    * 设置负面最近出现时间
    * 
    * @param ppxNegativeLastTime 要设置的负面最近出现时间
    */
    public void setPpxNegativeLastTime(String ppxNegativeLastTime){
        this.ppxNegativeLastTime = ppxNegativeLastTime;
    }

    /**
    * 获取负面累计出现次数
    *
    * @return 负面累计出现次数
    */
    public Integer getPpxTotalNegativeCount(){
        return ppxTotalNegativeCount;
    }

    /**
    * 设置负面累计出现次数
    * 
    * @param ppxTotalNegativeCount 要设置的负面累计出现次数
    */
    public void setPpxTotalNegativeCount(Integer ppxTotalNegativeCount){
        this.ppxTotalNegativeCount = ppxTotalNegativeCount;
    }

    /**
    * 获取贷款行为分
    *
    * @return 贷款行为分
    */
    public String getXyLoansScore(){
        return xyLoansScore;
    }

    /**
    * 设置贷款行为分
    * 
    * @param xyLoansScore 要设置的贷款行为分
    */
    public void setXyLoansScore(String xyLoansScore){
        this.xyLoansScore = xyLoansScore;
    }

    /**
    * 获取贷款行为置信度
    *
    * @return 贷款行为置信度
    */
    public String getXyLoansCredibility(){
        return xyLoansCredibility;
    }

    /**
    * 设置贷款行为置信度
    * 
    * @param xyLoansCredibility 要设置的贷款行为置信度
    */
    public void setXyLoansCredibility(String xyLoansCredibility){
        this.xyLoansCredibility = xyLoansCredibility;
    }

    /**
    * 获取贷款放款总订单数
    *
    * @return 贷款放款总订单数
    */
    public String getXyLoansCount(){
        return xyLoansCount;
    }

    /**
    * 设置贷款放款总订单数
    * 
    * @param xyLoansCount 要设置的贷款放款总订单数
    */
    public void setXyLoansCount(String xyLoansCount){
        this.xyLoansCount = xyLoansCount;
    }

    /**
    * 获取贷款已结清订单数
    *
    * @return 贷款已结清订单数
    */
    public String getXyLoansSettleCount(){
        return xyLoansSettleCount;
    }

    /**
    * 设置贷款已结清订单数
    * 
    * @param xyLoansSettleCount 要设置的贷款已结清订单数
    */
    public void setXyLoansSettleCount(String xyLoansSettleCount){
        this.xyLoansSettleCount = xyLoansSettleCount;
    }

    /**
    * 获取贷款逾期订单数（M0+)
    *
    * @return 贷款逾期订单数（M0+)
    */
    public String getXyLoansOverdueCount(){
        return xyLoansOverdueCount;
    }

    /**
    * 设置贷款逾期订单数（M0+)
    * 
    * @param xyLoansOverdueCount 要设置的贷款逾期订单数（M0+)
    */
    public void setXyLoansOverdueCount(String xyLoansOverdueCount){
        this.xyLoansOverdueCount = xyLoansOverdueCount;
    }

    /**
    * 获取贷款机构数
    *
    * @return 贷款机构数
    */
    public String getXyLoansOrgCount(){
        return xyLoansOrgCount;
    }

    /**
    * 设置贷款机构数
    * 
    * @param xyLoansOrgCount 要设置的贷款机构数
    */
    public void setXyLoansOrgCount(String xyLoansOrgCount){
        this.xyLoansOrgCount = xyLoansOrgCount;
    }

    /**
    * 获取消费金融类机构数
    *
    * @return 消费金融类机构数
    */
    public String getXyConsfinOrgCount(){
        return xyConsfinOrgCount;
    }

    /**
    * 设置消费金融类机构数
    * 
    * @param xyConsfinOrgCount 要设置的消费金融类机构数
    */
    public void setXyConsfinOrgCount(String xyConsfinOrgCount){
        this.xyConsfinOrgCount = xyConsfinOrgCount;
    }

    /**
    * 获取网络贷款类机构数
    *
    * @return 网络贷款类机构数
    */
    public String getXyLoansCashCount(){
        return xyLoansCashCount;
    }

    /**
    * 设置网络贷款类机构数
    * 
    * @param xyLoansCashCount 要设置的网络贷款类机构数
    */
    public void setXyLoansCashCount(String xyLoansCashCount){
        this.xyLoansCashCount = xyLoansCashCount;
    }

    /**
    * 获取近1个月贷款笔数
    *
    * @return 近1个月贷款笔数
    */
    public String getXyLatestOneMonth(){
        return xyLatestOneMonth;
    }

    /**
    * 设置近1个月贷款笔数
    * 
    * @param xyLatestOneMonth 要设置的近1个月贷款笔数
    */
    public void setXyLatestOneMonth(String xyLatestOneMonth){
        this.xyLatestOneMonth = xyLatestOneMonth;
    }

    /**
    * 获取近3个月贷款笔数
    *
    * @return 近3个月贷款笔数
    */
    public String getXyLatestThreeMonth(){
        return xyLatestThreeMonth;
    }

    /**
    * 设置近3个月贷款笔数
    * 
    * @param xyLatestThreeMonth 要设置的近3个月贷款笔数
    */
    public void setXyLatestThreeMonth(String xyLatestThreeMonth){
        this.xyLatestThreeMonth = xyLatestThreeMonth;
    }

    /**
    * 获取近6个月贷款笔数
    *
    * @return 近6个月贷款笔数
    */
    public String getXyLatestSixMonth(){
        return xyLatestSixMonth;
    }

    /**
    * 设置近6个月贷款笔数
    * 
    * @param xyLatestSixMonth 要设置的近6个月贷款笔数
    */
    public void setXyLatestSixMonth(String xyLatestSixMonth){
        this.xyLatestSixMonth = xyLatestSixMonth;
    }

    /**
    * 获取历史贷款机构成功扣款笔数
    *
    * @return 历史贷款机构成功扣款笔数
    */
    public String getXyHistorySucFee(){
        return xyHistorySucFee;
    }

    /**
    * 设置历史贷款机构成功扣款笔数
    * 
    * @param xyHistorySucFee 要设置的历史贷款机构成功扣款笔数
    */
    public void setXyHistorySucFee(String xyHistorySucFee){
        this.xyHistorySucFee = xyHistorySucFee;
    }

    /**
    * 获取历史贷款机构失败扣款笔数
    *
    * @return 历史贷款机构失败扣款笔数
    */
    public String getXyHistoryFailFee(){
        return xyHistoryFailFee;
    }

    /**
    * 设置历史贷款机构失败扣款笔数
    * 
    * @param xyHistoryFailFee 要设置的历史贷款机构失败扣款笔数
    */
    public void setXyHistoryFailFee(String xyHistoryFailFee){
        this.xyHistoryFailFee = xyHistoryFailFee;
    }

    /**
    * 获取历史贷款机构成功扣款数-失败数
    *
    * @return 历史贷款机构成功扣款数-失败数
    */
    public Integer getXyHistorySucMinusFailNum(){
        return xyHistorySucMinusFailNum;
    }

    /**
    * 设置历史贷款机构成功扣款数-失败数
    * 
    * @param xyHistorySucMinusFailNum 要设置的历史贷款机构成功扣款数-失败数
    */
    public void setXyHistorySucMinusFailNum(Integer xyHistorySucMinusFailNum){
        this.xyHistorySucMinusFailNum = xyHistorySucMinusFailNum;
    }

    /**
    * 获取近1个月贷款机构成功扣款笔数
    *
    * @return 近1个月贷款机构成功扣款笔数
    */
    public String getXyLatestOneMonthSuc(){
        return xyLatestOneMonthSuc;
    }

    /**
    * 设置近1个月贷款机构成功扣款笔数
    * 
    * @param xyLatestOneMonthSuc 要设置的近1个月贷款机构成功扣款笔数
    */
    public void setXyLatestOneMonthSuc(String xyLatestOneMonthSuc){
        this.xyLatestOneMonthSuc = xyLatestOneMonthSuc;
    }

    /**
    * 获取近1个月贷款机构失败扣款笔数
    *
    * @return 近1个月贷款机构失败扣款笔数
    */
    public String getXyLatestOneMonthFail(){
        return xyLatestOneMonthFail;
    }

    /**
    * 设置近1个月贷款机构失败扣款笔数
    * 
    * @param xyLatestOneMonthFail 要设置的近1个月贷款机构失败扣款笔数
    */
    public void setXyLatestOneMonthFail(String xyLatestOneMonthFail){
        this.xyLatestOneMonthFail = xyLatestOneMonthFail;
    }

    /**
    * 获取近1个月贷款机构成功扣款数-失败数
    *
    * @return 近1个月贷款机构成功扣款数-失败数
    */
    public Integer getXyLatestOneMonthSucMinusFailNum(){
        return xyLatestOneMonthSucMinusFailNum;
    }

    /**
    * 设置近1个月贷款机构成功扣款数-失败数
    * 
    * @param xyLatestOneMonthSucMinusFailNum 要设置的近1个月贷款机构成功扣款数-失败数
    */
    public void setXyLatestOneMonthSucMinusFailNum(Integer xyLatestOneMonthSucMinusFailNum){
        this.xyLatestOneMonthSucMinusFailNum = xyLatestOneMonthSucMinusFailNum;
    }

    /**
    * 获取信用贷款时长
    *
    * @return 信用贷款时长
    */
    public String getXyLoansLongTime(){
        return xyLoansLongTime;
    }

    /**
    * 设置信用贷款时长
    * 
    * @param xyLoansLongTime 要设置的信用贷款时长
    */
    public void setXyLoansLongTime(String xyLoansLongTime){
        this.xyLoansLongTime = xyLoansLongTime;
    }

    /**
    * 获取最近一次贷款时间
    *
    * @return 最近一次贷款时间
    */
    public String getXyLoansLatestTime(){
        return xyLoansLatestTime;
    }

    /**
    * 设置最近一次贷款时间
    * 
    * @param xyLoansLatestTime 要设置的最近一次贷款时间
    */
    public void setXyLoansLatestTime(String xyLoansLatestTime){
        this.xyLoansLatestTime = xyLoansLatestTime;
    }

    /**
    * 获取近一周与几个催收号码有过联系
    *
    * @return 近一周与几个催收号码有过联系
    */
    public Integer getPaL1wwdcnTNumsCon(){
        return paL1wwdcnTNumsCon;
    }

    /**
    * 设置近一周与几个催收号码有过联系
    * 
    * @param paL1wwdcnTNumsCon 要设置的近一周与几个催收号码有过联系
    */
    public void setPaL1wwdcnTNumsCon(Integer paL1wwdcnTNumsCon){
        this.paL1wwdcnTNumsCon = paL1wwdcnTNumsCon;
    }

    /**
    * 获取近一周与几家银行机构催收号码有过联系
    *
    * @return 近一周与几家银行机构催收号码有过联系
    */
    public Integer getPaL1wwdcnTNumsConBank(){
        return paL1wwdcnTNumsConBank;
    }

    /**
    * 设置近一周与几家银行机构催收号码有过联系
    * 
    * @param paL1wwdcnTNumsConBank 要设置的近一周与几家银行机构催收号码有过联系
    */
    public void setPaL1wwdcnTNumsConBank(Integer paL1wwdcnTNumsConBank){
        this.paL1wwdcnTNumsConBank = paL1wwdcnTNumsConBank;
    }

    /**
    * 获取近一周与几家消费金融机构催收号码有过联系
    *
    * @return 近一周与几家消费金融机构催收号码有过联系
    */
    public Integer getPaL1wwdcnTNumsConCf(){
        return paL1wwdcnTNumsConCf;
    }

    /**
    * 设置近一周与几家消费金融机构催收号码有过联系
    * 
    * @param paL1wwdcnTNumsConCf 要设置的近一周与几家消费金融机构催收号码有过联系
    */
    public void setPaL1wwdcnTNumsConCf(Integer paL1wwdcnTNumsConCf){
        this.paL1wwdcnTNumsConCf = paL1wwdcnTNumsConCf;
    }

    /**
    * 获取近一周与几家委外催收机构催收号码有过联系
    *
    * @return 近一周与几家委外催收机构催收号码有过联系
    */
    public Integer getPaL1wwdcnTNumsConF(){
        return paL1wwdcnTNumsConF;
    }

    /**
    * 设置近一周与几家委外催收机构催收号码有过联系
    * 
    * @param paL1wwdcnTNumsConF 要设置的近一周与几家委外催收机构催收号码有过联系
    */
    public void setPaL1wwdcnTNumsConF(Integer paL1wwdcnTNumsConF){
        this.paL1wwdcnTNumsConF = paL1wwdcnTNumsConF;
    }

    /**
    * 获取近一周与几家互联网金融机构催收号码有过联系
    *
    * @return 近一周与几家互联网金融机构催收号码有过联系
    */
    public Integer getPaL1wwdcnTNumsConIf(){
        return paL1wwdcnTNumsConIf;
    }

    /**
    * 设置近一周与几家互联网金融机构催收号码有过联系
    * 
    * @param paL1wwdcnTNumsConIf 要设置的近一周与几家互联网金融机构催收号码有过联系
    */
    public void setPaL1wwdcnTNumsConIf(Integer paL1wwdcnTNumsConIf){
        this.paL1wwdcnTNumsConIf = paL1wwdcnTNumsConIf;
    }

    /**
    * 获取近一周涉及催收号码的总机构数
    *
    * @return 近一周涉及催收号码的总机构数
    */
    public Integer getPaL1wwdcnTNumsConOrg(){
        return paL1wwdcnTNumsConOrg;
    }

    /**
    * 设置近一周涉及催收号码的总机构数
    * 
    * @param paL1wwdcnTNumsConOrg 要设置的近一周涉及催收号码的总机构数
    */
    public void setPaL1wwdcnTNumsConOrg(Integer paL1wwdcnTNumsConOrg){
        this.paL1wwdcnTNumsConOrg = paL1wwdcnTNumsConOrg;
    }

    /**
    * 获取近一周被催收号码呼叫次数
    *
    * @return 近一周被催收号码呼叫次数
    */
    public Integer getPaL1wwdcnTTimesIn(){
        return paL1wwdcnTTimesIn;
    }

    /**
    * 设置近一周被催收号码呼叫次数
    * 
    * @param paL1wwdcnTTimesIn 要设置的近一周被催收号码呼叫次数
    */
    public void setPaL1wwdcnTTimesIn(Integer paL1wwdcnTTimesIn){
        this.paL1wwdcnTTimesIn = paL1wwdcnTTimesIn;
    }

    /**
    * 获取近一周主叫催收号码次数
    *
    * @return 近一周主叫催收号码次数
    */
    public Integer getPaL1wwdcnTTimesOut(){
        return paL1wwdcnTTimesOut;
    }

    /**
    * 设置近一周主叫催收号码次数
    * 
    * @param paL1wwdcnTTimesOut 要设置的近一周主叫催收号码次数
    */
    public void setPaL1wwdcnTTimesOut(Integer paL1wwdcnTTimesOut){
        this.paL1wwdcnTTimesOut = paL1wwdcnTTimesOut;
    }

    /**
    * 获取近两周联系机构类型总数
    *
    * @return 近两周联系机构类型总数
    */
    public Integer getPaL2wwdcnTNumsConOrgType(){
        return paL2wwdcnTNumsConOrgType;
    }

    /**
    * 设置近两周联系机构类型总数
    * 
    * @param paL2wwdcnTNumsConOrgType 要设置的近两周联系机构类型总数
    */
    public void setPaL2wwdcnTNumsConOrgType(Integer paL2wwdcnTNumsConOrgType){
        this.paL2wwdcnTNumsConOrgType = paL2wwdcnTNumsConOrgType;
    }

    /**
    * 获取近三周联系互联网金融机构的总个数
    *
    * @return 近三周联系互联网金融机构的总个数
    */
    public Integer getPaL3wwdcnTNumsConIf(){
        return paL3wwdcnTNumsConIf;
    }

    /**
    * 设置近三周联系互联网金融机构的总个数
    * 
    * @param paL3wwdcnTNumsConIf 要设置的近三周联系互联网金融机构的总个数
    */
    public void setPaL3wwdcnTNumsConIf(Integer paL3wwdcnTNumsConIf){
        this.paL3wwdcnTNumsConIf = paL3wwdcnTNumsConIf;
    }

    /**
    * 获取近一月与几个催收号码有过联系
    *
    * @return 近一月与几个催收号码有过联系
    */
    public Integer getPaL1mwdcnTNumsCon(){
        return paL1mwdcnTNumsCon;
    }

    /**
    * 设置近一月与几个催收号码有过联系
    * 
    * @param paL1mwdcnTNumsCon 要设置的近一月与几个催收号码有过联系
    */
    public void setPaL1mwdcnTNumsCon(Integer paL1mwdcnTNumsCon){
        this.paL1mwdcnTNumsCon = paL1mwdcnTNumsCon;
    }

    /**
    * 获取近一月与几家银行机构催收号码有过联系
    *
    * @return 近一月与几家银行机构催收号码有过联系
    */
    public Integer getPaL1mwdcnTNumsConBank(){
        return paL1mwdcnTNumsConBank;
    }

    /**
    * 设置近一月与几家银行机构催收号码有过联系
    * 
    * @param paL1mwdcnTNumsConBank 要设置的近一月与几家银行机构催收号码有过联系
    */
    public void setPaL1mwdcnTNumsConBank(Integer paL1mwdcnTNumsConBank){
        this.paL1mwdcnTNumsConBank = paL1mwdcnTNumsConBank;
    }

    /**
    * 获取近一月与几家消费金融机构催收号码有过联系
    *
    * @return 近一月与几家消费金融机构催收号码有过联系
    */
    public Integer getPaL1mwdcnTNumsConCf(){
        return paL1mwdcnTNumsConCf;
    }

    /**
    * 设置近一月与几家消费金融机构催收号码有过联系
    * 
    * @param paL1mwdcnTNumsConCf 要设置的近一月与几家消费金融机构催收号码有过联系
    */
    public void setPaL1mwdcnTNumsConCf(Integer paL1mwdcnTNumsConCf){
        this.paL1mwdcnTNumsConCf = paL1mwdcnTNumsConCf;
    }

    /**
    * 获取近一月与几家委外催收机构催收号码有过联系
    *
    * @return 近一月与几家委外催收机构催收号码有过联系
    */
    public Integer getPaL1mwdcnTNumsConF(){
        return paL1mwdcnTNumsConF;
    }

    /**
    * 设置近一月与几家委外催收机构催收号码有过联系
    * 
    * @param paL1mwdcnTNumsConF 要设置的近一月与几家委外催收机构催收号码有过联系
    */
    public void setPaL1mwdcnTNumsConF(Integer paL1mwdcnTNumsConF){
        this.paL1mwdcnTNumsConF = paL1mwdcnTNumsConF;
    }

    /**
    * 获取近一月与几家互联网金融机构催收号码有过联系
    *
    * @return 近一月与几家互联网金融机构催收号码有过联系
    */
    public Integer getPaL1mwdcnTNumsConIf(){
        return paL1mwdcnTNumsConIf;
    }

    /**
    * 设置近一月与几家互联网金融机构催收号码有过联系
    * 
    * @param paL1mwdcnTNumsConIf 要设置的近一月与几家互联网金融机构催收号码有过联系
    */
    public void setPaL1mwdcnTNumsConIf(Integer paL1mwdcnTNumsConIf){
        this.paL1mwdcnTNumsConIf = paL1mwdcnTNumsConIf;
    }

    /**
    * 获取近一月涉及催收号码的总机构数
    *
    * @return 近一月涉及催收号码的总机构数
    */
    public Integer getPaL1mwdcnTNumsConOrg(){
        return paL1mwdcnTNumsConOrg;
    }

    /**
    * 设置近一月涉及催收号码的总机构数
    * 
    * @param paL1mwdcnTNumsConOrg 要设置的近一月涉及催收号码的总机构数
    */
    public void setPaL1mwdcnTNumsConOrg(Integer paL1mwdcnTNumsConOrg){
        this.paL1mwdcnTNumsConOrg = paL1mwdcnTNumsConOrg;
    }

    /**
    * 获取近一月被催收号码呼叫次数
    *
    * @return 近一月被催收号码呼叫次数
    */
    public Integer getPaL1mwdcnTTimesIn(){
        return paL1mwdcnTTimesIn;
    }

    /**
    * 设置近一月被催收号码呼叫次数
    * 
    * @param paL1mwdcnTTimesIn 要设置的近一月被催收号码呼叫次数
    */
    public void setPaL1mwdcnTTimesIn(Integer paL1mwdcnTTimesIn){
        this.paL1mwdcnTTimesIn = paL1mwdcnTTimesIn;
    }

    /**
    * 获取近一月主叫催收号码次数
    *
    * @return 近一月主叫催收号码次数
    */
    public Integer getPaL1mwdcnTTimesOut(){
        return paL1mwdcnTTimesOut;
    }

    /**
    * 设置近一月主叫催收号码次数
    * 
    * @param paL1mwdcnTTimesOut 要设置的近一月主叫催收号码次数
    */
    public void setPaL1mwdcnTTimesOut(Integer paL1mwdcnTTimesOut){
        this.paL1mwdcnTTimesOut = paL1mwdcnTTimesOut;
    }

    /**
    * 获取近两月被催收号码呼叫次数
    *
    * @return 近两月被催收号码呼叫次数
    */
    public Integer getPaL2mwdcnTTimesIn(){
        return paL2mwdcnTTimesIn;
    }

    /**
    * 设置近两月被催收号码呼叫次数
    * 
    * @param paL2mwdcnTTimesIn 要设置的近两月被催收号码呼叫次数
    */
    public void setPaL2mwdcnTTimesIn(Integer paL2mwdcnTTimesIn){
        this.paL2mwdcnTTimesIn = paL2mwdcnTTimesIn;
    }

    /**
    * 获取近两月申请人收到催收号的总个数
    *
    * @return 近两月申请人收到催收号的总个数
    */
    public Integer getPaL2mwdcnTNumsIn(){
        return paL2mwdcnTNumsIn;
    }

    /**
    * 设置近两月申请人收到催收号的总个数
    * 
    * @param paL2mwdcnTNumsIn 要设置的近两月申请人收到催收号的总个数
    */
    public void setPaL2mwdcnTNumsIn(Integer paL2mwdcnTNumsIn){
        this.paL2mwdcnTNumsIn = paL2mwdcnTNumsIn;
    }

    /**
    * 获取近两月被单个催收号码呼叫的最大次数
    *
    * @return 近两月被单个催收号码呼叫的最大次数
    */
    public Integer getPaL2mwdcnTaxTimesIn(){
        return paL2mwdcnTaxTimesIn;
    }

    /**
    * 设置近两月被单个催收号码呼叫的最大次数
    * 
    * @param paL2mwdcnTaxTimesIn 要设置的近两月被单个催收号码呼叫的最大次数
    */
    public void setPaL2mwdcnTaxTimesIn(Integer paL2mwdcnTaxTimesIn){
        this.paL2mwdcnTaxTimesIn = paL2mwdcnTaxTimesIn;
    }

    /**
    * 获取近两月申请人联系次数最大的催收号的总时长
    *
    * @return 近两月申请人联系次数最大的催收号的总时长
    */
    public Integer getPaL2mwdcnMaxTimesCon(){
        return paL2mwdcnMaxTimesCon;
    }

    /**
    * 设置近两月申请人联系次数最大的催收号的总时长
    * 
    * @param paL2mwdcnMaxTimesCon 要设置的近两月申请人联系次数最大的催收号的总时长
    */
    public void setPaL2mwdcnMaxTimesCon(Integer paL2mwdcnMaxTimesCon){
        this.paL2mwdcnMaxTimesCon = paL2mwdcnMaxTimesCon;
    }

    /**
    * 获取近两月晚上联系催收号的总天数
    *
    * @return 近两月晚上联系催收号的总天数
    */
    public Integer getPaL2mencnTTaysCon(){
        return paL2mencnTTaysCon;
    }

    /**
    * 设置近两月晚上联系催收号的总天数
    * 
    * @param paL2mencnTTaysCon 要设置的近两月晚上联系催收号的总天数
    */
    public void setPaL2mencnTTaysCon(Integer paL2mencnTTaysCon){
        this.paL2mencnTTaysCon = paL2mencnTTaysCon;
    }

    /**
    * 获取近三月与几个催收号码有过联系
    *
    * @return 近三月与几个催收号码有过联系
    */
    public Integer getPaL3mwdcnTNumsCon(){
        return paL3mwdcnTNumsCon;
    }

    /**
    * 设置近三月与几个催收号码有过联系
    * 
    * @param paL3mwdcnTNumsCon 要设置的近三月与几个催收号码有过联系
    */
    public void setPaL3mwdcnTNumsCon(Integer paL3mwdcnTNumsCon){
        this.paL3mwdcnTNumsCon = paL3mwdcnTNumsCon;
    }

    /**
    * 获取近三月与几家银行机构催收号码有过联系
    *
    * @return 近三月与几家银行机构催收号码有过联系
    */
    public Integer getPaL3mwdcnTNumsConBank(){
        return paL3mwdcnTNumsConBank;
    }

    /**
    * 设置近三月与几家银行机构催收号码有过联系
    * 
    * @param paL3mwdcnTNumsConBank 要设置的近三月与几家银行机构催收号码有过联系
    */
    public void setPaL3mwdcnTNumsConBank(Integer paL3mwdcnTNumsConBank){
        this.paL3mwdcnTNumsConBank = paL3mwdcnTNumsConBank;
    }

    /**
    * 获取近三月与几家消费金融机构催收号码有过联系
    *
    * @return 近三月与几家消费金融机构催收号码有过联系
    */
    public Integer getPaL3mwdcnTNumsConCf(){
        return paL3mwdcnTNumsConCf;
    }

    /**
    * 设置近三月与几家消费金融机构催收号码有过联系
    * 
    * @param paL3mwdcnTNumsConCf 要设置的近三月与几家消费金融机构催收号码有过联系
    */
    public void setPaL3mwdcnTNumsConCf(Integer paL3mwdcnTNumsConCf){
        this.paL3mwdcnTNumsConCf = paL3mwdcnTNumsConCf;
    }

    /**
    * 获取近三月与几家委外催收机构催收号码有过联系
    *
    * @return 近三月与几家委外催收机构催收号码有过联系
    */
    public Integer getPaL3mwdcnTNumsConF(){
        return paL3mwdcnTNumsConF;
    }

    /**
    * 设置近三月与几家委外催收机构催收号码有过联系
    * 
    * @param paL3mwdcnTNumsConF 要设置的近三月与几家委外催收机构催收号码有过联系
    */
    public void setPaL3mwdcnTNumsConF(Integer paL3mwdcnTNumsConF){
        this.paL3mwdcnTNumsConF = paL3mwdcnTNumsConF;
    }

    /**
    * 获取近三月与几家互联网金融机构催收号码有过联系
    *
    * @return 近三月与几家互联网金融机构催收号码有过联系
    */
    public Integer getPaL3mwdcnTNumsConIf(){
        return paL3mwdcnTNumsConIf;
    }

    /**
    * 设置近三月与几家互联网金融机构催收号码有过联系
    * 
    * @param paL3mwdcnTNumsConIf 要设置的近三月与几家互联网金融机构催收号码有过联系
    */
    public void setPaL3mwdcnTNumsConIf(Integer paL3mwdcnTNumsConIf){
        this.paL3mwdcnTNumsConIf = paL3mwdcnTNumsConIf;
    }

    /**
    * 获取近三月晚上涉及催收号码的总机构数
    *
    * @return 近三月晚上涉及催收号码的总机构数
    */
    public Integer getPaL3mencnTNumsConOrg(){
        return paL3mencnTNumsConOrg;
    }

    /**
    * 设置近三月晚上涉及催收号码的总机构数
    * 
    * @param paL3mencnTNumsConOrg 要设置的近三月晚上涉及催收号码的总机构数
    */
    public void setPaL3mencnTNumsConOrg(Integer paL3mencnTNumsConOrg){
        this.paL3mencnTNumsConOrg = paL3mencnTNumsConOrg;
    }

    /**
    * 获取近三月涉及催收号码的总机构数
    *
    * @return 近三月涉及催收号码的总机构数
    */
    public Integer getPaL3mwdcnTNumsConOrg(){
        return paL3mwdcnTNumsConOrg;
    }

    /**
    * 设置近三月涉及催收号码的总机构数
    * 
    * @param paL3mwdcnTNumsConOrg 要设置的近三月涉及催收号码的总机构数
    */
    public void setPaL3mwdcnTNumsConOrg(Integer paL3mwdcnTNumsConOrg){
        this.paL3mwdcnTNumsConOrg = paL3mwdcnTNumsConOrg;
    }

    /**
    * 获取近三月被催收号码呼叫次数
    *
    * @return 近三月被催收号码呼叫次数
    */
    public Integer getPaL3mwdcnTTimesIn(){
        return paL3mwdcnTTimesIn;
    }

    /**
    * 设置近三月被催收号码呼叫次数
    * 
    * @param paL3mwdcnTTimesIn 要设置的近三月被催收号码呼叫次数
    */
    public void setPaL3mwdcnTTimesIn(Integer paL3mwdcnTTimesIn){
        this.paL3mwdcnTTimesIn = paL3mwdcnTTimesIn;
    }

    /**
    * 获取近三月主叫催收号码次数
    *
    * @return 近三月主叫催收号码次数
    */
    public Integer getPaL3mwdcnTTimesOut(){
        return paL3mwdcnTTimesOut;
    }

    /**
    * 设置近三月主叫催收号码次数
    * 
    * @param paL3mwdcnTTimesOut 要设置的近三月主叫催收号码次数
    */
    public void setPaL3mwdcnTTimesOut(Integer paL3mwdcnTTimesOut){
        this.paL3mwdcnTTimesOut = paL3mwdcnTTimesOut;
    }

    /**
    * 获取近三月新增机构数
    *
    * @return 近三月新增机构数
    */
    public Integer getPaL3mwdcnAddTNumsInOrg(){
        return paL3mwdcnAddTNumsInOrg;
    }

    /**
    * 设置近三月新增机构数
    * 
    * @param paL3mwdcnAddTNumsInOrg 要设置的近三月新增机构数
    */
    public void setPaL3mwdcnAddTNumsInOrg(Integer paL3mwdcnAddTNumsInOrg){
        this.paL3mwdcnAddTNumsInOrg = paL3mwdcnAddTNumsInOrg;
    }

    /**
    * 获取近三月被每个催收号呼叫的天数的最大值
    *
    * @return 近三月被每个催收号呼叫的天数的最大值
    */
    public Integer getPaL3mwdcnMaxDaysIn(){
        return paL3mwdcnMaxDaysIn;
    }

    /**
    * 设置近三月被每个催收号呼叫的天数的最大值
    * 
    * @param paL3mwdcnMaxDaysIn 要设置的近三月被每个催收号呼叫的天数的最大值
    */
    public void setPaL3mwdcnMaxDaysIn(Integer paL3mwdcnMaxDaysIn){
        this.paL3mwdcnMaxDaysIn = paL3mwdcnMaxDaysIn;
    }

    /**
    * 获取近四月申请人联系机构类型的总个数
    *
    * @return 近四月申请人联系机构类型的总个数
    */
    public Integer getPaL4mwdcnTNumsConOrgType(){
        return paL4mwdcnTNumsConOrgType;
    }

    /**
    * 设置近四月申请人联系机构类型的总个数
    * 
    * @param paL4mwdcnTNumsConOrgType 要设置的近四月申请人联系机构类型的总个数
    */
    public void setPaL4mwdcnTNumsConOrgType(Integer paL4mwdcnTNumsConOrgType){
        this.paL4mwdcnTNumsConOrgType = paL4mwdcnTNumsConOrgType;
    }

    /**
    * 获取近四月非银机构呼入的总次数
    *
    * @return 近四月非银机构呼入的总次数
    */
    public Integer getPaL4mwdcnTTimesInNonBank(){
        return paL4mwdcnTTimesInNonBank;
    }

    /**
    * 设置近四月非银机构呼入的总次数
    * 
    * @param paL4mwdcnTTimesInNonBank 要设置的近四月非银机构呼入的总次数
    */
    public void setPaL4mwdcnTTimesInNonBank(Integer paL4mwdcnTTimesInNonBank){
        this.paL4mwdcnTTimesInNonBank = paL4mwdcnTTimesInNonBank;
    }

    /**
    * 获取近四月联系催收号的总时长
    *
    * @return 近四月联系催收号的总时长
    */
    public Integer getPaL4mwdcnTDurCon(){
        return paL4mwdcnTDurCon;
    }

    /**
    * 设置近四月联系催收号的总时长
    * 
    * @param paL4mwdcnTDurCon 要设置的近四月联系催收号的总时长
    */
    public void setPaL4mwdcnTDurCon(Integer paL4mwdcnTDurCon){
        this.paL4mwdcnTDurCon = paL4mwdcnTDurCon;
    }

    /**
    * 获取近四月联系催收号的总次数
    *
    * @return 近四月联系催收号的总次数
    */
    public Integer getPaL4mwdcnTTimesCon(){
        return paL4mwdcnTTimesCon;
    }

    /**
    * 设置近四月联系催收号的总次数
    * 
    * @param paL4mwdcnTTimesCon 要设置的近四月联系催收号的总次数
    */
    public void setPaL4mwdcnTTimesCon(Integer paL4mwdcnTTimesCon){
        this.paL4mwdcnTTimesCon = paL4mwdcnTTimesCon;
    }

    /**
    * 获取近四月与几家委外催收机构催收号码有过联系
    *
    * @return 近四月与几家委外催收机构催收号码有过联系
    */
    public Integer getPaL4mwdcnTNumsConF(){
        return paL4mwdcnTNumsConF;
    }

    /**
    * 设置近四月与几家委外催收机构催收号码有过联系
    * 
    * @param paL4mwdcnTNumsConF 要设置的近四月与几家委外催收机构催收号码有过联系
    */
    public void setPaL4mwdcnTNumsConF(Integer paL4mwdcnTNumsConF){
        this.paL4mwdcnTNumsConF = paL4mwdcnTNumsConF;
    }

    /**
    * 获取近四月被催收号呼叫的总时长
    *
    * @return 近四月被催收号呼叫的总时长
    */
    public Integer getPaL4mwdcnTDurIn(){
        return paL4mwdcnTDurIn;
    }

    /**
    * 设置近四月被催收号呼叫的总时长
    * 
    * @param paL4mwdcnTDurIn 要设置的近四月被催收号呼叫的总时长
    */
    public void setPaL4mwdcnTDurIn(Integer paL4mwdcnTDurIn){
        this.paL4mwdcnTDurIn = paL4mwdcnTDurIn;
    }

    /**
    * 获取近四月被叫次数最大的催收号的总时长
    *
    * @return 近四月被叫次数最大的催收号的总时长
    */
    public Integer getPaL4mwdcnTDurInMaxTimes(){
        return paL4mwdcnTDurInMaxTimes;
    }

    /**
    * 设置近四月被叫次数最大的催收号的总时长
    * 
    * @param paL4mwdcnTDurInMaxTimes 要设置的近四月被叫次数最大的催收号的总时长
    */
    public void setPaL4mwdcnTDurInMaxTimes(Integer paL4mwdcnTDurInMaxTimes){
        this.paL4mwdcnTDurInMaxTimes = paL4mwdcnTDurInMaxTimes;
    }

    /**
    * 获取近四月被几家委外催收机构呼叫过
    *
    * @return 近四月被几家委外催收机构呼叫过
    */
    public Integer getPaL4mwdcnTNumsInF(){
        return paL4mwdcnTNumsInF;
    }

    /**
    * 设置近四月被几家委外催收机构呼叫过
    * 
    * @param paL4mwdcnTNumsInF 要设置的近四月被几家委外催收机构呼叫过
    */
    public void setPaL4mwdcnTNumsInF(Integer paL4mwdcnTNumsInF){
        this.paL4mwdcnTNumsInF = paL4mwdcnTNumsInF;
    }

    /**
    * 获取近五月联系催收号的总天数
    *
    * @return 近五月联系催收号的总天数
    */
    public Integer getPaL5mwdcnTDaysCon(){
        return paL5mwdcnTDaysCon;
    }

    /**
    * 设置近五月联系催收号的总天数
    * 
    * @param paL5mwdcnTDaysCon 要设置的近五月联系催收号的总天数
    */
    public void setPaL5mwdcnTDaysCon(Integer paL5mwdcnTDaysCon){
        this.paL5mwdcnTDaysCon = paL5mwdcnTDaysCon;
    }

    /**
    * 获取近五月被委外催收号码呼叫的总时长
    *
    * @return 近五月被委外催收号码呼叫的总时长
    */
    public Integer getPaL5mwdcnTDurInF(){
        return paL5mwdcnTDurInF;
    }

    /**
    * 设置近五月被委外催收号码呼叫的总时长
    * 
    * @param paL5mwdcnTDurInF 要设置的近五月被委外催收号码呼叫的总时长
    */
    public void setPaL5mwdcnTDurInF(Integer paL5mwdcnTDurInF){
        this.paL5mwdcnTDurInF = paL5mwdcnTDurInF;
    }

    /**
    * 获取近五月联系催收手机总时长
    *
    * @return 近五月联系催收手机总时长
    */
    public Integer getPaL5mwdcmTDurCon(){
        return paL5mwdcmTDurCon;
    }

    /**
    * 设置近五月联系催收手机总时长
    * 
    * @param paL5mwdcmTDurCon 要设置的近五月联系催收手机总时长
    */
    public void setPaL5mwdcmTDurCon(Integer paL5mwdcmTDurCon){
        this.paL5mwdcmTDurCon = paL5mwdcmTDurCon;
    }

    /**
    * 获取近五月与几家互联网金融机构催收号码有过联系
    *
    * @return 近五月与几家互联网金融机构催收号码有过联系
    */
    public Integer getPaL5mwdcnTNumsConIf(){
        return paL5mwdcnTNumsConIf;
    }

    /**
    * 设置近五月与几家互联网金融机构催收号码有过联系
    * 
    * @param paL5mwdcnTNumsConIf 要设置的近五月与几家互联网金融机构催收号码有过联系
    */
    public void setPaL5mwdcnTNumsConIf(Integer paL5mwdcnTNumsConIf){
        this.paL5mwdcnTNumsConIf = paL5mwdcnTNumsConIf;
    }

    /**
    * 获取详单周期内与几个催收号码有过联系
    *
    * @return 详单周期内与几个催收号码有过联系
    */
    public Integer getPaAllwdcnTNumsCon(){
        return paAllwdcnTNumsCon;
    }

    /**
    * 设置详单周期内与几个催收号码有过联系
    * 
    * @param paAllwdcnTNumsCon 要设置的详单周期内与几个催收号码有过联系
    */
    public void setPaAllwdcnTNumsCon(Integer paAllwdcnTNumsCon){
        this.paAllwdcnTNumsCon = paAllwdcnTNumsCon;
    }

    /**
    * 获取详单周期内与几家银行机构催收号码有过联系
    *
    * @return 详单周期内与几家银行机构催收号码有过联系
    */
    public Integer getPaAllwdcnTNumsConBank(){
        return paAllwdcnTNumsConBank;
    }

    /**
    * 设置详单周期内与几家银行机构催收号码有过联系
    * 
    * @param paAllwdcnTNumsConBank 要设置的详单周期内与几家银行机构催收号码有过联系
    */
    public void setPaAllwdcnTNumsConBank(Integer paAllwdcnTNumsConBank){
        this.paAllwdcnTNumsConBank = paAllwdcnTNumsConBank;
    }

    /**
    * 获取详单周期内与几家消费金融机构催收号码有过联系
    *
    * @return 详单周期内与几家消费金融机构催收号码有过联系
    */
    public Integer getPaAllwdcnTNumsConCf(){
        return paAllwdcnTNumsConCf;
    }

    /**
    * 设置详单周期内与几家消费金融机构催收号码有过联系
    * 
    * @param paAllwdcnTNumsConCf 要设置的详单周期内与几家消费金融机构催收号码有过联系
    */
    public void setPaAllwdcnTNumsConCf(Integer paAllwdcnTNumsConCf){
        this.paAllwdcnTNumsConCf = paAllwdcnTNumsConCf;
    }

    /**
    * 获取详单周期内与几家委外催收机构催收号码有过联系
    *
    * @return 详单周期内与几家委外催收机构催收号码有过联系
    */
    public Integer getPaAllwdcnTNumsConF(){
        return paAllwdcnTNumsConF;
    }

    /**
    * 设置详单周期内与几家委外催收机构催收号码有过联系
    * 
    * @param paAllwdcnTNumsConF 要设置的详单周期内与几家委外催收机构催收号码有过联系
    */
    public void setPaAllwdcnTNumsConF(Integer paAllwdcnTNumsConF){
        this.paAllwdcnTNumsConF = paAllwdcnTNumsConF;
    }

    /**
    * 获取详单周期内与几家互联网金融机构催收号码有过联系
    *
    * @return 详单周期内与几家互联网金融机构催收号码有过联系
    */
    public Integer getPaAllwdcnTNumsConIf(){
        return paAllwdcnTNumsConIf;
    }

    /**
    * 设置详单周期内与几家互联网金融机构催收号码有过联系
    * 
    * @param paAllwdcnTNumsConIf 要设置的详单周期内与几家互联网金融机构催收号码有过联系
    */
    public void setPaAllwdcnTNumsConIf(Integer paAllwdcnTNumsConIf){
        this.paAllwdcnTNumsConIf = paAllwdcnTNumsConIf;
    }

    /**
    * 获取详单周期内涉及催收号码的总机构数
    *
    * @return 详单周期内涉及催收号码的总机构数
    */
    public Integer getPaAllwdcnTNumsConOrg(){
        return paAllwdcnTNumsConOrg;
    }

    /**
    * 设置详单周期内涉及催收号码的总机构数
    * 
    * @param paAllwdcnTNumsConOrg 要设置的详单周期内涉及催收号码的总机构数
    */
    public void setPaAllwdcnTNumsConOrg(Integer paAllwdcnTNumsConOrg){
        this.paAllwdcnTNumsConOrg = paAllwdcnTNumsConOrg;
    }

    /**
    * 获取详单周期内被催收号码呼叫次数
    *
    * @return 详单周期内被催收号码呼叫次数
    */
    public Integer getPaAllwdcnTTimesIn(){
        return paAllwdcnTTimesIn;
    }

    /**
    * 设置详单周期内被催收号码呼叫次数
    * 
    * @param paAllwdcnTTimesIn 要设置的详单周期内被催收号码呼叫次数
    */
    public void setPaAllwdcnTTimesIn(Integer paAllwdcnTTimesIn){
        this.paAllwdcnTTimesIn = paAllwdcnTTimesIn;
    }

    /**
    * 获取详单周期内主叫催收号码次数
    *
    * @return 详单周期内主叫催收号码次数
    */
    public Integer getPaAllwdcnTTimesOut(){
        return paAllwdcnTTimesOut;
    }

    /**
    * 设置详单周期内主叫催收号码次数
    * 
    * @param paAllwdcnTTimesOut 要设置的详单周期内主叫催收号码次数
    */
    public void setPaAllwdcnTTimesOut(Integer paAllwdcnTTimesOut){
        this.paAllwdcnTTimesOut = paAllwdcnTTimesOut;
    }

    /**
    * 获取详单周期内被委外催收号码呼叫的总时长
    *
    * @return 详单周期内被委外催收号码呼叫的总时长
    */
    public Integer getPaAllwdcnTDurInF(){
        return paAllwdcnTDurInF;
    }

    /**
    * 设置详单周期内被委外催收号码呼叫的总时长
    * 
    * @param paAllwdcnTDurInF 要设置的详单周期内被委外催收号码呼叫的总时长
    */
    public void setPaAllwdcnTDurInF(Integer paAllwdcnTDurInF){
        this.paAllwdcnTDurInF = paAllwdcnTDurInF;
    }

    /**
    * 获取详单周期内被叫次数最大的催收手机号的机构类型
    *
    * @return 详单周期内被叫次数最大的催收手机号的机构类型
    */
    public Integer getPaAllwdcmMaxOrgTypeIn(){
        return paAllwdcmMaxOrgTypeIn;
    }

    /**
    * 设置详单周期内被叫次数最大的催收手机号的机构类型
    * 
    * @param paAllwdcmMaxOrgTypeIn 要设置的详单周期内被叫次数最大的催收手机号的机构类型
    */
    public void setPaAllwdcmMaxOrgTypeIn(Integer paAllwdcmMaxOrgTypeIn){
        this.paAllwdcmMaxOrgTypeIn = paAllwdcmMaxOrgTypeIn;
    }

    /**
    * 获取全部详单周期工作日(周一~周五)白天(8~18 点)_平 均时长
    *
    * @return 全部详单周期工作日(周一~周五)白天(8~18 点)_平 均时长
    */
    public Integer getPaAlldtcnwkAdur(){
        return paAlldtcnwkAdur;
    }

    /**
    * 设置全部详单周期工作日(周一~周五)白天(8~18 点)_平 均时长
    * 
    * @param paAlldtcnwkAdur 要设置的全部详单周期工作日(周一~周五)白天(8~18 点)_平 均时长
    */
    public void setPaAlldtcnwkAdur(Integer paAlldtcnwkAdur){
        this.paAlldtcnwkAdur = paAlldtcnwkAdur;
    }

    /**
    * 获取全部详单周期全天(0~23 点)_平均时长
    *
    * @return 全部详单周期全天(0~23 点)_平均时长
    */
    public Integer getPaAllwdcnAdur(){
        return paAllwdcnAdur;
    }

    /**
    * 设置全部详单周期全天(0~23 点)_平均时长
    * 
    * @param paAllwdcnAdur 要设置的全部详单周期全天(0~23 点)_平均时长
    */
    public void setPaAllwdcnAdur(Integer paAllwdcnAdur){
        this.paAllwdcnAdur = paAllwdcnAdur;
    }

    /**
    * 获取近一月白天(8~18 点)_平均时长
    *
    * @return 近一月白天(8~18 点)_平均时长
    */
    public Integer getPaL1mdtcnAdur(){
        return paL1mdtcnAdur;
    }

    /**
    * 设置近一月白天(8~18 点)_平均时长
    * 
    * @param paL1mdtcnAdur 要设置的近一月白天(8~18 点)_平均时长
    */
    public void setPaL1mdtcnAdur(Integer paL1mdtcnAdur){
        this.paL1mdtcnAdur = paL1mdtcnAdur;
    }

    /**
    * 获取近一月全天(0~23 点)_平均时长
    *
    * @return 近一月全天(0~23 点)_平均时长
    */
    public Integer getPaL1mwdcnAdur(){
        return paL1mwdcnAdur;
    }

    /**
    * 设置近一月全天(0~23 点)_平均时长
    * 
    * @param paL1mwdcnAdur 要设置的近一月全天(0~23 点)_平均时长
    */
    public void setPaL1mwdcnAdur(Integer paL1mwdcnAdur){
        this.paL1mwdcnAdur = paL1mwdcnAdur;
    }

    /**
    * 获取全部详单周期周末(周六周日)午夜(0~7 点)_详单中 的联系人总个数
    *
    * @return 全部详单周期周末(周六周日)午夜(0~7 点)_详单中 的联系人总个数
    */
    public Integer getPaAllmncnrtNumsCon(){
        return paAllmncnrtNumsCon;
    }

    /**
    * 设置全部详单周期周末(周六周日)午夜(0~7 点)_详单中 的联系人总个数
    * 
    * @param paAllmncnrtNumsCon 要设置的全部详单周期周末(周六周日)午夜(0~7 点)_详单中 的联系人总个数
    */
    public void setPaAllmncnrtNumsCon(Integer paAllmncnrtNumsCon){
        this.paAllmncnrtNumsCon = paAllmncnrtNumsCon;
    }

    /**
    * 获取近一周周末(周六周日)全天(0~23 点)_详单中的联系 人总个数
    *
    * @return 近一周周末(周六周日)全天(0~23 点)_详单中的联系 人总个数
    */
    public Integer getPaL1wwdcnrtNumsCon(){
        return paL1wwdcnrtNumsCon;
    }

    /**
    * 设置近一周周末(周六周日)全天(0~23 点)_详单中的联系 人总个数
    * 
    * @param paL1wwdcnrtNumsCon 要设置的近一周周末(周六周日)全天(0~23 点)_详单中的联系 人总个数
    */
    public void setPaL1wwdcnrtNumsCon(Integer paL1wwdcnrtNumsCon){
        this.paL1wwdcnrtNumsCon = paL1wwdcnrtNumsCon;
    }

    /**
    * 获取近两月全天(0~23 点)_详单中的联系人总个数
    *
    * @return 近两月全天(0~23 点)_详单中的联系人总个数
    */
    public Integer getPaL2mwdcnNumsCon(){
        return paL2mwdcnNumsCon;
    }

    /**
    * 设置近两月全天(0~23 点)_详单中的联系人总个数
    * 
    * @param paL2mwdcnNumsCon 要设置的近两月全天(0~23 点)_详单中的联系人总个数
    */
    public void setPaL2mwdcnNumsCon(Integer paL2mwdcnNumsCon){
        this.paL2mwdcnNumsCon = paL2mwdcnNumsCon;
    }

    /**
    * 获取近两周工作日(周一~周五)全天(0~23 点)_详单中的 联系人总个数
    *
    * @return 近两周工作日(周一~周五)全天(0~23 点)_详单中的 联系人总个数
    */
    public Integer getPaL2wwdcnwkNumsCon(){
        return paL2wwdcnwkNumsCon;
    }

    /**
    * 设置近两周工作日(周一~周五)全天(0~23 点)_详单中的 联系人总个数
    * 
    * @param paL2wwdcnwkNumsCon 要设置的近两周工作日(周一~周五)全天(0~23 点)_详单中的 联系人总个数
    */
    public void setPaL2wwdcnwkNumsCon(Integer paL2wwdcnwkNumsCon){
        this.paL2wwdcnwkNumsCon = paL2wwdcnwkNumsCon;
    }

    /**
    * 获取全部详单周期全天(0~23 点)_通话一次催收号码的 平均时长
    *
    * @return 全部详单周期全天(0~23 点)_通话一次催收号码的 平均时长
    */
    public Integer getPaAllwdcnADurOneNums(){
        return paAllwdcnADurOneNums;
    }

    /**
    * 设置全部详单周期全天(0~23 点)_通话一次催收号码的 平均时长
    * 
    * @param paAllwdcnADurOneNums 要设置的全部详单周期全天(0~23 点)_通话一次催收号码的 平均时长
    */
    public void setPaAllwdcnADurOneNums(Integer paAllwdcnADurOneNums){
        this.paAllwdcnADurOneNums = paAllwdcnADurOneNums;
    }

    /**
    * 获取近四月全天(0~23 点)_通话一次催收号码的平均时 长
    *
    * @return 近四月全天(0~23 点)_通话一次催收号码的平均时 长
    */
    public Integer getPaL4mwdcnADurOneNums(){
        return paL4mwdcnADurOneNums;
    }

    /**
    * 设置近四月全天(0~23 点)_通话一次催收号码的平均时 长
    * 
    * @param paL4mwdcnADurOneNums 要设置的近四月全天(0~23 点)_通话一次催收号码的平均时 长
    */
    public void setPaL4mwdcnADurOneNums(Integer paL4mwdcnADurOneNums){
        this.paL4mwdcnADurOneNums = paL4mwdcnADurOneNums;
    }

    /**
    * 获取近六月全天(0~23 点)_通话一次催收号码的平均时 长
    *
    * @return 近六月全天(0~23 点)_通话一次催收号码的平均时 长
    */
    public Integer getPaL6mwdcnADurOneNums(){
        return paL6mwdcnADurOneNums;
    }

    /**
    * 设置近六月全天(0~23 点)_通话一次催收号码的平均时 长
    * 
    * @param paL6mwdcnADurOneNums 要设置的近六月全天(0~23 点)_通话一次催收号码的平均时 长
    */
    public void setPaL6mwdcnADurOneNums(Integer paL6mwdcnADurOneNums){
        this.paL6mwdcnADurOneNums = paL6mwdcnADurOneNums;
    }

    /**
    * 获取近一月工作日(周一~周五)白天(8~18 点)_最高排名 比例
    *
    * @return 近一月工作日(周一~周五)白天(8~18 点)_最高排名 比例
    */
    public Integer getPaL1mdtcnwkRankCount(){
        return paL1mdtcnwkRankCount;
    }

    /**
    * 设置近一月工作日(周一~周五)白天(8~18 点)_最高排名 比例
    * 
    * @param paL1mdtcnwkRankCount 要设置的近一月工作日(周一~周五)白天(8~18 点)_最高排名 比例
    */
    public void setPaL1mdtcnwkRankCount(Integer paL1mdtcnwkRankCount){
        this.paL1mdtcnwkRankCount = paL1mdtcnwkRankCount;
    }

    /**
    * 获取近一周周末(周六周日)白天(8~18 点)_最高排名比例
    *
    * @return 近一周周末(周六周日)白天(8~18 点)_最高排名比例
    */
    public Integer getPaL1wdtcnrtRankCount(){
        return paL1wdtcnrtRankCount;
    }

    /**
    * 设置近一周周末(周六周日)白天(8~18 点)_最高排名比例
    * 
    * @param paL1wdtcnrtRankCount 要设置的近一周周末(周六周日)白天(8~18 点)_最高排名比例
    */
    public void setPaL1wdtcnrtRankCount(Integer paL1wdtcnrtRankCount){
        this.paL1wdtcnrtRankCount = paL1wdtcnrtRankCount;
    }

    /**
    * 获取近两月白天(8~18 点)_最高排名比例
    *
    * @return 近两月白天(8~18 点)_最高排名比例
    */
    public Integer getPaL2mdtcnRankCount(){
        return paL2mdtcnRankCount;
    }

    /**
    * 设置近两月白天(8~18 点)_最高排名比例
    * 
    * @param paL2mdtcnRankCount 要设置的近两月白天(8~18 点)_最高排名比例
    */
    public void setPaL2mdtcnRankCount(Integer paL2mdtcnRankCount){
        this.paL2mdtcnRankCount = paL2mdtcnRankCount;
    }

    /**
    * 获取近三周工作日(周一~周五)全天(0~23 点)_最高排名 比例
    *
    * @return 近三周工作日(周一~周五)全天(0~23 点)_最高排名 比例
    */
    public Integer getPaL3wwdcnwkRankCount(){
        return paL3wwdcnwkRankCount;
    }

    /**
    * 设置近三周工作日(周一~周五)全天(0~23 点)_最高排名 比例
    * 
    * @param paL3wwdcnwkRankCount 要设置的近三周工作日(周一~周五)全天(0~23 点)_最高排名 比例
    */
    public void setPaL3wwdcnwkRankCount(Integer paL3wwdcnwkRankCount){
        this.paL3wwdcnwkRankCount = paL3wwdcnwkRankCount;
    }

    /**
    * 获取近一月周末(周六周日)夜晚(19~23 点)_将一小时打 6 个及以上电话称为特殊小时，统计这种小时的数量
    *
    * @return 近一月周末(周六周日)夜晚(19~23 点)_将一小时打 6 个及以上电话称为特殊小时，统计这种小时的数量
    */
    public Integer getPaL1mevcnrtSepcialHourCount(){
        return paL1mevcnrtSepcialHourCount;
    }

    /**
    * 设置近一月周末(周六周日)夜晚(19~23 点)_将一小时打 6 个及以上电话称为特殊小时，统计这种小时的数量
    * 
    * @param paL1mevcnrtSepcialHourCount 要设置的近一月周末(周六周日)夜晚(19~23 点)_将一小时打 6 个及以上电话称为特殊小时，统计这种小时的数量
    */
    public void setPaL1mevcnrtSepcialHourCount(Integer paL1mevcnrtSepcialHourCount){
        this.paL1mevcnrtSepcialHourCount = paL1mevcnrtSepcialHourCount;
    }

    /**
    * 获取近一月工作日(周一~周五)午夜(0~7 点)_将一小时 打 6 个及以上电话称为特殊小时，统计这种小时的数 量
    *
    * @return 近一月工作日(周一~周五)午夜(0~7 点)_将一小时 打 6 个及以上电话称为特殊小时，统计这种小时的数 量
    */
    public Integer getPaL1mmncnwkSepcialHourCount(){
        return paL1mmncnwkSepcialHourCount;
    }

    /**
    * 设置近一月工作日(周一~周五)午夜(0~7 点)_将一小时 打 6 个及以上电话称为特殊小时，统计这种小时的数 量
    * 
    * @param paL1mmncnwkSepcialHourCount 要设置的近一月工作日(周一~周五)午夜(0~7 点)_将一小时 打 6 个及以上电话称为特殊小时，统计这种小时的数 量
    */
    public void setPaL1mmncnwkSepcialHourCount(Integer paL1mmncnwkSepcialHourCount){
        this.paL1mmncnwkSepcialHourCount = paL1mmncnwkSepcialHourCount;
    }

    /**
    * 获取近一周夜晚(19~23 点)_将一小时打 6 个及以上电话 称为特殊小时，统计这种小时的数量
    *
    * @return 近一周夜晚(19~23 点)_将一小时打 6 个及以上电话 称为特殊小时，统计这种小时的数量
    */
    public Integer getPaL1wevcnSepcialHourCount(){
        return paL1wevcnSepcialHourCount;
    }

    /**
    * 设置近一周夜晚(19~23 点)_将一小时打 6 个及以上电话 称为特殊小时，统计这种小时的数量
    * 
    * @param paL1wevcnSepcialHourCount 要设置的近一周夜晚(19~23 点)_将一小时打 6 个及以上电话 称为特殊小时，统计这种小时的数量
    */
    public void setPaL1wevcnSepcialHourCount(Integer paL1wevcnSepcialHourCount){
        this.paL1wevcnSepcialHourCount = paL1wevcnSepcialHourCount;
    }

    /**
    * 获取近一周周末(周六周日)全天(0~23 点)_将一小时打 6 个及以上电话称为特殊小时，统计这种小时的数量
    *
    * @return 近一周周末(周六周日)全天(0~23 点)_将一小时打 6 个及以上电话称为特殊小时，统计这种小时的数量
    */
    public Integer getPaL1wwdcnrtSepcialHourCount(){
        return paL1wwdcnrtSepcialHourCount;
    }

    /**
    * 设置近一周周末(周六周日)全天(0~23 点)_将一小时打 6 个及以上电话称为特殊小时，统计这种小时的数量
    * 
    * @param paL1wwdcnrtSepcialHourCount 要设置的近一周周末(周六周日)全天(0~23 点)_将一小时打 6 个及以上电话称为特殊小时，统计这种小时的数量
    */
    public void setPaL1wwdcnrtSepcialHourCount(Integer paL1wwdcnrtSepcialHourCount){
        this.paL1wwdcnrtSepcialHourCount = paL1wwdcnrtSepcialHourCount;
    }

    /**
    * 获取近两月夜晚(19~23 点)_将一小时打 6 个及以上电话 称为特殊小时，统计这种小时的数量
    *
    * @return 近两月夜晚(19~23 点)_将一小时打 6 个及以上电话 称为特殊小时，统计这种小时的数量
    */
    public Integer getPaL2mevcnSepcialHourCount(){
        return paL2mevcnSepcialHourCount;
    }

    /**
    * 设置近两月夜晚(19~23 点)_将一小时打 6 个及以上电话 称为特殊小时，统计这种小时的数量
    * 
    * @param paL2mevcnSepcialHourCount 要设置的近两月夜晚(19~23 点)_将一小时打 6 个及以上电话 称为特殊小时，统计这种小时的数量
    */
    public void setPaL2mevcnSepcialHourCount(Integer paL2mevcnSepcialHourCount){
        this.paL2mevcnSepcialHourCount = paL2mevcnSepcialHourCount;
    }

    /**
    * 获取近两月周末(周六周日)夜晚(19~23 点)_将一小时打 6 个及以上电话称为特殊小时，统计这种小时的数量
    *
    * @return 近两月周末(周六周日)夜晚(19~23 点)_将一小时打 6 个及以上电话称为特殊小时，统计这种小时的数量
    */
    public Integer getPaL2mevcnrtSepcialHourCount(){
        return paL2mevcnrtSepcialHourCount;
    }

    /**
    * 设置近两月周末(周六周日)夜晚(19~23 点)_将一小时打 6 个及以上电话称为特殊小时，统计这种小时的数量
    * 
    * @param paL2mevcnrtSepcialHourCount 要设置的近两月周末(周六周日)夜晚(19~23 点)_将一小时打 6 个及以上电话称为特殊小时，统计这种小时的数量
    */
    public void setPaL2mevcnrtSepcialHourCount(Integer paL2mevcnrtSepcialHourCount){
        this.paL2mevcnrtSepcialHourCount = paL2mevcnrtSepcialHourCount;
    }

    /**
    * 获取近两月全天(0~23 点)_将一小时打 6 个及以上电话称 为特殊小时，统计这种小时的数量
    *
    * @return 近两月全天(0~23 点)_将一小时打 6 个及以上电话称 为特殊小时，统计这种小时的数量
    */
    public Integer getPaL2mwdcnSepcialHourCount(){
        return paL2mwdcnSepcialHourCount;
    }

    /**
    * 设置近两月全天(0~23 点)_将一小时打 6 个及以上电话称 为特殊小时，统计这种小时的数量
    * 
    * @param paL2mwdcnSepcialHourCount 要设置的近两月全天(0~23 点)_将一小时打 6 个及以上电话称 为特殊小时，统计这种小时的数量
    */
    public void setPaL2mwdcnSepcialHourCount(Integer paL2mwdcnSepcialHourCount){
        this.paL2mwdcnSepcialHourCount = paL2mwdcnSepcialHourCount;
    }

    /**
    * 获取近三月夜晚(19~23 点)_将一小时打 6 个及以上电话 称为特殊小时，统计这种小时的数量
    *
    * @return 近三月夜晚(19~23 点)_将一小时打 6 个及以上电话 称为特殊小时，统计这种小时的数量
    */
    public Integer getPaL3mevcnSepcialHourCount(){
        return paL3mevcnSepcialHourCount;
    }

    /**
    * 设置近三月夜晚(19~23 点)_将一小时打 6 个及以上电话 称为特殊小时，统计这种小时的数量
    * 
    * @param paL3mevcnSepcialHourCount 要设置的近三月夜晚(19~23 点)_将一小时打 6 个及以上电话 称为特殊小时，统计这种小时的数量
    */
    public void setPaL3mevcnSepcialHourCount(Integer paL3mevcnSepcialHourCount){
        this.paL3mevcnSepcialHourCount = paL3mevcnSepcialHourCount;
    }

    /**
    * 获取近三月周末(周六周日)全天(0~23 点)_将一小时打 6 个及以上电话称为特殊小时，统计这种小时的数量
    *
    * @return 近三月周末(周六周日)全天(0~23 点)_将一小时打 6 个及以上电话称为特殊小时，统计这种小时的数量
    */
    public Integer getPaL3mwdcnrtSepcialHourCount(){
        return paL3mwdcnrtSepcialHourCount;
    }

    /**
    * 设置近三月周末(周六周日)全天(0~23 点)_将一小时打 6 个及以上电话称为特殊小时，统计这种小时的数量
    * 
    * @param paL3mwdcnrtSepcialHourCount 要设置的近三月周末(周六周日)全天(0~23 点)_将一小时打 6 个及以上电话称为特殊小时，统计这种小时的数量
    */
    public void setPaL3mwdcnrtSepcialHourCount(Integer paL3mwdcnrtSepcialHourCount){
        this.paL3mwdcnrtSepcialHourCount = paL3mwdcnrtSepcialHourCount;
    }

    /**
    * 获取近六月工作日(周一~周五)全天(0~23 点)_将一小时 打 6 个及以上电话称为特殊小时，统计这种小时的数 量
    *
    * @return 近六月工作日(周一~周五)全天(0~23 点)_将一小时 打 6 个及以上电话称为特殊小时，统计这种小时的数 量
    */
    public Integer getPaL6mwdcnwkSepcialHourCount(){
        return paL6mwdcnwkSepcialHourCount;
    }

    /**
    * 设置近六月工作日(周一~周五)全天(0~23 点)_将一小时 打 6 个及以上电话称为特殊小时，统计这种小时的数 量
    * 
    * @param paL6mwdcnwkSepcialHourCount 要设置的近六月工作日(周一~周五)全天(0~23 点)_将一小时 打 6 个及以上电话称为特殊小时，统计这种小时的数 量
    */
    public void setPaL6mwdcnwkSepcialHourCount(Integer paL6mwdcnwkSepcialHourCount){
        this.paL6mwdcnwkSepcialHourCount = paL6mwdcnwkSepcialHourCount;
    }

    /**
    * 获取近两月白天(8~18 点)_活跃的天数
    *
    * @return 近两月白天(8~18 点)_活跃的天数
    */
    public Integer getPaL2mdtcnActiveDaysCount(){
        return paL2mdtcnActiveDaysCount;
    }

    /**
    * 设置近两月白天(8~18 点)_活跃的天数
    * 
    * @param paL2mdtcnActiveDaysCount 要设置的近两月白天(8~18 点)_活跃的天数
    */
    public void setPaL2mdtcnActiveDaysCount(Integer paL2mdtcnActiveDaysCount){
        this.paL2mdtcnActiveDaysCount = paL2mdtcnActiveDaysCount;
    }

    /**
    * 获取近两周全天(0~23 点)_活跃的天数
    *
    * @return 近两周全天(0~23 点)_活跃的天数
    */
    public Integer getPaL2wwdcnActiveDaysCount(){
        return paL2wwdcnActiveDaysCount;
    }

    /**
    * 设置近两周全天(0~23 点)_活跃的天数
    * 
    * @param paL2wwdcnActiveDaysCount 要设置的近两周全天(0~23 点)_活跃的天数
    */
    public void setPaL2wwdcnActiveDaysCount(Integer paL2wwdcnActiveDaysCount){
        this.paL2wwdcnActiveDaysCount = paL2wwdcnActiveDaysCount;
    }

    /**
    * 获取近三月周末(周六周日)全天(0~23 点)_活跃的天数
    *
    * @return 近三月周末(周六周日)全天(0~23 点)_活跃的天数
    */
    public Integer getPaL3mwdcnrtActiveDaysCount(){
        return paL3mwdcnrtActiveDaysCount;
    }

    /**
    * 设置近三月周末(周六周日)全天(0~23 点)_活跃的天数
    * 
    * @param paL3mwdcnrtActiveDaysCount 要设置的近三月周末(周六周日)全天(0~23 点)_活跃的天数
    */
    public void setPaL3mwdcnrtActiveDaysCount(Integer paL3mwdcnrtActiveDaysCount){
        this.paL3mwdcnrtActiveDaysCount = paL3mwdcnrtActiveDaysCount;
    }

    /**
    * 获取近两周白天(8~18 点)_被单个催收号码呼叫的最大 次数
    *
    * @return 近两周白天(8~18 点)_被单个催收号码呼叫的最大 次数
    */
    public Integer getPaL2wdtcnMaxTimesIn(){
        return paL2wdtcnMaxTimesIn;
    }

    /**
    * 设置近两周白天(8~18 点)_被单个催收号码呼叫的最大 次数
    * 
    * @param paL2wdtcnMaxTimesIn 要设置的近两周白天(8~18 点)_被单个催收号码呼叫的最大 次数
    */
    public void setPaL2wdtcnMaxTimesIn(Integer paL2wdtcnMaxTimesIn){
        this.paL2wdtcnMaxTimesIn = paL2wdtcnMaxTimesIn;
    }

    /**
    * 获取近两周夜晚(19~23 点)_申请人联系一个号码的天数 的最大值
    *
    * @return 近两周夜晚(19~23 点)_申请人联系一个号码的天数 的最大值
    */
    public Integer getPaL2wevcnMaxDaysOfOneNumberCount(){
        return paL2wevcnMaxDaysOfOneNumberCount;
    }

    /**
    * 设置近两周夜晚(19~23 点)_申请人联系一个号码的天数 的最大值
    * 
    * @param paL2wevcnMaxDaysOfOneNumberCount 要设置的近两周夜晚(19~23 点)_申请人联系一个号码的天数 的最大值
    */
    public void setPaL2wevcnMaxDaysOfOneNumberCount(Integer paL2wevcnMaxDaysOfOneNumberCount){
        this.paL2wevcnMaxDaysOfOneNumberCount = paL2wevcnMaxDaysOfOneNumberCount;
    }

    /**
    * 获取近三月周末(周六周日)全天(0~23 点)_申请人联系一 个号码的天数的最大值
    *
    * @return 近三月周末(周六周日)全天(0~23 点)_申请人联系一 个号码的天数的最大值
    */
    public Integer getPaL3mwdcnrtMaxDaysOfOneNumberCount(){
        return paL3mwdcnrtMaxDaysOfOneNumberCount;
    }

    /**
    * 设置近三月周末(周六周日)全天(0~23 点)_申请人联系一 个号码的天数的最大值
    * 
    * @param paL3mwdcnrtMaxDaysOfOneNumberCount 要设置的近三月周末(周六周日)全天(0~23 点)_申请人联系一 个号码的天数的最大值
    */
    public void setPaL3mwdcnrtMaxDaysOfOneNumberCount(Integer paL3mwdcnrtMaxDaysOfOneNumberCount){
        this.paL3mwdcnrtMaxDaysOfOneNumberCount = paL3mwdcnrtMaxDaysOfOneNumberCount;
    }

    /**
    * 获取近三周全天(0~23 点)_申请人联系一个号码的天数 的最大值
    *
    * @return 近三周全天(0~23 点)_申请人联系一个号码的天数 的最大值
    */
    public Integer getPaL3wwdcnMaxDaysOfOneNumberCount(){
        return paL3wwdcnMaxDaysOfOneNumberCount;
    }

    /**
    * 设置近三周全天(0~23 点)_申请人联系一个号码的天数 的最大值
    * 
    * @param paL3wwdcnMaxDaysOfOneNumberCount 要设置的近三周全天(0~23 点)_申请人联系一个号码的天数 的最大值
    */
    public void setPaL3wwdcnMaxDaysOfOneNumberCount(Integer paL3wwdcnMaxDaysOfOneNumberCount){
        this.paL3wwdcnMaxDaysOfOneNumberCount = paL3wwdcnMaxDaysOfOneNumberCount;
    }

    /**
    * 获取近两周全天(0~23 点)_被催收号码呼叫一次催收号 码的平均时长
    *
    * @return 近两周全天(0~23 点)_被催收号码呼叫一次催收号 码的平均时长
    */
    public Integer getPaL2wwdcnADurOneNumsIn(){
        return paL2wwdcnADurOneNumsIn;
    }

    /**
    * 设置近两周全天(0~23 点)_被催收号码呼叫一次催收号 码的平均时长
    * 
    * @param paL2wwdcnADurOneNumsIn 要设置的近两周全天(0~23 点)_被催收号码呼叫一次催收号 码的平均时长
    */
    public void setPaL2wwdcnADurOneNumsIn(Integer paL2wwdcnADurOneNumsIn){
        this.paL2wwdcnADurOneNumsIn = paL2wwdcnADurOneNumsIn;
    }

    /**
    * 获取近两周全天(0~23 点)_被催收号码呼叫次数
    *
    * @return 近两周全天(0~23 点)_被催收号码呼叫次数
    */
    public Integer getPaL2wwdcnTTimesIn(){
        return paL2wwdcnTTimesIn;
    }

    /**
    * 设置近两周全天(0~23 点)_被催收号码呼叫次数
    * 
    * @param paL2wwdcnTTimesIn 要设置的近两周全天(0~23 点)_被催收号码呼叫次数
    */
    public void setPaL2wwdcnTTimesIn(Integer paL2wwdcnTTimesIn){
        this.paL2wwdcnTTimesIn = paL2wwdcnTTimesIn;
    }

    /**
    * 获取近三月全天(0~23 点)_被催收号码呼叫的平均时长
    *
    * @return 近三月全天(0~23 点)_被催收号码呼叫的平均时长
    */
    public Integer getPaL3mwdcnADurIn(){
        return paL3mwdcnADurIn;
    }

    /**
    * 设置近三月全天(0~23 点)_被催收号码呼叫的平均时长
    * 
    * @param paL3mwdcnADurIn 要设置的近三月全天(0~23 点)_被催收号码呼叫的平均时长
    */
    public void setPaL3mwdcnADurIn(Integer paL3mwdcnADurIn){
        this.paL3mwdcnADurIn = paL3mwdcnADurIn;
    }

    /**
    * 获取近一月周末(周六周日)夜晚(19~23 点)_与催收号码 通话总时长
    *
    * @return 近一月周末(周六周日)夜晚(19~23 点)_与催收号码 通话总时长
    */
    public Integer getPaL1mevcnrtTDur(){
        return paL1mevcnrtTDur;
    }

    /**
    * 设置近一月周末(周六周日)夜晚(19~23 点)_与催收号码 通话总时长
    * 
    * @param paL1mevcnrtTDur 要设置的近一月周末(周六周日)夜晚(19~23 点)_与催收号码 通话总时长
    */
    public void setPaL1mevcnrtTDur(Integer paL1mevcnrtTDur){
        this.paL1mevcnrtTDur = paL1mevcnrtTDur;
    }

    /**
    * 获取近一月工作日(周一~周五)午夜(0~7 点)_与催收号 码通话总时长
    *
    * @return 近一月工作日(周一~周五)午夜(0~7 点)_与催收号 码通话总时长
    */
    public Integer getPaL1mmncnwkTDur(){
        return paL1mmncnwkTDur;
    }

    /**
    * 设置近一月工作日(周一~周五)午夜(0~7 点)_与催收号 码通话总时长
    * 
    * @param paL1mmncnwkTDur 要设置的近一月工作日(周一~周五)午夜(0~7 点)_与催收号 码通话总时长
    */
    public void setPaL1mmncnwkTDur(Integer paL1mmncnwkTDur){
        this.paL1mmncnwkTDur = paL1mmncnwkTDur;
    }

    /**
    * 获取近两月夜晚(19~23 点)_与催收号码通话总时长
    *
    * @return 近两月夜晚(19~23 点)_与催收号码通话总时长
    */
    public Integer getPaL2mevcnTDur(){
        return paL2mevcnTDur;
    }

    /**
    * 设置近两月夜晚(19~23 点)_与催收号码通话总时长
    * 
    * @param paL2mevcnTDur 要设置的近两月夜晚(19~23 点)_与催收号码通话总时长
    */
    public void setPaL2mevcnTDur(Integer paL2mevcnTDur){
        this.paL2mevcnTDur = paL2mevcnTDur;
    }

    /**
    * 获取近两月周末(周六周日)全天(0~23 点)_与催收号码通 话总时长
    *
    * @return 近两月周末(周六周日)全天(0~23 点)_与催收号码通 话总时长
    */
    public Integer getPaL2mwdcnrtTDur(){
        return paL2mwdcnrtTDur;
    }

    /**
    * 设置近两月周末(周六周日)全天(0~23 点)_与催收号码通 话总时长
    * 
    * @param paL2mwdcnrtTDur 要设置的近两月周末(周六周日)全天(0~23 点)_与催收号码通 话总时长
    */
    public void setPaL2mwdcnrtTDur(Integer paL2mwdcnrtTDur){
        this.paL2mwdcnrtTDur = paL2mwdcnrtTDur;
    }

    /**
    * 获取近三月夜晚(19~23 点)_与催收号码通话总时长
    *
    * @return 近三月夜晚(19~23 点)_与催收号码通话总时长
    */
    public Integer getPaL3mevcnTDur(){
        return paL3mevcnTDur;
    }

    /**
    * 设置近三月夜晚(19~23 点)_与催收号码通话总时长
    * 
    * @param paL3mevcnTDur 要设置的近三月夜晚(19~23 点)_与催收号码通话总时长
    */
    public void setPaL3mevcnTDur(Integer paL3mevcnTDur){
        this.paL3mevcnTDur = paL3mevcnTDur;
    }

    /**
    * 获取近三月周末(周六周日)全天(0~23 点)_与催收号码通 话总时长
    *
    * @return 近三月周末(周六周日)全天(0~23 点)_与催收号码通 话总时长
    */
    public Integer getPaL3mwdcnrtTDur(){
        return paL3mwdcnrtTDur;
    }

    /**
    * 设置近三月周末(周六周日)全天(0~23 点)_与催收号码通 话总时长
    * 
    * @param paL3mwdcnrtTDur 要设置的近三月周末(周六周日)全天(0~23 点)_与催收号码通 话总时长
    */
    public void setPaL3mwdcnrtTDur(Integer paL3mwdcnrtTDur){
        this.paL3mwdcnrtTDur = paL3mwdcnrtTDur;
    }

    /**
    * 获取近六月工作日(周一~周五)全天(0~23 点)_与催收号 码通话总时长
    *
    * @return 近六月工作日(周一~周五)全天(0~23 点)_与催收号 码通话总时长
    */
    public Integer getPaL6mwdcnwkTDur(){
        return paL6mwdcnwkTDur;
    }

    /**
    * 设置近六月工作日(周一~周五)全天(0~23 点)_与催收号 码通话总时长
    * 
    * @param paL6mwdcnwkTDur 要设置的近六月工作日(周一~周五)全天(0~23 点)_与催收号 码通话总时长
    */
    public void setPaL6mwdcnwkTDur(Integer paL6mwdcnwkTDur){
        this.paL6mwdcnwkTDur = paL6mwdcnwkTDur;
    }

    /**
    * 获取近四月全天(0~23 点)_与催收号码通话总时长
    *
    * @return 近四月全天(0~23 点)_与催收号码通话总时长
    */
    public Integer getPaL4mwdcnTDur(){
        return paL4mwdcnTDur;
    }

    /**
    * 设置近四月全天(0~23 点)_与催收号码通话总时长
    * 
    * @param paL4mwdcnTDur 要设置的近四月全天(0~23 点)_与催收号码通话总时长
    */
    public void setPaL4mwdcnTDur(Integer paL4mwdcnTDur){
        this.paL4mwdcnTDur = paL4mwdcnTDur;
    }

    /**
    * 获取近六月全天(0~23 点)_被催收号呼叫最小间隔天数
    *
    * @return 近六月全天(0~23 点)_被催收号呼叫最小间隔天数
    */
    public Integer getPaL6mwdcnMinTtvDaysIn(){
        return paL6mwdcnMinTtvDaysIn;
    }

    /**
    * 设置近六月全天(0~23 点)_被催收号呼叫最小间隔天数
    * 
    * @param paL6mwdcnMinTtvDaysIn 要设置的近六月全天(0~23 点)_被催收号呼叫最小间隔天数
    */
    public void setPaL6mwdcnMinTtvDaysIn(Integer paL6mwdcnMinTtvDaysIn){
        this.paL6mwdcnMinTtvDaysIn = paL6mwdcnMinTtvDaysIn;
    }

    /**
    * 获取近六月全天(0~23 点)_与委外机构催收号呼叫的总 时长
    *
    * @return 近六月全天(0~23 点)_与委外机构催收号呼叫的总 时长
    */
    public Integer getPaL6mwdcnTDurF(){
        return paL6mwdcnTDurF;
    }

    /**
    * 设置近六月全天(0~23 点)_与委外机构催收号呼叫的总 时长
    * 
    * @param paL6mwdcnTDurF 要设置的近六月全天(0~23 点)_与委外机构催收号呼叫的总 时长
    */
    public void setPaL6mwdcnTDurF(Integer paL6mwdcnTDurF){
        this.paL6mwdcnTDurF = paL6mwdcnTDurF;
    }

    /**
    * 获取周五的凌晨 2 点至 5 点通话时长
    *
    * @return 周五的凌晨 2 点至 5 点通话时长
    */
    public Integer getPaAll25acfriDur(){
        return paAll25acfriDur;
    }

    /**
    * 设置周五的凌晨 2 点至 5 点通话时长
    * 
    * @param paAll25acfriDur 要设置的周五的凌晨 2 点至 5 点通话时长
    */
    public void setPaAll25acfriDur(Integer paAll25acfriDur){
        this.paAll25acfriDur = paAll25acfriDur;
    }

    /**
    * 获取周一至周五的通话时长
    *
    * @return 周一至周五的通话时长
    */
    public Integer getPaAllwdacwkDur(){
        return paAllwdacwkDur;
    }

    /**
    * 设置周一至周五的通话时长
    * 
    * @param paAllwdacwkDur 要设置的周一至周五的通话时长
    */
    public void setPaAllwdacwkDur(Integer paAllwdacwkDur){
        this.paAllwdacwkDur = paAllwdacwkDur;
    }

    /**
    * 获取凌晨 2 点凌晨 5 点之间的通话次数
    *
    * @return 凌晨 2 点凌晨 5 点之间的通话次数
    */
    public Integer getPaAll25acTimes(){
        return paAll25acTimes;
    }

    /**
    * 设置凌晨 2 点凌晨 5 点之间的通话次数
    * 
    * @param paAll25acTimes 要设置的凌晨 2 点凌晨 5 点之间的通话次数
    */
    public void setPaAll25acTimes(Integer paAll25acTimes){
        this.paAll25acTimes = paAll25acTimes;
    }

    /**
    * 获取创建时间
    *
    * @return 创建时间
    */
    public Date getCreateTime(){
        return createTime;
    }

    /**
    * 设置创建时间
    * 
    * @param createTime 要设置的创建时间
    */
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    /**
    * 获取更新时间
    *
    * @return 更新时间
    */
    public Date getLastModifyTime(){
        return lastModifyTime;
    }

    /**
    * 设置更新时间
    * 
    * @param lastModifyTime 要设置的更新时间
    */
    public void setLastModifyTime(Date lastModifyTime){
        this.lastModifyTime = lastModifyTime;
    }

   public Integer getYdActualLoanPlatformCount1m() {
      return ydActualLoanPlatformCount1m;
   }

   public void setYdActualLoanPlatformCount1m(Integer ydActualLoanPlatformCount1m) {
      this.ydActualLoanPlatformCount1m = ydActualLoanPlatformCount1m;
   }

   public Integer getYdRepaymentTimesCount() {
      return ydRepaymentTimesCount;
   }

   public void setYdRepaymentTimesCount(Integer ydRepaymentTimesCount) {
      this.ydRepaymentTimesCount = ydRepaymentTimesCount;
   }

   public Integer getYdActualLoanPlatformCount6m() {
      return ydActualLoanPlatformCount6m;
   }

   public void setYdActualLoanPlatformCount6m(Integer ydActualLoanPlatformCount6m) {
      this.ydActualLoanPlatformCount6m = ydActualLoanPlatformCount6m;
   }

   public Integer getYdActualLoanPlatformCount3m() {
      return ydActualLoanPlatformCount3m;
   }

   public void setYdActualLoanPlatformCount3m(Integer ydActualLoanPlatformCount3m) {
      this.ydActualLoanPlatformCount3m = ydActualLoanPlatformCount3m;
   }

   public Integer getYdActualLoanPlatformCount() {
      return ydActualLoanPlatformCount;
   }

   public void setYdActualLoanPlatformCount(Integer ydActualLoanPlatformCount) {
      this.ydActualLoanPlatformCount = ydActualLoanPlatformCount;
   }

   public Integer getYdLoanPlatformCount3m() {
      return ydLoanPlatformCount3m;
   }

   public void setYdLoanPlatformCount3m(Integer ydLoanPlatformCount3m) {
      this.ydLoanPlatformCount3m = ydLoanPlatformCount3m;
   }

   public Integer getYdLoanPlatformCount1m() {
      return ydLoanPlatformCount1m;
   }

   public void setYdLoanPlatformCount1m(Integer ydLoanPlatformCount1m) {
      this.ydLoanPlatformCount1m = ydLoanPlatformCount1m;
   }

   public Integer getYdLoanPlatformCount6m() {
      return ydLoanPlatformCount6m;
   }

   public void setYdLoanPlatformCount6m(Integer ydLoanPlatformCount6m) {
      this.ydLoanPlatformCount6m = ydLoanPlatformCount6m;
   }

   public Integer getYdRepaymentPlatformCount6m() {
      return ydRepaymentPlatformCount6m;
   }

   public void setYdRepaymentPlatformCount6m(Integer ydRepaymentPlatformCount6m) {
      this.ydRepaymentPlatformCount6m = ydRepaymentPlatformCount6m;
   }

   public Integer getYdRepaymentPlatformCount1m() {
      return ydRepaymentPlatformCount1m;
   }

   public void setYdRepaymentPlatformCount1m(Integer ydRepaymentPlatformCount1m) {
      this.ydRepaymentPlatformCount1m = ydRepaymentPlatformCount1m;
   }

   public Integer getYdRepaymentPlatformCount3m() {
      return ydRepaymentPlatformCount3m;
   }

   public void setYdRepaymentPlatformCount3m(Integer ydRepaymentPlatformCount3m) {
      this.ydRepaymentPlatformCount3m = ydRepaymentPlatformCount3m;
   }

   public Integer getYdRepaymentPlatformCount() {
      return ydRepaymentPlatformCount;
   }

   public void setYdRepaymentPlatformCount(Integer ydRepaymentPlatformCount) {
      this.ydRepaymentPlatformCount = ydRepaymentPlatformCount;
   }

   public Integer getYdLoanPlatformCount() {
      return ydLoanPlatformCount;
   }

   public void setYdLoanPlatformCount(Integer ydLoanPlatformCount) {
      this.ydLoanPlatformCount = ydLoanPlatformCount;
   }

   public String getYdRiskEvaluation() {
      return ydRiskEvaluation;
   }

   public void setYdRiskEvaluation(String ydRiskEvaluation) {
      this.ydRiskEvaluation = ydRiskEvaluation;
   }

   public Integer getYdScore() {
      return ydScore;
   }

   public void setYdScore(Integer ydScore) {
      this.ydScore = ydScore;
   }

   public Integer getYxLoaningAm3m() {
      return yxLoaningAm3m;
   }

   public void setYxLoaningAm3m(Integer yxLoaningAm3m) {
      this.yxLoaningAm3m = yxLoaningAm3m;
   }

   public Integer getYdRefusedFeature() {
      return ydRefusedFeature;
   }

   public void setYdRefusedFeature(Integer ydRefusedFeature) {
      this.ydRefusedFeature = ydRefusedFeature;
   }

   public Integer getYxYdNoLoan() {
      return yxYdNoLoan;
   }

   public void setYxYdNoLoan(Integer yxYdNoLoan) {
      this.yxYdNoLoan = yxYdNoLoan;
   }

   public Integer getMxVoiceHasSensitivePhone() {
      return mxVoiceHasSensitivePhone;
   }

   public void setMxVoiceHasSensitivePhone(Integer mxVoiceHasSensitivePhone) {
      this.mxVoiceHasSensitivePhone = mxVoiceHasSensitivePhone;
   }

   public String getCompanyName() {
      return companyName;
   }

   public void setCompanyName(String companyName) {
      this.companyName = companyName;
   }

   public Integer getYdNoLoan6m() {
      return ydNoLoan6m;
   }

   public void setYdNoLoan6m(Integer ydNoLoan6m) {
      this.ydNoLoan6m = ydNoLoan6m;
   }

   public Integer getYdLoan1m() {
      return ydLoan1m;
   }

   public void setYdLoan1m(Integer ydLoan1m) {
      this.ydLoan1m = ydLoan1m;
   }

   public Integer getMxMessageNum() {
      return mxMessageNum;
   }

   public void setMxMessageNum(Integer mxMessageNum) {
      this.mxMessageNum = mxMessageNum;
   }

   public Integer getXySucMinusFailNum() {
      return xySucMinusFailNum;
   }

   public void setXySucMinusFailNum(Integer xySucMinusFailNum) {
      this.xySucMinusFailNum = xySucMinusFailNum;
   }

   public Integer getYdPlatformLoanNum() {
      return ydPlatformLoanNum;
   }

   public void setYdPlatformLoanNum(Integer ydPlatformLoanNum) {
      this.ydPlatformLoanNum = ydPlatformLoanNum;
   }

   public Integer getYdPlatformNum3m() {
      return ydPlatformNum3m;
   }

   public void setYdPlatformNum3m(Integer ydPlatformNum3m) {
      this.ydPlatformNum3m = ydPlatformNum3m;
   }

   public Integer getYdDeviceLinkIdCount() {
      return ydDeviceLinkIdCount;
   }

   public void setYdDeviceLinkIdCount(Integer ydDeviceLinkIdCount) {
      this.ydDeviceLinkIdCount = ydDeviceLinkIdCount;
   }

   public Integer getYxFraudScore() {
      return yxFraudScore;
   }

   public void setYxFraudScore(Integer yxFraudScore) {
      this.yxFraudScore = yxFraudScore;
   }
}
var RepaymentPlanList = require('../../RepaymentManage/RepaymentPlanList/index');//还款管理-还款计划
var PaymentHistory = require('../../RepaymentManage/PaymentHistory/index');//还款管理-还款记录
var AlipayPaymentList = require('../../RepaymentManage/AlipayPaymentList/index');//还款管理-支付宝还款列表
var BankCardPaymentList = require('../../RepaymentManage/BankCardPaymentList/index');//还款管理-银行卡还款列表
var DeductionsList = require('../../RepaymentManage/DeductionsList/index');//扣款列表
var StayDeductionsList = require('../../RepaymentManage/StayDeductionsList/index');//待扣款列表
var UserBasicInformation = require('../../UserInformation/UserBasicInformation/index');//客户管理-用户基本信息
var UserAuthenticationList = require('../../UserInformation/UserAuthenticationList/index');//客户管理-用户认证信息
var UserFeedback = require('../../UserInformation/UserFeedback/index');//客户管理-用户反馈
var UserEducationList = require('../../UserInformation/UserEducationList/index');//客户管理-用户教育信息列表
var BlackCustomerList = require('../../UserInformation/BlackCustomerList/index');//客户管理-黑名单
var ScoreRank = require('../../../creditrank/ScoreRank/index');//信用等级管理-评分等级
var AdjustCreditLine = require('../../../creditrank/AdjustCreditLine/index');//调整授信额度
var TableFieldMaintenance = require('../../../creditrank/TableFieldMaintenance/index');//信用评级表单字段维护
var AssessScoreCard = require('../../../creditrank/AssessScoreCard/index');//信用等级管理-评分卡
var LineTypeManage = require('../../../creditrank/LineTypeManage/index');//信用等级管理-额度类型管理
var BorrowingRulesManagement = require('../../BorrowingRulesManagement/index');//规则类型绑定
var LoanList = require('../../UserLoanManage/LoanList/index');//贷前管理-放款列表
var OverdueList = require('../../UserLoanManage/OverdueList/index');//贷前管理-逾期列表
var RepaymentList = require('../../UserLoanManage/RepaymentList/index');//还款列表
var BadDebtsList = require('../../UserLoanManage/BadDebtsList/index');//已坏账列表
var LoanSchedule = require('../../LoanSchedule/index');//贷中管理-借款进度
var ruleEngine = require('../../ruleEngine/index');//规则配置
var SystemConfig = require('../../SystemMng/SystemConfig/index');//系统配置
var sysUserManage = require('../../SystemMng/UserMang/index');//用户管理
var sysRoleManage = require('../../SystemMng/RoleMang/index');//角色管理
var Druid = require('../../SystemMng/Druid/index');//Druid
var sysMenuManage = require('../../SystemMng/MenuMang/index');//菜单管理
var sysDicManage = require('../../SystemMng/DictionaryMang/index');//字典管理
var SystemParameterSettings = require('../../SystemMng/SystemParameterSettings/index');//系统参数设置
var NoteMould = require('../../NewsRecord/NoteMould/index');//字典管理
var NoteMessage = require('../../NewsRecord/NoteMessage/index');//系统参数设置
var OperatorReport = require('../../CreditReport/OperatorReport/index');//信用报告,运营商报告
var CreditLoanReport = require('../../CreditReport/CreditLoanReport/index');//信用报告,信贷报告
var OutsideFee = require('../../FeeManage/OutsideFee/index'); //费用管理 外部数据费用
var XinyanLoanReport = require('../../CreditReport/XinyanLoanReport/index'); //信用报告 小额网贷报告

var AuditorStatistic = require('../../StatisticManage/AuditorStatistic/index'); //统计管理 审核人员统计
var AuditingStatistic = require('../../StatisticManage/AuditingStatistic/index'); //统计管理 审核统计
var ChannelStatistic = require('../../StatisticManage/ChannelStatistic/index'); //统计管理 渠道统计
var UserStatistic = require('../../StatisticManage/UserStatistic/index'); //统计管理 用户统计
var LoadStatistic = require('../../StatisticManage/LoadStatistic/index'); //统计管理 放款统计
var RepaymentStatistic = require('../../StatisticManage/RepaymentStatistic/index'); //统计管理 还款统计
var OverdueStatistic = require('../../StatisticManage/OverdueStatistic/index'); //统计管理 还款统计




module.exports = {
  RepaymentPlanList,
  PaymentHistory,
  AlipayPaymentList,
  BankCardPaymentList,
  DeductionsList,
  StayDeductionsList,
  UserBasicInformation,
  UserAuthenticationList,
  UserFeedback,
  UserEducationList,
  BlackCustomerList,
  ScoreRank,
  AdjustCreditLine,
  TableFieldMaintenance,
  AssessScoreCard,
  LineTypeManage,
  BorrowingRulesManagement,
  LoanList,
  OverdueList,
  RepaymentList,
  BadDebtsList,
  ruleEngine,
  SystemConfig,
  sysUserManage,
  sysRoleManage,
  Druid,
  sysMenuManage,
  sysDicManage,
  SystemParameterSettings,
  LoanSchedule,
  NoteMould,
  NoteMessage,
  OperatorReport,
  CreditLoanReport,
  OutsideFee,
  XinyanLoanReport,
  AuditorStatistic,
    AuditingStatistic,
    ChannelStatistic,
    UserStatistic,
    LoadStatistic,
    RepaymentStatistic,
    OverdueStatistic
}
var Reflux = require('reflux');
import React from 'react';
var AppActions = require('../actions/AppActions');
var Workbench = require('../../Public/Workbench/Index')
var UserBasicInformation = require('../../UserInformation/UserBasicInformation/index');//客户管理-用户认证信息
// console.log(localStorage.scriptid);
export default Reflux.createStore({
	listenables: [AppActions],
	onSetTabActiveKey(tabName, tabId,scriptid) {
		var me = this;
		// var tablist = window.tablist || this.tablist;
		// console.log('window',window.tablist);
	var tabLis = window.tablist || [{'key': 'ShowWorkbench', 'tabName': '工作台',"tabId":'ShowWorkbench',tabContent:<Workbench/>}];
	var tab = window.tablist || [];
		var flag = false;
		var activeId = tabId;
		var tablist= scriptid=='ShowWorkbench'? tabLis : tab;
		tablist.forEach((v) => {
			if (tabId === v.tabId) {
				flag = true; //当前activeKey 在tablist中已经存在
				return
			}
		});
		var tabContent;

		if (!flag) { // 点击左侧菜单时，没有相应标签页
			var Component = Workbench;

			var routeNames1 = [
					'CheckManagement',
					'ChannelInformationStatistics',
					'ChannelAppManage',
					'ChannelManage',
					'ScenePortManage',
					'ThirdPartyInquiry',
					'InterfaceExecutionRecord',
					'RiskControlDataStatistics',
					'RulesMatchResults',
					'FormFieldsToAdd',
					'CollectionPersonnelList',
					'CollectionFeedback',
					'CollectionTotalOrderList',
					'CollectionRecordList',
					'MyOrder',
					'CollectionFeedbackManage',
					'WaitCollectionList',
					'CollectionInList',
					'CommitmentRepaymentList',
					'CollectionSuccessList',
					'RecoveryRateStatistics',
					'CollectionOrderStatistics',
					'CollectionDailyStatistics',
					'OverdueNoAdmission',
					'NoRepaymentPutForward',
					'UnallocatedCollection',
					'StandbyReviewList',
					'MachinePassList',
					'RejectedOrderList',
					'ShildCreditAuditRecords',
					'LoanApplicationManage',
					'MachineRequestRecord',
					'AdverManageList'
				];

			var routeNames2 = [
					'RepaymentPlanList',
					'PaymentHistory',
					'AlipayPaymentList',
					'BankCardPaymentList',
					'DeductionsList',
					'StayDeductionsList',
					'UserBasicInformation',
					'UserAuthenticationList',
					'UserFeedback',
					'UserEducationList',
					'BlackCustomerList',
					'ScoreRank',
					'AdjustCreditLine',
					'TableFieldMaintenance',
					'AssessScoreCard',
					'LineTypeManage',
					'BorrowingRulesManagement',
					'LoanList',
					'OverdueList',
					'RepaymentList',
					'BadDebtsList',
					'LoanSchedule',
					'ruleEngine',
					'SystemConfig',
					'sysUserManage',
					'sysRoleManage',
					'Druid',
					'sysMenuManage',
					'sysDicManage',
					'SystemParameterSettings',
					'NoteMessage',
  					'NoteMould',
					'OperatorReport',
					'CreditLoanReport',
					'OutsideFee',
					'XinyanLoanReport'
			];

			var routeNames3 = [
					'OrdinaryUserList',
					'AgentList',
					'AgentModuleAdmin',
					'ShareDetail',
					'CashCheck',
					'CashRecord',
					'AgentModuleAdminBranch',
					'ShareDetailBranch',
					'CashCheckBranch',
					'CashRecordBranch',
					'remitList',
					'OperatingChargesList',
					'remitCheck',
					'systemCallList',
					'DailyPassRate',
					'PlatformDataDaily',
					'DailyRepaymentAnalysis',
					'MonthlyRepaymentAnalysis',
					'Monthly',
					'IncomeStatistics',
					'ExpenditureStatistics',
					'DailyPrincipal',
					'DailylLoanData',
					'TimedTaskList',
					'TimedTaskLog',
					'LoanInformation',
					'ShowWorkbench',
					'ReviewPersonnelList',
					'ReviewTotalOrderList',
					'BlackTaskManager',
					'MyReviewOrder'
			];

			if (routeNames1.indexOf(tabId)>-1) {
				require.ensure([], function (require) {
					Component = require('./route1')[tabId];
					tabContent = <Component />;
					me.updataTablist(tabId, tabName, tabContent, tablist);
				}, 'route1'); 
			}

			else if (routeNames2.indexOf(tabId)>-1) {
				require.ensure([], function (require) {
					Component = require('./route2')[tabId];
					tabContent = <Component />;
					me.updataTablist(tabId, tabName, tabContent, tablist);
				}, 'route2'); 
			}

			else if (routeNames3.indexOf(tabId)>-1) {
				require.ensure([], function (require) {
					Component = require('./route3')[tabId];
					tabContent = <Component />;
					me.updataTablist(tabId, tabName, tabContent, tablist);
				}, 'route3'); 
			} 
		} else this.update(activeId, tablist);

	},
	updataTablist(tabId, tabName, tabContent, tablist) {
		tablist = tablist.concat({
			key: tabId,
			tabName: tabName,
			tabId: tabId,
			tabContent: tabContent
		})
		this.update(tabId, tablist);
	},
	onRemoveTab(tabId) {
		console.log(tabId)
		var tablist = window.tablist || this.tablist;
		var foundIndex = 0;
		tablist = tablist.filter(function(t, index) {
			if (t.tabId !== tabId) {
				return true;
			} else {
				foundIndex = index;
				return false;
			}
		});
		var activeId = window.activeId || this.activeId;
		if (activeId === tabId) {
			
			if (foundIndex) {
				foundIndex--;
			}
			console.log(activeId,tablist[foundIndex])
			activeId = tablist[foundIndex]?tablist[foundIndex].tabId:'';
		}
		this.update(activeId, tablist);
	},
	update(activeId, tablist) {
		window.tablist = tablist;
		window.activeId = activeId
		this.trigger({
			activeId: activeId,
			tablist: tablist
		});
	}
});

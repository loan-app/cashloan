package com.xiji.cashloan.cl.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiji.cashloan.cl.domain.UserAuth;
import com.xiji.cashloan.cl.mapper.BorrowRepayMapper;
import com.xiji.cashloan.cl.mapper.SystemCountMapper;
import com.xiji.cashloan.cl.model.ManageBRepayModel;
import com.xiji.cashloan.cl.model.statistic.UserStatisticData;
import com.xiji.cashloan.cl.service.SystemCountService;
import com.xiji.cashloan.cl.util.black.CollectionUtil;
import com.xiji.cashloan.core.common.util.DateUtil;
import com.xiji.cashloan.core.domain.Borrow;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import tool.util.BigDecimalUtil;
import tool.util.StringUtil;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.text.ParseException;
import java.util.*;

/**
 * 首页系统数据统计
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@SuppressWarnings("unchecked")
@Service("systemCountService")
public class SystemCountServiceImpl implements SystemCountService {

	@Resource
	private SystemCountMapper systemCountMapper;

	@Resource
	private BorrowRepayMapper borrowRepayMapper;

	public Map<String,Object> reBuildMap(List<Map<String,Object>> maps){
		if(maps!=null){
			Map<String,Object> result = new HashMap<String, Object>();
			for(int i=0;i<maps.size();i++){
				String key = String.valueOf(maps.get(i).get("key"));
				if(StringUtil.isNotBlank(key)){
					key = key==null?"":key;

				}else{
					key = "未知地区";
				}
				Object value = maps.get(i).get("value");
				result.put(key, value);
			}
			result.remove("null");
			return result;
		}else{
			return new HashMap<String, Object>();
		}
	}

	/**
	 * 查询时间过长的内容保存context
	 * @param result
	 * @param rtValue
	 * @param rtMap
	 * @throws ParseException
	 */
	private void areaCountDispose(Map<String, Object> result, List<Map<String, Object>> rtValue, Map<String, Object> rtMap) throws ParseException{
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();    
		ServletContext context = webApplicationContext.getServletContext();

		if (StringUtil.isNotBlank(context)) {
			Object t = context.getAttribute("areaCountSelectTime");
			String todayStr = DateUtil.dateStr2(DateUtil.getNow());
			if (!todayStr.equals(t)) {

				rtValue = areaCount(1);
				result = reBuildMap(rtValue);
				rtMap.put("areaBorrowAmt", result);
				context.setAttribute("areaBorrowAmt", rtValue);

				rtValue = areaCount(2);
				result = reBuildMap(rtValue);
				rtMap.put("areaBorrowRepay", result);
				context.setAttribute("areaBorrowRepay", rtValue);

				rtValue = areaCount(3);
				result = reBuildMap(rtValue);
				rtMap.put("areaRegister", result);
				context.setAttribute("areaRegister", rtValue);

				context.setAttribute("areaCountSelectTime", todayStr);//保存时间

			} else {

				rtValue = (List<Map<String, Object>>) context.getAttribute("areaBorrowAmt");
				result = reBuildMap(rtValue);
				rtMap.put("areaBorrowAmt", result);

				rtValue = (List<Map<String, Object>>) context.getAttribute("areaBorrowRepay");
				result = reBuildMap(rtValue);
				rtMap.put("areaBorrowRepay", result);

				rtValue = (List<Map<String, Object>>) context.getAttribute("areaRegister");
				result = reBuildMap(rtValue);
				rtMap.put("areaRegister", result);
			}
		}
	}

	//所有地区数组
	private static String[] address = {"北京市","上海市","天津市","重庆市","内蒙古自治区","宁夏回族自治区","新疆维吾尔自治区","西藏自治区","广西壮族自治区"
		,"香港特别行政区","澳门特别行政区","黑龙江省","辽宁省","吉林省","河北省","河南省","湖北省","湖南省","山东省","山西省","陕西省","安徽省","浙江省","江苏省","福建省",
		"广东省","海南省","四川省","云南省","贵州省","青海省","甘肃省","江西省","台湾省"};
	//地区显示处理
	private String changeAdd(String address){
		address = address.replace("省", "").replace("市", "").replace("自治区", "").replace("回族", "").replace("维吾尔", "")
				.replace("壮族", "").replace("特别行政区", "");
		return address;
	}
	//累计数据统计
	private List<Map<String, Object>> areaCount(int type){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		switch (type) {
		case 1:
			for (int i = 0; i < address.length; i++) {
				Map<String, Object> map = new HashMap<>();
				map.put("key",changeAdd(address[i]));
				map.put("value", systemCountMapper.sumBorrowAmtByProvince(address[i]+"%"));
				list.add(map);
			}
			break;
		case 2:
			for (int i = 0; i < address.length; i++) {
				Map<String, Object> map = new HashMap<>();
				map.put("key",changeAdd(address[i]));
				map.put("value", systemCountMapper.sumBorrowRepayByProvince(address[i]+"%"));
				list.add(map);
			}
			break;
		default:
			for (int i = 0; i < address.length; i++) {
				Map<String, Object> map = new HashMap<>();
				String userCount = systemCountMapper.countRegisterByProvince(address[i]+"%");
				if (Integer.valueOf(userCount)>0) {
					map.put("key",changeAdd(address[i]));
					map.put("value", userCount);
				}
				list.add(map);
			}
			break;
		}
		return list;
	}

	@Override
	public Map<String, Object> todayInfo() throws Exception {
		Map<String,Object> rtMap = new HashMap<String, Object>();

		Map<String,Object> param = new HashMap<String, Object>();
		param.put("todayTime", DateUtil.getNow());

		Integer register = systemCountMapper.countRegister(param);
		rtMap.put("register", register);

		Integer login = systemCountMapper.countLogin(param);
		rtMap.put("login", login);

		double borrow = systemCountMapper.countBorrow(param);
		rtMap.put("borrow", borrow);

		double borrowPass = systemCountMapper.countBorrowPass(param);
		rtMap.put("borrowPass", borrowPass);

		double newBorrowPass = systemCountMapper.countNewBorrowPass(param);
		//rtMap.put("newBorrowPass", newBorrowPass);


		Double borrowLoan = systemCountMapper.countBorrowLoan(param);
		rtMap.put("borrowLoan", borrowLoan);

		Integer borrowRepay = systemCountMapper.countBorrowRepay(param);
		rtMap.put("borrowRepay", borrowRepay);

		Map<String, Object> borrowRepayParams = new HashMap<>();
		borrowRepayParams.put("startTime", DateUtil.dateStr(new Date(), DateUtil.DATEFORMAT_STR_002));
		borrowRepayParams.put("endTime", DateUtil.dateStr(new Date(), DateUtil.DATEFORMAT_STR_002));
		List<ManageBRepayModel> brs = borrowRepayMapper.listModel(borrowRepayParams);

		int todayShouldCnt =  brs.size();
		int todayRepayCnt = 0;//今日结清
		int todayNotRepayCnt = 0;//今日待还
		int todayDeferredCnt = 0;//今日展期
		for (ManageBRepayModel br : brs) {
			if ("10".equals(br.getState())) {
				todayRepayCnt++;
			}
			if ("20".equals(br.getState())) {
				todayNotRepayCnt++;
			}
			if ("30".equals(br.getState())) {
				todayDeferredCnt++;
			}
		}
		// double todayOverdueRate = 0;
		double todayShouldCntRate = 0;
		if (todayShouldCnt != 0) {
			// todayOverdueRate = todayNotRepayCnt * 100 / (double) todayShouldCnt;
			todayShouldCntRate = todayRepayCnt* 100 / (double) todayShouldCnt;
		}

		rtMap.put("todayShouldCnt", todayShouldCnt);
		rtMap.put("todayRepayCnt", todayRepayCnt);
		rtMap.put("todayNotRepayCnt", todayNotRepayCnt);
		rtMap.put("todayDeferredCnt", todayDeferredCnt);

		//rtMap.put("todayOverdueRate", String.format("%.2f", todayOverdueRate));
		rtMap.put("todayShouldCntRate",BigDecimalUtil.decimal(todayShouldCntRate,2));

		int todayCertification = 0;//当日实名
		int todayContact = 0;//通讯录认证人数
		int todayBank = 0;//当日绑卡
		int todayPhone = 0;//当日运营商认证
		int todayNewBorrow = 0;//新客借款
		int todayOldBorrow = 0;//老客借款
		int todayNewLoan = 0;//当日新客放款
		int todayOldLoan = 0;//当日老客放款
		double todayTotalSum = 0;//借出总金额
		double todayPrincipal = 0;//借出本金
		List<UserAuth> userAuths = systemCountMapper.listUserAuthByToday();
		if (CollectionUtil.isNotEmpty(userAuths)){
			for (UserAuth userAuth :userAuths){
				if ("30".equals(userAuth.getIdState())){
					todayCertification ++;
				}
				if ("30".equals(userAuth.getContactState())){
					todayContact++;
				}
				if ("30".equals(userAuth.getBankCardState())){
					todayBank++;
				}
				if ("30".equals(userAuth.getPhoneState())){
					todayPhone++;
				}
			}
		}

		List<Borrow> borrows = systemCountMapper.listBorrowByToday();
		if (CollectionUtil.isNotEmpty(borrows)){
			for (Borrow borrow1:borrows){
				if ("10".equals(borrow1.getAgain())){
					todayNewBorrow++;
				}
				if ("20".equals(borrow1.getAgain())){
					todayOldBorrow++;
				}
			}
		}
		if(todayNewBorrow>0){
			rtMap.put("passApr", BigDecimalUtil.decimal(newBorrowPass/todayNewBorrow*100,2));
		} else {
			rtMap.put("passApr", 0);
		}
		List<Borrow> borrowLoanList = systemCountMapper.listBorrowStatistics();
		if (CollectionUtil.isNotEmpty(borrowLoanList)){
			for (Borrow loan:borrowLoanList){
				if ("10".equals(loan.getAgain())){
						todayNewLoan++;
						todayTotalSum =  BigDecimalUtil.add(todayTotalSum,loan.getAmount());
						todayPrincipal = BigDecimalUtil.add(todayPrincipal,loan.getRealAmount());
				}

				if ("20".equals(loan.getAgain())){
						todayOldLoan++;
						todayPrincipal = BigDecimalUtil.add(todayPrincipal,loan.getRealAmount());
						todayTotalSum =  BigDecimalUtil.add(todayTotalSum,loan.getAmount());
				}
			}
		}
		if (register > 0){
			rtMap.put("borrowRate",BigDecimalUtil.decimal(todayNewLoan/register*100,2));
		}else {
			rtMap.put("borrowRate",0);
		}

		rtMap.put("todayCertification", todayCertification);
		rtMap.put("todayContact", todayContact);
		rtMap.put("todayBank", todayBank);
		rtMap.put("todayPhone", todayPhone);
		rtMap.put("todayNewBorrow", todayNewBorrow);
		rtMap.put("todayOldBorrow", todayOldBorrow);
		rtMap.put("todayNewLoan", todayNewLoan);
		rtMap.put("todayOldLoan", todayOldLoan);
		rtMap.put("todayTotalSum", todayTotalSum);
		rtMap.put("todayPrincipal", todayPrincipal);

		return rtMap;
	}

	@Override
	public Map<String, Object> cumulativeInfo() throws Exception {
		Map<String,Object> rtMap = new HashMap<String, Object>();
		Integer borrowLoanHistory = systemCountMapper.countBorrowLoanHistory();
		rtMap.put("borrowLoanHistory", borrowLoanHistory);

		Integer borrowRepayHistory = systemCountMapper.countBorrowRepayHistory();
		rtMap.put("borrowRepayHistory", borrowRepayHistory);

		// 统计借款申请的数量
		Integer borrowApplyHistory = systemCountMapper.totalBorrowApply();
		rtMap.put("borrowApplyHistory",borrowApplyHistory);

		// 统计注册用户数量
		Integer registerHistory = systemCountMapper.totalRegister();
		rtMap.put("registerHistory",registerHistory);

		return rtMap;
	}

	@Override
	public Map<String, Object> realTimeInfo() throws Exception {
		Map<String,Object> rtMap = new HashMap<String, Object>();
		Double needRepay  = systemCountMapper.sumBorrowNeedRepay();
		rtMap.put("needRepay", needRepay==null?0.00:needRepay);

		Double overdueRepay = systemCountMapper.sumBorrowOverdueRepay();
		rtMap.put("overdueRepay", overdueRepay==null?0.00:overdueRepay);

		Double sumOnlineLoadAmount =systemCountMapper.sumOnlineLoadAmount();
		rtMap.put("sumOnlineLoadAmount", sumOnlineLoadAmount==null?0.00:sumOnlineLoadAmount);

		Double sumUnlineLoadAmount = systemCountMapper.sumUnlineLoadAmount();
		rtMap.put("sumUnlineLoadAmount", sumUnlineLoadAmount==null?0.00:sumUnlineLoadAmount);

		Double sumOnlineRepaymentAmount = systemCountMapper.sumOnlineRepaymentAmount();
		rtMap.put("sumOnlineRepaymentAmount", sumOnlineRepaymentAmount == null?0.00:sumOnlineRepaymentAmount);

		Double sumUnlineRepaymentAmount = systemCountMapper.sumUnlineRepaymentAmount();
		rtMap.put("sumUnlineRepaymentAmount",sumUnlineRepaymentAmount == null?0.00:sumUnlineRepaymentAmount);
		return rtMap;
	}

	@Override
	public Map<String, Object> areaInfo() throws Exception {
		Map<String,Object> rtMap = new HashMap<String, Object>();

		Map<String,Object> result = null;
		List<Map<String,Object>> rtValue = null;

		this.areaCountDispose(result,rtValue,rtMap);

		return rtMap;
	}

	@Override
	public Map<String, Object> repayWayInfo() throws Exception {
		Map<String,Object> rtMap = new HashMap<String, Object>();

		Map<String,Object> result = null;
		List<Map<String,Object>> rtValue = null;

		rtValue = systemCountMapper.countRepaySource();
		result = reBuildMap(rtValue);
		String[] source = {"自动代扣","银行卡转账","支付宝转账","认证支付"};
		List<Map<String,Object>> sourceList = new ArrayList<Map<String,Object>>();
		Map<String,Object> sm;
		for(int i=0;i<source.length;i++){
			if(!result.containsKey(source[i])){
				result.put(source[i], 0);
			}
			sm = new HashMap<String, Object>();
			sm.put(source[i], result.get(source[i]));
			sourceList.add(sm);
		}
		rtMap.put("repaySource", sourceList);

		return rtMap;
	}

	@Override
	public Map<String, Object> loanAndRepayInfo() throws Exception {
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();    
		ServletContext context = webApplicationContext.getServletContext();
		Map<String,Object> result1;
		Map<String,Object> result2;
		Map<String,Object> result3;
		Map<String,Object> result4;
		Map<String,Object> rtMap = new HashMap<String, Object>();
		if (StringUtil.isNotBlank(context)) {
			Object t = context.getAttribute("loanAndRepaySelectTime");			
			String todayStr = DateUtil.dateStr2(DateUtil.getNow());
			if (!todayStr.equals(t)) {
				List<String> days = new ArrayList<String>();
				Date nowDate = DateUtil.getNow();
				days.add(DateUtil.dateStr2(nowDate));
				Calendar date = Calendar.getInstance();
				for (int i = 0; i < 15; i++) {
					date.setTime(nowDate);
					date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
					nowDate = date.getTime();
					String day = DateUtil.dateStr2(nowDate);
					days.add(day);
				}

				systemCountMapper.countFifteenDaysNeedRepay();
				List<Map<String,Object>> rtValue1 = systemCountMapper.countFifteenDaysNeedRepay();
				List<Map<String,Object>> rtValue2 = systemCountMapper.countFifteenDaysRealRepay();
				List<Map<String,Object>> rtValue4 = systemCountMapper.countFifteenDaysLoan();
				result1 = reBuildMap(rtValue1);
				result2 = reBuildMap(rtValue2);
				result4 = reBuildMap(rtValue4);
				result3 = new HashMap<String, Object>();
				for(int i=0;i<days.size();i++){
					String day = days.get(i);
					if(!result1.containsKey(day)){
						result1.put(day, 0.00);
					}
					if(!result2.containsKey(day)){
						result2.put(day, 0.00);
					}

					String needStr = String.valueOf(result1.get(day));
					needStr = (StringUtil.isNotBlank(needStr) && !"null".equals(needStr))?needStr:"0.00";
					String realStr = String.valueOf(result2.get(day));
					realStr = (StringUtil.isNotBlank(realStr) && !"null".equals(realStr))?realStr:"0.00";
					Double need = Double.valueOf(needStr);
					Double real = Double.valueOf(realStr);
					if(real>=need){
						result3.put(day, 0.00);
					}else if(real<need){
						Double diff = need - real;
						result3.put(day, diff/need);
					}else{
						result3.put(day, 1.0);
					}

					if(!result4.containsKey(day)){
						result4.put(day, 0);
					}
				}
				rtMap.put("fifteenDaysNeedRepay", result1);
				context.setAttribute("fifteenDaysNeedRepay", result1);
				rtMap.put("fifteenDaysRealRepay", result2);
				context.setAttribute("fifteenDaysRealRepay", result2);
				rtMap.put("fifteenDaysOverdueApr", result3);
				context.setAttribute("fifteenDaysOverdueApr", result3);
				rtMap.put("fifteenDaysLoan", result4);
				context.setAttribute("fifteenDaysLoan", result4);
				context.setAttribute("loanAndRepaySelectTime", todayStr);
			} else {
				result1 =(Map<String, Object>) context.getAttribute("fifteenDaysNeedRepay");
				rtMap.put("fifteenDaysNeedRepay", result1);

				result2 =(Map<String, Object>) context.getAttribute("fifteenDaysRealRepay");
				rtMap.put("fifteenDaysRealRepay", result2);

				result3 =(Map<String, Object>) context.getAttribute("fifteenDaysOverdueApr");
				rtMap.put("fifteenDaysOverdueApr", result3);

				result4 =(Map<String, Object>) context.getAttribute("fifteenDaysLoan");
				rtMap.put("fifteenDaysLoan", result4);
			}

		}
		return rtMap;
	}


	/**
	 * 用户数据统计
	 * @param params
	 * @param current
	 * @param pageSize
	 * @return
	 */
	@Override
	public Page<UserStatisticData> listUserStatisticData(Map<String,Object> params, Integer current, Integer pageSize){
		PageHelper.startPage(current, pageSize);
		Page<UserStatisticData> userStatisticData = (Page<UserStatisticData>) systemCountMapper.listUserStatisticData(params);
		return userStatisticData;
	}



//	/**
//	 * 渠道数据统计
//	 * @param params
//	 * @param current
//	 * @param pageSize
//	 * @return
//	 */
//	@Override
//	public Page<ChannelStatisticModel> listChannelStatisticData(Map<String,Object> params, Integer current, Integer pageSize){
//		PageHelper.startPage(current, pageSize);
//
//		Page<ChannelStatisticModel> channelStatisticData = (Page<ChannelStatisticModel>) systemCountMapper.listChannelStatisticData(params);
//
//		for (ChannelStatisticModel statisticData : channelStatisticData){
//
//			if (statisticData.getBorrowApplyCount() == null || statisticData.getBorrowApplyCount() <= 0){
//				statisticData.setMachineAuditPassRate(0.00);
//				statisticData.setMachineAuditNotPassRate(0.00);
//				statisticData.setReviewPassRate(0.00);
//				statisticData.setReviewNotPassRate(0.00);
//
//			} else {
//
//				statisticData.setMachineAuditPassRate(BigDecimalUtil.decimal((double)statisticData.getMachineAuditPassCount()/(double)statisticData.getBorrowApplyCount()*100,2));
//				statisticData.setMachineAuditNotPassRate(BigDecimalUtil.decimal((double)statisticData.getMachineAuditNotPassCount()/(double)statisticData.getBorrowApplyCount()*100,2));
//				statisticData.setReviewNotPassRate(BigDecimalUtil.decimal((double)statisticData.getReviewNotPassCount()/(double)statisticData.getBorrowApplyCount()*100,2));
//				statisticData.setReviewPassRate(BigDecimalUtil.decimal((double)statisticData.getReviewPassCount()/(double)statisticData.getBorrowApplyCount()*100,2));
//			}
//
//			if (statisticData.getUserRegister() == null || statisticData.getUserRegister() <= 0){
//				statisticData.setLoadRate(0.00);
//			} else {
//				statisticData.setLoadRate(BigDecimalUtil.decimal((double)(statisticData.getFirstLoadCount()+statisticData.getAgainLoadCount())/(double)statisticData.getUserRegister()*100,2));
//			}
//
//			if (statisticData.getAgainLoadCount() == null){
//				statisticData.setAgainLoadCount(0);
//			}
//			if (statisticData.getFirstLoadCount() == null){
//				statisticData.setFirstLoadCount(0);
//			}
//			if (statisticData.getAgainLoadCount() == 0 && statisticData.getFirstLoadCount() == 0){
//				statisticData.setOverdueRate(0.00);
//			}else {
//				statisticData.setOverdueRate(BigDecimalUtil.decimal((double)statisticData.getOverdueCount()/(double)(statisticData.getAgainLoadCount()+statisticData.getFirstLoadCount())*100,2));
//			}
//			if (statisticData.getFirstLoadCount() == 0){
//				statisticData.setFirstOverdueRate(0.00);
//			}else {
//				statisticData.setFirstOverdueRate(BigDecimalUtil.decimal((double)statisticData.getFirstOverdueCount()/(double)statisticData.getFirstLoadCount()*100,2));
//			}
//
//		}
//		return channelStatisticData;
//	}
}

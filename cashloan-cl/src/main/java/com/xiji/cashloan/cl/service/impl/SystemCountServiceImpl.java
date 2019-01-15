package com.xiji.cashloan.cl.service.impl;

<<<<<<< HEAD
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import com.xiji.cashloan.cl.mapper.BorrowRepayMapper;
=======
>>>>>>> ef1f89cf99340a4b2743258b96995a3f6b5b77b6
import com.xiji.cashloan.cl.mapper.SystemCountMapper;
import com.xiji.cashloan.cl.model.ManageBRepayModel;
import com.xiji.cashloan.cl.service.BorrowRepayService;
import com.xiji.cashloan.cl.service.SystemCountService;
<<<<<<< HEAD
import org.apache.http.client.utils.DateUtils;
=======
import com.xiji.cashloan.core.common.util.DateUtil;
>>>>>>> ef1f89cf99340a4b2743258b96995a3f6b5b77b6
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

		if(borrow>0){
			rtMap.put("passApr", BigDecimalUtil.decimal(borrowPass/borrow*100,2));
		} else {
			rtMap.put("passApr", 0);
		}

		Integer borrowLoan = systemCountMapper.countBorrowLoan(param);
		rtMap.put("borrowLoan", borrowLoan);

		Integer borrowRepay = systemCountMapper.countBorrowRepay(param);
		rtMap.put("borrowRepay", borrowRepay);

		Map<String, Object> borrowRepayParams = new HashMap<>();
		borrowRepayParams.put("startTime", DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		borrowRepayParams.put("endTime", DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		List<ManageBRepayModel> brs = borrowRepayMapper.listModel(borrowRepayParams);

		int todayShouldCnt =  brs.size();
		int todayRepayCnt = 0;
		int todayNotRepayCnt = 0;
		int todayDeferredCnt = 0;
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
		double todayOverdueRate = 0;
		if (todayShouldCnt != 0) {
			todayOverdueRate = todayNotRepayCnt * 100 / (double) todayShouldCnt;
		}

		rtMap.put("todayShouldCnt", todayShouldCnt);
		rtMap.put("todayRepayCnt", todayRepayCnt);
		rtMap.put("todayNotRepayCnt", todayNotRepayCnt);
		rtMap.put("todayDeferredCnt", todayDeferredCnt);

		rtMap.put("todayOverdueRate", String.format("%.2f", todayOverdueRate));

        if (register > 0){
        	rtMap.put("borrowRate",BigDecimalUtil.decimal(borrowLoan/register*100,2));
		}else {
        	rtMap.put("borrowRate",0);
		}

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


}

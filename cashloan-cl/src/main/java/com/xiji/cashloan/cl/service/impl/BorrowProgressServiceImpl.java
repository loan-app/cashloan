package com.xiji.cashloan.cl.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiji.cashloan.cl.domain.BankCard;
import com.xiji.cashloan.cl.domain.BorrowProgress;
import com.xiji.cashloan.cl.domain.BorrowRepayLog;
import com.xiji.cashloan.cl.mapper.BankCardMapper;
import com.xiji.cashloan.cl.mapper.BorrowProgressMapper;
import com.xiji.cashloan.cl.mapper.BorrowRepayLogMapper;
import com.xiji.cashloan.cl.mapper.BorrowRepayMapper;
import com.xiji.cashloan.cl.mapper.ClBorrowMapper;
import com.xiji.cashloan.cl.model.BorrowRepayModel;
import com.xiji.cashloan.cl.model.ClBorrowModel;
import com.xiji.cashloan.cl.model.ManageBorrowModel;
import com.xiji.cashloan.cl.model.ManageBorrowProgressModel;
import com.xiji.cashloan.cl.service.BorrowProgressService;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.core.domain.Borrow;
import com.xiji.cashloan.core.domain.UserBaseInfo;
import com.xiji.cashloan.core.mapper.UserBaseInfoMapper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tool.util.DateUtil;
import tool.util.StringUtil;


/**
 * 借款进度表ServiceImpl
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-02-14 10:33:38
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("borrowProgressService")
public class BorrowProgressServiceImpl extends BaseServiceImpl<BorrowProgress, Long> implements BorrowProgressService {
	
    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(BorrowProgressServiceImpl.class);
   
    @Resource
    private BorrowProgressMapper borrowProgressMapper;
    @Resource
    private ClBorrowMapper clBorrowMapper;
    @Resource
    private BorrowRepayMapper borrowRepayMapper;
    @Resource
    private BorrowRepayLogMapper borrowRepayLogMapper;
    @Resource
    private BankCardMapper bankCardMapper;
    @Resource
	private UserBaseInfoMapper userBaseInfoMapper;
    
	@Override
	public BaseMapper<BorrowProgress, Long> getMapper() {
		return borrowProgressMapper;
	}

	
	@Override
	public Map<String,Object> result(Borrow borrow) {
		Map<String,Object> searchMap = new HashMap<>();
		searchMap.put("borrowId", borrow.getId());
		BorrowRepayLog log = borrowRepayLogMapper.findSelective(searchMap);
		
		List<BorrowRepayModel> repay = borrowRepayMapper.listSelModel(searchMap);
		Map<String,Object> result = new HashMap<>();
		ClBorrowModel clBorrowModel = new ClBorrowModel();
		BeanUtils.copyProperties(borrow, clBorrowModel);
		clBorrowModel.setCreditTimeStr(DateUtil.dateStr(clBorrowModel.getCreateTime(),"yyyy-M-d"));
		clBorrowModel.setPenalty("20");
		if (!repay.isEmpty()) {
			clBorrowModel.setPenaltyAmount(repay.get(0).getPenaltyAmout());
			if (repay.get(0).getPenaltyAmout()>0) {
				clBorrowModel.setPenalty("10");
				clBorrowModel.setOverdueAmount(String.valueOf(clBorrowModel.getAmount()+clBorrowModel.getPenaltyAmount()));
			}
		}else{
			clBorrowModel.setOverdueAmount(String.valueOf(clBorrowModel.getAmount()));
		}
		searchMap.clear();
		searchMap.put("userId", borrow.getUserId());
		BankCard card = bankCardMapper.findSelective(searchMap);
		if(StringUtil.isNotBlank(searchMap)){
			clBorrowModel.setCardNo(card.getCardNo());
			clBorrowModel.setBank(card.getBank());
		}
		
		List<ClBorrowModel> brList = new ArrayList<ClBorrowModel>();
		brList.add(clBorrowModel);
		result.put("borrow", brList);
		Date repayDate = null;
		for (BorrowRepayModel borrowRepayModel : repay) {
			repayDate = borrowRepayModel.getRepayTime();
			borrowRepayModel.setRepayTimeStr(DateUtil.dateStr(borrowRepayModel.getRepayTime(),"yyyy-M-d"));
			borrowRepayModel.setAmount(borrowRepayModel.getAmount()+borrowRepayModel.getPenaltyAmout());
		}
		if (StringUtil.isNotBlank(log)) {
			for (BorrowRepayModel repayModel : repay) {
				repayModel.setRealRepayTime(DateUtil.dateStr(log.getRepayTime(),"yyyy-M-d"));
				repayModel.setRealRepayAmount(String.valueOf(log.getAmount()+log.getPenaltyAmout()));
			}
		}
		result.put("repay", repay);

		//展期逻辑
		String delaySwitch = Global.getValue("delay_switch");
		if (StringUtil.equalsIgnoreCase(delaySwitch,"10")) {
			//存在还款计划时，才需要展示展期信息
			if (repayDate != null) {
				Map<String,Object> delayItem = new HashMap<>();
				if(StringUtil.isNotBlank(searchMap)){
					if (StringUtil.isNotEmpty(card.getBank()) && StringUtil.isNotEmpty(card.getCardNo())) {
						delayItem.put("bankMsg",card.getBank()+"("+card.getCardNo().substring(card.getCardNo().length() - 4)+")");
					}
				}
				SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
				Date repayPlanTime = com.xiji.cashloan.core.common.util.DateUtil.valueOf(time.format(repayDate));
				Date nowDate = com.xiji.cashloan.core.common.util.DateUtil.valueOf(time.format(new Date()));
				String dateStr = "";
				if (nowDate.after(repayPlanTime)){
					dateStr = DateUtil.dateStr(DateUtil.rollDay(new Date(),7),"yyyy-M-d");
				}else {
					dateStr = DateUtil.dateStr(DateUtil.rollDay(repayDate,7),"yyyy-M-d");
				}
				delayItem.put("delayItemTips","顺延一个还款周期至"+dateStr+"日，需要支付展期服务费￥"+String.valueOf(borrow.getFee()));
				delayItem.put("delayRepayTimeStr",dateStr);
				result.put("delayItem", delayItem);
			}
		}

		return result;
	}
	
	/*private void addList(BorrowProgressModel bpModel) {
		if (bpModel.getState().equals(BorrowModel.STATE_PRE)
				||bpModel.getState().equals(BorrowModel.STATE_NEED_REVIEW)) {
			bpModel.setMsg("系统审核中,请耐心等待");
			bpModel.setType("10");
		}
		if (bpModel.getState().equals(BorrowModel.STATE_AUTO_PASS)
				||bpModel.getState().equals(BorrowModel.STATE_PASS)) {
			bpModel.setMsg("恭喜通过风控审核");
			bpModel.setType("10");
		}
		if (bpModel.getState().equals(BorrowModel.STATE_AUTO_REFUSED)
				||bpModel.getState().equals(BorrowModel.STATE_REFUSED)) {
			bpModel.setMsg("很遗憾,您未通过审核");
			bpModel.setType("20");
		}
		if (bpModel.getState().equals(BorrowModel.STATE_REPAY)
				||bpModel.getState().equals(BorrowModel.STATE_REPAY_FAIL)) {
			bpModel.setMsg("打款中,请注意查收短信");
			bpModel.setType("10");
		}
		if (bpModel.getState().equals(BorrowModel.STATE_FINISH)
				||bpModel.getState().equals(BorrowModel.STATE_REMISSION_FINISH)) {
			bpModel.setMsg("已还款");
			bpModel.setType("30");
		}
		if (bpModel.getState().equals(BorrowModel.STATE_DELAY)) {
			bpModel.setMsg("已逾期,请尽快还款");
			bpModel.setType("20");
		}
		if (bpModel.getState().equals(BorrowModel.STATE_BAD)) {
			bpModel.setMsg("已坏账");
			bpModel.setType("20");
		}
	}
	
	private String findBorrowDay(long userId) {
		String remark = null;
		Map<String,Object> searchMap = new HashMap<>();
		searchMap.put("userId", userId);
		List<RepayModel> modelList = clBorrowMapper.findRepay(searchMap);
		for (RepayModel repayModel : modelList) {
			if (StringUtil.isNotBlank(repayModel)) {
				int day = DateUtil.daysBetween(new Date(), repayModel.getRepayTime());
				if (day>0) {
					remark = "您需要"+day+"天后还款"+repayModel.getAmount()+"元";
				}else if(day == 0){
					remark = "您需要在今天还款"+repayModel.getAmount()+"元";
				}
			}
		}
		return remark;
	}*/
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public  Page<ManageBorrowProgressModel> listModel(Map<String, Object> params, int currentPage,
													  int pageSize) {
		Map<String, Object> bparams=new HashMap<String, Object>();
		if(StringUtil.isNotBlank(params)){
			if(params.containsKey("realName")){
				bparams.put("realName", params.get("realName"));
			}
			if(params.containsKey("phone")){
				bparams.put("phone", params.get("phone"));
			}
			if(params.containsKey("orderNo")){
				bparams.put("orderNo", params.get("orderNo"));
			}
			List<ManageBorrowModel> borrowList = clBorrowMapper.listModel(bparams);
			
			if(StringUtil.isNotBlank(params)&&StringUtil.isNotBlank(bparams)&&StringUtil.isNotBlank(borrowList)){
				bparams=new HashMap<String, Object>();
				List borrowIds=new ArrayList();
				if(borrowList.size()>0){
					for(ManageBorrowModel b:borrowList){
						borrowIds.add(b.getId());
					}
				}else{
					borrowIds.add("0");
				}
				if(StringUtil.isNotBlank(borrowIds)){
				    params.put("borrowIds", borrowIds);
				}
			}
		}
		
		PageHelper.startPage(currentPage, pageSize);
		List<ManageBorrowProgressModel> list = borrowProgressMapper.listModel(params);
		if (StringUtil.isNotBlank(list)) {
			for (int i = 0; i < list.size(); i++) {
				ManageBorrowProgressModel model = list.get(i);
				Borrow b = clBorrowMapper.findByPrimary(model.getBorrowId());
				if (b == null) {
					continue;
				}
				list.get(i).setAmount(b.getAmount());
				list.get(i).setOrderNo(b.getOrderNo());
				UserBaseInfo info = userBaseInfoMapper.findByUserId(model.getUserId());
				if (StringUtil.isNotBlank(info)) {
					list.get(i).setPhone(info.getPhone());
					list.get(i).setRealName(info.getRealName());
				}
			}
		}
		
		return (Page<ManageBorrowProgressModel>)list;
	}

	@Override
	public boolean save(BorrowProgress borrowProgress){
		int result = borrowProgressMapper.save(borrowProgress);
		if(result > 0){
			return true;
		}
		return false;
	}

	@Override
	public List<BorrowProgress> listSeletetiv(Map<String, Object> map) {
		return borrowProgressMapper.listSelective(map);
	}
	@Override
	public int isNormalBorrowProgress(long borrowId) {
		return borrowProgressMapper.isNormalBorrowProgress(borrowId);
	}
	
}

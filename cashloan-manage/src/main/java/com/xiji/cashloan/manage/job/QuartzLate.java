package com.xiji.cashloan.manage.job;

import com.xiji.cashloan.cl.domain.BorrowRepay;
import com.xiji.cashloan.cl.domain.UrgeRepayOrder;
import com.xiji.cashloan.cl.model.BorrowRepayModel;
import com.xiji.cashloan.cl.model.UrgeRepayOrderModel;
import com.xiji.cashloan.cl.service.*;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.exception.ServiceException;
import com.xiji.cashloan.core.domain.Borrow;
import com.xiji.cashloan.core.model.BorrowModel;
import com.xiji.cashloan.manage.domain.QuartzInfo;
import com.xiji.cashloan.manage.domain.QuartzLog;
import com.xiji.cashloan.manage.service.QuartzInfoService;
import com.xiji.cashloan.manage.service.QuartzLogService;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import tool.util.BeanUtil;
import tool.util.BigDecimalUtil;
import tool.util.DateUtil;
import tool.util.StringUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 */
@Component
@Lazy(value = false)
public class QuartzLate implements Job{
	
	private static final Logger logger = Logger.getLogger(QuartzLate.class);
	
	/**
	 * 定时计算逾期
	 * @throws ServiceException 
	 */
	public String late() throws ServiceException {
		BorrowRepayService borrowRepayService = (BorrowRepayService)BeanUtil.getBean("borrowRepayService");
		BorrowProgressService borrowProgressService = (BorrowProgressService)BeanUtil.getBean("borrowProgressService");
		ClBorrowService clBorrowService = (ClBorrowService)BeanUtil.getBean("clBorrowService");
		UrgeRepayOrderService urgeRepayOrderService = (UrgeRepayOrderService)BeanUtil.getBean("urgeRepayOrderService");
		ClSmsService clSmsService = (ClSmsService)BeanUtil.getBean("clSmsService");
		
		
		logger.info("进入逾期计算...");
		String quartzRemark = null;
		int succeed = 0;
		int fail = 0;
		int total = 0;
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("state",BorrowRepayModel.STATE_REPAY_NO);
		List<BorrowRepay> list = borrowRepayService.listSelective(paramMap);
		long badDebtDay = Long.parseLong(Global.getValue("bad_debt_day"));//逾期多少天自动标记为坏账
		String penaltyFee = Global.getValue("penalty_fee");//逾期费率
		String[] penaltyFees = penaltyFee.split(",");
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				try {
					long day = DateUtil.daysBetween(list.get(i).getRepayTime(),new Date());
					if (day>Integer.parseInt(list.get(i).getPenaltyDay())) {
						if (day>0) {
							Borrow borrow = clBorrowService.findByPrimary(list.get(i).getBorrowId());
							//借款天数与逾期利率对应
							double amout = 0;
							double penaltyAmoutMax = Double.parseDouble(Global.getValue("penalty_amout_max"));
							double penaltyAmout = list.get(i).getPenaltyAmout();
							
							Boolean isMax = true;
							//罚息超过上限不再计算
							if ((penaltyAmout/borrow.getAmount())<penaltyAmoutMax) {
								for (int j = 0; j < penaltyFees.length; j++) {
									String[] penaltyParams=penaltyFees[j].split("-");
									if(penaltyParams.length==2){
										//penaltyParams[0]天数，penaltyParams[1]费率
										if (penaltyParams[0].equals(borrow.getTimeLimit())) {
											amout = BigDecimalUtil.decimal(
													BigDecimalUtil.mul(borrow.getAmount(), Double.parseDouble(penaltyParams[1])),2);
										}
									}											
								}
								isMax = false;
							}else {
								amout = BigDecimalUtil.mul(borrow.getAmount(),penaltyAmoutMax);
							}
							Map<String,Object> data = new HashMap<>();
							BorrowRepay br = new BorrowRepay();
							br.setId(list.get(i).getId());
							if (isMax) {
								br.setPenaltyAmout(BigDecimalUtil.decimal(amout, 2));
							}else {
								br.setPenaltyAmout(BigDecimalUtil.decimal(amout*Math.abs(day), 2));
							}
							br.setPenaltyDay(Long.toString(Math.abs(day)));
							
							logger.info("还款计划id--" + br.getId() + " ==> 已经逾期 " + br.getPenaltyDay() + " 天,逾期费用 " + br.getPenaltyAmout() + "元");
							
							int msg  = borrowRepayService.updateLate(br);
							if (msg>0) {
								logger.debug("--修改逾期信息成功--");
								//保存逾期进度
								int count = borrowProgressService.isNormalBorrowProgress(list.get(i).getBorrowId());
								if(count <= 0){
									logger.debug("---------添加逾期进度---------");
									clBorrowService.savePressState(borrow, BorrowModel.STATE_DELAY,"");
									data = new HashMap<>();
									data.put("id", list.get(i).getBorrowId());
									data.put("state", BorrowModel.STATE_DELAY);
									data.put("isOverdue","20");
									msg = clBorrowService.updateSelective(data);
									logger.debug("---------添加逾期结束---------");
								} else if (!"50".equals(borrow.getState()) || !"90".equals(borrow.getState())){
									logger.debug("---------展期后逾期添加逾期进度---------");
									clBorrowService.savePressState(borrow, BorrowModel.STATE_DELAY,"");
									data = new HashMap<>();
									data.put("id", list.get(i).getBorrowId());
									data.put("state", BorrowModel.STATE_DELAY);
									msg = clBorrowService.updateSelective(data);
									logger.debug("---------展期后逾期添加逾期结束---------");
								}
								
								//催收计划
								logger.debug("---------修改催收计划start-------");
								UrgeRepayOrder uro =  urgeRepayOrderService.findByBorrowId(list.get(i).getBorrowId());
								if (StringUtil.isBlank(uro)) {
									urgeRepayOrderService.saveOrder(list.get(i).getBorrowId());
									clSmsService.overdue(list.get(i).getBorrowId());//逾期第一天发送短信通知
								}else {
									Map<String,Object> uroMap = new HashMap<>();
									uroMap.put("penaltyAmout", br.getPenaltyAmout());
									uroMap.put("penaltyDay", br.getPenaltyDay());
									uroMap.put("id", uro.getId());
									uroMap.put("repayTime", br.getRepayTime());
									uroMap.put("createTime", DateUtil.getNow());
									if(uro.getUserId() != null) {
										uroMap.put("state", UrgeRepayOrderModel.STATE_ORDER_WAIT);
									} else {
										uroMap.put("state", UrgeRepayOrderModel.STATE_ORDER_PRE);
									}
									if (day >= badDebtDay) {
										//修改催款计划
										uroMap.put("state", UrgeRepayOrderModel.STATE_ORDER_BAD);
										//添加借款进度
										clBorrowService.savePressState(borrow, BorrowModel.STATE_BAD,"");
										//修改借款信息
										data = new HashMap<>();
										data.put("id", list.get(i).getBorrowId());
										data.put("state",BorrowModel.STATE_BAD);
										msg = clBorrowService.updateSelective(data);
									}
									msg = urgeRepayOrderService.updateLate(uroMap);
								}
								logger.debug("---------修改催收计划end-------");
							}else {
								logger.error("定时计算逾期任务修改数据失败");
							}
						}
					}
					succeed++;
					total++;
				} catch (Exception e) {
					fail ++;
					total++;
					logger.error(e.getMessage(),e);
				}
			}
		}
			
		logger.info("逾期计算结束...");
		quartzRemark = "执行总次数"+total+",成功"+succeed+"次,失败"+fail+"次";
		return quartzRemark;
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		QuartzInfoService quartzInfoService = (QuartzInfoService)BeanUtil.getBean("quartzInfoService");
		QuartzLogService quartzLogService = (QuartzLogService)BeanUtil.getBean("quartzLogService");
		QuartzLog ql = new QuartzLog();
		Map<String,Object> qiData = new HashMap<>();
		QuartzInfo qi = quartzInfoService.findByCode("doLate");
		try {
			qiData.put("id", qi.getId());
			ql.setQuartzId(qi.getId());
			ql.setStartTime(DateUtil.getNow());
			
			String remark = late();
			
			ql.setTime(DateUtil.getNow().getTime()-ql.getStartTime().getTime());
			ql.setResult("10");
			ql.setRemark(remark);
			qiData.put("succeed", qi.getSucceed()+1);
			
		}catch (Exception e) {
			ql.setResult("20");
			qiData.put("fail", qi.getFail()+1);
			logger.error(e.getMessage(),e);
		}finally{
			logger.info("保存定时任务日志");
			quartzLogService.save(ql);
			quartzInfoService.update(qiData);
		}
	}
	
}

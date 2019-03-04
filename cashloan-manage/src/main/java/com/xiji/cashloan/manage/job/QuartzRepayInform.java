package com.xiji.cashloan.manage.job;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xiji.cashloan.manage.domain.QuartzInfo;
import com.xiji.cashloan.manage.domain.QuartzLog;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import tool.util.BeanUtil;
import tool.util.DateUtil;

import com.xiji.cashloan.cl.domain.BorrowRepay;
import com.xiji.cashloan.cl.model.BorrowRepayModel;
import com.xiji.cashloan.cl.service.BorrowRepayService;
import com.xiji.cashloan.cl.service.ClSmsService;
import com.xiji.cashloan.core.common.exception.ServiceException;
import com.xiji.cashloan.manage.service.QuartzInfoService;
import com.xiji.cashloan.manage.service.QuartzLogService;

/**
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 *
 */
@Component
@Lazy(value = false)
public class QuartzRepayInform implements Job{
	
	private static final Logger logger = Logger.getLogger(QuartzRepayInform.class);
	
	/**
	 * 还款提醒
	 * @throws ServiceException 
	 */
	public String repayInform() throws ServiceException {
		BorrowRepayService borrowRepayService = (BorrowRepayService)BeanUtil.getBean("borrowRepayService");
		ClSmsService clSmsService = (ClSmsService)BeanUtil.getBean("clSmsService");
		
		logger.info("进入还款日前0~1天计算...");
		String quartzRemark = null;
		int succeed = 0;
		int fail = 0;
		int total = 0;
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("state",BorrowRepayModel.STATE_REPAY_NO);
		List<BorrowRepay> list = borrowRepayService.listSelective(paramMap);
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				try {
					long day = DateUtil.daysBetween(new Date(), list.get(i).getRepayTime());
					if (day==1 || day==0) {//还款前发送短信通知
						clSmsService.repayBefore(list.get(i).getUserId(), list.get(i).getBorrowId());
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
		logger.info("还款日前0~2天计算结束");
		quartzRemark = "执行总次数"+total+",成功"+succeed+"次,失败"+fail+"次";
		return quartzRemark;
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		QuartzInfoService quartzInfoService = (QuartzInfoService)BeanUtil.getBean("quartzInfoService");
		QuartzLogService quartzLogService = (QuartzLogService)BeanUtil.getBean("quartzLogService");
		QuartzLog ql = new QuartzLog();
		Map<String,Object> qiData = new HashMap<>();
		QuartzInfo qi = quartzInfoService.findByCode("doRepayInform");
		try {
			qiData.put("id", qi.getId());
			ql.setQuartzId(qi.getId());
			ql.setStartTime(DateUtil.getNow());
			
			String remark = repayInform();
			
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
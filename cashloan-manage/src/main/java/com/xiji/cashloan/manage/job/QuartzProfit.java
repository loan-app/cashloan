package com.xiji.cashloan.manage.job;

import com.xiji.cashloan.cl.domain.BankCard;
import com.xiji.cashloan.cl.domain.PayLog;
import com.xiji.cashloan.cl.domain.ProfitAmount;
import com.xiji.cashloan.cl.model.PayLogModel;
import com.xiji.cashloan.cl.model.pay.common.PayCommonHelper;
import com.xiji.cashloan.cl.model.pay.common.PayCommonUtil;
import com.xiji.cashloan.cl.model.pay.common.vo.request.PaymentReqVo;
import com.xiji.cashloan.cl.model.pay.common.vo.response.PaymentResponseVo;
import com.xiji.cashloan.cl.monitor.BusinessExceptionMonitor;
import com.xiji.cashloan.cl.service.BankCardService;
import com.xiji.cashloan.cl.service.PayLogService;
import com.xiji.cashloan.cl.service.ProfitAmountService;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.exception.ServiceException;
import com.xiji.cashloan.core.domain.UserBaseInfo;
import com.xiji.cashloan.core.model.UserBaseInfoModel;
import com.xiji.cashloan.core.service.UserBaseInfoService;
import com.xiji.cashloan.manage.domain.QuartzInfo;
import com.xiji.cashloan.manage.domain.QuartzLog;
import com.xiji.cashloan.manage.service.QuartzInfoService;
import com.xiji.cashloan.manage.service.QuartzLogService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import tool.util.BeanUtil;
import tool.util.DateUtil;

/**
 * 自动奖励发放
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Component
@Lazy(value = false)
public class QuartzProfit implements Job {

	private static final Logger logger = Logger.getLogger(QuartzProfit.class);

	/**
	 * 自动奖励发放
	 * 
	 * @throws ServiceException
	 */
	public String profit() {
		ProfitAmountService profitAmountService = (ProfitAmountService) BeanUtil.getBean("profitAmountService");
		BankCardService bankCardService = (BankCardService) BeanUtil.getBean("bankCardService");
		UserBaseInfoService userBaseInfoService = (UserBaseInfoService) BeanUtil.getBean("userBaseInfoService");
		PayLogService payLogService = (PayLogService) BeanUtil.getBean("payLogService");


		List<ProfitAmount> profitAmountList = profitAmountService.listNoCash();

		String quartzRemark = null;
		int succeed = 0;
		int fail = 0;
		int total = 0;
		for (ProfitAmount profitAmount : profitAmountList) {
			try {
				double amountGrantMin = Global.getDouble("amount_grant_min");//奖金发放下限
				//奖金达到指定额度才给予发放
				if (profitAmount.getNoCashed()>=amountGrantMin) {
					BankCard bankCard = bankCardService.getBankCardByUserId(profitAmount.getUserId());
					UserBaseInfo baseInfo = userBaseInfoService.findByUserId(profitAmount.getUserId());
					if(baseInfo != null && UserBaseInfoModel.USER_STATE_BLACK.equals(baseInfo.getState())){
						continue;
					}
					if (PayCommonHelper.isEmpty(bankCard)) {
						logger.error("绑卡信息异常，请联系客户重新绑卡！userId:" +baseInfo.getUserId());
						continue;
					}
					Date date = DateUtil.getNow();

					PaymentReqVo vo = new PaymentReqVo();
					if ("dev".equals(Global.getValue("app_environment"))) {
						vo.setAmount(3.0);
					} else {
						vo.setAmount(profitAmount.getNoCashed());
					}
					vo.setBankCardName(baseInfo.getRealName());
					vo.setBankCardNo(bankCard.getCardNo());
					vo.setBorrowOrderNo(profitAmount.getId()+"");
					vo.setMobile(bankCard.getPhone());
					vo.setShareKey(bankCard.getUserId());
					PaymentResponseVo result = PayCommonUtil.payment(vo);

					PayLog payLog = new PayLog();
					payLog.setOrderNo(result.getOrderNo());
					payLog.setUserId(profitAmount.getUserId());
					payLog.setAmount(profitAmount.getNoCashed());
					payLog.setCardNo(bankCard.getCardNo());
					payLog.setBank(bankCard.getBank());
					payLog.setSource(PayLogModel.SOURCE_FUNDS_OWN);
					payLog.setType(PayLogModel.TYPE_PAYMENT);
					payLog.setScenes(PayLogModel.SCENES_PROFIT);
					if (PayCommonUtil.success(result.getStatus())) { //受理成功
						payLog.setState(PayLogModel.STATE_PAYMENT_WAIT);
					} else if (PayCommonUtil.needCheck(result.getStatus())) { // 疑似重复订单，待人工审核
						payLog.setState(PayLogModel.STATE_PENDING_REVIEW);
//					payLog.setConfirmCode(payment.getConfirm_code());
						payLog.setUpdateTime(com.xiji.cashloan.core.common.util.DateUtil.getNow());
					} else {
						BusinessExceptionMonitor.add(BusinessExceptionMonitor.TYPE_11, payLog.getOrderNo(), result.getMessage());
						payLog.setState(PayLogModel.STATE_PAYMENT_FAILED);
						payLog.setUpdateTime(com.xiji.cashloan.core.common.util.DateUtil.getNow());
					}

					payLog.setCode(result.getStatusCode());
					payLog.setRemark(result.getMessage());
					payLog.setPayReqTime(date);
					payLog.setCreateTime(DateUtil.getNow());
					payLogService.save(payLog);
				}
				succeed++;
				total++;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				fail++;
				total++;
			}
		}
		quartzRemark = "执行总次数"+total+",成功"+succeed+"次,失败"+fail+"次";
		return quartzRemark;

	}

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		QuartzInfoService quartzInfoService = (QuartzInfoService) BeanUtil.getBean("quartzInfoService");
		QuartzLogService quartzLogService = (QuartzLogService) BeanUtil.getBean("quartzLogService");
		// 查询当前任务信息
		QuartzInfo quartzInfo = quartzInfoService.findByCode("doProfit");
		QuartzLog quartzLog = new QuartzLog();
		Map<String, Object> qiData = new HashMap<String, Object>();
		qiData.put("id", quartzInfo.getId());
		quartzLog.setQuartzId(quartzInfo.getId());
		quartzLog.setStartTime(DateUtil.getNow());

		try {

			String remark = profit();

			quartzLog.setTime(DateUtil.getNow().getTime() - quartzLog.getStartTime().getTime());
			quartzLog.setResult("10");
			quartzLog.setRemark(remark);
			qiData.put("succeed", quartzInfo.getSucceed() + 1);
			logger.info("自动扣款完成...");
		} catch (Exception e) {
			quartzLog.setResult("20");
			qiData.put("fail", quartzInfo.getFail() + 1);
			logger.error(e.getMessage(), e);
		} finally {
			logger.info("保存定时任务日志");
			quartzLogService.save(quartzLog);
			quartzInfoService.update(qiData);
		}

	}
}
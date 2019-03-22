package com.xiji.cashloan.api.controller.assist;

import com.xiji.cashloan.cl.domain.BorrowRepay;
import com.xiji.cashloan.cl.domain.BorrowRepayLog;
import com.xiji.cashloan.cl.domain.PayLog;
import com.xiji.cashloan.cl.model.BorrowRepayLogModel;
import com.xiji.cashloan.cl.model.BorrowRepayModel;
import com.xiji.cashloan.cl.model.PayLogModel;
import com.xiji.cashloan.cl.model.pay.common.constant.PayConstant;
import com.xiji.cashloan.cl.model.pay.common.dto.RepaymentNotifyDto;
import com.xiji.cashloan.cl.service.BorrowRepayLogService;
import com.xiji.cashloan.cl.service.BorrowRepayService;
import com.xiji.cashloan.cl.service.ClSmsService;
import com.xiji.cashloan.cl.service.PayLogService;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.util.StringUtil;
import com.xiji.cashloan.core.model.BorrowModel;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tool.util.DateUtil;

/**
 * @Auther: king
 * @Date: 2019/1/28 14:12
 * @Description:
 */
@Service
public class RepaymentNotifyAssist {
    private static final Logger logger = LoggerFactory.getLogger(RepaymentNotifyAssist.class);
    @Resource
    private PayLogService payLogService;
    @Resource
    private BorrowRepayService borrowRepayService;
    @Resource
    private BorrowRepayLogService borrowRepayLogService;
    @Resource
    private ClSmsService clSmsService;

    public String doScenesRepayment(RepaymentNotifyDto model,PayLog payLog)throws Exception {
        logger.info("分期付 (还款)- 异步通知：-支付状态是：" + model.getMessage());
        if (PayLogModel.STATE_PAYMENT_WAIT.equals(payLog.getState())
            || PayLogModel.STATE_PENDING_REVIEW.equals(payLog.getState())) {

            // 分期付扣款成功，更新借款状态及支付订单 ，否则只更新订单状态
            if (StringUtil.equals(model.getStatus(), PayConstant.RESULT_SUCCESS)) {

                // 查找对应的还款计划
                Map<String, Object> repayMap = new HashMap<String, Object>();
                repayMap.put("userId", payLog.getUserId());
                repayMap.put("borrowId", payLog.getBorrowId());
                repayMap.put("state", BorrowRepayModel.STATE_REPAY_NO);
                BorrowRepay borrowRepay = borrowRepayService.findSelective(repayMap);

                if (borrowRepay != null) {
                    Map<String, Object> param = new HashMap<String, Object>();
                    param.put("id", borrowRepay.getId());
                    //param.put("repayTime", DateUtil.dateStr4(DateUtil.getNow()));
                    param.put("repayTime", DateUtil.getNow());
                    param.put("repayWay", BorrowRepayLogModel.REPAY_WAY_CHARGE);
                    param.put("repayAccount", model.getCardNo());
                    param.put("amount", payLog.getAmount());
                    param.put("serialNumber", payLog.getOrderNo());
                    param.put("penaltyAmout", borrowRepay.getPenaltyAmout());
                    param.put("state", "10");
                    if (!borrowRepay.getState().equals(BorrowRepayModel.STATE_REPAY_YES)) {
                        borrowRepayService.confirmRepay(param);
                    }
                }

                // 更新订单状态
                Map<String,Object> paramMap = new HashMap<String, Object>();
                paramMap.put("state", PayLogModel.STATE_PAYMENT_SUCCESS);
                paramMap.put("updateTime",DateUtil.getNow());
                paramMap.put("id",payLog.getId());
                payLogService.updateSelective(paramMap);
                //发送扣款成功短信
                clSmsService.repayInform(borrowRepay.getUserId(), borrowRepay.getBorrowId());

            } else {
                Map<String,Object> paramMap = new HashMap<String, Object>();
                paramMap.put("state", PayLogModel.STATE_PAYMENT_FAILED);
                paramMap.put("updateTime",DateUtil.getNow());
                paramMap.put("id",payLog.getId());
                payLogService.updateSelective(paramMap);
            }
        }else{
            logger.info("订单"+ payLog.getOrderNo() +"已处理");
        }
        return  PayConstant.RESULT_SUCCESS;
    }

    public String doScenesDeduction(RepaymentNotifyDto model,PayLog payLog)throws Exception {
        logger.info("分期付 (补扣)- 异步通知：-支付状态是：" + model.getMessage());
        if (PayLogModel.STATE_PAYMENT_WAIT.equals(payLog.getState())
            || PayLogModel.STATE_PENDING_REVIEW.equals(payLog.getState())) {

            // 分期付扣款成功，更新借款状态及支付订单 ，否则只更新订单状态
            if (StringUtil.equals(model.getStatus(), PayConstant.RESULT_SUCCESS)) {
                // 查询还款记录
                Map<String, Object> repayLogMap =  new HashMap<String, Object>();
                repayLogMap.put("borrowId", payLog.getBorrowId());
                repayLogMap.put("userId", payLog.getUserId());
                BorrowRepayLog repayLog = borrowRepayLogService.findSelective(repayLogMap);

                // 更新还款记录
                Map<String, Object> refundDeductionMap = new HashMap<String, Object>();
                refundDeductionMap.put("id", repayLog.getId());
                refundDeductionMap.put("refundDeduction", payLog.getAmount());
                refundDeductionMap.put("payTime", DateUtil.getNow());
                borrowRepayLogService.refundDeduction(refundDeductionMap);

                // 更新订单状态
                Map<String,Object> paramMap = new HashMap<String, Object>();
                paramMap.put("state", PayLogModel.STATE_PAYMENT_SUCCESS);
                paramMap.put("updateTime",DateUtil.getNow());
                paramMap.put("id",payLog.getId());
                payLogService.updateSelective(paramMap);
                //发送扣款成功短信
                clSmsService.repayInform(payLog.getUserId(), payLog.getBorrowId());
            } else {
                Map<String,Object> paramMap = new HashMap<String, Object>();
                paramMap.put("state", PayLogModel.STATE_PAYMENT_FAILED);
                paramMap.put("updateTime",DateUtil.getNow());
                paramMap.put("id",payLog.getId());
                payLogService.updateSelective(paramMap);
            }
        }else{
            logger.info("订单" + payLog.getOrderNo() + "已处理");
        }
        return  PayConstant.RESULT_SUCCESS;
    }

    public String doScenesActiveRepayment(RepaymentNotifyDto model,PayLog payLog)throws Exception {
        logger.info("主动扣款 - 异步通知：-支付状态是：" + model.getMessage());
        if (PayLogModel.STATE_PAYMENT_WAIT.equals(payLog.getState())
            || PayLogModel.STATE_PENDING_REVIEW.equals(payLog.getState())) {

            //付扣款成功，更新借款状态及支付订单 ，否则只更新订单状态
            // 分期付扣款成功，更新借款状态及支付订单 ，否则只更新订单状态
            if (StringUtil.equals(model.getStatus(), PayConstant.RESULT_SUCCESS)) {

                // 查找对应的还款计划
                Map<String, Object> repayMap = new HashMap<String, Object>();
                repayMap.put("userId", payLog.getUserId());
                repayMap.put("borrowId", payLog.getBorrowId());
                repayMap.put("state", BorrowRepayModel.STATE_REPAY_NO);
                BorrowRepay borrowRepay = borrowRepayService.findSelective(repayMap);

                if (borrowRepay != null) {
                    Map<String, Object> param = new HashMap<String, Object>();
                    param.put("id", borrowRepay.getId());
                    //param.put("repayTime", DateUtil.dateStr4(DateUtil.getNow()));
                    param.put("repayTime", DateUtil.getNow());
                    param.put("repayWay", BorrowRepayLogModel.REPAY_WAY_CHARGE);
                    param.put("repayAccount", model.getCardNo());
                    param.put("amount", payLog.getAmount());
                    param.put("serialNumber", payLog.getOrderNo());
                    param.put("penaltyAmout", borrowRepay.getPenaltyAmout());
                    param.put("state", "10");
                    if (!borrowRepay.getState().equals(BorrowRepayModel.STATE_REPAY_YES)) {
                        borrowRepayService.confirmRepay(param);
                    }
                }

                // 更新订单状态
                Map<String,Object> paramMap = new HashMap<String, Object>();
                paramMap.put("state", PayLogModel.STATE_PAYMENT_SUCCESS);
                paramMap.put("updateTime",DateUtil.getNow());
                paramMap.put("id",payLog.getId());
                payLogService.updateSelective(paramMap);
                //发送扣款成功短信
                clSmsService.repayInform(borrowRepay.getUserId(), borrowRepay.getBorrowId());

            } else {
                Map<String,Object> paramMap = new HashMap<String, Object>();
                paramMap.put("state", PayLogModel.STATE_PAYMENT_FAILED);
                paramMap.put("updateTime",DateUtil.getNow());
                paramMap.put("id",payLog.getId());
                payLogService.updateSelective(paramMap);
            }
        }else{
            logger.info("订单" + payLog.getOrderNo() + "已处理");
        }
        return  PayConstant.RESULT_SUCCESS;
    }

    /**
     * 主动展期，
     * 展期和主动代扣不一样，扣款的金额不一样，扣款金额为服务费
     * @param model
     * @param payLog
     * @throws Exception
     */
    public String doScenesActiveDelay(RepaymentNotifyDto model,PayLog payLog)throws Exception {
        logger.info("主动展期 - 异步通知：-支付状态是：" + model.getMessage());
        if (PayLogModel.STATE_PAYMENT_WAIT.equals(payLog.getState())
            || PayLogModel.STATE_PENDING_REVIEW.equals(payLog.getState())) {

            // 分期付扣款成功，更新借款状态及支付订单 ，否则只更新订单状态
            if (StringUtil.equals(model.getStatus(), PayConstant.RESULT_SUCCESS)) {
                // 查找对应的还款计划
                Map<String, Object> repayMap = new HashMap<String, Object>();
                repayMap.put("userId", payLog.getUserId());
                repayMap.put("borrowId", payLog.getBorrowId());
                repayMap.put("state", BorrowRepayModel.STATE_REPAY_NO);
                BorrowRepay borrowRepay = borrowRepayService.findSelective(repayMap);
                Date repayTime = null;
                if (borrowRepay != null) {
                    Map<String, Object> param = new HashMap<String, Object>();
                    param.put("id", borrowRepay.getId());
                    param.put("state", BorrowModel.STATE_DELAY_PAY);
                    param.put("amount", String.valueOf(payLog.getAmount()));
                    param.put("repayWay", BorrowRepayLogModel.REPAY_WAY_CHARGE);
//                    param.put("repayAccount", bankCard.getCardNo());
                    param.put("serialNumber", payLog.getOrderNo());
                    if(StringUtil.isNotBlank(Global.getValue("delay_days"))) {
                        param.put("delayDays", Global.getValue("delay_days"));
                    }
                    if (!borrowRepay.getState().equals(BorrowRepayModel.STATE_REPAY_YES)) {
                        Map<String, Object> delayPayMap = borrowRepayService.confirmDelayPay(param);
                        if (delayPayMap != null) {
                            repayTime = (Date) delayPayMap.get("repayTime");
                        }
                    }
                }
                // 更新订单状态
                Map<String,Object> paramMap = new HashMap<String, Object>();
                paramMap.put("state", PayLogModel.STATE_PAYMENT_SUCCESS);
                paramMap.put("updateTime",DateUtil.getNow());
                paramMap.put("id",payLog.getId());
                payLogService.updateSelective(paramMap);
                //发送展期成功短信
                clSmsService.delayPlan(payLog.getUserId(), payLog.getBorrowId(),repayTime);
            } else {
                Map<String,Object> paramMap = new HashMap<String, Object>();
                paramMap.put("state", PayLogModel.STATE_PAYMENT_FAILED);
                paramMap.put("updateTime",DateUtil.getNow());
                paramMap.put("id",payLog.getId());
                payLogService.updateSelective(paramMap);
            }
        }else{
            logger.info("订单" + payLog.getOrderNo() + "已处理");
        }
        return  PayConstant.RESULT_SUCCESS;
    }
}

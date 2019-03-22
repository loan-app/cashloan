package com.xiji.cashloan.api.controller.assist;

import com.xiji.cashloan.api.user.service.ContractService;
import com.xiji.cashloan.cl.domain.BorrowProgress;
import com.xiji.cashloan.cl.domain.BorrowRepayLog;
import com.xiji.cashloan.cl.domain.PayLog;
import com.xiji.cashloan.cl.model.PayLogModel;
import com.xiji.cashloan.cl.model.pay.common.constant.PayConstant;
import com.xiji.cashloan.cl.model.pay.common.dto.RepaymentNotifyDto;
import com.xiji.cashloan.cl.service.BorrowProgressService;
import com.xiji.cashloan.cl.service.BorrowRepayLogService;
import com.xiji.cashloan.cl.service.BorrowRepayService;
import com.xiji.cashloan.cl.service.ClBorrowService;
import com.xiji.cashloan.cl.service.ClSmsService;
import com.xiji.cashloan.cl.service.PayLogService;
import com.xiji.cashloan.cl.service.ProfitAmountService;
import com.xiji.cashloan.core.common.util.StringUtil;
import com.xiji.cashloan.core.domain.Borrow;
import com.xiji.cashloan.core.model.BorrowModel;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tool.util.DateUtil;

/**
 * @Auther: king
 * @Date: 2019/1/28 14:49
 * @Description:
 */
@Service
public class PaymentNotifyAssist {

    private static final Logger logger = LoggerFactory.getLogger(PaymentNotifyAssist.class);

    @Resource
    private PayLogService payLogService;
    @Resource
    private ClBorrowService clBorrowService;
    @Resource
    private BorrowProgressService borrowProgressService;
    @Resource
    private BorrowRepayService borrowRepayService;
    @Resource
    private BorrowRepayLogService borrowRepayLogService;
    @Resource
    private ProfitAmountService profitAmountService;
    @Resource
    private ContractService contractService;
    @Resource
    private ClSmsService clSmsService;
    /**
     * 代付，放款异步通知处理
     * @param model
     * @param payLog
     * @return
     * @throws Exception
     */
    public String doScenesLoans(RepaymentNotifyDto model,PayLog payLog)throws Exception {
        boolean ifPaySuccess = StringUtil.equals(model.getStatus(), PayConstant.RESULT_SUCCESS);
        logger.info("实时付款(放款) - 异步通知-支付状态是：" +ifPaySuccess);
        if (PayLogModel.STATE_PAYMENT_WAIT.equals(payLog.getState())
            || PayLogModel.STATE_AUDIT_PASSED.equals(payLog.getState())|| PayLogModel.STATE_PENDING_REVIEW.equals(payLog.getState())) {

            // 代付成功，更新借款状态及支付订单 ，否则只更新订单状态
            if (ifPaySuccess) {
                // 修改借款状态
                Map<String, Object> map = new HashMap<>();
                map.put("id", payLog.getBorrowId());
                map.put("state", BorrowModel.STATE_REPAY);
                clBorrowService.updatePayState(map);

                // 放款进度添加
                BorrowProgress bp = new BorrowProgress();
                bp.setUserId(payLog.getUserId());
                bp.setBorrowId(payLog.getBorrowId());
                bp.setState(BorrowModel.STATE_REPAY);
                bp.setRemark(BorrowModel.convertBorrowRemark(bp.getState()));
                bp.setCreateTime(DateUtil.getNow());
                borrowProgressService.save(bp);

                final Borrow borrow = clBorrowService.getById(payLog.getBorrowId());

                // 生成还款计划并授权
                borrowRepayService.genRepayPlan(borrow);
                // 更新订单状态
                Map<String,Object> paramMap = new HashMap<String, Object>();
                paramMap.put("state", PayLogModel.STATE_PAYMENT_SUCCESS);
                paramMap.put("updateTime",DateUtil.getNow());
                paramMap.put("payOrderNo",model.getPayPlatNo());
                paramMap.put("id",payLog.getId());
                payLogService.updateSelective(paramMap);
                //发送放款成功短信
                clSmsService.loanInform(payLog.getUserId(), payLog.getBorrowId());
                // 生成pdf合同文件
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        contractService.buildPdf(borrow.getId().toString());
                    }
                }).start();

            }else{
                Map<String, Object> borrowMap = new HashMap<String, Object>();
                borrowMap.put("id", payLog.getBorrowId());
                borrowMap.put("state", BorrowModel.STATE_REPAY_FAIL);
                clBorrowService.updatePayState(borrowMap);

                Map<String,Object> paramMap = new HashMap<String, Object>();
                paramMap.put("state", PayLogModel.STATE_PAYMENT_FAILED);
                paramMap.put("updateTime",DateUtil.getNow());
//				paramMap.put("remark",model.getInfo_order());
                paramMap.put("id",payLog.getId());
                payLogService.updateSelective(paramMap);

            }
        }else{
            logger.info("订单" + payLog.getOrderNo() + "已处理");
        }
        return  PayConstant.RESULT_SUCCESS;
    }
    /**
     * 取现（分润），放款异步通知处理
     * @param model
     * @param payLog
     * @throws Exception
     */
    public String doScenesProfit(RepaymentNotifyDto model,PayLog payLog)throws Exception {
        boolean ifPaySuccess = StringUtil.equals(model.getStatus(), PayConstant.RESULT_SUCCESS);
        logger.info("实时付款(取现) - 异步通知-支付状态是：" +ifPaySuccess);

        if (PayLogModel.STATE_PAYMENT_WAIT.equals(payLog.getState())
            || PayLogModel.STATE_AUDIT_PASSED.equals(payLog.getState())|| PayLogModel.STATE_PENDING_REVIEW.equals(payLog.getState())) {

            // 代付成功，更新借款状态及支付订单 ，否则只更新订单状态
            if (ifPaySuccess) {
                // 更新取现金额， 添加取现记录
                profitAmountService.cash(payLog.getUserId(), payLog.getAmount());

                // 更新订单状态
                Map<String,Object> paramMap = new HashMap<String, Object>();
                paramMap.put("state", PayLogModel.STATE_PAYMENT_SUCCESS);
                paramMap.put("updateTime",DateUtil.getNow());
                paramMap.put("payOrderNo",model.getPayPlatNo());
                paramMap.put("id",payLog.getId());
                payLogService.updateSelective(paramMap);

            }else{
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
     * 退还，异步通知处理
     * @param model
     * @param payLog
     * @throws Exception
     */
    public String doScenesRefund(RepaymentNotifyDto model, PayLog payLog)throws Exception {
        boolean ifPaySuccess = StringUtil.equals(model.getStatus(), PayConstant.RESULT_SUCCESS);
        logger.info("实时付款(退还) - 异步通知-支付状态是：" +ifPaySuccess);
        if (PayLogModel.STATE_PAYMENT_WAIT.equals(payLog.getState())
            || PayLogModel.STATE_AUDIT_PASSED.equals(payLog.getState())|| PayLogModel.STATE_PENDING_REVIEW.equals(payLog.getState())) {

            //代付成功 ，否则只更新订单状态
            if (ifPaySuccess){
                // 查询还款记录
                Map<String, Object> repayLogMap =  new HashMap<String, Object>();
                repayLogMap.put("borrowId", payLog.getBorrowId());
                repayLogMap.put("userId", payLog.getUserId());
                BorrowRepayLog repayLog = borrowRepayLogService.findSelective(repayLogMap);

                // 更新还款记录
                Map<String, Object> refundDeductionMap = new HashMap<String, Object>();
                refundDeductionMap.put("id", repayLog.getId());
                refundDeductionMap.put("refundDeduction", - payLog.getAmount());
                refundDeductionMap.put("payTime", DateUtil.getNow());
                borrowRepayLogService.refundDeduction(refundDeductionMap);

                // 更新订单状态
                Map<String,Object> paramMap = new HashMap<String, Object>();
                paramMap.put("state", PayLogModel.STATE_PAYMENT_SUCCESS);
                paramMap.put("updateTime",DateUtil.getNow());
                paramMap.put("payOrderNo",model.getPayPlatNo());
                paramMap.put("id",payLog.getId());
                payLogService.updateSelective(paramMap);
            }else {
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

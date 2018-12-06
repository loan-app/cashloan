package com.xiji.cashloan.cl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xiji.cashloan.cl.domain.PayLog;
import com.xiji.cashloan.cl.model.PayLogModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import tool.util.DateUtil;
import tool.util.StringUtil;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiji.cashloan.cl.mapper.ClBorrowMapper;
import com.xiji.cashloan.cl.mapper.PayLogMapper;
import com.xiji.cashloan.cl.model.ManagePayLogModel;
import com.xiji.cashloan.cl.model.pay.lianlian.ConfirmPaymentModel;
import com.xiji.cashloan.cl.model.pay.lianlian.util.LianLianHelper;
import com.xiji.cashloan.cl.service.PayLogService;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.core.common.util.JsonUtil;
import com.xiji.cashloan.core.common.util.OrderNoUtil;
import com.xiji.cashloan.core.domain.Borrow;
import com.xiji.cashloan.core.model.BorrowModel;


/**
 * 支付记录ServiceImpl
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("payLogService")
public class PayLogServiceImpl extends BaseServiceImpl<PayLog, Long> implements PayLogService {
	
	private static final Logger logger = LoggerFactory.getLogger(PayLogServiceImpl.class);
	
	@Resource
	private PayLogMapper payLogMapper;
	@Resource
	private ClBorrowMapper clBorrowMapper;

	@Override
	public BaseMapper<PayLog, Long> getMapper() {
		return payLogMapper;
	}
	
	@Override
	public boolean save(PayLog payLog) {
		payLog.setCreateTime(DateUtil.getNow());
		int result = payLogMapper.save(payLog);
		if (result > 0L) {
			return true;
		}
		return false;
	}

	@Override
	public Page<ManagePayLogModel> page(int current, int pageSize,
			Map<String, Object> searchMap) {
		PageHelper.startPage(current, pageSize);
		Page<ManagePayLogModel> page = (Page<ManagePayLogModel>) payLogMapper
				.page(searchMap);
		return page;
	}

	@Override
	public ManagePayLogModel findDetail(Long id) {
		return payLogMapper.findDetail(id);
	}

	@Override
	public boolean auditPay(Long id, String state) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		paramMap.put("state", state);
		int result = payLogMapper.updateSelective(paramMap);

		if (PayLogModel.STATE_AUDIT_PASSED.equals(state)) {
			PayLog payLog = payLogMapper.findByPrimary(id);
			confirmPayment(payLog);
		}

		if (result > 0L) {
			return true;
		}
		return false;
	}
	
	/**
	 * 调用连连支付接口进行支付
	 * @param payLog
	 */
	private void confirmPayment(PayLog payLog){
		// 场景设定支付通知地址
		String notifyUrl = "";
		if (PayLogModel.SCENES_LOANS.equals(payLog.getScenes())) {
			notifyUrl = Global.getValue("server_host") + "/pay/lianlian/paymentNotify.htm";
		} else if (PayLogModel.SCENES_PROFIT.equals(payLog.getScenes())) {
			notifyUrl = Global.getValue("server_host") + "/pay/lianlian/profitNotify.htm";
		} else if (PayLogModel.SCENES_REFUND.equals(payLog.getScenes())) {
			notifyUrl = Global.getValue("server_host") + "/pay/lianlian/refundNotify.htm";
		}
		
		String orderNo = OrderNoUtil.getSerialNumber();
		ConfirmPaymentModel confirmPayment = new ConfirmPaymentModel(orderNo);
		confirmPayment.setNo_order(payLog.getOrderNo());
		confirmPayment.setConfirm_code(payLog.getConfirmCode());
		confirmPayment.setNotify_url(notifyUrl);
		LianLianHelper helper = new LianLianHelper();
		confirmPayment = (ConfirmPaymentModel) helper.confirmPayment(confirmPayment);
		
		if (confirmPayment.checkReturn()) {
			logger.info("确认支付 " + payLog.getOrderNo());
		} else {
			logger.error("确认付款异常,原因：" + confirmPayment.getRet_msg());
		}
	}

	@Override
	public Map<String, Object> checkPayLogState(Long id, String state) {
		PayLog log = payLogMapper.findByPrimary(id);

		Map<String, Object> check = new HashMap<String, Object>();
		if (!PayLogModel.STATE_PENDING_REVIEW.equals(log.getState())) {
			check.put(Constant.RESPONSE_CODE_MSG, "当前交易记录状态不允许审核！");
		}

		// 若借款不是审核通过或放款失败 则不允许审核通过
		if (PayLogModel.SCENES_LOANS.equals(log.getScenes()) 
				&& PayLogModel.STATE_AUDIT_PASSED.equals(state)) {

			Borrow borrow = clBorrowMapper.findByPrimary(log.getBorrowId());
			if (BorrowModel.STATE_AUTO_PASS.equals(borrow.getState())
					|| BorrowModel.STATE_PASS.equals(borrow.getState())
					|| BorrowModel.STATE_REPAY_FAIL.equals(borrow.getState())
					|| BorrowModel.AUDIT_LOAN_PASS.equals(borrow.getState())) {
			} else {
				check.put(Constant.RESPONSE_CODE_MSG, "当前借款状态不允许审核通过！");
			}
		}
		return check;
	}

	@Override
	public PayLog findByOrderNo(String orderNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orderNo", orderNo);
		return payLogMapper.findSelective(paramMap);
	}
	
	@Override
	public boolean updateSelective(Map<String, Object> paramMap){
		int result  = payLogMapper.updateSelective(paramMap);
		if(result > 0L){
			return true;
		}
		return false;
	}

	@Override
	public PayLog findSelective(Map<String, Object> paramMap) {
		return payLogMapper.findSelective(paramMap);
	}
	
	@Override
	public PayLog findLatestOne(Map<String, Object> paramMap) {
		return payLogMapper.findLatestOne(paramMap);
	}
	
	@Override
	public List<PayLog> findCheckList(Map<String, Object> paramMap){
		return payLogMapper.findCheckList(paramMap);
	}

	@Override
	public boolean judge(long borrowId) {
		Map<String,Object> map = new HashMap<>();
		map.put("borrowId", borrowId);
		map.put("scenes", PayLogModel.SCENES_LOANS);
        List<PayLog> plist = payLogMapper.listSelective(map);
        boolean flag = true;
        for (PayLog payLog : plist) {
            if (PayLogModel.STATE_PAYMENT_WAIT.equals(payLog.getState())
                    || PayLogModel.STATE_PENDING_REVIEW.equals(payLog.getState())) {
                flag = false;
                break;
            }
        }
		return flag;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List listPayLog(String params) {
		Map<String, Object> searchMap = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(params)) {
			searchMap = JsonUtil.parse(params, Map.class);
		}
		String type  = StringUtil.isNull(searchMap.get("type"));
		String[] typeArray = type.split(",");
		
		List<String> typeList =  new ArrayList<String>();
		for (String typeStr : typeArray) {
			if (StringUtil.isNotBlank(typeStr)) {
				typeList.add(typeStr);
			}
		}
		searchMap.put("type", typeList);
		List<ManagePayLogModel> list = payLogMapper.page(searchMap);
		return list;
	}

	@Override
	public int doRepaymentNum(long borrowId) {
		return payLogMapper.doRepaymentCount(borrowId);
	}
	
	/**
	 * 保存支付记录
	 * 
	 * @param orderNo
	 * @param userId
	 * @param borrowId
	 * @param amount
	 * @param cardNo
	 * @param bank
	 * @param type
	 * @return
	 */
	@Override
	public int savePayLog(String orderNo, Long userId, Long borrowId, double amount, String cardNo, String bank, String scenes) {
		PayLog payLog = new PayLog();
		payLog.setOrderNo(orderNo);
		payLog.setUserId(userId);
		payLog.setBorrowId(borrowId);
		payLog.setAmount(amount);
		payLog.setCardNo(cardNo);
		payLog.setBank(bank);
		payLog.setSource(PayLogModel.SOURCE_FUNDS_OWN);
		payLog.setType(PayLogModel.TYPE_AUTH_PAY);
		payLog.setScenes(scenes);
		payLog.setState(PayLogModel.STATE_PAYMENT_WAIT);
		payLog.setPayReqTime(DateUtil.getNow());
		payLog.setCreateTime(DateUtil.getNow());
		int result = payLogMapper.save(payLog);
		return result;
	}

	@Override
	public List<PayLog> listSelective(Map<String, Object> pmap) {
		return payLogMapper.listSelective(pmap);
	}
}

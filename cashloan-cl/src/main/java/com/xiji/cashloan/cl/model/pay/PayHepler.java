package com.xiji.cashloan.cl.model.pay;

import com.xiji.cashloan.cl.model.pay.lianlian.AuthApplyModel;
import com.xiji.cashloan.cl.model.pay.lianlian.AuthSignModel;
import com.xiji.cashloan.cl.model.pay.lianlian.BasePaymentModel;
import com.xiji.cashloan.cl.model.pay.lianlian.CancelAuthSignModel;
import com.xiji.cashloan.cl.model.pay.lianlian.ConfirmPaymentModel;
import com.xiji.cashloan.cl.model.pay.lianlian.PaymentModel;
import com.xiji.cashloan.cl.model.pay.lianlian.QueryAuthSignModel;
import com.xiji.cashloan.cl.model.pay.lianlian.QueryPaymentModel;
import com.xiji.cashloan.cl.model.pay.lianlian.QueryRepaymentModel;
import com.xiji.cashloan.cl.model.pay.lianlian.RepaymentModel;

/**
 * 支付接口
 * 
 * @author gc
 * @version 1.0.0
 * @date 2017年4月19日 下午4:09:30 
 *
 * 官方网站：www.xiji.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface PayHepler {

	/**
	 * 签约
	 * 
	 * @param model
	 * @return
	 */
	public BasePaymentModel authSign(AuthSignModel model);

	/**
	 * 授权
	 * 
	 * @param paramMap
	 * @return
	 */
	public BasePaymentModel authApply(AuthApplyModel model);

	/**
	 * 取消签约
	 * 
	 * @param model
	 * @return
	 */
	public BasePaymentModel cancelAuthSign(CancelAuthSignModel model);

	/**
	 * 付款
	 * 
	 * @param model
	 * @return
	 */
	public BasePaymentModel payment(PaymentModel model);

	/**
	 * 确认付款
	 * 
	 * @param model
	 * @return
	 */
	public BasePaymentModel confirmPayment(ConfirmPaymentModel model);

	/**
	 * 扣款还款
	 * 
	 * @param paramMap
	 * @return
	 */
	public BasePaymentModel repayment(RepaymentModel model);
	
	/**
	 * 查询签约结果
	 * 
	 * @param model
	 * @return
	 */
	public BasePaymentModel queryAuthSign(QueryAuthSignModel model);

	
	/**
	 * 查询付款交易
	 * 
	 * @param model
	 * @return
	 */
	public BasePaymentModel queryPayment(QueryPaymentModel model);


	/**
	 * 扣款还款查询
	 * 
	 * @param model
	 * @return
	 */
	public BasePaymentModel queryRepayment(QueryRepaymentModel model);
	
	/**
	 * 对账
	 */
	
	
	
}

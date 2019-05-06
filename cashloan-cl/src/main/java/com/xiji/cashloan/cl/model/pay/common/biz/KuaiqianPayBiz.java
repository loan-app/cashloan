package com.xiji.cashloan.cl.model.pay.common.biz;

import com.xiji.cashloan.cl.model.pay.common.PayCommon;
import com.xiji.cashloan.cl.model.pay.common.constant.PayConstant;
import com.xiji.cashloan.cl.model.pay.common.vo.request.*;
import com.xiji.cashloan.cl.model.pay.common.vo.response.*;
import com.xiji.cashloan.cl.model.pay.kuaiqian.KuaiqianHelper;
import com.xiji.cashloan.cl.model.pay.kuaiqian.constant.KuaiqianConstant;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.paymock.vo.Pay2bankOrder;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.paymock.vo.Pay2bankOrderReturn;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.querymock.vo.Pay2bankSearchDetail;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.querymock.vo.Pay2bankSearchRequestParam;
import com.xiji.cashloan.cl.model.pay.kuaiqian.util.KuaiqianUtil;
import com.xiji.cashloan.core.common.context.Global;


/**
 * @auther : wnb
 * @date : 2019/4/30
 * @describe : 快钱支付实现类
 */
public class KuaiqianPayBiz implements PayCommon {

    @Override
    public PaymentResponseVo payment(PaymentReqVo vo) {

        KuaiqianHelper kuaiqianHelper = new KuaiqianHelper();
        Pay2bankOrder order = new Pay2bankOrder();
        order.setOrderId(KuaiqianUtil.getOrderId());
        // 无小数点，单位分
        order.setAmount((int)(vo.getAmount() *100) +"");
        order.setRemark(vo.getRemark());
        order.setBankAcctId(vo.getBankCardNo());
        order.setMobile(vo.getMobile());
        order.setBankName(vo.getBankName());
        order.setCreditName(vo.getBankCardName());
        if ("dev".equals(Global.getValue("app_environment"))) {
            order.setRemark("模拟交易失败");
        }
        PaymentResponseVo responseVo = new PaymentResponseVo();
        Pay2bankOrderReturn pay2bankOrderReturn = kuaiqianHelper.payment(order);
        if (KuaiqianConstant.RESPONSE_SUCCESS_CODE.equals(pay2bankOrderReturn.getErrorCode())){
            responseVo.setStatus(PayConstant.RESULT_SUCCESS);
            responseVo.setStatusCode(pay2bankOrderReturn.getErrorCode());
        }else if(KuaiqianConstant.RESPONSE_CHECK_FAIL.equals(pay2bankOrderReturn.getErrorCode())){
            responseVo.setStatus(PayConstant.STATUS_NEED_CHECK);
            responseVo.setStatusCode(pay2bankOrderReturn.getErrorCode());
        }else {
            responseVo.setStatus(PayConstant.STATUS_FAIL);
            responseVo.setStatusCode(pay2bankOrderReturn.getErrorCode());
        }
        responseVo.setMessage(pay2bankOrderReturn.getErrorMsg());
        responseVo.setOrderNo(order.getOrderId());
        return responseVo;
    }

    @Override
    public PaymentQueryResponseVo queryPayment(PaymentQueryVo vo) {
        Pay2bankSearchRequestParam order = new Pay2bankSearchRequestParam();
        order.setTargetPage(KuaiqianConstant.QUERY_TARGETPAGE);
        //每页条数  必填  1-20  正整数
        order.setPageSize(KuaiqianConstant.QUERY_PGAE_SIZE);
        //商家订单号
        order.setOrderId(vo.getOrderNo());
        //开始时间 必填
        order.setStartDate(KuaiqianUtil.getDate(7)); //2017-11-19 08:12:12
        //结束时间 必填  结束-开始<=7天
        order.setEndDate(KuaiqianUtil.getDate(1)); //2017-11-21 23:59:59

        KuaiqianHelper kuaiqianHelper = new KuaiqianHelper();
        Pay2bankSearchDetail result = kuaiqianHelper.queryPayment(order);
        PaymentQueryResponseVo responseVo = new PaymentQueryResponseVo();
        if (result != null) {
            if (KuaiqianConstant.RESPONSE_SUCCESS_CODE.equals(result.getErrorCode())) {
                responseVo.setStatus(PayConstant.RESULT_SUCCESS);
            }else {
                responseVo.setStatus(PayConstant.STATUS_PAYQUERY_NO_REQ);
            }
        }
        return responseVo;
    }

    @Override
    public BindCardMsgResponseVo bindMsg(BindCardMsgVo vo) {
        return null;
    }

    @Override
    public UnbindCardResponseVo unbind(UnbindCardVo vo) {
        return null;
    }

    @Override
    public BindCardMsgResponseVo bindCommit(BindCardMsgVo vo) {
        return null;
    }

    @Override
    public RepaymentResponseVo repayment(RepaymentReqVo vo) {
        return null;
    }

    @Override
    public RepaymentQueryResponseVo queryOrder(RepaymentQueryVo vo) {
        return null;
    }

    @Override
    public BindCardQueryResponseVo bindCardQuery(BindCardQueryVo vo) {
        return null;
    }

    @Override
    public CardBinQueryResponseVo queryCardBin(CardBinQueryVo vo) {
        return null;
    }
}

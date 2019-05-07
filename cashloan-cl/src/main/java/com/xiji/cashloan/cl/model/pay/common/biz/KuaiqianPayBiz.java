package com.xiji.cashloan.cl.model.pay.common.biz;

import com.xiji.cashloan.cl.model.pay.common.PayCommon;
import com.xiji.cashloan.cl.model.pay.common.constant.PayConstant;
import com.xiji.cashloan.cl.model.pay.common.vo.request.*;
import com.xiji.cashloan.cl.model.pay.common.vo.response.*;
import com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.KuaiqianpayHelper;
import com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.util.KuaiqianPayUtil;


/**
 * @auther : wnb
 * @date : 2019/4/30
 * @describe : 快钱支付实现类
 */
public class KuaiqianPayBiz implements PayCommon {

    @Override
    public PaymentResponseVo payment(PaymentReqVo vo) {

        KuaiqianPayHelper kuaiqianPayHelper = new KuaiqianPayHelper();
        Pay2bankOrder order = new Pay2bankOrder();
        order.setOrderId(KuaiqianPayUtil.getOrderId());
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
        Pay2bankOrderReturn pay2bankOrderReturn = kuaiqianPayHelper.payment(order);
        if (KuaiqianPayConstant.RESPONSE_SUCCESS_CODE.equals(pay2bankOrderReturn.getErrorCode())){
            responseVo.setStatus(PayConstant.RESULT_SUCCESS);
            responseVo.setStatusCode(pay2bankOrderReturn.getErrorCode());
        }else if(KuaiqianPayConstant.RESPONSE_CHECK_FAIL.equals(pay2bankOrderReturn.getErrorCode())){
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
        order.setTargetPage(KuaiqianPayConstant.QUERY_TARGETPAGE);
        //每页条数  必填  1-20  正整数
        order.setPageSize(KuaiqianPayConstant.QUERY_PGAE_SIZE);
        //商家订单号
        order.setOrderId(vo.getOrderNo());
        //开始时间 必填
        order.setStartDate(KuaiqianPayUtil.getDate(7)); //2017-11-19 08:12:12
        //结束时间 必填  结束-开始<=7天
        order.setEndDate(KuaiqianPayUtil.getDate(1)); //2017-11-21 23:59:59

        KuaiqianPayHelper kuaiqianPayHelper = new KuaiqianPayHelper();
        Pay2bankSearchDetail result = kuaiqianPayHelper.queryPayment(order);
        PaymentQueryResponseVo responseVo = new PaymentQueryResponseVo();
        if (result != null) {
            if (KuaiqianPayConstant.RESPONSE_SUCCESS_CODE.equals(result.getErrorCode())) {
                responseVo.setStatus(PayConstant.RESULT_SUCCESS);
            }else {
                responseVo.setStatus(PayConstant.STATUS_PAYQUERY_NO_REQ);
            }
        }
        return responseVo;
    }

    @Override
    public BindCardMsgResponseVo bindMsg(BindCardMsgVo vo) {
        BindXmlBeanReq beanReq = new BindXmlBeanReq();
        beanReq.setCustomerId(vo.getUserId());
        beanReq.setExternalRefNumber(KuaiqianPayUtil.getOrderId());
        beanReq.setPan(vo.getBankCardNo());
        beanReq.setCardHolderId(vo.getIdCard());
        beanReq.setCardHolderName(vo.getBankCardName());
        beanReq.setIdType("0");
        beanReq.setPhoneNO(vo.getMobile());

        KuaiqianpayHelper payHelper = new KuaiqianpayHelper();
        BindXmlBeanResp result = payHelper.bindMsg(beanReq);//调块钱接口发送验证码，同时验证四要素，卡号，证件类型，身份证，手机号，
        BindCardMsgResponseVo responseVo = new BindCardMsgResponseVo();
        if (result.checkReturn()) {
            responseVo.setStatus(PayConstant.RESULT_SUCCESS);
            responseVo.setMessage(result.getResponseTextMessage());
        }else {
            responseVo.setStatus(PayConstant.STATUS_FAIL);
            responseVo.setMessage(result.getResponseTextMessage());
        }
        responseVo.setOrderNo(beanReq.getExternalRefNumber());
        responseVo.setProtocolNo(result.getToken());
        return responseVo;
    }

    @Override
    public UnbindCardResponseVo unbind(UnbindCardVo vo) {
        return null;
    }

    @Override
    public BindCardMsgResponseVo bindCommit(BindCardMsgVo vo) {
        BindCardMsgResponseVo responseVo = new BindCardMsgResponseVo();
        BindXmlBeanReq beanReq = new BindXmlBeanReq();

        beanReq.setCustomerId(vo.getUserId());
        beanReq.setExternalRefNumber(KuaiqianPayUtil.getOrderId());
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

package com.xiji.cashloan.cl.model.pay.common.biz;

import com.xiji.cashloan.cl.model.pay.common.PayCommon;
import com.xiji.cashloan.cl.model.pay.common.constant.PayConstant;
import com.xiji.cashloan.cl.model.pay.common.vo.request.*;
import com.xiji.cashloan.cl.model.pay.common.vo.response.*;
import com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.KuaiqianpayHelper;
import com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.util.KuaiqianPayUtil;
import com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.vo.request.BindXmlBeanReq;
import com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.vo.response.BindXmlBeanResp;


public class KuaiqianPayBiz implements PayCommon {
    @Override
    public PaymentResponseVo payment(PaymentReqVo vo) {
        return null;
    }

    @Override
    public PaymentQueryResponseVo queryPayment(PaymentQueryVo vo) {
        return null;
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

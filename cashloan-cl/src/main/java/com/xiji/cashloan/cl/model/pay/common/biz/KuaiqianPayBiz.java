package com.xiji.cashloan.cl.model.pay.common.biz;

import com.xiji.cashloan.cl.model.pay.common.PayCommon;
import com.xiji.cashloan.cl.model.pay.common.constant.PayConstant;
import com.xiji.cashloan.cl.model.pay.common.vo.request.*;
import com.xiji.cashloan.cl.model.pay.common.vo.response.*;
import com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.KuaiqianpayHelper;
import com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.constant.KuaiqianPayConstant;
import com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.util.KuaiqianPayUtil;
import com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.vo.request.BindXmlBeanReq;
import com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.vo.request.PciDelReq;
import com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.vo.request.PciQueryReq;
import com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.vo.request.QueryTxnReq;
import com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.vo.response.BindXmlBeanResp;
import com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.vo.response.PciDelResp;
import com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.vo.response.PciQueryResp;
import com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.vo.response.QueryTxnResp;


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
        UnbindCardResponseVo responseVo = new UnbindCardResponseVo();
        PciDelReq pciDelReq = new PciDelReq();
        pciDelReq.setCustomerId(vo.getUserId());
        pciDelReq.setMerchantId(KuaiqianPayUtil.getAgreementMerchantId());
        pciDelReq.setVersion(KuaiqianPayConstant.PROTOCOL_VERSION);
        pciDelReq.setPayToken(vo.getProtocolNo());
        KuaiqianpayHelper kuaiqianpayHelper = new KuaiqianpayHelper();
        PciDelResp pciDelResp = kuaiqianpayHelper.unbind(pciDelReq);
        if (KuaiqianPayConstant.RESPONSE_SUCCESS_CODE.equals(pciDelResp.getResponseCode())){
            responseVo.setStatus(PayConstant.RESULT_SUCCESS);
            responseVo.setMessage(pciDelResp.getErrorMessage());
        } else {
            responseVo.setStatus(PayConstant.STATUS_FAIL);
            responseVo.setMessage(pciDelResp.getErrorMessage());
        }
        return responseVo;
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

        PciQueryReq queryReq = new PciQueryReq();
        queryReq.setCustomerId(vo.getUserId());
        queryReq.setVersion(KuaiqianPayConstant.PROTOCOL_VERSION);
        queryReq.setMerchantId(KuaiqianPayUtil.getAgreementMerchantId());
        queryReq.setTerminalId(KuaiqianPayUtil.getAgreementTerminalId());
        queryReq.setBindType("0");
        KuaiqianpayHelper kuaiqianpayHelper = new KuaiqianpayHelper();
        PciQueryResp pciQueryResp = kuaiqianpayHelper.bindQuery(queryReq);
        BindCardQueryResponseVo responseVo = new BindCardQueryResponseVo();
        if (KuaiqianPayConstant.RESPONSE_SUCCESS_CODE.equals(pciQueryResp.getResponseCode())){
            responseVo.setStatus(PayConstant.RESULT_SUCCESS);
            responseVo.setProtocolNo(pciQueryResp.getPayToken());
            responseVo.setMessage(pciQueryResp.getErrorMessage());
        }else {
            responseVo.setStatus(PayConstant.STATUS_FAIL);
            responseVo.setMessage(pciQueryResp.getErrorMessage());
        }
        return responseVo;
    }

    @Override
    public CardBinQueryResponseVo queryCardBin(CardBinQueryVo vo) {
        QueryTxnReq queryTxnReq = new QueryTxnReq();
        queryTxnReq.setCardNo(vo.getBankCardNo());
        queryTxnReq.setVersion(KuaiqianPayConstant.PROTOCOL_VERSION);
        queryTxnReq.setTxnType(KuaiqianPayConstant.TXN_TYPE);
        KuaiqianpayHelper kuaiqianpayHelper = new KuaiqianpayHelper();
        QueryTxnResp queryTxnResp = kuaiqianpayHelper.cardBinQuery(queryTxnReq);
        CardBinQueryResponseVo responseVo = new CardBinQueryResponseVo();
        if ("1".equals(queryTxnResp.getValidFlag())){
            responseVo.setStatus(PayConstant.RESULT_SUCCESS);
            responseVo.setBank(queryTxnResp.getIssuer());
        }else {
            responseVo.setStatus(PayConstant.STATUS_FAIL);
        }
        return responseVo;
    }
}

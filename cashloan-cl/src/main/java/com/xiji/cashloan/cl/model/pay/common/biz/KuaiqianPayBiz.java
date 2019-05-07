package com.xiji.cashloan.cl.model.pay.common.biz;

import com.xiji.cashloan.cl.model.pay.common.PayCommon;
import com.xiji.cashloan.cl.model.pay.common.constant.PayConstant;
import com.xiji.cashloan.cl.model.pay.common.vo.request.*;
import com.xiji.cashloan.cl.model.pay.common.vo.response.*;
import com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.KuaiqianPayHelper;
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
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.paymock.vo.Pay2bankOrder;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.paymock.vo.Pay2bankOrderReturn;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.querymock.vo.Pay2bankSearchDetail;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.querymock.vo.Pay2bankSearchRequestParam;
import com.xiji.cashloan.cl.service.BankCardService;
import com.xiji.cashloan.cl.service.ClBorrowService;
import com.xiji.cashloan.core.common.context.Global;



import javax.annotation.Resource;


/**
 * @auther : wnb
 * @date : 2019/4/30
 * @describe : 快钱支付实现类
 */
public class KuaiqianPayBiz implements PayCommon {

    @Resource
    private ClBorrowService borrowService;

    @Resource
    private BankCardService bankCardService;


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
        if (KuaiqianPayConstant.PAYFOR_RESPONSE_SUCCESS_CODE.equals(pay2bankOrderReturn.getErrorCode())){
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
            if (KuaiqianPayConstant.PAYFOR_RESPONSE_SUCCESS_CODE.equals(result.getErrorCode())) {
                responseVo.setStatus(PayConstant.RESULT_SUCCESS);
            }else {
                responseVo.setStatus(PayConstant.STATUS_PAYQUERY_NO_REQ);
            }
        }
        return responseVo;
    }

    @Override
    public BindCardMsgResponseVo bindMsg(BindCardMsgVo vo) {
        AgreementSendValidateCodeReqVo beanReq = new AgreementSendValidateCodeReqVo();
        beanReq.setCustomerId(vo.getUserId());
        beanReq.setExternalRefNumber(KuaiqianPayUtil.getOrderId());
        beanReq.setPan(vo.getBankCardNo());
        beanReq.setCardHolderId(vo.getIdCard());
        beanReq.setCardHolderName(vo.getBankCardName());
        beanReq.setIdType("0");
        beanReq.setPhoneNO(vo.getMobile());

        KuaiqianPayHelper payHelper = new KuaiqianPayHelper();
        AgreementSendValidateCodeRespVo result = payHelper.bindMsg(beanReq);//调块钱接口发送验证码，同时验证四要素，卡号，证件类型，身份证，手机号，
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
        KuaiqianPayHelper kuaiqianpayHelper = new KuaiqianPayHelper();
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
        BindCardReqVo reqVo = new BindCardReqVo();

        reqVo.setCustomerId(vo.getUserId());
        reqVo.setExternalRefNumber(vo.getOrderNo());
        reqVo.setPan(vo.getBankCardNo());
        reqVo.setPhoneNO(vo.getMobile());
        reqVo.setValidCode(vo.getMsgCode());
        reqVo.setToken(vo.getToken());
        KuaiqianPayHelper payHelper = new KuaiqianPayHelper();

        BindCardRespVo result=payHelper.bindCommit(reqVo);//绑卡

        if (result.checkReturn()) {
            responseVo.setStatus(PayConstant.RESULT_SUCCESS);
            responseVo.setProtocolNo(result.getPayToken());
            responseVo.setMessage(result.getResponseTextMessage());
        }else {
            responseVo.setStatus(PayConstant.STATUS_FAIL);
            responseVo.setMessage(result.getResponseTextMessage());
        }
        return responseVo;
    }

    @Override
    public RepaymentResponseVo repayment(RepaymentReqVo vo) {
        return null;
    }

    @Override
    public RepaymentQueryResponseVo queryOrder(RepaymentQueryVo vo) {
        QueryStatusReqVO reqVO = new QueryStatusReqVO();
        RepaymentQueryResponseVo responseVo = new RepaymentQueryResponseVo();
        reqVO.setVersion(KuaiqianPayConstant.PROTOCOL_VERSION);

        KuaiqianPayHelper payHelper = new KuaiqianPayHelper();
        QueryStatusRespVO result=payHelper.queryOrder(reqVO);//订单查询

        responseVo.setCode(PayConstant.QUERY_PAY_ERROR);
        if ("00".equals(result.getResponseCode())){

        }


        if (result.checkReturn()) {
            responseVo.setCode(result.getResponseCode());
            responseVo.setMsg(result.getResponseTextMessage());
        }else {
            responseVo.setCode(result.getResponseCode());
            responseVo.setMsg(result.getResponseTextMessage());
        }

        responseVo.setOrderNo(reqVO.getExternalRefNumber());

        return responseVo;
    }

    @Override
    public BindCardQueryResponseVo bindCardQuery(BindCardQueryVo vo) {

        PciQueryReq queryReq = new PciQueryReq();
        queryReq.setCustomerId(vo.getUserId());
        queryReq.setVersion(KuaiqianPayConstant.PROTOCOL_VERSION);
        queryReq.setMerchantId(KuaiqianPayUtil.getAgreementMerchantId());
        queryReq.setTerminalId(KuaiqianPayUtil.getAgreementTerminalId());
        queryReq.setBindType("0");
        KuaiqianPayHelper kuaiqianpayHelper = new KuaiqianPayHelper();
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
        KuaiqianPayHelper kuaiqianpayHelper = new KuaiqianPayHelper();
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

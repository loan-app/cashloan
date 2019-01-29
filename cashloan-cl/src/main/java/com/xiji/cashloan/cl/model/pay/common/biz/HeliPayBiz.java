package com.xiji.cashloan.cl.model.pay.common.biz;

import com.xiji.cashloan.cl.model.pay.common.PayCommon;
import com.xiji.cashloan.cl.model.pay.common.constant.PayConstant;
import com.xiji.cashloan.cl.model.pay.common.vo.request.BindCardMsgVo;
import com.xiji.cashloan.cl.model.pay.common.vo.request.BindCardQueryVo;
import com.xiji.cashloan.cl.model.pay.common.vo.request.CardBinQueryVo;
import com.xiji.cashloan.cl.model.pay.common.vo.request.PaymentQueryVo;
import com.xiji.cashloan.cl.model.pay.common.vo.request.PaymentReqVo;
import com.xiji.cashloan.cl.model.pay.common.vo.request.RepaymentQueryVo;
import com.xiji.cashloan.cl.model.pay.common.vo.request.RepaymentReqVo;
import com.xiji.cashloan.cl.model.pay.common.vo.request.UnbindCardVo;
import com.xiji.cashloan.cl.model.pay.common.vo.response.BindCardMsgResponseVo;
import com.xiji.cashloan.cl.model.pay.common.vo.response.BindCardQueryResponseVo;
import com.xiji.cashloan.cl.model.pay.common.vo.response.CardBinQueryResponseVo;
import com.xiji.cashloan.cl.model.pay.common.vo.response.PaymentQueryResponseVo;
import com.xiji.cashloan.cl.model.pay.common.vo.response.PaymentResponseVo;
import com.xiji.cashloan.cl.model.pay.common.vo.response.RepaymentQueryResponseVo;
import com.xiji.cashloan.cl.model.pay.common.vo.response.RepaymentResponseVo;
import com.xiji.cashloan.cl.model.pay.common.vo.response.UnbindCardResponseVo;
import com.xiji.cashloan.cl.model.pay.helipay.HelipayHelper;
import com.xiji.cashloan.cl.model.pay.helipay.constant.HelipayConstant;
import com.xiji.cashloan.cl.model.pay.helipay.util.HelipayUtil;
import com.xiji.cashloan.cl.model.pay.helipay.util.MessageHandle;
import com.xiji.cashloan.cl.model.pay.helipay.vo.request.AgreementBindCardValidateCodeVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.request.BindCardPayVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.request.BindCardVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.request.PayForReqVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.request.QueryOrderVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.request.UnBindCardVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.response.AgreementSendValidateCodeResponseVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.response.BindCardPayResponseVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.response.BindCardResponseVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.response.HeliPayForPaymentResultVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.response.QueryOrderResponseVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.response.UnBindCardResponseVo;
import com.xiji.cashloan.core.common.context.BankCardBinUtil;
import com.xiji.cashloan.core.common.util.StringUtil;
import com.xiji.cashloan.core.domain.BankCardBin;
import org.apache.commons.lang3.StringUtils;

/**
 * @Auther: king
 * @Date: 2019/1/26 15:13
 * @Description:
 */
public class HeliPayBiz implements PayCommon {

    @Override
    public PaymentResponseVo payment(PaymentReqVo vo) {
        HelipayHelper helipayHelper = new HelipayHelper();
        PayForReqVo reqVo = new PayForReqVo();
        reqVo.setOrderId(HelipayUtil.getOrderId());
        reqVo.setAmount(Double.toString(vo.getAmount()));
        reqVo.setBankCode(BankCardBinUtil.getBankCode(vo.getBankCardNo()));
        reqVo.setBankAccountName(vo.getBankCardName());
        reqVo.setBankAccountNo(vo.getBankCardNo());
        reqVo.setBiz(HelipayConstant.BIZ_TYPE_B2C);
        reqVo.setFeeType(HelipayConstant.PAYTYPE_PAYER);
        reqVo.setUrgency(HelipayConstant.PAY_URGENCY);
        reqVo.setSummary(vo.getRemark());
        reqVo.setNotifyUrl(HelipayUtil.paymentNotifyAddress());

        HeliPayForPaymentResultVo result = helipayHelper.payment(reqVo);
        PaymentResponseVo responseVo = new PaymentResponseVo();
        //受理成功
        if (StringUtil.equals(result.getRt2_retCode(),HelipayConstant.RESULT_CODE_SUCCESS)) {
            responseVo.setStatus(PayConstant.RESULT_SUCCESS);
            responseVo.setStatusCode(result.getRt2_retCode());
        } else if (error(result.getRt2_retCode())) {
            // 疑似重复订单，待人工审核
            responseVo.setStatus(PayConstant.STATUS_NEED_CHECK);
        } else {
            responseVo.setStatusCode(result.getRt2_retCode());
            responseVo.setStatus(PayConstant.STATUS_FAIL);
        }
        responseVo.setOrderNo(reqVo.getOrderId());
        responseVo.setMessage(result.getRt3_retMsg());
        return responseVo;
    }

    @Override
    public PaymentQueryResponseVo queryPayment(PaymentQueryVo vo) {
        HelipayHelper helipayHelper = new HelipayHelper();
        PayForReqVo reqVo = new PayForReqVo();
        reqVo.setOrderId(vo.getOrderNo());
        HeliPayForPaymentResultVo result = helipayHelper.queryPayment(reqVo);
        PaymentQueryResponseVo responseVo = new PaymentQueryResponseVo();
        if (result != null) {
            if (StringUtil.equalsIgnoreCase(result.getRt2_retCode(), HelipayConstant.DAIFU_RESPONSE_NO_ORDER)) {
                responseVo.setStatus(PayConstant.STATUS_PAYQUERY_NO_REQ);
            }else {
                responseVo.setStatus(PayConstant.RESULT_SUCCESS);
            }
        }
        return responseVo;
    }

    @Override
    public BindCardMsgResponseVo bindMsg(BindCardMsgVo vo) {
        AgreementBindCardValidateCodeVo reqVo = new AgreementBindCardValidateCodeVo();
        HelipayHelper helipayHelper = new HelipayHelper();
        reqVo.setP3_userId(vo.getUserId());
        reqVo.setP5_timestamp(HelipayUtil.getTimeStamp());
        reqVo.setP6_cardNo(vo.getBankCardNo());
        reqVo.setP7_phone(vo.getMobile());
        reqVo.setP8_idCardNo(vo.getIdCard());
        reqVo.setP9_idCardType(HelipayUtil.IDCARD);
        reqVo.setP10_payerName(vo.getBankCardName());
        reqVo.setP4_orderId(HelipayUtil.getOrderId());
        reqVo.setSignatureType(HelipayConstant.SIGNATURE_TYPE);
        AgreementSendValidateCodeResponseVo result = helipayHelper.bindMsg(reqVo);
        BindCardMsgResponseVo responseVo = new BindCardMsgResponseVo();
        try {
            responseVo.setStatus(PayConstant.STATUS_ERROR);
            if (!unSignError(result.getRt2_retCode()) && MessageHandle.checkSign(result)){
                if (success(result.getRt2_retCode())) {
                    responseVo.setStatus(PayConstant.RESULT_SUCCESS);
                    responseVo.setMessage(result.getRt3_retMsg());
                }else {
                    responseVo.setStatus(PayConstant.STATUS_FAIL);
                    responseVo.setMessage(result.getRt3_retMsg());
                }
            }
        } catch (Exception e) {
            responseVo.setStatus(PayConstant.STATUS_ERROR);
        }
        responseVo.setOrderNo(reqVo.getP4_orderId());

        return responseVo;
    }

    @Override
    public UnbindCardResponseVo unbind(UnbindCardVo vo) {
        UnBindCardVo reqVo = new UnBindCardVo();
        reqVo.setP3_userId(vo.getUserId());
        reqVo.setP4_bindId(vo.getProtocolNo());
        reqVo.setP5_orderId(HelipayUtil.getOrderId());
        reqVo.setP6_timestamp(HelipayUtil.getTimeStamp());
        reqVo.setSignatureType(HelipayConstant.SIGNATURE_TYPE);
        HelipayHelper helipayHelper = new HelipayHelper();
        UnBindCardResponseVo result = helipayHelper.unbind(reqVo);
        UnbindCardResponseVo responseVo = new UnbindCardResponseVo();
        try {
            responseVo.setStatus(PayConstant.STATUS_ERROR);
            if (!unSignError(result.getRt2_retCode()) && MessageHandle.checkSign(result)){
                if (success(result.getRt2_retCode())) {
                    responseVo.setStatus(PayConstant.RESULT_SUCCESS);
                    responseVo.setMessage(result.getRt3_retMsg());
                }else {
                    responseVo.setStatus(PayConstant.STATUS_FAIL);
                    responseVo.setMessage(result.getRt3_retMsg());
                }
            }
        } catch (Exception e) {
            responseVo.setStatus(PayConstant.STATUS_ERROR);
        }
        return responseVo;
    }

    @Override
    public BindCardMsgResponseVo bindCommit(BindCardMsgVo vo) {
        BindCardMsgResponseVo responseVo = new BindCardMsgResponseVo();
        BindCardVo reqVo = new BindCardVo();
        HelipayHelper helipayHelper = new HelipayHelper();
        reqVo.setP3_userId(vo.getUserId());
        reqVo.setP4_orderId(vo.getOrderNo());
        reqVo.setP5_timestamp(HelipayUtil.getTimeStamp());
        reqVo.setP6_payerName(vo.getBankCardName());
        reqVo.setP7_idCardType(HelipayUtil.IDCARD);
        reqVo.setP8_idCardNo(vo.getIdCard());
        reqVo.setP9_cardNo(vo.getBankCardNo());
        reqVo.setP13_phone(vo.getMobile());
        reqVo.setP14_validateCode(vo.getMsgCode());
        reqVo.setP15_isEncrypt("TRUE");
        reqVo.setSignatureType(HelipayConstant.SIGNATURE_TYPE);
        BindCardResponseVo result = helipayHelper.bindCommit(reqVo);
        try {
            responseVo.setStatus(PayConstant.STATUS_ERROR);
            if (!unSignError(result.getRt2_retCode()) && MessageHandle.checkSign(result)){
                if (success(result.getRt2_retCode())) {
                    responseVo.setStatus(PayConstant.RESULT_SUCCESS);
                    responseVo.setMessage(result.getRt3_retMsg());
                    responseVo.setProtocolNo(result.getRt10_bindId());
                }else {
                    responseVo.setStatus(PayConstant.STATUS_FAIL);
                    responseVo.setMessage(result.getRt3_retMsg());
                }
            }
        } catch (Exception e) {
            responseVo.setStatus(PayConstant.STATUS_ERROR);
        }
        return responseVo;
    }

    @Override
    public RepaymentResponseVo repayment(RepaymentReqVo vo) {
        BindCardPayVo reqVo = new BindCardPayVo();
        HelipayHelper helipayHelper = new HelipayHelper();
        reqVo.setP3_bindId(vo.getProtocolNo());
        reqVo.setP4_userId(vo.getUserId());
        reqVo.setP5_orderId(HelipayUtil.getOrderId());
        reqVo.setP6_timestamp(HelipayUtil.getTimeStamp());
        reqVo.setP7_currency(HelipayConstant.CURRENCY_CNY);
        reqVo.setP8_orderAmount(Double.toString(vo.getAmount()));
        reqVo.setP9_goodsName(vo.getRemark());
        reqVo.setP11_terminalType(vo.getTerminalType());
        reqVo.setP12_terminalId(vo.getTerminalId());
        reqVo.setP13_orderIp(vo.getIp());
        reqVo.setP16_serverCallbackUrl(HelipayUtil.rePaymentNotifyAddress());
        reqVo.setSignatureType(HelipayConstant.SIGNATURE_TYPE);
        BindCardPayResponseVo result = helipayHelper.repayment(reqVo);
        RepaymentResponseVo responseVo = new RepaymentResponseVo();
        responseVo.setOrderNo(reqVo.getP5_orderId());
        try {
            responseVo.setStatus(PayConstant.STATUS_ERROR);
            if (!unSignError(result.getRt2_retCode()) && MessageHandle.checkSign(result)){
                if (success(result.getRt2_retCode())) {
                    String payMsg = result.getRt3_retMsg();
                    if (tool.util.StringUtil.isNotEmpty(result.getRt6_serialNumber())) {
                        payMsg = result.getRt6_serialNumber()+"|" + payMsg;
                    }
                    responseVo.setStatus(PayConstant.RESULT_SUCCESS);
                    responseVo.setMessage(payMsg);
                    responseVo.setPayPlatNo(result.getRt6_serialNumber());
                } else {
                    responseVo.setStatus(PayConstant.STATUS_FAIL);
                    responseVo.setMessage(result.getRt3_retMsg());
                }
            }
        } catch (Exception e) {
            responseVo.setStatus(PayConstant.STATUS_ERROR);
        }
        responseVo.setStatusCode(result.getRt2_retCode());
        return responseVo;
    }

    @Override
    public RepaymentQueryResponseVo queryOrder(RepaymentQueryVo vo) {
        QueryOrderVo reqVo = new QueryOrderVo();
        HelipayHelper helipayHelper = new HelipayHelper();
        reqVo.setP2_orderId(vo.getOrderNo());
        reqVo.setSignatureType(HelipayConstant.SIGNATURE_TYPE);
        QueryOrderResponseVo result = helipayHelper.queryOrder(reqVo);
        RepaymentQueryResponseVo responseVo = new RepaymentQueryResponseVo();
        try {
            responseVo.setCode(PayConstant.QUERY_PAY_ERROR);
            if (!unSignError(result.getRt2_retCode()) && MessageHandle.checkSign(result)){
                if (success(result.getRt2_retCode())) {
                    responseVo.setCode(PayConstant.QUERY_PAY_SUCCESS);
                }else if(StringUtil.equals(result.getRt2_retCode(),HelipayConstant.ORDER_RETCODE_NEED_ORDERSTATUS)) {
                    if (StringUtil.equals(result.getRt2_retCode(),HelipayConstant.ORDER_STATUS_SUCCESS)){
                        responseVo.setCode(PayConstant.QUERY_PAY_SUCCESS);
                    }else if(StringUtil.equals(result.getRt2_retCode(),HelipayConstant.ORDER_STATUS_DOING)
                        || StringUtil.equals(result.getRt2_retCode(),HelipayConstant.ORDER_STATUS_INIT)){
                        responseVo.setCode(PayConstant.QUERY_PAY_PROCESSING);
                    }else {
                        responseVo.setCode(PayConstant.QUERY_PAY_FAIL);
                    }
                } else {
                    responseVo.setCode(PayConstant.QUERY_PAY_FAIL);
                }
            }
        } catch (Exception e) {
            responseVo.setCode(PayConstant.QUERY_PAY_ERROR);
        }
        return responseVo;
    }

    @Override
    public BindCardQueryResponseVo bindCardQuery(BindCardQueryVo vo) {
        BindCardQueryResponseVo responseVo = new BindCardQueryResponseVo();
        return responseVo;
    }

    @Override
    public CardBinQueryResponseVo queryCardBin(CardBinQueryVo vo) {
        BankCardBin cardBin = BankCardBinUtil.getBankCardBin(vo.getBankCardNo());
        CardBinQueryResponseVo responseVo = new CardBinQueryResponseVo();
        if (cardBin != null) {
            responseVo.setStatus(PayConstant.RESULT_SUCCESS);
            responseVo.setBank(cardBin.getBankName());
        }else {
            responseVo.setStatus(PayConstant.STATUS_FAIL);
        }
        return responseVo;
    }

    public boolean error(String code) {
        return StringUtils.startsWith(code, "300");
    }

    private boolean unSignError(String code){
       return error(code) || StringUtil.equals(code,PayConstant.REQ_ERROR_CODE_10);
    }

    private boolean success(String code) {
       return StringUtil.equals(code,HelipayConstant.RESULT_CODE_SUCCESS);
    }
}

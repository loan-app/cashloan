package com.xiji.cashloan.cl.model.pay.common.biz;

import com.xiji.cashloan.cl.model.pay.common.constant.PayConstant;
import com.xiji.cashloan.cl.model.pay.common.vo.PayCommon;
import com.xiji.cashloan.cl.model.pay.common.vo.request.BindCardMsgVo;
import com.xiji.cashloan.cl.model.pay.common.vo.request.PaymentQueryVo;
import com.xiji.cashloan.cl.model.pay.common.vo.request.PaymentReqVo;
import com.xiji.cashloan.cl.model.pay.common.vo.request.RepaymentQueryVo;
import com.xiji.cashloan.cl.model.pay.common.vo.request.RepaymentReqVo;
import com.xiji.cashloan.cl.model.pay.common.vo.request.UnbindCardVo;
import com.xiji.cashloan.cl.model.pay.common.vo.response.BindCardMsgResponseVo;
import com.xiji.cashloan.cl.model.pay.common.vo.response.PaymentQueryResponseVo;
import com.xiji.cashloan.cl.model.pay.common.vo.response.PaymentResponseVo;
import com.xiji.cashloan.cl.model.pay.common.vo.response.RepaymentQueryResponseVo;
import com.xiji.cashloan.cl.model.pay.common.vo.response.RepaymentResponseVo;
import com.xiji.cashloan.cl.model.pay.common.vo.response.UnbindCardResponseVo;
import com.xiji.cashloan.cl.model.pay.fuiou.agreement.BindXmlBeanReq;
import com.xiji.cashloan.cl.model.pay.fuiou.agreement.BindXmlBeanResp;
import com.xiji.cashloan.cl.model.pay.fuiou.agreement.OrderXmlBeanReq;
import com.xiji.cashloan.cl.model.pay.fuiou.agreement.OrderXmlBeanResp;
import com.xiji.cashloan.cl.model.pay.fuiou.agreement.QueryPayOrderInfo;
import com.xiji.cashloan.cl.model.pay.fuiou.constant.FuiouConstant;
import com.xiji.cashloan.cl.model.pay.fuiou.payfor.PayforreqModel;
import com.xiji.cashloan.cl.model.pay.fuiou.payfor.PayforrspModel;
import com.xiji.cashloan.cl.model.pay.fuiou.payfor.QrytransreqModel;
import com.xiji.cashloan.cl.model.pay.fuiou.payfor.QrytransrspModel;
import com.xiji.cashloan.cl.model.pay.fuiou.util.FuiouAgreementPayHelper;
import com.xiji.cashloan.cl.model.pay.fuiou.util.FuiouHelper;
import com.xiji.cashloan.cl.util.fuiou.AmtUtil;
import com.xiji.cashloan.cl.util.fuiou.FuiouDateUtil;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.util.IpUtil;
import com.xiji.cashloan.core.common.util.OrderNoUtil;
import java.util.Date;
import tool.util.DateUtil;
import tool.util.StringUtil;

/**
 * @Auther: king
 * @Date: 2019/1/26 15:13
 * @Description:
 */
public class FuiouPayBiz implements PayCommon {

    @Override
    public PaymentResponseVo payment(PaymentReqVo vo) {
        String orderNo = OrderNoUtil.getSerialNumber();
        PayforreqModel model = new PayforreqModel();
        model.setMerdt(tool.util.DateUtil.dateStr7(new Date()));
        model.setOrderno(orderNo);
        model.setAccntno(vo.getBankCardNo());
        model.setAccntnm(vo.getBankCardName());
        if ("dev".equals(Global.getValue("app_environment"))) {
            model.setAmt(AmtUtil.convertAmtToBranch(3.0));
        } else {
            model.setAmt(AmtUtil.convertAmtToBranch(vo.getAmount()));
        }
        model.setMobile(vo.getMobile());
        model.setEntseq(vo.getBorrowOrderNo());//借款号
        model.setMemo(vo.getBorrowOrderNo()+ "付款");
        model.setAddDesc(FuiouConstant.DAIFU_PAYFOR_ADDDESC);
        FuiouHelper fuiouHelper = new FuiouHelper();
        PayforrspModel result = fuiouHelper.payment(model);
        PaymentResponseVo responseVo = new PaymentResponseVo();

        if (result.acceptSuccess() || result.success()) { //受理成功
            responseVo.setStatus(PayConstant.RESULT_SUCCESS);
        } else if (result.error()) { // 疑似重复订单，待人工审核
            responseVo.setStatus(PayConstant.STATUS_NEED_CHECK);
        } else {
            responseVo.setStatus(PayConstant.STATUS_FAIL);
        }
        responseVo.setOrderNo(model.getOrderno());
        responseVo.setMessage(fuiouHelper.getRemark(result));
        return responseVo;
    }

    @Override
    public PaymentQueryResponseVo queryPayment(PaymentQueryVo vo) {
        QrytransreqModel model = new QrytransreqModel();
        model.setOrderno(vo.getOrderNo());
        model.setVer(FuiouConstant.DAIFU_PAYFOR_VERSION);
        model.setBusicd(FuiouConstant.DAIFU_PAYFOR_DAIFU_TYPE);
        model.setStartdt(FuiouDateUtil.getDate(14));
        model.setEnddt(FuiouDateUtil.getDate(1));
        FuiouHelper fuiouHelper = new FuiouHelper();
        QrytransrspModel result = fuiouHelper.queryPayment(model);
        PaymentQueryResponseVo responseVo = new PaymentQueryResponseVo();
        if (result != null) {
            if (StringUtil.equalsIgnoreCase(result.getRet(), FuiouConstant.DAIFU_RESPONSE_NO_SENDREQ_CODE)) {
                responseVo.setStatus(PayConstant.STATUS_PAYQUERY_NO_REQ);
            }else {
                responseVo.setStatus(PayConstant.RESULT_SUCCESS);
            }
        }
        return responseVo;
    }

    @Override
    public BindCardMsgResponseVo bindMsg(BindCardMsgVo vo) {
        BindXmlBeanReq beanReq = new BindXmlBeanReq();
        beanReq.setUserId(vo.getUserId());
        beanReq.setTradeDate(DateUtil.dateStr7(new Date()));
        beanReq.setMchntSsn(OrderNoUtil.getSerialNumber());
        beanReq.setAccount(vo.getBankCardName());
        beanReq.setCardNo(vo.getBankCardNo());
        beanReq.setIdType("0");
        beanReq.setIdCard(vo.getIdCard());
        beanReq.setMobileNo(vo.getMobile());

        FuiouAgreementPayHelper payHelper = new FuiouAgreementPayHelper();
        BindXmlBeanResp result = payHelper.bindMsg(beanReq);//调富有接口发送验证码，同时验证四要素，卡号，证件类型，身份证，手机号，
        BindCardMsgResponseVo responseVo = new BindCardMsgResponseVo();
        if (result.checkReturn()) {
            responseVo.setStatus(PayConstant.RESULT_SUCCESS);
            responseVo.setMessage(result.getResponseMsg());
        }else {
            responseVo.setStatus(PayConstant.STATUS_FAIL);
            responseVo.setMessage(result.getResponseMsg());
        }
        return responseVo;
    }

    @Override
    public UnbindCardResponseVo unbind(UnbindCardVo vo) {
        BindXmlBeanReq beanReq = new BindXmlBeanReq();
        beanReq.setUserId(vo.getUserId());
        beanReq.setProtocolNo(vo.getProtocolNo());
        FuiouAgreementPayHelper payHelper = new FuiouAgreementPayHelper();
        BindXmlBeanResp result = payHelper.unbind(beanReq);
        UnbindCardResponseVo responseVo = new UnbindCardResponseVo();
        if (result.checkReturn()) {
            responseVo.setStatus(PayConstant.RESULT_SUCCESS);
            responseVo.setMessage(result.getResponseMsg());
        }else {
            responseVo.setStatus(PayConstant.STATUS_FAIL);
            responseVo.setMessage(result.getResponseMsg());
        }
        return responseVo;
    }

    @Override
    public BindCardMsgResponseVo bindCommit(BindCardMsgVo vo) {
        //签约，即授权绑卡
        BindXmlBeanReq beanReq = new BindXmlBeanReq();
        beanReq.setUserId(vo.getUserId());
        beanReq.setTradeDate(DateUtil.dateStr7(new Date()));
        beanReq.setMchntSsn(vo.getOrderNo());//需要使用发送验证码时的流水号
        beanReq.setAccount(vo.getBankCardName());
        beanReq.setCardNo(vo.getBankCardNo());
        beanReq.setIdType("0");
        beanReq.setIdCard(vo.getIdCard());
        beanReq.setMobileNo(vo.getMobile());
        beanReq.setMsgCode(vo.getMsgCode());//验证码

        FuiouAgreementPayHelper payHelper = new FuiouAgreementPayHelper();
        BindXmlBeanResp result = payHelper.bindCommit(beanReq);//绑卡
        BindCardMsgResponseVo responseVo = new BindCardMsgResponseVo();
        if (result.checkReturn()) {
            responseVo.setStatus(PayConstant.RESULT_SUCCESS);
            responseVo.setProtocolNo(result.getProtocolNo());
            responseVo.setMessage(result.getResponseMsg());
        }else {
            responseVo.setStatus(PayConstant.STATUS_FAIL);
            responseVo.setMessage(result.getResponseMsg());
        }
        return responseVo;
    }

    @Override
    public RepaymentResponseVo repayment(RepaymentReqVo vo) {
        OrderXmlBeanReq beanReq = new OrderXmlBeanReq();
        beanReq.setUserId(vo.getUserId());
        beanReq.setUserIp(IpUtil.getLocalIp());
        beanReq.setType("03");
        String orderNo = OrderNoUtil.getSerialNumber();
        beanReq.setMchntOrderId(orderNo);
        if ("dev".equals(Global.getValue("app_environment"))) {
            beanReq.setAmt(AmtUtil.convertAmtToBranch("0.01"));
        } else {
            beanReq.setAmt(AmtUtil.convertAmtToBranch(vo.getAmount()));
        }

        beanReq.setProtocolNo(vo.getProtocolNo());
        beanReq.setNeedSendMsg("0");
        beanReq.setBackUrl(Global.getValue("server_host")+ "/pay/fuiou/repaymentNotify.htm");
        beanReq.setRem1("还款" + vo.getBorrowOrderNo());
        beanReq.setRem2("repayment_" + vo.getBorrowOrderNo());
        String key = Global.getValue("fuiou_protocol_mchntcd_key");
        FuiouAgreementPayHelper payHelper = new FuiouAgreementPayHelper();
        OrderXmlBeanResp result = payHelper.repayment(beanReq);

        RepaymentResponseVo responseVo = new RepaymentResponseVo();
        responseVo.setOrderNo(orderNo);
        if (result.checkSign(key)) {
            String payMsg = result.getResponseMsg();
            if (result.checkReturn() && StringUtil.isNotEmpty(result.getOrderId())) {
                payMsg = result.getOrderId()+"|" + payMsg;
            }
            responseVo.setStatus(PayConstant.RESULT_SUCCESS);
            responseVo.setMessage(payMsg);
            responseVo.setPayPlatNo(result.getOrderId());
        }else {
            responseVo.setStatus(PayConstant.STATUS_FAIL);
            responseVo.setMessage(result.getResponseMsg());
        }
        return responseVo;
    }

    @Override
    public RepaymentQueryResponseVo queryOrder(RepaymentQueryVo vo) {
        FuiouAgreementPayHelper payHelper = new FuiouAgreementPayHelper();
        QueryPayOrderInfo result = payHelper.queryPayInfo(vo);

        RepaymentQueryResponseVo responseVo = new RepaymentQueryResponseVo();
        responseVo.setOrderNo(result.getOrderNo());
        responseVo.setCode(result.getCode());
        responseVo.setMsg(result.getMsg());
        return responseVo;
    }
}

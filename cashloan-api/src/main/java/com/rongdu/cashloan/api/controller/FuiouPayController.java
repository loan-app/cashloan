package com.rongdu.cashloan.api.controller;

import com.alibaba.fastjson.JSON;
import com.rongdu.cashloan.cl.model.pay.fuiou.agreement.BankCardReq;
import com.rongdu.cashloan.cl.model.pay.fuiou.agreement.BankCardResp;
import com.rongdu.cashloan.cl.model.pay.fuiou.agreement.BindXmlBeanReq;
import com.rongdu.cashloan.cl.model.pay.fuiou.agreement.BindXmlBeanResp;
import com.rongdu.cashloan.cl.model.pay.fuiou.agreement.OrderQryByMSsn;
import com.rongdu.cashloan.cl.model.pay.fuiou.agreement.OrderQryResp;
import com.rongdu.cashloan.cl.model.pay.fuiou.agreement.OrderXmlBeanReq;
import com.rongdu.cashloan.cl.model.pay.fuiou.agreement.OrderXmlBeanResp;
import com.rongdu.cashloan.cl.model.pay.fuiou.agreement.QryByFuiouOrderReq;
import com.rongdu.cashloan.cl.model.pay.fuiou.agreement.QryByFuiouOrderResp;
import com.rongdu.cashloan.cl.model.pay.fuiou.constant.FuiouConstant;
import com.rongdu.cashloan.cl.model.pay.fuiou.payfor.PayforreqModel;
import com.rongdu.cashloan.cl.model.pay.fuiou.payfor.PayforrspModel;
import com.rongdu.cashloan.cl.model.pay.fuiou.payfor.QrytransreqModel;
import com.rongdu.cashloan.cl.model.pay.fuiou.payfor.QrytransrspModel;
import com.rongdu.cashloan.cl.model.pay.fuiou.util.FuiouAgreementPayHelper;
import com.rongdu.cashloan.cl.model.pay.fuiou.util.FuiouHelper;
import com.rongdu.cashloan.cl.util.fuiou.AmtUtil;
import com.rongdu.cashloan.cl.util.fuiou.FuiouDateUtil;
import com.rongdu.cashloan.core.common.context.Global;
import com.rongdu.cashloan.core.common.util.OrderNoUtil;
import com.rongdu.cashloan.core.common.util.StringUtil;
import com.rongdu.cashloan.core.common.web.controller.BaseController;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tool.util.DateUtil;

/**
 * @Auther: king
 * @Date: 2018/11/28 16:31
 * @Description:
 */
@Controller
@Scope("prototype")
public class FuiouPayController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(FuiouPayController.class);

    @RequestMapping(value = "/fuiou/bindMsg.htm",method = {RequestMethod.POST, RequestMethod.GET})
    public void bindMsg(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String version = "1.0";
            String mchntSsn = req.getParameter("mchntssn");
            String tradeDate = req.getParameter("tradedate");
            String mchntcd = req.getParameter("mchntcd");
            String key = req.getParameter("key");
            String userid = req.getParameter("userid");
            String account = req.getParameter("account");
            String cardNo = req.getParameter("cardno");
            String idType = req.getParameter("idtype");
            String idCard = req.getParameter("idcard");
            String mobileNo = req.getParameter("mobileno");
            String cvn = req.getParameter("cvn");
            String exp = req.getParameter("exp");
            String cvn2 = "";
//            if(cvn != null && !"".equals(cvn.trim())&& exp!=null && !"".equals(exp.trim())){
//                cvn2 = RSAUtils.encryptByPublicKey(cvn+";"+exp, Constants.FUIOU_PUB_KEY);
//            }

            BindXmlBeanReq beanReq = new BindXmlBeanReq();
            beanReq.setVersion(version);
            if (StringUtil.isEmpty(tradeDate)) {
                beanReq.setTradeDate(DateUtil.dateStr7(new Date()));
            } else {
                beanReq.setTradeDate(tradeDate);
            }
            if (StringUtil.isEmpty(mchntSsn)) {
                beanReq.setMchntSsn(OrderNoUtil.getSerialNumber());
            } else {
                beanReq.setMchntSsn(mchntSsn);
            }

            beanReq.setMchntCd(mchntcd);
            beanReq.setUserId(userid);
            beanReq.setAccount(account);
            beanReq.setCardNo(cardNo);
            beanReq.setIdType(idType);
            beanReq.setIdCard(idCard);
            beanReq.setMobileNo(mobileNo);
//            beanReq.setMchntSsn(mchntSsn);
            beanReq.setCvn(cvn2);
            FuiouAgreementPayHelper payHelper = new FuiouAgreementPayHelper();
            BindXmlBeanResp bindXmlBeanResp = payHelper.bindMsg(beanReq);
            String result = JSON.toJSONString(bindXmlBeanResp);
            logger.info(result);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf8");
            resp.getOutputStream().write(result.getBytes("utf8"));
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            resp.getOutputStream().write(e.getMessage().getBytes("UTF-8"));
        }
    }

    @RequestMapping(value = "/fuiou/bindCommit.htm",method = {RequestMethod.POST, RequestMethod.GET})
    public void bindCommit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String version = "1.0";
            String mchntSsn = req.getParameter("mchntssn");
            String tradeDate = req.getParameter("tradedate");
            String mchntcd = req.getParameter("mchntcd");
            String userid = req.getParameter("userid");
            String account = req.getParameter("account");
            String cardNo = req.getParameter("cardno");
            String idType = req.getParameter("idtype");
            String idCard = req.getParameter("idcard");
            String mobileNo = req.getParameter("mobileno");
            String cvn = req.getParameter("cvn");
            String msgcode = req.getParameter("msgcode");

            BindXmlBeanReq beanReq = new BindXmlBeanReq();
            beanReq.setVersion(version);
            beanReq.setTradeDate(tradeDate);
            beanReq.setMchntCd(mchntcd);
            beanReq.setUserId(userid);
            beanReq.setAccount(account);
            beanReq.setCardNo(cardNo);
            beanReq.setIdType(idType);
            beanReq.setIdCard(idCard);
            beanReq.setMobileNo(mobileNo);
            beanReq.setMchntSsn(mchntSsn);
            beanReq.setCvn(cvn);
            beanReq.setMsgCode(msgcode);

            FuiouAgreementPayHelper payHelper = new FuiouAgreementPayHelper();
            BindXmlBeanResp bindXmlBeanResp = payHelper.bindCommit(beanReq);
            String result = JSON.toJSONString(bindXmlBeanResp);
            logger.info(result);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf8");
            resp.getOutputStream().write(result.getBytes("utf8"));
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            resp.getOutputStream().write(e.getMessage().getBytes("utf8"));
        }
    }

    @RequestMapping(value = "/fuiou/unbind.htm",method = {RequestMethod.POST, RequestMethod.GET})
    public void unbind(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String userid = req.getParameter("userid");
            String protocolno = req.getParameter("protocolno");

            BindXmlBeanReq beanReq = new BindXmlBeanReq();
            beanReq.setUserId(userid);
            beanReq.setProtocolNo(protocolno);

            FuiouAgreementPayHelper payHelper = new FuiouAgreementPayHelper();
            BindXmlBeanResp bindXmlBeanResp = payHelper.unbind(beanReq);
            String result = JSON.toJSONString(bindXmlBeanResp);
            logger.info(result);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf8");
            resp.getOutputStream().write(result.getBytes("utf8"));
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            resp.getOutputStream().write(e.getMessage().getBytes("utf8"));
        }
    }

    @RequestMapping(value = "/fuiou/bindQuery.htm",method = {RequestMethod.POST, RequestMethod.GET})
    public void bindQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            BindXmlBeanReq beanReq = new BindXmlBeanReq();
            beanReq.setUserId(req.getParameter("userid"));
            FuiouAgreementPayHelper payHelper = new FuiouAgreementPayHelper();
            BindXmlBeanResp bindXmlBeanResp = payHelper.bindQuery(beanReq);
            String result = JSON.toJSONString(bindXmlBeanResp);
            logger.info(result);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf8");
            resp.getOutputStream().write(result.getBytes("utf8"));
        } catch (Exception e) {
            e.printStackTrace();
            resp.getOutputStream().write(e.getMessage().getBytes("utf8"));
        }
    }

    @RequestMapping(value = "/fuiou/payment.htm",method = {RequestMethod.POST, RequestMethod.GET})
    public void payment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String version = "1.0";
            String type = "03";
            String userid = req.getParameter("userid");
            String mchntorderid = req.getParameter("mchntorderid");
            String protocolno = req.getParameter("protocolno");
            String amt = req.getParameter("amt");
            String userip = req.getParameter("userip");
//            String needsendmsg = req.getParameter("needsendmsg");
            String backurl = req.getParameter("backurl");
            String rem1 = "";
            String rem2 = "";
            String rem3 = "";
            String orderalivetime = req.getParameter("orderalivetime");
            String cvn = req.getParameter("cvn");
            String exp = req.getParameter("exp");
            String cvn2 = "";

            OrderXmlBeanReq beanReq = new OrderXmlBeanReq();
            beanReq.setVersion(version);
            beanReq.setType(type);
            beanReq.setMchntOrderId(mchntorderid);
            beanReq.setUserId(userid);
            beanReq.setAmt(amt);
            beanReq.setProtocolNo(protocolno);
            beanReq.setCvn(cvn2);
            beanReq.setBackUrl(backurl);
            beanReq.setRem1(rem1);
            beanReq.setRem2(rem2);
            beanReq.setRem3(rem3);
            beanReq.setOrderAliveTime(orderalivetime);
            beanReq.setUserIp(userip);
//            beanReq.setNeedSendMsg(needsendmsg);

            FuiouAgreementPayHelper payHelper = new FuiouAgreementPayHelper();
            OrderXmlBeanResp orderXmlBeanResp = payHelper.repayment(beanReq);
            String result = JSON.toJSONString(orderXmlBeanResp);
            logger.info(result);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf8");
            String key = Global.getValue("protocol_mchntcd_key");
            if (orderXmlBeanResp.checkSign(key)) {
                resp.getOutputStream().write(result.getBytes("utf8"));
            }else {
                resp.getOutputStream().write(("sign异常|"+result).getBytes("utf8"));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            resp.getOutputStream().write(e.getMessage().getBytes("utf8"));
        }
    }
    @RequestMapping(value = "/fuiou/orderQry.htm",method = {RequestMethod.POST, RequestMethod.GET})
    public void orderQry(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            QryByFuiouOrderReq beanReq = new QryByFuiouOrderReq();
            beanReq.setOrderId(req.getParameter("orderId"));
            FuiouAgreementPayHelper payHelper = new FuiouAgreementPayHelper();
            QryByFuiouOrderResp bindXmlBeanResp = payHelper.queryOrderId(beanReq);
            String result = JSON.toJSONString(bindXmlBeanResp);
            logger.info(result);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf8");
            String key = Global.getValue("protocol_mchntcd_key");
            if (bindXmlBeanResp.checkSign(key)) {
                resp.getOutputStream().write(result.getBytes("utf8"));
            }else {
                resp.getOutputStream().write(("sign异常|"+result).getBytes("utf8"));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            resp.getOutputStream().write(e.getMessage().getBytes("utf8"));
        }
    }
    @RequestMapping(value = "/fuiou/checkResult.htm",method = {RequestMethod.POST, RequestMethod.GET})
    public void checkResult(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String version = req.getParameter("version");
            String mchntCd = req.getParameter("mchntCd");
            String mchntKey = req.getParameter("mchntKey");
            String mchntOrderId = req.getParameter("mchntOrderId");
            String rem1 = req.getParameter("rem1");
            String rem2 = req.getParameter("rem2");
            String rem3 = req.getParameter("rem3");

            OrderQryByMSsn data = new OrderQryByMSsn();
            data.setMchntCd(mchntCd);
            data.setMchntOrderId(mchntOrderId);
            data.setRem1(rem1);
            data.setRem2(rem2);
            data.setRem3(rem3);
            data.setVersion(version);

            FuiouAgreementPayHelper payHelper = new FuiouAgreementPayHelper();
            OrderQryResp bindXmlBeanResp = payHelper.checkResult(data);
            String result = JSON.toJSONString(bindXmlBeanResp);
            logger.info(result);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf8");
            String key = Global.getValue("protocol_mchntcd_key");
            if (bindXmlBeanResp.checkSign(key)) {
                resp.getOutputStream().write(result.getBytes("utf8"));
            }else {
                resp.getOutputStream().write(("sign异常|"+result).getBytes("utf8"));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            resp.getOutputStream().write(e.getMessage().getBytes("utf8"));
        }
    }

    @RequestMapping(value = "/fuiou/cardBinQuery.htm",method = {RequestMethod.POST, RequestMethod.GET})
    public void cardBinQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            BankCardReq beanreq = new BankCardReq();
            beanreq.setOno(req.getParameter("cardNo"));

            FuiouAgreementPayHelper payHelper = new FuiouAgreementPayHelper();
            BankCardResp bankCardResp = payHelper.cardBinQuery(beanreq);
            String result = JSON.toJSONString(bankCardResp);
            logger.info(result);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf8");
            String key = Global.getValue("protocol_mchntcd_key");
            if (bankCardResp.checkSign(key)) {
                resp.getOutputStream().write(result.getBytes("utf8"));
            }else {
                resp.getOutputStream().write(("sign异常|"+result).getBytes("utf8"));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            resp.getOutputStream().write(e.getMessage().getBytes("utf8"));
        }
    }

    @RequestMapping(value = "/fuiou/payQuery.htm",method = {RequestMethod.POST, RequestMethod.GET})
    public void payQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            QrytransreqModel model = new QrytransreqModel();
            model.setOrderno(req.getParameter("orderId"));
            model.setVer(FuiouConstant.DAIFU_PAYFOR_VERSION);
            model.setBusicd(FuiouConstant.DAIFU_PAYFOR_DAIFU_TYPE);
            model.setStartdt(FuiouDateUtil.getDate(NumberUtils.toInt(req.getParameter("startdt"),2)));
            model.setEnddt(FuiouDateUtil.getDate(NumberUtils.toInt(req.getParameter("enddt"),1)));

            FuiouHelper fuiouHelper = new FuiouHelper();
            QrytransrspModel qrytransrspModel = fuiouHelper.queryPayment(model);
            String result = JSON.toJSONString(qrytransrspModel);
            logger.info(result);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf8");
            resp.getOutputStream().write(result.getBytes("utf8"));
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            resp.getOutputStream().write(e.getMessage().getBytes("utf8"));
        }
    }

    @RequestMapping(value = "/fuiou/payCommit.htm",method = {RequestMethod.POST, RequestMethod.GET})
    public void payCommit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String orderNo = OrderNoUtil.getSerialNumber();
            PayforreqModel model = new PayforreqModel();
            model.setMerdt(DateUtil.dateStr7(new Date()));
            model.setVer(FuiouConstant.DAIFU_PAYFOR_VERSION);
            model.setAmt(AmtUtil.convertAmtToBranch(req.getParameter("amt")));
            model.setAccntno(req.getParameter("accntno"));
            model.setAccntnm(req.getParameter("accntnm"));
            model.setEntseq(req.getParameter("entseq"));
            model.setMemo(req.getParameter("memo"));
            model.setMobile(req.getParameter("mobile"));
            model.setOrderno(orderNo);
            model.setAddDesc(FuiouConstant.DAIFU_PAYFOR_ADDDESC);

            FuiouHelper fuiouHelper = new FuiouHelper();
            PayforrspModel qrytransrspModel = fuiouHelper.payment(model);
            String result = orderNo+"|"+JSON.toJSONString(qrytransrspModel);
            logger.info(result);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf8");
            resp.getOutputStream().write(result.getBytes("utf8"));
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            resp.getOutputStream().write(e.getMessage().getBytes("utf8"));
        }
    }

}

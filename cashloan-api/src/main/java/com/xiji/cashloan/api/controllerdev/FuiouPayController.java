package com.xiji.cashloan.api.controllerdev;

import com.alibaba.fastjson.JSON;
import com.xiji.cashloan.cl.model.pay.common.PayCommonUtil;
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
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.web.controller.BaseController;
import java.io.IOException;
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
            String userid = req.getParameter("userid");
            String account = req.getParameter("account");
            String cardNo = req.getParameter("cardno");
            String idCard = req.getParameter("idcard");
            String mobileNo = req.getParameter("mobileno");


            BindCardMsgVo vo = new BindCardMsgVo();
            vo.setUserId(userid);
            vo.setBankCardNo(cardNo);
            vo.setBankCardName(account);
            vo.setIdCard(idCard);
            vo.setMobile(mobileNo);
            BindCardMsgResponseVo responseVo = PayCommonUtil.bindMsg(vo);

            String result = JSON.toJSONString(responseVo);
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
            String mchntcd = req.getParameter("mchntssn");
            String userid = req.getParameter("userid");
            String account = req.getParameter("account");
            String cardNo = req.getParameter("cardno");
            String idType = req.getParameter("idtype");
            String idCard = req.getParameter("idcard");
            String mobileNo = req.getParameter("mobileno");
            String msgcode = req.getParameter("msgcode");

            BindCardMsgVo vo = new BindCardMsgVo();
            vo.setUserId(userid);
            vo.setBankCardNo(cardNo);
            vo.setBankCardName(account);
            vo.setIdCard(idCard);
            vo.setMobile(mobileNo);
            vo.setMsgCode(msgcode);
            vo.setOrderNo(mchntcd);
            BindCardMsgResponseVo responseVo = PayCommonUtil.bindCommit(vo);
            String result = JSON.toJSONString(responseVo);
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
            UnbindCardVo vo = new UnbindCardVo();
            vo.setProtocolNo(protocolno);
            vo.setUserId(userid);
            UnbindCardResponseVo responseVo = PayCommonUtil.unbind(vo);
            String result = JSON.toJSONString(responseVo);
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
            BindCardQueryVo vo = new BindCardQueryVo();
            vo.setUserId(req.getParameter("userid"));
            BindCardQueryResponseVo responseVo = PayCommonUtil.bindCardQuery(vo);
            String result = JSON.toJSONString(responseVo);
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

            String result = "";

            RepaymentReqVo vo = new RepaymentReqVo();
            vo.setAmount(NumberUtils.toDouble(amt));
            vo.setIp(userip);
            vo.setUserId(userid);
            vo.setProtocolNo(protocolno);
            vo.setBorrowOrderNo("borrowId");
            vo.setRemark("还款");
            vo.setTerminalId("xyz-id");
            vo.setTerminalType("OTHER");
            RepaymentResponseVo responseVo = PayCommonUtil.repayment(vo);

            result = JSON.toJSONString(responseVo);
            logger.info(result);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf8");
            resp.getOutputStream().write(result.getBytes("utf8"));
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            resp.getOutputStream().write(e.getMessage().getBytes("utf8"));
        }
    }
    @RequestMapping(value = "/fuiou/orderQry.htm",method = {RequestMethod.POST, RequestMethod.GET})
    public void orderQry(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            RepaymentQueryVo vo = new RepaymentQueryVo();
            vo.setPayPlatNo(req.getParameter("orderId"));
            RepaymentQueryResponseVo responseVo = PayCommonUtil.queryOrder(vo);
            String result = JSON.toJSONString(responseVo);
            logger.info(result);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf8");
            String key = Global.getValue("fuiou_protocol_mchntcd_key");
            resp.getOutputStream().write(result.getBytes("utf8"));
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            resp.getOutputStream().write(e.getMessage().getBytes("utf8"));
        }
    }
    @RequestMapping(value = "/fuiou/checkResult.htm",method = {RequestMethod.POST, RequestMethod.GET})
    public void checkResult(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String mchntOrderId = req.getParameter("mchntOrderId");
            RepaymentQueryVo vo = new RepaymentQueryVo();
            vo.setOrderNo(mchntOrderId);
            RepaymentQueryResponseVo responseVo = PayCommonUtil.queryOrder(vo);
            String result = JSON.toJSONString(responseVo);
            logger.info(result);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf8");
            String key = Global.getValue("fuiou_protocol_mchntcd_key");
            resp.getOutputStream().write(result.getBytes("utf8"));
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            resp.getOutputStream().write(e.getMessage().getBytes("utf8"));
        }
    }

    @RequestMapping(value = "/fuiou/cardBinQuery.htm",method = {RequestMethod.POST, RequestMethod.GET})
    public void cardBinQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            CardBinQueryVo vo = new CardBinQueryVo();
            vo.setBankCardNo(req.getParameter("cardNo"));
            CardBinQueryResponseVo responseVo = PayCommonUtil.queryCardBin(vo);
            String result = JSON.toJSONString(responseVo);
            logger.info(result);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf8");
            resp.getOutputStream().write(result.getBytes("utf8"));
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            resp.getOutputStream().write(e.getMessage().getBytes("utf8"));
        }
    }

    @RequestMapping(value = "/fuiou/payQuery.htm",method = {RequestMethod.POST, RequestMethod.GET})
    public void payQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
//            QrytransreqModel model = new QrytransreqModel();
//            model.setOrderno(req.getParameter("orderId"));
//            model.setVer(FuiouConstant.DAIFU_PAYFOR_VERSION);
//            model.setBusicd(FuiouConstant.DAIFU_PAYFOR_DAIFU_TYPE);
//            model.setStartdt(FuiouDateUtil.getDate(NumberUtils.toInt(req.getParameter("startdt"),2)));
//            model.setEnddt(FuiouDateUtil.getDate(NumberUtils.toInt(req.getParameter("enddt"),1)));
            PaymentQueryVo vo = new PaymentQueryVo();
            vo.setOrderNo(req.getParameter("orderId"));
            PaymentQueryResponseVo responseVo = PayCommonUtil.queryPayment(vo);

            String result = JSON.toJSONString(responseVo);
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
//            String orderNo = OrderNoUtil.getSerialNumber();
//            PayforreqModel model = new PayforreqModel();
//            model.setMerdt(DateUtil.dateStr7(new Date()));
//            model.setVer(FuiouConstant.DAIFU_PAYFOR_VERSION);
//            model.setAmt(AmtUtil.convertAmtToBranch(req.getParameter("amt")));
//            model.setAccntno(req.getParameter("accntno"));
//            model.setAccntnm(req.getParameter("accntnm"));
//            model.setEntseq(req.getParameter("entseq"));
//            model.setMemo(req.getParameter("memo"));
//            model.setMobile(req.getParameter("mobile"));
//            model.setOrderno(orderNo);
//            model.setAddDesc(FuiouConstant.DAIFU_PAYFOR_ADDDESC);

            PaymentReqVo vo = new PaymentReqVo();
            if ("dev".equals(Global.getValue("app_environment"))) {
                vo.setAmount(3.0);
            } else {
                vo.setAmount(NumberUtils.toDouble(req.getParameter("amt")));
            }
            vo.setBankCardName(req.getParameter("accntnm"));
            vo.setBankCardNo(req.getParameter("accntno"));
            vo.setBorrowOrderNo("borrowid");
            vo.setMobile(req.getParameter("mobile"));
            PaymentResponseVo responseVo = PayCommonUtil.payment(vo);

            String result = "";
            result = responseVo.getOrderNo()+"|"+JSON.toJSONString(responseVo);
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

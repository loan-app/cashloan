package com.xiji.cashloan.cl.model.pay.common;

import com.xiji.cashloan.cl.model.pay.common.biz.FuiouPayBiz;
import com.xiji.cashloan.cl.model.pay.common.biz.HeliPayBiz;
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
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.util.StringUtil;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: king
 * @Date: 2019/1/25 18:27
 * @Description:
 */
public class PayCommonUtil{

    private static final Map<String, PayCommon> payMode = new HashMap<>();

    static {
        payMode.put(PayConstant.PAY_MODE_FUIOU, new FuiouPayBiz());
        payMode.put(PayConstant.PAY_MODE_HELIPAY, new HeliPayBiz());
    }

    public static PaymentResponseVo payment(PaymentReqVo vo) {
        return payMode.get(payKey()).payment(vo);
    }
    public static PaymentQueryResponseVo queryPayment(PaymentQueryVo vo){
        return payMode.get(payKey()).queryPayment(vo);
    }
    public static BindCardMsgResponseVo bindMsg(BindCardMsgVo vo){
        return payMode.get(payKey()).bindMsg(vo);
    }
    public static UnbindCardResponseVo unbind(UnbindCardVo vo){
        return payMode.get(payKey()).unbind(vo);
    }
    public static BindCardMsgResponseVo bindCommit(BindCardMsgVo vo){
        return payMode.get(payKey()).bindCommit(vo);
    }
    public static RepaymentResponseVo repayment(RepaymentReqVo vo){
        return payMode.get(payKey()).repayment(vo);
    }
    public static RepaymentQueryResponseVo queryOrder(RepaymentQueryVo vo){
        return payMode.get(payKey()).queryOrder(vo);
    }
    public static BindCardQueryResponseVo bindCardQuery(BindCardQueryVo vo){
        return payMode.get(payKey()).bindCardQuery(vo);
    }
    public static CardBinQueryResponseVo queryCardBin(CardBinQueryVo vo){
        return payMode.get(payKey()).queryCardBin(vo);
    }


    public static String payKey() {
        String value = Global.getValue("pay_model_select");
        if (StringUtil.isBlank(value)) {
            return PayConstant.PAY_MODE_FUIOU;
        }
        return value;
    }

    public static boolean success(String status) {
        return StringUtil.equals(status, PayConstant.RESULT_SUCCESS);
    }

    public static boolean needCheck(String status) {
        return StringUtil.equals(status, PayConstant.STATUS_NEED_CHECK);
    }

    public static boolean fail(String status) {
        return StringUtil.equals(status, PayConstant.STATUS_FAIL);
    }
}

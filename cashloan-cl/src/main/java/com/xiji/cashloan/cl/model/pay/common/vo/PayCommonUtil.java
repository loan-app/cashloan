package com.xiji.cashloan.cl.model.pay.common.vo;

import com.xiji.cashloan.cl.model.pay.common.biz.FuiouPayBiz;
import com.xiji.cashloan.cl.model.pay.common.biz.HeliPayBiz;
import com.xiji.cashloan.cl.model.pay.common.constant.PayConstant;
import com.xiji.cashloan.cl.model.pay.common.vo.request.PaymentReqVo;
import com.xiji.cashloan.cl.model.pay.common.vo.response.PaymentResponseVo;
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

    public static String payKey() {
        String value = Global.getValue("pay_model_select");
        if (StringUtil.isBlank(value)) {
            return PayConstant.PAY_MODE_FUIOU;
        }
        return value;
    }

}

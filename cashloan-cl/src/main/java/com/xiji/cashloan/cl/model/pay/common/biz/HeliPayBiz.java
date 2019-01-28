package com.xiji.cashloan.cl.model.pay.common.biz;

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

/**
 * @Auther: king
 * @Date: 2019/1/26 15:13
 * @Description:
 */
public class HeliPayBiz implements PayCommon {

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
        return null;
    }

    @Override
    public UnbindCardResponseVo unbind(UnbindCardVo vo) {
        return null;
    }

    @Override
    public BindCardMsgResponseVo bindCommit(BindCardMsgVo vo) {
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
}

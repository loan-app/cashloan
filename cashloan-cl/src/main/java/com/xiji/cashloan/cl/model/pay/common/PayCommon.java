package com.xiji.cashloan.cl.model.pay.common;

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

/**
 * @Auther: king
 * @Date: 2019/1/26 15:14
 * @Description:
 */
public interface PayCommon {
    PaymentResponseVo payment(PaymentReqVo vo);
    PaymentQueryResponseVo queryPayment(PaymentQueryVo vo);
    BindCardMsgResponseVo bindMsg(BindCardMsgVo vo);
    UnbindCardResponseVo unbind(UnbindCardVo vo);
    BindCardMsgResponseVo bindCommit(BindCardMsgVo vo);
    RepaymentResponseVo repayment(RepaymentReqVo vo);
    RepaymentQueryResponseVo queryOrder(RepaymentQueryVo vo);
    BindCardQueryResponseVo bindCardQuery(BindCardQueryVo vo);
    CardBinQueryResponseVo queryCardBin(CardBinQueryVo vo);
}

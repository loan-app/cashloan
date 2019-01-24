package com.xiji.cashloan.cl.model.pay.helipay;

import com.alibaba.fastjson.JSON;
import com.xiji.cashloan.cl.model.pay.BasePay;
import com.xiji.cashloan.cl.model.pay.helipay.constant.HelipayConstant;
import com.xiji.cashloan.cl.model.pay.helipay.util.HelipayUtil;
import com.xiji.cashloan.cl.model.pay.helipay.vo.request.PayForReqVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.response.HeliPayForPaymentResultVo;
import com.xiji.cashloan.core.common.util.HttpsUtil;
import com.xiji.cashloan.core.common.util.OrderNoUtil;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Auther: king
 * @Date: 2019/1/24 16:51
 * @Description:
 */
public class HelipayHelper extends BasePay {
    public static final Logger logger = LoggerFactory.getLogger(HelipayHelper.class);

    public HeliPayForPaymentResultVo payment(PayForReqVo reqVo) {
        HeliPayForPaymentResultVo result = new HeliPayForPaymentResultVo();
        reqVo.setCustomerNumber(HelipayUtil.customerNumber());
        reqVo.setBizType(HelipayConstant.BTYPE_Transfer);
        String resp = null;
        try {
            Map<String,String> paramters = reqVo.paymentParams();
            saveReqLog(reqVo.getBizType(),reqVo.getOrderId(), "", JSON.toJSONString(reqVo));
            if (HelipayUtil.switchHelipay()) {
                resp = HttpsUtil.postClient(HelipayUtil.transferUrl(), paramters);
            }else {
                if (logger.isDebugEnabled()) {
                    logger.debug("关闭支付,模拟返回结果");
                }
                resp = "";
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        if (isException(resp)) {
            result.setRt2_retCode(resp);
        } else {
            result = HelipayUtil.convertPaymentResultVo(resp);
        }

        modifyReqLog(reqVo.getOrderId(),resp);
        return result;
    }

    public HeliPayForPaymentResultVo queryPayment(PayForReqVo reqVo) {
        HeliPayForPaymentResultVo result = new HeliPayForPaymentResultVo();
        reqVo.setCustomerNumber(HelipayUtil.customerNumber());
        reqVo.setBizType(HelipayConstant.BTYPE_TransferQuery);
        String resp = null;
        String orderNo = OrderNoUtil.getSerialNumber();
        try {

            Map<String,String> paramters = reqVo.payQueryParams();
            saveReqLog(reqVo.getBizType(),orderNo, "", JSON.toJSONString(reqVo));
            if (HelipayUtil.switchHelipay()) {
                resp = HttpsUtil.postClient(HelipayUtil.transferUrl(), paramters);
            }else {
                if (logger.isDebugEnabled()) {
                    logger.debug("关闭支付,模拟返回结果");
                }
                resp = "";
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        if (isException(resp)) {
            result.setRt2_retCode(resp);
        } else {
            result = HelipayUtil.convertPaymentResultVo(resp);
        }

        modifyReqLog(orderNo,resp);
        return result;
    }

    // ------------------------------------
    /**
     * 1、绑卡发短信接口
     * 2、绑卡接口
     * 3、绑卡支付接口
     * 4、支付查询接口
     * 5、解除绑卡接口
     * 6、用户绑定银行卡信息查询（仅限于交易卡）
     */

}

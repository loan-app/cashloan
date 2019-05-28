package com.xiji.cashloan.api.controller;

import com.xiji.cashloan.cl.model.pay.common.PayCommonUtil;
import com.xiji.cashloan.cl.model.pay.common.vo.response.RepaymentResponseVo;
import com.xiji.cashloan.cl.service.BorrowRepayService;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.util.ServletUtils;
import com.xiji.cashloan.core.common.web.controller.BaseController;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import tool.util.StringUtil;

/**
 * 主动还款 Controller
 * @author wnb
 * @version 1.0
 * @date 2018/12/03
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Scope("prototype")
@Controller
public class ClPaymentController extends BaseController {
	public static final Logger logger = LoggerFactory.getLogger(ClPaymentController.class);
	@Resource
	private BorrowRepayService borrowRepayService;

	/**
	 * 还款方法 ：
	 * 	若参数封装成功，还款状态变更为还款处理中
	 * @param borrowId
	 * @param ip
	 */
	@RequestMapping(value = "/api/act/borrow/repay/repayment.htm", method = RequestMethod.POST)
	public void repayment(@RequestParam(value="borrowId") Long borrowId,
						  @RequestParam(value="ip") String ip) {
		long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
		Map<String, String> sdkParams = borrowRepayService.repayment(borrowId, userId, ip);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, sdkParams);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "参数封装成功");
		ServletUtils.writeToResponse(response,result);
	}

	/**
	 * 主动还款 同步响应
	 * @param payResult
	 * @param payOrderNo
	 */
	@RequestMapping(value = "/api/act/borrow/repay/repaymentReturn.htm", method = RequestMethod.POST)
	public void repaymentReturn(@RequestParam(value = "payResult") String payResult, @RequestParam(value = "payOrderNo") String payOrderNo) {
		borrowRepayService.repaymentReturn(payResult, payOrderNo);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "处理成功");
		ServletUtils.writeToResponse(response,result);
	}

	/**
	 * 确认还款
	 * @param borrowId
	 * @param ip
	 * @param type 1-代扣还款，2-展期
	 */
	@RequestMapping(value = "/api/act/borrow/repay/confirmPay.htm", method = RequestMethod.POST)
	public void confirmPay(@RequestParam(value="borrowId") Long borrowId,
						   @RequestParam(value="ip",required = false) String ip,
						   @RequestParam(value="type") String type) {
		long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
		Map<String, String> payMap = borrowRepayService.confirmPay(borrowId, userId, ip, type);
		Map<String,Object> result = new HashMap<String,Object>();
		if (StringUtil.equals(payMap.get("code"), "12")) {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		}
		result.put(Constant.RESPONSE_CODE_MSG, payMap.get("msg"));
		ServletUtils.writeToResponse(response,result);
	}

	/**
	 * 笔笔验证
	 * 获取请求参数
	 *
	 */
	@RequestMapping(value = "/api/act/borrow/bibiVerify/getReqParameter.htm", method = RequestMethod.POST)
	public void getReqParameter(@RequestParam(value="borrowId") Long borrowId,
                                @RequestParam(value="ip",required = false) String ip,
                                @RequestParam(value="type") String type)throws Exception{
        long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
        Map<String,String> payMap = borrowRepayService.getReqParameter(borrowId, userId, ip, type);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put(Constant.RESPONSE_DATA, payMap);
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "参数封装成功");
        ServletUtils.writeToResponse(response,result);
	}

    /**
     *
     * 获取笔笔验证响应参数结果
     */
    @RequestMapping(value = "/api/act/borrow/bibiVerify/saveResParameter.htm",method = RequestMethod.POST)
    public void saveResParameter(@RequestParam("body") String body, HttpServletRequest request){

        Map<String, String> payMap= borrowRepayService.saveResParameter(body);
        Map<String,Object> result = new HashMap<String,Object>();

        if (StringUtil.equals(payMap.get("code"), "12")) {
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
        } else {
            result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        }
        result.put(Constant.RESPONSE_CODE_MSG, payMap.get("msg"));
        ServletUtils.writeToResponse(response,result);

    }

    /**
     * 支付选择接口
     * @param userId
     * @throws Exception
     */
    @RequestMapping(value = "/api/act/payment/select.htm")
    public void select(
            @RequestParam(value="userId", required = true) Long userId)throws Exception{
        Map<String,Object> data = new HashMap<>();
        String payType = Global.getValue("fuiou_payment_select");
        data.put("payType",1);//默认使用协议支付
        //判断是否为富友支付
        if("fuiou".equals(PayCommonUtil.payCompany(userId))){
            if (StringUtil.equals("1", payType)) {
                data.put("payType",1);//协议支付
            }else if (StringUtil.equals("2", payType)){
                data.put("payType",2);//笔笔验证
            }
        }
        Map<String,Object> result = new HashMap<String,Object>();
        result.put(Constant.RESPONSE_DATA, data);
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
        ServletUtils.writeToResponse(response,result);
    }

}
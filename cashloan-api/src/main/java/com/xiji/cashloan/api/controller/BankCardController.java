package com.xiji.cashloan.api.controller;

import com.alibaba.fastjson.JSONArray;
import com.xiji.cashloan.cl.domain.BankCard;
import com.xiji.cashloan.cl.model.BankCardModel;
import com.xiji.cashloan.cl.model.pay.fuiou.agreement.BankCardReq;
import com.xiji.cashloan.cl.model.pay.fuiou.agreement.BankCardResp;
import com.xiji.cashloan.cl.model.pay.fuiou.agreement.BindXmlBeanReq;
import com.xiji.cashloan.cl.model.pay.fuiou.agreement.BindXmlBeanResp;
import com.xiji.cashloan.cl.model.pay.fuiou.util.FuiouAgreementPayHelper;
import com.xiji.cashloan.cl.model.pay.lianlian.AgreementList;
import com.xiji.cashloan.cl.model.pay.lianlian.QueryAuthSignModel;
import com.xiji.cashloan.cl.model.pay.lianlian.constant.LianLianConstant;
import com.xiji.cashloan.cl.model.pay.lianlian.util.LianLianHelper;
import com.xiji.cashloan.cl.service.BankCardService;
import com.xiji.cashloan.cl.service.BorrowRepayService;
import com.xiji.cashloan.cl.service.ClBorrowService;
import com.xiji.cashloan.cl.service.ClSmsService;
import com.xiji.cashloan.cl.service.FourElementsLogService;
import com.xiji.cashloan.cl.service.UserAuthService;
import com.xiji.cashloan.cl.service.UserBlackInfoService;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.exception.BussinessException;
import com.xiji.cashloan.core.common.util.OrderNoUtil;
import com.xiji.cashloan.core.common.util.ServletUtils;
import com.xiji.cashloan.core.common.web.controller.BaseController;
import com.xiji.cashloan.core.domain.Borrow;
import com.xiji.cashloan.core.domain.User;
import com.xiji.cashloan.core.domain.UserBaseInfo;
import com.xiji.cashloan.core.model.BorrowModel;
import com.xiji.cashloan.core.service.CloanUserService;
import com.xiji.cashloan.core.service.UserBaseInfoService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import tool.util.BeanUtil;
import tool.util.DateUtil;
import tool.util.NumberUtil;
import tool.util.StringUtil;

 /**
 * 银行卡Controller
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2018/12/03
  *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Scope("prototype")
@Controller
@SuppressWarnings("unused")
public class BankCardController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(BankCardController.class);

	@Resource
	private BankCardService bankCardService;

	@Resource
	private CloanUserService cloanUserService;
	
	@Resource
	private UserBaseInfoService userInfoService;
	
	@Resource
	private UserAuthService userAuthService;
	
	@Resource
	private BorrowRepayService borrowRepayService;
	
	@Resource
	private ClBorrowService clBorrowService;
	
	@Resource
	private FourElementsLogService fourElementsLogService;
	
	@Resource
	private UserBlackInfoService userBlackInfoService;

	@Resource
 	private ClSmsService clSmsService;
	/**
	 * @description 获取单个用户的所有绑定银行卡
	 * @param userId
	 * @author chenxy
	 * @return void
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/api/act/mine/bankCard/getBankCardList.htm")
	public void getBankCardList(@RequestParam(value = "userId") String userId) {
		
		BankCardModel model = new BankCardModel();
		model.setChangeAble(BankCardModel.STATE_ENABLE);
		BankCard card = bankCardService.getBankCardByUserId(NumberUtil.getLong(userId));
		//校验能否更换银行卡
		List<Borrow> list = clBorrowService.findUserUnFinishedBorrow(NumberUtil.getLong(userId));
	    if (null != list && !list.isEmpty()) {
	    	Borrow borrow = list.get(0);
	    	if(borrow != null && !BorrowModel.STATE_REPAY_FAIL.equals(borrow.getState())){
	    		model.setChangeAble(BankCardModel.STATE_DISABLE);
	    	}
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		if (card != null) {
			BeanUtil.copyProperties(card, model);
			result.put(Constant.RESPONSE_DATA, model);
		}
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response, result);
	}

	private String check(String phone,String type){
		 String message = null;
		 if(com.xiji.cashloan.core.common.util.StringUtil
			 .isBlank(phone) || com.xiji.cashloan.core.common.util.StringUtil.isBlank(type)){
			 message = "参数不能为空";
		 } else if(!com.xiji.cashloan.core.common.util.StringUtil.isPhone(phone)){
			 message = "手机号码格式有误";
		 } else {
			 if(com.xiji.cashloan.core.common.util.StringUtil.equals("register", type)){
				 // 当日最大注册用户数
				 long todayCount = cloanUserService.todayCount();
				 String dayRegisterMax_ = Global.getValue("day_register_max");
				 if(com.xiji.cashloan.core.common.util.StringUtil.isNotBlank(dayRegisterMax_)){
					 int dayRegisterMax = Integer.parseInt(dayRegisterMax_);
					 if(dayRegisterMax > 0 && todayCount >= dayRegisterMax){
						 message = "今天注册人数已达到上限";
					 }
				 }

				 if (clSmsService.findUser(phone)>0) {
					 message = "该手机号已经注册";
				 }
			 }

			 if (com.xiji.cashloan.core.common.util.StringUtil.equals("findReg", type)) {
				 if (clSmsService.findUser(phone)<1) {
					 message = "该手机号不存在";
				 }
			 }

			 if (message==null&&clSmsService.countDayTime(phone, type) <= 0) {
				 message = "获取短信验证码过于频繁，请明日再试";
			 }
		 }
		 return message;
	 }
	 /**
	 * @description 验证码
	 * @param userId
	 * @author chenxy
	 * @return void
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/api/act/mine/bankCard/getCaptcha.htm")
	public void getCaptcha(@RequestParam(value = "bank", required = false) String bank,
		@RequestParam(value = "cardNo", required = true) String cardNo,
		@RequestParam(value = "userId", required = true) String userId,
		@RequestParam(value = "mobileNo", required = true) String mobileNo){
		//校验更换绑卡时，是否存在未结束的借款
		String bindCardSwitch = Global.getValue("bindCardSwitch");//10支持接口结束前换卡，20不支持
		if (StringUtil.equals(bindCardSwitch, "10")) {
			List<Borrow> list = clBorrowService.findUserUnFinishedBorrow(NumberUtil.getLong(userId));
			if (null != list && !list.isEmpty()) {
				Borrow borrow = list.get(0);
				if(borrow != null && BorrowModel.STATE_REPAY_FAIL != borrow.getState()){
					throw new BussinessException("借款结束前不能更换银行卡");
				}
			}
		}

		if(!StringUtil.isNumber(cardNo) || cardNo.length() <15 || cardNo.length() > 30   ){
			Map<String,Object> result=new HashMap<String,Object>();
			result.put(Constant.RESPONSE_CODE,Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG,"银行卡号格式有误");
			ServletUtils.writeToResponse(response,result);
			return ;
		}
		Map<String,Object> result=new HashMap<String,Object>();
		User user = cloanUserService.getById(NumberUtil.getLong(userId));
		UserBaseInfo baseInfo = userInfoService.findByUserId(NumberUtil.getLong(userId));
		String type = "bindCard";
		String phone = mobileNo;
		String message = this.check(phone, type);

		Map<String,Object> data = new HashMap<String,Object>();
		if (StringUtil.isBlank(message)) {
			long countDown = clSmsService.findTimeDifference(phone, type);
			if (countDown != 0) {
				data.put("countDown", countDown);
				data.put("state", "20");
				message = "获取短信验证码过于频繁，请稍后再试";
				logger.info("发送短信，phone：" + phone + "， type：" + type + "，发送前的校验结果message：" + message);
			} else {
				logger.info("发送短信，phone：" + phone + "， type：" + type + "，准备发送");
				BindXmlBeanReq beanReq = new BindXmlBeanReq();
				beanReq.setUserId(user.getUuid());
				beanReq.setTradeDate(DateUtil.dateStr7(new Date()));
				beanReq.setMchntSsn(OrderNoUtil.getSerialNumber());
				beanReq.setAccount(baseInfo.getRealName());
				beanReq.setCardNo(cardNo);
				beanReq.setIdType("0");
				beanReq.setIdCard(baseInfo.getIdNo());
				beanReq.setMobileNo(baseInfo.getPhone());

				FuiouAgreementPayHelper payHelper = new FuiouAgreementPayHelper();
				BindXmlBeanResp resp = payHelper.bindMsg(beanReq);//调富有接口发送验证码，同时验证四要素，卡号，证件类型，身份证，手机号，

				if (resp.checkReturn()) {
					data.put("state", "10");
					data.put("orderNo", beanReq.getMchntSsn());
					result.put(Constant.RESPONSE_DATA, data);
					result.put(Constant.RESPONSE_CODE,Constant.SUCCEED_CODE_VALUE);
					result.put(Constant.RESPONSE_CODE_MSG,"已发送，请注意查收");
					ServletUtils.writeToResponse(response, result);
				}else {
					result.put(Constant.RESPONSE_CODE,Constant.FAIL_CODE_VALUE);
					data.put("countDown", countDown);
					if (StringUtil.isNotEmpty(resp.getResponseMsg())) {
						result.put(Constant.RESPONSE_CODE_MSG,resp.getResponseMsg());
						message = resp.getResponseMsg();
					} else {
						result.put(Constant.RESPONSE_CODE_MSG,"发送失败");
						message = "发送失败";
					}
				}
				clSmsService.saveSmsBankCard(resp.checkReturn(),phone, type,resp.getResponseMsg());
			}
		}
		if (StringUtil.isNotEmpty(message)) {
			logger.info("发送短信，phone：" + phone + "， type：" + type + "，发送前的校验结果message：" + message);
			data.put("state", "20");
			data.put("message", message);
			result.put(Constant.RESPONSE_DATA, data);
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "发送失败");
		}
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 请求签约
	 * @param bank
	 * @param cardNo
	 * @param userId
	 */
	@RequestMapping(value = "/api/act/mine/bankCard/authSign.htm", method = RequestMethod.POST)
	public void authSign(
        @RequestParam(value = "bank", required = false) String bank,
        @RequestParam(value = "cardNo", required = true) String cardNo,
        @RequestParam(value = "userId", required = true) String userId,
        @RequestParam(value = "captcha", required = true) String captcha,
        @RequestParam(value = "orderNo", required = true) String orderNo,
		@RequestParam(value = "mobileNo", required = true) String mobileNo) {
        long userid = NumberUtil.getLong(userId);
        //校验更换绑卡时，是否存在未结束的借款
        String bindCardSwitch = Global.getValue("bindCardSwitch");//10支持接口结束前换卡，20不支持
        if (StringUtil.equals(bindCardSwitch, "10")) {
            List<Borrow> list = clBorrowService.findUserUnFinishedBorrow(userid);
            if (null != list && !list.isEmpty()) {
                Borrow borrow = list.get(0);
                if(borrow != null && BorrowModel.STATE_REPAY_FAIL != borrow.getState()){
                    throw new BussinessException("借款结束前不能更换银行卡");
                }
            }
        }

        if(!StringUtil.isNumber(cardNo) || cardNo.length() <15 || cardNo.length() > 30   ){
            Map<String,Object> result=new HashMap<String,Object>();
            result.put(Constant.RESPONSE_CODE,Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG,"银行卡号格式有误");
            ServletUtils.writeToResponse(response,result);
            return ;
        }
		if(StringUtil.isEmpty(captcha)){
			Map<String,Object> result=new HashMap<String,Object>();
			result.put(Constant.RESPONSE_CODE,Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG,"验证码为空！");
			ServletUtils.writeToResponse(response,result);
			return ;
		}

        User user = cloanUserService.getById(userid);
        UserBaseInfo baseInfo = userInfoService.findByUserId(userid);

        //黑名单操作
        userBlackInfoService.validUserBlackInfo(baseInfo);
        //签约，即授权绑卡
        BindXmlBeanReq beanReq = new BindXmlBeanReq();
        beanReq.setUserId(user.getUuid());
        beanReq.setTradeDate(DateUtil.dateStr7(new Date()));
        beanReq.setMchntSsn(orderNo);//需要使用发送验证码时的流水号
        beanReq.setAccount(baseInfo.getRealName());
        beanReq.setCardNo(cardNo);
        beanReq.setIdType("0");
        beanReq.setIdCard(baseInfo.getIdNo());
        beanReq.setMobileNo(mobileNo);
        beanReq.setMsgCode(captcha);//验证码

        FuiouAgreementPayHelper payHelper = new FuiouAgreementPayHelper();
        BindXmlBeanResp resp = payHelper.bindCommit(beanReq);//绑卡
        if (resp.checkReturn()) {
            String agreeNo = resp.getProtocolNo();
            if (!StringUtil.isNotEmpty(agreeNo)) {//没有协议号的情况下，需要重新查一下
                BindXmlBeanReq bindQuery = new BindXmlBeanReq();
                beanReq.setUserId(user.getUuid());
                BindXmlBeanResp bindResp = payHelper.bindQuery(bindQuery);
                if (bindResp.checkReturn()) {
                    agreeNo = bindResp.getProtocolNo();
                }
            }
            BankCard bankCard = bankCardService.getBankCardByUserId(userid);
            boolean flag = saveOrUpdate(userid, cardNo, bankCard, agreeNo,mobileNo);
            if (flag) {
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("userId", userId);
                paramMap.put("bankCardState", "30");
                userAuthService.updateByUserId(paramMap);
            }
            // 签约成功之后 ，查询是否有未还款的借款，进行重新授权
            //无需授权，绑卡成功即可
//            borrowRepayService.authSignApply(userid);

            Map<String,Object> result=new HashMap<String,Object>();
            result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
            ServletUtils.writeToResponse(response,result);
        }else {
            Map<String,Object> result=new HashMap<String,Object>();
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
            ServletUtils.writeToResponse(response,result);
        }
	}
	
	/**
	 * 签约响应回调
	 * 
	 * @param uuid
	 * @param signResult
	 * @param userId
	 */
	@RequestMapping(value = "/api/act/mine/bankCard/authSignReturn.htm", method = RequestMethod.POST)
	public void authSignReturn(
			@RequestParam(value = "uuid", required = true) String uuid,
			@RequestParam(value = "agreeNo", required = true) String agreeNo,
			@RequestParam(value = "signResult", required = true) String signResult,
			@RequestParam(value = "userId", required = true) Long userId,
			@RequestParam(value = "bank", required = false) String bank,
			@RequestParam(value = "cardNo", required = true) String cardNo) {
		Map<String, Object> result = new HashMap<String, Object>();

		if (StringUtil.isEmpty(uuid) || StringUtil.isEmpty(signResult)) {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "参数不能为空");
			ServletUtils.writeToResponse(response, result);
			return;
		}

		BankCard bankCard = bankCardService.getBankCardByUserId(userId);

		boolean flag = false;
		if (LianLianConstant.RESULT_SUCCESS.equals(signResult)) {
			flag = saveOrUpdate(userId, cardNo, bankCard, agreeNo,"");
		} else if (LianLianConstant.RESULT_PROCESSING.equals(signResult)) {
			String orderNo = OrderNoUtil.getSerialNumber();
			QueryAuthSignModel model = new QueryAuthSignModel(orderNo);
			model.setUser_id(StringUtil.isNull(uuid));
			LianLianHelper helper = new LianLianHelper();
			model = (QueryAuthSignModel) helper.queryAuthSign(model);
			List<AgreementList> agreementList = JSONArray.parseArray(model.getAgreement_list(), AgreementList.class);
			if (null != agreementList && !agreementList.isEmpty()) {
				AgreementList agreement = agreementList.get(0);
				flag = saveOrUpdate(userId, cardNo, bankCard, agreement.getNo_agree(),"");
			}
		}

		if (flag) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userId", userId);
			paramMap.put("bankCardState", "30");
			userAuthService.updateByUserId(paramMap);
		}

		// 签约成功之后 ，查询是否有未还款的借款，进行重新授权
		borrowRepayService.authSignApply(userId);	
		
		if (flag) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "保存成功");
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "保存失败");
		}

		ServletUtils.writeToResponse(response, result);
	}

	private boolean saveOrUpdate(Long userId, String cardNo, BankCard bankCard, String agreeNo,String mobileNo) {
		boolean flag = false;
        BankCardReq beanreq = new BankCardReq();
        beanreq.setOno(cardNo);

        FuiouAgreementPayHelper payHelper = new FuiouAgreementPayHelper();
        BankCardResp resp = payHelper.cardBinQuery(beanreq);
        String bank = "";
		String key = Global.getValue("fuiou_protocol_mchntcd_key");
        if (resp.checkReturn() && resp.checkSign(key)) {
			bank = StringUtil.isNull(resp.getCnm());
        }
		
		if (null == bankCard) {
			BankCard card = new BankCard();
			card.setCardNo(cardNo);
			card.setBindTime(DateUtil.getNow());
			card.setUserId(userId);
			card.setBank(bank);
			card.setAgreeNo(agreeNo);
			card.setPhone(mobileNo);
			flag = bankCardService.save(card);
		} else {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", bankCard.getId());
			paramMap.put("bank", bank);
			paramMap.put("cardNo", cardNo);
			paramMap.put("agreeNo", agreeNo);
			flag = bankCardService.updateSelective(paramMap);
		}
		return flag;
	}
}

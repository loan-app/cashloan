package com.xiji.cashloan.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiji.cashloan.cl.domain.UserAuth;
import com.xiji.cashloan.cl.domain.Zhima;
import com.xiji.cashloan.cl.model.zmxy.authorize.AuthInfoQuery;
import com.xiji.cashloan.cl.model.zmxy.base.ZmQueryCreator;
import com.xiji.cashloan.cl.service.UserAuthService;
import com.xiji.cashloan.cl.service.ZhimaService;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.util.ServletUtils;
import com.xiji.cashloan.core.common.util.StringUtil;
import com.xiji.cashloan.core.common.web.controller.BaseController;
import com.xiji.cashloan.core.domain.UserBaseInfo;
import com.xiji.cashloan.core.service.UserBaseInfoService;

 /**
 * 芝麻信用Controller
 * @author wnb
 * @version 1.0.0
 * @date 2018/12/03
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Scope("prototype")
@Controller
public class ZhimaController extends BaseController {

	@Resource
	private ZhimaService zhimaService;
	
	@Resource
	private UserAuthService userAuthService;
	
	@Resource
	private UserBaseInfoService userInfoService;
	
	@RequestMapping(value = "/api/act/mine/zhima/view.htm")
	public void view(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		
		long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
		paramMap.put("userId", userId);
		UserAuth userauth = userAuthService.getUserAuth(paramMap);
		if(userauth != null && "30".equals(userauth.getZhimaState())){
			Zhima zhima = zhimaService.findByUserId(userId);
			result.put(Constant.RESPONSE_DATA, zhima); 
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "查询芝麻信用信息成功");
		} else {
	        result.put(Constant.RESPONSE_CODE, Constant.PERM_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "请先进行芝麻信用授权");	
		}

		ServletUtils.writeToResponse(response,result);
	}
	
	
	@RequestMapping(value = "/api/act/mine/zhima/authorize.htm")
	public void authorize(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String,Object>();
		
		long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
		paramMap.put("userId", userId);
		UserAuth userauth = userAuthService.getUserAuth(paramMap);
		if(userauth != null && "30".equals(userauth.getZhimaState())){
			result.put(Constant.RESPONSE_CODE, Constant.PERM_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "该用户已授权");	
		} else {
			ZmQueryCreator zmQueryCreator = new ZmQueryCreator();
	        AuthInfoQuery authInfoQuery = zmQueryCreator.create(AuthInfoQuery.class);
			if (userauth != null && "30".equals(userauth.getIdState())){
	        	UserBaseInfo userinfo = userInfoService.findSelective(paramMap);
				if (userinfo != null && StringUtil.isNotBlank(userinfo.getIdNo()) 
						&& StringUtil.isNotBlank(userinfo.getRealName())){
					// H5请求芝麻信用认证地址
					String url = authInfoQuery.getPhoneAuthorizeUrl(userinfo.getIdNo(), userinfo.getRealName(), userinfo.getUserId().toString());
					Map<String,Object> r=new HashMap<String,Object>(); 
					r.put("url", url); 
					result.put(Constant.RESPONSE_DATA, r);
					result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
					result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
				} else {
	        		result.put(Constant.RESPONSE_CODE, Constant.PERM_CODE_VALUE);
					result.put(Constant.RESPONSE_CODE_MSG, "请先实名认证");	
	        	}
			} else {
	        	result.put(Constant.RESPONSE_CODE, Constant.PERM_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, "请先实名认证");	
	        }
		}

		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 芝麻信用授权认证的回调地址 不可加拦截
	 * @param request
	 * @param response
	 * @param params
	 * @param sign
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/actzm/mine/zhima/AuthCallBack.htm")
	public String AuthCallBack(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="params",required=true) String params,
			@RequestParam(value="sign",required=true) String sign) throws Exception{
		Map<String, Object> result = zhimaService.authCallBack(params,null);
		request.setAttribute("msg",result.get("msg"));
		request.setAttribute("code",result.get("code"));
		request.setAttribute("zmScore",result.get("zmScore"));
		return "zhimaAuthReturnback";
	}
	
}

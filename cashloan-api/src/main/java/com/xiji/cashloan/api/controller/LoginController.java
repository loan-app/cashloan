package com.xiji.cashloan.api.controller;

import com.xiji.cashloan.api.user.bean.AppAbsActionWrapper;
import com.xiji.cashloan.api.user.bean.AppDbSession;
import com.xiji.cashloan.api.user.bean.AppSessionBean;
import com.xiji.cashloan.api.user.service.DBService;
import com.xiji.cashloan.api.user.service.MybatisService;
import com.xiji.cashloan.api.user.service.SmsService;
import com.xiji.cashloan.api.user.service.UserService;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.util.StringUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author wnb
 * @date 2018/12/03
 *  @version 1.0
 */
@Scope("prototype")
@Controller
@SuppressWarnings({ "unchecked", "rawtypes" })
@RequestMapping("/api/user/")
public class LoginController {

	@Resource
	private DBService dbService;
	@Resource
	private MybatisService mybatisService;
	@Resource
	private AppDbSession appDbSession;

	@Resource(name = "clUserService_")
	private UserService userService;

	@Resource
	private SmsService smsService;


	@RequestMapping("login")
	public void login(final HttpServletRequest request,
			HttpServletResponse response, final String loginName,
			final String loginPwd, final String signMsg,final String blackBox) {
		new AppAbsActionWrapper(response) {
			@Override
			public Object doAction() {
				return userService.login(request, loginName, loginPwd, signMsg,blackBox);
			}
		};
	}

	//根据手机和验证码登录
	@RequestMapping("loginPhone")
	public void loginPhone(final HttpServletRequest request,
						   final HttpServletResponse response, final String loginName,final String vcode) {
		new AppAbsActionWrapper(response) {
			@Override
			public Object doAction() {
				return userService.loginPhone(request, loginName, vcode);
			}
		};
	}

	@RequestMapping("autoLogin")
	public void autoLogin(final HttpServletRequest request,
			final HttpServletResponse response, final String refresh_token) {
		new AppAbsActionWrapper(response) {
			@Override
			public Object doAction() {
				Map result = new LinkedHashMap();

				if (StringUtil.isEmpty(refresh_token)) {
					Map ret = new LinkedHashMap();
					ret.put("success", false);
					ret.put("msg", "非法请求");
					return ret;
				}

//				if (Math.abs(new Date().getTime() - subtime) > 60000) {
//					Map ret = new LinkedHashMap();
//					ret.put("success", false);
//					ret.put("msg", "请确认参数subtime是否正确");
//					return ret;
//				}

				/*
				 * String verify = MD5.encode(Global.getValue("app_key") +
				 * refreshToken + subtime); if
				 * (!verify.equalsIgnoreCase(signMsg)) { Map ret = new
				 * LinkedHashMap(); ret.put("success", false); ret.put("msg",
				 * "签名未通过"); return ret; } else {
				 */
				Object obj = appDbSession.autoLogin(request, refresh_token);

				if (!(obj instanceof AppSessionBean)) {
					return obj;
				}

				AppSessionBean bean = (AppSessionBean) obj;

				result.put("success", true);
				result.put("data", bean.getFront());
				result.put("msg", "成功自动登录");
				return result;
			}
		};
	}

	// FORGET_PWD
	// REGISTER_VCODE

	/**
	 * @param request
	 * @param response
	 * @param phone
	 *            REGISTER_VCODE FORGET_PWD
	 */
	@RequestMapping("fetchSmsVCode")
	public void fetchSmsVCode(final HttpServletRequest request,
			final HttpServletResponse response, final String phone) {
		new AppAbsActionWrapper(response) {
			@Override
			public Object doAction() {
				if (StringUtil.isEmpty(phone)) {
					Map ret = new LinkedHashMap();
					ret.put("success", false);
					ret.put("msg", "手机号码不能为空");
					return ret;
				}
				smsService.sendSmsByTpl(request, phone, "vcode", randomNum(4));
				Map ret = new LinkedHashMap();
				ret.put("success", true);
				ret.put("msg", "验证码已发送");
				return ret;
			}
		};
	}

	private static String randomNum(int len) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < len; i++) {
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}

	@RequestMapping("register")
	public void register(final HttpServletRequest request,
			final HttpServletResponse response, 
			final String loginName,
			final String loginPwd, 
			final String vcode,
			final String invitationCode, 
			final String client,
			final String registerCoordinate,
			final String registerAddr, 
			final String signMsg,
			final String blackBox) {
		new AppAbsActionWrapper(response) {
			@Override
			public Object doAction() {
				Map result = userService.registerUser(request, loginName,
						loginPwd, vcode, invitationCode, registerCoordinate,
						registerAddr, client, signMsg,"");
				if ((Boolean) result.get("success")) {
					result = userService.login(request, loginName, loginPwd,
							signMsg,blackBox);
					result.put("msg", result.get("msg"));
				}
				return result;
			}
		};
	}

	@RequestMapping("wxRegister")
	public void wxRegister(final HttpServletRequest request,
			final HttpServletResponse response, final String loginName,
			final String vcode,
			final String invitationCode, final String client,
			final String channelCode, final String registerCoordinate,
			final String registerAddr, final String signMsg,
			final String blackBox) {
		String wxRegister = Global.getValue("wxRegister");
		Map result = new HashMap();
		if("10".equals(wxRegister)) {
			result.put("msg", "注册成功!");
		}
		new AppAbsActionWrapper(response) {
			@Override
			public Object doAction() {
				Map result = userService.registerUser(request, loginName,
						null, vcode, invitationCode, registerCoordinate,
						registerAddr, client, signMsg,channelCode);
				if ((Boolean) result.get("success")) {
//					result = userService.login(request, loginName, loginPwd,
//							signMsg,blackBox);
					result.put("msg", "注册成功!");
				}
				return result;
			}
		};
	}

	@RequestMapping(value = "hbRegister", method = RequestMethod.POST)
	public void hbRegister(final HttpServletResponse response,final String cid,
							final int request_time,
							final String phone,final String sign) {

		new AppAbsActionWrapper(response) {
			@Override
			public Object doAction() {
				Map result = userService.apiRegister(cid, request_time,phone,sign);
				return result;
			}
		};
	}

	//设置登录密码
	@RequestMapping("login/setPwd.htm")
	public void setPwd(final HttpServletRequest request,
						  HttpServletResponse response, final String phone,
						  final String Pwd, final String signMsg) {
		new AppAbsActionWrapper(response) {
			@Override
			public Object doAction() {
				return userService.setPwd(phone, Pwd, signMsg);
			}
		};
	}

	@RequestMapping("login/forgetPwd.htm")
	public void forgetPwd(final HttpServletRequest request,
			HttpServletResponse response, final String phone,
			final String newPwd, final String vcode, final String signMsg) {
		new AppAbsActionWrapper(response) {
			@Override
			public Object doAction() {
				return userService.forgetPwd(phone, newPwd, vcode, signMsg);
			}
		};
	}

	@RequestMapping("validateSmsCode")
	public void validateSmsCode(final HttpServletRequest request,
			HttpServletResponse response, final String phone,
			final String type, final String vcode, final String signMsg) {
		new AppAbsActionWrapper(response) {
			@Override
			public Object doAction() {
				if (StringUtil.isEmpty(phone) || StringUtil.isEmpty(vcode)
						|| StringUtil.isEmpty(signMsg)) {
					Map ret = new LinkedHashMap();
					ret.put("success", false);
					ret.put("msg", "非法参数");
					return ret;
				}

				/*
				 * String _signMsg = MD5.encode(Global.getValue("app_key") +
				 * phone + vcode); if (!_signMsg.equalsIgnoreCase(signMsg)) {
				 * Map ret = new LinkedHashMap(); ret.put("success", false);
				 * ret.put("msg", "签名验签不通过"); return ret; }
				 */

				String msg = smsService.validateSmsCode(phone, type, vcode);
				Map ret = new LinkedHashMap();
				ret.put("success", true);
				ret.put("msg", msg);

				Map data = new LinkedHashMap();
				data.put("pass", msg == null);
				ret.put("data", data);
				return ret;
			}
		};

	}

	@RequestMapping("isPhoneExists")
	public void isPhoneExists(final HttpServletRequest request,
			HttpServletResponse response, final String phone) {
		new AppAbsActionWrapper(response) {
			@Override
			public Object doAction() {
				boolean exists = mybatisService.queryRec("usr.isPhoneExists",
						phone) != null;
				Map ret = new LinkedHashMap();
				ret.put("success", true);

				Map data = new LinkedHashMap();
				data.put("isExists", exists ? "20" : "10");
				if(!exists) {
					String registerUrl = Global.getValue("server_host")+Global.getValue("h5_invite")+"?channelCode=APP_REG";
					data.put("registerUrl", registerUrl);
				}
				ret.put("data", data);
				ret.put("msg", exists ? "该手机号码已存在" : "该手机不存在，可注册");
				return ret;
			}
		};
	}
}

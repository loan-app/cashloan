package com.xiji.cashloan.api.controller;


import com.xiji.cashloan.cl.service.NameMultiService;
import com.xiji.cashloan.cl.util.black.LogCode;
import com.xiji.cashloan.core.common.util.code.MD5;
import com.xiji.cashloan.core.common.web.controller.BaseController;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import tool.util.StringUtil;

/**
 *
 * @author:king
 * @date:2018/12/10
 */
@Scope("prototype")
@Controller
public class BlacklistController extends BaseController{
	
	public static final Logger logger = LoggerFactory.getLogger(BlacklistController.class);

	@Autowired
	private NameMultiService nameMultiService;

	@RequestMapping(value="/api/black/{token}/test.htm")
	public void test(HttpServletRequest request, HttpServletResponse resp,@PathVariable(value = "token") String token)
		throws IOException {
		Map<String, String> params = new HashMap<>();
		params.put("dimensionValue", request.getParameter("input"));
		if (StringUtil.isBlank(request.getParameter("source"))) {
			params.put("source", LogCode.ALL_SOURCE);
		}else {
			params.put("source", request.getParameter("source"));
		}

		if (StringUtil.isBlank(request.getParameter("tableName"))) {
			params.put("tableName", LogCode.table_black_list);
		}else {
			params.put("tableName", request.getParameter("tableName"));
		}

		JSONObject midResult = nameMultiService.dealBusiness(params);

		String key = "";
		midResult.put("token", token);
		midResult.put("sign", sign(midResult, token, key));
		//返回结果

		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf8");
		resp.getOutputStream().write(midResult.toString().getBytes("utf8"));
	}

	private String sign(JSONObject midResult,String token,String key) {
		if (LogCode.NORMAL_CODE != midResult.optInt(LogCode.RESULT_CODE,-100)){
			return "";
		}
		return MD5.encode(midResult.optInt(LogCode.RESULT_CODE)+"|"+midResult.optLong("match_time")+"|" + token+ "|" +key);
	}
}

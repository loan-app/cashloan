package com.xiji.cashloan.manage.controller;

import com.xiji.cashloan.cl.service.YixinRiskReportService;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.util.ServletUtils;
import com.xiji.cashloan.system.permission.annotation.RequiresPermission;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@Scope("prototype")
public class YixinRiskReportCotroller  {

    @Resource
    private YixinRiskReportService yixinRiskReportService;
    @RequestMapping(value="/modules/manage/yixin/risk/report/list.htm",method={RequestMethod.GET,RequestMethod.POST})
    @RequiresPermission(code = "modules:manage:yixin:risk:report:list",name = "收据收费列表")
    public void list(@RequestParam(value="userId",required=true)Long userId, HttpServletResponse response) {
        Map<String,String> map = yixinRiskReportService.getRecentlyYixinRiskReportMap(userId);
        Map<String, Object> result = new HashMap<>();
        result.put(Constant.RESPONSE_DATA, map);
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        ServletUtils.writeToResponse(response, result);
    }
}

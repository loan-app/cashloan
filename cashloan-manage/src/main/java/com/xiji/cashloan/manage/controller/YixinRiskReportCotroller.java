package com.xiji.cashloan.manage.controller;

import com.xiji.cashloan.cl.domain.YixinFraud;
import com.xiji.cashloan.cl.mapper.YixinFraudMapper;
import com.xiji.cashloan.cl.service.YixinFraudService;
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

/**
 *
 * 宜信风险评估Controller
 * @author wnb
 * @date 2018/12/28
 */
@Controller
@Scope("prototype")
public class YixinRiskReportCotroller  {

    @Resource
    private YixinRiskReportService yixinRiskReportService;

    @RequestMapping(value="/modules/manage/yixin/risk/report/list.htm",method={RequestMethod.GET,RequestMethod.POST})
    @RequiresPermission(code = "modules:manage:yixin:risk:report:list",name = "宜信风险评估报告")
    public void list(@RequestParam(value="borrowId",required=true)Long borrowId, HttpServletResponse response) {
        Map<String,Object> map = yixinRiskReportService.getRecentlyYixinRiskReportMap(borrowId);
        Map<String, Object> result = new HashMap<>();
        result.put(Constant.RESPONSE_DATA, map);
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        ServletUtils.writeToResponse(response, result);
    }


    @RequestMapping(value="/modules/manage/yixin/risk/report/queryFraud.htm",method={RequestMethod.GET,RequestMethod.POST})
    @RequiresPermission(code = "modules:manage:yixin:risk:report:queryFraud",name = "宜信欺诈甄别")
    public void queryFraud(@RequestParam(value="borrowId",required=true)Long borrowId, HttpServletResponse response) {
        Map<String, Object> yixinFraud = yixinRiskReportService.getYixinFraudMap(borrowId);
        Map<String, Object> result = new HashMap<>();
        result.put(Constant.RESPONSE_DATA, yixinFraud);
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        ServletUtils.writeToResponse(response, result);
    }


}

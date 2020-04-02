package com.xiji.cashloan.manage.controller;


import com.alibaba.fastjson.JSONObject;
import com.xiji.cashloan.cl.service.YouDunRiskReportService;
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
public class YoudunLoanDetailController {

    @Resource
    private YouDunRiskReportService youDunRiskReportService;

    @RequestMapping(value="/modules/manage/youdun/risk/report/list.htm",method={RequestMethod.GET,RequestMethod.POST})
    @RequiresPermission(code = "modules:manage:youudn:risk:report:list",name = " 有盾风险评估报告")
    public void list(@RequestParam(value="borrowId",required=true)Long borrowId, HttpServletResponse response) {
        Map<String, Object> map = youDunRiskReportService.getYouDunRiskReportMap(borrowId);
        JSONObject json = new JSONObject();
        if (map!=null){
            json=new JSONObject(map);
        }
        Map<String, Object> result = new HashMap<>();

        result.put(Constant.RESPONSE_DATA, json);
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        ServletUtils.writeToResponse(response, result);
    }
}

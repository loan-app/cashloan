package com.xiji.cashloan.manage.controller;

import com.alibaba.fastjson.JSON;
import com.xiji.cashloan.cl.domain.PinganGrayscale;
import com.xiji.cashloan.cl.service.PinganGrayscaleService;
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
 * 凭安报告
 * @author wnb
 * @date: 2018/12/29
 *
 */
@Controller
@Scope("prototype")
public class PinganGrayscaleController {

    @Resource
    private PinganGrayscaleService pinganGrayscaleService;

    @Resource
    private YixinRiskReportService yixinRiskReportService;
    @RequestMapping(value="/modules/manage/pingan/grayscale/report.htm",method={RequestMethod.GET,RequestMethod.POST})
    @RequiresPermission(code = "modules:manage:pingan:grayscale:report",name = "最近一次凭安报告")
    public void list(@RequestParam(value="userId",required=true)Long userId, HttpServletResponse response) {

        PinganGrayscale pinganGrayscale = pinganGrayscaleService.getPinganGrayscale(userId);
        JSON json = null;
        if (pinganGrayscale != null && pinganGrayscale.getData() != null){
            json = JSON.parseObject(pinganGrayscale.getData());
        }
        Map<String, Object> result = new HashMap<>();
        result.put(Constant.RESPONSE_DATA, json);
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        ServletUtils.writeToResponse(response, result);
    }

}

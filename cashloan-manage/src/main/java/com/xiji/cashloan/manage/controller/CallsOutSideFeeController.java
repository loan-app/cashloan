package com.xiji.cashloan.manage.controller;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.CallsOutSideFee;
import com.xiji.cashloan.cl.service.CallsOutSideFeeService;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.util.JsonUtil;
import com.xiji.cashloan.core.common.util.RdPage;
import com.xiji.cashloan.core.common.util.ServletUtils;
import com.xiji.cashloan.core.common.web.controller.BaseController;
import com.xiji.cashloan.core.model.CloanUserModel;
import com.xiji.cashloan.system.permission.annotation.RequiresPermission;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 财务消费
 * @author: Wnb
 * @date: 2018/12/5 15:01
 * @version:V1.0
 */
@Scope("prototype")
@Controller
public class CallsOutSideFeeController extends BaseController {

    @Resource
    protected HttpServletResponse response;

    @Resource
    private CallsOutSideFeeService callsOutSideFeeService;
    /**
     * 收据收费列表查询
     * @param searchParams
     * @param currentPage
     * @param pageSize
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/modules/manage/calls/outside/fee/list.htm",method={RequestMethod.GET,RequestMethod.POST})
    @RequiresPermission(code = "modules:manage:calls:outside:fee:list",name = "收据收费列表")
    public void list(@RequestParam(value="searchParams",required=false) String searchParams,
                     @RequestParam(value = "current") int currentPage,
                     @RequestParam(value = "pageSize") int pageSize){
        Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
        Page<CallsOutSideFee> page = callsOutSideFeeService.listCallsOutSideFee(params,currentPage,pageSize);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put(Constant.RESPONSE_DATA, page);
        result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        ServletUtils.writeToResponse(response,result);
    }

//    /**
//     * 总消费金额
//     */
//    @SuppressWarnings("unchecked")
//    @RequestMapping(value="/modules/manage/calls/outside/getTotalFee.htm",method={RequestMethod.GET,RequestMethod.POST})
//    @RequiresPermission(code = "modules:manage:calls:outside:fee:getTotalFee",name = "总消费金额")
//    public void getTotalFee(){
//        BigDecimal totalFee = callsOutSideFeeService.getTotalFee(0);
//        Map<String,Object> result = new HashMap<String,Object>();
//        result.put(Constant.RESPONSE_DATA, totalFee);
//        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
//        result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
//        ServletUtils.writeToResponse(response,result);
//    }
}

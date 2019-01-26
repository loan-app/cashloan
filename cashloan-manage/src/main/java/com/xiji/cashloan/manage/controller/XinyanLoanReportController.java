package com.xiji.cashloan.manage.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.MagicReqDetail;
import com.xiji.cashloan.cl.domain.XinyanLoanReport;
import com.xiji.cashloan.cl.domain.XinyanXwld;
import com.xiji.cashloan.cl.model.CreditLoanUserModel;
import com.xiji.cashloan.cl.model.XinyanLoanUserModel;
import com.xiji.cashloan.cl.service.MagicReqDetailService;
import com.xiji.cashloan.cl.service.XinyanLoanReportService;
import com.xiji.cashloan.cl.service.XinyanXwldService;
import com.xiji.cashloan.cl.service.impl.XinyanLoanReportServiceImpl;
import com.xiji.cashloan.cl.util.CallsOutSideFeeConstant;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.util.JsonUtil;
import com.xiji.cashloan.core.common.util.RdPage;
import com.xiji.cashloan.core.common.util.ServletUtils;
import com.xiji.cashloan.core.common.util.StringUtil;
import com.xiji.cashloan.core.common.web.controller.BaseController;
import com.xiji.cashloan.core.domain.UserBaseInfo;
import com.xiji.cashloan.core.service.UserBaseInfoService;
import com.xiji.cashloan.system.permission.annotation.RequiresPermission;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 新颜小额网贷报告
 * Created by szb on 18/12/5.
 */
@Controller
@Scope("prototype")
public class XinyanLoanReportController extends BaseController {

    @Resource
    private XinyanLoanReportService xinyanLoanReportService;
    @Resource
    private UserBaseInfoService userBaseInfoService;
    @Resource
    private XinyanXwldService xinyanXwldService;

    /**
     * 新颜小额网贷报告列表
     * @param searchParams
     * @param currentPage
     * @param pageSize
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/modules/manage/xinyan/loan/report/list.htm",method={RequestMethod.GET,RequestMethod.POST})
    @RequiresPermission(code = "modules:manage:xinyan:loan:report:list",name = "新颜小额网贷报告列表")
    public void list(@RequestParam(value="searchParams",required=false) String searchParams,
                     @RequestParam(value = "current") int currentPage,
                     @RequestParam(value = "pageSize") int pageSize){
        Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
        Page<XinyanLoanUserModel> page = xinyanLoanReportService.listUser(params, currentPage,pageSize);
        Map<String,Object> result = new HashMap<>();
        result.put(Constant.RESPONSE_DATA, page);
        result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        ServletUtils.writeToResponse(response,result);
    }


    /**
     * 用户小额网贷报告详细信息
     * @param userId
     */
    @RequestMapping(value="/modules/manage/xinyan/loan/report/detail.htm",method={RequestMethod.GET,RequestMethod.POST})
    @RequiresPermission(code = "modules:manage:xinyan:loan:report:detail",name = "用户小额网贷报告详细信息")
    public void detail(@RequestParam(value = "userId") Long userId){

        UserBaseInfo userBaseInfo = userBaseInfoService.getById(userId);
        JSONObject resJson = new JSONObject();
        if (userBaseInfo != null) {
            XinyanLoanReport xinyanLoanReport = xinyanLoanReportService.getLastRecord(userId);
            if(xinyanLoanReport != null) {
                resJson = (JSONObject)JSON.toJSON(xinyanLoanReport);
            }
        }
        Map<String,Object> result = new HashMap<>();
        result.put(Constant.RESPONSE_DATA, resJson);
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        ServletUtils.writeToResponse(response,result);
    }

    /**
     * 行为雷达报告展示
     * @param borrowId
     */
    @RequestMapping(value="/modules/manage/xinyan/xwld/detail.htm",method={RequestMethod.GET,RequestMethod.POST})
    @RequiresPermission(code = "modules:manage:xinyan:xwld:detail",name = "用户行为雷达信息")
    public void xwld(@RequestParam(value = "borrowId") Long borrowId){
        XinyanXwld xinyanXwld = xinyanXwldService.getByBorrowId(borrowId);
        JSONObject data = new JSONObject();
        if(xinyanXwld != null) {
            data = JSONObject.parseObject(xinyanXwld.getData());
        }

        Map<String,Object> result = new HashMap<>();
        result.put(Constant.RESPONSE_DATA, data);
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        ServletUtils.writeToResponse(response,result);
    }
}

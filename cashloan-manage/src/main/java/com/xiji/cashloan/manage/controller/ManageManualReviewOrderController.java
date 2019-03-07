package com.xiji.cashloan.manage.controller;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.model.ManualReviewCountModel;
import com.xiji.cashloan.cl.model.ManualReviewOrderModel;
import com.xiji.cashloan.cl.service.ManualReviewOrderService;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.util.JsonUtil;
import com.xiji.cashloan.core.common.util.RdPage;
import com.xiji.cashloan.core.common.util.ServletUtils;
import com.xiji.cashloan.system.domain.SysUser;
import com.xiji.cashloan.system.permission.annotation.RequiresPermission;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 信审人员以及信审统计相关Controller
 * Created by szb on 18/12/16.
 */
@Scope("prototype")
@Controller
public class ManageManualReviewOrderController extends ManageBaseController {

    @Resource
    private ManualReviewOrderService manualReviewOrderService;

    /**
     * 信审人员统计列表
     *
     * @param searchParams
     * @param current
     * @param pageSize
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/modules/manage/borrow/manual/review/member.htm")
    @RequiresPermission(code = "modules:manage:borrow:manual:review:member", name = "信审人员统计列表")
    public void member(
            @RequestParam(value = "searchParams", required = false) String searchParams,
            @RequestParam(value = "current") int current,
            @RequestParam(value = "pageSize") int pageSize) {
        Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);

        Page<ManualReviewCountModel> page = manualReviewOrderService.memberCount(params, current, pageSize);
        Map<String, Object> result = new HashMap<>();
        result.put(Constant.RESPONSE_DATA, page);
        result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");

        ServletUtils.writeToResponse(response, result);
    }

    /**
     * 审核专员信息列表
     * @param roleName
     */
    @RequestMapping(value="/modules/manage/borrow/manual/review/sysUserlist.htm",method={RequestMethod.GET,RequestMethod.POST})
    @RequiresPermission(code = "modules:manage:borrow:manual:review:sysUserlist",name = "审核专员信息列表")
    public void sysUserlist(@RequestParam(value = "roleName",required=false) String roleName)throws Exception {
        Map<String, Object> responseMap = new HashMap<String, Object>();
        Map<String, Object> params = new HashMap<String, Object>();
        if (null == roleName) {
            params.put("roleName", "reviewSpecialist");
        } else {
            params.put("roleName", roleName);
        }
        List<Map<String, Object>> users = sysUserService.getUserInfo(params);
        responseMap.put("data", users);
        responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        responseMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
        ServletUtils.writeToResponse(response, responseMap);
    }

    /**
     * 借款订单分配信审人员
     *
     * @param id
     * @param userId
     * @param userName
     */
    @RequestMapping(value = "/modules/manage/borrow/manual/review/allotUser.htm", method = {RequestMethod.GET, RequestMethod.POST})
    @RequiresPermission(code = "modules:manage:borrow:manual:review:allotUser", name = "借款订单分配审核人员")
    public void allotUser(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "userId") Long userId,
            @RequestParam(value = "userName") String userName) throws Exception {
        Map<String, Object> responseMap = new HashMap<String, Object>();
        List<String> strings =  Arrays.asList(id.split(","));
        List<Long> ids = new ArrayList<>();
        for (String string : strings) {
            ids.add(Long.valueOf(string));
        }
        Map<String, Object> params = new HashMap<>();
        params.put("ids", ids);
        params.put("userId", userId);
        params.put("userName", userName);
        params.put("state", ManualReviewOrderModel.STATE_ORDER_WAIT);
        int msg = manualReviewOrderService.orderAllotUser(params);
        if (msg > 0) {
            responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            responseMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
        } else {
            responseMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            responseMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
        }
        ServletUtils.writeToResponse(response, responseMap);
    }

    /**
     * 审核总订单列表
     *
     * @param searchParams
     * @param current
     * @param pageSize
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/modules/manage/borrow/manual/review/list.htm", method = {RequestMethod.GET, RequestMethod.POST})
    @RequiresPermission(code = "modules:manage:borrow:manual:review:list", name = "审核总订单列表")
    public void list(HttpServletRequest request, @RequestParam(value = "searchParams", required = false) String searchParams,
                     @RequestParam(value = "current") int current,
                     @RequestParam(value = "pageSize") int pageSize) throws Exception {
        Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
        SysUser sysUser = getLoginUser(request);
        if (sysUser != null) {
            if (params == null) {
                params = new HashMap<>();
            }
        }
        Page<ManualReviewOrderModel> page = manualReviewOrderService.list(params, current, pageSize);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constant.RESPONSE_DATA, page);
        result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        ServletUtils.writeToResponse(response, result);
    }

    /**
     * 我的审核订单列表
     *
     * @param searchParams
     * @param current
     * @param pageSize
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/modules/manage/borrow/my/manual/review/list.htm")
    @RequiresPermission(code = "modules:manage:borrow:my:manual:review:list", name = "我的审核订单列表")
    public void list(
            HttpServletResponse response,
            HttpServletRequest request,
            HttpSession session,
            @RequestParam(value = "searchParams", required = false) String searchParams,
            @RequestParam(value = "current") int current,
            @RequestParam(value = "pageSize") int pageSize) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
        SysUser sysUser = getLoginUser(request);
        Page<ManualReviewOrderModel> page = new Page<>();
        if (sysUser != null) {
            if (params == null) {
                params = new HashMap<>();
            }
            params.put("userId", sysUser.getId());
            page = manualReviewOrderService.list(params, current, pageSize);
            result.put(Constant.RESPONSE_DATA, page);
            result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
            result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        } else {
            result.put(Constant.RESPONSE_DATA, page);
            result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
            result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "登陆过期请重新登录");
        }
        ServletUtils.writeToResponse(response, result);
    }

}

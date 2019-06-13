package com.xiji.cashloan.manage.controller;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.ManualRepayOrder;
import com.xiji.cashloan.cl.model.ManualRepayOrderModel;
import com.xiji.cashloan.cl.service.ManualRepayOrderService;
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
 * 到期人员以及到期分配Controller
 * Created by szb on 19/03/07.
 */
@Scope("prototype")
@Controller
public class ManageManualRepayOrderController extends ManageBaseController {
    @Resource
    private ManualRepayOrderService manualRepayOrderService;

    /**
     * 到期员信息列表
     * @param roleName
     */
    @RequestMapping(value="/modules/manage/borrow/manual/repay/sysUserlist.htm",method={RequestMethod.GET, RequestMethod.POST})
    @RequiresPermission(code = "modules:manage:borrow:manual:repay:sysUserlist",name = "到期员信息列表")
    public void sysUserlist(@RequestParam(value = "roleName",required=false) String roleName)throws Exception {
        Map<String, Object> responseMap = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        if (null == roleName) {
            params.put("roleName", "repayPerson");
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
     * 到期订单分配到期人员
     *
     * @param id
     * @param userId
     * @param userName
     */
    @RequestMapping(value = "/modules/manage/borrow/manual/repay/allotUser.htm", method = {RequestMethod.GET, RequestMethod.POST})
    @RequiresPermission(code = "modules:manage:borrow:manual:repay:allotUser", name = "到期订单分配到期人员")
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
        params.put("state", ManualRepayOrderModel.STATE_ORDER_ALLOT);
        int msg = manualRepayOrderService.orderAllotUser(params);
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
     * 我的到期订单列表
     *
     * @param searchParams
     * @param current
     * @param pageSize
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/modules/manage/borrow/my/manual/repay/list.htm")
    @RequiresPermission(code = "modules:manage:borrow:my:manual:repay:list", name = "我的到期订单列表")
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
        Page<ManualRepayOrderModel> page = new Page<>();
        if (sysUser != null) {
            if (params == null) {
                params = new HashMap<>();
            }
            params.put("userId", sysUser.getId());
            page = manualRepayOrderService.list(params, current, pageSize);
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

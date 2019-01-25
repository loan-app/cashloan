package com.xiji.cashloan.manage.controller;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.UserRemark;
import com.xiji.cashloan.cl.service.UserRemarkService;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author wnb
 * @date 2019/01/17
 */
@Scope("prototype")
@Controller
public class UserRemarkCotroller extends ManageBaseController {

    @Resource
    private UserRemarkService userRemarkService;
    /**
     * 保存用户备注信息
     * @throws Exception
     */
    @RequestMapping(value = "/modules/manage/user/remark/save.htm", method = RequestMethod.POST)
    @RequiresPermission(code = "modules:manage:user:remark:save",name = "保存用户备注信息")
    public void save(@RequestParam(value="userId") Long userId,
                     @RequestParam(value="remark") String remark) throws Exception {
        Map<String,Object> result = new HashMap<>();
        SysUser sysUser = getLoginUser(request);
        if (sysUser == null){
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "请重新登录");
            ServletUtils.writeToResponse(response, result);
            return;
        }
        UserRemark userRemark = new UserRemark();
        userRemark.setCreateTime(new Date());
        userRemark.setOperateTime(new Date());
        userRemark.setRemark(remark);
        userRemark.setOperateId(sysUser.getId());
        userRemark.setUserId(userId);
        int count = userRemarkService.insert(userRemark);
        if (count > 0) {
            result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
        } else {
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
        }
        ServletUtils.writeToResponse(response, result);
    }


    /**
     * 查询用户备注信息列表
     * @throws Exception
     */
    @RequestMapping(value = "/modules/manage/user/remark/list.htm", method = RequestMethod.GET)
    @RequiresPermission(code = "modules:manage:user:remark:list",name = "备注列表")
    public void list(@RequestParam(value="searchParams",required=false) String searchParams,
                     @RequestParam(value = "current") int currentPage,
                     @RequestParam(value = "pageSize") int pageSize) throws Exception {

        Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
        Page<UserRemark> page = userRemarkService.listUserRemark(params,currentPage,pageSize);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put(Constant.RESPONSE_DATA, page);
        result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        ServletUtils.writeToResponse(response,result);
    }

}

package com.xiji.cashloan.api.controller;

import com.xiji.cashloan.cl.domain.AppList;
import com.xiji.cashloan.cl.service.AppListService;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.util.Base64;
import com.xiji.cashloan.core.common.util.ServletUtils;
import com.xiji.cashloan.core.common.web.controller.BaseController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 用户应用程序列表
 * @author:Wnb
 * @date:2018/12/20 11:33
 * @version:V1.0
 */
@Scope("prototype")
@Controller
public class AppListController extends BaseController {

    @Resource
    private AppListService appListService;

    /**
     * 保存用户应用列表
     * @param appList
     * @param userId
     * @throws ParseException
     */
    @RequestMapping(value = "/api/act/app/list/addAppList.htm", method = RequestMethod.POST)
    public void addAppList(@RequestParam(value="appList", required = true) String appList, @RequestParam(value = "userId", required = true) String userId) throws ParseException {
        String info = new String(Base64.decode(appList));
        AppList list = new AppList();
        list.setCreateTime(new Date());
        list.setAppList(info);
        list.setUserId(Long.parseLong(userId));
        appListService.insert(list);
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        returnMap.put(Constant.RESPONSE_CODE_MSG, "保存成功");
        ServletUtils.writeToResponse(response, returnMap);
    }
}

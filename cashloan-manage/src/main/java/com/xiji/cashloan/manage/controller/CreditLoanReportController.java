package com.xiji.cashloan.manage.controller;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.MagicReqDetail;
import com.xiji.cashloan.cl.model.CreditLoanUserModel;
import com.xiji.cashloan.cl.service.MagicReqDetailService;
import com.xiji.cashloan.cl.util.CallsOutSideFeeConstant;
import com.xiji.cashloan.cl.util.magic.MagicRiskConstant;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.util.JsonUtil;
import com.xiji.cashloan.core.common.util.RdPage;
import com.xiji.cashloan.core.common.util.ServletUtils;
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
 * 用户信贷报告
 * Created by szb on 18/12/5.
 */
@Controller
@Scope("prototype")
public class CreditLoanReportController extends BaseController {

    @Resource
    private MagicReqDetailService magicReqDetailService;
    @Resource
    private UserBaseInfoService userBaseInfoService;

    /**
     *用户信息列表
     * @param searchParams
     * @param currentPage
     * @param pageSize
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/modules/manage/credit/loan/report/list.htm",method={RequestMethod.GET,RequestMethod.POST})
    @RequiresPermission(code = "modules:manage:credit:loan:report:list",name = "用户信贷报告列表")
    public void list(@RequestParam(value="searchParams",required=false) String searchParams,
                     @RequestParam(value = "current") int currentPage,
                     @RequestParam(value = "pageSize") int pageSize){
        Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
        Page<CreditLoanUserModel> page = magicReqDetailService.listUser(params, currentPage,pageSize);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put(Constant.RESPONSE_DATA, page);
        result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        ServletUtils.writeToResponse(response,result);
    }


    /**
     * 用户信贷报告详细信息
     * @param userId
     */
    @RequestMapping(value="/modules/manage/credit/loan/report/detail.htm",method={RequestMethod.GET,RequestMethod.POST})
    @RequiresPermission(code = "modules:manage:credit:loan:report:detail",name = "用户信贷报告详细信息")
    public void detail(@RequestParam(value = "userId") Long userId){

        HashMap<String, Object> map = new HashMap<String,Object>();
        UserBaseInfo userBaseInfo = userBaseInfoService.getById(userId);
        if (userBaseInfo != null) {
            //用户运营商反欺诈报告信息
            MagicReqDetail antiFraud = magicReqDetailService.getLastRecord(userId, CallsOutSideFeeConstant.CALLS_TYPE_ANTI_FRAUD);
            if(antiFraud != null) {
                map.put("antiFraud", antiFraud.getData());
            }
            //用户多头接待信息
            MagicReqDetail multiIno = magicReqDetailService.getLastRecord(userId, CallsOutSideFeeConstant.CALLS_TYPE_MULTI_INFO);
            if(multiIno != null) {
                map.put("multiIno", multiIno.getData());
            }
            //用户黑灰名单
            MagicReqDetail blackGray = magicReqDetailService.getLastRecord(userId, CallsOutSideFeeConstant.CALLS_TYPE_BLACK_GRAY);
            if(blackGray != null) {
                map.put("blackGray", blackGray.getData());
            }
            //用户贷后欣慰信息
            MagicReqDetail postLoad = magicReqDetailService.getLastRecord(userId, CallsOutSideFeeConstant.CALLS_TYPE_POST_LOAD);
            if(postLoad != null) {
                map.put("postLoad", postLoad.getData());
            }
        }
        Map<String,Object> result = new HashMap<String,Object>();
        result.put(Constant.RESPONSE_DATA, map);
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        ServletUtils.writeToResponse(response,result);
    }
}

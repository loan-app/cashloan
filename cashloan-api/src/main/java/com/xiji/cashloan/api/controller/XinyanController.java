package com.xiji.cashloan.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.xiji.cashloan.cl.service.ClBorrowService;
import com.xiji.cashloan.cl.service.XinyanRiskService;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.exception.BussinessException;
import com.xiji.cashloan.core.common.util.ServletUtils;
import com.xiji.cashloan.core.common.util.StringUtil;
import com.xiji.cashloan.core.common.web.controller.BaseController;
import com.xiji.cashloan.core.domain.Borrow;
import com.xiji.cashloan.core.domain.UserBaseInfo;
import com.xiji.cashloan.core.model.BorrowModel;
import com.xiji.cashloan.core.service.UserBaseInfoService;
import com.xiji.cashloan.rc.domain.SceneBusinessLog;
import com.xiji.cashloan.rc.model.TppBusinessModel;
import com.xiji.cashloan.rc.service.SceneBusinessLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by szb on 19/1/17.
 */
@Scope("prototype")
@Controller
public class XinyanController extends BaseController {

    public static final Logger logger = LoggerFactory.getLogger(XinyanController.class);

    @Resource
    private UserBaseInfoService userBaseInfoService;
    @Resource
    private ClBorrowService clBorrowService;
    @Resource
    private XinyanRiskService xinyanRiskService;
    @Resource
    private SceneBusinessLogService sceneBusinessLogService;

    @RequestMapping(value = "/api/act/xinyan/preOrderNo.htm")
    public void protocolDetail() {
        long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
        UserBaseInfo userBaseInfo = userBaseInfoService.findByUserId(userId);
        if(userBaseInfo == null) {
            throw new BussinessException(StringUtil.isNull(Constant.FAIL_CODE_VALUE),"请先登录");
        }
        Map<String,Object> data = new HashMap<>();
        String preOrderNo = StringUtil.EMPTY;
        Borrow lastBorrow = clBorrowService.findLastBorrow(userId);
        if(lastBorrow != null && BorrowModel.STATE_PRE.equals(lastBorrow.getState())) {
            boolean needExcute = sceneBusinessLogService.haveNeedExcuteByNid(lastBorrow.getId(), TppBusinessModel.BUS_NID_XWLD);
            if(needExcute) {
                preOrderNo = xinyanRiskService.getPreOrderNo(lastBorrow);
            }
        }
        data.put("pre_order_no", preOrderNo);
        data.put("id_no", userBaseInfo.getIdNo());
        data.put("name", userBaseInfo.getRealName());
        Map<String,Object> result = new HashMap<>();
        result.put(Constant.RESPONSE_DATA, data);
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        ServletUtils.writeToResponse(response, result);
    }

    @RequestMapping(value = "/api/xinyanNotify.htm", method= RequestMethod.POST)
    public void protocolDetail(@RequestParam(value="result_data") String resultData) {
        logger.info("新颜行为雷达回调数据："+ resultData);
        final long borrowId = xinyanRiskService.saveXWLDNotify(resultData);
        if(borrowId != 0l) {
            Thread handleBorrow = new Thread(new Runnable() {
                @Override
                public void run() {
                    clBorrowService.syncSceneBusinessLog("1", "成功", borrowId, TppBusinessModel.BUS_NID_XWLD);
                }
            });
            handleBorrow.start();
        }
        response.setStatus(HttpServletResponse.SC_OK);
        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.write("ok");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.xiji.cashloan.manage.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiji.cashloan.cl.domain.BlacklistXindeData;
import com.xiji.cashloan.cl.model.xindedata.BlacklistXindeDataDto;
import com.xiji.cashloan.cl.model.xindedata.XindeDataUtil;
import com.xiji.cashloan.cl.service.BlacklistXindeDataService;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.util.ServletUtils;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import tool.util.BeanUtil;

/**
 * @Auther: king
 * @Date: 2018/12/5 15:32
 * @Description:
 */
@Scope("prototype")
@Controller
public class ManageXindeDataBlacklistController extends ManageBaseController{
    @Resource
    private BlacklistXindeDataService blacklistXindeDataService;
    /**
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/modules/manage/xinde/blacklist/detail.htm", method = RequestMethod.POST)
    public void detail(@RequestParam(value = "borrowId") long borrowId ) throws Exception {
        BlacklistXindeData xindeData = blacklistXindeDataService.findBlackData(borrowId);
        BlacklistXindeDataDto dto = new BlacklistXindeDataDto();

        JSONObject resJson = new JSONObject();
        if(xindeData != null) {
            BeanUtil.copyProperties(xindeData, dto);
            dto.setIsLastLoanRefusedStr(XindeDataUtil.convertloanRefused(dto.getIsLastloanRefused()));
            dto.setCurrentOverdueAmountStr(XindeDataUtil.convertOverdueAmountStr(dto.getCurrentOverdueAmount()));
            dto.setLastLoanRefuseReason(XindeDataUtil.convertRefuseReasonStr(dto.getLastLoanRefuseReason()));
            resJson = (JSONObject) JSON.toJSON(dto);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constant.RESPONSE_DATA, resJson);
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
        ServletUtils.writeToResponse(response, result);
    }
}

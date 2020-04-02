package com.xiji.cashloan.manage.controller;

import com.xiji.cashloan.cl.domain.BlacklistCommonData;
import com.xiji.cashloan.cl.model.paipaixin.PpxBlacklistDetailDto;
import com.xiji.cashloan.cl.model.paipaixin.PpxDataPaser;
import com.xiji.cashloan.cl.service.BlacklistCommonDataService;
import com.xiji.cashloan.cl.service.impl.assist.blacklist.BlacklistConstant;
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

/**
 * @Auther: king
 * @Date: 2018/12/5 15:32
 * @Description:
 */
@Scope("prototype")
@Controller
public class ManageBlacklistCommonDataController extends ManageBaseController{
    @Resource
    private BlacklistCommonDataService blacklistCommonDataService;
    /**
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/modules/manage/blacklist/ppx/detail.htm", method = RequestMethod.POST)
    public void detail(@RequestParam(value = "borrowId") long borrowId ) throws Exception {
        BlacklistCommonData commonData = blacklistCommonDataService.findByBorrowId(borrowId,
            BlacklistConstant.source_ppx);
        PpxBlacklistDetailDto dto = PpxDataPaser.parse(commonData);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constant.RESPONSE_DATA, dto);
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
        ServletUtils.writeToResponse(response, result);
    }
}

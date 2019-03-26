package com.xiji.cashloan.manage.controller;

import com.xiji.cashloan.cl.service.ClBorrowService;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.util.ServletUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 审核不通过 （自动审核不通过，人工复审不通过）,拉回重审.
 */
@Scope("prototype")
@Controller
public class ComeBackController {

    @Resource
    private ClBorrowService clBorrowService;

    @RequestMapping("/modules/manage/borrow/comeBack.htm")
    public void comeBackBorrow(@RequestParam(value="borrowId") long borrowId, HttpServletResponse response){

        int res =clBorrowService.comeBackBorrow(borrowId);

        Map<String, Object> result = new HashMap<>();
        if (res==1) {
            result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
        } else {
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
        }
        ServletUtils.writeToResponse(response, result);

    }

}

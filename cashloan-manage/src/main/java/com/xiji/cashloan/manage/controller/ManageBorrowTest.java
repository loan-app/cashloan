package com.xiji.cashloan.manage.controller;

import com.xiji.cashloan.cl.domain.CallsOutSideFee;
import com.xiji.cashloan.cl.model.ManageBorrowTestModel;
import com.xiji.cashloan.cl.service.*;
import com.xiji.cashloan.cl.util.CallsOutSideFeeConstant;
import com.xiji.cashloan.cl.util.xinyan.UUIDGenerator;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.util.ServletUtils;
import com.xiji.cashloan.core.domain.Borrow;
import com.xiji.cashloan.core.service.CloanUserService;
import com.xiji.cashloan.core.service.UserBaseInfoService;
import com.xiji.cashloan.rc.domain.TppBusiness;
import com.xiji.cashloan.rc.service.TppBusinessService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 */
@Scope("prototype")
@Controller
public class ManageBorrowTest extends ManageBaseController{

	@Resource
	private ClBorrowService clBorrowService;
	@Resource
	private CloanUserService userService;
	@Resource
	private OperatorReqLogService operatorReqLogService;
	@Resource
	private CloanUserService cloanUserService;
	@Resource
	private BorrowRepayLogService borrowRepayLogService;
	@Resource
	private TppBusinessService tppBusinessService;
	@Resource
	private DhbReqLogService dhbReqLogService;
    @Resource
	private UserBaseInfoService userBaseInfoService;
    @Resource
    private CallsOutSideFeeService callsOutSideFeeService;
	
	@RequestMapping(value = "/modules/manage/user/list.htm")
	public void list()throws Exception{
		List<ManageBorrowTestModel> list = clBorrowService.seleteUser();
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("list", list);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	
	@RequestMapping(value = "/modules/manage/borrow/apply.htm")
	public void apply(
			@RequestParam(value="amount") double amount,
			@RequestParam(value="timeLimit") String timeLimit,
			@RequestParam(value="userId") long userId,
			@RequestParam(value="cardId") long cardId
			) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		Borrow borrow = new Borrow(1l, amount, timeLimit, cardId, "", "", "","");
		borrow = clBorrowService.rcBorrowApply(borrow,"BEE2D38199B4E26D15235E0D7D3390D3","", Boolean.TRUE);
		if (borrow != null && borrow.getId() > 0) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "申请成功");
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "申请失败");
		}
		
		ServletUtils.writeToResponse(response,result);
	}
	/**
	 * 贷后邦测试
	 * @param borrowId
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/borrow/dhbSauron.htm")
	public void dhbSauron(
			@RequestParam(value="borrowId") long borrowId
			) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		Borrow borrow=new Borrow();
		borrow=clBorrowService.findByPrimary(borrowId);
		TppBusiness business = tppBusinessService.findByNid("DhbSauron",Long.valueOf(2));
		dhbReqLogService.queryDhbSauron(borrow, business);
		
		ServletUtils.writeToResponse(response,result);
	}

    /**
     *  充值
     * @param phone
     * @param money
     * @throws Exception
     */
    @RequestMapping(value = "/modules/manage/borrow/doRechargeable.htm")
	public void doRechargeable(
	        @RequestParam(value = "phone")String phone,
            @RequestParam(value="money") double money
            )throws Exception{

        HashMap<String, Object> result = new HashMap<>();
        String taskId = UUIDGenerator.getUUID();

        //插入收费记录表
        CallsOutSideFee callsOutSideFee = new CallsOutSideFee(null, taskId, CallsOutSideFeeConstant.CALLS_TYPE_TopUp,
                money, CallsOutSideFeeConstant.CAST_TYPE_RECHARGE, phone);
        int i = callsOutSideFeeService.save(callsOutSideFee);
        //获取app名称
        String appName = Global.getValue("appName");
        if ( i > 0) {
            result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "充值成功");
            result.put("充值金额为: ",money);
            result.put("产品名称",appName);
        } else {
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "充值失败");
            result.put(Constant.SESSION_OPERATOR,appName);
        }
        ServletUtils.writeToResponse(response,result);
    }
	
}

package com.xiji.cashloan.manage.controller;


import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.BlacklistTask;
import com.xiji.cashloan.cl.service.BlacklistTaskService;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.util.JsonUtil;
import com.xiji.cashloan.core.common.util.RdPage;
import com.xiji.cashloan.core.common.util.ServletUtils;
import com.xiji.cashloan.core.common.web.controller.BaseController;
import com.xiji.cashloan.manage.service.QuartzInfoService;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tool.util.DateUtil;

/**
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Scope("prototype")
@Controller
public class ManageTaskListController extends BaseController {

	private static final Logger logger = Logger.getLogger(ManageTaskListController.class);
	
	@Resource
	private QuartzInfoService quartzInfoService;
	@Resource
	private BlacklistTaskService blacklistTaskService;

	/**
	 * 黑名单任务
	 * @param search
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/modules/manage/task/blacklist/page.htm")
	public void page(
			@RequestParam(value="search") String search,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		
		Map<String, Object> searchMap = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(search)) {
			searchMap = JsonUtil.parse(search, Map.class);
		}
		
		Page<BlacklistTask> page = blacklistTaskService.page(searchMap,current, pageSize);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 添加定时任务
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/task/blacklist/addition.htm")
	public void addition(@RequestParam(value="taskName") String taskName,
			@RequestParam(value="taskType") String taskType,
			@RequestParam(value="taskData") String taskData,
			@RequestParam(value="yn") int yn,
			@RequestParam(value="remark") String remark) throws Exception {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("taskName", taskName);
		BlacklistTask task = blacklistTaskService.findSelective(paramMap);
        
        boolean flag = false;
        Map<String,Object> data = new HashMap<String,Object>();
        if (task == null) {
			task = new BlacklistTask();
			task.setCreateTime(DateUtil.getNow());
			task.setLastModifyTime(DateUtil.getNow());
			task.setRemark(remark);
			task.setTaskName(taskName);
			task.setTaskData(taskData);
			task.setTaskType(taskType);
			task.setTaskVersion(1);
			task.setYn(yn);
			flag = blacklistTaskService.save(task);
		}else {
			data.put("message", "任务已存在,请勿重复添加");
		}
        
        Map<String,Object> result = new HashMap<String,Object>();
        if (flag) {
        	result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "操作成功");
		}else {
			result.put(Constant.RESPONSE_DATA, data);
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "操作失败");
		}
        ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 启动定时任务
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/task/blacklist/up.htm")
	public void execute(@RequestParam(value = "id") Long id,@RequestParam(value = "taskVersion") Long taskVersion) {
        Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> data = new HashMap<>();
		data.put("id", id);
		data.put("yn", 1);
		data.put("taskVersion", taskVersion+1);
		boolean flag = blacklistTaskService.update(data);
        if (flag) {
        	result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "操作成功");
		}else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "操作失败");
		}
        ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 禁用定时任务
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/task/blacklist/delete.htm")
	public void delete(@RequestParam(value="id") Long id,@RequestParam(value = "taskVersion") Long taskVersion) throws Exception {
        Map<String,Object> data = new HashMap<>();
        data.put("id", id);
		data.put("yn", 2);
        boolean flag = blacklistTaskService.update(data);
        
        Map<String,Object> result = new HashMap<String,Object>();
        if (flag) {
        	result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "操作成功");
		}else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "操作失败");
		}
        ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 修改定时任务
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/task/blacklist/update.htm")
	public void update(@RequestParam(value = "id") long id,
		@RequestParam(value="taskType") String taskType,
		@RequestParam(value="taskData") String taskData,
		@RequestParam(value="taskVersion") String taskVersion,
		@RequestParam(value="remark") String remark) throws Exception {
        Map<String,Object> data = new HashMap<>();
        data.put("id", id);
        data.put("taskType", taskType);
        data.put("taskData", taskData);
        data.put("remark", remark);
		data.put("taskVersion", taskVersion+1);
        boolean flag = blacklistTaskService.update(data);
        Map<String,Object> result = new HashMap<String,Object>();
        if (flag) {
        	result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		}else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
		}
        ServletUtils.writeToResponse(response,result);
	}

}

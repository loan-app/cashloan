package com.xiji.cashloan.manage.controller;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.Channel;
import com.xiji.cashloan.cl.model.ChannelCountModel;
import com.xiji.cashloan.cl.model.ChannelModel;
import com.xiji.cashloan.cl.service.ChannelService;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.util.JsonUtil;
import com.xiji.cashloan.core.common.util.RdPage;
import com.xiji.cashloan.core.common.util.ServletUtils;
import com.xiji.cashloan.system.domain.SysRole;
import com.xiji.cashloan.system.domain.SysUser;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 渠道信息Controller
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
@SuppressWarnings("unchecked")
public class ChannelController extends ManageBaseController {

	@Resource
	private ChannelService channelService;

	private static final String ROLE_QUDAO = "QuDao";

	/**
	 * 保存
	 * @param code
	 * @param name
	 * @param linker
	 * @param phone
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/promotion/channel/save.htm", method = RequestMethod.POST)
	public void save(@RequestParam(value="code") String code,
			@RequestParam(value="name") String name,
			@RequestParam(value="linker") String linker,
			@RequestParam(value="phone") String phone) throws Exception {
		Channel channel=new Channel();
		channel.setLinker(linker);
		channel.setName(name);
		channel.setPhone(phone);
		Channel code2 = channelService.getChannelByCode(code);
		Map<String, Object> result = new HashMap<String, Object>();
		if (null != code2) {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "渠道编码已存在，不能重复");
		} else {
			channel.setCode(code);
			boolean flag = channelService.save(channel);
			if (flag) {
				result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
			} else {
				result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
			}
		}
		ServletUtils.writeToResponse(response, result);
	}

	/**
	 * 查询所有渠道信息
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/promotion/channel/listChannel.htm")
	public void listChannel() throws Exception {
		List<Channel> list = channelService.listChannel();
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_DATA, list);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response, result);
	}
	
	
	/**
	 * 渠道信息列表页查看
	 * 
	 * @param searchParams
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/promotion/channel/page.htm", method = {RequestMethod.POST,RequestMethod.GET})
	public void page(
			@RequestParam(value="searchParams",required=false) String searchParams,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Map<String, Object> searchMap = new HashMap<>();
		if (!StringUtils.isEmpty(searchParams)) {
			searchMap = JsonUtil.parse(searchParams, Map.class);
		}

		Page<ChannelModel> page = channelService.page(current, pageSize,searchMap);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response, result);
	}
	
	/**
	 * 修改
	 * @param id
	 * @param code
	 * @param name
	 * @param linker
	 * @param phone
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/promotion/channel/update.htm", method = RequestMethod.POST)
	public void update(
			@RequestParam(value="id") Long id,
			@RequestParam(value="code") String code,
			@RequestParam(value="name") String name,
			@RequestParam(value="linker") String linker,
			@RequestParam(value="phone") String phone) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		paramMap.put("name", name);
		paramMap.put("linker", linker);
		paramMap.put("phone", phone);
		Channel channelID = channelService.getChannelById(id);
		Channel code2 = channelService.getChannelByCode(code);
		Map<String, Object> result = new HashMap<String, Object>();
		if(channelID.getCode().equals(code)){
            paramMap.put("code", code);
            boolean flag = channelService.update(paramMap);
            if (flag) {
                result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
                result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
            } else {
                result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
            }
        }else {
            if (null != code2) {
                result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                result.put(Constant.RESPONSE_CODE_MSG, "渠道编码已存在，不能重复");
            } else {
                paramMap.put("code", code);
                boolean flag = channelService.update(paramMap);
                if (flag) {
                    result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
                    result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
                } else {
                    result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                    result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
                }
            }
        }
		ServletUtils.writeToResponse(response, result);
	}

	
	/**
	 * 渠道信息修改状态
	 */
	@RequestMapping(value = "/modules/manage/promotion/channel/updateState.htm", method = RequestMethod.POST)
	public void updateState(@RequestParam(value="id") Long id,
					@RequestParam(value="state") String state) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		paramMap.put("state", state);
		boolean flag = channelService.update(paramMap);
		Map<String, Object> result = new HashMap<String, Object>();
		if (flag) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
		}
		ServletUtils.writeToResponse(response, result);
	}

	/**
	 * 渠道限流信息状态
	 * todo 1：qq 2：微信 3：微博 4：其他
	 * @author wangqi
	 * @date 2019/6/14 16:22
	 * @param id
	 * @param conditions 限流具体的渠道 1QQ 2微信 3微博 4其他 限流多个渠道string类型参数用','逗号分隔
	 * */
	@RequestMapping(value = "/modules/manage/promotion/channel/updataCondition.htm", method = RequestMethod.POST)
	public void updataCondition(@RequestParam(value ="id")Long id,
								@RequestParam(value ="state")String state){
		String conditions = channelService.getConditionsById(id);
		//判断cl_channel表字段conditions是否包含新传入的参数值
		if(conditions.contains(state)){
			if(conditions.indexOf(state)==0&&conditions.length()==1) {
				String replace = conditions.replace(state, "");
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("id", id);
				paramMap.put("conditions", replace);
				boolean flag = channelService.updatecondition(paramMap);
				HashMap<String, Object> result = new HashMap<>();
				if (flag) {
					result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
					result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
				} else {
					result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
					result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
				}
				ServletUtils.writeToResponse(response, result);
			}else if(conditions.indexOf(state)==0&&conditions.length()!=1){
				String concat = state.concat(",");
				String replace = conditions.replace(concat, "");
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("id", id);
				paramMap.put("conditions", replace);
				boolean flag = channelService.updatecondition(paramMap);
				HashMap<String, Object> result = new HashMap<>();
				if (flag) {
					result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
					result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
				} else {
					result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
					result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
				}
				ServletUtils.writeToResponse(response, result);
			}else if(conditions.indexOf(state)!=0&&conditions.length()!=1){
				String s =",";
				String concat = s.concat(state);
				String replace = conditions.replace(concat, "");
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("id", id);
				paramMap.put("conditions", replace);
				boolean flag = channelService.updatecondition(paramMap);
				HashMap<String, Object> result = new HashMap<>();
				if (flag) {
					result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
					result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
				} else {
					result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
					result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
				}
				ServletUtils.writeToResponse(response, result);
			}
        }
		else {
			if (conditions.isEmpty()){
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("id",id);
				paramMap.put("conditions",state);
				boolean flag =channelService.updatecondition(paramMap);
				HashMap<String, Object> result = new HashMap<>();
				if (flag) {
					result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
					result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
				} else {
					result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
					result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
				}
				ServletUtils.writeToResponse(response, result);
			}else {
				int parseInt = Integer.parseInt(state);
				int bigint= parseInt+10;
				int smartint=parseInt-10;
				String big = String.valueOf(bigint);
				String smart = String.valueOf(smartint);
				if (conditions.contains(big)){
					String replace = conditions.replace(big, state);
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("id",id);
					paramMap.put("conditions",replace);
					boolean flag =channelService.updatecondition(paramMap);
					HashMap<String, Object> result = new HashMap<>();
					if (flag) {
						result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
						result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
					} else {
						result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
						result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
					}
					ServletUtils.writeToResponse(response, result);
				}
				if (conditions.contains(smart)){
					String replace = conditions.replace(smart, state);
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("id",id);
					paramMap.put("conditions",replace);
					boolean flag =channelService.updatecondition(paramMap);
					HashMap<String, Object> result = new HashMap<>();
					if (flag) {
						result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
						result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
					} else {
						result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
						result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
					}
					ServletUtils.writeToResponse(response, result);
				}else {
					String concat = conditions.concat(",");
					String concat1 = concat.concat(state);
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("id",id);
					paramMap.put("conditions",concat1);
					boolean flag =channelService.updatecondition(paramMap);
					HashMap<String, Object> result = new HashMap<>();
					if (flag) {
						result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
						result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
					} else {
						result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
						result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
					}
					ServletUtils.writeToResponse(response, result);
				}
			}
		}
	}

	/**
	 * 统计渠道用户信息
	 * 
	 * @param searchParams
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */ 
	@RequestMapping(value = "/modules/manage/promotion/channel/channelUserList.htm", method = {RequestMethod.POST,RequestMethod.GET})
	public void channelUserList(
			@RequestParam(value="searchParams",required=false) String searchParams,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Map<String, Object> searchMap = new HashMap<>();
		if (!StringUtils.isEmpty(searchParams)) {
			searchMap = JsonUtil.parse(searchParams, Map.class);
		} 
		Page<ChannelCountModel> page = channelService.channelUserList(current, pageSize,searchMap);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response, result);
	}
	
	/**
	 * 统计渠道用户信息
	 * @param pageSize
	 * @param current
	 * @param searchParams
	 *
	 * 2017年7月19日 20:49:47
	 */
	@RequestMapping(value = "/modules/manage/promotion/channel/channelUserCount.htm", method = {RequestMethod.POST,RequestMethod.GET})
	public void channelUserCount(
			@RequestParam(value="searchParams",required=false) String searchParams,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize, HttpServletRequest request) throws Exception {
		Map<String, Object> searchMap = new HashMap<>();
		if (!StringUtils.isEmpty(searchParams)) {
			searchMap = JsonUtil.parse(searchParams, Map.class);
		}
		Page<Map<String,Object>> page = new Page<>();
		SysRole sysRole = getRoleForLoginUser(request);
		SysUser loginUser = getLoginUser(request);
		//临时解决方案,如果用户角色为QuDao,根据登录用户名,去查询渠道的统计信息
		if(ROLE_QUDAO.equals(sysRole.getNid())) {
			String name = loginUser.getName();
			searchMap.put("name", name);
			page = (Page<Map<String, Object>>) channelService.oneChannelUserCount(searchMap,current,pageSize);
		} else {
			page = (Page<Map<String, Object>>) channelService.channelUserCount(searchMap,current,pageSize);
		}

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response, result);
	}
	
	@RequestMapping(value = "/modules/manage/promotion/channel/channelCount.htm", method = {RequestMethod.POST,RequestMethod.GET})
	public void channelCount(
			@RequestParam(value="searchParams",required=false) String searchParams) throws Exception {
		Map<String, Object> searchMap = new HashMap<>();
		if (!StringUtils.isEmpty(searchParams)) {
			searchMap = JsonUtil.parse(searchParams, Map.class);
		}
		List<Map<String,Object>> list = channelService.conversion(searchMap);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_DATA, list);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response, result);
	}
	
}

package com.xiji.cashloan.manage.controller;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.service.ChannelService;
import com.xiji.cashloan.cl.service.ClBorrowService;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.exception.ServiceException;
import com.xiji.cashloan.core.common.util.*;
import com.xiji.cashloan.core.common.web.controller.BaseController;
import com.xiji.cashloan.rc.service.TppBusinessService;
import com.xiji.cashloan.system.domain.SysConfig;
import com.xiji.cashloan.system.domain.SysUser;
import com.xiji.cashloan.system.model.SysConfigModel;
import com.xiji.cashloan.system.permission.annotation.RequiresPermission;
import com.xiji.cashloan.system.service.SysConfigService;
import com.xiji.cashloan.system.service.SysDictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tool.util.BigDecimalUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 * details: 系统参数表,Action请求层
 * source:  代码生成器
 */
@Scope("prototype")
@Controller
public class SysConfigController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(SysConfigController.class);
	@Resource
	private SysConfigService sysConfigService;
	@Resource
	private SysDictService sysDictService;
	@Resource
	private TppBusinessService tppBusinessService;
	@Resource
	private ClBorrowService clBorrowService;
	@Resource
	private ChannelService channelService;
    /**
     * 系统参数表表,插入数据
     * @param response      页面的response
     * @param json          页面参数
     * @throws ServiceException
     */
	@RequestMapping("/modules/manage/system/config/save.htm")
    @RequiresPermission(code = "modules:manage:system:config:save", name = "系统管理-系统参数设置-新增")
    public void saveOrUpdate(HttpServletRequest request,HttpServletResponse response,
    	@RequestParam(value = "json" ,required = false)String json,
    	@RequestParam(value = "status" ,required = false)String status//执行的动作
    	)throws Exception {
	     Map<String,Object> returnMap=new HashMap<String,Object>();
	     long flag;

        SysConfig sysConfig = new SysConfig();
        //对json对象进行转换
        if (!StringUtils.isEmpty(json))
            sysConfig = JsonUtil.parse(json, SysConfig.class);
		if("create".equals(status)){
			  SysUser sysUser = this.getLoginUser(request);
			  sysConfig.setStatus(1);//新建时有效
			  sysConfig.setCreator(sysUser.getId());
		//动态插入数据
			flag=sysConfigService.insertSysConfig(sysConfig);
		}else{
		 //修改数据
			flag=sysConfigService.updateSysConfig(sysConfig);
		}
		
		if(flag>0){//判断操作是否成功
			if(sysConfig.getCode().equals("fee")){
				SysConfig config = sysConfigService.findByCode("borrow_day");
				if((BigDecimalUtil.div(Double.parseDouble(sysConfig.getValue()),Double.parseDouble(config.getValue()))*365)>0.36) {
					returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
					returnMap.put(Constant.RESPONSE_CODE_MSG, "根据我国相关法律条例规定，年利率不得高于36%");
				}else {
					returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
					returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS + ",刷新缓存后生效");
				}
			}else if(sysConfig.getCode().equals("borrow_day")){
				SysConfig config = sysConfigService.findByCode("fee");
				if((BigDecimalUtil.div(Double.parseDouble(config.getValue()),Double.parseDouble(sysConfig.getValue()))*365)>0.36){
					returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
					returnMap.put(Constant.RESPONSE_CODE_MSG, "根据我国相关法律条例规定，年利率不得高于36%");
				}else {
					returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
					returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS + ",刷新缓存后生效");
				}
			} else if (sysConfig.getCode().equals("delay_fee")){
				SysConfig config = sysConfigService.findByCode("borrow_day");
				if((BigDecimalUtil.div(Double.parseDouble(sysConfig.getValue()),Double.parseDouble(config.getValue()))*365)>0.36){
					returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
					returnMap.put(Constant.RESPONSE_CODE_MSG, "根据我国相关法律条例规定，年利率不得高于36%");
				}else {
					returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
					returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS + ",刷新缓存后生效");
				}
			} else {
				returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS + ",刷新缓存后生效");
			}
        }else{
        	returnMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
        	returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
        }
        //返回给页面
        ServletUtils.writeToResponse(response, returnMap);
    }

    

    /**
     * 系统参数表,查询数据
     * @param response      页面的response
     * @param current       当前页数
     * @param pageSize      每页限制
     * @param searchParams   查询条件
     * @throws ServiceException
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/modules/manage/system/config/list.htm")
    @RequiresPermission(code = "modules:manage:system:config:list", name = "系统管理-系统参数设置-查询")
    public void listConfigs ( HttpServletRequest request,HttpServletResponse response,
                        @RequestParam(value = "current") Integer current,
                        @RequestParam(value = "pageSize") Integer pageSize,
                        @RequestParam(value = "searchParams",required = false)String searchParams)
    throws Exception{
		Map<String, Object> paramap = new HashMap<String, Object>();
    	if (!StringUtils.isEmpty(searchParams)){
    		paramap = JsonUtil.parse(searchParams, Map.class);
    	}
    	List<Map<String,Object>> typeList = new ArrayList<Map<String,Object>>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
    	//获取系统参数类型数据字典
    	List<Map<String, Object>> dicList = sysDictService.getDictsCache("SYSTEM_TYPE");
		for (Map<String, Object> dic : dicList) {
    		Map<String, Object> types = new HashMap<String, Object>();
    		types.put("systemType", dic.get("value"));
			types.put("systemTypeName", dic.get("text"));
    		dataMap.put((String) dic.get("value"), dic.get("text"));
    		typeList.add(types);
    	}
    	//返回页面的json参数
		Page<SysConfig> page = sysConfigService.getSysConfigPageList(current,pageSize,paramap);
    	
		List<SysConfigModel> sysModel = new ArrayList<SysConfigModel>();
		if (page != null && !page.isEmpty()) {
			for (SysConfig sys : page) {
				SysConfigModel model = new SysConfigModel();
				model = model.getSysModel(sys, dataMap);
				sysModel.add(model);
			}
		}
		Map<String, Object> returnMap = new HashMap<String,Object>();
    	
    	//返回给页面
    	returnMap.put("dicData", typeList);
    	returnMap.put(Constant.RESPONSE_DATA, sysModel);
    	returnMap.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
    	returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
    	returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);

    	ServletUtils.writeToResponse(response, returnMap);
    }
    
    /**
     * 系统参数表表,逻辑删除 修改状态
     * @param response      页面的response
     * @param id            页面参数
     * @throws ServiceException
     */
    @RequestMapping("/modules/manage/system/config/delete.htm")
    @RequiresPermission(code = "modules:manage:system:config:delete", name = "系统管理-系统参数设置- 修改状态")
    public void deleteSysConfig(HttpServletRequest request,HttpServletResponse response,
    	@RequestParam(value = "id" )String id,
    	@RequestParam(value = "status" )int status
    	)throws Exception {
	     Map<String,Object> returnMap=new HashMap<String,Object>();
	     long flag;
        SysConfig sysConfig = new SysConfig();
		 //修改数据
        	sysConfig.setId(Long.valueOf(id));
        	sysConfig.setStatus(status);
			flag=sysConfigService.updateSysConfig(sysConfig);
		
		if(flag>0){//判断操作是否成功
        	returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        	returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS+",刷新缓存后生效");
        }else{
        	returnMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
        	returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
        }
        //返回给页面
        ServletUtils.writeToResponse(response, returnMap);
    }
    
    /**
     * 重加载系统配置数据
     * 
     * @throws Exception
     */
    @RequestMapping("/modules/manage/system/config/reload.htm")
    @RequiresPermission(code = "modules:manage:system:config:reload", name = "系统管理-系统参数设置-缓存数据重加载")
    public void reload() throws Exception {
        Map<String, Object> returnMap = new HashMap<String, Object>();

    	//修改可用注册时可用额度
//    	SysConfig config = sysConfigService.findByCode("init_credit");
//    	clBorrowService.changeCreditTotal(Double.valueOf(config.getValue()));
        //注册额度
        String init_credit = sysConfigService.selectByCode("init_credit");
    	//展期天数
        String delay_fee = sysConfigService.selectByCode("delay_fee");
        //综合费率
        String fee = sysConfigService.selectByCode("fee");
        //借款额度
        String borrow_credit = sysConfigService.selectByCode("borrow_credit");
        //还款成功单次增加的额度
        String one_repay_credit = sysConfigService.selectByCode("one_repay_credit");
        //还款成功累计提额上限
        String imporove_credit_limit = sysConfigService.selectByCode("imporove_credit_limit");
        //还款提额次数
        String count_improve_credit = sysConfigService.selectByCode("count_improve_credit");
        //借款天数
        String borrow_day = sysConfigService.selectByCode("borrow_day");
        //是否启用砍头期
        String behead_fee = sysConfigService.selectByCode("behead_fee");
        //还款提额开关
        String is_improve_credit = sysConfigService.selectByCode("is_improve_credit");

        Map<String, Object> map = new HashMap<String, Object>();
        if (!StringUtil.equalsIgnoreCase(init_credit,Global.getValue("init_credit"))){
            clBorrowService.changeCreditTotal(Double.valueOf(init_credit));
            map.put("initCredit",init_credit);
        }
        if (!StringUtil.equalsIgnoreCase(delay_fee,Global.getValue("delay_fee"))){
            map.put("delayFee",delay_fee);
        }
        if (!StringUtil.equalsIgnoreCase(fee,Global.getValue("fee"))){
            map.put("fee",fee);
        }
        if (!StringUtil.equalsIgnoreCase(borrow_credit,Global.getValue("borrow_credit"))){
            map.put("borrowCredit",borrow_credit);
        }
        if (!StringUtil.equalsIgnoreCase(one_repay_credit,Global.getValue("one_repay_credit"))){
            map.put("oneRepayCredit",one_repay_credit);
        }
        if (!StringUtil.equalsIgnoreCase(imporove_credit_limit,Global.getValue("imporove_credit_limit"))){
            map.put("improveCreditLimit",imporove_credit_limit);
        }
        if (!StringUtil.equalsIgnoreCase(count_improve_credit,Global.getValue("count_improve_credit"))){
            map.put("countImproveCredit",count_improve_credit);
        }
        if (!StringUtil.equalsIgnoreCase(borrow_day,Global.getValue("borrow_day"))){
            map.put("borrowDay",borrow_day);
        }
        if (!StringUtil.equalsIgnoreCase(behead_fee,Global.getValue("behead_fee"))){
            map.put("beheadFee",behead_fee);
        }
        if (!StringUtil.equalsIgnoreCase(is_improve_credit,Global.getValue("is_improve_credit"))){
            map.put("isImproveCredit",is_improve_credit);
        }
        //批量修改渠道信息
        if (map !=null && map.size()>0){

            boolean flag = channelService.batchUpdateChannel(map);
        }

        // 调用缓存辅助类 重加载系统配置数据
		CacheUtil.initSysConfig();


        //前台缓存清理
        String webCleanUrl = Global.getValue("server_host") + "/system/config/reload.htm";
        String webResult = null;
        try {
        	webResult = HttpUtil.getHttpResponse(webCleanUrl);
        	logger.info("刷新api缓存结果:" + webResult);
        } catch (Exception e) {
        	logger.info("刷新api缓存出错");
        	logger.error(e.getMessage(),e);
        }
        
        if(StringUtil.isNotBlank(webResult)){
        	@SuppressWarnings("unchecked")
			Map<String, Object> result = JsonUtil.parse(webResult, Map.class);
			String resultCode = StringUtil.isNull(result.get(Constant.RESPONSE_CODE));
        	if (StringUtil.isNotBlank(resultCode)
        			&& StringUtil.isNull(Constant.SUCCEED_CODE_VALUE).equals(resultCode)) {
        		returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        		returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
        	}else{
        		returnMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
        		returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
        	}
        }else{
        	returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        	returnMap.put(Constant.RESPONSE_CODE_MSG, "后台缓存刷新完成,前台缓存刷新失败");
        }

        // 返回给页面
        ServletUtils.writeToResponse(response, returnMap);
    }
    
	public String appendContent(String content, SysConfig sysConfig) {
		String status = sysConfig.getStatus() == 1 ? "启用" : "关闭";
		return content += DateUtil.dateStr(new Date()) + ": <"
				+ sysConfig.getName() + ">" + "配置详情：" + "    id:"
				+ sysConfig.getId() + "    type:" + sysConfig.getType()
				+ "    code:" + sysConfig.getCode() + "    value:"
				+ sysConfig.getValue() + "     状态:" + status + "\r\n";
	}
}


package com.xiji.creditrank.cr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xiji.creditrank.cr.service.CardService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.util.JsonUtil;
import com.xiji.cashloan.core.common.util.RdPage;
import com.xiji.cashloan.core.common.util.ServletUtils;
import com.xiji.cashloan.core.common.web.controller.BaseController;
import com.xiji.creditrank.cr.model.FactorModel;
import com.xiji.creditrank.cr.model.FactorParamModel;
import com.xiji.creditrank.cr.service.FactorParamService;
import com.xiji.creditrank.cr.service.FactorService;
import com.xiji.creditrank.cr.service.ItemService;

 /**
 * 评分因子Controller
 *
  * @author wnb
  * @date 2018/11/27
  * @version 1.0.0
 * Copyright 杭州融都科技股份有限公司  creditrank All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Scope("prototype")
@Controller
public class FactorController extends BaseController {

	@Resource
	private FactorService factorService;
	
	@Resource
	private FactorParamService factorParamService;
	

	@Resource
	private ItemService itemService;
	
	@Resource
	private CardService cardService;
	
	/**
	 * 查询评分因子列表
	 * @param secreditrankh
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/modules/manage/cr/factor/page.htm", method=RequestMethod.POST)
	public void page(
			@RequestParam(value="secreditrankh",required=false) String secreditrankh,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Map<String,Object> secreditrankhMap = JsonUtil.parse(secreditrankh, Map.class);
		Page<FactorModel> page = factorService.page(secreditrankhMap,current, pageSize);
		for (FactorModel factorModel : page) {
			factorModel.setTab(factorModel.getCtable()+","+factorModel.getCcolumn());
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("factorId", factorModel.getId());
			List<FactorParamModel> paramList = factorParamService.listSelect(param);
			factorModel.setChildren(paramList);
		}
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 新增修改评分因子及参数
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked"})
	@RequestMapping(value = "/modules/manage/cr/factor/save.htm", method=RequestMethod.POST)
	public void save(
			@RequestParam(value="factorModel") String factorModel,
			@RequestParam(value="secreditrankh") String secreditrankh
			) throws Exception {
		List<Map<String,Object>> list = JsonUtil.parse(secreditrankh, List.class);
		Map<String,Object> factorMap = JsonUtil.parse(factorModel, Map.class);
		
		Map<String,Object> result = factorService.save(factorMap,list);
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 修改评分因子
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/modules/manage/cr/factor/update.htm", method=RequestMethod.POST)
	public void update(
			@RequestParam(value="factorModel") String factorModel,
			@RequestParam(value="secreditrankh",required=false) String secreditrankh) 
					throws Exception {
		List<Map<String,Object>> list = JsonUtil.parse(secreditrankh, List.class);
		Map<String,Object> factorMap = JsonUtil.parse(factorModel, Map.class);
		Map<String,Object> result = factorService.updateSelective(factorMap,list);
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 删除评分因子
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/cr/factor/delete.htm", method=RequestMethod.POST)
	public void delete(
			@RequestParam(value = "id") long id) throws Exception {
		Map<String,Object> result = factorService.deleteSelective(id);
		ServletUtils.writeToResponse(response,result);
	}
	
}

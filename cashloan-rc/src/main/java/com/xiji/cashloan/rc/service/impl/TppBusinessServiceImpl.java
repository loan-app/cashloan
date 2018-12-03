package com.xiji.cashloan.rc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.rc.domain.TppBusiness;
import com.xiji.cashloan.rc.mapper.TppBusinessMapper;
import com.xiji.cashloan.rc.model.ManageTppBusinessModel;
import com.xiji.cashloan.rc.model.TppBusinessModel;
import com.xiji.cashloan.rc.service.TppBusinessService;
import org.springframework.stereotype.Service;

import tool.util.DateUtil;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


/**
 * 第三方征信接口信息ServiceImpl
 * 
 * @author zlh
 * @version 1.0.0
 * @date 2017-03-14 13:41:57
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 创新一部：rdc@xiji.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("tppBusinessService")
public class TppBusinessServiceImpl extends BaseServiceImpl<TppBusiness, Long> implements TppBusinessService {
	
	@Resource
	private TppBusinessMapper tppBusinessMapper;

	@Override
	public BaseMapper<TppBusiness, Long> getMapper() {
		return tppBusinessMapper;
	}

	@Override
	public List<TppBusinessModel> listAll() {
		return tppBusinessMapper.listAll();
	}

	@Override
	public Page<ManageTppBusinessModel> page(Map<String, Object> paramMap,
											 int current, int pageSize) {
		PageHelper.startPage(current, pageSize);
		Page<ManageTppBusinessModel> page = (Page<ManageTppBusinessModel>) tppBusinessMapper.list(paramMap);
		return page;
	}

	@Override
	public List<TppBusiness> listSelective(Map<String, Object> paramMap) {
		return tppBusinessMapper.listSelective(paramMap);
	}

	@Override
	public boolean save(TppBusiness tppBusiness) {
		tppBusiness.setState("10");
		tppBusiness.setAddTime(DateUtil.getNow());

		int result = tppBusinessMapper.save(tppBusiness);
		if (result > 0L) {
			return true;
		}
		return false;
	}

	@Override
	public boolean update(TppBusiness tppBusiness) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", tppBusiness.getId());
		paramMap.put("nid", tppBusiness.getNid());
		paramMap.put("name", tppBusiness.getName());
		paramMap.put("url", tppBusiness.getUrl());
		paramMap.put("testUrl", tppBusiness.getTestUrl());
		paramMap.put("extend", tppBusiness.getExtend());
		int result = tppBusinessMapper.updateSelective(paramMap);
		if (result > 0L) {
			return true;
		}
		return false;
	}

	@Override
	public boolean enable(Long id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		paramMap.put("state", "10");
		int result = tppBusinessMapper.updateSelective(paramMap);
		if (result > 0L) {
			return true;
		}
		return false;
	}

	@Override
	public boolean disable(Long id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		paramMap.put("state", "20");
		int result = tppBusinessMapper.updateSelective(paramMap);
		if (result > 0L) {
			return true;
		}
		return false;
	}

	@Override
	public boolean tppBusinessExist(TppBusiness business) {
		TppBusiness rtTppBusi = tppBusinessMapper.findByNid(business.getNid(), business.getTppId());
		if(rtTppBusi!=null){
			return true;
		}
		return false;
	}

	@Override
	public TppBusiness findByNid(String nid,Long tppId) {
		return tppBusinessMapper.findByNid(nid,tppId);
	}

}
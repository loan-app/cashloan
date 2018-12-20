package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.mapper.AppListMapper;
import com.xiji.cashloan.cl.domain.AppList;
import com.xiji.cashloan.cl.service.AppListService;


/**
 * 运营商报告ServiceImpl
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2018-12-20 11:26:07
 */
 
@Service("appListService")
public class AppListServiceImpl extends BaseServiceImpl<AppList, Long> implements AppListService {
	
    private static final Logger logger = LoggerFactory.getLogger(AppListServiceImpl.class);
   
    @Resource
    private AppListMapper appListMapper;

	@Override
	public BaseMapper<AppList, Long> getMapper() {
		return appListMapper;
	}
	
}
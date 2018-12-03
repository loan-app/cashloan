package com.rongdu.cashloan.cl.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.rongdu.cashloan.cl.domain.SmsTpl;
import com.rongdu.cashloan.core.common.service.BaseService;

/**
 * 短信记录Service
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-03-13 18:36:01
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface SmsTplService extends BaseService<SmsTpl, Long>{
	
	Page<SmsTpl> list(Map<String, Object> params,int currentPage, int pageSize);
	
	int updateSelective(Map<String, Object> paramMap);
}

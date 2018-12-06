package com.xiji.cashloan.cl.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.SmsTpl;
import com.xiji.cashloan.core.common.service.BaseService;

/**
 * 短信记录Service
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface SmsTplService extends BaseService<SmsTpl, Long>{
	
	Page<SmsTpl> list(Map<String, Object> params,int currentPage, int pageSize);
	
	int updateSelective(Map<String, Object> paramMap);
}

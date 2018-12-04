package com.xiji.cashloan.core.common.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.BaseService;

/**
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 * @param <T>
 * @param <ID>
 */
public abstract class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {
	
	@Resource
	private BaseMapper<T, ID> baseMapper;
	
	public int insert(T record) {
		return getMapper().save(record);
	}

	public T getById(ID id) {
		return getMapper().findByPrimary(id);
	}

	public int updateById(T record) {
		return getMapper().update(record);
	}

	public abstract BaseMapper<T, ID> getMapper();
	
	/*protected String getLoginName() {
		// 增加用户登录判断
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user.getUsername();
	}*/

}

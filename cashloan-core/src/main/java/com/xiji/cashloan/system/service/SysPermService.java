package com.xiji.cashloan.system.service;

import java.util.List;
import java.util.Map;

import com.xiji.cashloan.core.common.service.BaseService;
import com.xiji.cashloan.system.domain.SysPerm;

/**
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 */
public interface SysPermService extends BaseService<SysPerm, Long>{

	int updateByPrimaryKeySelective(SysPerm record);
	    
	List<SysPerm> listByUserName(String userName);
	
	List<SysPerm> listByRoleId(Long roleId);
	
	List<Map<String, Object>> fetchAll();
}

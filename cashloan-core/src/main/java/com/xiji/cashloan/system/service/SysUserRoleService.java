package com.xiji.cashloan.system.service;

import java.util.List;

import com.xiji.cashloan.core.common.exception.ServiceException;
import com.xiji.cashloan.system.domain.SysUserRole;


/**
 * 
 * 用户角色关联信息service接口
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 */
public interface SysUserRoleService {
	/**
	 * 用户角色关联信息查询
	 * @param userId 角色ID
	 * @return 关联信息List
	 * @throws ServiceException 
	 */
	List<SysUserRole> getSysUserRoleList(Long userId) throws ServiceException;
	
}

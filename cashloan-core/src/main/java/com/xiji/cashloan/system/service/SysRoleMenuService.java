package com.xiji.cashloan.system.service;

import java.util.List;

import com.xiji.cashloan.core.common.exception.PersistentDataException;
import com.xiji.cashloan.core.common.exception.ServiceException;
import com.xiji.cashloan.system.domain.SysRoleMenu;


/**
 * 
 * 角色菜单关联信息service接口
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 */
public interface SysRoleMenuService {
	/**
	 * 角色菜单关联信息查询
	 * @param roleId 角色ID
	 * @return 角色List
	 * @throws ServiceException 
	 * @throws PersistentDataException 
	 */
	List<SysRoleMenu> getRoleMenuList(Long roleId) throws ServiceException, PersistentDataException;
	
}

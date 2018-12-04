package com.xiji.cashloan.system.service;

import java.util.List;

import com.xiji.cashloan.core.common.service.BaseService;
import com.xiji.cashloan.system.domain.SysRolePerm;

/**
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 */
public interface SysRolePermService extends BaseService<SysRolePerm, Long> {

	/**
	 * 删除角色所有权限
	 * @param roleId
	 * @return
	 */
	int deleteByRoleId(Integer roleId);
	
	/**
	 * 修改用户权限
	 * @param roleId
	 * @param permIds
	 */
	void updatePerms(Integer roleId, List<Integer> permIds,String user);
}

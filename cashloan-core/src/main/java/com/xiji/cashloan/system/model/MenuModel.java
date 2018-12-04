package com.xiji.cashloan.system.model;

import com.xiji.cashloan.system.domain.SysMenu;

/**
 * 
 * 系统菜单model
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 */
public class MenuModel extends SysMenu {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -616526029044963364L;
	
	/**
	 * 角色ID：security扩展类
	 */
	private Long roleId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}

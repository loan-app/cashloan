package com.xiji.cashloan.system.mapper;

import java.util.List;

import com.xiji.cashloan.core.common.exception.PersistentDataException;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.cashloan.system.domain.SysRoleMenu;

/**
 * 
 * 角色菜单关系DAO
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 */
@RDBatisDao
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu,Long> {
	
	/**
	 * 删除角色菜单关联表信息（物理删除）
	 * @param roleId 角色ID
	 * @version 1.0
	 * @author 吴国成
	 * @created 2014年9月22日
	 */
	void deleteByRoleId(long roleId);
	
	/**
	 * 角色菜单关联信息查询 LIST
	 * @param roleId  角色ID
	 * @return 角色菜单关系列表
	 * @version 1.0
	 * @author 吴国成
	 * @throws PersistentDataException 
	 * @created 2014年9月22日
	 */
	List<SysRoleMenu> getRoleMenuList(long roleId) throws PersistentDataException;
	
	void addRoleMenu(long roleId,Long menuId);
}

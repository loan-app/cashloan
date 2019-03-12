package com.xiji.cashloan.system.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xiji.cashloan.core.common.exception.BussinessException;
import com.xiji.cashloan.core.common.exception.ServiceException;
import com.xiji.cashloan.system.domain.SysRole;

/**
 * 
 * 系统角色服务类
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 */
public interface SysRoleService {

	/**
	 * 查询查询用户所拥有的角色
	 * @param userId
	 *            用户ID
	 * @return 角色List
	 */
	List<SysRole> getRoleListByUserId(long userId) throws ServiceException;

	/**
	 * 查询角色
	 * @param id
	 *            主键ID
	 * @return 角色
	 */
	SysRole getRoleById(long id)throws ServiceException ;

	/**
	 * 角色查询
	 * @return 角色List
	 */
	List<SysRole> getList(Map<String, Object> paramMap);

	/**
	 * 根据角色唯一标示查询用户信息角色信息
	 * @param uid 角色唯一标示
	 * @return 角色信息
	 */
	SysRole getRoleByNid(String uid) throws ServiceException;

	/**
	 * 角色删除
	 * @param id
	 *            主键ID
	 */
	int deleteRole(long id)throws ServiceException;
	
	int getRolecount(Map<String, Object> mapdata)throws ServiceException;

	Page<SysRole> getRolePageList(int currentPage,int pageSize,Map<String, Object> mapdata) throws ServiceException;

	long addRole(SysRole role) throws BussinessException;
	
	long insertByMap(Map<String, Object> data) throws ServiceException;
	
	int  updateRole(Map<String, Object> arg) throws BussinessException;
	
	List<Map<String,Object>> getByUserPassRolesList(String username,String password) throws ServiceException;
	
	/**
	 * 根据标识nid查询
	 * @param nid
	 * @return
	 */
	SysRole findByNid(String nid);
}

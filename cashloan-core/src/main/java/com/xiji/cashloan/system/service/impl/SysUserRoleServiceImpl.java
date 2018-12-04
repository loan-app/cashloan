package com.xiji.cashloan.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.system.domain.SysUserRole;
import com.xiji.cashloan.system.mapper.SysUserRoleMapper;
import com.xiji.cashloan.system.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.exception.ServiceException;

/**
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 */
@Service(value = "sysUserRoleServiceImpl")
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole, Long> implements SysUserRoleService {

	@Resource
	private SysUserRoleMapper sysUserRoleDao;
	
	@Override
	public List<SysUserRole> getSysUserRoleList(Long userId) throws ServiceException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		return sysUserRoleDao.getItemListByMap(map);
	}


	@Override
	public BaseMapper<SysUserRole, Long> getMapper() {
		return sysUserRoleDao;
	}
	


}

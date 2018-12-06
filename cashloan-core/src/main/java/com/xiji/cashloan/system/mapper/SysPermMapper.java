/**
 *
 *
 * @author rd
 * @version 1.0.0.0
 * @date 2016年12月01日 下午16:01:55
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
package com.xiji.cashloan.system.mapper;

import java.util.List;
import java.util.Map;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.cashloan.system.domain.SysPerm;


/**
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 */
@RDBatisDao
public interface SysPermMapper extends BaseMapper<SysPerm,Long>  {

    SysPerm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysPerm record);
    
    List<SysPerm> listByUserName(String userName);
    
    List<SysPerm> selectAll();
    
	List<SysPerm> listByRoleId(Long roleId);

	List<Map<String, Object>> fetchAll();
}
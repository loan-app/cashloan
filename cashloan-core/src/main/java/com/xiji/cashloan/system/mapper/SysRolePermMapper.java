/**
 *
 *
 * @author rd
 * @version 1.0.0.0
 * @date 2016年12月02日 下午14:56:41
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
package com.xiji.cashloan.system.mapper;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.cashloan.system.domain.SysRolePerm;


/**
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 */
@RDBatisDao
public interface SysRolePermMapper extends BaseMapper<SysRolePerm, Long> {

    SysRolePerm selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRolePerm record);
    
    int deleteByRoleId(Integer roleId);
    
}
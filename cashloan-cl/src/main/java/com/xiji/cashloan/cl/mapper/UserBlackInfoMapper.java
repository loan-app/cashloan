package com.xiji.cashloan.cl.mapper;

import java.util.Map;

import com.xiji.cashloan.cl.domain.UserBlackInfo;
import org.apache.ibatis.annotations.Param;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;

/**
 * 黑名单Dao
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/12/03
 *
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface UserBlackInfoMapper extends BaseMapper<UserBlackInfo, Long> {

	UserBlackInfo findByIdNo(@Param("idNo")String idNo);
	
	int deleteByID(Long id);
	
	int deleteByPhone(Map<String,Object> params);
	
	int deleteByIdNo(Map<String,Object> params);
}

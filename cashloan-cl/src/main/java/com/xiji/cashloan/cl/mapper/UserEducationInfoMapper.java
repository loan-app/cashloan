package com.xiji.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.xiji.cashloan.cl.domain.UserEducationInfo;
import com.xiji.cashloan.cl.model.UserEducationInfoModel;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;

/**
 * 学信查询记录表Mapper
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/12/03
 *
 *
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface UserEducationInfoMapper extends BaseMapper<UserEducationInfo,Long> {

	/**
	 * 列表查询
	 * @param searchMap
	 * @return
	 */
	List<UserEducationInfoModel> page(Map<String, Object> searchMap);

    

}

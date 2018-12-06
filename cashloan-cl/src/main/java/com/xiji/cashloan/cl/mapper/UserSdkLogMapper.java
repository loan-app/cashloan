package com.xiji.cashloan.cl.mapper;

import java.util.Map;

import com.xiji.cashloan.cl.domain.UserSdkLog;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;


/**
 * sdk识别记录表Dao
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
public interface UserSdkLogMapper extends BaseMapper<UserSdkLog,Long> {

	/**
	 * 查询当日可识别次数
	 * @param searchMap
	 * @return
	 */
	int countDayTime(Map<String, Object> searchMap);

    

}

package com.rongdu.cashloan.cl.mapper;

import java.util.Map;

import com.rongdu.cashloan.cl.domain.UserSdkLog;
import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.mapper.RDBatisDao;


/**
 * sdk识别记录表Dao
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-04-20 09:52:08
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 创新一部：rdc@erongdu.com
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

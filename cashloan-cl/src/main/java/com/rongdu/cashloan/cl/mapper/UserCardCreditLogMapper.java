package com.rongdu.cashloan.cl.mapper;

import java.util.Map;

import com.rongdu.cashloan.cl.domain.UserCardCreditLog;
import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.mapper.RDBatisDao;

/**
 * 人脸识别请求记录Dao
 * 
 * @author jdd
 * @version 1.0.0
 * @date 2017-04-10 14:37:56
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface UserCardCreditLogMapper extends BaseMapper<UserCardCreditLog,Long> {
	/**
	 * 获取用户当天请求次数
	 * @param paramMap
	 * @return
	 */
	int countByUserId(Map<String, Object> paramMap);

    

}

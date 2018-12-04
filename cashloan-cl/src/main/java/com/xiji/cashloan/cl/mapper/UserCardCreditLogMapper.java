package com.xiji.cashloan.cl.mapper;

import java.util.Map;

import com.xiji.cashloan.cl.domain.UserCardCreditLog;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;

/**
 * 人脸识别请求记录Dao
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/12/03
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
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

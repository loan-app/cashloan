package com.rongdu.cashloan.cl.mapper;

import com.rongdu.cashloan.cl.domain.Zhima;
import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.mapper.RDBatisDao;

/**
 * 芝麻信用Dao
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-02-14 11:35:27
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface ZhimaMapper extends BaseMapper<Zhima,Long> {
	
	Zhima findByUserId(long userId);

}

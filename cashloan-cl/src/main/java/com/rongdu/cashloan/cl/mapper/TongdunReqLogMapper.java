package com.rongdu.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.rongdu.cashloan.cl.domain.TongdunReqLog;
import com.rongdu.cashloan.cl.model.TongdunReqLogModel;
import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.mapper.RDBatisDao;

/**
 * 同盾第三方请求记录Dao
 * 
 * @author jdd
 * @version 1.0.0
 * @date 2017-04-26 15:26:56
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 创新一部：rdc@erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface TongdunReqLogMapper extends BaseMapper<TongdunReqLog,Long> {

	List<TongdunReqLogModel> listModelByMap(Map<String, Object> params);

	TongdunReqLogModel findModelById(long id);

    

}

package com.rongdu.cashloan.rc.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.service.impl.BaseServiceImpl;
import com.rongdu.cashloan.rc.domain.TppReqLog;
import com.rongdu.cashloan.rc.mapper.TppReqLogMapper;
import com.rongdu.cashloan.rc.service.TppReqLogService;


/**
 * 第三方征信请求记录ServiceImpl
 * 
 * @author zlh
 * @version 1.0.0
 * @date 2017-03-20 13:50:34
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 创新一部：rdc@erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("tppReqLogService")
public class TppReqLogServiceImpl extends BaseServiceImpl<TppReqLog, Long> implements TppReqLogService {
   
    @Resource
    private TppReqLogMapper tppReqLogMapper;

	@Override
	public BaseMapper<TppReqLog, Long> getMapper() {
		return tppReqLogMapper;
	}

	@Override
	public int modifyTppReqLog(TppReqLog log) {
		return tppReqLogMapper.modifyTppReqLog(log);
	}
	
	@Override
	public TppReqLog findSelective(Map<String, Object> params) {
		return tppReqLogMapper.findSelective(params);
	}
	
}
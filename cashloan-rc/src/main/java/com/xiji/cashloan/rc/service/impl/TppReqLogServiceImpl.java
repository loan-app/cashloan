package com.xiji.cashloan.rc.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.rc.domain.TppReqLog;
import com.xiji.cashloan.rc.mapper.TppReqLogMapper;
import com.xiji.cashloan.rc.service.TppReqLogService;


/**
 * 第三方征信请求记录ServiceImpl
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 *
 *
 *
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
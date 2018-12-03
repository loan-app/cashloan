package com.rongdu.cashloan.cl.service.impl;

import javax.annotation.Resource;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rongdu.cashloan.cl.domain.OperatorVoice;
import com.rongdu.cashloan.cl.mapper.OperatorVoiceMapper;
import com.rongdu.cashloan.cl.service.OperatorVoiceService;
import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.service.impl.BaseServiceImpl;
import com.rongdu.cashloan.core.common.util.ShardTableUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 魔蝎运营商数据-通话记录ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-11-24 14:50:00
 */
 
@Service("operatorVoiceService")
public class OperatorVoiceServiceImpl extends BaseServiceImpl<OperatorVoice, Long> implements OperatorVoiceService {
	
    private static final Logger logger = LoggerFactory.getLogger(OperatorVoiceServiceImpl.class);
   
    @Resource
    private OperatorVoiceMapper operatorVoiceMapper;

	@Override
	public BaseMapper<OperatorVoice, Long> getMapper() {
		return operatorVoiceMapper;
	}

	@Override
	public Page<OperatorVoice> findShardPage(Map<String, Object> params, int currentPage, int pageSize) {
		long userId = (long) params.get("userId");
		// 分表
		String tableName = ShardTableUtil.generateTableNameById("cl_operator_voice", userId, 30000);
		int countTable = operatorVoiceMapper.countTable(tableName);
		if (countTable == 0) {
			operatorVoiceMapper.createTable(tableName);
		}

		PageHelper.startPage(currentPage, pageSize);
		List<OperatorVoice> list = operatorVoiceMapper.listShardSelective(tableName, params);
		return (Page<OperatorVoice>) list;
	}
}
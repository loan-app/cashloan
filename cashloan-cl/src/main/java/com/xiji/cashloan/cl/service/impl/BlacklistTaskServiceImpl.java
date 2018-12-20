package com.xiji.cashloan.cl.service.impl;

import com.xiji.cashloan.cl.domain.BlacklistTask;
import com.xiji.cashloan.cl.mapper.BlacklistTaskMapper;
import com.xiji.cashloan.cl.service.BlacklistTaskService;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * 黑名单任务ServiceImpl
 *
 * @author king
 * @version 1.0.0
 * @date 2018-12-20 15:44:09
 */

@Service("blacklistTaskService")
public class BlacklistTaskServiceImpl extends BaseServiceImpl<BlacklistTask, Long> implements BlacklistTaskService {

	private static final Logger logger = LoggerFactory.getLogger(BlacklistTaskServiceImpl.class);

	@Resource
	private BlacklistTaskMapper blacklistTaskMapper;

	@Override
	public BaseMapper<BlacklistTask, Long> getMapper() {
		return blacklistTaskMapper;
	}
	@Override
	public List<BlacklistTask> listSelective(Map<String, Object> paramMap) {
		return blacklistTaskMapper.listSelective(paramMap);
	}
}
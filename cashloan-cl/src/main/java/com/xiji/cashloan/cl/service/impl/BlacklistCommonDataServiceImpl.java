package com.xiji.cashloan.cl.service.impl;

import com.xiji.cashloan.cl.domain.BlacklistCommonData;
import com.xiji.cashloan.cl.mapper.BlacklistCommonDataMapper;
import com.xiji.cashloan.cl.service.BlacklistCommonDataService;
import com.xiji.cashloan.cl.util.black.CollectionUtil;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.core.common.util.ShardTableUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * 黑名单通用处理模型实体ServiceImpl
 * 
 * @author king
 * @version 1.0.0
 * @date 2018-12-20 14:29:13
 */
 
@Service("blacklistCommonDataService")
public class BlacklistCommonDataServiceImpl extends BaseServiceImpl<BlacklistCommonData, Long> implements BlacklistCommonDataService {
	private static final int shardId = 3000000;
	private static final String shard_tableName = "cl_blacklist_common_data";
    private static final Logger logger = LoggerFactory.getLogger(BlacklistCommonDataServiceImpl.class);
   
    @Resource
    private BlacklistCommonDataMapper blacklistCommonDataMapper;

	@Override
	public BaseMapper<BlacklistCommonData, Long> getMapper() {
		return blacklistCommonDataMapper;
	}

	private String getShardTableName(Long borrowId) {
		return ShardTableUtil.generateTableNameById(shard_tableName, borrowId, shardId);
	}
	@Override
	public BlacklistCommonData findByBorrowId(Long borrowId) {
		String tableName = getShardTableName(borrowId) ;
		Map<String,Object> map = new HashMap<>();
		map.put("borrowId", borrowId);
		List<BlacklistCommonData> result = blacklistCommonDataMapper.listShardSelective(tableName, map);
		if (CollectionUtil.isNotEmpty(result)) {
			return result.get(0);
		}
		return null;
	}

	@Override
	public int saveShard(BlacklistCommonData data) {
		if (data == null || data.getBorrowId() == null) {
			return 0;
		}
		// 分表
		String tableName = getShardTableName(data.getBorrowId()) ;
		int countTable = blacklistCommonDataMapper.countTable(tableName);
		if (countTable == 0) {
			blacklistCommonDataMapper.createTable(tableName);
		}
		blacklistCommonDataMapper.saveShard(tableName, data);
		return 0;
	}
}
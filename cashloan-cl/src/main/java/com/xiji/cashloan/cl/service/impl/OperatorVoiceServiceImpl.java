package com.xiji.cashloan.cl.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiji.cashloan.cl.domain.OperatorVoice;
import com.xiji.cashloan.cl.domain.UserContacts;
import com.xiji.cashloan.cl.mapper.OperatorVoiceMapper;
import com.xiji.cashloan.cl.mapper.UserContactsMapper;
import com.xiji.cashloan.cl.service.OperatorVoiceService;
import com.xiji.cashloan.cl.util.black.CollectionUtil;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.core.common.util.ShardTableUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
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

	@Resource
    private UserContactsMapper userContactsMapper;

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


		// 分表
		String userContacts = ShardTableUtil.generateTableNameById("cl_user_contacts", userId, 30000);
		int countUserContacts = userContactsMapper.countTable(userContacts);
		if (countUserContacts == 0) {
			userContactsMapper.createTable(userContacts);
		}
		Map<String,Object> map = new HashMap<>();
		map.put("userId", userId);
		List<UserContacts> userContactsList = userContactsMapper.listShardSelective(userContacts, map);

		if (CollectionUtil.isNotEmpty(list) && CollectionUtil.isNotEmpty(userContactsList)){
			for(OperatorVoice operatorVoice :list){
				for (UserContacts userContacts1:userContactsList){
					if (operatorVoice.getPeerNumber() != null && operatorVoice.getPeerNumber().equals(userContacts1.getPhone())){
						operatorVoice.setPeerName(userContacts1.getName());
					}
				}
			}
		}

		return (Page<OperatorVoice>) list;
	}
}
package com.xiji.cashloan.cl.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xiji.cashloan.core.common.util.ShardTableUtil;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiji.cashloan.cl.domain.UserMessages;
import com.xiji.cashloan.cl.mapper.UserMessagesMapper;
import com.xiji.cashloan.cl.service.UserMessagesService;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;


/**
 * 用户资料--联系人ServiceImpl
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("clUserMessagesService")
public class UserMessagesServiceImpl extends BaseServiceImpl<UserMessages, Long> implements UserMessagesService {
	
    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(UserMessagesServiceImpl.class);
   
    @Resource
    private UserMessagesMapper clUserMessagesMapper;


	@Override
	public BaseMapper<UserMessages, Long> getMapper() {
		return clUserMessagesMapper;
	}

	@Override
	public Page<UserMessages> listMessages(long userId, int current, int pageSize) {
		PageHelper.startPage(current, pageSize);
		Map<String,Object> searchMap = new HashMap<>();
		searchMap.put("userId", userId);
		List<UserMessages> list = clUserMessagesMapper.listSelective(searchMap);
		for (UserMessages clUserMessages : list) {
			if ("10".equals(clUserMessages.getType())) {
				clUserMessages.setType("发送");
			}else {
				clUserMessages.setType("接收");
			}
		}
		return (Page<UserMessages>)list;
	}

	/**
	 * 用户短信(分表)新增
	 * @param userMessages
	 * @return
	 */
	@Override
	public int saveShardUserMsg(UserMessages userMessages){

		String tableName = ShardTableUtil.generateTableNameById("cl_user_messages",userMessages.getUserId(),30000);
		int countTable = clUserMessagesMapper.countTable(tableName);
		if (countTable == 0) {
			clUserMessagesMapper.createTable(tableName);
		}
		return clUserMessagesMapper.saveShard(tableName,userMessages);
	}


}
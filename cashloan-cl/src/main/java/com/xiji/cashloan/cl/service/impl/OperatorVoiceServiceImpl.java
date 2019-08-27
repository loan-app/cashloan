package com.xiji.cashloan.cl.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiji.cashloan.cl.domain.OperatorVoice;
import com.xiji.cashloan.cl.domain.UserContacts;
import com.xiji.cashloan.cl.mapper.OperatorVoiceMapper;
import com.xiji.cashloan.cl.mapper.UserContactsMapper;
import com.xiji.cashloan.cl.service.OperatorVoiceService;
import com.xiji.cashloan.cl.util.black.CollectionUtil;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.core.common.util.ShardTableUtil;
import com.xiji.cashloan.core.common.util.StringUtil;
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
		String operatorSelect = Global.getValue("operator_select");
		if (CollectionUtil.isNotEmpty(list) && CollectionUtil.isNotEmpty(userContactsList)){
			for(OperatorVoice operatorVoice :list){
				for (UserContacts userContacts1:userContactsList){
					if ("yunqiao".equals(operatorSelect)){
						if (StringUtil.isNotBlank(userContacts1.getPhone()) && StringUtil.isNotBlank(operatorVoice.getPeerNumber()) && userContacts1.getPhone().length() > 4 && operatorVoice.getPeerNumber().length()> 4 ){
							String phonePre;
							String peerNumberPre;
							switch (userContacts1.getPhone().length()){
								case 9:phonePre = userContacts1.getPhone().trim().substring(0,1);break;
								case 10:phonePre = userContacts1.getPhone().trim().substring(0,2);break;
								case 11:phonePre = userContacts1.getPhone().trim().substring(0,3);break;
								case 12:phonePre = userContacts1.getPhone().trim().substring(0,4);break;
								default:phonePre = userContacts1.getPhone().trim().substring(0,3);break;
							}
							switch (operatorVoice.getPeerNumber().length()){
								case 9:peerNumberPre = operatorVoice.getPeerNumber().trim().substring(0,1);break;
								case 10:peerNumberPre = operatorVoice.getPeerNumber().trim().substring(0,2);break;
								case 11:peerNumberPre = operatorVoice.getPeerNumber().trim().substring(0,3);break;
								case 12:peerNumberPre = operatorVoice.getPeerNumber().trim().substring(0,4);break;
								default:peerNumberPre = operatorVoice.getPeerNumber().trim().substring(0,3);break;
							}

							String phoneSuffix = userContacts1.getPhone().substring(userContacts1.getPhone().length()-4,userContacts1.getPhone().length());
							String peerNumberSuffix = operatorVoice.getPeerNumber().substring(operatorVoice.getPeerNumber().length()-4,operatorVoice.getPeerNumber().length());

							if (phonePre.equals(peerNumberPre) && phoneSuffix.equals(peerNumberSuffix)){
								operatorVoice.setPeerName(userContacts1.getName());
							}
					    }else {
						    if (userContacts1.getPhone().equals(operatorVoice.getPeerNumber())){
							    operatorVoice.setPeerName(userContacts1.getName());
						    }
					    }
					}
				}
			}
		}

		return (Page<OperatorVoice>) list;
	}
}
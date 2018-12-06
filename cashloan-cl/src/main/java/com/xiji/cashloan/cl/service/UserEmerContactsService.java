package com.xiji.cashloan.cl.service;

import java.util.List;
import java.util.Map;

import com.xiji.cashloan.cl.domain.UserEmerContacts;
import com.xiji.cashloan.core.common.service.BaseService;


/**
 * 紧急联系人表Service
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface UserEmerContactsService extends BaseService<UserEmerContacts, Long>{

	public List<UserEmerContacts> getUserEmerContactsByUserId(Map<String,Object> paramMap);
}

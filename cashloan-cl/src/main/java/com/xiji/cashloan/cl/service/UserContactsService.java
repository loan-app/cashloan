package com.xiji.cashloan.cl.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.UserContacts;
import com.xiji.cashloan.core.common.service.BaseService;

/**
 * 用户资料--联系人Service
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface UserContactsService extends BaseService<UserContacts, Long>{

	/**
	 * 查询通讯录
	 * @param userId
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<UserContacts> listContacts(long userId, int current, int pageSize,String name,String phone);

	/**
	 * 保存前删除原有记录
	 * @param userId 
	 * @param userId
	 * @param clUserContacts 
	 * @return
	 */
	boolean deleteAndSave(List<Map<String, Object>> infos, long userId);

}

package com.xiji.cashloan.cl.service;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.UserMessages;
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
public interface UserMessagesService extends BaseService<UserMessages, Long>{

	/**
	 * 短信查询
	 * @param userId
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<UserMessages> listMessages(long userId, int current, int pageSize);

}

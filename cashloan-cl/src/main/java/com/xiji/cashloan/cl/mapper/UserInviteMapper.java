package com.xiji.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.xiji.cashloan.cl.domain.UserInvite;
import com.xiji.cashloan.cl.model.InviteBorrowModel;
import com.xiji.cashloan.cl.model.ManageAgentModel;
import com.xiji.cashloan.cl.model.ManageProfitModel;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;

/**
 * 邀请记录Dao
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/12/03
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface UserInviteMapper extends BaseMapper<UserInvite,Long> {

	/**
	 * 代理商查询
	 * @param map
	 * @return
	 */
	List<ManageProfitModel> findAgent(Map<String, Object> map);

	/**
	 * 管理员代理商查询
	 * @param map
	 * @return
	 */
	List<ManageAgentModel> findSysAgent(Map<String, Object> map);

	/**
	 * 统计邀请的二级代理数量
	 * @param userId
	 * @return
	 */
	long count(Long userId);

	/**
	 * 查询邀请明细
	 * @param map
	 * @return
	 */
	List<InviteBorrowModel> listInviteBorrow(Map<String, Object> map);

}

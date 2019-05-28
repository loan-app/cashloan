package com.xiji.cashloan.rc.service;

import com.xiji.cashloan.core.common.service.BaseService;
import com.xiji.cashloan.rc.domain.SceneBusinessLog;

/**
 * 场景与第三方征信接口执行记录
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 *
 *
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface SceneBusinessLogService extends BaseService<SceneBusinessLog, Long> {

	
	/**
	 * 是否有未执行完的接口
	 * @return
	 */
	boolean haveNeedExcuteService(Long borrowId);
	
	/**
	 * 是否需要执行
	 * @param userId
	 * @param info
	 * @return true  有未完成的接口，false 没有未完成的接口
	 * @throws Exception
	 */
	boolean needExcute(Long userId, Long busId, String getWay, String period);

	/**
	 * 根据nid查询是否有未执行完的接口
	 * @param borrowId
	 * @param nid
     * @return
     */
	boolean haveNeedExcuteByNid(Long borrowId, String nid);

	/**
	 * 修改未执行完成的接口状态(用于临时解决订单卡住待机审)
	 */
	int updateBusinessLogByBorrowId(Long borrowId);
}

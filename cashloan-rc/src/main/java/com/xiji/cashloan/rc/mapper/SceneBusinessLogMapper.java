package com.xiji.cashloan.rc.mapper;


import java.util.List;

import com.xiji.cashloan.rc.domain.SceneBusinessLog;
import org.apache.ibatis.annotations.Param;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;

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
@RDBatisDao
public interface SceneBusinessLogMapper extends BaseMapper<SceneBusinessLog,Long> {

	/**
	 * 查询未完成的（失败的/需要重新执行的）记录数
	 * @param borrowId
	 * @return
	 */
	int countUnFinishLog(@Param("borrowId") Long borrowId);
	
	
	/**
	 * 查询未完成的（失败的/需要重新执行的）记录
	 * @param borrowId
	 * @return
	 */
	List<SceneBusinessLog> findSceneLogByBorrowId(@Param("borrowId") Long borrowId);
	
	/**
	 * 
	 * @param userId
	 * @param busId
	 * @return
	 */
	SceneBusinessLog findLastExcute(@Param("userId") Long userId, @Param("busId") Long busId);

	/**
	 * 根据Nid查询未完成的（失败的/需要重新执行的）记录数
	 * @param borrowId
	 * @param nid
     * @return
     */
	int haveNeedExcuteByNid(@Param("borrowId") Long borrowId, @Param("nid") String nid);

	/**
	 * 修改未执行完成的接口状态(用于临时解决订单卡住待机审)
	 */
	int updateBusinessLogByBorrowId(Long borrowId);
}

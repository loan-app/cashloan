package com.xiji.cashloan.rc.service;

import java.util.Map;

import com.xiji.cashloan.core.common.service.BaseService;
import com.xiji.cashloan.rc.domain.TppReqLog;

/**
 * 第三方征信请求记录Service
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 *
 *
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface TppReqLogService extends BaseService<TppReqLog, Long> {

	/**
	 * 根据订单号修改记录
	 * @param params
	 * @return
	 */
	int modifyTppReqLog(TppReqLog log);
	
	/**
	 * 根据参数查询
	 * @param params
	 * @return
	 */
	TppReqLog findSelective(Map<String, Object> params);
	
}

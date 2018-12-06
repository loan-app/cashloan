package com.xiji.cashloan.cl.service;

import com.xiji.cashloan.cl.domain.DhbReqLog;
import com.xiji.cashloan.core.common.service.BaseService;
import com.xiji.cashloan.core.domain.Borrow;
import com.xiji.cashloan.rc.domain.TppBusiness;

/**
 * 贷后邦反欺诈请求记录表Service
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface DhbReqLogService extends BaseService<DhbReqLog, Long>{
	/**
	 * 贷后邦反欺诈请求
	 * @param userId
	 * @param business
	 * @return
	 */
	int queryDhbSauron(Borrow borrow, TppBusiness business);

}

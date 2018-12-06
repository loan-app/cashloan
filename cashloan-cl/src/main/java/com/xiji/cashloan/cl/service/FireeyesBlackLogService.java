package com.xiji.cashloan.cl.service;

import com.xiji.cashloan.cl.domain.FireeyesBlackLog;
import com.xiji.cashloan.core.common.service.BaseService;
import com.xiji.cashloan.core.domain.Borrow;
import com.xiji.cashloan.rc.domain.TppBusiness;

/**
 * 火眼黑名单Service
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface FireeyesBlackLogService extends BaseService<FireeyesBlackLog, Long>{
	
	int queryFireeyesBlack(Borrow borrow, TppBusiness business);

}

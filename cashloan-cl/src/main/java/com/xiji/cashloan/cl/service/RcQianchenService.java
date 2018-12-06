package com.xiji.cashloan.cl.service;

import com.xiji.cashloan.core.common.exception.BussinessException;
import com.xiji.cashloan.core.domain.Borrow;
import com.xiji.cashloan.rc.domain.TppBusiness;

/**
 * 现金白卡风控接口
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface RcQianchenService {

	/**
	 * 调用浅橙风控接口
	 * @param userId 用户ID
	 * @param picPath 图片服务地址
	 * @throws BussinessException
	 */
	String qianchenRiskRequest(Long userId,Borrow borrow,String operatorNo,String reqOrderNo,TppBusiness tpp) throws BussinessException;
}

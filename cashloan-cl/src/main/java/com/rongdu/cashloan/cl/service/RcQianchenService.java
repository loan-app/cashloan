package com.rongdu.cashloan.cl.service;

import com.rongdu.cashloan.core.common.exception.BussinessException;
import com.rongdu.cashloan.core.domain.Borrow;
import com.rongdu.cashloan.rc.domain.TppBusiness;

/**
 * 现金白卡风控接口
 * @author caitt
 * @version 1.0
 * @date 2017年3月14日下午2:03:00
 * Copyright 杭州融都科技股份有限公司 现金贷  All Rights Reserved
 * 官方网站：www.erongdu.com
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

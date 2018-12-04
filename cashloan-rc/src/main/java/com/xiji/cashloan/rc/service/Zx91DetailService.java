package com.xiji.cashloan.rc.service;

import com.xiji.cashloan.core.common.service.BaseService;
import com.xiji.cashloan.rc.domain.TppBusiness;
import com.xiji.cashloan.rc.domain.Zx91Detail;

/**
 * 风控数据统计-91征信
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.xiji.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface Zx91DetailService extends BaseService<Zx91Detail, Long> {

	int query91zx1003(String idNo, String realName, Long userId, TppBusiness business);
}

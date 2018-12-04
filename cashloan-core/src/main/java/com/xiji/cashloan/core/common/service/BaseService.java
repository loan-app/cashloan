package com.xiji.cashloan.core.common.service;

import java.io.Serializable;

/**
 * 基类接口定义
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 * Copyright 杭州融都科技股份有限公司  All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface BaseService<T, ID extends Serializable> {

	int insert(T record);


	int updateById(T record);
	
	
	T getById(ID id);
	

}

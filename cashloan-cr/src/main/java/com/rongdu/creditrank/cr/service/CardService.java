package com.rongdu.creditrank.cr.service;

import java.rmi.ServerException;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.rongdu.cashloan.core.common.exception.CreditException;
import com.rongdu.cashloan.core.common.service.BaseService;
import com.rongdu.creditrank.cr.domain.Card;

/**
 * 评分卡Service
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-01-04 15:06:51
 * Copyright 杭州融都科技股份有限公司  creditrank All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface CardService extends BaseService<Card, Long>{

	/**
	 * 分页查询
	 * @param secreditrankhMap
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<Card> page(Map<String,Object> secreditrankhMap,int current,int pageSize);
	
	/**
	 * 查询所有评分卡
	 * @return
	 */
	List<Card> findAll();
	
	/**
	 * 保存数据
	 * @param cardName
	 * @return
	 * @throws CreditException 
	 */
	Map<String, Object> save(String cardName) throws CreditException;
	
	/**
	 * 主键查询
	 * @param cardId
	 * @return
	 */
	Card findByPrimary(long cardId);

	/**
	 * 修改状态
	 * @param map
	 * @return
	 * @throws ServerException 
	 * @throws CreditException 
	 */
	Map<String, Object> updateState(long id,String state) throws ServerException, CreditException;
	
	/**
	 * 修改
	 * @param cardMap
	 */
	void updateSelective(Map<String, Object> cardMap);
	
	/**
	 * 修改评分卡名称
	 * @param cardMap
	 */
	Map<String, Object> update(long id,String cardName);
}

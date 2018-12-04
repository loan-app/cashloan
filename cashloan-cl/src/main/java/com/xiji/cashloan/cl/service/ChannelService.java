package com.xiji.cashloan.cl.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.Channel;
import com.xiji.cashloan.cl.model.ChannelCountModel;
import com.xiji.cashloan.cl.model.ChannelModel;
import com.xiji.cashloan.core.common.service.BaseService;

/**
 * 渠道信息Service
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface ChannelService extends BaseService<Channel, Long>{

	/**
	 * 保存渠道信息
	 * 
	 * @param channel
	 * @return
	 */
	boolean save(Channel channel);
	
	/**
	 * 更新渠道信息
	 * 
	 * @param paramMap
	 * @return
	 */
	boolean update(Map<String, Object> paramMap);
	
	/**
	 * 根据code查询主键
	 * @param code
	 * @return
	 */
	long findIdByCode(String code);
	
	/**
	 * 根据code查询对象
	 * @param code
	 * @return
	 */
	Channel findByCode(String code);

	/**
	 * 列表查询渠道信息
	 * 
	 * @param current
	 * @param pageSize
	 * @param searchMap
	 * @return
	 */
	Page<ChannelModel> page(int current, int pageSize,
							Map<String, Object> searchMap);
	/**
	 * 渠道用户统计
	 * 
	 * @param current
	 * @param pageSize
	 * @param searchMap
	 * @return
	 */
	Page<ChannelCountModel> channelUserList(int current, int pageSize,
			Map<String, Object> searchMap);

	/**
	 * 查出所有渠道信息
	 */
	List<Channel> listChannel();
	
	/**
	 * 查询没有版本信息的渠道id和名称
	 */
	List<Channel> listChannelWithoutApp();
	
	/**
	 * 2017年7月19日 18:51:13
	 * chenxy
	 * 渠道用户统计
	 */
	List<Map<String,Object>> channelUserCount(Map<String, Object> paramMap,int current, int pageSize);

	/**
	 * 渠道转化
	 * @param searchMap
	 * @return
	 */
	List<Map<String, Object>> conversion(Map<String, Object> searchMap) throws Exception;
}

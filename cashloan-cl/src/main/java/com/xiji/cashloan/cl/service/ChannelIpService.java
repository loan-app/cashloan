package com.xiji.cashloan.cl.service;


import com.xiji.cashloan.cl.domain.ChannelIp;

import com.xiji.cashloan.core.common.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * 渠道明细表Service
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 *
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface ChannelIpService extends BaseService<ChannelIp, Long>{


	/**
	 * 查询uv信息
	 */
	public List<ChannelIp> listChannelIp();

	/**
	 * 主键查询信息
	 */
	public ChannelIp findByPrimary(long id);

	/**
	 * 保存信息
	 */
	public int save(ChannelIp channelIp);

	/**
	 * 更新信息
	 */
	public int updateSelective(Map<String, Object> paramMap);

	/**
     * 根据渠道id查询信息
	 * @param id
     * @return
     */
	ChannelIp findByChannelId(Long id);

    /**
     * 根据条件查询信息
	 * @param paramsMap
     * @return
     */
	ChannelIp findSelective(Map<String, Object> paramsMap);


	/**
	 * 删除渠道ip统计
	 * @return
	 */
	int deleteByCreateDate(Map<String, Object> paramsMap);
}

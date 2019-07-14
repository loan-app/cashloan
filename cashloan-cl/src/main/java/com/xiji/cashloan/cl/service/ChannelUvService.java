package com.xiji.cashloan.cl.service;


import com.xiji.cashloan.cl.domain.ChannelUv;

import com.xiji.cashloan.core.common.service.BaseService;


import java.util.List;
import java.util.Map;

/**
 * 渠道uv表Service
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 *
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface ChannelUvService extends BaseService<ChannelUv, Long>{


	/**
	 * 查询uv信息
	 */
	public List<ChannelUv> listChannelUv();

	/**
	 * 主键查询信息
	 */
	public ChannelUv findByPrimary(long id);

	/**
	 * 保存信息
	 */
	public int save(ChannelUv ChannelUv);

	/**
	 * 更新信息
	 */
	public int updateSelective(Map<String, Object> paramMap);

	/**
	 * 根据渠道id查询信息
	 * @param id
	 * @return
	 */
	ChannelUv findByChannelId(Long id);

	/**
	 * 根据条件查询信息
	 * @param paramsMap
	 * @return
	 */
	ChannelUv findSelective(Map<String, Object> paramsMap);
}

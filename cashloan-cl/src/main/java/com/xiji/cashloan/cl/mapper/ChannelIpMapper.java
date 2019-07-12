package com.xiji.cashloan.cl.mapper;


import com.xiji.cashloan.cl.domain.ChannelIp;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;

import java.util.List;
import java.util.Map;


/**
 * 渠道ip记录表Dao
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/12/03
 *
 *
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface ChannelIpMapper extends BaseMapper<ChannelIp,Long> {


	/**
	 * 查询app更新信息
	 */
	public List<ChannelIp> listSelective();
	
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
	public int updateSelective(ChannelIp channelIp);

	/**
     * 根据渠道id查询信息
	 * @param id
     * @return
     */
	ChannelIp findByChannelId(Long id);

	/**
	 *删除渠道ip
	 * @param params
	 * @return
	 */
	int deleteByCreateDate(Map<String, Object> params);
}

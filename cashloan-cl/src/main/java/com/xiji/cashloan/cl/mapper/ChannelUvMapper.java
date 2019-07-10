package com.xiji.cashloan.cl.mapper;


import com.xiji.cashloan.cl.domain.ChannelUv;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;

import java.util.List;


/**
 * app渠道版本表Dao
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
public interface ChannelUvMapper extends BaseMapper<ChannelUv,Long> {


	/**
	 * 查询app更新信息
	 */
	public List<ChannelUv> listSelective();
	
	/**
	 * 主键查询信息
	 */
	public ChannelUv findByPrimary(long id);
	
	/**
	 * 保存信息
	 */
	public int save(ChannelUv channelUv);
	
	/**
	 * 更新信息
	 */
	public int updateSelective(ChannelUv channelUv);

	/**
	 * 根据渠道id查询信息
	 * @param id
	 * @return
	 */
	ChannelUv findByChannelId(Long id);
}

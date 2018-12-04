package com.xiji.cashloan.cl.mapper;

import java.util.List;

import com.xiji.cashloan.cl.domain.ChannelApp;
import com.xiji.cashloan.cl.model.ChannelAppModel;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;


/**
 * app渠道版本表Dao
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/12/03
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 创新一部：rdc@xiji.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface ChannelAppMapper extends BaseMapper<ChannelApp,Long> {

	/**
	 * 查询app渠道更新版本信息
	 * @return
	 */
	public List<ChannelAppModel> listChannelAppModel();

	/**
	 * 查询app更新信息
	 */
	public List<ChannelApp> listSelective();
	
	/**
	 * 主键查询信息
	 */
	public ChannelApp findByPrimary(long id);
	
	/**
	 * 保存信息
	 */
	public int save(ChannelApp channelApp);
	
	/**
	 * 更新信息
	 */
	public int updateSelective(ChannelApp channelApp);
}

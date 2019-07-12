package com.xiji.cashloan.cl.service.impl;


import com.xiji.cashloan.cl.domain.ChannelIp;
import com.xiji.cashloan.cl.mapper.ChannelIpMapper;
import com.xiji.cashloan.cl.service.ChannelIpService;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * 渠道IP记录表ServiceImpl
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 *
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Service("channelIpService")
public class ChannelIpServiceImpl extends BaseServiceImpl<ChannelIp, Long> implements ChannelIpService {

	
    @Resource
    private ChannelIpMapper channelIpMapper;

	@Override
	public BaseMapper<ChannelIp, Long> getMapper() {
		return null;
	}

    

	@Override
	public List<ChannelIp> listChannelIp() {
		return channelIpMapper.listSelective();
	}

	@Override
	public ChannelIp findByPrimary(long id) {
		return channelIpMapper.findByPrimary(id);
	}

	@Override
	public int save(ChannelIp channelIp) {
		return channelIpMapper.save(channelIp);
	}

	@Override
	public int updateSelective(Map<String, Object> paramMap) {
		return channelIpMapper.updateSelective(paramMap);
	}

	@Override
	public ChannelIp findByChannelId(Long id) {
		return channelIpMapper.findByChannelId(id);
	}

	@Override
	public ChannelIp findSelective(Map<String, Object> paramsMap) {
		return channelIpMapper.findSelective(paramsMap);
	}

	@Override
	public int deleteByCreateDate(Map<String,Object> params) {
		return channelIpMapper.deleteByCreateDate(params);
	}


}
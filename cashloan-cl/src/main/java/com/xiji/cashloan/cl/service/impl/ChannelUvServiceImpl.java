package com.xiji.cashloan.cl.service.impl;


import com.xiji.cashloan.cl.domain.ChannelUv;
import com.xiji.cashloan.cl.mapper.ChannelUvMapper;
import com.xiji.cashloan.cl.service.ChannelUvService;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * 渠道uv表ServiceImpl
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 *
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Service("channelUvService")
public class ChannelUvServiceImpl extends BaseServiceImpl<ChannelUv, Long> implements ChannelUvService {
	
    @Resource
    private ChannelUvMapper channelUvMapper;

	@Override
	public BaseMapper<ChannelUv, Long> getMapper() {
		return null;
	}

    

	@Override
	public List<ChannelUv> listChannelUv() {
		return channelUvMapper.listSelective();
	}

	@Override
	public ChannelUv findByPrimary(long id) {
		return channelUvMapper.findByPrimary(id);
	}

	@Override
	public int save(ChannelUv channelUv) {
		return channelUvMapper.save(channelUv);
	}

	@Override
	public int updateSelective(Map<String, Object> paramMap) {
		return channelUvMapper.updateSelective(paramMap);
	}

	@Override
	public ChannelUv findByChannelId(Long id) {
		return channelUvMapper.findByChannelId(id);
	}

	@Override
	public ChannelUv findSelective(Map<String, Object> paramsMap) {
		return channelUvMapper.findSelective(paramsMap);
	}


}
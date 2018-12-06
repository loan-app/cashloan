package com.xiji.cashloan.rc.service.impl;

import javax.annotation.Resource;

import com.xiji.cashloan.rc.domain.Simplezx91Count;
import com.xiji.cashloan.rc.mapper.Simplezx91CountMapper;
import com.xiji.cashloan.rc.service.Simplezx91CountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;


/**
 * 风控数据统计-（简）通话记录统计ServiceImpl
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 *
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("simplezx91CountService")
public class Simplezx91CountServiceImpl extends BaseServiceImpl<Simplezx91Count, Long> implements Simplezx91CountService {
	
    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(Simplezx91CountServiceImpl.class);
   
    @Resource
    private Simplezx91CountMapper simplezx91CountMapper;

	@Override
	public BaseMapper<Simplezx91Count, Long> getMapper() {
		return simplezx91CountMapper;
	}
	
}
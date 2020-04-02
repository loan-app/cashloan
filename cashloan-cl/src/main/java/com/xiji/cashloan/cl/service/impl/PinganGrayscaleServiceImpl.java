package com.xiji.cashloan.cl.service.impl;

import com.xiji.cashloan.cl.domain.PinganGrayscale;
import com.xiji.cashloan.cl.mapper.PinganGrayscaleMapper;
import com.xiji.cashloan.cl.service.PinganGrayscaleService;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 凭安染黑度统计ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-26 21:28:46
 */
 
@Service("pinganGrayscaleService")
public class PinganGrayscaleServiceImpl extends BaseServiceImpl<PinganGrayscale, Long> implements PinganGrayscaleService {
	
    private static final Logger logger = LoggerFactory.getLogger(PinganGrayscaleServiceImpl.class);
   
    @Resource
    private PinganGrayscaleMapper pinganGrayscaleMapper;

	@Override
	public BaseMapper<PinganGrayscale, Long> getMapper() {
		return pinganGrayscaleMapper;
	}

	/**
	 * 查询最近一次凭安报告
	 * @return
	 */
	@Override
	public PinganGrayscale getPinganGrayscale(Long userId){
		return pinganGrayscaleMapper.getPinganGrayscale(userId);
	}
}
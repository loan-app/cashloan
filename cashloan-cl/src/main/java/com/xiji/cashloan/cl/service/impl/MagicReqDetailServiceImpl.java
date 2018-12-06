package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiji.cashloan.cl.model.CreditLoanUserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.mapper.MagicReqDetailMapper;
import com.xiji.cashloan.cl.domain.MagicReqDetail;
import com.xiji.cashloan.cl.service.MagicReqDetailService;

import java.util.List;
import java.util.Map;


/**
 * 魔杖2.0-请求详情ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-05 17:23:11
 */
 
@Service("magicReqDetailService")
public class MagicReqDetailServiceImpl extends BaseServiceImpl<MagicReqDetail, Long> implements MagicReqDetailService {
	
    private static final Logger logger = LoggerFactory.getLogger(MagicReqDetailServiceImpl.class);
   
    @Resource
    private MagicReqDetailMapper magicReqDetailMapper;

	@Override
	public BaseMapper<MagicReqDetail, Long> getMapper() {
		return magicReqDetailMapper;
	}

	@Override
	public Page<CreditLoanUserModel> listUser(Map<String, Object> params, int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<CreditLoanUserModel> list = magicReqDetailMapper.listModel(params);
		return (Page<CreditLoanUserModel>) list;
	}

	@Override
	public MagicReqDetail getLastRecord(Long userId, int type) {
		return magicReqDetailMapper.getLastRecord(userId, type);
	}
}
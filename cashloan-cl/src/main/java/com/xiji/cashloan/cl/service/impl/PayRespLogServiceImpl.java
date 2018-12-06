package com.xiji.cashloan.cl.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import com.xiji.cashloan.cl.mapper.PayRespLogMapper;
import com.xiji.cashloan.cl.model.ManagePayRespLogModel;
import org.springframework.stereotype.Service;

import tool.util.DateUtil;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiji.cashloan.cl.domain.PayRespLog;
import com.xiji.cashloan.cl.service.PayRespLogService;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;


/**
 * 支付响应记录ServiceImpl
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("payRespLogService")
public class PayRespLogServiceImpl extends BaseServiceImpl<PayRespLog, Long> implements PayRespLogService {
	
    @Resource
    private PayRespLogMapper payRespLogMapper;

    
	@Override
	public boolean save(PayRespLog log) {
		log.setCreateTime(DateUtil.getNow());
		int result = payRespLogMapper.save(log);
		if (result > 0L) {
			return true;
		}
		return false;
	}

	
	@Override
	public Page<ManagePayRespLogModel> page(int current, int pageSize,
											Map<String, Object> searchMap) {
		PageHelper.startPage(current,pageSize);
		Page<ManagePayRespLogModel>  page = (Page<ManagePayRespLogModel>) payRespLogMapper.page(searchMap);
		return page;
	}

	@Override
	public ManagePayRespLogModel findDetail(Long id) {
		return payRespLogMapper.findDetail(id);
	}
	
	@Override
	public BaseMapper<PayRespLog, Long> getMapper() {
		return payRespLogMapper;
	}
}
package com.xiji.cashloan.cl.service.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xiji.cashloan.cl.mapper.SystemRcMapper;
import com.xiji.cashloan.cl.model.DayPassApr;
import com.xiji.cashloan.cl.model.SystemDayData;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiji.cashloan.cl.service.SystemRcService;


/**
 * 平台数据日报
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Service("systemRcService")
public class SystemRcServiceImpl implements SystemRcService {
	
	@Resource
	private SystemRcMapper systemRcMapper;

	@Override
	public Page<SystemDayData> findDayData(Map<String,Object> params, Integer current, Integer pageSize) {
		PageHelper.startPage(current, pageSize);
		Page<SystemDayData> page = (Page<SystemDayData>) systemRcMapper.dayData(params);
		return page;
	}

	@Override
	public Page<DayPassApr> findDayApr(Map<String,Object> params, Integer current, Integer pageSize) {
		PageHelper.startPage(current, pageSize);
		List<DayPassApr> list = (Page<DayPassApr>) systemRcMapper.dayApr(params);
		/*NumberFormat nf = NumberFormat.getInstance();
		for (DayPassApr dayPassApr : list) {
			dayPassApr.setBorrowPassApr(nf.format(Double.parseDouble(dayPassApr.getBorrowPassApr())));
			dayPassApr.setBorrowApr(nf.format(Double.parseDouble(dayPassApr.getBorrowApr())));
		}*/
		return (Page<DayPassApr>)list;
	}

}

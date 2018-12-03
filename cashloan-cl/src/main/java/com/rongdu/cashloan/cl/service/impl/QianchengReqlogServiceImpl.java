package com.rongdu.cashloan.cl.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rongdu.cashloan.cl.domain.QianchengReqlog;
import com.rongdu.cashloan.cl.mapper.QianchengReqlogMapper;
import com.rongdu.cashloan.cl.model.QianchengReqlogModel;
import com.rongdu.cashloan.cl.service.QianchengReqlogService;
import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.service.impl.BaseServiceImpl;


/**
 * 浅橙借款申请审核ServiceImpl
 * 
 * @author caitt
 * @version 1.0.0
 * @date 2017-03-15 11:46:54
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("qianchengReqlogService")
public class QianchengReqlogServiceImpl extends BaseServiceImpl<QianchengReqlog, Long> implements QianchengReqlogService {
	
    @Resource
    private QianchengReqlogMapper qianchengReqlogMapper;

	@Override
	public BaseMapper<QianchengReqlog, Long> getMapper() {
		return qianchengReqlogMapper;
	}


	@Override
	public Page<QianchengReqlogModel> listQianchengReqlog(Map<String, Object> params, 
			int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<QianchengReqlogModel> list = qianchengReqlogMapper.listQianchengReqlog(params);
		if(list != null && list.size() > 0){
			for(int i=0;i<list.size();i++){
				QianchengReqlogModel reqLog = new QianchengReqlogModel();
				reqLog.setState(list.get(i).getState());
				list.get(i).setStateStr(reqLog.getStateStr());
			}
		}
		return (Page<QianchengReqlogModel>) list;
	}


	@Override
	public QianchengReqlog findByOrderNo(String orderNo) {
		return qianchengReqlogMapper.findByOrderNo(orderNo);
	}


	@Override
	public int update(QianchengReqlog reqLog) {
		return qianchengReqlogMapper.update(reqLog);
	}


	@Override
	public QianchengReqlog findByBorrowId(Long borrowId) {
		return qianchengReqlogMapper.findByBorrowId(borrowId);
	}

	
	
}
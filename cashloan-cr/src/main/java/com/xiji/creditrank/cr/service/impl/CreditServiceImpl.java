package com.xiji.creditrank.cr.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xiji.creditrank.cr.domain.CreditLog;
import com.xiji.creditrank.cr.mapper.CreditLogMapper;
import com.xiji.creditrank.cr.mapper.CreditMapper;
import com.xiji.creditrank.cr.model.CreditModel;
import com.xiji.creditrank.cr.service.CreditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.system.domain.SysUser;
import com.xiji.creditrank.cr.domain.Credit;


/**
 * 授信额度管理ServiceImpl
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2016-12-15 15:47:24
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("creditService")
public class CreditServiceImpl extends BaseServiceImpl<Credit, Long> implements CreditService {
	
    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(CreditServiceImpl.class);
   
    @Resource
    private CreditMapper creditMapper;
    @Resource
    private CreditLogMapper creditLogMapper;


	@Override
	public BaseMapper<Credit, Long> getMapper() {
		return creditMapper;
	}

	@Override
	public Page<CreditModel> page(Map<String, Object> searchMap,
								  int current, int pageSize) {
		PageHelper.startPage(current, pageSize);
		List<CreditModel> list = creditMapper.page(searchMap);
		return (Page<CreditModel>)list;
	}

	@Override
	public Page<CreditModel> listAll(Map<String, Object> searchMap,
			int current, int pageSize) {
		PageHelper.startPage(current, pageSize);
		List<CreditModel> list = creditMapper.listAll(searchMap);
		return (Page<CreditModel>)list;
	}
	
	@Override
	public Credit findSelective(Map<String, Object> searchMap) {
		return creditMapper.findSelective(searchMap);
	}

	@Override
	public Credit findByPrimary(long id) {
		return creditMapper.findByPrimary(id);
	}

	@Override
	public int updateSelective(Map<String, Object> param) {
		return creditMapper.updateSelective(param);
	}

	@Override
	public List<Credit> findByConsumerNo(String consumerNo) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("consumerNo", consumerNo);
		List<Credit> list = creditMapper.listSelective(result);
		return list;
	}

	@Override
	public int updateSelective(long id, double unuse,SysUser sysUser,String remark){
		int msg ;
		Credit credit = creditMapper.findByPrimary(id);
		Map<String,Object> param = new HashMap<String,Object>();
		double total = credit.getTotal(); //变动前额度
		double result = unuse-credit.getUnuse();//变动额度
		double later = credit.getTotal()+result;//变动后额度
		param.put("id", credit.getId());
		param.put("total", later);
		param.put("unuse", unuse);
		msg = creditMapper.updateSelective(param);
		
		if (msg>0) {
			if (unuse>credit.getUnuse()) {
				msg = saveLog(credit, sysUser, total, result, later, remark, "10");
			}else {
				msg = saveLog(credit, sysUser, total, result, later, remark, "20");
			}
			
		}
		return msg;
	}

	@Override
	public int updateState(long id, String state,SysUser sysUser) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("id", id);
		param.put("state", state);
		int msg = creditMapper.updateSelective(param);
		Credit credit = creditMapper.findByPrimary(id);
		if (msg>0) {
			if ("10".equals(state)) {
				msg = saveLog(credit, sysUser, 0.0, credit.getUnuse(), credit.getUnuse(), "解冻", "40");
			}else {
				msg = saveLog(credit, sysUser, credit.getUnuse(),credit.getUnuse(), 0.0, "冻结", "30");
			}
		}
		return msg;
	}

	public int saveLog(Credit credit,SysUser sysUser,
			double total,double result,double later,String remark,String type){//保存数据
		CreditLog log = new CreditLog();
		log.setModifyTime(new Date());
		log.setConsumerNo(credit.getConsumerNo());
		log.setCreditType(credit.getCreditType());
//		log.setModifyUser(sysUser.getUserName());
		log.setModifyUser("system");
		log.setPre(total);
		log.setNow(later);
		log.setModifyTotal(Math.abs(result));
		log.setRemark(remark);
		log.setType(type);
		int msg = creditLogMapper.save(log);
		return msg;
	}
}

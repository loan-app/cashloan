package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.mapper.XinyanXwldMapper;
import com.xiji.cashloan.cl.domain.XinyanXwld;
import com.xiji.cashloan.cl.service.XinyanXwldService;

import java.util.HashMap;
import java.util.Map;


/**
 * 行为雷达ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2019-01-16 20:25:23
 */
 
@Service("xinyanXwldService")
public class XinyanXwldServiceImpl extends BaseServiceImpl<XinyanXwld, Long> implements XinyanXwldService {
	
    private static final Logger logger = LoggerFactory.getLogger(XinyanXwldServiceImpl.class);
   
    @Resource
    private XinyanXwldMapper xinyanXwldMapper;

	@Override
	public BaseMapper<XinyanXwld, Long> getMapper() {
		return xinyanXwldMapper;
	}

	@Override
	public XinyanXwld getByBorrowId(Long borrowId) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("borrowId", borrowId);
		XinyanXwld xinyanXwld = xinyanXwldMapper.findSelective(queryMap);
		return xinyanXwld;
	}
}
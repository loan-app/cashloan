package com.xiji.cashloan.cl.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiji.cashloan.cl.domain.UserRemark;
import com.xiji.cashloan.cl.mapper.UserRemarkMapper;
import com.xiji.cashloan.cl.service.UserRemarkService;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * 用户备注ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2019-01-17 14:29:29
 */
 
@Service("userRemarkService")
public class UserRemarkServiceImpl extends BaseServiceImpl<UserRemark, Long> implements UserRemarkService {
	
    private static final Logger logger = LoggerFactory.getLogger(UserRemarkServiceImpl.class);
   
    @Resource
    private UserRemarkMapper userRemarkMapper;

	@Override
	public BaseMapper<UserRemark, Long> getMapper() {
		return userRemarkMapper;
	}

	/**
	 * 查询备注列表
	 * @param searchParams
	 * @param current
	 * @param pageSize
	 * @return
	 */
	@Override
	public Page<UserRemark> listUserRemark(Map<String, Object> searchParams, int current, int pageSize){
		PageHelper.startPage(current, pageSize);
		List<UserRemark> list = userRemarkMapper.listUserRemark(searchParams);
		return (Page<UserRemark>) list;
	}

	/**
	 * 保存备注信息
	 * @param userRemark
	 */
	@Override
	public int saveUserRemark(UserRemark userRemark){
		int i=userRemarkMapper.save(userRemark);
		return i;
	}
}
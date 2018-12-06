package com.xiji.cashloan.rc.service.impl;

import javax.annotation.Resource;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.core.common.util.ShardTableUtil;
import com.xiji.cashloan.core.service.UserBaseInfoService;
import com.xiji.cashloan.rc.domain.SimpleVoicesCount;
import com.xiji.cashloan.rc.mapper.SimpleVoicesCountMapper;
import com.xiji.cashloan.rc.service.SimpleVoicesCountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import tool.util.DateUtil;


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
 
@Service("simpleVoicesCountService")
public class SimpleVoicesCountServiceImpl extends BaseServiceImpl<SimpleVoicesCount, Long> implements SimpleVoicesCountService {
	
    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(SimpleVoicesCountServiceImpl.class);
   
    @Resource
    private SimpleVoicesCountMapper simpleVoicesCountMapper;
    @Resource
    private UserBaseInfoService userBaseInfoService;

	@Override
	public BaseMapper<SimpleVoicesCount, Long> getMapper() {
		return simpleVoicesCountMapper;
	}
	
	@Override
	public int countOne(long userId) {
		String tableName = ShardTableUtil.generateTableNameById("cl_operator_voice", userId, 30000);
		int count = simpleVoicesCountMapper.countOne(tableName, userId);
		
		/*UserBaseInfo user = userBaseInfoService.findByUserId(userId);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -6);
        //格式化输出年月日
		Double antCount = simpleVoicesCountMapper.countTwo("cl_operator_bills", user.getPhone(),DateUtil.dateStr2(cal.getTime()));
		*/
		
		SimpleVoicesCount simpleVoicesCount = new SimpleVoicesCount();
		simpleVoicesCount.setUserId(userId);
		simpleVoicesCount.setCountOne(count);
		simpleVoicesCount.setCreateTime(DateUtil.getNow());
		// simpleVoicesCount.setCountTwo(antCount);
		return simpleVoicesCountMapper.save(simpleVoicesCount);
	}

	@Override
	public SimpleVoicesCount findByUserId(long userId) {
		return simpleVoicesCountMapper.findByUserId(userId);
	}
	
}
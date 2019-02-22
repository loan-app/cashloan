package com.xiji.cashloan.cl.service.impl.statistic;

import com.xiji.cashloan.cl.domain.statistic.UserStatisticData;
import com.xiji.cashloan.cl.mapper.SystemCountMapper;
import com.xiji.cashloan.cl.mapper.statistic.UserStatisticDataMapper;
import com.xiji.cashloan.cl.service.statistic.UserStatisticDataService;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 用户统计数据ServiceImpl
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2019-02-15 15:41:39
 */
 
@Service("userStatisticDataService")
public class UserStatisticDataServiceImpl extends BaseServiceImpl<UserStatisticData, Long> implements UserStatisticDataService {
	
    private static final Logger logger = LoggerFactory.getLogger(UserStatisticDataServiceImpl.class);
   
    @Resource
    private UserStatisticDataMapper userStatisticDataMapper;

    @Resource
    private SystemCountMapper systemCountMapper;

	@Override
	public BaseMapper<UserStatisticData, Long> getMapper() {
		return userStatisticDataMapper;
	}

	@Override
	public Date getLateDate(){
		return userStatisticDataMapper.getLateDate();
	}

	@Override
	public List<UserStatisticData> listUserStatisticData(Map<String,Object> params){

		List<UserStatisticData> userStatisticDataList = userStatisticDataMapper.listUserStatisticData(params);

		for(UserStatisticData userStatisticData : userStatisticDataList){

			if (userStatisticData.getAuthCount() == null){
				userStatisticData.setAuthCount(0);
			}
			if (userStatisticData.getBankCount() == null){
				userStatisticData.setBankCount(0);
			}
			if (userStatisticData.getBorrowApplyCount() == null){
				userStatisticData.setBorrowApplyCount(0);
			}
			if (userStatisticData.getContactCount() == null){
				userStatisticData.setContactCount(0);
			}
			if (userStatisticData.getLoadCount() == null){
				userStatisticData.setLoadCount(0);
			}
			if (userStatisticData.getNewLoadCount() == null){
				userStatisticData.setNewLoadCount(0);
			}
			if (userStatisticData.getOldLoadCount() == null){
				userStatisticData.setOldLoadCount(0);
			}
			if (userStatisticData.getNewBorrowCount() == null){
				userStatisticData.setNewBorrowCount(0);
			}
			if (userStatisticData.getOldBorrowCount() == null){
				userStatisticData.setOldBorrowCount(0);
			}
			if (userStatisticData.getPhoneCount() == null){
				userStatisticData.setPhoneCount(0);
			}
			if (userStatisticData.getUserRegister() == null){
				userStatisticData.setUserRegister(0);
			}
		}

//		List<UserStatisticData> statisticDataList = new ArrayList<>();
//		for(com.xiji.cashloan.cl.model.statistic.UserStatisticData userStatisticData:userStatisticDataList){
//			UserStatisticData userStatisticData1 = new UserStatisticData();
//			BeanUtil.copyProperties(userStatisticData,userStatisticData1);
//			userStatisticData1.setCountTime(DateUtil.getDate(userStatisticData.getDate()));
//			statisticDataList.add(userStatisticData1);
//		}
		return userStatisticDataList;
	}
}
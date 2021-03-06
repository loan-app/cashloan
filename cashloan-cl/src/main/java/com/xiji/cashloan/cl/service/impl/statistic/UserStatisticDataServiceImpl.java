package com.xiji.cashloan.cl.service.impl.statistic;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiji.cashloan.cl.domain.statistic.UserStatisticData;
import com.xiji.cashloan.cl.mapper.SystemCountMapper;
import com.xiji.cashloan.cl.mapper.statistic.UserStatisticDataMapper;
import com.xiji.cashloan.cl.service.statistic.UserStatisticDataService;
import com.xiji.cashloan.cl.util.black.CollectionUtil;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.core.common.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
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
		return userStatisticDataList;
	}

	/**
	 * 用户数据统计
	 * @return
	 */
	@Override
	public Page<UserStatisticData> listUserStatistic(Map<String,Object> params, Integer current, Integer pageSize){

		PageHelper.startPage(current, pageSize);
		Page<UserStatisticData> userStatisticDataList = (Page<UserStatisticData>) userStatisticDataMapper.listUserStatistic(params);
		for (UserStatisticData statisticData :userStatisticDataList){
			statisticData.setCountTimeStr(DateUtil.dateStr(statisticData.getCountTime(),DateUtil.DATEFORMAT_STR_002));
		}
		return userStatisticDataList;
	}

	/**
	 * 更新用户下款数
	 * @return
	 */
	@Override
	public int updateUserLoanStatistic(Map<String,Object> params){

		List<UserStatisticData> userStatisticDataList = userStatisticDataMapper.listUserStatistic(params);
		int count = 0;
		if (CollectionUtil.isEmpty(userStatisticDataList)){
			return count;
		}
		UserStatisticData loadCount = userStatisticDataMapper.loanCount(params);

		params.put("again","10");
		UserStatisticData newLoadCount = userStatisticDataMapper.loanCount(params);

		params.put("again","20");
		UserStatisticData oldLoadCount = userStatisticDataMapper.loanCount(params);

		UserStatisticData userStatisticData = userStatisticDataList.get(0);

		if (userStatisticData != null){

			String countTimeStr = DateUtil.dateStr(userStatisticData.getCountTime(),DateUtil.DATEFORMAT_STR_002);

			if (countTimeStr != null && countTimeStr.equals(params.get("startDate"))){
				Map<String,Object> userStatisticMap = new HashMap<>();
				userStatisticMap.put("id",userStatisticData.getId());
				if (loadCount != null && loadCount.getLoadCount() != null){
					userStatisticMap.put("loadCount",loadCount.getLoadCount());
				}

				if (newLoadCount != null && newLoadCount.getLoadCount() != null){
					userStatisticMap.put("newLoadCount",newLoadCount.getLoadCount());
				}

				if (oldLoadCount != null && oldLoadCount.getLoadCount() != null){
					userStatisticMap.put("oldLoadCount",oldLoadCount.getLoadCount());
				}
				userStatisticMap.put("updateTime",new Date());

				if ((loadCount != null && loadCount.getLoadCount() != null)
						|| (oldLoadCount != null && oldLoadCount.getLoadCount() != null)
						|| (newLoadCount != null && newLoadCount.getLoadCount() != null)){
					count = userStatisticDataMapper.updateSelective(userStatisticMap);
				}
			}
		}
       return count;
	}


}
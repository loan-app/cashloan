package com.xiji.cashloan.manage.mapper;

import java.util.List;
import java.util.Map;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.cashloan.manage.domain.QuartzInfo;
import com.xiji.cashloan.manage.model.QuartzInfoModel;

/**
 * 定时任务详情Dao
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface QuartzInfoMapper extends BaseMapper<QuartzInfo, Long> {

	/**
	 * 定时任务查询
	 * 
	 * @param map
	 * @return
	 */
	List<QuartzInfoModel> page(Map<String, Object> map);
}

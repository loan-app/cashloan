package com.xiji.cashloan.core.mapper;


import java.util.List;
import java.util.Map;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.cashloan.core.domain.User;
import com.xiji.cashloan.core.model.CloanUserModel;

/**
 * 用户管理Dao
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface UserMapper extends BaseMapper<User,Long> {

	/**
	 * 分页查询返回CreditModel
	 * @param params
	 * @return
	 */
	//List<CreditModel> page(Map<String,Object> searchMap);
	
	
	List<CloanUserModel> listModel(Map<String, Object> params);

	CloanUserModel getModel(Long id);

	List<Map<String, Object>> queryAllDic();

	/**
	 * 手机号查询id
	 * @param phone
	 * @return
	 */
	User findByLoginName(String phone);

	/**
	 * 修改等级
	 * @param user
	 * @return
	 */
	int updateLevel(User user);

	/**
	 * 查询用户等级
	 * @param map
	 * @return
	 */
	List<User> findUserLevel(Map<String, Object> map);

	/**
	 * 据uuid 修改用户信息
	 * 
	 * @param paramMap
	 * @return
	 */
	int updateByUuid(Map<String, Object> paramMap);
	
	/**
	 * 今日注册用户数
	 * @return
	 */
	long todayCount();

	/**
	 * 渠道用户统计
	 */
	List<String> listByChannel(Map<String, Object> search);

	/**
	 * 渠道注册统计
	 * @param search
	 * @return
	 */
	String registerCount(Map<String, Object> search);

}

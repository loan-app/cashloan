package com.xiji.cashloan.core.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.cashloan.core.domain.UserBaseInfo;
import com.xiji.cashloan.core.model.ManagerUserModel;
import com.xiji.cashloan.core.model.UserWorkInfoModel;

/**
 * 用户详细信息Dao
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
public interface UserBaseInfoMapper extends BaseMapper<UserBaseInfo,Long> {

	List<Map<String, Object>> getDictsCache(String type);

	ManagerUserModel getBaseModelByUserId(Long userId);

	UserBaseInfo findUserImags(@Param("userId") Long userId);

	/**
	 * 查询用户工作信息
	 * 
	 * @param userId
	 * @return
	 */
	UserWorkInfoModel findWorkInfo(@Param("userId") Long userId);

	/**
	 * 据用户id查询用户详细信息
	 * 
	 * @param userId
	 * @return
	 */
	UserBaseInfo findByUserId(@Param("userId") Long userId);
	
	/**
	 * 将用户加入黑名单
	 * @param ids
	 * @return
	 */
	int updateBlackIdNos(List<Long> ids);
	
	/**
	 * 用户加入白名单
	 * @param ids
	 * @return
	 */
	int updateWhiteIdNos(List<Long> ids);
	
	List<Long> findByIdNo(@Param("idNo") String idNo);
	
	/**
	 * 查询是否有其它用户使用此身份证号
	 * @param idNo
	 * @param userId
	 * @return
	 */
	List<UserBaseInfo> findOhterUserIdNo(@Param("idNo")String idNo, @Param("userId")Long userId);
	
}

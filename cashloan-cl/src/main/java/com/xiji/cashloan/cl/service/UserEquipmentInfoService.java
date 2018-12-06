package com.xiji.cashloan.cl.service;

import com.xiji.cashloan.cl.domain.UserEquipmentInfo;
import com.xiji.cashloan.core.common.service.BaseService;


/**
 * 用户设备信息表Service
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 *
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface UserEquipmentInfoService extends BaseService<UserEquipmentInfo, Long>{

	/**
	 * 信息入库
	 * @param uei
	 * @return
	 */
	int saveOrUpdate(UserEquipmentInfo uei);

	/**
	 * 查询
	 * @param parseLong
	 * @return
	 */
	UserEquipmentInfo findSelective(long userId);

	/**
	 * 保存设备指纹
	 * @param loginName
	 * @param blackBox
	 */
	void save(String loginName, String blackBox);

}

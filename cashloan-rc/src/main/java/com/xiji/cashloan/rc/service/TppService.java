package com.xiji.cashloan.rc.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xiji.cashloan.core.common.service.BaseService;
import com.xiji.cashloan.rc.domain.Tpp;
import com.xiji.cashloan.rc.model.ManageTppModel;
import com.xiji.cashloan.rc.model.TppModel;

/**
 * 第三方征信信息Service
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 *
 *
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface TppService extends BaseService<Tpp, Long> {

	/**
	 * 获取分页列表
	 * 
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	Page<ManageTppModel> page(Map<String, Object> params, int currentPage,
							  int pageSize);

	/**
	 * 获取列表信息
	 * 
	 * @param params
	 * @return
	 */
	List<TppModel> listAll();

	/**
	 * 遍历所有第三方信息
	 * 
	 * @return
	 */
	List<TppModel> listAllWithBusiness();

	/**
	 * 添加第三方征信tpp
	 * 
	 * @param tpp
	 * @return
	 */
	boolean save(Tpp tpp);

	/**
	 * 更新第三方征信tpp
	 * 
	 * @param tpp
	 * @return
	 */
	boolean update(Tpp tpp);

	/**
	 * 启用第三方征信tpp
	 * 
	 * @param id
	 * @return
	 */
	boolean enable(Long id);

	/**
	 * 禁用第三方征信tpp
	 * 
	 * @param id
	 * @return
	 */
	boolean disable(Long id);
	
}

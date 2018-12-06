package com.xiji.creditrank.cr.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xiji.cashloan.core.common.service.BaseService;
import com.xiji.creditrank.cr.domain.CrResultDetail;
import com.xiji.creditrank.cr.model.CrResultDetailModel;

/**
 * 评分结果Service
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 * Copyright  All Rights Reserved
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface CrResultDetailService extends BaseService<CrResultDetail, Long>{

	Page<CrResultDetail> page(Map<String, Object> secreditrankhMap, int current,int pageSize);

	List<CrResultDetailModel> listDetailTree(Long resultId);

	List<CrResultDetail> listInfo(Long cardId);
}

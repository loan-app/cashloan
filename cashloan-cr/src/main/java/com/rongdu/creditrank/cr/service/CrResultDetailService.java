package com.rongdu.creditrank.cr.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.rongdu.cashloan.core.common.service.BaseService;
import com.rongdu.creditrank.cr.domain.CrResultDetail;
import com.rongdu.creditrank.cr.model.CrResultDetailModel;

/**
 * 评分结果Service
 * 
 * @author ctt
 * @version 1.0.0
 * @date 2017-01-05 16:46:34
 * Copyright 杭州融都科技股份有限公司  creditrank All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface CrResultDetailService extends BaseService<CrResultDetail, Long>{

	Page<CrResultDetail> page(Map<String, Object> secreditrankhMap, int current,int pageSize);

	List<CrResultDetailModel> listDetailTree(Long resultId);

	List<CrResultDetail> listInfo(Long cardId);
}

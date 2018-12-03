package com.xiji.creditrank.cr.mapper;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.creditrank.cr.domain.BorrowTypeCard;

/**
 * 评分卡类型绑定表Dao
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-01-12 10:50:10
 * Copyright 杭州融都科技股份有限公司  creditrank All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface BorrowTypeCardMapper extends BaseMapper<BorrowTypeCard,Long> {

    

}

package com.xiji.cashloan.cl.mapper;

import com.xiji.cashloan.cl.domain.BankCard;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import java.util.Map;

/**
 * 银行卡Dao
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/12/03
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface BankCardMapper extends BaseMapper<BankCard,Long> {
	
	/**
	 * 根据userId查询
	 * @return
	 */
	BankCard findByUserId(Map<String, Object> paramMap);


}

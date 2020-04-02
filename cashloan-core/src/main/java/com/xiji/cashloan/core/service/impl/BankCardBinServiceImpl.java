package com.xiji.cashloan.core.service.impl;


import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.core.domain.BankCardBin;
import com.xiji.cashloan.core.mapper.BankCardBinMapper;
import com.xiji.cashloan.core.service.BankCardBinService;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * 卡binServiceImpl
 *
 * @author wjs
 * @version 1.0.0
 * @date 2019-01-25 17:50:22
 */

@Service("bankCardBinService")
public class BankCardBinServiceImpl extends BaseServiceImpl<BankCardBin, Long> implements BankCardBinService {

	private static final Logger logger = LoggerFactory.getLogger(BankCardBinServiceImpl.class);

	@Resource
	private BankCardBinMapper bankCardBinMapper;

	@Override
	public BaseMapper<BankCardBin, Long> getMapper() {
		return bankCardBinMapper;
	}

	/**
	 * 数据查询
	 * @return 结果集
	 */
	@Override
	public List<BankCardBin> listSelective(Map<String, Object> paramMap){
		return bankCardBinMapper.listSelective(paramMap);
	}


}
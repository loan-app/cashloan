package com.xiji.cashloan.cl.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiji.cashloan.cl.domain.BorrowRepay;
import com.xiji.cashloan.cl.domain.ManualRepayOrder;
import com.xiji.cashloan.cl.mapper.BorrowRepayMapper;
import com.xiji.cashloan.cl.mapper.ManualRepayOrderMapper;
import com.xiji.cashloan.cl.model.ManualRepayOrderModel;
import com.xiji.cashloan.cl.service.ManualRepayOrderService;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 到期订单ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2019-03-07 16:20:42
 */
 
@Service("manualRepayOrderService")
public class ManualRepayOrderServiceImpl extends BaseServiceImpl<ManualRepayOrder, Long> implements ManualRepayOrderService {
	
    private static final Logger logger = LoggerFactory.getLogger(ManualRepayOrderServiceImpl.class);
   
    @Resource
    private ManualRepayOrderMapper manualRepayOrderMapper;
	@Resource
	private BorrowRepayMapper borrowRepayMapper;

	@Override
	public BaseMapper<ManualRepayOrder, Long> getMapper() {
		return manualRepayOrderMapper;
	}

	@Override
	public int orderAllotUser(Map<String, Object> params) {
		List<BorrowRepay> borrowRepays = borrowRepayMapper.selectByIds(params);
		List<Long> borrowIds = new ArrayList<>();
		for (BorrowRepay borrowRepay : borrowRepays) {
			borrowIds.add(borrowRepay.getBorrowId());
		}
		params.put("ids", borrowIds);
		return manualRepayOrderMapper.orderAllotUser(params);
	}

	@Override
	public Page<ManualRepayOrderModel> list(Map<String, Object> params, int current, int pageSize) {
		PageHelper.startPage(current, pageSize);
		List<ManualRepayOrderModel> list = manualRepayOrderMapper.listModel(params);

		return (Page<ManualRepayOrderModel>)list;
	}
}
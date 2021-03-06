package com.xiji.cashloan.cl.mapper;

import com.xiji.cashloan.cl.domain.UserAuth;
import com.xiji.cashloan.cl.model.statistic.ChannelStatisticModel;
import com.xiji.cashloan.cl.model.statistic.UserStatisticData;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.cashloan.core.domain.Borrow;

import java.util.List;
import java.util.Map;


/**
 *
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 */
@RDBatisDao
public interface SystemCountMapper {
	/**
	 * 统计当日注册用户数量
	 * @param param 
	 * @return
	 */
	Integer countRegister(Map<String, Object> param);

	/**
	 * 统计当日登陆用户数量
	 * @param param 
	 * @return
	 */
	Integer countLogin(Map<String, Object> param);
	
	/**
	 * 统计当日借款申请的数量
	 * @param param 
	 * @return
	 */
	double countBorrow(Map<String, Object> param);
	
	/**
	 * 统计当日借款申请通过的数量
	 * @param param 
	 * @return
	 */
	double countBorrowPass(Map<String, Object> param);


	/**
	 * 统计当日借款申请通过的数量
	 * @param param
	 * @return
	 */
	double countNewBorrowPass(Map<String, Object> param);

	/**
	 * 统计当日借款申请放款数量
	 * @param param 
	 * @return
	 */
	double countBorrowLoan(Map<String, Object> param);
	
	/**
	 * 统计当日还款量
	 * @param param 
	 * @return
	 */
	Integer countBorrowRepay(Map<String, Object> param);
	
	/**
	 * 统计历史放款总量
	 * @return
	 */
	Integer countBorrowLoanHistory();
	
	/**
	 * 统计历史还款总量
	 * @return
	 */
	Integer countBorrowRepayHistory();
	
	/**
	 * 待还款总额
	 * @return
	 */
	Double sumBorrowNeedRepay();
	
	/**
	 * 逾期未还款总额
	 * @return
	 */
	Double sumBorrowOverdueRepay();

	/**
	 * 线上放款总额
	 * @return
	 */
	Double sumOnlineLoadAmount();

	/**
	 * 线下放款总额
	 * @return
	 */
	Double sumUnlineLoadAmount();

	/**
	 * 线上还款总额
	 * @return
	 */
	Double sumOnlineRepaymentAmount();

	/**
	 * 线下还款总额
	 * @return
	 */
	Double sumUnlineRepaymentAmount();
	
	/**
	 * 累计成功借款金额(按地区分组)
	 * @param address 
	 * @return
	 */
	String sumBorrowAmtByProvince(String address);
	
	/**
	 * 累计还款金额(按地区分组)
	 * @return
	 */
	String sumBorrowRepayByProvince(String address);
	
	/**
	 * 累计新增用户(按地区分组)
	 * @return
	 */
	String countRegisterByProvince(String address);
	
	/**
	 * 最近15日每天放款量
	 * @return
	 */
	List<Map<String,Object>> countFifteenDaysLoan();
	
	/**
	 * 还款来源 10代扣，20银行卡转账，30支付宝转账
	 * @return
	 */
	List<Map<String,Object>> countRepaySource();
	
	/**
	 * 最近15日应还款量
	 * @return
	 */
	List<Map<String,Object>> countFifteenDaysNeedRepay();
	
	/**
	 * 最近15日实际还款量
	 * @return
	 */
	List<Map<String,Object>> countFifteenDaysRealRepay();

	/**
	 * 统计注册用户数量
	 * @return
	 */
	Integer totalRegister();

	/**
	 * 统计借款申请的数量
	 * @return
	 */
	Integer totalBorrowApply();

	/**
	 * 统计借款申请通过的数量
	 * @return
	 */
	Integer totalBorrowApplyPass();

	/**
	 * 查询当日注册认证信息
	 * @return
	 */
	List<UserAuth> listUserAuthByToday();

	/**
	 * 当日借款信息
	 * @return
	 */
	List<Borrow> listBorrowByToday();

	/**
	 * 当日放款成功借款信息列表
	 * @return
	 */
	List<Borrow> listBorrowStatistics();

    /**
     * 用户数据统计
     * @return
     */
	List<UserStatisticData> listUserStatisticData(Map<String,Object> params);


	/**
	 * 渠道数据统计
	 * @param params
	 * @return
	 */
	List<ChannelStatisticModel> listChannelStatisticData(Map<String,Object> params);
}

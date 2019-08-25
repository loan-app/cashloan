package com.xiji.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.xiji.cashloan.cl.model.*;
import org.apache.ibatis.annotations.Param;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.cashloan.core.domain.Borrow;

/**
 * 借款信息表Dao
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
public interface ClBorrowMapper extends BaseMapper<Borrow,Long> {
	
	/**
	 * 查询用户未完成的借款
	 * @param userId
	 * @return
	 */
	List<Borrow> findUserUnFinishedBorrow(@Param("userId")Long userId);

	/**
	 * 首页信息查询
	 * @return
	 */
	List<IndexModel> listIndex();

	/**
	 * 订单查询
	 * @param searchMap
	 * @return
	 */
	List<ClBorrowModel> findBorrow(Map<String, Object> searchMap);

	/**
	 * 查看借款
	 * @param searchMap
	 * @return
	 */
	List<RepayModel> findRepay(Map<String, Object> searchMap);

	/**
	 * 计时任务计算逾期
	 * @return
	 */
	List<RepayModel> compute();
	
	/**
	 * 查询
	 * @param params
	 * @return
	 */
	List<ManageBorrowModel> listModel(Map<String, Object> params);

	/**
	 * 查询所有
	 * @param searchMap
	 * @return
	 */
	List<ClBorrowModel> listAll(Map<String, Object> searchMap);

	/**
	 * 逾期未入催
	 * @param params
	 * @return
	 */
	List<ManageBorrowModel> listModelNotUrge(Map<String, Object> params);

	/**
	 * 查询未还款订单
	 * @param borrowMap
	 * @return
	 */
	Borrow findRepayBorrow(Map<String, Object> borrowMap);

	/**
	 * 查询可借款用户
	 * @return
	 */
	List<ManageBorrowTestModel> seleteUser();
	
	/**
	 * 更新借款状态
	 * @param satet
	 * @param id
	 * @return
	 */
	int updateState(@Param("state")String state,@Param("id")Long id);
	/**
	 * 借款部分还款信息
	 * @param params
	 * @return
	 */
	List<ManageBorrowModel> listBorrowModel(Map<String, Object> params);

	/**
	 * 查询未还款
	 * @param paramMap
	 * @return
	 */
	Borrow findByUserIdAndState(Map<String, Object> paramMap);

    /**
     * 支付时更新Borrow状态
     * @param paramMap
     * @return
     */
    int updatePayState(Map<String,Object> paramMap);

	/**
	 * 借款成功次数
	 * @param userId
	 * @return
	 */
	int successCount(long userId);
	
	/**
	 * 借款完成次数
	 * @param userId
	 * @return
	 */
	int  finishCount(long userId);

	/**
	 * 人工复审修改状态
	 * @param id
	 * @param state
	 * @return
	 */
	int reviewState(Map<String, Object> map);

	/**
	 * 复审通过查询
	 * @return
	 */
	List<ManageBorrowModel> listReview(Map<String, Object> params);

	/**
	 * 查询最后一条借款
	 * @param userId
	 * @return
	 */
	Borrow findLastBorrow(long userId);
	
	/**
	 * 查询当天借款总额
	 * 
	 * @param map
	 * @return
	 */
	double borrowAmountSum();
    
	/**
	 * 查询用户还款成功的记录数
	 */
	int userBorrowCount(long userId);

	int syncUpdateState(Map<String, Object> params);
	
	/**
	 * 放款审核修改状态
	 * @param id
	 * @param state
	 * @return
	 */
	int loanState(Map<String, Object> map);

	/**
	 * 根据userId统计借款人数
	 * @param channelId
	 * @return
	 */
	String borrowCountByUserId(String channelId);

	/**
	 * 根据userId统计借款次数
	 * @param channelId
	 * @return
	 */
	String borrowSumByUserId(String channelId);

	/**
	 * 根据userId统计借款成功人数
	 * @param channelId
	 * @return
	 */
	String borrowSuccessCountByUserId(String channelId);

	/**
	 * 根据userId统计借款成功次数
	 * @param channelId
	 * @return
	 */
	String borrowSuccessSumByUserId(String channelId);

	/**
	 * 根据userId统计放款人数
	 * @param channelId
	 * @return
	 */
	String loanCountByUserId(String channelId);

	/**
	 * 根据userId统计放款次数
	 * @param channelId
	 * @return
	 */
	String loanSumByUserId(String channelId);

	/**
	 * 根据userId统计应放款金额
	 * @param channelId
	 * @return
	 */
	String payCountByUserId(String channelId);

	/**
	 * 根据userId统计实际放款金额
	 * @param channelId
	 * @return
	 */
	String realPayCountUserId(String channelId);

	/**
	 * 根据userId统计逾期人数
	 * @param channelId
	 * @return
	 */
	String overdueCountByUserId(String channelId);
	/**
	 * 根据id进行状态修改
	 * @param map
	 * @return
	 */
	int updateFromPreState(Map<String, Object> map);
	/**
	 * 根据附属状态进行更新附属状态(附属状态为0时更新)
	 * @param map
	 * @return
	 */
	
	int updatesub(@Param("id")Long id);

	/**
	 * 共享数据给宜信
	 * @param userId
	 * @return
     */
	List<YixinShareModel> queryDataForYixin(Long userId);

	/**
	 * 获取模型数据
	 * @param borrowId
	 * @return
     */
	Map<String,Object> getModelData(Long borrowId);
}

package com.xiji.cashloan.cl.service;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.model.*;
import com.xiji.cashloan.core.common.service.BaseService;
import com.xiji.cashloan.core.domain.Borrow;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 借款信息表Service
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface ClBorrowService extends BaseService<Borrow, Long>{
 
	/**
	 * 保存浅橙返回信息
	 * @param qcRsMsg
	 * @param borrow
	 */
	void saveQcResult(String qcRsMsg,Borrow borrow);
	
	/**
	 * 判断是否可以借款
	 * @param borrow
	 * @param tradePwd
	 * @return
	 */
	boolean isCanBorrow(Borrow borrow,String tradePwd);
	
	/**
	 * 保存借款申请
	 * @param borrow
	 * @return
	 */
	Borrow saveBorrow(Borrow borrow);
	
	/**
	 * 修改借款状态
	 * @param id
	 * @param state
	 * @return
	 */
	int modifyState(long id, String state,String preState);

	/**
	 * 添加借款进度
	 * 
	 * @param borrow
	 * @param state
	 */
	void savePressState(Borrow borrow, String state,String remark);

	/**
	 * 信用额度修改
	 * 
	 * @param userId
	 * @param amount
	 * @param type
	 */
	int modifyCredit(Long userId, double amount, String type);

	/**
	 * 首页信息查询
	 * @param userId 
	 * @return
	 */
	Map<String,Object> findIndex(String userId);
	
	/**
	 * 根据借款金额和借款时长返回综合费用及明细
	 * @param amount
	 * @param timeLimit
	 * @return
	 */
	Map<String, Object> choice(double amount, String timeLimit);
	
	/**
	 * 查询所有借款费用信息
	 * @return
	 */
	List<Map<String,Object>> choices();
	
	/**
	 * 查询
	 * @param searchMap
	 * @return
	 */
	List<Borrow> findBorrowByMap(Map<String, Object> searchMap);

	/**
	 * 查询最新10条借款信息
	 * @return
	 */
	List<IndexModel> listIndex();

	/**
	 * 借款记录查看
	 * @param searchMap
	 * @return
	 */
	List<RepayModel> findRepay(Map<String, Object> searchMap);

	/**
	 * 分页查询
	 * @param searchMap
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<ClBorrowModel> page(Map<String, Object> searchMap, int current,
							 int pageSize);
	
	/**
	 * 关联用户的借款分页查询后台列表显示
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	Page<ManageBorrowModel> listModel(Map<String, Object> params,
			int currentPage, int pageSize);

	/**
	 * 修改数据
	 * @param data
	 * @return
	 */
	int updateSelective(Map<String, Object> data);

	/**
	 * 匹配规则查询
	 * @param borrowId
	 * @return
	 */
	Map<String, Object> findResult(long borrowId);
    
    /**
     * 查询可借款用户
     * @return
     */
	List<ManageBorrowTestModel> seleteUser();

	/**
	 * 放款
	 * @param borrow
	 * @param date
	 * @return
	 * @throws Exception 
	 */
	void borrowLoan(Borrow borrow,Date date) ;
	
	/**
	 * 后台人工复审功能
	 * @param borrowId
	 * @param state
	 * @param remark
	 * @return 
	 */
	int manualVerifyBorrow(Long borrowId, String state, String remark, Long userId,Boolean isBlack);
	
	/**
	 * 借款部分还款信息
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	Page<ManageBorrowModel> listBorrowModel(Map<String, Object> params,
			int currentPage, int pageSize);
	
	List<ManageBorrowModel> listBorrowModel(Map<String, Object> params);
	 /**
	  * 借款详细信息
	  * @param borrowId
	  * @return
	  */
	ManageBorrowModel getModelByBorrowId(long borrowId);

	/**
	 * 借款进度显示
	 * @param borrow
	 * @param pageFlag
	 * @return
	 */
	List<BorrowProgressModel> borrowProgress(Borrow borrow,String pageFlag);
	
	/**
	 * 支付时更新状态
	 * @return 
	 * @return
	 */
	void updatePayState(Map<String, Object> paramMap);

	/**
	 * 主键查询借款
	 * @param borrowId
	 * @return
	 */
	Borrow findByPrimary(Long borrowId);
	
	/**
	 * 新增借款申请业务处理
	 * @param borrow
	 * @return
	 */
	ClBorrowModel rcBorrowApply(Borrow borrow,String tradePwd,String mobileType, boolean xwldFlag) throws Exception;
	
	/**
	 * 借款规则审核
	 * @param borrowId
	 */
	void rcBorrowRuleVerify(Long borrowId);
	
	/**
	 * 统计接口接口审核结果
	 * @param state
	 * @param desc
	 * @param borrowId
	 * @param nid
	 * @return
	 */
	void syncSceneBusinessLog(Long borrowId,String nid,int count);
	
	/**
	 * 接口异步通知时更新
	 * @param state
	 * @param desc
	 * @param borrowId
	 * @param nid
	 */
	void syncSceneBusinessLog(String state, String desc,Long borrowId,String nid);

	/**
	 * 查询借款
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	List listBorrow(Map<String, Object> params);
	
	/**
	 * 获取风控数据并审核
	 * @param borrowId
	 * @param mobileType
	 */
	void verifyBorrowData(long borrowId, String mobileType);
	
	/**
	 * 重新获取风控数据并审核
	 * @param borrowId
	 */
	void reVerifyBorrowData(Long borrowId);

	/**
	 * 复审通过查询
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	Page<ManageBorrowModel> listReview(Map<String,Object> params,int currentPage, int pageSize);

	/**
	 * 查询是未完成借款
	 * @param long1
	 * @return
	 */
	List<Borrow> findUserUnFinishedBorrow(long userId);

	/**
	 * 查询最后一条借款
	 * @param userId
	 * @return
	 */
	Borrow findLastBorrow(long userId);

	/**
	 * 修改借款状态
	 * @param borrowId
	 * @param userId
	 * @param stateRepayProcessing
	 */
	int modifyBorrowState(long borrowId, long userId, String state);
	
	/**
	 * 改变总额度
	 * @param total
	 * @return
	 */
	void changeCreditTotal(double total);
	
	/**
	 * 
	 * @description 审核放款
	 * @param borrowId
	 * @param state
	 * @param remark
	 * @author mcwang
	 * @return void
	 * @since  1.0.0
	 */
	int auditBorrowLoan(Long borrowId, String state, String remark,Long userId);

	/**
	 * 共享数据给宜信
	 * @param userId
	 * @param idNo
	 * @param name
     * @return
     */
	List<YixinShareModel> queryDataForYixin(Long userId, String idNo, String name);

	/**
	 *
	 */

}

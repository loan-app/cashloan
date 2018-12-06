package com.xiji.cashloan.core.mapper;

import java.util.List;
import java.util.Map;

import com.xiji.cashloan.core.model.BorrowModel;
import org.apache.ibatis.annotations.Param;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.cashloan.core.domain.Borrow;

/**
 * 借款申请管理Dao
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface BorrowMapper extends BaseMapper<Borrow,Long> {

    /**
     * 查询借款申请
     * @param map
     * @return
     */
    List<BorrowModel> selectByConditions(Map<String,Object> map);
    
    /**
     * 根据用户标识和标的标识查询借款申请
     */
    List<BorrowModel> findByConsumerAndBorrow(String consumerNo,String borrowNo);
    
    /**
     * 自动审批查找需要比对的值
     * @param sql
     * @return
     */
    String findValidValue(@Param("statement")String statement);
  
}

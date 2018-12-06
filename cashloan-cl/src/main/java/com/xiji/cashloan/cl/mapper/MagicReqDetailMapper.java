package com.xiji.cashloan.cl.mapper;

import com.xiji.cashloan.cl.model.CreditLoanUserModel;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.cashloan.cl.domain.MagicReqDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 魔杖2.0-请求详情Dao
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-05 17:23:11
 */
@RDBatisDao
public interface MagicReqDetailMapper extends BaseMapper<MagicReqDetail, Long> {

    List<CreditLoanUserModel> listModel(Map<String, Object> params);

    MagicReqDetail getLastRecord(@Param("userId") Long userId, @Param("type") int type);
}

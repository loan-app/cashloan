package com.xiji.cashloan.cl.mapper;

import com.xiji.cashloan.cl.domain.OperatorSms;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import org.apache.ibatis.annotations.Param;

/**
 * 魔蝎运营商数据-短信详情Dao
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-11-24 14:59:54
 */
@RDBatisDao
public interface OperatorSmsMapper extends BaseMapper<OperatorSms, Long> {

    /**
     * 根据表名查询表数量
     * @param tableName
     * @return
     */
    int countTable(String tableName);

    /**
     * 根据表名创建表
     * @param tableName
     */
    void createTable(@Param("tableName") String tableName);

    /**
     * (分表)新增
     * @param tableName
     * @param operatorSms
     * @return
     */
    int saveShard(@Param("tableName")String tableName, @Param("item")OperatorSms operatorSms);

}

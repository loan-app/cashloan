package com.rongdu.cashloan.cl.mapper;


import com.rongdu.cashloan.cl.domain.OperatorNet;
import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.mapper.RDBatisDao;
import org.apache.ibatis.annotations.Param;

/**
 * 魔蝎运营商数据-流量详情Dao
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-11-24 15:06:22
 */
@RDBatisDao
public interface OperatorNetMapper extends BaseMapper<OperatorNet, Long> {

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
     * @param operatorNet
     * @return
     */
    int saveShard(@Param("tableName")String tableName, @Param("item")OperatorNet operatorNet);

}

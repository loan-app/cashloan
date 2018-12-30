package com.xiji.cashloan.cl.mapper;

import com.xiji.cashloan.cl.domain.OperatorVoiceCnt;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/**
 * 通话详情统计Dao
 * 
 * @author king
 * @version 1.0.0
 * @date 2018-12-17 11:31:50
 */
@RDBatisDao
public interface OperatorVoiceCntMapper extends BaseMapper<OperatorVoiceCnt, Long> {
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
     * @param operatorVoiceCnt
     * @return
     */
    int saveShard(@Param("tableName")String tableName, @Param("item")OperatorVoiceCnt operatorVoiceCnt);

    /**
     * 根据参数(分表)查询
     * @param tableName
     * @param params
     * @return
     */
    List<OperatorVoiceCnt> listShardSelective(
        @Param("tableName") String tableName,
        @Param("params") Map<String, Object> params);

    OperatorVoiceCnt getLastRecord(@Param("tableName") String tableName, @Param("userId") Long userId);

    int countNotNull(@Param("tableName") String tableName1, @Param("userId") Long userId);

    void updateLastContactTime(Map<String, Object> updateMap);
}

package com.xiji.cashloan.cl.mapper;

import com.xiji.cashloan.cl.domain.BlacklistCommonData;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/**
 * 黑名单通用处理模型实体Dao
 * 
 * @author king
 * @version 1.0.0
 * @date 2018-12-20 14:29:13
 */
@RDBatisDao
public interface BlacklistCommonDataMapper extends BaseMapper<BlacklistCommonData, Long> {
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
     * @return
     */
    int saveShard(@Param("tableName")String tableName, @Param("item")BlacklistCommonData blacklistCommonData);

    /**
     * 根据参数(分表)查询
     * @param tableName
     * @param params
     * @return
     */
    List<BlacklistCommonData> listShardSelective(
        @Param("tableName") String tableName,
        @Param("params") Map<String, Object> params);
}

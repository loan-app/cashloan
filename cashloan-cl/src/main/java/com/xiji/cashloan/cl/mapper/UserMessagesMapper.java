package com.xiji.cashloan.cl.mapper;

import com.xiji.cashloan.cl.domain.UserMessages;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import org.apache.ibatis.annotations.Param;

/**
 * 用户资料--联系人Dao
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
public interface UserMessagesMapper extends BaseMapper<UserMessages,Long> {

    /**
     * (分表)新增
     * @param tableName
     * @param userMessages
     * @return
     */
    int saveShard(@Param("tableName")String tableName, @Param("userMessages")UserMessages userMessages);

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
}

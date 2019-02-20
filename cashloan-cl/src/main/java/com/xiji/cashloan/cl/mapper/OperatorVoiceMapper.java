package com.xiji.cashloan.cl.mapper;


import com.xiji.cashloan.cl.domain.OperatorVoice;
import com.xiji.cashloan.cl.model.OperatorVoiceModel;
import com.xiji.cashloan.cl.model.PinganCallDetailsModel;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 魔蝎运营商数据-通话记录Dao
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-11-24 14:50:00
 */
@RDBatisDao
public interface OperatorVoiceMapper extends BaseMapper<OperatorVoice, Long> {

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
     * @param operatorVoice
     * @return
     */
    int saveShard(@Param("tableName")String tableName, @Param("item")OperatorVoice operatorVoice);

    /**
     * 根据参数(分表)查询
     * @param tableName
     * @param params
     * @return
     */
    List<OperatorVoice> listShardSelective(
            @Param("tableName") String tableName,
            @Param("params") Map<String, Object> params);

    OperatorVoiceModel operatorVoicesCount(@Param("tableName") String tableName, @Param("userId") Long userId, @Param("phone") String phone);

    OperatorVoiceModel operatorVoicesCount1(@Param("tableName1") String tableName1, @Param("userId") Long userId, @Param("phone") String phone);

    /**
     * 根据用户id,请求记录id,查询通话详情
     * @param tableName
     * @param reqLogId
     * @return
     */
    List<PinganCallDetailsModel> queryPinganVoiceDetail(@Param("tableName") String tableName, @Param("reqLogId") Long reqLogId);

    /**
     * 获取每个号码的最后通话时间
     * @param tableName
     * @param userId
     * @param reqLogId
     * @return
     */
    List<Map<String, String>> getLastContactTime(@Param("tableName") String tableName, @Param("userId") Long userId, @Param("reqLogId") Long reqLogId);
}

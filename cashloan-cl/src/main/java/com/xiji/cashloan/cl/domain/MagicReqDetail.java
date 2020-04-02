package com.xiji.cashloan.cl.domain;

import com.xiji.cashloan.core.common.util.DateUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * 魔杖2.0-请求详情实体
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-05 17:23:11
 */
 public class MagicReqDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 用户id
    */
    private Long userId;

    /**
    * 报告id
    */
    private String transId;

    /**
    * 返回内容
    */
    private String data;

    /**
    * 类型 2-魔杖反欺诈 3-魔杖多头 4-魔杖黑灰名单 5-魔杖贷后行为
    */
    private Integer type;

    /**
    * 创建时间
    */
    private Date createTime;

    public MagicReqDetail() {
        super();
    }

    public MagicReqDetail(Long userId, String transId, String data, Integer type) {
        super();
        this.userId = userId;
        this.transId = transId;
        this.data = data;
        this.type = type;
        this.createTime = DateUtil.getNow();
    }

    /**
    * 获取主键Id
    *
    * @return id
    */
    public Long getId(){
        return id;
    }

    /**
    * 设置主键Id
    * 
    * @param 要设置的主键Id
    */
    public void setId(Long id){
        this.id = id;
    }

    /**
    * 获取用户id
    *
    * @return 用户id
    */
    public Long getUserId(){
        return userId;
    }

    /**
    * 设置用户id
    * 
    * @param userId 要设置的用户id
    */
    public void setUserId(Long userId){
        this.userId = userId;
    }

    /**
    * 获取报告id
    *
    * @return 报告id
    */
    public String getTransId(){
        return transId;
    }

    /**
    * 设置报告id
    * 
    * @param transId 要设置的报告id
    */
    public void setTransId(String transId){
        this.transId = transId;
    }

    /**
    * 获取返回内容
    *
    * @return 返回内容
    */
    public String getData(){
        return data;
    }

    /**
    * 设置返回内容
    * 
    * @param data 要设置的返回内容
    */
    public void setData(String data){
        this.data = data;
    }

    /**
    * 获取类型 2-魔杖反欺诈 3-魔杖多头 4-魔杖黑灰名单 5-魔杖贷后行为
    *
    * @return 类型 2-魔杖反欺诈 3-魔杖多头 4-魔杖黑灰名单 5-魔杖贷后行为
    */
    public Integer getType(){
        return type;
    }

    /**
    * 设置类型 2-魔杖反欺诈 3-魔杖多头 4-魔杖黑灰名单 5-魔杖贷后行为
    * 
    * @param type 要设置的类型 2-魔杖反欺诈 3-魔杖多头 4-魔杖黑灰名单 5-魔杖贷后行为
    */
    public void setType(Integer type){
        this.type = type;
    }

    /**
    * 获取创建时间
    *
    * @return 创建时间
    */
    public Date getCreateTime(){
        return createTime;
    }

    /**
    * 设置创建时间
    * 
    * @param createTime 要设置的创建时间
    */
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

}
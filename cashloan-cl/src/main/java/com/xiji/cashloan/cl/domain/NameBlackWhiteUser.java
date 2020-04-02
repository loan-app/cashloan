package com.xiji.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by szb on 18/12/15.
 */
public class NameBlackWhiteUser implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    private Long id;

    /**
     * 类别：01-身份证、02-手机号、等
     */
    private String dimensionkey;

    /**
     * 类别对应的值
     */
    private String dimensionvalue;

    /**
     * 来源
     */
    private String source;

    /**
     * 状态 0:正常，1:删除
     */
    private Integer status;

    /**
     *
     */
    private Date createtime;

    /**
     *
     */
    private Date lastmodifytime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDimensionkey() {
        return dimensionkey;
    }

    public void setDimensionkey(String dimensionkey) {
        this.dimensionkey = dimensionkey;
    }

    public String getDimensionvalue() {
        return dimensionvalue;
    }

    public void setDimensionvalue(String dimensionvalue) {
        this.dimensionvalue = dimensionvalue;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getLastmodifytime() {
        return lastmodifytime;
    }

    public void setLastmodifytime(Date lastmodifytime) {
        this.lastmodifytime = lastmodifytime;
    }
}

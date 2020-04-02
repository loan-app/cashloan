package com.xiji.cashloan.cl.domain;

import java.io.Serializable;

/**
 * 运营商信息-通话记录月份信息实体
 * Created by szb on 18/11/23.
 */
public class OperatorMonthInfo extends OperatorBase implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 有通话记录月份数
     */
    private Integer monthCount;

    /**
     * 通话记录获取失败月份数
     */
    private Integer missMonthCount;

    /**
     * 无通话记录月份数
     */
    private Integer noCallCount;

    public Integer getMonthCount() {
        return monthCount;
    }

    public void setMonthCount(Integer monthCount) {
        this.monthCount = monthCount;
    }

    public Integer getMissMonthCount() {
        return missMonthCount;
    }

    public void setMissMonthCount(Integer missMonthCount) {
        this.missMonthCount = missMonthCount;
    }

    public Integer getNoCallCount() {
        return noCallCount;
    }

    public void setNoCallCount(Integer noCallCount) {
        this.noCallCount = noCallCount;
    }
}

package com.xiji.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 运营商信息-套餐详情实体(数据库存储)
 * Created by szb on 18/11/23.
 */
public class OperatorPackage extends OperatorBase implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账单起始日
     */
    private Date billStartDate;

    /**
     * 账单结束日
     */
    private Date billEndDate;

    /**
     * 套餐项目名称
     */
    private String item;

    /**
     * 套餐项目总量
     */
    private String total;

    /**
     * 套餐项目已使用量
     */
    private String used;

    /**
     * 套餐项目单位：语音-分; 流量-KB; 短/彩信-条
     */
    private String unit;

    public Date getBillStartDate() {
        return billStartDate;
    }

    public void setBillStartDate(Date billStartDate) {
        this.billStartDate = billStartDate;
    }

    public Date getBillEndDate() {
        return billEndDate;
    }

    public void setBillEndDate(Date billEndDate) {
        this.billEndDate = billEndDate;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}

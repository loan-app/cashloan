package com.xiji.cashloan.cl.domain.operator;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 运营商信息-套餐详情实体(源数据)
 * Created by szb on 18/11/23.
 */
public class OperatorPackageMeta implements Serializable {

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
     * 套餐明细
     */
    private List<OperatorPackageItem> items;

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

    public List<OperatorPackageItem> getItems() {
        return items;
    }

    public void setItems(List<OperatorPackageItem> items) {
        this.items = items;
    }
}

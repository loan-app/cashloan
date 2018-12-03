package com.rongdu.cashloan.cl.domain.operator;

import java.io.Serializable;
import java.util.List;

/**
 * 运营商信息-流量详情实体(源数据)
 * Created by szb on 18/11/23.
 */
public class OperatorNetMeta implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 月份，格式 yyyy-MM
     */
    private String billMonth;

    /**
     * 流量详情明细
     */
    private List<OperatorNetItem> items;

    public String getBillMonth() {
        return billMonth;
    }

    public void setBillMonth(String billMonth) {
        this.billMonth = billMonth;
    }

    public List<OperatorNetItem> getItems() {
        return items;
    }

    public void setItems(List<OperatorNetItem> items) {
        this.items = items;
    }
}

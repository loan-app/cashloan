package com.xiji.cashloan.cl.domain.operator;

import java.io.Serializable;
import java.util.List;

/**
 * 运营商信息-短信详情实体(源数据)
 * Created by szb on 18/11/23.
 */
public class OperatorSmsMeta implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 月份，格式 yyyy-MM
     */
    private String billMonth;

    /**
     * 短信详情明细
     */
    private List<OperatorSmsItem> items;

    public String getBillMonth() {
        return billMonth;
    }

    public void setBillMonth(String billMonth) {
        this.billMonth = billMonth;
    }

    public List<OperatorSmsItem> getItems() {
        return items;
    }

    public void setItems(List<OperatorSmsItem> items) {
        this.items = items;
    }
}

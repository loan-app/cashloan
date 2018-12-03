package com.rongdu.cashloan.cl.domain.operator;

import java.io.Serializable;
import java.util.List;

/**
 * 运营商信息-通话记录实体(源数据)
 * Created by szb on 18/11/23.
 */
public class OperatorVoiceMeta implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 通话记录月份，格式 yyyy-MM
     */
    private String billMonth;

    /**
     * 通话记录明细
     */
    private List<OperatorVoiceItem> items;

    public String getBillMonth() {
        return billMonth;
    }

    public void setBillMonth(String billMonth) {
        this.billMonth = billMonth;
    }

    public List<OperatorVoiceItem> getItems() {
        return items;
    }

    public void setItems(List<OperatorVoiceItem> items) {
        this.items = items;
    }
}

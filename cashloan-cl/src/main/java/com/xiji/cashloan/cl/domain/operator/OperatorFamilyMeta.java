package com.xiji.cashloan.cl.domain.operator;

import java.io.Serializable;
import java.util.List;

/**
 * 运营商信息-亲情网实体(源数据)
 * Created by szb on 18/11/23.
 */
public class OperatorFamilyMeta implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 亲情网编号
     */
    private Integer familyNum;

    private List<OperatorFamilyItem> items;

    public Integer getFamilyNum() {
        return familyNum;
    }

    public void setFamilyNum(Integer familyNum) {
        this.familyNum = familyNum;
    }

    public List<OperatorFamilyItem> getItems() {
        return items;
    }

    public void setItems(List<OperatorFamilyItem> items) {
        this.items = items;
    }
}

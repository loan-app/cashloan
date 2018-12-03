package com.rongdu.cashloan.cl.domain.mozhang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by lu on 2018/9/27.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UntrustedInfoBean {
    /**
     * courtcase_cnt : 0
     * dishonest_cnt : 0
     * dishonest_detail_ino : [{"court_name":"string","area_name":"string","case_code":"string","publish_date":"string","gist_id":"string","duty":"string","performance":"string","disrupt_type_name":"string"}]
     */

    @JsonProperty("courtcase_cnt")
    private int courtcaseCnt;
    @JsonProperty("dishonest_cnt")
    private int dishonestCnt;
    @JsonProperty("dishonest_detail_ino")
    private List<DishonestDetailInoBean> dishonestDetailIno;

    public int getCourtcaseCnt() {
        return courtcaseCnt;
    }

    public void setCourtcaseCnt(int courtcaseCnt) {
        this.courtcaseCnt = courtcaseCnt;
    }

    public int getDishonestCnt() {
        return dishonestCnt;
    }

    public void setDishonestCnt(int dishonestCnt) {
        this.dishonestCnt = dishonestCnt;
    }

    public List<DishonestDetailInoBean> getDishonestDetailIno() {
        return dishonestDetailIno;
    }

    public void setDishonestDetailIno(List<DishonestDetailInoBean> dishonestDetailIno) {
        this.dishonestDetailIno = dishonestDetailIno;
    }
}

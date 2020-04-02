package com.xiji.cashloan.cl.domain.mozhang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author wnb
 * @date 2018/12/03
 * @version 1.0.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuspiciousMobileBean {
    /**
     * other_names_cnt : 0
     * other_idcards_cnt : 0
     * other_idcards_black_cnt : 0
     * information_sources_cnt : 0
     * other_names : [{"latest_used_time":"string","name":"string"}]
     * other_idcards : [{"latest_used_time":"string","idcard":"string","isblack":true}]
     * information_sources : [{"latest_used_time":"string","org_type":"string"}]
     */

    @JsonProperty("other_names_cnt")
    private int otherNamesCnt;
    @JsonProperty("other_idcards_cnt")
    private int otherIdcardsCnt;
    @JsonProperty("other_idcards_black_cnt")
    private int otherIdcardsBlackCnt;
    @JsonProperty("information_sources_cnt")
    private int informationSourcesCnt;
    @JsonProperty("other_names")
    private List<OtherNamesBean> otherNames;
    @JsonProperty("other_idcards")
    private List<OtherIdcardsBean> otherIdcards;
    @JsonProperty("information_sources")
    private List<InformationSourcesBean> informationSources;

    public int getOtherNamesCnt() {
        return otherNamesCnt;
    }

    public void setOtherNamesCnt(int otherNamesCnt) {
        this.otherNamesCnt = otherNamesCnt;
    }

    public int getOtherIdcardsCnt() {
        return otherIdcardsCnt;
    }

    public void setOtherIdcardsCnt(int otherIdcardsCnt) {
        this.otherIdcardsCnt = otherIdcardsCnt;
    }

    public int getOtherIdcardsBlackCnt() {
        return otherIdcardsBlackCnt;
    }

    public void setOtherIdcardsBlackCnt(int otherIdcardsBlackCnt) {
        this.otherIdcardsBlackCnt = otherIdcardsBlackCnt;
    }

    public int getInformationSourcesCnt() {
        return informationSourcesCnt;
    }

    public void setInformationSourcesCnt(int informationSourcesCnt) {
        this.informationSourcesCnt = informationSourcesCnt;
    }

    public List<OtherNamesBean> getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(List<OtherNamesBean> otherNames) {
        this.otherNames = otherNames;
    }

    public List<OtherIdcardsBean> getOtherIdcards() {
        return otherIdcards;
    }

    public void setOtherIdcards(List<OtherIdcardsBean> otherIdcards) {
        this.otherIdcards = otherIdcards;
    }

    public List<InformationSourcesBean> getInformationSources() {
        return informationSources;
    }

    public void setInformationSources(List<InformationSourcesBean> informationSources) {
        this.informationSources = informationSources;
    }
}

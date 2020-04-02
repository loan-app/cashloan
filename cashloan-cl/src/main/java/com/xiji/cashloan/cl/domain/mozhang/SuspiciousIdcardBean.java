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
public class SuspiciousIdcardBean {
    /**
     * other_names_cnt : 0
     * other_mobiles_cnt : 0
     * other_mobiles_black_cnt : 0
     * information_sources_cnt : 0
     * other_names : [{"latest_used_time":"string","name":"string"}]
     * other_mobiles : [{"latest_used_time":"string","mobile":"string","carrier":"string","mobile_location":"string","isblack":true}]
     * information_sources : [{"latest_used_time":"string","org_type":"string"}]
     */

    @JsonProperty("other_names_cnt")
    private int otherNamesCnt;
    @JsonProperty("other_mobiles_cnt")
    private int otherMobilesCnt;
    @JsonProperty("other_mobiles_black_cnt")
    private int otherMobilesBlackCnt;
    @JsonProperty("information_sources_cnt")
    private int informationSourcesCnt;
    @JsonProperty("other_names")
    private List<OtherNamesBean> otherNames;
    @JsonProperty("other_mobiles")
    private List<OtherMobilesBean> otherMobiles;
    @JsonProperty("information_sources")
    private List<InformationSourcesBean> informationSources;

    public int getOtherNamesCnt() {
        return otherNamesCnt;
    }

    public void setOtherNamesCnt(int otherNamesCnt) {
        this.otherNamesCnt = otherNamesCnt;
    }

    public int getOtherMobilesCnt() {
        return otherMobilesCnt;
    }

    public void setOtherMobilesCnt(int otherMobilesCnt) {
        this.otherMobilesCnt = otherMobilesCnt;
    }

    public int getOtherMobilesBlackCnt() {
        return otherMobilesBlackCnt;
    }

    public void setOtherMobilesBlackCnt(int otherMobilesBlackCnt) {
        this.otherMobilesBlackCnt = otherMobilesBlackCnt;
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

    public List<OtherMobilesBean> getOtherMobiles() {
        return otherMobiles;
    }

    public void setOtherMobiles(List<OtherMobilesBean> otherMobiles) {
        this.otherMobiles = otherMobiles;
    }

    public List<InformationSourcesBean> getInformationSources() {
        return informationSources;
    }

    public void setInformationSources(List<InformationSourcesBean> informationSources) {
        this.informationSources = informationSources;
    }
}

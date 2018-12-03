package com.rongdu.cashloan.cl.domain.mozhang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by lu on 2018/9/27.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuspiciousDeviceBean {
    /**
     * other_devices_cnt : 0
     * mobile_other_devices_cnt : 0
     * idcard_other_devices_cnt : 0
     * information_sources_cnt : 0
     * other_names_cnt : 0
     * other_mobiles_cnt : 0
     * other_mobiles_black_cnt : 0
     * other_idcards_cnt : 0
     * other_idcards_black_cnt : 0
     * other_names : [{"latest_used_time":"string","name":"string"}]
     */

    @JsonProperty("other_devices_cnt")
    private int otherDevicesCnt;
    @JsonProperty("mobile_other_devices_cnt")
    private int mobileOtherDevicesCnt;
    @JsonProperty("idcard_other_devices_cnt")
    private int idcardOtherDevicesCnt;
    @JsonProperty("information_sources_cnt")
    private int informationSourcesCnt;
    @JsonProperty("other_names_cnt")
    private int otherNamesCnt;
    @JsonProperty("other_mobiles_cnt")
    private int otherMobilesCnt;
    @JsonProperty("other_mobiles_black_cnt")
    private int otherMobilesBlackCnt;
    @JsonProperty("other_idcards_cnt")
    private int otherIdcardsCnt;
    @JsonProperty("other_idcards_black_cnt")
    private int otherIdcardsBlackCnt;
    @JsonProperty("other_names")
    private List<OtherNamesBean> otherNames;
    @JsonProperty("other_mobiles")
    private List<OtherMobilesBean> otherMobiles;
    @JsonProperty("other_idcards")
    private List<OtherIdcardsBean> otherIdcards;
    @JsonProperty("information_sources")
    private List<InformationSourcesBean> informationSources;

    public int getOtherDevicesCnt() {
        return otherDevicesCnt;
    }

    public void setOtherDevicesCnt(int otherDevicesCnt) {
        this.otherDevicesCnt = otherDevicesCnt;
    }

    public int getMobileOtherDevicesCnt() {
        return mobileOtherDevicesCnt;
    }

    public void setMobileOtherDevicesCnt(int mobileOtherDevicesCnt) {
        this.mobileOtherDevicesCnt = mobileOtherDevicesCnt;
    }

    public int getIdcardOtherDevicesCnt() {
        return idcardOtherDevicesCnt;
    }

    public void setIdcardOtherDevicesCnt(int idcardOtherDevicesCnt) {
        this.idcardOtherDevicesCnt = idcardOtherDevicesCnt;
    }

    public int getInformationSourcesCnt() {
        return informationSourcesCnt;
    }

    public void setInformationSourcesCnt(int informationSourcesCnt) {
        this.informationSourcesCnt = informationSourcesCnt;
    }

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

package com.rongdu.cashloan.cl.domain.mozhang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by lu on 2018/9/27.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BlackInfoDetailBean {
    /**
     * mobile_name_in_blacklist : true
     * mobile_name_blacklist_updated_time : string
     * idcard_name_in_blacklist : true
     * idcard_name_blacklist_updated_time : string
     * black_types : string
     * blacklist_record : {"overdue_count":0,"overdue_amount":"string","overdue_status":"string"}
     */

    @JsonProperty("mobile_name_in_blacklist")
    private boolean mobileNameInBlacklist;
    @JsonProperty("mobile_name_blacklist_updated_time")
    private String mobileNameBlacklistUpdatedTime;
    @JsonProperty("idcard_name_in_blacklist")
    private boolean idcardNameInBlacklist;
    @JsonProperty("idcard_name_blacklist_updated_time")
    private String idcardNameBlacklistUpdatedTime;
    @JsonProperty("black_types")
    private String blackTypes;
    @JsonProperty("blacklist_record")
    private BlacklistRecordBean blacklistRecord;

    public boolean isMobileNameInBlacklist() {
        return mobileNameInBlacklist;
    }

    public void setMobileNameInBlacklist(boolean mobileNameInBlacklist) {
        this.mobileNameInBlacklist = mobileNameInBlacklist;
    }

    public String getMobileNameBlacklistUpdatedTime() {
        return mobileNameBlacklistUpdatedTime;
    }

    public void setMobileNameBlacklistUpdatedTime(String mobileNameBlacklistUpdatedTime) {
        this.mobileNameBlacklistUpdatedTime = mobileNameBlacklistUpdatedTime;
    }

    public boolean isIdcardNameInBlacklist() {
        return idcardNameInBlacklist;
    }

    public void setIdcardNameInBlacklist(boolean idcardNameInBlacklist) {
        this.idcardNameInBlacklist = idcardNameInBlacklist;
    }

    public String getIdcardNameBlacklistUpdatedTime() {
        return idcardNameBlacklistUpdatedTime;
    }

    public void setIdcardNameBlacklistUpdatedTime(String idcardNameBlacklistUpdatedTime) {
        this.idcardNameBlacklistUpdatedTime = idcardNameBlacklistUpdatedTime;
    }

    public String getBlackTypes() {
        return blackTypes;
    }

    public void setBlackTypes(String blackTypes) {
        this.blackTypes = blackTypes;
    }

    public BlacklistRecordBean getBlacklistRecord() {
        return blacklistRecord;
    }

    public void setBlacklistRecord(BlacklistRecordBean blacklistRecord) {
        this.blacklistRecord = blacklistRecord;
    }
}

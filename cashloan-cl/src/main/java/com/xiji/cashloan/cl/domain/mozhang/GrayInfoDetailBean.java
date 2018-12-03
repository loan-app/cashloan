package com.xiji.cashloan.cl.domain.mozhang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by lu on 2018/9/27.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GrayInfoDetailBean {
    /**
     * mobile_name_in_gray : true
     * mobile_name_gray_updated_time : string
     * idcard_name_in_gray : true
     * idcard_name_gray_updated_time : string
     * gray_types : string
     * graylist_record : {"overdue_count":0,"overdue_amount":"string","overdue_status":"string"}
     */

    @JsonProperty("mobile_name_in_gray")
    private boolean mobileNameInGray;
    @JsonProperty("mobile_name_gray_updated_time")
    private String mobileNameGrayUpdatedTime;
    @JsonProperty("idcard_name_in_gray")
    private boolean idcardNameInGray;
    @JsonProperty("idcard_name_gray_updated_time")
    private String idcardNameGrayUpdatedTime;
    @JsonProperty("gray_types")
    private String grayTypes;
    @JsonProperty("graylist_record")
    private GraylistRecordBean graylistRecord;

    public boolean isMobileNameInGray() {
        return mobileNameInGray;
    }

    public void setMobileNameInGray(boolean mobileNameInGray) {
        this.mobileNameInGray = mobileNameInGray;
    }

    public String getMobileNameGrayUpdatedTime() {
        return mobileNameGrayUpdatedTime;
    }

    public void setMobileNameGrayUpdatedTime(String mobileNameGrayUpdatedTime) {
        this.mobileNameGrayUpdatedTime = mobileNameGrayUpdatedTime;
    }

    public boolean isIdcardNameInGray() {
        return idcardNameInGray;
    }

    public void setIdcardNameInGray(boolean idcardNameInGray) {
        this.idcardNameInGray = idcardNameInGray;
    }

    public String getIdcardNameGrayUpdatedTime() {
        return idcardNameGrayUpdatedTime;
    }

    public void setIdcardNameGrayUpdatedTime(String idcardNameGrayUpdatedTime) {
        this.idcardNameGrayUpdatedTime = idcardNameGrayUpdatedTime;
    }

    public String getGrayTypes() {
        return grayTypes;
    }

    public void setGrayTypes(String grayTypes) {
        this.grayTypes = grayTypes;
    }

    public GraylistRecordBean getGraylistRecord() {
        return graylistRecord;
    }

    public void setGraylistRecord(GraylistRecordBean graylistRecord) {
        this.graylistRecord = graylistRecord;
    }
}

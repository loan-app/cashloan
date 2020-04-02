package com.xiji.cashloan.cl.domain.mozhang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author wnb
 * @date 2018/12/03
 * @version 1.0.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OtherMobilesBean {
    /**
     * latest_used_time : string
     * mobile : string
     * carrier : string
     * mobile_location : string
     * isblack : true
     */

    @JsonProperty("latest_used_time")
    private String latestUsedTime;
    @JsonProperty("mobile")
    private String mobile;
    @JsonProperty("carrier")
    private String carrier;
    @JsonProperty("mobile_location")
    private String mobileLocation;
    @JsonProperty("isblack")
    private boolean isblack;

    public String getLatestUsedTime() {
        return latestUsedTime;
    }

    public void setLatestUsedTime(String latestUsedTime) {
        this.latestUsedTime = latestUsedTime;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getMobileLocation() {
        return mobileLocation;
    }

    public void setMobileLocation(String mobileLocation) {
        this.mobileLocation = mobileLocation;
    }

    public boolean isIsblack() {
        return isblack;
    }

    public void setIsblack(boolean isblack) {
        this.isblack = isblack;
    }
}

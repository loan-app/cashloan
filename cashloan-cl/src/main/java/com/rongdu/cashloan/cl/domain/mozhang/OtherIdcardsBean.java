package com.rongdu.cashloan.cl.domain.mozhang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by lu on 2018/9/27.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OtherIdcardsBean {
    /**
     * latest_used_time : string
     * idcard : string
     * isblack : true
     */

    @JsonProperty("latest_used_time")
    private String latestUsedTime;
    @JsonProperty("idcard")
    private String idcard;
    @JsonProperty("isblack")
    private boolean isblack;

    public String getLatestUsedTime() {
        return latestUsedTime;
    }

    public void setLatestUsedTime(String latestUsedTime) {
        this.latestUsedTime = latestUsedTime;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public boolean isIsblack() {
        return isblack;
    }

    public void setIsblack(boolean isblack) {
        this.isblack = isblack;
    }
}

package com.rongdu.cashloan.cl.domain.mozhang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by lu on 2018/9/27.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OtherNamesBean {
    /**
     * latest_used_time : string
     * name : string
     */

    @JsonProperty("latest_used_time")
    private String latestUsedTime;
    @JsonProperty("name")
    private String name;

    public String getLatestUsedTime() {
        return latestUsedTime;
    }

    public void setLatestUsedTime(String latestUsedTime) {
        this.latestUsedTime = latestUsedTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

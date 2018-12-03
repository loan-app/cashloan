package com.rongdu.cashloan.cl.domain.mozhang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by lu on 2018/9/27.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InformationSourcesBean {
    /**
     * latest_used_time : string
     * org_type : string
     */

    @JsonProperty("latest_used_time")
    private String latestUsedTime;
    @JsonProperty("org_type")
    private String orgType;

    public String getLatestUsedTime() {
        return latestUsedTime;
    }

    public void setLatestUsedTime(String latestUsedTime) {
        this.latestUsedTime = latestUsedTime;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }
}

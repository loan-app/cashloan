package com.rongdu.cashloan.cl.domain.mozhang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by lu on 2018/9/27.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterInfoBean {
    /**
     * other_count : 0
     * org_count : 0
     * org_types : ["string"]
     */

    @JsonProperty("other_count")
    private int otherCount;
    @JsonProperty("org_count")
    private int orgCount;
    @JsonProperty("org_types")
    private List<String> orgTypes;

    public int getOtherCount() {
        return otherCount;
    }

    public void setOtherCount(int otherCount) {
        this.otherCount = otherCount;
    }

    public int getOrgCount() {
        return orgCount;
    }

    public void setOrgCount(int orgCount) {
        this.orgCount = orgCount;
    }

    public List<String> getOrgTypes() {
        return orgTypes;
    }

    public void setOrgTypes(List<String> orgTypes) {
        this.orgTypes = orgTypes;
    }
}

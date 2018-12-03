package com.xiji.cashloan.cl.domain.mozhang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by lu on 2018/9/27.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DishonestDetailInoBean {
    /**
     * court_name : string
     * area_name : string
     * case_code : string
     * publish_date : string
     * gist_id : string
     * duty : string
     * performance : string
     * disrupt_type_name : string
     */

    @JsonProperty("court_name")
    private String courtName;
    @JsonProperty("area_name")
    private String areaName;
    @JsonProperty("case_code")
    private String caseCode;
    @JsonProperty("publish_date")
    private String publishDate;
    @JsonProperty("gist_id")
    private String gistId;
    @JsonProperty("duty")
    private String duty;
    @JsonProperty("performance")
    private String performance;
    @JsonProperty("disrupt_type_name")
    private String disruptTypeName;

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCaseCode() {
        return caseCode;
    }

    public void setCaseCode(String caseCode) {
        this.caseCode = caseCode;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getGistId() {
        return gistId;
    }

    public void setGistId(String gistId) {
        this.gistId = gistId;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    public String getDisruptTypeName() {
        return disruptTypeName;
    }

    public void setDisruptTypeName(String disruptTypeName) {
        this.disruptTypeName = disruptTypeName;
    }
}

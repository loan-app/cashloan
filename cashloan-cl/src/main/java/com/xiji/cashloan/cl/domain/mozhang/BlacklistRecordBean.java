package com.xiji.cashloan.cl.domain.mozhang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author wnb
 * @date 2018/12/03
 * @version 1.0.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BlacklistRecordBean {
    /**
     * overdue_count : 0
     * overdue_amount : string
     * overdue_status : string
     */

    @JsonProperty("overdue_count")
    private int overdueCount;
    @JsonProperty("overdue_amount")
    private String overdueAmount;
    @JsonProperty("overdue_status")
    private String overdueStatus;

    public int getOverdueCount() {
        return overdueCount;
    }

    public void setOverdueCount(int overdueCount) {
        this.overdueCount = overdueCount;
    }

    public String getOverdueAmount() {
        return overdueAmount;
    }

    public void setOverdueAmount(String overdueAmount) {
        this.overdueAmount = overdueAmount;
    }

    public String getOverdueStatus() {
        return overdueStatus;
    }

    public void setOverdueStatus(String overdueStatus) {
        this.overdueStatus = overdueStatus;
    }
}

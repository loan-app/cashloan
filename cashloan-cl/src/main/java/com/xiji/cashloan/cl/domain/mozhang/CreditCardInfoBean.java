package com.xiji.cashloan.cl.domain.mozhang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author wnb
 * @date 2018/12/03
 * @version 1.0.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditCardInfoBean {
    /**
     * update_date : string
     * card_amount : 0
     * total_credit_limit : string
     * total_credit_available : string
     * max_credit_limit : string
     * overdue_times : 0
     * overdue_months : int
     */

    @JsonProperty("update_date")
    private String updateDate;
    @JsonProperty("card_amount")
    private int cardAmount;
    @JsonProperty("total_credit_limit")
    private String totalCreditLimit;
    @JsonProperty("total_credit_available")
    private String totalCreditAvailable;
    @JsonProperty("max_credit_limit")
    private String maxCreditLimit;
    @JsonProperty("overdue_times")
    private int overdueTimes;
    @JsonProperty("overdue_months")
    private String overdueMonths;

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public int getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(int cardAmount) {
        this.cardAmount = cardAmount;
    }

    public String getTotalCreditLimit() {
        return totalCreditLimit;
    }

    public void setTotalCreditLimit(String totalCreditLimit) {
        this.totalCreditLimit = totalCreditLimit;
    }

    public String getTotalCreditAvailable() {
        return totalCreditAvailable;
    }

    public void setTotalCreditAvailable(String totalCreditAvailable) {
        this.totalCreditAvailable = totalCreditAvailable;
    }

    public String getMaxCreditLimit() {
        return maxCreditLimit;
    }

    public void setMaxCreditLimit(String maxCreditLimit) {
        this.maxCreditLimit = maxCreditLimit;
    }

    public int getOverdueTimes() {
        return overdueTimes;
    }

    public void setOverdueTimes(int overdueTimes) {
        this.overdueTimes = overdueTimes;
    }

    public String getOverdueMonths() {
        return overdueMonths;
    }

    public void setOverdueMonths(String overdueMonths) {
        this.overdueMonths = overdueMonths;
    }
}

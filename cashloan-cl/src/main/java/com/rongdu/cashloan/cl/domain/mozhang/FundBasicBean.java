package com.rongdu.cashloan.cl.domain.mozhang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by lu on 2018/9/27.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FundBasicBean {
    /**
     * last_pay_date : string
     * update_date : string
     * open_date : string
     * open_location : string
     * account_status : string
     * balance : string
     * base_amount : string
     * monthly_income : string
     */

    @JsonProperty("last_pay_date")
    private String lastPayDate;
    @JsonProperty("update_date")
    private String updateDate;
    @JsonProperty("open_date")
    private String openDate;
    @JsonProperty("open_location")
    private String openLocation;
    @JsonProperty("account_status")
    private String accountStatus;
    @JsonProperty("balance")
    private String balance;
    @JsonProperty("base_amount")
    private String baseAmount;
    @JsonProperty("monthly_income")
    private String monthlyIncome;

    public String getLastPayDate() {
        return lastPayDate;
    }

    public void setLastPayDate(String lastPayDate) {
        this.lastPayDate = lastPayDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public String getOpenLocation() {
        return openLocation;
    }

    public void setOpenLocation(String openLocation) {
        this.openLocation = openLocation;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(String baseAmount) {
        this.baseAmount = baseAmount;
    }

    public String getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(String monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }
}

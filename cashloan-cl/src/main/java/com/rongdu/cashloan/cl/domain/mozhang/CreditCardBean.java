package com.rongdu.cashloan.cl.domain.mozhang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by lu on 2018/9/27.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditCardBean {
    /**
     * update_date : string
     * bank_nums : 0
     * card_amount : 0
     * total_credit_limit : string
     * max_credit_limit : string
     * overdue_card : 0
     * bill_nums : 0
     * credit_overdue_item_12m : {"overdue_times":0,"overdue_months":0}
     * credit_overdue_item_6m : {"overdue_times":0,"overdue_months":0}
     * credit_overdue_item_3m : {"overdue_times":0,"overdue_months":0}
     * last_overdue_date : string
     * max_overdue_money : string
     * continue_overdue_times : 0
     */

    @JsonProperty("update_date")
    private String updateDate;
    @JsonProperty("bank_nums")
    private int bankNums;
    @JsonProperty("card_amount")
    private int cardAmount;
    @JsonProperty("total_credit_limit")
    private String totalCreditLimit;
    @JsonProperty("max_credit_limit")
    private String maxCreditLimit;
    @JsonProperty("overdue_card")
    private int overdueCard;
    @JsonProperty("bill_nums")
    private int billNums;
    @JsonProperty("credit_overdue_item_12m")
    private CreditOverdueItemBean creditOverdueItem12m;
    @JsonProperty("credit_overdue_item_6m")
    private CreditOverdueItemBean creditOverdueItem6m;
    @JsonProperty("credit_overdue_item_3m")
    private CreditOverdueItemBean creditOverdueItem3m;
    @JsonProperty("last_overdue_date")
    private String lastOverdueDate;
    @JsonProperty("max_overdue_money")
    private String maxOverdueMoney;
    @JsonProperty("continue_overdue_times")
    private int continueOverdueTimes;

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public int getBankNums() {
        return bankNums;
    }

    public void setBankNums(int bankNums) {
        this.bankNums = bankNums;
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

    public String getMaxCreditLimit() {
        return maxCreditLimit;
    }

    public void setMaxCreditLimit(String maxCreditLimit) {
        this.maxCreditLimit = maxCreditLimit;
    }

    public int getOverdueCard() {
        return overdueCard;
    }

    public void setOverdueCard(int overdueCard) {
        this.overdueCard = overdueCard;
    }

    public int getBillNums() {
        return billNums;
    }

    public void setBillNums(int billNums) {
        this.billNums = billNums;
    }

    public CreditOverdueItemBean getCreditOverdueItem12m() {
        return creditOverdueItem12m;
    }

    public void setCreditOverdueItem12m(CreditOverdueItemBean creditOverdueItem12m) {
        this.creditOverdueItem12m = creditOverdueItem12m;
    }

    public CreditOverdueItemBean getCreditOverdueItem6m() {
        return creditOverdueItem6m;
    }

    public void setCreditOverdueItem6m(CreditOverdueItemBean creditOverdueItem6m) {
        this.creditOverdueItem6m = creditOverdueItem6m;
    }

    public CreditOverdueItemBean getCreditOverdueItem3m() {
        return creditOverdueItem3m;
    }

    public void setCreditOverdueItem3m(CreditOverdueItemBean creditOverdueItem3m) {
        this.creditOverdueItem3m = creditOverdueItem3m;
    }

    public String getLastOverdueDate() {
        return lastOverdueDate;
    }

    public void setLastOverdueDate(String lastOverdueDate) {
        this.lastOverdueDate = lastOverdueDate;
    }

    public String getMaxOverdueMoney() {
        return maxOverdueMoney;
    }

    public void setMaxOverdueMoney(String maxOverdueMoney) {
        this.maxOverdueMoney = maxOverdueMoney;
    }

    public int getContinueOverdueTimes() {
        return continueOverdueTimes;
    }

    public void setContinueOverdueTimes(int continueOverdueTimes) {
        this.continueOverdueTimes = continueOverdueTimes;
    }
}

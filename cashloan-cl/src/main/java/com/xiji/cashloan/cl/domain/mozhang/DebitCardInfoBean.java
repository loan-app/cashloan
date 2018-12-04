package com.xiji.cashloan.cl.domain.mozhang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author wnb
 * @date 2018/12/03
 * @version 1.0.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DebitCardInfoBean {
    /**
     * update_date : string
     * card_amount : 0
     * balance : string
     * total_income : string
     * total_salary_income : string
     * total_loan_income : string
     * total_outcome : string
     * total_consume_outcome : string
     * total_loan_outcome : string
     */

    @JsonProperty("update_date")
    private String updateDate;
    @JsonProperty("card_amount")
    private int cardAmount;
    @JsonProperty("balance")
    private String balance;
    @JsonProperty("total_income")
    private String totalIncome;
    @JsonProperty("total_salary_income")
    private String totalSalaryIncome;
    @JsonProperty("total_loan_income")
    private String totalLoanIncome;
    @JsonProperty("total_outcome")
    private String totalOutcome;
    @JsonProperty("total_consume_outcome")
    private String totalConsumeOutcome;
    @JsonProperty("total_loan_outcome")
    private String totalLoanOutcome;

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

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(String totalIncome) {
        this.totalIncome = totalIncome;
    }

    public String getTotalSalaryIncome() {
        return totalSalaryIncome;
    }

    public void setTotalSalaryIncome(String totalSalaryIncome) {
        this.totalSalaryIncome = totalSalaryIncome;
    }

    public String getTotalLoanIncome() {
        return totalLoanIncome;
    }

    public void setTotalLoanIncome(String totalLoanIncome) {
        this.totalLoanIncome = totalLoanIncome;
    }

    public String getTotalOutcome() {
        return totalOutcome;
    }

    public void setTotalOutcome(String totalOutcome) {
        this.totalOutcome = totalOutcome;
    }

    public String getTotalConsumeOutcome() {
        return totalConsumeOutcome;
    }

    public void setTotalConsumeOutcome(String totalConsumeOutcome) {
        this.totalConsumeOutcome = totalConsumeOutcome;
    }

    public String getTotalLoanOutcome() {
        return totalLoanOutcome;
    }

    public void setTotalLoanOutcome(String totalLoanOutcome) {
        this.totalLoanOutcome = totalLoanOutcome;
    }
}

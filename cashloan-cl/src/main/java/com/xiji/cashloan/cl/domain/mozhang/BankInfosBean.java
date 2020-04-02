package com.xiji.cashloan.cl.domain.mozhang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author wnb
 * @date 2018/12/03
 * @version 1.0.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BankInfosBean {
    /**
     * debit_card_info : {"update_date":"string","card_amount":0,"balance":"string","total_income":"string","total_salary_income":"string","total_loan_income":"string","total_outcome":"string","total_consume_outcome":"string","total_loan_outcome":"string"}
     * credit_card_info : {"update_date":"string","card_amount":0,"total_credit_limit":"string","total_credit_available":"string","max_credit_limit":"string","overdue_times":0,"overdue_months":"int"}
     */

    @JsonProperty("debit_card_info")
    private DebitCardInfoBean debitCardInfo;
    @JsonProperty("credit_card_info")
    private CreditCardInfoBean creditCardInfo;

    public DebitCardInfoBean getDebitCardInfo() {
        return debitCardInfo;
    }

    public void setDebitCardInfo(DebitCardInfoBean debitCardInfo) {
        this.debitCardInfo = debitCardInfo;
    }

    public CreditCardInfoBean getCreditCardInfo() {
        return creditCardInfo;
    }

    public void setCreditCardInfo(CreditCardInfoBean creditCardInfo) {
        this.creditCardInfo = creditCardInfo;
    }
}

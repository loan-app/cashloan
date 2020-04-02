package com.xiji.cashloan.cl.model.xindedata;

import com.xiji.cashloan.cl.domain.BlacklistXindeData;

/**
 * @Auther: king
 * @Date: 2018/12/19 17:01
 * @Description:
 */
public class BlacklistXindeDataDto extends BlacklistXindeData {
    private String currentOverdueAmountStr;
    private String isLastLoanRefusedStr;

    public String getCurrentOverdueAmountStr() {
        return currentOverdueAmountStr;
    }

    public void setCurrentOverdueAmountStr(String currentOverdueAmountStr) {
        this.currentOverdueAmountStr = currentOverdueAmountStr;
    }

    public String getIsLastLoanRefusedStr() {
        return isLastLoanRefusedStr;
    }

    public void setIsLastLoanRefusedStr(String isLastLoanRefusedStr) {
        this.isLastLoanRefusedStr = isLastLoanRefusedStr;
    }
}

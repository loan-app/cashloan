package com.xiji.cashloan.cl.model.pay.common;

import com.xiji.cashloan.cl.domain.BankCard;
import com.xiji.cashloan.core.common.util.StringUtil;

/**
 * @Auther: king
 * @Date: 2019/2/1 15:25
 * @Description:
 */
public class PayCommonHelper {
    public static boolean isCurrentPayCompany(BankCard card) {
        return StringUtil.equalsIgnoreCase(card.getAgreeCompany(), PayCommonUtil.payCompany(card.getUserId()));
    }

    public static boolean isEmpty(BankCard bankCard) {
        return bankCard == null;
    }
}

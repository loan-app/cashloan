package com.xiji.cashloan.core.common.context;

import com.xiji.cashloan.core.common.util.StringUtil;
import com.xiji.cashloan.core.domain.BankCardBin;
import com.xiji.cashloan.core.service.BankCardBinService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.map.HashedMap;
import tool.util.BeanUtil;

/**
 * @Auther: king
 * @Date: 2019/1/25 14:24
 * @Description:
 */
public class BankCardBinUtil {
    public static Map<String, BankCardBin> configMap;
    private static final BankCardBin defaultBin = new BankCardBin();

    public static BankCardBin getBankCardBin(String cardNo) {
        String prefix = StringUtil.substring(cardNo, 0, 6);
        if (configMap.containsKey(prefix)) {
            return configMap.get(prefix);
        }
        prefix = StringUtil.substring(cardNo, 0, 9);
        if (configMap.containsKey(prefix)) {
            return configMap.get(prefix);
        }
        prefix = StringUtil.substring(cardNo, 0, 7);
        if (configMap.containsKey(prefix)) {
            return configMap.get(prefix);
        }
        prefix = StringUtil.substring(cardNo, 0, 8);
        if (configMap.containsKey(prefix)) {
            return configMap.get(prefix);
        }
        prefix = StringUtil.substring(cardNo, 0, 10);
        if (configMap.containsKey(prefix)) {
            return configMap.get(prefix);
        }
        prefix = StringUtil.substring(cardNo, 0, 11);
        if (configMap.containsKey(prefix)) {
            return configMap.get(prefix);
        }
        prefix = StringUtil.substring(cardNo, 0, 12);
        if (configMap.containsKey(prefix)) {
            return configMap.get(prefix);
        }
        prefix = StringUtil.substring(cardNo, 0, 3);
        if (configMap.containsKey(prefix)) {
            return configMap.get(prefix);
        }
        prefix = StringUtil.substring(cardNo, 0, 4);
        if (configMap.containsKey(prefix)) {
            return configMap.get(prefix);
        }
        prefix = StringUtil.substring(cardNo, 0, 5);
        if (configMap.containsKey(prefix)) {
            return configMap.get(prefix);
        }
        return defaultBin;
    }

    public static String getBankCode(String cardNo) {
        return getBankCardBin(cardNo).getBankCode();
    }

    public static void initCardBin() {
        BankCardBinService bankCardBinService = (BankCardBinService) BeanUtil.getBean("bankCardBinService");
        List<BankCardBin> list = bankCardBinService.listSelective(new HashedMap());
        Map<String, BankCardBin> tempMap = new HashMap<String, BankCardBin>();
        for (BankCardBin bin : list) {
            if (bin != null) {
                tempMap.put(bin.getCardBin(), bin);
            }
        }
        configMap = new HashMap<String, BankCardBin>();
        configMap.putAll(tempMap);
    }
}

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
public class BankCandBinUtil {
    public static Map<String, String> configMap;

    public static String getBankCode(String cardNo) {
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
        return "";
    }

    public static void initCardBin() {
        BankCardBinService bankCardBinService = (BankCardBinService) BeanUtil.getBean("bankCardBinService");
        List<BankCardBin> list = bankCardBinService.listSelective(new HashedMap());
        Map<String, Object> tempMap = new HashMap<String, Object>();
        for (BankCardBin bin : list) {
            if (bin != null) {
                tempMap.put(bin.getCardBin(), bin.getBankCode());
            }
        }
        Global.configMap = new HashMap<String, Object>();
        Global.configMap.putAll(tempMap);
    }
}

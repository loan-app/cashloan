package com.xiji.cashloan.cl.model.paipaixin;

import com.alibaba.fastjson.JSONObject;
import com.xiji.cashloan.cl.domain.BlacklistCommonData;
import com.xiji.cashloan.core.common.util.StringUtil;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: king
 * @Date: 2018/12/21 11:55
 * @Description:
 */
public class PpxDataPaser {

    private static Map<String, String> moneyMap = new HashMap<>();
    private static Map<String, String> dateMap = new HashMap<>();
    static {
        moneyMap.put("1","1~1000");
        moneyMap.put("2","1000~2000");
        moneyMap.put("3","2000~3000");
        moneyMap.put("4","3000~4000");
        moneyMap.put("5","4000~6000");
        moneyMap.put("6","6000~8000");
        moneyMap.put("7","8000~10000");
        moneyMap.put("8","10000~30000");
        moneyMap.put("9","30000~50000");
        moneyMap.put("10","50000~100000");
        moneyMap.put("11","≥100000");
        moneyMap.put("","");

        dateMap.put("1","1~30天");
        dateMap.put("2","31~60天");
        dateMap.put("3","61~90天");
        dateMap.put("4","91~120天");
        dateMap.put("5","121~120天");
        dateMap.put("6","151~180天");
        dateMap.put("7",">180天");
    }

    public static PpxBlacklistDetailDto parse(BlacklistCommonData commonData) {
        PpxBlacklistDetailDto detailDto = new PpxBlacklistDetailDto();
        if (commonData == null || StringUtil.isEmpty(commonData.getDataMsg())) {
            return detailDto;
        }
        JSONObject resJson = JSONObject.parseObject(commonData.getDataMsg());
        if (resJson != null) {
            JSONObject blacksum = resJson.getJSONObject("blackSummary");
            if (blacksum != null) {
                JSONObject hkxw = blacksum.getJSONObject("HKXW");
                if (hkxw != null) {
                    detailDto.setHk001(hkxw.getString("HK001"));
                    detailDto.setHk002(hkxw.getString("HK002"));
                    detailDto.setHk003(hkxw.getString("HK003"));
                    detailDto.setHk004(moneyMap.get(StringUtil.trimToEmpty(hkxw.getString("HK004"))));
                    detailDto.setHk005(dateMap.get(StringUtil.trimToEmpty(hkxw.getString("HK005"))));
                    detailDto.setHk006(moneyMap.get(StringUtil.trimToEmpty(hkxw.getString("HK006"))));
                    detailDto.setHk007(dateMap.get(StringUtil.trimToEmpty(hkxw.getString("HK007"))));

                }

                JSONObject lsqz = blacksum.getJSONObject("LSQZ");
                if (lsqz != null) {
                    detailDto.setQz001(lsqz.getString("QZ001"));
                    detailDto.setQz002(lsqz.getString("QZ002"));
                    detailDto.setQz003(lsqz.getString("QZ003"));
                }

                JSONObject zffm = blacksum.getJSONObject("ZFFM");
                if (zffm != null) {
                    detailDto.setFm001(zffm.getString("FM001"));
                    detailDto.setFm002(zffm.getString("FM002"));
                    detailDto.setFm003(zffm.getString("FM003"));
                }
            }
            String isBlack = resJson.getString("isBlack");
            if (StringUtil.equals("1", isBlack)) {
                detailDto.setIsBlack("是");
            }
            String isAlert = resJson.getString("isAlert");
            if (StringUtil.equals("1", isAlert)) {
                detailDto.setIsAlert("是");
            }
        }
        return detailDto;
    }
}

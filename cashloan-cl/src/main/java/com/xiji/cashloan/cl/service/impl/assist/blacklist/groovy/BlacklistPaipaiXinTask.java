package com.xiji.cashloan.cl.service.impl.assist.blacklist.groovy;

import com.alibaba.fastjson.JSONObject;
import com.xiji.cashloan.cl.domain.BlacklistCommonData;
import com.xiji.cashloan.cl.model.paipaixin.VerifyUtil;
import com.xiji.cashloan.cl.service.BlacklistCommonDataService;
import com.xiji.cashloan.cl.service.impl.assist.blacklist.BlacklistProcess;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.util.HttpsUtil;
import com.xiji.cashloan.core.common.util.StringUtil;
import com.xiji.cashloan.core.domain.Borrow;
import com.xiji.cashloan.core.domain.UserBaseInfo;
import com.xiji.cashloan.core.service.UserBaseInfoService;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tool.util.BeanUtil;

/**
 * @Auther: king
 * @Date: 2018/12/20 14:52
 * @Description:
 */
public class BlacklistPaipaiXinTask extends BlacklistProcess {
    private static final Logger logger = LoggerFactory.getLogger(BlacklistPaipaiXinTask.class);

    @Override
    public void doTask(Borrow borrow,long start) throws Exception {
        BlacklistCommonDataService commonDataService = (BlacklistCommonDataService) BeanUtil.getBean("blacklistCommonDataService");
        UserBaseInfoService userBaseInfoService = (UserBaseInfoService) BeanUtil.getBean("userBaseInfoService");
        UserBaseInfo baseInfo = userBaseInfoService.findByUserId(borrow.getUserId());
        String host = Global.getValue("paipaixin_blacklist_url");
        final String secret = Global.getValue("paipaixin_data_appSecret");
        final String appId = Global.getValue("paipaixin_data_appId");
        String method = Global.getValue("paipaixin_blacklist_method");

        Map<String, String> param = new HashMap<>();
        param.put("method", method);
        param.put("name", covertName(baseInfo.getRealName()));
        param.put("idNumber", baseInfo.getIdNo());
        param.put("mobile", baseInfo.getPhone());

        Map<String, String> headerMap = VerifyUtil.signinMap(method, appId, secret);
        param.putAll(headerMap);
        String resp = HttpsUtil.postClient(host, param);
        logger.info("borrowId" + borrow.getId() + "调用拍拍信接口，耗时" + (System.currentTimeMillis() - start) + " ms");
        if (StringUtil.isNotEmpty(resp)) {
            if (!HttpsUtil.error(resp)) {
                JSONObject object = JSONObject.parseObject(resp);
                if (StringUtil.equals("api.resp.sys#success",object.getString("resp_code"))) {
                    JSONObject respBody = object.getJSONObject("resp_body");
                    if (StringUtil.equals("success",respBody.getString("result"))){
                        JSONObject msg = respBody.getJSONObject("msg");
                        if (msg != null) {
                            int queryStatus = NumberUtils.toInt(msg.getString("queryStatus"),2);
                            if (queryStatus == 1) {
                                JSONObject dataJson = msg.getJSONObject("data");
                                if (dataJson != null) {
                                    int blackint = NumberUtils.toInt(dataJson.getString("isBlack"),2);
                                    BlacklistCommonData commonData = new BlacklistCommonData();
                                    Date date = new Date();
                                    commonData.setLastModifyTime(date);
                                    commonData.setCreateTime(date);
                                    commonData.setUserId(baseInfo.getUserId());
                                    commonData.setIdCard(baseInfo.getIdNo());
                                    commonData.setPhone(baseInfo.getPhone());
                                    if (blackint == 1) {
                                        commonData.setBlackType(1);
                                    }
                                    commonData.setName(baseInfo.getRealName());
                                    commonData.setBorrowId(borrow.getId());
                                    commonData.setSource("paipaixin");
                                    commonData.setDataMsg(msg.getString("data"));

                                    commonDataService.saveShard(commonData);
                                }
                            }
                        }
                    }
                }else {
                    logger.error("调用返回状态码：" + object.getString("resp_code"));
                }
            }
        }
    }

    private String covertName(String name)throws Exception {
        return URLEncoder.encode(name,"UTF-8");
    }
}

package com.xiji.cashloan.cl.util;

import com.xiji.cashloan.cl.service.impl.assist.blacklist.BlacklistProcess;
import groovy.lang.GroovyClassLoader;
import java.io.IOException;

public class GroovyUtil {

    public static <T> T createGroovy(String value) throws Exception{
        T dataParseService = null;
        ClassLoader cl = new GroovyUtil().getClass().getClassLoader();
        GroovyClassLoader groovyCl = new GroovyClassLoader(cl);
        try{
            Class groovyClass = groovyCl.parseClass(value);
            dataParseService = (T)groovyClass.newInstance();
        }catch(Exception e){
            throw e;
        }finally{
            try {
                groovyCl.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dataParseService;
    }
    
    
    public static void main(String[] args) throws Exception{
        String value = "package com.xiji.cashloan.cl.service.impl.assist.blacklist.groovy;\n"
            + "\n"
            + "import com.alibaba.fastjson.JSONObject;\n"
            + "import com.xiji.cashloan.cl.domain.BlacklistCommonData;\n"
            + "import com.xiji.cashloan.cl.model.paipaixin.VerifyUtil;\n"
            + "import com.xiji.cashloan.cl.service.BlacklistCommonDataService;\n"
            + "import com.xiji.cashloan.cl.service.impl.assist.blacklist.BlacklistProcess;\n"
            + "import com.xiji.cashloan.core.common.context.Global;\n"
            + "import com.xiji.cashloan.core.common.util.HttpsUtil;\n"
            + "import com.xiji.cashloan.core.common.util.StringUtil;\n"
            + "import com.xiji.cashloan.core.domain.Borrow;\n"
            + "import com.xiji.cashloan.core.domain.UserBaseInfo;\n"
            + "import com.xiji.cashloan.core.service.UserBaseInfoService;\n"
            + "import java.net.URLEncoder;\n"
            + "import java.util.Date;\n"
            + "import java.util.HashMap;\n"
            + "import java.util.Map;\n"
            + "import org.apache.commons.lang3.math.NumberUtils;\n"
            + "import org.slf4j.Logger;\n"
            + "import org.slf4j.LoggerFactory;\n"
            + "import tool.util.BeanUtil;\n"
            + "\n"
            + "/**\n"
            + " * @Auther: king\n"
            + " * @Date: 2018/12/20 14:52\n"
            + " * @Description:\n"
            + " */\n"
            + "public class BlacklistPaipaiXinTask extends BlacklistProcess {\n"
            + "    private static final Logger logger = LoggerFactory.getLogger(BlacklistPaipaiXinTask.class);\n"
            + "\n"
            + "    @Override\n"
            + "    public void doTask(Borrow borrow) throws Exception {\n"
            + "        BlacklistCommonDataService commonDataService = (BlacklistCommonDataService) BeanUtil.getBean(\"blacklistCommonDataService\");\n"
            + "        UserBaseInfoService userBaseInfoService = (UserBaseInfoService) BeanUtil.getBean(\"userBaseInfoService\");\n"
            + "        UserBaseInfo baseInfo = userBaseInfoService.findByUserId(borrow.getUserId());\n"
            + "        String host = Global.getValue(\"paipaixin_blacklist_url\");\n"
            + "        final String secret = Global.getValue(\"paipaixin_data_appSecret\");\n"
            + "        final String appId = Global.getValue(\"paipaixin_data_appId\");\n"
            + "        String method = Global.getValue(\"paipaixin_blacklist_method\");\n"
            + "\n"
            + "        Map<String, String> param = new HashMap<>();\n"
            + "        param.put(\"method\", method);\n"
            + "        param.put(\"name\", covertName(baseInfo.getRealName()));\n"
            + "        param.put(\"idNumber\", baseInfo.getIdNo());\n"
            + "        param.put(\"mobile\", baseInfo.getPhone());\n"
            + "\n"
            + "        Map<String, String> headerMap = VerifyUtil.signinMap(method, appId, secret);\n"
            + "        param.putAll(headerMap);\n"
            + "        String resp = HttpsUtil.postClient(host, param);\n"
            + "        if (StringUtil.isNotEmpty(resp)) {\n"
            + "            if (!HttpsUtil.error(resp)) {\n"
            + "                JSONObject object = JSONObject.parseObject(resp);\n"
            + "                if (StringUtil.equals(\"api.resp.sys#success\",object.getString(\"resp_code\"))) {\n"
            + "                    JSONObject respBody = object.getJSONObject(\"resp_body\");\n"
            + "                    if (StringUtil.equals(\"success\",respBody.getString(\"result\"))){\n"
            + "                        JSONObject msg = respBody.getJSONObject(\"msg\");\n"
            + "                        if (msg != null) {\n"
            + "                            int queryStatus = NumberUtils.toInt(msg.getString(\"queryStatus\"),2);\n"
            + "                            if (queryStatus == 1) {\n"
            + "                                JSONObject dataJson = msg.getJSONObject(\"data\");\n"
            + "                                if (dataJson != null) {\n"
            + "                                    int blackint = NumberUtils.toInt(msg.getString(\"isBlack\"),2);\n"
            + "                                    BlacklistCommonData commonData = new BlacklistCommonData();\n"
            + "                                    Date date = new Date();\n"
            + "                                    commonData.setLastModifyTime(date);\n"
            + "                                    commonData.setCreateTime(date);\n"
            + "                                    commonData.setUserId(baseInfo.getUserId());\n"
            + "                                    commonData.setIdCard(baseInfo.getIdNo());\n"
            + "                                    commonData.setPhone(baseInfo.getPhone());\n"
            + "                                    if (blackint == 1) {\n"
            + "                                        commonData.setBlackType(1);\n"
            + "                                    }\n"
            + "                                    commonData.setName(baseInfo.getRealName());\n"
            + "                                    commonData.setBorrowId(borrow.getId());\n"
            + "                                    commonData.setSource(\"paipaixin\");\n"
            + "                                    commonData.setDataMsg(msg.getString(\"data\"));\n"
            + "                                    commonDataService.saveShard(commonData);\n"
            + "                                }\n"
            + "                            }\n"
            + "                        }\n"
            + "                    }\n"
            + "                }else {\n"
            + "                    logger.error(\"调用返回状态码：\" + object.getString(\"resp_code\"));\n"
            + "                }\n"
            + "            }\n"
            + "        }\n"
            + "    }\n"
            + "\n"
            + "    private String covertName(String name)throws Exception {\n"
            + "        return URLEncoder.encode(name,\"UTF-8\");\n"
            + "    }\n"
            + "}\n";
        BlacklistProcess task = GroovyUtil.createGroovy(value);
        if (task != null) {
            task.setVersion(3);
            System.out.println(task.getVersion());
        }
    }
}

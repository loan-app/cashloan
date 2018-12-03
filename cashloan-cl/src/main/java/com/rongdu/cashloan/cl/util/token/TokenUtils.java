package com.rongdu.cashloan.cl.util.token;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rongdu.cashloan.cl.common.DsConstant;
import com.rongdu.cashloan.cl.model.dsdata.TokenCreditRequest;
import com.rongdu.cashloan.core.common.context.Global;
import credit.Header;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.content.StringBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Auther: king
 * @Date: 2018/11/15 14:13
 * @Description:
 */
public class TokenUtils {
    private static final Logger logger = LoggerFactory.getLogger(TokenUtils.class);
    private static LRU<String, TokenItem> CACHE = new LRU<String, TokenItem>(
        16, 0.75f, true,16);

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        long timestamp = System.currentTimeMillis();
        Header header = new Header("690abc5009eda69f786c380cef51336a", timestamp);
        String securityKey = "ea108d5b1dc792680172d0e925a69470598ed54d";
        TokenCreditRequest request = new TokenCreditRequest("https://api.dsdatas.com/credit/api/token", header,
            securityKey);
        String result = null;
        try {
            result = request.request();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result);
        System.out.printf(tool.util.DateUtil.dateStr2(new Date(getCurrentTime())));
    }

    public static TokenItem load() {
        final String APIKEY = Global.getValue("apikey");
        final String SECRETKEY = Global.getValue("secretkey");
        final String APIHOST = Global.getValue("token_apihost");//地址
        Header header = new Header(APIKEY, getCurrentTime());
        TokenCreditRequest request = new TokenCreditRequest(APIHOST, header,SECRETKEY);
        String result = null;
        try {
            result = request.request();
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new RuntimeException("调外部接口获取token失败");
        }
        JSONObject object = JSON.parseObject(result);
        if (DsConstant.DS_DATA_CODE_SUCCESS == object.getIntValue(DsConstant.DS_CODE_KEY)){
            JSONObject data = JSON.parseObject(object.getString("data"));
            return new TokenItem(data.getString("token"),data.getLongValue("expire"));
        }else {
            throw new RuntimeException("调外部接口获取token失败："+result);
        }
    }

    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public static StringBody getCurrentTimeBody() throws Exception {
        ContentType contentType = ContentType.create("application/text", "utf-8");
        return new StringBody(String.valueOf(System.currentTimeMillis()), contentType);
    }

    public static String TokenKey(long current) {
        return tool.util.DateUtil.dateStr2(new Date(current));
    }

    public static TokenItem getToken() {
        long currentTime = getCurrentTime();
        String tokenKey = TokenKey(currentTime);
        TokenItem item = CACHE.get(tokenKey);
        if (item != null) {
            if (item.getExpire() > currentTime) {
                return item;
            }
        }
        try {
            lock.lock();
            item = CACHE.get(tokenKey);

            if (item != null && item .getExpire() > currentTime){
                return item;
            }

            item = load();
            CACHE.put(tokenKey,item);
        }finally {
            lock.unlock();
        }
        return  item;
    }
}

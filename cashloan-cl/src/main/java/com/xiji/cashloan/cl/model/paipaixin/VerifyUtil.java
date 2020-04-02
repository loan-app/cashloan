package com.xiji.cashloan.cl.model.paipaixin;

import java.util.HashMap;
import java.util.Map;

public class VerifyUtil {
	/**
	 * 生成签名验签
	 * @param appkey
	 * @param appkey
	 * @param appSecret
	 * @return
	 */
	public static Map<String,String> signinMap(String method, String appkey, String appSecret){
		String timestamp = System.currentTimeMillis() + "";
		String sign_method = DigestUtil.SHA;
		Map<String,String> map = new HashMap<String,String>();
		map.put("appkey", appkey);
		map.put("method", method);
		map.put("sign_method", sign_method);
		map.put("timestamp", timestamp);
		String sign = null;		
		if(sign_method.toUpperCase().equals(DigestUtil.MD5)){
			 sign = DigestUtil.digestMD5(map, appSecret);
		}else{			
			 sign = DigestUtil.digestSHA(map, appSecret);
		}
		map.put("sign", sign);
		return map;
	}
}

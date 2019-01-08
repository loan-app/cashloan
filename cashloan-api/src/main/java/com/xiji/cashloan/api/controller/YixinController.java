package com.xiji.cashloan.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiji.cashloan.cl.model.YixinShareModel;
import com.xiji.cashloan.cl.service.ClBorrowService;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.util.StringUtil;
import com.xiji.cashloan.core.domain.UserBaseInfo;
import com.xiji.cashloan.core.service.UserBaseInfoService;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 宜信风险评估
 * Created by szb on 18/12/25.
 */
@Controller
@Scope("prototype")
public class YixinController {

    @Resource
    private UserBaseInfoService userBaseInfoService;
    @Resource
    private ClBorrowService clBorrowService;

    public static final Logger logger = LoggerFactory.getLogger(YixinController.class);

    private static final String RC4 = "RC4";
    private static final String UTF8 = "UTF-8";

    @RequestMapping(value = "/api/yixin/riskShareData.htm")
    public void rishShareData(HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        String params = request.getParameter("params");
        String strParam = StringUtil.EMPTY;
        if (StringUtil.isNotBlank(params)) {
            logger.debug("接到加密的数据:" + params);
            strParam = decrypt(params, Global.getValue("yixin_sign"));
            logger.debug("解密后的数据:" + strParam);
        }
        JSONObject returnJson = new JSONObject();
        JSONObject dataJson = new JSONObject();
        if (StringUtil.isBlank(strParam)) {
            returnJson.put("success", Boolean.FALSE);
        } else {
            JSONObject json = JSONObject.parseObject(strParam);
            String idNo = json.getString("id_no");
            String name = json.getString("name");
            Map<String, Object> param = new HashMap<>();
            param.put("realName", name);
            param.put("idNo", idNo);
            UserBaseInfo user = userBaseInfoService.findSelective(param);
            if (user != null) {
                List<YixinShareModel> yixinShareModels = clBorrowService.queryDataForYixin(user.getId(), idNo, name);
                JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(yixinShareModels));
                dataJson.put("loanRecords", jsonArray);
                dataJson.put("riskResults", new JSONArray());
            }
            returnJson.put("success", Boolean.TRUE);
            logger.info("返回给宜信加密前数据:" + dataJson.toJSONString());
            String strData = encrypt(dataJson.toJSONString(), Global.getValue("yixin_sign"));
            returnJson.put("data", strData);
        }


        PrintWriter writer = response.getWriter();
        writer.print(returnJson);
        writer.flush();
        writer.close();
    }

    /**
     * 对密文进行URL解码、RC4解密.
     *
     * @param text 密文
     * @return 明文
     */
    public static String decrypt(String text, String rc4Key) throws UnsupportedEncodingException {
        /** URL解码 */
        String textDecoded = URLDecoder.decode(text, UTF8);
        /** RC4解密 */
        String textDecodedDecrypted = decode(textDecoded, rc4Key);
        return textDecodedDecrypted;
    }

    /**
     * RC4从密文解密为明文，输入是经过Base64的；如果解密失败，返回值是null
     *
     * @param encodedText
     * @param rc4Key
     * @return
     */
    public static final String decode(final String encodedText, final String rc4Key) {
        try {
            final Cipher c1 = Cipher.getInstance(RC4);
            c1.init(Cipher.DECRYPT_MODE, new SecretKeySpec(rc4Key.getBytes(), RC4));
            return new String(c1.doFinal(Base64.decodeBase64(encodedText.getBytes())), UTF8);
        } catch (final Throwable t) {
            t.printStackTrace();
            return null;
        }
    }

    private void writeMessage(HttpServletResponse response, int status, String content) {
        response.setStatus(status);
        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对明文进行RC4加密、URL编码.
     *
     * @param text 明文
     * @return 密文
     */
    public static String encrypt(String text, String rc4Key) throws UnsupportedEncodingException {
        /** RC4加密 */
        String textEncrypted = encode(text, rc4Key);
        /** URL编码 */
        String textEncryptedEncoded = URLEncoder.encode(textEncrypted, "UTF-8");
        return textEncryptedEncoded;
    }

    /**
     * RC4加密明文（可能包含汉字），输出是经过Base64的；如果加密失败，返回值是null
     *
     * @param plainText
     * @param rc4Key
     * @return
     */
    public static final String encode(final String plainText, final String rc4Key) {
        try {
            final Cipher c1 = Cipher.getInstance(RC4);
            c1.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(rc4Key.getBytes(), RC4));
            return new String(Base64.encodeBase64(c1.doFinal(plainText.getBytes(UTF8))));
        } catch (final Throwable t) {
            t.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws Exception{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id_no", "330327198211304274");
        jsonObject.put("name", "李希梁");
        String strData = encrypt(jsonObject.toJSONString(), "4e6c59f09de5767e");
        System.out.println(strData);
    }
}

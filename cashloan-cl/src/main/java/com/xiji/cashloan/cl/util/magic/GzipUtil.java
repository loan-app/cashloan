package com.xiji.cashloan.cl.util.magic;

import org.apache.commons.codec.binary.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by szb on 18/11/23.
 */
public class GzipUtil {
    public static String uncompress(InputStream gzippedResponse) throws IOException {

        InputStream decompressedResponse = new GZIPInputStream(gzippedResponse);
        Reader reader = new InputStreamReader(decompressedResponse, "UTF-8");
        StringWriter writer = new StringWriter();

        char[] buffer = new char[10240];
        for(int length = 0; (length = reader.read(buffer)) > 0;){
            writer.write(buffer, 0, length);
        }

        writer.close();
        reader.close();
        decompressedResponse.close();
        gzippedResponse.close();

        return writer.toString();
    }

    public static byte[] compress(String str, String encoding)  throws IOException {
        if (str == null || str.length() == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip;
        gzip = new GZIPOutputStream(out);
        gzip.write(str.getBytes(encoding));
        gzip.close();
        return out.toByteArray();
    }

    public static void main(String[] args) throws IOException {
        // {mobile} 申请人的手机号
        // {task_id} 用户授权认证，创建运营商采集任务的任务ID
        // 请注意修改
        // 样例：URL url = new URL("https://api.51datakey.com/carrier/v3/mobiles/18116270000/mxdata?task_id=008ef370-5f11-11e6-909c-00163e004a23“);
        URL url = new URL("https://api.51datakey.com/carrier/v3/mobiles/13701794314/mxdata?task_id=164b8900-a035-11e8-b9d5-00163e0f4b67");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
        connection.setRequestProperty("Accept", "application/json");

        // value 为魔蝎分配的token
        // 请注意修改
        // 样例：connection.setRequestProperty("Authorization", "token 090b2fd02d034dbea409bbd5f9900bc2");
        connection.setRequestProperty("Authorization", "token " + "8ef655524dcf4a0c8bdb8d08bcb7ef41");
        connection.connect();
        InputStream inputStream = connection.getInputStream();
        String uncompress = uncompress(inputStream);
        System.out.println(uncompress);
        System.out.println("原长度：" + uncompress.length());
        System.out.println("压缩后字符串：" + compress(uncompress, "UTF-8").toString().length());
        System.out.println("解压缩后字符串：" + uncompress(new ByteArrayInputStream(compress(uncompress, "UTF-8"))));
        connection.disconnect();

    }

    public static byte[] gzip(String str){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = null;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes("UTF-8"));
        } catch (IOException e){
            e.printStackTrace();
        } finally{
            if(gzip != null){
                try{
                    gzip.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return out.toByteArray();
    }
}

package com.xiji.cashloan.cl.util.weijifen;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Gzip {
    public static String compress(String data) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length());
        GZIPOutputStream gzip = new GZIPOutputStream(bos);
        gzip.write(data.getBytes());
        gzip.close();
        byte[] compressed = bos.toByteArray();
        bos.close();
        return Base64Utils.encode(compressed);
    }

    public static String decompress(String compressed) throws Exception {
        ByteArrayInputStream bis = new ByteArrayInputStream(Base64Utils.decode(compressed));
        GZIPInputStream gis = new GZIPInputStream(bis);
        BufferedReader br = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        gis.close();
        bis.close();
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
//        String content = IOUtils.toString(new File("/Users/luotianbao/Desktop/上报参数.json").toURI(), UTF_8);
//        content = decompress(content);
//        System.out.println(content);
    }
}

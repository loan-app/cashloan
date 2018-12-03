package com.rongdu.cashloan.cl.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by szb on 18/11/21.
 */
public class HttpUtils {
    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;

        try {
            while((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException var14) {
            var14.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException var13) {
                var13.printStackTrace();
            }

        }

        return sb.toString();
    }
}

package com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.util;

import com.xiji.cashloan.core.common.util.OrderNoUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;


public class KuaiqianPayUtil {


    public static String getOrderId() {
        return "xjkq" + OrderNoUtil.getSerialNumber();
    }


    /**
     * inputStream 流 转化成 String
     * @param request
     * @return
     * @throws Exception
     */
    public static String streamToStr(HttpServletRequest request) throws Exception{

        request.setCharacterEncoding("utf-8");
        InputStream is = request.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] receiveBuffer = new byte[2048];
        int readBytesSize = is.read(receiveBuffer);
        while(readBytesSize != -1){
            bos.write(receiveBuffer, 0, readBytesSize);
            readBytesSize = is.read(receiveBuffer);
        }
       return new String(bos.toByteArray(), "UTF-8");
    }
}

package com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.util;

import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.util.OrderNoUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;


public class KuaiqianPayUtil {


    public static String getOrderId() {
        return "xjkq" + OrderNoUtil.getSerialNumber();
    }


    /**
     * 获取快钱协议支付卡bin查询url
     * @return
     */
    public static String getQueryTxnUrl(){
        return Global.getValue("kuaiqian_agreement_query_txn");
    }

    /**
     * 查询绑卡信息url
     * @return
     */
    public static String getPciQueryUrl(){
        return Global.getValue("kuaiqian_agreement_pci_query");
    }

    /**
     * 解绑url
     * @return
     */
    public static String getPciDelUrl(){
        return Global.getValue("kuaiqian_agreement_pci_del");
    }

    /**
     * 协议支付商户号
     * @return
     */
    public static String getAgreementMerchantId(){
        return Global.getValue("kuaiqian_agreement_merchantId");
    }

    /**
     * 协议支付终端号
     * @return
     */
    public static String getAgreementTerminalId(){
        return Global.getValue("kuaiqian_agreement_terminalId");
    }

    /**
     * 获取通用的请求属性
     * @return
     */
    public static String getAgreementAuth(){
        return Global.getValue("kuaiqian_agreement_auth");
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

package com.xiji.cashloan.cl.model.pay.kuaiqian.util;

import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.notifymock.dto.NotifyHead;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.notifymock.dto.NotifyResponse;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.notifymock.dto.NotifyResponseBody;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.paymock.vo.SealDataType;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.querymock.vo.Pay2bankSearchHead;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.querymock.vo.Pay2bankSearchRequest;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.querymock.vo.SearchRequestBody;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.util.OrderNoUtil;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @auther : wnb
 * @date : 2019/4/30
 * @describe :
 */
public class KuaiqianPayUtil {

    public static String getOrderId() {
        return "xjkq" + OrderNoUtil.getSerialNumber();
    }

    public static String getMemberCode(){
        return Global.getValue("kuaiqian_payfor_membercode");
    }

    public static String getPayforPayUrl(){
        return Global.getValue("kuaiqian_payfor_pay_url");
    }

    public static String getPayforQueryUrl(){
        return Global.getValue("kuaiqian_payfor_query_url");
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

    /**
     * 获取对应格式的 时间
     * @param i
     * @return
     */
    public static String getDate(int i){
        SimpleDateFormat sf = null;
        String str = "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        if(i == 1){
            sf = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
            str = sf.format(calendar.getTime());
            return str;
        }else{
            sf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
            calendar.add(Calendar.DAY_OF_YEAR, -i);
            str = sf.format(calendar.getTime());
            return str;
        }
    }



    /**
     * 创建request
     * @return
     */
    public static Pay2bankSearchRequest genRequest(String membercode_head , String version){
        Pay2bankSearchRequest request = new Pay2bankSearchRequest();
        Pay2bankSearchHead head = new Pay2bankSearchHead();
        head.setMemberCode(membercode_head);
        head.setVersion(version);
        SearchRequestBody requestBody = new SearchRequestBody();
        SealDataType sealDataType = new SealDataType();
        requestBody.setSealDataType(sealDataType);
        request.setPay2bankSearchHead(head);
        request.setSearchRequestBody(requestBody);
        return request;
    }

    /**
     * JavaBean转换成xml
     * @param obj
     * @param encoding
     * @return
     */
    public static String convertToXml(Object obj, String encoding) {
        String result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);

            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            result = writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * xml转换成JavaBean
     * @param xml
     * @param c
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T converyToJavaBean(String xml, Class<T> c) {
        T t = null;
        try {
            JAXBContext context = JAXBContext.newInstance(c);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return t;
    }


    /**
     *
     * @param membercode_head
     * @param version
     * @return
     */
    public static NotifyResponse genResponse(String membercode_head , String version){
        NotifyResponse response = new NotifyResponse();
        NotifyHead head = new NotifyHead();
        head.setMemberCode(membercode_head);
        head.setVersion(version);
        NotifyResponseBody responseBody = new NotifyResponseBody();
        SealDataType sealDataType = new SealDataType();
        responseBody.setSealDataType(sealDataType);
        responseBody.setIsReceived("1");
        response.setNotifyHead(head);
        response.setNotifyResponseBody(responseBody);
        return response;
    }
}

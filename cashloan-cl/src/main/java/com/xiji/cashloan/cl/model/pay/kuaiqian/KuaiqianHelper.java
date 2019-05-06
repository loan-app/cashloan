package com.xiji.cashloan.cl.model.pay.kuaiqian;

import com.alibaba.fastjson.JSON;
import com.bill99.asap.exception.CryptoException;
import com.bill99.asap.service.ICryptoService;
import com.bill99.asap.service.impl.CryptoServiceFactory;
import com.bill99.schema.asap.commons.Mpf;
import com.bill99.schema.asap.data.SealedData;
import com.bill99.schema.asap.data.UnsealedData;
import com.xiji.cashloan.cl.model.pay.BasePay;
import com.xiji.cashloan.cl.model.pay.kuaiqian.constant.KuaiqianConstant;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.notifymock.dto.NotifyRequest;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.notifymock.dto.NotifyResponse;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.paymock.util.CCSUtil;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.paymock.util.PKIUtil;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.paymock.vo.Pay2bankOrder;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.paymock.vo.Pay2bankOrderReturn;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.paymock.vo.Pay2bankRequest;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.paymock.vo.Pay2bankResponse;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.querymock.vo.*;
import com.xiji.cashloan.cl.model.pay.kuaiqian.util.KuaiqianUtil;
import com.xiji.cashloan.cl.util.fuiou.XmlBeanUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.log4j.Logger;
import tool.util.BeanUtil;

import javax.net.ssl.SSLContext;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @auther : wnb
 * @date : 2019/4/30
 * @describe :
 */
public class KuaiqianHelper extends BasePay {

    //接口版本
    private static String VERSION = "1.0";

    //字符编码
    private static String encoding = "UTF-8";

    //策略编码，固定值 F41
    private static String fetureCode = "F41";

    private Logger logger = Logger.getLogger(KuaiqianHelper.class);

    /**
     * 放款支付
     * @param
     * @return
     */
    public Pay2bankOrderReturn payment(Pay2bankOrder order) {

        logger.info("--------------------------快钱放款支付开始-------------------------");
        String requestXml = this.genPKIMsg(order);

        Pay2bankOrderReturn pay2bankOrderReturn = new Pay2bankOrderReturn();
        String msg = null;
        try {
            msg = this.invokeCSSCollection(requestXml,KuaiqianUtil.getPayforPayUrl());
            pay2bankOrderReturn = this.unsealMsg(msg);
        } catch (Exception e) {
            logger.error("payment is error, e ==>"+e);
        }
        logger.info("--------------------------快钱放款支付核心逻辑结束-------------------------");
        return pay2bankOrderReturn;
    }

    /**
     *  代付款 -- 查询交易
     * @param order
     * @return
     */
    public Pay2bankSearchDetail queryPayment(Pay2bankSearchRequestParam order) {

        String requestXml = genPKIMsgQuery(order);
        String msg = null;
        Pay2bankSearchDetail pay2bankSearchDetail = null;
        try {
            msg =  invokeCSSCollection(requestXml,KuaiqianUtil.getPayforQueryUrl());
            pay2bankSearchDetail = this.unsealMsgQuery(msg);
        } catch (Exception e) {
            logger.error("queryPayment is error, e ==>"+e.getMessage());
        }
        return pay2bankSearchDetail;
    }


    public String genPKIMsg(Pay2bankOrder order) {

        //构建一个订单对象 (获取原始报文)
        String orderXml = CCSUtil.convertToXml(order, encoding);
        logger.info("快钱放款支付请求明文报文 requestXml ==> "+orderXml);

        // 保存代付请求数据
        saveReqLog(KuaiqianConstant.BTYPE_PAY_MOCK,order.getOrderId(),"",orderXml);
        //加签、加密
        Mpf mpf = genMpf(fetureCode , KuaiqianUtil.getMemberCode());
        SealedData sealedData = null;
        try {
            ICryptoService service = CryptoServiceFactory.createCryptoService();
            sealedData = service.seal(mpf, orderXml.getBytes());
        } catch (CryptoException e) {
            logger.error("快钱放款支付加签、加密失败  e ==> "+e);
        }
        Pay2bankRequest request = CCSUtil.genRequest(KuaiqianUtil.getMemberCode(), VERSION);
        byte[] nullbyte = {};
        byte[] byteOri = sealedData.getOriginalData() == null ? nullbyte : sealedData.getOriginalData();
        byte[] byteEnc = sealedData.getEncryptedData() == null ? nullbyte : sealedData.getEncryptedData();
        byte[] byteEnv = sealedData.getDigitalEnvelope() == null ? nullbyte : sealedData.getDigitalEnvelope();
        byte[] byteSig = sealedData.getSignedData() == null ? nullbyte : sealedData.getSignedData();
        request.getRequestBody().getSealDataType().setOriginalData(PKIUtil.byte2UTF8StringWithBase64(byteOri));
        //获取加签报文
        request.getRequestBody().getSealDataType().setSignedData(PKIUtil.byte2UTF8StringWithBase64(byteSig));
		//获取加密报文
        request.getRequestBody().getSealDataType().setEncryptedData(PKIUtil.byte2UTF8StringWithBase64(byteEnc));
		//数字信封
        request.getRequestBody().getSealDataType().setDigitalEnvelope(PKIUtil.byte2UTF8StringWithBase64(byteEnv));

        //请求报文
        String requestXml = CCSUtil.convertToXml(request, encoding);

        return requestXml;
    }


    public  String genPKIMsgQuery(Pay2bankSearchRequestParam orderDto) {
        //构建一个订单对象  获取原始报文
        String orderXml = KuaiqianUtil.convertToXml(orderDto, encoding);
        logger.info(" query 请求明文报文 ==> "+orderXml);
        // 保存代付请求数据
        saveReqLog(KuaiqianConstant.BTYPE_QUERY_MOCK,orderDto.getOrderId(),"",orderXml);

        //加签、加密
        Mpf mpf = genMpf(fetureCode , KuaiqianUtil.getMemberCode());
        SealedData sealedData = null;
        try {
            ICryptoService service = CryptoServiceFactory.createCryptoService();
            sealedData = service.seal(mpf, orderXml.getBytes());
        } catch (CryptoException e) {
            logger.error(" query 加签、加密异常 ==> "+e);
        }
        Pay2bankSearchRequest request = KuaiqianUtil.genRequest(KuaiqianUtil.getMemberCode(), VERSION);
        byte[] nullbyte = {};
        byte[] byteOri = sealedData.getOriginalData() == null ? nullbyte : sealedData.getOriginalData();
        byte[] byteEnc = sealedData.getEncryptedData() == null ? nullbyte : sealedData.getEncryptedData();
        byte[] byteEnv = sealedData.getDigitalEnvelope() == null ? nullbyte : sealedData.getDigitalEnvelope();
        byte[] byteSig = sealedData.getSignedData() == null ? nullbyte : sealedData.getSignedData();
        request.getSearchRequestBody().getSealDataType().setOriginalData(PKIUtil.byte2UTF8StringWithBase64(byteOri));
        //获取加签报文
        request.getSearchRequestBody().getSealDataType().setSignedData(PKIUtil.byte2UTF8StringWithBase64(byteSig));
		//获取加密报文
        request.getSearchRequestBody().getSealDataType().setEncryptedData(PKIUtil.byte2UTF8StringWithBase64(byteEnc));
		//数字信封
        request.getSearchRequestBody().getSealDataType().setDigitalEnvelope(PKIUtil.byte2UTF8StringWithBase64(byteEnv));

        //请求报文
        String requestXml = CCSUtil.convertToXml(request, encoding);
        logger.info("query 请求加密报文 ==> "+requestXml);
        return requestXml;
    }


    private  String invokeCSSCollection(String requestXml,String url) throws Exception {
        //初始化HttpClient
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(url);
        SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
        sslContext.init(null, null, null);
        SSLContext.setDefault(sslContext);
        //请求服务端
//		InputStream in_withcode = new ByteArrayInputStream(requestXml.getBytes(encoding));
//		method.setRequestBody(in_withcode);
        // url的连接等待超时时间设置
        client.getHttpConnectionManager().getParams().setConnectionTimeout(2000);

        // 读取数据超时时间设置
        client.getHttpConnectionManager().getParams().setSoTimeout(3000);
        method.setRequestEntity(new StringRequestEntity(requestXml, "text/html", "utf-8"));
        client.executeMethod(method);

        //打印服务器返回的状态
        logger.info("服务器返回的状态 ==> "+method.getStatusLine());

        //打印返回的信息
        InputStream stream = method.getResponseBodyAsStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(stream,encoding));
        StringBuffer buf = new StringBuffer();
        String line;
        while (null != (line = br.readLine())) {
            buf.append(line).append("\n");
        }
        //释放连接
        method.releaseConnection();
        return buf.toString();
    }


    public  Pay2bankOrderReturn unsealMsg(String msg) throws Exception {
        Pay2bankResponse response = CCSUtil.converyToJavaBean(msg, Pay2bankResponse.class);
        Pay2bankOrderReturn pay2bankOrderReturn = new Pay2bankOrderReturn();
        pay2bankOrderReturn.setErrorCode(response.getResponseBody().getErrorCode());
        pay2bankOrderReturn.setErrorMsg(response.getResponseBody().getErrorMsg());
        // response.getResponseBody().getErrorCode().equals("0000");
        SealedData sealedData = new SealedData();
        sealedData.setOriginalData(response.getResponseBody().getSealDataType().getOriginalData()==null?null: PKIUtil.utf8String2ByteWithBase64(response.getResponseBody().getSealDataType().getOriginalData()));
        sealedData.setSignedData(response.getResponseBody().getSealDataType().getSignedData()==null?null:PKIUtil.utf8String2ByteWithBase64(response.getResponseBody().getSealDataType().getSignedData()));
        sealedData.setEncryptedData(response.getResponseBody().getSealDataType().getEncryptedData()==null?null:PKIUtil.utf8String2ByteWithBase64(response.getResponseBody().getSealDataType().getEncryptedData()));
        sealedData.setDigitalEnvelope(response.getResponseBody().getSealDataType().getDigitalEnvelope()==null?null:PKIUtil.utf8String2ByteWithBase64(response.getResponseBody().getSealDataType().getDigitalEnvelope()));
        Mpf mpf = genMpf(fetureCode , KuaiqianUtil.getMemberCode());
        UnsealedData unsealedData = null;
        try {
            ICryptoService service = CryptoServiceFactory.createCryptoService();
            unsealedData = service.unseal(mpf, sealedData);
        } catch (CryptoException e) {
            logger.error(this.getClass().getName()+"  unsealMsg  e ==>{}",e);
        }
        byte[] decryptedData = unsealedData.getDecryptedData();
        String rtnString = null;
        if (null != decryptedData) {
            rtnString = PKIUtil.byte2UTF8String(decryptedData);
        } else {
            rtnString = PKIUtil.byte2UTF8String(sealedData.getOriginalData());
        }
        logger.info("快钱代付解密后返回报文 ==> "+rtnString);
        Pay2bankOrder pay2bankOrder = KuaiqianUtil.converyToJavaBean(rtnString, Pay2bankOrder.class);

        logger.info("payment unsealMsg pay2bankOrder ==> "  + JSON.toJSONString(pay2bankOrder));
        BeanUtil.copyProperties(pay2bankOrder,pay2bankOrderReturn);
        logger.info("payment unsealMsg pay2bankOrderReturn ==> "  + JSON.toJSONString(pay2bankOrderReturn));
        this.modifyReqLog(pay2bankOrderReturn.getOrderId(),rtnString);
        return pay2bankOrderReturn;
    }

    /**
     *
     * @param msg
     * @return
     * @throws Exception
     */
    private  Pay2bankSearchDetail unsealMsgQuery(String msg) throws Exception {
        logger.info("query 加密返回报文 ==> "+msg);
        Pay2bankSearchResponse response = CCSUtil.converyToJavaBean(msg, Pay2bankSearchResponse.class);
        SealedData sealedData = new SealedData();
        sealedData.setOriginalData(response.getSearchResponseBody().getSealDataType().getOriginalData()==null?null: com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.querymock.util.PKIUtil.utf8String2ByteWithBase64(response.getSearchResponseBody().getSealDataType().getOriginalData()));
        sealedData.setSignedData(response.getSearchResponseBody().getSealDataType().getSignedData()==null?null: com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.querymock.util.PKIUtil.utf8String2ByteWithBase64(response.getSearchResponseBody().getSealDataType().getSignedData()));
        sealedData.setEncryptedData(response.getSearchResponseBody().getSealDataType().getEncryptedData()==null?null: com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.querymock.util.PKIUtil.utf8String2ByteWithBase64(response.getSearchResponseBody().getSealDataType().getEncryptedData()));
        sealedData.setDigitalEnvelope(response.getSearchResponseBody().getSealDataType().getDigitalEnvelope()==null?null: com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.querymock.util.PKIUtil.utf8String2ByteWithBase64(response.getSearchResponseBody().getSealDataType().getDigitalEnvelope()));
        Mpf mpf = genMpf(fetureCode , KuaiqianUtil.getMemberCode());
        UnsealedData unsealedData = null;
        try {
            ICryptoService service = CryptoServiceFactory.createCryptoService();
            unsealedData = service.unseal(mpf, sealedData);
        } catch (CryptoException e) {
            logger.error(" query e ==>"+e);
        }
        byte[] decryptedData = unsealedData.getDecryptedData();
        String rtnString ;
        if (null != decryptedData) {
            rtnString = PKIUtil.byte2UTF8String(decryptedData);
        } else {
            rtnString = PKIUtil.byte2UTF8String(sealedData.getOriginalData());
        }
        logger.info(" query 解密后返回报文 ==>"+rtnString);
        Pay2bankSearchResult pay2bankSearchResult= XmlBeanUtils.convertXml2Bean(rtnString, Pay2bankSearchResult.class);
        Pay2bankSearchDetail pay2bankSearchDetail = new Pay2bankSearchDetail();
        if (pay2bankSearchResult != null && pay2bankSearchResult.getResultList() != null && pay2bankSearchResult.getResultList().size() > 0){
            pay2bankSearchDetail = pay2bankSearchResult.getResultList().get(0);
        }
        modifyReqLog(pay2bankSearchDetail.getOrderId(), rtnString);
        return pay2bankSearchDetail;
    }

    /**
     *
     * @param httpRequest
     * @return
     */
    public String genRequestXml(HttpServletRequest httpRequest) {
        String line = null;
        ServletInputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();
        try {
            is = httpRequest.getInputStream();
            isr = new InputStreamReader(is, "utf-8");
            br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            logger.error("genRequestXml exception", e);
        } finally{
            try {
                if (null != is)is.close();
                if (null != isr)isr.close();
                if (null != br)br.close();
            } catch (Exception e) {
                logger.error("io close exception", e);
            }
        }
        return sb.toString();
    }


    /**
     * 加密加签 请求报文
     * @param ori
     * @return
     */
    public String sealxml(String ori){
        Mpf mpf = genMpf(fetureCode ,KuaiqianUtil.getMemberCode());
        SealedData sealedData = null;
        try {
            ICryptoService service = CryptoServiceFactory.createCryptoService();
            sealedData = service.seal(mpf, ori.getBytes());
        } catch (CryptoException e) {
            logger.error("响应加签加密验签失败 e ==> "+e);
            return null;
        }
        NotifyResponse response = KuaiqianUtil.genResponse(KuaiqianUtil.getMemberCode() , VERSION);
        byte[] nullbyte = {};
        byte[] byteOri = sealedData.getOriginalData() == null ? nullbyte : sealedData.getOriginalData();
        byte[] byteEnc = sealedData.getEncryptedData() == null ? nullbyte : sealedData.getEncryptedData();
        byte[] byteEnv = sealedData.getDigitalEnvelope() == null ? nullbyte : sealedData.getDigitalEnvelope();
        byte[] byteSig = sealedData.getSignedData() == null ? nullbyte : sealedData.getSignedData();
        response.getNotifyResponseBody().getSealDataType().setOriginalData(PKIUtil.byte2UTF8StringWithBase64(byteOri));
        //获取加签报文
        response.getNotifyResponseBody().getSealDataType().setSignedData(PKIUtil.byte2UTF8StringWithBase64(byteSig));
        //获取加密报文
        response.getNotifyResponseBody().getSealDataType().setEncryptedData(PKIUtil.byte2UTF8StringWithBase64(byteEnc));
        //数字信封
        response.getNotifyResponseBody().getSealDataType().setDigitalEnvelope(PKIUtil.byte2UTF8StringWithBase64(byteEnv));

        //请求报文
        String requestXml = KuaiqianUtil.convertToXml(response, "utf-8");
        logger.info("notify 请求加密报文 ==> "+requestXml);
        return requestXml;
    }

    /**
     * 解密请求报文
     * @param request
     */
    public String unsealxml(NotifyRequest request){
        SealedData sealedData = new SealedData();
        sealedData.setOriginalData(request.getNotifyRequestBody().getSealDataType().getOriginalData()==null?null: com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.notifymock.util.PKIUtil.utf8String2ByteWithBase64(request.getNotifyRequestBody().getSealDataType().getOriginalData()));
        sealedData.setSignedData(request.getNotifyRequestBody().getSealDataType().getSignedData()==null?null: com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.notifymock.util.PKIUtil.utf8String2ByteWithBase64(request.getNotifyRequestBody().getSealDataType().getSignedData()));
        sealedData.setEncryptedData(request.getNotifyRequestBody().getSealDataType().getEncryptedData()==null?null: com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.notifymock.util.PKIUtil.utf8String2ByteWithBase64(request.getNotifyRequestBody().getSealDataType().getEncryptedData()));
        sealedData.setDigitalEnvelope(request.getNotifyRequestBody().getSealDataType().getDigitalEnvelope()==null?null: com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.notifymock.util.PKIUtil.utf8String2ByteWithBase64(request.getNotifyRequestBody().getSealDataType().getDigitalEnvelope()));
        Mpf mpf = genMpf(fetureCode , KuaiqianUtil.getMemberCode());
        UnsealedData unsealedData = null;
        try {
            ICryptoService service = CryptoServiceFactory.createCryptoService();
            unsealedData = service.unseal(mpf, sealedData);
        } catch (CryptoException e) {
            logger.error("请求解密验签失败 e ==> "+e);
            return null;
        }
        byte[] decryptedData = unsealedData.getDecryptedData();
        String rtnString = null;
        if (null != decryptedData) {
            rtnString = PKIUtil.byte2UTF8String(decryptedData);
        } else {
            rtnString = PKIUtil.byte2UTF8String(sealedData.getOriginalData());
        }
        logger.info("notify 解密后返回报文 ==>"+rtnString);
        return rtnString;
    }
    /**
     *
     * @param fetureCode
     * @param membercode
     * @return
     */
    private Mpf genMpf(String fetureCode, String membercode) {
        Mpf mpf = new Mpf();
        mpf.setFeatureCode(fetureCode);
        mpf.setMemberCode(membercode);
        return mpf;
    }
}

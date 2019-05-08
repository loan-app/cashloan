package com.xiji.cashloan.cl.model.pay.kuaiqian;

import com.alibaba.fastjson.JSON;
import com.bill99.asap.exception.CryptoException;
import com.bill99.asap.service.ICryptoService;
import com.bill99.asap.service.impl.CryptoServiceFactory;
import com.bill99.schema.asap.commons.Mpf;
import com.bill99.schema.asap.data.SealedData;
import com.bill99.schema.asap.data.UnsealedData;
import com.xiji.cashloan.cl.domain.PayReqLog;
import com.xiji.cashloan.cl.model.pay.BasePay;
import com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.util.Post;
import com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.vo.TransInfo;
import com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.vo.request.*;
import com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.vo.response.*;
import com.xiji.cashloan.cl.model.pay.kuaiqian.constant.KuaiqianPayConstant;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.notifymock.dto.NotifyRequest;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.notifymock.dto.NotifyResponse;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.paymock.util.CCSUtil;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.paymock.vo.Pay2bankOrder;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.paymock.vo.Pay2bankOrderReturn;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.paymock.vo.Pay2bankRequest;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.paymock.vo.Pay2bankResponse;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.querymock.vo.*;
import com.xiji.cashloan.cl.model.pay.kuaiqian.util.KuaiqianPayUtil;
import com.xiji.cashloan.cl.model.pay.kuaiqian.util.PKIUtil;
import com.xiji.cashloan.cl.service.PayReqLogService;
import com.xiji.cashloan.cl.util.fuiou.XmlBeanUtils;
import com.xiji.cashloan.core.common.context.Global;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.log4j.Logger;
import tool.util.BeanUtil;
import tool.util.DateUtil;

import javax.net.ssl.SSLContext;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther : wnb
 * @date : 2019/4/30
 * @describe :
 */
public class KuaiqianPayHelper extends BasePay {

    //接口版本
    private static String VERSION = "1.0";

    //字符编码
    private static String encoding = "UTF-8";

    //策略编码，固定值 F41
    private static String fetureCode = "F41";

    private Logger logger = Logger.getLogger(KuaiqianPayHelper.class);

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
            msg = this.invokeCSSCollection(requestXml, KuaiqianPayUtil.getPayforPayUrl());
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
            msg =  invokeCSSCollection(requestXml, KuaiqianPayUtil.getPayforQueryUrl());
            pay2bankSearchDetail = this.unsealMsgQuery(msg);
        } catch (Exception e) {
            logger.error("queryPayment is error, e ==>"+e.getMessage());
        }
        return pay2bankSearchDetail;
    }

    /**
     * 银行卡解绑
     * @param pciDelReq
     * @return
     */
    public PciDelResp unbind(PciDelReq pciDelReq){
        TransInfo transInfo = new TransInfo();
        //设置节点
        transInfo.setRecordeText_1("PciDeleteContent");
        transInfo.setRecordeText_2("ErrorMsgContent");
        StringBuffer str1Xml = new StringBuffer();
        str1Xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
                .append("<MasMessage xmlns=\"http://www.99bill.com/mas_cnp_merchant_interface\">")
                .append("<version>"+pciDelReq.getVersion()+"</version>")
                .append("<PciDeleteContent>")
                .append("<merchantId>" + pciDelReq.getMerchantId() + "</merchantId>")
                .append("<customerId>" + pciDelReq.getCustomerId() + "</customerId>")
                .append("<payToken>" + pciDelReq.getPayToken() + "</payToken>")
                .append("<pan>" + pciDelReq.getPan() + "</pan>")
                .append("<storablePan>" + pciDelReq.getStorablePan() + "</storablePan>")
                .append("<bankId>" + pciDelReq.getBankId() + "</bankId>")
                .append("</PciDeleteContent>")
                .append("</MasMessage>");

        logger.info("tr1报文  str1Xml = "+str1Xml);
        String orderNo = KuaiqianPayUtil.getOrderId();
        saveReqLog(KuaiqianPayConstant.PROTOCOL_UNBIND,orderNo, "", str1Xml.toString());
        PciDelResp pciDelResp = new PciDelResp();
        try {
            HashMap respXml = Post.sendPost(KuaiqianPayUtil.getPciDelUrl(),str1Xml.toString(),transInfo);

            pciDelResp = this.getPciDelResp(respXml);
            modifyReqLog(orderNo,JSON.toJSONString(pciDelResp));
        } catch (Exception e) {
            logger.error("unbind is error , e ==> "+e);
        }
        return pciDelResp;
    }

    /**
     * 查询绑卡信息
     * @param pciQueryReq
     * @return
     */
    public PciQueryResp bindQuery(PciQueryReq pciQueryReq){

        PciQueryResp pciQueryResp = new PciQueryResp();
        TransInfo transInfo = new TransInfo();
        //设置节点
        transInfo.setRecordeText_1("PciQueryContent");
        transInfo.setRecordeText_2("ErrorMsgContent");
        StringBuffer str1Xml = new StringBuffer();
        str1Xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
                .append("<MasMessage xmlns=\"http://www.99bill.com/mas_cnp_merchant_interface\">")
                .append("<version>"+pciQueryReq.getVersion()+"</version>")
                .append("<PciQueryContent>")
                .append("<merchantId>" + pciQueryReq.getMerchantId() + "</merchantId>")
                .append("<customerId>" + pciQueryReq.getCustomerId() + "</customerId>")
                .append("<cardType>" + pciQueryReq.getCardType() + "</cardType>")
                .append("<storablePan>" + pciQueryReq.getStorablePan() + "</storablePan>")
                .append("<bankId>" + pciQueryReq.getBankId() + "</bankId>")
                .append("</PciQueryContent>")
                .append("</MasMessage>");
        String orderNo = KuaiqianPayUtil.getOrderId();
        saveReqLog(KuaiqianPayConstant.PROTOCOL_BINDQUERY,orderNo, "", str1Xml.toString());
        try {
            HashMap respXml = Post.sendPost(KuaiqianPayUtil.getPciQueryUrl(),str1Xml.toString(),transInfo);
            pciQueryResp = getPciQueryResp(respXml);
            modifyReqLog(orderNo,JSON.toJSONString(pciQueryResp));
        } catch (Exception e) {
            logger.error("bindQuery is error , e ==> "+e);
        }
        return pciQueryResp;
    }

    /**
     * 查询卡bin 信息
     * @param txnReq
     * @return
     */
    public QueryTxnResp cardBinQuery(QueryTxnReq txnReq) {

        TransInfo transInfo = new TransInfo();
        //设置消费交易的两个节点
        transInfo.setRecordeText_1("QryCardContent");
        transInfo.setRecordeText_2("ErrorMsgContent");
        //Tr1报文拼接
        StringBuffer str1Xml = new StringBuffer();
        str1Xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
                .append("<MasMessage xmlns=\"http://www.99bill.com/mas_cnp_merchant_interface\">")
                .append("<version>"+txnReq.getVersion()+"</version>")
                .append("<QryCardContent>")
                .append("<txnType>" + txnReq.getTxnType() + "</txnType>")
                .append("<cardNo>" + txnReq.getCardNo() + "</cardNo>")
                .append("</QryCardContent>")
                .append("</MasMessage>");
        QueryTxnResp queryTxnResp = new QueryTxnResp();
        String orderNo = KuaiqianPayUtil.getOrderId();
        saveReqLog(KuaiqianPayConstant.PROTOCOL_CARDBINQUERY,orderNo, "", str1Xml.toString());

        try {
            HashMap respXml = Post.sendPost(KuaiqianPayUtil.getQueryTxnUrl(),str1Xml.toString(),transInfo);
            queryTxnResp = this.getQueryTxnResp(respXml);
            modifyReqLog(orderNo,JSON.toJSONString(queryTxnResp));
        } catch (Exception e) {
            logger.error("cardBinQuery is error , e ==> "+e);
        }
        return queryTxnResp;
    }

    /**
     * 解绑响应参数设置
     * @param respXml
     * @return
     */
    private PciDelResp getPciDelResp(HashMap respXml){
        PciDelResp pciDelResp = new PciDelResp();
        pciDelResp.setBankId((String)respXml.get("bankId"));
        pciDelResp.setCustomerId((String)respXml.get("customerId"));
        pciDelResp.setMerchantId((String)respXml.get("merchantId"));
        pciDelResp.setPayToken((String)respXml.get("payToken"));
        pciDelResp.setStorablePan((String)respXml.get("storablePan"));
        pciDelResp.setErrorCode((String)respXml.get("errorCode"));
        pciDelResp.setErrorMessage((String)respXml.get("errorMessage"));
        pciDelResp.setVersion((String)respXml.get("version"));
        pciDelResp.setResponseCode((String)respXml.get("responseCode"));
        pciDelResp.setPan((String)respXml.get("pan"));
        pciDelResp.setResponseTextMessage((String)respXml.get("responseTextMessage"));
        return pciDelResp;
    }


    /**
     * 查询绑卡信息
     * @param respXml
     * @return
     */
    private PciQueryResp getPciQueryResp(HashMap respXml){

        PciQueryResp pciQueryResp = new PciQueryResp();
        pciQueryResp.setBankId((String)respXml.get("bankId"));
        pciQueryResp.setBindType((String)respXml.get("bandType"));
        pciQueryResp.setCardType((String)respXml.get("cardType"));
        pciQueryResp.setCustomerId((String)respXml.get("customerId"));
        pciQueryResp.setMerchantId((String)respXml.get("merchantId"));
        pciQueryResp.setPayToken((String)respXml.get("payToken"));
        pciQueryResp.setStorablePan((String)respXml.get("storablePan"));
        pciQueryResp.setErrorCode((String)respXml.get("errorCode"));
        pciQueryResp.setErrorMessage((String)respXml.get("errorMessage"));
        pciQueryResp.setTerminalId((String)respXml.get("terminalId"));
        pciQueryResp.setVersion((String)respXml.get("version"));
        pciQueryResp.setResponseCode((String)respXml.get("responseCode"));
        return pciQueryResp;
    }

    /**
     * 获取查询卡bin 结果数据
     * @param respXml
     * @return
     */
    private QueryTxnResp getQueryTxnResp(HashMap respXml){

        QueryTxnResp queryTxnResp = new QueryTxnResp();
        queryTxnResp.setBankId((String)respXml.get("bankId"));
        queryTxnResp.setCardNo((String)respXml.get("cardNo"));
        queryTxnResp.setCardOrg((String)respXml.get("cardOrg"));
        queryTxnResp.setCardType((String)respXml.get("cardType"));
        queryTxnResp.setIssuer((String)respXml.get("issuer"));
        queryTxnResp.setTxnType((String)respXml.get("txnType"));
        queryTxnResp.setValidateType((String)respXml.get("validateType"));
        queryTxnResp.setValidFlag((String)respXml.get("validFlag"));
        queryTxnResp.setVersion((String)respXml.get("version"));
        return queryTxnResp;
    }


    private String genPKIMsg(Pay2bankOrder order) {

        //构建一个订单对象 (获取原始报文)
        String orderXml = CCSUtil.convertToXml(order, encoding);
        logger.info("快钱放款支付请求明文报文 requestXml ==> "+orderXml);

        // 保存代付请求数据
        saveReqLog(KuaiqianPayConstant.BTYPE_PAY_MOCK,order.getOrderId(),"",orderXml);
        //加签、加密
        Mpf mpf = genMpf(fetureCode , KuaiqianPayUtil.getMemberCode());
        SealedData sealedData = null;
        try {
            ICryptoService service = CryptoServiceFactory.createCryptoService();
            sealedData = service.seal(mpf, orderXml.getBytes());
        } catch (CryptoException e) {
            logger.error("快钱放款支付加签、加密失败  e ==> "+e);
        }
        Pay2bankRequest request = CCSUtil.genRequest(KuaiqianPayUtil.getMemberCode(), VERSION);
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
        String orderXml = KuaiqianPayUtil.convertToXml(orderDto, encoding);
        logger.info(" query 请求明文报文 ==> "+orderXml);
        // 保存代付请求数据
        saveReqLog(KuaiqianPayConstant.BTYPE_QUERY_MOCK,orderDto.getOrderId(),"",orderXml);

        //加签、加密
        Mpf mpf = genMpf(fetureCode , KuaiqianPayUtil.getMemberCode());
        SealedData sealedData = null;
        try {
            ICryptoService service = CryptoServiceFactory.createCryptoService();
            sealedData = service.seal(mpf, orderXml.getBytes());
        } catch (CryptoException e) {
            logger.error(" query 加签、加密异常 ==> "+e);
        }
        Pay2bankSearchRequest request = KuaiqianPayUtil.genRequest(KuaiqianPayUtil.getMemberCode(), VERSION);
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
        Mpf mpf = genMpf(fetureCode , KuaiqianPayUtil.getMemberCode());
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
        Pay2bankOrder pay2bankOrder = KuaiqianPayUtil.converyToJavaBean(rtnString, Pay2bankOrder.class);

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
        Mpf mpf = genMpf(fetureCode , KuaiqianPayUtil.getMemberCode());
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
        Mpf mpf = genMpf(fetureCode , KuaiqianPayUtil.getMemberCode());
        SealedData sealedData = null;
        try {
            ICryptoService service = CryptoServiceFactory.createCryptoService();
            sealedData = service.seal(mpf, ori.getBytes());
        } catch (CryptoException e) {
            logger.error("响应加签加密验签失败 e ==> "+e);
            return null;
        }
        NotifyResponse response = KuaiqianPayUtil.genResponse(KuaiqianPayUtil.getMemberCode() , VERSION);
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
        String requestXml = KuaiqianPayUtil.convertToXml(response, "utf-8");
        logger.info("notify 请求加密报文 ==> "+requestXml);
        return requestXml;
    }

    /**
     * 解密请求报文
     * @param request
     */
    public String unsealxml(NotifyRequest request){
        SealedData sealedData = new SealedData();
        sealedData.setOriginalData(request.getNotifyRequestBody().getSealDataType().getOriginalData()==null?null: PKIUtil.utf8String2ByteWithBase64(request.getNotifyRequestBody().getSealDataType().getOriginalData()));
        sealedData.setSignedData(request.getNotifyRequestBody().getSealDataType().getSignedData()==null?null: PKIUtil.utf8String2ByteWithBase64(request.getNotifyRequestBody().getSealDataType().getSignedData()));
        sealedData.setEncryptedData(request.getNotifyRequestBody().getSealDataType().getEncryptedData()==null?null: PKIUtil.utf8String2ByteWithBase64(request.getNotifyRequestBody().getSealDataType().getEncryptedData()));
        sealedData.setDigitalEnvelope(request.getNotifyRequestBody().getSealDataType().getDigitalEnvelope()==null?null: PKIUtil.utf8String2ByteWithBase64(request.getNotifyRequestBody().getSealDataType().getDigitalEnvelope()));
        Mpf mpf = genMpf(fetureCode , KuaiqianPayUtil.getMemberCode());
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


    /**
     * 还款支付
     * @param reqVo
     * @return
     */
    public PayForRespVo repayment(PayForReqVo reqVo) {

        PayForRespVo payForRespVo = new PayForRespVo();
        TransInfo transInfo = new TransInfo();
        //设置消费交易的两个节点
        transInfo.setRecordeText_1("TxnMsgContent");
        transInfo.setRecordeText_2("ErrorMsgContent");
        //Tr1报文拼接
        String str1Xml = "";

        str1Xml += "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        str1Xml += "<MasMessage xmlns=\"http://www.99bill.com/mas_cnp_merchant_interface\">";
        str1Xml += "<version>"+reqVo.getVersion()+"</version>";
        str1Xml += "<TxnMsgContent>";
        str1Xml += "<interactiveStatus>" + reqVo.getInteractiveStatus() + "</interactiveStatus>";
        str1Xml += "<spFlag>" + reqVo.getSpFlag() + "</spFlag>";
        str1Xml += "<txnType>" + reqVo.getTxnType() + "</txnType>";
        str1Xml += "<merchantId>" + reqVo.getMerchantId() + "</merchantId>";
        str1Xml += "<terminalId>" + reqVo.getTerminalId() + "</terminalId>";
        str1Xml += "<externalRefNumber>" + reqVo.getExternalRefNumber() + "</externalRefNumber>";
        str1Xml += "<entryTime>" + reqVo.getEntryTime() + "</entryTime>";
        str1Xml += "<amount>" + reqVo.getAmount() + "</amount>";
        str1Xml += "<customerId>" + reqVo.getCustomerId() + "</customerId>";
        str1Xml += "<payToken>" + reqVo.getPayToken() + "</payToken>";
        str1Xml += "<tr3Url>"+reqVo.getTr3Url()+"</tr3Url>";

        str1Xml += "<extMap>";
        str1Xml += "<extDate><key>phone</key><value></value></extDate>";
        str1Xml += "<extDate><key>validCode</key><value></value></extDate>";
        str1Xml += "<extDate><key>savePciFlag</key><value>0</value></extDate>";
        str1Xml += "<extDate><key>token</key><value></value></extDate>";
        str1Xml += "<extDate><key>payBatch</key><value>2</value></extDate>";
        str1Xml += "</extMap>";

        str1Xml += "</TxnMsgContent>";
        str1Xml += "</MasMessage>";

        logger.info("放款支付tr1报文  str1Xml = " + str1Xml);

        String url = Global.getValue("kq_protocol_pay_url");    //测试环境地址

        //保存请求log
        saveReqLog(KuaiqianPayConstant.PROTOCOL_BINDMSG, reqVo.getExternalRefNumber(),"", JSON.toJSONString(reqVo));

        HashMap respXml=null;
        try{
            respXml = Post.sendPost(url,str1Xml,transInfo);
            //TR2接收的数据
            logger.info("respXml = " + respXml);

            if(respXml==null) {
                logger.error("读取内容出错");
            } else {
                payForRespVo=map2Bean(respXml, PayForRespVo.class);
                //如果TR2获取的应答码responseCode的值为00时，成功
                if("00".equals((String)respXml.get("responseCode"))) {
                    //更新返回数据
                    modifyReqLog(reqVo.getExternalRefNumber(), respXml.toString());
                    logger.info("交易成功");
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }

        return payForRespVo;
    }


    /**
     * 获取验证码
     * @param reqVo
     * @return
     */
    public AgreementSendValidateCodeRespVo bindMsg(AgreementSendValidateCodeReqVo reqVo) {
        AgreementSendValidateCodeRespVo bindResult = new AgreementSendValidateCodeRespVo();
        reqVo.setVersion(KuaiqianPayConstant.PROTOCOL_VERSION);
        reqVo.setMerchantId(KuaiqianPayUtil.getAgreementMerchantId());
        reqVo.setTerminalId(KuaiqianPayUtil.getAgreementTerminalId());

        TransInfo transInfo = new TransInfo();
        //设置手机动态鉴权节点
        transInfo.setRecordeText_1("indAuthContent");
        transInfo.setRecordeText_2("ErrorMsgContent");

        //Tr1报文拼接
        String str1Xml = "";

        str1Xml += "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
        str1Xml += "<MasMessage xmlns=\"http://www.99bill.com/mas_cnp_merchant_interface\">";
        str1Xml += "<version>"+reqVo.getVersion()+"</version>";
        str1Xml += "<indAuthContent>";
        str1Xml += "<merchantId>" + reqVo.getMerchantId() + "</merchantId>";
        str1Xml += "<terminalId>" + reqVo.getTerminalId()+ "</terminalId>";
        str1Xml += "<customerId>" + reqVo.getCustomerId()+ "</customerId>";
        str1Xml += "<externalRefNumber>" + reqVo.getExternalRefNumber()+ "</externalRefNumber>";
        str1Xml += "<pan>" + reqVo.getPan() + "</pan>";
        str1Xml += "<cardHolderName>" + reqVo.getCardHolderName() + "</cardHolderName>";
        str1Xml += "<idType>" + reqVo.getIdType() + "</idType>";
        str1Xml += "<cardHolderId>" + reqVo.getCardHolderId() + "</cardHolderId>";
        if(!"".equals(reqVo.getExpiredDate()) && !"".equals(reqVo.getCvv2())){
            str1Xml += "<expiredDate>" + reqVo.getExpiredDate() + "</expiredDate>";
            str1Xml += "<cvv2>" + reqVo.getCvv2() + "</cvv2>";
        }
        str1Xml += "<phoneNO>" + reqVo.getPhoneNO() + "</phoneNO>";
        str1Xml += " <bindType>0</bindType>";
        str1Xml += "</indAuthContent>";
        str1Xml += "</MasMessage>";

        logger.info("获取验证码tr1报文  str1Xml = " + str1Xml);

        String url = Global.getValue("kq_Captcha_url");

        //保存请求log
        saveReqLog(KuaiqianPayConstant.PROTOCOL_BINDMSG, reqVo.getExternalRefNumber(),"", JSON.toJSONString(reqVo));

        HashMap respXml=null;
        try{
            respXml = Post.sendPost(url,str1Xml,transInfo);
            //TR2接收的数据
            logger.info("respXml = " + respXml);

            if(respXml==null) {
                logger.error("读取内容出错");
            } else {
                bindResult=map2Bean(respXml, AgreementSendValidateCodeRespVo.class);

                logger.info("返回结果bindResult ="+bindResult);
                //如果TR2获取的应答码responseCode的值为00时，成功
                if("00".equals((String)respXml.get("responseCode"))) {
                    String token = respXml.get("token").toString();
                    updateReqLog(reqVo.getExternalRefNumber(), respXml.toString(),token);
                    logger.info("验证码发送成功");
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }

        return bindResult;
    }


    /**
     * 绑卡
     * @param beanReq
     * @return
     */
    public BindCardRespVo bindCommit(BindCardReqVo beanReq) {
        BindCardRespVo bindResult = new BindCardRespVo();
        beanReq.setVersion(KuaiqianPayConstant.PROTOCOL_VERSION);
        beanReq.setMerchantId(KuaiqianPayUtil.getAgreementMerchantId());
        beanReq.setTerminalId(KuaiqianPayUtil.getAgreementTerminalId());
        String orderNo = KuaiqianPayUtil.getOrderId();

        TransInfo transInfo = new TransInfo();
        //设置手机动态鉴权节点
        transInfo.setRecordeText_1("indAuthDynVerifyContent");
        transInfo.setRecordeText_2("ErrorMsgContent");

        //Tr1报文拼接
        String str1Xml = "";

        str1Xml += "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
        str1Xml += "<MasMessage xmlns=\"http://www.99bill.com/mas_cnp_merchant_interface\">";
        str1Xml += "<version>"+beanReq.getVersion()+"</version>";
        str1Xml += "<indAuthDynVerifyContent>";
        str1Xml += "<merchantId>" + beanReq.getMerchantId() + "</merchantId>";
        str1Xml += "<terminalId>" + beanReq.getTerminalId() + "</terminalId>";
        str1Xml += "<customerId>" + beanReq.getCustomerId() + "</customerId>";
        str1Xml += "<externalRefNumber>" + beanReq.getExternalRefNumber() + "</externalRefNumber>";
        str1Xml += "<pan>" + beanReq.getPan() + "</pan>";
        str1Xml += "<validCode>" + beanReq.getValidCode() + "</validCode>";
        str1Xml += "<token>" + beanReq.getToken() + "</token>";
        str1Xml += "<phoneNO>" + beanReq.getPhoneNO() + "</phoneNO>";
        str1Xml += "</indAuthDynVerifyContent>";
        str1Xml += "</MasMessage>";

        logger.info("绑卡tr1报文  str1Xml = " + str1Xml);

        String url = Global.getValue("kq_bindCard_url");
        //保存请求log
        saveReqLog(KuaiqianPayConstant.PROTOCOL_BINDCOMMIT, orderNo, "", JSON.toJSONString(beanReq));

        HashMap respXml=null;
        try{
            respXml = Post.sendPost(url,str1Xml,transInfo);
            //TR2接收的数据
            logger.info("respXml = " + respXml);

            if(respXml==null) {
                logger.error("读取内容出错");
            } else {
                bindResult=map2Bean(respXml, BindCardRespVo.class);
                //如果TR2获取的应答码responseCode的值为00时，成功
                if("00".equals((String)respXml.get("responseCode"))) {
                    //更新返回数据
                    modifyReqLog(orderNo, respXml.toString());
                    logger.info("卡信息验证交易成功");
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
        return bindResult;
    }

    public QueryStatusRespVO queryOrder(QueryStatusReqVO reqVO) {

        QueryStatusRespVO bindResult = new QueryStatusRespVO();
        TransInfo transInfo= new TransInfo();
        //设置消费交易的两个节点
        transInfo.setRecordeText_1("TxnMsgContent");
        transInfo.setRecordeText_2("ErrorMsgContent");

        //Tr1报文拼接
        String str1Xml = "";


        str1Xml += "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        str1Xml += "<MasMessage xmlns=\"http://www.99bill.com/mas_cnp_merchant_interface\">";
        str1Xml += "<version>"+reqVO.getVersion()+"</version>";

        str1Xml += "<QryTxnMsgContent>";
        str1Xml += "<txnType>" + reqVO.getTxnType() + "</txnType>";
        str1Xml += "<merchantId>" + reqVO.getMerchantId() + "</merchantId>";
        str1Xml += "<terminalId>" + reqVO.getTerminalId() + "</terminalId>";
        str1Xml += "<externalRefNumber>" + reqVO.getExternalRefNumber() + "</externalRefNumber>";
//        str1Xml += "<refNumber>" + refNumber + "</refNumber>";
//        str1Xml += "<txnStatus>" + txnStatus + "</txnStatus>";
        str1Xml += "</QryTxnMsgContent>";
        str1Xml += "</MasMessage>";

        logger.info("查询交易订单tr1报文  str1Xml = " + str1Xml);

        String url = Global.getValue("kq_query_status_url");
        //保存请求log
        saveReqLog(KuaiqianPayConstant.PROTOCOL_BINDCOMMIT, reqVO.getExternalRefNumber(), "", JSON.toJSONString(reqVO));

        HashMap respXml=null;
        try{
            respXml = Post.sendPost(url,str1Xml,transInfo);
            //TR2接收的数据
            logger.info("respXml = " + respXml);

            if(respXml==null) {
                logger.error("读取内容出错");
            } else {
                bindResult=map2Bean(respXml, QueryStatusRespVO.class);
                //如果TR2获取的应答码responseCode的值为00时，成功
                if("00".equals((String)respXml.get("responseCode"))) {
                    //更新返回数据
                    modifyReqLog(reqVO.getExternalRefNumber(), respXml.toString());
                    logger.info("查询交易成功");
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }

        return bindResult;
    }


    /**
     * 更新返回数据
     *
     * @param orderNo
     * @param resp
     */
    protected void updateReqLog(String orderNo, String resp,String token) {
        PayReqLogService payReqLogService = (PayReqLogService) BeanUtil.getBean("payReqLogService");
        PayReqLog reqLog = payReqLogService.findByOrderNo(orderNo);
        if (null == reqLog) {
            return;
        }
        reqLog.setReturnParams(resp);
        reqLog.setToken(token);
        reqLog.setReturnTime(DateUtil.getNow());
        payReqLogService.updateById(reqLog);
    }
    /**
     *
     *
     * Map转换层Bean。
     * @param <T>
     * @param map
     * @param class1
     * @return
     */
    public static <T> T map2Bean(Map<String, String> map, Class<T> class1) {
        T bean = null;
        try {
            bean = class1.newInstance();
            BeanUtils.populate(bean, map);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }

}

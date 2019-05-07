package com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.notifymock;

import com.bill99.asap.exception.CryptoException;
import com.bill99.asap.service.ICryptoService;
import com.bill99.asap.service.impl.CryptoServiceFactory;
import com.bill99.schema.asap.commons.Mpf;
import com.bill99.schema.asap.data.SealedData;
import com.bill99.schema.asap.data.UnsealedData;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.notifymock.dto.NotifyRequest;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.notifymock.dto.NotifyResponse;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.notifymock.util.CCSUtil;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.notifymock.util.PKIUtil;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;




/**
 * 单笔快到银出款
 * http请求入口
 * @author zan.liang
 *
 */
public class NotifyController extends AbstractController{

    //测试账户信息
    private static String membercode = "10011637747";
    private static String fetureCode = "F41";
    //接口版本
    private static String VERSION = "1.0";
    private Logger logger = Logger.getLogger(NotifyController.class);

    public static Mpf genMpf(String fetureCode, String membercode) {
        Mpf mpf = new Mpf();
        mpf.setFeatureCode(fetureCode);
        mpf.setMemberCode(membercode);
        return mpf;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest httpRequest,
                                                 HttpServletResponse httpResponse) throws Exception {
        long start = System.currentTimeMillis();
        //获取客户端请求报文
        String requestXml = genRequestXml(httpRequest);
        NotifyRequest request = CCSUtil.converyToJavaBean(requestXml, NotifyRequest.class);
        unsealxml(request);//解密请求报文
        //调用单笔快到银api2.0服务
        String responseXml = sealxml(request.getNotifyRequestBody().getSealDataType().getOriginalData());
        //返回响应报文
        httpResponse.setCharacterEncoding("utf-8");
        httpResponse.setContentType("utf-8");
        httpResponse.getWriter().write(responseXml);
        httpResponse.getWriter().flush();
        long end = System.currentTimeMillis();
        logger.info("cost "+(end-start));
        return null;
    }

    private String sealxml(String ori){
        Mpf mpf = genMpf(fetureCode , membercode);
        SealedData sealedData = null;
        try {
            ICryptoService service = CryptoServiceFactory.createCryptoService();
            sealedData = service.seal(mpf, ori.getBytes());
        } catch (CryptoException e) {
            System.out.println(e);
        }
        NotifyResponse response = CCSUtil.genResponse(membercode , VERSION);
        byte[] nullbyte = {};
        byte[] byteOri = sealedData.getOriginalData() == null ? nullbyte : sealedData.getOriginalData();
        byte[] byteEnc = sealedData.getEncryptedData() == null ? nullbyte : sealedData.getEncryptedData();
        byte[] byteEnv = sealedData.getDigitalEnvelope() == null ? nullbyte : sealedData.getDigitalEnvelope();
        byte[] byteSig = sealedData.getSignedData() == null ? nullbyte : sealedData.getSignedData();
        response.getNotifyResponseBody().getSealDataType().setOriginalData(PKIUtil.byte2UTF8StringWithBase64(byteOri));
        //获取加签报文
        response.getNotifyResponseBody().getSealDataType().setSignedData(PKIUtil.byte2UTF8StringWithBase64(byteSig));
//		//获取加密报文
        response.getNotifyResponseBody().getSealDataType().setEncryptedData(PKIUtil.byte2UTF8StringWithBase64(byteEnc));
//		//数字信封
        response.getNotifyResponseBody().getSealDataType().setDigitalEnvelope(PKIUtil.byte2UTF8StringWithBase64(byteEnv));

        //请求报文
        String requestXml = CCSUtil.convertToXml(response, "utf-8");
        System.out.println("请求加密报文 = " + requestXml);
        return requestXml;
    }

    private void unsealxml(NotifyRequest request){
        SealedData sealedData = new SealedData();
        sealedData.setOriginalData(request.getNotifyRequestBody().getSealDataType().getOriginalData()==null?null:PKIUtil.utf8String2ByteWithBase64(request.getNotifyRequestBody().getSealDataType().getOriginalData()));
        sealedData.setSignedData(request.getNotifyRequestBody().getSealDataType().getSignedData()==null?null:PKIUtil.utf8String2ByteWithBase64(request.getNotifyRequestBody().getSealDataType().getSignedData()));
        sealedData.setEncryptedData(request.getNotifyRequestBody().getSealDataType().getEncryptedData()==null?null:PKIUtil.utf8String2ByteWithBase64(request.getNotifyRequestBody().getSealDataType().getEncryptedData()));
        sealedData.setDigitalEnvelope(request.getNotifyRequestBody().getSealDataType().getDigitalEnvelope()==null?null:PKIUtil.utf8String2ByteWithBase64(request.getNotifyRequestBody().getSealDataType().getDigitalEnvelope()));
        Mpf mpf = genMpf(fetureCode , membercode);
        UnsealedData unsealedData = null;
        try {
            ICryptoService service = CryptoServiceFactory.createCryptoService();
            unsealedData = service.unseal(mpf, sealedData);
        } catch (CryptoException e) {
            System.out.println(e);
        }
        byte[] decryptedData = unsealedData.getDecryptedData();
        if (null != decryptedData) {
            String rtnString = PKIUtil.byte2UTF8String(decryptedData);
            System.out.println("解密后返回报文 = " + rtnString);
        } else {
            String  rtnString = PKIUtil.byte2UTF8String(sealedData.getOriginalData());
            System.out.println("解密后返回报文 = " + rtnString);
        }
    }

    private String genRequestXml(HttpServletRequest httpRequest) {
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

}

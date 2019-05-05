package com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.notifymock.util;


import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.notifymock.dto.NotifyHead;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.notifymock.dto.NotifyResponse;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.notifymock.dto.NotifyResponseBody;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.paymock.vo.SealDataType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;


/**
 * 工具�?
 * @author zhiwei.ma
 *
 */
public class CCSUtil {
	
//	/**
//	 * 创建request
//	 * @return
//	 */
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
	
}

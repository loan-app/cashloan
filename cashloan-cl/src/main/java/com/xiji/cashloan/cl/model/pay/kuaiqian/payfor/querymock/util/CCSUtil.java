package com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.querymock.util;

import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.paymock.vo.SealDataType;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.querymock.vo.Pay2bankSearchHead;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.querymock.vo.Pay2bankSearchRequest;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.querymock.vo.Pay2bankSearchRequestParam;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.querymock.vo.SearchRequestBody;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;






/**
 * 工具类
 * @author zhiwei.ma
 *
 */
public class CCSUtil {
	
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
	 * 随机生成一笔订单
	 * @return
	 */
	public static Pay2bankSearchRequestParam genParam(){
		Pay2bankSearchRequestParam order = new Pay2bankSearchRequestParam();
		//页码 必填 正整数
		order.setTargetPage("1");
		//每页条数  必填  1-20  正整数
		order.setPageSize("20");
		//商家订单号 
		order.setOrderId("KQ20190213154133");//test_20180322092536  test_20171120174007
		//金额（分） 
	    order.setAmount("");
		//银行名称 
	    order.setBankName("");
		//开户行  
		order.setBranchName("");
		//收款人姓名  
		order.setCreditName("");
		//银行卡号 
		order.setBankAcctId("");
		//开始时间 必填
		order.setStartDate("2019-02-12 00:00:00"); //2017-11-19 08:12:12
		//结束时间 必填  结束-开始<=7天
		order.setEndDate("2019-02-14 23:59:59"); //2017-11-21 23:59:59
		return order;
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

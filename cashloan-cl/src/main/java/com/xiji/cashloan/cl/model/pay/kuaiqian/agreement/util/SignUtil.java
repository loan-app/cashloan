package com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;


/**
 * @project mgwCore
 * @description:数据验签
 * @author cen
 * @create_time:Jun 21, 2009
 * @modify_time:Jun 21, 2009
 */
public class SignUtil {

	/**
	 * @param data 被签名的原数据字节数组，xml去掉signData节点。
	 * @param signData 签名字节数组。
	 * @param certFile X.509标准的证书文件。
	 * @return 如果验签通过，就返回true
	 * @throws RuntimeException
	 */
	public static boolean veriSign(byte[] data, byte[] signData, String certFile)
			throws RuntimeException{

		InputStream is = null;
		try {
			//加载公钥
			is = new FileInputStream(certFile);

			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			Certificate cert = cf.generateCertificate(is);

			PublicKey publicKey = cert.getPublicKey();

			Signature sig = Signature.getInstance("SHA1WithRSA");
			byte[] signed = Base64Binrary.decodeBase64Binrary(new String(signData));
			sig.initVerify(publicKey);
			sig.update(data);
			return sig.verify(signed);

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if (is != null) {
				try{
					is.close();
				}catch(Exception e){
					throw new RuntimeException(e.getMessage(), e);
				}

			}
		}
	}

	/**
	 * @param tr3Xml tr3的xml。
	 * @return 如果验签通过就返回true
	 * @throws RuntimeException
	 */

	public static boolean veriSignForXml(String tr3Xml)
	{
		String certFile="";
		try {
			Resource fileRource = new ClassPathResource("cer/mgw.cer");
			certFile = fileRource.getURL().toString();
			//certFile = SignUtil.class.getResource("mgw.cer").toURI().getPath();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String dataBeforeSign =  tr3Xml.replaceAll("<signature>.*</signature>", "");

		int beginIndex = tr3Xml.indexOf("<signature>");
		int endIndex = tr3Xml.indexOf("</signature>");
		String signData =  tr3Xml.substring(beginIndex + 11, endIndex);

		try {
			return veriSign(dataBeforeSign.getBytes("UTF-8"),
					signData.getBytes("UTF-8"), certFile);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}

	}

	public static void main(String[] args) throws Exception {

		String tr3Xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><MasMessage xmlns=\"http://www.99bill.com/mas_cnp_merchant_interface\"><version>1.0</version><TxnMsgContent><txnType>PUR</txnType><interactiveStatus>TR3</interactiveStatus><amount>0.2</amount><merchantId>104110045112012</merchantId><terminalId>00002012</terminalId><entryTime>20190509141252</entryTime><externalRefNumber>xjkq1905091336597346</externalRefNumber><transTime>20190509141252</transTime><refNumber>110070172591</refNumber><responseCode>00</responseCode><cardOrg>CU</cardOrg><storableCardNo>6217851473</storableCardNo><authorizationCode>372646</authorizationCode><signature>HNemeg4bBy5kV+6yEyxHLhr3bac3EGSsjg7YQ6BiYipK4sRyjPXyAOLjXrmP0ETTpdXCb4PSpRFghFhWLJieGNMGVqjjJc5V7vIEycZDB66xfI+yoVJMRxeEfXK9eMW2UI7+9ENJNFLwdf/9cuJsRsIzkAmYrN/Rajqew344iTM=</signature></TxnMsgContent></MasMessage>";
		Resource fileRource = new ClassPathResource("cer/mgw.cer");
		String certFile = ((ClassPathResource) fileRource).getURL().getPath();
		//String certFile_1 = SignUtil.class.getResource("mgw.cer").toURI().getPath();
		System.out.println("certFile ==>" + certFile );

		System.out.println("fileRource.exists() ==>"+fileRource.exists());

		System.out.println("fileRource).getURL() ==>"+fileRource.getFilename());

		Boolean b = veriSignForXml(tr3Xml);

		System.out.println("b ==>"+b);
		//System.out.println("certFile_1 == >" + certFile_1);
	}

}
	
	



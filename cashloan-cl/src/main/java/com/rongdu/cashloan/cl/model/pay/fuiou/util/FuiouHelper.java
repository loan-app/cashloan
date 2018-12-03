package com.rongdu.cashloan.cl.model.pay.fuiou.util;

import com.alibaba.fastjson.JSON;
import com.rongdu.cashloan.cl.model.pay.fuiou.constant.FuiouConstant;
import com.rongdu.cashloan.cl.model.pay.fuiou.payfor.PayforreqModel;
import com.rongdu.cashloan.cl.model.pay.fuiou.payfor.PayforrspModel;
import com.rongdu.cashloan.cl.model.pay.fuiou.payfor.QrytransreqModel;
import com.rongdu.cashloan.cl.model.pay.fuiou.payfor.QrytransrspModel;
import com.rongdu.cashloan.cl.util.fuiou.MD5Util;
import com.rongdu.cashloan.cl.util.fuiou.XmlBeanUtils;
import com.rongdu.cashloan.core.common.context.Global;
import com.rongdu.cashloan.core.common.util.HttpsUtil;
import com.rongdu.cashloan.core.common.util.OrderNoUtil;
import java.util.HashMap;
import javax.xml.bind.JAXBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tool.util.StringUtil;

/**
 * 富有支付
 */

public class FuiouHelper extends BasePay{

	public static final Logger logger = LoggerFactory.getLogger(FuiouHelper.class);
	/**
	 * 代收付 -- 支付（放款）
	 * @param model
	 * @return
	 */
	public PayforrspModel payment(PayforreqModel model) {
		PayforrspModel result = new PayforrspModel();
		String payUrl = Global.getValue("fuiou_daifu_req_url");
		String merid = Global.getValue("fuiou_merid");
		String pwd = Global.getValue("fuiou_pwd");
		String xml = null;
		try {
			xml = XmlBeanUtils.convertBean2Xml(model, "UTF-8",true);
		} catch (JAXBException e) {
			logger.error(e.getMessage(), e);
		}

		HashMap param = new HashMap();
		param.put("merid",merid);
		param.put("reqtype",FuiouConstant.DAIFU_INTERFACE_REQ);
		param.put("xml",xml);
		/**
		 * Md5(merid+”|”+pwd+”|”+reqtype+”|”+xml)
		 */
		String macSource = merid+"|"+pwd+"|"+FuiouConstant.DAIFU_INTERFACE_REQ+"|"+xml;
		param.put("mac",MD5Util.encode(macSource, "UTF-8").toUpperCase());
		// 获取系统参数中支付启用状态
		String fuiouSwitch = Global.getValue("fuiou_switch");
		String resp = null;
//		logger.info(JSON.toJSONString(param));
		saveReqLog(FuiouConstant.DAIFU_INTERFACE_REQ,model.getOrderno(), "",JSON.toJSONString(param));

		if (StringUtil.isNotBlank(fuiouSwitch) && "1".equals(fuiouSwitch)) {
			if (logger.isDebugEnabled()) {
				logger.debug("请求地址：" + payUrl);
			}
			resp = HttpsUtil.postClient(payUrl, param);
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("关闭连连支付,模拟返回结果");
			}
			resp = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><payforrsp><ret>000000</ret><memo>成功</memo></payforrsp>";
		}
		/**
		 * 调用接口异常
		 * ,出现异常时，需要进行二次审核，查询是否调用接口成功
		 */
		if (isException(resp)) {
			result.setErrorCode(resp);
		} else {
			try {
				result = XmlBeanUtils.convertXml2Bean(resp, PayforrspModel.class);
			} catch (JAXBException e) {
				logger.error(e.getMessage(), e);
				result.setErrorCode("3005");
			}
		}

		modifyReqLog(model.getOrderno(),resp);
		return result;
	}

	/**
	 * 代收付 -- 查询交易(条件可组合)
	 * @param model
	 * @return
	 */
	public QrytransrspModel queryPayment(QrytransreqModel model) {
		QrytransrspModel result = new QrytransrspModel();
		String payUrl = Global.getValue("fuiou_daifu_req_url");
		String merid = Global.getValue("fuiou_merid");
		String pwd = Global.getValue("fuiou_pwd");
		String xml = null;
		try {
			xml = XmlBeanUtils.convertBean2Xml(model, "UTF-8",true);
		} catch (JAXBException e) {
			logger.error(e.getMessage(), e);
		}

		HashMap param = new HashMap();
		param.put("merid",merid);
		param.put("reqtype",FuiouConstant.DAIFU_INTERFACE_QUERYREQ);
		param.put("xml",xml);
		/**
		 * Md5(merid+”|”+pwd+”|”+reqtype+”|”+xml)
		 */
		String macSource = merid+"|"+pwd+"|"+FuiouConstant.DAIFU_INTERFACE_QUERYREQ+"|"+xml;
		param.put("mac",MD5Util.encode(macSource, "UTF-8").toUpperCase());
		// 获取系统参数中支付启用状态
		String fuiouSwitch = Global.getValue("fuiou_switch");
		String resp = null;
		String orderNo = OrderNoUtil.getSerialNumber();
//		logger.info(JSON.toJSONString(param));
		saveReqLog(FuiouConstant.DAIFU_INTERFACE_QUERYREQ,orderNo, "",JSON.toJSONString(param));

		if (StringUtil.isNotBlank(fuiouSwitch) && "1".equals(fuiouSwitch)) {
			if (logger.isDebugEnabled()) {
				logger.debug("请求地址：" + payUrl);
			}
			resp = HttpsUtil.postClient(payUrl, param);
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("关闭连连支付,模拟返回结果");
			}
			resp = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><qrytransrsp><ret>000000</ret><memo>成功</memo><trans><merdt>20181121</merdt><orderno>1811211136312501</orderno><accntno>6227001541120012765</accntno><accntnm>傅元晶</accntnm><amt>440000</amt><entseq></entseq><memo></memo><state>1</state><result>渠道资金到账已复核,交易已发送</result><reason>交易成功</reason></trans></qrytransrsp>";
		}
		/**
		 * 调用接口异常
		 */
		if (isException(resp)) {
			result.setErrorCode(resp);
		} else {
			try {
				result = XmlBeanUtils.convertXml2Bean(resp, QrytransrspModel.class);
			} catch (JAXBException e) {
				logger.error(e.getMessage(), e);
				result.setErrorCode("3005");
			}
		}

		modifyReqLog(orderNo,resp);
		return result;
	}

	public String getRemark(PayforrspModel result) {
		if (StringUtil.isEmpty(result.getRet())) {
			return result.getErrorCode() + "|代扣接口请求异常";
		}else {
			return result.getMemo();
		}
	}
}


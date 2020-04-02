package com.jiya.cashloan;

import com.alibaba.fastjson.JSON;
import com.xiji.cashloan.cl.model.pay.fuiou.constant.FuiouConstant;
import com.xiji.cashloan.cl.model.pay.fuiou.payfor.PayforreqModel;
import com.xiji.cashloan.cl.model.pay.fuiou.payfor.PayforrspModel;
import com.xiji.cashloan.cl.model.pay.fuiou.payfor.QrytransreqModel;
import com.xiji.cashloan.cl.model.pay.fuiou.payfor.QrytransrspModel;
import com.xiji.cashloan.cl.model.pay.fuiou.util.FuiouHelper;
import com.xiji.cashloan.cl.util.fuiou.FuiouDateUtil;
import com.xiji.cashloan.cl.util.fuiou.MD5Util;
import com.xiji.cashloan.cl.util.fuiou.XmlBeanUtils;
import com.xiji.cashloan.core.common.util.HttpsUtil;
import com.xiji.cashloan.core.common.util.OrderNoUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.xml.bind.JAXBException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tool.util.DateUtil;
import tool.util.StringUtil;

/**
 * @Auther: king
 * @Date: 2018/11/22 18:52
 * @Description:
 */
public class PayForTest {
    public static final Logger logger = LoggerFactory.getLogger(FuiouHelper.class);

    public static void main(String[] args) {
//        payment();
        testqueryPayment();
    }

    public static void payment() {
        String orderNo = OrderNoUtil.getSerialNumber();
        PayforreqModel model = new PayforreqModel();
        model.setMerdt(DateUtil.dateStr7(new Date()));
        model.setVer("1.0");
        model.setAmt("10000");
        model.setAccntno("6212261904006115311");
        model.setAccntnm("似曾相识");
//        model.setBankno("0102");
//        model.setCityno("2900");
        model.setAddDesc("1");
        model.setMobile("15388888888");
        model.setOrderno(orderNo);
        PayforrspModel result = new PayforrspModel();
        String payUrl = "https://fht-test.fuiou.com/fuMer/req.do";
        String xml = null;
        try {
            xml = XmlBeanUtils.convertBean2Xml(model, "UTF-8",true);
        } catch (JAXBException e) {
            logger.error(e.getMessage(), e);
        }
        HashMap param = new HashMap();
        param.put("merid","0002900F0345178");
        param.put("reqtype","payforreq");
        param.put("xml",xml);
        /**
         * Md5(merid+”|”+pwd+”|”+reqtype+”|”+xml)
         */
        String merid = "0002900F0345178";
        String pwd = "123456";
        String macSource = merid+"|"+pwd+"|"+"payforreq"+"|"+xml;
        param.put("mac", MD5Util.encode(macSource, "UTF-8").toUpperCase());
        // 获取系统参数中支付启用状态
        String fuiouSwitch = "1";
        String resp = null;
        if (StringUtil.isNotBlank(fuiouSwitch) && "1".equals(fuiouSwitch)) {
            logger.debug("请求地址：" + payUrl);
            resp = HttpsUtil.postClient(payUrl, param);
        } else {
            logger.debug("关闭连连支付,模拟返回结果");
            resp = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><payforrsp><ret>000000</ret><memo>成功</memo></payforrsp>";
        }
        /**
         * 调用接口异常
         * ,出现异常时，需要进行二次审核，查询是否调用接口成功
         */
        if (isException(resp)) {
            result.setErrorCode(resp);
        }
        try {
            result = XmlBeanUtils.convertXml2Bean(resp, PayforrspModel.class);
        } catch (JAXBException e) {
            logger.error(e.getMessage(), e);
        }
        System.out.println(orderNo);
        System.out.println(JSON.toJSON(result));
    }
    private static boolean isException(String resp) {
        return StringUtils.startsWith(resp, "300");
    }

    public static String getDate(){
        SimpleDateFormat sf=new SimpleDateFormat("yyyyMMdd");
        Date date=new Date();
        String date1=sf.format(date);
        return date1;
    }

    public static void testqueryPayment() {
        QrytransreqModel model = new QrytransreqModel();
        model.setOrderno("1811290913624031");
        model.setVer("1.0");
        model.setBusicd("AP01");
        model.setStartdt(FuiouDateUtil.getDate(3));
        model.setEnddt(FuiouDateUtil.getDate(1));
        QrytransrspModel result = queryPayment(model);
        System.out.println(JSON.toJSON(result));
    }

    public static QrytransrspModel queryPayment(QrytransreqModel model) {
        QrytransrspModel result = new QrytransrspModel();
        String payUrl =  "https://fht-test.fuiou.com/fuMer/req.do";
        String merid = "0002900F0345178";
        String pwd = "123456";
        String xml = null;
        try {
            xml = XmlBeanUtils.convertBean2Xml(model, "UTF-8",true);
        } catch (JAXBException e) {
            logger.error(e.getMessage(), e);
        }

        HashMap param = new HashMap();
        param.put("merid",merid);
        param.put("reqtype", FuiouConstant.DAIFU_INTERFACE_QUERYREQ);
        param.put("xml",xml);
        String macSource = merid+"|"+pwd+"|"+FuiouConstant.DAIFU_INTERFACE_QUERYREQ+"|"+xml;
        param.put("mac",MD5Util.encode(macSource, "UTF-8").toUpperCase());
        // 获取系统参数中支付启用状态
        String fuiouSwitch = "1";
        String resp = null;

//        saveReqLog(FuiouConstant.DAIFU_INTERFACE_QUERYREQ,model.getOrderno(), "", JSON.toJSONString(param));

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
                result.setErrorCode("3003");
            }
        }

//        modifyReqLog(model.getOrderno(),resp);
        return result;
    }
}

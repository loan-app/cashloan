package com.jiya.cashloan;

import com.rongdu.cashloan.cl.model.pay.fuiou.payfor.PayforrspModel;
import com.rongdu.cashloan.cl.model.pay.lianlian.PaymentModel;
import com.rongdu.cashloan.cl.util.fuiou.XmlBeanUtils;
import com.rongdu.cashloan.core.common.util.ReflectUtil;
import javax.xml.bind.JAXBException;

/**
 * @Auther: king
 * @Date: 2018/11/21 12:24
 * @Description:
 */
public class Admin {

    public static void main(String[] args) {

//        testTrans();

//        try {
//            String test = "testxyz";
//            String key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIbA52JWbirSYa2iTd/P7 G6NGgOAAmgGFcTaktRVhHtgeeTHd24iT2MNTCIw/3ykcWu/55hbpHBcZIiLf/XZ940iae SgIGmfoJa9xdVmZ5l4ElPUVtLJMntUfbPdAMP8SEwjMP8Nr6PvzjcKXS5GCfCuTW/F/dK z1mR1LOcxAkLBAgMBAAECgYBjkzBoLk4CPqwHTqQU+uRPXN0YMQOWMsjrSkittvPK56Or Nuo97ASVwUG9Ek/4ntthL9HHeBCvJtbzP4Iy/fo6sevZVcaURNb3mn1R/gdIitwFur8bd F+VA5mZX8cTR4D4liZZvBHwx+UtvdWClzoOSeSpFZn7/6nMXpYzam3WQQJBALvXIHeAdP rtktmRtqmdVNYGqmgtE7jqkaqZ9VgUMcIt8W01oPEDp27NtmGTM06nneIk/ajagq97nsb c6JPa6PUCQQC3pm9RM782qnL/5fzNsv7HyTjFAlIg3Q+PNlSj1d3ekNlqRJ0hv4/aLiqr LqtqbfHu98aeGt4JsdilT/Z9rwMdAkEAlFgwFtBHEkh/Wf3ewRM0hZZcC8vVsIrnoVDXV skUBuNbsEDTKqQVHceuSl8C/RIY+Rj3jtuKq+W4HhsmPmZ65QJAbtbypG244ENreOrT80 ou32Gg87Z83vzMoUDHQMKZT/TYY3zZ4T5+kc3/TqWyK2AD/photY+9pthByzRBroVsOQJ AMUQ/c+Mngb8kKxU+mF/CwDSlwbL8/lM/xoDnT/qQxmxTiEysohd3jO98C0BA3+YHFswa XKtY7/Tp/H1VeX9EdA==";
//            String encypt =  DESCoderFUIOU.desEncrypt(test, DESCoderFUIOU.getKeyLength8(key));
//            System.out.println(encypt);
//            System.out.println(DESCoderFUIOU.desDecrypt(encypt,DESCoderFUIOU.getKeyLength8(key)));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        test();
//        testCovert();
//        testPaymentModel();
//        PayforrspModel payforrspModel = new PayforrspModel();
//        System.out.println(payforrspModel.error());
    }
    private  static void testTrans() {
        Qrytransreq qrytransreq = new Qrytransreq();
        qrytransreq.setBusicd("vua");
        qrytransreq.setOrderNo("test");
        Trans trans = new Trans();
        trans.setOrderNo("tard");
        trans.setAmt("1111");
        trans.setMerdt("88888");
        qrytransreq.setTrans(trans);
        String orderPlain = null;
        try {
            orderPlain = XmlBeanUtils.convertBean2Xml(qrytransreq, "UTF-8",true);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        System.out.println("[订单信息:]" + orderPlain);

        try {
            Qrytransreq orderRespData = XmlBeanUtils.convertXml2Bean(orderPlain, Qrytransreq.class);
            System.out.println(orderRespData.getTrans().getAmt());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private  static void test() {
        OrderRespData reqData = new OrderRespData();
        reqData.amt = "testabc";
        String orderPlain = null;
        try {
            orderPlain = XmlBeanUtils.convertBean2Xml(reqData, "UTF-8",true);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        System.out.println("[订单信息:]" + orderPlain);

        try {
            OrderRespData orderRespData = XmlBeanUtils.convertXml2Bean(orderPlain, OrderRespData.class);
            System.out.println(orderRespData.amt);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private static void testPaymentModel() {
        PaymentModel payment = new PaymentModel();
        payment.setOrderNo("orderNo");
        payment.setOid_partner("oidPartner");
        payment.setDt_order("dt_ordre");
        System.out.println(ReflectUtil.fieldValueToJson(payment,payment.respParamNames()));
    }

    private static void testCovert() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><payforrsp><ret>000000</ret><memo>成功</memo><xyz>成功</xyz></payforrsp>";
        try {
            PayforrspModel result = XmlBeanUtils.convertXml2Bean(xml, PayforrspModel.class);
            if (result != null) {
                System.out.println(result.getRet());
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}

package com.xiji.cashloan.cl.model.pay.kuaiqian.agreement;

import com.xiji.cashloan.cl.model.pay.BasePay;


public class KuaiqianPayHelper extends BasePay {

//    public static final Logger logger = LoggerFactory.getLogger(KuaiqianPayHelper.class);
//
//    /**
//     * 获取验证码
//     * @param reqVo
//     * @return
//     */
//    public BindXmlBeanResp bindMsg(BindXmlBeanReq reqVo) {
//        BindXmlBeanResp bindResult = new BindXmlBeanResp();
//        reqVo.setVersion(KuaiqianPayConstant.PROTOCOL_VERSION);
//        reqVo.setMerchantId(Global.getValue("kuaiqian_protocol_merchantId"));
//        reqVo.setTerminalId(Global.getValue("kuaiqian_protocol_terminalId"));
//
//        TransInfo transInfo = new TransInfo();
//        //设置手机动态鉴权节点
//        transInfo.setRecordeText_1("indAuthContent");
//        transInfo.setRecordeText_2("ErrorMsgContent");
//
//        //Tr1报文拼接
//        String str1Xml = "";
//
//        str1Xml += "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
//        str1Xml += "<MasMessage xmlns=\"http://www.99bill.com/mas_cnp_merchant_interface\">";
//        str1Xml += "<version>"+reqVo.getVersion()+"</version>";
//        str1Xml += "<indAuthContent>";
//        str1Xml += "<merchantId>" + reqVo.getMerchantId() + "</merchantId>";
//        str1Xml += "<terminalId>" + reqVo.getTerminalId()+ "</terminalId>";
//        str1Xml += "<customerId>" + reqVo.getCustomerId()+ "</customerId>";
//        str1Xml += "<externalRefNumber>" + reqVo.getExternalRefNumber()+ "</externalRefNumber>";
//        str1Xml += "<pan>" + reqVo.getPan() + "</pan>";
//        str1Xml += "<cardHolderName>" + reqVo.getCardHolderName() + "</cardHolderName>";
//        str1Xml += "<idType>" + reqVo.getIdType() + "</idType>";
//        str1Xml += "<cardHolderId>" + reqVo.getCardHolderId() + "</cardHolderId>";
//        if(!"".equals(reqVo.getExpiredDate()) && !"".equals(reqVo.getCvv2())){
//            str1Xml += "<expiredDate>" + reqVo.getExpiredDate() + "</expiredDate>";
//            str1Xml += "<cvv2>" + reqVo.getCvv2() + "</cvv2>";
//        }
//        str1Xml += "<phoneNO>" + reqVo.getPhoneNO() + "</phoneNO>";
//        str1Xml += " <bindType>0</bindType>";
//        str1Xml += "</indAuthContent>";
//        str1Xml += "</MasMessage>";
//
//        System.out.println("tr1报文  str1Xml = " + str1Xml);
//
//        String url = Global.getValue("kq_Captcha_test_url");    //测试环境地址
//        //String url = "kq_Captcha_production_url";     //生产环境地址
//        String signStr = reqVo.sendMsgSignStr();
//        saveReqLog(KuaiqianPayConstant.PROTOCOL_BINDMSG, reqVo.getExternalRefNumber(), signStr, JSON.toJSONString(reqVo));
//
//        HashMap respXml=null;
//        try{
//             respXml = Post.sendPost(url,str1Xml,transInfo);
//            //TR2接收的数据
//            System.out.println("respXml = " + respXml);
//
//            if(respXml==null) {
//                logger.error("读取内容出错");
//            } else {
//                bindResult=map2Bean(respXml,BindXmlBeanResp.class);
//                //如果TR2获取的应答码responseCode的值为00时，成功
//                if("00".equals((String)respXml.get("responseCode"))) {
//                    String token = respXml.get("token").toString();
//                    updateReqLog(reqVo.getExternalRefNumber(), respXml.toString(),token);
//                    logger.info("卡信息验证交易成功");
//                }
//            }
//        }catch (Exception e){
//            logger.error(e.getMessage(), e);
//        }
//
//        return bindResult;
//    }
//
//    /**
//     * 银行卡解绑
//     * @param pciDelReq
//     * @return
//     */
//    public PciDelResp unbind(PciDelReq pciDelReq){
//        TransInfo transInfo = new TransInfo();
//        //设置节点
//        transInfo.setRecordeText_1("PciDeleteContent");
//        transInfo.setRecordeText_2("ErrorMsgContent");
//        StringBuffer str1Xml = new StringBuffer();
//        str1Xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
//                .append("<MasMessage xmlns=\"http://www.99bill.com/mas_cnp_merchant_interface\">")
//                .append("<version>"+pciDelReq.getVersion()+"</version>")
//                .append("<PciDeleteContent>")
//                .append("<merchantId>" + pciDelReq.getMerchantId() + "</merchantId>")
//                .append("<customerId>" + pciDelReq.getCustomerId() + "</customerId>")
//                .append("<payToken>" + pciDelReq.getPayToken() + "</payToken>")
//                .append("<pan>" + pciDelReq.getPan() + "</pan>")
//                .append("<storablePan>" + pciDelReq.getStorablePan() + "</storablePan>")
//                .append("<bankId>" + pciDelReq.getBankId() + "</bankId>")
//                .append("</PciDeleteContent>")
//                .append("</MasMessage>");
//
//        logger.info("tr1报文  str1Xml = "+str1Xml);
//        String orderNo = KuaiqianPayUtil.getOrderId();
//        saveReqLog(KuaiqianPayConstant.PROTOCOL_UNBIND,orderNo, "", str1Xml.toString());
//        PciDelResp pciDelResp = new PciDelResp();
//        try {
//            HashMap respXml = Post.sendPost(KuaiqianPayUtil.getPciDelUrl(),str1Xml.toString(),transInfo);
//
//            pciDelResp = this.getPciDelResp(respXml);
//            modifyReqLog(orderNo,JSON.toJSONString(pciDelResp));
//        } catch (Exception e) {
//            logger.error("unbind is error , e ==> "+e);
//        }
//        return pciDelResp;
//    }
//
//    /**
//     * 查询绑卡信息
//     * @param pciQueryReq
//     * @return
//     */
//    public PciQueryResp bindQuery(PciQueryReq pciQueryReq){
//
//        PciQueryResp pciQueryResp = new PciQueryResp();
//        TransInfo transInfo = new TransInfo();
//        //设置节点
//        transInfo.setRecordeText_1("PciQueryContent");
//        transInfo.setRecordeText_2("ErrorMsgContent");
//        StringBuffer str1Xml = new StringBuffer();
//        str1Xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
//                .append("<MasMessage xmlns=\"http://www.99bill.com/mas_cnp_merchant_interface\">")
//                .append("<version>"+pciQueryReq.getVersion()+"</version>")
//                .append("<PciQueryContent>")
//                .append("<merchantId>" + pciQueryReq.getMerchantId() + "</merchantId>")
//                .append("<customerId>" + pciQueryReq.getCustomerId() + "</customerId>")
//                .append("<cardType>" + pciQueryReq.getCardType() + "</cardType>")
//                .append("<storablePan>" + pciQueryReq.getStorablePan() + "</storablePan>")
//                .append("<bankId>" + pciQueryReq.getBankId() + "</bankId>")
//                .append("</PciQueryContent>")
//                .append("</MasMessage>");
//        String orderNo = KuaiqianPayUtil.getOrderId();
//        saveReqLog(KuaiqianPayConstant.PROTOCOL_BINDQUERY,orderNo, "", str1Xml.toString());
//        try {
//            HashMap respXml = Post.sendPost(KuaiqianPayUtil.getPciQueryUrl(),str1Xml.toString(),transInfo);
//            pciQueryResp = getPciQueryResp(respXml);
//            modifyReqLog(orderNo,JSON.toJSONString(pciQueryResp));
//        } catch (Exception e) {
//            logger.error("bindQuery is error , e ==> "+e);
//        }
//        return pciQueryResp;
//    }
//
//    /**
//     * 查询卡bin 信息
//     * @param txnReq
//     * @return
//     */
//    public QueryTxnResp cardBinQuery(QueryTxnReq txnReq) {
//
//        TransInfo transInfo = new TransInfo();
//        //设置消费交易的两个节点
//        transInfo.setRecordeText_1("QryCardContent");
//        transInfo.setRecordeText_2("ErrorMsgContent");
//        //Tr1报文拼接
//        StringBuffer str1Xml = new StringBuffer();
//        str1Xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
//                .append("<MasMessage xmlns=\"http://www.99bill.com/mas_cnp_merchant_interface\">")
//                .append("<version>"+txnReq.getVersion()+"</version>")
//                .append("<QryCardContent>")
//                .append("<txnType>" + txnReq.getTxnType() + "</txnType>")
//                .append("<cardNo>" + txnReq.getCardNo() + "</cardNo>")
//                .append("</QryCardContent>")
//                .append("</MasMessage>");
//        QueryTxnResp queryTxnResp = new QueryTxnResp();
//        String orderNo = KuaiqianPayUtil.getOrderId();
//        saveReqLog(KuaiqianPayConstant.PROTOCOL_CARDBINQUERY,orderNo, "", str1Xml.toString());
//
//        try {
//            HashMap respXml = Post.sendPost(KuaiqianPayUtil.getQueryTxnUrl(),str1Xml.toString(),transInfo);
//            queryTxnResp = this.getQueryTxnResp(respXml);
//            modifyReqLog(orderNo,JSON.toJSONString(queryTxnResp));
//        } catch (Exception e) {
//            logger.error("cardBinQuery is error , e ==> "+e);
//        }
//        return queryTxnResp;
//    }
//
//    /**
//     * 解绑响应参数设置
//     * @param respXml
//     * @return
//     */
//    private PciDelResp getPciDelResp(HashMap respXml){
//        PciDelResp pciDelResp = new PciDelResp();
//        pciDelResp.setBankId((String)respXml.get("bankId"));
//        pciDelResp.setCustomerId((String)respXml.get("customerId"));
//        pciDelResp.setMerchantId((String)respXml.get("merchantId"));
//        pciDelResp.setPayToken((String)respXml.get("payToken"));
//        pciDelResp.setStorablePan((String)respXml.get("storablePan"));
//        pciDelResp.setErrorCode((String)respXml.get("errorCode"));
//        pciDelResp.setErrorMessage((String)respXml.get("errorMessage"));
//        pciDelResp.setVersion((String)respXml.get("version"));
//        pciDelResp.setResponseCode((String)respXml.get("responseCode"));
//        pciDelResp.setPan((String)respXml.get("pan"));
//        pciDelResp.setResponseTextMessage((String)respXml.get("responseTextMessage"));
//        return pciDelResp;
//    }
//
//
//    /**
//     * 查询绑卡信息
//     * @param respXml
//     * @return
//     */
//    private PciQueryResp getPciQueryResp(HashMap respXml){
//
//        PciQueryResp pciQueryResp = new PciQueryResp();
//        pciQueryResp.setBankId((String)respXml.get("bankId"));
//        pciQueryResp.setBindType((String)respXml.get("bandType"));
//        pciQueryResp.setCardType((String)respXml.get("cardType"));
//        pciQueryResp.setCustomerId((String)respXml.get("customerId"));
//        pciQueryResp.setMerchantId((String)respXml.get("merchantId"));
//        pciQueryResp.setPayToken((String)respXml.get("payToken"));
//        pciQueryResp.setStorablePan((String)respXml.get("storablePan"));
//        pciQueryResp.setErrorCode((String)respXml.get("errorCode"));
//        pciQueryResp.setErrorMessage((String)respXml.get("errorMessage"));
//        pciQueryResp.setTerminalId((String)respXml.get("terminalId"));
//        pciQueryResp.setVersion((String)respXml.get("version"));
//        pciQueryResp.setResponseCode((String)respXml.get("responseCode"));
//        return pciQueryResp;
//    }
//
//    /**
//     * 获取查询卡bin 结果数据
//     * @param respXml
//     * @return
//     */
//    private QueryTxnResp getQueryTxnResp(HashMap respXml){
//
//        QueryTxnResp queryTxnResp = new QueryTxnResp();
//        queryTxnResp.setBankId((String)respXml.get("bankId"));
//        queryTxnResp.setCardNo((String)respXml.get("cardNo"));
//        queryTxnResp.setCardOrg((String)respXml.get("cardOrg"));
//        queryTxnResp.setCardType((String)respXml.get("cardType"));
//        queryTxnResp.setIssuer((String)respXml.get("issuer"));
//        queryTxnResp.setTxnType((String)respXml.get("txnType"));
//        queryTxnResp.setValidateType((String)respXml.get("validateType"));
//        queryTxnResp.setValidFlag((String)respXml.get("validFlag"));
//        queryTxnResp.setVersion((String)respXml.get("version"));
//        return queryTxnResp;
//    }
//
//
//    /**
//     * 更新返回数据
//     *
//     * @param orderNo
//     * @param resp
//     */
//    protected void updateReqLog(String orderNo, String resp,String token) {
//        PayReqLogService payReqLogService = (PayReqLogService) BeanUtil.getBean("payReqLogService");
//        PayReqLog reqLog = payReqLogService.findByOrderNo(orderNo);
//        if (null == reqLog) {
//            return;
//        }
//        reqLog.setReturnParams(resp);
//        reqLog.setToken(token);
//        reqLog.setReturnTime(DateUtil.getNow());
//        payReqLogService.updateById(reqLog);
//    }
//    /**
//     *
//     *
//     * Map转换层Bean。
//     * @param <T>
//     * @param map
//     * @param class1
//     * @return
//     */
//    public static <T> T map2Bean(Map<String, String> map, Class<T> class1) {
//        T bean = null;
//        try {
//            bean = class1.newInstance();
//            BeanUtils.populate(bean, map);
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        return bean;
//    }


}

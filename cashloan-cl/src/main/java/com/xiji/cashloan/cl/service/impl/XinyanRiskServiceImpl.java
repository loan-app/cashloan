package com.xiji.cashloan.cl.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiji.cashloan.cl.domain.*;
import com.xiji.cashloan.cl.mapper.*;
import com.xiji.cashloan.cl.model.xinyan.XinyanConstant;
import com.xiji.cashloan.cl.service.XinyanRiskService;
import com.xiji.cashloan.cl.util.CallsOutSideFeeConstant;
import com.xiji.cashloan.cl.util.token.HttpRestUtils;
import com.xiji.cashloan.cl.util.xinyan.MD5Utils;
import com.xiji.cashloan.cl.util.xinyan.SecurityUtil;
import com.xiji.cashloan.cl.util.xinyan.UUIDGenerator;
import com.xiji.cashloan.cl.util.xinyan.rsa.RsaCodingUtil;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.util.DateUtil;
import com.xiji.cashloan.core.common.util.StringUtil;
import com.xiji.cashloan.core.domain.Borrow;
import com.xiji.cashloan.core.domain.UserBaseInfo;
import com.xiji.cashloan.core.mapper.UserBaseInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 新颜数据
 * Created by szb on 18/12/10.
 */
@Service
public class XinyanRiskServiceImpl implements XinyanRiskService {
    public static final Logger logger = LoggerFactory.getLogger(XinyanRiskServiceImpl.class);

    @Resource
    private XinyanReqLogMapper xinyanReqLogMapper;
    @Resource
    private XinyanLoanReportMapper xinyanLoanReportMapper;
    @Resource
    private UserBaseInfoMapper userBaseInfoMapper;
    @Resource
    private CallsOutSideFeeMapper callsOutSideFeeMapper;
    @Resource
    private XinyanPreNoLogMapper xinyanPreNoLogMapper;
    @Resource
    private ClBorrowMapper clBorrowMapper;
    @Resource
    private XinyanXwldMapper xinyanXwldMapper;


    @Override
    public int queryLoan(Borrow borrow) {
        int i = 0;
        UserBaseInfo userBaseinfo = userBaseInfoMapper.findByUserId(borrow.getUserId());
        if (userBaseinfo == null) {
            logger.error("查询用户userId：" + userBaseinfo.getUserId() + ",用户不存在");
            return i;
        }
        Date createDate = DateUtil.getNow();
        Long userId = userBaseinfo.getUserId();
        XinyanReqLog log = new XinyanReqLog();
        log.setUserId(userId);
        log.setBorrowId(borrow.getId());
        log.setCreateTime(createDate);
        log.setType(XinyanConstant.LOAN_TYPE);
        log.setIsFee(0);

        try {
            /** 1、商户号 **/
            String memberId = Global.getValue("xy_member_id");
            /** 2、终端号 **/
            String terminalId = Global.getValue("xy_terminal_id");
            /** 3、请求地址 **/
            String url = Global.getValue("xy_loan_url");
            /** 4、私钥密码 **/
            String pfxpwd = Global.getValue("xy_pfx_pwd");
            Map<String, String> headers = new HashMap<>();

            String idNo = userBaseinfo.getIdNo();
            String realName = userBaseinfo.getRealName();
            logger.info("原始数据:idNo:" + idNo + ",realName:" + realName);
            //加密数据
            idNo = MD5Utils.encryptMD5(idNo.trim());
            realName = MD5Utils.encryptMD5(realName.trim());

            String tradeDate = new SimpleDateFormat("yyyyMMddHHmmss").format(createDate);// 订单日期
            String transId = UUIDGenerator.getUUID();// 必须入库 并且唯一 商户订单号
            log.setTransId(transId);

            String data = getData(memberId, terminalId, tradeDate, transId, idNo, realName, pfxpwd);

            Map<String, Object> params = new HashMap<>();
            params.put("member_id", memberId);
            params.put("terminal_id", terminalId);
            params.put("data_type", "json");
            params.put("data_content", data);
            String result = HttpRestUtils.postForm(url, headers, params);
            if (StringUtil.isNotBlank(result)) {
                JSONObject resJson = JSONObject.parseObject(result);
                log.setRespCode(String.valueOf(resJson.get("errorCode")));
                log.setRespParams(String.valueOf(resJson.get("errorMsg")));
                log.setIsSuccess(resJson.getBoolean("success") ? 1 : 0);
                Date respTime = DateUtil.getNow();
                log.setRespTime(respTime);
                if (resJson.getBoolean("success")) {
                    JSONObject dataJson = JSONObject.parseObject(resJson.getString("data"));
                    log.setDataCode(dataJson.getString("code"));
                    log.setTradeNo(dataJson.getString("trade_no"));
                    if ("0".equals(dataJson.getString("code"))) {
                        log.setIsFee(1);
                        //插入收费记录表
                        CallsOutSideFee callsOutSideFee = new CallsOutSideFee(userId, dataJson.getString("trade_no"), CallsOutSideFeeConstant.CALLS_TYPE_XINYAN_LOAN,
                                CallsOutSideFeeConstant.FEE_XINYAN_LOAN, CallsOutSideFeeConstant.CAST_TYPE_CONSUME, userBaseinfo.getPhone());
                        callsOutSideFeeMapper.save(callsOutSideFee);
                        //插入报告
                        i = saveLoanReport(String.valueOf(dataJson.get("result_detail")), borrow.getUserId(), dataJson.getString("trade_no"), respTime);
                    } else if("1".equals(dataJson.getString("code"))) {
                        i = 1;
                    } else {
                        logger.error("用户" + userBaseinfo.getRealName() + "，请求新颜,新颜返回未知异常");
                    }
                }
            } else {
                logger.error("用户" + userBaseinfo.getRealName() + "，请求新颜，同步响应数据为空，result:" + result);
            }
        } catch (Exception e) {
            logger.error("用户" + userBaseinfo.getRealName() + "，请求新颜过程出现异常", e);
        }
        xinyanReqLogMapper.save(log);
        return i;
    }

    @Override
    public String getPreOrderNo(Borrow borrow) {
        String preOrderNo = StringUtil.EMPTY;
        UserBaseInfo userBaseinfo = userBaseInfoMapper.findByUserId(borrow.getUserId());
        if (userBaseinfo == null) {
            logger.error("查询用户userId：" + userBaseinfo.getUserId() + ",用户不存在");
            return preOrderNo;
        }
        Date createDate = DateUtil.getNow();
        Long userId = userBaseinfo.getUserId();
        XinyanPreNoLog log = new XinyanPreNoLog();
        log.setUserId(userId);
        log.setBorrowId(borrow.getId());
        log.setCreateTime(createDate);
        log.setType(XinyanConstant.XWLD_PRE_ORDER_NO);

        try {
            /** 1、商户号 **/
            String memberId = Global.getValue("xy_member_id");
            /** 2、终端号 **/
            String terminalId = Global.getValue("xy_terminal_id");
            /** 3、请求地址 **/
            String url = Global.getValue("xy_loan_url");
            /** 4、私钥密码 **/
            String pfxpwd = Global.getValue("xy_pfx_pwd");
            Map<String, String> headers = new HashMap<>();

            String tradeDate = new SimpleDateFormat("yyyyMMddHHmmss").format(createDate);// 订单日期
            String transId = UUIDGenerator.getUUID();// 必须入库 并且唯一 商户订单号
            log.setTransId(transId);

            String data = getPreOrderNoData(memberId, terminalId, tradeDate, transId, pfxpwd);

            Map<String, Object> params = new HashMap<>();
            params.put("member_id", memberId);
            params.put("terminal_id", terminalId);
            params.put("data_type", "json");
            params.put("data_content", data);
            String result = HttpRestUtils.postForm(url, headers, params);
            if (StringUtil.isNotBlank(result)) {
                JSONObject resJson = JSONObject.parseObject(result);
                log.setRespCode(String.valueOf(resJson.get("errorCode")));
                log.setRespParams(String.valueOf(resJson.get("errorMsg")));
                log.setIsSuccess(resJson.getBoolean("success") ? 1 : 0);
                Date respTime = DateUtil.getNow();
                log.setRespTime(respTime);
                if (resJson.getBoolean("success")) {
                    JSONObject dataJson = JSONObject.parseObject(resJson.getString("data"));
                    log.setDataCode(dataJson.getString("code"));
                    if ("0".equals(dataJson.getString("code"))) {
                        preOrderNo = dataJson.getString("pre_order_no");
                        log.setPreOrderNo(dataJson.getString("pre_order_no"));
                    } else if("1".equals(dataJson.getString("code"))) {
                        logger.error("用户" + userBaseinfo.getRealName() + "，请求新颜,新颜返回失败");
                    } else {
                        logger.error("用户" + userBaseinfo.getRealName() + "，请求新颜,新颜返回未知异常");
                    }
                }
            } else {
                logger.error("用户" + userBaseinfo.getRealName() + "，请求新颜，同步响应数据为空，result:" + result);
            }
        } catch (Exception e) {
            logger.error("用户" + userBaseinfo.getRealName() + "，请求新颜过程出现异常", e);
        }
        xinyanPreNoLogMapper.save(log);
        return preOrderNo;
    }

    @Override
    public long saveXWLDNotify(String resultData) {
        long borrowId = 0l;
        try {
            if (StringUtil.isNotBlank(resultData)) {
                JSONObject resJson = JSONObject.parseObject(resultData);
                XinyanReqLog log = new XinyanReqLog();
                log.setRespCode(String.valueOf(resJson.get("errorCode")));
                log.setRespParams(String.valueOf(resJson.get("errorMsg")));
                log.setIsSuccess(resJson.getBoolean("success") ? 1 : 0);
                Date respTime = DateUtil.getNow();
                log.setRespTime(respTime);
                if (resJson.getBoolean("success")) {
                    JSONObject dataJson = JSONObject.parseObject(resJson.getString("data"));
                    String idNo = dataJson.getString("id_no");
                    Map<String, Object> queryMap = new HashMap<>();
                    queryMap.put("idNo", idNo);
                    UserBaseInfo userBaseInfo = userBaseInfoMapper.findSelective(queryMap);
                    if (userBaseInfo != null) {
                        Borrow lastBorrow = clBorrowMapper.findLastBorrow(userBaseInfo.getUserId());
                        log.setUserId(userBaseInfo.getUserId());
                        log.setBorrowId(lastBorrow.getId());
                        log.setCreateTime(respTime);
                        log.setType(XinyanConstant.XWLD_TYPE);
                        log.setIsFee(0);
                        log.setDataCode(dataJson.getString("code"));
                        log.setTradeNo(dataJson.getString("trade_no"));
                        if ("0".equals(dataJson.getString("code"))) {
                            log.setIsFee(1);
                            //插入收费记录表
                            CallsOutSideFee callsOutSideFee = new CallsOutSideFee(userBaseInfo.getUserId(), dataJson.getString("trade_no"), CallsOutSideFeeConstant.CALLS_TYPE_XINYAN_XWLD,
                                    CallsOutSideFeeConstant.FEE_XINYAN_XWLD, CallsOutSideFeeConstant.CAST_TYPE_CONSUME, userBaseInfo.getPhone());
                            callsOutSideFeeMapper.save(callsOutSideFee);
                            //保存结果
                            XinyanXwld xinyanXwld = new XinyanXwld(userBaseInfo.getUserId(), lastBorrow.getId(), dataJson.getString("trade_no"), String.valueOf(dataJson.get("result_detail")));
                            xinyanXwldMapper.save(xinyanXwld);
                            borrowId = lastBorrow.getId();
                        } else if("1".equals(dataJson.getString("code"))) {
                            //查询未命中
                            borrowId = lastBorrow.getId();
                        } else {
                            logger.error("用户" + userBaseInfo.getRealName() + "，请求新颜,新颜返回未知异常");
                        }
                        xinyanReqLogMapper.save(log);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("借款订单行为雷达报告处理异常," + e.getMessage());
        }
        return borrowId;
    }

    private int saveLoanReport(String detail, Long userId, String tradeNo, Date respTime) {
        int i = 0;
        JSONObject jsonObject = JSONObject.parseObject(detail);
        XinyanLoanReport xinyanLoanReport = new XinyanLoanReport();
        xinyanLoanReport.setUserId(userId);
        xinyanLoanReport.setTradeNo(tradeNo);
        xinyanLoanReport.setScore(jsonObject.getString("xy_100001"));
        xinyanLoanReport.setCurMaxCredit(jsonObject.getString("xy_100002"));
        xinyanLoanReport.setCurAvgCredit(jsonObject.getString("xy_100003"));
        xinyanLoanReport.setCurLoanCnt30d(jsonObject.getString("xy_100004"));
        xinyanLoanReport.setCurLoanCnt90d(jsonObject.getString("xy_100005"));
        xinyanLoanReport.setCurLoanCnt180d(jsonObject.getString("xy_100006"));
        xinyanLoanReport.setCurLoanTotalCnt(jsonObject.getString("xy_100007"));
        xinyanLoanReport.setCurLoanOrgTotalCnt(jsonObject.getString("xy_100008"));
        xinyanLoanReport.setCurLastToEndLoan(jsonObject.getString("xy_100009"));
        xinyanLoanReport.setCurLoanClearCnt(jsonObject.getString("xy_100010"));
        xinyanLoanReport.setCurOverdueCnt30d(jsonObject.getString("xy_100011"));
        xinyanLoanReport.setCurOverdueCntMore30d(jsonObject.getString("xy_100012"));
        xinyanLoanReport.setQueryOrgCnt(jsonObject.getString("xy_100013"));
        xinyanLoanReport.setQueryCnt(jsonObject.getString("xy_100014"));
        xinyanLoanReport.setLastToEndDays(jsonObject.getString("xy_100015"));
        xinyanLoanReport.setQueryCnt30d(jsonObject.getString("xy_100016"));
        xinyanLoanReport.setQueryCnt90d(jsonObject.getString("xy_100017"));
        xinyanLoanReport.setQueryCnt180d(jsonObject.getString("xy_100018"));
        xinyanLoanReport.setLoanClearNum(jsonObject.getString("xy_100019"));
        xinyanLoanReport.setOverdueCnt30d(jsonObject.getString("xy_100020"));
        xinyanLoanReport.setOverdueCntMore30d(jsonObject.getString("xy_100021"));
        xinyanLoanReport.setWorkDayNotOverdueAmountRadio30d(jsonObject.getString("xy_100022"));
        xinyanLoanReport.setNotOverdueOrderRadio180d(jsonObject.getString("xy_100023"));
        xinyanLoanReport.setOverdueOrderRadio90d(jsonObject.getString("xy_100024"));
        xinyanLoanReport.setMaxLoanRate12m(jsonObject.getString("xy_100025"));
        xinyanLoanReport.setAvgLoanRate12m(jsonObject.getString("xy_100026"));
        xinyanLoanReport.setOverdueOrgCnt6m(jsonObject.getString("xy_100027"));
        xinyanLoanReport.setDdOverdueDays20time(jsonObject.getString("xy_100028"));
        xinyanLoanReport.setDdWorkDayOverdueDays3time(jsonObject.getString("xy_100029"));
        xinyanLoanReport.setDdOverdueDays12m(jsonObject.getString("xy_100030"));
        xinyanLoanReport.setDdMaxOverdueDays3m(jsonObject.getString("xy_100031"));
        xinyanLoanReport.setCreateTime(DateUtil.getNow());
        xinyanLoanReport.setRespTime(respTime);

        i = xinyanLoanReportMapper.save(xinyanLoanReport);
        return i;
    }


    private String getData(String memberId, String terminalId, String tradeDate, String transId, String idNo,
                           String realName, String pfxpwd) throws Exception {
        /** 组装参数 **/
        Map<Object, Object> arrayData = new HashMap<Object, Object>();
        arrayData.put("member_id", memberId);
        arrayData.put("terminal_id", terminalId);
        arrayData.put("trade_date", tradeDate);
        arrayData.put("trans_id", transId);
        arrayData.put("versions", XinyanConstant.VERSION);
        arrayData.put("industry_type", XinyanConstant.INDUSTRY_TYPE);// 参照文档传自己公司对应的行业参数
        arrayData.put("id_no", idNo);
        arrayData.put("id_name", realName);

        JSONObject jsonObjectFromMap = JSONObject.parseObject(JSON.toJSONString(arrayData));
        String xmlOrJson = jsonObjectFromMap.toString();
        logger.info("====请求明文:" + xmlOrJson);

        /** base64 编码 **/
        String base64str = SecurityUtil.Base64Encode(xmlOrJson);
        base64str = base64str.replaceAll("\r\n", "");//重要 避免出现换行空格符
        System.out.println("base64str:" + base64str);
        /** rsa加密 **/
        String pfxName = Global.getValue("xy_pfx_name");
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(pfxName);

        String dataContent = RsaCodingUtil.encryptByPriPfxFile(base64str, inputStream, pfxpwd);// 加密数据

        return dataContent;
    }

    private String getPreOrderNoData(String memberId, String terminalId, String tradeDate, String transId, String pfxpwd) throws Exception {

        /** 组装参数 **/
        Map<Object, Object> arrayData = new HashMap<Object, Object>();
        arrayData.put("member_id", memberId);
        arrayData.put("terminal_id", terminalId);
        arrayData.put("trade_date", tradeDate);
        arrayData.put("trans_id", transId);
        arrayData.put("versions", XinyanConstant.XWLD_VERSION);
        arrayData.put("industry_type", XinyanConstant.INDUSTRY_TYPE);// 参照文档传自己公司对应的行业参数
        arrayData.put("product_type", XinyanConstant.PRODUCT_TYPE_XWLD);
        arrayData.put("source", "SDK");
        //回调地址
        arrayData.put("url", Global.getValue("server_host") + "/api/xinyanNotify.htm");

        JSONObject jsonObjectFromMap = JSONObject.parseObject(JSON.toJSONString(arrayData));
        String xmlOrJson = jsonObjectFromMap.toString();
        logger.info("====请求明文:" + xmlOrJson);

        /** base64 编码 **/
        String base64str = SecurityUtil.Base64Encode(xmlOrJson);
        base64str = base64str.replaceAll("\r\n", "");//重要 避免出现换行空格符
        System.out.println("base64str:" + base64str);
        /** rsa加密 **/
        String pfxName = Global.getValue("xy_pfx_name");
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(pfxName);

        String dataContent = RsaCodingUtil.encryptByPriPfxFile(base64str, inputStream, pfxpwd);// 加密数据

        return dataContent;
    }
}

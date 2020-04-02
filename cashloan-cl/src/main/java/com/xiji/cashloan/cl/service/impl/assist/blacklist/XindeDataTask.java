package com.xiji.cashloan.cl.service.impl.assist.blacklist;

import com.alibaba.fastjson.JSON;
import com.xiji.cashloan.cl.domain.BlacklistXindeData;
import com.xiji.cashloan.cl.model.xindedata.XdAdvanceResult;
import com.xiji.cashloan.cl.model.xindedata.XdBlackResult;
import com.xiji.cashloan.cl.model.xindedata.XindeResponse;
import com.xiji.cashloan.cl.service.BlacklistXindeDataService;
import com.xiji.cashloan.cl.util.SHAUtils;
import com.xiji.cashloan.cl.util.black.CollectionUtil;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.util.DateUtil;
import com.xiji.cashloan.core.common.util.HttpsUtil;
import com.xiji.cashloan.core.common.util.StringUtil;
import com.xiji.cashloan.core.domain.Borrow;
import com.xiji.cashloan.core.domain.UserBaseInfo;
import com.xiji.cashloan.core.service.UserBaseInfoService;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tool.util.BeanUtil;

/**
 * @Auther: king
 * @Date: 2018/12/19 12:54
 * @Description:
 */
public class XindeDataTask implements Runnable {
    public static final Logger logger = LoggerFactory.getLogger(XindeDataTask.class);
    private Borrow borrow;

    public XindeDataTask(Borrow borrow) {
        this.borrow = borrow;
    }
    @Override
    public void run() {
        try {
            int start = DateUtil.getNowTime();
            BlacklistXindeDataService xindeDataService = (BlacklistXindeDataService) BeanUtil.getBean("blacklistXindeDataService");
            UserBaseInfoService userBaseInfoService = (UserBaseInfoService) BeanUtil.getBean("userBaseInfoService");
            UserBaseInfo baseInfo = userBaseInfoService.findByUserId(borrow.getUserId());
            String host = Global.getValue("xinde_data_url");
            StringBuilder url = new StringBuilder();
            final String secret = Global.getValue("xinde_data_appSecret");
            final String appId = Global.getValue("xinde_data_appId");
            long time = System.currentTimeMillis()/1000;
            Map<String, Object> signMap = new HashMap<>();
            signMap.put("appid", appId);
            signMap.put("time", time);
            String signature = SHAUtils.decrypt(SHAUtils.getOrderByLexicographic(signMap,secret));
            Map<String, Object> param = new HashMap<>();
            param.put("type", "blackList");
            param.put("phoneNo", baseInfo.getPhone());
            param.put("userID", baseInfo.getIdNo());

            url.append(host).append("?appid=").append(appId).append("&time=").append(time).append("&signature=").append(signature);
            String resp = HttpsUtil.postStrClient(url.toString(), JSON.toJSONString(param));
            XindeResponse xindeResponse = (XindeResponse) JSON.parseObject(resp, XindeResponse.class);
            if (xindeResponse != null && StringUtil.equals("done", xindeResponse.getStatus())) {
                int end = DateUtil.getNowTime();
                if (CollectionUtil.isNotEmpty(xindeResponse.getResult())) {
                    BlacklistXindeData xindeData = new BlacklistXindeData();
                    XdBlackResult xdBlackResult = xindeResponse.getResult().get(0);
                    xindeData.setBorrowId(borrow.getId());
                    xindeData.setCreateTime(new Date());
                    xindeData.setIdCard(baseInfo.getIdNo());
                    xindeData.setPhone(baseInfo.getPhone());

                    xindeData.setFirstLoanTime(xdBlackResult.getFirstLoanTime());
                    xindeData.setTotalLoanCount(xdBlackResult.getTotalLoanCount());
                    xindeData.setTotalOverdueCount(xdBlackResult.getTotalOverDueCount());
                    xindeData.setLongestOverduePaid(xdBlackResult.getLongestOverDuePaid());
                    xindeData.setCurrentOverdueAmount(xdBlackResult.getCurrentOverDueAmount());
                    xindeData.setCurrentOverdueCount(xdBlackResult.getCurrentOverDueCount());
                    xindeData.setLongestOverdueUnpaid(xdBlackResult.getLongestOverDueUnpaid());
                    xindeData.setLastLoanRefuseTime(xdBlackResult.getLastLoanRefuseTime());
                    xindeData.setLastLoanRefuseReason(xdBlackResult.getLastLoanRefuseReason());
                    xindeData.setIsLastloanRefused(String.valueOf(xdBlackResult.isLastLoanRefused()));
                    xindeData.setLastModifyTime(new Date());
                    if (CollectionUtil.isNotEmpty(xindeResponse.getAdvanceResult())){
                        XdAdvanceResult xdAdvanceResult = xindeResponse.getAdvanceResult().get(0);
                        if (xdAdvanceResult != null) {
                            xindeData.setOverDue90ContactsCount(xdAdvanceResult.getOverDue90ContactsCount());
                        }
                    }
//                    xindeData.setRemark(JSON.toJSONString(xindeResponse.getAdvanceResult()));//预留字段
                    xindeDataService.insert(xindeData);
                }
                logger.info("borrowId" + borrow.getId() + "处理信德数据，耗时" + (end - start) + "秒");
            } else {
                int end = DateUtil.getNowTime();
                logger.info("borrowId" + borrow.getId() + "处理信德数据，调接口失败，耗时" + (end - start) + "秒");
            }
        } catch (Exception e) {
            // 运营商报告保存失败
            logger.error("严重问题，borrowId:" + borrow.getId() + "处理信德数据失败", e);
            return;
        }
    }
}

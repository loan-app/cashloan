package com.xiji.cashloan.cl.model.pay;

import com.xiji.cashloan.cl.domain.PayReqLog;
import com.xiji.cashloan.cl.service.PayReqLogService;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tool.util.BeanUtil;
import tool.util.DateUtil;
import tool.util.IPUtil;

/**
 * @Auther: king
 * @Date: 2018/11/27 15:14
 * @Description:
 */
public class BasePay {
    protected void saveReqLog(String service,String orderNo,String signParams,String reqDetail) {
        PayReqLogService payReqLogService = (PayReqLogService) BeanUtil.getBean("payReqLogService");
        PayReqLog payReqLog = new PayReqLog();
        payReqLog.setOrderNo(orderNo);
        payReqLog.setService(service);
        payReqLog.setCreateTime(DateUtil.getNow());
        payReqLog.setParams(signParams);
        payReqLog.setReqDetailParams(reqDetail);

        if (null != ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())){
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String ip = IPUtil.getRemortIP(request);
            payReqLog.setIp(ip);
        }
        payReqLogService.save(payReqLog);
    }

    /**
     * 更新返回数据
     *
     * @param orderNo
     * @param resp
     */
    protected void modifyReqLog(String orderNo, String resp) {
        PayReqLogService payReqLogService = (PayReqLogService) BeanUtil.getBean("payReqLogService");
        PayReqLog reqLog = payReqLogService.findByOrderNo(orderNo);
        if (null == reqLog) {
            return;
        }
        reqLog.setReturnParams(resp);
        reqLog.setReturnTime(DateUtil.getNow());
        payReqLogService.updateById(reqLog);
    }


    protected boolean isException(String resp) {
        return StringUtils.startsWith(resp, "300");
    }
}

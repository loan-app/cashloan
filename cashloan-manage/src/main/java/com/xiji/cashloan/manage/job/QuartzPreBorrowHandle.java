package com.xiji.cashloan.manage.job;

import com.xiji.cashloan.cl.domain.BankCard;
import com.xiji.cashloan.cl.domain.PayLog;
import com.xiji.cashloan.cl.domain.ProfitAmount;
import com.xiji.cashloan.cl.model.PayLogModel;
import com.xiji.cashloan.cl.model.pay.common.PayCommonHelper;
import com.xiji.cashloan.cl.model.pay.common.PayCommonUtil;
import com.xiji.cashloan.cl.model.pay.common.vo.request.PaymentReqVo;
import com.xiji.cashloan.cl.model.pay.common.vo.response.PaymentResponseVo;
import com.xiji.cashloan.cl.monitor.BusinessExceptionMonitor;
import com.xiji.cashloan.cl.service.BankCardService;
import com.xiji.cashloan.cl.service.ClBorrowService;
import com.xiji.cashloan.cl.service.PayLogService;
import com.xiji.cashloan.cl.service.ProfitAmountService;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.exception.ServiceException;
import com.xiji.cashloan.core.domain.Borrow;
import com.xiji.cashloan.core.domain.UserBaseInfo;
import com.xiji.cashloan.core.model.BorrowModel;
import com.xiji.cashloan.core.model.UserBaseInfoModel;
import com.xiji.cashloan.core.service.UserBaseInfoService;
import com.xiji.cashloan.manage.domain.QuartzInfo;
import com.xiji.cashloan.manage.domain.QuartzLog;
import com.xiji.cashloan.manage.service.QuartzInfoService;
import com.xiji.cashloan.manage.service.QuartzLogService;
import com.xiji.cashloan.rc.service.SceneBusinessLogService;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import tool.util.BeanUtil;
import tool.util.DateUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by szb on 19/5/16.
 */
@Component
@Lazy(value = false)
public class QuartzPreBorrowHandle implements Job {
    private static final Logger logger = Logger.getLogger(QuartzPreBorrowHandle.class);

    /**
     * 卡住待机审订单处理
     * @throws ServiceException
     */
    public String preBorrowHandle() {
        ClBorrowService clBorrowService = (ClBorrowService) BeanUtil.getBean("clBorrowService");
        SceneBusinessLogService sceneBusinessLogService = (SceneBusinessLogService) BeanUtil.getBean("sceneBusinessLogService");
        //查询待机审订单
        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("state", BorrowModel.STATE_PRE);
        List<Borrow> borrowList = clBorrowService.findBorrowByMap(searchMap);

        String quartzRemark = null;
        int succeed = 0;
        int fail = 0;
        int total = 0;
        for (Borrow borrow : borrowList) {
            try {
                Date d1 = borrow.getCreateTime();
                Date d2 = DateUtil.getNow();
                long diff = d2.getTime() - d1.getTime();
                //如果超过了5分钟,仍然待机审,触发
                if (diff > 5 * 60 * 1000) {
                    sceneBusinessLogService.updateBusinessLogByBorrowId(borrow.getId());
                    clBorrowService.reVerifyBorrowData(borrow.getId());
                }
                succeed++;
                total++;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                fail++;
                total++;
            }
        }
        quartzRemark = "执行总次数"+total+",成功"+succeed+"次,失败"+fail+"次";
        return quartzRemark;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        QuartzInfoService quartzInfoService = (QuartzInfoService) BeanUtil.getBean("quartzInfoService");
        QuartzLogService quartzLogService = (QuartzLogService) BeanUtil.getBean("quartzLogService");
        // 查询当前任务信息
        QuartzInfo quartzInfo = quartzInfoService.findByCode("preBorrowHandle");
        QuartzLog quartzLog = new QuartzLog();
        Map<String, Object> qiData = new HashMap<>();
        qiData.put("id", quartzInfo.getId());
        quartzLog.setQuartzId(quartzInfo.getId());
        quartzLog.setStartTime(DateUtil.getNow());

        try {
            String remark = preBorrowHandle();
            quartzLog.setTime(DateUtil.getNow().getTime() - quartzLog.getStartTime().getTime());
            quartzLog.setResult("10");
            quartzLog.setRemark(remark);
            qiData.put("succeed", quartzInfo.getSucceed() + 1);
            logger.info("待机审订单处理完成...");
        } catch (Exception e) {
            quartzLog.setResult("20");
            qiData.put("fail", quartzInfo.getFail() + 1);
            logger.error(e.getMessage(), e);
        } finally {
            logger.info("保存定时任务日志");
            quartzLogService.save(quartzLog);
            quartzInfoService.update(qiData);
        }

    }
}

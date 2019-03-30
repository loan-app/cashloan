package com.xiji.cashloan.manage.job;

import com.xiji.cashloan.cl.service.ManualReviewOrderService;
import com.xiji.cashloan.core.common.util.DateUtil;
import com.xiji.cashloan.manage.domain.QuartzInfo;
import com.xiji.cashloan.manage.domain.QuartzLog;
import com.xiji.cashloan.manage.service.QuartzInfoService;
import com.xiji.cashloan.manage.service.QuartzLogService;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import tool.util.BeanUtil;

import java.util.HashMap;
import java.util.Map;


/**
 * @auther : wnb
 * @date : 2019/3/29
 * @describe :
 */
@Component
@Lazy(value = false)
public class QuartzDistributionOrder implements Job {

    private static final Logger logger = Logger.getLogger(QuartzDistributionOrder.class);

    /**
     * 订单自动分配
     * @return
     */
    public String automaticDistributionOrder(){

        logger.info("进入订单自动分配.....");
        ManualReviewOrderService manualReviewOrderService = (ManualReviewOrderService) BeanUtil.getBean("manualReviewOrderService");

        int count = manualReviewOrderService.automaticDistributionOrder();

        return "成功分配了"+count+"笔订单";
    }



    @Override
    public void execute(JobExecutionContext context){
        QuartzInfoService quartzInfoService = (QuartzInfoService) BeanUtil.getBean("quartzInfoService");
        QuartzLogService quartzLogService = (QuartzLogService) BeanUtil.getBean("quartzLogService");
        // 查询当前任务信息
        QuartzInfo quartzInfo = quartzInfoService.findByCode("automaticDistributionOrder");
        Map<String, Object> qiData = new HashMap<>();
        qiData.put("id", quartzInfo.getId());

        QuartzLog quartzLog = new QuartzLog();
        quartzLog.setQuartzId(quartzInfo.getId());
        quartzLog.setStartTime(DateUtil.getNow());
        try {
            String remark = this.automaticDistributionOrder();
            quartzLog.setTime(DateUtil.getNow().getTime() - quartzLog.getStartTime().getTime());
            quartzLog.setResult("10");
            quartzLog.setRemark(remark);
            qiData.put("succeed", quartzInfo.getSucceed() + 1);
        } catch (Exception e) {
            quartzLog.setResult("20");
            qiData.put("fail", quartzInfo.getFail() + 1);
            logger.error(e.getMessage(), e);
        } finally {
            logger.info("保存订单自动分配定时任务执行记录");
            quartzLogService.save(quartzLog);
            quartzInfoService.update(qiData);
        }
    }
}

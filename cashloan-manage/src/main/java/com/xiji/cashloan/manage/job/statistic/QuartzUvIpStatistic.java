package com.xiji.cashloan.manage.job.statistic;

import com.xiji.cashloan.cl.service.ChannelIpService;
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


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther : wnb
 * @date : 2019/2/15
 * @describe : 渠道ip记录
 */
@Component
@Lazy(value = false)
public class QuartzUvIpStatistic implements Job {

    private static final Logger logger = Logger.getLogger(QuartzUvIpStatistic.class);


    /**
     * 渠道ip记录清除
     * @return
     */
    public String deleteChannelIpStatistic(){
        logger.info("进入渠道ip记录清除.....");
        ChannelIpService channelIpService = (ChannelIpService)BeanUtil.getBean("channelIpService");

        Map<String,Object> params = new HashMap<>();
        Date dateBefore = DateUtil.getDateBefore(-1,new Date());

        params.put("createDate",DateUtil.dateStr(dateBefore, "yyyy-MM-dd"));

        int result=channelIpService.deleteByCreateDate(params);


        return "删除渠道ip记录"+result+"条.";
    }


    @Override
    public void execute(JobExecutionContext context){
        QuartzInfoService quartzInfoService = (QuartzInfoService) BeanUtil.getBean("quartzInfoService");
        QuartzLogService quartzLogService = (QuartzLogService) BeanUtil.getBean("quartzLogService");
        // 查询当前任务信息
        QuartzInfo quartzInfo = quartzInfoService.findByCode("deleteChannelIp");
        Map<String, Object> qiData = new HashMap<>();
        qiData.put("id", quartzInfo.getId());

        QuartzLog quartzLog = new QuartzLog();
        quartzLog.setQuartzId(quartzInfo.getId());
        quartzLog.setStartTime(DateUtil.getNow());
        try {
            String remark = deleteChannelIpStatistic();
            quartzLog.setTime(DateUtil.getNow().getTime() - quartzLog.getStartTime().getTime());
            quartzLog.setResult("10");
            quartzLog.setRemark(remark);
            qiData.put("succeed", quartzInfo.getSucceed() + 1);
        } catch (Exception e) {
            quartzLog.setResult("20");
            qiData.put("fail", quartzInfo.getFail() + 1);
            logger.error(e.getMessage(), e);
        } finally {
            logger.info("保存渠道ip记录清除定时任务执行记录");
            quartzLogService.save(quartzLog);
            quartzInfoService.update(qiData);
        }
    }
}

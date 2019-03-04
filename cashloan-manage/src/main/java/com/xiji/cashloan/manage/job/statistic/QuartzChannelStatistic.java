package com.xiji.cashloan.manage.job.statistic;

import com.xiji.cashloan.cl.domain.statistic.ChannelStatisticData;
import com.xiji.cashloan.cl.service.statistic.ChannelStatisticDataService;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther : wnb
 * @date : 2019/2/15
 * @describe : 渠道数据统计
 */
@Component
@Lazy(value = false)
public class QuartzChannelStatistic implements Job{

    private static final Logger logger = Logger.getLogger(QuartzChannelStatistic.class);


    /**
     * 保存渠道统计数据
     * @return
     */
    public String insertChannelStatistic(){

        logger.info("进入渠道统计数据.....");
        ChannelStatisticDataService channelStatisticDataService = (ChannelStatisticDataService)BeanUtil.getBean("channelStatisticDataService");
        Date lateTime = channelStatisticDataService.getLateTime();

        Date dateBefore = DateUtil.getDateBefore(-1,new Date());
        Map<String,Object> params = new HashMap<>();
        DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");

        String endDateStr = dateFormat.format(dateBefore);
        if (lateTime == null){
            params.put("endDate",endDateStr);
        }else {
            params.put("endDate",endDateStr);
            String startDateStr = dateFormat.format(lateTime);
            params.put("startDate",startDateStr);
        }
        List<ChannelStatisticData> channelStatisticDataList = channelStatisticDataService.listChannelStatisticData(params);
        for (ChannelStatisticData channelStatisticData : channelStatisticDataList){
            channelStatisticData.setCreateTime(new Date());
            channelStatisticDataService.insert(channelStatisticData);
        }
        return "成功统计了"+channelStatisticDataList.size()+"天渠道数据报表";
    }


    @Override
    public void execute(JobExecutionContext context){
        QuartzInfoService quartzInfoService = (QuartzInfoService) BeanUtil.getBean("quartzInfoService");
        QuartzLogService quartzLogService = (QuartzLogService) BeanUtil.getBean("quartzLogService");
        // 查询当前任务信息
        QuartzInfo quartzInfo = quartzInfoService.findByCode("insertChannelStatistic");
        Map<String, Object> qiData = new HashMap<>();
        qiData.put("id", quartzInfo.getId());

        QuartzLog quartzLog = new QuartzLog();
        quartzLog.setQuartzId(quartzInfo.getId());
        quartzLog.setStartTime(DateUtil.getNow());
        try {
            String remark = insertChannelStatistic();
            quartzLog.setTime(DateUtil.getNow().getTime() - quartzLog.getStartTime().getTime());
            quartzLog.setResult("10");
            quartzLog.setRemark(remark);
            qiData.put("succeed", quartzInfo.getSucceed() + 1);
        } catch (Exception e) {
            quartzLog.setResult("20");
            qiData.put("fail", quartzInfo.getFail() + 1);
            logger.error(e.getMessage(), e);
        } finally {
            logger.info("保存用户统计数据定时任务执行记录");
            quartzLogService.save(quartzLog);
            quartzInfoService.update(qiData);
        }
    }
}

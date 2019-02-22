package com.xiji.cashloan.manage.job.statistic;

import com.xiji.cashloan.cl.domain.statistic.AuditorStatisticData;
import com.xiji.cashloan.cl.service.statistic.AuditorStatisticDataService;
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
 * @date : 2019/2/20
 * @describe : 审核人员统计
 */

@Component
@Lazy(value = false)
public class QuartzAuditorStatistic implements Job {

    private static final Logger logger = Logger.getLogger(QuartzAuditorStatistic.class);


    /**
     * 添加审核人员统计
     * @return
     */
    public String insertAuditorStatistic(){

        logger.info("进入审核人员统计数据.....");
        AuditorStatisticDataService auditorStatisticDataService = (AuditorStatisticDataService) BeanUtil.getBean("auditorStatisticDataService");
        Date lateDate = auditorStatisticDataService.getLateDate();

        Map<String,Object> params = new HashMap<>();

        Date dateBefore = DateUtil.getDateBefore(-1,new Date());

        DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        String endDateStr = dateFormat.format(dateBefore);
        List<AuditorStatisticData> auditorStatisticDataList;
        params.put("endDate",endDateStr);
        if (lateDate != null) {
            String startDateStr = dateFormat.format(lateDate);
            params.put("startDate", startDateStr);
        }
        auditorStatisticDataList = auditorStatisticDataService.listAuditorStatisticData(params);
        for(AuditorStatisticData auditorStatisticData:auditorStatisticDataList){
            auditorStatisticData.setCreateTime(new Date());
            auditorStatisticDataService.insert(auditorStatisticData);
        }
        return "成功统计了"+auditorStatisticDataList.size()+"天审核人员数据报表";
    }

    @Override
    public void execute(JobExecutionContext context){
        QuartzInfoService quartzInfoService = (QuartzInfoService) BeanUtil.getBean("quartzInfoService");
        QuartzLogService quartzLogService = (QuartzLogService) BeanUtil.getBean("quartzLogService");
        // 查询当前任务信息
        QuartzInfo quartzInfo = quartzInfoService.findByCode("insertAuditorStatistic");
        Map<String, Object> qiData = new HashMap<>();
        qiData.put("id", quartzInfo.getId());

        QuartzLog quartzLog = new QuartzLog();
        quartzLog.setQuartzId(quartzInfo.getId());
        quartzLog.setStartTime(DateUtil.getNow());
        try {
            String remark = this.insertAuditorStatistic();
            quartzLog.setTime(DateUtil.getNow().getTime() - quartzLog.getStartTime().getTime());
            quartzLog.setResult("10");
            quartzLog.setRemark(remark);
            qiData.put("succeed", quartzInfo.getSucceed() + 1);
        } catch (Exception e) {
            quartzLog.setResult("20");
            qiData.put("fail", quartzInfo.getFail() + 1);
            logger.error(e.getMessage(), e);
        } finally {
            logger.info("保存审核人员统计数据定时任务执行记录");
            quartzLogService.save(quartzLog);
            quartzInfoService.update(qiData);
        }
    }
}

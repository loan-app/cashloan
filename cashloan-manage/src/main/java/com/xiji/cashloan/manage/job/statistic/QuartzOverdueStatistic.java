package com.xiji.cashloan.manage.job.statistic;

import com.xiji.cashloan.cl.domain.statistic.OverdueStatisticData;
import com.xiji.cashloan.cl.service.statistic.OverdueStatisticDataService;
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
 * @date : 2019/3/7
 * @describe :逾期统计定时器
 */

@Component
@Lazy(value = false)
public class QuartzOverdueStatistic implements Job {


    private static final Logger logger = Logger.getLogger(QuartzOverdueStatistic.class);


    /**
     * 添加审核人员统计
     * @return
     */
    public String insertOverdueStatistic(){

        logger.info("进入逾期统计数据.....");
        OverdueStatisticDataService overdueStatisticDataService = (OverdueStatisticDataService) BeanUtil.getBean("overdueStatisticDataService");
        Date lateDate = overdueStatisticDataService.getLateDate();

        Map<String,Object> params = new HashMap<>();

        Date dateBefore = DateUtil.getDateBefore(-1,new Date());

        DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        String endDateStr = dateFormat.format(dateBefore);
        List<OverdueStatisticData> overdueStatisticDataList;
        params.put("endDate",endDateStr);
        if (lateDate != null) {
            String startDateStr = dateFormat.format(lateDate);
            params.put("startDate", startDateStr);
        }
        overdueStatisticDataList = overdueStatisticDataService.listOverdueStatisticData(params);
        for(OverdueStatisticData overdueStatisticData:overdueStatisticDataList){
            overdueStatisticData.setCreateTime(new Date());
            overdueStatisticDataService.insert(overdueStatisticData);
        }
        return "成功统计了"+overdueStatisticDataList.size()+"天逾期数据报表";
    }

    @Override
    public void execute(JobExecutionContext context){
        QuartzInfoService quartzInfoService = (QuartzInfoService) BeanUtil.getBean("quartzInfoService");
        QuartzLogService quartzLogService = (QuartzLogService) BeanUtil.getBean("quartzLogService");
        // 查询当前任务信息
        QuartzInfo quartzInfo = quartzInfoService.findByCode("insertOverdueStatistic");
        Map<String, Object> qiData = new HashMap<>();
        qiData.put("id", quartzInfo.getId());

        QuartzLog quartzLog = new QuartzLog();
        quartzLog.setQuartzId(quartzInfo.getId());
        quartzLog.setStartTime(DateUtil.getNow());
        try {
            String remark = this.insertOverdueStatistic();
            quartzLog.setTime(DateUtil.getNow().getTime() - quartzLog.getStartTime().getTime());
            quartzLog.setResult("10");
            quartzLog.setRemark(remark);
            qiData.put("succeed", quartzInfo.getSucceed() + 1);
        } catch (Exception e) {
            quartzLog.setResult("20");
            qiData.put("fail", quartzInfo.getFail() + 1);
            logger.error(e.getMessage(), e);
        } finally {
            logger.info("保存逾期统计数据定时任务执行记录");
            quartzLogService.save(quartzLog);
            quartzInfoService.update(qiData);
        }
    }
}

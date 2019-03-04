package com.xiji.cashloan.manage.job.statistic;

import com.xiji.cashloan.cl.domain.statistic.UserStatisticData;
import com.xiji.cashloan.cl.service.statistic.UserStatisticDataService;
import com.xiji.cashloan.cl.util.black.CollectionUtil;
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
 * @describe : 用户数据统计
 */
@Component
@Lazy(value = false)
public class QuartzUserStatistic implements Job {

    private static final Logger logger = Logger.getLogger(QuartzUserStatistic.class);


    /**
     * 保存用户统计数据
     * @return
     */
    public String insertUserStatistic(){
        logger.info("进入用户数据统计.....");
        UserStatisticDataService userStatisticDataService = (UserStatisticDataService)BeanUtil.getBean("userStatisticDataService");
        Date date = userStatisticDataService.getLateDate();

        Map<String,Object> params = new HashMap<>();

        Date dateBefore = DateUtil.getDateBefore(-1,new Date());

        DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        String endDateStr = dateFormat.format(dateBefore);
        List<UserStatisticData> userStatisticDataList;
        params.put("endDate",endDateStr);

        if (date != null) {
            String startDateStr = dateFormat.format(date);
            params.put("startDate", startDateStr);
        }

        userStatisticDataList = userStatisticDataService.listUserStatisticData(params);
        if (CollectionUtil.isNotEmpty(userStatisticDataList)){
            for(UserStatisticData userStatisticData : userStatisticDataList){
                userStatisticData.setCreateTime(new Date());
                userStatisticDataService.insert(userStatisticData);
            }
        }
        return "成功统计了"+userStatisticDataList.size()+"天用户数据报表";
    }


    @Override
    public void execute(JobExecutionContext context){
        QuartzInfoService quartzInfoService = (QuartzInfoService) BeanUtil.getBean("quartzInfoService");
        QuartzLogService quartzLogService = (QuartzLogService) BeanUtil.getBean("quartzLogService");
        // 查询当前任务信息
        QuartzInfo quartzInfo = quartzInfoService.findByCode("insertUserStatistic");
        Map<String, Object> qiData = new HashMap<>();
        qiData.put("id", quartzInfo.getId());

        QuartzLog quartzLog = new QuartzLog();
        quartzLog.setQuartzId(quartzInfo.getId());
        quartzLog.setStartTime(DateUtil.getNow());
        try {
            String remark = insertUserStatistic();
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

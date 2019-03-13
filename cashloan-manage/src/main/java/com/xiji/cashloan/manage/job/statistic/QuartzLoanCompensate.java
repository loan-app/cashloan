package com.xiji.cashloan.manage.job.statistic;

import com.xiji.cashloan.cl.service.statistic.ChannelStatisticDataService;
import com.xiji.cashloan.cl.service.statistic.UserStatisticDataService;
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
import java.util.Map;

/**
 * @auther : wnb
 * @date : 2019/3/12
 * @describe :下款数 补偿操作
 */
@Component
@Lazy(value = false)
public class QuartzLoanCompensate implements Job {


    private static final Logger logger = Logger.getLogger(QuartzLoadStatistic.class);


    /**
     * 添加放款补偿统计
     * @return
     */
    public String updateLoanStatistic(){
        logger.info("进入放款补偿统计.....");

        UserStatisticDataService userStatisticDataService = (UserStatisticDataService)BeanUtil.getBean("userStatisticDataService");

        Map<String,Object> params = new HashMap<>();
        Date dateBefore = DateUtil.getDateBefore(-1,new Date());
        DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        params.put("startDate",dateFormat.format(dateBefore));
        params.put("endDate",dateFormat.format(dateBefore));
        int updateUserLoanCount = userStatisticDataService.updateUserLoanStatistic(params);

        ChannelStatisticDataService channelStatisticDataService = (ChannelStatisticDataService) BeanUtil.getBean("channelStatisticDataService");
        int updateChannelLoanCount = channelStatisticDataService.updateChannelLoan(params);
        return "成功更新用户统计下款数"+updateUserLoanCount+"条；渠道统计下款数"+updateChannelLoanCount+"条";
    }

    @Override
    public void execute(JobExecutionContext context){
        QuartzInfoService quartzInfoService = (QuartzInfoService) BeanUtil.getBean("quartzInfoService");
        QuartzLogService quartzLogService = (QuartzLogService) BeanUtil.getBean("quartzLogService");
        // 查询当前任务信息
        QuartzInfo quartzInfo = quartzInfoService.findByCode("updateLoanStatistic");
        Map<String, Object> qiData = new HashMap<>();
        qiData.put("id", quartzInfo.getId());

        QuartzLog quartzLog = new QuartzLog();
        quartzLog.setQuartzId(quartzInfo.getId());
        quartzLog.setStartTime(DateUtil.getNow());
        try {
            String remark = updateLoanStatistic();
            quartzLog.setTime(DateUtil.getNow().getTime() - quartzLog.getStartTime().getTime());
            quartzLog.setResult("10");
            quartzLog.setRemark(remark);
            qiData.put("succeed", quartzInfo.getSucceed() + 1);
        } catch (Exception e) {
            quartzLog.setResult("20");
            qiData.put("fail", quartzInfo.getFail() + 1);
            logger.error(e.getMessage(), e);
        } finally {
            logger.info("保存放款统计数据定时任务执行记录");
            quartzLogService.save(quartzLog);
            quartzInfoService.update(qiData);
        }
    }
}

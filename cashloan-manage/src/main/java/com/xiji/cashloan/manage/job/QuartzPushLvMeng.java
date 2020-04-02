package com.xiji.cashloan.manage.job;

import com.alibaba.fastjson.JSON;
import com.xiji.cashloan.cl.model.UserOverdueModel;
import com.xiji.cashloan.cl.service.statistic.OverdueStatisticDataService;
import com.xiji.cashloan.cl.util.LmApiUtil;
import com.xiji.cashloan.core.common.exception.ServiceException;
import com.xiji.cashloan.core.common.util.DateUtil;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import tool.util.BeanUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 推送绿盟黑名单逾期超过三天用户
 * @author zjy
 * @version 1.0.5
 */
@Component
@Lazy(value = false)
public class QuartzPushLvMeng implements Job {
    private static final Logger logger = Logger.getLogger(QuartzPushLvMeng.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            pushLvMengOverdue();
        } catch (ServiceException e) {
            logger.error(e.getMessage(),e);
        }
    }

    public void pushLvMengOverdue() throws ServiceException {
        OverdueStatisticDataService overdueStatisticDataService = (OverdueStatisticDataService) BeanUtil.getBean("overdueStatisticDataService");

        Map<String, Object> params = new HashMap<>();
        DateFormat dateFormat2= new SimpleDateFormat("yyyy-MM-dd");
        String startDateStr = dateFormat2.format(DateUtil.getDateBefore(-4, new Date()));
        params.put("startDate", startDateStr);
        List<UserOverdueModel> list = overdueStatisticDataService.queryOverdueData(params);
        if(null != list
                && list.size() > 0) {
            List<LmApiUtil.Black> blackList = new ArrayList<>();
            for(UserOverdueModel model:list) {
                LmApiUtil.Black back = new LmApiUtil.Black();
                back.setIdcard(model.getIdcard());
                back.setPhone(model.getPhone());
                back.setName(model.getName());
                back.setType(1);
                blackList.add(back);
            }
            try {
                logger.info("推送绿盟黑名单逾期超过三天用户:{}" + JSON.toJSONString(blackList));
                LmApiUtil.subBlack(blackList);
            } catch (Exception e) {
                logger.error("推送绿盟黑名单逾期超过三天用户出错：{}", e);
            }

        }


    }


}

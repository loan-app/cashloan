package com.xiji.cashloan.manage.job;

import com.xiji.cashloan.cl.service.UserAuthService;
import com.xiji.cashloan.core.common.context.Global;
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
 * 运营商认证定时更新
 * @author wnb
 * @date :2019/1/15
 */
@Component
@Lazy(value = false)
public class QuartzUserAuth implements Job {


    private static final Logger logger = Logger.getLogger(QuartzUserAuth.class);


    /**
     * 更新运营商认证
     * @return
     */
    public String updateUserAuth(){
        logger.info("进入代扣运行商认证任务...");
        UserAuthService userAuthService = (UserAuthService)BeanUtil.getBean("userAuthService");
        int authenticationCycle = Global.getInt("authentication_cycle");
        Date updateAuthTime = DateUtil.getDateBefore(-authenticationCycle,new Date());

        Map<String,Object> userAuth = new HashMap<>();
        userAuth.put("phoneState","10");
        userAuth.put("updateAuthTime",updateAuthTime);
        int success = userAuthService.updateAuthByTime(userAuth);
        return "成功更新"+success+"个用户运营商认证";
    }

    @Override
    public void execute(JobExecutionContext context){
        QuartzInfoService quartzInfoService = (QuartzInfoService) BeanUtil.getBean("quartzInfoService");
        QuartzLogService quartzLogService = (QuartzLogService) BeanUtil.getBean("quartzLogService");
        // 查询当前任务信息
        QuartzInfo quartzInfo = quartzInfoService.findByCode("doUpdateUserAuth");
        Map<String, Object> qiData = new HashMap<>();
        qiData.put("id", quartzInfo.getId());

        QuartzLog quartzLog = new QuartzLog();
        quartzLog.setQuartzId(quartzInfo.getId());
        quartzLog.setStartTime(DateUtil.getNow());
        try {
            String remark = updateUserAuth();
            quartzLog.setTime(DateUtil.getNow().getTime() - quartzLog.getStartTime().getTime());
            quartzLog.setResult("10");
            quartzLog.setRemark(remark);
            qiData.put("succeed", quartzInfo.getSucceed() + 1);
        } catch (Exception e) {
            quartzLog.setResult("20");
            qiData.put("fail", quartzInfo.getFail() + 1);
            logger.error(e.getMessage(), e);
        } finally {
            logger.info("保存代扣还款定时任务执行记录");
            quartzLogService.save(quartzLog);
            quartzInfoService.update(qiData);
        }

    }
}

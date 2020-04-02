package com.xiji.cashloan.cl.service.impl.assist.blacklist;

import com.xiji.cashloan.cl.domain.BlacklistTask;
import com.xiji.cashloan.cl.service.BlacklistTaskService;
import com.xiji.cashloan.cl.util.GroovyUtil;
import com.xiji.cashloan.cl.util.black.CollectionUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import tool.util.BeanUtil;
import tool.util.StringUtil;

/**
 * @Auther: king
 * @Date: 2018/12/20 15:06
 * @Description:
 */
public class BlacklistUtil {
    private static final Logger logger = Logger.getLogger(BlacklistUtil.class);
    private static final Map<String, BlacklistProcess> baseTaskHashMap = new HashMap<>();
    private static ReentrantLock lock = new ReentrantLock();
    private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
    public static Map<String, BlacklistProcess> getBaseTaskHashMap() {
        return baseTaskHashMap;
    }
    public static void initConfig() {
        logger.info("初始化系统BlacklistUtil...");
        // 初始化系统BlacklistUtil
        int initialDelay = 10;    //10秒
        int delay = 3 * 60;           //5分钟
        executorService.scheduleWithFixedDelay(new Thread(new Runnable() {
            @Override
            public void run() {
                BlacklistTaskService taskService = (BlacklistTaskService) BeanUtil.getBean("blacklistTaskService");
                Map<String, Object> paramMap = new HashedMap();
//                paramMap.put("yn",1);
                List<BlacklistTask> list = taskService.listSelective(paramMap);
                try {
                    lock.lock();
                    if (CollectionUtil.isNotEmpty(list)) {
                        for (BlacklistTask task : list) {
                            if (task.getYn() == 2) {
                                baseTaskHashMap.remove(task.getTaskName());
                                continue;
                            }

                            if (StringUtil.isNotEmpty(task.getTaskData())) {
                                try {

                                    boolean needCreate = false;
                                    String taskName = task.getTaskName();
                                    if (baseTaskHashMap.containsKey(taskName)) {
                                        if (task.getTaskVersion() > baseTaskHashMap.get(taskName).getVersion()) {
                                            needCreate = true;
                                        }
                                    }else {
                                        needCreate = true;
                                    }

                                    if (needCreate) {
                                        BlacklistProcess baseTask = (BlacklistProcess) GroovyUtil.createGroovy(task.getTaskData());
                                        baseTask.setVersion(task.getTaskVersion());
                                        baseTaskHashMap.put(taskName,baseTask);
                                    }
                                } catch (Exception e) {
                                      logger.error("blacklistUtil groovy 初始化失败，taskName:"+task.getTaskName(),e);
                                }
                            }
                        }
                    }
                }finally {
                    lock.unlock();
                }
            }

        }), initialDelay, delay, TimeUnit.SECONDS);
    }

}

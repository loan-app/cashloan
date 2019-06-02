package com.xiji.cashloan.manage.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by szb on 19/6/3.
 */
public class LoginUtil {
    private static final Logger logger = LoggerFactory.getLogger(LoginUtil.class);
    private static ReentrantLock lock = new ReentrantLock();
    private static final int MAX_TIMES = 5;
    private static Map<String, Integer> CACHE = new HashMap<>();

    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public static String LoginKey(long current) {
        return tool.util.DateUtil.dateStr2(new Date(current));
    }

    public static boolean LoginFailLock(Long userId) {
        long currentTime = getCurrentTime();
        String loginKey = LoginKey(currentTime) + "_" + userId;
        try {
            lock.lock();
            Integer times = CACHE.get(loginKey);

            if (times != null){
                CACHE.put(loginKey, times + 1);
                logger.error("user:" + userId + " 每日登录失败次数:" + (times + 1));
                if(times >= MAX_TIMES) {
                    return true;
                } else {
                    return false;
                }
            }

            times = 0;
            logger.error("user:" + userId + " 每日登录失败次数:" + (times + 1));
            CACHE.put(loginKey, times + 1);
        }finally {
            lock.unlock();
        }
        return false;
    }

    public static void removeFailTimes(Long userId) {
        long currentTime = getCurrentTime();
        String loginKey = LoginKey(currentTime) + "_" + userId;
        CACHE.remove(loginKey);
    }

}

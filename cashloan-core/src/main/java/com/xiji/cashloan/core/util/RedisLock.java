package com.xiji.cashloan.core.util;

import com.xiji.cashloan.core.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 简化使用版的Redis分布式锁，只需要一个参数lockKey就可以加锁解锁
 * @author zhangyunxiang
 * @see <a href="https://www.cnblogs.com/linjiqin/p/8003838.html">Redis分布式锁的正确实现方式</a>
 */
public class RedisLock {

    private static final Long lockExpireSecs = 1800L;

    private static final Logger log = LoggerFactory.getLogger(RedisLock.class);

    /**
     * Redis客户端
     */
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 保存每把锁的请求id，持有请求id才能解锁，请求id保存在线程内
     */
    private ThreadLocal<Map<String, String>> requestIds;

    private Expiration lockExpiration;

    private RedisStringCommands.SetOption setOption;

    public RedisLock() {
        requestIds = new ThreadLocal<>();
        setOption = RedisStringCommands.SetOption.SET_IF_ABSENT;
    }

    @PostConstruct
    private void init() {
        lockExpiration = Expiration.seconds(lockExpireSecs);
    }

    /**
     * 尝试获取redis分布式锁
     * @param lockKey 锁键
     * @return 是否成功获取锁
     */
    public boolean tryLock(String lockKey) {
        if (StringUtil.isEmpty(lockKey)) {
            return false;
        }
        final byte[] key = lockKey.getBytes();
        String requestId = createRequestId(lockKey);
        final byte[] value = requestId.getBytes();
//
//        Boolean r = redisTemplate.execute((RedisConnection redis) ->
//                redis.set(key, value, lockExpiration, setOption));

        Boolean r = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.set(key, value, lockExpiration, setOption);
            }
        });
        log.info("tryLock succeed: " + r + ", lockKey: " + lockKey + ", requestId: " + requestId);
        return r != null && r;
    }

    /**
     * 释放redis分布式锁
     * @param lockKey 锁键
     * @return 是否释放成功
     */
    public boolean releaseLock(final String lockKey) {
        // 获取锁对应的value值，检查是否与requestId相等，如果相等则删除锁（解锁）
        // 使用lua脚本保证操作的原子性
        final String script = "if redis.call('get', KEYS[1]) == ARGV[1] " +
                "then return redis.call('del', KEYS[1]) " +
                "else return 0 end";
        final String requestId = getRequestId(lockKey);
        if (requestId == null) {
//            log.debug("release lock 失败, requestId 为空, lockKey:{}", lockKey);
            return false;
        }
//        Boolean r = redisTemplate.execute((RedisConnection redis) ->
//                redis.eval(script.getBytes(), ReturnType.BOOLEAN, 1, lockKey.getBytes(), requestId.getBytes()));
        Boolean r = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.eval(script.getBytes(), ReturnType.BOOLEAN, 1, lockKey.getBytes(), requestId.getBytes());
            }
        });

        log.info("releaseLock succeed: " + r + ", lockKey: " + lockKey + ", requestId: " + requestId);
        return r != null && r;
    }

    private String createRequestId(String lockKey) {
        String uuid = UUID.randomUUID().toString();
        Map<String, String> localMap = requestIds.get();
        if (localMap == null) {
            localMap = new HashMap<>();
            requestIds.set(localMap);
        }
        localMap.put(lockKey, uuid);
        return uuid;
    }

    private String getRequestId(String lockKey) {
        Map<String, String> requestIdMap = requestIds.get();
        if (requestIdMap == null) {
            return null;
        }
        return requestIdMap.get(lockKey);
    }

}

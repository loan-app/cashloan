package com.xiji.cashloan.cl.service.impl.assist.blacklist;

import com.xiji.cashloan.core.domain.Borrow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Auther: king
 * @Date: 2018/12/20 14:53
 * @Description:
 */
public class BlacklistBaseTask implements Runnable{
    private static final Logger logger = LoggerFactory.getLogger(BlacklistBaseTask.class);
    private BlacklistProcess process;
    private Borrow borrow;

    public BlacklistBaseTask(BlacklistProcess process,Borrow borrow) {
        this.process = process;
        this.borrow = borrow;
    }
    @Override
    public void run() {
        try {
            long start = System.currentTimeMillis();
            process.execute(borrow);
            logger.info("borrowId" + borrow.getId() + "处理黑名单调用，耗时" + (System.currentTimeMillis() - start) + " ms");
        } catch (Exception e) {
            logger.error("borrowId:" + borrow.getId() +"处理黑名单数据失败", e);
        }
    }
}

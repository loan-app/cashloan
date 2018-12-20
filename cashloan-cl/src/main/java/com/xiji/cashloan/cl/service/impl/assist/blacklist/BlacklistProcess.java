package com.xiji.cashloan.cl.service.impl.assist.blacklist;

import com.xiji.cashloan.core.domain.Borrow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Auther: king
 * @Date: 2018/12/20 14:53
 * @Description:
 */
public abstract class BlacklistProcess {
    private static final Logger logger = LoggerFactory.getLogger(BlacklistProcess.class);
    private int version;
    public BlacklistProcess() {}
    public void execute(Borrow borrow) {
        try {
            long start = System.currentTimeMillis();
            doTask(borrow,start);
            logger.info("borrowId" + borrow.getId() + "处理黑名单调用，耗时" + (System.currentTimeMillis() - start) + " ms");
        } catch (Exception e) {
            logger.error("borrowId:" + borrow.getId() +"处理黑名单数据失败", e);
        }
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public abstract void doTask(Borrow borrow,long start)  throws Exception;
}

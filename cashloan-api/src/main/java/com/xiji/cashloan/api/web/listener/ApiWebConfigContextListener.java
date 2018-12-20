package com.xiji.cashloan.api.web.listener;

import com.xiji.cashloan.cl.service.impl.assist.blacklist.BlacklistUtil;
import com.xiji.cashloan.core.common.web.listener.WebConfigContextListener;
import javax.servlet.ServletContextEvent;
import org.apache.log4j.Logger;

/**
 * 监听器
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */

public class ApiWebConfigContextListener extends WebConfigContextListener {
	private static Logger logger=Logger.getLogger(ApiWebConfigContextListener.class);
	@Override
	public void contextInitialized(ServletContextEvent event) {
		logger.info("api 启动加载...");
		
		// 系统参数
		super.contextInitialized(event);
		//黑名单服务启动
		BlacklistUtil.initConfig();
	}
}

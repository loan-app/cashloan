package com.xiji.cashloan.core.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.system.domain.SysConfig;
import com.xiji.cashloan.system.service.SysConfigService;
import org.apache.log4j.Logger;

import tool.util.BeanUtil;
import tool.util.StringUtil;


/**
 * 缓存帮助类
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 * Copyright 杭州融都科技股份有限公司 资产匹配系统 All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public class CacheUtil {

    private static final Logger logger = Logger.getLogger(CacheUtil.class);

    /**
     * 初始化系统参数配置
     */
	public static void initSysConfig() {

        logger.info("初始化系统配置Config...");
        // 系统配置Config缓存
        SysConfigService sysConfigService = (SysConfigService) BeanUtil.getBean("sysConfigService");

        Map<String, Object> configMap = new HashMap<String, Object>();

        List<SysConfig> sysConfigs = sysConfigService.findAll();
        for (SysConfig sysConfig : sysConfigs) {
            if (null != sysConfig && StringUtil.isNotBlank(sysConfig.getCode())) {
                configMap.put(sysConfig.getCode(), sysConfig.getValue());
            }
        }

        Global.configMap = new HashMap<String, Object>();
        Global.configMap.putAll(configMap);
    }

 
}

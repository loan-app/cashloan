package com.xiji.cashloan.rule.model.srule.config.builder;

import com.xiji.cashloan.rule.model.srule.config.rule.Rule;
import com.xiji.cashloan.rule.model.srule.config.rule.RuleBasic;

/**
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 */
public interface Builder {
	

    /**
     * storing the rules which  produced from each thread
     */
    ThreadLocal<RuleBasic> threadLocalRules = new ThreadLocal<RuleBasic>();


    /**
     * return the rule want to build
     *
     * @return
     * @throws Exception
     */
    Rule build() throws Exception;


}

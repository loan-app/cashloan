package com.xiji.creditrank.cr.model.srule.config.builder;

import java.util.Map;

import com.xiji.creditrank.cr.model.srule.utils.RulePolicy;

/**
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 */
public interface RuleConfigurer<H> extends Builder {

    /**
     * define the rule policy , is match all or match one
     *
     * @param rulePolicy
     * @return
     */
    RuleConfigurer<H> rulePolicy(RulePolicy rulePolicy);


    /**
     * load the string data to range the match value ,help machine to recognize
     *
     * @param map
     * @return
     */
    RuleConfigurer<H> preLoad(Map<H, Integer> map);


    RuleConfigurer<H> name(String name);


}

package com.xiji.creditrank.cr.model.srule.config.builder;


import java.util.Map;

import com.xiji.creditrank.cr.model.srule.config.rule.Rule;
import com.xiji.creditrank.cr.model.srule.config.rule.RuleBasic;
import com.xiji.creditrank.cr.model.srule.utils.RulePolicy;

/**
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SimpleRuleConfigurer<H, O extends Rule, B extends AbstractRuleBuilder<H, O>> extends RuleConfigurerAdapter<H, O, B> {

    @Override
    public RuleConfigurer<H> rulePolicy(RulePolicy rulePolicy) {
        RuleBasic ruleBasic = threadLocalRules.get();
        ruleBasic.setRulePolicy(rulePolicy);
        return this;
    }

    @Override
    public RuleConfigurer<H> preLoad(Map<H, Integer> map) {
        RuleBasic<H> ruleBasic = threadLocalRules.get();
        ruleBasic.setPreLoad(map);
        return this;
    }

    @Override
    public RuleConfigurer<H> name(String name) {
        RuleBasic<H> ruleBasic = threadLocalRules.get();
        ruleBasic.setName(name);
        return this;
    }

}

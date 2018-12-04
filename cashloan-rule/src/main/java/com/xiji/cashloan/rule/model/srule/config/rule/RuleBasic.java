package com.xiji.cashloan.rule.model.srule.config.rule;

import java.util.List;
import java.util.Map;

import com.xiji.cashloan.rule.model.srule.config.condition.Condition;
import com.xiji.cashloan.rule.model.srule.utils.RulePolicy;

/**
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 */
public interface RuleBasic<T> {

    void setId(long id);

    void setColumn(String column);

    void setName(String name);

    void setValueType(Class<T> clazz);

    void setConditions(List<Condition<T>> conditions);

    void setRulePolicy(RulePolicy rulePolicy);

    void setPreLoad(Map<T, Integer> preLoad);
}

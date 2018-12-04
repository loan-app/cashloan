package com.xiji.creditrank.cr.model.srule.config.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xiji.creditrank.cr.model.srule.config.rule.Rule;
import com.xiji.creditrank.cr.model.srule.exception.RuleNotFoundException;

/**
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 */
public abstract class AbstractRulesLogic implements RulesLogic {


    protected List<Rule> ruleList;

    protected Map<Long, Boolean> resultMap = new HashMap<Long, Boolean>();

    @Override
    public Map<Long, Boolean> rulesResult() throws RuleNotFoundException {
        if (ruleList == null || ruleList.size() == 0) {
            throw new RuleNotFoundException("can not found rules to be matched! ");
        }
        return resultMap;
    }

}

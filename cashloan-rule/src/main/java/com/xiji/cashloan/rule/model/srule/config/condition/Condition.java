package com.xiji.cashloan.rule.model.srule.config.condition;

import com.xiji.cashloan.rule.model.srule.utils.ConditionOpt;

/**
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 */
public interface Condition<T> {

    /**
     * 设置操作符
     *
     * @param opt
     * @return
     */
    Condition<T> opt(ConditionOpt opt);


    /**
     * 设置条件的值
     *
     * @param t
     * @return
     */
    Condition<T> value(T t);


    ConditionOpt getOpt();


    T getValue();

}

package com.xiji.creditrank.cr.model.srule.config.rule;

import com.xiji.creditrank.cr.model.srule.exception.RuleValueException;

/**
 * user interface show method to use
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 */
public interface Rule {


    /**
     * set the obj to be matched
     *
     * @param o
     */
    void matchTo(Object o) throws RuleValueException;


    /**
     * start match logic and give the result
     *
     * @return
     */
    boolean beginMatch() throws RuleValueException;


    /**
     * get id
     *
     * @return
     */
    public long getId();

    /**
     * get column name
     *
     * @return
     */
    public String getColumn();

    /**
     * get rule name
     *
     * @return
     */
    public String getName();


}

package com.xiji.cashloan.rule.model.srule.exception;

/**
 * 规则构建过程中出现的异常
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 */
public class RuleBuildException extends IllegalStateException{

    public RuleBuildException(String s) {
        super(s);
    }
}

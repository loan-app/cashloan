package com.xiji.cashloan.rule.model.srule.exception;

/**
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 */
public class RuleNotFoundException extends RuntimeException{

    public RuleNotFoundException(String message) {
        super(message);
    }
}

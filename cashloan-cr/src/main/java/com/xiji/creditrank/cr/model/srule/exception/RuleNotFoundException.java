package com.xiji.creditrank.cr.model.srule.exception;

/**
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 */
@SuppressWarnings("serial")
public class RuleNotFoundException extends RuntimeException{

    public RuleNotFoundException(String message) {
        super(message);
    }
}

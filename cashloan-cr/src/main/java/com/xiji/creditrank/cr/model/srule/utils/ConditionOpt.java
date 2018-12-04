package com.xiji.creditrank.cr.model.srule.utils;

/**
 * 条件的操作符常量
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 */
public enum ConditionOpt {


    BIGGER(">"),
    BIGGER_EQUAL(">="),
    SMALL("<"),
    SMALL_EQUAL("<="),
    EQUAL("="),
    NOT_EQUAL("!="),
    INCLUDE("include"),
    NOT_INCLUDE("exclude");


    private String value;

    ConditionOpt(String opt) {
        this.value = opt;
    }


    public String getValue() {
        return this.value;
    }


    public static ConditionOpt getOpt(String opt) {
        if (opt != null) {
            ConditionOpt[] values = values();
            for (ConditionOpt each : values) {
                if (each.value.equals(opt)) {
                    return each;
                }
            }

        }
        return null;
    }

}

package com.rongdu.cashloan.cl.model.moxie;

/**
 * 魔蝎回调时间对应运营商数据状态
 * Created by szb on 18/11/27.
 */
public enum  OperatorStatusEnum {

    //任务创建成功
    BACK_STATE_SUBMIT("task.submit", "1"),
    //授权登录成功
    BACK_STATE_LOGIN_SUCCESS("task.true", "2"),
    //授权登录失败
    BACK_STATE_LOGIN_FAILED("task.false", "3"),
    //采集成功
    BACK_STATE_BILL("bill", "4"),
    //采集失败
    BACK_STATE_TASK_FAIL("task.fail", "5"),
    //报告生成成功
    BACK_STATE_REPORT_SUCCESS("report.true", "6"),
    //报告生成失败
    BACK_STATE_REPORT_FAILED("report.false", "7");

    private String key;

    private String status;


    private OperatorStatusEnum(String key, String status) {
        this.key = key;
        this.status = status;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String value) {
        this.key = key;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public static OperatorStatusEnum getStatusEnumByKey(String key) {
        for (OperatorStatusEnum statusEnum : OperatorStatusEnum.values()) {
            if (statusEnum.getKey().equals(key)) {
                return statusEnum;
            }
        }
        return null;
    }

    public static String getStatusByKey(String key) {
        for (OperatorStatusEnum statusEnum : OperatorStatusEnum.values()) {
            if (statusEnum.getKey().equals(key)) {
                return statusEnum.getStatus();
            }
        }
        return null;
    }
}

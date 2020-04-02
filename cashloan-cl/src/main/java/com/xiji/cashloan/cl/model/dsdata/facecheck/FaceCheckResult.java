package com.xiji.cashloan.cl.model.dsdata.facecheck;

/**
 * @Auther: king
 * @Date: 2019/1/30 15:32
 * @Description:
 */
public class FaceCheckResult {
    private boolean success;
    private double score = 0.0;
    private String taskId = "";
    private String code = "";
    /**
     * 请求参数
     */
    private String reqParams = "";

    /**
     * 响应参数
     */
    private String returnParams = "";
    private String msg = "";
    private String faceModel = "";

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReqParams() {
        return reqParams;
    }

    public void setReqParams(String reqParams) {
        this.reqParams = reqParams;
    }

    public String getReturnParams() {
        return returnParams;
    }

    public void setReturnParams(String returnParams) {
        this.returnParams = returnParams;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFaceModel() {
        return faceModel;
    }

    public void setFaceModel(String faceModel) {
        this.faceModel = faceModel;
    }
}

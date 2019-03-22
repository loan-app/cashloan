package com.xiji.cashloan.cl.model.dsdata.facecheck;

/**
 * @Auther: king
 * @Date: 2019/2/18 11:27
 * @Description:
 */
public class FaceCheckIdCardResult {
    private boolean success;
    private String name = "";
    private String idNum = "";
    private String address = "";

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

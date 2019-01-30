package com.xiji.cashloan.cl.model.dsdata.facecheck;

/**
 * @Auther: king
 * @Date: 2019/1/30 15:46
 * @Description:
 */
public class FaceCheckReq {
    private String url;
    private String livingImgPath;
    private String frontImgPath;
    private String realName;
    private String idCard;

    /**
     * 登录名
     */
    private String loginName;
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLivingImgPath() {
        return livingImgPath;
    }

    public void setLivingImgPath(String livingImgPath) {
        this.livingImgPath = livingImgPath;
    }

    public String getFrontImgPath() {
        return frontImgPath;
    }

    public void setFrontImgPath(String frontImgPath) {
        this.frontImgPath = frontImgPath;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}

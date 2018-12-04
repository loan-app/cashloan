package com.xiji.cashloan.api.user.bean;

import java.util.Map;

/**
 * @author wnb
 * @date 2018/12/03
 * @version 1.0.0
 */
@SuppressWarnings({ "rawtypes" })
public class AppSessionBean {
//    private String token;
//    private String refreshToken;
//    private String userId;
    private Map session;


    public AppSessionBean(Map session) {
        this.session = session;
    }

    public Map getFront(){
        return (Map) session.get("front");
    }


//    public String getToken() {
//        return token;
//    }
//
//    public String getRefreshToken() {
//        return refreshToken;
//    }

    public String getUserId() {
        return getFront().get("userId").toString();
    }

    public Map getSession() {
        return session;
    }

    public Map getUserData(){
        return (Map) session.get("userData");
    }
}

package com.rongdu.cashloan.cl.util.token;

/**
 * @Auther: king
 * @Date: 2018/11/16 10:21
 * @Description:
 */
public class TokenItem {
    private String token;
    private long expire;

    public TokenItem() {

    }
    public TokenItem(String token, long expire) {
        this.token = token;
        this.expire = expire;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }
}

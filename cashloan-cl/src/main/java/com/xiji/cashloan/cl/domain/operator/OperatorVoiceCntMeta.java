package com.xiji.cashloan.cl.domain.operator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: king
 * @Date: 2018/12/17 12:35
 * @Description:
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperatorVoiceCntMeta {
    @JsonProperty("city")
    private String city;
    @JsonProperty("peer_num")
    private String peerNum;
    @JsonProperty("call_cnt_6m")
    private int callCnt6m;
    @JsonProperty("call_time_6m")
    private int callTime6m;
    @JsonProperty("dial_cnt_6m")
    private int dialCnt6m;
    @JsonProperty("dial_time_6m")
    private int dialTime6m;
    @JsonProperty("dialed_cnt_6m")
    private int dialedCnt6m;
    @JsonProperty("dialed_time_6m")
    private int dialedTime6m;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPeerNum() {
        return peerNum;
    }

    public void setPeerNum(String peerNum) {
        this.peerNum = peerNum;
    }

    public int getCallCnt6m() {
        return callCnt6m;
    }

    public void setCallCnt6m(int callCnt6m) {
        this.callCnt6m = callCnt6m;
    }

    public int getCallTime6m() {
        return callTime6m;
    }

    public void setCallTime6m(int callTime6m) {
        this.callTime6m = callTime6m;
    }

    public int getDialCnt6m() {
        return dialCnt6m;
    }

    public void setDialCnt6m(int dialCnt6m) {
        this.dialCnt6m = dialCnt6m;
    }

    public int getDialTime6m() {
        return dialTime6m;
    }

    public void setDialTime6m(int dialTime6m) {
        this.dialTime6m = dialTime6m;
    }

    public int getDialedCnt6m() {
        return dialedCnt6m;
    }

    public void setDialedCnt6m(int dialedCnt6m) {
        this.dialedCnt6m = dialedCnt6m;
    }

    public int getDialedTime6m() {
        return dialedTime6m;
    }

    public void setDialedTime6m(int dialedTime6m) {
        this.dialedTime6m = dialedTime6m;
    }
}

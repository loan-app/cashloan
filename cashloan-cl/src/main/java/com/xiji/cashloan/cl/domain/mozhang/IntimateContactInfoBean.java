package com.xiji.cashloan.cl.domain.mozhang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by lu on 2018/9/27.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IntimateContactInfoBean {
    /**
     * intimatenum : 0
     * auth_intimatenum : 0
     * auth_intimatenum_ratio : string
     * black_intimatenum : 0
     * black_intimatenum_ratio : string
     * intimate_type : string
     * auth_intimate_indirectnum : 0
     * auth_intimate_indirectnum_ratio : string
     * black_intimate_indirectnum : 0
     * black_intimate_indirectnum_ratio : string
     * black_intimate_indirect_peernum : 0
     * black_intimate_indirect_peernum_ratio : string
     * auth_intimate_indirect_peernum : 0
     * auth_intimate_indirect_peernum_ratio : string
     */

    @JsonProperty("intimatenum")
    private int intimatenum;
    @JsonProperty("auth_intimatenum")
    private int authIntimatenum;
    @JsonProperty("auth_intimatenum_ratio")
    private String authIntimatenumRatio;
    @JsonProperty("black_intimatenum")
    private int blackIntimatenum;
    @JsonProperty("black_intimatenum_ratio")
    private String blackIntimatenumRatio;
    @JsonProperty("intimate_type")
    private String intimateType;
    @JsonProperty("auth_intimate_indirectnum")
    private int authIntimateIndirectnum;
    @JsonProperty("auth_intimate_indirectnum_ratio")
    private String authIntimateIndirectnumRatio;
    @JsonProperty("black_intimate_indirectnum")
    private int blackIntimateIndirectnum;
    @JsonProperty("black_intimate_indirectnum_ratio")
    private String blackIntimateIndirectnumRatio;
    @JsonProperty("black_intimate_indirect_peernum")
    private int blackIntimateIndirectPeernum;
    @JsonProperty("black_intimate_indirect_peernum_ratio")
    private String blackIntimateIndirectPeernumRatio;
    @JsonProperty("auth_intimate_indirect_peernum")
    private int authIntimateIndirectPeernum;
    @JsonProperty("auth_intimate_indirect_peernum_ratio")
    private String authIntimateIndirectPeernumRatio;

    public int getIntimatenum() {
        return intimatenum;
    }

    public void setIntimatenum(int intimatenum) {
        this.intimatenum = intimatenum;
    }

    public int getAuthIntimatenum() {
        return authIntimatenum;
    }

    public void setAuthIntimatenum(int authIntimatenum) {
        this.authIntimatenum = authIntimatenum;
    }

    public String getAuthIntimatenumRatio() {
        return authIntimatenumRatio;
    }

    public void setAuthIntimatenumRatio(String authIntimatenumRatio) {
        this.authIntimatenumRatio = authIntimatenumRatio;
    }

    public int getBlackIntimatenum() {
        return blackIntimatenum;
    }

    public void setBlackIntimatenum(int blackIntimatenum) {
        this.blackIntimatenum = blackIntimatenum;
    }

    public String getBlackIntimatenumRatio() {
        return blackIntimatenumRatio;
    }

    public void setBlackIntimatenumRatio(String blackIntimatenumRatio) {
        this.blackIntimatenumRatio = blackIntimatenumRatio;
    }

    public String getIntimateType() {
        return intimateType;
    }

    public void setIntimateType(String intimateType) {
        this.intimateType = intimateType;
    }

    public int getAuthIntimateIndirectnum() {
        return authIntimateIndirectnum;
    }

    public void setAuthIntimateIndirectnum(int authIntimateIndirectnum) {
        this.authIntimateIndirectnum = authIntimateIndirectnum;
    }

    public String getAuthIntimateIndirectnumRatio() {
        return authIntimateIndirectnumRatio;
    }

    public void setAuthIntimateIndirectnumRatio(String authIntimateIndirectnumRatio) {
        this.authIntimateIndirectnumRatio = authIntimateIndirectnumRatio;
    }

    public int getBlackIntimateIndirectnum() {
        return blackIntimateIndirectnum;
    }

    public void setBlackIntimateIndirectnum(int blackIntimateIndirectnum) {
        this.blackIntimateIndirectnum = blackIntimateIndirectnum;
    }

    public String getBlackIntimateIndirectnumRatio() {
        return blackIntimateIndirectnumRatio;
    }

    public void setBlackIntimateIndirectnumRatio(String blackIntimateIndirectnumRatio) {
        this.blackIntimateIndirectnumRatio = blackIntimateIndirectnumRatio;
    }

    public int getBlackIntimateIndirectPeernum() {
        return blackIntimateIndirectPeernum;
    }

    public void setBlackIntimateIndirectPeernum(int blackIntimateIndirectPeernum) {
        this.blackIntimateIndirectPeernum = blackIntimateIndirectPeernum;
    }

    public String getBlackIntimateIndirectPeernumRatio() {
        return blackIntimateIndirectPeernumRatio;
    }

    public void setBlackIntimateIndirectPeernumRatio(String blackIntimateIndirectPeernumRatio) {
        this.blackIntimateIndirectPeernumRatio = blackIntimateIndirectPeernumRatio;
    }

    public int getAuthIntimateIndirectPeernum() {
        return authIntimateIndirectPeernum;
    }

    public void setAuthIntimateIndirectPeernum(int authIntimateIndirectPeernum) {
        this.authIntimateIndirectPeernum = authIntimateIndirectPeernum;
    }

    public String getAuthIntimateIndirectPeernumRatio() {
        return authIntimateIndirectPeernumRatio;
    }

    public void setAuthIntimateIndirectPeernumRatio(String authIntimateIndirectPeernumRatio) {
        this.authIntimateIndirectPeernumRatio = authIntimateIndirectPeernumRatio;
    }
}

package com.rongdu.cashloan.cl.domain.mozhang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by lu on 2018/9/27.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MobileContactBean {
    /**
     * contactnum : 0
     * auth_contactnum : 0
     * auth_contact_ratio : string
     * black_contactnum : 0
     * black_contactnum_ratio : string
     * contact_type : string
     * auth_indirectnum : 0
     * auth_indirectnum_ratio : string
     * black_indirectnum : 0
     * black_indirectnum_ratio : string
     * black_indirect_peernum : 0
     * black_indirect_peernum_ratio : string
     * auth_indirect_peernum : 0
     * auth_indirect_peernum_ratio : string
     */

    @JsonProperty("contactnum")
    private int contactnum;
    @JsonProperty("auth_contactnum")
    private int authContactnum;
    @JsonProperty("auth_contact_ratio")
    private String authContactRatio;
    @JsonProperty("black_contactnum")
    private int blackContactnum;
    @JsonProperty("black_contactnum_ratio")
    private String blackContactnumRatio;
    @JsonProperty("contact_type")
    private String contactType;
    @JsonProperty("auth_indirectnum")
    private int authIndirectnum;
    @JsonProperty("auth_indirectnum_ratio")
    private String authIndirectnumRatio;
    @JsonProperty("black_indirectnum")
    private int blackIndirectnum;
    @JsonProperty("black_indirectnum_ratio")
    private String blackIndirectnumRatio;
    @JsonProperty("black_indirect_peernum")
    private int blackIndirectPeernum;
    @JsonProperty("black_indirect_peernum_ratio")
    private String blackIndirectPeernumRatio;
    @JsonProperty("auth_indirect_peernum")
    private int authIndirectPeernum;
    @JsonProperty("auth_indirect_peernum_ratio")
    private String authIndirectPeernumRatio;

    public int getContactnum() {
        return contactnum;
    }

    public void setContactnum(int contactnum) {
        this.contactnum = contactnum;
    }

    public int getAuthContactnum() {
        return authContactnum;
    }

    public void setAuthContactnum(int authContactnum) {
        this.authContactnum = authContactnum;
    }

    public String getAuthContactRatio() {
        return authContactRatio;
    }

    public void setAuthContactRatio(String authContactRatio) {
        this.authContactRatio = authContactRatio;
    }

    public int getBlackContactnum() {
        return blackContactnum;
    }

    public void setBlackContactnum(int blackContactnum) {
        this.blackContactnum = blackContactnum;
    }

    public String getBlackContactnumRatio() {
        return blackContactnumRatio;
    }

    public void setBlackContactnumRatio(String blackContactnumRatio) {
        this.blackContactnumRatio = blackContactnumRatio;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public int getAuthIndirectnum() {
        return authIndirectnum;
    }

    public void setAuthIndirectnum(int authIndirectnum) {
        this.authIndirectnum = authIndirectnum;
    }

    public String getAuthIndirectnumRatio() {
        return authIndirectnumRatio;
    }

    public void setAuthIndirectnumRatio(String authIndirectnumRatio) {
        this.authIndirectnumRatio = authIndirectnumRatio;
    }

    public int getBlackIndirectnum() {
        return blackIndirectnum;
    }

    public void setBlackIndirectnum(int blackIndirectnum) {
        this.blackIndirectnum = blackIndirectnum;
    }

    public String getBlackIndirectnumRatio() {
        return blackIndirectnumRatio;
    }

    public void setBlackIndirectnumRatio(String blackIndirectnumRatio) {
        this.blackIndirectnumRatio = blackIndirectnumRatio;
    }

    public int getBlackIndirectPeernum() {
        return blackIndirectPeernum;
    }

    public void setBlackIndirectPeernum(int blackIndirectPeernum) {
        this.blackIndirectPeernum = blackIndirectPeernum;
    }

    public String getBlackIndirectPeernumRatio() {
        return blackIndirectPeernumRatio;
    }

    public void setBlackIndirectPeernumRatio(String blackIndirectPeernumRatio) {
        this.blackIndirectPeernumRatio = blackIndirectPeernumRatio;
    }

    public int getAuthIndirectPeernum() {
        return authIndirectPeernum;
    }

    public void setAuthIndirectPeernum(int authIndirectPeernum) {
        this.authIndirectPeernum = authIndirectPeernum;
    }

    public String getAuthIndirectPeernumRatio() {
        return authIndirectPeernumRatio;
    }

    public void setAuthIndirectPeernumRatio(String authIndirectPeernumRatio) {
        this.authIndirectPeernumRatio = authIndirectPeernumRatio;
    }
}

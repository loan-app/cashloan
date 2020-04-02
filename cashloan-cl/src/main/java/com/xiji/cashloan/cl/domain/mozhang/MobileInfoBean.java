package com.xiji.cashloan.cl.domain.mozhang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author wnb
 * @date 2018/12/03
 * @version 1.0.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MobileInfoBean {
    /**
     * match_score : 0
     * mobile_contact_30d : {"contactnum":0,"auth_contactnum":0,"auth_contact_ratio":"string","black_contactnum":0,"black_contactnum_ratio":"string","contact_type":"string","auth_indirectnum":0,"auth_indirectnum_ratio":"string","black_indirectnum":0,"black_indirectnum_ratio":"string","black_indirect_peernum":0,"black_indirect_peernum_ratio":"string","auth_indirect_peernum":0,"auth_indirect_peernum_ratio":"string"}
     * intimate_contact_info_30d : {"intimatenum":0,"auth_intimatenum":0,"auth_intimatenum_ratio":"string","black_intimatenum":0,"black_intimatenum_ratio":"string","intimate_type":"string","auth_intimate_indirectnum":0,"auth_intimate_indirectnum_ratio":"string","black_intimate_indirectnum":0,"black_intimate_indirectnum_ratio":"string","black_intimate_indirect_peernum":0,"black_intimate_indirect_peernum_ratio":"string","auth_intimate_indirect_peernum":0,"auth_intimate_indirect_peernum_ratio":"string"}
     * mobile_contact_90d : {"contactnum":0,"auth_contactnum":0,"auth_contact_ratio":"string","black_contactnum":0,"black_contactnum_ratio":"string","contact_type":"string","auth_indirectnum":0,"auth_indirectnum_ratio":"string","black_indirectnum":0,"black_indirectnum_ratio":"string","black_indirect_peernum":0,"black_indirect_peernum_ratio":"string","auth_indirect_peernum":0,"auth_indirect_peernum_ratio":"string"}
     * intimate_contact_info_90d : {"intimatenum":0,"auth_intimatenum":0,"auth_intimatenum_ratio":"string","black_intimatenum":0,"black_intimatenum_ratio":"string","intimate_type":"string","auth_intimate_indirectnum":0,"auth_intimate_indirectnum_ratio":"string","black_intimate_indirectnum":0,"black_intimate_indirectnum_ratio":"string","black_intimate_indirect_peernum":0,"black_intimate_indirect_peernum_ratio":"string","auth_intimate_indirect_peernum":0,"auth_intimate_indirect_peernum_ratio":"string"}
     * mobile_contact_180d : {"contactnum":0,"auth_contactnum":0,"auth_contact_ratio":"string","black_contactnum":0,"black_contactnum_ratio":"string","contact_type":"string","auth_indirectnum":0,"auth_indirectnum_ratio":"string","black_indirectnum":0,"black_indirectnum_ratio":"string","black_indirect_peernum":0,"black_indirect_peernum_ratio":"string","auth_indirect_peernum":0,"auth_indirect_peernum_ratio":"string"}
     * intimate_contact_info_180d : {"intimatenum":0,"auth_intimatenum":0,"auth_intimatenum_ratio":"string","black_intimatenum":0,"black_intimatenum_ratio":"string","intimate_type":"string","auth_intimate_indirectnum":0,"auth_intimate_indirectnum_ratio":"string","black_intimate_indirectnum":0,"black_intimate_indirectnum_ratio":"string","black_intimate_indirect_peernum":0,"black_intimate_indirect_peernum_ratio":"string","auth_intimate_indirect_peernum":0,"auth_intimate_indirect_peernum_ratio":"string"}
     */

    @JsonProperty("match_score")
    private int matchScore;
    @JsonProperty("mobile_contact_30d")
    private MobileContactBean mobileContact30d;
    @JsonProperty("intimate_contact_info_30d")
    private IntimateContactInfoBean intimateContactInfo30d;
    @JsonProperty("mobile_contact_90d")
    private MobileContactBean mobileContact90d;
    @JsonProperty("intimate_contact_info_90d")
    private IntimateContactInfoBean intimateContactInfo90d;
    @JsonProperty("mobile_contact_180d")
    private MobileContactBean mobileContact180d;
    @JsonProperty("intimate_contact_info_180d")
    private IntimateContactInfoBean intimateContactInfo180d;

    public int getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(int matchScore) {
        this.matchScore = matchScore;
    }

    public MobileContactBean getMobileContact30d() {
        return mobileContact30d;
    }

    public void setMobileContact30d(MobileContactBean mobileContact30d) {
        this.mobileContact30d = mobileContact30d;
    }

    public IntimateContactInfoBean getIntimateContactInfo30d() {
        return intimateContactInfo30d;
    }

    public void setIntimateContactInfo30d(IntimateContactInfoBean intimateContactInfo30d) {
        this.intimateContactInfo30d = intimateContactInfo30d;
    }

    public MobileContactBean getMobileContact90d() {
        return mobileContact90d;
    }

    public void setMobileContact90d(MobileContactBean mobileContact90d) {
        this.mobileContact90d = mobileContact90d;
    }

    public IntimateContactInfoBean getIntimateContactInfo90d() {
        return intimateContactInfo90d;
    }

    public void setIntimateContactInfo90d(IntimateContactInfoBean intimateContactInfo90d) {
        this.intimateContactInfo90d = intimateContactInfo90d;
    }

    public MobileContactBean getMobileContact180d() {
        return mobileContact180d;
    }

    public void setMobileContact180d(MobileContactBean mobileContact180d) {
        this.mobileContact180d = mobileContact180d;
    }

    public IntimateContactInfoBean getIntimateContactInfo180d() {
        return intimateContactInfo180d;
    }

    public void setIntimateContactInfo180d(IntimateContactInfoBean intimateContactInfo180d) {
        this.intimateContactInfo180d = intimateContactInfo180d;
    }
}

package com.xiji.cashloan.cl.domain.mozhang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author wnb
 * @date 2018/12/03
 * @version 1.0.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VerificationInfoBean {
    /**
     * has_carrier_data : true
     * has_onlinebank_data : true
     */

    @JsonProperty("has_carrier_data")
    private boolean hasCarrierData;
    @JsonProperty("has_onlinebank_data")
    private boolean hasOnlinebankData;

    public boolean isHasCarrierData() {
        return hasCarrierData;
    }

    public void setHasCarrierData(boolean hasCarrierData) {
        this.hasCarrierData = hasCarrierData;
    }

    public boolean isHasOnlinebankData() {
        return hasOnlinebankData;
    }

    public void setHasOnlinebankData(boolean hasOnlinebankData) {
        this.hasOnlinebankData = hasOnlinebankData;
    }
}

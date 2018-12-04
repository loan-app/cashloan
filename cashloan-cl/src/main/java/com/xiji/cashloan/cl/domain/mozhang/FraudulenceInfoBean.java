package com.xiji.cashloan.cl.domain.mozhang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by szb on 18/12/4.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FraudulenceInfoBean {
    @JsonProperty("is_hit")
    private boolean isHit;
    @JsonProperty("type")
    private String type;

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

package com.xiji.cashloan.cl.domain.mozhang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author wnb
 * @date 2018/12/03
 * @version 1.0.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonInfoBean {
    /**
     * idcard : string
     * idcard_location : string
     * mobile : string
     * carrier : string
     * mobile_location : string
     * name : string
     * age : 0
     * gender : string
     * email : string
     */

    @JsonProperty("idcard")
    private String idcard;
    @JsonProperty("idcard_location")
    private String idcardLocation;
    @JsonProperty("mobile")
    private String mobile;
    @JsonProperty("carrier")
    private String carrier;
    @JsonProperty("mobile_location")
    private String mobileLocation;
    @JsonProperty("name")
    private String name;
    @JsonProperty("age")
    private int age;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("email")
    private String email;

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getIdcardLocation() {
        return idcardLocation;
    }

    public void setIdcardLocation(String idcardLocation) {
        this.idcardLocation = idcardLocation;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getMobileLocation() {
        return mobileLocation;
    }

    public void setMobileLocation(String mobileLocation) {
        this.mobileLocation = mobileLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

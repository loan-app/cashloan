package com.jiya.cashloan;
import com.alibaba.fastjson.JSON;
import com.xiji.cashloan.cl.domain.mozhang.FraudulenceInfoBean;

/**
 * Created by jacky.cheng on 2016/3/10.
 */
public class Test2 {
    public static void main(String[] args) {
        //期望的json格式
        String json = "{\"is_hit\":true,\"type\":\"伪造流水\"}";
        FraudulenceInfoBean f = JSON.parseObject(json, FraudulenceInfoBean.class);
        System.out.println(f.isIsHit());
    }

}

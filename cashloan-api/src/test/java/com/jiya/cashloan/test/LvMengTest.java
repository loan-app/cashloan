package com.jiya.cashloan.test;

import com.xiji.cashloan.cl.util.LvMengApiClientUtil;

public class LvMengTest {
    public static void main(String[] args) {

        boolean flag = LvMengApiClientUtil.userDetail("362325199005271618", "18510065860", "庄建园");
    }
}

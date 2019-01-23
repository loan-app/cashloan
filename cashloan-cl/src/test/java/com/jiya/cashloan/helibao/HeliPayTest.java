package com.jiya.cashloan.helibao;

import java.io.File;

/**
 * @Auther: king
 * @Date: 2019/1/23 16:46
 * @Description:
 */
public class HeliPayTest {

    /**
     * 商户编号：C1800000002 快捷接口测试证书
     *
     * C1800000002.pfx 证书密码：qwer1234
     *
     * 代付接口：
     *
     * MD5签名密钥：Vx977zHtKzoMkb3vZjxLzVvNHg1469cO
     *
     * RSA私钥：MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAM+PcQN9JmrYJ+sKwI9kmBLAsBS6HHDT2Sgkh8BYbyTKovSMQat1II6l42HhUH6lr7bXwnRUw8I4qrNBZjz4cWZBi+vgkSL/1f1M/erW27t61DobvpgDpZQTtmQ7IDsiLuY7C7We+WwaBcddnju74ij3FPWCpgYBHGwdv5wwzRxdAgMBAAECgYBAzSreiPsujm/gDQpTeneUGz6eKgDpJOr+gnEzlyiUFwPLT+LM0hOpFZepHnxQHhB/CFu4kCJSB/kbYAa4cGSOlPo8zBLCfNajClZMLaKMAIb+0TmYNAnVcadC/4fXibzAW0zRS2/OK4H7wWUVEYyC66m+ieBaH5Jt/72+e6aYTQJBAPjjhGanLk22ml8i5+MzN94RBQStbGNxI6xtBXoKEIB2W/INPddZ877e7tknh+fVvctTZlE4Q5V1TT2ZL4wzke8CQQDVfaE9Cbc+aeg3Mb+Ap64tCK4WTHhWzHySN7VGTLdeF41ZjqTrIS7SSQyZOPOt/lMfFgXO0EnSdCqL+aexXFJzAkBeHyxi5bZNDVEzyS+IbEYkZKtRKYRj1tV2z4PSsxuqeRgsYXWRiyLye7w3wwtSUTKFQfTfojdsvf+H2/ZvPtFhAkAMygfctjZKAOIuXEaSmHjwrbJwF4il+n4D7F5ppbLeah7HnKn4g/ZgFowwqZ6/b5rfI9yZNRUXDGp4FC6di2BNAkB572zRbBT5Ot9mx9xVg6g/t0s3+LLEs1LBFEWQatRR9oC6qUzGNKTnZ/d5254ngnYXSRaQEZT698cJQV7kvmg4
     */

    public static void main(String[] args) {
        String property = System.getProperty("user.dir");
        String pfxpath = property + File.separator + "8000013189_pri.pfx";

        File pfxfile = new File(pfxpath);
        if (!pfxfile.exists()) {
            System.out.println("私钥文件不存在！");
            throw new RuntimeException("私钥文件不存在！");
        }
    }

    public static void testDaifu() {

    }

}

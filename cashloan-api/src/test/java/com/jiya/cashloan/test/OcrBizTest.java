package com.jiya.cashloan.test;

import com.xiji.cashloan.core.common.util.StringUtil;
import com.xiji.cashloan.core.common.util.file.FileUtil;

/**
 * @Auther: king
 * @Date: 2019/2/19 14:29
 * @Description:
 */
public class OcrBizTest {

    public static void main(String[] args) {
//        String str = "https://idsafe-auth.udcredit.com/front/4.0/api/file_download/platform/web?name=i_20190219142802739403518.jpg&param=8664FD3190B582E56A68ADFE111826F66CE32513FDA74844F81A2EFA6395DDC9C40EE31F77";
//        down(str);

//        System.out.println(123415%10);
        System.out.println(StringUtil.contains("abc=1234a","="));
    }

    public static void down(String pic) {
        long start = System.currentTimeMillis();
        FileUtil.uploadImg(pic, "123456", "faceId");
        System.out.println(System.currentTimeMillis() - start);
    }
}

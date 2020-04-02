package com.xiji.cashloan.cl.model.pay.fuiou.payfor;

import com.xiji.cashloan.cl.model.pay.fuiou.constant.FuiouConstant;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang3.StringUtils;
import tool.util.StringUtil;

/**
 * @Auther: king
 * @Date: 2018/11/21 19:40
 * @Description:
 */
@XmlRootElement(name = "payforrsp") // 必须要标明这个元素
@XmlAccessorType(XmlAccessType.FIELD)
public class PayforrspModel extends PayforBaseModel{

    public boolean acceptSuccess() {
        if (StringUtil.isNotBlank(super.getRet()) && FuiouConstant.DAIFU_RESPONSE_ACCEPTSUCCESS_CODE.equals(super.getRet())) {
            return true;
        }
        return false;
    }
    public boolean success() {
        if (StringUtil.isNotBlank(super.getRet()) && FuiouConstant.RESPONSE_SUCCESS_CODE.equals(super.getRet())) {
            return true;
        }
        return false;
    }
    public boolean error() {
        return StringUtils.startsWith(super.getErrorCode(), "300");
    }
}

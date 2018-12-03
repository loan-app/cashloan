package com.rongdu.cashloan.cl.model.pay.fuiou.payfor;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Auther: king
 * @Date: 2018/11/21 19:41
 * @Description:
 */
@XmlRootElement(name = "qrytransrsp") // 必须要标明这个元素
@XmlAccessorType(XmlAccessType.FIELD)
public class QrytransrspModel extends PayforBaseModel {

    private List<TransModel> trans;

    public List<TransModel> getTrans() {
        return trans;
    }

    public void setTrans(List<TransModel> trans) {
        this.trans = trans;
    }
}


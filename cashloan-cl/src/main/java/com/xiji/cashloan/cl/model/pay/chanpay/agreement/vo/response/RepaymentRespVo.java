package com.xiji.cashloan.cl.model.pay.chanpay.agreement.vo.response;

import com.xiji.cashloan.cl.model.pay.chanpay.constant.ChanPayConstant;
import org.nuxeo.common.xmap.annotation.XNode;
import tool.util.StringUtil;

public class RepaymentRespVo {
    @XNode("TRXID")
    private String trxId;//订单号
    @XNode("ORDERTRXID")
    private String orderTrxid;//畅捷流水号
    @XNode("STATUS")
    private String status;//订单状态 S:成功，F:失败；P：处理中
    @XNode("RETCODE")
    private String retCode;//业务返回码
    @XNode("RETMSG")
    private String retMsg;//返回描述
    @XNode("APPRETCODE")
    private String appRetcode;//应用返回码
    @XNode("APPRETMSG")
    private String appRetMsg;//应用返回描述
    @XNode("EXTENSION")
    private String extension;//扩展字段

    public String getTrxId() {
        return trxId;
    }

    public void setTrxId(String trxId) {
        this.trxId = trxId;
    }

    public String getOrderTrxid() {
        return orderTrxid;
    }

    public void setOrderTrxid(String orderTrxid) {
        this.orderTrxid = orderTrxid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public String getAppRetcode() {
        return appRetcode;
    }

    public void setAppRetcode(String appRetcode) {
        this.appRetcode = appRetcode;
    }

    public String getAppRetMsg() {
        return appRetMsg;
    }

    public void setAppRetMsg(String appRetMsg) {
        this.appRetMsg = appRetMsg;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public boolean checkReturn() {
        return StringUtil.equals(retCode, ChanPayConstant.RESPONSE_SUCCESS_CODE);
    }

    @Override
    public String toString() {
        return "RepaymentRespVo{" +
                "trxId='" + trxId + '\'' +
                ", orderTrxid='" + orderTrxid + '\'' +
                ", status='" + status + '\'' +
                ", retCode='" + retCode + '\'' +
                ", retMsg='" + retMsg + '\'' +
                ", appRetcode='" + appRetcode + '\'' +
                ", appRetMsg='" + appRetMsg + '\'' +
                ", extension='" + extension + '\'' +
                '}';
    }
}

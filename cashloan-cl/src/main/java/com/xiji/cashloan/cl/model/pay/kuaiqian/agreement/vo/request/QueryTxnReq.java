package com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.vo.request;

/**
 * @auther : wnb
 * @date : 2019/5/7
 * @describe :卡bin 查询参数类
 */
public class QueryTxnReq {

    /**
     * 接口版本 号
     */
    private String version;

    /**
     * 交易类型 编码
     */
    private String txnType;

    /**
     * 卡号
     */
    private String cardNo;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
}

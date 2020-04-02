package com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.paymock;

/**
 * @auther : wnb
 * @date : 2019/5/3
 * @describe :
 */
public class Pay2bankOrderReturn extends Pay2bankOrder {

    /**
     * 错误 描述
     */
    private String errorMsg ;

    /**
     * 错误代码
     */
    private String errorCode;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}

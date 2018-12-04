package com.xiji.cashloan.cl.model.zmxy.creditScore;

import com.antgroup.zmxy.openplatform.api.response.ZhimaCreditScoreGetResponse;
import com.xiji.cashloan.cl.model.zmxy.base.BaseResp;


/**
 * @author wnb
 * @date 2018/11/30
 * @version 1.0.0
 */
public class ZmScoreResp extends BaseResp {

	private static final long serialVersionUID = 1L;

	/**
	 * 鉴权失败
	 */
	public static final String AUTHENTICATION_FAIL = "ZMCREDIT.authentication_fail";
	
    /**
     * 芝麻信用对于每一次请求返回的业务号。后续可以通过此业务号进行对账
     */
    private String bizNo;

    /**
     * 芝麻信用分
     */
    private String zmScore;

    public String getBizNo() {
        return bizNo;
    }

    public String getZmScore() {
        return zmScore;
    }

    public ZmScoreResp(ZhimaCreditScoreGetResponse response) {
        super(response);
        this.bizNo = response.getBizNo();
        this.zmScore = response.getZmScore();
    }






}

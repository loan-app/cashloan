package com.xiji.cashloan.cl.model.pay.lianlian;

import com.xiji.cashloan.cl.model.pay.lianlian.constant.LianLianConstant;

/**
 * 连连支付  银行卡卡Bin查询
 * 
 * @author gc
 * @version 1.0.0
 * @date 2017年8月24日 下午4:10:14
 *
 * 官方网站：www.xiji.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public class QueryBankCardModel extends BasePaymentModel {

    /**
     * 版本号
     */
    private String api_version;

    /**
     * 银行卡号
     */
    private String card_no;

    /**
     * 所属银行编码
     */
    private String bank_code;

    /**
     * 所属银行名称
     */
    private String bank_name;

    /**
     * 银行卡类型
     */
    private String card_type;

	public QueryBankCardModel() {
		super();
	}
	
	public QueryBankCardModel(String orderNo) {
		super();
		this.setService("queryBankCardBin");
		this.setOrderNo(orderNo);
		this.setApi_version(LianLianConstant.API_VERSION);
		this.setSubUrl("https://queryapi.lianlianpay.com/bankcardbin.htm");

	}

    @Override
    public String[] signParamNames() {
        return new String[] { "oid_partner", "api_version", "card_no", "sign_type", "sign" };
    }

    @Override
    public String[] reqParamNames() {
        return new String[] { "oid_partner", "api_version", "card_no", "sign_type", "sign" };
    }

    @Override
    public String[] respParamNames() {
        return new String[] { "ret_code", "ret_msg", "sign_type", "sign", "card_no", "bank_code", "bank_name",
                "card_type" };
    }

    /**
     * 获取api_version
     * @return api_version
     */
    public String getApi_version() {
        return api_version;
    }

    /**
     * 设置api_version
     * @param api_version
     */
    public void setApi_version(String api_version) {
        this.api_version = api_version;
    }

    /**
     * 获取card_no
     * @return card_no
     */
    public String getCard_no() {
        return card_no;
    }

    /**
     * 设置card_no
     * @param card_no
     */
    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    /**
     * 获取bank_code
     * @return bank_code
     */
    public String getBank_code() {
        return bank_code;
    }

    /**
     * 设置bank_code
     * @param bank_code
     */
    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
    }

    /**
     * 获取bank_name
     * @return bank_name
     */
    public String getBank_name() {
        return bank_name;
    }

    /**
     * 设置bank_name
     * @param bank_name
     */
    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    /**
     * 获取card_type
     * @return card_type
     */
    public String getCard_type() {
        return card_type;
    }

    /**
     * 设置card_type
     * @param card_type
     */
    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }
	
    
}

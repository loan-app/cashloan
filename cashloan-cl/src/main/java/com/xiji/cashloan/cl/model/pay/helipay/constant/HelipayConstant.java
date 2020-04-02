package com.xiji.cashloan.cl.model.pay.helipay.constant;

/**
 * @Auther: king
 * @Date: 2018/11/21 20:29
 * @Description:
 */
public class HelipayConstant {
    /** 处理结果 */
    /** 处理结果 - 接受成功 */
    public static final String RESULT_CODE_SUCCESS = "0000";
    /** 处理结果 - 订单已经存在 */
    public static final String RESULT_CODE_EXISTS = "0001";
    /** 处理结果 - 账户余额不足 */
    public static final String RESULT_CODE_NOCASH = "0003";

    /** 接口类型 - 代付 */
    public static final String BTYPE_Transfer = "Transfer";
    /** 接口类型 - 代付单查询 */
    public static final String BTYPE_TransferQuery = "TransferQuery";

    /** 接口类型 - 绑卡短信 */
    public static final String BTYPE_AGREEMENT_PAYCODE = "AgreementPayBindCardValidateCode";
    /** 接口类型 - 绑卡 */
    public static final String BTYPE_QuickPayBindCard = "QuickPayBindCard";
    /** 接口类型 - 绑卡支付 */
    public static final String BTYPE_QuickPayBindPay = "QuickPayBindPay";
    /** 接口类型 - 绑卡支付查询 */
    public static final String BTYPE_QuickPayQuery = "QuickPayQuery";
    /** 接口类型 - 解除绑卡 */
    public static final String BTYPE_BankCardUnbind = "BankCardUnbind";

    /** 接口类型 - 商户用户注册 */
    public static final String BTYPE_MerchantUserRegister = "MerchantUserRegister";

    /** 接口类型 - 商户用户查询 */
    public static final String BTYPE_MerchantUserQuery = "MerchantUserQuery";

    /** 接口类型 - 委托代付下单 */
    public static final String BTYPE_EntrustedLoanTransfer = "EntrustedLoanTransfer";

    /** 接口类型 - 委托代付订单查询 */
    public static final String BTYPE_EntrustedLoanTransferQuery = "EntrustedLoanTransferQuery";

    /** 接口类型 - 用户资质上传 */
    public static final String BTYPE_UploadCredential = "UploadCredential";

    /** 接口类型 - 用户资质查询 */
    public static final String BTYPE_UploadCredentialQuery = "UploadCredentialQuery";

    /** 业务类型 -  B2C-对私 */
    public static final String BIZ_TYPE_B2C = "B2C";
    /** 业务类型 -  B2B-对公 */
    public static final String BIZ_TYPE_B2B = "B2B";

    /** 手续费 -  PAYER:付款方收取手续费 */
    public static final String PAYTYPE_PAYER = "PAYER";
    /** 手续费 - RECEIVER:收款方收取手续费  */
    public static final String PAYTYPE_RECEIVER = "RECEIVER";
    /** 是否加急 */
    public static final String PAY_URGENCY = "true";
    /** 加密方式 */
    public static final String SIGNATURE_TYPE = "MD5WITHRSA";
    /** 币种 */
    public static final String CURRENCY_CNY = "CNY";
    /** 订单状态 成功 */
    public static final String ORDER_STATUS_SUCCESS = "SUCCESS";
    /** 订单状态 CANCELLED：已取消*/
    public static final String ORDER_STATUS_CANCELLED = "CANCELLED";
    /** 订单状态 退款*/
    public static final String ORDER_STATUS_REFUNDED = "REFUNDED";
    /** 订单状态 失败*/
    public static final String ORDER_STATUS_FAILED = "FAILED";
    /** 订单状态 处理中*/
    public static final String ORDER_STATUS_DOING = "DOING";
    /** 订单状态 未支付*/
    public static final String ORDER_STATUS_INIT = "INIT";

    /** 交易状态 返回码8000，以订单状态为准*/
    public static final String ORDER_RETCODE_NEED_ORDERSTATUS= "8000";

    public static final String DAIFU_RESPONSE_NO_ORDER = "0005";

    /**
     * 字符串"&"
     */
    public static final String SPLIT="&";

    /**
     * Des3秘钥, 是恒定值
     */
    public static final String DESKEY_KEY = "xglHJa0H1QKQi1Z9aaqnL0l2";

    /**
     * 借贷记类型 DEBIT-借记卡
     */
    public static final String ONLINE_CARDTYPE ="DEBIT" ;


    /** 证件类型 身份证正面*/
    public static final String  FRONT_OF_ID_CARD="FRONT_OF_ID_CARD";
    /** 证件类型 身份证反面*/
    public static final String  BACK_OF_ID_CARD="BACK_OF_ID_CARD";
    /** 证件类型 手持身份证照*/
    public static final String  HANDHELD_OF_ID_CARD="HANDHELD_OF_ID_CARD";
    /** 证件类型 税务登记证*/
    public static final String  TAX_REG_CERTIFICATE="TAX_REG_CERTIFICATE";
    /** 证件类型 营业执照*/
    public static final String  BUSINESS_LICENSE="BUSINESS_LICENSE";
    /** 证件类型 组织机构证*/
    public static final String  ORG_CERTIFICATE="ORG_CERTIFICATE";



}

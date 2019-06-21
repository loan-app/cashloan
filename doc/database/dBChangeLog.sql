INSERT INTO `arc_sys_config` VALUES (null, '80', '合利支付代付地址', 'helipay_transfer_url', 'http://test.trx.helipay.com/trx/transfer/interface.action', '1', '合利支付代付地址', '1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '合利支付快捷支付', 'helipay_quickpay_api_url', 'http://test.trx.helipay.com/trx/quickPayApi/interface.action', '1', '合利支付快捷支付', '1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '合利支付商务号', 'helipay_customer_no', 'C1800000002', '1', '合利支付商务号', '1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '合利支付rsakey', 'helipay_transfer_sign_key', 'MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAM+PcQN9JmrYJ+sKwI9kmBLAsBS6HHDT2Sgkh8BYbyTKovSMQat1II6l42HhUH6lr7bXwnRUw8I4qrNBZjz4cWZBi+vgkSL/1f1M/erW27t61DobvpgDpZQTtmQ7IDsiLuY7C7We+WwaBcddnju74ij3FPWCpgYBHGwdv5wwzRxdAgMBAAECgYBAzSreiPsujm/gDQpTeneUGz6eKgDpJOr+gnEzlyiUFwPLT+LM0hOpFZepHnxQHhB/CFu4kCJSB/kbYAa4cGSOlPo8zBLCfNajClZMLaKMAIb+0TmYNAnVcadC/4fXibzAW0zRS2/OK4H7wWUVEYyC66m+ieBaH5Jt/72+e6aYTQJBAPjjhGanLk22ml8i5+MzN94RBQStbGNxI6xtBXoKEIB2W/INPddZ877e7tknh+fVvctTZlE4Q5V1TT2ZL4wzke8CQQDVfaE9Cbc+aeg3Mb+Ap64tCK4WTHhWzHySN7VGTLdeF41ZjqTrIS7SSQyZOPOt/lMfFgXO0EnSdCqL+aexXFJzAkBeHyxi5bZNDVEzyS+IbEYkZKtRKYRj1tV2z4PSsxuqeRgsYXWRiyLye7w3wwtSUTKFQfTfojdsvf+H2/ZvPtFhAkAMygfctjZKAOIuXEaSmHjwrbJwF4il+n4D7F5ppbLeah7HnKn4g/ZgFowwqZ6/b5rfI9yZNRUXDGp4FC6di2BNAkB572zRbBT5Ot9mx9xVg6g/t0s3+LLEs1LBFEWQatRR9oC6qUzGNKTnZ/d5254ngnYXSRaQEZT698cJQV7kvmg4', '1', '合利支付RSAKEY', '1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '合利支付开关', 'helipay_switch', '1', '1', '合利支付开关', '1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '合利支付md5key', 'helipay_daifu_md5_key', 'Vx977zHtKzoMkb3vZjxLzVvNHg1469cO', '1', '合利支付md5key', '1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '合利支付cert', 'helipay_cert_name', 'helipay.cer', '1', '合利支付cert', '1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '合利支付pfx', 'helipay_pfx_name', 'C1800000002.pfx', '1', '合利支付pfx', '1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '合利支付pwd', 'helipay_pfx_pwd', 'qwer1234', '1', '合利支付pwd', '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '支付公司选择', 'pay_model_select', 'fuiou', '1', '支付公司选择，fuiou,helipay', '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '支付测试指定userid和公司', 'pay_model_test', '', '1', '支付测试指定userid和公司：1=helipay', '1');
alter table cl_bank_card add column `agree_company` varchar(32) DEFAULT '' COMMENT '签约协议公司，fuiou或者helipay';
update cl_bank_card set agree_company = 'fuiou';
CREATE TABLE `k_bank_card_bin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(128) NOT NULL DEFAULT '' COMMENT '分行名称',
  `bank_code` varchar(32) NOT NULL DEFAULT '' COMMENT '银行编码',
  `bank_name` varchar(128) NOT NULL DEFAULT '' COMMENT '银行名称',
  `card_name` varchar(128) NOT NULL DEFAULT '' COMMENT '卡名称',
  `card_type` varchar(24) NOT NULL DEFAULT '' COMMENT '卡类型，借记卡，准贷记卡，贷记卡等',
  `card_bin` varchar(24) NOT NULL DEFAULT '' COMMENT '卡bin',
  `bin_len` tinyint(4) NOT NULL DEFAULT '6' COMMENT '卡bin长度',
  `card_len` tinyint(4) NOT NULL DEFAULT '0' COMMENT '卡长度',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `yn` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否有效：1-有效;0-无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED COMMENT='卡bin信息';

-- 添加token获取地址，2018-11-16
INSERT INTO `arc_sys_config` VALUES (null, 70, 'token', 'token_apihost', 'https://api.dsdatas.com/credit/api/token', 1, '获取token的host', 1);

update arc_sys_config set value='690abc5009eda69f786c380cef51336a' where code='apikey';
update arc_sys_config set value='ea108d5b1dc792680172d0e925a69470598ed54d' where code='secretkey';

-- 修改短信发送地址 2018-11-19
update arc_sys_config set value='https://api.dsdatas.com/movek/movekSimpleInfo' where code = 'sms_apihost';

-- 添加魔蝎相关配置
INSERT INTO `arc_sys_config` VALUES (null, '90', '回调验签', 'mx_secret', '27c7e4bc518c48d095d9caf544771876', '1', '魔蝎回调验签', '1');
INSERT INTO `arc_sys_config` VALUES (null, '90', '魔蝎token', 'mx_token', '1c083a407db749959ab0011911d42398', '1', '魔蝎token', '1');
INSERT INTO `arc_sys_config` VALUES (null, '90', '魔蝎apikey', 'mx_apikey', '253d35f12bd14d4d9150b99a4e179453', '1', '魔蝎apikey', '1');
INSERT INTO `arc_sys_config` VALUES (null, '90', '魔蝎获取运营商加强数据URL', 'mx_operator_mxdata', 'https://api.51datakey.com/carrier/v3/mobiles/{mobile}/mxdata-ex', '1', '魔蝎运营商数据加强版请求url', '1');

-- 富有支付相关配置 2018-11-28日添加
INSERT INTO `arc_sys_config` VALUES (null, 80, '富有验证码接口', 'fuiou_protocol_bindmsg_url', 'http://www-1.fuiou.com:18670/mobile_pay/newpropay/bindMsg.pay', 1, '发送短信验证码接口-绑卡', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '协议卡绑定', 'fuiou_protocol_bindcommit_url', 'http://www-1.fuiou.com:18670/mobile_pay/newpropay/bindCommit.pay', 1, '协议卡绑定', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '协议解绑接口', 'fuiou_protocol_unbind_url', 'http://www-1.fuiou.com:18670/mobile_pay/newpropay/unbind.pay', 1, '协议解绑接口', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '协议卡查询', 'fuiou_protocol_bindquery_url', 'http://www-1.fuiou.com:18670/mobile_pay/newpropay/bindQuery.pay', 1, '协议卡查询', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '协议支付接口', 'fuiou_protocol_order_url', 'http://www-1.fuiou.com:18670/mobile_pay/newpropay/order.pay', 1, '协议支付接口', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '富友订单号查询', 'fuiou_protocol_queryorderid_url', 'http://www-1.fuiou.com:18670/mobile_pay/findPay/queryOrderId.pay', 1, '订单结果查询接口(富友订单号)', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '商户订单号查询', 'fuiou_protocol_checkresult_url', 'http://www-1.fuiou.com:18670/mobile_pay/checkInfo/checkResult.pay', 1, '订单结果查询接口(商户订单号)', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '卡Bin查询接口', 'fuiou_protocol_cardbinquery_url', 'http://www-1.fuiou.com:18670/mobile_pay/findPay/cardBinQuery.pay', 1, '商户支持卡 Bin 查询接口', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, 'RSA私钥', 'fuiou_protocol_privatekey', '\"\"', 1, 'RSA私钥', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '商户号', 'fuiou_protocol_mchntcd', '0002900F0096235', 1, '商户号', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '商户密钥', 'fuiou_protocol_mchntcd_key', '5old71wihg2tqjug9kkpxnhx9hiujoqj', 1, '商户密钥', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '支付是否开启', 'fuiou_switch', '1', 1, '1开2关', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '商户号', 'fuiou_merid', '0002900F0345178', 1, '商户号', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '商户密码', 'fuiou_pwd', '123456', 1, '商户密码', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '代收付地址', 'fuiou_daifu_req_url', 'https://fht-test.fuiou.com/fuMer/req.do', 1, '代收付地址第三方接口', 1);
INSERT INTO `arc_sys_config` VALUES (null, 20, '是否启用砍头息', 'behead_fee', '10', 1, '是否启用砍头息10启用，20-不启用', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, 'face的apiKey', 'face_api_key', 'c3_OTRm4Jhhl6hbDllq-Nqj5nheHlSiQ', 1, 'face的apiKey', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, 'face的secret', 'face_api_secret', '44rXoUZghpR-lDr6BFVsuSC6L962yQc6', 1, 'face的secret', 1);
INSERT INTO `arc_sys_config` VALUES (null, 20, '是否展期', 'delay_switch', '10', 1, '是否展期：10-开启，20-不启用', 1);
INSERT INTO `arc_sys_config` VALUES (null, 20,'app名称','appName','鲁班.柒号',1,'产品名称',1);
INSERT INTO `arc_sys_config` VALUES (null, 20,'绑卡开关','bindCardSwitch','20',1,'10-不支持借款未结束换绑卡，20-支持借款未结束换绑卡',1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '魔蝎报告url前缀', 'magic_prefix_report_url', 'https://tenant.51datakey.com/carrier/report_data?data=', 1, '魔蝎报告url前缀', 1);

alter table `cl_pay_log` add column `pay_order_no` varchar(64)  default '';


-- 修改移动端秘钥
update `arc_sys_config` set value='oQIhAP24Kb3Bsf7IE14wpl751bQc9VAPsFZ+LdB4riBgg2TDAiEAsSomOO1v8mK2VWhEQh6mttgN' where code='app_key';

-- 添加魔蝎获取运营商报告url
INSERT INTO `arc_sys_config` VALUES (null, '90', '运营商报告', 'mx_operator_report', 'https://api.51datakey.com/carrier/v3/mobiles/{mobile}/mxreport', '1', '获取魔蝎运营商报告URL', '1');

-- 添加魔蝎运营商认证页面url
INSERT INTO `arc_sys_config` VALUES (null, '90', '魔蝎运营商认证URL', 'mx_operator_url', 'https://api.51datakey.com/h5/importV3/index.html#/carrier', '1', '魔蝎运营商认证页面url', '1');

-- 添加魔蝎风控云相关配置 2018-12-3
INSERT INTO `arc_sys_config` VALUES (null, '90', '魔蝎私钥', 'mx_private_key', 'MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCYhUelDx060oB1Z6Go0QkzxyKtcVTZYMJtJtsKzwQjvYqHTGRZUkg1wKSehTb1kZFSa+uA6bM7xP5CAVnOL5QHZWy1a5kENIXXl8gRbvb35gSOTVhgaoEWUA7fRSj6Gm8c9g1MwGVihtVY8Bl6khidnqnDrooLopWvif7kajLBg2byoy8nsjLv9uMDVyjuYtiAJvaBW/7LcUfqSFjxWULkWXv6O5yYUIW3nY0uAGxPMbsrcFbtZ4jFsLVnb/Prny6jVEvTHAyXXxMKzf9JVPq0JLIkYzjArdSPuT2MzIVqfXAvVCaMrY5OfxIpogq7v7IzExWRALK6YQFNSFFQRm81AgMBAAECggEAJkAmu6qSthHczZTz4haqrCWg+MaXdPzjReScwpUwgJYki9IIyK3uFiz+HBNkuSmMvJL8ORRjMvYwnfUgxKkCAujY3pbhhUB24J7cOiMOJRW2xsl3BJcxUJs7X5tEr5S5kRXUOA94XcFa+Dce7LJ/IeiMwtqfHFPmHmQJXNeVaxpZhKYdJ9UUbwRpEawpnNEWlK8VMfJuItFBtmqQxWR+75OTcjEnETN8LIBOk+jk1F5ZTqe6QnsHxMjziQxrUaozMwEsawVwuo84pumZO8JsdB/6jklmXaH7tKfRO1zyXg/G+7Wpi/1vEHUed5nH9Sqi1XbtUzvPvJ92V+UBm/rd8QKBgQDeZ/2RnoUZVIv2rwKx9dP1s+ydKdWugFxwUGKAk798xdk5cBvAusTXN1y6JJevpaQhz7s6ieANHq2oUOc3XkVOUoQ2GE80zYmTeip8xk11b2GEBBq1IkyqbgO7Oc+WKh466GXAl0BPIU/PHCzJEfxWyDQIFq0MvnKK0aWhdKLC/wKBgQCvjvOW9rslIap4Wjq+ulai6QxHQhk4mVevLA37AZ9wNAS60VOcx2N5qzv6qFm16fyIhV8emOb1FRa70JHDvytSrxICeapU1D0+bQdwvAqR8EYZCFwhElGRczUzBrI1C6F7QCFXiogtFFK6iX97H0l+bgVzHxk5kkeCRKH6ax0xywKBgQCsqo9yPl+XCsye98Bf6Tplxwor+g6rK3yYtc/DgvFnLTM4JJFnT5kCfTjASyh4dWC33068Y85OYULxKd5YXhfwdp+uX7EejXQByxaxSENaqN5MX+NcPQTITCEZeghvzMMreMGGg+apiHMtl/ItKIl/1m6O2QwAmNrUa27rqmGygQKBgGWJ58eL4wgB5cgAX/PKcKt+4RQh3daHkliFwXxJHv9VhtrKkXKfPCC18lXyoxh9QgtwIGjhBMwBq4lrg7NU0WjcSy1VUzgHHXkKtH/2sqJf9xspY6fZIYZfvQhLOi1GEdroZ6BrRoHUPFkZh7sdiHNwjQRdm0haG7PzoD/nThZrAoGAVw4yyk6WtOQrqHLbfVKM4VZg7gJl3K58fYWYZpin1ADXqbeHrzUd3aeMhTZKnzqNZ9WcAvmxDzizhJPUUTutfEnXcJBiL3PpkmGHMv9IqSycq/8D2/iIZzHXT4KK69FsqH/FYYW9r8tmeCZuEoFM29qLHUV9Zy1OHIziDSzwPHQ=', '1', '魔蝎私钥', '1');
INSERT INTO `arc_sys_config` VALUES (null, '90', '魔蝎应用id', 'mx_app_id', '1c083a407db749959ab0011911d42398', '1', '魔蝎分配应用id', '1');
INSERT INTO `arc_sys_config` VALUES (null, '90', '魔蝎风控请求url', 'mx_risk_url', 'https://api.51datakey.com/risk-gateway/api/gateway', '1', '魔蝎风控url', '1');

-- 配置修改
update `arc_sys_config` set value='http://47.110.61.233:8080' where code='server_host';
update `arc_sys_config` set value='http://47.110.61.233:8081' where code='manage_host';
update `arc_sys_config` set value='7',remark='借款天数(7,10,14)' where code='borrow_day';
update `arc_sys_config` set value='0.25',remark='综合费用集合(0.098,0.12,0.15)' where code='fee';
update `arc_sys_config` set value='https://api.megvii.com/faceid/v3/ocridcard' where code='linkface_idOcr';
update `arc_sys_config` set value='https://api.megvii.com/faceid/v2/verify' where code='linkface_liVerification';
update `arc_sys_config` set value='10' where code='zhima_auth';
update `arc_sys_config` set value='/api/act/borrow/protocolPreview1.htm?protocol=me' where code='protocol_borrow';

-- 修改调用外部收据收费表用户标识字段
ALTER TABLE `cl_calls_outside_fee` change user_id `user_id` bigint(20) DEFAULT  NULL COMMENT '用户标识';

-- 配置修改,修改借款额度
update `arc_sys_config` set value=1000,remark='借款额度(500,1000)' where code='borrow_credit';

-- 新增自动审核成功,是否人工审核配置 2018.12.9
INSERT INTO `arc_sys_config` VALUES (null, '10', '人工审核开关', 'review_loan', '10', '1', '机审通过,人工审核开关 10-启用 20-禁用', '1');

-- 新建新颜请求记录表和小额网贷报告表
DROP TABLE IF EXISTS `cl_xinyan_req_log`;
CREATE TABLE `cl_xinyan_req_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `trans_id` varchar(64) DEFAULT NULL COMMENT '申请订单号',
  `trade_no` varchar(64) DEFAULT '' COMMENT '同步响应订单号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `borrow_id` bigint(20) DEFAULT NULL COMMENT '借款订单id',
  `is_success` tinyint(1) DEFAULT NULL COMMENT '请求是否成功 0-失败 1-成功',
  `resp_code` varchar(10) DEFAULT '' COMMENT '回调返回码',
  `resp_params` mediumtext COMMENT '同步响应结果',
  `resp_time` datetime DEFAULT NULL COMMENT '同步响应时间',
  `is_fee` tinyint(1) DEFAULT NULL COMMENT '是否收费 0-不收费 1-收费',
  `type` tinyint(2) DEFAULT NULL COMMENT '类型 1-小额网贷 2-行为雷达',
  `data_code` varchar(10) DEFAULT NULL COMMENT 'data响应码 0-查询成功 1-查询未命中 9-其他异常',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='新颜请求记录表';

DROP TABLE IF EXISTS `cl_xinyan_loan_report`;
CREATE TABLE `cl_xinyan_loan_report` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `trade_no` varchar(64) DEFAULT '' COMMENT '交易订单号',
  `score` varchar(50) DEFAULT NULL COMMENT '分数',
  `cur_max_credit` varchar(50) DEFAULT NULL COMMENT '本业务最大授信额度',
  `cur_avg_credit` varchar(50) DEFAULT NULL COMMENT '本业务平均授信额度',
  `cur_loan_cnt_30d` varchar(50) DEFAULT NULL COMMENT '本业务近1个月贷款笔数',
  `cur_loan_cnt_90d` varchar(50) DEFAULT NULL COMMENT '本业务近3个月贷款笔数',
  `cur_loan_cnt_180d` varchar(50) DEFAULT NULL COMMENT '本业务近6个月贷款笔数',
  `cur_loan_total_cnt` varchar(50) DEFAULT NULL COMMENT '本业务贷款总笔数',
  `cur_loan_org_total_cnt` varchar(50) DEFAULT NULL COMMENT '本业务贷款机构数',
  `cur_last_to_end_loan` varchar(50) DEFAULT NULL COMMENT '本业务最近一次贷款距今天数',
  `cur_loan_clear_cnt` varchar(50) DEFAULT NULL COMMENT '本业务贷款已结清笔数',
  `cur_overdue_cnt_30d` varchar(50) DEFAULT NULL COMMENT '本业务贷款逾期订单数（30天）',
  `cur_overdue_cnt_more_30d` varchar(50) DEFAULT NULL COMMENT '本业务贷款逾期订单数（30天以上）',
  `query_org_cnt` varchar(50) DEFAULT NULL COMMENT '查询多头机构数',
  `query_cnt` varchar(50) DEFAULT NULL COMMENT '总查询次数',
  `last_to_end_days` varchar(50) DEFAULT NULL COMMENT '最近查询时间距今天数',
  `query_cnt_30d` varchar(50) DEFAULT NULL COMMENT '近1个月查询多头',
  `query_cnt_90d` varchar(50) DEFAULT NULL COMMENT '近3个月查询多头',
  `query_cnt_180d` varchar(50) DEFAULT NULL COMMENT '近6个月查询多头',
  `loan_clear_num` varchar(50) DEFAULT NULL COMMENT '贷款已结清笔数',
  `overdue_cnt_30d` varchar(50) DEFAULT NULL COMMENT '贷款逾期订单数（30天）',
  `overdue_cnt_more_30d` varchar(50) DEFAULT NULL COMMENT '贷款逾期订单数（30天以上）',
  `work_day_not_overdue_amount_radio_30d` varchar(50) DEFAULT NULL COMMENT '最近1个月工作日全部产品非逾期借贷在总借贷中金额占比',
  `not_overdue_order_radio_180d` varchar(50) DEFAULT NULL COMMENT '最近6个月全部时间全部产品非逾期借贷在总借贷中订单数占比',
  `overdue_order_radio_90d` varchar(50) DEFAULT NULL COMMENT '最近3个月全部时间消费金融类在全部产品中借贷确定逾期订单数占比',
  `max_loan_rate_12m` varchar(50) DEFAULT NULL COMMENT '最近一年新增平台全部时间全部产品最大借贷费率',
  `avg_loan_rate_12m` varchar(50) DEFAULT NULL COMMENT '最近12个月全部时间超短期现金贷平均借贷费率',
  `overdue_org_cnt_6m` varchar(50) DEFAULT NULL COMMENT '最近半年新增平台全部时间全部产品平均借贷确定逾期平台数',
  `dd_overdue_days_20time` varchar(50) DEFAULT NULL COMMENT '最近20次全部时间全部产品最大借贷疑似逾期天数',
  `dd_work_day_overdue_days_3time` varchar(50) DEFAULT NULL COMMENT '最近3次工作日全部产品平均借贷疑似逾期天数',
  `dd_overdue_days_12m` varchar(50) DEFAULT NULL COMMENT '最近一年新增平台全部时间全部产品平均还款疑似逾期天数',
  `dd_max_overdue_days_3m` varchar(50) DEFAULT NULL COMMENT '最近3个月全部时间全部产品最大还款疑似逾期天数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `resp_time` datetime DEFAULT NULL COMMENT '响应时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='新颜-小额网贷报告';

ALTER TABLE cl_calls_outside_fee add cast_type tinyint(4) NOT NULL COMMENT '费用类型 0：消费，1：充值';

ALTER TABLE cl_calls_outside_fee add phone CHAR(16) NOT NULL comment '手机号码';

ALTER TABLE cl_calls_outside_fee change type type tinyint(4) NOT NULL COMMENT '调用类型 1-运营商 2-魔杖反欺诈 3-魔杖多头 4-魔杖黑灰名单 5-魔杖贷后行为,6-发送短信，7-人脸识别';

-- 屏蔽代理商管理菜单
delete from arc_sys_role_menu where menu_id in (3,4,30,31,32,33,34,35,36,37,38);

-- 黑名单接口 2018-12-10
DROP TABLE IF EXISTS `cl_name_blacklist`;
CREATE TABLE `cl_name_blacklist` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增字段',
  `dimensionKey` varchar(8) NOT NULL DEFAULT '' COMMENT '类别：01-身份证、02-手机号、等',
  `dimensionValue` varchar(64) NOT NULL DEFAULT '' COMMENT '类别对应的值',
  `source` varchar(16) NOT NULL DEFAULT '' COMMENT '来源',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态 0:正常，1:删除',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastModifyTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_dimensionValue_dimensionKey_source` (`dimensionValue`,`dimensionKey`,`source`),
  KEY `idx_dimensionValue` (`dimensionValue`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED COMMENT='黑名单';

DROP TABLE IF EXISTS `cl_name_whitelist`;
CREATE TABLE `cl_name_whitelist` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增字段',
  `dimensionKey` varchar(8) NOT NULL DEFAULT '' COMMENT '类别：01-身份证、02-手机号、等',
  `dimensionValue` varchar(64) NOT NULL DEFAULT '' COMMENT '类别对应的值',
  `source` varchar(16) NOT NULL DEFAULT '' COMMENT '来源',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态 0:正常，1:删除',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastModifyTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_dimensionValue_dimensionKey_source` (`dimensionValue`,`dimensionKey`,`source`),
  KEY `idx_dimensionValue` (`dimensionValue`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED COMMENT='白名单';

-- 新增小额网贷报告菜单
INSERT INTO `arc_sys_menu` VALUES ('1006', '0', '小额网贷报告', '1001', '', null, '00000000003', null, '', '2017-01-01 00:00:00', '', '小额网贷报告', '0', 'XinyanLoanReport', null, null, null, null);
INSERT INTO `arc_sys_role_menu` VALUES ('106', '1', '1006');

-- 新颜请求相关配置
update arc_sys_config set value = 'cl_user_base_info,cl_operator_basic,cl_magic_untrusted,cl_magic_black_gray,cl_magic_fraudulence_info,cl_magic_multipoint,cl_magic_risk_qq_group,cl_magic_suspicious_device,cl_magic_suspicious_idcard,cl_magic_suspicious_mobile,cl_magic_loan_behavior_analysis,cl_magic_mobile_contact,cl_magic_intimate_contact,cl_magic_credit_card_overdue,cl_magic_risk_device,cl_xinyan_loan_report' where code = 'rule_tables';

INSERT INTO `arc_sys_config` VALUES (null, '100', '新颜商户号', 'xy_member_id', '8000013189', '1', '新颜商户号', '1');
INSERT INTO `arc_sys_config` VALUES (null, '100', '新颜终端号', 'xy_terminal_id', '8000013189', '1', '新颜终端号', '1');
INSERT INTO `arc_sys_config` VALUES (null, '100', '新颜小额网贷请求地址', 'xy_loan_url', 'https://test.xinyan.com/product/integrity/v1/loans', '1', '新颜小额网贷请求地址', '1');
INSERT INTO `arc_sys_config` VALUES (null, '100', '新颜私钥密码', 'xy_pfx_pwd', '217526', '1', '新颜私钥密码', '1');
INSERT INTO `arc_sys_config` VALUES (null, '100', '新颜私钥文件名称', 'xy_pfx_name', '8000013189_pri.pfx', '1', '新颜私钥文件名称', '1');

-- 删除人工复审菜单
delete from arc_sys_role_menu where menu_id = 50;

-- 信审人员管理,配单相关
INSERT INTO `arc_sys_role` VALUES ('8', '审核管理员', 'reviewer',  '2017-01-01 00:00:00', 'system', '2017-01-01 00:00:00', 'system', '审核管理员', '0');
INSERT INTO `arc_sys_role` VALUES ('9', '审核专员', 'reviewSpecialist',  '2017-01-01 00:00:00', 'system', '2017-01-01 00:00:00', 'system', '请勿改动该角色唯一标识', '0');

INSERT INTO `arc_sys_menu` VALUES ('1008', '0', '审核人员管理', '0', '', 'icon-renyuan', '00000000015', null, '', '2017-01-01 00:00:00', '', '审核人员管理', '0', 'ReviewPersonnelManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('1009', '0', '审核订单管理', '0', '', 'icon-dingdan', '00000000015', null, '', '2017-01-01 00:00:00', '', '审核订单管理', '0', 'ReviewOrderListManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('1010', '0', '我的审核订单', '0', '', 'icon-wodedingdan', '00000000015', null, '', '2017-01-01 00:00:00', '', '我的审核订单', '0', 'MyReviewOrderManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('1011', '0', '信审员', '1008', '', null, '00000000001', null, '', '2017-01-01 00:00:00', '', '信审员列表', '0', 'ReviewPersonnelList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('1012', '0', '审核总订单', '1009', '', null, '00000000001', null, '', null, '', '审核总订单列表', '0', 'ReviewTotalOrderList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('1013', '0', '我的订单', '1010', '', null, '00000000001', null, '', null, '', '我的信审订单', '0', 'MyReviewOrder', null, null, null, null);

INSERT INTO `arc_sys_role_menu` VALUES ('108', '1', '1008');
INSERT INTO `arc_sys_role_menu` VALUES ('109', '1', '1009');
INSERT INTO `arc_sys_role_menu` VALUES ('110', '1', '1010');
INSERT INTO `arc_sys_role_menu` VALUES ('111', '1', '1011');
INSERT INTO `arc_sys_role_menu` VALUES ('112', '1', '1012');
INSERT INTO `arc_sys_role_menu` VALUES ('113', '1', '1013');

DROP TABLE IF EXISTS `cl_manual_review_order`;
CREATE TABLE `cl_manual_review_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '审核人id',
  `user_name` varchar(20) DEFAULT '' COMMENT '审核人姓名',
  `borrow_name` varchar(20) DEFAULT '' COMMENT '借款人姓名',
  `phone` varchar(20) DEFAULT '' COMMENT '借款人手机号',
  `borrow_id` bigint(20) DEFAULT NULL COMMENT '借款id',
  `state` varchar(2) DEFAULT '10' COMMENT '订单状态   10未分配，11待审核，20审核通过，30审核拒绝',
  `remark` varchar(500) DEFAULT '' COMMENT '备注说明',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `review_time` datetime DEFAULT NULL COMMENT '审核时间',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `borrow_id` (`borrow_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人工审核订单表';

-- 删除广告菜单权限
delete from arc_sys_role_menu where menu_id = 23;
delete from arc_sys_role_menu where menu_id = 98;

-- 展期提醒短信模板修改
update cl_sms_tpl set tpl = '您的订单已展期成功，新的截止日期{$year}年{$month}月{$day}日',number = 'SMS1193783392' where type = 'delayPlan';

DROP TABLE IF EXISTS `cl_operator_voice_cnt`;
CREATE TABLE `cl_operator_voice_cnt` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增字段',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `req_log_id` bigint(20) NOT NULL COMMENT '请求记录id',
  `peer_num` varchar(32) NOT NULL DEFAULT '' COMMENT '运营商号码',
  `peer_name` varchar(64) NOT NULL DEFAULT '' COMMENT '运营商',
  `city` varchar(32) NOT NULL DEFAULT '' COMMENT '号码归属地',
  `contact_phone` varchar(32) NOT NULL DEFAULT '' COMMENT '通讯录联系号码',
  `contact_name` varchar(64) CHARACTER SET utf8mb4 NOT NULL DEFAULT '' COMMENT '通讯录联系人姓名',
  `call_cnt_num` varchar(32) NOT NULL DEFAULT '' COMMENT '联系次数/时间(秒)',
  `dial_cnt_num` varchar(32) NOT NULL DEFAULT '' COMMENT '主叫次数/时间(秒)',
  `dialed_cnt_num` varchar(32) NOT NULL DEFAULT '' COMMENT '被叫次数/时间(秒)',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastModifyTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPRESSED COMMENT='通话详情统计';

-- 修改推广链接
update arc_sys_config set value = '/h5/invite.jsp' where code = 'h5_invite';

INSERT INTO `arc_sys_config` VALUES (null, '80', '信德数聚appid', 'xinde_data_appId', 'c614780e6f19c43e2d1af3f81bcfba9addb32922', '1', '信德数聚appid', '1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '信德数聚secret', 'xinde_data_appSecret', '68753208ba1abf4a2c0bc2871ae644ea79cabca2', '1', '信德数聚secret', '1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '信德数聚url', 'xinde_data_url', 'https://api.xindedata.com/v1/task', '1', 'url', '1');

CREATE TABLE `cl_blacklist_xinde_data` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增字段',
  `borrow_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '借款id',
  `phone` varchar(24) NOT NULL DEFAULT '' COMMENT '手机号',
  `id_card` varchar(24) NOT NULL DEFAULT '' COMMENT '身份证号',
  `is_lastloan_refused` varchar(8) NOT NULL DEFAULT 'false' COMMENT '最后一次申请是否被拒贷 true: 是；false: 否',
  `total_loan_count` int(4) NOT NULL DEFAULT '0' COMMENT '历史借款次数(所有的借款次数，包含当前借款)',
  `total_overdue_count` int(4) NOT NULL DEFAULT '0' COMMENT '历史逾期次数(所有的逾期次数，包含当前逾期)',
  `longest_overdue_paid` varchar(16) NOT NULL DEFAULT '' COMMENT '已经还清的历史逾期最长时间，M1:小于1月; M2:大于1月，小于2月; M3:大于2月，小于3月; M4:3月及以上',
  `current_overdue_count` int(4) NOT NULL DEFAULT '0' COMMENT '当前处于逾期状态的借款笔数',
  `current_overdue_amount` int(4) NOT NULL DEFAULT '0' COMMENT '当前逾期总金额，0: 0(没有逾期); 1:[0,100]; 2:[100,500); 3:[500,1000); 4:[1000,2000); 5:[2000,4000); 6:[4000,6000); 7:[6000,10000); 8:>=10000',
  `over_due90_contacts_count` int(4) NOT NULL DEFAULT '0' COMMENT '有逾期90天以上运营商联系人个数',
  `longest_overdue_unpaid` varchar(16) NOT NULL DEFAULT '' COMMENT '当前最长逾期时间(不包括已经还清的)，M1:小于1月; M2:大于1月，小于2月; M3:大于2月，小于3月; M4:3月及以上',
  `last_loan_refuse_reason` varchar(16) NOT NULL DEFAULT '' COMMENT '最后一次拒贷原因',
  `last_loan_refuse_time` varchar(16) NOT NULL DEFAULT '' COMMENT '最后一次拒贷时间',
  `remark` text COMMENT '其他详情',
  `first_loan_time` varchar(32) NOT NULL DEFAULT '',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_phone_idcard_borrowid` (`phone`,`id_card`,`borrow_id`),
  KEY `borrow_id` (`borrow_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED COMMENT='信德数聚灰名单结果';

-- 添加用户短信内容
ALTER table cl_user_messages ADD content text CHARACTER SET utf8mb4 COMMENT '短信内容';

-- 给用户短信表添加 索引
ALTER TABLE cl_user_messages ADD INDEX index_user_id ( `user_id` ) ;

-- 2018-12-20
CREATE TABLE `cl_blacklist_task` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增字段',
  `task_name` varchar(32) NOT NULL DEFAULT '' COMMENT '任务名称',
  `task_type` varchar(32) NOT NULL DEFAULT '' COMMENT '任务类型',
  `task_version` int(4) NOT NULL DEFAULT '1' COMMENT '任务版本',
  `task_data` text COMMENT '任务代码数据',
  `remark` varchar(64) NOT NULL DEFAULT '' COMMENT '备注',
  `yn` tinyint(4) NOT NULL DEFAULT '2' COMMENT '是否有效：1-有效;2-无效',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `task_name` (`task_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED COMMENT='黑名单外部数据调用任务';

CREATE TABLE `cl_blacklist_common_data` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增字段',
  `borrow_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '借款id',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户的id',
  `phone` varchar(24) NOT NULL DEFAULT '' COMMENT '手机号',
  `name` varchar(16) NOT NULL DEFAULT '' COMMENT '姓名',
  `id_card` varchar(24) NOT NULL DEFAULT '' COMMENT '身份证号',
  `black_type` int(4) NOT NULL DEFAULT '0' COMMENT '0-正常，1-黑名单，2-灰名单',
  `data_msg` text COMMENT '数据详情',
  `source` varchar(32) NOT NULL DEFAULT '' COMMENT '黑名来源',
  `remark` varchar(64) NOT NULL DEFAULT '' COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_phone_idcard_borrowid` (`phone`,`id_card`,`borrow_id`),
  KEY `borrow_id` (`borrow_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED COMMENT='外部黑名单数据';

INSERT INTO `arc_sys_config` VALUES (null, '80', '拍拍信appid', 'paipaixin_data_appId', 'key2cf89a6900644d80b36fe25ccc67968e', '1', '拍拍信appid', '1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '拍拍信secret', 'paipaixin_data_appSecret', 'nqw2bTruB5311OIytF8qyv5LSDy4shO5g89eebQ4zAXSIYV7L2kKuMs9f6OfEmL0', '1', '拍拍信secret', '1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '拍拍信url', 'paipaixin_blacklist_url', 'https://api.ppcredit.com/router/rest', '1', '拍拍信url', '1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '拍拍信黑名单method', 'paipaixin_blacklist_method', 'ppc.risklist.query.v1', '1', '拍拍信黑名单method', '1');

INSERT INTO `arc_sys_menu` VALUES ('1014', '0', '任务管理', '0', '', 'icon-tongji', '00000000022', null, '', '2017-01-01 00:00:00', '', '任务管理', '0', 'TaskManageController', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('1015', '0', '黑名单任务', '1014', '', null, '00000000001', null, '', null, '', '黑名单任务', '0', 'BlackTaskManager', null, null, null, null);
INSERT INTO `arc_sys_role_menu` VALUES (null, '1', '1014');
INSERT INTO `arc_sys_role_menu` VALUES (null, '1', '1015');

-- 用户应用程序列表
CREATE TABLE `cl_app_list` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `user_id` bigint(20) NOT NULL COMMENT '用户标识(关联客户主键)',
  `app_list` text COMMENT '应用程序列表',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户应用程序列表';

-- 宜信风险评估接口相关配置
INSERT INTO `arc_sys_config` VALUES (null, '80', '宜信用户名', 'yixin_user_name', 'jiya_testusr', '1', '宜信用户名', '1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '宜信调用秘钥', 'yixin_sign', '1aea54c5af220130', '1', '宜信调用秘钥', '1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '宜信风险评估调用url', 'yixin_risk_url', 'https://starapi.zhichengcredit.com/submit', '1', '宜信风险评估调用url', '1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '宜信风险评估接口名称', 'yixin_risk_api_name', 'credit.evaluation.share.api', '1', '宜信风险评估接口名称', '1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '宜信欺诈甄别接口名称', 'yixin_fraud_api_name', 'fraud.screening.advance.api', '1', '宜信欺诈甄别接口名称', '1');

-- 宜信风险评估 欺诈甄别加入到借款策略中
INSERT INTO `cl_rc_tpp` VALUES ('3', 'YX', 'yixin', '', '', '', '', '10', '2018-12-26 00:00:00');
INSERT INTO `cl_rc_tpp_business` VALUES ('5', '3', 'YX风险评估', 'YixinRisk', '10', '', '', null, '2018-12-26 00:00:00');
INSERT INTO `cl_rc_scene_business` VALUES ('5', '10', '5', '10', 0, '10', '10', '2018-12-26 00:00:00');
INSERT INTO `cl_rc_tpp_business` VALUES ('6', '3', 'YX欺诈甄别', 'YixinFraud', '10', '', '', null, '2018-12-26 00:00:00');
INSERT INTO `cl_rc_scene_business` VALUES ('6', '10', '6', '10', 0, '10', '10', '2018-12-26 00:00:00');

-- 宜信风险评估建表
DROP TABLE IF EXISTS `cl_yixin_req_log`;
CREATE TABLE `cl_yixin_req_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `flow_id` varchar(64) DEFAULT '' COMMENT '同步响应流水号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `borrow_id` bigint(20) DEFAULT NULL COMMENT '借款订单id',
  `is_success` tinyint(1) DEFAULT NULL COMMENT '请求是否成功 0-失败 1-成功',
  `resp_code` varchar(10) DEFAULT '' COMMENT '回调返回码',
  `resp_msg` mediumtext COMMENT '同步响应结果',
  `resp_time` datetime DEFAULT NULL COMMENT '同步响应时间',
  `is_fee` tinyint(1) DEFAULT NULL COMMENT '是否收费 0-不收费 1-收费',
  `type` tinyint(2) DEFAULT NULL COMMENT '类型 1-风险评估',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='宜信请求记录';

DROP TABLE IF EXISTS `cl_yixin_risk_report`;
CREATE TABLE `cl_yixin_risk_report` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户标识',
  `borrow_id` bigint(20) DEFAULT NULL COMMENT '借款订单id',
  `flow_id` varchar(64) DEFAULT '' COMMENT '流水号',
  `data` longtext COMMENT '返回内容',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='宜信风险评估';

-- 凭安染黑度统计接口
DROP TABLE IF EXISTS `cl_pingan_req_log`;
CREATE TABLE `cl_pingan_req_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `borrow_id` bigint(20) DEFAULT NULL COMMENT '借款订单id',
  `is_success` tinyint(1) DEFAULT NULL COMMENT '请求是否成功 0-失败 1-成功',
  `resp_code` varchar(10) DEFAULT '' COMMENT '回调返回码',
  `resp_msg` mediumtext COMMENT '同步响应message',
  `resp_time` datetime DEFAULT NULL COMMENT '同步响应时间',
  `is_fee` tinyint(1) DEFAULT NULL COMMENT '是否收费 0-不收费 1-收费',
  `type` tinyint(2) DEFAULT NULL COMMENT '类型 1-染黑度统计',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='凭安请求记录';

DROP TABLE IF EXISTS `cl_pingan_grayscale`;
CREATE TABLE `cl_pingan_grayscale` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户标识',
  `borrow_id` bigint(20) DEFAULT NULL COMMENT '借款订单id',
  `data` longtext COMMENT '返回内容',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='凭安染黑度统计';

INSERT INTO `arc_sys_config` VALUES (null, '80', '凭安vkey', 'pingan_vkey', '120181225001', '1', '凭安vkey', '1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '凭安染黑度统计url', 'pingan_grayscale_stat_url', 'https://jrapi.pacra.cn/service?t=grayscale_stat', '1', '凭安染黑度统计url', '1');

-- 修改外部费用表结构
ALTER TABLE `cl_calls_outside_fee` change task_id `task_id` varchar(64) DEFAULT  NULL COMMENT '任务id';

-- 凭安染黑度统计加入到借款策略中
INSERT INTO `cl_rc_tpp` VALUES ('4', 'PA', 'pingan', '', '', '', '', '10', '2018-12-26 00:00:00');
INSERT INTO `cl_rc_tpp_business` VALUES ('7', '4', 'PA染黑统计', 'PinganGrayscaleStat', '10', '', '', null, '2018-12-26 00:00:00');
INSERT INTO `cl_rc_scene_business` VALUES ('7', '10', '7', '10', 0, '10', '10', '2018-12-26 00:00:00');

-- 有盾用户画像数据加入到借款策略中
INSERT INTO `cl_rc_tpp` VALUES ('6', 'YD', 'youdun', '', '', '', '', '10', '2019-03-12 00:00:00');
INSERT INTO `cl_rc_tpp_business` VALUES ('11', '6', 'YD用户画像', 'YouDunUserPortrait', '10', '', '', null, '2019-03-12 00:00:00');
INSERT INTO `cl_rc_scene_business` VALUES ('11', '10', '11', '10', 0, '10', '10', '2019-03-12 00:00:00');

-- 宜信欺诈甄别
DROP TABLE IF EXISTS `cl_yixin_fraud`;
CREATE TABLE `cl_yixin_fraud` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户标识',
  `borrow_id` bigint(20) DEFAULT NULL COMMENT '借款订单id',
  `flow_id` varchar(64) DEFAULT '' COMMENT '流水号',
  `data` longtext COMMENT '返回内容',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='宜信欺诈甄别';

-- 通话详情统计表新增最后联系时间字段
alter table `cl_operator_voice_cnt` add column `last_contact_time` datetime  default null comment '最后联系时间';

-- 魔蝎黑灰名单
INSERT INTO `cl_rc_tpp_business` VALUES ('8', '1', '黑灰名单', 'MagicBlackGray', '10', '', '', null, '2019-01-03 00:00:00');
INSERT INTO `cl_rc_scene_business` VALUES ('8', '10', '8', '10', 0, '10', '10', '2019-01-03 00:00:00');
update cl_rc_tpp_business set state = '20' where nid in ('MagicApply', 'MagicPostLoad');

-- 删除信用报告菜单
delete from arc_sys_menu where id in (1001,1002,1003,1006);
delete from arc_sys_role_menu where menu_id in (1001,1002,1003,1006);

-- 修改app_list长度
ALTER TABLE `cl_app_list` change app_list `app_list` MEDIUMTEXT COMMENT '应用程序列表';
-- 添加认证更新周期数据
INSERT INTO `arc_sys_config` VALUES (null, '20', '认证更新周期', 'authentication_cycle', '7', '1', '认证更新周期', '1');

INSERT INTO `cl_quartz_info` VALUES ('4', '运营商周期更新', 'doUpdateUserAuth', '0 0 0 * * ?', 'com.xiji.cashloan.manage.job.QuartzUserAuth', '0', '0', '20', '2017-03-27 14:53:27');

-- 最新版本号
INSERT INTO `arc_sys_config` VALUES (null, '10', '最新版本号', 'last_version', '1.0.1', '1', '系统最新版本号', '1');
-- 强制更新版本号
INSERT INTO `arc_sys_config` VALUES (null, '10', '强制更新版本号', 'mandatory_update_version', '1.0.0', '1', '系统强制更新版本号', '1');
-- 最新版本下载地址 线上
INSERT INTO `arc_sys_config` VALUES (null, '10', '最新版本下载地址', 'last_version_download_url', 'http://jy.xyhuigou.com/h5/invite.jsp', '1', '最新版本下载地址', '1');

-- 借款信息表添加是否逾期字段
ALTER table cl_borrow add is_overdue varchar(2) DEFAULT '10' COMMENT '是否逾期 10：未逾期，20 ：已逾期';
-- 同步更新是否逾期字段
update cl_borrow SET is_overdue = '20' where id in ( select borrow_id from cl_borrow_repay where penalty_day > 0) and is_overdue = '10';
-- 用户备注表
DROP TABLE IF EXISTS cl_user_remark;
CREATE TABLE `cl_user_remark` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `operate_id` bigint(20) NOT NULL COMMENT '操作人ID',
  `remark` varchar(128) DEFAULT '' COMMENT '备注',
  `operate_time` datetime NOT NULL COMMENT '操作时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户备注表';


-- 借款订单-运营商记录表
DROP TABLE IF EXISTS cl_borrow_operator_log;
CREATE TABLE `cl_borrow_operator_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `req_log_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '请求记录id',
  `borrow_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '借款标识',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='借款订单-运营商记录表';

-- 新颜行为雷达表
DROP TABLE IF EXISTS `cl_xinyan_xwld`;
CREATE TABLE `cl_xinyan_xwld` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户标识',
  `borrow_id` bigint(20) DEFAULT NULL COMMENT '借款订单id',
  `trade_no` varchar(64) DEFAULT '' COMMENT '交易订单号',
  `data` longtext COMMENT '返回内容',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `borrow_id` (`borrow_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='新颜行为雷达';

-- 新颜获取预订单号记录表
DROP TABLE IF EXISTS `cl_xinyan_pre_no_log`;
CREATE TABLE `cl_xinyan_pre_no_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `trans_id` varchar(64) NOT NULL DEFAULT '' COMMENT '申请订单号',
  `pre_order_no` varchar(64) NOT NULL DEFAULT '' COMMENT '预订单号',
  `user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '用户标识',
  `borrow_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '借款订单id',
  `is_success` tinyint(1) NOT NULL DEFAULT 0 COMMENT '请求是否成功 0-失败 1-成功',
  `resp_code` varchar(10) NOT NULL DEFAULT '' COMMENT '接口响应错误码',
  `resp_params` varchar(100) NOT NULL DEFAULT '' COMMENT '接口响应错误描述',
  `resp_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '同步响应时间',
  `type` tinyint(2)  NOT NULL DEFAULT 1 COMMENT '类型 1-行为雷达',
  `data_code` varchar(10) NOT NULL DEFAULT '0' COMMENT 'data响应码 0-查询成功 1-失败 9-其他异常',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='新颜获取预订单号记录表';

-- 运营商策略
INSERT INTO `cl_rc_tpp` VALUES ('5', '运营商', 'Operator', '', '', '', '', '10', '2019-01-17 00:00:00');
INSERT INTO `cl_rc_tpp_business` VALUES ('9', '5', '运营商', 'Operator', '10', '', '', null, '2019-01-17 00:00:00');
INSERT INTO `cl_rc_scene_business` VALUES ('9', '10', '9', '10', 0, '10', '10', '2019-01-17 00:00:00');

-- 新颜行为雷达
INSERT INTO `cl_rc_tpp_business` VALUES ('10', '2', '行为雷达', 'XYXWLD', '10', '', '', null, '2019-01-17 00:00:00');
INSERT INTO `cl_rc_scene_business` VALUES ('10', '10', '10', '10', 0, '10', '10', '2019-01-17 00:00:00');
update cl_rc_tpp_business set state = '20' where nid = 'XinyanLoan';

-- 展期天数
INSERT INTO `arc_sys_config` VALUES (null, '10', '展期天数', 'delay_days', '6', '1', '展期天数', '1');

-- 还款记录新增还款类型字段
ALTER TABLE cl_borrow_repay_log add column `type` varchar(10)  default '10' COMMENT '还款类型 10-还款 20-展期还款';

-- 新颜预订单号请求url
INSERT INTO `arc_sys_config` VALUES (null, '100', '预订单号请求地址', 'xy_pre_order_url', 'https://test.xinyan.com/entry/sdk/preOrder', '1', '预订单号获取请求地址', '1');

-- 规则表字段长度修改
ALTER TABLE `arc_rule_engine_config` change ccolumn `ccolumn` varchar(100) DEFAULT '' COMMENT '设置关联表列';
ALTER TABLE `arc_rule_engine_config` change column_comment `column_comment` varchar(255) DEFAULT '' COMMENT '字段名称';
ALTER TABLE `arc_borrow_rule_result` change col_nid `col_nid` varchar(100) NOT NULL DEFAULT '' COMMENT '列名英文名称';
ALTER TABLE `arc_borrow_rule_result` change col_name `col_name` varchar(255) NOT NULL DEFAULT '' COMMENT '列名中文名称';


-- 用户统计数据
DROP TABLE IF EXISTS `cl_user_statistic_data`;
CREATE TABLE `cl_user_statistic_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `count_time` datetime NOT NULL COMMENT '统计时间',
  `user_register` int(11) NOT NULL COMMENT '当日注册数',
  `auth_count` int(11) NOT NULL DEFAULT '0' COMMENT '实名认证人数',
  `contact_count` int(11) NOT NULL DEFAULT '0' COMMENT '通讯录认证数',
  `bank_count` int(11) NOT NULL DEFAULT '0' COMMENT '银行卡绑定数',
  `phone_count` int(11) NOT NULL DEFAULT '0' COMMENT '手机运营商认证数',
  `borrow_apply_count` int(11) NOT NULL DEFAULT '0' COMMENT '当日申请总数',
  `new_borrow_count` int(11) NOT NULL DEFAULT '0' COMMENT '当日新客借款',
  `old_borrow_count` int(11) NOT NULL DEFAULT '0' COMMENT '当日老客借款数',
  `new_load_count` int(11) NOT NULL DEFAULT '0' COMMENT '当日新客下款数',
  `old_load_count` int(11) NOT NULL DEFAULT '0' COMMENT '当日老客下款数',
  `load_count` int(11) NOT NULL DEFAULT '0' COMMENT '当日下款数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户统计数据';


-- 渠道统计数据
DROP TABLE IF EXISTS `cl_channel_statistic_data`;
CREATE TABLE `cl_channel_statistic_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `count_time` datetime NOT NULL COMMENT '统计时间',
  `channel_id` int(11) NOT NULL COMMENT '渠道ID',
  `user_register` int(11) NOT NULL COMMENT '当日注册数',
  `borrow_apply_count` int(11) NOT NULL DEFAULT '0' COMMENT '当日申请总数',
  `machine_audit_pass_count` int(11) NOT NULL DEFAULT '0' COMMENT '当日机审通过数',
  `machine_audit_not_pass_count` int(11) NOT NULL DEFAULT '0' COMMENT '当日机审拒绝数',
  `review_pass_count` int(11) NOT NULL DEFAULT '0' COMMENT '当日人工通过数',
  `review_not_pass_count` int(11) NOT NULL DEFAULT '0' COMMENT '当日人工拒绝数',
  `first_load_count` int(11) NOT NULL DEFAULT '0' COMMENT '当日首贷放款笔数',
  `again_load_count` int(11) NOT NULL DEFAULT '0' COMMENT '当日复贷放款笔数',
  `expire_overdue_count` int(11) NOT NULL DEFAULT '0' COMMENT '当日到期逾期笔数',
  `first_expire_overdue_count` int(11) NOT NULL DEFAULT '0' COMMENT '当日到期首逾笔数',
  `machine_audit_pass_rate` decimal(10,2) DEFAULT '0.00' COMMENT '机审通过率',
  `machine_audit_not_pass_rate` decimal(10,2) DEFAULT '0.00' COMMENT '机审拒绝率',
  `review_pass_rate` decimal(10,2) DEFAULT '0.00' COMMENT '人工复审通过率',
  `review_not_pass_rate` decimal(10,2) DEFAULT '0.00' COMMENT '人工复审拒绝率',
  `first_overdue_rate` decimal(10,2) DEFAULT '0.00' COMMENT '首逾率',
  `overdue_rate` decimal(10,2) DEFAULT '0.00' COMMENT '逾期率',
  `load_rate` decimal(10,2) DEFAULT '0.00' COMMENT '放款率',
  `extend_count` int(11) NOT NULL DEFAULT '0' COMMENT '今日到期展期数',
  `extend_overdue_count` int(11) NOT NULL DEFAULT '0' COMMENT '今日到期展期逾期数',
  `first_extend_count` int(11) NOT NULL DEFAULT '0' COMMENT '今日到期首贷展期数',
  `first_extend_overdue_count` int(11) NOT NULL DEFAULT '0' COMMENT '今日到期首贷展期逾期数',
  `first_expire_load_count` int(11) NOT NULL DEFAULT '0' COMMENT '今日到期首贷放款笔数',
  `again_expire_load_count` int(11) NOT NULL DEFAULT '0' COMMENT '今日到期复贷放款笔数',
  `new_borrow_apply_count` int(11) NOT NULL DEFAULT '0' COMMENT '当日新客申请量',
  `new_machine_audit_pass_count` int(11) NOT NULL DEFAULT '0' COMMENT '当日新客机审通过数',
  `new_machine_audit_not_pass_count` int(11) NOT NULL DEFAULT '0' COMMENT '当日新客机审拒绝数',
  `new_review_pass_count` int(11) NOT NULL DEFAULT '0' COMMENT '当日新客人工通过数',
  `new_review_not_pass_count` int(11) NOT NULL DEFAULT '0' COMMENT '当日新客人工拒绝数',
  `again_expire_overdue_count` int(11) NOT NULL DEFAULT '0' COMMENT '当日到期复贷逾期笔数',
  `again_overdue_rate` decimal(10,2) DEFAULT '0.00' COMMENT '复贷逾期率',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='渠道统计数据';


-- 放款统计数据
DROP TABLE IF EXISTS `cl_load_statistic_data`;
CREATE TABLE `cl_load_statistic_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `count_time` datetime NOT NULL COMMENT '统计时间',
  `load_count` int(11) NOT NULL DEFAULT '0' COMMENT '放款笔数',
  `load_amount` decimal(10,2) DEFAULT '0.00' COMMENT '放款金额',
  `load_principal` decimal(10,2) DEFAULT '0.00' COMMENT '放款本金',
  `first_load_count` int(11) NOT NULL DEFAULT '0' COMMENT '首贷人数',
  `first_load_amount` decimal(10,2) DEFAULT '0.00' COMMENT '首贷金额',
  `first_load_principal` decimal(10,2) DEFAULT '0.00' COMMENT '首贷本金',
  `again_load_count` int(11) NOT NULL DEFAULT '0' COMMENT '复贷人数',
  `again_load_amount` decimal(10,2) DEFAULT '0.00' COMMENT '复贷金额',
  `again_load_principal` decimal(10,2) DEFAULT '0.00' COMMENT '复贷本金',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='放款统计数据';


-- 审核统计数据
DROP TABLE IF EXISTS `cl_auditing_statistic_data`;
CREATE TABLE `cl_auditing_statistic_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `count_time` datetime NOT NULL COMMENT '统计时间',
  `borrow_apply_count` int(11) NOT NULL DEFAULT '0' COMMENT '申请笔数',
  `machine_audit_not_pass_count` int(11) NOT NULL DEFAULT '0' COMMENT '机审拒绝数',
  `machine_audit_pass_count` int(11) NOT NULL DEFAULT '0' COMMENT '当日机审通过数',
  `review_count` int(11) NOT NULL DEFAULT '0' COMMENT '人工审核笔数',
  `review_pass_count` int(11) NOT NULL DEFAULT '0' COMMENT '人工审核通过笔数',
  `review_not_pass_count` int(11) NOT NULL DEFAULT '0' COMMENT '人工审核拒绝笔数',
  `machine_audit_pass_rate` decimal(10,2) DEFAULT '0.00' COMMENT '机审通过率',
  `machine_audit_not_pass_rate` decimal(10,2) DEFAULT '0.00' COMMENT '机审拒绝率',
  `review_pass_rate` decimal(10,2) DEFAULT '0.00' COMMENT '人工复审通过率',
  `review_not_pass_rate` decimal(10,2) DEFAULT '0.00' COMMENT '人工复审拒绝率',
  `load_count` int(11) NOT NULL DEFAULT '0' COMMENT '放款笔数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='审核统计数据';


-- 还款统计数据
DROP TABLE IF EXISTS `cl_repayment_statistic_data`;
CREATE TABLE `cl_repayment_statistic_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `count_time` datetime NOT NULL COMMENT '统计时间',
  `should_order` int(11) NOT NULL DEFAULT '0' COMMENT '应还订单笔数',
  `remain_order` int(11) NOT NULL DEFAULT '0' COMMENT '待还订单笔数',
  `advance_repayment` int(11) NOT NULL DEFAULT '0' COMMENT '提前还款笔数',
  `normal_repayment` int(11) NOT NULL DEFAULT '0' COMMENT '正常还款笔数',
  `extend_repayment` int(11) NOT NULL DEFAULT '0' COMMENT '展期还款笔数',
  `overdue_repayment` int(11) NOT NULL DEFAULT '0' COMMENT '逾期还款笔数',
  `should_amount` decimal(10,2) DEFAULT '0.00' COMMENT '应还金额',
  `real_return_amount` decimal(10,2) DEFAULT '0.00' COMMENT '实还金额',
  `load_amount` decimal(10,2) DEFAULT '0.00' COMMENT '放款本金',
  `derate_amount` decimal(10,2) DEFAULT '0.00' COMMENT '减免金额',
  `remain_amount` decimal(10,2) DEFAULT '0.00' COMMENT '待还金额',
  `today_real_return_amount` decimal(10,2) DEFAULT '0.00' COMMENT '今日实还金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='还款统计数据';


-- 审核人员统计数据
DROP TABLE IF EXISTS `cl_auditor_statistic_data`;
CREATE TABLE `cl_auditor_statistic_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `count_time` datetime NOT NULL COMMENT '统计时间',
  `auditor_id` int(11) NOT NULL COMMENT '审核人员ID',
  `borrow_apply_count` int(11) NOT NULL DEFAULT '0' COMMENT '申请笔数',
  `pass_order` int(11) NOT NULL DEFAULT '0' COMMENT '人审通过总量',
  `first_pass_rate` decimal(10,2) DEFAULT '0.00' COMMENT '新客通过率',
  `current_overdue_rate` decimal(10,2) DEFAULT '0.00' COMMENT '总逾期率',
  `first_overdue_rate` decimal(10,2) DEFAULT '0.00' COMMENT '首逾率',
  `current_overdue` int(11) NOT NULL DEFAULT '0' COMMENT '总逾期数',
  `first_load_count` int(11) NOT NULL DEFAULT '0' COMMENT '首贷放款数',
  `first_overdue` int(11) NOT NULL DEFAULT '0' COMMENT '首贷逾期数',
  `load_count` int(11) NOT NULL DEFAULT '0' COMMENT '放款订单数',
  `first_pass_order` int(11) NOT NULL DEFAULT '0' COMMENT '新客通过数',
  `first_extend_overdue_count` int(11) NOT NULL DEFAULT '0' COMMENT '首贷展期逾期数',
  `new_borrow_apply_count` int(11) NOT NULL DEFAULT '0' COMMENT '新客申请量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='审核人员统计数据';


DROP TABLE IF EXISTS `cl_overdue_statistic_data`;
CREATE TABLE `cl_overdue_statistic_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `count_time` datetime NOT NULL COMMENT '统计时间',
  `new_overdue` int(11) NOT NULL DEFAULT '0' COMMENT '新客未还量',
  `again_overdue` int(11) NOT NULL DEFAULT '0' COMMENT '复借未还量',
  `extend_overdue` int(11) NOT NULL DEFAULT '0' COMMENT '展期未还量',
  `new_expire` int(11) NOT NULL DEFAULT '0' COMMENT '新客到期量',
  `again_expire` int(11) NOT NULL DEFAULT '0' COMMENT '复借到期量',
  `extend_expire` int(11) NOT NULL DEFAULT '0' COMMENT '展期到期量',
  `new_repayment` int(11) NOT NULL DEFAULT '0' COMMENT '新客已还款量',
  `again_repayment` int(11) NOT NULL DEFAULT '0' COMMENT '复借已还款量',
  `extend_repayment` int(11) NOT NULL DEFAULT '0' COMMENT '展期已还款量',
  `new_overdue_rate` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '新客逾期率',
  `again_overdue_rate` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '复借逾期率',
  `extend_overdue_rate` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '展期逾期率',
  `overdue_rate` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '总逾期率',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='逾期统计数据';

-- 添加统计报表菜单
INSERT INTO `arc_sys_menu` VALUES ('1016', '0', '统计报表', '0', '', 'icon-qian', '00000000001', null, '', '2017-01-01 00:00:00', '', '统计报表', '0', 'StatisticManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('1017', '0', '用户统计', '1016', '', 'icon-qian', '00000000001', null, '', '2017-01-01 00:00:00', '', '用户统计', '0', 'UserStatistic', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('1018', '0', '审核统计', '1016', '', 'icon-qian', '00000000002', null, '', '2017-01-01 00:00:00', '', '审核统计', '0', 'AuditingStatistic', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('1019', '0', '审核人员逾期率统计', '1016', '', 'icon-qian', '00000000003', null, '', '2017-01-01 00:00:00', '', '审核人员逾期率统计', '0', 'AuditorOverdueStatistic', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('1020', '0', '还款统计', '1016', '', 'icon-qian', '00000000004', null, '', '2017-01-01 00:00:00', '', '还款统计', '0', 'RepaymentStatistic', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('1021', '0', '渠道统计', '1016', '', 'icon-qian', '00000000005', null, '', '2017-01-01 00:00:00', '', '渠道统计', '0', 'ChannelStatistic', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('1022', '0', '放款统计', '1016', '', 'icon-qian', '00000000006', null, '', '2017-01-01 00:00:00', '', '放款统计', '0', 'LoadStatistic', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('1023', '0', '逾期统计', '1016', '', 'icon-qian', '00000000007', null, '', '2017-01-01 00:00:00', '', '逾期统计', '0', 'OverdueStatistic', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('1024', '0', '审核人员通过率统计', '1016', '', 'icon-qian', '00000000003', null, '', '2017-01-01 00:00:00', '', '审核人员通过率统计', '0', 'AuditorPassStatistic', null, null, null, null);

-- INSERT INTO `arc_sys_role_menu` VALUES ('', '1', '1016');
INSERT INTO `arc_sys_role_menu` VALUES (null, '1', '1017');
INSERT INTO `arc_sys_role_menu` VALUES (null, '1', '1018');
INSERT INTO `arc_sys_role_menu` VALUES (null, '1', '1019');
INSERT INTO `arc_sys_role_menu` VALUES (null, '1', '1020');
INSERT INTO `arc_sys_role_menu` VALUES (null, '1', '1021');
INSERT INTO `arc_sys_role_menu` VALUES (null, '1', '1022');
INSERT INTO `arc_sys_role_menu` VALUES (null, '1', '1023');
INSERT INTO `arc_sys_role_menu` VALUES (null, '1', '1024');



-- 添加定时任务信息表
insert into `cl_quartz_info` ( `state`, `fail`,  `code`, `succeed`, `class_name`, `create_time`, `name`, `cycle`) values ( '10', '0',  'insertChannelStatistic', '0', 'com.xiji.cashloan.manage.job.statistic.QuartzChannelStatistic', now(), '保存渠道统计数据', '0 10 2 1/1 * ?');
insert into `cl_quartz_info` ( `state`, `fail`,  `code`, `succeed`, `class_name`, `create_time`, `name`, `cycle`) values ( '10', '0',  'insertAuditorStatistic', '0', 'com.xiji.cashloan.manage.job.statistic.QuartzAuditorStatistic', now(), '保存审核人员统计数据', '0 10 2 1/1 * ?');
insert into `cl_quartz_info` ( `state`, `fail`,  `code`, `succeed`, `class_name`, `create_time`, `name`, `cycle`) values ( '10', '0',  'insertRepaymentStatistic', '0', 'com.xiji.cashloan.manage.job.statistic.QuartzRepaymentStatistic', now(), '保存还款统计数据统计', '0 0 3 * * ?');
insert into `cl_quartz_info` ( `state`, `fail`,  `code`, `succeed`, `class_name`, `create_time`, `name`, `cycle`) values ( '10', '0',  'insertUserStatistic', '0', 'com.xiji.cashloan.manage.job.statistic.QuartzUserStatistic', now(), '保存用户统计数据', '0 0 3 * * ?');
insert into `cl_quartz_info` ( `state`, `fail`,  `code`, `succeed`, `class_name`, `create_time`, `name`, `cycle`) values ( '10', '0',  'insertLoadStatistic', '0', 'com.xiji.cashloan.manage.job.statistic.QuartzLoadStatistic', now(), '保存放款统计数据', '0 0 3 * * ?');
insert into `cl_quartz_info` ( `state`, `fail`,  `code`, `succeed`, `class_name`, `create_time`, `name`, `cycle`) values ( '10', '0',  'insertAuditingStatistic', '0', 'com.xiji.cashloan.manage.job.statistic.QuartzAuditingStatistic', now(), '保存审核统计数据', '0 0 3 * * ?');

insert into `cl_quartz_info` ( `state`, `fail`,  `code`, `succeed`, `class_name`, `create_time`, `name`, `cycle`) values ( '10', '0',  'insertOverdueStatistic', '0', 'com.xiji.cashloan.manage.job.statistic.QuartzOverdueStatistic', now(), '保存逾期统计数据', '0 10 2 1/1 * ?');

insert into `cl_quartz_info` ( `state`, `fail`,  `code`, `succeed`, `class_name`, `create_time`, `name`, `cycle`) values ( '10', '0',  'updateLoanStatistic', '0', 'com.xiji.cashloan.manage.job.statistic.QuartzLoanCompensate', now(), '下款统计补偿', '0 0 12 1/1 * ?');






-- 新增渠道后台角色
INSERT INTO `arc_sys_role` VALUES (null, '渠道', 'QuDao',  '2019-01-01 00:00:00', 'system', '2019-01-01 00:00:00', 'system', '请勿改动该角色唯一标识', '0');

-- 新增到期提醒短信模板
INSERT INTO `cl_sms_tpl` VALUES ('7', 'repayBefore', '到期提醒', '{$name}先生/女士，您的账单今天到期请及时处理增加个人信誉以便提额，最晚时间下午6点！请登录APP或者联系后台客服{$telephone}处理', 'SMS0678041320', '10');

-- 到期提醒定时任务
INSERT INTO `cl_quartz_info` VALUES (null, '到期提醒', 'doRepayInform', '0 0 10 * * ?', 'com.xiji.cashloan.manage.job.QuartzRepayInform', '0', '0', '10', '2019-03-04 00:00:00');


alter table cl_auditor_statistic_data add again_load_count int(11) NOT NULL DEFAULT '0' COMMENT '复贷放款数';
alter table cl_auditor_statistic_data add `again_overdue` int(11) NOT NULL DEFAULT '0' COMMENT '到期复贷逾期数';

-- 还款计划类型
ALTER TABLE cl_borrow_repay add column `type` varchar(10)  default '1' COMMENT '还款计划类型 1-正常 2-展期';

-- 初始化还款计划类型
update `cl_borrow_repay` br,(select borrow_id from `cl_borrow_repay`  GROUP BY borrow_id HAVING(count(borrow_id)) > 1) temp
set br.type = 2 where br.borrow_id = temp.borrow_id and (state = 10 or state = 20);

-- 还款计划表添加借款订单金额属性
alter table cl_borrow_repay add column `borrow_amount` decimal(12,2)  default '0.0' COMMENT '借款订单金额';
-- 同步原数据借款订单金额
update cl_borrow_repay cbr, (select br.borrow_id,b.amount from `cl_borrow_repay` br  LEFT JOIN `cl_borrow` b  on br.borrow_id = b.id) temp set cbr.borrow_amount = temp.amount
where cbr.borrow_id = temp.borrow_id;

-- 英趣思汀
INSERT INTO `arc_sys_config` VALUES (null, 20, '身份识别sdk选择', 'orc_sdk_select', '2', 1, '身份识别选择：1-face++,2-有盾,2=1234(该值表示2-有盾，选择userId尾号含有1234的)', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '活体检查路由', 'k_ocr_checkface_router', 'face', 1, '路由默认是face,其他：rate-身份证末尾1使用face++，face，kface-新接入英趣思汀网络', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '英趣思汀活体检测key', 'k_ocr_base64key', 'MIIBVgIBADANBgkqhkiG9w0BAQEFAASCAUAwggE8AgEAAkEAoQbUJfG8h63o2klN3InuK1qUetS71O0YINFlHyZzzKmRBCgNyvuDt8ZuCjB9Zrexk+FNOeUg2dGV8XSCZKwLmwIDAQABAkEAg/C3ddvMMZQi/nEf9juiRi2zCa4ztbULlyBb7hkwuxlL+HYHln8EhgvBTGAWb596BQTmmDET1iVgDm+pWEfd2QIhANk6cX7/H4AkKr9GLlc5KMJNm7+/tJzoMTw6uETwfL1HAiEAvcRuUYY4azGhBAJmsoxSy/S0DSGYZlohMN+FYjSRmQ0CIH+257GVx2xsVyGb3nTzqy4JuO9Ug5jYvtG9aEdH6N7TAiEAgoeV9l+jeSBHB/H63/+jiAUGwC2GnYiLYgmtvtI4ABUCIQC1BoDi3sip+YcY3gw6+SbChaRNAcfZVoeJK60ZM5+xww==', 1, '英趣思汀活体检测key', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '英趣思汀活体检测渠道号', 'k_ocr_channelno', 'CH13IVR8S', 1, '英趣思汀活体检测渠道号', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '英趣思汀活体检测url', 'k_ocr_checkface_url', 'http://rryqo.com/finance/v1/face/match?applyNo=', 1, '英趣思汀活体检测url', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '英趣思汀身份证识别', 'k_ocr_check_idcard_url', 'http://rryqo.com/finance/v1/ocr/idCard?applyNo=', 1, '英趣思汀身份证识别', 1);


-- 有盾用户画像
INSERT INTO `arc_sys_config` VALUES (null, 80, '有盾密钥', 'youdun_pub_key', 'f2ae8f6d-f6d7-4d50-9daa-ac253b1fb46d', 1, '有盾密钥', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '有盾产品编码', 'youdun_product_code', 'Y1001005', 1, '有盾产品编码', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '有盾安全密钥', 'youdun_secret_key', '250b6c96-b8e0-46b8-9878-8753766cfe83', 1, '有盾产品编码', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '有盾url请求地址', 'youdun_risk_url', 'https://api4.udcredit.com/dsp-front/4.1/dsp-front/default/pubkey/%s/product_code/%s/out_order_id/%s/signature/%s', 1, '有盾url请求地址', 1);

-- 有盾请求记录
DROP TABLE IF EXISTS `cl_youdun_req_log`;
CREATE TABLE `cl_youdun_req_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `flow_id` varchar(64) DEFAULT '' COMMENT '同步响应流水号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `borrow_id` bigint(20) DEFAULT NULL COMMENT '借款订单id',
  `is_success` tinyint(1) DEFAULT NULL COMMENT '请求是否成功 0-失败 1-成功',
  `resp_code` varchar(10) DEFAULT '' COMMENT '回调返回码',
  `resp_msg` mediumtext COMMENT '同步响应结果',
  `resp_time` datetime DEFAULT NULL COMMENT '同步响应时间',
  `is_fee` tinyint(1) DEFAULT NULL COMMENT '是否收费 0-不收费 1-收费',
  `type` tinyint(2) DEFAULT NULL COMMENT '类型 1-风险评估',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='有盾请求记录';

-- 有盾风险评估表
DROP TABLE IF EXISTS `cl_youdun_risk_report`;
CREATE TABLE `cl_youdun_risk_report` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户标识',
  `borrow_id` bigint(20) DEFAULT NULL COMMENT '借款订单id',
  `flow_id` varchar(64) DEFAULT '' COMMENT '流水号',
  `data` longtext COMMENT '返回内容',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='有盾风险评估表';

-- 新增人脸认证类型
ALTER TABLE cl_user_base_info add column `id_type` varchar(10)  default '20' COMMENT '人脸认证类型 10-FACE++ 20-有盾';
update cl_user_base_info set id_type = '10';

-- 决策数据表新增有盾字段
ALTER TABLE cl_decision add column `yd_actual_loan_platform_count_1m` int(11)  default 0 COMMENT '近1月实际借款平台数' after pa_all25ac_times;
ALTER TABLE cl_decision add column `yd_repayment_times_count` int(11)  default 0 COMMENT '还款次数' after yd_actual_loan_platform_count_1m;
ALTER TABLE cl_decision add column `yd_actual_loan_platform_count_6m` int(11)  default 0 COMMENT '近6月实际借款平台数' after yd_repayment_times_count;
ALTER TABLE cl_decision add column `yd_actual_loan_platform_count_3m` int(11)  default 0 COMMENT '近3月实际借款平台数' after yd_actual_loan_platform_count_6m;
ALTER TABLE cl_decision add column `yd_actual_loan_platform_count` int(11)  default 0 COMMENT '实际借款平台数' after yd_actual_loan_platform_count_3m;
ALTER TABLE cl_decision add column `yd_loan_platform_count_3m` int(11)  default 0 COMMENT '近3月申请平台数' after yd_actual_loan_platform_count;
ALTER TABLE cl_decision add column `yd_loan_platform_count_1m` int(11)  default 0 COMMENT '近1月申请平台数' after yd_loan_platform_count_3m;
ALTER TABLE cl_decision add column `yd_loan_platform_count_6m` int(11)  default 0 COMMENT '近6月申请平台数' after yd_loan_platform_count_1m;
ALTER TABLE cl_decision add column `yd_repayment_platform_count_6m` int(11)  default 0 COMMENT '近6月还款平台数' after yd_loan_platform_count_6m;
ALTER TABLE cl_decision add column `yd_repayment_platform_count_1m` int(11)  default 0 COMMENT '近1月还款平台数' after yd_repayment_platform_count_6m;
ALTER TABLE cl_decision add column `yd_repayment_platform_count_3m` int(11)  default 0 COMMENT '近3月还款平台数' after yd_repayment_platform_count_1m;
ALTER TABLE cl_decision add column `yd_repayment_platform_count` int(11)  default 0 COMMENT '还款平台总数' after yd_repayment_platform_count_3m;
ALTER TABLE cl_decision add column `yd_loan_platform_count` int(11)  default 0 COMMENT '申请借款平台总数' after yd_repayment_platform_count;
ALTER TABLE cl_decision add column `yd_risk_evaluation` varchar(100)  default '' COMMENT '风险等级' after yd_loan_platform_count;
ALTER TABLE cl_decision add column `yd_score` int(11)  default 0 COMMENT '评估模型得分，分数越高风险越高，0标识缺乏足够信息判断' after yd_risk_evaluation;

-- 添加渠道统计放款数
 ALTER table cl_channel_statistic_data add `expire_load_count` int(11) NOT NULL DEFAULT '0' COMMENT '当日到期放款数';

-- 线上线下放还款金额
alter table cl_load_statistic_data add `online_load_principal` decimal(10,2) DEFAULT '0.00' COMMENT '线上放款本金';
alter table cl_load_statistic_data add `unline_load_principal` decimal(10,2) DEFAULT '0.00' COMMENT '线下放款本金';

alter table cl_repayment_statistic_data add `online_amount` decimal(10,2) DEFAULT '0.00' COMMENT '线上还款金额';
alter table cl_repayment_statistic_data add `unline_amount` decimal(10,2) DEFAULT '0.00' COMMENT '线下还款金额';

-- 到期提醒短信发送url
INSERT INTO `arc_sys_config` VALUES (null, 70, '到期短信发送host', 'sms_apihost2', 'https://api.dsdatas.com/movek/movekSimpleInfoV2', 1, '到期短信发送host', 1);

-- 修改到期提醒短信模板
update cl_sms_tpl set tpl = '{$name}先生/女士，您的账单今天到期请及时处理增加个人信誉以便提额，最晚时间下午6点！请登录APP或者联系后台客服{$telephone}处理',number='SMS0509829445' where type='repayBefore';

insert into `arc_sys_config` ( `status`, `remark`,  `code`, `value`, `type`, `creator`, `name`) values ( '1', '信审拥有待审核订单最大值，默认20单', 'manual_audit_max', '20', '20', '1', '信审拥有待审核订单最大值');
insert into `cl_quartz_info` ( `state`, `fail`,  `code`, `succeed`, `class_name`, `create_time`, `name`, `cycle`) values ( '10', '0',  'automaticDistributionOrder', '0', 'com.xiji.cashloan.manage.job.QuartzDistributionOrder', now(), '待审核订单自动分配', '0 0/5 * * * ?');

-- 公信宝对接sql
INSERT INTO `arc_sys_config` VALUES (null, '80', '公信宝appId', 'gxb_appid', 'gxb51864c27832a59be', '1', '公信宝appId', '1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '公信宝appSecret', 'gxb_appsecret', 'a22a93d48cdd4ca29b653e6970108b01', '1', '公信宝appSecret', '1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '公信宝获取token的接口地址', 'gxb_get_token_url', 'https://prod.gxb.io/crawler/auth/v2/get_auth_token', '1', '公信宝获取token的接口地址', '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '运营商公司选择', 'operator_select', 'moxie', '1', '运营商公司选择，moxie,gxb', '1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '公信宝H5接入地址', 'gxb_h5_url', 'https://prod.gxb.io/v2/auth', '1', '公信宝H5接入地址', '1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '公信宝拉取全部运营商报告数据url', 'gxb_pull_all_report_url', 'https://prod.gxb.io/crawler/data/report/', '1', '公信宝拉取全部运营商报告数据url', '1');
ALTER table cl_operator_req_log add req_token varchar(64) DEFAULT '' COMMENT '授权token';

-- 富友支付选择
INSERT INTO `arc_sys_config` VALUES (null, 20, '富友支付选择', 'fuiou_payment_select', '1', 1, '富友支付选择：1-协议支付,2-笔笔验证', 1);

-- 修改字段长度
alter table cl_operator_basic change city `city` varchar(100) DEFAULT  '' COMMENT '城市';

-- 修改紧急联系人姓名字段编码格式
alter table `cl_user_emer_contacts`  change name name varchar(50) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '联系人';

-- 修改表字段长度
ALTER TABLE `cl_calls_outside_fee` MODIFY COLUMN `fee` decimal(9,2)  NOT NULL COMMENT '收取费用' AFTER `type`;
ALTER TABLE `cl_calls_outside_fee` MODIFY COLUMN `type` smallint(4) NOT NULL COMMENT '调用类型 1-运营商 2-魔杖反欺诈 3-魔杖多头 4-魔杖黑灰名单 5-魔杖贷后行为,6-发送短信，7-人脸识别' AFTER `task_id`;

-- 处理卡在待机审的订单(暂时解决办法)
insert into `cl_quartz_info` ( `state`, `fail`,  `code`, `succeed`, `class_name`, `create_time`, `name`, `cycle`) values ( '10', '0',  'preBorrowHandle', '0', 'com.xiji.cashloan.manage.job.QuartzPreBorrowHandle', now(), '待机审订单处理', '0 0/5 * * * ?');

-- 添加登录验证码过期时间
INSERT INTO `arc_sys_config` VALUES (null, '60', '登录短信验证码过期时间', 'login_sms_time_limit', '20', '1', '单位：分钟', '1');

-- 插入后台登录短信模板
INSERT INTO `cl_sms_tpl` VALUES ('8', 'sysLogin', '后台登录', '尊敬的用户，您的登录验证码为:', 'SMS0966640668', '10');


-- 快钱自动付款相关配置测试环境
INSERT INTO `arc_sys_config` VALUES (null, 80, '快钱付款url', 'kuaiqian_payfor_pay_url', 'https://sandbox.99bill.com/fo-pay/pay2bank/pay', 1, '快钱付款url', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '快钱付款订单查询url', 'kuaiqian_payfor_query_url', 'https://sandbox.99bill.com/fo-pay-query/pay2bank/query', 1, '快钱付款订单查询url', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '快钱付款商户唯一标识', 'kuaiqian_payfor_membercode', '10012138842', 1, '快钱付款商户唯一标识', 1);
-- 块钱协议支付相关配置
INSERT INTO `arc_sys_config` VALUES (null, '80', '块钱发送短信验证码地址', 'kq_Captcha_url', 'https://sandbox.99bill.com:9445/cnp/ind_auth', '1', '块钱发送短信验证码地址', '1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '块钱发送绑卡地址', 'kq_bindCard_url', 'https://sandbox.99bill.com:9445/cnp/ind_auth_verify', '1', '块钱发送绑卡地址', '1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '块钱协议支付接口地址', 'kq_protocol_pay_url', 'https://sandbox.99bill.com:9445/cnp/purchase', '1', '块钱协议支付接口地址', '1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '块钱查询交易状态接口地址', 'kq_query_status_url', 'https://sandbox.99bill.com:9445/cnp/query_txn', '1', '块钱查询交易状态接口地址', '1');
INSERT INTO `arc_sys_config` VALUES (null, 80, '快钱协议支付卡bin查询url', 'kuaiqian_agreement_query_txn', 'https://sandbox.99bill.com:9445/cnp/query_txn', 1, '快钱协议支付卡bin查询url', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '快钱协议支付查询绑卡信息', 'kuaiqian_agreement_pci_query', 'https://sandbox.99bill.com:9445/cnp/pci_query', 1, '快钱协议支付查询绑卡信息', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '快钱协议支付商户号', 'kuaiqian_agreement_merchantId', '104110045112012', 1, '快钱协议支付商户号', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '快钱协议支付终端号', 'kuaiqian_agreement_terminalId', '00002012', 1, '快钱协议支付终端号', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '快钱银行卡解绑url', 'kuaiqian_agreement_pci_del', 'https://sandbox.99bill.com/cnp/pci_del', 1, '快钱银行卡解绑url', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '块钱网关证书文件名', 'kuaiqian_certificate_name', '10411004511201290', 1, '块钱网关证书文件名', 1);
-- 支付请求记录表
ALTER TABLE cl_pay_req_log add token varchar(64) DEFAULT '' COMMENT '获取验证码返回的令牌信息token';

-- 展期费率
INSERT INTO `arc_sys_config` VALUES (null, '20', '展期费率', 'delay_fee', '0.30', '1', '占借款金额的比例(参数值为小数)', '1');


-- 添加渠道配置字段
ALTER TABLE cl_channel add fee varchar(16) DEFAULT '0.00'   COMMENT '综合费用集合(0.098,0.12,0.15)';
ALTER TABLE cl_channel add init_credit varchar(64) DEFAULT '0.00'   COMMENT '注册时给予额度';
ALTER TABLE cl_channel add borrow_credit varchar(64) DEFAULT '0.00'  COMMENT '借款额度';
ALTER TABLE cl_channel add is_improve_credit varchar(10) DEFAULT '10' COMMENT '还款提额开关(还款提额 10启用 20禁用)';
ALTER TABLE cl_channel add one_repay_credit varchar(64) DEFAULT '0.00' COMMENT '还款成功单次增加的额度	';
ALTER TABLE cl_channel add improve_credit_limit varchar(64) DEFAULT '0.00' COMMENT '还款成功累计提额上限';
ALTER TABLE cl_channel add borrow_day varchar(4) DEFAULT '' COMMENT '借款天数';
ALTER TABLE cl_channel add delay_fee varchar (16) DEFAULT '0.00' COMMENT '展期费率';
ALTER TABLE cl_channel add behead_fee varchar(10) DEFAULT '10' COMMENT '是否启用砍头息10启用，20-不启用';



-- 最终逾期统计
INSERT INTO `arc_sys_menu` VALUES ('1027', '0', '最终逾期统计', '1016', '', 'icon-qian', '00000000009', null, '', '2017-01-01 00:00:00', '', '最终逾期统计', '0', 'NowOverdueStatistic', null, null, null, null);
INSERT INTO `arc_sys_role_menu` VALUES (null, '1', '1027');

INSERT INTO `arc_sys_menu` VALUES ('1028', '0', '实时到期还款统计', '1016', '', 'icon-qian', '00000000009', null, '', '2017-01-01 00:00:00', '', '实时到期还款统计', '0', 'RealTimeMaturityStatistic', null, null, null, null);
INSERT INTO `arc_sys_role_menu` VALUES (null, '1', '1028');

-- 渠道配置更新
UPDATE cl_channel set fee = (select value from arc_sys_config where code = 'fee'),
borrow_credit =(select value from arc_sys_config where code = 'borrow_credit'),
init_credit = (select value from arc_sys_config where code = 'init_credit'),
is_improve_credit =(select value from arc_sys_config where code = 'is_improve_credit'),
one_repay_credit =(select value from arc_sys_config where code = 'one_repay_credit'),
improve_credit_limit =(select value from arc_sys_config where code = 'imporove_credit_limit'),
borrow_day =(select value from arc_sys_config where code = 'borrow_day'),
delay_fee =(select value from arc_sys_config where code = 'fee'),
behead_fee =(select value from arc_sys_config where code = 'behead_fee');


-- 添加用户管理列表 未借款用户信息
INSERT INTO `arc_sys_menu` VALUES ('1025', '0', '未复借用户信息', '2', '', null, '00000000006', null, '', null, '', '未借用户信息', '0', 'UserNotBorrowAgain', null, null, null, null);

INSERT INTO `arc_sys_role_menu` VALUES (null, '1', '1025');

-- 根据完成次数提额
INSERT INTO `arc_sys_config` VALUES (null, '20', '还款提额次数', 'count_improve_credit', '1,1,1', '1', '参照还款提额次数(1,2,3)格式每个数字代表当次提额要还款的次数,数字的个数参照提额上限除以单次增加的额度', '1');
--  添加成功还款次数字段
ALTER TABLE arc_credit add column `num` int(11) DEFAULT '0' COMMENT '当次有效还款次数' after count;

-- 还款提额次数
ALTER TABLE cl_channel add count_improve_credit varchar(64) DEFAULT '1,1,1' COMMENT '还款提额次数:参照(1,2,3)格式，每个数字代表当次提额要还款的次数，数字的个数参照提额上限除以单次增加的额度';
update cl_channel set count_improve_credit =(select value from arc_sys_config where code = 'count_improve_credit');



-- 新建角色到期人员和我的还款订单菜单
INSERT INTO `arc_sys_role` VALUES (null, '到期人员', 'repayPerson',  '2019-03-07 00:00:00', 'system', '2019-03-07 00:00:00', 'system', '请勿改动该角色唯一标识', '0');

INSERT INTO `arc_sys_menu` VALUES ('1030', '0', '我的到期订单', '10', '', null, '00000000006', null, '', null, '', '我的到期订单', '0', 'MyRepayOrder', null, null, null, null);
INSERT INTO `arc_sys_role_menu` VALUES (null, '1', '1030');

-- 创建到期订单表
DROP TABLE IF EXISTS `cl_manual_repay_order`;
CREATE TABLE `cl_manual_repay_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '到期人id',
  `borrow_user_id` bigint(20) NOT NULL COMMENT '借款人id',
  `borrow_name` varchar(20) DEFAULT '' COMMENT '借款人姓名',
  `phone` varchar(20) DEFAULT '' COMMENT '借款人手机号',
  `borrow_id` bigint(20) DEFAULT NULL COMMENT '借款id',
  `borrow_repay_id` bigint(20) DEFAULT NULL COMMENT '还款计划id',
  `state` varchar(2) DEFAULT '10' COMMENT '分配状态   10未分配，20已分配',
  `remark` varchar(500) DEFAULT '' COMMENT '备注说明',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `review_time` datetime DEFAULT NULL COMMENT '分配时间',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `borrow_id` (`borrow_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='到期订单表';

-- 插入到期数据
insert into `cl_manual_repay_order`(`borrow_repay_id`,`borrow_name`, `phone`, `borrow_id`, `borrow_user_id`)
select br.id,u.real_name borrow_name,u.phone phone, br.borrow_id borrow_id,u.user_id user_id from cl_borrow_repay br left join cl_user_base_info u on br.user_id = u.user_id where br.state = 20;
-- 修改到期插入时间
update cl_manual_repay_order set create_time = now();
update cl_manual_repay_order set review_time = now();

-- 决策数据添加字段长度
alter table cl_decision change mx_native_place mx_native_place varchar(64) NOT NULL DEFAULT '' COMMENT '籍贯';

ALTER TABLE cl_decision add column yd_device_link_id_count int(11) DEFAULT 0 COMMENT '同设备使用用户总数';

INSERT INTO `arc_sys_menu` VALUES ('1032', '0', '我的渠道统计', '24', '', null, '00000000006', null, '', null, '', '我的到期订单', '0', 'MyChannelList', null, null, null, null);
INSERT INTO `arc_sys_role_menu` VALUES (null, '1', '1032');
ALTER TABLE cl_decision add column device_link_id_count int(11) DEFAULT 0 COMMENT '同设备使用用户总数';

ALTER TABLE cl_channel add column conditions varchar(50) DEFAULT '' COMMENT '限流1QQ，2微信，3微博，4其他';
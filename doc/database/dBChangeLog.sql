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
INSERT INTO `arc_sys_config` VALUES (null, 80, '富有验证码接口', 'protocol_bindmsg_url', 'http://www-1.fuiou.com:18670/mobile_pay/newpropay/bindMsg.pay', 1, '发送短信验证码接口-绑卡', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '协议卡绑定', 'protocol_bindcommit_url', 'http://www-1.fuiou.com:18670/mobile_pay/newpropay/bindCommit.pay', 1, '协议卡绑定', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '协议解绑接口', 'protocol_unbind_url', 'http://www-1.fuiou.com:18670/mobile_pay/newpropay/unbind.pay', 1, '协议解绑接口', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '协议卡查询', 'protocol_bindquery_url', 'http://www-1.fuiou.com:18670/mobile_pay/newpropay/bindQuery.pay', 1, '协议卡查询', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '协议支付接口', 'protocol_order_url', 'http://www-1.fuiou.com:18670/mobile_pay/newpropay/order.pay', 1, '协议支付接口', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '富友订单号查询', 'protocol_queryorderid_url', 'http://www-1.fuiou.com:18670/mobile_pay/findPay/queryOrderId.pay', 1, '订单结果查询接口(富友订单号)', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '商户订单号查询', 'protocol_checkresult_url', 'http://www-1.fuiou.com:18670/mobile_pay/checkInfo/checkResult.pay', 1, '订单结果查询接口(商户订单号)', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '卡Bin查询接口', 'protocol_cardbinquery_url', 'http://www-1.fuiou.com:18670/mobile_pay/findPay/cardBinQuery.pay', 1, '商户支持卡 Bin 查询接口', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, 'RSA私钥', 'protocol_privatekey', '\"\"', 1, 'RSA私钥', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '商户号', 'protocol_mchntcd', '0002900F0096235', 1, '商户号', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '商户密钥', 'protocol_mchntcd_key', '5old71wihg2tqjug9kkpxnhx9hiujoqj', 1, '商户密钥', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '支付是否开启', 'fuiou_switch', '1', 1, '1开2关', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '商户号', 'fuiou_merid', '0002900F0345178', 1, '商户号', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '商户密码', 'fuiou_pwd', '123456', 1, '商户密码', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, '代收付地址', 'fuiou_daifu_req_url', 'https://fht-test.fuiou.com/fuMer/req.do', 1, '代收付地址第三方接口', 1);
INSERT INTO `arc_sys_config` VALUES (null, 20, '是否启用砍头息', 'behead_fee', '10', 1, '是否启用砍头息10启用，20-不启用', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, 'face的apiKey', 'face_api_key', 'c3_OTRm4Jhhl6hbDllq-Nqj5nheHlSiQ', 1, 'face的apiKey', 1);
INSERT INTO `arc_sys_config` VALUES (null, 80, 'face的secret', 'face_api_secret', '44rXoUZghpR-lDr6BFVsuSC6L962yQc6', 1, 'face的secret', 1);
INSERT INTO `arc_sys_config` VALUES (null, 20, '是否展期', 'delay_switch', '10', 1, '是否展期：10-开启，20-不启用', 1);
INSERT INTO `arc_sys_config` VALUES (null, 20,'app名称','appName','哆啦a借',1,'产品名称',1);
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

-- 修改调用外部收据收费表用户标识字段
ALTER TABLE `cl_calls_outside_fee` change user_id `user_id` bigint(20) DEFAULT  NULL COMMENT '用户标识';

-- 配置修改,修改借款额度
update `arc_sys_config` set value=1000,remark='借款额度(500,1000)' where code='borrow_credit';

-- 新增自动审核成功,是否人工审核配置 2018.12.9
INSERT INTO `arc_sys_config` VALUES (null, '10', '人工审核开关', 'arc_sys_config', '10', '1', '机审通过,人工审核开关 10-启用 20-禁用', '1');
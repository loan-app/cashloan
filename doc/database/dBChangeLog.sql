-- 广告管理  2017-08-16
-- ----------------------------
-- Table structure for `cl_adver`
-- ----------------------------
CREATE TABLE `cl_adver` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(50) DEFAULT '' COMMENT '标题',
  `link` varchar(255) DEFAULT '' COMMENT '链接',
  `path` varchar(255) DEFAULT '' COMMENT '路径',
  `sort` int(11) DEFAULT 0 COMMENT '排序号',
  `state` varchar(2) DEFAULT '' COMMENT '状态 10启用 20禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='广告';

INSERT INTO `arc_sys_menu` VALUES ('', '0', '广告管理', '0', '', 'icon-zhuanyefengkong', '00000000027', null, '', '2017-01-01 00:00:00', '', '广告管理', '0', '1', 'AdverManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('', '0', '广告信息', '注意！此处为上一条的id', '', '', '00000000001', '2017-01-01 00:00:00', '', '2017-01-01 00:00:00', '', '广告信息', '0', '1', 'AdverManageList', null, null, null, null);

-- 字段字符集，防止特殊符号保存失败
ALTER TABLE cl_opinion MODIFY COLUMN `opinion` varchar(160) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '意见';
ALTER TABLE cl_user_contacts MODIFY COLUMN `name` varchar(20) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '姓名';

-- 添加borrow表联合索引,防止出现重复订单  2017-08-24
ALTER TABLE cl_borrow ADD UNIQUE(`user_id`,`create_time`);

-- 工作台 菜单化 2017-08-24
INSERT INTO `arc_sys_menu` VALUES ('', '0', '工作台', '0', '', 'icon-yonghuxinxi', '00000000001', null, '', '2017-01-01 00:00:00', '', '工作台', '0', '1', 'ShowWorkbench', null, null, null, null);


-- 规则决策评分 2017-08-30
CREATE TABLE `arc_borrow_score_result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `borrow_id` bigint(20) DEFAULT NULL COMMENT '借款Id',
  `rule_engin_id` bigint(20) DEFAULT NULL COMMENT '所属规则引擎id',
  `result_type` varchar(16) NOT NULL DEFAULT '' COMMENT '结果描述   评分结果模式下  10 不通过 20 需人工审核  30通过',
  `formula` varchar(16) NOT NULL DEFAULT '' COMMENT '表达式 ',
  `integral` int(11) DEFAULT '0' COMMENT '分值范围',
  `score` int(11) DEFAULT '0' COMMENT '规则对应的总得分',
  `result` varchar(2) DEFAULT '' COMMENT '比对结果  Y匹配 N不匹配',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规则决策评分';

ALTER TABLE arc_borrow_rule_result ADD COLUMN `score` int(11) DEFAULT '0' COMMENT '评分模式得分';

-- 四要素认证及短信查询接口 2017-8-30
INSERT INTO `arc_sys_config` VALUES (null, '60', '获取短信报告结果接口地址', 'sms_apihost_report', 'http://ucdevapi.ucredit.erongyun.net/smsSend/getReportByOrderNo', '1', '获取短信报告结果接口地址', '1');
INSERT INTO `arc_sys_config` VALUES (null, '70', '四要素认证接口名称', 'four_elements_interfaceName', 'txbankCardFourCheckFourQuery', '1', '四要素认证接口名称', '1');
INSERT INTO `arc_sys_config` VALUES (null, '70', '四要素认证接口地址', 'tx_bankCard_four', 'http://ucdevapi.ucredit.erongyun.net/credit/api/mock/query', '1', '四要素认证接口地址', '1');

CREATE TABLE `cl_user_black_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `real_name` varchar(255) NOT NULL COMMENT '真实姓名',
  `id_no` varchar(18) NOT NULL COMMENT '身份证号',
  `phone` varchar(11) NOT NULL COMMENT '手机号码',
  `type` varchar(255) NOT NULL DEFAULT '10' COMMENT '类型：10 黑名单 20 白名单',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='黑名单信息';

-- 授信额度表添加count字段，用来记录提额次数2017-8-30
ALTER TABLE arc_credit ADD COLUMN `count` int(10) DEFAULT '0' COMMENT '提额次数';

-- 提额参数 2017-8-31
INSERT INTO `arc_sys_config` VALUES (null, '20', '还款提额开关', 'is_improve_credit', '10', '1', '10启用 20关闭', '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '还款成功单次增加的额度', 'one_repay_credit', '50', '1', '提额', '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '还款成功累计提额上限', 'imporove_credit_limit', '500', '1', '提额上限', '1');

-- 放款审核开关 2017-08-31
INSERT INTO `arc_sys_config` VALUES (null, '20', '放款审核开关', 'manual_loan', '10', '1', '是否启用放款审核  10启用 20关闭', '1');

-- 四要素认证次数2017-8-31
INSERT INTO `arc_sys_config` VALUES (null, '20', '单日每人四要素认证最大次数', 'four_elements_verity_most_time', '5', '1', '单日每人四要素认证最大次数', '1');
-- 费用集合2017-09-01
INSERT INTO `arc_sys_config` ( `type`, `name`, `code`, `value`, `status`, `remark`, `creator`) VALUES ( '20', '费用集合', 'fee_map', '服务费-0.5,信息认证费-0.4,借款利息-0.1', '1', '费用集合', '1');

-- 四要素认证开关2017-09-01
INSERT INTO `arc_sys_config` VALUES (null, '20', '四要素认证开关', 'four_elements_switch', '10', '1', '四要素认证开关', '1');

-- 四要素认证记录表2017-09-04
CREATE TABLE `cl_four_elements_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `user_name` varchar(30) DEFAULT NULL COMMENT '用户姓名',
  `id_card` varchar(30) DEFAULT '' COMMENT '身份证号',
  `phone` varchar(30) DEFAULT '' COMMENT '手机号',
  `card_no` varchar(30) DEFAULT '' COMMENT '银行卡号',
  `code` varchar(3) DEFAULT '' COMMENT '返回码 200 为请求成功，并获取相关数据，400 为某些原因导请求失败，如参数格式错误、无相关数据等，500 为服务器内部错误，401 为无权限请求。',
  `check_status` varchar(10) DEFAULT '' COMMENT '验证状态   SAME 验证通过, DIFFERENT 验证不通过, ACCOUNTNO_UNABLE_VERITY 无法验证',
  `result` varchar(30) DEFAULT '' COMMENT '结果',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='四要素认证记录';

-- 添加身份证号唯一索引
-- 索引.sql里有这个索引的设置  alter table cl_user_base_info add constraint uk_id_no unique (id_no);


-- 91征信表
-- ----------------------------
-- Table structure for cl_91zx_detail
-- ----------------------------
DROP TABLE IF EXISTS `cl_91zx_detail`;
CREATE TABLE `cl_91zx_detail` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(11) NOT NULL COMMENT '用户标识',
  `trx_no` varchar(64) DEFAULT '' COMMENT '交易代码',
  `borrow_type` int(11) DEFAULT '0' COMMENT '借款类型 0.未知1.个人信贷2.个人抵押3.企业信贷4.企业抵押',
  `borrow_state` int(11) DEFAULT '0' COMMENT '借款状态 0.未知1.拒贷2.批贷已放款4.借款人放弃申请5.审核中6.待放款（3和6意义一样）',
  `borrow_amt_min` decimal(12,2) DEFAULT '0.00' COMMENT '合同金额 区间  单位：万',
  `borrow_amt_max` decimal(12,2) DEFAULT '0.00' COMMENT '合同金额 区间  单位：万 -7.[0,0.1) -6.[0.1,0.2) -5.[0.2,0.3) -4.[0.3,0.4) -3.[0.4,0.6) -2.[0.6,0.8) -1.[0.8,1) 1.[1,2) 2.[2,4) 3.[4,6) 4.[6,8)…….(单位:万元)',
  `contract_date` datetime DEFAULT NULL COMMENT '合同日期',
  `loan_period` int(11) DEFAULT '0' COMMENT '批贷期数',
  `repay_state` int(11) DEFAULT '0' COMMENT '还款状态 0.未知1.正常2.M1 3.M2 4.M3 5.M4 6.M5 7.M6 8.M6+ 9.已还清',
  `arrears_amount` decimal(12,2) DEFAULT '0.00' COMMENT '逾期金额 实际金额*100000 取整',
  `company_code` varchar(128) DEFAULT '' COMMENT '公司代码',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `order_no` (`trx_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='风控数据--91征信数据';

-- ----------------------------
-- Table structure for cl_91zx_req_log
-- ----------------------------
DROP TABLE IF EXISTS `cl_91zx_req_log`;
CREATE TABLE `cl_91zx_req_log` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(11) NOT NULL COMMENT '用户标识',
  `req_order` varchar(64) NOT NULL DEFAULT '' COMMENT '请求订单号',
  `req_params` varchar(255) NOT NULL DEFAULT '' COMMENT '请求参数',
  `create_time` datetime NOT NULL COMMENT '添加时间',
  `resp_code` varchar(10) DEFAULT '' COMMENT '回调返回码',
  `resp_params` mediumtext COMMENT '同步响应结果',
  `resp_time` datetime DEFAULT NULL COMMENT '同步响应时间',
  `resp_order_no` varchar(64) DEFAULT '' COMMENT '同步响应订单号',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `order_no` (`req_order`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='风控数据--浅橙征信数据请求记录';

-- ----------------------------
-- Table structure for cl_rc_simple_91zx_count
-- ----------------------------
DROP TABLE IF EXISTS `cl_rc_simple_91zx_count`;
CREATE TABLE `cl_rc_simple_91zx_count` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(11) NOT NULL COMMENT '用户标识',
  `borrow_count` int(11) NOT NULL DEFAULT '0' COMMENT '借款成功次数',
  `overdue_amt` decimal(12,2) DEFAULT '0.00' COMMENT '逾期金额 单位：元',
  `amt_min` decimal(12,2) DEFAULT '0.00' COMMENT '借款区间（低）单位：万',
  `amt_max` decimal(12,2) DEFAULT '0.00' COMMENT '借款区间（低）单位：万',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='风控数据统计-（简）91征信统计';

-- 91征信配置
INSERT INTO `cl_rc_tpp` (`id`, `name`, `nid`, `mer_no`, `sign_type`, `key`, `extend`, `state`, `add_time`) VALUES ('4', '91征信', '91zx', '', '', '', '{\"companyCode\":\"P21T2CF1103364550\",\"sign\":\"5594553F9C34037CE053580213ACB03F\",\"url\":\"http://210.72.229.172:8181/jyzx/zxservice.do\"}', '20', '2017-09-08 17:48:31');
INSERT INTO `cl_rc_tpp_business` (`id`, `tpp_id`, `name`, `nid`, `state`, `extend`, `url`, `test_url`, `add_time`) VALUES ('7', '4', '91征信即时查询', 'Zx91Query', '10', '{\"companyCode\":\"P21T2CF1103364550\",\"sign\":\"5594553F9C34037CE053580213ACB03F\"}', 'http://210.72.229.172:8181/jyzx/zxservice.do', 'http://210.72.229.172:8181/jyzx/zxservice.do', '2017-09-11 09:41:16');


-- 用户详情表添加婚姻状况字段 2017-09-21
alter table `cl_user_base_info` ADD COLUMN `marital` VARCHAR(20) DEFAULT '' COMMENT '婚姻状况';

-- 用户设备信息表添加app安装时间等字段 2017-09-21
alter table `cl_user_equipment_info` ADD COLUMN `app_install_time` datetime DEFAULT null COMMENT 'APP安装时间';
alter table `cl_user_equipment_info` ADD COLUMN `app_market` VARCHAR(20) DEFAULT '' null COMMENT 'APP应用市场';

-- 添加首再贷标识 2017-10-12
ALTER TABLE `cl_borrow` ADD COLUMN `again` varchar(2) DEFAULT '' COMMENT '首再贷标识  10首贷  20再贷' AFTER `ip`;
-- again字段数据初始化
UPDATE cl_borrow SET again = '0' WHERE state in (21,27);
UPDATE `cl_borrow` SET again = '20' WHERE again!='0' and user_id IN (SELECT a.user_id FROM (SELECT user_id FROM `cl_borrow` WHERE again!='0' GROUP BY user_id HAVING COUNT(user_id) >1) a);
UPDATE `cl_borrow` SET again = '10' WHERE id IN (SELECT a.id FROM (SELECT MIN(id)id,user_id ,COUNT(user_id) FROM `cl_borrow` WHERE again!='0' GROUP BY user_id HAVING COUNT(user_id) >1) a);

ALTER TABLE `cl_borrow`
ADD COLUMN `sign_service_id`  varchar(32) NULL DEFAULT '0' COMMENT '电子签章签署记录ID' AFTER `ip`;

-- 添加通讯录统计 2017-10-19
alter table cl_user_contacts ADD COLUMN total_count int(11) DEFAULT 0 COMMENT '通话次数';
alter table cl_user_contacts ADD COLUMN sum_duration int(11) DEFAULT 0 COMMENT '通话总时长';

-- 添加认证时间 2017-11-06
ALTER TABLE `cl_user_auth`
DROP COLUMN `acc_fund_state`,
ADD COLUMN `id_time`  datetime NULL COMMENT '身份认证时间' AFTER `id_state`,
ADD COLUMN `contact_time`  datetime NULL COMMENT '紧急联系人认证时间' AFTER `contact_state`,
ADD COLUMN `bank_card_time`  datetime NULL COMMENT '银行卡认证时间' AFTER `bank_card_state`,
ADD COLUMN `phone_time`  datetime NULL COMMENT '手机运营商认证时间' AFTER `phone_state`,
ADD COLUMN `zhima_time`  datetime NULL COMMENT '芝麻认证时间' AFTER `zhima_state`,
ADD COLUMN `work_info_time`  datetime NULL COMMENT '工作信息认证时间' AFTER `work_info_state`,
ADD COLUMN `other_info_time`  datetime NULL COMMENT '其他信息认证时间' AFTER `other_info_state`;
-- 添加认证时间 2017-12-06
ALTER TABLE `cl_borrow` ADD COLUMN `sub_state` varchar(16) DEFAULT '0' COMMENT '附属状态 11表示不再执行规则';


-- 支付记录新增响应码字段 2017-11-22 15:22:57
ALTER TABLE `cl_pay_log` ADD COLUMN `code` varchar(10) DEFAULT '' COMMENT '支付方响应码';
INSERT INTO `arc_sys_config` VALUES (null, '80', '连连需要更换银行卡的响应码', 'lianlian_change_bank_card_code', '1005,4012', '1', '连连需要更换银行卡的响应码', '1');

-- arc_sys_config中添加支付记录响应码字段 2017-11-28 14:00:54
INSERT INTO `arc_sys_config` VALUES (null, '80', '连连需要更换银行卡的响应备注', 'lianlian_change_bank_card_remark', '卡状态异常', '1', '连连需要更换银行卡的响应备注', '1');


--添加token获取地址，2018-11-16
INSERT INTO `arc_sys_config` VALUES (null, 70, 'token', 'token_apihost', 'https://api.dsdatas.com/credit/api/token', 1, '获取token的host', 1);

update arc_sys_config set value='690abc5009eda69f786c380cef51336a' where code='apikey';
update arc_sys_config set value='ea108d5b1dc792680172d0e925a69470598ed54d' where code='secretkey';

-- 修改短信发送地址 2018-11-19
update arc_sys_config set value='https://api.dsdatas.com/movek/movekSimpleInfo' where code = 'sms_apihost';

-- 修改大圣接口请求url
update cl_rc_tpp_business set url='https://api.dsdatas.com/d/dhbGetSauronC' where nid='DhbSauron';
update cl_rc_tpp_business set url='https://api.dsdatas.com/blackData/fireEyesBlackv2' where nid='FireeyesBlack';

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

-- 运营商涉及表改动,最后执行该sql文件,且只有第一次初始化执行

-- 删除原运营商基础信息表,重新创建
DROP TABLE IF EXISTS `cl_operator_basic`;
CREATE TABLE `cl_operator_basic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `name` varchar(24) DEFAULT '' COMMENT '姓名',
  `mobile` varchar(24) NOT NULL DEFAULT '' COMMENT '手机号码',
  `idcard` varchar(64) DEFAULT '' COMMENT '证件号',
  `carrier` varchar(16) NOT NULL COMMENT 'CHINA_MOBILE-移动; CHINA_UNICOM-联通; CHINA_TELECOM-电信',
  `province` varchar(24) NOT NULL COMMENT '省份',
  `city` varchar(8) DEFAULT NULL COMMENT '城市',
  `open_time` date DEFAULT NULL COMMENT '开卡时间',
  `level` varchar(8) DEFAULT NULL COMMENT '星级',
  `package_name` varchar(128) DEFAULT NULL COMMENT '当前手机套餐名称',
  `available_balance` int(11) DEFAULT NULL COMMENT '可用余额(单位分)',
  `real_balance` int(11) DEFAULT NULL COMMENT '实际可用余额(单位分)',
  `state` varchar(10) DEFAULT NULL COMMENT '本机号码状态',
  `reliability` int(2) DEFAULT '0' COMMENT '实名状态 -1:未知 0-未实名 1-已实名',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营商基本信息';

-- 删除原运营商账单表,重新创建
DROP TABLE IF EXISTS `cl_operator_bills`;
CREATE TABLE `cl_operator_bill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `mobile` varchar(24) NOT NULL DEFAULT '' COMMENT '手机号码',
  `bill_month` varchar(32) DEFAULT NULL COMMENT '账单月',
  `bill_start_date` varchar(32) DEFAULT NULL COMMENT '账单日起始日yyyy-MM-dd',
  `bill_end_date` varchar(32) DEFAULT NULL COMMENT '账单日结束日yyyy-MM-dd',
  `base_fee` int(11) DEFAULT NULL COMMENT '套餐及固定费(单位分)',
  `extra_service_fee` int(11) DEFAULT NULL COMMENT '增值业务费(单位分)',
  `voice_fee` int(11) DEFAULT NULL COMMENT '语音费(单位分)',
  `sms_fee` int(11) DEFAULT NULL COMMENT '短彩信费(单位分)',
  `web_fee` int(11) DEFAULT NULL COMMENT '网络流量费(单位分)',
  `extra_fee` int(11) DEFAULT NULL COMMENT '其它费用(单位分)',
  `total_fee` int(11) DEFAULT NULL COMMENT '总费用(单位分)',
  `discount` int(11) DEFAULT NULL COMMENT '优惠费(单位分)',
  `extra_discount` int(11) DEFAULT NULL COMMENT '其它优惠(单位分). 包括他人代付, 第三方支付等',
  `actual_fee` int(11) DEFAULT NULL COMMENT '个人实际费用(单位分)',
  `paid_fee` int(11) DEFAULT NULL COMMENT '已支付费用(单位分)',
  `unpaid_fee` int(11) DEFAULT NULL COMMENT '未支付费用(单位分)',
  `related_mobiles` text COMMENT '关联的手机号, 多个手机号以逗号分隔',
  `point` int(11) DEFAULT NULL COMMENT '当前可用积分',
  `last_point` int(11) DEFAULT NULL COMMENT '上期可用积分',
  `notes` varchar(1024) DEFAULT '' COMMENT '备注',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营商-手机账单信息';


-- 删除原运营商通话记录表,重新创建
DROP TABLE IF EXISTS `cl_operator_voices`;
CREATE TABLE `cl_operator_voice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `mobile` varchar(24) NOT NULL DEFAULT '' COMMENT '手机号码',
  `bill_month` varchar(32) DEFAULT NULL COMMENT '账单月',
  `time` varchar(32) DEFAULT NULL COMMENT '通话时间',
  `peer_number` varchar(32) DEFAULT NULL COMMENT '对方号码',
  `location` varchar(64) DEFAULT NULL COMMENT '通话地(自己的)',
  `location_type` varchar(256) DEFAULT NULL COMMENT '通话地类型. e.g.省内漫游',
  `duration` int(11) DEFAULT NULL COMMENT '通话时长(单位秒)',
  `dial_type` varchar(8) DEFAULT NULL COMMENT 'DIAL-主叫; DIALED-被叫',
  `fee` int(11) DEFAULT NULL COMMENT '通话费(单位分)',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营商-通话记录信息';

-- 删除原运营商认证请求记录表,重新创建
DROP TABLE IF EXISTS `cl_operator_req_log`;
CREATE TABLE `cl_operator_req_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户标识',
  `task_id` varchar(64) DEFAULT '' COMMENT '任务id',
  `task_state` varchar(2) DEFAULT '' COMMENT '状态，0-发送请求成功 1-任务创建成功 2-授权登录成功 3-授权登录失败 4-采集成功 5-采集失败 6-报告生成成功 7-报告生成失败',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `resp_time` datetime DEFAULT NULL COMMENT '同步响应时间',
  `message` text COMMENT '结果描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营商认证请求记录表';

-- 删除运营商认证数据详情表,重新创建
DROP TABLE IF EXISTS `cl_operator_resp_detail`;
CREATE TABLE `cl_operator_resp_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `req_log_id` bigint(20) NOT NULL COMMENT '请求记录标识',
  `task_id` varchar(50) DEFAULT '' COMMENT '任务Id',
  `operator_data` longtext COMMENT '运营商数据',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营商认证结果记录表';

-- 新建运营商-亲情网信息表
CREATE TABLE `cl_operator_family` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `mobile` varchar(24) NOT NULL DEFAULT '' COMMENT '手机号码',
  `family_num` tinyint(4) DEFAULT NULL COMMENT '当前手机号下所属亲网编号',
  `long_number` varchar(24) DEFAULT NULL COMMENT '成员手机号',
  `short_number` int(11) DEFAULT NULL COMMENT '成员短号',
  `member_type` varchar(8) DEFAULT NULL COMMENT '成员类型. MASTER-家长; MEMBER-普通成员',
  `join_date` varchar(32) DEFAULT NULL COMMENT '加入日期',
  `expire_time` varchar(32) DEFAULT NULL COMMENT '失效日期',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营商-亲情网信息';

-- 新建运营商-月份信息表
CREATE TABLE `cl_operator_monthinfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `mobile` varchar(24) NOT NULL DEFAULT '' COMMENT '手机号码',
  `month_count` int(11) DEFAULT '0' COMMENT '有通话记录月份数',
  `miss_month_count` int(11) DEFAULT '0' COMMENT '通话记录获取失败月份数',
  `no_call_month` int(11) DEFAULT '0' COMMENT '无通话记录月份数',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营商-月份信息';

-- 新建运营商-流量详情表
CREATE TABLE `cl_operator_net` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `mobile` varchar(24) NOT NULL DEFAULT '' COMMENT '手机号码',
  `bill_month` varchar(32) DEFAULT NULL COMMENT '账单月',
  `time` timestamp NOT NULL DEFAULT '1970-01-02 00:00:00' COMMENT '上网时间',
  `location` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '通信地(自己的)',
  `service_name` varchar(256) DEFAULT NULL COMMENT '业务名称.',
  `net_type` varchar(256) DEFAULT NULL COMMENT '网络类型.4g',
  `duration` int(11) DEFAULT NULL COMMENT '通信时长(单位秒)',
  `subflow` DOUBLE(10,2) DEFAULT NULL COMMENT '流量使用量(单位B)',
  `fee` int(11) DEFAULT NULL COMMENT '通信费(单位分)',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营商-流量详情';

-- 新建运营商-套餐信息表
CREATE TABLE `cl_operator_package` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `mobile` varchar(24) NOT NULL DEFAULT '' COMMENT '手机号码',
  `bill_start_date` datetime DEFAULT NULL COMMENT '账单起始日',
  `bill_end_date` datetime DEFAULT NULL COMMENT '账单结束日',
  `item` varchar(512) DEFAULT NULL COMMENT '项目名称. e.g.手机上网自选30元包.流量单位l默认KB, 语音默认秒, 短信默认条',
  `unit` varchar(64) DEFAULT NULL COMMENT '单位',
  `total` varchar(16) DEFAULT NULL COMMENT '总量',
  `used` varchar(16) DEFAULT NULL COMMENT '已使用量',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营商-套餐信息';

-- 新建运营商-充值记录表
CREATE TABLE `cl_operator_recharge` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `mobile` varchar(24) NOT NULL DEFAULT '' COMMENT '手机号码',
  `recharge_time` varchar(32) DEFAULT NULL COMMENT '充值时间',
  `amount` int(11) DEFAULT NULL COMMENT '充值金额(单位分)',
  `type` varchar(256) DEFAULT NULL COMMENT '充值方式. e.g. 现金',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营商-充值记录信息';

-- 新建运营商-短信详情表
CREATE TABLE `cl_operator_sms` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `mobile` varchar(24) NOT NULL DEFAULT '' COMMENT '手机号码',
  `bill_month` varchar(32) DEFAULT NULL COMMENT '账单月',
  `time` varchar(32) DEFAULT NULL COMMENT '收/发短信时间',
  `peer_number` varchar(64) DEFAULT NULL COMMENT '对方号码',
  `location` varchar(64) DEFAULT NULL COMMENT '通信地(自己的)',
  `send_type` varchar(16) DEFAULT NULL COMMENT 'SEND-发送; RECEIVE-收取',
  `msg_type` varchar(8) DEFAULT NULL COMMENT 'SMS-短信; MSS-彩信',
  `service_name` varchar(256) DEFAULT NULL COMMENT '业务名称. e.g. 点对点(网内)',
  `fee` int(11) DEFAULT NULL COMMENT '通信费(单位分)',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营商-短信详情';

-- 新增请求记录id
ALTER TABLE cl_operator_basic ADD req_log_id BIGINT(20) NOT NULL COMMENT '请求记录id' AFTER user_id;
ALTER TABLE cl_operator_bill ADD req_log_id BIGINT(20) NOT NULL COMMENT '请求记录id' AFTER user_id;
ALTER TABLE cl_operator_family ADD req_log_id BIGINT(20) NOT NULL COMMENT '请求记录id' AFTER user_id;
ALTER TABLE cl_operator_monthinfo ADD req_log_id BIGINT(20) NOT NULL COMMENT '请求记录id' AFTER user_id;
ALTER TABLE cl_operator_net ADD req_log_id BIGINT(20) NOT NULL COMMENT '请求记录id' AFTER user_id;
ALTER TABLE cl_operator_net_1 ADD req_log_id BIGINT(20) NOT NULL COMMENT '请求记录id' AFTER user_id;
ALTER TABLE cl_operator_package ADD req_log_id BIGINT(20) NOT NULL COMMENT '请求记录id' AFTER user_id;
ALTER TABLE cl_operator_recharge ADD req_log_id BIGINT(20) NOT NULL COMMENT '请求记录id' AFTER user_id;
ALTER TABLE cl_operator_sms ADD req_log_id BIGINT(20) NOT NULL COMMENT '请求记录id' AFTER user_id;
ALTER TABLE cl_operator_sms_1 ADD req_log_id BIGINT(20) NOT NULL COMMENT '请求记录id' AFTER user_id;
ALTER TABLE cl_operator_voice ADD req_log_id BIGINT(20) NOT NULL COMMENT '请求记录id' AFTER user_id;
ALTER TABLE cl_operator_voice_1 ADD req_log_id BIGINT(20) NOT NULL COMMENT '请求记录id' AFTER user_id;

-- 索引
ALTER TABLE `cl_operator_voice`
ADD INDEX `user_id` (`user_id`) USING BTREE,
ADD INDEX `mobile` (`mobile`) USING BTREE,
ADD INDEX `req_log_id` (`req_log_id`) USING BTREE;

ALTER TABLE `cl_operator_sms`
ADD INDEX `user_id` (`user_id`) USING BTREE,
ADD INDEX `mobile` (`mobile`) USING BTREE,
ADD INDEX `req_log_id` (`req_log_id`) USING BTREE;

ALTER TABLE `cl_operator_net`
ADD INDEX `user_id` (`user_id`) USING BTREE,
ADD INDEX `mobile` (`mobile`) USING BTREE,
ADD INDEX `req_log_id` (`req_log_id`) USING BTREE;

-- 新建运营商报告表
CREATE TABLE `cl_operator_report` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户标识',
  `req_log_id` bigint(20) NOT NULL COMMENT '请求记录标识',
  `task_id` varchar(64) DEFAULT '' COMMENT '订单号',
  `report` longtext COMMENT '运营商报告内容',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营商报告表表';

-- 新建调用外部数据收费记录表
CREATE TABLE `cl_calls_outside_fee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户标识',
  `task_id` varchar(64) NOT NULL COMMENT '任务id',
  `type` TINYINT(4) NOT NULL COMMENT '调用类型 1-运营商 2-魔杖反欺诈 3-魔杖多头 4-魔杖黑灰名单 5-魔杖贷后行为',
  `fee` decimal(5,2) NOT NULL COMMENT '收取费用',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='调用外部收据收费表';

-- 新增运营商报告查看链接表
CREATE TABLE `cl_operator_report_link` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户标识',
  `task_id` varchar(64) NOT NULL COMMENT '任务id',
  `message` text COMMENT '运营商报告链接',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营商报告查看链接';
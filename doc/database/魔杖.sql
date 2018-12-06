-- 1.基本信息表
DROP TABLE IF EXISTS `cl_magic_basic`;
CREATE TABLE `cl_magic_basic` (
	`id` BIGINT (11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`user_id` BIGINT (20) DEFAULT NULL COMMENT '用户id',
	`mobile` VARCHAR (11) DEFAULT '' COMMENT '用户手机号',
	`user_name` VARCHAR (20) DEFAULT '' COMMENT '姓名',
	`idcard` VARCHAR (20) DEFAULT '' COMMENT '身份证号',
	`idcard_location` VARCHAR (100) DEFAULT '' COMMENT '身份证归属地 省/市/区(县)',
	`trans_id` VARCHAR (64) DEFAULT '' COMMENT '报告id',
	`age` TINYINT (4) DEFAULT NULL COMMENT '年龄',
	`gender` VARCHAR (10) DEFAULT '' COMMENT '性别',
	`email` VARCHAR (255) DEFAULT '' COMMENT '邮箱',
	`education_level` TINYINT (2) DEFAULT NULL COMMENT '学历 0-未知 1-专科 2-本科 3-硕士 4-博士 5-预科 6-夜大/电大/函大',
	`education_is_graduation` TINYINT (1) DEFAULT NULL COMMENT '是否毕业 0-未毕业 1-毕业',
	`has_carrier_data` TINYINT (1) DEFAULT NULL COMMENT '是否导入过运营商数据 0-否 1-是',
	`has_onlinebank_data` TINYINT (1) DEFAULT NULL COMMENT '是否导入过网银信用卡数据 0-否 1-是',
	`create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '魔杖2.0报告-基础信息表';

-- 2.黑灰名单
DROP TABLE IF EXISTS `cl_magic_black_gray`;
CREATE TABLE `cl_magic_black_gray` (
	`id` BIGINT (11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`user_id` BIGINT (20) DEFAULT NULL COMMENT '用户id',
	`trans_id` VARCHAR (64) DEFAULT '' COMMENT '报告id',
	`black_types` VARCHAR (100) DEFAULT '' COMMENT '黑名单类型列表',
	`black_mobile_name_in_blacklist` TINYINT (1) DEFAULT NULL COMMENT '手机和姓名是否在黑名单 0-否 1-是',
	`black_mobile_name_updated_time` VARCHAR (20) DEFAULT NULL COMMENT '手机和姓名黑名单信息更新时间',
	`black_idcard_name_in_blacklist` TINYINT (1) DEFAULT NULL COMMENT '身份证和姓名是否在黑名单 0-否 1-是',
	`black_idcard_name_updated_time` VARCHAR (20) DEFAULT NULL COMMENT '身份证和姓名黑名单信息更新时间',
	`black_overdue_amount` VARCHAR(20) DEFAULT NULL COMMENT '黑名单最大逾期金额',
	`black_overdue_count` INT (4) DEFAULT NULL COMMENT '黑名单逾期次数',
	`black_overdue_status` VARCHAR (8) DEFAULT NULL COMMENT '黑名单最大逾期天数 M0:0-15天 M1:16-30天 M2:31-60天 M3:61-90天 M4:91-120天 M5:121-150天 M6:151-180天 M6+:大于180天',
	`gray_types` VARCHAR (100) DEFAULT '' COMMENT '灰名单类型列表',
	`gray_mobile_name_in_blacklist` TINYINT (1) DEFAULT NULL COMMENT '手机和姓名是否在灰名单 0-否 1-是',
	`gray_mobile_name_black_updated_time` VARCHAR (20) DEFAULT NULL COMMENT '手机和姓名灰名单信息更新时间',
	`gray_idcard_name_in_blacklist` TINYINT (1) DEFAULT NULL COMMENT '身份证和姓名是否在灰名单 0-否 1-是',
	`gray_idcard_name_black_updated_time` VARCHAR (20) DEFAULT NULL COMMENT '身份证和姓名灰名单信息更新时间',
	`gray_overdue_amount` VARCHAR(20) DEFAULT NULL COMMENT '灰名单最大逾期金额',
	`gray_overdue_count` INT (4) DEFAULT NULL COMMENT '灰名单逾期次数',
	`gray_overdue_status` VARCHAR (8) DEFAULT NULL COMMENT '灰名单最大逾期天数 M0:0-15天 M1:16-30天 M2:31-60天 M3:61-90天 M4:91-120天 M5:121-150天 M6:151-180天 M6+:大于180天',
	`create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '魔杖2.0-黑灰名单记录表';

-- 3.联系人（朋友圈）
DROP TABLE IF EXISTS `cl_magic_mobile_contact`;
CREATE TABLE `cl_magic_mobile_contact` (
	`id` BIGINT (11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`user_id` BIGINT (20) DEFAULT NULL COMMENT '用户id',
	`trans_id` VARCHAR (64) DEFAULT '' COMMENT '报告id',
	`match_score` INT (8) DEFAULT NULL COMMENT '涉黑评分 取值0-100, 分值越低涉黑程度越深',
	`contactnum_30d` INT (8) DEFAULT NULL COMMENT '近30天直接联系人数',
	`auth_contactnum_30d` INT (8) DEFAULT NULL COMMENT '近30天授权过的直接联系人个数',
	`auth_contact_ratio_30d` VARCHAR (10) DEFAULT NULL COMMENT '近30天授权过的直接联系人个数占比',
	`black_contactnum_30d` INT (8) DEFAULT NULL COMMENT '近30天命中黑名单的直接联系人个数',
	`black_contactnum_ratio_30d` VARCHAR (10) DEFAULT NULL COMMENT '近30天命中黑名单的直接联系人个数个数占比',
	`contact_type_30d` VARCHAR (10) DEFAULT NULL COMMENT '近30天直接联系人命中黑名单的类型, 格式：逾期,欺诈',
	`auth_indirectnum_30d` INT (8) DEFAULT NULL COMMENT '近30天授权过的间接联系人个数',
	`auth_indirectnum_ratio_30d` VARCHAR (10) DEFAULT NULL COMMENT '近30天授权过的间接联系人个数占比',
	`black_indirectnum_30d` INT (8) DEFAULT NULL COMMENT '近30天命中黑名单的间接联系人个数',
	`black_indirectnum_ratio_30d` VARCHAR (10) DEFAULT NULL COMMENT '近30天命中黑名单的间接联系人个数占比',
	`black_indirect_peernum_30d` INT (8) DEFAULT NULL COMMENT '近30天引起间接联系人在黑名单的直接联系人数',
	`black_indirect_peernum_ratio_30d` VARCHAR (10) DEFAULT NULL COMMENT '近30天引起间接联系人在黑名单的直接联系人数占比',
	`auth_indirect_peernum_30d` INT (8) DEFAULT NULL COMMENT '近30天引起间接联系人授权的直接联系人数',
	`auth_indirect_peernum_ratio_30d` VARCHAR (10) DEFAULT NULL COMMENT '近30天引起间接联系人授权的直接联系人数占比',
	`contactnum_90d` INT (8) DEFAULT NULL COMMENT '近90天直接联系人数',
	`auth_contactnum_90d` INT (8) DEFAULT NULL COMMENT '近90天授权过的直接联系人个数',
	`auth_contact_ratio_90d` VARCHAR (10) DEFAULT NULL COMMENT '近90天授权过的直接联系人个数占比',
	`black_contactnum_90d` INT (8) DEFAULT NULL COMMENT '近90天命中黑名单的直接联系人个数',
	`black_contactnum_ratio_90d` VARCHAR (10) DEFAULT NULL COMMENT '近90天命中黑名单的直接联系人个数个数占比',
	`contact_type_90d` VARCHAR (10) DEFAULT NULL COMMENT '近90天直接联系人命中黑名单的类型, 格式：逾期,欺诈',
	`auth_indirectnum_90d` INT (8) DEFAULT NULL COMMENT '近90天授权过的间接联系人个数',
	`auth_indirectnum_ratio_90d` VARCHAR (10) DEFAULT NULL COMMENT '近90天授权过的间接联系人个数占比',
	`black_indirectnum_90d` INT (8) DEFAULT NULL COMMENT '近90天命中黑名单的间接联系人个数',
	`black_indirectnum_ratio_90d` VARCHAR (10) DEFAULT NULL COMMENT '近90天命中黑名单的间接联系人个数占比',
	`black_indirect_peernum_90d` INT (8) DEFAULT NULL COMMENT '近90天引起间接联系人在黑名单的直接联系人数',
	`black_indirect_peernum_ratio_90d` VARCHAR (10) DEFAULT NULL COMMENT '近90天引起间接联系人在黑名单的直接联系人数占比',
	`auth_indirect_peernum_90d` INT (8) DEFAULT NULL COMMENT '近90天引起间接联系人授权的直接联系人数',
	`auth_indirect_peernum_ratio_90d` VARCHAR (10) DEFAULT NULL COMMENT '近90天引起间接联系人授权的直接联系人数占比',
	`contactnum_180d` INT (8) DEFAULT NULL COMMENT '近180天直接联系人数',
	`auth_contactnum_180d` INT (8) DEFAULT NULL COMMENT '近180天授权过的直接联系人个数',
	`auth_contact_ratio_180d` VARCHAR (10) DEFAULT NULL COMMENT '近180天授权过的直接联系人个数占比',
	`black_contactnum_180d` INT (8) DEFAULT NULL COMMENT '近180天命中黑名单的直接联系人个数',
	`black_contactnum_ratio_180d` VARCHAR (10) DEFAULT NULL COMMENT '近180天命中黑名单的直接联系人个数个数占比',
	`contact_type_180d` VARCHAR (10) DEFAULT NULL COMMENT '近180天直接联系人命中黑名单的类型, 格式：逾期,欺诈',
	`auth_indirectnum_180d` INT (8) DEFAULT NULL COMMENT '近180天授权过的间接联系人个数',
	`auth_indirectnum_ratio_180d` VARCHAR (10) DEFAULT NULL COMMENT '近180天授权过的间接联系人个数占比',
	`black_indirectnum_180d` INT (8) DEFAULT NULL COMMENT '近180天命中黑名单的间接联系人个数',
	`black_indirectnum_ratio_180d` VARCHAR (10) DEFAULT NULL COMMENT '近180天命中黑名单的间接联系人个数占比',
	`black_indirect_peernum_180d` INT (8) DEFAULT NULL COMMENT '近180天引起间接联系人在黑名单的直接联系人数',
	`black_indirect_peernum_ratio_180d` VARCHAR (10) DEFAULT NULL COMMENT '近180天引起间接联系人在黑名单的直接联系人数占比',
	`auth_indirect_peernum_180d` INT (8) DEFAULT NULL COMMENT '近180天引起间接联系人授权的直接联系人数',
	`auth_indirect_peernum_ratio_180d` VARCHAR (10) DEFAULT NULL COMMENT '近180天引起间接联系人授权的直接联系人数占比',
	`create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '魔杖2.0-联系人数据';

-- 4.亲密联系人（朋友圈）
DROP TABLE IF EXISTS `cl_magic_intimate_contact`;
CREATE TABLE `cl_magic_intimate_contact` (
	`id` BIGINT (11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`user_id` BIGINT (20) DEFAULT NULL COMMENT '用户id',
	`trans_id` VARCHAR (64) DEFAULT '' COMMENT '报告id',
	`intimatenum_30d` INT (8) DEFAULT NULL COMMENT '近30天亲密直接联系人数',
	`auth_intimatenum_30d` INT (8) DEFAULT NULL COMMENT '近30天授权过的亲密直接联系人个数',
	`auth_intimatenum_ratio_30d` VARCHAR (10) DEFAULT NULL COMMENT '近30天授权过的亲密直接联系人个数占比',
	`black_intimatenum_30d` INT (8) DEFAULT NULL COMMENT '近30天命中黑名单的亲密直接联系人个数',
	`black_intimatenum_ratio_30d` VARCHAR (10) DEFAULT NULL COMMENT '近30天命中黑名单的亲密直接联系人个数个数占比',
	`intimate_type_30d` VARCHAR (10) DEFAULT NULL COMMENT '近30天亲密直接联系人命中黑名单的类型, 格式：逾期,欺诈',
	`auth_intimate_indirectnum_30d` INT (8) DEFAULT NULL COMMENT '近30天授权过的亲密间接联系人个数',
	`auth_intimate_indirectnum_ratio_30d` VARCHAR (10) DEFAULT NULL COMMENT '近30天授权过的间接联系人个数占比',
	`black_intimate_indirectnum_30d` INT (8) DEFAULT NULL COMMENT '近30天命中黑名单的间接联系人个数',
	`black_intimate_indirectnum_ratio_30d` VARCHAR (10) DEFAULT NULL COMMENT '近30天命中黑名单的亲密间接联系人个数占比',
	`black_intimate_indirect_peernum_30d` INT (8) DEFAULT NULL COMMENT '近30天引起亲密间接联系人在黑名单的亲密直接联系人数',
	`black_intimate_indirect_peernum_ratio_30d` VARCHAR (10) DEFAULT NULL COMMENT '近30天引起亲密间接联系人在黑名单的亲密直接联系人数占比',
	`auth_intimate_indirect_peernum_30d` INT (8) DEFAULT NULL COMMENT '近30天引起亲密间接联系人授权的亲密直接联系人数',
	`auth_intimate_indirect_peernum_ratio_30d` VARCHAR (10) DEFAULT NULL COMMENT '近30天引起亲密间接联系人授权的亲密直接联系人数占比',
	`intimatenum_90d` INT (8) DEFAULT NULL COMMENT '近90天亲密直接联系人数',
	`auth_intimatenum_90d` INT (8) DEFAULT NULL COMMENT '近90天授权过的亲密直接联系人个数',
	`auth_intimatenum_ratio_90d` VARCHAR (10) DEFAULT NULL COMMENT '近90天授权过的亲密直接联系人个数占比',
	`black_intimatenum_90d` INT (8) DEFAULT NULL COMMENT '近90天命中黑名单的亲密直接联系人个数',
	`black_intimatenum_ratio_90d` VARCHAR (10) DEFAULT NULL COMMENT '近90天命中黑名单的亲密直接联系人个数个数占比',
	`intimate_type_90d` VARCHAR (10) DEFAULT NULL COMMENT '近90天亲密直接联系人命中黑名单的类型, 格式：逾期,欺诈',
	`auth_intimate_indirectnum_90d` INT (8) DEFAULT NULL COMMENT '近90天授权过的亲密间接联系人个数',
	`auth_intimate_indirectnum_ratio_90d` VARCHAR (10) DEFAULT NULL COMMENT '近90天授权过的间接联系人个数占比',
	`black_intimate_indirectnum_90d` INT (8) DEFAULT NULL COMMENT '近90天命中黑名单的间接联系人个数',
	`black_intimate_indirectnum_ratio_90d` VARCHAR (10) DEFAULT NULL COMMENT '近90天命中黑名单的亲密间接联系人个数占比',
	`black_intimate_indirect_peernum_90d` INT (8) DEFAULT NULL COMMENT '近90天引起亲密间接联系人在黑名单的亲密直接联系人数',
	`black_intimate_indirect_peernum_ratio_90d` VARCHAR (10) DEFAULT NULL COMMENT '近90天引起亲密间接联系人在黑名单的亲密直接联系人数占比',
	`auth_intimate_indirect_peernum_90d` INT (8) DEFAULT NULL COMMENT '近90天引起亲密间接联系人授权的亲密直接联系人数',
	`auth_intimate_indirect_peernum_ratio_90d` VARCHAR (10) DEFAULT NULL COMMENT '近90天引起亲密间接联系人授权的亲密直接联系人数占比',
	`intimatenum_180d` INT (8) DEFAULT NULL COMMENT '近180天亲密直接联系人数',
	`auth_intimatenum_180d` INT (8) DEFAULT NULL COMMENT '近180天授权过的亲密直接联系人个数',
	`auth_intimatenum_ratio_180d` VARCHAR (10) DEFAULT NULL COMMENT '近180天授权过的亲密直接联系人个数占比',
	`black_intimatenum_180d` INT (8) DEFAULT NULL COMMENT '近180天命中黑名单的亲密直接联系人个数',
	`black_intimatenum_ratio_180d` VARCHAR (10) DEFAULT NULL COMMENT '近180天命中黑名单的亲密直接联系人个数个数占比',
	`intimate_type_180d` VARCHAR (10) DEFAULT NULL COMMENT '近180天亲密直接联系人命中黑名单的类型, 格式：逾期,欺诈',
	`auth_intimate_indirectnum_180d` INT (8) DEFAULT NULL COMMENT '近180天授权过的亲密间接联系人个数',
	`auth_intimate_indirectnum_ratio_180d` VARCHAR (10) DEFAULT NULL COMMENT '近180天授权过的间接联系人个数占比',
	`black_intimate_indirectnum_180d` INT (8) DEFAULT NULL COMMENT '近180天命中黑名单的间接联系人个数',
	`black_intimate_indirectnum_ratio_180d` VARCHAR (10) DEFAULT NULL COMMENT '近180天命中黑名单的亲密间接联系人个数占比',
	`black_intimate_indirect_peernum_180d` INT (8) DEFAULT NULL COMMENT '近180天引起亲密间接联系人在黑名单的亲密直接联系人数',
	`black_intimate_indirect_peernum_ratio_180d` VARCHAR (10) DEFAULT NULL COMMENT '近180天引起亲密间接联系人在黑名单的亲密直接联系人数占比',
	`auth_intimate_indirect_peernum_180d` INT (8) DEFAULT NULL COMMENT '近180天引起亲密间接联系人授权的亲密直接联系人数',
	`auth_intimate_indirect_peernum_ratio_180d` VARCHAR (10) DEFAULT NULL COMMENT '近180天引起亲密间接联系人授权的亲密直接联系人数占比',
	`create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '魔杖2.0-亲密联系人数据';

-- 5.多头记录表
DROP TABLE IF EXISTS `cl_magic_multipoint`;
CREATE TABLE `cl_magic_multipoint` (
	`id` BIGINT (11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`user_id` BIGINT (20) DEFAULT NULL COMMENT '用户id',
	`trans_id` VARCHAR (64) DEFAULT '' COMMENT '报告id',
	`register_other_count` INT (8) DEFAULT NULL COMMENT '第三方渠道注册机构数量',
	`register_org_count` INT (8) DEFAULT NULL COMMENT '注册机构数量',
	`register_org_types` VARCHAR (100) DEFAULT NULL COMMENT '注册机构类型',
	`queried_detail_org_count` INT (8) DEFAULT NULL COMMENT '机构查询次数',
	`loan_org_cnt_all` INT (8) DEFAULT NULL COMMENT '汇总借贷机构数',
	`loan_org_cnt` INT (8) DEFAULT NULL COMMENT '借贷机构数(去重)',
	`loan_cnt` INT (8) DEFAULT NULL COMMENT '借贷次数',
	`loan_org_cnt_15d` INT (8) DEFAULT NULL COMMENT '近15天贷款的机构数',
	`loan_org_cnt_30d` INT (8) DEFAULT NULL COMMENT '近30天贷款的机构数',
	`loan_org_cnt_90d` INT (8) DEFAULT NULL COMMENT '近90天贷款的机构数',
	`loan_org_cnt_180d` INT (8) DEFAULT NULL COMMENT '近180天贷款的机构数',
	`loan_cnt_15d` INT (8) DEFAULT NULL COMMENT '近15天贷款的次数',
	`loan_cnt_30d` INT (8) DEFAULT NULL COMMENT '近30天贷款的次数',
	`loan_cnt_90d` INT (8) DEFAULT NULL COMMENT '近90天贷款的次数',
	`loan_cnt_180d` INT (8) DEFAULT NULL COMMENT '近180天贷款的次数',
	`create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '魔杖2.0-多头汇总表';

-- 6.多头查询分析表（按机构类型统计贷款申请次数）
DROP TABLE IF EXISTS `cl_magic_multipoint_queried_analyze`;
CREATE TABLE `cl_magic_multipoint_queried_analyze` (
	`id` BIGINT (11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`user_id` BIGINT (20) DEFAULT NULL COMMENT '用户id',
	`trans_id` VARCHAR (64) DEFAULT '' COMMENT '报告id',
	`org_type` VARCHAR (100) DEFAULT NULL COMMENT '机构类型',
	`loan_cnt_15d` INT (8) DEFAULT NULL COMMENT '近15天贷款申请次数',
	`loan_cnt_30d` INT (8) DEFAULT NULL COMMENT '近30天贷款申请次数',
	`loan_cnt_90d` INT (8) DEFAULT NULL COMMENT '近90天贷款申请次数',
	`loan_cnt_180d` INT (8) DEFAULT NULL COMMENT '近180天贷款申请次数',
	`create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '魔杖2.0-多头查询分析表';

-- 7.多头机构查询历史表
DROP TABLE IF EXISTS `cl_magic_multipoint_queried_info`;
CREATE TABLE `cl_magic_multipoint_queried_info` (
	`id` BIGINT (11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`user_id` BIGINT (20) DEFAULT NULL COMMENT '用户id',
	`trans_id` VARCHAR (64) DEFAULT '' COMMENT '报告id',
	`date` VARCHAR (64) DEFAULT '' COMMENT '查询日期',
	`org_type` VARCHAR (100) DEFAULT NULL COMMENT '机构类型',
	`is_self` TINYINT (1) DEFAULT NULL COMMENT '是否本机构查询 0-否 1-是',
	`create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '魔杖2.0-多头查询详情表';

-- 8.法院失信汇总表
DROP TABLE IF EXISTS `cl_magic_untrusted`;
CREATE TABLE `cl_magic_untrusted` (
	`id` BIGINT (11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`user_id` BIGINT (20) DEFAULT NULL COMMENT '用户id',
	`trans_id` VARCHAR (64) DEFAULT '' COMMENT '报告id',
	`courtcase_cnt` INT (8) DEFAULT NULL COMMENT '法院执行人次数',
	`dishonest_cnt` INT (8) DEFAULT NULL COMMENT '失信未执行次数',
	`create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '魔杖2.0-法院失信汇总';

-- 9.法院失信详情表
DROP TABLE IF EXISTS `cl_magic_untrusted_detail`;
CREATE TABLE `cl_magic_untrusted_detail` (
	`id` BIGINT (11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`user_id` BIGINT (20) DEFAULT NULL COMMENT '用户id',
	`trans_id` VARCHAR (64) DEFAULT '' COMMENT '报告id',
	`court_name` VARCHAR (100) DEFAULT NULL COMMENT '执行法院',
	`area_name` VARCHAR (30) DEFAULT NULL COMMENT '省份',
	`case_code` VARCHAR (255) DEFAULT NULL COMMENT '执行依据文号',
	`publish_date` VARCHAR (50) DEFAULT NULL COMMENT '立案时间. 格式yyyy年MM月dd日',
	`gist_id` VARCHAR (100) DEFAULT NULL COMMENT '案号',
	`duty` VARCHAR (100) DEFAULT NULL COMMENT '生效法律文书确定的义务',
	`performance` VARCHAR (100) DEFAULT NULL COMMENT '被执行人的履行性质',
	`disrupt_type_name` VARCHAR (100) DEFAULT NULL COMMENT '失信被执行人行为具体情形',
	`create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '魔杖2.0-法院失信详情';

-- 10.身份证存疑汇总表
DROP TABLE IF EXISTS `cl_magic_suspicious_idcard`;
CREATE TABLE `cl_magic_suspicious_idcard` (
	`id` BIGINT (11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`user_id` BIGINT (20) DEFAULT NULL COMMENT '用户id',
	`trans_id` VARCHAR (64) DEFAULT '' COMMENT '报告id',
	`other_names_cnt` INT (8) DEFAULT NULL COMMENT '身份证存疑姓名数',
	`other_mobiles_cnt` INT (8) DEFAULT NULL COMMENT '身份证存疑手机号码数',
	`other_mobiles_black_cnt` INT (8) DEFAULT NULL COMMENT '身份证存疑触黑手机号码数',
	`information_sources_cnt` INT (8) DEFAULT NULL COMMENT '提供数据的机构数',
	`create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '魔杖2.0-身份证存疑汇总';

-- 11.身份证存疑姓名表（type idcard mobile）
DROP TABLE IF EXISTS `cl_magic_suspicious_other_name`;
CREATE TABLE `cl_magic_suspicious_other_name` (
	`id` BIGINT (11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`user_id` BIGINT (20) DEFAULT NULL COMMENT '用户id',
	`trans_id` VARCHAR (64) DEFAULT '' COMMENT '报告id',
	`latest_used_time` VARCHAR (50) DEFAULT NULL COMMENT '最后使用时间',
	`name` VARCHAR (50) DEFAULT NULL COMMENT '姓名',
	`type` VARCHAR (8) DEFAULT NULL COMMENT '存疑类型 idcard-身份证 mobile-手机 device-设备',
	`create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '魔杖2.0-身份证存疑姓名表';

-- 12.手机号存疑表（手机号命中黑名单情况）
DROP TABLE IF EXISTS `cl_magic_suspicious_other_mobile`;
CREATE TABLE `cl_magic_suspicious_other_mobile` (
	`id` BIGINT (11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`user_id` BIGINT (20) DEFAULT NULL COMMENT '用户id',
	`trans_id` VARCHAR (64) DEFAULT '' COMMENT '报告id',
	`mobile` VARCHAR (20) DEFAULT NULL COMMENT '手机号',
	`other_mobile_location` VARCHAR (50) DEFAULT NULL COMMENT '手机号归属地',
	`carrier` VARCHAR (20) DEFAULT NULL COMMENT '运营商',
	`isblack` TINYINT (1) DEFAULT NULL COMMENT '手机号是否命中黑灰名单 0-否 1-是',
	`latest_used_time` VARCHAR (50) DEFAULT NULL COMMENT '最后使用时间',
	`type` VARCHAR (8) DEFAULT NULL COMMENT '存疑类型 idcard-身份证 device-设备',
	`create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '魔杖2.0-手机号存疑表（手机号命中黑名单情况）';

-- 13.提供数据机构表（type idcard mobile device）
DROP TABLE IF EXISTS `cl_magic_suspicious_information_source`;
CREATE TABLE `cl_magic_suspicious_information_source` (
	`id` BIGINT (11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`user_id` BIGINT (20) DEFAULT NULL COMMENT '用户id',
	`trans_id` VARCHAR (64) DEFAULT '' COMMENT '报告id',
	`org_type` VARCHAR (20) DEFAULT NULL COMMENT '机构类型',
	`latest_used_time` VARCHAR (50) DEFAULT NULL COMMENT '最后使用时间',
	`type` VARCHAR (8) DEFAULT NULL COMMENT '存疑类型 idcard-身份证 mobile-手机 device-设备',
	`create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '魔杖2.0-提供数据机构表';

-- 14.手机存疑汇总表
DROP TABLE IF EXISTS `cl_magic_suspicious_mobile`;
CREATE TABLE `cl_magic_suspicious_mobile` (
	`id` BIGINT (11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`user_id` BIGINT (20) DEFAULT NULL COMMENT '用户id',
	`trans_id` VARCHAR (64) DEFAULT '' COMMENT '报告id',
	`other_names_cnt` INT (8) DEFAULT NULL COMMENT '手机存疑姓名数',
	`other_idcards_cnt` INT (8) DEFAULT NULL COMMENT '手机存疑身份证数',
	`other_idcards_black_cnt` INT (8) DEFAULT NULL COMMENT '手机存疑触黑身份证数',
	`information_sources_cnt` INT (8) DEFAULT NULL COMMENT '提供数据的机构数',
	`create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '魔杖2.0-手机存疑汇总表';

-- 15.设备存疑汇总表
DROP TABLE IF EXISTS `cl_magic_suspicious_device`;
CREATE TABLE `cl_magic_suspicious_device` (
	`id` BIGINT (11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`user_id` BIGINT (20) DEFAULT NULL COMMENT '用户id',
	`trans_id` VARCHAR (64) DEFAULT '' COMMENT '报告id',
	`other_devices_cnt` INT (8) DEFAULT NULL COMMENT '使用过的设备数',
	`mobile_other_devices_cnt` INT (8) DEFAULT NULL COMMENT '手机号码使用过的设备数',
	`idcard_other_devices_cnt` INT (8) DEFAULT NULL COMMENT '身份证号码使用过的设备数',
	`information_sources_cnt` INT (8) DEFAULT NULL COMMENT '提供数据的机构数',
	`other_names_cnt` INT (8) DEFAULT NULL COMMENT '使用过的设备上登陆的其他姓名数',
	`other_mobiles_cnt` INT (8) DEFAULT NULL COMMENT '使用过的设备上登陆的其他手机号码数',
	`other_mobiles_black_cnt` INT (8) DEFAULT NULL COMMENT '使用过的设备上登陆的其他触黑手机号码数',
	`other_idcards_cnt` INT (8) DEFAULT NULL COMMENT '使用过的设备上登陆的其他身份证号码数',
	`other_idcards_black_cnt` INT (8) DEFAULT NULL COMMENT '使用过的设备上登陆的其他触黑身份证号码数',
	`create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '魔杖2.0-设备存疑汇总表';

-- 16.手机号身份证存疑表（身份证命中黑名单情况）
DROP TABLE IF EXISTS `cl_magic_suspicious_other_idcard`;
CREATE TABLE `cl_magic_suspicious_other_idcard` (
	`id` BIGINT (11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`user_id` BIGINT (20) DEFAULT NULL COMMENT '用户id',
	`trans_id` VARCHAR (64) DEFAULT '' COMMENT '报告id',
	`idcard` VARCHAR (20) DEFAULT NULL COMMENT '身份证号',
	`isblack` TINYINT (1) DEFAULT NULL COMMENT '身份证是否命中黑灰名单 0-否 1-是',
	`latest_used_time` VARCHAR (50) DEFAULT NULL COMMENT '最后使用时间',
	`type` VARCHAR (8) DEFAULT NULL COMMENT '存疑类型 mobile-手机号 device-设备',
	`create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '魔杖2.0-身份证存疑表（身份证命中黑名单情况）';

-- 17.QQ群风险表
DROP TABLE IF EXISTS `cl_magic_risk_qq_group`;
CREATE TABLE `cl_magic_risk_qq_group` (
	`id` BIGINT (11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`user_id` BIGINT (20) DEFAULT NULL COMMENT '用户id',
	`trans_id` VARCHAR (64) DEFAULT '' COMMENT '报告id',
	`loan_groupcnt` INT (8) DEFAULT NULL COMMENT '命中借贷群数量',
	`installment_groupcnt` INT (8) DEFAULT NULL COMMENT '命中分期群数量',
	`financial_management_groupcnt` INT (8) DEFAULT NULL COMMENT '命中理财群数量',
	`woolen_groupcnt` INT (8) DEFAULT NULL COMMENT '命中薅羊毛群数量',
	`gambling_groupcnt` INT (8) DEFAULT NULL COMMENT '命中赌博彩票群数量',
	`create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '魔杖2.0-QQ群风险';

-- 18.设备指纹风险表
DROP TABLE IF EXISTS `cl_magic_risk_device`;
CREATE TABLE `cl_magic_risk_device` (
	`id` BIGINT (11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`user_id` BIGINT (20) DEFAULT NULL COMMENT '用户id',
	`trans_id` VARCHAR (64) DEFAULT '' COMMENT '报告id',
	`loan_cnt` INT (8) DEFAULT NULL COMMENT '设备指纹对应的借贷APP数量',
	`consumption_cnt` INT (8) DEFAULT NULL COMMENT '设备指纹对应的消费分期APP数量',
	`lottery_cnt` INT (8) DEFAULT NULL COMMENT '设备指纹对应的彩票APP数量',
	`loan_cnt_ratio` VARCHAR (20) DEFAULT NULL COMMENT '设备指纹对应的借贷APP数量占比',
	`consumption_cnt_ratio` VARCHAR (20) DEFAULT NULL COMMENT '设备指纹对应的消费分期APP数量占比',
	`lottery_cnt_ratio` VARCHAR (20) DEFAULT NULL COMMENT '设备指纹对应的彩票APP数量占比',
	`create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '魔杖2.0-设备指纹风险';

-- 19.信用卡违约表
DROP TABLE IF EXISTS `cl_magic_credit_card_overdue`;
CREATE TABLE `cl_magic_credit_card_overdue` (
	`id` BIGINT (11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`user_id` BIGINT (20) DEFAULT NULL COMMENT '用户id',
	`trans_id` VARCHAR (64) DEFAULT '' COMMENT '报告id',
	`update_date` VARCHAR (50) DEFAULT NULL COMMENT '最近数据更新时间',
	`bank_nums` INT (8) DEFAULT NULL COMMENT '信用卡开户银行数',
	`card_amount` INT (8) DEFAULT NULL COMMENT '卡片数目',
	`total_credit_limit` VARCHAR (50) DEFAULT NULL COMMENT '总信用额',
	`max_credit_limit` VARCHAR (50) DEFAULT NULL COMMENT '单一银行最高信用额',
	`overdue_card` INT (11) DEFAULT NULL COMMENT '有过逾期的卡片数',
	`bill_nums` INT (11) DEFAULT NULL COMMENT '账单总数',
	`overdue_times_12m` INT (11) DEFAULT NULL COMMENT '近一年逾期账单数',
	`overdue_months_12m` INT (11) DEFAULT NULL COMMENT '近一年逾期月数',
	`overdue_times_6m` INT (11) DEFAULT NULL COMMENT '近6个月逾期账单数',
	`overdue_months_6m` INT (11) DEFAULT NULL COMMENT '近6个月逾期月数',
	`overdue_times_3m` INT (11) DEFAULT NULL COMMENT '近3个月逾期账单数',
	`overdue_months_3m` INT (11) DEFAULT NULL COMMENT '近3个月逾期月数',
	`max_overdue_money` VARCHAR (50) DEFAULT NULL COMMENT '最大逾期金额',
	`continue_overdue_times` INT (11) DEFAULT NULL COMMENT '单卡最大连续逾期账单数',
	`last_overdue_date` VARCHAR (50) DEFAULT NULL COMMENT '最近一次逾期时间',
	`create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '魔杖2.0-信用卡违约表';

-- 20.贷后行为表
DROP TABLE IF EXISTS `cl_magic_loan_behavior_analysis`;
CREATE TABLE `cl_magic_loan_behavior_analysis` (
	`id` BIGINT (11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`user_id` BIGINT (20) DEFAULT NULL COMMENT '用户id',
	`trans_id` VARCHAR (64) DEFAULT '' COMMENT '报告id',
	`defaultday_from_first_to_end` VARCHAR (8) DEFAULT NULL COMMENT '借贷第一次逾期距今天数(确定)',
	`jiedai4_sum_fail_cnt_d7` VARCHAR (8) DEFAULT NULL COMMENT '最近7日借贷逾期还款笔数',
	`jiedai_avg_defaultdays_d7` VARCHAR (8) DEFAULT NULL COMMENT '最近7日借贷逾期平均天数(确定)',
	`jiedai4_count_fill_d3_cnt_d7` VARCHAR (8) DEFAULT NULL COMMENT '最近7日借贷逾期还款大于3日笔数',
	`jiedai_max_defaultdays_d7` VARCHAR (8) DEFAULT NULL COMMENT '最近7日借贷逾期最大天数(确定)',
	`dd_jiedai_sum_fill_d5_cnt_d7` VARCHAR (8) DEFAULT NULL COMMENT '最近7日借贷逾期借贷大于5天笔数',
	`dd_jiedai_count_fail_mamberadd_d7` VARCHAR (8) DEFAULT NULL COMMENT '最近7日借贷逾期借贷新增借贷平台数',
	`dd_jiedai_avg_fail_days1_d7` VARCHAR (8) DEFAULT NULL COMMENT '最近7日借贷逾期借贷平均天数(疑似)',
	`jiedai_min_defaultdays_d7` VARCHAR (8) DEFAULT NULL COMMENT '最近7日借贷逾期最小天数(确定)',
	`last_to_end_sure_due_all_pro_all_time_d7` VARCHAR (8) DEFAULT NULL COMMENT '近7天最近一次逾期距今天数(确定)',
	`max_sure_due_days_non_cdq_all_time_d7` VARCHAR (8) DEFAULT NULL COMMENT '近7天非超短期现金贷最大逾期天数(确定)',
	`sum_sure_due_days_all_pro_all_time_d7` VARCHAR (8) DEFAULT NULL COMMENT '近7天累计逾期天数(确定)',
	`last_to_end_sure_due_non_cdq_all_time_d7` VARCHAR (8) DEFAULT NULL COMMENT '近7天最近一次非超短期现金贷逾期距今天数(确定)',
	`jiedai_avg_defaultdays_d15` VARCHAR (8) DEFAULT NULL COMMENT '最近15日借贷逾期平均天数(确定)',
	`jiedai_min_defaultdays_d15` VARCHAR (8) DEFAULT NULL COMMENT '最近15日借贷逾期最小天数(确定)',
	`jiedai4_sum_fail_cnt_d15` VARCHAR (8) DEFAULT NULL COMMENT '最近15日借贷逾期还款笔数',
	`dd_jiedai_avg_fail_days1_d15` VARCHAR (8) DEFAULT NULL COMMENT '最近15日借贷逾期借贷平均天数(疑似)',
	`jiedai4_count_fill_d3_cnt_d15` VARCHAR (8) DEFAULT NULL COMMENT '最近15日借贷逾期还款大于3日笔数',
	`jiedai4_count_fill_d5_cnt_d15` VARCHAR (8) DEFAULT NULL COMMENT '最近15日借贷逾期还款大于5日笔数',
	`dd_jiedai_count_fail_mamberadd_d15` VARCHAR (8) DEFAULT NULL COMMENT '最近15日借贷逾期借贷新增借贷平台数',
	`jiedai_sum_fail_amt_d15` VARCHAR (8) DEFAULT NULL COMMENT '最近15日逾期借贷总金额',
	`dd_jiedai_max_fail_days1_d15` VARCHAR (8) DEFAULT NULL COMMENT '最近15日借贷逾期借贷最大天数(疑似)',
	`sum_sure_due_days_all_pro_all_time_d15` VARCHAR (8) DEFAULT NULL COMMENT '近15天累计逾期天数(确定)',
	`last_to_end_sure_due_all_pro_all_time_d15` VARCHAR (8) DEFAULT NULL COMMENT '近15天最近一次逾期距今天数(确定)',
	`max_sure_due_days_non_cdq_all_time_d15` VARCHAR (8) DEFAULT NULL COMMENT '近15天非超短期现金贷最大逾期天数(确定)',
	`last_to_end_sure_due_non_cdq_all_time_d15` VARCHAR (8) DEFAULT NULL COMMENT '近15天最近一次非超短期现金贷逾期距今天数(确定)',
	`jiedai_avg_defaultdays_m1` VARCHAR (8) DEFAULT NULL COMMENT '最近1月借贷逾期平均天数(确定)',
	`dd_jiedai_max_fail_days1_m1` VARCHAR (8) DEFAULT NULL COMMENT '最近1月借贷逾期借贷最大天数(疑似)',
	`jiedai4_sum_fail_cnt1` VARCHAR (8) DEFAULT NULL COMMENT '最近1月借贷逾期还款笔数',
	`dd_jiedai_max_fail_days_m1` VARCHAR (8) DEFAULT NULL COMMENT '最近1月借贷逾期借贷最大天数(确定)',
	`dd_jiedai_count_fail_mamberadd_m1` VARCHAR (8) DEFAULT NULL COMMENT '最近1月借贷逾期借贷新增借贷平台数',
	`jiedai4_count_fill_d3_cnt_m1` VARCHAR (8) DEFAULT NULL COMMENT '最近1月借贷逾期还款大于3日笔数',
	`dd_jiedai_min_fail_days1_m1` VARCHAR (8) DEFAULT NULL COMMENT '最近1月借贷逾期借贷最小天数(疑似)',
	`jiedai_sum_fail_amt1` VARCHAR (8) DEFAULT NULL COMMENT '最近1月逾期借贷总金额',
	`cdq_dd_jiedai_max_fail_days1_m1` VARCHAR (8) DEFAULT NULL COMMENT '最近1月超短期借贷逾期借贷最大天数(疑似)',
	`jiedai4_count_fill_d5_cnt_m1` VARCHAR (8) DEFAULT NULL COMMENT '最近1月借贷逾期还款大于5日笔数',
	`jiedai4_avg_succ_amt1` VARCHAR (8) DEFAULT NULL COMMENT '最近1月借贷未逾期还款金额平均值',
	`sum_sure_due_days_non_cdq_all_time_m1` VARCHAR (8) DEFAULT NULL COMMENT '近1个月非超短期现金贷累计逾期天数(确定)',
	`sum_sure_due_days_all_pro_all_time_m1` VARCHAR (8) DEFAULT NULL COMMENT '近1个月累计逾期天数(确定)',
	`avg_sure_due_days_non_cdq_all_time_m1` VARCHAR (8) DEFAULT NULL COMMENT '近1个月非超短期现金贷平均逾期天数(确定)',
	`pct_pay_amt_cdq_pro_all_time_m1` VARCHAR (8) DEFAULT NULL COMMENT '近1个月超短期现金贷还款金额占比',
	`max_pay_amt_all_pro_all_time_m1` VARCHAR (8) DEFAULT NULL COMMENT '近1个月最大还款金额',
	`jiedai_avg_defaultdays_m3` VARCHAR (8) DEFAULT NULL COMMENT '最近3月借贷逾期平均天数(确定)',
	`dd_jiedai_max_fail_days1_m3` VARCHAR (8) DEFAULT NULL COMMENT '最近3月借贷逾期借贷最大天数(疑似)',
	`dd_jiedai_avg_fail_days_m3` VARCHAR (8) DEFAULT NULL COMMENT '最近3月借贷逾期借贷平均天数(确定)',
	`dd_jiedai_count_fail_mamberadd_m3` VARCHAR (8) DEFAULT NULL COMMENT '最近3月借贷逾期借贷新增借贷平台数',
	`jiedai4_count_fill_d3_cnt_m3` VARCHAR (8) DEFAULT NULL COMMENT '最近3月借贷逾期还款大于3日笔数',
	`jiedai4_count_fill_d5_cnt_m3` VARCHAR (8) DEFAULT NULL COMMENT '最近3月借贷逾期还款大于5日笔数',
	`cdq_dd_jiedai_avg_fail_days1_m3` VARCHAR (8) DEFAULT NULL COMMENT '最近3月超短期借贷逾期借贷平均天数(疑似)',
	`jiedai4_avg_succ_amt3` VARCHAR (8) DEFAULT NULL COMMENT '最近3月借贷未逾期还款金额平均值',
	`jiedai_sum_fail_amt3` VARCHAR (8) DEFAULT NULL COMMENT '最近3月逾期借贷总金额',
	`dd_jiedai_min_fail_days1_m3` VARCHAR (8) DEFAULT NULL COMMENT '最近3月借贷逾期借贷最小天数(疑似)',
	`sum_sure_due_days_all_pro_all_time_m3` VARCHAR (8) DEFAULT NULL COMMENT '近3个月累计逾期天数(确定)',
	`sum_sure_due_days_non_cdq_all_time_m3` VARCHAR (8) DEFAULT NULL COMMENT '近3个月非超短期现金贷累计逾期天数(确定)',
	`avg_sure_due_days_all_pro_all_time_m3` VARCHAR (8) DEFAULT NULL COMMENT '近3个月平均逾期天数(确定)',
	`max_due_cnt_non_cdq_all_time_m3` VARCHAR (8) DEFAULT NULL COMMENT '近3个月非超短期现金贷最大逾期次数',
	`avg_sure_due_days_non_cdq_all_time_m3` VARCHAR (8) DEFAULT NULL COMMENT '近3个月非超短期现金贷平均逾期天数(确定)',
	`pct_pay_amt_cdq_pro_all_time_m3` VARCHAR (8) DEFAULT NULL COMMENT '近3个月超短期现金贷还款金额占比',
	`jiedai_avg_defaultdays_m6` VARCHAR (8) DEFAULT NULL COMMENT '最近6月借贷逾期平均天数(确定)',
	`dd_jiedai_avg_fail_days1_m6` VARCHAR (8) DEFAULT NULL COMMENT '最近6月借贷逾期借贷平均天数(疑似)',
	`dd_jiedai_avg_fail_days_m6` VARCHAR (8) DEFAULT NULL COMMENT '最近6月借贷逾期借贷平均天数(确定)',
	`dd_jiedai_count_fail_mamberadd_m6` VARCHAR (8) DEFAULT NULL COMMENT '最近6月借贷逾期借贷新增借贷平台数',
	`jiedai4_count_fill_d3_cnt_m6` VARCHAR (8) DEFAULT NULL COMMENT '最近6月借贷逾期还款大于3日笔数',
	`cdq_dd_jiedai_avg_fail_days1_m6` VARCHAR (8) DEFAULT NULL COMMENT '最近6月超短期借贷逾期借贷平均天数(疑似)',
	`cdq_dd_jiedai_max_fail_days1_m6` VARCHAR (8) DEFAULT NULL COMMENT '最近6月超短期借贷逾期借贷最大天数(疑似)',
	`jiedai4_avg_succ_amt6` VARCHAR (8) DEFAULT NULL COMMENT '最近6月借贷未逾期还款金额平均值',
	`jiedai4_count_fill_d5_cnt_m6` VARCHAR (8) DEFAULT NULL COMMENT '最近6月借贷逾期还款大于5日笔数',
	`sum_sure_due_days_non_cdq_all_time_m6` VARCHAR (8) DEFAULT NULL COMMENT '近6个月非超短期现金贷累计逾期天数(确定)',
	`max_sure_due_days_all_pro_all_time_m6` VARCHAR (8) DEFAULT NULL COMMENT '近6个月最大逾期天数(确定)',
	`avg_sure_due_days_all_pro_all_time_m6` VARCHAR (8) DEFAULT NULL COMMENT '近6个月平均逾期天数(确定)',
	`avg_sure_due_days_non_cdq_all_time_m6` VARCHAR (8) DEFAULT NULL COMMENT '近6个月非超短期现金贷平均逾期天数(确定)',
	`pct_pay_amt_cdq_pro_all_time_m6` VARCHAR (8) DEFAULT NULL COMMENT '近6个月超短期现金贷还款金额占比',
	`dd_jiedai_max_fail_days_m12` VARCHAR (8) DEFAULT NULL COMMENT '最近12月借贷逾期借贷最大天数(确定)',
	`dd_jiedai_sum_fill_d5_cnt_m12` VARCHAR (8) DEFAULT NULL COMMENT '最近12月借贷逾期借贷大于5天笔数',
	`last_to_end_sure_due_all_pro_all_time_m12` VARCHAR (8) DEFAULT NULL COMMENT '近12个月最近一次逾期距今天数(确定)',
	`sum_sure_due_days_non_cdq_all_time_m12` VARCHAR (8) DEFAULT NULL COMMENT '近12个月非超短期现金贷累计逾期天数(确定)',
	`last_to_end_sure_due_non_cdq_all_time_m12` VARCHAR (8) DEFAULT NULL COMMENT '近12个月最近一次非超短期现金贷逾期距今天数(确定)',
	`max_due_cnt_all_pro_all_time_m12` VARCHAR (8) DEFAULT NULL COMMENT '近12个月最大逾期次数',
	`max_due_cnt_non_cdq_all_time_m12` VARCHAR (8) DEFAULT NULL COMMENT '近12个月非超短期现金贷最大逾期次数',
	`max_pay_amt_all_pro_all_time_m12` VARCHAR (8) DEFAULT NULL COMMENT '近12个月最大还款金额',
	`sum_pay_cnt_all_pro_all_time_m12` VARCHAR (8) DEFAULT NULL COMMENT '近12个月累计还款笔数',
	`create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '魔杖2.0-贷后行为表';

-- 21.公积金基本信息+统计信息表
DROP TABLE IF EXISTS `cl_magic_fund_info`;
CREATE TABLE `cl_magic_fund_info` (
	`id` BIGINT (11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`user_id` BIGINT (20) DEFAULT NULL COMMENT '用户id',
	`trans_id` VARCHAR (64) DEFAULT '' COMMENT '报告id',
	`update_date` VARCHAR (50) DEFAULT NULL COMMENT '最近数据更新时间. 格式为yyyy-MM-dd',
	`last_pay_date` VARCHAR (50) DEFAULT NULL COMMENT '最近缴存时间. 格式为yyyy-MM-dd',
	`account_status` VARCHAR (20) DEFAULT NULL COMMENT '账户状态',
	`open_date` VARCHAR (50) DEFAULT NULL COMMENT '开户时间. 格式为yyyy-MM-dd',
	`open_location` VARCHAR (100) DEFAULT NULL COMMENT '开户地区',
	`balance` VARCHAR (50) DEFAULT NULL COMMENT '账户余额',
	`email` VARCHAR (100) DEFAULT NULL COMMENT '公积金邮箱',
	`base_amount` VARCHAR (50) DEFAULT NULL COMMENT '缴存基数',
	`monthly_income` VARCHAR (50) DEFAULT NULL COMMENT '月缴存金额',
	`total_months` VARCHAR (20) DEFAULT NULL COMMENT '近一年缴纳月数',
	`continuous_months` TINYINT (4) DEFAULT NULL COMMENT '近一年连续缴纳月数',
	`repay_times` TINYINT (4) DEFAULT NULL COMMENT '近一年补缴次数',
	`total_companies` TINYINT (4) DEFAULT NULL COMMENT '近一年缴纳单位数',
	`create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '魔杖2.0-公积金信息表';

-- 22.借记卡信息+引用卡信息表（字段加前缀处理）
DROP TABLE IF EXISTS `cl_magic_bank_info`;
CREATE TABLE `cl_magic_bank_info` (
	`id` BIGINT (11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`user_id` BIGINT (20) DEFAULT NULL COMMENT '用户id',
	`trans_id` VARCHAR (64) DEFAULT '' COMMENT '报告id',
	`debit_update_date` VARCHAR (50) DEFAULT NULL COMMENT '最近数据更新时间. 格式为yyyy-MM-dd',
	`debit_card_amount` INT (8) DEFAULT NULL COMMENT '借记卡数量',
	`debit_balance` VARCHAR (50) DEFAULT NULL COMMENT '借记卡总余额',
	`debit_total_income` VARCHAR (50) DEFAULT NULL COMMENT '近一年总收入',
	`debit_total_salary_income` VARCHAR (50) DEFAULT NULL COMMENT '近一年工资收入',
	`debit_total_loan_income` VARCHAR (50) DEFAULT NULL COMMENT '近一年贷款收入',
	`debit_total_outcome` VARCHAR (50) DEFAULT NULL COMMENT '近一年总支出',
	`debit_total_consume_outcome` VARCHAR (50) DEFAULT NULL COMMENT '近一年消费支出',
	`debit_total_loan_outcome` VARCHAR (50) DEFAULT NULL COMMENT '近一年还贷支出',
	`credit_update_date` VARCHAR (50) DEFAULT NULL COMMENT '最近数据更新时间. 格式为yyyy-MM-dd',
	`credit_card_amount` INT (8) DEFAULT NULL COMMENT '信用卡数量',
	`credit_total_credit_limit` VARCHAR (50) DEFAULT NULL COMMENT '总信用额度',
	`credit_total_credit_available` VARCHAR (50) DEFAULT NULL COMMENT '总可用信用额',
	`credit_max_credit_limit` VARCHAR (50) DEFAULT NULL COMMENT '单一银行最高信用额',
	`credit_overdue_times` TINYINT (4) DEFAULT NULL COMMENT '近一年逾期次数',
	`credit_overdue_months` TINYINT (4) DEFAULT NULL COMMENT '近一年逾期月数',
	`create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '魔杖2.0-银行卡信息表';

-- 23.欺诈风险名单
DROP TABLE IF EXISTS `cl_magic_fraudulence_info`;
CREATE TABLE `cl_magic_fraudulence_info` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `trans_id` varchar(64) DEFAULT '' COMMENT '报告id',
  `is_hit` tinyint(1) DEFAULT NULL COMMENT '是否命中 0-否 1-是',
  `type` varchar(50) DEFAULT NULL COMMENT '命中欺诈类型',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='魔杖2.0-欺诈风险名单';

-- 24.魔杖2.0请求记录表
DROP TABLE IF EXISTS `cl_magic_req_log`;
CREATE TABLE `cl_magic_req_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `trans_id` varchar(64) DEFAULT NULL COMMENT '任务id',
  `borrow_id` bigint(20) DEFAULT NULL COMMENT '借款标识',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `resp_code` varchar(10) DEFAULT '' COMMENT '回调返回码',
  `resp_params` text COMMENT '同步响应结果',
  `type` tinyint(4) NOT NULL COMMENT '类型 2-魔杖反欺诈 3-魔杖多头 4-魔杖黑灰名单 5-魔杖贷后行为',
  `resp_time` datetime DEFAULT NULL COMMENT '同步响应时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='魔杖2.0请求记录表';

-- 25.请求详情表
DROP TABLE IF EXISTS `cl_magic_req_detail`;
CREATE TABLE `cl_magic_req_detail` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `trans_id` varchar(64) DEFAULT '' COMMENT '报告id',
  `data` longtext COMMENT '返回内容',
  `type` tinyint(4) NOT NULL COMMENT '类型 2-魔杖反欺诈 3-魔杖多头 4-魔杖黑灰名单 5-魔杖贷后行为',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='魔杖2.0-请求详情';
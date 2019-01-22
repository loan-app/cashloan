SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for arc_borrow_rule_config
-- ----------------------------
DROP TABLE IF EXISTS `arc_borrow_rule_config`;
CREATE TABLE `arc_borrow_rule_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `borrow_rule_id` bigint(20) DEFAULT NULL COMMENT '借款规则配置id',
  `rule_id` bigint(20) DEFAULT NULL COMMENT '规则id',
  `rule_sort` int(11) DEFAULT NULL COMMENT '规则执行顺序',
  `config_id` bigint(20) DEFAULT NULL COMMENT '规则配置id',
  `config_sort` int(11) DEFAULT NULL COMMENT '配置执行顺序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='借款规则详细配置表';

-- ----------------------------
-- Records of arc_borrow_rule_config
-- ----------------------------
INSERT INTO `arc_borrow_rule_config` VALUES ('1', '1', '4', '0', '32', '0');
INSERT INTO `arc_borrow_rule_config` VALUES ('2', '1', '4', '0', '33', '0');
INSERT INTO `arc_borrow_rule_config` VALUES ('3', '1', '4', '0', '34', '0');
INSERT INTO `arc_borrow_rule_config` VALUES ('4', '1', '4', '0', '35', '0');
INSERT INTO `arc_borrow_rule_config` VALUES ('5', '1', '4', '0', '36', '0');
INSERT INTO `arc_borrow_rule_config` VALUES ('6', '1', '4', '0', '30', '0');
INSERT INTO `arc_borrow_rule_config` VALUES ('7', '1', '4', '0', '31', '0');
INSERT INTO `arc_borrow_rule_config` VALUES ('8', '1', '4', '0', '16', '0');
INSERT INTO `arc_borrow_rule_config` VALUES ('9', '1', '4', '0', '17', '0');
INSERT INTO `arc_borrow_rule_config` VALUES ('10', '1', '4', '0', '18', '0');
INSERT INTO `arc_borrow_rule_config` VALUES ('11', '1', '4', '0', '19', '0');
INSERT INTO `arc_borrow_rule_config` VALUES ('12', '1', '4', '0', '20', '0');
INSERT INTO `arc_borrow_rule_config` VALUES ('13', '1', '4', '0', '21', '0');
INSERT INTO `arc_borrow_rule_config` VALUES ('14', '1', '4', '0', '22', '0');
INSERT INTO `arc_borrow_rule_config` VALUES ('15', '1', '4', '0', '23', '0');
INSERT INTO `arc_borrow_rule_config` VALUES ('16', '1', '4', '0', '24', '0');
INSERT INTO `arc_borrow_rule_config` VALUES ('17', '1', '4', '0', '25', '0');
INSERT INTO `arc_borrow_rule_config` VALUES ('18', '1', '4', '0', '26', '0');
INSERT INTO `arc_borrow_rule_config` VALUES ('19', '1', '4', '0', '27', '0');
INSERT INTO `arc_borrow_rule_config` VALUES ('20', '1', '4', '0', '28', '0');
INSERT INTO `arc_borrow_rule_config` VALUES ('21', '1', '4', '0', '29', '0');
INSERT INTO `arc_borrow_rule_config` VALUES ('22', '1', '4', '0', '15', '0');
INSERT INTO `arc_borrow_rule_config` VALUES ('23', '1', '3', '0', '7', '0');
INSERT INTO `arc_borrow_rule_config` VALUES ('24', '1', '3', '0', '8', '0');
INSERT INTO `arc_borrow_rule_config` VALUES ('25', '1', '3', '0', '9', '0');
INSERT INTO `arc_borrow_rule_config` VALUES ('26', '1', '3', '0', '10', '0');
INSERT INTO `arc_borrow_rule_config` VALUES ('27', '1', '3', '0', '11', '0');
INSERT INTO `arc_borrow_rule_config` VALUES ('28', '1', '3', '0', '12', '0');
INSERT INTO `arc_borrow_rule_config` VALUES ('29', '1', '3', '0', '13', '0');
INSERT INTO `arc_borrow_rule_config` VALUES ('30', '1', '3', '0', '14', '0');
INSERT INTO `arc_borrow_rule_config` VALUES ('31', '1', '2', '0', '5', '0');
INSERT INTO `arc_borrow_rule_config` VALUES ('32', '1', '2', '0', '6', '0');
INSERT INTO `arc_borrow_rule_config` VALUES ('33', '1', '2', '0', '4', '0');
INSERT INTO `arc_borrow_rule_config` VALUES ('34', '1', '2', '0', '3', '0');
INSERT INTO `arc_borrow_rule_config` VALUES ('35', '1', '1', '0', '2', '0');
INSERT INTO `arc_borrow_rule_config` VALUES ('36', '1', '1', '0', '1', '0');

-- ----------------------------
-- Table structure for arc_borrow_rule_engine
-- ----------------------------
DROP TABLE IF EXISTS `arc_borrow_rule_engine`;
CREATE TABLE `arc_borrow_rule_engine` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `borrow_type_name` varchar(32) NOT NULL DEFAULT '' COMMENT '借款类型',
  `borrow_type` varchar(11) NOT NULL DEFAULT '' COMMENT '借款类型标识 10 分期借款',
  `adapted_name` varchar(32) DEFAULT NULL COMMENT '规则适用场景名称 10 贷前，20 贷后',
  `adapted_id` varchar(11) DEFAULT NULL COMMENT '规则适用场景标识 10 贷前，20 贷后',
  `add_time` datetime NOT NULL COMMENT '添加规则时间',
  `req_ext` varchar(64) DEFAULT '' COMMENT '预留字段',
  `rule_count` int(11) DEFAULT '0' COMMENT '规则数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='借款规则管理';

-- ----------------------------
-- Records of arc_borrow_rule_engine
-- ----------------------------
INSERT INTO `arc_borrow_rule_engine` VALUES ('1', '现金贷', '01', '贷前', '10', '2018-12-06 00:00:00', '', '36');

-- ----------------------------
-- Table structure for arc_borrow_rule_result
-- ----------------------------
DROP TABLE IF EXISTS `arc_borrow_rule_result`;
CREATE TABLE `arc_borrow_rule_result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `borrow_id` bigint(20) NOT NULL COMMENT '借款申请表ID',
  `rule_id` bigint(20) NOT NULL COMMENT '规则表ID',
  `tb_nid` varchar(50) NOT NULL DEFAULT '' COMMENT '表英文名称',
  `tb_name` varchar(50) NOT NULL DEFAULT '' COMMENT '表中文名称',
  `col_nid` varchar(50) NOT NULL DEFAULT '' COMMENT '列名英文名称',
  `col_name` varchar(50) NOT NULL DEFAULT '' COMMENT '列名中文名称',
  `matching` varchar(50) NOT NULL DEFAULT '' COMMENT '匹配当前值',
  `value` varchar(50) NOT NULL DEFAULT '' COMMENT '匹配值',
  `rule` varchar(50) NOT NULL DEFAULT '' COMMENT '匹配规则表达式',
  `result` varchar(1) NOT NULL DEFAULT '' COMMENT '规则匹配结果  Y 匹配成功  N匹配失败',
  `result_type` varchar(2) NOT NULL DEFAULT '' COMMENT '结果类型  10 不通过 20 需人工复审 30 通过',
  `req_ext` varchar(50) DEFAULT '' COMMENT '扩展字段',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `score` int(11) DEFAULT '0' COMMENT '评分模式得分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='借款规则匹配结果表';


-- ----------------------------
-- Table structure for arc_borrow_score_result
-- ----------------------------
DROP TABLE IF EXISTS `arc_borrow_score_result`;
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


-- ----------------------------
-- Table structure for arc_borrow_type_card
-- ----------------------------
DROP TABLE IF EXISTS `arc_borrow_type_card`;
CREATE TABLE `arc_borrow_type_card` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT ,
  `borrow_type_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '借款类型id',
  `borrow_type_name` varchar(32) NOT NULL DEFAULT '' COMMENT '借款类型名称',
  `card_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '评分卡id',
  `card_name` varchar(32) NOT NULL DEFAULT '' COMMENT '评分卡名称',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评分卡类型绑定表';

-- ----------------------------
-- Records of arc_borrow_type_card
-- ----------------------------

-- ----------------------------
-- Table structure for arc_cr_card
-- ----------------------------
DROP TABLE IF EXISTS `arc_cr_card`;
CREATE TABLE `arc_cr_card` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `card_name` varchar(32) NOT NULL DEFAULT '' COMMENT '评分卡名称',
  `score` int(11) NOT NULL DEFAULT '0' COMMENT '评分卡总分',
  `state` varchar(2) NOT NULL DEFAULT '10' COMMENT '状态 10启用,20禁用',
  `type` varchar(2) DEFAULT '' COMMENT '是否被使用 10-已使用 20-未使用',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `nid` varchar(16) NOT NULL DEFAULT '' COMMENT '唯一标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评分卡';

-- ----------------------------
-- Records of arc_cr_card
-- ----------------------------

-- ----------------------------
-- Table structure for arc_cr_credit_type
-- ----------------------------
DROP TABLE IF EXISTS `arc_cr_credit_type`;
CREATE TABLE `arc_cr_credit_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '额度类型',
  `credit_type_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '借款类型id',
  `card_id` varchar(64) NOT NULL DEFAULT '0' COMMENT '评分卡关联id',
  `rank_id` varchar(64) NOT NULL DEFAULT '0' COMMENT '评分等级关联id',
  `borrow_type_id` varchar(20) NOT NULL DEFAULT '0' COMMENT '借款类型关联id',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='额度类型管理表';

-- ----------------------------
-- Records of arc_cr_credit_type
-- ----------------------------

-- ----------------------------
-- Table structure for arc_cr_factor
-- ----------------------------
DROP TABLE IF EXISTS `arc_cr_factor`;
CREATE TABLE `arc_cr_factor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `item_id` bigint(20) NOT NULL COMMENT '评分项目id',
  `factor_name` varchar(32) NOT NULL DEFAULT '' COMMENT '评分因子名称',
  `factor_score` int(11) NOT NULL DEFAULT '0' COMMENT '因子最高分值',
  `state` varchar(2) NOT NULL DEFAULT '10' COMMENT '状态 10启用 20禁用',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `type` varchar(8) DEFAULT '10' COMMENT '维护类型 10-系统 20-手动 30-关联评分卡',
  `nnid` varchar(8) DEFAULT '10' COMMENT '信息类型 10 定性 20定量',
  `ctable` varchar(32) DEFAULT '' COMMENT '关联表',
  `ctable_name` varchar(64) DEFAULT '' COMMENT '关联表名称',
  `ccolumn` varchar(32) DEFAULT '' COMMENT '关联字段',
  `ccolumn_name` varchar(64) DEFAULT '' COMMENT '关联字段名称',
  `ctype` varchar(8) DEFAULT '' COMMENT '关联字段类型',
  `card_id` bigint(20) DEFAULT '0' COMMENT '关联评分卡id',
  `nid` varchar(16) DEFAULT '' COMMENT '唯一标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评分因子表';

-- ----------------------------
-- Records of arc_cr_factor
-- ----------------------------

-- ----------------------------
-- Table structure for arc_cr_factor_param
-- ----------------------------
DROP TABLE IF EXISTS `arc_cr_factor_param`;
CREATE TABLE `arc_cr_factor_param` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `factor_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '评分因子id',
  `param_score` int(11) NOT NULL DEFAULT '0' COMMENT '分值',
  `formula` varchar(32) NOT NULL DEFAULT '' COMMENT '公式符号 <=>',
  `cvalue` varchar(32) NOT NULL DEFAULT '' COMMENT '值',
  `state` varchar(2) NOT NULL DEFAULT '10' COMMENT '状态 10启用 20禁用',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `nid` varchar(16) DEFAULT '' COMMENT '唯一标识',
  `req_ext` varchar(128) DEFAULT '' COMMENT '扩展字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评分因子参数表';

-- ----------------------------
-- Records of arc_cr_factor_param
-- ----------------------------

-- ----------------------------
-- Table structure for arc_cr_info
-- ----------------------------
DROP TABLE IF EXISTS `arc_cr_info`;
CREATE TABLE `arc_cr_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tb_nid` varchar(32) NOT NULL DEFAULT '' COMMENT '表名',
  `tb_name` varchar(32) NOT NULL DEFAULT '' COMMENT '表名注释',
  `detail` text NOT NULL COMMENT '规则信息',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `state` varchar(8) DEFAULT '10' COMMENT '状态10 启用 20 禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评分卡规则信息表';

-- ----------------------------
-- Records of arc_cr_info
-- ----------------------------

-- ----------------------------
-- Table structure for arc_cr_item
-- ----------------------------
DROP TABLE IF EXISTS `arc_cr_item`;
CREATE TABLE `arc_cr_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `card_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '评分卡id',
  `item_name` varchar(32) NOT NULL DEFAULT '' COMMENT '评分项目名称',
  `score` int(11) NOT NULL DEFAULT '0' COMMENT '项目分值',
  `state` varchar(2) NOT NULL DEFAULT '10' COMMENT '状态 10启用 20禁用',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `req_ext` varchar(128) DEFAULT '' COMMENT '扩展字段',
  `nid` varchar(16) DEFAULT '' COMMENT '唯一标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评分卡项目表';

-- ----------------------------
-- Records of arc_cr_item
-- ----------------------------

-- ----------------------------
-- Table structure for arc_cr_rank
-- ----------------------------
DROP TABLE IF EXISTS `arc_cr_rank`;
CREATE TABLE `arc_cr_rank` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rank_name` varchar(32) NOT NULL DEFAULT '' COMMENT '评分等级名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评分卡等级表';

-- ----------------------------
-- Records of arc_cr_rank
-- ----------------------------

-- ----------------------------
-- Table structure for arc_cr_rank_detail
-- ----------------------------
DROP TABLE IF EXISTS `arc_cr_rank_detail`;
CREATE TABLE `arc_cr_rank_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rank_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '评分等级id',
  `rank` varchar(32) NOT NULL DEFAULT '' COMMENT '评分等级',
  `amount_min` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '起始信用额度',
  `amount_max` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '最高信用额度',
  `score_min` int(11) NOT NULL DEFAULT '0' COMMENT '最低分值',
  `score_max` int(11) NOT NULL DEFAULT '0' COMMENT '最高分值',
  `state` varchar(2) NOT NULL DEFAULT '10' COMMENT '是否可用 10是,20否',
  `rtype` varchar(2) NOT NULL DEFAULT '' COMMENT '额度类别 10区间 20固定值',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评分卡等级详情表';

-- ----------------------------
-- Records of arc_cr_rank_detail
-- ----------------------------

-- ----------------------------
-- Table structure for arc_cr_result
-- ----------------------------
DROP TABLE IF EXISTS `arc_cr_result`;
CREATE TABLE `arc_cr_result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `consumer_no` varchar(30) NOT NULL,
  `credit_type_id` bigint(20) NOT NULL,
  `total_score` int(11) DEFAULT '0' COMMENT '总得分',
  `total_amount` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '总额度',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评分结果表';

-- ----------------------------
-- Records of arc_cr_result
-- ----------------------------

-- ----------------------------
-- Table structure for arc_cr_result_detail
-- ----------------------------
DROP TABLE IF EXISTS `arc_cr_result_detail`;
CREATE TABLE `arc_cr_result_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `result_id` bigint(20) NOT NULL COMMENT '评分结果表ID',
  `card_id` bigint(20) NOT NULL COMMENT '评分卡ID',
  `item_id` bigint(20) DEFAULT NULL COMMENT '评分项目ID',
  `factor_id` int(11) DEFAULT NULL,
  `param_id` bigint(20) DEFAULT NULL COMMENT '评分因子参数ID',
  `param_name` varchar(32) DEFAULT NULL COMMENT '参数名称',
  `param_score` int(255) DEFAULT NULL COMMENT '最高分值',
  `formula` varchar(255) DEFAULT NULL COMMENT '表达式',
  `cvalue` varchar(255) DEFAULT NULL COMMENT '参数取值范围',
  `value` varchar(255) DEFAULT NULL COMMENT '参与评分的因子实际值',
  `level` varchar(2) NOT NULL COMMENT '评分分数级别  10 评分卡 20 项目 30 因子',
  `score` int(11) NOT NULL COMMENT '得分',
  `amount` decimal(12,2) DEFAULT '0.00' COMMENT '评分卡额度',
  `card_level` varchar(2) DEFAULT NULL COMMENT '评分卡等级  来源于rank表',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评分结果明细表';

-- ----------------------------
-- Records of arc_cr_result_detail
-- ----------------------------

-- ----------------------------
-- Table structure for arc_credit
-- ----------------------------
DROP TABLE IF EXISTS `arc_credit`;
CREATE TABLE `arc_credit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `consumer_no` varchar(32) NOT NULL DEFAULT '0' COMMENT '用户标识',
  `total` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '总额度',
  `credit_type` bigint(20) NOT NULL DEFAULT '0' COMMENT '额度类型',
  `grade` varchar(16) NOT NULL DEFAULT '0' COMMENT '评分',
  `used` decimal(10,2) DEFAULT '0.00' COMMENT '已使用额度',
  `unuse` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '可使用额度',
  `state` varchar(2) NOT NULL DEFAULT '0' COMMENT '状态 10 -正常 20-冻结',
  `req_ext` varchar(64) DEFAULT '' COMMENT '扩展字段',
  `count` int(11) DEFAULT '0' COMMENT '提额次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='授信额度表';

-- ----------------------------
-- Records of arc_credit
-- ----------------------------

-- ----------------------------
-- Table structure for arc_credit_log
-- ----------------------------
DROP TABLE IF EXISTS `arc_credit_log`;
CREATE TABLE `arc_credit_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `consumer_no` varchar(32) NOT NULL DEFAULT '0' COMMENT '授信额度个人唯一标识',
  `pre` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '变动前额度',
  `now` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '变动后额度',
  `modify_total` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '变动额度',
  `modify_time` datetime NOT NULL COMMENT '变动时间',
  `modify_user` varchar(16) NOT NULL DEFAULT '' COMMENT '变动操作人',
  `type` varchar(2) NOT NULL DEFAULT '0' COMMENT '变动类型 10-增加 20-减少 30-冻结 40-解冻',
  `credit_type` bigint(20) DEFAULT '0' COMMENT '额度类型',
  `remark` varchar(128) DEFAULT '' COMMENT '变更内容',
  `req_ext` varchar(64) DEFAULT NULL COMMENT '扩展字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='授信额度调整记录';

-- ----------------------------
-- Records of arc_credit_log
-- ----------------------------

-- ----------------------------
-- Table structure for arc_rule_engine
-- ----------------------------
DROP TABLE IF EXISTS `arc_rule_engine`;
CREATE TABLE `arc_rule_engine` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) DEFAULT '' COMMENT '规则名称',
  `state` varchar(2) DEFAULT '10' COMMENT '状态 ：10启用，20禁用',
  `config_count` int(11) DEFAULT '0' COMMENT '规则引擎下的配置数量',
  `req_ext` varchar(32) DEFAULT '' COMMENT '扩展字段',
  `add_ip` varchar(16) DEFAULT '' COMMENT '添加IP',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `integral` int(11) DEFAULT '0' COMMENT '规则积分 备用',
  `type` varchar(2) DEFAULT '10' COMMENT '规则模式  10 评分模式  20 结果模式',
  `type_result_status` varchar(2) DEFAULT NULL COMMENT '是否启用结果评级   10 不启用 20启用 ',
  `sort` int(11) DEFAULT '0' COMMENT '权重',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规则引擎';

-- ----------------------------
-- Records of arc_rule_engine
-- ----------------------------
INSERT INTO `arc_rule_engine` VALUES ('1', '运营商信息', '10', '2', null, '127.0.0.1', '2018-12-06 12:46:14', null, '20', '10', null);
INSERT INTO `arc_rule_engine` VALUES ('2', '魔杖黑灰名单', '10', '4', null, '127.0.0.1', '2018-12-06 12:47:54', null, '20', '10', null);
INSERT INTO `arc_rule_engine` VALUES ('3', '魔杖多头', '10', '8', null, '127.0.0.1', '2018-12-06 13:41:44', null, '20', '10', null);
INSERT INTO `arc_rule_engine` VALUES ('4', '魔杖反欺诈', '10', '22', null, '127.0.0.1', '2018-12-06 13:44:44', null, '20', '10', null);

-- ----------------------------
-- Table structure for arc_rule_engine_config
-- ----------------------------
DROP TABLE IF EXISTS `arc_rule_engine_config`;
CREATE TABLE `arc_rule_engine_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rule_engin_id` bigint(20) DEFAULT NULL COMMENT '所属规则引擎id',
  `ctable` varchar(32) DEFAULT '' COMMENT '设置关联表名称',
  `table_comment` varchar(64) DEFAULT NULL COMMENT '表名名称',
  `ccolumn` varchar(32) DEFAULT '' COMMENT '设置关联表列',
  `column_comment` varchar(64) DEFAULT NULL COMMENT '字段名称',
  `formula` varchar(16) DEFAULT '' COMMENT '公式',
  `cvalue` varchar(64) DEFAULT '' COMMENT '值',
  `state` varchar(2) DEFAULT '10' COMMENT '状态 10启用，20禁用  ',
  `req_ext` varchar(32) DEFAULT '' COMMENT '扩展字段',
  `add_ip` varchar(32) DEFAULT '' COMMENT '添加IP',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `type` varchar(16) DEFAULT NULL COMMENT '规则类型 int string',
  `integral` int(11) DEFAULT '0' COMMENT '分值   评分模式下有值',
  `result_type` varchar(2) DEFAULT NULL COMMENT '备用',
  `result` varchar(2) DEFAULT NULL COMMENT '结果    结果模式下有值 10 不通过 20 需人工审核  30通过',
  `sort` int(11) DEFAULT '0' COMMENT '权重',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规则引擎配置信息';

-- ----------------------------
-- Records of arc_rule_engine_config
-- ----------------------------
INSERT INTO `arc_rule_engine_config` VALUES  ('1', '1', 'cl_operator_basic', '运营商基本信息', 'province', '省份', 'include', '新疆', '10', null, '127.0.0.1', '2018-12-06 12:46:14', 'string', null, null, '10', null);
INSERT INTO `arc_rule_engine_config` VALUES  ('2', '1', 'cl_operator_basic', '运营商基本信息', 'reliability', '实名状态', '!=', '1', '10', null, '127.0.0.1', '2018-12-06 12:46:46', 'int', null, null, '10', null);
INSERT INTO `arc_rule_engine_config` VALUES  ('3', '2', 'cl_magic_black_gray', '魔杖2.0-黑灰名单记录表', 'black_mobile_name_in_blacklist', '手机和姓名是否在黑名单', '=', '1', '10', null, '127.0.0.1', '2018-12-06 12:47:54', 'int', null, null, '10', null);
INSERT INTO `arc_rule_engine_config` VALUES  ('4', '2', 'cl_magic_black_gray', '魔杖2.0-黑灰名单记录表', 'black_idcard_name_in_blacklist', '身份证和姓名是否在黑名单', '=', '1', '10', null, '127.0.0.1', '2018-12-06 12:48:13', 'int', null, null, '10', null);
INSERT INTO `arc_rule_engine_config` VALUES  ('5', '2', 'cl_magic_black_gray', '魔杖2.0-黑灰名单记录表', 'gray_idcard_name_in_blacklist', '身份证和姓名是否在灰名单', '=', '1', '10', null, '127.0.0.1', '2018-12-06 13:39:19', 'int', null, null, '10', null);
INSERT INTO `arc_rule_engine_config` VALUES  ('6', '2', 'cl_magic_black_gray', '魔杖2.0-黑灰名单记录表', 'gray_mobile_name_in_blacklist', '手机和姓名是否在灰名单', '=', '1', '10', null, '127.0.0.1', '2018-12-06 13:39:19', 'int', null, null, '10', null);
INSERT INTO `arc_rule_engine_config` VALUES  ('7', '3', 'cl_magic_multipoint', '魔杖2.0-多头汇总表', 'loan_org_cnt_15d', '近15天贷款的机构数', '>=', '30', '10', null, '127.0.0.1', '2018-12-06 13:41:44', 'int', null, null, '10', null);
INSERT INTO `arc_rule_engine_config` VALUES  ('8', '3', 'cl_magic_multipoint', '魔杖2.0-多头汇总表', 'loan_org_cnt_30d', '近30天贷款的机构数', '>=', '45', '10', null, '127.0.0.1', '2018-12-06 13:41:44', 'int', null, null, '10', null);
INSERT INTO `arc_rule_engine_config` VALUES  ('9', '3', 'cl_magic_multipoint', '魔杖2.0-多头汇总表', 'loan_org_cnt_90d', '近90天贷款的机构数', '>=', '80', '10', null, '127.0.0.1', '2018-12-06 13:41:44', 'int', null, null, '10', null);
INSERT INTO `arc_rule_engine_config` VALUES  ('10', '3', 'cl_magic_multipoint', '魔杖2.0-多头汇总表', 'loan_org_cnt_180d', '近180天贷款的机构数', '>=', '120', '10', null, '127.0.0.1', '2018-12-06 13:41:44', 'int', null, null, '10', null);
INSERT INTO `arc_rule_engine_config` VALUES  ('11', '3', 'cl_magic_multipoint', '魔杖2.0-多头汇总表', 'loan_cnt_15d', '近15天贷款的次数', '>=', '30', '10', null, '127.0.0.1', '2018-12-06 13:41:44', 'int', null, null, '10', null);
INSERT INTO `arc_rule_engine_config` VALUES  ('12', '3', 'cl_magic_multipoint', '魔杖2.0-多头汇总表', 'loan_cnt_30d', '近30天贷款的次数', '>=', '45', '10', null, '127.0.0.1', '2018-12-06 13:41:44', 'int', null, null, '10', null);
INSERT INTO `arc_rule_engine_config` VALUES  ('13', '3', 'cl_magic_multipoint', '魔杖2.0-多头汇总表', 'loan_cnt_90d', '近90天贷款的次数', '>=', '80', '10', null, '127.0.0.1', '2018-12-06 13:41:44', 'int', null, null, '10', null);
INSERT INTO `arc_rule_engine_config` VALUES  ('14', '3', 'cl_magic_multipoint', '魔杖2.0-多头汇总表', 'loan_cnt_180d', '近180天贷款的次数', '>=', '120', '10', null, '127.0.0.1', '2018-12-06 13:41:44', 'int', null, null, '10', null);
INSERT INTO `arc_rule_engine_config` VALUES  ('15', '4', 'cl_magic_fraudulence_info', '魔杖2.0-欺诈风险名单', 'is_hit', '是否命中', '=', '1', '10', null, '127.0.0.1', '2018-12-06 13:44:44', 'int', null, null, '10', null);
INSERT INTO `arc_rule_engine_config` VALUES  ('16', '4', 'cl_magic_suspicious_mobile', '魔杖2.0-手机存疑汇总表', 'other_names_cnt', '手机存疑姓名数', '>', '0', '10', null, '127.0.0.1', '2018-12-06 13:49:31', 'int', null, null, '10', null);
INSERT INTO `arc_rule_engine_config` VALUES  ('17', '4', 'cl_magic_suspicious_mobile', '魔杖2.0-手机存疑汇总表', 'other_idcards_cnt', '手机存疑身份证数', '>', '0', '10', null, '127.0.0.1', '2018-12-06 13:49:31', 'int', null, null, '10', null);
INSERT INTO `arc_rule_engine_config` VALUES  ('18', '4', 'cl_magic_suspicious_mobile', '魔杖2.0-手机存疑汇总表', 'other_idcards_black_cnt', '手机存疑触黑身份证数', '>', '0', '10', null, '127.0.0.1', '2018-12-06 13:49:31', 'int', null, null, '10', null);
INSERT INTO `arc_rule_engine_config` VALUES  ('19', '4', 'cl_magic_suspicious_idcard', '魔杖2.0-身份证存疑汇总', 'other_names_cnt', '身份证存疑姓名数', '>', '0', '10', null, '127.0.0.1', '2018-12-06 13:49:31', 'int', null, null, '10', null);
INSERT INTO `arc_rule_engine_config` VALUES  ('20', '4', 'cl_magic_suspicious_idcard', '魔杖2.0-身份证存疑汇总', 'other_mobiles_cnt', '身份证存疑手机号码数', '>', '0', '10', null, '127.0.0.1', '2018-12-06 13:49:31', 'int', null, null, '10', null);
INSERT INTO `arc_rule_engine_config` VALUES  ('21', '4', 'cl_magic_suspicious_idcard', '魔杖2.0-身份证存疑汇总', 'other_mobiles_black_cnt', '身份证存疑触黑手机号码数', '>', '0', '10', null, '127.0.0.1', '2018-12-06 13:49:31', 'int', null, null, '10', null);
INSERT INTO `arc_rule_engine_config` VALUES  ('22', '4', 'cl_magic_suspicious_device', '魔杖2.0-设备存疑汇总表', 'mobile_other_devices_cnt', '手机号码使用过的设备数', '>=', '3', '10', null, '127.0.0.1', '2018-12-06 13:49:31', 'int', null, null, '10', null);
INSERT INTO `arc_rule_engine_config` VALUES  ('23', '4', 'cl_magic_suspicious_device', '魔杖2.0-设备存疑汇总表', 'idcard_other_devices_cnt', '身份证号码使用过的设备数', '>=', '3', '10', null, '127.0.0.1', '2018-12-06 13:49:31', 'int', null, null, '10', null);
INSERT INTO `arc_rule_engine_config` VALUES  ('24', '4', 'cl_magic_suspicious_device', '魔杖2.0-设备存疑汇总表', 'other_devices_cnt', '使用过的设备数', '>=', '3', '10', null, '127.0.0.1', '2018-12-06 13:49:31', 'int', null, null, '10', null);
INSERT INTO `arc_rule_engine_config` VALUES  ('25', '4', 'cl_magic_suspicious_device', '魔杖2.0-设备存疑汇总表', 'other_names_cnt', '使用过的设备上登陆的其他姓名数', '>=', '3', '10', null, '127.0.0.1', '2018-12-06 13:49:31', 'int', null, null, '10', null);
INSERT INTO `arc_rule_engine_config` VALUES  ('26', '4', 'cl_magic_suspicious_device', '魔杖2.0-设备存疑汇总表', 'other_mobiles_cnt', '使用过的设备上登陆的其他手机号码数', '>=', '3', '10', null, '127.0.0.1', '2018-12-06 13:49:31', 'int', null, null, '10', null);
INSERT INTO `arc_rule_engine_config` VALUES  ('27', '4', 'cl_magic_suspicious_device', '魔杖2.0-设备存疑汇总表', 'other_mobiles_black_cnt', '使用过的设备上登陆的其他触黑手机号码数', '>', '0', '10', null, '127.0.0.1', '2018-12-06 13:49:31', 'int', null, null, '10', null);
INSERT INTO `arc_rule_engine_config` VALUES  ('28', '4', 'cl_magic_suspicious_device', '魔杖2.0-设备存疑汇总表', 'other_idcards_cnt', '使用过的设备上登陆的其他身份证号码数', '>=', '3', '10', null, '127.0.0.1', '2018-12-06 13:49:31', 'int', null, null, '10', null);
INSERT INTO `arc_rule_engine_config` VALUES  ('29', '4', 'cl_magic_suspicious_device', '魔杖2.0-设备存疑汇总表', 'other_idcards_black_cnt', '使用过的设备上登陆的其他触黑身份证号码数', '>', '0', '10', null, '127.0.0.1', '2018-12-06 13:49:31', 'int', null, null, '10', null);
INSERT INTO `arc_rule_engine_config` VALUES  ('30', '4', 'cl_magic_untrusted', '魔杖2.0-法院失信汇总', 'courtcase_cnt', '法院执行人次数', '>', '0', '10', null, '127.0.0.1', '2018-12-06 13:51:01', 'int', null, null, '10', null);
INSERT INTO `arc_rule_engine_config` VALUES  ('31', '4', 'cl_magic_untrusted', '魔杖2.0-法院失信汇总', 'dishonest_cnt', '失信未执行次数', '>', '0', '10', null, '127.0.0.1', '2018-12-06 13:51:01', 'int', null, null, '10', null);
INSERT INTO `arc_rule_engine_config` VALUES  ('32', '4', 'cl_magic_risk_qq_group', '魔杖2.0-QQ群风险', 'loan_groupcnt', '命中借贷群数量', '>', '1', '10', null, '127.0.0.1', '2018-12-06 13:52:31', 'int', null, null, '10', null);
INSERT INTO `arc_rule_engine_config` VALUES  ('33', '4', 'cl_magic_risk_qq_group', '魔杖2.0-QQ群风险', 'installment_groupcnt', '命中分期群数量', '>', '1', '10', null, '127.0.0.1', '2018-12-06 13:52:31', 'int', null, null, '10', null);
INSERT INTO `arc_rule_engine_config` VALUES  ('34', '4', 'cl_magic_risk_qq_group', '魔杖2.0-QQ群风险', 'financial_management_groupcnt', '命中理财群数量', '>', '1', '10', null, '127.0.0.1', '2018-12-06 13:52:31', 'int', null, null, '10', null);
INSERT INTO `arc_rule_engine_config` VALUES  ('35', '4', 'cl_magic_risk_qq_group', '魔杖2.0-QQ群风险', 'woolen_groupcnt', '命中薅羊毛群数量', '>', '1', '10', null, '127.0.0.1', '2018-12-06 13:52:31', 'int', null, null, '10', null);
INSERT INTO `arc_rule_engine_config` VALUES  ('36', '4', 'cl_magic_risk_qq_group', '魔杖2.0-QQ群风险', 'gambling_groupcnt', '命中赌博彩票群数量', '>', '1', '10', null, '127.0.0.1', '2018-12-06 13:52:31', 'int', null, null, '10', null);

-- ----------------------------
-- Table structure for arc_rule_engine_info
-- ----------------------------
DROP TABLE IF EXISTS `arc_rule_engine_info`;
CREATE TABLE `arc_rule_engine_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rule_engin_id` bigint(20) DEFAULT NULL COMMENT '所属规则引擎id',
  `min_integral` int(11) DEFAULT '0' COMMENT '分值范围最小值',
  `max_integral` int(11) DEFAULT '0' COMMENT '分值范围最大值',
  `result` varchar(16) DEFAULT NULL COMMENT '结果描述   评分结果模式下  10 不通过 20 需人工审核  30通过',
  `req_ext` varchar(2) DEFAULT NULL COMMENT '备份字段',
  `formula` varchar(16) DEFAULT NULL COMMENT '表达式 ',
  `integral` int(11) DEFAULT NULL COMMENT '分值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规则评分设置';

-- ----------------------------
-- Records of arc_rule_engine_info
-- ----------------------------

-- ----------------------------
-- Table structure for arc_rule_info
-- ----------------------------
DROP TABLE IF EXISTS `arc_rule_info`;
CREATE TABLE `arc_rule_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tb_nid` varchar(50) NOT NULL COMMENT '表名称',
  `tb_name` varchar(50) NOT NULL COMMENT '列名',
  `detail` text NOT NULL COMMENT '对应的规则信息',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `req_ext` varchar(2) DEFAULT NULL COMMENT '备份字段',
  `state` varchar(2) DEFAULT '10' COMMENT '状态：10启用，20禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规则信息表';

-- ----------------------------
-- Records of arc_rule_info
-- ----------------------------
INSERT INTO `arc_rule_info` VALUES ('1', 'cl_magic_untrusted', '魔杖2.0-法院失信汇总', '[{\"name\":\"法院执行人次数\",\"nid\":\"courtcase_cnt\",\"type\":\"int\"},{\"name\":\"失信未执行次数\",\"nid\":\"dishonest_cnt\",\"type\":\"int\"}]', '2018-12-06 12:22:41', null, '10');
INSERT INTO `arc_rule_info` VALUES ('2', 'cl_magic_black_gray', '魔杖2.0-黑灰名单记录表', '[{\"name\":\"手机和姓名是否在黑名单\",\"nid\":\"black_mobile_name_in_blacklist\",\"type\":\"int\"},{\"name\":\"手机和姓名是否在黑名单\",\"nid\":\"black_mobile_name_in_blacklist\",\"type\":\"int\"},{\"name\":\"身份证和姓名是否在黑名单\",\"nid\":\"black_idcard_name_in_blacklist\",\"type\":\"int\"},{\"name\":\"手机和姓名是否在灰名单\",\"nid\":\"gray_mobile_name_in_blacklist\",\"type\":\"int\"},{\"name\":\"身份证和姓名是否在灰名单\",\"nid\":\"gray_idcard_name_in_blacklist\",\"type\":\"int\"}]', '2018-12-06 12:24:25', null, '10');
INSERT INTO `arc_rule_info` VALUES ('3', 'cl_magic_fraudulence_info', '魔杖2.0-欺诈风险名单', '[{\"name\":\"是否命中\",\"nid\":\"is_hit\",\"type\":\"int\"}]', '2018-12-06 12:24:36', null, '10');
INSERT INTO `arc_rule_info` VALUES ('4', 'cl_magic_multipoint', '魔杖2.0-多头汇总表', '[{\"name\":\"近15天贷款的机构数\",\"nid\":\"loan_org_cnt_15d\",\"type\":\"int\"},{\"name\":\"近30天贷款的机构数\",\"nid\":\"loan_org_cnt_30d\",\"type\":\"int\"},{\"name\":\"近90天贷款的机构数\",\"nid\":\"loan_org_cnt_90d\",\"type\":\"int\"},{\"name\":\"近180天贷款的机构数\",\"nid\":\"loan_org_cnt_180d\",\"type\":\"int\"},{\"name\":\"近15天贷款的次数\",\"nid\":\"loan_cnt_15d\",\"type\":\"int\"},{\"name\":\"近30天贷款的次数\",\"nid\":\"loan_cnt_30d\",\"type\":\"int\"},{\"name\":\"近90天贷款的次数\",\"nid\":\"loan_cnt_90d\",\"type\":\"int\"},{\"name\":\"近180天贷款的次数\",\"nid\":\"loan_cnt_180d\",\"type\":\"int\"}]', '2018-12-06 12:24:55', null, '10');
INSERT INTO `arc_rule_info` VALUES ('5', 'cl_magic_risk_qq_group', '魔杖2.0-QQ群风险', '[{\"name\":\"命中借贷群数量\",\"nid\":\"loan_groupcnt\",\"type\":\"int\"},{\"name\":\"命中分期群数量\",\"nid\":\"installment_groupcnt\",\"type\":\"int\"},{\"name\":\"命中理财群数量\",\"nid\":\"financial_management_groupcnt\",\"type\":\"int\"},{\"name\":\"命中薅羊毛群数量\",\"nid\":\"woolen_groupcnt\",\"type\":\"int\"},{\"name\":\"命中赌博彩票群数量\",\"nid\":\"gambling_groupcnt\",\"type\":\"int\"}]', '2018-12-06 12:25:06', null, '10');
INSERT INTO `arc_rule_info` VALUES ('6', 'cl_magic_suspicious_device', '魔杖2.0-设备存疑汇总表', '[{\"name\":\"手机号码使用过的设备数\",\"nid\":\"mobile_other_devices_cnt\",\"type\":\"int\"},{\"name\":\"身份证号码使用过的设备数\",\"nid\":\"idcard_other_devices_cnt\",\"type\":\"int\"},{\"name\":\"使用过的设备数\",\"nid\":\"other_devices_cnt\",\"type\":\"int\"},{\"name\":\"使用过的设备上登陆的其他姓名数\",\"nid\":\"other_names_cnt\",\"type\":\"int\"},{\"name\":\"使用过的设备上登陆的其他手机号码数\",\"nid\":\"other_mobiles_cnt\",\"type\":\"int\"},{\"name\":\"使用过的设备上登陆的其他触黑手机号码数\",\"nid\":\"other_mobiles_black_cnt\",\"type\":\"int\"},{\"name\":\"使用过的设备上登陆的其他身份证号码数\",\"nid\":\"other_idcards_cnt\",\"type\":\"int\"},{\"name\":\"使用过的设备上登陆的其他触黑身份证号码数\",\"nid\":\"other_idcards_black_cnt\",\"type\":\"int\"}]', '2018-12-06 12:25:26', null, '10');
INSERT INTO `arc_rule_info` VALUES ('7', 'cl_magic_suspicious_idcard', '魔杖2.0-身份证存疑汇总', '[{\"name\":\"身份证存疑姓名数\",\"nid\":\"other_names_cnt\",\"type\":\"int\"},{\"name\":\"身份证存疑手机号码数\",\"nid\":\"other_mobiles_cnt\",\"type\":\"int\"},{\"name\":\"身份证存疑触黑手机号码数\",\"nid\":\"other_mobiles_black_cnt\",\"type\":\"int\"}]', '2018-12-06 12:25:38', null, '10');
INSERT INTO `arc_rule_info` VALUES ('8', 'cl_magic_suspicious_mobile', '魔杖2.0-手机存疑汇总表', '[{\"name\":\"手机存疑姓名数\",\"nid\":\"other_names_cnt\",\"type\":\"int\"},{\"name\":\"手机存疑身份证数\",\"nid\":\"other_idcards_cnt\",\"type\":\"int\"},{\"name\":\"手机存疑触黑身份证数\",\"nid\":\"other_idcards_black_cnt\",\"type\":\"int\"}]', '2018-12-06 12:25:49', null, '10');
INSERT INTO `arc_rule_info` VALUES ('9', 'cl_operator_basic', '运营商基本信息', '[{\"name\":\"省份\",\"nid\":\"province\",\"type\":\"string\"},{\"name\":\"实名状态\",\"nid\":\"reliability\",\"type\":\"int\"}]', '2018-12-06 12:26:10', null, '10');

-- ----------------------------
-- Table structure for arc_sys_config
-- ----------------------------
DROP TABLE IF EXISTS `arc_sys_config`;
CREATE TABLE `arc_sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type` tinyint(3) DEFAULT '10' COMMENT '类型',
  `name` varchar(30) DEFAULT '' COMMENT '参数名称',
  `code` varchar(50) DEFAULT '' COMMENT '编号',
  `value` varchar(2048) DEFAULT '' COMMENT '参数对应的值',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态  0不启用  1启用',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注说明',
  `creator` int(11) DEFAULT NULL COMMENT '创建者',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统配置表';

-- ----------------------------
-- Records of arc_sys_config
-- ----------------------------
INSERT INTO `arc_sys_config` VALUES (null, '10', '应用环境（非技术人员勿动）', 'app_environment', 'dev', '1', 'dev/prod', '1');
INSERT INTO `arc_sys_config` VALUES (null, '10', 'api服务域名', 'server_host', 'http://10.10.2.156:8080', '1', '第三方接口回调用', '1');
INSERT INTO `arc_sys_config` VALUES (null, '10', 'manage服务域名', 'manage_host', 'http://10.10.2.156:8081', '1', '管理后台域名', '1');
INSERT INTO `arc_sys_config` VALUES (null, '10', '移动端密钥', 'app_key', '11IhAP24Kb3Bsf7IE14wpl751bQc9VAPsFZ+LdB4riBgg2TDAiEAsSomOO1v8mK2VWhEQh6mttgN1', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES (null, '10', '版本控制', 'check_version', '1.0.0', '1', 'version版本号', '1');
INSERT INTO `arc_sys_config` VALUES (null, '10', '规则引擎配置表', 'rule_tables', 'cl_user_base_info,cl_operator_basic,cl_magic_untrusted,cl_magic_black_gray,cl_magic_fraudulence_info,cl_magic_multipoint,cl_magic_risk_qq_group,cl_magic_suspicious_device,cl_magic_suspicious_idcard,cl_magic_suspicious_mobile,cl_magic_loan_behavior_analysis,cl_magic_mobile_contact,cl_magic_intimate_contact,cl_magic_credit_card_overdue,cl_magic_risk_device', '1', '规则引擎配置表', '1');
INSERT INTO `arc_sys_config` VALUES (null, '10', 'app是否开启定位', 'is_location', '10', '1', '10开启 20关闭', '1');

INSERT INTO `arc_sys_config` VALUES (null, '20', '当日最大注册用户数', 'day_register_max', '0', '0', '当日最大注册用户数', '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '当日放款上限', 'loan_ceiling', '10000', '1', '当日放款上限', '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '注册时给予额度', 'init_credit', '1000', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '借款天数', 'borrow_day', '7,10,14', '1', '借款天数', '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '借款额度', 'borrow_credit', '500,1000', '1', '借款额度', '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '综合费用', 'fee', '0.098,0.12,0.15', '1', '综合费用集合', '1');
-- INSERT INTO `arc_sys_config` VALUES (null, '20', '服务费', 'service_fee', '0.75', '1', '综合费用的75%', '1');
-- INSERT INTO `arc_sys_config` VALUES (null, '20', '信息认证费', 'info_auth_fee', '0.20', '1', '综合费用的25%', '1');
-- INSERT INTO `arc_sys_config` VALUES (null, '20', '借款利息', 'interest', '0.05', '1', '综合费用的5%', '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '费用集合', 'fee_map', '服务费-0.5,信息认证费-0.4,借款利息-0.1', '1', '费用集合', '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '逾期利率', 'penalty_fee', '7-0.02,10-0.03,14-0.04', '1', '格式说明：借款天数-对应逾期利率', '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '逾期罚金上限', 'penalty_amout_max', '0.5', '1', '超过本金一定比例后不再计算罚金', '1');

INSERT INTO `arc_sys_config` VALUES (null, '20', '平台名称', 'title', '现金贷', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '平台电话', 'telephone', '13333333333', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '手机号码号段', 'phone_number_segment', '134,135,136,137,138,139,147,150,151,152,157,158,159,178,182,183,184,187,188,130,131,132,145,155,156,171,175,176,185,186,133,149,153,173,177,180,181,189,170', '1', '用于校验手机号码格式', '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '借款失败后再次借款间隔（天）', 'again_borrow', '15', '1', '', '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '平台收款账户信息-收款人', 'repay_collection_info_name', '', '1', '用于还款登记', '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '平台收款账户信息-收款行', 'repay_collection_info_bank', '', '1', '用于还款登记', '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '平台收款账户信息-银行卡', 'repay_collection_info_bank_card', '', '1', '用于还款登记', '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '平台收款账户信息-支付宝账号', 'repay_collection_info_alipay_account', '', '1', '用于还款登记', '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '记为坏账天数', 'bad_debt_day', '60', '1', '逾期多少天自动标记为坏账', '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '代扣最大次数', 'do_repayment_max', '20', '1', '单笔还款计划代扣最大次数', '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '是否代扣今天的待还', 'do_repayment_today', '20', '1', '10代扣，20不代扣', '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '一级代理分润率', 'level_one', '20.00', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '普通用户分润率', 'level_three', '5.00', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '奖金发放下限', 'amount_grant_min', '100', '1', '达到一定额度才给予发放奖金', '1');

insert into `arc_sys_config` VALUES (null, '20', '认证必填项总数','auth_total','5','1','','1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '人证识别比对合格率', 'idCard_credit_pass_rate', '0.8', '1', '用于个人信息中的人证识别', '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '单日每人证识别最大次数', 'idCardCredit_day_most_times', '10', '1', '单日每人提交人脸识别最大次数', '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '单日每人运营商认证最大次数', 'operatorCredit_day_most_times', '10', '1', '单日每人进行运营商认证的最大次数', '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '四要素认证开关', 'four_elements_switch', '10', '1', '四要素认证开关', '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', 'sdk识别每日可调用次数', 'sdk_time', '{\"faceTime\":\"3\",\"ocrTime\":\"6\"}', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '单日每人四要素认证最大次数', 'four_elements_verity_most_time', '5', '1', '单日每人四要素认证最大次数', '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '芝麻信用配置','zhima_auth','30','1','10-去除 20-选填 30-必填','1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '引流开关', 'divert_able', '20', '1', '10开 20关', '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '引流地址', 'divert_url', 'http://', '1', 'APP引流地址', '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '逾期等级天数', 'penalty_day_level', '30,60', '1', '逾期等级天数，M1 30天以下，M2 60天以下， M3 60天以上', '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '放款审核开关', 'manual_loan', '20', '1', '放款审核  10启用 20禁用', '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '还款提额开关', 'is_improve_credit', '20', '1', '还款提额 10启用 20禁用', '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '还款成功单次增加的额度', 'one_repay_credit', '50', '1', '提额', '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '还款成功累计提额上限', 'imporove_credit_limit', '500', '1', '提额上限', '1');


INSERT INTO `arc_sys_config` VALUES (null, '30', '注册协议', 'protocol_register', '/h5/protocol_register.jsp', '1', '注册协议', '1');
INSERT INTO `arc_sys_config` VALUES (null, '30', '借款协议', 'protocol_borrow', '/h5/protocol_borrow.jsp', '1', '借款协议', '1');


INSERT INTO `arc_sys_config` VALUES (null, '40', '还款帮助', 'h5_repay_help', '/h5/repay_help.jsp', '1', '还款帮助', '1');
INSERT INTO `arc_sys_config` VALUES (null, '40', '还款方式', 'h5_repay_type', '/h5/repay_type.jsp', '1', '还款方式', '1');
INSERT INTO `arc_sys_config` VALUES (null, '40', '关于我们', 'h5_about_us', '/h5/aboutUs.jsp', '1', '关于我们的', '1');
INSERT INTO `arc_sys_config` VALUES (null, '40', '帮助中心', 'h5_help', '/h5/help.jsp', '1', '帮助中心', '1');
INSERT INTO `arc_sys_config` VALUES (null, '40', '邀请页面', 'h5_invite', '/h5/index.jsp', '1', '邀请页面', '1');
INSERT INTO `arc_sys_config` VALUES (null, '40', '邀请规则页面', 'h5_invite_rule', '/h5/invite_rule.jsp', '1', '邀请规则页面', '1');


INSERT INTO `arc_sys_config` VALUES (null, '50', '绑卡备注', 'remark_bank_card', '备注\r\n1.借款通过申请后，我们将会将你的所借款项发放到该张银行卡；\r\n2.若申请重新绑卡，则新卡将激活为收款银行卡；\r\n3.未完成借款期间不允许更换银行卡。\r\n', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES (null, '50', '邀请-我的奖金备注', 'remark_profit_amount', '系统会在每月10日将上月9日至本月10日的奖金汇款至您绑定的银行卡，请注意查收。', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES (null, '50', '邀请用户备注', 'remark_invite', '1分钟认证，20分钟到账，无抵押，纯信用贷。时下最流行的移动贷款APP。国内首批利用大数据、人工智能实现风控审批的信贷服务平台。\r\n', '1', '邀请用户内容', '1');


INSERT INTO `arc_sys_config` VALUES (null, '60', '短信通道', 'sms_passageway', '10', '1', '10大圣', '1');
INSERT INTO `arc_sys_config` VALUES (null, '60', '发送短信APIHOST', 'sms_apihost', 'https://api.dsdatas.com/sms/api/getSmsParameSend', '1', null, '1');
-- INSERT INTO `arc_sys_config` VALUES (null, '60', '发送短信渠道编号', 'sms_channelNo', 'CH1945487800', '1', null, '1');
-- INSERT INTO `arc_sys_config` VALUES (null, '60', '发送短信接口名称', 'sms_interfaceName', 'movekSimpleInfo', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES (null, '60', '短信验证码过期时间', 'sms_time_limit', '5', '1', '单位：分钟', '1');
INSERT INTO `arc_sys_config` VALUES (null, '60', '短信获取倒计时', 'sms_countdown', '60', '1', '倒计时60秒', '1');
INSERT INTO `arc_sys_config` VALUES (null, '60', '单日每类短信验证码允许获取的最大次数', 'sms_day_most_times', '{\"verifyTime\": 10,\"register\": 5,\"findReg\":5,\"findPay\": 5,\"bindCard\": 5}', '1', '单日每类短信验证码允许获取的最大次数', '1');
INSERT INTO `arc_sys_config` VALUES (null, '60', '短信批量接口APIHOST', 'sms_apihost_batch', 'https://api.dsdatas.com/sms/api/batchSmsSend', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES (null, '60', '短信批量接口名称', 'sms_interfaceName_batch', 'movekSmsSend', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES (null, '60', '获取短信报告结果接口地址', 'sms_apihost_report', 'https://api.dsdatas.com/smsSend/getReportByOrderNo', '1', '获取短信报告结果接口地址', '1');


INSERT INTO `arc_sys_config` VALUES (null, '70', '大圣数据APIKEY', 'apikey', '690abc5009eda69f786c380cef51336a', '1', '调用大圣数据接口用', '1');
INSERT INTO `arc_sys_config` VALUES (null, '70', '大圣数据SECRETKEY', 'secretkey', 'ea108d5b1dc792680172d0e925a69470598ed54d', '1', '调用大圣数据接口用', '1');
INSERT INTO `arc_sys_config` VALUES (null, '70', '商汤2.0/linkface OCR地址识别地址', 'linkface_idOcr', 'https://api.dsdatas.com/linkface/idOcr', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES (null, '70', '商汤2.0/linkface 人证对比地址', 'linkface_liVerification', 'https://api.dsdatas.com/linkface/liVerification', '1', null, '1');

INSERT INTO `arc_sys_config` VALUES (null, '70', '运营商认证_商户号', 'operator_channelNo', 'CH0673607634', '1', '上数运营商验证用的商户号', '1');
INSERT INTO `arc_sys_config` VALUES (null, '70', '运营商认证_接口名称', 'operator_interfaceName', 'buildAuthCollItemListUrl', '1', '上数运营商验证用的接口名称', '1');
INSERT INTO `arc_sys_config` VALUES (null, '70', '运营商认证_接口地址', 'operator_apihost', 'https://api.dsdatas.com/credit/api/v1.5/query', '1', '上数运营商验证用的域名', '1');

INSERT INTO `arc_sys_config` VALUES (null, '70', '四要素认证接口名称', 'four_elements_interfaceName', 'txbankCardFourCheckFourQuery', '1', '四要素认证接口名称', '1');
INSERT INTO `arc_sys_config` VALUES (null, '70', '四要素认证接口地址', 'tx_bankCard_four', 'https://api.dsdatas.com/credit/api/v1/query', '1', '四要素认证接口地址', '1');
INSERT INTO `arc_sys_config` VALUES (null, '70', '天行查询渠道号', 'tx_channelNo', 'CH0844282371', '1', NULL, '1');

INSERT INTO `arc_sys_config` VALUES (null, '80', '支付是否开启', 'lianlian_switch', '1', '1', '1开2关', '1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '连连支付商户号', 'lianlian_business_no', '', '1', '调用第三方支付接口用', '1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '连连支付公钥', 'lianlian_public_key', '****', '1', '调用第三方支付接口用', '1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '连连支付私钥', 'lianlian_private_key', '', '1', '调用第三方支付接口用', '1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '连连对账SFTP配置', 'lianlian_sftp', '{\"host\":\"hz-sftp1.lianlianpay.com\",\"port\":\"\",\"user\":\"\",\"passwd\":\"\",\"path\":\"\"}', '1', '连连对账SFTP配置', '1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '连连需要更换银行卡的响应码', 'lianlian_change_bank_card_code', '1005,4012', '1', '连连需要更换银行卡的响应码', '1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '连连需要更换银行卡的响应备注', 'lianlian_change_bank_card_remark', '卡状态异常', '1', '连连需要更换银行卡的响应备注', '1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '芝麻应用标识', 'zhima_app_id', '', '1', '反欺诈和行业关注名单用', '1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '芝麻公钥', 'zhima_public_key', '', '1', '反欺诈和行业关注名单用', '1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '商家私钥', 'zhima_private_key', '', '1', '反欺诈和行业关注名单用，其实是商家私钥，code值主要是为了不冲突。。', '1');

-- INSERT INTO `arc_sys_config` VALUES (null, '80', '同盾商户秘钥','tongdun_operator_partner_key','','1','同盾商户秘钥','1');
-- INSERT INTO `arc_sys_config` VALUES (null, '80', '同盾商户标识','tongdun_operator_partner_code','','1','同盾商户标识','1');

-- ----------------------------
-- Table structure for arc_sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `arc_sys_dict`;
CREATE TABLE `arc_sys_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type_code` varchar(64) DEFAULT NULL COMMENT '类型编码',
  `type_name` varchar(32) DEFAULT NULL COMMENT '类型名称',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `remark` varchar(30) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `type_code` (`type_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典表';

-- ----------------------------
-- Records of arc_sys_dict
-- ----------------------------
INSERT INTO `arc_sys_dict` VALUES ('2', 'PRODUCT_TYPE', '借款类型', '20', '借款类型');
INSERT INTO `arc_sys_dict` VALUES ('6', 'RANK_TYPE', '授信等级', '10', '授信等级');
INSERT INTO `arc_sys_dict` VALUES ('7', 'CREDIT_TYPE', '额度类型', '30', '额度类型');
INSERT INTO `arc_sys_dict` VALUES ('8', 'CONTACT_RELATION', '联系人关系类型', '40', '联系人关系类型');
INSERT INTO `arc_sys_dict` VALUES ('9', 'EDUCATIONAL_STATE', '教育程度', '36', '教育程度');
INSERT INTO `arc_sys_dict` VALUES ('10', 'MARITAL_STATE', '婚姻状况', '28', '婚姻状况');
INSERT INTO `arc_sys_dict` VALUES ('11', 'LIVE_TIME', '居住时长', '27', '居住时长');
INSERT INTO `arc_sys_dict` VALUES ('12', 'WORK_TIME', '工作时长', '26', '工作时长');
INSERT INTO `arc_sys_dict` VALUES ('13', 'SALARY_RANGE', '月薪范围', '25', '月薪范围');
INSERT INTO `arc_sys_dict` VALUES ('14', 'BANK_TYPE', '银行代码', '24', '银行代码');
INSERT INTO `arc_sys_dict` VALUES ('15', 'SYSTEM_TYPE', '系统参数类别', '41', '系统参数类别');
INSERT INTO `arc_sys_dict` VALUES ('16', 'URGE_WAY', '催收方式', '42', '催收方式');
INSERT INTO `arc_sys_dict` VALUES ('18', 'URGE_STATE', '催收状态', '43', '催收状态');
INSERT INTO `arc_sys_dict` VALUES ('20', 'THIRD_DATA_SCENE', '借款场景', '45', '借款场景');
INSERT INTO `arc_sys_dict` VALUES ('21', 'KINSFOLK_RELATION', '直系联系人', '46', '直系联系人');

-- ----------------------------
-- Table structure for arc_sys_dict_detail
-- ----------------------------
DROP TABLE IF EXISTS `arc_sys_dict_detail`;
CREATE TABLE `arc_sys_dict_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `item_code` varchar(64) DEFAULT NULL COMMENT '参数编码',
  `item_value` varchar(32) DEFAULT NULL COMMENT '参数值',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级ID',
  `state` varchar(2) NOT NULL DEFAULT '10' COMMENT '状态  10 有效 20 无效',
  PRIMARY KEY (`id`),
  KEY `item_code` (`item_code`) USING BTREE,
  KEY `parent_id_index` (`parent_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典表数据明细';

-- ----------------------------
-- Records of arc_sys_dict_detail
-- ----------------------------
INSERT INTO `arc_sys_dict_detail` VALUES ('1', '01', '现金贷', '2', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('2', '01', 'A', '6', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('3', '02', 'B', '6', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('4', '03', 'C', '6', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('5', '01', '房贷额度', '7', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('6', '02', '车贷额度', '7', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('10', '01', '配偶', '21', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('11', '02', '子女', '21', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('12', '03', '父亲', '21', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('13', '04', '母亲', '21', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('14', '05', '同事', '8', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('15', '01', '初中及小学以下', '9', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('16', '02', '中专/职高/高中', '9', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('17', '03', '高职/大专', '9', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('18', '04', '本科', '9', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('19', '05', '硕士', '9', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('20', '06', '博士', '9', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('21', '07', '博士后', '9', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('22', '01', '未婚', '10', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('23', '02', '已婚', '10', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('24', '03', '离异', '10', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('25', '04', '丧偶', '10', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('26', '01', '半年以内', '11', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('27', '02', '半年到一年', '11', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('28', '03', '一年以上', '11', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('29', '01', '一年以下', '12', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('30', '02', '一年至三年', '12', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('31', '03', '三年至五年', '12', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('32', '04', '五年以上', '12', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('33', '01', '5千以下', '13', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('34', '02', '5千至1万', '13', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('35', '03', '1万至2万', '13', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('36', '04', '2万以上', '13', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('37', '0801020000', '中国工商银行', '14', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('38', '0801030000', '中国农业银行', '14', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('39', '0801040000', '中国银行', '14', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('40', '0801050000', '中国建设银行', '14', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('41', '0803010000', '交通银行', '14', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('42', '0803020000', '中信银行', '14', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('43', '0803030000', '光大银行', '14', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('44', '0803040000', '华夏银行', '14', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('45', '0803050000', '民生银行', '14', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('46', '0803060000', '广发银行', '14', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('47', '0804100000', '平安银行', '14', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('48', '0803080000', '招商银行', '14', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('49', '30', '协议参数', '15', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('50', '20', '业务参数', '15', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('51', '10', '系统关键参数', '15', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('52', '06', '朋友', '8', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('53', '40', 'H5参数', '15', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('54', '70', '大圣', '15', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('55', '80', '第三方', '15', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('56', '50', '备注参数', '15', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('57', '60', '短信参数', '15', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('58', '10', '电话', '16', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('59', '20', '邮件', '16', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('60', '30', '短信', '16', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('61', '40', '现场沟通', '16', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('62', '50', '其他', '16', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('66', '20', '催收中', '18', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('67', '30', '承诺还款', '18', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('68', '40', '催收成功', '18', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('69', '50', '坏账', '18', '10');

INSERT INTO `arc_sys_dict_detail` VALUES ('70', '0803100000', '浦发银行', '14', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('71', '0803090000', '兴业银行', '14', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('72', '0801000000', '邮政储蓄银行', '14', '10');

-- ----------------------------
-- Table structure for arc_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `arc_sys_user`;
CREATE TABLE `arc_sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `name` varchar(20) DEFAULT '' COMMENT '姓名',
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '账号',
  `password` varchar(128) DEFAULT '' COMMENT '密码',
  `job_num` varchar(20) DEFAULT '' COMMENT '工号',
  `company_id` char(64) DEFAULT NULL COMMENT '公司',
  `office_id` char(64) DEFAULT NULL COMMENT '部门',
  `office_over` varchar(100) DEFAULT NULL COMMENT '管辖机构',
  `position` int(3) unsigned zerofill DEFAULT '0' COMMENT '职位',
  `email` varchar(30) DEFAULT '' COMMENT '邮箱',
  `phone` varchar(20) DEFAULT '' COMMENT '电话',
  `mobile` varchar(11) DEFAULT '' COMMENT '手机',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态 0启用 1禁用',
  `login_ip` varchar(128) DEFAULT '' COMMENT '最近登录IP',
  `login_time` datetime DEFAULT NULL COMMENT '最近登录时间',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_user` varchar(20) DEFAULT '' COMMENT '添加者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(20) DEFAULT '' COMMENT '修改者',
  `remark` varchar(100) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `userNameIndex` (`user_name`) USING BTREE,
  KEY `officeIdIndex` (`office_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户表';

-- ----------------------------
-- Records of arc_sys_user
-- ----------------------------
INSERT INTO `arc_sys_user` VALUES (1, '管理员', 'system', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', '1', '', '', '', NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, '2017-01-01 00:00:00', 'system', NULL);


-- ----------------------------
-- Table structure for arc_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `arc_sys_role`;
CREATE TABLE `arc_sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(20) DEFAULT '' COMMENT '角色名',
  `nid` varchar(30) DEFAULT '' COMMENT '角色唯一标示',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_user` varchar(20) DEFAULT '' COMMENT '添加者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(20) DEFAULT '' COMMENT '修改者',
  `remark` varchar(100) DEFAULT '' COMMENT '备注',
  `is_delete` tinyint(4) DEFAULT NULL COMMENT '是否删除  0否 1是',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nid_index` (`nid`) USING BTREE,
  KEY `primary_key` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of arc_sys_role
-- ----------------------------
INSERT INTO `arc_sys_role` VALUES ('1', '系统管理员', 'system', '2017-01-01 00:00:00', 'system', '2017-01-01 00:00:00', 'system', '超级管理员', '0');
INSERT INTO `arc_sys_role` VALUES ('2', '风控专员', 'riskcontrol', '2017-01-01 00:00:00', 'riskcontrol', '2017-01-01 00:00:00', 'system', '风控专员', '0');
INSERT INTO `arc_sys_role` VALUES ('3', '财务人员', 'finance', '2017-01-01 00:00:00', 'finance', '2017-01-01 00:00:00', 'system', '财务人员', '0');
INSERT INTO `arc_sys_role` VALUES ('4', '客服人员', 'customerservice', '2017-01-01 00:00:00', 'customerservice', '2017-01-01 00:00:00', 'system', '客服人员', '0');
INSERT INTO `arc_sys_role` VALUES ('5', '一级代理商', 'agent',  '2017-01-01 00:00:00', 'system', '2017-01-01 00:00:00', 'system', '请勿改动该角色唯一标识', '0');
INSERT INTO `arc_sys_role` VALUES ('6', '催收管理员', 'collector',  '2017-01-01 00:00:00', 'system', '2017-01-01 00:00:00', 'system', '催收管理员', '0');
INSERT INTO `arc_sys_role` VALUES ('7', '催收专员', 'collectionSpecialist',  '2017-01-01 00:00:00', 'system', '2017-01-01 00:00:00', 'system', '请勿改动该角色唯一标识', '0');

-- ----------------------------
-- Table structure for arc_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `arc_sys_user_role`;
CREATE TABLE `arc_sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) NOT NULL COMMENT '角色主键',
  `user_id` int(11) NOT NULL COMMENT '用户主键',
  `level` tinyint(1) unsigned zerofill DEFAULT NULL COMMENT '级别',
  PRIMARY KEY (`id`),
  KEY `role_id_index` (`role_id`) USING BTREE,
  KEY `user_id_index` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of arc_sys_user_role
-- ----------------------------
INSERT INTO `arc_sys_user_role` VALUES ('1', '1', '1', null);

-- ----------------------------
-- Table structure for arc_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `arc_sys_menu`;
CREATE TABLE `arc_sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `sys_type` tinyint(3) DEFAULT '0' COMMENT '类型  0公用',
  `name` varchar(128) DEFAULT '' COMMENT '菜单名称',
  `parent_id` int(11) unsigned DEFAULT NULL COMMENT '父级ID',
  `href` varchar(512) DEFAULT '' COMMENT '链接地址',
  `icon_cls` varchar(512) DEFAULT '' COMMENT '图标',
  `sort` int(11) unsigned zerofill DEFAULT '00000000000' COMMENT '排序',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_user` varchar(128) DEFAULT '' COMMENT '添加者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(128) DEFAULT '' COMMENT '修改者',
  `remark` varchar(256) DEFAULT '' COMMENT '备注',
  `is_delete` tinyint(2) DEFAULT NULL COMMENT '是否删除 ：0不删除，1删除',
  `scriptid` varchar(255) DEFAULT NULL COMMENT '脚本名称',
  `controller_name` varchar(255) DEFAULT NULL COMMENT '控制器名字',
  `leaf` tinyint(1) DEFAULT NULL COMMENT '是否为子节点  1 true 0 false',
  `level` tinyint(2) unsigned zerofill DEFAULT NULL COMMENT '树状层数据',
  `scriptid_old` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of arc_sys_menu
-- ----------------------------
INSERT INTO `arc_sys_menu` VALUES ('1', '0', '工作台', '0', '', 'icon-yonghuxinxi', '00000000001', null, '', '2017-01-01 00:00:00', '', '工作台', '0', 'ShowWorkbench', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('2', '0', '用户管理', '0', '', 'icon-yonghuxinxi', '00000000002', null, '', '2017-01-01 00:00:00', '', '用户信息', '0', 'UserInformation', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('3', '0', '代理商管理', '0', '', 'icon-dailishang', '00000000003', null, '', '2017-01-01 00:00:00', '', '代理商管理-管理员', '0', 'AgentManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('4', '0', '一级代理商管理', '0', '', 'icon-dailishang', '00000000004', null, '', '2017-01-01 00:00:00', '', '代理商管理-代理商', '0', 'AgentBranchManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('5', '0', '征信引擎', '0', '', 'icon-tongji', '00000000005', null, '', '2017-01-01 00:00:00', '', '第三方征信', '0', 'ThirdPartyInquiry', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('6', '0', '规则引擎', '0', '', 'icon-guizeshuoming', '00000000006', null, '', '2017-01-01 00:00:00', '', null, '0', null, null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('7', '0', '风控管理', '0', '', 'icon-zhuanyefengkong', '00000000007', null, '', '2017-01-01 00:00:00', '', '风控管理', '0', 'RiskControlManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('8', '0', '借款管理', '0', '', 'icon-jiekuanjilu', '00000000008', null, '', '2017-01-01 00:00:00', '', '借款管理', '0', '', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('9', '0', '支付管理', '0', '', 'icon-qian', '00000000009', null, '', '2017-01-01 00:00:00', '', '打款管理', '0', null, null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('10', '0', '还款管理', '0', '', 'icon-qian', '00000000010', null, '', '2017-01-01 00:00:00', '', '还款管理', '0', 'RepaymentManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11', '0', '贷后管理', '0', '', 'icon-qian', '00000000011', null, '', '2017-01-01 00:00:00', '', '贷后管理', '0', null, null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('12', '0', '催收人员管理', '0', '', 'icon-renyuan', '00000000012', null, '', '2017-01-01 00:00:00', '', '催收人员管理', '0', 'CollectionPersonnelManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('13', '0', '催收订单管理', '0', '', 'icon-dingdan', '00000000013', null, '', '2017-01-01 00:00:00', '', '催收订单管理', '0', 'CollectionOrderListManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('14', '0', '我的催收订单', '0', '', 'icon-wodedingdan', '00000000014', null, '', '2017-01-01 00:00:00', '', '我的催收订单管理', '0', 'MyCollectionOrderManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('15', '0', '催收预警', '0', '', 'icon-yujingxiaoxi', '00000000015', null, '', '2017-01-01 00:00:00', '', '催收预警', '0', null, null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('16', '0', '催收统计', '0', '', 'icon-tongji', '00000000016', null, '', '2017-01-01 00:00:00', '', '催收统计管理', '0', null, null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('17', '0', '风控数据', '0', '', 'icon-zhuanyefengkong', '00000000017', null, '', '2017-01-01 00:00:00', '', '风控数据', '0', 'WindControlData', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('18', '0', '运营数据', '0', '', 'icon-tongji', '00000000018', null, '', '2017-01-01 00:00:00', '', ' 运营数据', '0', 'OperationalData', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('19', '0', '统计管理', '0', '', 'icon-tongji', '00000000019', null, '', '2017-01-01 00:00:00', '', ' 统计管理', '0', 'StatisticalManagement', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('20', '0', '系统管理', '0', '', 'icon-xitongguanli', '00000000020', '2017-01-01 00:00:00', null, '2017-01-01 00:00:00', null, '1', '0', 'sysManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('21', '0', '消息管理', '0', '', 'icon-tongji', '00000000021', null, '', '2017-08-30 17:37:47', '', '消息管理', '0', 'newsManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('22', '0', '定时任务', '0', '', 'icon-tongji', '00000000022', null, '', '2017-08-30 17:37:13', '', '定时任务', '0', 'TimedTask', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('23', '0', '广告管理', '0', '', 'icon-zhuanyefengkong', '00000000023', null, '', '2017-01-01 00:00:00', '', '广告管理', '0', 'AdverManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('24', '0', '渠道管理', '0', '', 'icon-tongji', '00000000024', null, '', '2017-08-30 17:37:35', '', '渠道管理', '0', 'PipelineManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('25', '0', '用户信息', '2', '', null, '00000000001', null, '', '2017-01-01 00:00:00', '', '用户基本信息', '0', 'UserBasicInformation', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('26', '0', '用户认证信息', '2', '', null, '00000000002', null, '', '2017-01-01 00:00:00', '', '用户认证信息', '0', 'UserAuthenticationList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('27', '0', '用户通讯录', '2', '', null, '00000000003', null, '', null, '', null, null, 'UserAddressBook', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('28', '0', '用户反馈列表', '2', '', null, '00000000004', null, '', null, '', '用户反馈列表', '0', 'UserFeedback', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('29', '0', '黑/白名单管理', '2', '', null, '00000000005', null, '', null, '', '黑/白名单管理', '0', 'BlackCustomerList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('30', '0', '用户代理等级', '3', '', null, '00000000001', null, '', '2017-01-01 00:00:00', '', '用户代理等级', '0', 'OrdinaryUserList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('31', '0', '代理商列表', '3', '', null, '00000000002', null, '', '2017-01-01 00:00:00', '', '代理商列表', '0', 'AgentList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('32', '0', '奖励资金账户', '3', '', null, '00000000003', null, '', '2017-01-01 00:00:00', '', '奖励资金账户', '0', 'CashCheck', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('33', '0', '奖励获得记录', '3', '', null, '00000000004', null, '', '2017-01-01 00:00:00', '', '奖励获得记录', '0', 'ShareDetail', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('34', '0', '奖励打款记录', '3', '', null, '00000000005', null, '', '2017-01-01 00:00:00', '', '奖励打款记录', '0', 'CashRecord', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('35', '0', '代理商管理', '4', '', null, '00000000001', null, '', null, '', '代理商管理', '0', 'AgentModuleAdminBranch', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('36', '0', '奖励资金账户', '4', '', null, '00000000002', null, '', '2017-01-01 00:00:00', '', '奖励资金账户', '0', 'CashCheckBranch', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('37', '0', '奖励获得记录', '4', '', null, '00000000003', null, '', '2017-01-01 00:00:00', '', '奖励获得记录', '0', 'ShareDetailBranch', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('38', '0', '奖励打款记录', '4', '', null, '00000000004', null, '', '2017-01-01 00:00:00', '', '奖励打款记录', '0', 'CashRecordBranch', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('39', '0', '第三方征信', '5', '', null, '00000000001', null, '', '2017-01-01 00:00:00', '', '第三方征信', '0', 'ThirdPartyInquiry', null, null, null, null);
-- INSERT INTO `arc_sys_menu` VALUES ('40', '0', '系统统计',  '5', '', null, '00000000002', null, '', '2017-01-01 00:00:00', '', '系统统计', '0', 'RiskControlDataStatistics', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('41', '0', '获取机制',  '5', '', null, '00000000003', null, '', '2017-01-01 00:00:00', '', '获取机制', '0', 'ScenePortManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('42', '0', '表字段维护', '6', '', null, '00000000001', null, '', '2017-01-01 00:00:00', '', '表字段维护', '0', 'FormFieldsToAdd', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('43', '0', '规则配置', '6', '', 'icon', '00000000002', null, '', '2017-01-01 00:00:00', '', '规则配置', '0', 'ruleEngine', null, '1', '02', null);
INSERT INTO `arc_sys_menu` VALUES ('44', '0', '规则类型绑定', '6', '', null, '00000000003', null, '', '2017-01-01 00:00:00', '', '规则类型绑定', '0', 'BorrowingRulesManagement', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('45', '0', '规则匹配记录', '6', '', null, '00000000004', null, '', '2017-01-01 00:00:00', '', '规则匹配记录', '1', 'RulesMatchResults', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('46', '0', '待机审订单', '7', '', null, '00000000001', null, '', '2017-01-01 00:00:00', '', '待机审订单列表', '0', 'StandbyReviewList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('47', '0', '机审通过订单', '7', '', null, '00000000002', null, '', null, '', '机审通过订单', '0', 'MachinePassList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('48', '0', '机审拒绝订单', '7', '', null, '00000000003', null, '', null, '', '机审拒绝订单列表', '0', 'RejectedOrderList', null, null, null, null);
-- INSERT INTO `arc_sys_menu` VALUES ('49', '0', '浅橙贷前审核记录', '7', '', null, '00000000004', null, '', null, '', '浅橙贷前审核记录', '0', 'MachineRequestRecord', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('50', '0', '人工复审', '7', '', null, '00000000005', null, '', null, '', '人工复审', '0', 'LoanApplicationManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('51', '0', '发布借款', '8', '', null, '00000000001', null, '', '2017-01-01 00:00:00', '', null, '1', 'TargetRelease', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('52', '0', '借款订单', '8', '', null, '00000000002', null, '', '2017-01-01 00:00:00', '', ' 用户借款管理', '0', 'LoanInformation', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('53', '0', '借款进度', '8', '', null, '00000000003', null, '', '2017-01-01 00:00:00', '', '借款进度', '0', 'LoanSchedule', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('54', '0', '放款订单', '8', '', null, '00000000004', null, '', null, '', '放款列表', '0', 'LoanList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('55', '0', '支付记录', '9', '', null, '00000000001', null, '', '2017-01-01 00:00:00', '', '支付记录', '0', 'remitList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('56', '0', '支付审核', '9', '', null, '00000000002', null, '', '2017-01-01 00:00:00', '', '放款支付审核', '0', 'remitCheck', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('57', '0', '支付对账记录', '9', '', null, '00000000003', null, '', '2017-01-01 00:00:00', '', '支付对账记录', '0', 'CheckManagement', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('58', '0', '还款计划', '10', '', null, '00000000001', null, '', '2017-01-01 00:00:00', '', '还款计划列表', '0', 'RepaymentPlanList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('59', '0', '还款记录', '10', '', null, '00000000002', null, '', '2017-01-01 00:00:00', '', '还款记录', '0', 'PaymentHistory', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('60', '0', '代扣支付记录', '10', '', null, '00000000003', null, '', '2017-01-01 00:00:00', '', '代扣支付记录', '0', 'DeductionsList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('61', '0', '支付宝还款记录', '10', '', null, '00000000004', null, '', '2017-01-01 00:00:00', '', '支付宝还款记录列表', '0', 'AlipayPaymentList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('62', '0', '银行卡还款记录', '10', '', null, '00000000005', null, '', '2017-01-01 00:00:00', '', '银行卡还款记录列表', '0', 'BankCardPaymentList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('63', '0', '已还款订单', '11', '', null, '00000000001', null, '', '2017-01-01 00:00:00', '', '已还款列表', '0', 'RepaymentList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('64', '0', '已逾期订单', '11', '', null, '00000000002', null, '', '2017-01-01 00:00:00', '', '已逾期列表', '0', 'OverdueList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('65', '0', '已坏账订单', '11', '', null, '00000000003', null, '', null, '', '已坏账订单', '0', 'BadDebtsList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('66', '0', '催收员', '12', '', null, '00000000001', null, '', '2017-01-01 00:00:00', '', '催收员列表', '0', 'CollectionPersonnelList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('67', '0', '催收总订单', '13', '', null, '00000000001', null, '', null, '', '催收总订单列表', '0', 'CollectionTotalOrderList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('68', '0', '待催收订单', '13', '', null, '00000000002', null, '', null, '', '待催收记录列表', '0', 'CollectionRecordList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('69', '0', '催收反馈', '13', '', null, '00000000003', null, '', '2017-01-01 00:00:00', '', '催收反馈', '0', 'CollectionFeedback', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('70', '0', '我的订单', '14', '', null, '00000000001', null, '', null, '', '我的订单', '0', 'MyOrder', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('71', '0', '待催收订单', '14', '', null, '00000000002', null, '', null, '', '待催收列表', '0', 'WaitCollectionList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('72', '0', '催收中订单', '14', '', null, '00000000003', null, '', null, '', '催收中列表', '0', 'CollectionInList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('73', '0', '承诺还款订单', '14', '', null, '00000000004', null, '', null, '', '承诺还款列表', '0', 'CommitmentRepaymentList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('74', '0', '催收成功订单', '14', '', null, '00000000005', null, '', null, '', '催收成功列表', '0', 'CollectionSuccessList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('75', '0', '催收反馈', '14', '', null, '00000000006', null, '', null, '', '催收反馈', '0', 'CollectionFeedbackManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('76', '0', '未还款已出催', '15', '', null, '00000000001', null, '', null, '', '未还款已出催', '0', 'NoRepaymentPutForward', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('77', '0', '未分配催收员', '15', '', null, '00000000002', null, '', null, '', '未分配催收员', '0', 'UnallocatedCollection', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('78', '0', '催回率统计', '16', '', null, '00000000001', null, '', null, '', '催回率统计', '0', 'RecoveryRateStatistics', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('79', '0', '催收订单统计', '16', '', null, '00000000002', null, '', null, '', '催收订单统计', '0', 'CollectionOrderStatistics', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('80', '0', '催收员每日统计', '16', '', null, '00000000003', null, '', null, '', '催收员每日统计', '0', 'CollectionDailyStatistics', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('81', '0', '每日通过率', '17', '', null, '00000000001', null, '', null, '', '每日通过率', '0', 'DailyPassRate', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('82', '0', '平台数据日报', '17', '', null, '00000000002', null, '', null, '', '平台数据日报', '0', 'PlatformDataDaily', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('83', '0', '每日还款分析', '18', '', null, '00000000001', null, '', null, '', '每日还款分析', '0', 'DailyRepaymentAnalysis', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('84', '0', '每月还款分析', '18', '', null, '00000000002', null, '', null, '', ' 每月还款分析', '0', 'MonthlyRepaymentAnalysis', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('85', '0', '每月逾期分析', '18', '', null, '00000000003', null, '', '2017-01-01 00:00:00', '', '每月逾期分析', '0', 'Monthly', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('86', '0', '每日未还本金', '19', '', null, '00000000001', null, '', null, '', '每日未还本金', '0', 'DailyPrincipal', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('87', '0', '每日放款收支数据', '19', '', null, '00000000002', null, '', null, '', ' 每日放款收支数据', '0', 'DailylLoanData', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('88', '0', '用户管理', '20', null, 'icon-yonghuguanli', '00000000001', null, null, '2017-01-01 00:00:00', null, '1', '0', 'sysUserManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('89', '0', '角色管理', '20', null, 'icon-jiaoseguanli', '00000000002', null, null, '2017-01-01 00:00:00', null, '1', '0', 'sysRoleManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('90', '0', '菜单管理', '20', null, 'icon-caidanguanli', '00000000003', null, null, '2017-01-01 00:00:00', null, '1', '0', 'sysMenuManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('91', '0', '字典管理', '20', null, 'icon-zidianguanli', '00000000004', null, null, '2017-01-01 00:00:00', null, '1', '0', 'sysDicManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('92', '0', '系统参数设置', '20', '', null, '00000000005', null, '', null, '', null, '0', 'SystemParameterSettings', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('93', '0', 'Druid监控', '20', '', null, '00000000006', null, '', '2017-01-01 00:00:00', '', 'Druid监控', '0', 'Druid', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('94', '0', '短信记录', '21', '', null, '00000000001', null, '', null, '', '短信记录', '0', 'NoteMessage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('95', '0', '短信模板', '21', '', null, '00000000002', null, '', null, '', '短信模板', '0', 'NoteMould', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('96', '0', '任务列表', '22', '', null, '00000000001', null, '', '2017-01-01 00:00:00', '', '定时任务列表', '0', 'TimedTaskList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('97', '0', '执行记录', '22', '', null, '00000000002', null, '', '2017-01-01 00:00:00', '', '定时任务执行记录', '0', 'TimedTaskLog', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('98', '0', '广告信息', '23', '', '', '00000000001', '2017-01-01 00:00:00', '', '2017-01-01 00:00:00', '', '广告信息', '0', 'AdverManageList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('99', '0', '渠道管理', '24', '', null, '00000000001', null, '', null, '', '渠道管理', '0', 'ChannelManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('100', '0', '渠道信息统计', '24', '', null, '00000000001', null, '', null, '', '渠道信息统计', '0', 'ChannelInformationStatistics', null, null, null, null);

INSERT INTO `arc_sys_menu` VALUES ('1001', '0', '信用报告', '0', '', 'icon-xinyong', '00000000002', null, '', '2018-12-06 00:00:00', '', '信用报告', '0', 'CreditReport', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('1002', '0', '运营商报告', '1001', '', null, '00000000001', null, '', '2017-12-06 00:00:00', '', '运营商报告', '0', 'OperatorReport', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('1003', '0', '信贷报告', '1001', '', null, '00000000002', null, '', '2017-01-01 00:00:00', '', '信贷报告', '0', 'CreditLoanReport', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('1004', '0', '费用管理', '0', '', 'icon-qian', '00000000025', null, '', '2017-01-01 00:00:00', '', '费用管理', '0', 'FeeManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('1005', '0', '数据费用', '1004', '', null, '00000000001', null, '', '2017-01-01 00:00:00', '', '数据费用', '0', 'OutsideFee', null, null, null, null);


-- ----------------------------
-- Table structure for arc_sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `arc_sys_role_menu`;
CREATE TABLE `arc_sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` int(11) NOT NULL COMMENT '角色主键',
  `menu_id` int(11) NOT NULL COMMENT '菜单主键',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_id` (`role_id`,`menu_id`) USING BTREE,
  KEY `role_id_index` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单表';

-- ----------------------------
-- Records of arc_sys_role_menu
-- ----------------------------
INSERT INTO `arc_sys_role_menu` VALUES ('1', '1', '1');
INSERT INTO `arc_sys_role_menu` VALUES ('2', '1', '2');
INSERT INTO `arc_sys_role_menu` VALUES ('3', '1', '3');
INSERT INTO `arc_sys_role_menu` VALUES ('4', '1', '4');
INSERT INTO `arc_sys_role_menu` VALUES ('5', '1', '5');
INSERT INTO `arc_sys_role_menu` VALUES ('6', '1', '6');
INSERT INTO `arc_sys_role_menu` VALUES ('7', '1', '7');
INSERT INTO `arc_sys_role_menu` VALUES ('8', '1', '8');
INSERT INTO `arc_sys_role_menu` VALUES ('9', '1', '9');
INSERT INTO `arc_sys_role_menu` VALUES ('10', '1', '10');
INSERT INTO `arc_sys_role_menu` VALUES ('11', '1', '11');
INSERT INTO `arc_sys_role_menu` VALUES ('12', '1', '12');
INSERT INTO `arc_sys_role_menu` VALUES ('13', '1', '13');
INSERT INTO `arc_sys_role_menu` VALUES ('14', '1', '14');
INSERT INTO `arc_sys_role_menu` VALUES ('15', '1', '15');
INSERT INTO `arc_sys_role_menu` VALUES ('16', '1', '16');
INSERT INTO `arc_sys_role_menu` VALUES ('17', '1', '17');
INSERT INTO `arc_sys_role_menu` VALUES ('18', '1', '18');
INSERT INTO `arc_sys_role_menu` VALUES ('19', '1', '19');
INSERT INTO `arc_sys_role_menu` VALUES ('20', '1', '20');
INSERT INTO `arc_sys_role_menu` VALUES ('21', '1', '21');
INSERT INTO `arc_sys_role_menu` VALUES ('22', '1', '22');
INSERT INTO `arc_sys_role_menu` VALUES ('23', '1', '23');
INSERT INTO `arc_sys_role_menu` VALUES ('24', '1', '24');
INSERT INTO `arc_sys_role_menu` VALUES ('25', '1', '25');
INSERT INTO `arc_sys_role_menu` VALUES ('26', '1', '26');
INSERT INTO `arc_sys_role_menu` VALUES ('27', '1', '27');
INSERT INTO `arc_sys_role_menu` VALUES ('28', '1', '28');
INSERT INTO `arc_sys_role_menu` VALUES ('29', '1', '29');
INSERT INTO `arc_sys_role_menu` VALUES ('30', '1', '30');
INSERT INTO `arc_sys_role_menu` VALUES ('31', '1', '31');
INSERT INTO `arc_sys_role_menu` VALUES ('32', '1', '32');
INSERT INTO `arc_sys_role_menu` VALUES ('33', '1', '33');
INSERT INTO `arc_sys_role_menu` VALUES ('34', '1', '34');
INSERT INTO `arc_sys_role_menu` VALUES ('35', '1', '35');
INSERT INTO `arc_sys_role_menu` VALUES ('36', '1', '36');
INSERT INTO `arc_sys_role_menu` VALUES ('37', '1', '37');
INSERT INTO `arc_sys_role_menu` VALUES ('38', '1', '38');
INSERT INTO `arc_sys_role_menu` VALUES ('39', '1', '39');
-- INSERT INTO `arc_sys_role_menu` VALUES ('40', '1', '40');
INSERT INTO `arc_sys_role_menu` VALUES ('41', '1', '41');
INSERT INTO `arc_sys_role_menu` VALUES ('42', '1', '42');
INSERT INTO `arc_sys_role_menu` VALUES ('43', '1', '43');
INSERT INTO `arc_sys_role_menu` VALUES ('44', '1', '44');
INSERT INTO `arc_sys_role_menu` VALUES ('45', '1', '45');
INSERT INTO `arc_sys_role_menu` VALUES ('46', '1', '46');
INSERT INTO `arc_sys_role_menu` VALUES ('47', '1', '47');
INSERT INTO `arc_sys_role_menu` VALUES ('48', '1', '48');
-- INSERT INTO `arc_sys_role_menu` VALUES ('49', '1', '49');
INSERT INTO `arc_sys_role_menu` VALUES ('50', '1', '50');
INSERT INTO `arc_sys_role_menu` VALUES ('51', '1', '51');
INSERT INTO `arc_sys_role_menu` VALUES ('52', '1', '52');
INSERT INTO `arc_sys_role_menu` VALUES ('53', '1', '53');
INSERT INTO `arc_sys_role_menu` VALUES ('54', '1', '54');
INSERT INTO `arc_sys_role_menu` VALUES ('55', '1', '55');
INSERT INTO `arc_sys_role_menu` VALUES ('56', '1', '56');
INSERT INTO `arc_sys_role_menu` VALUES ('57', '1', '57');
INSERT INTO `arc_sys_role_menu` VALUES ('58', '1', '58');
INSERT INTO `arc_sys_role_menu` VALUES ('59', '1', '59');
INSERT INTO `arc_sys_role_menu` VALUES ('60', '1', '60');
INSERT INTO `arc_sys_role_menu` VALUES ('61', '1', '61');
INSERT INTO `arc_sys_role_menu` VALUES ('62', '1', '62');
INSERT INTO `arc_sys_role_menu` VALUES ('63', '1', '63');
INSERT INTO `arc_sys_role_menu` VALUES ('64', '1', '64');
INSERT INTO `arc_sys_role_menu` VALUES ('65', '1', '65');
INSERT INTO `arc_sys_role_menu` VALUES ('66', '1', '66');
INSERT INTO `arc_sys_role_menu` VALUES ('67', '1', '67');
INSERT INTO `arc_sys_role_menu` VALUES ('68', '1', '68');
INSERT INTO `arc_sys_role_menu` VALUES ('69', '1', '69');
INSERT INTO `arc_sys_role_menu` VALUES ('70', '1', '70');
INSERT INTO `arc_sys_role_menu` VALUES ('71', '1', '71');
INSERT INTO `arc_sys_role_menu` VALUES ('72', '1', '72');
INSERT INTO `arc_sys_role_menu` VALUES ('73', '1', '73');
INSERT INTO `arc_sys_role_menu` VALUES ('74', '1', '74');
INSERT INTO `arc_sys_role_menu` VALUES ('75', '1', '75');
INSERT INTO `arc_sys_role_menu` VALUES ('76', '1', '76');
INSERT INTO `arc_sys_role_menu` VALUES ('77', '1', '77');
INSERT INTO `arc_sys_role_menu` VALUES ('78', '1', '78');
INSERT INTO `arc_sys_role_menu` VALUES ('79', '1', '79');
INSERT INTO `arc_sys_role_menu` VALUES ('80', '1', '80');
INSERT INTO `arc_sys_role_menu` VALUES ('81', '1', '81');
INSERT INTO `arc_sys_role_menu` VALUES ('82', '1', '82');
INSERT INTO `arc_sys_role_menu` VALUES ('83', '1', '83');
INSERT INTO `arc_sys_role_menu` VALUES ('84', '1', '84');
INSERT INTO `arc_sys_role_menu` VALUES ('85', '1', '85');
INSERT INTO `arc_sys_role_menu` VALUES ('86', '1', '86');
INSERT INTO `arc_sys_role_menu` VALUES ('87', '1', '87');
INSERT INTO `arc_sys_role_menu` VALUES ('88', '1', '88');
INSERT INTO `arc_sys_role_menu` VALUES ('89', '1', '89');
INSERT INTO `arc_sys_role_menu` VALUES ('90', '1', '90');
INSERT INTO `arc_sys_role_menu` VALUES ('91', '1', '91');
INSERT INTO `arc_sys_role_menu` VALUES ('92', '1', '92');
INSERT INTO `arc_sys_role_menu` VALUES ('93', '1', '93');
INSERT INTO `arc_sys_role_menu` VALUES ('94', '1', '94');
INSERT INTO `arc_sys_role_menu` VALUES ('95', '1', '95');
INSERT INTO `arc_sys_role_menu` VALUES ('96', '1', '96');
INSERT INTO `arc_sys_role_menu` VALUES ('97', '1', '97');
INSERT INTO `arc_sys_role_menu` VALUES ('98', '1', '98');
INSERT INTO `arc_sys_role_menu` VALUES ('99', '1', '99');
INSERT INTO `arc_sys_role_menu` VALUES ('100', '1', '100');
INSERT INTO `arc_sys_role_menu` VALUES ('101', '1', '1001');
INSERT INTO `arc_sys_role_menu` VALUES ('102', '1', '1002');
INSERT INTO `arc_sys_role_menu` VALUES ('103', '1', '1003');
INSERT INTO `arc_sys_role_menu` VALUES ('104', '1', '1004');
INSERT INTO `arc_sys_role_menu` VALUES ('105', '1', '1005');


-- ----------------------------
-- Table structure for arc_sys_perm
-- ----------------------------
DROP TABLE IF EXISTS `arc_sys_perm`;
CREATE TABLE `arc_sys_perm` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(128) NOT NULL COMMENT '编码',
  `name` varchar(512) DEFAULT '' COMMENT '权限名称',
  `perm_level` int(2) NOT NULL DEFAULT '1' COMMENT '权限级别 1-系统级别 2-普通级别',
  `remark` varchar(128) DEFAULT '' COMMENT '权限备注',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `add_user` varchar(11) NOT NULL DEFAULT '' COMMENT '添加人',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of arc_sys_perm
-- ----------------------------

-- ----------------------------
-- Table structure for arc_sys_role_perm
-- ----------------------------
DROP TABLE IF EXISTS `arc_sys_role_perm`;
CREATE TABLE `arc_sys_role_perm` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `perm_id` int(11) NOT NULL COMMENT '权限ID',
  `add_user` varchar(11) NOT NULL DEFAULT '' COMMENT '添加人',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of arc_sys_role_perm
-- ----------------------------

-- ----------------------------
-- Table structure for `cl_adver`
-- ----------------------------
DROP TABLE IF EXISTS `cl_adver`;
CREATE TABLE `cl_adver` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(50) DEFAULT '' COMMENT '标题',
  `link` varchar(255) DEFAULT '' COMMENT '链接',
  `path` varchar(255) DEFAULT '' COMMENT '路径',
  `sort` int(11) DEFAULT 0 COMMENT '排序号',
  `state` varchar(2) DEFAULT '' COMMENT '状态 10启用 20禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='广告';

-- ----------------------------
-- Table structure for cl_app_session
-- ----------------------------
DROP TABLE IF EXISTS `cl_app_session`;
CREATE TABLE `cl_app_session` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `token` char(36) DEFAULT NULL COMMENT 'token值',
  `refresh_token` char(36) DEFAULT NULL COMMENT '刷新指令',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `expire_time` datetime DEFAULT NULL COMMENT '有效期',
  `last_access_time` datetime DEFAULT NULL COMMENT '最后访问时间',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态',
  `session` varchar(2000) DEFAULT '' COMMENT 'session',
  `err_data` varchar(255) DEFAULT '' COMMENT '错误提示',
  `login_type` tinyint(2) DEFAULT NULL COMMENT '登录类别',
  `login_id` int(11) DEFAULT NULL COMMENT '登录id',
  PRIMARY KEY (`id`),
  KEY `token` (`token`),
  KEY `fresh_token` (`refresh_token`),
  KEY `customer_id` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='app登录信息表';

-- ----------------------------
-- Records of cl_app_session
-- ----------------------------

-- ----------------------------
-- Table structure for cl_bank_card
-- ----------------------------
DROP TABLE IF EXISTS `cl_bank_card`;
CREATE TABLE `cl_bank_card` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户标识',
  `bank` varchar(30) DEFAULT '' COMMENT '开户行',
  `card_no` varchar(30) DEFAULT '' COMMENT '银行卡号',
  `phone` varchar(30) DEFAULT '' COMMENT '预留手机号',
  `agree_no` varchar(32) DEFAULT '' COMMENT '签约协议编号',
  `bind_time` datetime DEFAULT NULL COMMENT '绑卡时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银行卡信息表';

-- ----------------------------
-- Records of cl_bank_card
-- ----------------------------

-- ----------------------------
-- Table structure for cl_borrow
-- ----------------------------
DROP TABLE IF EXISTS `cl_borrow`;
CREATE TABLE `cl_borrow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `order_no` varchar(30) NOT NULL DEFAULT '0' COMMENT '订单号',
  `amount` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '借款金额',
  `real_amount` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '实际到账金额',
  `fee` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '综合费用(借款利息+服务费+信息认证费)',
  `create_time` datetime NOT NULL COMMENT '订单生成时间',
  `time_limit` varchar(10) NOT NULL COMMENT '借款期限(天)',
  `state` varchar(4) NOT NULL DEFAULT '' COMMENT '订单状态 10-审核中 20-自动审核成功  21自动审核不通过  22自动审核未决待人工复审 26人工复审通过 27人工复审不通过 30-待还款 40-已还款 41减免还款 50已逾期',
  `card_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '收款银行卡关联id',
  `service_fee` decimal(10,2) DEFAULT '0.00' COMMENT '服务费',
  `info_auth_fee` decimal(10,2) DEFAULT '0.00' COMMENT '信息认证费',
  `interest` decimal(10,2) DEFAULT '0.00' COMMENT '借款利息',
  `client` varchar(30) DEFAULT '10' COMMENT '客户端 默认10 app',
  `address` varchar(512) DEFAULT '' COMMENT '发起借款地址',
  `coordinate` varchar(64) DEFAULT '' COMMENT '借款经纬度坐标',
  `remark` varchar(64) DEFAULT '' COMMENT '备注',
  `ip` varbinary(64) DEFAULT '' COMMENT 'ip地址',
  `again` varchar(2) DEFAULT '' COMMENT '首再贷标识  10首贷  20再贷',
  `sign_service_id` varchar(32) DEFAULT '' COMMENT '电子签章签署记录ID',
  `sub_state` varchar(16) DEFAULT '0' COMMENT '附属状态 11表示不再执行规则',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='借款信息表';

-- ----------------------------
-- Table structure for cl_borrow_progress
-- ----------------------------
DROP TABLE IF EXISTS `cl_borrow_progress`;
CREATE TABLE `cl_borrow_progress` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT '0' COMMENT '关联用户id',
  `borrow_id` bigint(20) DEFAULT '0' COMMENT '借款信息id',
  `state` varchar(30) DEFAULT NULL COMMENT '借款进度状态 10申请成功待审核  20自动审核通过 21自动审核不通过  22自动审核未决待人工复审 26人工复审通过 27人工复审不通过    30放款成功  31放款失败   40还款成功    50逾期  90坏账',
  `remark` varchar(256) DEFAULT NULL COMMENT '进度描述',
  `create_time` datetime DEFAULT NULL COMMENT '进度生成时间',
  `loan_time` datetime DEFAULT NULL COMMENT '放款日期',
  `repay_time` datetime DEFAULT NULL COMMENT '还款日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='借款进度表';

-- ----------------------------
-- Records of cl_borrow_progress
-- ----------------------------

-- ----------------------------
-- Table structure for cl_borrow_repay
-- ----------------------------
DROP TABLE IF EXISTS `cl_borrow_repay`;
CREATE TABLE `cl_borrow_repay` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `borrow_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '借款订单id',
  `amount` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '还款金额',
  `repay_time` datetime NOT NULL COMMENT '应还款时间',
  `state` varchar(10) NOT NULL COMMENT '状态 10已还款 20未还款',
  `penalty_amout` decimal(12,2) DEFAULT '0.00' COMMENT '逾期罚金',
  `penalty_day` int(10) DEFAULT '0' COMMENT '逾期天数',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='还款计划表';

-- ----------------------------
-- Records of cl_borrow_repay
-- ----------------------------

-- ----------------------------
-- Table structure for cl_borrow_repay_log
-- ----------------------------
DROP TABLE IF EXISTS `cl_borrow_repay_log`;
CREATE TABLE `cl_borrow_repay_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `repay_id` bigint(20) DEFAULT '0' COMMENT '还款计划id',
  `borrow_id` bigint(20) DEFAULT '0' COMMENT '借款订单id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `amount` decimal(10,2) DEFAULT '0.00' COMMENT '实际还款金额',
  `penalty_day` int(10) DEFAULT '0' COMMENT '逾期天数',
  `penalty_amout` decimal(10,2) DEFAULT '0.00' COMMENT '逾期罚金',
  `repay_way` varchar(2) DEFAULT '' COMMENT '还款方式   10代扣，20银行卡转账，30支付宝转账',
  `repay_account` varchar(60) DEFAULT '' COMMENT '还款账号',
  `serial_number` varchar(60) DEFAULT '' COMMENT '还款流水号',
  `refund_deduction` decimal(10,2) DEFAULT '0.00' COMMENT '退还或补扣金额',
  `pay_time` datetime DEFAULT NULL COMMENT '退还或补扣支付时间',
  `repay_time` datetime DEFAULT NULL COMMENT '实际还款时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='还款记录表';

-- ----------------------------
-- Records of cl_borrow_repay_log
-- ----------------------------

-- ----------------------------
-- Table structure for cl_channel
-- ----------------------------
DROP TABLE IF EXISTS `cl_channel`;
CREATE TABLE `cl_channel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `code` varchar(16) binary DEFAULT '' COMMENT '渠道代码',
  `name` varchar(16) DEFAULT '' COMMENT '渠道名称',
  `linker` varchar(16) DEFAULT '' COMMENT '联系人',
  `phone` varchar(16) DEFAULT '' COMMENT '联系电话',
  `type` varchar(2) DEFAULT '' COMMENT '渠道类型  (备用)',
  `state` varchar(2) NOT NULL DEFAULT '' COMMENT '状态 10：启用20：禁用',
  `create_time` datetime NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='渠道信息表';

-- ----------------------------
-- Records of cl_channel
-- ----------------------------

-- ----------------------------
-- Table structure for cl_opinion
-- ----------------------------
DROP TABLE IF EXISTS `cl_opinion`;
CREATE TABLE `cl_opinion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `opinion` varchar(160) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '意见',
  `sys_user_id` bigint(20) DEFAULT NULL COMMENT '管理员标识',
  `feedback` varchar(500) DEFAULT '' COMMENT '反馈',
  `state` varchar(2) DEFAULT '' COMMENT '状态 10待确认，20已确认',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `confirm_time` datetime DEFAULT NULL COMMENT '确认时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='意见反馈表';

-- ----------------------------
-- Records of cl_opinion
-- ----------------------------

-- ----------------------------
-- Table structure for cl_pay_check
-- ----------------------------
DROP TABLE IF EXISTS `cl_pay_check`;
CREATE TABLE `cl_pay_check` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `order_no` varchar(64) NOT NULL DEFAULT '' COMMENT '订单号',
  `order_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '订单金额',
  `real_pay_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '实际支付金额(第三方交易金额)',
  `type` varchar(2) NOT NULL DEFAULT '' COMMENT '错误类型 10:金额不匹配   20:我方单边账 30:支付方单边',
  `state` varchar(2) NOT NULL DEFAULT '' COMMENT '交易状态 10:成功 20:退款',
  `process_result` varchar(2) DEFAULT '' COMMENT '处理结果 10:待处理 20:已处理',
  `process_way` varchar(2) DEFAULT '' COMMENT '处理方式 10不处理 20补录 30补扣 40 退还',
  `process_time` datetime DEFAULT NULL COMMENT '处理时间',
  `remark` varchar(255) DEFAULT '' COMMENT '备注说明',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付对账记录表';

-- ----------------------------
-- Records of cl_pay_check
-- ----------------------------

-- ----------------------------
-- Table structure for cl_pay_log
-- ----------------------------
DROP TABLE IF EXISTS `cl_pay_log`;
CREATE TABLE `cl_pay_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` varchar(64) DEFAULT '' COMMENT '请求订单标识',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `borrow_id` bigint(20) DEFAULT NULL COMMENT '借款标识',
  `amount` decimal(10,2) NOT NULL DEFAULT '0' COMMENT '支付金额',
  `card_no` varchar(20) DEFAULT '' COMMENT '用户银行卡卡号',
  `bank` varchar(30) DEFAULT '' COMMENT '用户银行卡开户行',
  `confirm_code` varchar(10) DEFAULT '' COMMENT '确认码，实时付款确认交易使用',
  `source` varchar(2) DEFAULT '' COMMENT '资金来源 10:自有资金 20:其他资金',
  `type` varchar(2) DEFAULT '' COMMENT '支付方式 10:代付 20:代扣 30:线下代付  40:线下代扣',
  `scenes` varchar(2) DEFAULT '' COMMENT '业务场景  10、放款  11、分润 12、退还 20、还款 21、补扣',
  `state` varchar(2) DEFAULT '' COMMENT '支付状态   10:待支付 、15:待审核 、20:审核通过、 30:审核不通过 、40:支付成功、50:支付失败',
  `code` varchar(10) DEFAULT '' COMMENT '支付方响应码',
  `remark` varchar(128) DEFAULT '' COMMENT '备注',
  `pay_req_time` datetime DEFAULT NULL COMMENT '支付请求时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付记录表';

-- ----------------------------
-- Records of cl_pay_log
-- ----------------------------

-- ----------------------------
-- Table structure for cl_pay_req_log
-- ----------------------------
DROP TABLE IF EXISTS `cl_pay_req_log`;
CREATE TABLE `cl_pay_req_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `service` varchar(30) DEFAULT '' COMMENT '第三方接口名称',
  `order_no` varchar(64) DEFAULT '' COMMENT '商户订单编号',
  `params` mediumtext COMMENT '请求参数',
  `req_detail_params` mediumtext COMMENT '请求tpp参数拼接',
  `return_params` mediumtext COMMENT '页面返回/同步回调参数',
  `return_time` datetime DEFAULT NULL COMMENT '页面返回/同步回调时间',
  `notify_params` mediumtext COMMENT '后台通知/异步回调参数',
  `notify_time` datetime DEFAULT NULL COMMENT '后台通知/异步回调时间',
  `result` int(11) DEFAULT '0' COMMENT '响应结果  1成功，-1失败',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `ip` varchar(64) DEFAULT '' COMMENT '请求IP',
  PRIMARY KEY (`id`),
  UNIQUE KEY `reqLog_orderNo` (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付请求记录表';

-- ----------------------------
-- Records of cl_pay_req_log
-- ----------------------------

-- ----------------------------
-- Table structure for cl_pay_resp_log
-- ----------------------------
DROP TABLE IF EXISTS `cl_pay_resp_log`;
CREATE TABLE `cl_pay_resp_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` varchar(64) DEFAULT '' COMMENT '商户订单编号',
  `type` tinyint(1) DEFAULT '0' COMMENT '通知类型   1、TPP同步返回  2、Tpp异步响应',
  `params` text COMMENT 'Tpp通知信息',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`),
  KEY `respLog_orderNo` (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付响应通知记录表';

-- ----------------------------
-- Records of cl_pay_resp_log
-- ----------------------------

-- ----------------------------
-- Table structure for cl_profit_agent
-- ----------------------------
DROP TABLE IF EXISTS `cl_profit_agent`;
CREATE TABLE `cl_profit_agent` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `level` int(11) NOT NULL DEFAULT '0' COMMENT '分润等级',
  `user_id` bigint(20) NOT NULL COMMENT '代理商id（用户id）',
  `rate` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '分润率',
  `create_time` datetime DEFAULT NULL COMMENT '成为代理商时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `leader_id` bigint(20) DEFAULT NULL COMMENT '上级代理id',
  `apply_time` datetime DEFAULT NULL COMMENT '二级代理商升级为一级代理商时间',
  `old_rate` decimal(20,2) DEFAULT '0' COMMENT '二级代理商时的利润率',
  `is_use` int(11) DEFAULT '20' COMMENT '10-启用 20-禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代理商用户信息表';

-- ----------------------------
-- Records of cl_profit_agent
-- ----------------------------

-- ----------------------------
-- Table structure for cl_profit_amount
-- ----------------------------
DROP TABLE IF EXISTS `cl_profit_amount`;
CREATE TABLE `cl_profit_amount` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `total` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '总金额',
  `no_cashed` decimal(12,2) DEFAULT '0.00' COMMENT '未提现',
  `cashed` decimal(12,2) DEFAULT '0.00' COMMENT '已提现',
  `state` varchar(2) DEFAULT '' COMMENT '状态 10正常 20冻结',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分润资金表';

-- ----------------------------
-- Records of cl_profit_amount
-- ----------------------------

-- ----------------------------
-- Table structure for cl_profit_cash_log
-- ----------------------------
DROP TABLE IF EXISTS `cl_profit_cash_log`;
CREATE TABLE `cl_profit_cash_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `amount` decimal(12,2) DEFAULT '0' COMMENT '提现金额',
  `add_time` datetime DEFAULT NULL COMMENT '提现时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分润提现记录表';

-- ----------------------------
-- Records of cl_profit_cash_log
-- ----------------------------

-- ----------------------------
-- Table structure for cl_profit_log
-- ----------------------------
DROP TABLE IF EXISTS `cl_profit_log`;
CREATE TABLE `cl_profit_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `borrow_id` bigint(20) DEFAULT NULL COMMENT '借款id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `agent_id` bigint(20) DEFAULT NULL COMMENT '分润人id',
  `amount` decimal(12,2) DEFAULT '0' COMMENT '分润金额',
  `rate` decimal(12,2) DEFAULT '0' COMMENT '分润率',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分润记录表';

-- ----------------------------
-- Records of cl_profit_log
-- ----------------------------

-- ----------------------------
-- Table structure for cl_qiancheng_req_log
-- ----------------------------
DROP TABLE IF EXISTS `cl_qiancheng_req_log`;
CREATE TABLE `cl_qiancheng_req_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` varchar(64) DEFAULT '' COMMENT '申请订单号',
  `borrow_id` bigint(20) DEFAULT NULL COMMENT '借款标识',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `state` varchar(4) DEFAULT '10' COMMENT '审核状态  10 已提交申请   20 审核通过  30 审核不通过',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `resp_code` varchar(10) DEFAULT '' COMMENT '回调返回码',
  `update_time` datetime DEFAULT NULL COMMENT '回调更新时间',
  `resp_params` varchar(1024) DEFAULT '' COMMENT '同步响应结果',
  `notify_params` varchar(2048) DEFAULT '' COMMENT '异步通知结果',
  `resp_time` datetime DEFAULT NULL COMMENT '同步响应时间',
  `resp_order_no` varchar(64) DEFAULT '' COMMENT '同步响应订单号',
  `rs_state` varchar(32) DEFAULT '' COMMENT '审核结果',
  `rs_desc` varchar(512) DEFAULT '' COMMENT '审核结果描述',
  `req_params` mediumtext COMMENT '请求参数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='浅橙请求记录表';

-- ----------------------------
-- Records of cl_qiancheng_req_log
-- ----------------------------

-- ----------------------------
-- Table structure for cl_quartz_info
-- ----------------------------
DROP TABLE IF EXISTS `cl_quartz_info`;
CREATE TABLE `cl_quartz_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) DEFAULT '' COMMENT '定时任务名称',
  `code` varchar(64) DEFAULT '' COMMENT '定时任务code标识',
  `cycle` varchar(32) DEFAULT '' COMMENT '定时任务执行周期',
  `class_name` varchar(64) DEFAULT '' COMMENT '定时任务执行类',
  `succeed` int(11) DEFAULT '0' COMMENT '成功执行次数',
  `fail` int(11) DEFAULT '0' COMMENT '失败执行次数',
  `state` varchar(2) DEFAULT '' COMMENT '是否启用 10启用 20禁用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务信息表';

-- ----------------------------
-- Records of cl_quartz_info
-- ----------------------------
INSERT INTO `cl_quartz_info` VALUES ('1', '代扣还款', 'doRepayment', '0 0 1,20 * * ?', 'com.xiji.cashloan.manage.job.QuartzRepayment', '12', '2', '10', '2017-03-21 18:50:45');
INSERT INTO `cl_quartz_info` VALUES ('2', '逾期计算', 'doLate', '0 0 2 * * ?', 'com.xiji.cashloan.manage.job.QuartzLate', '2007', '0', '10', '2017-03-15 16:22:04');
INSERT INTO `cl_quartz_info` VALUES ('3', '发放奖励', 'doProfit', '0 0 3 10 * ?', 'com.xiji.cashloan.manage.job.QuartzProfit', '0', '0', '20', '2017-03-27 14:53:27');

-- ----------------------------
-- Table structure for cl_quartz_log
-- ----------------------------
DROP TABLE IF EXISTS `cl_quartz_log`;
CREATE TABLE `cl_quartz_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `quartz_id` bigint(20) DEFAULT NULL COMMENT '定时任务id',
  `start_time` datetime DEFAULT NULL COMMENT '启动时间',
  `time` int(11) DEFAULT '0' COMMENT '任务用时',
  `result` varchar(2) DEFAULT '' COMMENT '执行结果 10成功 20失败',
  `remark` varchar(128) DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`id`),
  KEY `quartz_id` (`quartz_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务执行记录表';

-- ----------------------------
-- Records of cl_quartz_log
-- ----------------------------

-- ----------------------------
-- Table structure for `cl_rc_simple_borrow_count`
-- ----------------------------
DROP TABLE IF EXISTS `cl_rc_simple_borrow_count`;
CREATE TABLE `cl_rc_simple_borrow_count` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `count_one` int(11) DEFAULT '0' COMMENT '借款人逾期30天以上已还借款数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
--   ,`count_two` int(11) DEFAULT '0' COMMENT '借款人有逾期未还借款数',
--   `count_three` int(11) DEFAULT '0' COMMENT '借款人有逾期已还借款数',
--   `count_four` int(11) DEFAULT '0' COMMENT '借款人正常还款数'
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='风控数据-（简）借款统计';

-- ----------------------------
-- Records of cl_rc_simple_borrow_count
-- ----------------------------

-- ----------------------------
-- Table structure for `cl_rc_simple_contact_count`
-- ----------------------------
DROP TABLE IF EXISTS `cl_rc_simple_contact_count`;
CREATE TABLE `cl_rc_simple_contact_count` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `count_one` int(11) DEFAULT '0' COMMENT '通讯录总条数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='风控数据-（简）通讯录统计';

-- ----------------------------
-- Records of cl_rc_simple_contact_count
-- ----------------------------

-- ----------------------------
-- Table structure for `cl_rc_simple_voices_count`
-- ----------------------------
DROP TABLE IF EXISTS `cl_rc_simple_voices_count`;
CREATE TABLE `cl_rc_simple_voices_count` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `count_one` int(11) DEFAULT '0' COMMENT '通话记录总次数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='风控数据-（简）通话记录统计';

-- ----------------------------
-- Records of cl_rc_simple_voices_count
-- ----------------------------

-- ----------------------------
-- Table structure for cl_rc_scene_business
-- ----------------------------
DROP TABLE IF EXISTS `cl_rc_scene_business`;
CREATE TABLE `cl_rc_scene_business` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `scene` varchar(32) DEFAULT '' COMMENT '场景',
  `business_id` int(11) DEFAULT NULL COMMENT '第三方征信接口主键',
  `get_way` varchar(2) DEFAULT '' COMMENT '获取方式 00获取一次   10每次获取    20固定周期获取（单位为天）',
  `period` int(11) DEFAULT '0' COMMENT '周期  单位为天 当get_way为20时有效',
  `state` varchar(2) DEFAULT '' COMMENT '状态 10，启用；20，禁用',
  `type` varchar(255) DEFAULT '' COMMENT '类型  10 第三方接口  20 数据统计接口',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='场景与接口关系';
-- ----------------------------
-- Records of cl_rc_scene_business
-- ----------------------------
INSERT INTO `cl_rc_scene_business` VALUES ('1', '10', '1', '10', 0, '10', '10', '2018-12-06 00:00:00');
INSERT INTO `cl_rc_scene_business` VALUES ('2', '10', '2', '10', 0, '10', '10', '2018-12-06 00:00:00');
INSERT INTO `cl_rc_scene_business` VALUES ('3', '10', '3', '10', 0, '10', '10', '2018-12-06 00:00:00');
INSERT INTO `cl_rc_scene_business` VALUES ('4', '10', '4', '10', 0, '10', '10', '2018-12-06 00:00:00');


-- ----------------------------
-- Table structure for cl_rc_scene_business_log
-- ----------------------------
DROP TABLE IF EXISTS `cl_rc_scene_business_log`;
CREATE TABLE `cl_rc_scene_business_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `scene_id` bigint(20) DEFAULT NULL COMMENT '场景关联ID',
  `borrow_id` bigint(20) DEFAULT NULL COMMENT '借款申请Id',
  `tpp_id` bigint(20) DEFAULT NULL COMMENT '第三方主键ID',
  `business_id` bigint(20) DEFAULT NULL COMMENT '接口Id',
  `nid` varchar(32) DEFAULT '' COMMENT '接口简称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `type` varchar(2) DEFAULT '' COMMENT '类型  10 第三方接口 20 数据统计接口',
  `rs_state` varchar(255) DEFAULT '' COMMENT '返回状态',
  `rs_desc` varchar(255) DEFAULT '' COMMENT '返回结果描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='第三方征信接口信息';

-- ----------------------------
-- Records of cl_rc_scene_business_log
-- ----------------------------

-- ----------------------------
-- Table structure for cl_rc_statistics
-- ----------------------------
DROP TABLE IF EXISTS `cl_rc_statistics`;
CREATE TABLE `cl_rc_statistics` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) DEFAULT '' COMMENT '第三方名称',
  `nid` varchar(32) DEFAULT '' COMMENT '第三方简称',
  `extend` varchar(1024) DEFAULT '' COMMENT '第三方参数扩展字段',
  `state` varchar(10) DEFAULT '' COMMENT '状态  10启用  20禁用',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据统计分类';

-- ----------------------------
-- Records of cl_rc_statistics
-- ----------------------------

-- ----------------------------
-- Table structure for cl_rc_statistics_business
-- ----------------------------
DROP TABLE IF EXISTS `cl_rc_statistics_business`;
CREATE TABLE `cl_rc_statistics_business` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `statistics_id` bigint(20) DEFAULT NULL COMMENT '第三方主键',
  `name` varchar(32) DEFAULT '' COMMENT '接口名称',
  `nid` varchar(32) DEFAULT '' COMMENT '接口简称',
  `state` varchar(10) DEFAULT '' COMMENT '状态  10启用  20禁用',
  `extend` varchar(1024) DEFAULT '',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据统计分类接口';

-- ----------------------------
-- Records of cl_rc_statistics_business
-- ----------------------------

-- ----------------------------
-- Table structure for cl_rc_tpp
-- ----------------------------
DROP TABLE IF EXISTS `cl_rc_tpp`;
CREATE TABLE `cl_rc_tpp` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) DEFAULT '' COMMENT '第三方名称',
  `nid` varchar(32) DEFAULT '' COMMENT '第三方简称',
  `mer_no` varchar(128) DEFAULT '' COMMENT '商户号',
  `sign_type` varchar(8) DEFAULT '' COMMENT '加密方式',
  `key` varchar(2048) DEFAULT '' COMMENT '加密所需要的key',
  `extend` varchar(1024) DEFAULT '' COMMENT '第三方参数扩展字段',
  `state` varchar(2) DEFAULT '' COMMENT '状态 10，启用；20，禁用',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='第三方征信信息';

-- ----------------------------
-- Records of cl_rc_tpp
-- ----------------------------
INSERT INTO `cl_rc_tpp` VALUES ('1', '魔杖2.0', 'magic', '', '', '', '', '10', '2018-12-06 00:00:00');
INSERT INTO `cl_rc_tpp` VALUES ('2', '新颜小额网贷报告', 'xinyan', '', '', '', '', '10', '2018-12-06 00:00:00');

-- ----------------------------
-- Table structure for cl_rc_tpp_business
-- ----------------------------
DROP TABLE IF EXISTS `cl_rc_tpp_business`;
CREATE TABLE `cl_rc_tpp_business` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tpp_id` bigint(20) DEFAULT NULL COMMENT '第三方主键',
  `name` varchar(32) DEFAULT '' COMMENT '接口名称',
  `nid` varchar(32) DEFAULT '' COMMENT '接口简称',
  `state` varchar(2) DEFAULT '' COMMENT '状态 10启用，20禁用',
  `extend` varchar(1024) DEFAULT '' COMMENT '扩展字段',
  `url` varchar(256) DEFAULT '' COMMENT '接口请求地址',
  `test_url` varchar(256) DEFAULT '' COMMENT '测试地址',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='第三方征信接口信息';

-- ----------------------------
-- Records of cl_rc_tpp_business
-- ----------------------------
INSERT INTO `cl_rc_tpp_business` VALUES ('1', '1', '魔杖申请准入', 'MagicApply', '10', '', '', null, '2018-12-06 00:00:00');
INSERT INTO `cl_rc_tpp_business` VALUES ('2', '1', '魔杖反欺诈', 'MagicAntiFraud', '10', '', '', null, '2018-12-06 00:00:00');
INSERT INTO `cl_rc_tpp_business` VALUES ('3', '1', '魔杖贷后行为', 'MagicPostLoad', '10', '', '', null, '2018-12-06 00:00:00');
INSERT INTO `cl_rc_tpp_business` VALUES ('4', '2', '新颜小额网贷报告', 'XinyanLoan', '10', '', '', null, '2018-12-06 00:00:00');

-- ----------------------------
-- Table structure for cl_sms
-- ----------------------------
DROP TABLE IF EXISTS `cl_sms`;
CREATE TABLE `cl_sms` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `phone` varchar(11) DEFAULT '' COMMENT '手机号码',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `content` varchar(200) DEFAULT '' COMMENT '发送信息',
  `resp_time` datetime DEFAULT NULL COMMENT '响应时间',
  `resp` varchar(255) DEFAULT '' COMMENT '响应信息',
  `sms_type` varchar(30) DEFAULT '' COMMENT '短信类型',
  `code` varchar(6) DEFAULT '' COMMENT '验证码',
  `order_no` varchar(32) DEFAULT '' COMMENT '订单号',
  `state` varchar(2) DEFAULT '' COMMENT '状态  10发送成功未使用，20发送成功已使用，30发送中,40发送失败',
  `verify_time` int(11) DEFAULT '0' COMMENT '短信验证次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='短信发送记录表';

-- ----------------------------
-- Records of cl_sms
-- ----------------------------

-- ----------------------------
-- Table structure for cl_sms_tpl
-- ----------------------------
DROP TABLE IF EXISTS `cl_sms_tpl`;
CREATE TABLE `cl_sms_tpl` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` varchar(30) DEFAULT '' COMMENT '短信类型',
  `type_name` varchar(50) DEFAULT '' COMMENT '类型名称',
  `tpl` varchar(255) DEFAULT '' COMMENT '短信模板',
  `number` varchar(64) DEFAULT '' COMMENT '模板编号',
  `state` varchar(2) DEFAULT '' COMMENT '短信模板状态 10启用 20禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='短信模板表';

-- ----------------------------
-- Records of cl_sms_tpl
-- ----------------------------
INSERT INTO `cl_sms_tpl` VALUES ('1', 'register', '注册验证码', '尊敬的用户，您的注册验证码为:', 'SMS1774090067', '10');
INSERT INTO `cl_sms_tpl` VALUES ('2', 'findReg', '找回登录密码', '尊敬的用户，您的密码找回验证码为:', 'SMS1999911248', '10');
INSERT INTO `cl_sms_tpl` VALUES ('3', 'findPay', '找回交易密码', '尊敬的用户，您的交易密码找回验证码为:', 'SMS0157552381', '10');
INSERT INTO `cl_sms_tpl` VALUES ('4', 'loanInform', '放款通知', '尊敬的用户,您在{$time}的借款申请已放款成功,请查看您的收款银行卡!', 'SMS1525274518', '10');
INSERT INTO `cl_sms_tpl` VALUES ('5', 'repayInform', '还款通知', '尊敬的用户,您在{$time}借款{$loan}元,现已执行系统代扣还款成功,请知悉!', 'SMS1829478820', '10');
INSERT INTO `cl_sms_tpl` VALUES ('6', 'delayPlan', '展期通知', '[{$appName}]您的订单已展期成功，新的截止日期{$year}年{$month}月{$day}日', 'SMS0271048174', '10');

-- ----------------------------
-- Table structure for cl_tongdun_req_log
-- ----------------------------
DROP TABLE IF EXISTS `cl_tongdun_req_log`;
CREATE TABLE `cl_tongdun_req_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` varchar(64) DEFAULT '' COMMENT '申请订单号',
  `borrow_id` bigint(20) DEFAULT NULL COMMENT '借款标识',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `state` varchar(4) DEFAULT '10' COMMENT '审核状态  10 已提交申请   20 审核通过  30 审核不通过',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `submit_code` varchar(10) DEFAULT '' COMMENT '获取审核报告返回码',
  `submit_params` mediumtext COMMENT '获取审核报告响应结果',
  `report_id` varchar(64) DEFAULT '' COMMENT '风险报告编码',
  `update_time` datetime DEFAULT NULL COMMENT '查询报告时间',
  -- `query_params` mediumtext COMMENT '查询报告响应结果',
  `query_code` varchar(16) DEFAULT '' COMMENT '查询报告返回码',
  `rs_state` varchar(16) DEFAULT '' COMMENT '风控结果    Accept:建议通过,Review:建议审核,Reject:建议拒绝',
  `rs_score` int(11) DEFAULT '0' COMMENT '风控分数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='同盾贷前审核请求记录表';

-- ----------------------------
-- Records of cl_tongdun_req_log
-- ----------------------------

-- ----------------------------
-- Table structure for cl_tongdun_resp_detail
-- ----------------------------
DROP TABLE IF EXISTS `cl_tongdun_resp_detail`;
CREATE TABLE `cl_tongdun_resp_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `req_id` bigint(20) DEFAULT NULL COMMENT '同盾请求id',
  `order_no` varchar(64) DEFAULT '' COMMENT '同盾申请记录订单号',
  `report_id` varchar(64) DEFAULT '' COMMENT '同盾审核报告id',
  `query_params` mediumtext COMMENT '审核报告具体信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='同盾贷前审核响应通知详情表';

-- ----------------------------
-- Table structure for cl_urge_repay_log
-- ----------------------------
DROP TABLE IF EXISTS `cl_urge_repay_log`;
CREATE TABLE `cl_urge_repay_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `due_id` bigint(20) DEFAULT NULL COMMENT '催收订单id',
  `borrow_id` bigint(20) DEFAULT NULL COMMENT '借款id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '催款人id',
  `state` varchar(2) DEFAULT '' COMMENT '状态   20催收中 30承诺还款',
  `remark` varchar(500) DEFAULT '' COMMENT '备注说明',
  `promise_time` datetime DEFAULT NULL COMMENT '承诺还款时间',
  `create_time` datetime DEFAULT NULL COMMENT '催收时间',
  `way` varchar(2) DEFAULT '' COMMENT '催款方式   10电话，20邮件，30短信，40现场沟通，50其他',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='催款记录表';

-- ----------------------------
-- Records of cl_urge_repay_log
-- ----------------------------

-- ----------------------------
-- Table structure for cl_urge_repay_order
-- ----------------------------
DROP TABLE IF EXISTS `cl_urge_repay_order`;
CREATE TABLE `cl_urge_repay_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '催收人员id',
  `user_name` varchar(20) DEFAULT '' COMMENT '催款人姓名',
  `borrow_name` varchar(20) DEFAULT '' COMMENT '借款人姓名',
  `phone` varchar(20) DEFAULT '' COMMENT '借款人手机号',
  `borrow_id` bigint(20) DEFAULT NULL COMMENT '借款id',
  `order_no` varchar(30) DEFAULT '' COMMENT '借款订单号',
  `amount` decimal(12,2) DEFAULT '0' COMMENT '借款金额',
  `time_limit` varchar(10) DEFAULT '' COMMENT '借款期限',
  `borrow_time` datetime DEFAULT NULL COMMENT '借款时间',
  `repay_time` datetime DEFAULT NULL COMMENT '应还款时间',
  `penalty_day` int(11) DEFAULT '0' COMMENT '逾期天数',
  `penalty_amout` decimal(12,2) DEFAULT '0' COMMENT '逾期罚金',
  `state` varchar(2) DEFAULT '10' COMMENT '订单状态   10未分配，11待催收，20催收中，30承诺还款，40催收成功，50坏账',
  `count` int(11) DEFAULT '0' COMMENT '催款总次数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `success_time` datetime DEFAULT NULL COMMENT '成功时间',
  `level` varchar(2) DEFAULT '' COMMENT '逾期等级  M1 M2 M3',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='催款订单表';

-- ----------------------------
-- Records of cl_urge_repay_order
-- ----------------------------

-- ----------------------------
-- Table structure for cl_user
-- ----------------------------
DROP TABLE IF EXISTS `cl_user`;
CREATE TABLE `cl_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(40) DEFAULT '' COMMENT 'UUID',
  `login_name` varchar(30) DEFAULT '' COMMENT '登录名',
  `login_pwd` varchar(50) DEFAULT '' COMMENT '登录密码',
  `loginpwd_modify_time` datetime DEFAULT NULL COMMENT '上次登录密码修改时间',
  `regist_time` datetime DEFAULT NULL COMMENT '注册时间',
  `register_client` varchar(10) DEFAULT '' COMMENT '注册客户端',
  `trade_pwd` varchar(50) DEFAULT '' COMMENT '交易密码',
  `tradepwd_modify_time` datetime DEFAULT NULL COMMENT '上次交易密码修改时间',
  `invitation_code` varchar(10) binary DEFAULT '' COMMENT '邀请码',
  `channel_id` bigint(20) DEFAULT NULL COMMENT '渠道',
  `level` varchar(2) DEFAULT '' COMMENT '代理等级   1一级，2二级，3普通用户',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of cl_user
-- ----------------------------

-- ----------------------------
-- Table structure for cl_user_auth
-- ----------------------------
DROP TABLE IF EXISTS `cl_user_auth`;
CREATE TABLE `cl_user_auth` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `id_state` varchar(2) DEFAULT '' COMMENT '身份认证状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善',
  `id_time` datetime DEFAULT NULL COMMENT '身份认证时间',
  `contact_state` varchar(2) DEFAULT '' COMMENT '紧急联系人状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善',
  `contact_time` datetime DEFAULT NULL COMMENT '紧急联系人认证时间',
  `bank_card_state` varchar(2) DEFAULT '' COMMENT '银行卡状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善',
  `bank_card_time` datetime DEFAULT NULL COMMENT '银行卡认证时间',
  `phone_state` varchar(2) DEFAULT '' COMMENT '手机运营商认证状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善',
  `phone_time` datetime DEFAULT NULL COMMENT '运营商认证时间',
  `zhima_state` varchar(2) DEFAULT '' COMMENT '芝麻授信状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善',
  `zhima_time` datetime DEFAULT NULL COMMENT '芝麻认证时间',
  `work_info_state` varchar(2) DEFAULT '' COMMENT '工作信息状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善',
  `work_info_time` datetime DEFAULT NULL COMMENT '工作信息认证时间',
  `other_info_state` varchar(2) DEFAULT '' COMMENT '更多信息状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善',
  `other_info_time` datetime DEFAULT NULL COMMENT '其他信息认证时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户认证状态表';

-- ----------------------------
-- Records of cl_user_auth
-- ----------------------------

-- ----------------------------
-- Table structure for cl_user_base_info
-- ----------------------------
DROP TABLE IF EXISTS `cl_user_base_info`;
CREATE TABLE `cl_user_base_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '客户表 外键',
  `phone` varchar(11) DEFAULT '' COMMENT '手机号码',
  `real_name` varchar(30) DEFAULT '' COMMENT '真实姓名',
  `age` int(11) DEFAULT '0' COMMENT '年龄 ',
  `sex` varchar(30) DEFAULT '' COMMENT '性别',
  `national` varchar(30) DEFAULT '' COMMENT '民族',
  `id_no` varchar(18) DEFAULT NULL COMMENT '证件号码',
  `id_addr` varchar(255) DEFAULT '' COMMENT '身份证地址',
  `living_img` varchar(255) DEFAULT '' COMMENT '自拍(人脸识别照片)',
  `ocr_img` varchar(255) DEFAULT '' COMMENT '身份证头像',
  `front_img` varchar(255) DEFAULT '' COMMENT '身份证正面',
  `back_img` varchar(255) DEFAULT '' COMMENT '身份证反面',
  `education` varchar(30) DEFAULT '' COMMENT '学历',
  `marital` varchar(20) DEFAULT '' COMMENT '婚姻状况',
  `company_name` varchar(50) DEFAULT '' COMMENT '公司名称',
  `company_phone` varchar(32) DEFAULT '' COMMENT '公司电话',
  `company_addr` varchar(255) DEFAULT '' COMMENT '公司地址',
  `company_detail_addr` varchar(255) DEFAULT '' COMMENT '公司详细地址',
  `company_coordinate` varchar(100) DEFAULT '' COMMENT '公司坐标(经度,纬度)',
  `salary` varchar(30) DEFAULT '' COMMENT '月薪范围',
  `working_years` varchar(30) DEFAULT '' COMMENT '工作年限',
  `working_img` varchar(512) DEFAULT '' COMMENT '工作照片',
  `live_time` varchar(30) DEFAULT '' COMMENT '居住时长',
  `live_addr` varchar(255) DEFAULT '' COMMENT '居住地址',
  `live_detail_addr` varchar(255) DEFAULT '' COMMENT '居住详细地址',
  `live_coordinate` varchar(100) DEFAULT '' COMMENT '居住地坐标，(经度,纬度)',
  `phone_server_pwd` varchar(30) DEFAULT '' COMMENT '运营商服务密码',
  `register_addr` varchar(255) DEFAULT '' COMMENT '注册地址',
  `register_coordinate` varchar(100) DEFAULT '' COMMENT '注册地坐标，(经度,纬度)',
  `state` varchar(30) DEFAULT '0' COMMENT '名单状态   0正常  10黑名单  20白名单',
  `black_reason` varchar(255) DEFAULT '' COMMENT '拉黑原因',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户详情表';

-- ----------------------------
-- Records of cl_user_base_info
-- ----------------------------

-- ----------------------------
-- Table structure for cl_user_black_info
-- ----------------------------
DROP TABLE IF EXISTS `cl_user_black_info`;
CREATE TABLE `cl_user_black_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `real_name` varchar(30) DEFAULT '' COMMENT '真实姓名',
  `id_no` varchar(18) DEFAULT '' COMMENT '身份证号码',
  `phone` varchar(11) NOT NULL DEFAULT '' COMMENT '手机号码',
  `type` varchar(2) DEFAULT '' COMMENT '类型 10黑名单 20白名单',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='黑/白名单信息';
-- ----------------------------
-- Records of cl_user_black_info
-- ----------------------------

-- ----------------------------
-- Table structure for cl_user_card_credit_log
-- ----------------------------
DROP TABLE IF EXISTS `cl_user_card_credit_log`;
CREATE TABLE `cl_user_card_credit_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `req_params` mediumtext COMMENT '请求参数',
  `return_params` mediumtext COMMENT '响应参数',
  `confidence` varchar(30) DEFAULT '' COMMENT '人脸匹配值',
  `result` varchar(10) DEFAULT '' COMMENT '结果',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人脸识别请求记录表';

-- ----------------------------
-- Records of cl_user_card_credit_log
-- ----------------------------

-- ----------------------------
-- Table structure for cl_user_contacts
-- ----------------------------
DROP TABLE IF EXISTS `cl_user_contacts`;
CREATE TABLE `cl_user_contacts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `name` varchar(20) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '姓名',
  `phone` varchar(20) DEFAULT '' COMMENT '手机号码',
  `total_count` INT(11) DEFAULT 0 COMMENT '通话次数',
  `sum_duration` INT(11) DEFAULT 0 COMMENT '通话总时长',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户通讯录表';

-- ----------------------------
-- Records of cl_user_contacts
-- ----------------------------

-- ----------------------------
-- Table structure for cl_user_education_info
-- ----------------------------
DROP TABLE IF EXISTS `cl_user_education_info`;
CREATE TABLE `cl_user_education_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `education_type` varchar(32) DEFAULT '' COMMENT '教育类别',
  `profession` varchar(32) DEFAULT '' COMMENT '专业',
  `graduate_school` varchar(32) DEFAULT '' COMMENT '毕业学校',
  `matriculation_time` varchar(32) DEFAULT '' COMMENT '入学时间',
  `graduation_time` varchar(32) DEFAULT '' COMMENT '毕业时间',
  `graduation_conclusion` varchar(32) DEFAULT '' COMMENT '教育情况',
  `education_background` varchar(32) DEFAULT '' COMMENT '学位',
  `state` varchar(2) DEFAULT '' COMMENT '是否匹配    10匹配  20不匹配',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学信查询记录表';

-- ----------------------------
-- Records of cl_user_education_info
-- ----------------------------

-- ----------------------------
-- Table structure for cl_user_emer_contacts
-- ----------------------------
DROP TABLE IF EXISTS `cl_user_emer_contacts`;
CREATE TABLE `cl_user_emer_contacts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) DEFAULT '' COMMENT '联系人',
  `phone` varchar(20) DEFAULT '' COMMENT '联系号码',
  `user_id` bigint(20) DEFAULT NULL COMMENT '客户表 外键',
  `relation` varchar(30) DEFAULT '' COMMENT '与本人关系',
  `type` varchar(20) DEFAULT '' COMMENT '是否直系',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='紧急联系人表';

-- ----------------------------
-- Records of cl_user_emer_contacts
-- ----------------------------

-- ----------------------------
-- Table structure for cl_user_equipment_info
-- ----------------------------
DROP TABLE IF EXISTS `cl_user_equipment_info`;
CREATE TABLE `cl_user_equipment_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `black_box` text COMMENT '设备指纹',
  `operating_system` varchar(20) DEFAULT '' COMMENT '操作系统',
  `system_versions` varchar(20) DEFAULT '' COMMENT '系统版本',
  `phone_type` varchar(20) DEFAULT '' COMMENT '手机型号',
  `phone_brand` varchar(50) DEFAULT '' COMMENT '手机品牌',
  `phone_mark` varchar(100) DEFAULT '' COMMENT '手机设备标识',
  `mac` varchar(100) DEFAULT '' COMMENT 'mac',
  `version_name` varchar(20) DEFAULT '' COMMENT '应用版本号',
  `version_code` varchar(20) DEFAULT '' COMMENT '应用build号',
  `app_install_time` datetime DEFAULT NULL COMMENT 'APP安装时间',
  `app_market` varchar(20) DEFAULT '' COMMENT 'APP应用市场',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户设备信息表';

-- ----------------------------
-- Records of cl_user_equipment_info
-- ----------------------------

-- ----------------------------
-- Table structure for cl_user_invite
-- ----------------------------
DROP TABLE IF EXISTS `cl_user_invite`;
CREATE TABLE `cl_user_invite` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `invite_time` datetime DEFAULT NULL COMMENT '邀请时间',
  `invite_id` bigint(20) DEFAULT NULL COMMENT '被邀请人id',
  `invite_name` varchar(32) DEFAULT '' COMMENT '被邀请人名称',
  `user_id` bigint(20) DEFAULT NULL COMMENT '邀请人id',
  `user_name` varchar(32) DEFAULT '' COMMENT '邀请人名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邀请记录表';

-- ----------------------------
-- Records of cl_user_invite
-- ----------------------------

-- ----------------------------
-- Table structure for cl_user_messages
-- ----------------------------
DROP TABLE IF EXISTS `cl_user_messages`;
CREATE TABLE `cl_user_messages` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `name` varchar(20) DEFAULT '' COMMENT '短信收发人',
  `phone` varchar(20) DEFAULT '' COMMENT '手机号码',
  `time` datetime DEFAULT NULL COMMENT '收发时间',
  `type` varchar(2) DEFAULT '' COMMENT '收发标识，10发20收',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8utf8mb4 COMMENT='用户短信表';

-- ----------------------------
-- Records of cl_user_messages
-- ----------------------------

-- ----------------------------
-- Table structure for cl_user_other_info
-- ----------------------------
DROP TABLE IF EXISTS `cl_user_other_info`;
CREATE TABLE `cl_user_other_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识(关联客户主键)',
  `taobao` varchar(32) DEFAULT '' COMMENT '淘宝账号',
  `email` varchar(32) DEFAULT '' COMMENT '常用邮箱',
  `qq` varchar(16) DEFAULT '' COMMENT 'qq账号',
  `wechat` varchar(32) DEFAULT '' COMMENT '微信账号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户其他信息表';

-- ----------------------------
-- Records of cl_user_other_info
-- ----------------------------

-- ----------------------------
-- Table structure for cl_user_sdk_log
-- ----------------------------
DROP TABLE IF EXISTS `cl_user_sdk_log`;
CREATE TABLE `cl_user_sdk_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `time_type` varchar(20) DEFAULT '' COMMENT '识别类型',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='sdk识别记录表';

-- ----------------------------
-- Records of cl_user_sdk_log
-- ----------------------------

-- ----------------------------
-- Table structure for cl_zhima
-- ----------------------------
DROP TABLE IF EXISTS `cl_zhima`;
CREATE TABLE `cl_zhima` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `open_id` varchar(100) DEFAULT '' COMMENT '用户在芝麻信用里面的唯一标识',
  `score` decimal(12,2) DEFAULT '0' COMMENT '分数',
  `bind` varchar(2) DEFAULT '' COMMENT '是否已绑定 10 否 20 是',
  `bind_time` datetime DEFAULT NULL COMMENT '绑定时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='芝麻信用';

-- ----------------------------
-- Records of cl_zhima
-- ----------------------------

-- ----------------------------
-- Table structure for cl_channel_app
-- ----------------------------
DROP TABLE IF EXISTS `cl_channel_app`;
CREATE TABLE `cl_channel_app` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `channel_id` bigint(20) NOT NULL COMMENT '渠道id',
  `app_type` varchar(16) DEFAULT '' COMMENT '应用类型',
  `latest_version` varchar(16) DEFAULT '' COMMENT '最新版本',
  `min_version` varchar(16) DEFAULT '' COMMENT '最低支持版本',
  `download_url` varchar(64) DEFAULT '' COMMENT '下载地址',
  `state` varchar(2) DEFAULT '' COMMENT '状态 10启用，20禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='app版本信息';

-- ----------------------------
-- Records of cl_channel_app
-- ----------------------------
 
-- 公积金
DROP TABLE IF EXISTS `cl_accfund_info`;
CREATE TABLE `cl_accfund_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account_no` varchar(50) DEFAULT '' COMMENT '用户账号',
  `amount` decimal(12,2) DEFAULT '0' COMMENT '账户金额(分)',
  `biz_no` varchar(50) DEFAULT '' COMMENT '业务号',
  `company` varchar(200) DEFAULT '' COMMENT '公司信息',
  `deposit_status` varchar(50) DEFAULT '' COMMENT '缴纳状态',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `house_accumulation_fund_id` varchar(200) DEFAULT '' COMMENT '公积金 ID',
  `id_card` varchar(50) DEFAULT '' COMMENT '用户身份证号',
  `name` varchar(50) DEFAULT '' COMMENT '姓名',
  `region` varchar(200) DEFAULT '' COMMENT '公积金所在地',
  `user_id` bigint(20) DEFAULT NULL COMMENT '客户表 外键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公积金基本信息表';

DROP TABLE IF EXISTS `cl_accfund_log`;
CREATE TABLE `cl_accfund_log` (
  `id` bigint(20) NOT NULL,
  `account_no` varchar(50) DEFAULT '' COMMENT '用户账号',
  `amount` decimal(12,2) DEFAULT '0' COMMENT '账户金额(分)',
  `amount_balance` decimal(12,2) DEFAULT '0',
  `biz_no` varchar(50) DEFAULT '' COMMENT '业务号',
  `biz_time` datetime DEFAULT NULL COMMENT '业务发生时间',
  `digest` varchar(50) DEFAULT '' COMMENT '摘要',
  `fund_flow` varchar(50) DEFAULT '' COMMENT '资金流向（流入、流出）INCOME/EXPENSE',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `house_accumulation_fund_id` varchar(200) DEFAULT '' COMMENT '公积金 ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '客户表 外键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公积金详细信息表(流水)';

-- 贷后邦反欺诈信息
DROP TABLE IF EXISTS `cl_rc_dhb_binding`;
CREATE TABLE `cl_rc_dhb_binding` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` varchar(64) DEFAULT '' COMMENT '订单号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `binding_idcards_size` int(11) DEFAULT '0' COMMENT '可疑绑定身份证个数',
  `binding_idcards_detail` mediumtext COMMENT '可疑绑定身份证具体信息',
  `binding_phones_size` int(11) DEFAULT '0' COMMENT '可疑绑定号码个数',
  `binding_phones_detail` mediumtext COMMENT '可疑绑定号码具体信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='贷后邦可疑绑定信息';

DROP TABLE IF EXISTS `cl_rc_dhb_history_org`;
CREATE TABLE `cl_rc_dhb_history_org` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` varchar(64) DEFAULT '' COMMENT '订单号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `online_installment_cnt` int(11) DEFAULT '0' COMMENT '线上消费分期出现次数',
  `offline_installment_cnt` int(11) DEFAULT '0' COMMENT '线下消费分期出现次数',
  `credit_card_repayment_cnt` int(11) DEFAULT '0' COMMENT '信用卡代还出现次数',
  `payday_loan_cnt` int(11) DEFAULT '0' COMMENT '小额快速贷出现次数',
  `online_cash_loan_cnt` int(11) DEFAULT '0' COMMENT '线上现金贷出现次数',
  `offline_cash_loan_cnt` int(11) DEFAULT '0' COMMENT '线下现金贷出现次数',
  `others_cnt` int(11) DEFAULT '0' COMMENT '其他',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='贷后邦历史机构类型';

DROP TABLE IF EXISTS `cl_rc_dhb_history_search`;
CREATE TABLE `cl_rc_dhb_history_search` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` varchar(64) DEFAULT '' COMMENT '订单号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `search_cnt` int(11) DEFAULT NULL COMMENT '历史查询总次数',
  `search_cnt_recent_7_days` int(11) DEFAULT '0' COMMENT '最近7天查询次数',
  `search_cnt_recent_14_days` int(11) DEFAULT '0' COMMENT '最近14天查询次数',
  `search_cnt_recent_30_days` int(11) DEFAULT '0' COMMENT '最近30天查询次数',
  `search_cnt_recent_60_days` int(11) DEFAULT '0' COMMENT '最近60天查询次数',
  `search_cnt_recent_90_days` int(11) DEFAULT '0' COMMENT '最近90天查询次数',
  `search_cnt_recent_180_days` int(11) DEFAULT '0' COMMENT '最近180天查询次数',
  `org_cnt` int(11) DEFAULT '0' COMMENT '历史查询总机构数',
  `org_cnt_recent_7_days` int(11) DEFAULT '0' COMMENT '最近7天查询机构数',
  `org_cnt_recent_14_days` int(11) DEFAULT '0' COMMENT '最近14天查询机构数',
  `org_cnt_recent_30_days` int(11) DEFAULT '0' COMMENT '最近30天查询机构数',
  `org_cnt_recent_60_days` int(11) DEFAULT '0' COMMENT '最近60天查询机构数',
  `org_cnt_recent_90_days` int(11) DEFAULT '0' COMMENT '最近90天查询机构数',
  `org_cnt_recent_180_days` int(11) DEFAULT '0' COMMENT '最近180天查询机构数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='贷后邦历史查询';

DROP TABLE IF EXISTS `cl_rc_dhb_req_log`;
CREATE TABLE `cl_rc_dhb_req_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` varchar(64) DEFAULT NULL COMMENT '申请订单号',
  `borrow_id` bigint(20) DEFAULT NULL COMMENT '借款标识',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `resp_code` varchar(10) DEFAULT '' COMMENT '回调返回码',
  `resp_params` mediumtext COMMENT '同步响应结果',
  `resp_time` datetime DEFAULT NULL COMMENT '同步响应时间',
  `resp_order_no` varchar(64) DEFAULT '' COMMENT '同步响应订单号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='贷后邦贷后邦反欺诈请求记录表';

DROP TABLE IF EXISTS `cl_rc_dhb_risk_blacklist`;
CREATE TABLE `cl_rc_dhb_risk_blacklist` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` varchar(64) DEFAULT NULL COMMENT '订单号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `idcard_in_blacklist` varchar(10) DEFAULT '' COMMENT '身份证是否命中黑名单',
  `phone_in_blacklist` varchar(10) DEFAULT '' COMMENT '手机号是否命中黑名单',
  `in_court_blacklist` varchar(10) DEFAULT '' COMMENT '是否命中法院黑名单',
  `in_p2p_blacklist` varchar(10) DEFAULT '' COMMENT '是否命中网贷黑名单',
  `in_bank_blacklist` varchar(10) DEFAULT '' COMMENT '是否命中银行黑名单',
  `last_appear_idcard_in_blacklist` varchar(32) DEFAULT '' COMMENT '最近该身份证出现在黑名单中时间',
  `last_appear_phone_in_blacklist` varchar(32) DEFAULT '' COMMENT '最近该手机号出现在黑名单中时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='贷后邦黑名单';

DROP TABLE IF EXISTS `cl_rc_dhb_risk_social_network`;
CREATE TABLE `cl_rc_dhb_risk_social_network` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` varchar(64) DEFAULT NULL COMMENT '订单号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `sn_score` int(11) DEFAULT '0' COMMENT '葫芦分',
  `sn_order1_contacts_cnt` int(11) DEFAULT '0' COMMENT '直接联系人',
  `sn_order1_blacklist_contacts_cnt` int(11) DEFAULT '0' COMMENT '直接联系人在黑名单中数量(直接黑人)',
  `sn_order2_blacklist_contacts_cnt` int(11) DEFAULT '0' COMMENT '间接联系人在黑名单中数量(间接黑人)',
  `sn_order2_blacklist_routers_cnt` int(11) DEFAULT '0' COMMENT '认识间接黑人的直接联系人个数',
  `sn_order2_blacklist_routers_pct` double(12,2) DEFAULT '0.00' COMMENT '认识间接黑人的直接联系人比例',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='贷后邦社交风险点';

DROP TABLE IF EXISTS `cl_rc_dhb_user_basic`;
CREATE TABLE `cl_rc_dhb_user_basic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` varchar(64) DEFAULT '' COMMENT '订单号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `age` int(11) DEFAULT '0' COMMENT '年龄',
  `gender` varchar(2) DEFAULT '' COMMENT '性别',
  `birthday` varchar(32) DEFAULT '' COMMENT '生日日期',
  `idcard_validate` int(11) DEFAULT '0' COMMENT '身份证是否是有效身份证   1通过 0未通过',
  `idcard_province` varchar(32) DEFAULT '' COMMENT '身份证户籍省份',
  `idcard_city` varchar(32) DEFAULT '' COMMENT '身份证户籍城市',
  `idcard_region` varchar(32) DEFAULT '' COMMENT '身份证户籍地区',
  `phone_operator` varchar(32) DEFAULT '' COMMENT '手机运营商',
  `phone_province` varchar(32) DEFAULT '' COMMENT '手机归属地省份',
  `phone_city` varchar(32) DEFAULT '' COMMENT '手机归属地城市',
  `record_idcard_days` int(11) DEFAULT '0' COMMENT '身份证号记录天数',
  `record_phone_days` int(11) DEFAULT '0' COMMENT '手机号记录天数',
  `last_appear_idcard` varchar(32) DEFAULT '' COMMENT '身份证最近出现时间',
  `last_appear_phone` varchar(32) DEFAULT '' COMMENT '手机号最近出现时间',
  `used_idcards_cnt` int(11) DEFAULT '0' COMMENT '关联身份证数量',
  `used_phones_cnt` int(11) DEFAULT '0' COMMENT '关联手机号数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='贷后邦用户基本信息表';

DROP TABLE IF EXISTS `cl_rc_fireeyes_black_log`;
CREATE TABLE `cl_rc_fireeyes_black_log` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `phone` varchar(11) DEFAULT '' COMMENT '用户手机号',
  `user_name` varchar(20) DEFAULT '' COMMENT '姓名',
  `user_card` varchar(20) DEFAULT '' COMMENT '身份证号',
  `order_no` VARCHAR(64) DEFAULT '' COMMENT '订单编号',
  `is_black` varchar(64) DEFAULT '' COMMENT '同步响应返回是否黑名单内容',
  `resp_code` varchar(10) DEFAULT '' COMMENT '响应码',
  `resp_params` longtext COMMENT '同步响应结果',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='火眼黑名单记录表';

DROP TABLE IF EXISTS `cl_four_elements_log`;
CREATE TABLE `cl_four_elements_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `user_name` varchar(20) DEFAULT NULL COMMENT '用户姓名',
  `id_card` varchar(20) DEFAULT '' COMMENT '身份证号',
  `phone` varchar(11) DEFAULT '' COMMENT '手机号',
  `card_no` varchar(30) DEFAULT '' COMMENT '银行卡号',
  `code` varchar(3) DEFAULT '' COMMENT '返回码 200 为请求成功，并获取相关数据，400 为某些原因导请求失败，如参数格式错误、无相关数据等，500 为服务器内部错误，401 为无权限请求。',
  `check_status` varchar(50) DEFAULT '' COMMENT '验证状态   SAME 验证通过, DIFFERENT 验证不通过, ACCOUNTNO_UNABLE_VERITY 无法验证',
  `result` varchar(30) DEFAULT '' COMMENT '结果',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='四要素认证记录';



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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='风控数据--91征信数据';

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='风控数据--浅橙征信数据请求记录';

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


DROP TABLE IF EXISTS `cl_calls_outside_fee`;
CREATE TABLE `cl_calls_outside_fee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户标识',
  `task_id` varchar(64) NOT NULL COMMENT '任务id',
  `type` TINYINT(4) NOT NULL COMMENT '调用类型 1-运营商 2-魔杖反欺诈 3-魔杖申请准入 5-魔杖贷后行为',
  `fee` decimal(5,2) NOT NULL COMMENT '收取费用',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='调用外部收据收费表';
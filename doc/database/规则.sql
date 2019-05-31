TRUNCATE TABLE arc_rule_engine_config;
TRUNCATE TABLE arc_rule_info;
TRUNCATE TABLE arc_rule_engine;
TRUNCATE TABLE arc_borrow_rule_engine;
TRUNCATE TABLE arc_borrow_rule_config;
insert into `arc_rule_info`(`id`,`tb_nid`,`tb_name`,`detail`,`add_time`,`req_ext`,`state`)
values(1,'cl_decision','决策数据','[{"name":"通讯录联系人数量","nid":"contact_num","type":"int"},{"name":"年龄","nid":"age","type":"int"},{"name":"通讯录联系人数量","nid":"contact_num","type":"int"},{"name":"通讯录命中敏感性词汇(借、贷、催收)5个以上","nid":"contact_sensitive_num","type":"int"},{"name":"身份证是否命中法院黑名单","nid":"mx_is_name_and_idcard_in_court_black","type":"int"},{"name":"身份证是否命中金融机构黑名单","nid":"mx_is_name_and_idcard_in_finance_black","type":"int"},{"name":"手机号是否命中金融机构黑名单","nid":"mx_is_name_and_mobile_in_finance_black","type":"int"},{"name":"查询过该用户相关企业数量","nid":"mx_searched_org_cnt","type":"int"},{"name":"身份证号码组合过的其他号码数量","nid":"mx_idcard_with_other_phone_num","type":"int"},{"name":"180天内无通话记录天数","nid":"mx_phone_no_voice_days","type":"int"},{"name":"连续近5个月(当月不算在内)平均主叫次数20次以下并且连续近5个月(当月不算在内)平均通话时长(主叫+被叫)70分钟以下","nid":"mx_five_month_voice_situation","type":"int"},{"name":"连续近5个月(当月不算在内)话费消费低于20元的次数","nid":"mx_five_month_bill_less_than_20_num","type":"int"},{"name":"通讯录与通话记录中匹配两个以下号码","nid":"mx_contact_matching_voice_situation","type":"int"},{"name":"手机号未实名认证(或认证的用户姓名与当前用户不匹配)","nid":"mx_is_reliability","type":"int"},{"name":"用户号码联系黑中介分数(0-100,分数越低风险越高，40分以下为高危人群)","nid":"mx_phone_gray_score","type":"int"},{"name":"逾期历史数量","nid":"yx_overdue_history_count","type":"int"},{"name":"历史逾期M3+(大于90天)数量","nid":"yx_overdue_history_m3_count","type":"int"},{"name":"历史逾期M6+(大于180天)数量","nid":"yx_overdue_history_m6_count","type":"int"},{"name":"申请借款数量大于20且放款数量为0,0-否","nid":"yx_AM20_no_accept","type":"int"},{"name":"法院执行人次数","nid":"mx_courtcase_cnt","type":"int"},{"name":"法院未执行次数","nid":"mx_dishonest_cnt","type":"int"},{"name":"身份证存疑姓名数","nid":"mx_idcard_other_names_cnt","type":"int"},{"name":"身份证存疑手机号码数","nid":"mx_idcard_other_mobiles_cnt","type":"int"},{"name":"身份证存疑触黑手机号码数","nid":"mx_idcard_other_mobiles_black_cnt","type":"int"},{"name":"手机存疑姓名数","nid":"mx_mobile_other_names_cnt","type":"int"},{"name":"手机存疑身份证数","nid":"mx_mobile_other_idcards_cnt","type":"int"},{"name":"手机存疑触黑身份证数","nid":"mx_mobile_other_idcards_black_cnt","type":"int"},{"name":"使用过的设备数","nid":"mx_other_devices_cnt","type":"int"},{"name":"手机号码使用过的设备数","nid":"mx_mobile_other_devices_cnt","type":"int"},{"name":"身份证号码使用过的设备数","nid":"mx_idcard_other_devices_cnt","type":"int"},{"name":"使用过的设备上登陆的其他姓名数","nid":"mx_device_other_names_cnt","type":"int"},{"name":"使用过的设备上登陆的其他手机号码数","nid":"mx_device_other_mobiles_cnt","type":"int"},{"name":"使用过的设备上登陆的其他触黑手机号码数","nid":"mx_device_other_mobiles_black_cnt","type":"int"},{"name":"使用过的设备上登陆的其他身份证号码数","nid":"mx_device_other_idcards_cnt","type":"int"},{"name":"使用过的设备上登陆的其他触黑身份证号码数","nid":"mx_device_other_idcards_black_cnt","type":"int"},{"name":"手机和姓名是否在黑名单","nid":"mx_black_mobile_name_in_blacklist","type":"int"},{"name":"身份证和姓名是否在黑名单","nid":"mx_black_idcard_name_in_blacklist","type":"int"},{"name":"是否命中欺诈风险名单","nid":"mx_fraud_is_hit","type":"int"},{"name":"贷款行为分","nid":"xy_loans_score","type":"string"},{"name":"贷款行为置信度","nid":"xy_loans_credibility","type":"string"},{"name":"历史贷款机构成功扣款数-失败数","nid":"xy_history_suc_minus_fail_num","type":"int"},{"name":"近1个月贷款机构成功扣款数-失败数","nid":"xy_latest_one_month_suc_minus_fail_num","type":"int"},{"name":"贷款逾期订单数（M0+)","nid":"xy_loans_overdue_count","type":"string"},{"name":"近1个月贷款机构失败扣款笔数","nid":"xy_latest_one_month_fail","type":"string"},{"name":"历史贷款机构成功扣款数-失败数","nid":"xy_history_suc_minus_fail_num","type":"int"},{"name":"近1个月贷款机构成功扣款数-失败数","nid":"xy_latest_one_month_suc_minus_fail_num","type":"int"},{"name":"当前处于逾期状态的借款笔数","nid":"xd_current_overdue_count","type":"int"},{"name":"当前逾期总金额，0:","nid":"xd_current_overdue_amount","type":"int"},{"name":"当前最长逾期时间(不包括已经还清的)，M1:小于1月;","nid":"xd_longest_overdue_unpaid","type":"string"},{"name":"当前逾期总金额，0:","nid":"xd_current_overdue_amount","type":"int"}]','2019-01-23 10:33:13',null,'10');

insert into `arc_borrow_rule_engine`(`id`,`borrow_type_name`,`borrow_type`,`adapted_name`,`adapted_id`,`add_time`,`req_ext`,`rule_count`)
values(1,'现金贷','01','贷前','10','2019-01-23 12:00:10','',12);

insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(1,1,'cl_decision','决策数据','contact_num','通讯录联系人数量','<','50','10',null,'0:0:0:0:0:0:0:1','2019-01-23 11:02:17','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(2,1,'cl_decision','决策数据','age','年龄','>','48','10',null,'0:0:0:0:0:0:0:1','2019-01-23 11:02:17','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(3,1,'cl_decision','决策数据','age','年龄','<','20','10',null,'0:0:0:0:0:0:0:1','2019-01-23 11:02:17','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(4,1,'cl_decision','决策数据','mx_is_name_and_idcard_in_court_black','身份证是否命中法院黑名单','=','1','10',null,'0:0:0:0:0:0:0:1','2019-01-23 11:02:17','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(5,1,'cl_decision','决策数据','mx_is_name_and_idcard_in_finance_black','身份证是否命中金融机构黑名单','=','1','10',null,'0:0:0:0:0:0:0:1','2019-01-23 11:02:17','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(6,1,'cl_decision','决策数据','mx_is_name_and_mobile_in_finance_black','手机号是否命中金融机构黑名单','=','1','10',null,'0:0:0:0:0:0:0:1','2019-01-23 11:02:17','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(7,1,'cl_decision','决策数据','mx_searched_org_cnt','查询过该用户相关企业数量','>=','140','10',null,'0:0:0:0:0:0:0:1','2019-01-23 11:02:17','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(8,1,'cl_decision','决策数据','mx_idcard_with_other_phone_num','身份证号码组合过的其他号码数量','>','4','10',null,'0:0:0:0:0:0:0:1','2019-01-23 11:27:23','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(9,1,'cl_decision','决策数据','mx_phone_no_voice_days','180天内无通话记录天数','>=','70','10',null,'0:0:0:0:0:0:0:1','2019-01-23 11:27:23','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(11,1,'cl_decision','决策数据','mx_five_month_bill_less_than_20_num','连续近5个月(当月不算在内)话费消费低于20元的次数','>=','4','10',null,'0:0:0:0:0:0:0:1','2019-01-23 11:27:23','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(12,1,'cl_decision','决策数据','mx_contact_matching_voice_situation','通讯录与通话记录中匹配四个以下号码','=','1','10',null,'0:0:0:0:0:0:0:1','2019-01-23 11:27:23','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(13,1,'cl_decision','决策数据','mx_is_reliability','手机号未实名认证(或认证的用户姓名与当前用户不匹配)','=','0','10',null,'0:0:0:0:0:0:0:1','2019-01-23 11:27:23','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(15,1,'cl_decision','决策数据','yx_overdue_history_m3_count','历史逾期M3+(大于90天)数量','>=','1','10',null,'0:0:0:0:0:0:0:1','2019-01-23 11:27:23','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(16,1,'cl_decision','决策数据','yx_overdue_history_m6_count','历史逾期M6+(大于180天)数量','>=','1','10',null,'0:0:0:0:0:0:0:1','2019-01-23 11:27:23','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(17,1,'cl_decision','决策数据','yx_AM20_no_accept','申请借款数量大于20且放款数量为0,0-否','=','1','10',null,'0:0:0:0:0:0:0:1','2019-01-23 11:27:23','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(18,1,'cl_decision','决策数据','mx_courtcase_cnt','法院执行人次数','>=','1','10',null,'0:0:0:0:0:0:0:1','2019-01-23 11:27:23','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(19,1,'cl_decision','决策数据','mx_dishonest_cnt','法院未执行次数','>=','1','10',null,'0:0:0:0:0:0:0:1','2019-01-23 11:27:23','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(20,1,'cl_decision','决策数据','mx_idcard_other_names_cnt','身份证存疑姓名数','>','3','10',null,'0:0:0:0:0:0:0:1','2019-01-23 11:27:23','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(21,1,'cl_decision','决策数据','mx_idcard_other_mobiles_cnt','身份证存疑手机号码数','>','3','10',null,'0:0:0:0:0:0:0:1','2019-01-23 11:27:23','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(22,1,'cl_decision','决策数据','mx_idcard_other_mobiles_black_cnt','身份证存疑触黑手机号码数','>','0','10',null,'0:0:0:0:0:0:0:1','2019-01-23 11:27:23','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(23,1,'cl_decision','决策数据','mx_mobile_other_names_cnt','手机存疑姓名数','>','3','10',null,'0:0:0:0:0:0:0:1','2019-01-23 11:27:23','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(24,1,'cl_decision','决策数据','mx_mobile_other_idcards_cnt','手机存疑身份证数','>','3','10',null,'0:0:0:0:0:0:0:1','2019-01-23 11:27:23','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(25,1,'cl_decision','决策数据','mx_mobile_other_idcards_black_cnt','手机存疑触黑身份证数','>','3','10',null,'0:0:0:0:0:0:0:1','2019-01-23 11:27:23','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(26,1,'cl_decision','决策数据','mx_device_other_names_cnt','使用过的设备上登陆的其他姓名数','>=','5','10',null,'0:0:0:0:0:0:0:1','2019-01-23 11:27:23','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(27,1,'cl_decision','决策数据','mx_device_other_mobiles_cnt','使用过的设备上登陆的其他手机号码数','>=','5','10',null,'0:0:0:0:0:0:0:1','2019-01-23 11:27:23','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(28,1,'cl_decision','决策数据','mx_device_other_mobiles_black_cnt','使用过的设备上登陆的其他触黑手机号码数','>','0','10',null,'0:0:0:0:0:0:0:1','2019-01-23 11:27:23','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(29,1,'cl_decision','决策数据','mx_device_other_idcards_cnt','使用过的设备上登陆的其他身份证号码数','>=','5','10',null,'0:0:0:0:0:0:0:1','2019-01-23 11:27:23','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(30,1,'cl_decision','决策数据','mx_device_other_idcards_black_cnt','使用过的设备上登陆的其他触黑身份证号码数','>','0','10',null,'0:0:0:0:0:0:0:1','2019-01-23 11:27:23','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(31,1,'cl_decision','决策数据','mx_black_mobile_name_in_blacklist','手机和姓名是否在黑名单','=','1','10',null,'0:0:0:0:0:0:0:1','2019-01-23 11:27:23','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(32,1,'cl_decision','决策数据','mx_black_idcard_name_in_blacklist','身份证和姓名是否在黑名单','=','1','10',null,'0:0:0:0:0:0:0:1','2019-01-23 11:27:23','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(34,1,'cl_decision','决策数据','xy_loans_score','贷款行为分','<','450','10',null,'0:0:0:0:0:0:0:1','2019-01-23 11:27:23','string',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(35,1,'cl_decision','决策数据','xy_loans_credibility','贷款行为置信度','<','60','10',null,'0:0:0:0:0:0:0:1','2019-01-23 11:27:23','string',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(36,1,'cl_decision','决策数据','xy_loans_overdue_count','贷款逾期订单数（M0+)','>','8','10',null,'0:0:0:0:0:0:0:1','2019-01-23 11:27:23','string',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(40,1,'cl_decision','决策数据','xd_current_overdue_count','当前处于逾期状态的借款笔数','>','0','10',null,'0:0:0:0:0:0:0:1','2019-01-23 11:27:23','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(41,1,'cl_decision','决策数据','xd_longest_overdue_unpaid','当前最长逾期时间(不包括已经还清的)，M1:小于1月;','include','M','10',null,'0:0:0:0:0:0:0:1','2019-01-23 11:27:23','string',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(42,1,'cl_decision','决策数据','xd_current_overdue_amount','当前逾期总金额，0:','>','0','10',null,'0:0:0:0:0:0:0:1','2019-01-23 11:27:23','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(43,1,'cl_decision','决策数据','yx_overdue_history_count','逾期历史数量','>=','1','10',null,'115.192.184.73','2019-01-26 01:58:52','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(44,1,'cl_decision','决策数据','yx_loaning_AM3m','借贷正常N笔以上 且借贷多头近3月申请平台大于M家,0-否 1-是','=','1','10',null,'115.192.184.73','2019-05-16 11:10:52','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(45,1,'cl_decision','决策数据','yd_refused_feature','是否命中有盾拒绝风险项,0-否 1-是','=','1','10',null,'115.192.184.73','2019-05-16 11:10:52','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(46,1,'cl_decision','决策数据','yx_yd_no_loan','宜信有盾无下款,0-否 1-是','=','1','10',null,'115.192.184.73','2019-05-16 11:10:52','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(47,1,'cl_decision','决策数据','mx_voice_has_sensitive_phone','通话详情包含敏感号码,0-否 1-是','=','1','10',null,'115.192.184.73','2019-05-16 11:10:52','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(48,1,'cl_decision','决策数据','company_name','公司名称','include','房产中介','10',null,'115.192.184.73','2019-05-16 11:10:52','string',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(49,1,'cl_decision','决策数据','yd_no_loan_6m','多头近6月未下款且申请平台大于15 0-否 1-是','=','1','10',null,'115.192.184.73','2019-05-30 15:10:52','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(50,1,'cl_decision','决策数据','yd_loan_1m','多头近1月申请平台数大于等于30家 且下款平台数小于3 0-否 1-是','=','1','10',null,'115.192.184.73','2019-05-30 15:10:52','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(51,1,'cl_decision','决策数据','mx_message_num','近一月短信和被叫是上一个月的2倍及以上 0-否 1-是','=','1','10',null,'115.192.184.73','2019-05-30 15:10:52','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(52,1,'cl_decision','决策数据','xy_suc_minus_fail_num','还款行为历史和一月失败均大于成功 0-否 1-是','=','1','10',null,'115.192.184.73','2019-05-30 15:10:52','int',null,null,'10',null);
insert into `arc_rule_engine_config`(`id`,`rule_engin_id`,`ctable`,`table_comment`,`ccolumn`,`column_comment`,`formula`,`cvalue`,`state`,`req_ext`,`add_ip`,`add_time`,`type`,`integral`,`result_type`,`result`,`sort`)
values(53,1,'cl_decision','决策数据','yd_platform_loan_num','多头实际借款平台数1 还款平台1 还款笔数1 0-否 1-是','=','1','10',null,'115.192.184.73','2019-05-30 15:10:52','int',null,null,'10',null);

insert into `arc_rule_engine`(`id`,`name`,`state`,`config_count`,`req_ext`,`add_ip`,`add_time`,`integral`,`type`,`type_result_status`,`sort`)
values(1,'决策','10',37,null,'0:0:0:0:0:0:0:1','2019-01-23 11:02:17',null,'20','10',null);

insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(1,1,1,0,43,99);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(2,1,1,0,8,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(4,1,1,0,9,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(5,1,1,0,11,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(6,1,1,0,12,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(8,1,1,0,13,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(9,1,1,0,15,98);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(10,1,1,0,16,97);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(11,1,1,0,17,96);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(12,1,1,0,18,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(13,1,1,0,19,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(14,1,1,0,20,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(15,1,1,0,21,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(16,1,1,0,22,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(17,1,1,0,23,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(18,1,1,0,24,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(19,1,1,0,25,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(20,1,1,0,26,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(21,1,1,0,27,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(22,1,1,0,28,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(23,1,1,0,29,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(24,1,1,0,30,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(25,1,1,0,31,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(27,1,1,0,32,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(28,1,1,0,34,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(29,1,1,0,35,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(33,1,1,0,36,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(34,1,1,0,40,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(35,1,1,0,41,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(36,1,1,0,42,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(37,1,1,0,1,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(38,1,1,0,2,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(39,1,1,0,3,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(40,1,1,0,4,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(41,1,1,0,5,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(42,1,1,0,6,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(79,1,1,0,7,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(80,1,1,0,44,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(81,1,1,0,45,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(82,1,1,0,46,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(83,1,1,0,47,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(84,1,1,0,48,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(85,1,1,0,49,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(86,1,1,0,50,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(87,1,1,0,51,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(88,1,1,0,52,0);
insert into `arc_borrow_rule_config`(`id`,`borrow_rule_id`,`rule_id`,`rule_sort`,`config_id`,`config_sort`)
values(89,1,1,0,53,0);
-- 删除信德相关规则配置
delete from arc_borrow_rule_config where config_id in (40,41,42);
-- 删除运营商用户未实名认证条件
delete from arc_borrow_rule_config where config_id in (12);
-- 部分系统使用公信宝运营商时，删除魔杖黑灰名单规则配置
delete from arc_borrow_rule_config where config_id in (4,5,6,7,8,9,11,12,13,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32);
-- 删除菜单栏
delete from arc_sys_menu where id in (3,4,30,31,32,33,34,35,36,37,38,5,6,39,41,42,43,44,45,50,29,56,57,18,83,84,85,90,91,93,94,95,97,1001,1002,1003,1006,1014,1015,23,98);
delete from arc_sys_role_menu where menu_id in (3,4,30,31,32,33,34,35,36,37,38,5,6,39,41,42,43,44,45,50,29,56,57,18,83,84,85,90,91,93,94,95,97,1001,1002,1003,1006,1014,1015,23,98);
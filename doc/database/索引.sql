ALTER TABLE `arc_borrow_rule_result`
ADD INDEX `borrow_id` (`borrow_id`) USING BTREE ;

ALTER TABLE `arc_cr_factor_param`
ADD INDEX `factor_id` (`factor_id`) USING BTREE ;

ALTER TABLE `arc_cr_result`
ADD INDEX `consumer_no` (`consumer_no`) USING BTREE ;

ALTER TABLE `arc_cr_result_detail`
ADD INDEX `result_id` (`result_id`) USING BTREE ;

ALTER TABLE `arc_credit`
ADD UNIQUE INDEX `consumer_no` (`consumer_no`) USING BTREE ;

ALTER TABLE `arc_credit_log`
ADD INDEX `consumer_no` (`consumer_no`) USING BTREE ;

ALTER TABLE `cl_bank_card`
ADD INDEX `user_id` (`user_id`) USING BTREE ;

ALTER TABLE `cl_borrow`
ADD INDEX `user_id` (`user_id`) USING BTREE ,
ADD UNIQUE INDEX `userid_createtime` (`user_id`,`create_time`),
ADD UNIQUE INDEX `order_no` (`order_no`) USING BTREE ;

ALTER TABLE `cl_borrow_progress`
ADD INDEX `borrow_id` (`borrow_id`) USING BTREE ;

ALTER TABLE `cl_borrow_repay`
ADD INDEX `borrow_id` (`borrow_id`) USING BTREE ;

ALTER TABLE `cl_borrow_repay_log`
ADD INDEX `repay_id` (`repay_id`) USING BTREE ,
ADD INDEX `borrow_id` (`borrow_id`) USING BTREE ;

ALTER TABLE `cl_four_elements_log` 
ADD INDEX `user_id` (`user_id`) USING BTREE;

ALTER TABLE `cl_opinion` 
ADD INDEX `user_id` (`user_id`) USING BTREE;

ALTER TABLE `cl_pay_check`
ADD UNIQUE INDEX `order_no` (`order_no`) USING BTREE ;

ALTER TABLE `cl_pay_log`
ADD UNIQUE INDEX `order_no` (`order_no`) USING BTREE ;

ALTER TABLE `cl_profit_agent`
ADD INDEX `user_id` (`user_id`) USING BTREE ;

ALTER TABLE `cl_profit_amount`
ADD UNIQUE INDEX `user_id` (`user_id`) USING BTREE ;

ALTER TABLE `cl_profit_cash_log`
ADD INDEX `user_id` (`user_id`) USING BTREE ;

ALTER TABLE `cl_profit_log`
ADD INDEX `user_id` (`user_id`) USING BTREE ,
ADD INDEX `borrow_id` (`borrow_id`) USING BTREE ;

ALTER TABLE `cl_qiancheng_req_log`
ADD INDEX `borrow_id` (`borrow_id`) USING BTREE ,
ADD INDEX `user_id` (`user_id`) USING BTREE ,
ADD INDEX `order_no` (`order_no`) USING BTREE ;

ALTER TABLE `cl_rc_scene_business_log` 
ADD INDEX `borrow_id` (`borrow_id`) USING BTREE ,
ADD INDEX `nid` (`nid`) USING BTREE ;

ALTER TABLE `cl_sms` 
ADD INDEX `phone` (`phone`) USING BTREE;

ALTER TABLE `cl_tongdun_req_log`
ADD INDEX `user_id` (`user_id`) USING BTREE ,
ADD UNIQUE INDEX `order_no` (`order_no`) USING BTREE ;

ALTER TABLE `cl_user`
ADD UNIQUE INDEX `login_name` (`login_name`) USING BTREE,
ADD UNIQUE INDEX `invitation_code` (`invitation_code`) USING BTREE;

ALTER TABLE `cl_user_auth`
ADD UNIQUE INDEX `user_id` (`user_id`) USING BTREE;

ALTER TABLE `cl_user_base_info`
ADD UNIQUE INDEX `user_id` (`user_id`) USING BTREE,
ADD UNIQUE INDEX `phone` (`phone`) USING BTREE,
ADD UNIQUE INDEX `id_no` (`id_no`) USING BTREE;

ALTER TABLE `cl_user_emer_contacts`
ADD INDEX `user_id` (`user_id`) USING BTREE;

ALTER TABLE `cl_urge_repay_log`
ADD INDEX `borrow_id` (`borrow_id`) USING BTREE ,
ADD INDEX `user_id` (`user_id`) USING BTREE ;

ALTER TABLE `cl_urge_repay_order`
ADD INDEX `user_id` (`user_id`) USING BTREE ,
ADD INDEX `borrow_id` (`borrow_id`) USING BTREE ;

ALTER TABLE `cl_user_card_credit_log`
ADD INDEX `user_id` (`user_id`) USING BTREE ;

ALTER TABLE `cl_user_contacts`
ADD INDEX `user_id` (`user_id`) USING BTREE ;

ALTER TABLE `cl_user_equipment_info`
ADD UNIQUE INDEX `user_id` (`user_id`) USING BTREE ;

ALTER TABLE `cl_user_messages`
ADD INDEX `user_id` (`user_id`) USING BTREE ;

ALTER TABLE `cl_user_other_info`
ADD UNIQUE INDEX `user_id` (`user_id`) USING BTREE ;

ALTER TABLE `cl_user_invite`
ADD INDEX `user_id` (`user_id`) USING BTREE ;

ALTER TABLE `cl_zhima`
ADD UNIQUE INDEX `user_id` (`user_id`) USING BTREE ;

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

ALTER TABLE `cl_operator_resp_detail`
ADD INDEX `task_id` (`task_id`) USING BTREE ;

ALTER TABLE `cl_operator_report`
ADD INDEX `user_id` (`user_id`) USING BTREE ;
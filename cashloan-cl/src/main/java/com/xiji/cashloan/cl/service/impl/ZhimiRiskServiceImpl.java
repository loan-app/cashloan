package com.xiji.cashloan.cl.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiji.cashloan.cl.domain.OperatorReqLog;
import com.xiji.cashloan.cl.domain.UserEmerContacts;
import com.xiji.cashloan.cl.mapper.UserEmerContactsMapper;
import com.xiji.cashloan.cl.service.ZhimiRiskService;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.domain.Borrow;
import com.xiji.cashloan.core.domain.User;
import com.xiji.cashloan.core.domain.UserBaseInfo;
import com.xiji.cashloan.core.mapper.UserBaseInfoMapper;
import com.xiji.cashloan.core.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by szb on 19/5/5.
 */
public class ZhimiRiskServiceImpl implements ZhimiRiskService {
    public static final Logger logger = LoggerFactory.getLogger(ZhimiRiskServiceImpl.class);

    @Resource
    private UserBaseInfoMapper userBaseInfoMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserEmerContactsMapper userEmerContactsMapper;

    @Override
    public int getScore(Borrow borrow, boolean isAgain) {
        int i = 0;
        UserBaseInfo userBaseinfo = userBaseInfoMapper.findByUserId(borrow.getUserId());
        if (userBaseinfo == null) {
            logger.error("查询用户userId：" + userBaseinfo.getUserId() + ",用户不存在");
            return i;
        }
        User user = userMapper.findByPrimary(borrow.getUserId());
        String vkey = Global.getValue("zhimi_vkey");
        String API_HOST = Global.getValue("zhimi_model_url");
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("model_name", "");
        paramMap.put("first_loan", isAgain ? 1 : 0);
        paramMap.put("product", "");
        paramMap.put("channel", user.getChannelId());
        paramMap.put("apply_time", borrow.getCreateTime());
        paramMap.put("mobile", userBaseinfo.getPhone());
        paramMap.put("name", userBaseinfo.getRealName());
        paramMap.put("idcard", userBaseinfo.getIdNo());

        //紧急联系人
        JSONArray eContacts = new JSONArray();
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("userId", borrow.getUserId());
        List<UserEmerContacts> userEmerContactses = userEmerContactsMapper.listSelective(queryMap);
        if(userEmerContactses == null || userEmerContactses.size() != 2) {
            logger.error("用户userId：" + userBaseinfo.getUserId() + "紧急联系人未完善");
            return i;
        }
        for (UserEmerContacts userEmerContact : userEmerContactses) {
            JSONObject eContact = new JSONObject();
            eContact.put("contact_name", userEmerContact.getName());
            eContact.put("contact_phone", userEmerContact.getPhone());
            eContacts.add(eContact);
        }
        paramMap.put("e_contacts", eContacts);
        Global.getValue("")
        if() {

        }


        //查询用户通话详单
        OperatorReqLog operatorReqLog = operatorReqLogMapper.queryLastReqLog(userId);

        return 0;
    }
}

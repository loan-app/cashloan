package com.xiji.cashloan.api.controller;

import com.xiji.cashloan.cl.domain.Channel;
import com.xiji.cashloan.cl.service.ChannelService;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.util.ServletUtils;
import com.xiji.cashloan.core.common.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Scope("prototype")
@Controller

public class ChannelsController extends BaseController{
    @Autowired
    private ChannelService channelService;

    /**
     * 二级限流
     * @author wangqi
     * @date 2019/6/17
     * @param code
     * @return contidion (Type:String code:1限流qq 2限流微信 3限流微博 4限流其他 0不做二级限流)
     * */
    @RequestMapping(value="/api/channels/condition.htm")
    public void findConditionById(@RequestParam(value = "code") String code){
        String byId = channelService.findConditionById(code);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constant.RESPONSE_DATA, byId);
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
         ServletUtils.writeToResponse(response,result);
    }

    /**
     * 渠道uv统计
     */
    @RequestMapping(value = "/api/channels/getUvCount.htm", method = {RequestMethod.POST,RequestMethod.GET})
    public void getUvCount(
            @RequestParam(value="code") String code)throws Exception {
        //根据渠道编码查询渠道信息
        Channel code2 = channelService.getChannelByCode(code);
        int uvCount = code2.getUvCount();

        if( uvCount == 0 ){
            /* 第一次访问 */
            uvCount = 1;
        }else{
            uvCount += 1;
        }
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("uvCount", uvCount);
        paramMap.put("code", code);
        boolean flag = channelService.update(paramMap);
        Map<String, Object> result = new HashMap<String, Object>();
        if (flag) {
            result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
        } else {
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
        }
        ServletUtils.writeToResponse(response, result);

    }

}

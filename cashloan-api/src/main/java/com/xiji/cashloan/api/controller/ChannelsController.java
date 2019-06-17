package com.xiji.cashloan.api.controller;

import com.xiji.cashloan.cl.service.ChannelService;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.util.ServletUtils;
import com.xiji.cashloan.core.common.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
     * @param id （channel渠道主键）
     * @return contidion (Type:String code:1限流qq 2限流微信 3限流微博 4限流其他 0不做二级限流)
     * */
    @RequestMapping(value="/api/channels/condition.htm")
    public void findConditionById(@RequestParam(value = "id") long id){
        String byId = channelService.findConditionById(id);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constant.RESPONSE_DATA, byId);
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
         ServletUtils.writeToResponse(response,result);
    }

}

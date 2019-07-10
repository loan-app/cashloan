package com.xiji.cashloan.api.controller;

import com.xiji.cashloan.cl.domain.Channel;
import com.xiji.cashloan.cl.domain.ChannelUv;
import com.xiji.cashloan.cl.service.ChannelService;
import com.xiji.cashloan.cl.service.ChannelUvService;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.util.DateUtil;
import com.xiji.cashloan.core.common.util.ServletUtils;
import com.xiji.cashloan.core.common.util.StringUtil;
import com.xiji.cashloan.core.common.web.controller.AbstractController;
import com.xiji.cashloan.core.common.web.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Scope("prototype")
@Controller

public class ChannelsController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(ChannelsController.class);
    @Autowired
    private ChannelService channelService;

    @Autowired
    private ChannelUvService channelUvService;
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

        if (code2==null){
            logger.info("该渠道信息不存在.");
            return;
        }

        Map<String, Object> paramsMap = new HashMap<>();
        Long channelId = code2.getId();
        Date date = new Date();
        paramsMap.put("channelId",channelId);
        paramsMap.put("countDate", DateUtil.dateStr(date,"yyyy-MM-dd"));
        ChannelUv channelUv =channelUvService.findSelective(paramsMap);

        int update=0;
        int save=0;
        if (channelUv==null){
            ChannelUv uv = new ChannelUv();
            uv.setChannelId(channelId);
            uv.setCountDate(date);
            uv.setName(code2.getName());
            uv.setUvCount(1l);
            save = channelUvService.save(uv);
            logger.info("添加一条uv.");
        }else {
            Map<String, Object> map = new HashMap<>();
            map.put("id",channelUv.getId());
            map.put("uvCount",channelUv.getUvCount()+1);
            update= channelUvService.updateSelective(map);
            logger.info(code2.getName()+"渠道,uv点击量加1");
        }

        Map<String, Object> result = new HashMap<String, Object>();
        if (update>0||save >0) {
            result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
        } else {
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
        }
        ServletUtils.writeToResponse(response, result);

    }

}

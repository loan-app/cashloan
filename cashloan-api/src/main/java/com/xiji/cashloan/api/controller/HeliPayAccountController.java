package com.xiji.cashloan.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiji.cashloan.cl.domain.HelipayUser;
import com.xiji.cashloan.cl.domain.PayReqLog;
import com.xiji.cashloan.cl.domain.PayRespLog;
import com.xiji.cashloan.cl.mapper.HelipayUserMapper;
import com.xiji.cashloan.cl.model.PayRespLogModel;
import com.xiji.cashloan.cl.model.pay.common.PayCommonUtil;
import com.xiji.cashloan.cl.model.pay.helipay.util.Disguiser;
import com.xiji.cashloan.cl.model.pay.helipay.util.HelipayUtil;
import com.xiji.cashloan.cl.model.pay.helipay.util.MyBeanUtils;
import com.xiji.cashloan.cl.model.pay.helipay.vo.delegation.UserRegisterNotifyVo;
import com.xiji.cashloan.cl.model.pay.kuaiqian.util.KuaiqianPayUtil;
import com.xiji.cashloan.cl.service.HelipayUserService;
import com.xiji.cashloan.cl.service.PayReqLogService;
import com.xiji.cashloan.cl.service.PayRespLogService;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.util.JsonUtil;
import com.xiji.cashloan.core.common.util.ServletUtils;
import com.xiji.cashloan.core.common.util.StringUtil;
import com.xiji.cashloan.core.common.web.controller.BaseController;
import com.xiji.cashloan.core.domain.UserBaseInfo;
import com.xiji.cashloan.core.service.UserBaseInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import tool.util.DateUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @auther : wnb
 * @date : 2019/7/30
 * @describe :合利宝用户开户控制层
 */
@Scope("prototype")
@Controller
public class HeliPayAccountController extends BaseController {

    public static final Logger logger = LoggerFactory.getLogger(HeliPayAccountController.class);

    @Resource
    private UserBaseInfoService userBaseInfoService;

    @Resource
    private HelipayUserMapper helipayUserMapper;

    @Resource
    private PayReqLogService payReqLogService;

    @Resource
    private PayRespLogService payRespLogService;

    @Resource
    private HelipayUserService helipayUserService;

    /**
     * 合利宝商户用户注册
     */
    @RequestMapping(value = "/api/heliPay/register.htm")
    public void heliPayRegister(@RequestParam(value="userId") long userId,@RequestParam("phone") String phone){

        UserBaseInfo userBaseInfo = userBaseInfoService.findByUserId(userId);
        Map<String,Object> result = new HashMap<String,Object>();
        if (userBaseInfo == null || StringUtil.isBlank(userBaseInfo.getPhone())|| StringUtil.isBlank(userBaseInfo.getIdNo()) || StringUtil.isBlank(userBaseInfo.getRealName())){
            result.put(Constant.RESPONSE_CODE, Constant.OPERATION_FAIL);
            result.put(Constant.RESPONSE_CODE_MSG, "请先实名认证");
            ServletUtils.writeToResponse(response,result);
            return;
        }
        // 使用传入 的手机号
        userBaseInfo.setPhone(phone);
        Map<String,Object> params = new HashMap<>();
        params.put("userId",userId);
        HelipayUser helipayUser = helipayUserMapper.findSelective(params);
        if (helipayUser != null){
            Boolean flag = helipayUserService.heliPayUpload(userId,helipayUser.getId(),helipayUser.getHelipayUserId());
            if (flag){
                result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
                result.put(Constant.RESPONSE_CODE_MSG, "开户成功");
            }else {
                result.put(Constant.RESPONSE_CODE, Constant.OPERATION_FAIL);
                result.put(Constant.RESPONSE_CODE_MSG, "图片上传认证失败");
            }
            return;
        }
        helipayUserService.helipayRegister(userBaseInfo);
//        if ("success".equals(helipayRegister.get("code"))){
//            result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
//            result.put(Constant.RESPONSE_CODE_MSG, helipayRegister.get("message"));
//        }else {
//            result.put(Constant.RESPONSE_CODE, Constant.OPERATION_FAIL);
//            result.put(Constant.RESPONSE_CODE_MSG, helipayRegister.get("message"));
//        }

        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "开户成功");
        ServletUtils.writeToResponse(response,result);

    }


    /**
     * 合利宝商户用户注册回调
     * @param
     */
    @RequestMapping(value = "/api/heliPay/registerNotify.htm")
    public void registerNotify() throws Exception {

        logger.info("----------------合利宝商户用户注册回调 - 异步通知开始------------------------");
        long start = System.currentTimeMillis();

        String encryData = KuaiqianPayUtil.streamToStr(request);
        UserRegisterNotifyVo notifyVo = JSONObject.parseObject(encryData, UserRegisterNotifyVo.class);
        String params = JSON.toJSONString(notifyVo);
        logger.info("合利宝商户用户注册回调异步通知接口:" + params);


        String assemblyRespOriSign = MyBeanUtils.getSigned(notifyVo, null);
        String responseSign = notifyVo.getSign();
        String checkSign = Disguiser.disguiseMD5(assemblyRespOriSign.trim()+HelipayUtil.split+HelipayUtil.getMD5Key());

        if (!checkSign.equals(responseSign)) {
            logger.error("验签失败" + JsonUtil.toString(notifyVo));
            return;
        }

        PayReqLog payReqLog = payReqLogService.findByHelipayUserId(notifyVo.getRt5_userId());
        if (payReqLog != null) {
            // 保存respLog
            PayRespLog payRespLog = new PayRespLog(payReqLog.getOrderNo(), PayRespLogModel.RESP_LOG_TYPE_NOTIFY,params);
            payRespLogService.save(payRespLog);

            payReqLog.setNotifyParams(params);
            payReqLog.setNotifyTime(DateUtil.getNow());
            payReqLogService.updateById(payReqLog);
        }

        Map<String,Object> map = new HashMap<>();
        map.put("helipayUserId",notifyVo.getRt5_userId());
        HelipayUser helipayUser = helipayUserMapper.findSelective(map);
        if (helipayUser == null){
            logger.error("合利宝商户用户不存在，helipayUserId"+notifyVo.getRt5_userId());
            return;
        }

        map.clear();
        map.put("id",helipayUser.getId());
        map.put("gmtModified",new Date());
        map.put("userStatus",notifyVo.getRt6_userStatus());
        int count = helipayUserService.updateSelective(map);

        Map<String,String> result = new HashMap<>();
        if (count == 1){
            writeResult(response,"success");
        }
        long end = System.currentTimeMillis();
        logger.info("cost "+(end-start));
    }

    /**
     *
     * 商户用户注册状态
     * @param userId 用户id
     */
    @RequestMapping(value = "/api/heliPay/helipayUserStatus.htm")
    public void helipayUserStatus(@RequestParam(value="userId") long userId){

        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        HelipayUser helipayUser = helipayUserMapper.findSelective(map);
        if (helipayUser == null){
            helipayUser = new HelipayUser();
            helipayUser.setUserStatus("NO_EXIST");
        }
        Map<String,Object> result = new HashMap<String,Object>();
        result.put(Constant.RESPONSE_DATA, helipayUser);
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
        ServletUtils.writeToResponse(response,result);
    }





    private void writeResult(HttpServletResponse response, String result)throws Exception {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf8");
        response.getOutputStream().write(result.getBytes("utf8"));
    }



}

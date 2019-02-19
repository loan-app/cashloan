package com.xiji.cashloan.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.xiji.cashloan.api.util.CollectionUtil;
import com.xiji.cashloan.api.util.OcrUtil;
import com.xiji.cashloan.cl.domain.CallsOutSideFee;
import com.xiji.cashloan.cl.domain.UserAuth;
import com.xiji.cashloan.cl.domain.UserCardCreditLog;
import com.xiji.cashloan.cl.domain.UserMessages;
import com.xiji.cashloan.cl.model.UserAuthModel;
import com.xiji.cashloan.cl.model.dsdata.facecheck.FaceCheckBiz;
import com.xiji.cashloan.cl.model.dsdata.facecheck.FaceCheckIdCardResult;
import com.xiji.cashloan.cl.model.dsdata.facecheck.FaceCheckReq;
import com.xiji.cashloan.cl.model.dsdata.facecheck.FaceCheckResult;
import com.xiji.cashloan.cl.service.BankCardService;
import com.xiji.cashloan.cl.service.CallsOutSideFeeService;
import com.xiji.cashloan.cl.service.ClBorrowService;
import com.xiji.cashloan.cl.service.OperatorReqLogService;
import com.xiji.cashloan.cl.service.OperatorRespDetailService;
import com.xiji.cashloan.cl.service.OperatorService;
import com.xiji.cashloan.cl.service.UserAuthService;
import com.xiji.cashloan.cl.service.UserBlackInfoService;
import com.xiji.cashloan.cl.service.UserCardCreditLogService;
import com.xiji.cashloan.cl.service.UserContactsService;
import com.xiji.cashloan.cl.service.UserMessagesService;
import com.xiji.cashloan.cl.util.CallsOutSideFeeConstant;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.util.Base64;
import com.xiji.cashloan.core.common.util.JsonUtil;
import com.xiji.cashloan.core.common.util.ServletUtils;
import com.xiji.cashloan.core.common.util.StringUtil;
import com.xiji.cashloan.core.common.util.file.FileUtil;
import com.xiji.cashloan.core.common.util.file.UploadFileModel;
import com.xiji.cashloan.core.common.web.controller.BaseController;
import com.xiji.cashloan.core.domain.Borrow;
import com.xiji.cashloan.core.domain.User;
import com.xiji.cashloan.core.domain.UserBaseInfo;
import com.xiji.cashloan.core.model.UserWorkInfoModel;
import com.xiji.cashloan.core.service.CloanUserService;
import com.xiji.cashloan.core.service.UserBaseInfoService;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import tool.util.DateUtil;
import tool.util.NumberUtil;

/**
 * 用户详情表Controller
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/12/03
 *
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Controller
@Scope("prototype")
public class UserBaseInfoController extends BaseController {

    public static final Logger logger = LoggerFactory.getLogger(UserBaseInfoController.class);

    @Resource
    private UserBaseInfoService userBaseInfoService;

    @Resource
    private UserAuthService userAuthService;

    @Resource
    private BankCardService bankCardService;

    @Resource
    private OperatorReqLogService operatorReqLogService;

    @Resource
    private OperatorRespDetailService operatorRespDetailService;

    @Resource
    private UserContactsService userContactsService;

    @Resource
    private UserMessagesService userMessagesService;

    @Resource
    private ClBorrowService clBorrowService;

    @Resource
    private OperatorService operatorService;

    @Resource
    private CloanUserService cloanUserService;

    @Resource
    private UserCardCreditLogService userCardCreditLogService;

    @Resource
    private UserBlackInfoService userBlackInfoService;
    @Resource
    private CallsOutSideFeeService callsOutSideFeeService;

    /**
     * @param userId
     * @return void
     * @description 根据userId获取用户信息
     * @author chenxy
     * @since 1.0.0
     */
    @RequestMapping(value = "/api/act/mine/userInfo/getUserInfo.htm", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getUserInfo(
            @RequestParam(value = "userId", required = true) String userId) {
        String serverHost = Global.getValue("server_host");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);
        UserBaseInfo info = userBaseInfoService.findSelective(paramMap);
        if (null != info && null != info.getLivingImg()) {
            info.setLivingImg(serverHost + "/readFile.htm?path=" + info.getLivingImg());
            info.setFrontImg(serverHost + "/readFile.htm?path=" + info.getFrontImg());
            info.setBackImg(serverHost + "/readFile.htm?path=" + info.getBackImg());
            info.setOcrImg(serverHost + "/readFile.htm?path=" + info.getOcrImg());
        }
        return JsonUtil.newJson().addData(Constant.RESPONSE_DATA, info).toJson().toJSONString();
    }

    /**
     * 查询用户工作信息
     *
     * @param userId
     */
    @RequestMapping(value = "/api/act/mine/userInfo/getWorkInfo.htm", method = RequestMethod.GET)
    public void getWorkInfo(
            @RequestParam(value = "userId", required = true) Long userId) {
        UserWorkInfoModel info = userBaseInfoService.getWorkInfo(userId);
        if (StringUtil.isNotBlank(info.getWorkingImg())) {
            info.setWorkImgState(UserWorkInfoModel.WORK_IMG_ADDED);
        } else {
            info.setWorkImgState(UserWorkInfoModel.WORK_IMG_NO_ADD);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constant.RESPONSE_DATA, info);
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
        ServletUtils.writeToResponse(response, result);
    }

    /**
     * 查询用户工作信息
     *
     * @param userId
     */
    @RequestMapping(value = "/api/act/mine/userInfo/getWorkImg.htm", method = RequestMethod.GET)
    public void getWorkImg(@RequestParam(value = "userId", required = true) Long userId) {
        String serverHost = Global.getValue("server_host");

        UserWorkInfoModel info = userBaseInfoService.getWorkInfo(userId);
        String workImgPath = info.getWorkingImg();
        List<String> list = new ArrayList<String>();
        if (StringUtil.isNotBlank(workImgPath)) {
            String[] imgArray = workImgPath.split(";");

            for (int i = 0; i < imgArray.length; i++) {
                if (StringUtil.isNotBlank(imgArray[i])) {
                    list.add(serverHost + "/readFile.htm?path=" + imgArray[i]);
                }
            }
        }

        Map<String, Object> listMap = new HashMap<String, Object>();
        listMap.put("list", list);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constant.RESPONSE_DATA, listMap);
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
        ServletUtils.writeToResponse(response, result);
    }

    /**
     * @param userId
     * @return void
     * @description 根据userId获取用户姓名
     * @author chenxy
     * @since 1.0.0
     */
    @RequestMapping(value = "/api/act/mine/userInfo/getUserName.htm", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getUserName(
            @RequestParam(value = "userId", required = true) String userId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);
        UserBaseInfo info = userBaseInfoService.findSelective(paramMap);
        paramMap.clear();
        Map<String, Object> temp = new HashMap<String, Object>();
        temp.put("name", info.getRealName());
        return JsonUtil.newJson().addData(Constant.RESPONSE_DATA, temp).toJson().toJSONString();
    }

    /**
     * @param type
     * @return void
     * @description 根据type获取相应的字典项
     * @author chenxy
     * @since 1.0.0
     */
    @RequestMapping(value = "/api/act/dict/list.htm", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getDicts(@RequestParam(value = "type") String type) {
        Map<String, Object> dicList = new HashMap<String, Object>();
        if (type != null && type != "") {
            String[] types = type.split(",");
            for (int i = 0; i < types.length; i++) {
                dicList.put(StringUtil.clearUnderline(types[i]) + "List", userBaseInfoService.getDictsCache(types[i]));
            }
        }
        if (!dicList.isEmpty()) {
            return JsonUtil.newJson().addData(Constant.RESPONSE_DATA, dicList).toJson().toJSONString();
        } else {
            return JsonUtil.newFailJson().toJson().toJSONString();
        }
    }

    /**
     * 商汤2.0身份证OCR识别，上传数据文件，该文件需要APP中的SDK 生成
     *
     * @param byteFront 正面身份证数据流
     * @param byteBack  反面身份证数据流
     * @throws Exception
     */
    @RequestMapping(value = "/api/act/mine/userInfo/apiLinkfaceIDOcrRequest.htm")
    public void apiLinkfaceIDOcrRequest(
            @RequestParam(value = "byteFront", required = false) MultipartFile byteFront,
            @RequestParam(value = "byteBack", required = false) MultipartFile byteBack
    ) throws Exception {
        final String LINKFACEHOST = Global.getValue("linkface_idOcr");
        Map<String, Object> map = new HashMap<String, Object>();
        List<UploadFileModel> list = new LinkedList<>();

        saveFile(list, byteFront);
//        saveFile(list, byteBack);

//        LinkfaceIDTkOcrRequest linkfaceIDTkOcrRequest = new LinkfaceIDTkOcrRequest(LINKFACEHOST, new File(list.get(0).getResPath()), null);
//        String result = linkfaceIDTkOcrRequest.request();
//        linkfaceIDOcrRequest.setOcrFrontImg(new File(list.get(0).getResPath()));
//        linkfaceIDOcrRequest.setOcrBackImg(new File(list.get(1).getResPath()));
//
//        linkfaceIDOcrRequest.signByKey(SECRETKEY);
//        String result = linkfaceIDOcrRequest.request();
        logger.info("请求地址--" + LINKFACEHOST);
//        logger.info("ocr返回结果-->" + result);
        FaceCheckReq req = new FaceCheckReq();
        req.setUrl(LINKFACEHOST);
        req.setFrontImgPath(list.get(0).getResPath());
        FaceCheckIdCardResult idCardResult = FaceCheckBiz.checkIdCard(req);

        Map<String, Object> resultMap = new HashMap<>();
//        JSONObject resultJson = JSONObject.parseObject(result);
//        if (StringUtil.isNotBlank(resultJson)) {
//            JSONObject name = JSONObject.parseObject(StringUtil.isNull(resultJson.get("name")));
//            JSONObject idCard = JSONObject.parseObject(StringUtil.isNull(resultJson.get("idcard_number")));
//            JSONObject address = JSONObject.parseObject(StringUtil.isNull(resultJson.get("address")));
//            resultMap.put("name", name.getString("result"));
//            resultMap.put("idNum", idCard.getString("result"));
//            resultMap.put("address", address.getString("result"));
//            //	if (200 == resultJson.getInteger("code")) {
//            //		JSONObject data = JSONObject.parseObject(StringUtil.isNull(resultJson.get("data")));
//            //		JSONObject front = JSONObject.parseObject(StringUtil.isNull(data.get("front")));
//            //		JSONObject res = JSONObject.parseObject(StringUtil.isNull(front.get("res")));
//            //		if (StringUtil.isNotBlank(res)) {
//            //			resultMap.put("name",res.getString("name"));
//            //			resultMap.put("idNum",res.getString("idNum"));
//            //			resultMap.put("address",res.getString("address"));
//            //		}
//        }
        if (idCardResult != null) {
            resultMap.put("name", idCardResult.getName());
            resultMap.put("idNum", idCardResult.getIdNum());
            resultMap.put("address", idCardResult.getAddress());
        }

        map.put(Constant.RESPONSE_DATA, resultMap);
        map.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        map.put(Constant.RESPONSE_CODE_MSG, "保存成功");

        ServletUtils.writeToResponse(response, map);
    }

    /**
     * @param byteLiving 自拍数据流
     * @param livingImg  自拍
     * @param frontImg   正面
     * @param backImg    背面
     * @param realName   名字
     * @param idNo       身份证号
     * @param education  学历
     * @param liveAddr   居住地址
     * @param detailAddr 居住详细地址
     * @param ocrType ocr类型，有盾 youdun
     * @param ocrResult 有盾认证结果json
     * @param orderNo 有盾认证请求流水号
     * @return void
     * @throws Exception
     * @description 人证对比，上传数据文件，该文件需要APP中的SDK 生成
     */
    @RequestMapping(value = "/api/act/mine/userInfo/apiLinkfaceliRequest.htm")
    public void apiLinkfaceliRequest(
            @RequestParam(value = "byteLiving", required = false) MultipartFile byteLiving,
            @RequestParam(value = "livingImg", required = false) MultipartFile livingImg,
            @RequestParam(value = "frontImg", required = false) MultipartFile frontImg,
            @RequestParam(value = "backImg", required = false) MultipartFile backImg,
            @RequestParam(value = "realName", required = false) String realName,
            @RequestParam(value = "idNo", required = false) String idNo,
            @RequestParam(value = "education", required = false) String education,
            @RequestParam(value = "liveAddr", required = false) String liveAddr,
            @RequestParam(value = "detailAddr", required = false) String detailAddr,
            @RequestParam(value = "ocrType", required = false) String ocrType,
            @RequestParam(value = "ocrResult", required = false) String ocrResult,
            @RequestParam(value = "orderNo", required = false) String orderNo,
            @RequestParam(value = "liveCoordinate", required = false) String liveCoordinate)
            throws Exception {
        long userId = NumberUtil.getLong(request.getSession().getAttribute("userId").toString());
        Map<String, Object> returnMap = new HashMap<String, Object>();
        JSONObject ocrResultJson = null;
        if (OcrUtil.ifYoudun(ocrType)) {
            if (StringUtil.isNotEmpty(ocrResult)) {
                ocrResultJson = JSONObject.parseObject(ocrResult);
                if (OcrUtil.success(ocrResultJson)) {
                    realName = OcrUtil.getIdName(ocrResultJson);
                    idNo = OcrUtil.getIdCard(ocrResultJson);
                }
            }
        }
        Map<String, Object> infoMap = new HashMap<String, Object>();
        infoMap.put("idNo", idNo);

        UserBaseInfo info = userBaseInfoService.findSelective(infoMap);
        if (info == null) {
            infoMap.clear();
            infoMap.put("userId", userId);
            info = userBaseInfoService.findSelective(infoMap);
        }

        if (info != null
                && info.getUserId().toString().equals(StringUtil.isNull(userId))
                && (StringUtil.isBlank(info.getIdNo()) || info.getIdNo()
                .equals(idNo))) {
            if (!userCardCreditLogService.isCanCredit(info.getUserId())) {
                returnMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                returnMap.put(Constant.RESPONSE_CODE_MSG, "今日请求认证次数已超过限制,请明日再尝试");
            } else {
                // 信息校验成功,继续业务处理
                // 根据userId获取user表里面的login_name并存入baseinfo表里面的phone字段
                User user = cloanUserService.getById(userId);
                info.setPhone(user.getLoginName());
                info.setRealName(realName);
                info.setSex(StringUtil.getSex(idNo));
                info.setAge(StringUtil.getAge(idNo));
                info.setIdNo(idNo);
                info.setEducation(education);
                info.setLiveDetailAddr(detailAddr);
                info.setLiveAddr(liveAddr);
                info.setLiveCoordinate(liveCoordinate);

                Map<String, Object> userAuthMap = new HashMap<String, Object>();
                userAuthMap.put("userId", userId);
                UserAuth userAuth = userAuthService.getUserAuth(userAuthMap);

                String taskId = "";
                double match = 0.0;

                if ((livingImg != null && 30 != Integer.parseInt(userAuth.getIdState()))
                    || OcrUtil.ifYoudun(ocrType)) {
                    logger.info("用户" + user.getLoginName() + "完善个人信息，进入人证识别比对");
                    response.setContentType("text/html;charset=utf8");
                    List<UploadFileModel> list = new LinkedList<>();
                    UserCardCreditLog log = new UserCardCreditLog();
                    log.setCreateTime(DateUtil.getNow());
                    log.setUserId(info.getUserId());

                    if (OcrUtil.ifYoudun(ocrType)){
                        savePicFile(list, OcrUtil.getLivingPhoto(ocrResultJson), info.getPhone());
                        savePicFile(list, OcrUtil.getFrontcard(ocrResultJson), info.getPhone());
                        savePicFile(list, OcrUtil.getBackcard(ocrResultJson), info.getPhone());

                        log.setReqParams("");
                        log.setReturnParams(ocrResult);
                        log.setResult(OcrUtil.getAuth(ocrResultJson));
                        match = OcrUtil.getScore(ocrResultJson);
                        log.setConfidence(String.valueOf(match));
                        if (StringUtil.equalsIgnoreCase("F",OcrUtil.getAuth(ocrResultJson))){
                            //认证失败
                            match = 0.0;
                        }
                        taskId = orderNo;
                    }else {
                        saveMultipartFile(list, livingImg, info.getPhone());
                        saveMultipartFile(list, frontImg, info.getPhone());
                        saveMultipartFile(list, backImg, info.getPhone());
//                    saveFile(list, byteLiving);
//                    info.setOcrImg(list.get(3).getResPath());

                        final String LINKFACEHOST = Global.getValue("linkface_liVerification");
                        FaceCheckReq req = new FaceCheckReq();
                        req.setUrl(LINKFACEHOST);
                        req.setIdCard(idNo);
                        req.setLivingImgPath(list.get(0).getResPath());
                        req.setFrontImgPath(list.get(1).getResPath());
                        req.setRealName(user.getLoginName());
                        FaceCheckResult faceCheckResult = FaceCheckBiz.checkFace(req);
                        log.setReqParams(faceCheckResult.getReqParams());
                        log.setReturnParams(faceCheckResult.getReturnParams());
                        log.setResult(faceCheckResult.getCode());
                        match = faceCheckResult.getScore();
                        taskId = faceCheckResult.getTaskId();
                        log.setConfidence(String.valueOf(match));
                    }

                    info.setLivingImg(list.get(0).getResPath());
                    info.setFrontImg(list.get(1).getResPath());
                    info.setBackImg(list.get(2).getResPath());
                    userCardCreditLogService.insert(log);
                    logger.info("用户" + user.getLoginName() + "完善个人信息，进行人证识别比对，比对值为:" + match);
                } else if (UserAuthModel.STATE_VERIFIED.equals(userAuth.getIdState())) {
                    match = Global.getDouble("idCard_credit_pass_rate");
                }

                logger.info("用户" + user.getLoginName() + "完善个人信息，人证识别比对最终值为：" + match);

                if (match >= Global.getDouble("idCard_credit_pass_rate")) {
                    int count = userBaseInfoService.updateById(info);
                    returnMap.put("idState", UserAuthModel.STATE_VERIFIED);
                    returnMap.put("idTime", DateUtil.getNow());
                    returnMap.put("userId", userId);
                    userAuthService.updateByUserId(returnMap);
                    CallsOutSideFee callsOutSideFee = new CallsOutSideFee(userId,taskId, CallsOutSideFeeConstant.CALLS_TYPE_FACE_DETECT,CallsOutSideFeeConstant.FEE_FACE_DETECT,CallsOutSideFeeConstant.CAST_TYPE_CONSUME,info.getPhone());
                    callsOutSideFeeService.insert(callsOutSideFee);
                    returnMap.clear();
                    if (count > 0) {
                        returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
                        returnMap.put(Constant.RESPONSE_CODE_MSG, "保存成功");
                    } else {
                        returnMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                        returnMap.put(Constant.RESPONSE_CODE_MSG, "系统错误，保存失败");
                    }
                } else {
                    returnMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                    returnMap.put(Constant.RESPONSE_CODE_MSG, "认证失败，请到光线充足处重新认证");
                }
            }
        } else {
            // 信息校验失败,返回前台处理
            returnMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            returnMap.put(Constant.RESPONSE_CODE_MSG, "处理失败，身份信息已存在");
        }
        ServletUtils.writeToResponse(response, returnMap);
    }

    @RequestMapping(value = "/api/act/mine/workInfo/saveOrUpdate.htm", method = RequestMethod.POST)
    public void workInfoSave(
            @RequestParam(value = "userId", required = true) String userId,
            @RequestParam(value = "companyName", required = false) String companyName,
            @RequestParam(value = "companyPhone", required = false) String companyPhone,
            @RequestParam(value = "companyAddr", required = false) String companyAddr,
            @RequestParam(value = "companyDetailAddr", required = false) String companyDetailAddr,
            @RequestParam(value = "companyCoordinate", required = false) String companyCoordinate,
            @RequestParam(value = "workingYears", required = false) String workingYears) {

        Map<String, Object> userMap = new HashMap<String, Object>();
        userMap.put("userId", userId);
        UserBaseInfo info = userBaseInfoService.findSelective(userMap);

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("companyName", companyName);
        paramMap.put("companyPhone", companyPhone);
        paramMap.put("companyAddr", companyAddr);
        paramMap.put("companyDetailAddr", companyDetailAddr);
        paramMap.put("companyCoordinate", companyCoordinate);
        paramMap.put("workingYears", workingYears);
        paramMap.put("id", info.getId());
        boolean flag = userBaseInfoService.updateSelective(paramMap);

        if (flag) {
            Map<String, Object> authMap = new HashMap<String, Object>();
            authMap.put("userId", userId);
            authMap.put("workInfoState", UserAuthModel.STATE_VERIFIED);
            authMap.put("workInfoTime", DateUtil.getNow());
            userAuthService.updateByUserId(authMap);
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (flag) {
            resultMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            resultMap.put(Constant.RESPONSE_CODE_MSG, "保存成功");
        } else {
            resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            resultMap.put(Constant.RESPONSE_CODE_MSG, "系统错误，保存失败");
        }
        ServletUtils.writeToResponse(response, resultMap);
    }

    @RequestMapping(value = "/api/act/mine/workInfo/workImgSave.htm", method = RequestMethod.POST)
    public void workImgSava(
            @RequestParam(value = "userId", required = true) String userId,
            @RequestParam(value = "workImgFir", required = false) MultipartFile workImgFir,
            @RequestParam(value = "workImgSec", required = false) MultipartFile workImgSec,
            @RequestParam(value = "workImgThr", required = false) MultipartFile workImgThr) {
        Map<String, Object> userMap = new HashMap<String, Object>();
        userMap.put("userId", userId);
        UserBaseInfo info = userBaseInfoService.findSelective(userMap);

        // 判断是否已经添加过 ，如果已经添加过 则将原图片路径添加过
        String workImg = "";
        if (null != info && StringUtil.isNotBlank(info.getWorkingImg())) {
            workImg = StringUtil.isNull(info.getWorkingImg());
        }

        StringBuilder buffer = new StringBuilder(workImg);

        if (workImgFir != null) {
            List<UploadFileModel> list = new LinkedList<>();
            response.setContentType("text/html;charset=utf8");
            saveMultipartFile(list, workImgFir, info.getPhone());
            buffer.append(list.get(0).getResPath()).append(";");
        }

        if (workImgSec != null) {
            List<UploadFileModel> list = new LinkedList<>();
            response.setContentType("text/html;charset=utf8");
            saveMultipartFile(list, workImgSec, info.getPhone());
            buffer.append(list.get(0).getResPath()).append(";");
        }

        if (workImgThr != null) {
            List<UploadFileModel> list = new LinkedList<>();
            response.setContentType("text/html;charset=utf8");
            saveMultipartFile(list, workImgThr, info.getPhone());
            buffer.append(list.get(0).getResPath()).append(";");
        }

        boolean flag = true;
        if(StringUtil.isNotBlank(buffer)) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("workingImg", buffer.toString());
            if (info != null) {
                paramMap.put("id", info.getId());
            }
            flag = userBaseInfoService.updateSelective(paramMap);
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (flag) {
            resultMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            resultMap.put(Constant.RESPONSE_CODE_MSG, "保存成功");
        } else {
            resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            resultMap.put(Constant.RESPONSE_CODE_MSG, "系统错误，保存失败");
        }
        ServletUtils.writeToResponse(response, resultMap);
    }

    private void saveMultipartFile(List<UploadFileModel> list, MultipartFile file, String phone) {
        if (!file.isEmpty()) {
            UploadFileModel model = FileUtil.uploadImg(file, phone, "faceId");
            list.add(model);
        }
    }
    private void savePicFile(List<UploadFileModel> list, String picUrl, String phone) {
        if (StringUtil.isNotEmpty(picUrl)) {
            UploadFileModel model = FileUtil.uploadImg(picUrl, phone, "faceId");
            list.add(model);
        }
    }

    /**
     * @param encodedInfo
     * @return void
     * @description 保存用户通讯录信息
     * @author chenxy
     * @since 1.0.0
     */
    @SuppressWarnings({"unchecked"})
    @RequestMapping(value = "/api/act/mine/userInfo/contacts.htm", method = RequestMethod.POST)
    public void contacts(@RequestParam(value = "info", required = true) String encodedInfo) {
        long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());

        String info = new String(Base64.decode(encodedInfo));
        List<Map<String, Object>> infos = JsonUtil.parse(info, List.class);
        boolean flag = userContactsService.deleteAndSave(infos, userId);
        Map<String, Object> returnMap = new HashMap<String, Object>();
        if (flag) {
            returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            returnMap.put(Constant.RESPONSE_CODE_MSG, "保存成功");
        } else {
            returnMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            returnMap.put(Constant.RESPONSE_CODE_MSG, "保存失败");
        }
        ServletUtils.writeToResponse(response, returnMap);
    }

    /**
     * @param encodedInfo
     * @param userId
     * @return void
     * @throws ParseException
     * @description 保存用户短信信息
     * @author chenxy
     * @since 1.0.0
     */
    @RequestMapping(value = "/api/act/mine/userInfo/messages.htm", method = RequestMethod.POST)
    public void messages(
            @RequestParam(value = "info", required = true) String encodedInfo,
            @RequestParam(value = "userId", required = true) String userId)
            throws ParseException {
		String info = new String(Base64.decode(encodedInfo));
		List<Map<String, Object>> infos = JsonUtil.parse(info, List.class);
		if (CollectionUtil.isNotEmpty(infos)){
            for (Map<String, Object> map : infos) {
                UserMessages clUserMessages = new UserMessages();
                clUserMessages.setName(map.get("name") + "");
                clUserMessages.setPhone(map.get("phone") + "");
                clUserMessages.setTime(new Date(Long.parseLong(map.get("time") + "")));
                clUserMessages.setType(map.get("type") + "");
                clUserMessages.setUserId(Long.parseLong(userId));
                clUserMessages.setContent(map.get("content")+"");
                userMessagesService.saveShardUserMsg(clUserMessages);
            }
        }
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        returnMap.put(Constant.RESPONSE_CODE_MSG, "保存成功");
        ServletUtils.writeToResponse(response, returnMap);
    }

    /**
     * @param userId
     * @return void
     * @description 获取验证状态
     * @author chenxy
     * @since 1.0.0
     */
    @RequestMapping(value = "/api/act/mine/userAuth/getUserAuth.htm", method = RequestMethod.GET)
    public void getUserAuth(
            @RequestParam(value = "userId", required = true) String userId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);
        UserAuth userAuth = userAuthService.getUserAuth(paramMap);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_DATA, userAuth);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        ServletUtils.writeToResponse(response, result);
    }


    @RequestMapping(value = "/api/act/mine/userAuth/getAuthImgLogo.htm", method = RequestMethod.GET)
    public void getAuthImgLogo() {

        String serverHost = Global.getValue("server_host");
        String path = request.getSession().getServletContext().getRealPath("/");
        StringBuilder buffer = new StringBuilder(path)
                .append(File.separatorChar).append("static")
                .append(File.separatorChar).append("images")
                .append(File.separator).append("authImgLogo.png");

        Map<String, Object> data = new HashMap<>();
        String filePath = StringUtil.isNull(buffer);
        data.put("authImgLogo", serverHost + "/readFile.htm?path=" + filePath);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constant.RESPONSE_DATA, data);
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        ServletUtils.writeToResponse(response, result);
    }


    /**
     * 引流判断
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/api/act/needDivert.htm")
    public void needDivert(final HttpServletRequest request,
                           HttpServletResponse response) {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        String userId = (String) request.getSession().getAttribute("userId");
        String able = Global.getValue("divert_able");
        //判断用户是否登录
        if (userId != null) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("userId", userId);
            //获得用户借款数据
            Borrow borrow = clBorrowService.findLastBorrow(Long.parseLong(userId));
            //判断是否启用引流和审核通过
            if (borrow != null && "10".equals(able) && ("21".equals(borrow.getState()) || "27".equals(borrow.getState()))) {
                //不通过则引流
                dataMap.put("state", "10");
                dataMap.put("url", Global.getValue("divert_url"));
            } else {
                //通过则不需要引流
                dataMap.put("state", "20");
            }
            result.put(Constant.RESPONSE_DATA, dataMap);
            result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
        } else {
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "查询失败");
        }
        ServletUtils.writeToResponse(response, result);
    }

    private void saveFile(List<UploadFileModel> list, MultipartFile file) {
        if (!file.isEmpty()) {
            UploadFileModel model = saveFile(file);
            list.add(model);
        }
    }

    private UploadFileModel saveFile(MultipartFile file) {
        UploadFileModel model = new UploadFileModel();
        model.setCreateTime(DateUtil.getNow());

        // 文件名称
        String picName = file.getOriginalFilename();

        CommonsMultipartFile cf = (CommonsMultipartFile) file;
        DiskFileItem fi = (DiskFileItem) cf.getFileItem();
        File newFile = (File) fi.getStoreLocation();
        logger.debug("图片----------" + newFile);
        // 校验图片大小
        Long picSize = file.getSize();
        if (picSize.compareTo(20971520L) > 0) {
            model.setErrorMsg("文件超出20M大小限制");
            return model;
        }
        // 保存文件
        String s = File.separator;
        String filePath = s + "data" + s + "image" + s + "temporary" + s +
                DateUtil.dateStr(DateUtil.getNow(), DateUtil.DATEFORMAT_STR_013) + s + picName;
        File files = new File(filePath);
        if (!files.exists()) {
            try {
                files.mkdirs();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                model.setErrorMsg("文件目录不存在");
                return model;
            }
        }
        try {
            file.transferTo(files);
        } catch (IllegalStateException | IOException e) {
            logger.error(e.getMessage(), e);
        }
        // 转存文件
        model.setResPath(filePath);
        model.setFileName(picName);
        model.setFileSize(new BigDecimal(picSize));
        return model;
    }
}

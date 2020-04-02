package com.xiji.cashloan.cl.model.dsdata.facecheck;

import static com.xiji.cashloan.rc.controller.SceneBusinessLogController.logger;

import com.alibaba.fastjson.JSONObject;
import com.xiji.cashloan.cl.model.dsdata.FaceCheckIdCardRequest;
import com.xiji.cashloan.cl.model.dsdata.FaceCheckOcrRequest;
import com.xiji.cashloan.cl.model.dsdata.LinkfaceHsTkCreditRequest;
import com.xiji.cashloan.cl.model.dsdata.LinkfaceIDTkOcrRequest;
import com.xiji.cashloan.cl.model.dsdata.util.FaceCheckUtil;
import java.io.File;
import org.apache.commons.lang3.math.NumberUtils;
import tool.util.StringUtil;

/**
 * @Auther: king
 * @Date: 2019/1/30 15:49
 * @Description:
 */
public class FaceCheckBiz {

    public static FaceCheckResult checkFace(FaceCheckReq req) throws Exception {
        if (StringUtil.equals(FaceCheckUtil.router(req.getIdCard()),FaceCheckUtil.model_face)){
            return faceOcr(req);
        }else {
            return kFaceCheck(req);
        }
    }

    private static FaceCheckResult faceOcr(FaceCheckReq req) throws Exception {
        FaceCheckResult result = new FaceCheckResult();

        LinkfaceHsTkCreditRequest linkfaceHsTkRequest = new LinkfaceHsTkCreditRequest(req.getUrl(), new File(req.getLivingImgPath()), req.getRealName(), req.getIdCard());
        String str = linkfaceHsTkRequest.request();
        logger.info("用户" + req.getLoginName() + "完善个人信息，进行人证识别比对，result为:" + result);
        JSONObject resultJson = JSONObject.parseObject(str);

        if (resultJson.get("request_id") != null) {
            result.setTaskId(resultJson.get("request_id").toString());
        }
        JSONObject data = JSONObject.parseObject(StringUtil.isNull(resultJson.get("result_faceid")));
        result.setCode(String.valueOf(resultJson.get("code")));
        double match = 0.0;
        if (data == null){
            logger.info("人证识别请求失败"+result);
            match = 0.00;
        }else {
            match = data.getDoubleValue("confidence");
        }
        result.setScore(match);
        result.setReqParams(JSONObject.toJSONString(linkfaceHsTkRequest));
        result.setReturnParams(str);
        result.setFaceModel(FaceCheckUtil.model_face);
        return result;
    }
    private static FaceCheckResult kFaceCheck(FaceCheckReq req) throws Exception {
        FaceCheckResult result = new FaceCheckResult();
        String orderNo = FaceCheckUtil.getSeqNumber();
        FaceCheckOcrRequest request = new FaceCheckOcrRequest(req.getFrontImgPath(),req.getLivingImgPath(),orderNo);
        String str = request.request();
        logger.info("用户" + req.getLoginName() + "完善个人信息，进行人证识别比对，result为:" + result);
        JSONObject resultJson = JSONObject.parseObject(str);
        if (resultJson.get("errorCode") != null) {
            result.setCode(resultJson.get("errorCode").toString());
        }
        if (resultJson.get("score") != null) {
            result.setScore(NumberUtils.toDouble(resultJson.get("score").toString(),0.0));
        }
        result.setReqParams(JSONObject.toJSONString(req));
        result.setTaskId(orderNo);
        result.setReturnParams(str);
        result.setFaceModel(FaceCheckUtil.model_kFace);
        return result;
    }

    public static FaceCheckIdCardResult checkIdCard(FaceCheckReq req) throws Exception {
        if (StringUtil.equals(FaceCheckUtil.routerIDCard(),FaceCheckUtil.model_face)){
            return faceIDCard(req);
        }else {
            return kFaceCheckIDCard(req);
        }
    }

    private static FaceCheckIdCardResult faceIDCard(FaceCheckReq req) throws Exception {
        FaceCheckIdCardResult result = new FaceCheckIdCardResult();
        LinkfaceIDTkOcrRequest linkfaceIDTkOcrRequest = new LinkfaceIDTkOcrRequest(req.getUrl(), new File(req.getFrontImgPath()), null);
        String requestStr = linkfaceIDTkOcrRequest.request();
        logger.info("ocr返回结果-->" + requestStr);
        JSONObject resultJson = JSONObject.parseObject(requestStr);
        if (StringUtil.isNotBlank(resultJson)) {
            JSONObject name = JSONObject.parseObject(StringUtil.isNull(resultJson.get("name")));
            JSONObject idCard = JSONObject.parseObject(StringUtil.isNull(resultJson.get("idcard_number")));
            JSONObject address = JSONObject.parseObject(StringUtil.isNull(resultJson.get("address")));
            result.setName(name.getString("result"));
            result.setIdNum(idCard.getString("result"));
            result.setAddress(address.getString("result"));
        }
        return result;
    }

    /**
     * // normal-识别正常
     // reversed_side-未摆正身份证
     // non_idcard-上传的图片中不包含身份证
     // blurred-身份证模糊
     // over_exposure-身份证关键字段反光或过曝
     // unknown-未知状态
     // Unauthorized
     private String imageStatus;

     //姓名、民族、住址、公民身份号码、出生、性别
     //失效日期 签发机关 签发日期
     private Map wordResults;
     * @param req
     * @return
     * @throws Exception
     */
    private static FaceCheckIdCardResult kFaceCheckIDCard(FaceCheckReq req) throws Exception {
        FaceCheckIdCardResult result = new FaceCheckIdCardResult();
        String orderNo = FaceCheckUtil.getSeqNumber();
        FaceCheckIdCardRequest request = new FaceCheckIdCardRequest(req.getFrontImgPath(),orderNo);
        String requestStr = request.request();
        logger.info("kFaceCheckIDCard ocr返回结果-->" + requestStr);
        JSONObject resultJson = JSONObject.parseObject(requestStr);
        if (StringUtil.isNotBlank(resultJson)) {
            if ("normal".equals(resultJson.getString("imageStatus"))){
                JSONObject wordResult = resultJson.getJSONObject("wordResults");
                if (wordResult != null) {
                    result.setName(wordResult.getString("姓名"));
                    result.setIdNum(wordResult.getString("公民身份号码"));
                    result.setAddress(wordResult.getString("住址"));
                }
            }
        }
        return result;
    }

}

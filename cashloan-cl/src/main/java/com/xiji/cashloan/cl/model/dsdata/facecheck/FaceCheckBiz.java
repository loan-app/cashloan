package com.xiji.cashloan.cl.model.dsdata.facecheck;

import static com.xiji.cashloan.rc.controller.SceneBusinessLogController.logger;

import com.alibaba.fastjson.JSONObject;
import com.xiji.cashloan.cl.model.dsdata.FaceCheckOcrRequest;
import com.xiji.cashloan.cl.model.dsdata.LinkfaceHsTkCreditRequest;
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
}

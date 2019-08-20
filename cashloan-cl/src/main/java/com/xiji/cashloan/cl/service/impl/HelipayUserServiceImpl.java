package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import com.xiji.cashloan.cl.model.pay.common.PayCommonUtil;
import com.xiji.cashloan.cl.model.pay.helipay.HelipayHelper;
import com.xiji.cashloan.cl.model.pay.helipay.constant.HelipayConstant;
import com.xiji.cashloan.cl.model.pay.helipay.util.HelipayUtil;
import com.xiji.cashloan.cl.model.pay.helipay.vo.delegation.MerchantUserUploadResVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.delegation.MerchantUserUploadVo;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.util.StringUtil;
import com.xiji.cashloan.core.domain.UserBaseInfo;
import com.xiji.cashloan.core.service.UserBaseInfoService;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.mapper.HelipayUserMapper;
import com.xiji.cashloan.cl.domain.HelipayUser;
import com.xiji.cashloan.cl.service.HelipayUserService;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

import java.util.Map;


/**
 * 合利宝用户注册信息ServiceImpl
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2019-07-30 15:31:02
 */
 
@Service("helipayUserService")
public class HelipayUserServiceImpl extends BaseServiceImpl<HelipayUser, Long> implements HelipayUserService {
	
    private static final Logger logger = LoggerFactory.getLogger(HelipayUserServiceImpl.class);
   
    @Resource
    private HelipayUserMapper helipayUserMapper;



    @Resource
    private UserBaseInfoService userBaseInfoService;

	@Override
	public BaseMapper<HelipayUser, Long> getMapper() {
		return helipayUserMapper;
	}


	/**
	 * 查询合利宝用户注册信息
	 * @param params
	 * @return
	 */
	public HelipayUser getHelipayUser(Map<String,Object> params){
		return helipayUserMapper.getHelipayUser(params);
	}


	/**
	 * 更新合利宝用户注册信息
	 *
	 * @param paramMap
	 *            更新条件
	 */
	public int updateSelective(Map<String, Object> paramMap){
		return helipayUserMapper.updateSelective(paramMap);
	}


	/**
	 * 合利宝用户注册
	 *
	 */
	@Override
	public void helipayRegister(final UserBaseInfo userBaseInfo){

        new Thread() {
            @Override
            public void run() {
                logger.info("用户进入合利宝注册------>");
                Map<String,String> result = PayCommonUtil.helipayRegister(userBaseInfo);
                if ("success".equals(result.get("code"))){
                    logger.info("用户进入合利宝注册------>");
                    heliPayUpload(userBaseInfo.getUserId(),result.get("helipayUserId"));
                }
            }
        }.start();
	}


	@Override
	public boolean heliPayUpload(Long userId, String helipayUserId) {

		String serverHost = Global.getValue("server_host");
		UserBaseInfo userBaseInfo = userBaseInfoService.findByUserId(userId);
		userBaseInfo.setFrontImg(userBaseInfo.getFrontImg()!=null?serverHost +"/readFile.htm?path="+ userBaseInfo.getFrontImg():"");
		userBaseInfo.setBackImg(userBaseInfo.getBackImg()!=null?serverHost +"/readFile.htm?path="+ userBaseInfo.getBackImg():"");

		//身份证正面
		File frontFile = new File(userBaseInfo.getFrontImg());
		//文件转换
        MultipartFile multipartFrontFile = fileToMultipartFile(frontFile);
        //参数封装
        MerchantUserUploadVo frontUploadVo = setRequestVo(helipayUserId, HelipayConstant.FRONT_OF_ID_CARD);
        HelipayHelper helipayHelper = new HelipayHelper();
        MerchantUserUploadResVo frontRespVo =
                helipayHelper.userUpload(frontUploadVo, multipartFrontFile, "FRONT", Long.parseLong(helipayUserId));

        //身份证返面
        File backFile = new File(userBaseInfo.getBackImg());
        //文件转换
        MultipartFile multipartBackFile = fileToMultipartFile(backFile);
        //参数封装
        MerchantUserUploadVo BackUploadVo = setRequestVo(helipayUserId, HelipayConstant.BACK_OF_ID_CARD);

        MerchantUserUploadResVo backRespVo =
                helipayHelper.userUpload(BackUploadVo, multipartBackFile, "BACK", Long.parseLong(helipayUserId));

        if (StringUtil.isNotBlank(frontRespVo) && StringUtil.isNotBlank(backRespVo)){

            if ("UPLOADED".equals(frontRespVo.getRt7_credentialStatus()) &&
                    "UPLOADED".equals(backRespVo.getRt7_credentialStatus()) ){ //上传成功
                return true;
            }
        }
        return false;
	}

    /**
     * File转换为MultipartFile
     * @param file
     * @return
     */
	private MultipartFile fileToMultipartFile(File file){
        MultipartFile multipartBackFile =null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            multipartBackFile = new MockMultipartFile(file.getName(), file.getName(),
                    ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
        }catch (Exception e){
            logger.info("文件转换异常.");
            e.printStackTrace();
        }

	    return multipartBackFile;
    }

    /**
     * 请求参数封装
     * @param helipayUserId
     * @param credentialType
     * @return
     */
    private MerchantUserUploadVo setRequestVo(String helipayUserId,String credentialType){
        MerchantUserUploadVo uploadVo = new MerchantUserUploadVo();
        String orderId = HelipayUtil.getOrderId();
        uploadVo.setP1_bizType(HelipayConstant.BTYPE_UploadCredential);
        uploadVo.setP2_customerNumber(HelipayUtil.customerNumber());
        uploadVo.setP3_orderId(orderId);
        uploadVo.setP4_userId(helipayUserId);
        uploadVo.setP5_timestamp(HelipayUtil.getTimeStamp());
        uploadVo.setP6_credentialType(credentialType);
        return uploadVo;
    }

}
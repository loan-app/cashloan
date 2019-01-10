package com.xiji.cashloan.cl.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiji.cashloan.cl.domain.NameBlackWhiteUser;
import com.xiji.cashloan.cl.domain.NameBlacklist;
import com.xiji.cashloan.cl.domain.NameWhitelist;
import com.xiji.cashloan.cl.domain.UserBlackInfo;
import com.xiji.cashloan.cl.mapper.NameBlacklistMapper;
import com.xiji.cashloan.cl.mapper.NameWhitelistMapper;
import com.xiji.cashloan.cl.mapper.UserAuthMapper;
import com.xiji.cashloan.cl.mapper.UserBlackInfoMapper;
import com.xiji.cashloan.cl.service.UserBlackInfoService;
import com.xiji.cashloan.cl.service.impl.assist.blacklist.BlacklistConstant;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.core.common.util.StringUtil;
import com.xiji.cashloan.core.domain.UserBaseInfo;
import com.xiji.cashloan.core.mapper.UserBaseInfoMapper;
import com.xiji.cashloan.core.model.UserBaseInfoModel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;


/**
 * 黑名单ServiceImpl
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("userBlackInfoService")
public class UserBlackInfoServiceImpl extends BaseServiceImpl<UserBlackInfo, Long> implements UserBlackInfoService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserBlackInfoServiceImpl.class);
	
    @Resource
    private UserBlackInfoMapper userBlackInfoMapper;
    @Resource
    private UserBaseInfoMapper userBaseInfoMapper;
    @Resource
    private UserAuthMapper userAuthMapper;
	@Resource
	private NameBlacklistMapper nameBlacklistMapper;
	@Resource
	private NameWhitelistMapper nameWhitelistMapper;

//	private static final String DIMENSION_KEY_IDNO = "01";
//	private static final String DIMENSION_KEY_PHONE = "02";

	@Override
	public BaseMapper<UserBlackInfo, Long> getMapper() {
		return userBlackInfoMapper;
	}

	@Override
	public Page<NameBlackWhiteUser> listInfo(Map<String, Object> params,
			int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<NameBlackWhiteUser> nameBlackWhiteUsers = new ArrayList<>();
		if(params != null && params.size() > 0) {
			if("10".equals(params.get("type").toString())) {
				nameBlackWhiteUsers = nameBlacklistMapper.queryByParams(params);
			} else {
				nameBlackWhiteUsers = nameWhitelistMapper.queryByParams(params);
			}
		}
		return (Page<NameBlackWhiteUser>) nameBlackWhiteUsers;
	}

	@Override
	public List<List<String>> importUserInfo(MultipartFile userInfoFile,String type) throws Exception {
		List<List<String>> blackInfos = new ArrayList<List<String>>();
		List<String> values = new ArrayList<String>();
		values.add("姓名");
		values.add("身份证号码");
		values.add("手机号");
		values.add("处理结果");
		blackInfos.add(values);
		Workbook book = null;
		if (StringUtil.isNotBlank(userInfoFile.getOriginalFilename()) && userInfoFile.getOriginalFilename().endsWith(".xls")) {
			book = new HSSFWorkbook(userInfoFile.getInputStream());
		} else if (StringUtil.isNotBlank(userInfoFile.getOriginalFilename()) && userInfoFile.getOriginalFilename().endsWith(".xlsx")) {
			book = new XSSFWorkbook(userInfoFile.getInputStream());
		}else{
			logger.error("文件格式错误"+userInfoFile.getOriginalFilename());
		}

		Date date = new Date();
		
		if (book != null) {
			Sheet sheet = book.getSheetAt(0);
			int lostRowNum = sheet.getLastRowNum();
			Map<String,Object> paramMap = new HashMap<String, Object>();
			List<Long> userInfoIds = new ArrayList<Long>();
			for (int i = 1; i <= lostRowNum; i++) {
				Row row = sheet.getRow(i);
				getCellValue(row,0);
				String realName = getCellValue(row,0);
				String idNo = getCellValue(row,1);
				String phone = getCellValue(row,2);
				values = new ArrayList<String>();
				values.add(realName);
				values.add(idNo);
				values.add(phone);
				paramMap.put("realName", realName);
				paramMap.put("idNo", idNo);
				paramMap.put("phone", phone);
				UserBlackInfo info = userBlackInfoMapper.findSelective(paramMap);
				if(info==null ){
					if(StringUtil.isNotBlank(realName) && StringUtil.isNumber(idNo) && idNo.length()==18 && StringUtil.isNumber(phone) && phone.length()==11 ){
						info = new UserBlackInfo();
						info.setRealName(realName);
						info.setIdNo(idNo);
						info.setPhone(phone);
						info.setCreateTime(date);
						info.setType(type);
						userBlackInfoMapper.save(info);
						
						//需要更新的用户信息
						paramMap.clear();
						paramMap.put("realName", row.getCell(0).toString());
						paramMap.put("idNo", row.getCell(1).toString());
						paramMap.put("phone", row.getCell(2).toString());
						UserBaseInfo userInfo = userBaseInfoMapper.findSelective(paramMap);
						if(userInfo!=null){
							userInfoIds.add(userInfo.getId());
						}
						values.add("已处理");
					}else{
						values.add("未处理，格式错误");
					}
				}else if(info!=null){
					values.add("未处理，已存在对应信息");
				}
				
				blackInfos.add(values);
			}
			
			if(userInfoIds.size()>0){
				if("10".equals(type)){
					userBaseInfoMapper.updateBlackIdNos(userInfoIds);
				}else if("20".equals(type)){
					userBaseInfoMapper.updateWhiteIdNos(userInfoIds);
				}
			}
			
		}
		
		return blackInfos;
	}

	@Override
	public UserBlackInfo findByIdNo(String idNo) {
		return userBlackInfoMapper.findByIdNo(idNo);
	}

	@Override
	public List<UserBlackInfo> listSelective(Map<String, Object> params) {
		return userBlackInfoMapper.listSelective(params);
	}

	@Override
	public int deleteByID(Long id) {
		//将用户黑名单状态修改为正常
		UserBlackInfo blackInfo = userBlackInfoMapper.findByPrimary(id);
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("idNo", blackInfo.getIdNo());
		UserBaseInfo baseInfo = userBaseInfoMapper.findSelective(paramMap);
		if(!ObjectUtils.isEmpty(baseInfo)){
			baseInfo.setState(UserBaseInfoModel.USER_STATE_NOBLACK);
			userBaseInfoMapper.update(baseInfo);
		}
	
			return userBlackInfoMapper.deleteByID(id);
		
		
	}
	
	/**
	 * 黑名单操作
	 * @param baseInfo
	 */
	public void validUserBlackInfo(UserBaseInfo baseInfo){
		Map<String,Object> paramMap = new HashMap<String, Object>();
		//查询白名单用户
		paramMap.put("dimensionkey", BlacklistConstant.DIMENSION_KEY_IDNO);
		paramMap.put("dimensionvalue", baseInfo.getIdNo());
		paramMap.put("status",BlacklistConstant.BLACK_LIST_STATUS_NORMAL);
		List<NameWhitelist> nameWhitelists1 = nameWhitelistMapper.listSelective(paramMap);
		paramMap.clear();
		paramMap.put("dimensionkey", BlacklistConstant.DIMENSION_KEY_PHONE);
		paramMap.put("dimensionvalue", baseInfo.getPhone());
		paramMap.put("status",BlacklistConstant.BLACK_LIST_STATUS_NORMAL);
		List<NameWhitelist> nameWhitelists2 = nameWhitelistMapper.listSelective(paramMap);
		if(nameWhitelists1.size() > 0 || nameWhitelists2.size() > 0){
			baseInfo.setState(UserBaseInfoModel.USER_STATE_WHITE);
			baseInfo.setBlackReason("白名单用户");
			userBaseInfoMapper.update(baseInfo);
		}
		
		//查询黑名单用户
		paramMap.clear();
		paramMap.put("dimensionkey", BlacklistConstant.DIMENSION_KEY_IDNO);
		paramMap.put("dimensionvalue", baseInfo.getIdNo());
		paramMap.put("status",BlacklistConstant.BLACK_LIST_STATUS_NORMAL);
		List<NameBlacklist> nameBlacklists1 = nameBlacklistMapper.listSelective(paramMap);
		paramMap.clear();
		paramMap.put("dimensionkey", BlacklistConstant.DIMENSION_KEY_PHONE);
		paramMap.put("dimensionvalue", baseInfo.getPhone());
		paramMap.put("status",BlacklistConstant.BLACK_LIST_STATUS_NORMAL);
		List<NameBlacklist> nameBlacklists2 = nameBlacklistMapper.listSelective(paramMap);
		if(nameBlacklists1.size() > 0 || nameBlacklists2.size() > 0) {
			baseInfo.setState(UserBaseInfoModel.USER_STATE_BLACK);
			baseInfo.setBlackReason("黑名单用户");
			userBaseInfoMapper.update(baseInfo);
		}
		//出于安全性考虑，请勿改变查询顺序，避免因为数据重复造成某些用户错误进入黑名单，直接放款
	}

	@Override
	public void deleteBlackOrWhiteInfo(Long userId, String state) {
		if(StringUtil.isNotBlank(state) && UserBaseInfoModel.USER_STATE_NOBLACK.equals(state) && userId!=null && userId>0){
			UserBaseInfo baseInfo = userBaseInfoMapper.findByUserId(userId);
			String type = "";
			if(UserBaseInfoModel.USER_STATE_BLACK.equals(baseInfo.getState())){
				type = "10";
			}else if(UserBaseInfoModel.USER_STATE_WHITE.equals(baseInfo.getState())){
				type = "20";
			}
			if(StringUtil.isNotBlank(type)){
				Map<String,Object> params = new HashMap<String, Object>();
				if(StringUtil.isNotBlank(baseInfo.getPhone())){
					params.clear();
					params.put("type",type);
					params.put("phone", baseInfo.getPhone());
					userBlackInfoMapper.deleteByPhone(params);
				}
				
				if(StringUtil.isNotBlank(baseInfo.getIdNo())){
					params.clear();
					params.put("type",type);
					params.put("idNo", baseInfo.getIdNo());
					userBlackInfoMapper.deleteByIdNo(params);
				}


			}
		}


	}

	/**
	 * 删除黑/白名单状态
	 * @param userId
	 * @param state
	 */
	@Override
	public void deleteBlackOrWhite(Long userId, String state,String type){

		if(StringUtil.isNotBlank(state) && UserBaseInfoModel.USER_STATE_NOBLACK.equals(state) && userId != null && userId > 0) {
			UserBaseInfo baseInfo = userBaseInfoMapper.findByUserId(userId);
			Date date = new Date();
			Map<String,Object> paramsPhone = new HashMap<String, Object>();
			paramsPhone.put("dimensionkey",BlacklistConstant.DIMENSION_KEY_PHONE);
			paramsPhone.put("dimensionvalue",baseInfo.getPhone());
			paramsPhone.put("status",BlacklistConstant.BLACK_LIST_STATUS_DELETE);
			paramsPhone.put("lastmodifytime",date);


			Map<String,Object> paramsIdNo = new HashMap<String, Object>();
				paramsIdNo.put("dimensionkey",BlacklistConstant.DIMENSION_KEY_IDNO);
				paramsIdNo.put("dimensionvalue",baseInfo.getIdNo());
				paramsIdNo.put("status",BlacklistConstant.BLACK_LIST_STATUS_DELETE);
			    paramsIdNo.put("lastmodifytime",date);

			// 删除黑名单
			if(UserBaseInfoModel.USER_STATE_NOBLACK.equals(baseInfo.getState()) && "black".equals(type)){
				if (StringUtil.isNotBlank(baseInfo.getPhone())){
					nameBlacklistMapper.updateNameBlacklistStatus(paramsPhone);
				}
				if (StringUtil.isNotBlank(baseInfo.getIdNo())){
					nameBlacklistMapper.updateNameBlacklistStatus(paramsIdNo);
				}
			}

			// 删除白名单
			if (UserBaseInfoModel.USER_STATE_NOBLACK.equals(baseInfo.getState()) && "white".equals(type)){
				if (StringUtil.isNotBlank(baseInfo.getPhone())){
					nameWhitelistMapper.updateNameWhitelistStatus(paramsPhone);
				}
				if (StringUtil.isNotBlank(baseInfo.getIdNo())){
					nameWhitelistMapper.updateNameWhitelistStatus(paramsIdNo);
				}
			}
		}
	}


	/**
	 * 添加黑名单状态
	 * @param userId
	 * @param state
	 */
	@Override
	public void addNameBlack(Long userId, String state){
		if(StringUtil.isNotBlank(state) && UserBaseInfoModel.USER_STATE_BLACK.equals(state) && userId != null && userId > 0) {
			UserBaseInfo baseInfo = userBaseInfoMapper.findByUserId(userId);
			Date date = new Date();

			// 添加手机号黑名单
			Map<String, Object> paramsPhone = new HashMap<String, Object>();
			paramsPhone.put("dimensionkey", BlacklistConstant.DIMENSION_KEY_PHONE);
			paramsPhone.put("dimensionvalue", baseInfo.getPhone());
			paramsPhone.put("status", BlacklistConstant.BLACK_LIST_STATUS_NORMAL);
			paramsPhone.put("lastmodifytime", date);
			nameBlacklistMapper.updateNameBlacklistStatus(paramsPhone);

			paramsPhone.clear();
			paramsPhone.put("dimensionkey", BlacklistConstant.DIMENSION_KEY_PHONE);
			paramsPhone.put("dimensionvalue", baseInfo.getPhone());
			paramsPhone.put("source", BlacklistConstant.SOURCE_ADD);
			NameBlacklist nameBlacklistPhone = nameBlacklistMapper.findSelective(paramsPhone);
			if (nameBlacklistPhone == null) {
				nameBlacklistPhone = new NameBlacklist();
				nameBlacklistPhone.setSource(BlacklistConstant.SOURCE_ADD);
				nameBlacklistPhone.setLastmodifytime(date);
				nameBlacklistPhone.setCreatetime(date);
				nameBlacklistPhone.setStatus(BlacklistConstant.BLACK_LIST_STATUS_NORMAL);
				nameBlacklistPhone.setDimensionvalue(baseInfo.getPhone());
				nameBlacklistPhone.setDimensionkey(BlacklistConstant.DIMENSION_KEY_PHONE);
				nameBlacklistMapper.save(nameBlacklistPhone);
			}

			// 添加身份证黑名单
			Map<String, Object> paramsIdNo = new HashMap<String, Object>();
			paramsIdNo.put("dimensionkey", BlacklistConstant.DIMENSION_KEY_IDNO);
			paramsIdNo.put("dimensionvalue", baseInfo.getIdNo());
			paramsIdNo.put("status", BlacklistConstant.BLACK_LIST_STATUS_NORMAL);
			paramsIdNo.put("lastmodifytime", date);
			nameBlacklistMapper.updateNameBlacklistStatus(paramsIdNo);

			paramsIdNo.clear();
			paramsIdNo.put("dimensionkey", BlacklistConstant.DIMENSION_KEY_IDNO);
			paramsIdNo.put("dimensionvalue", baseInfo.getIdNo());
			paramsIdNo.put("source", BlacklistConstant.SOURCE_ADD);
			NameBlacklist nameBlacklistIdNo = nameBlacklistMapper.findSelective(paramsIdNo);
			if (nameBlacklistIdNo == null) {
				nameBlacklistIdNo = new NameBlacklist();
				nameBlacklistIdNo.setSource(BlacklistConstant.SOURCE_ADD);
				nameBlacklistIdNo.setLastmodifytime(date);
				nameBlacklistIdNo.setCreatetime(date);
				nameBlacklistIdNo.setStatus(BlacklistConstant.BLACK_LIST_STATUS_NORMAL);
				nameBlacklistIdNo.setDimensionvalue(baseInfo.getIdNo());
				nameBlacklistIdNo.setDimensionkey(BlacklistConstant.DIMENSION_KEY_IDNO);
				nameBlacklistMapper.save(nameBlacklistIdNo);
			}
		}
	}


	/**
	 * 添加白名单状态
	 * @param userId
	 * @param state
	 */
	@Override
	public void addNameWhite(Long userId, String state){
		if(StringUtil.isNotBlank(state) && UserBaseInfoModel.USER_STATE_WHITE.equals(state) && userId != null && userId > 0) {
			UserBaseInfo baseInfo = userBaseInfoMapper.findByUserId(userId);
			Date date = new Date();

			// 添加手机号白名单
			Map<String, Object> paramsPhone = new HashMap<String, Object>();
			paramsPhone.put("dimensionkey", BlacklistConstant.DIMENSION_KEY_PHONE);
			paramsPhone.put("dimensionvalue", baseInfo.getPhone());
			paramsPhone.put("status", BlacklistConstant.BLACK_LIST_STATUS_NORMAL);
			paramsPhone.put("lastmodifytime", date);
			nameWhitelistMapper.updateNameWhitelistStatus(paramsPhone);

			paramsPhone.clear();
			paramsPhone.put("dimensionkey", BlacklistConstant.DIMENSION_KEY_PHONE);
			paramsPhone.put("dimensionvalue", baseInfo.getPhone());
			paramsPhone.put("source", BlacklistConstant.SOURCE_ADD);
			NameWhitelist nameWhitelistPhone = nameWhitelistMapper.findSelective(paramsPhone);
			if (nameWhitelistPhone == null) {
				nameWhitelistPhone = new NameWhitelist();
				nameWhitelistPhone.setSource(BlacklistConstant.SOURCE_ADD);
				nameWhitelistPhone.setLastmodifytime(date);
				nameWhitelistPhone.setCreatetime(date);
				nameWhitelistPhone.setStatus(BlacklistConstant.BLACK_LIST_STATUS_NORMAL);
				nameWhitelistPhone.setDimensionvalue(baseInfo.getPhone());
				nameWhitelistPhone.setDimensionkey(BlacklistConstant.DIMENSION_KEY_PHONE);
				nameWhitelistMapper.save(nameWhitelistPhone);
			}

			// 添加身份证白名单
			Map<String, Object> paramsIdNo = new HashMap<String, Object>();
			paramsIdNo.put("dimensionkey", BlacklistConstant.DIMENSION_KEY_IDNO);
			paramsIdNo.put("dimensionvalue", baseInfo.getIdNo());
			paramsIdNo.put("status", BlacklistConstant.BLACK_LIST_STATUS_NORMAL);
			paramsIdNo.put("lastmodifytime", date);
			nameBlacklistMapper.updateNameBlacklistStatus(paramsIdNo);

			paramsIdNo.clear();
			paramsIdNo.put("dimensionkey", BlacklistConstant.DIMENSION_KEY_IDNO);
			paramsIdNo.put("dimensionvalue", baseInfo.getIdNo());
			paramsIdNo.put("source", BlacklistConstant.SOURCE_ADD);
			NameWhitelist nameWhitelistIdNo = nameWhitelistMapper.findSelective(paramsIdNo);
			if (nameWhitelistIdNo == null) {
				nameWhitelistIdNo = new NameWhitelist();
				nameWhitelistIdNo.setSource(BlacklistConstant.SOURCE_ADD);
				nameWhitelistIdNo.setLastmodifytime(date);
				nameWhitelistIdNo.setCreatetime(date);
				nameWhitelistIdNo.setStatus(BlacklistConstant.BLACK_LIST_STATUS_NORMAL);
				nameWhitelistIdNo.setDimensionvalue(baseInfo.getIdNo());
				nameWhitelistIdNo.setDimensionkey(BlacklistConstant.DIMENSION_KEY_IDNO);
				nameWhitelistMapper.save(nameWhitelistIdNo);
			}
		}
	}



	@Override
	public List<List<String>> importUserInfoNew(MultipartFile userInfoFile, String type) throws Exception {
		List<List<String>> nameInfos = new ArrayList<List<String>>();
		List<String> values = new ArrayList<String>();
		// String source = "IP";
		values.add("类别");
		values.add("对应值");
		values.add("处理结果");
		nameInfos.add(values);
		Workbook book = null;
		if (StringUtil.isNotBlank(userInfoFile.getOriginalFilename()) && userInfoFile.getOriginalFilename().endsWith(".xls")) {
			book = new HSSFWorkbook(userInfoFile.getInputStream());
		} else if (StringUtil.isNotBlank(userInfoFile.getOriginalFilename()) && userInfoFile.getOriginalFilename().endsWith(".xlsx")) {
			book = new XSSFWorkbook(userInfoFile.getInputStream());
		}else{
			logger.error("文件格式错误"+userInfoFile.getOriginalFilename());
		}

		Date date = new Date();

		if (book != null) {
			Sheet sheet = book.getSheetAt(0);
			int lostRowNum = sheet.getLastRowNum();
			Map<String,Object> paramMap = new HashMap<String, Object>();
			List<Long> userInfoIds = new ArrayList<Long>();
			if("10".equals(type)) {
				for (int i = 1; i <= lostRowNum; i++) {
					Row row = sheet.getRow(i);
					String dimensionKey = getCellValue(row,0);
					String dimensionValue = getCellValue(row,1);
					values = new ArrayList<String>();
					values.add(dimensionKey);
					values.add(dimensionValue);
					if((StringUtil.equals(BlacklistConstant.DIMENSION_KEY_IDNO, dimensionKey) || StringUtil.equals(BlacklistConstant.DIMENSION_KEY_PHONE, dimensionKey)) && StringUtil.isNotBlank(dimensionValue)) {
						paramMap.clear();
						paramMap.put("dimensionkey", dimensionKey);
						paramMap.put("dimensionvalue", dimensionValue);
						paramMap.put("status",BlacklistConstant.BLACK_LIST_STATUS_NORMAL);
						paramMap.put("lastmodifytime",new Date());
						nameBlacklistMapper.updateNameBlacklistStatus(paramMap);

						paramMap.clear();
						paramMap.put("dimensionkey", dimensionKey);
						paramMap.put("dimensionvalue", dimensionValue);
						paramMap.put("source", BlacklistConstant.SOURCE_IP);
						NameBlacklist nameBlack = nameBlacklistMapper.findSelective(paramMap);
						if(nameBlack == null) {
							nameBlack = new NameBlacklist();
							nameBlack.setCreatetime(date);
							nameBlack.setLastmodifytime(date);
							nameBlack.setDimensionkey(dimensionKey);
							nameBlack.setDimensionvalue(dimensionValue);
							nameBlack.setSource(BlacklistConstant.SOURCE_IP);
							nameBlack.setStatus(BlacklistConstant.BLACK_LIST_STATUS_NORMAL);
							nameBlacklistMapper.save(nameBlack);

							//需要更新的用户信息
							paramMap.clear();
							if(StringUtil.equals(BlacklistConstant.DIMENSION_KEY_IDNO, dimensionKey)) {
								paramMap.put("idNo", row.getCell(1).toString());
							} else {
								paramMap.put("phone", row.getCell(1).toString());
							}
							UserBaseInfo userInfo = userBaseInfoMapper.findSelective(paramMap);
							if(userInfo != null){
								userInfoIds.add(userInfo.getId());
							}
							values.add("已处理");
						} else {
							values.add("未处理，已存在对应信息");
						}
					}else {
						values.add("未处理，格式错误");
					}
					nameInfos.add(values);
				}
			} else {
				for (int i = 1; i <= lostRowNum; i++) {
					Row row = sheet.getRow(i);
					String dimensionKey = getCellValue(row,0);
					String dimensionValue = getCellValue(row,1);
					values = new ArrayList<String>();
					values.add(dimensionKey);
					values.add(dimensionValue);
					if((StringUtil.equals(BlacklistConstant.DIMENSION_KEY_IDNO, dimensionKey) || StringUtil.equals(BlacklistConstant.DIMENSION_KEY_PHONE, dimensionKey)) && StringUtil.isNotBlank(dimensionValue)) {
						paramMap.clear();
						paramMap.put("dimensionkey", dimensionKey);
						paramMap.put("dimensionvalue", dimensionValue);
						paramMap.put("source", BlacklistConstant.SOURCE_IP);
						NameWhitelist nameWhite = nameWhitelistMapper.findSelective(paramMap);
						if(nameWhite == null) {
							nameWhite = new NameWhitelist();
							nameWhite.setCreatetime(date);
							nameWhite.setLastmodifytime(date);
							nameWhite.setDimensionkey(dimensionKey);
							nameWhite.setDimensionvalue(dimensionValue);
							nameWhite.setSource(BlacklistConstant.SOURCE_IP);
							nameWhite.setStatus(BlacklistConstant.BLACK_LIST_STATUS_NORMAL);
							nameWhitelistMapper.save(nameWhite);

							//需要更新的用户信息
							paramMap.clear();
							if(StringUtil.equals(BlacklistConstant.DIMENSION_KEY_IDNO, dimensionKey)) {
								paramMap.put("idNo", row.getCell(1).toString());
							} else {
								paramMap.put("phone", row.getCell(1).toString());
							}
							UserBaseInfo userInfo = userBaseInfoMapper.findSelective(paramMap);
							if(userInfo != null){
								userInfoIds.add(userInfo.getId());
							}
							values.add("已处理");
						} else if(nameWhite != null) {
							values.add("未处理，已存在对应信息");
						}
					} else {
						values.add("未处理，格式错误");
					}
					nameInfos.add(values);
				}
			}
			if(userInfoIds.size()>0){
				if("10".equals(type)){
					userBaseInfoMapper.updateBlackIdNos(userInfoIds);
				}else if("20".equals(type)){
					userBaseInfoMapper.updateWhiteIdNos(userInfoIds);
				}
			}

		}
		return nameInfos;
	}

	private int CheckRowError(Row row,int i,List<String> values){
		for(int j=0;j<i;j++){
			row.getCell(j).setCellType(row.getCell(j).CELL_TYPE_STRING);
			if(row.getCell(j)==null|| row.getCell(j).equals("")||row.getCell(j).getCellType()== HSSFCell.CELL_TYPE_BLANK){
			   
			}
            
		}
		return 1;
		
	}
	private String getCellValue(Row row,int j){
		String cellValue ="";
		if(row.getCell(j)==null|| row.getCell(j).equals("")||row.getCell(j).getCellType()== HSSFCell.CELL_TYPE_BLANK){
		  cellValue =""; 
		} else {
			row.getCell(j).setCellType(row.getCell(j).CELL_TYPE_STRING);
			cellValue =  row.getCell(j).getStringCellValue();
		}
		return cellValue;
		
		
	}
}
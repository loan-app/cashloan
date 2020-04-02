package com.xiji.cashloan.cl.service.impl;

import com.xiji.cashloan.cl.service.NameMultiService;
import com.xiji.cashloan.cl.util.black.CollectionUtil;
import com.xiji.cashloan.cl.util.black.JSONUtil;
import com.xiji.cashloan.cl.util.black.LogCode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


/**
 * 黑名单ServiceImpl
 * 
 * @author king
 * @version 1.0.0
 * @date 2018-12-10 16:29:50
 */
 
@Service("nameMultiService")
public class NameMultiServiceImpl implements NameMultiService {
	private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(NameMultiServiceImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public JSONObject dealBusiness(Map<String, String> params) {
		logger.info("开始执行dealBusiness 方法！");

		JSONObject result = new JSONObject();
		result.put(LogCode.RESULT_CODE, LogCode.NORMAL_CODE);

		if (!checkParam(result,params)) {
			return result;
		}

		String sql = getExecuteSql(params);
		Object[] args = getArgs(params);

		List<Map<String, Object>> datas;

		if (StringUtils.isEmpty(sql)){
			logger.error("获取sql异常！");
			result.put(LogCode.RESULT_CODE, LogCode.ERROR_CODE);
			return result;
		}
		datas = jdbcTemplate.queryForList(sql, args);

		processResult(params, datas, result);
		result.put("match_time", System.currentTimeMillis());
		return result;
	}

	public boolean checkParam(JSONObject result, Map<String, String> params) {
		String value = params.get("dimensionValue");
		String source = params.get("source");
		String tableName = params.get("tableName");

		//输入参数验证，不能为空
		if (StringUtils.isBlank(source) || StringUtils.isBlank(value)) {
			logger.info("tableName:" + tableName + ",input params is empty(source:" + source + ",dimensionValue:" + value
				+ ")");
			result.put(LogCode.RESULT_CODE, LogCode.IS_NULL_CODE);
			return false;
		}
		return true;
	}

	public Object[] getArgs(Map<String, String> params) {
		String value = params.get("dimensionValue");
		String source = params.get("source");
		List<Object> args = new ArrayList();
		//多维度数据解析
		Map<String, DimensionInfo> dimensionInfos = dimensionParse(value);
		for (Entry<String, DimensionInfo> dimensionInfo : dimensionInfos.entrySet()) {
			DimensionInfo item = dimensionInfo.getValue();
			if (item != null) {
				args.add(item.getDimensionKey());
				args.add(item.getDimensionValue());
			}
		}

		if (!LogCode.isAllSource(source)) { //所有来源
			String[] sources = StringUtils.split(source, ",");
			for (String item : sources) {
				args.add(item);
			}
		}

		return args.toArray();
	}
	public void processResult(Map<String, String> params, List<Map<String, Object>> midResults, JSONObject result) {
		boolean isHit = false;
		String tableName = params.get("tableName");

		if (CollectionUtil.isNotEmpty(midResults)) {
			logger.info("tableName:" + tableName + ",rows size:" + midResults.size());
			for (Map<String, Object> item : midResults) {
				Object tmpObject = item.get("status");
				if (tmpObject != null) {
					int status = NumberUtils.toInt(tmpObject.toString(), 1);
					if (status != 1) { //未删除的
						isHit = true;
						logger.info("isTrue source:" + item.get("source"));
						break;
					}
				}
			}
		}
		result.put(LogCode.RESULT_CODE, LogCode.NORMAL_CODE);
		result.put("match_result", isHit);
		result.put("match_time", System.currentTimeMillis());
	}

	public String getExecuteSql(Map<String, String> params) {
		String value = params.get("dimensionValue");
		String source = params.get("source");
		String tableName = params.get("tableName");
		//多维度数据解析
		Map<String, DimensionInfo> dimensionInfos = dimensionParse(value);
		logger.info("dimensionInfos size:" + dimensionInfos.size());
		if (CollectionUtil.isEmpty(dimensionInfos)) {
			return null;
		}
		StringBuffer sql = new StringBuffer();
		sql.append("select status, source from ").append(tableName).append(" where ");
		sql.append(" (");
		for (Entry<String, DimensionInfo> dimensionInfo : dimensionInfos.entrySet()) {
			DimensionInfo tmp = dimensionInfo.getValue();
			if (tmp != null) {
				sql.append(" (dimensionKey = ? and dimensionValue = ? ) or ");
			}
		}
		sql.setLength(sql.length() - 3);
		sql.append(") ");
		if (!LogCode.isAllSource(source)) { //所有来源
			String[] sources = StringUtils.split(source, ",");
			sql.append(" and source in(");
			for (int i = 0; i < sources.length; i++) {
				if (i > 0) {
					sql.append(",");
				}
				sql.append("?");
			}
			sql.append(")");
		}
		return sql.toString();
	}
	private Map<String, DimensionInfo> dimensionParse(String value) {
		Map<String, DimensionInfo> dimensionInfos = new HashMap<String, DimensionInfo>();
		//输入参数是jsonarray（批量）
		if (JSONUtil.isJsonArray(value)) {
			JSONArray array = new JSONArray(value);
			for (int i = 0; i < array.length(); i++) {
				JSONObject tmpObj = null;
				try {
					tmpObj = array.getJSONObject(i);
				} catch (Exception e) {
					String tmpStr = array.getString(i);
					try {
						tmpObj = new JSONObject(tmpStr);
					} catch (Exception e1) {
					}
				}
				if (tmpObj != null) {
					String dimensionKey = tmpObj.optString("dimensionKey", "");
					String dimensionValue = tmpObj.optString("dimensionValue", "");
					if (StringUtils.isBlank(dimensionKey) || StringUtils.isBlank(dimensionValue)) {
						continue;
					}
					DimensionInfo dimensionInfo = new DimensionInfo();
					dimensionInfo.setDimensionKey(dimensionKey);
					dimensionInfo.setDimensionValue(dimensionValue);
					dimensionInfos.put(dimensionInfo.getKey(), dimensionInfo);
				}
			}
		}
		return dimensionInfos;
	}
	static class DimensionInfo {

		private String dimensionKey = "";          //维度
		private String dimensionValue = "";        //值

		public String getDimensionKey() {
			return dimensionKey;
		}

		public void setDimensionKey(String dimensionKey) {
			this.dimensionKey = dimensionKey;
		}

		public String getDimensionValue() {
			return dimensionValue;
		}

		public void setDimensionValue(String dimensionValue) {
			this.dimensionValue = dimensionValue;
		}

		public String getKey() {
			return dimensionKey + "-" + dimensionValue;
		}
	}
	
}
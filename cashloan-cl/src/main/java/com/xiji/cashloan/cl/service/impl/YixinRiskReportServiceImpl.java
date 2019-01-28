package com.xiji.cashloan.cl.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiji.cashloan.cl.domain.YixinRiskReport;
import com.xiji.cashloan.cl.mapper.YixinRiskReportMapper;
import com.xiji.cashloan.cl.service.YixinRiskReportService;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.core.common.util.StringUtil;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


/**
 * 宜信风险评估ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-25 16:26:48
 */
 
@Service("yixinRiskReportService")
public class YixinRiskReportServiceImpl extends BaseServiceImpl<YixinRiskReport, Long> implements YixinRiskReportService {
	
    private static final Logger logger = LoggerFactory.getLogger(YixinRiskReportServiceImpl.class);
   
    @Resource
    private YixinRiskReportMapper yixinRiskReportMapper;

	@Override
	public BaseMapper<YixinRiskReport, Long> getMapper() {
		return yixinRiskReportMapper;
	}


	/**
	 * 根据借款id 获取风险评估报告
	 * @param borrowId
	 * @return
	 */
	@Override
	public Map<String,Object> getRecentlyYixinRiskReportMap(Long borrowId){
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("borrowId", borrowId);
		YixinRiskReport yixinRiskReport = yixinRiskReportMapper.findSelective(queryMap);
		if (yixinRiskReport == null || yixinRiskReport.getData() == null){
			return null;
		}

		JSONArray loanRecordsJsonArray = JSON.parseObject(yixinRiskReport.getData()).getJSONArray("loanRecords");
		Set<String> orgNames = new HashSet<>();
		Set<String> borrowOrgNames = new HashSet<>();
		// 借款申请已同意数
		int countApprovalAccept = 0;
		// 借款申请笔数
		int countBorrowApply = 0;
		// 历史逾期数
		int countOverdueHistory = 0;
		// M3历史逾期数
		int countOverdueHistoryM3 = 0;
		// M6历史逾期数
		int countOverdueHistoryM6 = 0;
		//
		if (loanRecordsJsonArray != null){
			Iterator iterator = loanRecordsJsonArray.iterator();
			while (iterator.hasNext()){
				countBorrowApply = countBorrowApply +1;
				String str = iterator.next().toString();
				JSONObject json = JSON.parseObject(str);
				if (json.get("approvalStatus") != null){
					String result = json.get("approvalStatus").toString();
					if ("ACCEPT".equals(result)){
						countApprovalAccept = countApprovalAccept +1;
						borrowOrgNames.add(JSON.parseObject(str).get("orgName").toString());
					}
				}
				countBorrowApply = countBorrowApply +1;
				orgNames.add(JSON.parseObject(str).get("orgName").toString());
				if (json.get("overdueM3") != null) {
					countOverdueHistoryM3 = countOverdueHistoryM3 +1;
				}
				if (json.get("overdueM6") != null) {
					countOverdueHistoryM6 = countOverdueHistoryM6 +1;
				}
				if (StringUtil.isNotBlank(json.getString("overdueStatus"))) {
					countOverdueHistory = countOverdueHistory + 1;
				}
			}
		}

		Map<String,Object> map = new HashedMap();
		// 借款机构数
		int countCorporateBorrower = orgNames.size();
		// 审批机构数
		int countApprovalMechanism = borrowOrgNames.size();

		// 借款机构数
		map.put("countCorporateBorrower",countCorporateBorrower+"");
		// 审批机构数
		map.put("countApprovalMechanism",countApprovalMechanism+"");
		// 审批放款笔数
		map.put("countApprovalAccept",countApprovalAccept+"");
		// 借款申请笔数
		map.put("countBorrowApply",countBorrowApply+"");
		// 历史逾期数
		map.put("countOverdueHistory",countOverdueHistory+"");
        // M3历史逾期数
		map.put("countOverdueHistoryM3",countOverdueHistoryM3+"");
        // M6历史逾期数
		map.put("countOverdueHistoryM6",countOverdueHistoryM6+"");
        // 风险评估报告
		map.put("yixinRiskReport",JSON.parseObject(yixinRiskReport.getData()));
     return map;
	}

}
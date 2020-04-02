package com.jiya.cashloan.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.xiji.cashloan.cl.domain.YixinRiskReport;
import com.xiji.cashloan.core.common.util.HttpsUtil;
import org.apache.commons.collections.map.HashedMap;

import java.util.*;

/**
 * @Auther: king
 * @Date: 2018/12/3 23:38
 * @Description:
 */
public class ApiServiceTest {

    public static void main(String[] args) {
        //testfindProgress();
//        testconfirmPay();
        //testfindProgress_wnb();
        testWnb();
    }


    public static void testfindProgress() {
        String url = "http://47.110.61.233:8080/api/act/mine/borrow/findProgress.htm";
        Map<String,String> param = new HashMap<String, String>();
        param.put("web","debug");
        param.put("borrowId","5");
        System.out.println(JSON.toJSON(param));
        String resp = HttpsUtil.postClient(url, param);
        System.out.println();
        System.out.println(resp);
    }

    public static void testfindProgress_wnb() {
        String msg = "[\n" +
                "  {\n" +
                "    \"versionName\" : \"1\",\n" +
                "    \"appName\" : \"weixin\",\n" +
                "    \"packageName\" : \"2\",\n" +
                "    \"lastUpdateTime \" : \"3\",\n" +
                "    \"firstInstallTime\" : \"4\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"versionName\" : \"1\",\n" +
                "    \"appName\" : \"mqq\",\n" +
                "    \"packageName\" : \"1\",\n" +
                "    \"lastUpdateTime \" : \"1\",\n" +
                "    \"firstInstallTime\" : \"1\"\n" +
                "  }\n" +
                "]";

        JSONArray jsonArray = JSONArray.parseArray(msg);

        Iterator  iterator = jsonArray.iterator();

        while (iterator.hasNext()){

            String s = iterator.next().toString();
            System.out.println(JSON.parseObject(s).get("versionName"));
            System.out.println(JSON.parseObject(s).get("appName"));
            System.out.println(JSON.parseObject(s).get("packageName"));
            System.out.println(JSON.parseObject(s).get("lastUpdateTime "));
            System.out.println(JSON.parseObject(s).get("firstInstallTime"));


        }

    }


    public static void testWnb(){

        YixinRiskReport yixinRiskReport = new YixinRiskReport();
        yixinRiskReport.setData("{\"loanRecords\":[{\"approvalStatus\":\"REJECT\",\"idNo\":\"130823199301106510\",\"loanAmount\":\"(0,1000]\",\"loanDate\":\"201810\",\"loanType\":\"CREDIT\",\"name\":\"宋昱龙\",\"orgName\":\"126\",\"overdueAmount\":\"\",\"periods\":1},{\"approvalStatus\":\"REJECT\",\"idNo\":\"130823199301106510\",\"loanAmount\":\"(1000,5000]\",\"loanDate\":\"201812\",\"loanType\":\"CREDIT\",\"name\":\"宋昱龙\",\"orgName\":\"456\",\"overdueAmount\":\"\",\"periods\":1},{\"approvalStatus\":\"REJECT\",\"idNo\":\"130823199301106510\",\"loanAmount\":\"(0,1000]\",\"loanDate\":\"201805\",\"loanType\":\"CREDIT\",\"name\":\"宋昱龙\",\"orgName\":\"496\",\"overdueAmount\":\"\",\"periods\":1},{\"approvalStatus\":\"REJECT\",\"idNo\":\"130823199301106510\",\"loanAmount\":\"(1000,5000]\",\"loanDate\":\"201810\",\"loanType\":\"CREDIT\",\"name\":\"宋昱龙\",\"orgName\":\"213\",\"overdueAmount\":\"\",\"periods\":1},{\"approvalStatus\":\"ACCEPT\",\"idNo\":\"130823199301106510\",\"loanAmount\":\"(1000,5000]\",\"loanDate\":\"201811\",\"loanStatus\":\"COMPLETED\",\"loanType\":\"CREDIT\",\"name\":\"宋昱龙\",\"orgName\":\"213\",\"overdueAmount\":\"(5000,10000]\",\"overdueM3\":1,\"overdueM6\":1,\"overdueStatus\":\"M3+\",\"overdueTotal\":7,\"periods\":1},{\"approvalStatus\":\"ACCEPT\",\"idNo\":\"130823199301106510\",\"loanAmount\":\"(1000,5000]\",\"loanDate\":\"201812\",\"loanStatus\":\"COMPLETED\",\"loanType\":\"CREDIT\",\"name\":\"宋昱龙\",\"orgName\":\"213\",\"overdueAmount\":\"(5000,10000]\",\"overdueM3\":1,\"overdueM6\":1,\"overdueStatus\":\"M3+\",\"overdueTotal\":7,\"periods\":1},{\"approvalStatus\":\"REJECT\",\"idNo\":\"130823199301106510\",\"loanAmount\":\"(1000,5000]\",\"loanDate\":\"201812\",\"loanType\":\"CREDIT\",\"name\":\"宋昱龙\",\"orgName\":\"213\",\"overdueAmount\":\"\",\"periods\":1},{\"approvalStatus\":\"ACCEPT\",\"idNo\":\"130823199301106510\",\"loanAmount\":\"(1000,5000]\",\"loanDate\":\"201812\",\"loanStatus\":\"NORMAL\",\"loanType\":\"CREDIT\",\"name\":\"宋昱龙\",\"orgName\":\"213\",\"overdueAmount\":\"(5000,10000]\",\"overdueM3\":1,\"overdueM6\":1,\"overdueStatus\":\"M3+\",\"overdueTotal\":7,\"periods\":1},{\"approvalStatus\":\"ACCEPT\",\"idNo\":\"130823199301106510\",\"loanAmount\":\"(1000,5000]\",\"loanDate\":\"201812\",\"loanStatus\":\"NORMAL\",\"loanType\":\"CREDIT\",\"name\":\"宋昱龙\",\"orgName\":\"213\",\"overdueAmount\":\"(5000,10000]\",\"overdueM3\":1,\"overdueTotal\":7,\"periods\":1},{\"approvalStatus\":\"REJECT\",\"idNo\":\"130823199301106510\",\"loanAmount\":\"(1000,5000]\",\"loanDate\":\"201809\",\"loanType\":\"CREDIT\",\"name\":\"宋昱龙\",\"orgName\":\"213\",\"overdueAmount\":\"\",\"periods\":1},{\"approvalStatus\":\"REJECT\",\"idNo\":\"130823199301106510\",\"loanAmount\":\"(0,1000]\",\"loanDate\":\"201809\",\"loanType\":\"CREDIT\",\"name\":\"宋昱龙\",\"orgName\":\"213\",\"overdueAmount\":\"\",\"periods\":1},{\"approvalStatus\":\"REJECT\",\"idNo\":\"130823199301106510\",\"loanAmount\":\"(1000,5000]\",\"loanDate\":\"201809\",\"loanType\":\"CREDIT\",\"name\":\"宋昱龙\",\"orgName\":\"213\",\"overdueAmount\":\"\",\"periods\":1},{\"approvalStatus\":\"ACCEPT\",\"idNo\":\"130823199301106510\",\"loanAmount\":\"(1000,5000]\",\"loanDate\":\"201810\",\"loanStatus\":\"COMPLETED\",\"loanType\":\"CREDIT\",\"name\":\"宋昱龙\",\"orgName\":\"213\",\"overdueAmount\":\"\",\"periods\":1},{\"approvalStatus\":\"REJECT\",\"idNo\":\"130823199301106510\",\"loanAmount\":\"(0,1000]\",\"loanDate\":\"201811\",\"loanType\":\"CREDIT\",\"name\":\"宋昱龙\",\"orgName\":\"288\",\"overdueAmount\":\"\",\"periods\":1},{\"approvalStatus\":\"ACCEPT\",\"idNo\":\"130823199301106510\",\"loanAmount\":\"(1000,5000]\",\"loanDate\":\"201812\",\"loanStatus\":\"COMPLETED\",\"loanType\":\"CREDIT\",\"name\":\"宋昱龙\",\"orgName\":\"288\",\"overdueAmount\":\"\",\"periods\":1},{\"approvalStatus\":\"ACCEPT\",\"idNo\":\"130823199301106510\",\"loanAmount\":\"(1000,5000]\",\"loanDate\":\"201812\",\"loanStatus\":\"NORMAL\",\"loanType\":\"CREDIT\",\"name\":\"宋昱龙\",\"orgName\":\"288\",\"overdueAmount\":\"\",\"periods\":1},{\"approvalStatus\":\"ACCEPT\",\"idNo\":\"130823199301106510\",\"loanAmount\":\"(1000,5000]\",\"loanDate\":\"201811\",\"loanStatus\":\"COMPLETED\",\"loanType\":\"CREDIT\",\"name\":\"宋昱龙\",\"orgName\":\"37\",\"overdueAmount\":\"\",\"periods\":1},{\"approvalStatus\":\"CUSTOMER_REJECT\",\"idNo\":\"130823199301106510\",\"loanAmount\":\"(1000,5000]\",\"loanDate\":\"201812\",\"loanType\":\"CREDIT\",\"name\":\"宋昱龙\",\"orgName\":\"37\",\"overdueAmount\":\"\",\"periods\":1},{\"approvalStatus\":\"ACCEPT\",\"idNo\":\"130823199301106510\",\"loanAmount\":\"(1000,5000]\",\"loanDate\":\"201812\",\"loanStatus\":\"COMPLETED\",\"loanType\":\"CREDIT\",\"name\":\"宋昱龙\",\"orgName\":\"37\",\"overdueAmount\":\"\",\"periods\":1},{\"approvalStatus\":\"ACCEPT\",\"idNo\":\"130823199301106510\",\"loanAmount\":\"(1000,5000]\",\"loanDate\":\"201812\",\"loanStatus\":\"COMPLETED\",\"loanType\":\"CREDIT\",\"name\":\"宋昱龙\",\"orgName\":\"37\",\"overdueAmount\":\"\",\"periods\":1},{\"approvalStatus\":\"CUSTOMER_REJECT\",\"idNo\":\"130823199301106510\",\"loanAmount\":\"(1000,5000]\",\"loanDate\":\"201812\",\"loanType\":\"CREDIT\",\"name\":\"宋昱龙\",\"orgName\":\"37\",\"overdueAmount\":\"\",\"periods\":1},{\"approvalStatus\":\"IN_PROGRESS\",\"idNo\":\"130823199301106510\",\"loanAmount\":\"(1000,5000]\",\"loanDate\":\"201812\",\"loanType\":\"CREDIT\",\"name\":\"宋昱龙\",\"orgName\":\"37\",\"overdueAmount\":\"\",\"periods\":1}],\"queriedHistory\":{\"checkedRecords\":[{\"orgName\":\"000\",\"orgType\":\"NONE_LICENSED_CASH_LOAN\",\"queryReason\":\"LOAN_AUDIT\",\"time\":\"2018-12-27\"},{\"orgName\":\"37\",\"orgType\":\"NONE_LICENSED_CASH_LOAN\",\"queryReason\":\"LOAN_AUDIT\",\"time\":\"2018-12-27\"},{\"orgName\":\"213\",\"orgType\":\"NONE_LICENSED_CASH_LOAN\",\"queryReason\":\"LOAN_AUDIT\",\"time\":\"2018-12-24\"},{\"orgName\":\"288\",\"orgType\":\"NONE_LICENSED_CASH_LOAN\",\"queryReason\":\"LOAN_AUDIT\",\"time\":\"2018-12-17\"},{\"orgName\":\"496\",\"orgType\":\"NONE_LICENSED_CASH_LOAN\",\"queryReason\":\"LOAN_AUDIT\",\"time\":\"2018-12-10\"},{\"orgName\":\"282\",\"orgType\":\"NONE_LICENSED_CASH_LOAN\",\"queryReason\":\"LOAN_AUDIT\",\"time\":\"2018-10-08\"},{\"orgName\":\"126\",\"orgType\":\"NONE_LICENSED_CONSUMPTION_PERIOD\",\"queryReason\":\"LOAN_AUDIT\",\"time\":\"2018-10-01\"},{\"orgName\":\"4\",\"orgType\":\"NONE_LICENSED_CASH_LOAN\",\"queryReason\":\"LOAN_AUDIT\",\"time\":\"2018-09-30\"},{\"orgName\":\"456\",\"orgType\":\"NONE_LICENSED_CONSUMPTION_PERIOD\",\"queryReason\":\"LOAN_AUDIT\",\"time\":\"2018-09-29\"},{\"orgName\":\"43\",\"orgType\":\"NONE_LICENSED_CASH_LOAN\",\"queryReason\":\"LOAN_AUDIT\",\"time\":\"2018-09-14\"},{\"orgName\":\"377\",\"orgType\":\"P2P\",\"queryReason\":\"LOAN_AUDIT\",\"time\":\"2018-09-08\"},{\"orgName\":\"192\",\"orgType\":\"P2P\",\"queryReason\":\"LOAN_AUDIT\",\"time\":\"2018-07-05\"}],\"orgCountTotal\":27,\"otherOrgCount\":11,\"timesByCurrentOrg\":1},\"riskResults\":[]}");
        //String msg = JSON.parseObject(yixinRiskReport.getData()).getString("data");

        //JSONArray loanRecordsJsonArray = JSON.parseObject(yixinRiskReport.getData()).getJSONArray("loanRecords");

        String loanRecords = JSON.parseObject(yixinRiskReport.getData()).getJSONArray("loanRecords").toJSONString();
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
        // JSONArray
        if (loanRecordsJsonArray != null){
            Iterator iterator = loanRecordsJsonArray.iterator();
            while (iterator.hasNext()){
                    String str = iterator.next().toString();
                    if (JSON.parseObject(str).get("approvalStatus") != null){
                        String result = JSON.parseObject(str).get("approvalStatus").toString();
                        if ("ACCEPT".equals(result)){
                            countApprovalAccept = countApprovalAccept +1;
                            borrowOrgNames.add(JSON.parseObject(str).get("orgName").toString());
                        }
                    }
                    countBorrowApply = countBorrowApply +1;
                    orgNames.add(JSON.parseObject(str).get("orgName").toString());

                    if (JSON.parseObject(str).get("overdueM3") != null){
                        countOverdueHistoryM3 = countOverdueHistoryM3 +1;
                    }

                    if (JSON.parseObject(str).get("overdueM6") != null){
                        countOverdueHistoryM6 = countOverdueHistoryM6 +1;
                    }

                    if (JSON.parseObject(str).get("overdueTotal") != null){
                        countOverdueHistory = countOverdueHistory +1;
                    }
            }
        }

        Map<String,String> map = new HashedMap();
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
        map.put("yixinRiskReport",yixinRiskReport.getData());

        System.out.println("借款机构数:"+map.get("countCorporateBorrower"));
        System.out.println("审批机构数"+map.get("countApprovalMechanism"));
        System.out.println("审批放款笔数:"+map.get("countApprovalAccept"));
        System.out.println("借款申请笔数:"+map.get("countBorrowApply"));
        System.out.println("历史逾期数:"+map.get("countOverdueHistory"));
        System.out.println("M3历史逾期数:"+map.get("countOverdueHistoryM3"));
        System.out.println("M6历史逾期数:"+map.get("countOverdueHistoryM6"));
        //System.out.println("msg:"+msg);
        System.out.println("loanRecords:"+loanRecords);

        System.out.println("orgNames");
        for (String s : orgNames){
            System.out.println(s);
        }
        System.out.println("borrowOrgNames");
        for(String s :borrowOrgNames){
            System.out.println(s);
        }
    }
    public static void testfindIndex() {
        String url = "http://47.110.61.233:8080/api/borrow/findIndex.htm";
        Map<String,String> param = new HashMap<String, String>();
        param.put("web","debug");
        param.put("borrowId","5");
        System.out.println(JSON.toJSON(param));
        String resp = HttpsUtil.postClient(url, param);
        System.out.println();
        System.out.println(resp);
    }

    public static void testconfirmPay() {
        String url = "http://47.110.61.233:8080/api/act/borrow/repay/confirmPay.htm";
        Map<String,String> param = new HashMap<String, String>();
        param.put("web","debug");
        param.put("borrowId","5");
        param.put("ip","192.10.10.12");
        param.put("type","2");
        System.out.println(JSON.toJSON(param));
        String resp = HttpsUtil.postClient(url, param);
        System.out.println();
        System.out.println(resp);
    }
}

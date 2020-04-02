package com.xiji.cashloan.cl.model.xindedata;

import java.util.List;

/**
 * @Auther: king
 * @Date: 2018/12/19 12:05
 * @Description:
 */
public class XindeResponse {
    private String tid;
    private String status;//查询结果状态(done: 成功; failed: 失败)
    private List<XdBlackResult> result;//灰名单结果
    private List<XdAdvanceResult> advanceResult;//多维数据

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<XdBlackResult> getResult() {
        return result;
    }

    public void setResult(List<XdBlackResult> result) {
        this.result = result;
    }

    public List<XdAdvanceResult> getAdvanceResult() {
        return advanceResult;
    }

    public void setAdvanceResult(List<XdAdvanceResult> advanceResult) {
        this.advanceResult = advanceResult;
    }
}

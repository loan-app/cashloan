package com.xiji.cashloan.api.controllerdev;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.OperatorReport;
import com.xiji.cashloan.cl.domain.OperatorVoiceCnt;
import com.xiji.cashloan.cl.service.OperatorReportService;
import com.xiji.cashloan.cl.service.OperatorVoiceCntService;
import com.xiji.cashloan.core.common.web.controller.BaseController;
import java.io.IOException;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Auther: king
 * @Date: 2018/11/28 16:31
 * @Description:
 */
@Controller
@Scope("prototype")
public class VoiceCntFixController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(VoiceCntFixController.class);
    @Resource
    private OperatorReportService operatorReportService;
    @Resource
    private OperatorVoiceCntService operatorVoiceCntService;

    @RequestMapping(value = "/voicecnt/fix.htm",method = {RequestMethod.POST, RequestMethod.GET})
    public void task(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long reportId = Long.parseLong(req.getParameter("orderId"));
            for (long i = 1;i < reportId;i++) {
                OperatorReport oldReport = operatorReportService.getById(i);
                if (oldReport != null) {
                    Page<OperatorVoiceCnt> page = operatorVoiceCntService.page(oldReport.getUserId(), 1, 5);
                    if (page.size() > 0) {
                        continue;
                    }
                    operatorVoiceCntService
                        .paserReportDetail(oldReport.getReport(), oldReport.getUserId(), new Date(), oldReport.getReqLogId());
                }
            }

            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf8");
            resp.getOutputStream().write("success".getBytes("utf8"));
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            resp.getOutputStream().write(e.getMessage().getBytes("UTF-8"));
        }
    }

}
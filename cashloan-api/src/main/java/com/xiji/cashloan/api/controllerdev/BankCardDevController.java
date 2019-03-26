package com.xiji.cashloan.api.controllerdev;

import com.alibaba.fastjson.JSON;
import com.xiji.cashloan.cl.domain.BankCard;
import com.xiji.cashloan.cl.service.BankCardService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Auther: king
 * @Date: 2019/1/31 11:27
 * @Description:
 */
@Controller
public class BankCardDevController {
    @Resource
    private BankCardService bankCardService;
    @RequestMapping(value = "/bankcard/query.htm",method = {RequestMethod.POST, RequestMethod.GET})
    public void task(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String userId = req.getParameter("userId");
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("userId", userId);
            BankCard bankCard = bankCardService.findSelective(paramMap);
                resp.setContentType("application/json");
            resp.setCharacterEncoding("utf8");
            resp.getOutputStream().write(JSON.toJSONString(bankCard).getBytes("utf8"));
        } catch (Exception e) {
            resp.getOutputStream().write(e.getMessage().getBytes("UTF-8"));
        }
    }
}

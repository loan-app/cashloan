package com.xiji.cashloan.cl.manage;

import com.xiji.cashloan.cl.domain.BankCard;
import com.xiji.cashloan.cl.mapper.BankCardMapper;
import com.xiji.cashloan.cl.model.pay.common.PayCommonUtil;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @Auther: king
 * @Date: 2019/1/31 11:07
 * @Description:
 */
@Service
public class BankCardManage implements BankCardMapper {
    @Resource
    private BankCardMapper bankCardMapper;

    @Override
    public int save(BankCard e) {
        if (e != null) {
            e.setAgreeCompany(PayCommonUtil.payCompany(e.getUserId()));
        }
        return bankCardMapper.save(e);
    }

    @Override
    public void saveRecord(BankCard e) {
        if (e != null) {
            e.setAgreeCompany(PayCommonUtil.payCompany(e.getUserId()));
        }
        bankCardMapper.saveRecord(e);
    }


    @Override
    public int update(BankCard e) {
        if (e != null) {
            e.setAgreeCompany(PayCommonUtil.payCompany(e.getUserId()));
        }
        return bankCardMapper.update(e);
    }

    @Override
    public int updateSelective(Map<String, Object> paramMap) {
        long userId = 0;
        if (paramMap.get("userId") != null){
            userId = Long.valueOf(paramMap.get("userId").toString());
        }
        paramMap.put("agreeCompany",PayCommonUtil.payCompany(userId));
        return bankCardMapper.updateSelective(paramMap);
    }
    @Override
    public int updatecondition(BankCard e) {
        if (e != null) {
            e.setAgreeCompany(PayCommonUtil.payCompany(e.getUserId()));
        }
        return bankCardMapper.updatecondition(e);
    }

    @Override
    public int updateconditionSelective(Map<String, Object> paramMap) {
        long userId = 0;
        if (paramMap.get("userId") != null){
            userId = Long.valueOf(paramMap.get("userId").toString());
        }
        paramMap.put("agreeCompany",PayCommonUtil.payCompany(userId));
        return bankCardMapper.updateconditionSelective(paramMap);
    }

    @Override
    public BankCard findSelective(Map<String, Object> paramMap) {
        long userId = 0;
        if (paramMap.get("userId") != null){
            userId = Long.valueOf(paramMap.get("userId").toString());
        }
        paramMap.put("agreeCompany",PayCommonUtil.payCompany(userId));
        return bankCardMapper.findSelective(paramMap);
    }

    @Override
    public BankCard findByPrimary(Long primary) {
        return bankCardMapper.findByPrimary(primary);
    }

    @Override
    public List<BankCard> listSelective(Map<String, Object> paramMap) {
        if (paramMap != null) {
            long userId = 0;
            if (paramMap.get("userId") != null){
                userId = Long.valueOf(paramMap.get("userId").toString());
            }
            paramMap.put("agreeCompany",PayCommonUtil.payCompany(userId));
        }
        return bankCardMapper.listSelective(paramMap);
    }

    @Override
    public BankCard findByUserId(Map<String, Object> paramMap) {
        if (paramMap != null) {
            long userId = 0;
            if (paramMap.get("userId") != null){
                userId = Long.valueOf(paramMap.get("userId").toString());
            }
            paramMap.put("agreeCompany",PayCommonUtil.payCompany(userId));
        }
        return bankCardMapper.findByUserId(paramMap);
    }
}

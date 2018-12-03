package com.rongdu.cashloan.cl.service.impl;

import com.rongdu.cashloan.cl.domain.OperatorBasic;
import com.rongdu.cashloan.cl.service.MxOperatorBasicService;
import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Created by szb on 18/11/23.
 */
@Service("mxOperatorBasicService")
public class MxOperatorBasicServiceImpl extends BaseServiceImpl<OperatorBasic, Long> implements MxOperatorBasicService {


    @Override
    public BaseMapper<OperatorBasic, Long> getMapper() {
        return null;
    }
}

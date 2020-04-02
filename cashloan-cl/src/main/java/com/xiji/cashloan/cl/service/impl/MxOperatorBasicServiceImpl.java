package com.xiji.cashloan.cl.service.impl;

import com.xiji.cashloan.cl.domain.OperatorBasic;
import com.xiji.cashloan.cl.service.MxOperatorBasicService;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
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

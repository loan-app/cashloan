package com.xiji.cashloan.api.user.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

/**
 * @author wnb
 * @date 2018/12/03
 * @version 1.0.0
 */
@Service
@SuppressWarnings({ "rawtypes" })
public class MybatisService {
    @Resource
    private SqlSessionTemplate sessionTemplate;

    public List<Map> querySql(String statment, Object parameter) {
        return sessionTemplate.selectList(statment, parameter);
    }

    public Map queryRec(String statment, Object parameter) {
        return sessionTemplate.selectOne(statment, parameter);
    }
}

package com.xiji.cashloan.cl.model.tongdun.http;

import java.util.List;
import java.util.Observer;

import com.alibaba.fastjson.JSONObject;

/**
 * 对json的类型分别处理，统一定义操作接口
 **
 * @author wnb
 * @date 2018/11/30
 * @version 1.0.0
 */
public interface JsonNodeTypeParser extends Observer {


    /**
     * 当获取到目标字段时需要做的处理方法
     * @param list 当前key的上一级key的所有jsonObject集合
     * @param jsonKey 当前key的名称
     * @return
     * @throws Exception
     */
    Object onTargetNode(List<JSONObject> list, String jsonKey) throws Exception;


    /**
     * 当获取目标之前的节点时需要做处理的方法
     * @param list 当前key的上一级key的所有jsonObject集合
     * @param jsonKey 当前key的名称
     * @throws Exception
     */
    void onTargetPreviousNode(List<JSONObject> list, String jsonKey) throws Exception;

}

package com.xiji.cashloan.cl.model.tongdun.http;

import org.springframework.http.ResponseEntity;

/**
 * 接口响应数据解析 接口
 * @author wnb
 * @date 2018/11/30
 * @version 1.0.0
 *
 */
public interface HttpResponseParser<T extends HttpRestResponse> {


    T handle(ResponseEntity<String> responseBody) throws HttpRestException;


}

package com.xiji.cashloan.cl.model.tongdun.http;

import org.springframework.http.ResponseEntity;

/**
 * @author wnb
 * @date 2018/11/30
 * @version 1.0.0
 */
public class ResponseDataToJsonParser<T extends HttpRestResponse> implements HttpResponseParser<T>{

    private Class<T> clazz;

    public ResponseDataToJsonParser(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T handle(ResponseEntity<String> responseBody) throws HttpRestException {
        T response = null;
        int status = responseBody.getStatusCode().value();
        String body = responseBody.getBody();

        try {
            response = JsonFieldAutoPickGenerator.autoSetter(body, this.clazz);
            response.setBody(body);
            response.setCode(status);
            response.setMessage("");
        } catch (Exception e) {
            throw new HttpRestException(e.getMessage(),e);
        }
        return response;
    }
}

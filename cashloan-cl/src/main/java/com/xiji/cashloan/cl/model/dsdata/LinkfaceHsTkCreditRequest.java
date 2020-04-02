package com.xiji.cashloan.cl.model.dsdata;

import com.xiji.cashloan.core.common.context.Global;
import java.io.File;
import java.nio.charset.Charset;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.util.Asserts;
import org.apache.http.util.CharsetUtils;

/**
 * 活体验证
 * Created by szb on 18/11/17.
 */
public class LinkfaceHsTkCreditRequest extends DsTkCreditRequest {
    private File livingImg;
    private String name;
    private String idCard;

    public LinkfaceHsTkCreditRequest(String host, File livingImg, String name, String idCard) {
        super(host);
        this.livingImg = livingImg;
        this.name = name;
        this.idCard = idCard;
    }

    @Override
    protected HttpEntity handle() throws Exception {
        Asserts.notNull(this.livingImg, "livingImg");
        MultipartEntityBuilder builder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                .addPart("image", new FileBody(this.livingImg))
                .addPart("api_key", new StringBody(Global.getValue("face_api_key"), ContentType.MULTIPART_FORM_DATA))
                .addPart("api_secret", new StringBody(Global.getValue("face_api_secret"), ContentType.MULTIPART_FORM_DATA))
                .addPart("comparison_type", new StringBody("1", ContentType.MULTIPART_FORM_DATA))
                .addPart("face_image_type", new StringBody("raw_image", ContentType.MULTIPART_FORM_DATA))
                .addPart("idcard_name", new StringBody(this.name, Charset.forName("utf-8")))
                .addPart("idcard_number", new StringBody(this.idCard, ContentType.MULTIPART_FORM_DATA));


        HttpEntity mulitpartEntity1 = builder.setCharset(CharsetUtils.get("UTF-8")).build();
        return mulitpartEntity1;
    }
}

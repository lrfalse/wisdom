package com.wisdom.third.wx.common.util.http;

import java.io.File;
import java.io.IOException;

import com.wisdom.third.wx.common.bean.result.WxError;
import com.wisdom.third.wx.common.bean.result.WxMediaUploadResult;
import com.wisdom.third.wx.common.exception.WxErrorException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * 上传媒体文件请求执行器，请求的参数是File, 返回的结果是String
 *
 */
public class MediaUploadRequestExecutor implements RequestExecutor<WxMediaUploadResult, File> {

  @Override
  public WxMediaUploadResult execute(CloseableHttpClient httpclient, HttpHost httpProxy, String uri, File file) throws WxErrorException, ClientProtocolException, IOException {
    HttpPost httpPost = new HttpPost(uri);
    if (httpProxy != null) {
      RequestConfig config = RequestConfig.custom().setProxy(httpProxy).build();
      httpPost.setConfig(config);
    }
    if (file != null) {
      HttpEntity entity = MultipartEntityBuilder
            .create()
            .addBinaryBody("media", file)
            .setMode(HttpMultipartMode.RFC6532)
            .build();
      httpPost.setEntity(entity);
      httpPost.setHeader("Content-Type", ContentType.MULTIPART_FORM_DATA.toString());
    }
      CloseableHttpResponse response = httpclient.execute(httpPost);
      String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
      WxError error = WxError.fromJson(responseContent);
      if (error.getErrorCode() != 0) {
        throw new WxErrorException(error);
      }
      return WxMediaUploadResult.fromJson(responseContent);

  }

}

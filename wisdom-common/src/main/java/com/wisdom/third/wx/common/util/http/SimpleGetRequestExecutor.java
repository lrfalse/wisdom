package com.wisdom.third.wx.common.util.http;

import java.io.IOException;

import com.wisdom.third.wx.common.bean.result.WxError;
import com.wisdom.third.wx.common.exception.WxErrorException;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * 简单的GET请求执行器，请求的参数是String, 返回的结果也是String
 *
 */
public class SimpleGetRequestExecutor implements RequestExecutor<String, String> {

  @Override
  public String execute(CloseableHttpClient httpclient, HttpHost httpProxy, String uri, String queryParam) throws WxErrorException, ClientProtocolException, IOException {
    if (queryParam != null) {
      if (uri.indexOf('?') == -1) {
        uri += '?';
      }
      uri += uri.endsWith("?") ? queryParam : '&' + queryParam;
    }
    HttpGet httpGet = new HttpGet(uri);
    if (httpProxy != null) {
      RequestConfig config = RequestConfig.custom().setProxy(httpProxy).build();
      httpGet.setConfig(config);
    }

    CloseableHttpResponse response = httpclient.execute(httpGet);
      String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
      WxError error = WxError.fromJson(responseContent);
      if (error.getErrorCode() != 0) {
        throw new WxErrorException(error);
      }
      return responseContent;
  }

}

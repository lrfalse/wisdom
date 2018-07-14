package com.wisdom.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import com.wisdom.utils.exception.HttpClientException;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtils {
    private static final Logger log = LoggerFactory.getLogger(HttpClientUtils.class);
    public static final String DEFAULT_ENCODING = "UTF-8";
    public static final int DEFAULT_REQUEST_TIMEOUT = 10000;
    public static final int DEFAULT_CONNECTION_REQUEST_TIMEOUT = 3000;
    public static final int DEFAULT_CONNECT_TIMEOUT = 3000;
    public static final long DEFAULT_KEEPALIVE_TIME = 60000L;
    private PoolingHttpClientConnectionManager poolingHttpClientConnectionManager;
    private CloseableHttpClient closeableHttpClient;
    private RequestConfig defaultRequestConfig = RequestConfig.custom().setRedirectsEnabled(this.isHandleRedirects()).setMaxRedirects(this.getMaxRedirects()).setConnectionRequestTimeout(3000).setConnectTimeout(3000).setSocketTimeout(10000).build();
    private ConnectionConfig defaultConnectionConfig = ConnectionConfig.custom().setBufferSize(this.getSocketBufferSize()).setCharset(Charset.forName("UTF-8")).build();
    private SocketConfig defaultSocketConfig = SocketConfig.custom().setTcpNoDelay(this.isTcpNodelay()).setSoTimeout(10000).setSoLinger(this.getSoLinger()).setSoReuseAddress(this.isSoReuseaddr()).setSoKeepAlive(this.isKeepAlive()).build();
    private static final HttpClientUtils.IdleConnectionMonitorThread idleThread = new HttpClientUtils.IdleConnectionMonitorThread();
    private String userAgent = "Apache-HttpClient-4.5.3";
    private boolean tcpNodelay = true;
    private int soLinger = -1;
    private boolean soReuseaddr = true;
    private int socketBufferSize = 8192;
    private boolean staleConnectionCheck = false;
    private boolean keepAlive = true;
    private boolean handleRedirects = true;
    private int maxRedirects = 10;
    private int maxPerRoute = 50;
    private int maxTotal = 200;

    public HttpClientUtils() {
        Registry socketFactoryRegistry = RegistryBuilder.create()
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .build();
        this.poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        this.poolingHttpClientConnectionManager.setMaxTotal(this.getMaxTotal());
        this.poolingHttpClientConnectionManager.setDefaultMaxPerRoute(this.getMaxPerRoute());
        this.poolingHttpClientConnectionManager.setDefaultConnectionConfig(this.defaultConnectionConfig);
        this.poolingHttpClientConnectionManager.setDefaultSocketConfig(this.defaultSocketConfig);
        this.closeableHttpClient = HttpClients.custom().setRetryHandler(new HttpClientUtils.HttpRequestRetryHandler()).setKeepAliveStrategy(new HttpClientUtils.HttpConnectionKeepAliveStrategy()).setUserAgent(this.getUserAgent()).setDefaultRequestConfig(this.defaultRequestConfig).setConnectionManager(this.poolingHttpClientConnectionManager).build();
        idleThread.addConnMgr(this.poolingHttpClientConnectionManager);
    }

    public void shutdown() {
        idleThread.shutdown();

        try {
            this.closeableHttpClient.close();
        } catch (IOException var2) {
            throw new HttpClientException("HttpClientUtils.shutdown error", var2);
        }
    }

    public String httpGet(String url) {
        return this.httpGet(url, (Map)null, 10000, "UTF-8");
    }

    public String httpGet(String url, Map<String, String> queryParams) {
        return this.httpGet(url, queryParams, 10000, "UTF-8");
    }

    public String httpGet(String url, Map<String, String> queryParams, int requestTimeout) {
        return this.httpGet(url, queryParams, requestTimeout, "UTF-8");
    }

    public final String httpGet(String url, Map<String, String> queryParams, int requestTimeout, String encoding) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(url), "Param url must be not null and empty");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(encoding), "Param encoding must be not null and empty");
        String urlToSend = url;
        String result = null;
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder("consumer_log - ");
        sb.append(url).append("|");
        if (queryParams != null) {
            sb.append(queryParams.toString()).append("|");
        }

        if (queryParams != null && !queryParams.isEmpty()) {
            List<NameValuePair> qparams = Lists.newArrayList();
            Iterator var11 = queryParams.entrySet().iterator();

            while(var11.hasNext()) {
                Entry<String, String> entry = (Entry)var11.next();
                qparams.add(new BasicNameValuePair((String)entry.getKey(), (String)entry.getValue()));
            }

            String queryParamStr = URLEncodedUtils.format(qparams, encoding);
            if (url.endsWith("?")) {
                urlToSend = url + queryParamStr;
            } else {
                urlToSend = url + "?" + queryParamStr;
            }
        }

        try {
            HttpGet httpGet = new HttpGet(urlToSend);
            if (requestTimeout > 0) {
                RequestConfig requestConfig = RequestConfig.copy(this.defaultRequestConfig).setSocketTimeout(requestTimeout).build();
                httpGet.setConfig(requestConfig);
            }

            HttpResponse httpResponse = this.closeableHttpClient.execute(httpGet);
            result = EntityUtils.toString(httpResponse.getEntity(), encoding);
        } catch (Exception var16) {
            sb.append("Exception:").append(var16.getMessage()).append("|");
            throw new HttpClientException("HttpClientUtils.httpGet error.", var16);
        } finally {
            sb.append(result).append("|");
            sb.append(System.currentTimeMillis() - start);
            log.info(sb.toString());
        }

        return result;
    }

    public  final String httpPostForm(String url, Map<String, String> postParams) {
        return this.httpPostForm(url, postParams, 10000, "UTF-8");
    }
    public  final JSONObject httpPostFormReturnJSON(String url, Map<String, String> postParams) {
        return JSON.parseObject(this.httpPostForm(url, postParams, 10000, "UTF-8"));
    }


    public final String httpPostForm(String url, Map<String, String> postParams, int requestTimeout) {
        return this.httpPostForm(url, postParams, requestTimeout, "UTF-8");
    }

    public final String httpPostForm(String url, Map<String, String> postParams, int requestTimeout, String encoding) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(url), "Param url must be not null and empty");
        Preconditions.checkArgument(postParams != null && !postParams.isEmpty(), "Param postParams must be not null and empty");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(encoding), "Param encoding must be not null and empty");
        String urlToSend = url;
        String result = null;
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append(url).append("|");
        sb.append(postParams.toString()).append("|");

        try {
            HttpPost httpPost = new HttpPost(urlToSend);
            List<NameValuePair> qparams = Lists.newArrayList();
            Iterator var12 = postParams.entrySet().iterator();

            while(var12.hasNext()) {
                Entry<String, String> entry = (Entry)var12.next();
                qparams.add(new BasicNameValuePair((String)entry.getKey(), (String)entry.getValue()));
            }

            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(qparams, encoding);
            httpPost.setEntity(entity);
            if (requestTimeout > 0) {
                RequestConfig requestConfig = RequestConfig.copy(this.defaultRequestConfig).setSocketTimeout(requestTimeout).build();
                httpPost.setConfig(requestConfig);
            }

            HttpResponse httpResponse = this.closeableHttpClient.execute(httpPost);
            result = EntityUtils.toString(httpResponse.getEntity(), encoding);
        } catch (Exception var17) {
            sb.append("Exception:").append(var17.getMessage()).append("|");
            throw new HttpClientException("HttpClientUtils.httpPostForm error.", var17);
        } finally {
            sb.append(result).append("|");
            sb.append(System.currentTimeMillis() - start);
            log.info(sb.toString());
        }

        return result;
    }

    public final String httpPostJson(String url, String jsonParams) {
        return this.httpPostJson(url, jsonParams, 10000, "UTF-8");
    }

    public final String httpPostJson(String url, String jsonParams, int requestTimeout) {
        return this.httpPostJson(url, jsonParams, requestTimeout, "UTF-8");
    }

    public final String httpPostJson(String url, String jsonParams, int requestTimeout, String encoding) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(url), "Param url must be not null and empty");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(jsonParams), "Param jsonParams must be not null and empty");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(encoding), "Param encoding must be not null and empty");
        String urlToSend = url;
        String result = null;
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append(url).append("|");
        sb.append(jsonParams).append("|");

        try {
            HttpPost httpPost = new HttpPost(urlToSend);
            StringEntity entity = new StringEntity(jsonParams, encoding);
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            if (requestTimeout > 0) {
                RequestConfig requestConfig = RequestConfig.copy(this.defaultRequestConfig).setSocketTimeout(requestTimeout).build();
                httpPost.setConfig(requestConfig);
            }

            HttpResponse httpResponse = this.closeableHttpClient.execute(httpPost);
            result = EntityUtils.toString(httpResponse.getEntity(), encoding);
        } catch (Exception var16) {
            sb.append("Exception:" + var16.getMessage()).append("|");
            throw new HttpClientException("HttpClientUtils.httpPostJson error.", var16);
        } finally {
            sb.append(result).append("|");
            sb.append(System.currentTimeMillis() - start);
            log.info(sb.toString());
        }

        return result;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public boolean isTcpNodelay() {
        return this.tcpNodelay;
    }

    public void setTcpNodelay(boolean tcpNodelay) {
        this.tcpNodelay = tcpNodelay;
    }

    public int getSoLinger() {
        return this.soLinger;
    }

    public void setSoLinger(int soLinger) {
        this.soLinger = soLinger;
    }

    public boolean isSoReuseaddr() {
        return this.soReuseaddr;
    }

    public void setSoReuseaddr(boolean soReuseaddr) {
        this.soReuseaddr = soReuseaddr;
    }

    public int getSocketBufferSize() {
        return this.socketBufferSize;
    }

    public void setSocketBufferSize(int socketBufferSize) {
        this.socketBufferSize = socketBufferSize;
    }

    public boolean isStaleConnectionCheck() {
        return this.staleConnectionCheck;
    }

    public void setStaleConnectionCheck(boolean staleConnectionCheck) {
        this.staleConnectionCheck = staleConnectionCheck;
    }

    public boolean isKeepAlive() {
        return this.keepAlive;
    }

    public void setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public boolean isHandleRedirects() {
        return this.handleRedirects;
    }

    public void setHandleRedirects(boolean handleRedirects) {
        this.handleRedirects = handleRedirects;
    }

    public int getMaxRedirects() {
        return this.maxRedirects;
    }

    public void setMaxRedirects(int maxRedirects) {
        this.maxRedirects = maxRedirects;
    }

    public int getMaxPerRoute() {
        return this.maxPerRoute;
    }

    public void setMaxPerRoute(int maxPerRoute) {
        this.maxPerRoute = maxPerRoute;
    }

    public int getMaxTotal() {
        return this.maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    static {
        idleThread.setDaemon(true);
        idleThread.start();
    }

    private static class HttpRequestRetryHandler extends DefaultHttpRequestRetryHandler {
        public HttpRequestRetryHandler() {
        }

        public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
            boolean retry = super.retryRequest(exception, executionCount, context);
            if (exception instanceof NoHttpResponseException) {
                HttpClientUtils.log.info("HttpClientUtils.retryRequest detected NoHttpResponseException,sleep 1 ml,then retry");

                try {
                    Thread.sleep(1L);
                } catch (InterruptedException var6) {
                    ;
                }

                if (executionCount > super.getRetryCount()) {
                    retry = false;
                } else {
                    retry = true;
                }
            }

            return retry;
        }
    }

    private static class HttpConnectionKeepAliveStrategy extends DefaultConnectionKeepAliveStrategy {
        private HttpConnectionKeepAliveStrategy() {
        }

        public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
            long keepAlive = super.getKeepAliveDuration(response, context);
            if (keepAlive == -1L) {
                HttpHost target = (HttpHost)context.getAttribute("http.target_host");
                if ("www.naughty-server.com".equalsIgnoreCase(target.getHostName())) {
                    keepAlive = 5000L;
                } else {
                    keepAlive = 60000L;
                }
            }

            return keepAlive;
        }
    }

    private static class IdleConnectionMonitorThread extends Thread {
        private final Set<HttpClientConnectionManager> connMgrSet = Sets.newHashSet();
        private volatile boolean shutdown;

        public IdleConnectionMonitorThread() {
            super("HttpClientUtils$IdleConnectionMonitorThread");
        }

        public void addConnMgr(HttpClientConnectionManager connectionManager) {
            this.connMgrSet.add(connectionManager);
        }

        public void run() {
            label31:
            while(true) {
                try {
                    if (!this.shutdown) {
                        synchronized(this) {
                            this.wait(3000L);
                            Iterator var2 = this.connMgrSet.iterator();

                            while(true) {
                                if (!var2.hasNext()) {
                                    continue label31;
                                }

                                HttpClientConnectionManager connMgr = (HttpClientConnectionManager)var2.next();
                                connMgr.closeExpiredConnections();
                                connMgr.closeIdleConnections(60000L, TimeUnit.MILLISECONDS);
                            }
                        }
                    }
                } catch (InterruptedException var6) {
                    ;
                }

                return;
            }
        }

        public void shutdown() {
            this.shutdown = true;
            synchronized(this) {
                this.notifyAll();
            }
        }
    }
}

package com.wisdom.rpc;

import com.wisdom.third.famvideo.FamVideoClient;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import okhttp3.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class NonCloudFeignConfig {

    private static final Logger log = LoggerFactory.getLogger(NonCloudFeignConfig.class);

    @Value("${famvideo.fam_video_url}")
    private String fam_video_fam_video_url;
    @Autowired
    okhttp3.OkHttpClient okHttpClient;
    @Autowired
    OkHttpLoggingInterceptor okHttpLoggingInterceptor;

    @Bean
    public okhttp3.OkHttpClient okHttpClient() {
        okhttp3.OkHttpClient.Builder ClientBuilder = new okhttp3.OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS) //读取超时
                .connectTimeout(10, TimeUnit.SECONDS) //连接超时
                .writeTimeout(60, TimeUnit.SECONDS) //写入超时
                .connectionPool(new ConnectionPool(10 /*maxIdleConnections*/, 3, TimeUnit.MINUTES));
        return ClientBuilder.build();
    }

    @Bean
    public FamVideoClient housingEstateClient() {
        return Feign.builder().logger(new MyLogger())
                .logLevel(MyLogger.Level.FULL)
                .retryer(new MyRetryer(100L, 1L, 3))
                .decode404()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .client(new OkHttpClient(okHttpClient))
//                .target(FamVideoClient.class, fam_video_fam_video_url);
                .target(FamVideoClient.class, "http://localhost:18080");
    }
}

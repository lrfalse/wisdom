package com.wisdom.rpc;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OkHttpLoggingInterceptor implements Interceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(OkHttpLoggingInterceptor.class);

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        try {
            Response response = chain.proceed(request);
            return response;
        }catch (Exception e) {
            throw e;
        }finally {
        }
    }
}
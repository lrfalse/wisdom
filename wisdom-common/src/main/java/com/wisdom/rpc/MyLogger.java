package com.wisdom.rpc;

import feign.Logger;
import feign.Request;
import feign.Response;
import feign.Util;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import static feign.Util.valuesOrEmpty;


@Slf4j
public class MyLogger extends Logger {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(OkHttpLoggingInterceptor.class);


    @Override
    protected void logRequest(String configKey, Level logLevel, Request request) {
        StringBuffer sb = new StringBuffer("请求->" + configKey);
        sb.append(String.format("---> %s %s HTTP/1.1", request.method(), request.url()));

        for (String field : request.headers().keySet()) {
            for (String value : valuesOrEmpty(request.headers(), field)) {
                sb.append(String.format("%s: %s", field, value));
            }
        }

        int bodyLength = 0;
        if (request.body() != null) {
            bodyLength = request.body().length;
            if (logLevel.ordinal() >= Level.FULL.ordinal()) {
                String
                        bodyText =
                        request.charset() != null ? new String(request.body(), request.charset()) : null;
                sb.append(String.format("%s", bodyText != null ? bodyText : "Binary data"));
            }
        }
        sb.append(String.format("---> END HTTP (%s-byte body)", bodyLength));
        log.info(sb.toString());
    }

    @Override
    protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response,
                                              long elapsedTime) throws IOException {
        StringBuffer sb = new StringBuffer("响应->" + configKey);
        String reason = response.reason() != null && logLevel.compareTo(Level.NONE) > 0 ?
                " " + response.reason() : "";
        sb.append(String.format("<--- HTTP/1.1 %s%s (%sms)", response.status(), reason, elapsedTime));

        for (String field : response.headers().keySet()) {
            for (String value : valuesOrEmpty(response.headers(), field)) {
                sb.append(String.format("%s: %s", field, value));
            }
        }

        int bodyLength = 0;
        if (response.body() != null) {
            if (logLevel.ordinal() >= Level.FULL.ordinal()) {
            }
            byte[] bodyData = Util.toByteArray(response.body().asInputStream());
            bodyLength = bodyData.length;
            sb.append(Charset.forName("UTF-8").decode(ByteBuffer.wrap(bodyData)).toString());

            sb.append(String.format("<--- END HTTP (%s-byte body)", bodyLength));
            log.info(sb.toString());
            return Response.create(response.status(), response.reason(), response.headers(), bodyData);
        } else {
            sb.append(String.format("<--- END HTTP (%s-byte body)", bodyLength));
        }

        log.info(sb.toString());
        return response;
    }

    @Override
    protected void log(String configKey, String format, Object... args) {

    }


}

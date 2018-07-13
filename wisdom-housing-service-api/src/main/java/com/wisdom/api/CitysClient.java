package com.wisdom.api;

import com.alibaba.fastjson.JSONObject;
import com.wisdom.vo.CitysVo;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface CitysClient {
    @RequestLine("GET /citys/get?id={id}")
    JSONObject get(@Param("id") Long id);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /citys/set")
    JSONObject set(CitysVo citysVo);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /citys/search")
    JSONObject search(CitysVo citysVo);
}

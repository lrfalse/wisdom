package com.wisdom.api;


import com.alibaba.fastjson.JSONObject;
import com.wisdom.vo.ProvincesVo;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface ProvincesClient {
    @RequestLine("GET /provinces/get?id={id}")
    JSONObject get(@Param("id") Long id);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /provinces/set")
    JSONObject set(ProvincesVo provincesVo);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /provinces/search")
    JSONObject search(ProvincesVo provincesVo);
}

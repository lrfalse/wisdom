package com.wisdom.api;

import com.alibaba.fastjson.JSONObject;
import com.wisdom.vo.CountrysVo;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface CountrysClient {
    @RequestLine("GET /countrys/get?id={id}")
    JSONObject get(@Param("id") Long id);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /countrys/set")
    JSONObject set(CountrysVo countrysVo);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /countrys/search")
    JSONObject search(CountrysVo countrysVo);
}

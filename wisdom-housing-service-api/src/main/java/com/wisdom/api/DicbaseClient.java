package com.wisdom.api;

import com.alibaba.fastjson.JSONObject;
import com.wisdom.vo.DicbaseVo;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface DicbaseClient {
    @RequestLine("GET /dicbase/get?id={id}")
    JSONObject get(@Param("id") Long id);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /dicbase/set")
    JSONObject set(DicbaseVo dicbaseVo);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /dicbase/search")
    JSONObject search(DicbaseVo dicbaseVo);
}

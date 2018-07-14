package com.wisdom.api;

import com.alibaba.fastjson.JSONObject;
import com.wisdom.vo.DicbaseVo;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface DicbaseClient {
    @RequestLine("GET /dic_base/get?id={id}")
    JSONObject get(@Param("id") Long id);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /dic_base/set")
    JSONObject set(DicbaseVo dicbaseVo);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /dic_base/search")
    JSONObject search(DicbaseVo dicbaseVo);
}

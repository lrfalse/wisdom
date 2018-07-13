package com.wisdom.api;


import com.alibaba.fastjson.JSONObject;
import com.wisdom.vo.AreasVo;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
public interface AreasClient {
    @RequestLine("GET /areas/get?id={id}")
    JSONObject get(@Param("id") Long id);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /areas/set")
    JSONObject set(AreasVo areasVo);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /areas/search")
    JSONObject search(AreasVo areasVo);

}

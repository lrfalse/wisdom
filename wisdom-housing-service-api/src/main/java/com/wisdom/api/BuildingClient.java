package com.wisdom.api;


import com.alibaba.fastjson.JSONObject;
import com.wisdom.vo.BuildingVo;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface BuildingClient {
    @RequestLine("GET /building/get?id={id}")
    JSONObject get(@Param("id") Long id);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /building/set")
    JSONObject set(BuildingVo buildingVo);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /building/search")
    JSONObject search(BuildingVo buildingVo);
}

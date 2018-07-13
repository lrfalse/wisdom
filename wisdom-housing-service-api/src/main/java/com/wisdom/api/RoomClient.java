package com.wisdom.api;


import com.alibaba.fastjson.JSONObject;
import com.wisdom.vo.RoomVo;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface RoomClient {
    @RequestLine("GET /room/get?id={id}")
    JSONObject get(@Param("id") Long id);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /room/set")
    JSONObject set(RoomVo roomVo);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /room/search")
    JSONObject search(RoomVo roomVo);
}

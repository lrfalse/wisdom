package com.wisdom.api;

import com.alibaba.fastjson.JSONObject;
import com.wisdom.vo.MembertoRoomVo;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface MembertoRoomClient {
    @RequestLine("GET /membertoroom/get?id={id}")
    JSONObject get(@Param("id") Long id);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /membertoroom/set")
    JSONObject set(MembertoRoomVo membertoRoomVo);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /membertoroom/search")
    JSONObject search(MembertoRoomVo membertoRoomVo);
}

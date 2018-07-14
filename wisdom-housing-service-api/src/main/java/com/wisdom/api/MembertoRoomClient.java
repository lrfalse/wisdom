package com.wisdom.api;

import com.alibaba.fastjson.JSONObject;
import com.wisdom.vo.MembertoRoomVo;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface MembertoRoomClient {
    @RequestLine("GET /member_to_room/get?id={id}")
    JSONObject get(@Param("id") Long id);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /member_to_room/set")
    JSONObject set(MembertoRoomVo membertoRoomVo);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /member_to_room/search")
    JSONObject search(MembertoRoomVo membertoRoomVo);
}

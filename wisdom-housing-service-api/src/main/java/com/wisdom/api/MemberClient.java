package com.wisdom.api;

import com.alibaba.fastjson.JSONObject;
import com.wisdom.vo.MemberVo;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.Map;

public interface MemberClient {
    @RequestLine("GET /member/get?id={id}")
    JSONObject get(@Param("id") Long id);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /member/set")
    JSONObject set(MemberVo memberVo);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /member/search")
    JSONObject search(MemberVo memberVo);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /member/building_phone")
    JSONObject buildingPhone(MemberVo memberVo);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /member/is_identity")
    JSONObject isIdentity(MemberVo memberVo);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /member/is_recognition")
    JSONObject isFace(MemberVo memberVo);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /member/search_housing_room")
    JSONObject searchHousingRoom(Map map);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /member/search_room_member")
    JSONObject searchRoomMember(Map map);
}

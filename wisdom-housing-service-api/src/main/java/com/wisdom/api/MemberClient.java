package com.wisdom.api;

import com.alibaba.fastjson.JSONObject;
import com.wisdom.vo.MemberVo;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

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

}

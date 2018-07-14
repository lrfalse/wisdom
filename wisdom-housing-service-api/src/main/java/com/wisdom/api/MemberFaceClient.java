package com.wisdom.api;


import com.alibaba.fastjson.JSONObject;
import com.wisdom.vo.MemberFaceVo;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface MemberFaceClient {
    @RequestLine("GET /member_face/get?id={id}")
    JSONObject get(@Param("id") Long id);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /member_face/set")
    JSONObject set(MemberFaceVo memberFaceVo);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /member_face/search")
    JSONObject search(MemberFaceVo memberFaceVo);
}

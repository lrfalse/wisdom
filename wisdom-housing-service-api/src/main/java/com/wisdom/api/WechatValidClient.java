package com.wisdom.api;


import com.alibaba.fastjson.JSONObject;
import com.wisdom.vo.WechatValidVo;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface WechatValidClient {
    @RequestLine("GET /wechatvalid/get?id={id}")
    JSONObject get(@Param("id") Long id);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /wechatvalid/set")
    JSONObject set(WechatValidVo wechatValidVo);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /wechatvalid/search")
    JSONObject search(WechatValidVo wechatValidVo);
}

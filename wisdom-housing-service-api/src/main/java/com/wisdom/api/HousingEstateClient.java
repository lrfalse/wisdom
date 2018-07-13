package com.wisdom.api;

import com.alibaba.fastjson.JSONObject;
import com.wisdom.vo.HousingEstateVo;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface HousingEstateClient {
    @RequestLine("GET /housingestate/get?id={id}")
    JSONObject get(@Param("id") Long id);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /housingestate/set")
    JSONObject set(HousingEstateVo housingEstateVo);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /housingestate/search")
    JSONObject search(HousingEstateVo housingEstateVo);
}

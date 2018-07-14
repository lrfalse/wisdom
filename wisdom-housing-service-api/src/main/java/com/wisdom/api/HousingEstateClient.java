package com.wisdom.api;

import com.alibaba.fastjson.JSONObject;
import com.wisdom.vo.HousingEstateVo;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface HousingEstateClient {
    @RequestLine("GET /housing_estate/get?id={id}")
    JSONObject get(@Param("id") Long id);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /housing_estate/set")
    JSONObject set(HousingEstateVo housingEstateVo);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /housing_estate/search")
    JSONObject search(HousingEstateVo housingEstateVo);
}

package com.wisdom.api;

import com.alibaba.fastjson.JSONObject;
import com.wisdom.vo.HousingDerviceVo;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface HousingDerviceClient {
    @RequestLine("GET /housing_dervice/get?id={id}")
    JSONObject get(@Param("id") Long id);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /housing_dervice/set")
    JSONObject set(HousingDerviceVo housingDerviceVo);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /housing_dervice/search")
    JSONObject search(HousingDerviceVo housingDerviceVo);
}

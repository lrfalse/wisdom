package com.wisdom.third.famvideo;

import com.alibaba.fastjson.JSONObject;
import feign.Param;
import feign.RequestLine;

public interface FamVideoClient {
    @RequestLine(value="POST ${famvideo.user_Api.getToken} ")
    JSONObject get(@Param("platformId") String platformId, @Param("signKey") String signKey);

    @RequestLine(value="POST ${famvideo.device_Api.addUser} ")
    JSONObject addUser(@Param("account") String account, @Param("password") String password, @Param("name") String name, @Param("passportUrl") String passportUrl, @Param("platform") String platform, @Param("token") String token);

    @RequestLine(value="POST ${famvideo.device_Api.addDevice} ")
    JSONObject addDevice(@Param("deviceId") String deviceId, @Param("deviceName") String deviceName, @Param("password") String password, @Param("userId") String userId, @Param("token") String token);

    @RequestLine(value="POST ${famvideo.device_Api.deleteDevice} ")
    JSONObject deleteDevice(@Param("doorId") String doorId, @Param("userId") String userId, @Param("token") String token);

    @RequestLine(value="POST ${famvideo.user_Api.uploadFace} ")
    JSONObject uploadFace(@Param("name") String name, @Param("token") String token, @Param("userId") Long userId, @Param("type") Integer type, @Param("contact") String contact, @Param("imgUrl") String imgUrl, @Param("img") String img, @Param("handler") String handler, @Param("id") String id, @Param("no") String no, @Param("platform") String platform);

    @RequestLine(value="POST ${famvideo.user_Api.removeFace} ")
    JSONObject removeFace(@Param("id") Long id,@Param("token") String token);

    @RequestLine(value="POST ${famvideo.user_Api.batchRemoveFaceUser} ")
    JSONObject batchRemoveFaceUser(@Param("faceUsers") String faceUsers,@Param("token") String token);

    @RequestLine(value="POST ${famvideo.user_Api.uploadFaceOfDevice} ")
    JSONObject uploadFaceOfDevice(@Param("faceDevice") String faceDevice, @Param("token") String token, @Param("batch") Long batch);

    @RequestLine(value="POST ${famvideo.user_Api.uploadFaceOfDeviceByFace} ")
    JSONObject uploadFaceOfDeviceByFace(@Param("faceDevice") String faceDevice, @Param("token") String token, @Param("faceUser") Long faceUser);

    @RequestLine(value="POST ${famvideo.user_Api.removeFaceOfDeviceByFaceAndDevice} ")
    JSONObject removeFaceOfDeviceByFaceAndDevice(@Param("token") String token, @Param("faceUsers") String faceUsers, @Param("devices") String devices, @Param("userId") String userId);

    @RequestLine(value="POST ${famvideo.user_Api.getStrangerUrl} ")
    JSONObject getStrangerUrl(@Param("userId") String userId, @Param("token") String token);

    @RequestLine(value="POST ${famvideo.user_Api.updateFaceOfDevice} ")
    JSONObject updateFaceOfDevice(@Param("token") String faceDevice, @Param("token") String  token);

    @RequestLine(value="POST ${famvideo.user_Api.getStranger} ")
    JSONObject getStranger(@Param("token") String token, @Param("userId") String userId, @Param("start") int start, @Param("limit") int limit, @Param("timeStr") String timeStr);

    @RequestLine(value="POST ${famvideo.user_Api.downloadRecord} ")
    JSONObject downloadRecord(@Param("token") String token, @Param("userId") String userId, @Param("start") int start, @Param("limit") int limit);

}

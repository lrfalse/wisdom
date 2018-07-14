package com.wisdom.third.famvideo;

import com.alibaba.fastjson.JSONObject;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface FamVideoClient {
    @Headers({" Content-Type: application/x-www-form-urlencoded "})
    @RequestLine(value="POST /famvideo/user_Api/getToken.html ")
    JSONObject getToken(@Param("platformId") String platformId, @Param("signKey") String signKey);

    @Headers({" Content-Type: application/x-www-form-urlencoded "})
    @RequestLine(value="POST /famvideo/device_Api/addUser.html ")
    JSONObject addUser(@Param("account") String account, @Param("password") String password, @Param("name") String name, @Param("passportUrl") String passportUrl, @Param("platform") String platform, @Param("token") String token);

    @Headers({" Content-Type: application/x-www-form-urlencoded "})
    @RequestLine(value="POST /famvideo/device_Api/addDevice.html ")
    JSONObject addDevice(@Param("deviceId") String deviceId, @Param("deviceName") String deviceName, @Param("password") String password, @Param("userId") String userId, @Param("token") String token);

    @Headers({" Content-Type: application/x-www-form-urlencoded "})
    @RequestLine(value="POST /famvideo/device_Api/deleteDevice.html ")
    JSONObject deleteDevice(@Param("doorId") String doorId, @Param("userId") String userId, @Param("token") String token);

    @Headers({" Content-Type: application/x-www-form-urlencoded "})
    @RequestLine(value="POST /famvideo/user_Api/uploadFace.html ")
    JSONObject uploadFace(@Param("name") String name, @Param("token") String token, @Param("userId") Long userId, @Param("type") Integer type, @Param("contact") String contact, @Param("imgUrl") String imgUrl, @Param("img") String img, @Param("handler") String handler, @Param("id") String id, @Param("no") String no, @Param("platform") String platform);

    @Headers({" Content-Type: application/x-www-form-urlencoded "})
    @RequestLine(value="POST /famvideo/user_Api/removeFace.html ")
    JSONObject removeFace(@Param("id") Long id,@Param("token") String token);

    @Headers({" Content-Type: application/x-www-form-urlencoded "})
    @RequestLine(value="POST /famvideo/user_Api/batchRemoveFaceUser.html ")
    JSONObject batchRemoveFaceUser(@Param("faceUsers") String faceUsers,@Param("token") String token);

    @Headers({" Content-Type: application/x-www-form-urlencoded "})
    @RequestLine(value="POST /famvideo/user_Api/uploadFaceOfDevice.html ")
    JSONObject uploadFaceOfDevice(@Param("faceDevice") String faceDevice, @Param("token") String token, @Param("batch") Long batch);

    @Headers({" Content-Type: application/x-www-form-urlencoded "})
    @RequestLine(value="POST /famvideo/user_Api/uploadFaceOfDeviceByFace.html ")
    JSONObject uploadFaceOfDeviceByFace(@Param("faceDevice") String faceDevice, @Param("token") String token, @Param("faceUser") Long faceUser);

    @Headers({" Content-Type: application/x-www-form-urlencoded "})
    @RequestLine(value="POST /famvideo/user_Api/removeFaceOfDeviceByFaceAndDevice.html ")
    JSONObject removeFaceOfDeviceByFaceAndDevice(@Param("token") String token, @Param("faceUsers") String faceUsers, @Param("devices") String devices, @Param("userId") String userId);

    @Headers({" Content-Type: application/x-www-form-urlencoded "})
    @RequestLine(value="POST /famvideo/user_Api/getStrangerUrl.html ")
    JSONObject getStrangerUrl(@Param("userId") String userId, @Param("token") String token);

    @Headers({" Content-Type: application/x-www-form-urlencoded "})
    @RequestLine(value="POST /famvideo/user_Api/updateFaceOfDevice.html ")
    JSONObject updateFaceOfDevice(@Param("token") String faceDevice, @Param("token") String  token);

    @Headers({" Content-Type: application/x-www-form-urlencoded "})
    @RequestLine(value="POST /famvideo/user_Api/getStranger.html ")
    JSONObject getStranger(@Param("token") String token, @Param("userId") String userId, @Param("start") int start, @Param("limit") int limit, @Param("timeStr") String timeStr);

    @Headers({" Content-Type: application/x-www-form-urlencoded "})
    @RequestLine(value="POST /famvideo/user_Api/downloadRecord.html ")
    JSONObject downloadRecord(@Param("token") String token, @Param("userId") String userId, @Param("start") int start, @Param("limit") int limit);

}

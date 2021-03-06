package com.wisdom.third.famvideo;

import com.alibaba.fastjson.JSONObject;
import com.wisdom.utils.HttpClientUtils;
import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description(功能描述) :中商接口对接
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/7/14 16:09
 **/
@Service
public class FamVideoClientService {
	@Value("${famvideo.fam_video_url}")
	private String fam_video_fam_video_url;
	@Autowired
	private Environment evn;

	private static HttpClientUtils httpClientUtils = new HttpClientUtils();

	/**
	  * @Description(功能描述): 获取token
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/14 18:18
	  **/
	public JSONObject getToken(@Param("platformId") String platformId, @Param("signKey") String signKey){
		Map<String, String> maps=new HashMap<>();
		maps.put("platformId", platformId);
		maps.put("signKey", signKey);
		return httpClientUtils.httpPostFormReturnJSON(fam_video_fam_video_url+evn.getProperty("famvideo.user_Api.getToken"),maps);
	}

	/** 
	  * @Description(功能描述): 新增平台帐号
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/14 18:22
	  **/ 
	public JSONObject addUser(@Param("account") String account, @Param("password") String password, @Param("name") String name, @Param("passportUrl") String passportUrl, @Param("platform") String platform, @Param("token") String token){
		Map<String, String> maps=new HashMap<>();
		maps.put("account", account);
		maps.put("password", password);
		maps.put("name", name);
		maps.put("passportUrl", passportUrl);
		maps.put("platform", platform);
		maps.put("token", token);
		return httpClientUtils.httpPostFormReturnJSON(fam_video_fam_video_url+evn.getProperty("famvideo.device_Api.addUser"),maps);
	}

	/** 
	  * @Description(功能描述): 添加设备
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/14 18:25
	  **/ 
	public JSONObject addDevice(@Param("deviceId") String deviceId, @Param("deviceName") String deviceName, @Param("password") String password, @Param("userId") String userId, @Param("token") String token, @Param("platform") String platform){
		Map<String, String> maps=new HashMap<>();
		maps.put("deviceId", deviceId);
		maps.put("deviceName", deviceName);
		maps.put("password", password);
		maps.put("userId", userId);
		maps.put("token", token);
		maps.put("platform", platform);
		return httpClientUtils.httpPostFormReturnJSON(fam_video_fam_video_url+evn.getProperty("famvideo.device_Api.addDevice"),maps);
	}

	/**
	  * @Description(功能描述): 删除设备
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/14 18:26
	  **/
	public JSONObject deleteDevice(@Param("doorId") String doorId, @Param("userId") String userId, @Param("token") String token, @Param("platform") String platform){
		Map<String, String> maps=new HashMap<>();
		maps.put("doorId", doorId);		//门闸id
		maps.put("userId", userId);
		maps.put("token", token);
		maps.put("platform", platform);
		return httpClientUtils.httpPostFormReturnJSON(fam_video_fam_video_url+evn.getProperty("famvideo.device_Api.deleteDevice"),maps);
	}

	/** 
	  * @Description(功能描述): 上传人脸票据
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/14 18:31
	  **/ 
	public JSONObject uploadFace(@Param("name") String name, @Param("token") String token, @Param("user") String user, @Param("type") Integer type,  @Param("imgUrl") String imgUrl, @Param("img") String img, @Param("handler") String handler, @Param("id") String id, @Param("no") String no, @Param("platform") String platform){
		Map<String, String> maps=new HashMap<>();
		maps.put("name", name);			//人脸用户姓名
		maps.put("token", token);
		maps.put("user",user);
		maps.put("type",String.valueOf(type));	//类型（0:黑名单,1:会员,3:陌生人）
		maps.put("imgUrl", imgUrl);				//头像下载地址(不能超过150个字符)
		maps.put("img", img);					//
		//maps.put("contact", contact);	//联系方式
		maps.put("handler", handler);	//上传人姓名
		maps.put("id", id);				//人脸用户id
		maps.put("no", no);				//人脸编号
		maps.put("platform", platform);	//平台id
		return httpClientUtils.httpPostFormReturnJSON(fam_video_fam_video_url+evn.getProperty("famvideo.user_Api.uploadFace"),maps);
	}
	/**
	  * @Description(功能描述): 删除人脸票据  新增平台id
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/14 23:13
	  **/
	public JSONObject removeFace(@Param("id") String id,@Param("token") String token,@Param("platform") String platform){
		Map<String, String> maps=new HashMap<>();
		maps.put("id", id);			//人脸票据id
		maps.put("token", token);
		maps.put("platform", platform);
		return httpClientUtils.httpPostFormReturnJSON(fam_video_fam_video_url+evn.getProperty("famvideo.user_Api.removeFace"),maps);
	}
	/**
	  * @Description(功能描述): 批量删除人脸票
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/14 18:33
	  **/
	public JSONObject batchRemoveFaceUser(@Param("faceUsers") String faceUsers,@Param("token") String token,@Param("platform") String platform){
		Map<String, String> maps=new HashMap<>();
		maps.put("faceUsers", faceUsers);
		maps.put("token", token);
		maps.put("platform", platform);
		return httpClientUtils.httpPostFormReturnJSON(fam_video_fam_video_url+evn.getProperty("famvideo.user_Api.batchRemoveFaceUser"),maps);
	}

	/**
	  * @Description(功能描述): 通过批次号上传人脸与设备的对应关系（通行证）
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/14 18:35
	  **/
	public JSONObject uploadFaceOfDevice(@Param("faceDevice") String faceDevice, @Param("token") String token, @Param("batch") Long batch,@Param("platform") String platform){
		Map<String, String> maps=new HashMap<>();
		maps.put("faceDevice", faceDevice);
		maps.put("token", token);

		maps.put("batch", String.valueOf(batch==null?"":batch));
		maps.put("platform", platform);
		return httpClientUtils.httpPostFormReturnJSON(fam_video_fam_video_url+evn.getProperty("famvideo.user_Api.uploadFaceOfDevice"),maps);
	}

	/**
	  * @Description(功能描述): 通过人脸用户来上传人脸票与设备的关系（通行证）
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/14 18:35
	  **/
	public JSONObject uploadFaceOfDeviceByFace(@Param("faceDevice") String faceDevice, @Param("token") String token, @Param("faceUser") Long faceUser,@Param("platform") String platform){
		Map<String, String> maps=new HashMap<>();
		maps.put("faceDevice", faceDevice);
		maps.put("token", token);
		maps.put("faceUser", String.valueOf(faceUser));
		maps.put("platform", platform);
		return httpClientUtils.httpPostFormReturnJSON(fam_video_fam_video_url+evn.getProperty("famvideo.user_Api.uploadFaceOfDeviceByFace"),maps);
	}
	/**
	  * @Description(功能描述): 通过人脸用户和设备来删除通行证
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/14 18:35
	  **/
	public JSONObject removeFaceOfDeviceByFaceAndDevice(@Param("token") String token, @Param("faceUsers") String faceUsers,
														@Param("devices") String devices, @Param("userId") String userId,@Param("platform") String platform){
		Map<String, String> maps=new HashMap<>();
		maps.put("token", token);
		maps.put("faceUsers", faceUsers);
		maps.put("devices", devices);
		maps.put("userId", userId);
		maps.put("platform", platform);
		return httpClientUtils.httpPostFormReturnJSON(fam_video_fam_video_url+evn.getProperty("famvideo.user_Api.removeFaceOfDeviceByFaceAndDevice"),maps);
	}
	/**
	  * @Description(功能描述): 上传更新过的所有通行证(仅支持一人对同一设备只有一张通行证)
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/14 18:35
	  **/
	public JSONObject updateFaceOfDevice(@Param("faceDevice") String faceDevice, @Param("token") String  token, @Param("time") String  time,@Param("platform") String platform){
		Map<String, String> maps=new HashMap<>();
		maps.put("faceDevice", faceDevice);
		maps.put("token", token);
		maps.put("time", time);
		maps.put("platform", platform);
		return httpClientUtils.httpPostFormReturnJSON(fam_video_fam_video_url+evn.getProperty("famvideo.user_Api.updateFaceOfDevice"),maps);
	}
	/**
	  * @Description(功能描述):获取陌生人信息
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/14 18:44
	  **/
	public JSONObject getStranger(@Param("token") String token, @Param("userId") String userId, @Param("start") int start, @Param("limit") int limit,
								  @Param("timeStr") String timeStr,@Param("platform") String platform){
		Map<String, String> maps=new HashMap<>();
		maps.put("token", token);
		maps.put("userId", userId);
		maps.put("start", String.valueOf(start));
		maps.put("limit", String.valueOf(limit));
		maps.put("timeStr", timeStr);
		maps.put("platform", platform);
		return httpClientUtils.httpPostFormReturnJSON(fam_video_fam_video_url+evn.getProperty("famvideo.user_Api.getStranger"),maps);
	}

	/** 
	  * @Description(功能描述): 下载通行记录
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/14 18:47
	  **/ 
	public JSONObject downloadRecord(@Param("token") String token, @Param("userId") String userId, @Param("start") int start, @Param("limit") int limit,@Param("platform") String platform){
		Map<String, String> maps=new HashMap<>();
		maps.put("token", token);
		maps.put("userId", userId);
		maps.put("start", String.valueOf(start));
		maps.put("limit", String.valueOf(limit));
		maps.put("platform", platform);
		return httpClientUtils.httpPostFormReturnJSON(fam_video_fam_video_url+evn.getProperty("famvideo.user_Api.downloadRecord"),maps);
	}



}

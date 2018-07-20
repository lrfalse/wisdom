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
  * @Description(功能描述): 中商密码和远程开门接口
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/20 10:12
  **/ 
@Service
public class GatClientService {
	@Value("${gat.server_url}")
	private String get_server_url;	//中商密码和远程开门请求地址
	@Autowired
	private Environment evn;

	private static HttpClientUtils httpClientUtils = new HttpClientUtils();

	/**
	  * @Description(功能描述): 获取token
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/14 18:18
	  **/
	public JSONObject getToken(@Param("appId") String appId, @Param("appSecretKey") String appSecretKey){
		Map<String, String> maps=new HashMap<>();
		maps.put("appId", appId);				//第三方用户唯一凭证
		maps.put("appSecretKey", appSecretKey);	//第三方用户唯一凭证密钥 
		return httpClientUtils.httpPostFormReturnJSON(get_server_url+evn.getProperty("gat.device_Api.getToken"),maps);
	}

	/** 
	  * @Description(功能描述): 注册设备信息
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/20 14:34
	  **/ 
	public JSONObject registHard(@Param("token") String token, @Param("equiNo") String equiNo){
		Map<String, String> maps=new HashMap<>();
		maps.put("token", token);				//第三方用户唯一凭证
		maps.put("equiNo", equiNo);				//硬件设备号
//		maps.put("equiPass", equiPass);			//硬件密码（须和声波助手配置时的密码一致，默认123456）
//		maps.put("cityName", cityName);			//城市名称（例如 重庆市）
//		maps.put("areaName", areaName);			//区域名称（例如 渝中区）
//		maps.put("longitude", longitude);		//经度
//		maps.put("latitude", latitude);			//纬度
		return httpClientUtils.httpPostFormReturnJSON(get_server_url+evn.getProperty("gat.device_Api.registHard"),maps);
	}

	/**
	  * @Description(功能描述): 在线远程开门
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/20 10:16
	  **/ 
	public JSONObject openDoor(@Param("token") String token, @Param("equiNo") String equiNo){
		Map<String, String> maps=new HashMap<>();
		maps.put("token", token);				//接口调用凭据
		maps.put("equiNo", equiNo);				//硬件设备号
		return httpClientUtils.httpPostFormReturnJSON(get_server_url+evn.getProperty("gat.device_Api.openDoor"),maps);
	}

	/** 
	  * @Description(功能描述): 公共锁生成临时密码
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/20 10:17
	  **/ 
	public JSONObject doorByPass(@Param("token") String token, @Param("equiNo") String equiNo, @Param("minutes") String minutes
			, @Param("remark") String remark){
		Map<String, String> maps=new HashMap<>();
		maps.put("token", token);				//第三方用户唯一凭证
		maps.put("equiNo", equiNo);				//硬件设备号
		maps.put("minutes", minutes);			//分钟数（3,5,10,20），共 4 档
		maps.put("remark", remark);				//备注
		return httpClientUtils.httpPostFormReturnJSON(get_server_url+evn.getProperty("gat.device_Api.doorByPass"),maps);
	}
}

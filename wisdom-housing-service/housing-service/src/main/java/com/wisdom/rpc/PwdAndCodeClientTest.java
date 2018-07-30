package com.wisdom.rpc;

import com.alibaba.fastjson.JSONObject;
import com.wisdom.third.famvideo.GatClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description(功能描述) :新增二维码和密码验证
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/7/20 10:00
 **/
@RestController
@RequestMapping("/pwdAndcodeApi")
public class PwdAndCodeClientTest {
	@Autowired
	GatClientService gatClientService;

	private String appId="100000111";
	private String appSecretKey="6C0B03A44D053408F65CA049EE6DD5F0";
	private String token="";
	private Long expireTime;//token过期时间
	private String equiNo="610000392";//设备id

	/**
	  * @Description(功能描述): 获取token
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/20 10:23
	  **/
	@RequestMapping(value = "/getToken", method = RequestMethod.GET)
	public JSONObject getToken(){
		JSONObject jsonObject= gatClientService.getToken(appId, appSecretKey);
		if("0".equals(jsonObject.getString("code"))){
			JSONObject json=(JSONObject)jsonObject.get("data");
			token=  json.getString("token");
			expireTime=json.getLong("expireTime");
		}
		return jsonObject;
	}

	/**
	  * @Description(功能描述): 注册设备
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/20 14:43
	  **/
	@RequestMapping(value = "/registHard", method = RequestMethod.GET)
	public JSONObject registHard(){
//		String equiPass="";//硬件密码（须和声波助手配置时的密码一致，默认123456）
//		String cityName="";			//城市名称（例如 重庆市）
//		String areaName="";			//区域名称（例如 渝中区）
//		String longitude="";		//经度
//		String latitude="";			//纬度
		JSONObject jsonObject= gatClientService.registHard(token,equiNo);
		if("0".equals(jsonObject.getString("code"))){
			System.out.println("注册成功");
		}
		return jsonObject;
	}

	/**
	  * @Description(功能描述): 在线远程开门
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/20 10:25
	  **/
	@RequestMapping(value = "/openDoor", method = RequestMethod.GET)
	public JSONObject openDoor(){
		JSONObject jsonObject= gatClientService.openDoor(token, equiNo);
		if("0".equals(jsonObject.getString("code"))){
			 System.out.println("开门成功");
		}
		return jsonObject;
	}

	/**
	  * @Description(功能描述): 密码开门
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/20 10:26
	  **/
	@RequestMapping(value = "/doorByPass", method = RequestMethod.GET)
	public JSONObject doorByPass(){
		String minutes="20"; 	//密码有效时间分钟数（3,5,10,20），共 4 档
		String remark="备注";	//备注
		JSONObject jsonObject= gatClientService.doorByPass(token, equiNo,minutes,remark);
		if("0".equals(jsonObject.getString("code"))){
			JSONObject json=(JSONObject)jsonObject.get("data");
			String passwd=json.getString("password");			//密码
			String passendtime=json.getString("passendtime");	//过期时间

			System.out.println("密码生成成功：passwd="+passwd+"passendtime="+passendtime);
		}
		return jsonObject;
	}
}

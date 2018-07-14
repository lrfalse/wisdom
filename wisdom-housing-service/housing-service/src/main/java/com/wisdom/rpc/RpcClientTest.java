package com.wisdom.rpc;

import com.alibaba.fastjson.JSONObject;
import com.wisdom.third.famvideo.FamVideoClient;
import com.wisdom.third.famvideo.FamVideoClientService;
import com.wisdom.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RpcClientTest {
	@Autowired
	FamVideoClient famVideoClent;

	@Autowired
	FamVideoClientService famVideoClentService;
	private String platform="153026671031026";			//平台id
	private String signKey="yong_chuan";				//key
	private String token="";							//接口调用依据
	private String deviceId="600091018";				//设备id
	private String password=MD5.MD5Encode("test");//密码

    /**
      * @Description(功能描述): 获取token
      * @author(作者): lrfalse<wangliyou>
      * @date(开发日期): 2018/7/14 12:15
      **/
    @RequestMapping(value = "/getToken", method = RequestMethod.GET)
    public JSONObject get(){
		JSONObject jsonObject= famVideoClentService.getToken(platform, "yong_chuan");
		if("success".equals(jsonObject.get("msg"))){
			JSONObject json=(JSONObject)jsonObject.get("result");
			token= (String) json.get("token");
		}
		return jsonObject;
	}

	/**
	  * @Description(功能描述): 新增平台帐号
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/14 18:52
	  **/
	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public JSONObject addUser(){
		String account="test";							//帐号
		String name="王大仙";								//昵称
		String passportUrl="www.baidu.com";				//通行证回调地址
		JSONObject jsonObject=  famVideoClentService.addUser(account,password,name,passportUrl,platform,token);
		if("success".equals(jsonObject.get("msg"))){
			JSONObject json=(JSONObject)jsonObject.get("result");
			deviceId= (String) json.get("deviceId");	//设备id
		}
		return jsonObject;
	}

	/**
	  * @Description(功能描述): 添加设备
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/14 19:22
	  **/
	String userId="";										//用户id
	private String doorId;//门阀id
	@RequestMapping(value = "/addDevice", method = RequestMethod.GET)
	public JSONObject addDevice(){
		String deviceName= "翼晟俊测试设备";					//设备名称
		JSONObject jsonObject= famVideoClentService.addDevice(deviceId,deviceName,password,userId,token);
		if("success".equals(jsonObject.get("msg"))){
			JSONObject json=(JSONObject)jsonObject.get("result");
			doorId= (String) json.get("areaServerId");	//设备id
		}
		return jsonObject;

	}
	/**
	  * @Description(功能描述): 删除设备
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/14 22:35
	  **/
	@RequestMapping(value = "/deleteDevice", method = RequestMethod.GET)
	public JSONObject deleteDevice(){
		return famVideoClentService.deleteDevice(doorId,userId,token);
	}

	/**
	  * @Description(功能描述): 上传人脸票据
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/14 22:55
	  **/
	private String id;	//人脸用户id
	@RequestMapping(value = "/uploadFace", method = RequestMethod.GET)
	public JSONObject uploadFace(){
		String name="测试";
		int type=1;		//类型（0:黑名单,1:会员,3:陌生人）
		String imgUrl="https://ss2.bdstatic.com/8_V1bjqh_Q23odCf/pacific/1670704645.jpg";	//头像下载地址(不能超过150个字符)
		String img="";
		String handler="王大仙";
		String id="1";
		String no="1";
		JSONObject jsonObject= famVideoClentService.uploadFace(name,token,userId,type,imgUrl,img,handler,id,no,platform);
		if("success".equals(jsonObject.get("msg"))){
			JSONObject json=(JSONObject)jsonObject.get("result");
			id= (String) json.get("id");	//人脸用户id
		}
		return jsonObject;
	}

	/**
	  * @Description(功能描述): 删除人脸票据
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/14 23:12
	  **/
	@RequestMapping(value = "/removeFace", method = RequestMethod.GET)
	public JSONObject removeFace(){
		return famVideoClentService.removeFace(id,token);
	}
    
}

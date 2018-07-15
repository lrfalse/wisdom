package com.wisdom.rpc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.media.sound.FFT;
import com.wisdom.third.famvideo.FamVideoClient;
import com.wisdom.third.famvideo.FamVideoClientService;
import com.wisdom.utils.DateUtils;
import com.wisdom.utils.MD5;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api")
public class RpcClientTest {
	@Autowired
	FamVideoClient famVideoClent;

	//TODO 14陌生人接口暂未实现 ，15.下载通行记录（用回调替用）
	//TODO 9 与13功能雷同，用暂用13接口
	@Autowired
	FamVideoClientService famVideoClentService;
	private String platform="153161585518128";			//平台id
	private String signKey="yong_chuan";				//key
	private String token="";							//接口调用依据
	private String deviceId="600091810";				//设备id （查看硬件设备上设备串号）
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
	String userId="153163964637471";										//用户id  社区id 153163964637471
	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public JSONObject addUser(){
		String account="test4";							//帐号
		String name="王大仙4";								//昵称
		String passportUrl="www.baidu.com";				//通行证回调地址
		JSONObject jsonObject=  famVideoClentService.addUser(account,password,name,passportUrl,platform,token);
		jsonObject.get("result");
		if("success".equals(jsonObject.get("msg"))){
			userId=(String)jsonObject.get("result");
		}
		return jsonObject;
	}

	/**
	  * @Description(功能描述): 添加设备
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/14 19:22
	  **/
	private String doorId="153164613460035686";//门闸id   153164613460035686
	@RequestMapping(value = "/addDevice", method = RequestMethod.GET)
	public JSONObject addDevice(){
		String deviceName= "翼晟俊测试设备";					//设备名称
		JSONObject jsonObject= famVideoClentService.addDevice(deviceId,deviceName,password,userId,token,platform);
		if("success".equals(jsonObject.get("msg"))){
			doorId=jsonObject.getString("result");//门闸id   -- 153164039781790952
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
		return famVideoClentService.deleteDevice(doorId,userId,token,platform);
	}

	/**
	  * @Description(功能描述): 上传人脸票据
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/14 22:55
	  **/

	@RequestMapping(value = "/uploadFace", method = RequestMethod.GET)
	public JSONObject uploadFace(){
		String name="测试";
		int type=1;		//类型（0:黑名单,1:会员,3:陌生人）
		String imgUrl="https://ss2.bdstatic.com/8_V1bjqh_Q23odCf/pacific/1670704645.jpg";	//头像下载地址(不能超过150个字符)
		String img="";
		String handler="王大仙";
		String id="";	//人脸用户id：为空时添加，不为空时修改
		String no="1";	//人脸编号，这个no类似于学生编号，职工编号
		JSONObject jsonObject= famVideoClentService.uploadFace(name,token,userId,type,imgUrl,img,handler,id,no,platform);  //TODO userId 对应user

		if("success".equals(jsonObject.get("msg"))){
			String userFaceId =jsonObject.getString("result");	//注册成功后的人脸用户id  153164129024332
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
		 String id="153164129024332";
		JSONObject jsonObject=famVideoClentService.removeFace(id,token,platform);
		if("success".equals(jsonObject.getString("msg"))){	//删除成功

		}
		return jsonObject;
	}
	/**
	 * @Description(功能描述): 批量删除人脸票
	 * @author(作者): lrfalse<wangliyou>
	 * @date(开发日期): 2018/7/14 23:12
	 **/
	@RequestMapping(value = "/batchRemoveFaceUser", method = RequestMethod.GET)
	public JSONObject batchRemoveFaceUser(){
		String id="153164129024332";	//人脸用户id,多个中间用逗号隔开
		JSONObject jsonObject=famVideoClentService.batchRemoveFaceUser(id,token,platform);
		if("success".equals(jsonObject.getString("msg"))){	//删除成功

		}
		return jsonObject;
	}
	/**
	 * @Description(功能描述): 通过人脸用户来上传人脸票与设备的关系（通行证）
	 * @author(作者): lrfalse<wangliyou>
	 * @date(开发日期): 2018/7/14 23:12
	 **/
	@RequestMapping(value = "/uploadFaceOfDeviceByFace", method = RequestMethod.GET)
	public JSONObject uploadFaceOfDeviceByFace(){
		String faceUserId="153164426420063";		//用户人脸票据id
		Long faceUser=153164426420063L;					//人脸票据id
		Map map=new HashMap();
		map.put("date","2018-07-15,2218-07-15");		//有效时间段
		map.put("deviceId",doorId);						//门闸id
		map.put("endTime", "23:59:59");
		map.put("startTime","23:59:59");
		map.put("faceuser",faceUserId);
		map.put("deviceType",1);						//0：摄像头，1：门闸
		map.put("user",userId);						//用户id  用user字段
		List list =new ArrayList();						//必须多个对象
		list.add(map);
		String faceDevice=JSON.toJSONString(list);
		JSONObject jsonObject=famVideoClentService.uploadFaceOfDeviceByFace(faceDevice,token,faceUser,platform);
		if("success".equals(jsonObject.getString("msg"))){	//上传成功成功
//{"hasnext":false,"msg":"success"}
		}
		return jsonObject;
	}

	/**
	 * @Description(功能描述): 通过批次号上传人脸与设备的对应关系 :  批次可理解成楼栋
	 * @author(作者): lrfalse<wangliyou>
	 * @date(开发日期): 2018/7/14 18:35
	 **/
	@RequestMapping(value = "/uploadFaceOfDevice", method = RequestMethod.GET)
	public JSONObject uploadFaceOfDevice(){
		String faceUserId="153164426420063";				//用户人脸票据id
		Long batch=null;									// 批次，为空时新增，不为空修改
		Map map=new HashMap();
		map.put("date","2018-07-15,2218-07-15");		//有效时间段
		map.put("deviceId",doorId);						//门闸id
		map.put("endTime", "23:59:59");
		map.put("startTime","23:59:59");
		map.put("faceuser",faceUserId);
		map.put("deviceType",1);							//0：摄像头，1：门闸
		map.put("user",userId);							//用户id  用user字段
		List list =new ArrayList();							//必须多个对象
		list.add(map);
		String faceDevice=JSON.toJSONString(list);
		JSONObject jsonObject=famVideoClentService.uploadFaceOfDevice(faceDevice,token,batch,platform);
		if("success".equals(jsonObject.getString("msg"))){	//上传成功成功
			//{"hasnext":false,"msg":"success"}
			batch=jsonObject.getLong("result");
		}
		return jsonObject;
	}

	//通行证参数
	class faceDevice{
		Date date;//"2018-07-15,2218-07-15,"
		String  devieId;	//设备id
		String endTime;	//结束时间不包括日期
		String startTime;	//开始时间不包括日期
		String faceuser;	//人脸用户id
		String deviceType;	//类型，0：摄像头，1：门闸
		String userId;		//用户id
		//String batch; 	//批次号,如调用过’通过批次号上传人脸与设备的对应关系（通行证）’接口，则需要传入此参数,此参数也由次接口获得，如不传入则后面批次操作时则对此人脸无用
	}
    
}

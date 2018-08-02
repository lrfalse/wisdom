package com.wisdom.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.wisdom.log.LogWriter;
import com.wisdom.model.Member;
import com.wisdom.api.Resp;
import com.wisdom.service.MemberService;
import com.wisdom.third.famvideo.FamVideoClientService;
import com.wisdom.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Value("${famvideo.platform}")
    private String platform;
    @Value("${famvideo.signKey}")
    private String signKey;
    @Autowired
    MemberService memberService;
    @Autowired
    FamVideoClientService famVideoClentService;
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Resp get(@RequestParam(value = "id", required = false, defaultValue = "0") Long id){
        return this.memberService.queryByObject(id);
    }
    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public Resp set(@RequestBody Member member){
        return this.memberService.save(member);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Resp list(@RequestBody Member member){
        return this.memberService.queryByList(member);
    }

    @RequestMapping(value = "/building_phone", method = RequestMethod.POST)
    public Resp buildingPhone(@RequestBody Member member){
        return this.memberService.updateByPhone(member);
    }

    @RequestMapping(value = "/upload_face_img", method = RequestMethod.POST)
    public Resp uploadFaceImg(@RequestBody Member member){
        String token="";
        JSONObject jsonObject= famVideoClentService.getToken(platform, signKey);
        if(jsonObject.get("result")!=null){
            JSONObject json=(JSONObject)jsonObject.get("result");
            token= (String) json.get("token");
        }
        List<Map> memberList=this.memberService.queryByMemberUserIdInfo(member);
        if(!token.equals("")) {
            for(Map m:memberList) {
                String name=m.get("name").toString();
                int type=1;		//类型（0:黑名单,1:会员,3:陌生人）
                String imgUrl=member.getImgUrl();	//头像下载地址(不能超过150个字符)
                String handler=m.get("name").toString();
                String userId=(m.get("userId")==null?"":m.get("userId").toString());
                if(!userId.equals("")) {
                    JSONObject json=famVideoClentService.uploadFace(name, token, userId, type, imgUrl, "", handler, "", "", platform);
                    LogWriter.info("人脸上传："+json);
                    if("success".equals(jsonObject.get("msg"))){
                        String userFaceId =json.getString("result");
                        Long faceUser=Long.parseLong(userFaceId);					//人脸票据id
                        Map map=new HashMap();
                        map.put("date",DateUtils.currentTimeDay()+",2218-07-15");		//有效时间段
                        map.put("deviceId",m.get("doorId"));						//门闸id
                        map.put("endTime", "23:59:59");
                        map.put("startTime","23:59:59");
                        map.put("faceuser",userFaceId);
                        map.put("deviceType",1);						//0：摄像头，1：门闸
                        map.put("user",userId);						//用户id  用user字段
                        List list =new ArrayList();						//必须多个对象
                        list.add(map);
                        String faceDevice=JSON.toJSONString(list);
                        JSONObject jsont=famVideoClentService.uploadFaceOfDeviceByFace(faceDevice,token,faceUser,platform);
                        LogWriter.info("人脸设备绑定上传："+jsont);
                    }


                }
            }
        }
        return this.memberService.updateByFaceImg(member);
    }


    @RequestMapping(value = "/is_identity", method = RequestMethod.POST)
    public Resp isIdentity(@RequestBody Member member){
        return this.memberService.queryByPerfectIdentity(member);
    }
    @RequestMapping(value = "/is_recognition", method = RequestMethod.POST)
    public Resp isRecognition(@RequestBody Member member){
        return this.memberService.queryByFaceRecognition(member);
    }

    @RequestMapping(value = "/search_housing_room", method = RequestMethod.POST)
    public Resp searchHousingRoom(@RequestBody Map map){
        return this.memberService.queryByHousingRoom(map);
    }
    @RequestMapping(value = "/search_room_member", method = RequestMethod.POST)
    public Resp searchRoomMember(@RequestBody Map map){
        return this.memberService.queryByRoomMember(map);
    }

    @RequestMapping(value = "/search_invitation_code", method = RequestMethod.POST)
    public Resp searchInvitationCode(@RequestParam("rootId") Long rootId){
        String code=this.memberService.queryByInvitationCode(rootId);
        return Resp.success("code",(code==null?"":code));
    }
}

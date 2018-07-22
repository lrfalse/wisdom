package com.wisdom.controller;

import com.alibaba.fastjson.JSONObject;
import com.wisdom.api.MemberClient;
import com.wisdom.api.MembertoRoomClient;
import com.wisdom.vo.MemberVo;
import com.wisdom.vo.MembertoRoomVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    MemberClient memberClient;
    @Autowired
    MembertoRoomClient membertoRoomClient;


    @RequestMapping(value="/building_phone", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject buildingPhone(MemberVo member){
        JSONObject return_json=new JSONObject();
        MembertoRoomVo membertoRoomVo=new MembertoRoomVo();
        membertoRoomVo.setRoomId(member.getRoomId());
        membertoRoomVo.setPhone(member.getPhone());
        JSONObject o_result=this.membertoRoomClient.isMemberToRoom(membertoRoomVo);
        if(o_result!=null){
            JSONObject o_result_data=o_result.getJSONObject("data");
            //手机号存在房间号中验证
            boolean o_result_flag=o_result_data.getBoolean("flag");
            if(o_result_flag){
                //手机号加短信码验证
                if(member.getValidCodeType()==1){
                    boolean flag_a=false;
                    if(!flag_a){
                        return_json.put("code","400");
                        return_json.put("err_msg","短信码不正确，请仔细核对！");
                        return return_json;
                    }
                }else{//手机号加邀请码验证
                    JSONObject code_json=this.memberClient.searchInvitationCode(member.getRoomId());
                    String code=code_json.getJSONObject("data").getString("code");
                    if(!code.equals(member.getCode())){
                        return_json.put("code","400");
                        return_json.put("err_msg","邀请码不正确，请联系业主！");
                        return return_json;
                    }
                }
            }else{
                return_json.put("code","400");
                return_json.put("err_msg","您在该小区未注册，请联系业主！");
                return return_json;
            }
        }
        return memberClient.buildingPhone(member);
    }

    @RequestMapping(value="/get_member", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getMember(MemberVo member){
        return memberClient.search(member);
    }

    @RequestMapping(value="/set", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject setMemeber(MemberVo member){
        return memberClient.set(member);
    }

    @RequestMapping(value="/search_housing_room", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject searchHousingRoom(Long memberId){
        Map map=new HashMap();
        map.put("memberId",memberId);
        return this.memberClient.searchHousingRoom(map);
    }

    @RequestMapping(value="/search_room_member", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject searchRoomMember(Long roomId){
        Map map=new HashMap();
        map.put("roomId",roomId);
        return this.memberClient.searchRoomMember(map);
    }

}

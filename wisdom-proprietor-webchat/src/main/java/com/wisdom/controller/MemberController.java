package com.wisdom.controller;

import com.alibaba.fastjson.JSONObject;
import com.wisdom.api.MemberClient;
import com.wisdom.vo.MemberVo;
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


    @RequestMapping(value="/building_phone", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject buildingPhone(MemberVo member){
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

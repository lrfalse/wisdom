package com.wisdom.controller;

import com.alibaba.fastjson.JSONObject;
import com.wisdom.api.MemberClient;
import com.wisdom.vo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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


}

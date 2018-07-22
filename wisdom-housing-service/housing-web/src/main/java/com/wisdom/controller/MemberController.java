package com.wisdom.controller;

import com.wisdom.model.Member;
import com.wisdom.api.Resp;
import com.wisdom.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    MemberService memberService;
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

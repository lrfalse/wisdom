package com.wisdom.controller;

import com.wisdom.model.MembertoRoom;
import com.wisdom.api.Resp;
import com.wisdom.service.MembertoRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member_to_room")
public class MembertoRoomController {
    @Autowired
    MembertoRoomService membertoRoomService;
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Resp get(@RequestParam(value = "id", required = false, defaultValue = "0") Long id){
        return this.membertoRoomService.queryByObject(id);
    }
    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public Resp set(@RequestBody MembertoRoom membertoRoom){
        return this.membertoRoomService.save(membertoRoom);
    }
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Resp list(@RequestBody MembertoRoom membertoRoom){
        return this.membertoRoomService.queryByList(membertoRoom);
    }
}

package com.wisdom.controller;

import com.wisdom.model.MemberFace;
import com.wisdom.api.Resp;
import com.wisdom.service.MemberFaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/memberface")
public class MemberFaceController {
    @Autowired
    MemberFaceService memberFaceService;
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Resp get(@RequestParam(value = "id", required = false, defaultValue = "0") Long id){
        return this.memberFaceService.queryByObject(id);
    }
    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public Resp set(@RequestBody MemberFace memberFace){
        return this.memberFaceService.save(memberFace);
    }
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Resp list(@RequestBody MemberFace memberFace){
        return this.memberFaceService.queryByList(memberFace);
    }
}

package com.wisdom.controller;

import com.wisdom.model.WechatValid;
import com.wisdom.api.Resp;
import com.wisdom.service.WechatValidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wechatvaild")
public class WechatValidController {
    @Autowired
    WechatValidService wechatValidService;
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Resp get(@RequestParam(value = "id", required = false, defaultValue = "0") Long id){
        return this.wechatValidService.queryByObject(id);
    }
    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public Resp set(@RequestBody WechatValid wechatValid){
        return this.wechatValidService.save(wechatValid);
    }
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Resp list(@RequestBody WechatValid wechatValid){
        return this.wechatValidService.queryByList(wechatValid);
    }
}

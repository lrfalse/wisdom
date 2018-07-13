package com.wisdom.controller;

import com.wisdom.model.Dicbase;
import com.wisdom.api.Resp;
import com.wisdom.service.DicbaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dicbase")
public class DicbaseController {
    @Autowired
    DicbaseService dicbaseService;
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Resp get(@RequestParam(value = "id", required = false, defaultValue = "0") Long id){
        return this.dicbaseService.queryByObject(id);
    }
    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public Resp set(@RequestBody Dicbase dicbase){
        return this.dicbaseService.save(dicbase);
    }
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Resp list(@RequestBody Dicbase dicbase){
        return this.dicbaseService.queryByList(dicbase);
    }
}

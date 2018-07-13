package com.wisdom.controller;

import com.wisdom.model.Countrys;
import com.wisdom.api.Resp;
import com.wisdom.service.CountrysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/countrys")
public class CountrysController {
    @Autowired
    CountrysService countrysService;
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Resp get(@RequestParam(value = "id", required = false, defaultValue = "0") Long id){
        return this.countrysService.queryByObject(id);
    }
    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public Resp set(@RequestBody Countrys countrys){
        return this.countrysService.save(countrys);
    }
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Resp list(@RequestBody Countrys countrys){
        return this.countrysService.queryByList(countrys);
    }
}

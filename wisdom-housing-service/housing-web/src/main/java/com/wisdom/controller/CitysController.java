package com.wisdom.controller;

import com.wisdom.model.Citys;
import com.wisdom.api.Resp;
import com.wisdom.service.CitysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/citys")
public class CitysController {
    @Autowired
    CitysService citysService;
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Resp get(@RequestParam(value = "id", required = false, defaultValue = "0") Long id){
        return this.citysService.queryByObject(id);
    }
    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public Resp set(@RequestBody Citys citys){
        return this.citysService.save(citys);
    }
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Resp list(@RequestBody Citys citys){
        return this.citysService.queryByList(citys);
    }
}

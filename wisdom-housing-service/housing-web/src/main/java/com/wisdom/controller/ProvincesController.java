package com.wisdom.controller;

import com.wisdom.model.Provinces;
import com.wisdom.api.Resp;
import com.wisdom.service.ProvincesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/provinces")
public class ProvincesController {
    @Autowired
    ProvincesService provincesService;
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Resp get(@RequestParam(value = "id", required = false, defaultValue = "0") Long id){
        return this.provincesService.queryByObject(id);
    }
    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public Resp set(@RequestBody Provinces provinces){
        return this.provincesService.save(provinces);
    }
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Resp list(@RequestBody Provinces provinces){
        return this.provincesService.queryByList(provinces);
    }
}

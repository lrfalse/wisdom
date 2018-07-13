package com.wisdom.controller;

import com.wisdom.model.HousingDervice;
import com.wisdom.api.Resp;
import com.wisdom.service.HousingDerviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/housingdervice")
public class HousingDerviceController {
    @Autowired
    HousingDerviceService housingDerviceService;
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Resp get(@RequestParam(value = "id", required = false, defaultValue = "0") Long id){
        return this.housingDerviceService.queryByObject(id);
    }
    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public Resp set(@RequestBody HousingDervice housingDervice){
        return this.housingDerviceService.save(housingDervice);
    }
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Resp list(@RequestBody HousingDervice housingDervice){
        return this.housingDerviceService.queryByList(housingDervice);
    }
}

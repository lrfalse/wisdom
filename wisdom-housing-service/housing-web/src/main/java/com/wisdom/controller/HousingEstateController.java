package com.wisdom.controller;

import com.wisdom.model.HousingEstate;
import com.wisdom.api.Resp;
import com.wisdom.service.HousingEstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/housingestate")
public class HousingEstateController {
    @Autowired
    HousingEstateService housingEstateService;
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Resp get(@RequestParam(value = "id", required = false, defaultValue = "0") Long id){
        return this.housingEstateService.queryByObject(id);
    }
    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public Resp set(@RequestBody HousingEstate housingEstate){
        return this.housingEstateService.save(housingEstate);
    }
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Resp list(@RequestBody HousingEstate housingEstate){
        return this.housingEstateService.queryByList(housingEstate);
    }
}

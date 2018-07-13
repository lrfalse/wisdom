package com.wisdom.controller;

import com.wisdom.model.Building;
import com.wisdom.api.Resp;
import com.wisdom.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/building")
public class BuildingController {
    @Autowired
    BuildingService buildingService;
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Resp get(@RequestParam(value = "id", required = false, defaultValue = "0") Long id){
        return this.buildingService.queryByObject(id);
    }
    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public Resp set(@RequestBody Building building){
            return this.buildingService.save(building);
    }
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Resp list(@RequestBody Building building){
        return this.buildingService.queryByList(building);
    }
}

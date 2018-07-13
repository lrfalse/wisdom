package com.wisdom.controller;

import com.alibaba.fastjson.JSONObject;
import com.wisdom.api.BuildingClient;
import com.wisdom.api.HousingEstateClient;
import com.wisdom.api.RoomClient;
import com.wisdom.vo.AreasVo;
import com.wisdom.vo.BuildingVo;
import com.wisdom.vo.HousingEstateVo;
import com.wisdom.vo.RoomVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/room")
public class RoomController {
    @Autowired
    HousingEstateClient housingEstateClient;

    @Autowired
    BuildingClient buildingClient;

    @Autowired
    RoomClient roomClient;

    @RequestMapping(value="/search_room", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject searchRoom(@RequestParam Long buildingId){
        RoomVo vo=new RoomVo();
        vo.setBuildingId(buildingId);
        return this.roomClient.search(vo);
    }

    @RequestMapping(value="/search_housing_estate", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject searchHousingEstate(@RequestParam Long areasId){
        HousingEstateVo vo=new HousingEstateVo();
        vo.setAreasId(areasId);
        return this.housingEstateClient.search(vo);
    }

    @RequestMapping(value="/search_building", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject searchBuilding(@RequestParam Long housingEstateId){
        BuildingVo vo=new BuildingVo();
        vo.setHousingEstateId(housingEstateId);
        return this.buildingClient.search(vo);
    }
}

package com.wisdom.controller;

import com.wisdom.api.Resp;
import com.wisdom.model.Areas;
import com.wisdom.service.AreasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/areas")
public class AreasController {
    @Autowired
    AreasService areasService;
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Resp get(@RequestParam(value = "id", required = false, defaultValue = "0") Long id){
        return this.areasService.queryByObject(id);
    }
    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public Resp set(@RequestBody Areas areas){
       System.out.println(this.areasService.save(areas));
        return this.areasService.save(areas);
    }
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Resp list(@RequestBody Areas areas){
        return this.areasService.queryByList(areas);
    }

}

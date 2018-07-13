package com.wisdom.service.impl;

import com.wisdom.framework.database.conf.TargetDataSource;
import com.wisdom.log.LogWriter;
import com.wisdom.mapper.BuildingMapper;
import com.wisdom.model.Building;
import com.wisdom.service.BuildingService;
import com.wisdom.api.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    BuildingMapper mapper;
    @TargetDataSource("ds")
    public Resp save(Building building){
        try {
            if (building.getId() == null) {
                this.mapper.insertSelective(building);
            } else {
                this.mapper.updateByPrimaryKeySelective(building);
            }
        }catch (Exception e){
            LogWriter.error(e,"保存失败");
            return Resp.error("-1","保存失败");
        }
        return Resp.success("data",building);
    }

    @TargetDataSource("ds")
    public Resp queryByObject(Long id) {
        Building building=new Building();
        building.setId(id);
        return Resp.success("data",this.mapper.selectOne(building));
    }

    @TargetDataSource("ds")
    public Resp queryByList(Building building) {
        return Resp.success("data",this.mapper.select(building));
    }
}

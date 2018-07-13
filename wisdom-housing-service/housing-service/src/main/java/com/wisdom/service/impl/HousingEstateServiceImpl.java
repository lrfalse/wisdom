package com.wisdom.service.impl;

import com.wisdom.framework.database.conf.TargetDataSource;
import com.wisdom.log.LogWriter;
import com.wisdom.mapper.HousingEstateMapper;
import com.wisdom.model.HousingEstate;
import com.wisdom.service.HousingEstateService;
import com.wisdom.api.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HousingEstateServiceImpl implements HousingEstateService {
    @Autowired
    HousingEstateMapper mapper;
    @TargetDataSource("ds")
    public Resp save(HousingEstate housingEstate) {
        try {
            if (housingEstate.getId() == null) {
                this.mapper.insertSelective(housingEstate);
            } else {
                this.mapper.updateByPrimaryKeySelective(housingEstate);
            }
        }catch (Exception e){
            LogWriter.error(e,"保存失败");
            return Resp.error("-1","保存失败");
        }
        return Resp.success("data",housingEstate);
    }

    @TargetDataSource("ds")
    public Resp queryByObject(Long id) {
        HousingEstate housingEstate=new HousingEstate();
        housingEstate.setId(id);
        return Resp.success("data",this.mapper.selectOne(housingEstate));
    }

    @TargetDataSource("ds")
    public Resp queryByList(HousingEstate housingEstate) {
        return Resp.success("data",this.mapper.select(housingEstate));
    }
}

package com.wisdom.service.impl;

import com.wisdom.framework.database.conf.TargetDataSource;
import com.wisdom.log.LogWriter;
import com.wisdom.mapper.HousingDerviceMapper;
import com.wisdom.model.HousingDervice;
import com.wisdom.service.HousingDerviceService;
import com.wisdom.api.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HousingDerviceServiceImpl implements HousingDerviceService {
    @Autowired
    HousingDerviceMapper mapper;
    @TargetDataSource("ds")
    public Resp save(HousingDervice housingDervice) {
        try {
            if (housingDervice.getId() == null) {
                this.mapper.insertSelective(housingDervice);
            } else {
                this.mapper.updateByPrimaryKeySelective(housingDervice);
            }
        }catch (Exception e){
            LogWriter.error(e,"保存失败");
            return Resp.error("-1","保存失败");
        }
        return Resp.success("data",housingDervice);
    }

    @TargetDataSource("ds")
    public Resp queryByObject(Long id) {
        HousingDervice housingDervice=new HousingDervice();
        housingDervice.setId(id);
        return Resp.success("data",this.mapper.selectOne(housingDervice));
    }

    @TargetDataSource("ds")
    public Resp queryByList(HousingDervice housingDervice) {
        return Resp.success("data",this.mapper.select(housingDervice));
    }
}

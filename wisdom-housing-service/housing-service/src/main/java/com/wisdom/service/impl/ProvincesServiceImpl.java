package com.wisdom.service.impl;

import com.wisdom.framework.database.conf.TargetDataSource;
import com.wisdom.log.LogWriter;
import com.wisdom.mapper.ProvincesMapper;
import com.wisdom.model.Provinces;
import com.wisdom.service.ProvincesService;
import com.wisdom.api.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvincesServiceImpl implements ProvincesService {
    @Autowired
    ProvincesMapper mapper;
    @TargetDataSource("ds")
    public Resp save(Provinces provinces) {

        try {
            if (provinces.getId() == null) {
                this.mapper.insertSelective(provinces);
            } else {
                this.mapper.updateByPrimaryKeySelective(provinces);
            }
        }catch (Exception e){
            LogWriter.error(e,"保存失败");
            return Resp.error("-1","保存失败");
        }
        return Resp.success("data",provinces);
    }

    @TargetDataSource("ds")
    public Resp queryByObject(Long id) {
        Provinces provinces=new Provinces();
        provinces.setId(id);
        return Resp.success("data",this.mapper.selectOne(provinces));
    }

    @TargetDataSource("ds")
    public Resp queryByList(Provinces provinces) {

        return Resp.success("data",this.mapper.select(provinces));
    }
}

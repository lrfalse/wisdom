package com.wisdom.service.impl;

import com.wisdom.framework.database.conf.TargetDataSource;
import com.wisdom.log.LogWriter;
import com.wisdom.mapper.CountrysMapper;
import com.wisdom.model.Countrys;
import com.wisdom.service.CountrysService;
import com.wisdom.api.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountrysServiceImpl implements CountrysService {
    @Autowired
    CountrysMapper mapper;
    @TargetDataSource("ds")
    public Resp save(Countrys countrys) {
        try {
            if (countrys.getId() == null) {
                this.mapper.insertSelective(countrys);
            } else {
                this.mapper.updateByPrimaryKeySelective(countrys);
            }
        }catch (Exception e){
            LogWriter.error(e,"保存失败");
            return Resp.error("-1","保存失败");
        }
        return Resp.success("data",countrys);
    }

    @TargetDataSource("ds")
    public Resp queryByObject(Long id) {
        Countrys countrys=new Countrys();
        countrys.setId(id);
        return Resp.success("data",this.mapper.selectOne(countrys));
    }

    @TargetDataSource("ds")
    public Resp queryByList(Countrys countrys) {
        return Resp.success("data",this.mapper.select(countrys));
    }
}

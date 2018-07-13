package com.wisdom.service.impl;

import com.wisdom.framework.database.conf.TargetDataSource;
import com.wisdom.log.LogWriter;
import com.wisdom.mapper.CitysMapper;
import com.wisdom.model.Citys;
import com.wisdom.service.CitysService;
import com.wisdom.api.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CitysServiceImpl implements CitysService {
    @Autowired
    CitysMapper mapper;

    @TargetDataSource("ds")
    public Resp save(Citys citys){
        try {
            if (citys.getId() == null) {
                this.mapper.insertSelective(citys);
            } else {
                this.mapper.updateByPrimaryKeySelective(citys);
            }
        }catch (Exception e){
            LogWriter.error(e,"保存失败");
            return Resp.error("-1","保存失败");
        }
        return Resp.success("data",citys);
    }

    @TargetDataSource("ds")
    public Resp queryByObject(Long id) {
        Citys citys=new Citys();
        citys.setId(id);
        return Resp.success("data",this.mapper.selectOne(citys));
    }

    @TargetDataSource("ds")
    public Resp queryByList(Citys citys) {
        return Resp.success("data",this.mapper.select(citys));
    }
}

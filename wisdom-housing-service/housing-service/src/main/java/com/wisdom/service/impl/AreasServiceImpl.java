package com.wisdom.service.impl;

import com.wisdom.framework.database.conf.TargetDataSource;
import com.wisdom.log.LogWriter;
import com.wisdom.mapper.AreasMapper;
import com.wisdom.model.Areas;
import com.wisdom.service.AreasService;
import com.wisdom.api.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AreasServiceImpl implements AreasService {
    @Autowired
    AreasMapper mapper;

    @TargetDataSource("ds")
    public Resp save(Areas areas){
        try {
            if (areas.getId() == null) {
                this.mapper.insertSelective(areas);
            } else {
                this.mapper.updateByPrimaryKeySelective(areas);
            }
            return Resp.success("data",areas);
        }catch (Exception e){
            LogWriter.error(e,"保存失败");
            return Resp.error("-1","保存失败");
        }
    }
    @TargetDataSource("ds")
    public Resp queryByObject(Long id) {
        Areas areas=new Areas();
        areas.setId(id);
        return Resp.success("data",this.mapper.selectOne(areas));
    }
    @TargetDataSource("ds")
    public Resp queryByList(Areas areas) {
        return Resp.success("data",this.mapper.select(areas));
    }
}

package com.wisdom.service.impl;

import com.wisdom.framework.database.conf.TargetDataSource;
import com.wisdom.log.LogWriter;
import com.wisdom.mapper.DicbaseMapper;
import com.wisdom.model.Dicbase;
import com.wisdom.service.DicbaseService;
import com.wisdom.api.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DicbaseServiceImpl implements DicbaseService {
    @Autowired
    DicbaseMapper mapper;
    @TargetDataSource("ds")
    public Resp save(Dicbase dicbase) {
        try {
            if (dicbase.getId() == null) {
                this.mapper.insertSelective(dicbase);
            } else {
                this.mapper.updateByPrimaryKeySelective(dicbase);
            }
        }catch (Exception e){
            LogWriter.error(e,"保存失败");
            return Resp.error("-1","保存失败");
        }
        return Resp.success("data",dicbase);
    }

    @TargetDataSource("ds")
    public Resp queryByObject(Long id) {

        Dicbase dicbase=new Dicbase();
        dicbase.setId(id);
        return Resp.success("data",this.mapper.selectOne(dicbase));
    }

    @TargetDataSource("ds")
    public Resp queryByList(Dicbase dicbase) {
        return Resp.success("data",this.mapper.select(dicbase));
    }
}

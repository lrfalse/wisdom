package com.wisdom.service.impl;

import com.wisdom.framework.database.conf.TargetDataSource;
import com.wisdom.log.LogWriter;
import com.wisdom.mapper.WechatValidMapper;
import com.wisdom.model.WechatValid;
import com.wisdom.service.WechatValidService;
import com.wisdom.api.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WechatValidServiceImpl implements WechatValidService {
    @Autowired
    WechatValidMapper mapper;
    @TargetDataSource("ds")
    public Resp save(WechatValid wechatValid) {

        try {
            if (wechatValid.getId() == null) {
                this.mapper.insertSelective(wechatValid);
            } else {
                this.mapper.updateByPrimaryKeySelective(wechatValid);
            }
        }catch (Exception e){
            LogWriter.error(e,"保存失败");
            return Resp.error("-1","保存失败");
        }
        return Resp.success("data",wechatValid);
    }

    @TargetDataSource("ds")
    public Resp queryByObject(Long id) {

        WechatValid wechatValid=new WechatValid();
        wechatValid.setId(id);
        return Resp.success("data",this.mapper.selectOne(wechatValid));
    }

    @TargetDataSource("ds")
    public Resp queryByList(WechatValid wechatValid) {

        return Resp.success("data",this.mapper.select(wechatValid));
    }
}

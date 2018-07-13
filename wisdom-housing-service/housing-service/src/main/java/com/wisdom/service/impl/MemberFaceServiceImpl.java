package com.wisdom.service.impl;

import com.wisdom.framework.database.conf.TargetDataSource;
import com.wisdom.log.LogWriter;
import com.wisdom.mapper.MemberFaceMapper;
import com.wisdom.model.MemberFace;
import com.wisdom.service.MemberFaceService;
import com.wisdom.api.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberFaceServiceImpl implements MemberFaceService {
    @Autowired
    MemberFaceMapper mapper;
    @TargetDataSource("ds")
    public Resp save(MemberFace memberFace) {
        try {
            if (memberFace.getId() == null) {
                this.mapper.insertSelective(memberFace);
            } else {
                this.mapper.updateByPrimaryKeySelective(memberFace);
            }
        }catch (Exception e){
            LogWriter.error(e,"保存失败");
            return Resp.error("-1","保存失败");
        }
        return Resp.success("data",memberFace);
    }

    @TargetDataSource("ds")
    public Resp queryByObject(Long id) {
        MemberFace memberFace=new MemberFace();
        memberFace.setId(id);
        return Resp.success("data",this.mapper.selectOne(memberFace));
    }

    @TargetDataSource("ds")
    public Resp queryByList(MemberFace memberFace) {
        return Resp.success("data",this.mapper.select(memberFace));
    }
}

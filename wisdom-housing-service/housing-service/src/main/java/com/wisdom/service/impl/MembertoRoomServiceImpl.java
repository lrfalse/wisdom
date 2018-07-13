package com.wisdom.service.impl;

import com.wisdom.framework.database.conf.TargetDataSource;
import com.wisdom.log.LogWriter;
import com.wisdom.mapper.MembertoRoomMapper;
import com.wisdom.model.MembertoRoom;
import com.wisdom.service.MembertoRoomService;
import com.wisdom.api.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembertoRoomServiceImpl implements MembertoRoomService {
    @Autowired
    MembertoRoomMapper mapper;
    @TargetDataSource("ds")
    public Resp save(MembertoRoom membertoRoom) {

        try {
            if (membertoRoom.getId() == null) {
                this.mapper.insertSelective(membertoRoom);
            } else {
                this.mapper.updateByPrimaryKeySelective(membertoRoom);
            }
        }catch (Exception e){
            LogWriter.error(e,"保存失败");
            return Resp.error("-1","保存失败");
        }
        return Resp.success("data",membertoRoom);
    }

    @TargetDataSource("ds")
    public Resp queryByObject(Long id) {

        MembertoRoom membertoRoom=new MembertoRoom();
        membertoRoom.setId(id);
        return Resp.success("data",this.mapper.selectOne(membertoRoom));
    }

    @TargetDataSource("ds")
    public Resp queryByList(MembertoRoom membertoRoom) {

        return Resp.success("data",this.mapper.select(membertoRoom));
    }
}

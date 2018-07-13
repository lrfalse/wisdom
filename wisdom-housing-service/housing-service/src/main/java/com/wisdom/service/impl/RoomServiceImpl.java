package com.wisdom.service.impl;

import com.wisdom.framework.database.conf.TargetDataSource;
import com.wisdom.log.LogWriter;
import com.wisdom.mapper.RoomMapper;
import com.wisdom.model.Room;
import com.wisdom.service.RoomService;
import com.wisdom.api.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomMapper mapper;
    @TargetDataSource("ds")
    public Resp save(Room room) {
        try {
            if (room.getId() == null) {
                this.mapper.insertSelective(room);
            } else {
                this.mapper.updateByPrimaryKeySelective(room);
            }
        }catch (Exception e){
            LogWriter.error(e,"保存失败");
            return Resp.error("-1","保存失败");
        }
        return Resp.success("data",room);
    }

    @TargetDataSource("ds")
    public Resp queryByObject(Long id) {
        Room room=new Room();
        room.setId(id);
        return Resp.success("data",this.mapper.selectOne(room));
    }

    @TargetDataSource("ds")
    public Resp queryByList(Room room) {

        return Resp.success("data",this.mapper.select(room));
    }
}

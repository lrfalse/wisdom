package com.wisdom.mapper;

import com.wisdom.framework.database.MapperBase;
import com.wisdom.model.MembertoRoom;

import java.util.Map;

public interface MembertoRoomMapper extends MapperBase<MembertoRoom> {

    public MembertoRoom selectMemberToRoom(MembertoRoom membertoRoom);

}
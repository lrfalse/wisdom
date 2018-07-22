package com.wisdom.service;

import com.wisdom.model.MembertoRoom;
import com.wisdom.api.Resp;

import java.util.Map;

public interface MembertoRoomService {
     Resp save(MembertoRoom membertoRoom);
     Resp queryByObject(Long id);
     Resp queryByList(MembertoRoom membertoRoom);
     MembertoRoom queryByMemberToRoom(MembertoRoom membertoRoom);
}

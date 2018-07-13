package com.wisdom.service;

import com.wisdom.model.MembertoRoom;
import com.wisdom.api.Resp;

public interface MembertoRoomService {
     Resp save(MembertoRoom membertoRoom);
     Resp queryByObject(Long id);
     Resp queryByList(MembertoRoom membertoRoom);
}

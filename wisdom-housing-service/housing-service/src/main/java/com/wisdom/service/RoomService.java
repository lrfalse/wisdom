package com.wisdom.service;

import com.wisdom.model.Room;
import com.wisdom.api.Resp;

public interface RoomService {
     Resp save(Room room);
     Resp queryByObject(Long id);
     Resp queryByList(Room room);
}

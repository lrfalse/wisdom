package com.wisdom.service;

import com.wisdom.api.Resp;
import com.wisdom.model.Building;

public interface BuildingService {
     Resp save(Building building);
     Resp queryByObject(Long id);
     Resp queryByList(Building building);
}

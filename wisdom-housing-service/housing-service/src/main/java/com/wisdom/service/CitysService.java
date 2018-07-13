package com.wisdom.service;

import com.wisdom.model.Citys;
import com.wisdom.api.Resp;

public interface CitysService {
     Resp save(Citys citys);
     Resp queryByObject(Long id);
     Resp queryByList(Citys citys);
}

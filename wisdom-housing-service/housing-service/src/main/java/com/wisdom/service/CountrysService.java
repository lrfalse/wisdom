package com.wisdom.service;

import com.wisdom.model.Countrys;
import com.wisdom.api.Resp;

public interface CountrysService {
     Resp save(Countrys countrys);
     Resp queryByObject(Long id);
     Resp queryByList(Countrys countrys);
}

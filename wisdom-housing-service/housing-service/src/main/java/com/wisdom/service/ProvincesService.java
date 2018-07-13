package com.wisdom.service;

import com.wisdom.model.Provinces;
import com.wisdom.api.Resp;

public interface ProvincesService {
     Resp save(Provinces provinces);
     Resp queryByObject(Long id);
     Resp queryByList(Provinces provinces);
}

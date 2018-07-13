package com.wisdom.service;

import com.wisdom.api.Resp;
import com.wisdom.model.Areas;


public interface AreasService {
     Resp save(Areas areas);
     Resp queryByObject(Long id);
     Resp queryByList(Areas areas);
}

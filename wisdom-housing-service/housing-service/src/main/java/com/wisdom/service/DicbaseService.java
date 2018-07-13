package com.wisdom.service;

import com.wisdom.model.Dicbase;
import com.wisdom.api.Resp;

public interface DicbaseService {
     Resp save(Dicbase dicbase);
     Resp queryByObject(Long id);
     Resp queryByList(Dicbase dicbase);
}

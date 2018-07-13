package com.wisdom.service;

import com.wisdom.model.HousingDervice;
import com.wisdom.api.Resp;

public interface HousingDerviceService {
     Resp save(HousingDervice housingDervice);
     Resp queryByObject(Long id);
     Resp queryByList(HousingDervice housingDervice);
}

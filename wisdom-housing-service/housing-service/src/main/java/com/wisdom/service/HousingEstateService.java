package com.wisdom.service;

import com.wisdom.model.HousingEstate;
import com.wisdom.api.Resp;

public interface HousingEstateService {
     Resp save(HousingEstate housingEstate);
     Resp queryByObject(Long id);
     Resp queryByList(HousingEstate housingEstate);
}

package com.wisdom.service;

import com.wisdom.model.MemberFace;
import com.wisdom.api.Resp;

public interface MemberFaceService {
     Resp save(MemberFace memberFace);
     Resp queryByObject(Long id);
     Resp queryByList(MemberFace memberFace);
}

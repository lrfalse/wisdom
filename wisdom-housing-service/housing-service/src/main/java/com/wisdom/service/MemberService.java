package com.wisdom.service;

import com.wisdom.model.Member;
import com.wisdom.api.Resp;

public interface MemberService {
     Resp save(Member member);
     Resp queryByObject(Long id);
     Resp queryByList(Member member);
     Resp updateByPhone(Member member);
}

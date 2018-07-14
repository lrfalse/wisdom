package com.wisdom.service;

import com.wisdom.model.Member;
import com.wisdom.api.Resp;

import java.util.List;
import java.util.Map;

public interface MemberService {
     Resp save(Member member);
     Resp queryByObject(Long id);
     Resp queryByList(Member member);
     Resp updateByPhone(Member member);
     Resp queryByPerfectIdentity(Member member);
     Resp queryByFaceRecognition(Member member);
     Resp queryByHousingRoom(Map map);
     Resp queryByRoomMember(Map map);
}

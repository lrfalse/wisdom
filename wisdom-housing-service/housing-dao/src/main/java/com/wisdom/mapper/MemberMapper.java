package com.wisdom.mapper;

import com.wisdom.framework.database.MapperBase;
import com.wisdom.model.Member;

import java.util.List;
import java.util.Map;

public interface MemberMapper extends MapperBase<Member> {

    int updateByPhone(Member member);
    List<Member> selectByPerfectIdentity(Member member);
    List<Member> selectByFaceRecognition(Member member);
    List<Map> selectByHousingRoom(Map map);
    List<Map> selectByRoomMember(Map map);
    String selectByInvitationCode(Long roomId);
    int updateByFaceImg(Member member);
    List<Map> selectByMemberUserIdInfo(Member member);
}
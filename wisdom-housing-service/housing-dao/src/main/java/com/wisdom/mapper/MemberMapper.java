package com.wisdom.mapper;

import com.wisdom.framework.database.MapperBase;
import com.wisdom.model.Member;

import java.util.List;

public interface MemberMapper extends MapperBase<Member> {

    public int updateByPhone(Member member);
    List<Member> selectByPerfectIdentity(Member member);
    List<Member> selectByFaceRecognition(Member member);

}
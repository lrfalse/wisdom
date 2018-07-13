package com.wisdom.mapper;

import com.wisdom.framework.database.MapperBase;
import com.wisdom.model.Member;

public interface MemberMapper extends MapperBase<Member> {

    public int updateByPhone(Member member);

}
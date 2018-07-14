package com.wisdom.service.impl;

import com.wisdom.framework.database.conf.TargetDataSource;
import com.wisdom.log.LogWriter;
import com.wisdom.mapper.MemberMapper;
import com.wisdom.model.Member;
import com.wisdom.service.MemberService;
import com.wisdom.api.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberMapper mapper;
    @TargetDataSource("ds")
    public Resp save(Member member) {
        try {
            if (member.getId() == null) {
                this.mapper.insertSelective(member);
            } else {
                this.mapper.updateByPrimaryKeySelective(member);
            }
        }catch (Exception e){
            LogWriter.error(e,"保存失败");
            return Resp.error("-1","保存失败");
        }
        return Resp.success("data",member);
    }

    @TargetDataSource("ds")
    public Resp queryByObject(Long id) {
        Member member=new Member();
        member.setId(id);
        return Resp.success("data",this.mapper.selectOne(member));
    }

    @TargetDataSource("ds")
    public Resp queryByList(Member member) {
        return Resp.success("data",this.mapper.select(member));
    }

    @TargetDataSource("ds")
    public Resp updateByPhone(Member member) {
        int flag=this.mapper.updateByPhone(member);
        try {
            if (flag > 0) {
                return Resp.success("更新成功");
            } else {
                return Resp.error("-1", "更新成功");
            }
        }catch(Exception ex){
            LogWriter.error(ex,"更新成功");
            return Resp.error("-1", "更新成功");
        }
    }
    @TargetDataSource("ds")
    public Resp queryByPerfectIdentity(Member member) {
        boolean flag=false;
        List<Member> list = this.mapper.selectByPerfectIdentity(member);
        if (list.size() > 0) {
            flag = true;
        }
        return Resp.success("flag",flag);
    }
    @TargetDataSource("ds")
    public Resp queryByFaceRecognition(Member member) {
        boolean flag=false;
        List<Member> list=this.mapper.selectByFaceRecognition(member);
        if(list.size()>0){
            flag=true;
        }
        return Resp.success("flag",flag);
    }
}

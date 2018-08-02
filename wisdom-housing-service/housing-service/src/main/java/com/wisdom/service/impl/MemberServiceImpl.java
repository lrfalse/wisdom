package com.wisdom.service.impl;

import com.wisdom.framework.database.conf.TargetDataSource;
import com.wisdom.log.LogWriter;
import com.wisdom.mapper.MemberMapper;
import com.wisdom.mapper.MembertoRoomMapper;
import com.wisdom.model.Member;
import com.wisdom.model.MembertoRoom;
import com.wisdom.service.MemberService;
import com.wisdom.api.Resp;
import com.wisdom.service.MembertoRoomService;
import com.wisdom.utils.ShareCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberMapper mapper;

    @Autowired
    MembertoRoomService membertoRoomService;
    @TargetDataSource("ds")
    public Resp save(Member member) {
        try {
            if (member.getId() == null) {
                if(member.getRelationship()==1) {
                    String shearCode = member.getPhone().substring(7, 11);
                    member.setInvitationCode(String.valueOf(ShareCodeUtil.codeToId(shearCode)));
                    member.setUseNumber(0);
                }
                this.mapper.insertSelective(member);
                MembertoRoom membertoRoom=new MembertoRoom();
                membertoRoom.setRoomId(member.getRoomId());
                membertoRoom.setMemberId(member.getId());
                this.membertoRoomService.save(membertoRoom);
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
    public Resp updateByFaceImg(Member member){
        int flag=this.mapper.updateByFaceImg(member);
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

    @TargetDataSource("ds")
    public Resp queryByHousingRoom(Map map) {
        return Resp.success("data",this.mapper.selectByHousingRoom(map));
    }

    @TargetDataSource("ds")
    public Resp queryByRoomMember(Map map) {
        return Resp.success("data",this.mapper.selectByRoomMember(map));
    }

    @TargetDataSource("ds")
    public String queryByInvitationCode(Long roomId){

        return this.mapper.selectByInvitationCode(roomId);
    }

    @TargetDataSource("ds")
    public List<Map> queryByMemberUserIdInfo(Member member) {
        return this.mapper.selectByMemberUserIdInfo(member);
    }
}

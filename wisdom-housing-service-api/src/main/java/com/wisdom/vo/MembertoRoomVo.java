package com.wisdom.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MembertoRoomVo {
    private Long id;
    private Long roomId;
    private Long memberId;
}
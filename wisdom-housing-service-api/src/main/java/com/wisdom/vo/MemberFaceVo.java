package com.wisdom.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class MemberFaceVo {
    private Long id;
    private Long housingEstateId;
    private String housingEstateName;
    private Long memberId;
    private String name;
    private String userId;
    private Integer type;
    private String imgUrl;
    private String faceId;
    private String faceNo;
    private String creater;
    private Date createDate;
    private String updater;
    private Date updateDate;
}
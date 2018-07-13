package com.wisdom.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class RoomVo {
    private Long id;
    private Long housingEstateId;
    private String housingEstateName;
    private Long buildingId;
    private String buildName;
    private String name;
    private Integer isValid;
    private String creater;
    private Date createDate;
    private String updater;
    private Date updateDate;
}
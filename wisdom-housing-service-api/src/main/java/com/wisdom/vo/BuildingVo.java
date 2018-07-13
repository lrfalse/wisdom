package com.wisdom.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class BuildingVo {
    private Long id;
    private Long housingEstateId;
    private String housingEstateName;
    private String name;
    private Integer isValid;
    private String creater;
    private Date createDate;
    private String updater;
    private Date updateDate;
}
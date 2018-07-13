package com.wisdom.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class HousingDerviceVo {
    private Long id;
    private Long housingEstateId;
    private String housingEstateName;
    private String derviceCode;
    private String derviceName;
    private String derviceId;
    private String passwords;
    private String doorId;
    private String userId;
    private String creater;
    private Date createDate;
    private String updater;
    private Date updateDate;
}
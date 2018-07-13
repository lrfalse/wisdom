package com.wisdom.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class HousingEstateVo {
    private Long id;
    private Long provincesId;
    private String provincesName;
    private Long cityId;
    private String cityName;
    private Long areasId;
    private String areasName;
    private String name;
    private String baiLongitude;
    private String baiLatitude;
    private String gorLongitude;
    private String gorLatitude;
    private String account;
    private String passwords;
    private String userId;
    private Integer isValid;
    private String creater;
    private Date createDate;
    private String updater;
    private Date updateDate;
}
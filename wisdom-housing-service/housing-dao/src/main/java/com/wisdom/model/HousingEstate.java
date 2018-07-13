package com.wisdom.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import javax.persistence.*;
@Getter
@Setter
@ToString
@Table(name="housingestate")
public class HousingEstate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="provincesId")
    private Long provincesId;
    @Column(name="provincesName")
    private String provincesName;
    @Column(name="cityId")
    private Long cityId;
    @Column(name="cityName")
    private String cityName;
    @Column(name="areasId")
    private Long areasId;
    @Column(name="areasName")
    private String areasName;
    @Column(name="name")
    private String name;
    @Column(name="baiLongitude")
    private String baiLongitude;
    @Column(name="baiLatitude")
    private String baiLatitude;
    @Column(name="gorLongitude")
    private String gorLongitude;
    @Column(name="gorLatitude")
    private String gorLatitude;
    @Column(name="account")
    private String account;
    @Column(name="passwords")
    private String passwords;
    @Column(name="userId")
    private String userId;
    @Column(name="isValid")
    private Integer isValid;
    @Column(name="creater")
    private String creater;
    @Column(name="createDate")
    private Date createDate;
    @Column(name="updater")
    private String updater;
    @Column(name="updateDate")
    private Date updateDate;

}
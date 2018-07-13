package com.wisdom.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import javax.persistence.*;
@Getter
@Setter
@ToString
@Table(name="housingdervice")
public class HousingDervice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="housingEstateId")
    private Long housingEstateId;
    @Column(name="housingEstateName")
    private String housingEstateName;
    @Column(name="derviceCode")
    private String derviceCode;
    @Column(name="derviceName")
    private String derviceName;
    @Column(name="derviceId")
    private String derviceId;
    @Column(name="passwords")
    private String passwords;
    @Column(name="doorId")
    private String doorId;
    @Column(name="userId")
    private String userId;
    @Column(name="creater")
    private String creater;
    @Column(name="createDate")
    private Date createDate;
    @Column(name="updater")
    private String updater;
    @Column(name="updateDate")
    private Date updateDate;

}
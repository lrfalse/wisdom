package com.wisdom.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import javax.persistence.*;
@Getter
@Setter
@ToString
@Table(name="room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="housingEstateId")
    private Long housingEstateId;
    @Column(name="housingEstateName")
    private String housingEstateName;
    @Column(name="buildingId")
    private Long buildingId;
    @Column(name="buildName")
    private String buildName;
    @Column(name="name")
    private String name;
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
package com.wisdom.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import javax.persistence.*;
@Getter
@Setter
@ToString
@Table(name="memberface")
public class MemberFace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="housingEstateId")
    private Long housingEstateId;
    @Column(name="housingEstateName")
    private String housingEstateName;
    @Column(name="memberId")
    private Long memberId;
    @Column(name="name")
    private String name;
    @Column(name="userId")
    private String userId;
    @Column(name="type")
    private Integer type;
    @Column(name="imgUrl")
    private String imgUrl;
    @Column(name="faceId")
    private String faceId;
    @Column(name="faceNo")
    private String faceNo;
    @Column(name="creater")
    private String creater;
    @Column(name="createDate")
    private Date createDate;
    @Column(name="updater")
    private String updater;
    @Column(name="updateDate")
    private Date updateDate;

}
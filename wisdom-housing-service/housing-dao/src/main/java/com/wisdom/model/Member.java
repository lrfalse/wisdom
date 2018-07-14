package com.wisdom.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import javax.persistence.*;
@Getter
@Setter
@ToString
@Table(name="member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="openId")
    private String openId;
    @Column(name="name")
    private String name;
    @Column(name="phone")
    private String phone;
    @Column(name="sex")
    private Integer sex;
    @Column(name="relationship")
    private Integer relationship;
    @Column(name="mid")
    private Long mid;
    @Column(name="mbName")
    private String mbName;
    @Column(name="isPerfectIdentity")
    private int isPerfectIdentity;
    @Column(name="isFaceRecognition")
    private int isFaceRecognition;
    @Column(name="creater")
    private String creater;
    @Column(name="createDate")
    private Date createDate;
    @Column(name="updateDate")
    private String updater;
    @Column(name="updateDate")
    private Date updateDate;
    @Transient
    private Long roomId;

}
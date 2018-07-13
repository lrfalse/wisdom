package com.wisdom.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
@Getter
@Setter
@ToString
@Table(name="wechatvalid")
public class WechatValid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="openId")
    private String openId;
    @Column(name="isPerfectIdentity")
    private Integer isPerfectIdentity;
    @Column(name="isFaceRecognition")
    private Integer isFaceRecognition;
}
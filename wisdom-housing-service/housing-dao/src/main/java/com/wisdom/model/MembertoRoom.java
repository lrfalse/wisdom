package com.wisdom.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
@Getter
@Setter
@ToString
@Table(name="membertoroom")
public class MembertoRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="roomId")
    private Long roomId;
    @Column(name="memberId")
    private Long memberId;
    @Transient
    private String phone;

}
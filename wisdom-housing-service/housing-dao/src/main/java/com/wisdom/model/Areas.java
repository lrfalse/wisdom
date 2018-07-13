package com.wisdom.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
@Getter
@Setter
@ToString
@Table(name="areas")
public class Areas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="countrysId")
    private Long countrysId;
    @Column(name="provincesId")
    private Long provincesId;
    @Column(name="cityId")
    private Long cityId;
    @Column(name="code")
    private String code;
    @Column(name="name")
    private String name;
    @Column(name="isValid")
    private Integer isValid;
}
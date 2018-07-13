package com.wisdom.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import javax.persistence.*;
@Getter
@Setter
@ToString
@Table(name="dicbase")
public class Dicbase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="codeId")
    private String codeId;
    @Column(name="codeName")
    private String codeName;
    @Column(name="viewName")
    private String viewName;
    @Column(name="isVaild")
    private Integer isVaild;
    @Column(name="seq")
    private Integer seq;
    @Column(name="creater")
    private String creater;

    private Date createDate;

    private String updater;

    private Date updateDate;


}
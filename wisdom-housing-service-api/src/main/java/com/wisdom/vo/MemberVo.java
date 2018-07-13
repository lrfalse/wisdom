package com.wisdom.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class MemberVo {
    private Long id;
    private String openId;
    private String name;
    private String phone;
    private Integer sex;
    private Integer relationship;
    private Long mid;
    private String mbName;
    private String creater;
    private Date createDate;
    private String updater;
    private Date updateDate;
}
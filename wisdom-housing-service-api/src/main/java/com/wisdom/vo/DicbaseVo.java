package com.wisdom.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class DicbaseVo {
    private Long id;
    private String codeId;
    private String codeName;
    private String viewName;
    private Integer isVaild;
    private Integer seq;
    private String creater;
    private Date createDate;
    private String updater;
    private Date updateDate;
}
package com.wisdom.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CitysVo {
    private Long id;
    private Long countrysId;
    private Long provincesId;
    private String code;
    private String name;
    private Integer isValid;
}
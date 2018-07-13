package com.wisdom.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProvincesVo {
    private Long id;
    private Long countrysId;
    private String code;
    private String name;
    private Integer isValid;
}
package com.wisdom.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CountrysVo {
    private Long id;
    private String code;
    private String name;
    private Integer isValid;
}
package com.wisdom.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WechatValidVo {
    private Long id;
    private String openId;
    private Integer isPerfectIdentity;
    private Integer isFaceRecognition;
}
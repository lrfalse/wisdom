package com.wisdom.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description(功能描述) :短信
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/7/20 9:27
 **/
@Getter
@Setter
@ToString
public class SmsVo {
	private String phone;
	private String smsCode;
}

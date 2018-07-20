package com.wisdom.controller;

import com.aliyuncs.exceptions.ClientException;
import com.wisdom.api.ErrorCode;
import com.wisdom.api.Resp;
import com.wisdom.third.wx.common.util.RandomUtils;
import com.wisdom.utils.AliYunSms;
import com.wisdom.utils.RandomStringGenerator;
import com.wisdom.vo.SmsVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/sms")
public class SmsController {
	private static  final String CODE_SESSION_NAME="code" ;

	@RequestMapping(value = "/sendSms", method = RequestMethod.POST)
	public Resp sendSms(HttpServletRequest request,@RequestBody SmsVo smsVo) throws ClientException {
		String phone=smsVo.getPhone();
		String code= RandomStringGenerator.getRandomNumberByLength(4);
		AliYunSms.sendSms(phone, code);
		request.getSession().setAttribute(phone+CODE_SESSION_NAME, code);
		request.getSession().setMaxInactiveInterval(2*60);
		return Resp.success("发送成功");
	}

	@RequestMapping(value = "/authCode", method = RequestMethod.POST)
	public Resp authCode(HttpServletRequest request,@RequestBody SmsVo smsVo){
		String phone=smsVo.getPhone();
		String smsCode=smsVo.getSmsCode();
		String sendCode=(String)request.getSession().getAttribute(phone+CODE_SESSION_NAME);
		if(smsCode.equals(sendCode)){
			return Resp.success("短信验证成功");
		}else{
			return Resp.error(ErrorCode.ERROR_CODE);
		}
	}
}

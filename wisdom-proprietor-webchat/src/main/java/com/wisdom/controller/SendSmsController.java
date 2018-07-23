package com.wisdom.controller;

import com.alibaba.fastjson.JSONObject;
import com.wisdom.utils.AliYunSms;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auther: yanyunfeng
 * @date: 2018/7/22 18:12
 * @description:
 */
@Controller
@RequestMapping("/api")
public class SendSmsController {

    @RequestMapping(value="/send",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject send(String phone){
        JSONObject returnJson=new JSONObject();
        try {
            int numcode = (int) ((Math.random() * 9 + 1) * 100000);
            AliYunSms.sendSms(phone,String.valueOf(numcode));
            returnJson.put("code",String.valueOf(numcode));
            returnJson.put("flag",true);
        }catch(Exception ex){
            returnJson.put("code","");
            returnJson.put("flag",false);
        }
        return returnJson;
    }
}

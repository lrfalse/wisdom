package com.wisdom.rpc;

import com.alibaba.fastjson.JSONObject;
import com.wisdom.third.famvideo.FamVideoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RpcClientTest {
    @Autowired
    FamVideoClient famVideoClent;

    /**
      * @Description(功能描述): 获取token
      * @author(作者): lrfalse<wangliyou>
      * @date(开发日期): 2018/7/14 12:15
      **/
    @RequestMapping(value = "/getToken", method = RequestMethod.GET)
    public JSONObject get(){
		return famVideoClent.getToken("153026671031026", "yong_chuan");
	}
    
}

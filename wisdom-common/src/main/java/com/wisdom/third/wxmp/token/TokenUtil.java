package com.wisdom.third.wxmp.token;

import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wisdom.third.wxmp.oauth.util.HttpKit;

public class TokenUtil {

	private static final String TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token";
	private String appid;
    private String secret;

    public TokenUtil() {
        super();
    }

    public TokenUtil(String appid, String secret) {
        super();
        this.appid = appid;
        this.secret = secret;
    }
	public String getToken()throws Exception{
		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", getAppid());
        params.put("secret", getSecret());
        params.put("grant_type", "client_credential");
        String tokenStr=HttpKit.get(TOKEN_URL,params);
        JSONObject json=JSON.parseObject(tokenStr);
        return json.getString("access_token");
	} 
	public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}

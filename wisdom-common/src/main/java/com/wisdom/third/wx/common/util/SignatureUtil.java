package com.wisdom.third.wx.common.util;

import com.alibaba.fastjson.JSONObject;
import com.wisdom.third.wxmp.token.TokenUtil;
import com.wisdom.utils.HttpClientUtils;
import com.wisdom.utils.RandomStringGenerator;
import java.util.Date;

public class SignatureUtil {
    /**
     * 获取微信js-sdk参数
     */
    public static JSONObject getConfig(String url,String appId,String appSecret) throws Exception{
        //获取页面路径(前端获取时采用location.href.split('#')[0]获取url)
        //获取access_token
        TokenUtil tokenUtil=new TokenUtil(appId,appSecret);
        String access_token=tokenUtil.getToken();
        //获取ticket
        String jsapi_ticket = getTickect(access_token);
        //获取Unix时间戳(java时间戳为13位,所以要截取最后3位,保留前10位)
        Long timeStamp = new Date().getTime()/1000;
        //签名
        String noncestr=RandomStringGenerator.getRandomStringByLength(32);
        String signature = "";
        try {
            //签名
            signature = getSignature(noncestr,jsapi_ticket,url,timeStamp);
        }catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject json = new JSONObject();
        json.put("appId", appId);
        json.put("timestamp", timeStamp);
        json.put("nonceStr", noncestr);
        json.put("signature", signature);
        return json;
    }

    /**
     * 生成签名
     * @param nonceStr 随机字串
     * @param jsapi_ticket 票据
     * @param url
     * @param timestamp 时间戳
     * @return
     */
    private static String getSignature(String nonceStr,String jsapi_ticket,String url,Long timestamp){
        String template = "jsapi_ticket=%s&noncestr=%s&timestamp=%s&url=%s";
        String result = String.format(template, jsapi_ticket,nonceStr,timestamp,url);
        return org.apache.commons.codec.digest.DigestUtils.shaHex(result);
    }
    /**
     * 获取jsapi_ticket
     *
     */
    public static String getTickect(String access_token) {
        String ticket = "";
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", access_token);
        // 获取网页授权凭证
        HttpClientUtils httpClientUtils=new HttpClientUtils();
        String responseString = httpClientUtils.httpGet(requestUrl);
        JSONObject jsonObject= JSONObject.parseObject(responseString);
        if (null != jsonObject) {
            try {
                ticket = jsonObject.getString("ticket");
            } catch (Exception e) {
                int errorCode = jsonObject.getInteger("errcode");
                String errorMsg = jsonObject.getString("errmsg");
            }
        }
        return ticket;
    }
}

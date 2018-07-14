package com.wisdom.third.wxmp.msg;

import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson.JSON;

/**
 * 微信发送模板消息
 * @author yyf
 * @date   2016-06-01
 */
public class MsgTemplate {
	private static final String TEMPLATE_SEND="https://api.weixin.qq.com/cgi-bin/message/template/send";
	private static final long serialVersionUID = 3132269950618303986L;
    private String touser;
    private String template_id;
    private String url;
    private String topcolor;

    public static MsgTemplate sendNewMsg(String touser, String template_id, String url) {
        return new MsgTemplate(touser, template_id, url);
    }

    public static MsgTemplate sendNewMsg(String touser, String template_id, String url, String topcolor) {
        return new MsgTemplate(touser, template_id, url, topcolor);
    }

    private Map<String, TextField> data = new HashMap<String, TextField>();

    public MsgTemplate() {

    }

    private MsgTemplate(String touser, String template_id, String url) {
        this.touser = touser;
        this.template_id = template_id;
        this.url = url;
    }

    private MsgTemplate(String touser, String template_id, String url, String topcolor) {
        this.touser = touser;
        this.template_id = template_id;
        this.url = url;
        this.topcolor = topcolor;
    }

    public MsgTemplate addTextField(String dataName, String value) {
        return addTextField(dataName, value, null);
    }

    public MsgTemplate addTextField(String dataName, String value, String color) {
        data.put(dataName, new TextField(value, color));
        return this;
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTopcolor() {
        return topcolor;
    }

    public void setTopcolor(String topcolor) {
        this.topcolor = topcolor;
    }

    public Map<String, TextField> getData() {
        return data;
    }

    public void setData(Map<String, TextField> data) {
        this.data = data;
    }


    public String pushMsg(String token) {
    	String msg="";
    	try {
    		String content=JSON.toJSONString(this);
    		String url=TEMPLATE_SEND+"?access_token="+token;
    		//msg=HttpClientUtil.post(url, content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    	return msg;
    }


    public static void main(String args[]) {
    	try{
	    	//TokenUtil tokenUtil=new TokenUtil("wx4b0e118c4e623f4e","db44e57dda977c545c12e758edef6b90");
	    	//String token=tokenUtil.getToken();
	        String msg=MsgTemplate.sendNewMsg("oDnSzv2_rB46ROO29vOCgorweQJc", "x1qDzh96aAd5xNqzHgkRZmDODPrxmGajjuRIheJa-qg", "http://www.baidu.com")
	                .addTextField("first", "您好，您的微信支付已成功")
	                .addTextField("keyword1", "123456789901234567", "#000000")
	                .addTextField("keyword2", "28.88元", "#000000")
	                .addTextField("keyword3", "一元超市", "#000000")
	                .addTextField("keyword4", "2016年4月8日17:00:00", "#000000")
	                .addTextField("remark", "谢谢您的购买")
	                .pushMsg("tEGLR7CSErxeELdrsLTPDhxAWy5XK3gn42Ncn55P-CSszXp8udkd_m22_oYNHo5yLVIxWCYsbLnUHwABEpnEY04vN6bvBbXdN_hIbp8Eb0tn9zmaAvMBqy4GQJC5a2gSPTQhCFAEIF");
	        System.out.println(msg);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}


    }
	
	
}

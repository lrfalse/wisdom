package com.wisdom.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wisdom.api.MemberClient;
import com.wisdom.log.LogWriter;
import com.wisdom.third.wx.common.util.SignatureUtil;
import com.wisdom.third.wxmp.oauth.tencent.Oauth;
import com.wisdom.third.wxmp.token.TokenUtil;
import com.wisdom.utils.ProjectUtils;
import com.wisdom.vo.MemberVo;
import com.wisdom.vo.RedirectVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/api")
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    @Value("${url.redirect_url}")
    String redirect_url;
    @Value("${weixin.oauth2_url}")
    String weixin_oauth2_url;
    @Value("${weixin.AppID}")
    String appId;
    @Value("${weixin.AppSecret}")
    String appSecret;
    @Value("${app.url}")
    String app_url;

    @Autowired
    MemberClient memberClient;

    @RequestMapping(value="/auth",method = RequestMethod.GET)
    public void auth(HttpServletRequest request, HttpServletResponse response,RedirectVo vo){
        try {
            String redirect_uri=ProjectUtils.urlEnodeUTF8(redirect_url);
            String url=weixin_oauth2_url+"?appid="+appId+"&redirect_uri="+redirect_uri+"&response_type=code&scope=snsapi_base&state=128#wechat_redirect";
           // String url=redirect_url+"?openId=1111111111";
            response.sendRedirect(url);
        }catch(IOException ex){
            LogWriter.error(ex,"获取微信授权失败");
        }
    }
    @RequestMapping(value="/redirect",method = RequestMethod.GET)
    public void redirect(HttpServletRequest request,HttpServletResponse response,RedirectVo vo){
        try {
            String openId=getUserOpenId(request);
            MemberVo memberVo=new MemberVo();
            memberVo.setOpenId(openId);
            memberVo.setIsPerfectIdentity(1);
            memberVo.setIsFaceRecognition(1);
            JSONObject idJson= memberClient.isIdentity(memberVo);
            JSONObject faceJson=memberClient.isFace(memberVo);
            boolean idFlag=idJson.getJSONObject("data").getBoolean("flag");
            boolean faceFlag=faceJson.getJSONObject("data").getBoolean("flag");
            if(!idFlag){
                response.sendRedirect(app_url+"/member/notface.html?oid="+openId);
            }else{
                if(!faceFlag){
                    response.sendRedirect(app_url+"/member/uploadface.html?oid="+openId);
                }else{
                    response.sendRedirect(app_url+"/member/brushedface.html?oid="+openId);
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
            LogWriter.error(ex,"获取微信授权失败");
        }
    }
    /**
     * 获取OpenId
     * @param request
     * @return
     * @throws Exception
     */
    private String getUserOpenId(HttpServletRequest request) throws Exception {
        String code =null;
        code= request.getParameter("code");
        String openId="";
        if (code == null) {
            openId = request.getParameter("openId");
            return openId;
        }
        Oauth o = new Oauth(appId,appSecret);
        String token = o.getToken(code);
        JSONObject node=JSON.parseObject(token);
        openId = node.getString("openid");
        return openId;
    }
    @RequestMapping(value="/we_chat_config",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getWeChatConfig(String url)throws Exception{
        JSONObject config= SignatureUtil.getConfig(url,appId,appSecret);
        return config;
    }
}

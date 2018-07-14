package com.wisdom.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wisdom.api.MemberClient;
import com.wisdom.log.LogWriter;
import com.wisdom.third.wxmp.oauth.tencent.Oauth;
import com.wisdom.vo.MemberVo;
import com.wisdom.vo.RedirectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/api")
public class AuthController {
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
            response.sendRedirect(redirect_url+"?openId=000001");
        }catch(IOException ex){
            LogWriter.error(ex,"获取微信授权失败");
        }
    }
    @RequestMapping(value="/redirect",method = RequestMethod.GET)
    public void redirect(HttpServletRequest request,HttpServletResponse response,RedirectVo vo){
        try {
            String openId=request.getParameter("openId");
            MemberVo memberVo=new MemberVo();
            memberVo.setOpenId(openId);
            memberVo.setIsPerfectIdentity(1);
            memberVo.setIsFaceRecognition(1);
            JSONObject idJson= memberClient.isIdentity(memberVo);
            JSONObject faceJson=memberClient.isFace(memberVo);
            boolean idFlag=idJson.getJSONObject("data").getBoolean("flag");
            boolean faceFlag=faceJson.getJSONObject("data").getBoolean("flag");
            if(!idFlag){
                response.sendRedirect(app_url+"/member/notface.html");
            }else{
                if(!faceFlag){
                    response.sendRedirect(app_url+"/member/uploadface.html");
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
     * @param pid
     * @return
     * @throws Exception
     */
    private String getUserOpenId(HttpServletRequest request,String pid) throws Exception {
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
}

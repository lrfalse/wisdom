package com.wisdom.controller;
import com.alibaba.fastjson.JSONObject;
import com.wisdom.api.MemberClient;
import com.wisdom.log.LogWriter;
import com.wisdom.third.wx.common.exception.WxErrorException;
import com.wisdom.third.wx.cp.api.WxCpConfigStorage;
import com.wisdom.third.wx.cp.api.WxCpInMemoryConfigStorage;
import com.wisdom.third.wx.cp.api.WxCpService;
import com.wisdom.third.wx.cp.api.WxCpServiceImpl;
import com.wisdom.third.wx.mp.api.WxMpConfigStorage;
import com.wisdom.third.wx.mp.api.WxMpInMemoryConfigStorage;
import com.wisdom.third.wx.mp.api.WxMpService;
import com.wisdom.third.wx.mp.api.WxMpServiceImpl;
import com.wisdom.utils.ProjectUtils;
import com.wisdom.vo.MemberVo;
import lombok.experimental.var;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping("/face")
public class FaceUploadController {
    @Value("${weixin.AppID}")
    String appId;
    @Value("${weixin.AppSecret}")
    String appSecret;
    @Value("${upload.face_path}")
    String upload_face_path;
    @Value("${upload.face_url}")
    String upload_face_url;
    @Autowired
    MemberClient memberClient;
    @RequestMapping(value="/upload", method=RequestMethod.POST)
    @ResponseBody
    public Object getPhoto(String media_id,String openId) {
        WxMpService wxMpService=new WxMpServiceImpl();
        JSONObject returnJson=new JSONObject();
        try {
            WxMpConfigStorage config=new WxMpInMemoryConfigStorage();
            ((WxMpInMemoryConfigStorage) config).setAppId(appId);
            ((WxMpInMemoryConfigStorage) config).setSecret(appSecret);
            wxMpService.setWxMpConfigStorage(config);
            File fileIn = wxMpService.mediaDownload(media_id);
            File fileOut = new File(upload_face_path+File.separator+openId+".jpg");
            ProjectUtils.copy(fileIn,fileOut);
            MemberVo memberVo=new MemberVo();
            memberVo.setOpenId(openId);
            memberVo.setImgUrl(upload_face_url+openId+".jpg");
            memberVo.setIsFaceRecognition(1);
            memberClient.uploadFaceImg(memberVo);
            returnJson.put("flag",true);
        }catch(Exception ex){
            returnJson.put("flag",false);
        }
        return returnJson;
    }
}

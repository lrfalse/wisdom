//package com.wisdom.controller;
//
//import com.alibaba.fastjson.JSON;
//import org.apache.commons.io.FileUtils;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.net.ssl.HttpsURLConnection;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//@Controller
//@RequestMapping("/face")
//public class FaceUploadController {
//
//    @RequestMapping(value="/upload", method=RequestMethod.POST)
//    @ResponseBody
//    public Object getPhoto(String media_id) throws NoSuchAlgorithmException{
//        //http请求方式: GET,https调用
////        var url = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
//        AccessToken token = AccessTokenJsapiTicketThread.accessToken;
//        String url = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=" + token.getAccess_token() + "&media_id=" + media_id;
//        HttpsURLConnection httpsUrl = null;
//        InputStream inputStream = null;
//        Date now = new Date();
//        String saveFileName = null;
//        try {
//            httpsUrl = HttpUtil.initHttpsConnection(url, "GET");
//            int responseCode = httpsUrl.getResponseCode();
//            if (responseCode == 200) {
//                // 从服务器返回一个输入流
//                inputStream = httpsUrl.getInputStream();
//                byte[] data = new byte[1024];
//                int len = 0;
//                FileOutputStream fileOutputStream = null;
//                saveFileName = DateUtil.convertYMDH(now) + RandomStringUtils.random(6, true, true) + ".jpg";;
//                // 绝对路径
//                String path = imageRootPath + DateUtil.convertYMD(now) + "/" + saveFileName;
//                File dir = new File(imageRootPath + DateUtil.convertYMD(now) + "/");
//                if (!dir.exists()) {
//                    FileUtils.forceMkdir(dir);
//                }
//                try {
//                    fileOutputStream = new FileOutputStream(path);
//                    while ((len = inputStream.read(data)) != -1) {
//                        fileOutputStream.write(data, 0, len);
//                    }
//                    fileOutputStream.flush();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } finally {
//                    if (inputStream != null) {
//                        try {
//                            inputStream.close();
//                        } catch (IOException e) {
//                        }
//                    }
//                    if (fileOutputStream != null) {
//                        try {
//                            fileOutputStream.close();
//                        } catch (IOException e) {
//                        }
//                    }
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        // 返回图片路径
//        return JsonConvertor.convertSuccessResult(DateUtil.convertYMD(now) + "/" + saveFileName);
//    }
//}

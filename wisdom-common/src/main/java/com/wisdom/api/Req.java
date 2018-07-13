 package com.wisdom.api;
 
 import java.io.Serializable;
 import java.util.Map;
 
 public class Req
   implements Serializable
 {
   private String appKey;
   private String accessToken;
   private String sign;
   private String timestamp;
   private String version;
   private String cityCode;
   private String userCode;
   private Map<String, Object> data;
 
   public String getAppKey()
   {
    return this.appKey;
   }
 
   public String getAccessToken() {
     return this.accessToken;
   }
 
   public String getSign() {
     return this.sign;
   }
 
   public String getTimestamp() {
     return this.timestamp;
   }
 
   public String getVersion() {
     return this.version;
   }
 
   public String getCityCode() {
     return this.cityCode;
   }
 
   public String getUserCode() {
     return this.userCode;
   }
 
   public Map<String, Object> getData()
   {
     return this.data;
   }
 
   public void setAppKey(String appKey)
   {
     this.appKey = appKey; } 
   public void setAccessToken(String accessToken) { this.accessToken = accessToken; } 
  public void setSign(String sign) { this.sign = sign; } 
   public void setTimestamp(String timestamp) { this.timestamp = timestamp; } 
   public void setVersion(String version) { this.version = version; } 
   public void setCityCode(String cityCode) { this.cityCode = cityCode; } 
   public void setUserCode(String userCode) { this.userCode = userCode; } 
   public void setData(Map<String, Object> data) { this.data = data; } 
   public String toString() { return "Req(appKey=" + getAppKey() + ", accessToken=" + getAccessToken() + ", sign=" + getSign() + ", timestamp=" + getTimestamp() + ", version=" + getVersion() + ", cityCode=" + getCityCode() + ", userCode=" + getUserCode() + ", data=" + getData() + ")";
   }
 }
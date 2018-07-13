 package com.wisdom.api;
 
 import com.google.common.collect.Maps;
 import java.io.Serializable;
 import java.util.Map;
 
 public class Resp
   implements Serializable
 {
   public static final String CODE_SUCCESS = "0";
   public static final String MESSAGE_SUCCESS = "success";
   private String code;
   private String msg;
   private String message;
   private String status;
   private String err_msg;
   private Map<String, Object> data;
 
   private Resp(String code, String msg, Map<String, Object> data)
   {
     this.code = code;
     this.msg = msg;
     this.message = msg;
     this.data = data;
     this.status = code;
     this.err_msg = msg;
   }
 
   public Resp(Map<String, Object> map) {
     this.code = (map.get("code") == null ? String.valueOf(map.get("status")) : String.valueOf(map.get("code")));
     this.msg = (map.get("msg") == null ? String.valueOf(map.get("err_msg")) : String.valueOf(map.get("msg")));
     this.message = (map.get("msg") == null ? String.valueOf(map.get("err_msg")) : String.valueOf(map.get("msg")));
     this.data = ((Map)map.get("data"));
     this.status = (map.get("code") == null ? String.valueOf(map.get("status")) : String.valueOf(map.get("code")));
    this.err_msg = (map.get("msg") == null ? String.valueOf(map.get("err_msg")) : String.valueOf(map.get("msg")));
   }
 
   public static Resp success(String key, Object value) {
     Map data = Maps.newHashMap();
     data.put(key, value);
     return new Resp("0", "success", data);
   }
 
   public static Resp success(String msg) {
     return new Resp("0", msg, null);
   }
 
   public static Resp success(Map<String, Object> data)
   {
     return new Resp("0", "success", data);
   }
 
   public static Resp success() {
     return new Resp("0", "success", null);
   }
 
   public static Resp error(ErrorCode errorCode, String[] message) {
     return new Resp(errorCode.getCode(), String.format(errorCode.getMessage(), message), null);
   }
 
   public static Resp error(String code, String message) {
     return new Resp(code, message, null);
   }
 
   public static Resp error(ErrorCode errorCode, Map<String, Object> data) {
     return new Resp(errorCode.getCode(), errorCode.getMessage(), data);
   }
 
   public static Resp error(ErrorCode code) {
     return new Resp(code.getCode(), code.getMessage(), null);
   }
 
   public boolean respIsSuccess() {
     return "0".equals(this.code);
   }
 
   public Resp append(String key, Object value) {
     if (this.data == null) {
      this.data = Maps.newHashMap();
     }
     this.data.put(key, value);
     return this;
   }
 
   public String toString()
   {
     return "Resp(code=" + getCode() + ", msg=" + getMsg() + ", message=" + getMessage() + ", status=" + getStatus() + ", err_msg=" + getErr_msg() + ", data=" + getData() + ")";
   }
 
   public String getCode()
   {
     return this.code;
   }
 
   public String getMsg()
   {
     return this.msg;
   }
   public String getMessage() {
    return this.message;
   }
 
   public String getStatus()
   {
     return this.status;
   }
 
   public String getErr_msg()
   {
     return this.err_msg;
   }
 
   public Map<String, Object> getData()
   {
     return this.data;
   }
 }
 package com.wisdom.api;
 
 import java.io.Serializable;
 
 public class ErrorCode
   implements Serializable
 {
 public static final ErrorCode PARAM_REQUIRED = new ErrorCode("40001", "必填参数【%s】为空");
  public static final ErrorCode PARAM_INVALID = new ErrorCode("40006", "参数格式无效【%s】");
   public static final ErrorCode SIGN = new ErrorCode("40002", "签名验证失败");
   public static final ErrorCode AUTHORITY = new ErrorCode("40003", "无权限做此操作");
   public static final ErrorCode DECRYPT = new ErrorCode("40004", "解密失败");
   public static final ErrorCode INVALID_CALLER = new ErrorCode("40005", "非法调用方");
   public static final ErrorCode UPLOAD_TYPE = new ErrorCode("40007", "文件类型只允许上传【%s】");
   public static final ErrorCode UPLOAD_SIZE = new ErrorCode("40008", "文件大小不可超过【%s】M");

   public static final ErrorCode ERROR_CODE = new ErrorCode("40009", "对不起，短信过期或者验证失败，请重新获取短信验证");

   public static final ErrorCode SERVER = new ErrorCode("50000", "服务器异常【%s】");
   private String code;
   private String message;
 
   public ErrorCode(String code, String message)
   {
    this.code = code;
     this.message = message;
   }
 
   public String getCode()
   {
     return this.code;
   }
   public String getMessage() { return this.message;
   }
 }
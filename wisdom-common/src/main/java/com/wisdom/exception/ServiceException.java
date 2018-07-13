 package com.wisdom.exception;


 import com.wisdom.api.ErrorCode;

 public class ServiceException extends RuntimeException
 {
   private final String code;
 
   public ServiceException(ErrorCode errorCode)
   {
     super(errorCode.getMessage());
     this.code = errorCode.getCode();
   }
 
   public ServiceException(ErrorCode errorCode, Throwable cause) {
     super(errorCode.getMessage(), cause);
     this.code = errorCode.getCode();
   }
 
   public ServiceException(ErrorCode errorCode, String appendMessage)
   {
     super(String.format(errorCode.getMessage(), new Object[] { appendMessage == null ? "" : appendMessage }));
     this.code = errorCode.getCode();
   }
 
   public ServiceException(ErrorCode errorCode, Throwable cause, String appendMessage) {
     super(String.format(errorCode.getMessage(), new Object[] { appendMessage == null ? "" : appendMessage }), cause);
     this.code = errorCode.getCode();
   }
 
   public String getCode() {
     return this.code;
   }
 
   public String toString()
   {
    StringBuilder sb = new StringBuilder(getClass().getName());
     sb.append("{");
     sb.append("code=").append(getCode());
     sb.append(",");
     sb.append("message=").append(getLocalizedMessage());
     sb.append('}');
     return sb.toString();
   }
 }
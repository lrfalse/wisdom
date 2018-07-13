 package com.wisdom.security.exception;
 
 public class EncryptException extends RuntimeException
 {
   public EncryptException(String message)
   {
     super(message);
   }
 
   public EncryptException(String message, Throwable cause) {
    super(message, cause);
   }
 }
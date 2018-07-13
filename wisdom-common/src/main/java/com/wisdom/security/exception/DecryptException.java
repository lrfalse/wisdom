 package com.wisdom.security.exception;
 
 public class DecryptException extends RuntimeException
 {
   public DecryptException(String message)
   {
    super(message);
   }
 
   public DecryptException(String message, Throwable cause) {
    super(message, cause);
   }
 }
 package com.wisdom.utils;
 
 import com.google.common.base.Preconditions;
 
 public final class HexByteTransformer
 {
   private static final char[] hexDigits = "0123456789abcdef".toCharArray();
 
   public static byte[] hex2Bytes(String hex)
   {
     Preconditions.checkNotNull(hex);
 
    byte[] arrB = hex.getBytes();
     int iLen = arrB.length;
 
     byte[] bytes = new byte[iLen / 2];
     for (int i = 0; i < iLen; i += 2) {
      String strTmp = new String(arrB, i, 2);
      bytes[(i / 2)] = (byte)Integer.parseInt(strTmp, 16);
     }
     return bytes;
   }
 
   public static String bytes2Hex(byte[] bytes)
   {
    Preconditions.checkNotNull(bytes);
 
    StringBuilder sb = new StringBuilder(2 * bytes.length);
    for (byte b : bytes) {
      sb.append(hexDigits[(b >> 4 & 0xF)]).append(hexDigits[(b & 0xF)]);
     }
    return sb.toString();
   }
 }
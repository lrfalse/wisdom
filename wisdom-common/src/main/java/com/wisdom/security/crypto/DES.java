 package com.wisdom.security.crypto;
 
 import com.google.common.base.Charsets;
 import com.google.common.base.Preconditions;
 import com.wisdom.security.exception.DecryptException;
 import com.wisdom.security.exception.EncryptException;
 import com.wisdom.utils.HexByteTransformer;

 import java.security.NoSuchAlgorithmException;
 import javax.crypto.Cipher;
 import javax.crypto.KeyGenerator;
 import javax.crypto.SecretKey;
 import javax.crypto.SecretKeyFactory;
 import javax.crypto.spec.DESKeySpec;
 
 public final class DES
 {
   public static final String ALGORITHM_DES = "DES";
 
   public static String encrypt(String message, String keyStr)
     throws EncryptException
   {
     Preconditions.checkNotNull(message);
     Preconditions.checkNotNull(keyStr);
     try
     {
       DESKeySpec desKey = new DESKeySpec(keyStr.getBytes());
       SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
       SecretKey securekey = keyFactory.generateSecret(desKey);
       Cipher cipher = Cipher.getInstance("DES");
       cipher.init(1, securekey);
       byte[] result = cipher.doFinal(message.getBytes(Charsets.UTF_8));
       return HexByteTransformer.bytes2Hex(result); } catch (Exception e) {
     }
     return "";
   }
 
   public static String decrypt(String encryptMessage, String keyStr)
     throws DecryptException {
       Preconditions.checkNotNull(encryptMessage);
       Preconditions.checkNotNull(keyStr);
       try {
           DESKeySpec desKey = new DESKeySpec(keyStr.getBytes());
           SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
           SecretKey securekey = keyFactory.generateSecret(desKey);
           Cipher cipher = Cipher.getInstance("DES");
           cipher.init(2, securekey);
           byte[] result = cipher.doFinal(HexByteTransformer.hex2Bytes(encryptMessage));
           return new String(result, Charsets.UTF_8);
       } catch (Exception e) {

       }
       return null;
   }
 
   public static byte[] initSecretKey()
     throws NoSuchAlgorithmException
   {
     KeyGenerator kg = KeyGenerator.getInstance("DES");
 
     kg.init(56);
 
     SecretKey secretKey = kg.generateKey();
     return secretKey.getEncoded();
   }
 }
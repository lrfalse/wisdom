 package com.wisdom.security.messagedigest;
 
 import com.google.common.base.Charsets;
 import com.google.common.base.Preconditions;
 import com.google.common.hash.Hasher;
 import com.google.common.hash.Hashing;
 
 public final class MD5
 {
   public static String generateDigestString(String message)
   {
     Preconditions.checkNotNull(message);
 
     Hasher hasher = Hashing.md5().newHasher();
     hasher.putString(message, Charsets.UTF_8);
     String digest = hasher.hash().toString();
     return digest;
   }
 }
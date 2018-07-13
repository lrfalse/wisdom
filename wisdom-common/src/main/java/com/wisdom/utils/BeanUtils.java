 package com.wisdom.utils;
 
 import com.google.common.collect.Maps;
 import java.util.Iterator;
 import java.util.Map;
 import org.apache.commons.beanutils.BeanMap;
 
 public class BeanUtils
 {
   public static Map<String, Object> bean2Map(Object bean)
   {
     if (bean == null) {
       return null;
     }
     Map map = Maps.newHashMap();
     BeanMap beanMap = new BeanMap(bean);
     Iterator keyIterator = beanMap.keyIterator();
     while (keyIterator.hasNext()) {
      String property = (String)keyIterator.next();
       if ("class".equals(property)) {
         continue;
       }
       Object value = beanMap.get(property);
       map.put(property, value);
     }
     return map;
   }
 
   public static <T> T mapToBean(Map<String, Object> map, Class<T> beanClass) {
     if ((map == null) || (map.isEmpty()) || (beanClass == null)) {
       return null;
     }
     try
     {
       T obj = initClass(beanClass);
       org.apache.commons.beanutils.BeanUtils.copyProperties(obj, map);
        return obj;
     } catch (Exception e) {
         throw new RuntimeException(e);
     }
   }
 
   private static <T> T initClass(Class<T> beanClass) throws IllegalAccessException, InstantiationException
   {
     return beanClass.newInstance();
   }
 
   public static void copyProperties(Object dest, Object orig) {
     if (dest == null) {
       throw new RuntimeException("copyProperties error,第一个参数目标对象为NULL");
     }
     if (orig == null)
       throw new RuntimeException("copyProperties error,第二个参数源对象为NULL");
     try
     {
       org.apache.commons.beanutils.BeanUtils.copyProperties(dest, orig);
     } catch (Exception e) {
       throw new RuntimeException("copyProperties error", e);
     }
   }
 }
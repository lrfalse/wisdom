 package com.wisdom.rabbitmq;

 import com.google.common.base.Preconditions;
 import com.google.common.collect.Maps;
 import com.wisdom.api.Resp;
 import com.wisdom.serialization.json.FastJson;
 import com.wisdom.utils.HttpClientUtils;
 import com.wisdom.utils.StringUtils;

 import java.util.Map;
 
 public class RabbitMqClient
 {
   private static HttpClientUtils httpClientUtils = new HttpClientUtils();
 
   public static Resp sendMsgBlock(RabbitReq rabbitReq, String active)
   {
     Preconditions.checkNotNull(rabbitReq);
     Preconditions.checkArgument(StringUtils.isNotBlank(active), "Param active must be not null and empty");
     Preconditions.checkArgument(StringUtils.isNotBlank(rabbitReq.getSystemName()), "Param systemName must be not null and empty");
     Preconditions.checkArgument(StringUtils.isNotBlank(rabbitReq.getExchangeName()), "Param exchangeName must be not null and empty");
     Preconditions.checkArgument(rabbitReq.getJsonData() != null, "Param jsonData must be not null and empty");
 
     Map postParams = Maps.newHashMap();
     postParams.put("jsonData", rabbitReq.getJsonData().toJSONString());
     postParams.put("systemName", rabbitReq.getSystemName());
     postParams.put("exchangeName", rabbitReq.getExchangeName());
     String respStr;
     if (active.equalsIgnoreCase("production"))
       respStr = httpClientUtils.httpPostForm("https://md-message-center.shanyishanmei.com/api/msg/v1/sendBlock", postParams);
     else {
       respStr = httpClientUtils.httpPostForm("http://10.25.132.83:8118/api/msg/v1/sendBlock", postParams);
     }
     if (org.apache.commons.lang3.StringUtils.isBlank(respStr)) {
       return Resp.error("-1", "向mq发送消息失败");
     }
 
     Map map = (Map)FastJson.jsonStr2Object(respStr, Map.class);
     if ((map.get("code") == null) || (!"0".equalsIgnoreCase((String)map.get("code")))) {
       return Resp.error("-1", "向mq发送消息失败");
     }
     return Resp.success();
   }
 
   public static Resp sendMsg(RabbitReq rabbitReq, String active)
   {
     Preconditions.checkNotNull(rabbitReq);
     Preconditions.checkArgument(StringUtils.isNotBlank(active), "Param active must be not null and empty");
     Preconditions.checkArgument(StringUtils.isNotBlank(rabbitReq.getSystemName()), "Param systemName must be not null and empty");
     Preconditions.checkArgument(StringUtils.isNotBlank(rabbitReq.getExchangeName()), "Param exchangeName must be not null and empty");
     Preconditions.checkArgument(rabbitReq.getJsonData() != null, "Param jsonData must be not null and empty");
 
     Map postParams = Maps.newHashMap();
     postParams.put("jsonData", rabbitReq.getJsonData().toJSONString());
     postParams.put("systemName", rabbitReq.getSystemName());
    postParams.put("exchangeName", rabbitReq.getExchangeName());
     String respStr;
     if (active.equalsIgnoreCase("production"))
       respStr = httpClientUtils.httpPostForm("https://sales-message-center.shanyishanmei.com/api/msg/v1/send", postParams);
     else {
       respStr = httpClientUtils.httpPostForm("http://10.25.132.83:8113/api/msg/v1/send", postParams);
     }
     if (org.apache.commons.lang3.StringUtils.isBlank(respStr)) {
       return Resp.error("-1", "向mq发送消息失败");
     }
 
     Map map = (Map)FastJson.jsonStr2Object(respStr, Map.class);
     if ((map.get("code") == null) || (!"0".equalsIgnoreCase((String)map.get("code")))) {
       return Resp.error("-1", "向mq发送消息失败");
     }
     return Resp.success();
   }
 
   public static Resp sendDelayMsg(RabbitReq rabbitReq, String active, Integer delay)
   {
     Preconditions.checkNotNull(rabbitReq);
     Preconditions.checkArgument(StringUtils.isNotBlank(active), "Param active must be not null and empty");
     Preconditions.checkArgument(StringUtils.isNotBlank(rabbitReq.getSystemName()), "Param systemName must be not null and empty");
     Preconditions.checkArgument(StringUtils.isNotBlank(rabbitReq.getExchangeName()), "Param exchangeName must be not null and empty");
     Preconditions.checkArgument(rabbitReq.getJsonData() != null, "Param jsonData must be not null and empty");
     Preconditions.checkArgument(delay != null, "Param delay must be not null and empty");
 
     Map postParams = Maps.newHashMap();
     postParams.put("jsonData", rabbitReq.getJsonData().toJSONString());
     postParams.put("systemName", rabbitReq.getSystemName());
     postParams.put("exchangeName", rabbitReq.getExchangeName());
     String respStr;
     if (active.equalsIgnoreCase("production"))
       respStr = httpClientUtils.httpPostForm("https://sales-message-center.shanyishanmei.com/api/msg/v1/sendDelay?delay=" + delay, postParams);
     else {
       respStr = httpClientUtils.httpPostForm("http://10.25.132.83:8113/api/msg/v1/sendDelay?delay=" + delay, postParams);
     }
     if (org.apache.commons.lang3.StringUtils.isBlank(respStr)) {
       return Resp.error("-1", "向mq发送延时消息失败");
     }
 
     Map map = (Map)FastJson.jsonStr2Object(respStr, Map.class);
     if ((map.get("code") == null) || (!"0".equalsIgnoreCase((String)map.get("code")))) {
       return Resp.error("-1", "向mq发送延时消息失败");
     }
     return Resp.success();
   }
 
   public static Resp sendTimingMsg(RabbitReq rabbitReq, String active, Long timeStamp)
   {
     Preconditions.checkNotNull(rabbitReq);
     Preconditions.checkArgument(StringUtils.isNotBlank(active), "Param active must be not null and empty");
     Preconditions.checkArgument(StringUtils.isNotBlank(rabbitReq.getSystemName()), "Param systemName must be not null and empty");
     Preconditions.checkArgument(StringUtils.isNotBlank(rabbitReq.getExchangeName()), "Param exchangeName must be not null and empty");
     Preconditions.checkArgument(rabbitReq.getJsonData() != null, "Param jsonData must be not null and empty");
     Preconditions.checkArgument(timeStamp != null, "Param timeStamp must be not null and empty");
 
     Map postParams = Maps.newHashMap();
     postParams.put("jsonData", rabbitReq.getJsonData().toJSONString());
     postParams.put("systemName", rabbitReq.getSystemName());
     postParams.put("exchangeName", rabbitReq.getExchangeName());
     String respStr;
     if (active.equalsIgnoreCase("production"))
       respStr = httpClientUtils.httpPostForm("https://sales-message-center.shanyishanmei.com/api/msg/v1/sendTiming?delay=" + timeStamp, postParams);
     else {
       respStr = httpClientUtils.httpPostForm("http://10.25.132.83:8113/api/msg/v1/sendTiming?delay=" + timeStamp, postParams);
     }
     if (org.apache.commons.lang3.StringUtils.isBlank(respStr)) {
       return Resp.error("-1", "向mq发送定时消息失败");
     }
 
     Map map = (Map)FastJson.jsonStr2Object(respStr, Map.class);
     if ((map.get("code") == null) || (!"0".equalsIgnoreCase((String)map.get("code")))) {
       return Resp.error("-1", "向mq发送定时消息失败");
     }
     return Resp.success();
   }
 }
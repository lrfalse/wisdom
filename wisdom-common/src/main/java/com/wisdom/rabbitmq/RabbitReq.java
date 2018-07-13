 package com.wisdom.rabbitmq;
 
 import com.alibaba.fastjson.JSONObject;
 
 public class RabbitReq
 {
   private String systemName;
   private String exchangeName;
   private JSONObject jsonData;
 
   public String getSystemName()
   {
     return this.systemName;
   }
 
   public String getExchangeName() {
    return this.exchangeName;
   }
 
   public JSONObject getJsonData() {
    return this.jsonData;
   }
 
   public void setSystemName(String systemName)
   {
     this.systemName = systemName; } 
   public void setExchangeName(String exchangeName) { this.exchangeName = exchangeName; } 
   public void setJsonData(JSONObject jsonData) { this.jsonData = jsonData; } 
   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof RabbitReq)) return false; RabbitReq other = (RabbitReq)o; if (!other.canEqual(this)) return false; Object this$systemName = getSystemName(); Object other$systemName = other.getSystemName(); if (this$systemName == null ? other$systemName != null : !this$systemName.equals(other$systemName)) return false; Object this$exchangeName = getExchangeName(); Object other$exchangeName = other.getExchangeName(); if (this$exchangeName == null ? other$exchangeName != null : !this$exchangeName.equals(other$exchangeName)) return false; Object this$jsonData = getJsonData(); Object other$jsonData = other.getJsonData(); return this$jsonData == null ? other$jsonData == null : this$jsonData.equals(other$jsonData); } 
   protected boolean canEqual(Object other) { return other instanceof RabbitReq; } 
   public int hashCode() { int PRIME = 59; int result = 1; Object $systemName = getSystemName(); result = result * 59 + ($systemName == null ? 43 : $systemName.hashCode()); Object $exchangeName = getExchangeName(); result = result * 59 + ($exchangeName == null ? 43 : $exchangeName.hashCode()); Object $jsonData = getJsonData(); return result * 59 + ($jsonData == null ? 43 : $jsonData.hashCode()); } 
   public String toString() { return "RabbitReq(systemName=" + getSystemName() + ", exchangeName=" + getExchangeName() + ", jsonData=" + getJsonData() + ")";
   }
 }

package com.wisdom.third.wx.mp.util.json;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.wisdom.third.wx.common.api.WxConsts;
import com.wisdom.third.wx.mp.bean.WxMpMassGroupMessage;

public class WxMpMassGroupMessageGsonAdapter implements JsonSerializer<WxMpMassGroupMessage> {

  public JsonElement serialize(WxMpMassGroupMessage message, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject messageJson = new JsonObject();
    
    JsonObject filter = new JsonObject();
    if(null == message.getGroupId()) {
      filter.addProperty("is_to_all", true);
    } else {
      filter.addProperty("is_to_all", false);
      filter.addProperty("group_id", message.getGroupId());
    }
    messageJson.add("filter", filter);
    
    if (WxConsts.MASS_MSG_NEWS.equals(message.getMsgtype())) {
      JsonObject sub = new JsonObject();
      sub.addProperty("media_id", message.getMediaId());
      messageJson.add(WxConsts.MASS_MSG_NEWS, sub);
    }
    if (WxConsts.MASS_MSG_TEXT.equals(message.getMsgtype())) {
      JsonObject sub = new JsonObject();
      sub.addProperty("content", message.getContent());
      messageJson.add(WxConsts.MASS_MSG_TEXT, sub);
    }
    if (WxConsts.MASS_MSG_VOICE.equals(message.getMsgtype())) {
      JsonObject sub = new JsonObject();
      sub.addProperty("media_id", message.getMediaId());
      messageJson.add(WxConsts.MASS_MSG_VOICE, sub);
    }
    if (WxConsts.MASS_MSG_IMAGE.equals(message.getMsgtype())) {
      JsonObject sub = new JsonObject();
      sub.addProperty("media_id", message.getMediaId());
      messageJson.add(WxConsts.MASS_MSG_IMAGE, sub);
    }
    if (WxConsts.MASS_MSG_VIDEO.equals(message.getMsgtype())) {
      JsonObject sub = new JsonObject();
      sub.addProperty("media_id", message.getMediaId());
      messageJson.add(WxConsts.MASS_MSG_VIDEO, sub);
    }
    messageJson.addProperty("msgtype", message.getMsgtype());
    return messageJson;
  }

}

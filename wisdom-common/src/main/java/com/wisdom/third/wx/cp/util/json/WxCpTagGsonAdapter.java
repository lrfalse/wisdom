
package com.wisdom.third.wx.cp.util.json;

import com.google.gson.*;
import com.wisdom.third.wx.common.util.json.GsonHelper;
import com.wisdom.third.wx.cp.bean.WxCpTag;

import java.lang.reflect.Type;

public class WxCpTagGsonAdapter implements JsonSerializer<WxCpTag>, JsonDeserializer<WxCpTag> {

  public JsonElement serialize(WxCpTag tag, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject o = new JsonObject();
    o.addProperty("tagid", tag.getId());
    o.addProperty("tagname", tag.getName());
    return o;
  }

  public WxCpTag deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    JsonObject jsonObject = json.getAsJsonObject();
    return new WxCpTag(GsonHelper.getString(jsonObject, "tagid"), GsonHelper.getString(jsonObject, "tagname"));
  }

}

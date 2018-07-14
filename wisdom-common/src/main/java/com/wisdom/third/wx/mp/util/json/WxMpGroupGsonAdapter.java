
package com.wisdom.third.wx.mp.util.json;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.wisdom.third.wx.common.util.json.GsonHelper;
import com.wisdom.third.wx.mp.bean.WxMpGroup;

public class WxMpGroupGsonAdapter implements JsonSerializer<WxMpGroup>, JsonDeserializer<WxMpGroup> {

  public JsonElement serialize(WxMpGroup group, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject json = new JsonObject();
    JsonObject groupJson = new JsonObject();
    groupJson.addProperty("name", group.getName());
    groupJson.addProperty("id", group.getId());
    groupJson.addProperty("count", group.getCount());
    json.add("group", groupJson);
    return json;
  }

  public WxMpGroup deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    WxMpGroup group = new WxMpGroup();
    JsonObject groupJson = json.getAsJsonObject();
    if (json.getAsJsonObject().get("group") != null) {
      groupJson = json.getAsJsonObject().get("group").getAsJsonObject();
    }
    if (groupJson.get("name") != null && !groupJson.get("name").isJsonNull()) {
      group.setName(GsonHelper.getAsString(groupJson.get("name")));
    }
    if (groupJson.get("id") != null && !groupJson.get("id").isJsonNull()) {
      group.setId(GsonHelper.getAsPrimitiveLong(groupJson.get("id")));
    }
    if (groupJson.get("count") != null && !groupJson.get("count").isJsonNull()) {
      group.setCount(GsonHelper.getAsPrimitiveLong(groupJson.get("count")));
    }
    return group;
  }
  
}

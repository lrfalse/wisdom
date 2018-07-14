
package com.wisdom.third.wx.cp.util.json;

import com.google.gson.*;
import com.wisdom.third.wx.common.util.json.GsonHelper;
import com.wisdom.third.wx.cp.bean.WxCpDepart;

import java.lang.reflect.Type;

public class WxCpDepartGsonAdapter implements JsonSerializer<WxCpDepart>, JsonDeserializer<WxCpDepart> {

  public JsonElement serialize(WxCpDepart group, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject json = new JsonObject();
    if (group.getId() != null) {
      json.addProperty("id", group.getId());
    }
    if (group.getName() != null) {
      json.addProperty("name", group.getName());
    }
    if (group.getParentId() != null) {
      json.addProperty("parentid", group.getParentId());
    }
    if (group.getOrder() != null) {
      json.addProperty("order", String.valueOf(group.getOrder()));
    }
    return json;
  }

  public WxCpDepart deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    WxCpDepart depart = new WxCpDepart();
    JsonObject departJson = json.getAsJsonObject();
    if (departJson.get("id") != null && !departJson.get("id").isJsonNull()) {
      depart.setId(GsonHelper.getAsInteger(departJson.get("id")));
    }
    if (departJson.get("name") != null && !departJson.get("name").isJsonNull()) {
      depart.setName(GsonHelper.getAsString(departJson.get("name")));
    }
    if (departJson.get("order") != null && !departJson.get("order").isJsonNull()) {
      depart.setOrder(GsonHelper.getAsInteger(departJson.get("order")));
    }
    if (departJson.get("parentid") != null && !departJson.get("parentid").isJsonNull()) {
      depart.setParentId(GsonHelper.getAsInteger(departJson.get("parentid")));
    }
    return depart;
  }

}

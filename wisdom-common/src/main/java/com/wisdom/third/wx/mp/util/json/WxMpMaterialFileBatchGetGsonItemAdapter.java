
package com.wisdom.third.wx.mp.util.json;

import java.lang.reflect.Type;
import java.util.Date;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.wisdom.third.wx.common.util.json.GsonHelper;
import com.wisdom.third.wx.mp.bean.result.WxMpMaterialFileBatchGetResult;

public class WxMpMaterialFileBatchGetGsonItemAdapter implements JsonDeserializer<WxMpMaterialFileBatchGetResult.WxMaterialFileBatchGetNewsItem> {

  public WxMpMaterialFileBatchGetResult.WxMaterialFileBatchGetNewsItem deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    WxMpMaterialFileBatchGetResult.WxMaterialFileBatchGetNewsItem wxMaterialFileBatchGetNewsItem = new WxMpMaterialFileBatchGetResult.WxMaterialFileBatchGetNewsItem();
    JsonObject json = jsonElement.getAsJsonObject();
    if (json.get("media_id") != null && !json.get("media_id").isJsonNull()) {
      wxMaterialFileBatchGetNewsItem.setMediaId(GsonHelper.getAsString(json.get("media_id")));
    }
    if (json.get("update_time") != null && !json.get("update_time").isJsonNull()) {
      wxMaterialFileBatchGetNewsItem.setUpdateTime(new Date(1000 * GsonHelper.getAsLong(json.get("update_time"))));
    }
    if (json.get("name") != null && !json.get("name").isJsonNull()) {
      wxMaterialFileBatchGetNewsItem.setName(GsonHelper.getAsString(json.get("name")));
    }
    if (json.get("url") != null && !json.get("url").isJsonNull()) {
      wxMaterialFileBatchGetNewsItem.setUrl(GsonHelper.getAsString(json.get("url")));
    }
    return wxMaterialFileBatchGetNewsItem;
  }
}

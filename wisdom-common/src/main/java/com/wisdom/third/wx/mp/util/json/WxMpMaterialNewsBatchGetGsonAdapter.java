
package com.wisdom.third.wx.mp.util.json;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.wisdom.third.wx.common.util.json.GsonHelper;
import com.wisdom.third.wx.mp.bean.result.WxMpMaterialNewsBatchGetResult;

public class WxMpMaterialNewsBatchGetGsonAdapter implements JsonDeserializer<WxMpMaterialNewsBatchGetResult> {

  public WxMpMaterialNewsBatchGetResult deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    WxMpMaterialNewsBatchGetResult wxMpMaterialNewsBatchGetResult = new WxMpMaterialNewsBatchGetResult();
    JsonObject json = jsonElement.getAsJsonObject();
    if (json.get("total_count") != null && !json.get("total_count").isJsonNull()) {
      wxMpMaterialNewsBatchGetResult.setTotalCount(GsonHelper.getAsInteger(json.get("total_count")));
    }
    if (json.get("item_count") != null && !json.get("item_count").isJsonNull()) {
      wxMpMaterialNewsBatchGetResult.setItemCount(GsonHelper.getAsInteger(json.get("item_count")));
    }
    if (json.get("item") != null && !json.get("item").isJsonNull()) {
      JsonArray item = json.getAsJsonArray("item");
      List<WxMpMaterialNewsBatchGetResult.WxMaterialNewsBatchGetNewsItem> items = new ArrayList<WxMpMaterialNewsBatchGetResult.WxMaterialNewsBatchGetNewsItem>();
      for (JsonElement anItem : item) {
        JsonObject articleInfo = anItem.getAsJsonObject();
        items.add(WxMpGsonBuilder.create().fromJson(articleInfo, WxMpMaterialNewsBatchGetResult.WxMaterialNewsBatchGetNewsItem.class));
      }
      wxMpMaterialNewsBatchGetResult.setItems(items);
    }
    return wxMpMaterialNewsBatchGetResult;
  }
}

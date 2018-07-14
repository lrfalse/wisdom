
package com.wisdom.third.wx.mp.util.json;

import java.lang.reflect.Type;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.wisdom.third.wx.common.util.json.GsonHelper;
import com.wisdom.third.wx.mp.bean.result.WxMpMaterialCountResult;

public class WxMpMaterialCountResultAdapter implements JsonDeserializer<WxMpMaterialCountResult> {

  public WxMpMaterialCountResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    WxMpMaterialCountResult countResult = new WxMpMaterialCountResult();
    JsonObject materialCountResultJsonObject = json.getAsJsonObject();

    if (materialCountResultJsonObject.get("voice_count") != null && !materialCountResultJsonObject.get("voice_count").isJsonNull()) {
      countResult.setVoiceCount(GsonHelper.getAsInteger(materialCountResultJsonObject.get("voice_count")));
    }
    if (materialCountResultJsonObject.get("video_count") != null && !materialCountResultJsonObject.get("video_count").isJsonNull()) {
      countResult.setVideoCount(GsonHelper.getAsInteger(materialCountResultJsonObject.get("video_count")));
    }
    if (materialCountResultJsonObject.get("image_count") != null && !materialCountResultJsonObject.get("image_count").isJsonNull()) {
      countResult.setImageCount(GsonHelper.getAsInteger(materialCountResultJsonObject.get("image_count")));
    }
    if (materialCountResultJsonObject.get("news_count") != null && !materialCountResultJsonObject.get("news_count").isJsonNull()) {
      countResult.setNewsCount(GsonHelper.getAsInteger(materialCountResultJsonObject.get("news_count")));
    }
    return countResult;
  }

}

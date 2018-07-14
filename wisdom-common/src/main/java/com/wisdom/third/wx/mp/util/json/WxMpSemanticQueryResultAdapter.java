
package com.wisdom.third.wx.mp.util.json;

import java.lang.reflect.Type;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.wisdom.third.wx.common.util.json.GsonHelper;
import com.wisdom.third.wx.mp.bean.result.WxMpSemanticQueryResult;

public class WxMpSemanticQueryResultAdapter implements JsonDeserializer<WxMpSemanticQueryResult> {

  public WxMpSemanticQueryResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    WxMpSemanticQueryResult result = new WxMpSemanticQueryResult();
    JsonObject resultJsonObject = json.getAsJsonObject();

    if (GsonHelper.getString(resultJsonObject, "query") != null) {
      result.setQuery(GsonHelper.getString(resultJsonObject, "query"));
    }
    if (GsonHelper.getString(resultJsonObject, "type") != null) {
      result.setType(GsonHelper.getString(resultJsonObject, "type"));
    }
    if (resultJsonObject.get("semantic") != null) {
      result.setSemantic(resultJsonObject.get("semantic").toString());
    }
    if (resultJsonObject.get("result") != null) {
      result.setResult(resultJsonObject.get("result").toString());
    }
    if (GsonHelper.getString(resultJsonObject, "answer") != null) {
      result.setAnswer(GsonHelper.getString(resultJsonObject, "answer"));
    }
    if (GsonHelper.getString(resultJsonObject, "text") != null) {
      result.setText(GsonHelper.getString(resultJsonObject, "text"));
    }
    return result;
  }
  
}

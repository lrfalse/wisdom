
package com.wisdom.third.wx.mp.util.json;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.wisdom.third.wx.common.util.json.GsonHelper;
import com.wisdom.third.wx.mp.bean.result.WxMpMassSendResult;

public class WxMpMassSendResultAdapter implements JsonDeserializer<WxMpMassSendResult> {

  public WxMpMassSendResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    WxMpMassSendResult sendResult = new WxMpMassSendResult();
    JsonObject sendResultJsonObject = json.getAsJsonObject();

    if (sendResultJsonObject.get("errcode") != null && !sendResultJsonObject.get("errcode").isJsonNull()) {
      sendResult.setErrorCode(GsonHelper.getAsString(sendResultJsonObject.get("errcode")));
    }
    if (sendResultJsonObject.get("errmsg") != null && !sendResultJsonObject.get("errmsg").isJsonNull()) {
      sendResult.setErrorMsg(GsonHelper.getAsString(sendResultJsonObject.get("errmsg")));
    }
    if (sendResultJsonObject.get("msg_id") != null && !sendResultJsonObject.get("msg_id").isJsonNull()) {
      sendResult.setMsgId(GsonHelper.getAsString(sendResultJsonObject.get("msg_id")));
    }
    return sendResult;
  }
  
}


package com.wisdom.third.wx.cp.util.json;


import com.google.gson.*;
import com.wisdom.third.wx.common.api.WxConsts;
import com.wisdom.third.wx.common.util.StringUtils;
import com.wisdom.third.wx.cp.bean.WxCpMessage;

import java.lang.reflect.Type;

public class WxCpMessageGsonAdapter implements JsonSerializer<WxCpMessage> {

  public JsonElement serialize(WxCpMessage message, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject messageJson = new JsonObject();
    messageJson.addProperty("agentid", message.getAgentId());
    if (StringUtils.isNotBlank(message.getToUser())) {
      messageJson.addProperty("touser", message.getToUser());
    }
    messageJson.addProperty("msgtype", message.getMsgType());

    if (StringUtils.isNotBlank(message.getToParty())) {
      messageJson.addProperty("toparty", message.getToParty());
    }
    if (StringUtils.isNotBlank(message.getToTag())) {
      messageJson.addProperty("totag", message.getToTag());
    }
    if (WxConsts.CUSTOM_MSG_TEXT.equals(message.getMsgType())) {
      JsonObject text = new JsonObject();
      text.addProperty("content", message.getContent());
      messageJson.add("text", text);
    }

    if (WxConsts.CUSTOM_MSG_IMAGE.equals(message.getMsgType())) {
      JsonObject image = new JsonObject();
      image.addProperty("media_id", message.getMediaId());
      messageJson.add("image", image);
    }

    if (WxConsts.CUSTOM_MSG_FILE.equals(message.getMsgType())) {
      JsonObject image = new JsonObject();
      image.addProperty("media_id", message.getMediaId());
      messageJson.add("file", image);
    }

    if (WxConsts.CUSTOM_MSG_VOICE.equals(message.getMsgType())) {
      JsonObject voice = new JsonObject();
      voice.addProperty("media_id", message.getMediaId());
      messageJson.add("voice", voice);
    }

    if (WxConsts.CUSTOM_MSG_VIDEO.equals(message.getMsgType())) {
      JsonObject video = new JsonObject();
      video.addProperty("media_id", message.getMediaId());
      video.addProperty("thumb_media_id", message.getThumbMediaId());
      video.addProperty("title", message.getTitle());
      video.addProperty("description", message.getDescription());
      messageJson.add("video", video);
    }

    if (WxConsts.CUSTOM_MSG_NEWS.equals(message.getMsgType())) {
      JsonObject newsJsonObject = new JsonObject();
      JsonArray articleJsonArray = new JsonArray();
      for (WxCpMessage.WxArticle article : message.getArticles()) {
        JsonObject articleJson = new JsonObject();
        articleJson.addProperty("title", article.getTitle());
        articleJson.addProperty("description", article.getDescription());
        articleJson.addProperty("url", article.getUrl());
        articleJson.addProperty("picurl", article.getPicUrl());
        articleJsonArray.add(articleJson);
      }
      newsJsonObject.add("articles", articleJsonArray);
      messageJson.add("news", newsJsonObject);
    }
    
    return messageJson;
  }

}

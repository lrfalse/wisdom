
package com.wisdom.third.wx.mp.util.json;

import java.lang.reflect.Type;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.wisdom.third.wx.common.util.json.GsonHelper;
import com.wisdom.third.wx.mp.bean.result.WxMpQrCodeTicket;

public class WxQrCodeTicketAdapter implements JsonDeserializer<WxMpQrCodeTicket> {

  public WxMpQrCodeTicket deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    WxMpQrCodeTicket ticket = new WxMpQrCodeTicket();
    JsonObject ticketJsonObject = json.getAsJsonObject();

    if (ticketJsonObject.get("ticket") != null && !ticketJsonObject.get("ticket").isJsonNull()) {
      ticket.setTicket(GsonHelper.getAsString(ticketJsonObject.get("ticket")));
    }
    if (ticketJsonObject.get("expire_seconds") != null && !ticketJsonObject.get("expire_seconds").isJsonNull()) {
      ticket.setExpire_seconds(GsonHelper.getAsPrimitiveInt(ticketJsonObject.get("expire_seconds")));
    }
    if (ticketJsonObject.get("url") != null && !ticketJsonObject.get("url").isJsonNull()) {
      ticket.setUrl(GsonHelper.getAsString(ticketJsonObject.get("url")));
    }
    return ticket;
  }
  
}


package com.wisdom.third.wx.mp.util.json;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.wisdom.third.wx.common.util.json.GsonHelper;
import com.wisdom.third.wx.mp.bean.result.WxMpUserCumulate;


public class WxMpUserCumulateGsonAdapter implements JsonDeserializer<WxMpUserCumulate> {

  private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

  public WxMpUserCumulate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    WxMpUserCumulate cumulate = new WxMpUserCumulate();
    JsonObject summaryJsonObject = json.getAsJsonObject();

    try {
      String refDate = GsonHelper.getString(summaryJsonObject, "ref_date");
      if (refDate != null) {
        cumulate.setRefDate(SIMPLE_DATE_FORMAT.parse(refDate));
      }
      cumulate.setCumulateUser(GsonHelper.getInteger(summaryJsonObject, "cumulate_user"));
    } catch (ParseException e) {
      throw new JsonParseException(e);
    }
    return cumulate;

  }
  
}

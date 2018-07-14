package com.wisdom.third.wx.cp.util.json;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wisdom.third.wx.common.bean.result.WxError;
import com.wisdom.third.wx.common.util.json.WxErrorAdapter;
import com.wisdom.third.wx.cp.bean.WxCpDepart;
import com.wisdom.third.wx.cp.bean.WxCpMessage;
import com.wisdom.third.wx.cp.bean.WxCpTag;
import com.wisdom.third.wx.cp.bean.WxCpUser;

public class WxCpGsonBuilder {

  public static final GsonBuilder INSTANCE = new GsonBuilder();

  static {
    INSTANCE.disableHtmlEscaping();
    INSTANCE.registerTypeAdapter(WxCpMessage.class, new WxCpMessageGsonAdapter());
    INSTANCE.registerTypeAdapter(WxCpDepart.class, new WxCpDepartGsonAdapter());
    INSTANCE.registerTypeAdapter(WxCpUser.class, new WxCpUserGsonAdapter());
    INSTANCE.registerTypeAdapter(WxError.class, new WxErrorAdapter());
    INSTANCE.registerTypeAdapter(WxCpTag.class, new WxCpTagGsonAdapter());
  }

  public static Gson create() {
    return INSTANCE.create();
  }

}

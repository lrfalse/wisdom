package com.wisdom.third.wx.common.bean.result;


import com.wisdom.third.wx.common.util.json.WxGsonBuilder;

import java.io.Serializable;


/**
 * 微信错误码说明
 */
public class WxError implements Serializable {

  private int errorCode;
  
  private String errorMsg;

  private String json;
  
  public int getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(int errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorMsg() {
    return errorMsg;
  }

  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }

  public String getJson() {
    return json;
  }

  public void setJson(String json) {
    this.json = json;
  }

  public static WxError fromJson(String json) {
    WxError error = WxGsonBuilder.create().fromJson(json, WxError.class);
    return error;
  }

  @Override
  public String toString() {
    return "微信错误: errcode=" + errorCode + ", errmsg=" + errorMsg + "\njson:" + json;
  }

}

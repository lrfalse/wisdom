package com.wisdom.third.wx.common.session;

public interface InternalSession {

  WxSession getSession();
  public void setValid(boolean isValid);

  boolean isValid();

  String getIdInternal();

  void expire();

  void access();

  void endAccess();

  void setCreationTime(long time);

  void setMaxInactiveInterval(int interval);

  void setId(String id);
}

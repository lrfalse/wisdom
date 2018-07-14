package com.wisdom.third.wx.common.api;


import com.wisdom.third.wx.common.exception.WxErrorException;

/**
 * WxErrorException处理器
 */
public interface WxErrorExceptionHandler {

  public void handle(WxErrorException e);

}

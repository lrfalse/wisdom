package com.wisdom.third.wx.common.util;

import com.wisdom.third.wx.common.api.WxErrorExceptionHandler;
import com.wisdom.third.wx.common.exception.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class LogExceptionHandler implements WxErrorExceptionHandler {

  private Logger log = LoggerFactory.getLogger(WxErrorExceptionHandler.class);

  @Override
  public void handle(WxErrorException e) {

    log.error("Error happens", e);

  }

}

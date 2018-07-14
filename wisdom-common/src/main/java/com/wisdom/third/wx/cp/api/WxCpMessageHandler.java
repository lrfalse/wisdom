package com.wisdom.third.wx.cp.api;


import com.wisdom.third.wx.common.exception.WxErrorException;
import com.wisdom.third.wx.common.session.WxSessionManager;
import com.wisdom.third.wx.cp.bean.WxCpXmlMessage;
import com.wisdom.third.wx.cp.bean.WxCpXmlOutMessage;

import java.util.Map;

/**
 * 处理微信推送消息的处理器接口
 */
public interface WxCpMessageHandler {

  /**
   * @param wxMessage
   * @param context        上下文，如果handler或interceptor之间有信息要传递，可以用这个
   * @param wxCpService
   * @param sessionManager
   * @return xml格式的消息，如果在异步规则里处理的话，可以返回null
   */
  public WxCpXmlOutMessage handle(WxCpXmlMessage wxMessage,
                                  Map<String, Object> context,
                                  WxCpService wxCpService,
                                  WxSessionManager sessionManager) throws WxErrorException;

}

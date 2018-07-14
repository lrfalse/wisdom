package com.wisdom.third.wx.mp.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.wisdom.third.wx.common.api.WxConsts;
import com.wisdom.third.wx.common.util.xml.XStreamMediaIdConverter;

@XStreamAlias("xml")
public class WxMpXmlOutVoiceMessage extends WxMpXmlOutMessage {

  @XStreamAlias("Voice")
  @XStreamConverter(value = XStreamMediaIdConverter.class)
  private String mediaId;

  public WxMpXmlOutVoiceMessage() {
    this.msgType = WxConsts.XML_MSG_VOICE;
  }

  public String getMediaId() {
    return mediaId;
  }

  public void setMediaId(String mediaId) {
    this.mediaId = mediaId;
  }

}

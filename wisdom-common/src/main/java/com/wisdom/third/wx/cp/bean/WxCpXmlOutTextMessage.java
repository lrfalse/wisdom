package com.wisdom.third.wx.cp.bean;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.wisdom.third.wx.common.api.WxConsts;
import com.wisdom.third.wx.common.util.xml.XStreamCDataConverter;

@XStreamAlias("xml")
public class WxCpXmlOutTextMessage extends WxCpXmlOutMessage {
  
  @XStreamAlias("Content")
  @XStreamConverter(value=XStreamCDataConverter.class)
  private String content;

  public WxCpXmlOutTextMessage() {
    this.msgType = WxConsts.XML_MSG_TEXT;
  }
  
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  
}

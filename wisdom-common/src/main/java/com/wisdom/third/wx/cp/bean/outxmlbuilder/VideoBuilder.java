package com.wisdom.third.wx.cp.bean.outxmlbuilder;

import com.wisdom.third.wx.cp.bean.WxCpXmlOutVideoMessage;

/**
 * 视频消息builder
 *
 */
public final class VideoBuilder extends BaseBuilder<VideoBuilder, WxCpXmlOutVideoMessage> {

  private String mediaId;
  private String title;
  private String description;

  public VideoBuilder title(String title) {
    this.title = title;
    return this;
  }
  public VideoBuilder description(String description) {
    this.description = description;
    return this;
  }
  public VideoBuilder mediaId(String mediaId) {
    this.mediaId = mediaId;
    return this;
  }
  
  public WxCpXmlOutVideoMessage build() {
    WxCpXmlOutVideoMessage m = new WxCpXmlOutVideoMessage();
    setCommon(m);
    m.setTitle(title);
    m.setDescription(description);
    m.setMediaId(mediaId);
    return m;
  }
  
}

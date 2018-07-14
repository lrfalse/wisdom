package com.wisdom.third.wx.cp.bean.messagebuilder;

import com.wisdom.third.wx.common.api.WxConsts;
import com.wisdom.third.wx.cp.bean.WxCpMessage;

/**
 * 语音消息builder
 * <pre>
 * 用法: WxCustomMessage m = WxCustomMessage.VOICE().mediaId(...).toUser(...).build();
 * </pre>
 *
 */
public final class VoiceBuilder extends BaseBuilder<VoiceBuilder> {
  private String mediaId;

  public VoiceBuilder() {
    this.msgType = WxConsts.CUSTOM_MSG_VOICE;
  }

  public VoiceBuilder mediaId(String media_id) {
    this.mediaId = media_id;
    return this;
  }

  public WxCpMessage build() {
    WxCpMessage m = super.build();
    m.setMediaId(this.mediaId);
    return m;
  }
}

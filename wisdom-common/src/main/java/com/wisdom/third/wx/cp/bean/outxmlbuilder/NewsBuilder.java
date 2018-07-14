package com.wisdom.third.wx.cp.bean.outxmlbuilder;


import java.util.ArrayList;
import java.util.List;

import com.wisdom.third.wx.cp.bean.WxCpXmlOutNewsMessage;
import com.wisdom.third.wx.cp.bean.WxCpXmlOutNewsMessage.Item;

/**
 * 图文消息builder
 */
public final class NewsBuilder extends BaseBuilder<NewsBuilder, WxCpXmlOutNewsMessage> {

  protected final List<WxCpXmlOutNewsMessage.Item> articles = new ArrayList<Item>();
  
  public NewsBuilder addArticle(Item item) {
    this.articles.add(item);
    return this;
  }
  
  public WxCpXmlOutNewsMessage build() {
    WxCpXmlOutNewsMessage m = new WxCpXmlOutNewsMessage();
    for(Item item : articles) {
      m.addArticle(item);
    }
    setCommon(m);
    return m;
  }
  
}

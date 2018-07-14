package com.wisdom.third.wx.common.util.xml;

import com.thoughtworks.xstream.converters.basic.StringConverter;

public class XStreamCDataConverter extends StringConverter {

  @Override
  public String toString(Object obj) {
    return "<![CDATA[" + super.toString(obj) + "]]>";
  }

}
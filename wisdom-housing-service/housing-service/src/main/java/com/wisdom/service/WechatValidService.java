package com.wisdom.service;

import com.wisdom.model.WechatValid;
import com.wisdom.api.Resp;

public interface WechatValidService {
     Resp save(WechatValid wechatValid);
     Resp queryByObject(Long id);
     Resp queryByList(WechatValid wechatValid);
}

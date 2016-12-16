package com.github.knightliao.canalx.rest.client.protocol;

import com.github.knightliao.canalx.rest.client.protocol.jackson.JacksonRestProxy;
import com.github.knightliao.canalx.rest.client.support.type.RequestTypeEnum;

/**
 * @author knightliao
 * @date 2016/12/15 11:01
 */
public class ProtocolProxyFactory {

    /**
     * 获取 Gson Rpc Proxy
     *
     * @param url
     *
     * @return
     */
    public static JacksonRestProxy getJacksonRestProxy(String url, RequestTypeEnum requestTypeEnum) {

        return new JacksonRestProxy(url, requestTypeEnum);
    }
}

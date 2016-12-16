package com.github.knightliao.canalx.rest.client.protocol.jackson;

import com.github.knightliao.canalx.rest.client.core.proxy.RestProxyBase;
import com.github.knightliao.canalx.rest.client.exception.ParseErrorException;
import com.github.knightliao.canalx.rest.client.support.type.RequestTypeEnum;

/**
 * @author knightliao
 * @date 2016/12/14 23:56
 */
public class JacksonRestProxy extends RestProxyBase {

    protected JacksonCodec jacksonCodec = new JacksonCodec();

    @Override
    protected <T> Object deserialize(String result, Class<T> tClass)
            throws ParseErrorException {

        try {

            return jacksonCodec.decode(tClass, result);

        } catch (Exception e) {
            throw new ParseErrorException(e);
        }
    }

    /**
     * @param url
     */
    public JacksonRestProxy(String url, RequestTypeEnum requestTypeEnum) {
        super(url, requestTypeEnum);
    }
}

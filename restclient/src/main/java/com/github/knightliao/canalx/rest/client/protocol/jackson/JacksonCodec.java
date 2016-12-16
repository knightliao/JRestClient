package com.github.knightliao.canalx.rest.client.protocol.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.knightliao.canalx.rest.client.support.codec.Codec;

/**
 */
public class JacksonCodec implements Codec {

    @Override
    public <T> T decode(Class<T> clazz, String str) throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        //JSON from file to Object
        T object = mapper.readValue(str, clazz);

        return object;
    }
}

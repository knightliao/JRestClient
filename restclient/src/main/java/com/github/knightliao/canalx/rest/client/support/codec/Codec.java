package com.github.knightliao.canalx.rest.client.support.codec;

/**
 * @author knightliao
 * @date 2016/12/15 23:04
 */
public interface Codec {

    <T> T decode(Class<T> clazz, String str) throws Exception;

}
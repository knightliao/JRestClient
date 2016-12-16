package com.github.knightliao.canalx.rest.client.spring.test.core;

import java.util.Map;

import com.github.knightliao.canalx.rest.client.spring.test.core.dto.User;
import com.github.knightliao.canalx.rest.client.support.annotation.RequestParam;
import com.github.knightliao.canalx.rest.client.support.annotation.RequestType;
import com.github.knightliao.canalx.rest.client.support.type.RequestTypeEnum;

/**
 * @author knightliao
 * @date 2016/12/15 10:53
 */
public interface KvRestClient {

    @RequestType(type = RequestTypeEnum.GET)
    Map<String, String> getDbKv(@RequestParam(name = "tableId") String tableId,
                                @RequestParam(name = "key") String key);

    @RequestType(type = RequestTypeEnum.GET)
    User getUser(@RequestParam(name = "tableId") String tableId,
                 @RequestParam(name = "key") String key);
}

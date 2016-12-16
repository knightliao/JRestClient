package com.github.knightliao.canalx.rest.client.core.proxy;

import java.util.HashMap;
import java.util.Map;

import com.github.knightliao.canalx.rest.client.core.proxy.request.GetRequest;
import com.github.knightliao.canalx.rest.client.support.type.RequestTypeEnum;

/**
 * @author knightliao
 * @date 2016/12/15 12:05
 */
public class RequestFactory {

    private static Map<RequestTypeEnum, IRequest> map = new HashMap<>();

    static {
        map.put(RequestTypeEnum.GET, makeGetRequest());
    }

    private static IRequest makeGetRequest() {

        return new GetRequest();
    }

    public static IRequest getRequest(RequestTypeEnum requestTypeEnum) {

        return map.get(requestTypeEnum);
    }

}

package com.github.knightliao.canalx.rest.client.core.proxy;

import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.client.methods.HttpUriRequest;

/**
 * @author knightliao
 * @date 2016/12/15 11:46
 */
public interface IRequest {

    HttpUriRequest getRequest(String url, Map<String, Object> param) throws URISyntaxException;

}

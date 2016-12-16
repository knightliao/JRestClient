package com.github.knightliao.canalx.rest.client.core.proxy.request;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;

import com.github.knightliao.canalx.rest.client.core.proxy.IRequest;

/**
 * @author knightliao
 * @date 2016/12/15 11:47
 */
public class GetRequest implements IRequest {

    @Override
    public HttpUriRequest getRequest(String url, Map<String, Object> param) throws URISyntaxException {

        HttpGet someHttpGet = new HttpGet(url);

        URIBuilder uriBuilder = new URIBuilder(someHttpGet.getURI());

        // set param
        for (String key : param.keySet()) {
            // get parameter cannot be cast to string
            // TODO: should be fixed next time
            uriBuilder.setParameter(key, (String) param.get(key));
        }

        URI uri = uriBuilder.build();

        return new HttpGet(uri);
    }

}

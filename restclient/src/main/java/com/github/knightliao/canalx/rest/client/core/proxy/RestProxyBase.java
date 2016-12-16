package com.github.knightliao.canalx.rest.client.core.proxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.knightliao.canalx.rest.client.core.dto.ResponseDto;
import com.github.knightliao.canalx.rest.client.exception.ParseErrorException;
import com.github.knightliao.canalx.rest.client.exception.RestServiceException;
import com.github.knightliao.canalx.rest.client.support.annotation.RequestParam;
import com.github.knightliao.canalx.rest.client.support.type.RequestTypeEnum;

/**
 *
 */
public abstract class RestProxyBase implements InvocationHandler, Cloneable {

    protected static final Logger LOG = LoggerFactory.getLogger(RestProxyBase.class);

    private String url;
    private RequestTypeEnum requestTypeEnum;
    private int _connectTimeout = 30; // 默认连接超时30秒
    private int _readTimeout = 60; // 默认连接超时60秒

    /**
     * @return
     *
     * @throws ParseErrorException
     */
    protected abstract <T> Object deserialize(String result, Class<T> tClass)
            throws ParseErrorException;

    /**
     * @param url
     */
    public RestProxyBase(String url, RequestTypeEnum requestTypeEnum) {
        this.url = url;
        this.requestTypeEnum = requestTypeEnum;
    }

    /**
     * 调用 函数
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        try {

            LOG.debug("request url: {}, method:{}, args:{} ", url, method.getName(), args);

            // get request parm
            IRequest iRequest = RequestFactory.getRequest(requestTypeEnum);
            Map<String, Object> paramMap = getParamMap(method, args);
            HttpUriRequest httpUriRequest = iRequest.getRequest(url, paramMap);

            // http client
            CloseableHttpClient httpclient = getHttpClient();

            // response
            CloseableHttpResponse httpResponse = httpclient.execute(httpUriRequest);

            if (httpResponse.getStatusLine().getStatusCode() != 200) {
                throw new Exception("status code is not 200");
            }

            // result
            String result = getResult(httpResponse);

            return deserialize(result, method.getReturnType());

        } catch (Exception e) {

            throw new RestServiceException(e);
        }
    }

    /*
     */
    private ResponseDto getResponseDto(CloseableHttpResponse httpResponse, Object object) {
        return new ResponseDto(object, httpResponse.getStatusLine().getStatusCode());
    }

    /**
     *
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    private CloseableHttpClient getHttpClient() {

        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setConnectTimeout(_connectTimeout)
                .setConnectionRequestTimeout(_readTimeout)
                .build();

        // make request
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultRequestConfig(defaultRequestConfig)
                .build();

        return httpclient;
    }

    /**
     * @param httpResponse
     *
     * @return
     */
    private String getResult(CloseableHttpResponse httpResponse) throws RestServiceException {

        try {
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(httpResponse.getEntity().getContent()));

            StringBuilder result = new StringBuilder();
            String line = "";

            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            return result.toString();

        } catch (IOException e) {

            throw new RestServiceException("read from response error", e);
        }
    }

    /**
     * @return
     */
    private Map<String, Object> getParamMap(Method method, Object[] objects) throws RestServiceException {

        Map<String, Object> map = new HashMap<>();

        List<String> paramNames = getRequestParamNameList(method);

        if (paramNames.size() != objects.length) {
            throw new RestServiceException(
                    "param name size is not equal to args size. method name: " + method.getName());
        }

        int index = 0;
        for (String param : paramNames) {
            map.put(param, objects[index]);
            index += 1;
        }

        return map;
    }

    /**
     * @param method
     *
     * @return
     */
    private List<String> getRequestParamNameList(Method method) throws RestServiceException {

        List<String> paramNames = new ArrayList<>();

        Annotation[][] annotations = method.getParameterAnnotations();

        int index = 0;
        for (Annotation[] annotationOne : annotations) {

            boolean found = false;
            for (Annotation annotationTwo : annotationOne) {

                if (annotationTwo instanceof RequestParam) {
                    RequestParam requestParam = (RequestParam) annotationTwo;
                    String paramName = requestParam.name();
                    paramNames.add(paramName);
                    found = true;
                }
            }

            if (!found) {
                throw new RestServiceException("cannot find RequestParam for method " + method.getName() + "  index: "
                        + index);
            }

            index += 1;
        }

        return paramNames;
    }

    public void setConnectTimeout(int v) {
        _connectTimeout = v;
    }

    public void setReadTimeout(int v) {
        _readTimeout = v;
    }

}

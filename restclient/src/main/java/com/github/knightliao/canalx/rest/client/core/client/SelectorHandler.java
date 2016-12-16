package com.github.knightliao.canalx.rest.client.core.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.knightliao.canalx.rest.client.core.proxy.RestProxyBase;
import com.github.knightliao.canalx.rest.client.core.selector.ServiceInvoker;
import com.github.knightliao.canalx.rest.client.core.selector.ServiceSelector;
import com.github.knightliao.canalx.rest.client.core.selector.impl.RandomServiceSelector;
import com.github.knightliao.canalx.rest.client.exception.RestServiceException;
import com.github.knightliao.canalx.rest.client.protocol.ProtocolProxyFactory;
import com.github.knightliao.canalx.rest.client.support.annotation.RequestType;
import com.github.knightliao.canalx.rest.client.support.type.RequestTypeEnum;

/**
 * @author knightliao
 * @date 2016/12/14 22:58
 */
public class SelectorHandler implements InvocationHandler {

    protected static final Logger LOGGER = LoggerFactory.getLogger(SelectorHandler.class);

    private RestClient restClientProxy;

    public SelectorHandler(RestClient restClientProxy) {
        this.restClientProxy = restClientProxy;
    }

    /**
     * @param proxy
     * @param method
     * @param args
     *
     * @return
     *
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 待调用的接口
        final Class targetClass = method.getDeclaringClass();

        // 待调用的方法
        Method targetMethod = method;

        // 待传入参数
        final Object[] targetArgs = args;

        // 方法类型
        final RequestTypeEnum requestTypeEnum = getRequestTypeEnum(method);

        //
        // 具体调用时实现重试功能
        //
        String[] services = restClientProxy.getServices();
        List<ServiceInvoker> serviceInvokers = new ArrayList<ServiceInvoker>(services.length);
        for (int i = 0; i < services.length; i++) {

            final String curServiceUrl = services[i];

            ServiceInvoker service = new ServiceInvoker() {

                public Object getInvoker() throws RestServiceException {

                    RestProxyBase restProxy = getRestProxy(curServiceUrl, requestTypeEnum);

                    //
                    if (restClientProxy.getConnectionTimeout() > 0) {
                        restProxy.setConnectTimeout(restClientProxy.getConnectionTimeout());
                    }

                    //
                    if (restClientProxy.getReadTimeout() > 0) {
                        restProxy.setReadTimeout(restClientProxy.getReadTimeout());
                    }

                    return SelectorHandler.createProxy(targetClass, restProxy);
                }

            };
            serviceInvokers.add(service);
        }

        ServiceSelector serviceSelector =
                new RandomServiceSelector(serviceInvokers, restClientProxy.getRetryTimes(), targetMethod,
                        targetArgs);
        return serviceSelector.invoke(restClientProxy.isErrorExit());
    }

    /**
     * @return
     */
    private RestProxyBase getRestProxy(String curServiceUrl, RequestTypeEnum requestTypeEnum) {
        return ProtocolProxyFactory.getJacksonRestProxy(curServiceUrl, requestTypeEnum);
    }

    /**
     * @param type
     * @param handler
     * @param <T>
     *
     * @return
     */
    private static <T> T createProxy(Class<T> type, InvocationHandler handler) {

        Class<?>[] clazz = new Class[] {type};
        return (T) Proxy.newProxyInstance(SelectorHandler.class.getClassLoader(), clazz, handler);
    }

    /**
     * @param method
     *
     * @return
     *
     * @throws Exception
     */
    private RequestTypeEnum getRequestTypeEnum(Method method) throws Exception {
        // request type
        RequestType requestType = method.getAnnotation(RequestType.class);

        if (requestType == null) {
            throw new Exception("cannot find request type for method " + method.toString());
        }

        return requestType.type();
    }

}

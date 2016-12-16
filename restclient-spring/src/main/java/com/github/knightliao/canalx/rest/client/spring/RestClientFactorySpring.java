package com.github.knightliao.canalx.rest.client.spring;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import com.github.knightliao.canalx.rest.client.core.client.RestClient;

/**
 * @author knightliao
 * @date 2016/12/14 20:44
 */
public class RestClientFactorySpring implements FactoryBean, InitializingBean {

    // 配置的服务器列表，含端口
    private String servers;

    // 协议前缀
    private String protocol;

    // 调用的Url
    private String serviceUrl;

    // 接口
    private Class serviceInterface;

    // 重试次数
    private int retryTimes;

    // codec类型
    private String codecType;

    // 出错后是否直接退出,默认是false
    protected boolean errorExit = false;

    // 连接超时，毫秒数
    private int connectionTimeout;

    // 读超时，毫秒数
    private int readTimeout;

    @Override
    public Object getObject() throws Exception {

        RestClient.Builder builder = RestClient.create();
        if (servers != null) {
            builder.servers(servers);
        }

        if (protocol != null) {
            builder.protocol(protocol);
        }

        if (serviceUrl != null) {
            builder.serviceUrl(serviceUrl);
        }
        if (serviceInterface != null) {
            builder.serviceInterface(serviceInterface);
        }
        if (retryTimes > 0) {
            builder.retryTimes(retryTimes);
        }
        if (codecType != null) {
            builder.codecType(codecType);
        }
        if (connectionTimeout > 0) {
            builder.connectionTimeout(connectionTimeout);
        }

        if (readTimeout > 0) {
            builder.readTimeout(readTimeout);
        }

        return builder.errorExit(errorExit).build();
    }

    @Override
    public Class<?> getObjectType() {
        return serviceInterface;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public String getServers() {
        return servers;
    }

    public void setServers(String servers) {
        this.servers = servers;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public Class getServiceInterface() {
        return serviceInterface;
    }

    public void setServiceInterface(Class serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    public int getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
    }

    public String getCodecType() {
        return codecType;
    }

    public void setCodecType(String codecType) {
        this.codecType = codecType;
    }

    public boolean isErrorExit() {
        return errorExit;
    }

    public void setErrorExit(boolean errorExit) {
        this.errorExit = errorExit;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }
}

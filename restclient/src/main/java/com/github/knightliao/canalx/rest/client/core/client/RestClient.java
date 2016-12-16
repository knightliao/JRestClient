package com.github.knightliao.canalx.rest.client.core.client;

import java.lang.reflect.Proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

import lombok.Data;

/**
 * @author knightliao
 * @date 2016/12/14 22:54
 */
@Data
public class RestClient {

    protected static final Logger LOG = LoggerFactory.getLogger(RestClient.class);

    // 服务列表，非空，由server+url生成
    private String[] services;

    // 配置的服务器列表，含端口
    private String[] servers;

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
    protected boolean errorExit;

    // 连接超时，毫秒数
    private int connectionTimeout;

    // 读超时，毫秒数
    private int readTimeout;

    public RestClient(String[] servers, String protocol, String serviceUrl, Class serviceInterface,
                      int retryTimes,
                      String codecType, boolean errorExit, int connectionTimeout, int readTimeout) {
        this.servers = servers;
        this.protocol = protocol;
        this.serviceUrl = serviceUrl;
        this.serviceInterface = serviceInterface;
        this.retryTimes = retryTimes;
        this.codecType = codecType;
        this.errorExit = errorExit;
        this.connectionTimeout = connectionTimeout;
        this.readTimeout = readTimeout;

        services = new String[servers.length];
        for (int i = 0; i < servers.length; i++) {
            services[i] = protocol + servers[i] + serviceUrl;
        }
    }

    /**
     * @return
     */
    public static Builder create() {
        return new Builder();
    }

    @Data
    public static final class Builder {

        // 配置的服务器列表，含端口
        private String[] servers;

        // 协议前缀
        private String protocol = "http://";

        // 调用的Url
        private String serviceUrl;

        // 接口
        private Class serviceInterface;

        // 重试次数
        private int retryTimes = 3;

        // codec类型
        private String codecType = "jackson";

        // 出错后是否直接退出,默认是false
        protected boolean errorExit = false;

        // 连接超时，毫秒数
        private int connectionTimeout;

        // 读超时，毫秒数
        private int readTimeout;

        private Builder() {
        }

        public Builder servers(String servers) {
            this.servers = servers.split(",");
            return this;
        }

        public Builder protocol(String protocol) {
            this.protocol = protocol;
            return this;
        }

        public Builder serviceUrl(String serviceUrl) {
            this.serviceUrl = serviceUrl;
            return this;
        }

        public Builder retryTimes(int retryTimes) {
            this.retryTimes = retryTimes;
            return this;
        }

        public Builder serviceInterface(Class serviceInterface) {
            this.serviceInterface = serviceInterface;
            return this;
        }

        public Builder codecType(String codecType) {
            this.codecType = codecType;
            return this;
        }

        public Builder errorExit(boolean errorExit) {
            this.errorExit = errorExit;
            return this;
        }

        public Builder connectionTimeout(int connectionTimeout) {
            this.connectionTimeout = connectionTimeout;
            return this;
        }

        public Builder readTimeout(int readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        private void validate() {
            Preconditions.checkNotNull(servers, "servers can not be null");
            Preconditions.checkNotNull(serviceUrl, "serviceUrl can not be null");
        }

        /**
         * Create the {@link RestClient}.
         */
        public Object build() throws Exception {

            validate();

            RestClient restClient = new RestClient(servers, protocol, serviceUrl, serviceInterface,
                    retryTimes, codecType, errorExit, connectionTimeout, readTimeout);

            return Proxy.newProxyInstance(getClass().getClassLoader(), new Class[] {serviceInterface},
                    new SelectorHandler(restClient));
        }
    }
}

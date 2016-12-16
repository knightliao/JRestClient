package com.github.knightliao.canalx.rest.client.test.core.client;

import org.junit.Assert;
import org.junit.Test;

import com.github.knightliao.canalx.rest.client.core.client.RestClient;

/**
 * @author knightliao
 * @date 2016/12/14 22:54
 */
public class RestClientFailedTestCase {

    @Test
    public void testWithKv() {

        try {

            KvRestClient restClient =
                    (KvRestClient) RestClient.create().servers("127.0.0.1:8398,127.0.0.1:8398")
                            .serviceUrl("/canalx/rest/get")
                            .retryTimes(3)
                            .serviceInterface(KvRestClient.class).build();

            restClient.getDbKv("test.user", "1");

        } catch (Exception e) {

            Assert.assertTrue(true);
        }
    }

}

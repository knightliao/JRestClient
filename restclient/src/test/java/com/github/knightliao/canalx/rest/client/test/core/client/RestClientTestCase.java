package com.github.knightliao.canalx.rest.client.test.core.client;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.github.knightliao.canalx.rest.client.core.client.RestClient;
import com.github.knightliao.canalx.rest.client.test.base.BaseCoreTestCase;
import com.github.knightliao.canalx.rest.client.test.core.client.dto.User;

/**
 * @author knightliao
 * @date 2016/12/14 22:54
 */
public class RestClientTestCase extends BaseCoreTestCase {

    @Test
    public void testWithKv() {

        try {

            KvRestClient restClient =
                    (KvRestClient) RestClient.create().servers("127.0.0.1:8398,127.0.0.1:8398").
                            serviceUrl("/canalx/rest/get")
                            .serviceInterface(KvRestClient.class).build();

            Map<String, String> ret = restClient.getDbKv("test.user", "1");

            Assert.assertEquals(ret.get("id"), 2);
            Assert.assertEquals(ret.get("name"), "ffdd");
            Assert.assertEquals(ret.get("phone"), "");

        } catch (Exception e) {

            Assert.assertTrue(false);
        }
    }

    @Test
    public void testWithObject() {

        try {

            KvRestClient restClient =
                    (KvRestClient) RestClient.create().servers("127.0.0.1:8398,127.0.0.1:8398").serviceUrl("/canalx/rest/get")
                            .serviceInterface(KvRestClient.class).build();

            User user = restClient.getUser("test.user", "1");

            Assert.assertEquals(user.getId(), 2);
            Assert.assertEquals(user.getName(), "ffdd");
            Assert.assertEquals(user.getPhone(), "");

        } catch (Exception e) {

            Assert.assertTrue(false);
        }
    }
}

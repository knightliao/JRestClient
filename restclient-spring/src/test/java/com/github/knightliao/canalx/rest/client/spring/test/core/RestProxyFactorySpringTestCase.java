package com.github.knightliao.canalx.rest.client.spring.test.core;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.knightliao.canalx.rest.client.core.client.RestClient;
import com.github.knightliao.canalx.rest.client.spring.test.base.BaseCoreTestCase;
import com.github.knightliao.canalx.rest.client.spring.test.core.dto.User;

/**
 * @author liaoqiqi
 * @version 2014-9-2
 */
public class RestProxyFactorySpringTestCase extends BaseCoreTestCase {

    private static String[] fn = null;

    // 初始化spring文档
    private static void contextInitialized() {
        fn = new String[] {"applicationContext.xml"};
    }

    /**
     *
     */
    @Test
    public void testWithOK() {

        try {

            contextInitialized();
            ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(fn);

            //
            //
            //
            KvRestClient kvRestClient = (KvRestClient) ctx.getBean("demoServiceDriver");

            User user = kvRestClient.getUser("test.user", "1");

            Assert.assertEquals(user.getId(), 2);
            Assert.assertEquals(user.getName(), "ffdd");
            Assert.assertEquals(user.getPhone(), "");

        } catch (Exception e) {

            Assert.assertTrue(false);
        }

    }

    @Test
    public void testWithObject() {

        try {

            KvRestClient restClient =
                    (KvRestClient) RestClient.create().servers("127.0.0.1:8398,127.0.0.1:8398")
                            .serviceUrl("/canalx/rest/get")
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

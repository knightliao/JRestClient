restclient-spring tutorial
==========================

::

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

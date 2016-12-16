rest-client tutorial
=======

interface: 

	public interface KvRestClient {
    
        @RequestType(type = RequestTypeEnum.GET)
        Map<String, String> getDbKv(@RequestParam(name = "tableId") String tableId,
                                    @RequestParam(name = "key") String key);
    
        @RequestType(type = RequestTypeEnum.GET)
        User getUser(@RequestParam(name = "tableId") String tableId,
                     @RequestParam(name = "key") String key);
    }

example:

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

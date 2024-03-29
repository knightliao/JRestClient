# JRestClient
=======

[![Apache License 2](https://img.shields.io/badge/license-ASF2-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0.txt)
[![Build Status](https://travis-ci.org/knightliao/JRestClient.svg?branch=master)](https://travis-ci.org/knightliao/JRestClient) 
[![Coverage Status](https://coveralls.io/repos/github/knightliao/JRestClient/badge.svg?branch=master)](https://coveralls.io/github/knightliao/JRestClient?branch=master) 
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.knightliao.canalx.rest/restclient/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/com.github.knightliao.canalx.rest/restclient) 
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.knightliao.canalx.rest/restclient-spring/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/com.github.knightliao.canalx.rest/restclient-spring)

rest http client
 
## Func 

- restclient
    - rest http client 
- restclient-spring
    - rest http client using spring 
    
## Quick Start 

interface: 

    public interface KvRestClient {
    
        @RequestType(type = RequestTypeEnum.GET)
        Map<String, String> getDbKv(@RequestParam(name = "tableId") String tableId,
                                    @RequestParam(name = "key") String key);
    
        @RequestType(type = RequestTypeEnum.GET)
        User getUser(@RequestParam(name = "tableId") String tableId,
                     @RequestParam(name = "key") String key);
    }
    
test example:
    
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

doc: http://restclient.readthedocs.io/en/latest/

## Maven

### jutf

    <dependency>
        <groupId>com.github.knightliao.canalx.rest</groupId>
        <artifactId>restclient</artifactId>
        <version>0.0.1</version>
    </dependency>
    
### jutf-Spring

    <dependency>
        <groupId>com.github.knightliao.canalx.rest</groupId>
        <artifactId>restclient-spring</artifactId>
        <version>0.0.1</version>
    </dependency>
    

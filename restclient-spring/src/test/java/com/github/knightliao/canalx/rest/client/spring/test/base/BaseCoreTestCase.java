package com.github.knightliao.canalx.rest.client.spring.test.base;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;

/**
 * @author knightliao
 * @date 2016/12/15 23:49
 */
public class BaseCoreTestCase {

    @ClassRule
    @Rule
    public static WireMockClassRule wireMockRule = new WireMockClassRule(RemoteMockServer.PORT);

    /**
     * 导入配置
     */
    @Before
    public void init() {

        //
        // 设置Mock服务数据
        //
        setupRemoteData();
    }

    /**
     *
     */
    private static void setupRemoteData() {

        //
        // 配置文件
        //
        stubFor(get(urlEqualTo(RemoteMockServer.URL))
                .willReturn(aResponse().withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody(RemoteMockServer.RESULT.getBytes())));

    }

    @Test
    public void pass() {

    }
}

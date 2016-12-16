package com.github.knightliao.canalx.rest.client.test.proxy;

import java.lang.reflect.Proxy;

import org.junit.Test;

/**
 * @author knightliao
 * @date 2016/12/14 20:55
 */
public class ProxyTest {

    @Test
    public void test() {

        Laptop laptop = new Laptop();
        TimeHandler handler = new TimeHandler(laptop);

        IComputer computer = (IComputer) Proxy
                .newProxyInstance(laptop.getClass().getClassLoader(), laptop.getClass().getInterfaces(), handler);

        computer.execute();
    }
}

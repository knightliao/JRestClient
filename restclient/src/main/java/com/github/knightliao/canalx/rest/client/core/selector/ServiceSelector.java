package com.github.knightliao.canalx.rest.client.core.selector;

/**
 *
 */
public interface ServiceSelector {

    Object invoke(boolean errorExit);
}

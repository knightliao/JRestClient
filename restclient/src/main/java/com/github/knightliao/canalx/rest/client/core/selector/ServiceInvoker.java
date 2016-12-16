package com.github.knightliao.canalx.rest.client.core.selector;

import com.github.knightliao.canalx.rest.client.exception.RestServiceException;

/**
 * @author knightliao
 * @date 2016/12/14 23:01
 */
public interface ServiceInvoker {

    Object getInvoker() throws RestServiceException;
}

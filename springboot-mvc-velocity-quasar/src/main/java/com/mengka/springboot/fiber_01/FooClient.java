package com.mengka.springboot.fiber_01;

import com.mengka.springboot.fiber_01.common.FooException;

/**
 * @author huangyy
 * @version cabbage-forward2.0,2018-03-17
 * @since cabbage-forward2.0
 */
public interface FooClient {

    String op(String arg) throws FooException, InterruptedException;
}

package com.mengka.springboot.fiber_01;

import java.util.concurrent.Future;

/**
 * @author huangyy
 * @version cabbage-forward2.0,2018-03-17
 * @since cabbage-forward2.0
 */
public interface AsyncFooClient {

    Future<String> asyncOp(String arg, FooCompletion<String> callback);
}

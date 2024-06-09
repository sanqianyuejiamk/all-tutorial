package com.mengka.springboot.fiber_01;

import com.mengka.springboot.fiber_01.common.FooException;

/**
 * fiber学习
 * http://blog.paralleluniverse.co/2014/08/12/noasync/
 *
 * @author huangyy
 * @version cabbage-forward2.0,2018-03-17
 * @since cabbage-forward2.0
 */
public interface FooCompletion<T> {

    void success(T result);

    void failure(FooException exception);
}
